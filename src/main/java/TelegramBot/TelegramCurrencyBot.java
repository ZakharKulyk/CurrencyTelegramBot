package TelegramBot;


import UserConfiguration.UserConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.HashMap;

public class TelegramCurrencyBot extends TelegramLongPollingBot {

    HashMap<String, UserConfig>dataBase = new HashMap<>();
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        CreatingKeyboards keyboards = new CreatingKeyboards();
        if (IsMessagePresent(update) && update.getMessage().getText().equalsIgnoreCase("/start")){
            String chatId = update.getMessage().getChatId().toString();
            message.setChatId(chatId);
            message.setText(CreatingKeyboards.stringWrapper("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют"));
            message.setReplyMarkup(keyboards.createMainKeyboard());

            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String chatId = callbackQuery.getMessage().getChatId().toString();
            message.setChatId(chatId);

            if (callbackQuery.getData().equals("Settings")){
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }

            if(callbackQuery.getData().equals("NotificationTime")){

            }

            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "https://t.me/JavaCurrency123Bot";
    }

    @Override
    public String getBotToken() {
        return "7434436728:AAGSSBb--F8Q9TZwkh1Ntdw7gWpEmXuX0Ps";
    }

    private static boolean IsMessagePresent(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }
}
