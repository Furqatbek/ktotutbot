package uz.ktotut.bot.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotService {
    void handleUpdate(Update update);
}
