package uz.ktotut.bot.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.ktotut.bot.config.BotConfig;

import java.util.HashMap;
import java.util.Map;

@Service
public class KtoTutBotService {
    private final BotConfig botConfig;
    private final RestTemplate restTemplate;

    public KtoTutBotService(BotConfig botConfig, RestTemplate restTemplate) {
        this.botConfig = botConfig;
        this.restTemplate = new RestTemplate();
    }

    public void sendMessage(String chatId, String message) {
        String url = "https://api.telegram.org/bot" + botConfig.getBotToken() + "/sendMessage";

        Map<String, String> params = new HashMap<>();
        params.put("chat_id", chatId);
        params.put("text", message);

        restTemplate.postForObject(url,params,String.class);
    }

    public String processMessage(String message) {
        message = message.trim().replaceAll("\\s+", "");
        if (message.startsWith("+")) {
            return "https://t.me/" + message;
        } else {
            return "Пожалуйста, отправьте номер в следующем формате: \"+123456789012\".\n";
        }
    }
}

