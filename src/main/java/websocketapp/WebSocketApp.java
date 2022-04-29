package websocketapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableFeignClients
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketApp {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApp.class, args);
    }
}
