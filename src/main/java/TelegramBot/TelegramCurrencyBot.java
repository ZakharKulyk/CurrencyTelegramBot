package TelegramBot;


import CurrencySort.CurrencySort;
import UserConfiguration.UserConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.time.LocalTime;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static Constants.ConstansDev.*;

public class TelegramCurrencyBot extends TelegramLongPollingBot {

    HashMap<String, UserConfig> dataBase = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        CurrencySort currencySort = new CurrencySort();
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
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String chatId = callbackQuery.getMessage().getChatId().toString();
            message.setChatId(chatId);
            UserConfig userConfig = dataBase.get(chatId);
            long delay;
            LocalTime newTime = null;

            if (callbackQuery.getData().equals(SETTINGS)) {
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }
            if (callbackQuery.getData().equals(NOTIFICATION_TIME)){
                message.setText(CreatingKeyboards.stringWrapper("Оберіть час для повідомлення"));
                message.setReplyMarkup(keyboards.createTimeNotificationKeyboard());
            }
            if (callbackQuery.getData().equals(BANK)) {
                message.setText(CreatingKeyboards.stringWrapper("Оберіть банк: "));
                message.setReplyMarkup(keyboards.bankKeyboard(userConfig));
            }
            if (callbackQuery.getData().equals(MONO_BANK)) {
                if (userConfig.getBanks().contains(MONO_BANK)) {
                    userConfig.removeBank(MONO_BANK);
                } else {
                    userConfig.addBank(MONO_BANK);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }
            if (callbackQuery.getData().equals(PRIVAT_BANK)) {
                if (userConfig.getBanks().contains(PRIVAT_BANK)) {
                    userConfig.removeBank(PRIVAT_BANK);
                } else {
                    userConfig.addBank(PRIVAT_BANK);
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }
            if (callbackQuery.getData().equals(NBU_CALLBACK_DATA)) {
                if (userConfig.getBanks().contains(CreatingKeyboards.stringWrapper(NBU_BANK))) {
                    userConfig.removeBank(CreatingKeyboards.stringWrapper(NBU_BANK));
                } else {
                    userConfig.addBank(CreatingKeyboards.stringWrapper(NBU_BANK));
                }
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }
            if (callbackQuery.getData().equals(GET_INFO_CALLBACK_DATA)) {
              message.setText(currencySort.getInfo(userConfig));
              message.setReplyMarkup(keyboards.createMainKeyboard());
            }

            if (callbackQuery.getData().equals(DIGITS_AFTER_DECIMAL_CALLBACK_DATA)) {
                message.setText(CreatingKeyboards.stringWrapper("Вкажіть кількість знаків після коми"));
                message.setReplyMarkup(keyboards.createDecimalPlacesKeyboard(userConfig));
            }
            if (callbackQuery.getData().equals(DIGITS_AFTER_DECIMAL2)) {
                if (userConfig.getDecimalPlaces().contains("2")) {
                    userConfig.removeDigitsAfterDecimalPlace("2");
                    userConfig.setDecimal(1);
                } else {
                    userConfig.addDigitsAfterDecimalPlace("2");
                    userConfig.setDecimal(2);
                }
                message.setText(CreatingKeyboards.stringWrapper("Значення встановлено: 2 знаки після коми"));
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            } else if (callbackQuery.getData().equals(DIGITS_AFTER_DECIMAL3)) {
                if (userConfig.getDecimalPlaces().contains("3")) {
                    userConfig.removeDigitsAfterDecimalPlace("3");
                    userConfig.setDecimal(1);
                } else {
                    userConfig.addDigitsAfterDecimalPlace("3");
                    userConfig.setDecimal(3);
                }
                message.setText(CreatingKeyboards.stringWrapper("Значення встановлено: 3 знаки після коми"));
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            } else if (callbackQuery.getData().equals(DIGITS_AFTER_DECIMAL4)) {
                if (userConfig.getDecimalPlaces().contains("4")) {
                    userConfig.removeDigitsAfterDecimalPlace("4");
                    userConfig.setDecimal(1);
                } else {
                    userConfig.addDigitsAfterDecimalPlace("4");
                    userConfig.setDecimal(4);
                }
                message.setText(CreatingKeyboards.stringWrapper("Значення встановлено: 4 знаки після коми"));
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }
            if (callbackQuery.getData().equals("9:00")){
                newTime = LocalTime.of(9,0);

                userConfig.setTimeForNotification(newTime);
                delay = userConfig.calculateDelay();
                message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime.toString()));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());

                Runnable tast = (()->{
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(currencySort.getInfo(userConfig));
                    sendMessage.setChatId(chatId);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }

                });

                userConfig.scheduleNotification(tast, delay, TimeUnit.DAYS.toSeconds(1),TimeUnit.SECONDS);
            }
            if (callbackQuery.getData().equals("10:00")){
                newTime = LocalTime.of(10,0);
                userConfig.setTimeForNotification(newTime);
                delay = userConfig.calculateDelay();
                message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime.toString()));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());

                Runnable tast = (()->{
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(currencySort.getInfo(userConfig));
                    sendMessage.setChatId(chatId);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }

                });

                userConfig.scheduleNotification(tast, delay, TimeUnit.DAYS.toSeconds(1),TimeUnit.SECONDS);
            }
            if (callbackQuery.getData().equals("11:00")){
                newTime = LocalTime.of(11,0);
                userConfig.setTimeForNotification(newTime);
                delay = userConfig.calculateDelay();
                message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime.toString()));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());

                Runnable tast = (()->{
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(currencySort.getInfo(userConfig));
                    sendMessage.setChatId(chatId);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                });

                userConfig.scheduleNotification(tast, delay, TimeUnit.DAYS.toSeconds(1),TimeUnit.SECONDS);
            }
            if (callbackQuery.getData().equals("12:00")){
                newTime = LocalTime.of(12,00);
                userConfig.setTimeForNotification(newTime);
                delay = userConfig.calculateDelay();
                message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime.toString()));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());

                Runnable tast = (()->{
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(currencySort.getInfo(userConfig));
                    sendMessage.setChatId(chatId);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                });

                userConfig.scheduleNotification(tast, delay, TimeUnit.DAYS.toSeconds(1),TimeUnit.SECONDS);
            }

            if (callbackQuery.getData().equals("13:00")){
                newTime = LocalTime.of(13,00);
                userConfig.setTimeForNotification(newTime);
                delay = userConfig.calculateDelay();
                message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime.toString()));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());

                Runnable tast = (()->{
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(currencySort.getInfo(userConfig));
                    sendMessage.setChatId(chatId);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                });

                userConfig.scheduleNotification(tast, delay, TimeUnit.DAYS.toSeconds(1),TimeUnit.SECONDS);
            }
            if (callbackQuery.getData().equals("14:00")){
                newTime = LocalTime.of(14,00);
                userConfig.setTimeForNotification(newTime);
                delay = userConfig.calculateDelay();
                message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime.toString()));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());

                Runnable tast = (()->{
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(currencySort.getInfo(userConfig));
                    sendMessage.setChatId(chatId);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                });

                userConfig.scheduleNotification(tast, delay, TimeUnit.DAYS.toSeconds(1),TimeUnit.SECONDS);
            }
            if (callbackQuery.getData().equals("15:00")){
                newTime = LocalTime.of(15,00);
                userConfig.setTimeForNotification(newTime);
                delay = userConfig.calculateDelay();
                message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime.toString()));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());

                Runnable tast = (()->{
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(currencySort.getInfo(userConfig));
                    sendMessage.setChatId(chatId);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                });

                userConfig.scheduleNotification(tast, delay, TimeUnit.DAYS.toSeconds(1),TimeUnit.SECONDS);
            }

            if (callbackQuery.getData().equals("16:00")){
                newTime = LocalTime.of(16,00);
                userConfig.setTimeForNotification(newTime);
                delay = userConfig.calculateDelay();
                message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime.toString()));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());

                Runnable tast = (()->{
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(currencySort.getInfo(userConfig));
                    sendMessage.setChatId(chatId);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                });

                userConfig.scheduleNotification(tast, delay, TimeUnit.DAYS.toSeconds(1),TimeUnit.SECONDS);
            }
            if (callbackQuery.getData().equals("17:00")){
                newTime = LocalTime.of(17,00);
                userConfig.setTimeForNotification(newTime);
                delay = userConfig.calculateDelay();
                message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime.toString()));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());

                Runnable tast = (()->{
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(currencySort.getInfo(userConfig));
                    sendMessage.setChatId(chatId);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                });

                userConfig.scheduleNotification(tast, delay, TimeUnit.DAYS.toSeconds(1),TimeUnit.SECONDS);
            }
            if (callbackQuery.getData().equals("18:00")){
                newTime = LocalTime.of(18,00);
                userConfig.setTimeForNotification(newTime);
                delay = userConfig.calculateDelay();
                message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime.toString()));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());

                Runnable tast = (()->{
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(currencySort.getInfo(userConfig));
                    sendMessage.setChatId(chatId);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                });

                userConfig.scheduleNotification(tast, delay, TimeUnit.DAYS.toSeconds(1),TimeUnit.SECONDS);
            }
            if (callbackQuery.getData().equals(TURN_OF_NOTIFICATION)){
                if(userConfig.getScheduledFuture()!=null){
                    userConfig.getScheduledFuture().cancel(true);
                    message.setText(CreatingKeyboards.stringWrapper("Повідомлення о курсі валют відмінено"));
                    message.setReplyMarkup(keyboards.createSettingsKeyboard());
                }else {
                    message.setText(CreatingKeyboards.stringWrapper("Повідомлення відсутні"));
                }

            }
            if (callbackQuery.getData().equals(BACK_TO_MAIN_MENU)){
                message.setText(CreatingKeyboards.stringWrapper("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют"));
                message.setReplyMarkup(keyboards.createMainKeyboard());
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
        return "ShelduedSenderBot";
    }

    @Override
    public String getBotToken() {
        return  "6695346541:AAHMVAu5RwQ3adakniu_cFBvGF5wad_f7c8";
    }

    private static boolean IsMessagePresent(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }
}
