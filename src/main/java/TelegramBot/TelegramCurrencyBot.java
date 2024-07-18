package TelegramBot;


import UserConfiguration.UserConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;

public class TelegramCurrencyBot extends TelegramLongPollingBot {

 HashMap<String, UserConfig>dataBase = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return super.getBotToken();
    }
}
