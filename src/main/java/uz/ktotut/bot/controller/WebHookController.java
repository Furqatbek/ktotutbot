package uz.ktotut.bot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.ktotut.bot.service.impl.KtoTutBotService;

@RestController
@RequestMapping("/webhook")
public class WebHookController {

    private final KtoTutBotService botService;

    public WebHookController(KtoTutBotService botService) {
        this.botService = botService;
    }


    @PostMapping
    public void handleUpdate(@RequestBody Update update) {
        System.out.println("Update: " + update);
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String response = botService.processMessage(text);
            botService.sendMessage(String.valueOf(chatId), response);
        }
    }
}
