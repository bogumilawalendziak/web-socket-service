package websocketapp.service;

import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import websocketapp.model.Music;
import websocketapp.model.MusicDTO;
import websocketapp.model.Status;
import websocketapp.repository.MusicRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class MusicService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicService.class);
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final JsoupDownloader jsoupDownloader;

    private final MusicRepository musicRepository;
    private static String URL = "https://myradioonline.pl/radio-nowy-swiat/teraz-gramy";


    public void checkMusic() {

        Document document = jsoupDownloader.getDocument(URL);
        String author = getValue(document).split("-")[0];
        String tittle = getValue(document).split("-")[1];
        Status status;

        if(author==null || tittle == null) status = Status.ERROR;
        else status = Status.SUCCESS;

        MusicDTO musicDTO = new MusicDTO(tittle,author);

        if(findByExample(musicDTO).isEmpty()) saveToDb(author,tittle, status);
        sendMessage(tittle+" - " + author);
    }

    private Music saveToDb(String author, String tittle, Status status) {
        Music music = new Music();
        music.setDate(String.valueOf(new Date()));
        music.setSinger(author);
        music.setTittle(tittle);
        music.setStatus(status);
        LOGGER.info("Saving new music to DB");
       return musicRepository.save(music);
    }

    private String getValue(Document document) {

        Elements elements = document.getElementsByAttributeValue("class", "actSong");
        Optional<Elements> element = elements.stream().map(s -> s.getElementsByTag("a")).findFirst();

        return element.map(Elements::text).orElse(null);
    }


    private void sendMessage(String music) {

        simpMessagingTemplate.convertAndSend("/topic/music", music);
    }

    public List<Music> findByExample(MusicDTO musicDTO) {
        Music music = new Music();
        music.setSinger(musicDTO.getSinger());
        music.setTittle(musicDTO.getTittle());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("singer", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("tittle", ExampleMatcher.GenericPropertyMatcher::storeDefaultMatching);
        Example<Music> example = Example.of(music, matcher);

        return musicRepository.findAll(example);
    }

}
