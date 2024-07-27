package telegramBot;


import сonstants.ConstantForDevProcess;
import userConfiguration.UserConfig;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import static сonstants.ConstantForDevProcess.*;

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
        nine.setCallbackData("9:00");


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

        InlineKeyboardButton digitsAfterDecimalButton = new InlineKeyboardButton();
        digitsAfterDecimalButton.setText(stringWrapper("Кількість знаків після коми"));
        digitsAfterDecimalButton.setCallbackData(ConstantForDevProcess.DIGITS_AFTER_DECIMAL_CALLBACK_DATA);

        InlineKeyboardButton bankButton = new InlineKeyboardButton();
        bankButton.setText(stringWrapper("Банк"));
        bankButton.setCallbackData(BANK);

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

    public InlineKeyboardMarkup bankKeyboard(UserConfig userConfig) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton monoBank = new InlineKeyboardButton();
        monoBank.setText(isContain(userConfig.getBanks(), MONO_BANK));
        monoBank.setCallbackData(MONO_BANK);

        InlineKeyboardButton privatBank = new InlineKeyboardButton();
        privatBank.setText(isContain(userConfig.getBanks(), PRIVAT_BANK));
        privatBank.setCallbackData(PRIVAT_BANK);

        InlineKeyboardButton nbuBank = new InlineKeyboardButton();
        nbuBank.setText(isContain(userConfig.getBanks(), stringWrapper(NBU_BANK)));
        nbuBank.setCallbackData(NBU_CALLBACK_DATA);

        List<InlineKeyboardButton> monoRow = new ArrayList<>();
        monoRow.add(monoBank);

        List<InlineKeyboardButton> privatRow = new ArrayList<>();
        privatRow.add(privatBank);

        List<InlineKeyboardButton> nbuRow = new ArrayList<>();
        nbuRow.add(nbuBank);

        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        allButtons.add(monoRow);
        allButtons.add(privatRow);
        allButtons.add(nbuRow);

        inlineKeyboardMarkup.setKeyboard(allButtons);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup createDecimalPlacesKeyboard(UserConfig userConfig) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton digitsAfterDecimalButtonTwo = new InlineKeyboardButton();
        digitsAfterDecimalButtonTwo.setText(isContain(userConfig.getDecimalPlaces(), "2"));
        digitsAfterDecimalButtonTwo.setCallbackData(DIGITS_AFTER_DECIMAL2);

        InlineKeyboardButton digitsAfterDecimalButtonThree = new InlineKeyboardButton();
        digitsAfterDecimalButtonThree.setText(isContain(userConfig.getDecimalPlaces(), "3"));
        digitsAfterDecimalButtonThree.setCallbackData(DIGITS_AFTER_DECIMAL3);

        InlineKeyboardButton digitsAfterDecimalButtonFour = new InlineKeyboardButton();
        digitsAfterDecimalButtonFour.setText(isContain(userConfig.getDecimalPlaces(), "4"));
        digitsAfterDecimalButtonFour.setCallbackData(DIGITS_AFTER_DECIMAL4);

        List<InlineKeyboardButton> buttonTwo = new ArrayList<>();
        buttonTwo.add(digitsAfterDecimalButtonTwo);
        List<InlineKeyboardButton> buttonThree = new ArrayList<>();
        buttonThree.add(digitsAfterDecimalButtonThree);
        List<InlineKeyboardButton> buttonFour = new ArrayList<>();
        buttonFour.add(digitsAfterDecimalButtonFour);

        List<List<InlineKeyboardButton>> allButton = new ArrayList<>();
        allButton.add(buttonTwo);
        allButton.add(buttonThree);
        allButton.add(buttonFour);

        inlineKeyboardMarkup.setKeyboard(allButton);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup createCurrencyKeyboard(UserConfig userConfig) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton dollar = new InlineKeyboardButton();
        InlineKeyboardButton euro = new InlineKeyboardButton();

        dollar.setText(isContain(userConfig.getCurrentCurrencies(), DOLLAR));
        dollar.setCallbackData(DOLLAR);

        euro.setText(isContain(userConfig.getCurrentCurrencies(), EURO));
        euro.setCallbackData(EURO);

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        row.add(dollar);
        row.add(euro);

        rows.add(row);

        inlineKeyboardMarkup.setKeyboard(rows);

        return inlineKeyboardMarkup;

    }


    public static String stringWrapper(String str) {
        String result = "";
        try {
            result = new String(str.getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding is not supported", e);
        }
        return result;
    }


    public static String isContain(List<String> bankList, String word) {
        if (bankList.contains(word)) {
            return stringWrapper("✅") + word;
        }
        return word;
    }


}
