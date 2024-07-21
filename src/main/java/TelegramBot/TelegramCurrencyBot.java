package TelegramBot;


import CurrencySort.CurrencySort;
import UserConfiguration.UserConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.HashMap;

import static Constants.ConstansDev.*;

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
            if (!dataBase.containsKey(chatId)) {
                dataBase.put(chatId, new UserConfig());
            }

            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String chatId = callbackQuery.getMessage().getChatId().toString();
            message.setChatId(chatId);
            UserConfig userConfig = dataBase.get(chatId);

            if (callbackQuery.getData().equals(SETTINGS)){
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }
            if (callbackQuery.getData().equals(BANK)){
                message.setText(CreatingKeyboards.stringWrapper("Оберіть банк: "));
                message.setReplyMarkup(keyboards.bankKeyboard(userConfig));
            }
            if (callbackQuery.getData().equals(MONO_BANK)){
                if (userConfig.getBanks().contains(MONO_BANK)){
                    userConfig.removeBank(MONO_BANK);
                }else {
                    userConfig.addBank(MONO_BANK);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }
            if (callbackQuery.getData().equals(PRIVAT_BANK)){
                if (userConfig.getBanks().contains(PRIVAT_BANK)){
                    userConfig.removeBank(PRIVAT_BANK);
                }else {
                    userConfig.addBank(PRIVAT_BANK);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }
            if (callbackQuery.getData().equals(NBU_CALLBACK_DATA)){
                if (userConfig.getBanks().contains(CreatingKeyboards.stringWrapper(NBU_BANK))){
                    userConfig.removeBank(CreatingKeyboards.stringWrapper(NBU_BANK));
                }else {
                    userConfig.addBank(CreatingKeyboards.stringWrapper(NBU_BANK));
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }
            if(callbackQuery.getData().equals(GET_INFO_CALLBACK_DATA))
            {
                CurrencySort currencySort = new CurrencySort();
                currencySort.SortBuySalePrivatUsdValue();
                message.setText(CreatingKeyboards.stringWrapper("Курс в ПриватБанк: USD/UAH\n" +
                        "Покупка: " + currencySort.getPrivateBuyUsd() + "\n" +
                        "Продаж: " + currencySort.getPrivatSellUsd()));
                message.setReplyMarkup(keyboards.createMainKeyboard());
            }

            if(callbackQuery.getData().equals(DIGITS_AFTER_DECIMAL_CALLBACK_DATA))
            {
                message.setText(CreatingKeyboards.stringWrapper("Вкажіть кількість знаків після коми"));
                message.setReplyMarkup(keyboards.createDecimalPlacesKeyboard(userConfig));
            }
            if (callbackQuery.getData().equals(DIGITS_AFTER_DECIMAL2)) {
                if (userConfig.getDecimalPlaces().contains("2")) {
                    userConfig.removeDigitsAfterDecimalPlace("2");
                } else {
                    userConfig.addDigitsAfterDecimalPlace("2");
                    userConfig.setDecimal(2);
                }
                message.setText(CreatingKeyboards.stringWrapper("Значення встановлено: 2 знаки після коми"));
            } else if (callbackQuery.getData().equals(DIGITS_AFTER_DECIMAL3)) {
                if (userConfig.getDecimalPlaces().contains("3")) {
                    userConfig.removeDigitsAfterDecimalPlace("3");
                } else {
                    userConfig.addDigitsAfterDecimalPlace("3");
                    userConfig.setDecimal(3);
                }
                message.setText(CreatingKeyboards.stringWrapper("Значення встановлено: 3 знаки після коми"));
            } else if (callbackQuery.getData().equals(DIGITS_AFTER_DECIMAL4)) {
                if (userConfig.getDecimalPlaces().contains("4")) {
                    userConfig.removeDigitsAfterDecimalPlace("4");
                } else {
                    userConfig.addDigitsAfterDecimalPlace("4");
                    userConfig.setDecimal(4);
                }
                message.setText(CreatingKeyboards.stringWrapper("Значення встановлено: 4 знаки після коми"));
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
        return "@Dddrcresbot";
    }

    @Override
    public String getBotToken() {
        return "7050193955:AAHqGVe-jZi2zT5sIobN0yPvwzNg-v2s0zc";
    }

    private static boolean IsMessagePresent(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }
}
