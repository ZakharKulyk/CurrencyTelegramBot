package telegramBot;


import сonstants.ConstantForDevProcess;
import userConfiguration.UserConfig;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;



public class CreatingKeyboards {
    public ReplyKeyboard createMainKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton getInfoButton = new InlineKeyboardButton();
        getInfoButton.setText(stringWrapper("Отримати інформацію") + "\uD83D\uDCCA");
        getInfoButton.setCallbackData(ConstantForDevProcess.GET_INFO_CALLBACK_DATA);

        InlineKeyboardButton settingsButton = new InlineKeyboardButton();
        settingsButton.setText(stringWrapper("Налаштування") + "\u2699");
        settingsButton.setCallbackData(ConstantForDevProcess.SETTINGS);

        List<InlineKeyboardButton> mainMenuKeyboard = new ArrayList<>();
        mainMenuKeyboard.add(getInfoButton);
        mainMenuKeyboard.add(settingsButton);

        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        allButtons.add(mainMenuKeyboard);

        inlineKeyboardMarkup.setKeyboard(allButtons);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup createTimeNotificationKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        InlineKeyboardButton nine = new InlineKeyboardButton();
        nine.setText("9:00");
        nine.setCallbackData("09:00");


        InlineKeyboardButton ten = new InlineKeyboardButton();
        ten.setText("10:00");
        ten.setCallbackData("10:00");

        InlineKeyboardButton eleven = new InlineKeyboardButton();
        eleven.setText("11:00");
        eleven.setCallbackData("11:00");

        InlineKeyboardButton twelve = new InlineKeyboardButton();
        twelve.setText("12:00");
        twelve.setCallbackData("12:00");

        InlineKeyboardButton thirteen = new InlineKeyboardButton();
        thirteen.setText("13:00");
        thirteen.setCallbackData("13:00");

        InlineKeyboardButton fourteen = new InlineKeyboardButton();
        fourteen.setText("14:00");
        fourteen.setCallbackData("14:00");

        InlineKeyboardButton fifteen = new InlineKeyboardButton();
        fifteen.setText("15:00");
        fifteen.setCallbackData("15:00");

        InlineKeyboardButton sixteen = new InlineKeyboardButton();
        sixteen.setText("16:00");
        sixteen.setCallbackData("16:00");

        InlineKeyboardButton seventeen = new InlineKeyboardButton();
        seventeen.setText("17:00");
        seventeen.setCallbackData("17:00");

        InlineKeyboardButton eighteen = new InlineKeyboardButton();
        eighteen.setText("18:00");
        eighteen.setCallbackData("18:00");

        InlineKeyboardButton turnOff = new InlineKeyboardButton();
        turnOff.setText(stringWrapper("Вимкнути повідомлення"));
        turnOff.setCallbackData("turnOffNotifications");

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        List<InlineKeyboardButton> fourthRow = new ArrayList<>();

        firstRow.add(nine);
        firstRow.add(ten);
        firstRow.add(eleven);

        secondRow.add(twelve);
        secondRow.add(thirteen);
        secondRow.add(fourteen);

        thirdRow.add(fifteen);
        thirdRow.add(sixteen);
        thirdRow.add(seventeen);

        fourthRow.add(eighteen);
        fourthRow.add(turnOff);

        rows.add(firstRow);
        rows.add(secondRow);
        rows.add(thirdRow);
        rows.add(fourthRow);

        inlineKeyboardMarkup.setKeyboard(rows);

        return inlineKeyboardMarkup;

    }

    public InlineKeyboardMarkup createSettingsKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();

        allButtons.add(createButtonRow("Кількість знаків після коми", ConstantForDevProcess.DIGITS_AFTER_DECIMAL_CALLBACK_DATA));
        allButtons.add(createButtonRow("Банк", ConstantForDevProcess.BANK));
        allButtons.add(createButtonRow("Валюти", "Currencies"));
        allButtons.add(createButtonRow("Час оповіщень", "NotificationTime"));
        allButtons.add(createButtonRow("Назад", "BackToMainMenu"));

        inlineKeyboardMarkup.setKeyboard(allButtons);
        return inlineKeyboardMarkup;
    }


    public InlineKeyboardMarkup bankKeyboard(UserConfig userConfig) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();

        allButtons.add(createButtonRow(isContain(userConfig.getBanks(), ConstantForDevProcess.MONO_BANK), ConstantForDevProcess.MONO_BANK));
        allButtons.add(createButtonRow(isContain(userConfig.getBanks(), ConstantForDevProcess.PRIVAT_BANK), ConstantForDevProcess.PRIVAT_BANK));
        allButtons.add(createButtonRow(isContain(userConfig.getBanks(), ConstantForDevProcess.NBU_BANK), ConstantForDevProcess.NBU_CALLBACK_DATA));

        inlineKeyboardMarkup.setKeyboard(allButtons);
        return inlineKeyboardMarkup;
    }


    public InlineKeyboardMarkup createDecimalPlacesKeyboard(UserConfig userConfig) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();

        allButtons.add(createButtonRow(isContain(userConfig.getDecimalPlaces(), "2"), ConstantForDevProcess.DIGITS_AFTER_DECIMAL2));
        allButtons.add(createButtonRow(isContain(userConfig.getDecimalPlaces(), "3"), ConstantForDevProcess.DIGITS_AFTER_DECIMAL3));
        allButtons.add(createButtonRow(isContain(userConfig.getDecimalPlaces(), "4"), ConstantForDevProcess.DIGITS_AFTER_DECIMAL4));

        inlineKeyboardMarkup.setKeyboard(allButtons);
        return inlineKeyboardMarkup;
    }


    public InlineKeyboardMarkup createCurrencyKeyboard(UserConfig userConfig) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        rows.add(createButtonRow(isContain(userConfig.getCurrentCurrencies(), ConstantForDevProcess.DOLLAR), ConstantForDevProcess.DOLLAR, isContain(userConfig.getCurrentCurrencies(), ConstantForDevProcess.EURO), ConstantForDevProcess.EURO));

        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }


    private List<InlineKeyboardButton> createButtonRow(String text1, String callbackData1) {
        InlineKeyboardButton button = createButton(text1, callbackData1);
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(button);
        return row;
    }

    private List<InlineKeyboardButton> createButtonRow(String text1, String callbackData1, String text2, String callbackData2) {
        InlineKeyboardButton button1 = createButton(text1, callbackData1);
        InlineKeyboardButton button2 = createButton(text2, callbackData2);
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(button1);
        row.add(button2);
        return row;
    }

    private InlineKeyboardButton createButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(stringWrapper(text));
        button.setCallbackData(callbackData);
        return button;
    }


    public static String stringWrapper(String str) {
        String result = " ";
        try {
            result = new String(str.getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding is not supported", e);
        }
        return result;
    }

    public static String isContain(List<String> bankList, String word) {
        if (bankList.contains(word)) {
            return "✅" + word;
        }
        return word;
    }





}
