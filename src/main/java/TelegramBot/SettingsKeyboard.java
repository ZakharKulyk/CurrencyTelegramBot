package TelegramBot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class SettingsKeyboard {
    public InlineKeyboardMarkup createSettingsKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton digitsAfterDecimalButton = new InlineKeyboardButton();
        digitsAfterDecimalButton.setText("Кількість знаків після коми");
        digitsAfterDecimalButton.setCallbackData("DigitsAfterDecimal");

        InlineKeyboardButton bankButton = new InlineKeyboardButton();
        bankButton.setText("Банк");
        bankButton.setCallbackData("Bank");

        InlineKeyboardButton currenciesButton = new InlineKeyboardButton();
        currenciesButton.setText("Валюти");
        currenciesButton.setCallbackData("Currencies");

        InlineKeyboardButton notificationTimeButton = new InlineKeyboardButton();
        notificationTimeButton.setText("Час оповіщень");
        notificationTimeButton.setCallbackData("NotificationTime");

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText("Назад");
        backButton.setCallbackData("BackToMainMenu");

        List<InlineKeyboardButton> settingsMenuKeyboard = new ArrayList<>();
        settingsMenuKeyboard.add(digitsAfterDecimalButton);
        settingsMenuKeyboard.add(bankButton);
        settingsMenuKeyboard.add(currenciesButton);
        settingsMenuKeyboard.add(notificationTimeButton);
        settingsMenuKeyboard.add(backButton);

        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        allButtons.add(settingsMenuKeyboard);

        inlineKeyboardMarkup.setKeyboard(allButtons);
        return inlineKeyboardMarkup;
    }
}
