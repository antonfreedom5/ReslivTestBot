package ForReslivTestBot.Bot.Controller;

import ForReslivTestBot.Bot.Service.CityService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BotController extends TelegramLongPollingBot {

    @Autowired
    CityService cityService;

    @Getter
    @Value("${bot.name}")
    private String BotUsername;

    @Getter
    @Value("${bot.token}")
    private String BotToken;

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText() && cityService.existByName(message.getText())) {
            sendTextMessage(message.getChatId(), cityService.getCityByName(message.getText()).getInformation());
        } else {
            sendTextMessage(message.getChatId(), "Нет информации об этом городе!");
        }
    }

    public void sendTextMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.getStackTrace();
        }
    }

}
