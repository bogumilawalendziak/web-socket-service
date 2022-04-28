package websocketapp.service;

import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import websocketapp.model.Music;
import websocketapp.model.Status;
import websocketapp.repository.MusicRepository;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MusicService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final JsoupDownloader jsoupDownloader;

    private final MusicRepository musicRepository;
    private static String URL = "  https://nowyswiat.online/";
    private static String tittleId = "qtmplayer__title proradio-marquee";
    private static String authorId = "qtmplayer__artist proradio-marquee";


    public void checkMusic() {

        Document document = jsoupDownloader.getDocument(URL);
        String author = getValue(document,authorId);
        String tittle = getValue(document,tittleId);
        Status status;

        if(author==null || tittle == null) status = Status.ERROR;
        else status = Status.SUCCESS;
        Music music = saveToDb(author,tittle, status);
        sendMessage(music.getTittle()+" - " + music.getSinger());
    }

    private Music saveToDb(String author, String tittle, Status status) {
        Music music = new Music();
        music.setDate(String.valueOf(new Date()));
        music.setSinger(author);
        music.setTittle(tittle);
        music.setStatus(status);
       return musicRepository.save(music);
    }

    private String getValue(Document document, String fieldId) {

        Elements elements = document.getElementsByAttributeValue("class", fieldId);
        Optional<Elements> element = elements.stream().map(s -> s.getElementsByTag("span")).findFirst();

        return element.map(Elements::text).orElse(null);
    }


    private void sendMessage(String music) {

        simpMessagingTemplate.convertAndSend("/topic/music", music);
    }

}
