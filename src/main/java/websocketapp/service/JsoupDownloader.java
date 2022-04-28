package websocketapp.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsoupDownloader {

    Logger LOGGER = LoggerFactory.getLogger(JsoupDownloader.class);
    private final static String requestUserAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36";

    public Document getDocument(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).timeout(60000).userAgent(requestUserAgent).followRedirects(true).get();
        } catch (IOException e) {
            LOGGER.error("Couldnt downad page source: "+url);
        }

        return document;
    }
}
