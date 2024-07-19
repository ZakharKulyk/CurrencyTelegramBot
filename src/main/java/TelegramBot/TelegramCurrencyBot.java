package TelegramBot;


import UserConfiguration.UserConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TelegramCurrencyBot extends TelegramLongPollingBot {

 HashMap<String, UserConfig>dataBase = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        if (IsMessagePresent(update) && update.getMessage().getText().equalsIgnoreCase("/start")){
            SendMessage message = new SendMessage();
            String chatId = update.getMessage().getChatId().toString();
            message.setChatId(chatId);
            message.setText(stringWrapper("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют"));
            message.setReplyMarkup(createKeyboard());

            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return super.getBotToken();
    }

    private static boolean IsMessagePresent(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }
    private ReplyKeyboard createKeyboard(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton getInfoButton = new InlineKeyboardButton();
        getInfoButton.setText(stringWrapper("Отримати інформацію") + "\uD83D\uDCCA");
        getInfoButton.setCallbackData("Get_info");

        InlineKeyboardButton settingsButton = new InlineKeyboardButton();
        settingsButton.setText(stringWrapper("Налаштування") + "\u2699");
        settingsButton.setCallbackData("Settings");

        List<InlineKeyboardButton> mainMenuKeyboard = new ArrayList<>();
        mainMenuKeyboard.add(getInfoButton);
        mainMenuKeyboard.add(settingsButton);

        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        allButtons.add(mainMenuKeyboard);

        inlineKeyboardMarkup.setKeyboard(allButtons);
        return inlineKeyboardMarkup;
    }
    public static String stringWrapper(String str){
        String result = "";
        try {
            result = new String(str.getBytes() , "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding is not supported", e);
        }
        return result;
    }
}
