package TelegramBot;

import UserConfiguration.UserConfig;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static Constants.ConstansDev.*;

public class CreatingKeyboards {

    public InlineKeyboardMarkup createMainKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        List<InlineKeyboardButton> mainMenuKeyboard = new ArrayList<>();

        mainMenuKeyboard.add(createInlineKeyboardButton(stringWrapper("Отримати інформацію") + "\uD83D\uDCCA", GET_INFO_CALLBACK_DATA));
        mainMenuKeyboard.add(createInlineKeyboardButton(stringWrapper("Налаштування") + "\u2699", SETTINGS));

        allButtons.add(mainMenuKeyboard);
        inlineKeyboardMarkup.setKeyboard(allButtons);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup createTimeNotificationKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (int hour = 9; hour <= 18; hour++) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(hour + ":00");
            button.setCallbackData(hour + ":00");
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(button);
            rows.add(row);
        }

        InlineKeyboardButton turnOff = new InlineKeyboardButton();
        turnOff.setText(stringWrapper("Вимкнути повідомлення"));
        turnOff.setCallbackData("turnOffNotifications");

        List<InlineKeyboardButton> fourthRow = new ArrayList<>();
        fourthRow.add(turnOff);
        rows.add(fourthRow);

        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup createSettingsKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();

        allButtons.add(createRowWithSingleButton(stringWrapper("Кількість знаків після коми"), DIGITS_AFTER_DECIMAL_CALLBACK_DATA));
        allButtons.add(createRowWithSingleButton(stringWrapper("Банк"), BANK));
        allButtons.add(createRowWithSingleButton(stringWrapper("Валюти"), "Currencies"));
        allButtons.add(createRowWithSingleButton(stringWrapper("Час оповіщень"), "NotificationTime"));
        allButtons.add(createRowWithSingleButton(stringWrapper("Назад"), "BackToMainMenu"));

        inlineKeyboardMarkup.setKeyboard(allButtons);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup bankKeyboard(UserConfig userConfig) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        allButtons.add(createRowWithSingleButton(isContain(userConfig.getBanks(), stringWrapper(MONO_BANK)), MONO_BANK));
        allButtons.add(createRowWithSingleButton(isContain(userConfig.getBanks(), stringWrapper(PRIVAT_BANK)), PRIVAT_BANK));
        allButtons.add(createRowWithSingleButton(isContain(userConfig.getBanks(), stringWrapper(NBU_BANK)), NBU_CALLBACK_DATA));

        inlineKeyboardMarkup.setKeyboard(allButtons);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup createDecimalPlacesKeyboard(UserConfig userConfig) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        allButtons.add(createRowWithSingleButton(isContain(userConfig.getDecimalPlaces(), "2"), DIGITS_AFTER_DECIMAL2));
        allButtons.add(createRowWithSingleButton(isContain(userConfig.getDecimalPlaces(), "3"), DIGITS_AFTER_DECIMAL3));
        allButtons.add(createRowWithSingleButton(isContain(userConfig.getDecimalPlaces(), "4"), DIGITS_AFTER_DECIMAL4));

        inlineKeyboardMarkup.setKeyboard(allButtons);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup createCurrencyKeyboard(UserConfig userConfig) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        allButtons.add(createRowWithSingleButton("USD", DOLLAR, userConfig.isSelected("USD")));
        allButtons.add(createRowWithSingleButton("EUR", EURO, userConfig.isSelected("EUR")));

        inlineKeyboardMarkup.setKeyboard(allButtons);
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardButton createInlineKeyboardButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }

    private InlineKeyboardButton createInlineKeyboardButton(String text, String callbackData, boolean isSelected) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text + (isSelected ? " \u2705" : ""));
        button.setCallbackData(callbackData);
        return button;
    }

    private List<InlineKeyboardButton> createRowWithSingleButton(String buttonText, String callbackData) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(createInlineKeyboardButton(buttonText, callbackData));
        return row;
    }

    private List<InlineKeyboardButton> createRowWithSingleButton(String buttonText, String callbackData, boolean isSelected) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(createInlineKeyboardButton(buttonText, callbackData, isSelected));
        return row;
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

    public static String isContain(List<String> bankList, String word) {
        return bankList.contains(word) ? stringWrapper("✅") + word : word;
    }
}

