package TelegramBot;

import CurrencySort.CurrencySort;
import UserConfiguration.UserConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;

public class TelegramCurrencyBot extends TelegramLongPollingBot {

    HashMap<String, UserConfig> dataBase = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        CreatingKeyboards keyboards = new CreatingKeyboards();

        if (IsMessagePresent(update) && update.getMessage().getText().equalsIgnoreCase("/start")) {
            String chatId = update.getMessage().getChatId().toString();
            message.setChatId(chatId);
            message.setText(CreatingKeyboards.stringWrapper("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют"));
            message.setReplyMarkup(keyboards.createMainKeyboard());

            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String chatId = callbackQuery.getMessage().getChatId().toString();
            message.setChatId(chatId);

            UserConfig userConfig = dataBase.getOrDefault(chatId, new UserConfig());

            switch (callbackQuery.getData()) {
                case "Settings":
                    message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                    message.setReplyMarkup(keyboards.createSettingsKeyboard());
                    break;
                case "Get_info":
                    CurrencySort currencySort = new CurrencySort();
                    currencySort.SortBuySalePrivatUsdValue();
                    message.setText(CreatingKeyboards.stringWrapper("Курс в ПриватБанк: USD/UAH\n" +
                            "Покупка: " + currencySort.getPrivateBuyUsd() + "\n" +
                            "Продаж: " + currencySort.getPrivatSellUsd()));
                    message.setReplyMarkup(keyboards.createMainKeyboard());
                    break;
                case "DigitsAfterDecimal":
                    message.setText(CreatingKeyboards.stringWrapper("Вкажіть кількість знаків після коми"));
                    message.setReplyMarkup(keyboards.createDecimalPlacesKeyboard());
                    break;
                case "DigitsAfterDecimal2":
                    userConfig.setDecimal(2);
                    dataBase.put(chatId, userConfig);
                    message.setText(CreatingKeyboards.stringWrapper("Значення встановлено: 2 знаки після коми"));
                    break;
                case "DigitsAfterDecimal3":
                    userConfig.setDecimal(3);
                    dataBase.put(chatId, userConfig);
                    message.setText(CreatingKeyboards.stringWrapper("Значення встановлено: 3 знаки після коми"));
                    break;
                case "DigitsAfterDecimal4":
                    userConfig.setDecimal(4);
                    dataBase.put(chatId, userConfig);
                    message.setText(CreatingKeyboards.stringWrapper("Значення встановлено: 4 знаки після коми"));
                    break;
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
        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }

    private static boolean IsMessagePresent(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }
}
