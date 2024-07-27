package telegramBot;

import currencySort.CurrencySort;
import userConfiguration.UserConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static сonstants.ConstantForDevProcess.*;

public class TelegramCurrencyBot extends TelegramLongPollingBot {

    private final HashMap<String, UserConfig> dataBase = new HashMap<>();
    private final CurrencySort currencySort = new CurrencySort();
    private final CreatingKeyboards keyboards = new CreatingKeyboards();

    @Override
    public void onUpdateReceived(Update update) {
        if (isMessagePresent(update) && update.getMessage().getText().equalsIgnoreCase("/start")) {
            handleStartCommand(update);
        } else if (update.hasCallbackQuery()) {
            handleCallbackQuery(update.getCallbackQuery());


        }
    }

    private void handleStartCommand(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(CreatingKeyboards.stringWrapper("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют"));
        message.setReplyMarkup(keyboards.createMainKeyboard());

        if (!dataBase.containsKey(chatId)) {
            dataBase.put(chatId, new UserConfig());
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void handleCallbackQuery(CallbackQuery callbackQuery) {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        UserConfig userConfig = dataBase.get(chatId);

        if (callbackQuery.getData().equals(DIGITS_AFTER_DECIMAL_CALLBACK_DATA)) {
            message.setText(CreatingKeyboards.stringWrapper("Вкажіть кількість знаків після коми"));
            message.setReplyMarkup(keyboards.createDecimalPlacesKeyboard(userConfig));
        }
        if (callbackQuery.getData().equals(DIGITS_AFTER_DECIMAL2)) {
            if (userConfig.getDecimalPlaces().contains("4")) {
                userConfig.removeDigitsAfterDecimalPlace("4");
                if (userConfig.getDecimalPlaces().contains("2")) {
                    userConfig.removeDigitsAfterDecimalPlace("2");
                    userConfig.setDecimal(1);
                } else {
                    userConfig.addDigitsAfterDecimalPlace("2");
                    userConfig.setDecimal(2);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            } else if (userConfig.getDecimalPlaces().contains("3")) {
                userConfig.removeDigitsAfterDecimalPlace("3");
                if (userConfig.getDecimalPlaces().contains("2")) {
                    userConfig.removeDigitsAfterDecimalPlace("2");
                    userConfig.setDecimal(1);
                } else {
                    userConfig.addDigitsAfterDecimalPlace("2");
                    userConfig.setDecimal(2);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            } else {
                if (userConfig.getDecimalPlaces().contains("2")) {
                    userConfig.removeDigitsAfterDecimalPlace("2");
                    userConfig.setDecimal(1);
                } else {
                    userConfig.addDigitsAfterDecimalPlace("2");
                    userConfig.setDecimal(2);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }
        } else if (callbackQuery.getData().equals(DIGITS_AFTER_DECIMAL3)) {
            if (userConfig.getDecimalPlaces().contains("2")) {
                userConfig.removeDigitsAfterDecimalPlace("2");
                if (userConfig.getDecimalPlaces().contains("3")) {
                    userConfig.removeDigitsAfterDecimalPlace("3");
                    userConfig.setDecimal(1);
                } else {
                    userConfig.addDigitsAfterDecimalPlace("3");
                    userConfig.setDecimal(3);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            } else if (userConfig.getDecimalPlaces().contains("4")) {
                userConfig.removeDigitsAfterDecimalPlace("4");
                if (userConfig.getDecimalPlaces().contains("3")) {
                    userConfig.removeDigitsAfterDecimalPlace("3");
                    userConfig.setDecimal(1);
                } else {
                    userConfig.addDigitsAfterDecimalPlace("3");
                    userConfig.setDecimal(3);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            } else {
                if (userConfig.getDecimalPlaces().contains("3")) {
                    userConfig.removeDigitsAfterDecimalPlace("3");
                    userConfig.setDecimal(1);
                } else {
                    userConfig.addDigitsAfterDecimalPlace("3");
                    userConfig.setDecimal(3);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }
        } else if (callbackQuery.getData().equals(DIGITS_AFTER_DECIMAL4)) {
            if (userConfig.getDecimalPlaces().contains("2")) {
                userConfig.removeDigitsAfterDecimalPlace("2");
                if (userConfig.getDecimalPlaces().contains("4")) {
                    userConfig.removeDigitsAfterDecimalPlace("4");
                    userConfig.setDecimal(1);
                } else {
                    userConfig.addDigitsAfterDecimalPlace("4");
                    userConfig.setDecimal(4);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            } else if (userConfig.getDecimalPlaces().contains("3")) {
                userConfig.removeDigitsAfterDecimalPlace("3");
                if (userConfig.getDecimalPlaces().contains("4")) {
                    userConfig.removeDigitsAfterDecimalPlace("4");
                    userConfig.setDecimal(1);
                } else {
                    userConfig.addDigitsAfterDecimalPlace("4");
                    userConfig.setDecimal(4);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            } else {
                if (userConfig.getDecimalPlaces().contains("4")) {
                    userConfig.removeDigitsAfterDecimalPlace("4");
                    userConfig.setDecimal(1);
                } else {
                    userConfig.addDigitsAfterDecimalPlace("4");
                    userConfig.setDecimal(4);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }
        }


        switch (callbackQuery.getData()) {
            case SETTINGS:
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
                break;
            case NOTIFICATION_TIME:
                message.setText(CreatingKeyboards.stringWrapper("Оберіть час для повідомлення"));
                message.setReplyMarkup(keyboards.createTimeNotificationKeyboard());
                break;
            case BANK:
                message.setText(CreatingKeyboards.stringWrapper("Оберіть банк: "));
                message.setReplyMarkup(keyboards.bankKeyboard(userConfig));
                break;
            case MONO_BANK:
            case PRIVAT_BANK:
            case NBU_CALLBACK_DATA:
                toggleBank(callbackQuery.getData(), userConfig);
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
                break;
            case GET_INFO_CALLBACK_DATA:
                message.setText(currencySort.getExchangeRate(userConfig));
                message.setReplyMarkup(keyboards.createMainKeyboard());
                break;
            case CURRENCIES:
                message.setText(CreatingKeyboards.stringWrapper("Оберіть валюту"));
                message.setReplyMarkup(keyboards.createCurrencyKeyboard(userConfig));
                break;
            case DOLLAR:
            case EURO:
                toggleCurrency(callbackQuery.getData(), userConfig);
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
                break;

            case "09:00":
            case "10:00":
            case "11:00":
            case "12:00":
            case "13:00":
            case "14:00":
            case "15:00":
            case "16:00":
            case "17:00":
            case "18:00":
                handleNotificationTime(callbackQuery.getData(), userConfig, message, chatId);
                break;
            case TURN_OF_NOTIFICATION:
                handleTurnOffNotification(userConfig, message);
                break;
            case BACK_TO_MAIN_MENU:
                message.setText(CreatingKeyboards.stringWrapper("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют"));
                message.setReplyMarkup(keyboards.createMainKeyboard());
                break;
            default:
                message.setText(CreatingKeyboards.stringWrapper("Невідома команда"));
                break;
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void handleNotificationTime(String time, UserConfig userConfig, SendMessage message, String chatId) {
        LocalTime newTime = LocalTime.parse(time);
        userConfig.setTimeForNotification(newTime);
        long delay = userConfig.calculateDelay();
        message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime));
        message.setReplyMarkup(keyboards.createSettingsKeyboard());

        Runnable task = () -> {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(currencySort.getExchangeRate(userConfig));
            sendMessage.setReplyMarkup(keyboards.createMainKeyboard());
            sendMessage.setChatId(chatId);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        };

        userConfig.scheduleNotification(task, delay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
    }

    private void toggleBank(String bank, UserConfig userConfig) {
        String bankName = bank.equals(NBU_CALLBACK_DATA) ? CreatingKeyboards.stringWrapper(NBU_BANK) : bank;
        if (userConfig.getBanks().contains(bankName)) {
            userConfig.removeBank(bankName);
        } else {
            userConfig.addBank(bankName);
        }
    }

    private void toggleCurrency(String currency, UserConfig userConfig) {
        if (userConfig.getCurrentCurrencies().contains(currency)) {
            userConfig.deleteCurrency(currency);
        } else {
            userConfig.addCurrency(currency);
        }
    }


    private void handleTurnOffNotification(UserConfig userConfig, SendMessage message) {
        if (userConfig.getScheduledFuture() != null) {
            userConfig.getScheduledFuture().cancel(true);
            message.setText(CreatingKeyboards.stringWrapper("Повідомлення о курсі валют відмінено"));
            message.setReplyMarkup(keyboards.createSettingsKeyboard());
        } else {
            message.setText(CreatingKeyboards.stringWrapper("Повідомлення відсутні"));
            message.setReplyMarkup(keyboards.createCurrencyKeyboard(userConfig));
        }
    }

    @Override
    public String getBotUsername() {
        return "ShelduedSenderBot";
    }

    @Override
    public String getBotToken() {
        return "6695346541:AAHMVAu5RwQ3adakniu_cFBvGF5wad_f7c8";
    }

    private static boolean isMessagePresent(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }
}
