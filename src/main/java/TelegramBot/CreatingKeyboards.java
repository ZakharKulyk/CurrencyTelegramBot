package TelegramBot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CreatingKeyboards {
    public ReplyKeyboard createMainKeyboard(){
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
    public InlineKeyboardMarkup createSettingsKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton digitsAfterDecimalButton = new InlineKeyboardButton();
        digitsAfterDecimalButton.setText(stringWrapper("Кількість знаків після коми"));
        digitsAfterDecimalButton.setCallbackData("DigitsAfterDecimal");

        InlineKeyboardButton bankButton = new InlineKeyboardButton();
        bankButton.setText(stringWrapper("Банк"));
        bankButton.setCallbackData("Bank");

        InlineKeyboardButton currenciesButton = new InlineKeyboardButton();
        currenciesButton.setText(stringWrapper("Валюти"));
        currenciesButton.setCallbackData("Currencies");

        InlineKeyboardButton notificationTimeButton = new InlineKeyboardButton();
        notificationTimeButton.setText(stringWrapper("Час оповіщень"));
        notificationTimeButton.setCallbackData("NotificationTime");

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText(stringWrapper("Назад"));
        backButton.setCallbackData("BackToMainMenu");

        List<InlineKeyboardButton> digitsRow = new ArrayList<>();
        digitsRow.add(digitsAfterDecimalButton);

        List<InlineKeyboardButton> bankRow = new ArrayList<>();
        bankRow.add(bankButton);

        List<InlineKeyboardButton> currenciesRow = new ArrayList<>();
        currenciesRow.add(currenciesButton);

        List<InlineKeyboardButton> notificationRow = new ArrayList<>();
        notificationRow.add(notificationTimeButton);

        List<InlineKeyboardButton> backRow = new ArrayList<>();
        backRow.add(backButton);

        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        allButtons.add(digitsRow);
        allButtons.add(bankRow);
        allButtons.add(currenciesRow);
        allButtons.add(notificationRow);
        allButtons.add(backRow);

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
