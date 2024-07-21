package TelegramBot;


import UserConfiguration.UserConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.time.LocalTime;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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

            if (callbackQuery.getData().equals("Settings")) {
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            }

            if (callbackQuery.getData().equals("NotificationTime")) {
                message.setChatId(chatId);
                message.setText(CreatingKeyboards.stringWrapper("Оберіть час для оповіщення "));
                message.setReplyMarkup(keyboards.createTimeNotificationKeyboard(dataBase.get(chatId)));



            }
            if(callbackQuery.getData().equals("9:00")){
                UserConfig userConfig = dataBase.get(chatId);
                LocalTime newTime;
                long delay;
                newTime = LocalTime.of(19, 14);


                userConfig.setTimeForNotification(newTime);
                delay = userConfig.calculateDelay();

                message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime.toString()));

                Runnable task = () -> {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(CreatingKeyboards.stringWrapper("тут буде курс валюти"));
                    sendMessage.setChatId(chatId);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                };

                userConfig.scheduleNotification(task, delay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);

            }
            if(callbackQuery.getData().equals("10:00")){
                UserConfig userConfig = dataBase.get(chatId);
                LocalTime newTime;
                long delay;
                newTime = LocalTime.of(19, 15);


                userConfig.setTimeForNotification(newTime);
                delay = userConfig.calculateDelay();

                message.setText(CreatingKeyboards.stringWrapper("Актуальний курс буде надіслано о " + newTime.toString()));

                Runnable task = () -> {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(CreatingKeyboards.stringWrapper("тут буде курс валюти"));
                    sendMessage.setChatId(chatId);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                };

                userConfig.scheduleNotification(task, delay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);

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
    return "https://t.me/JavaCurrency123Bot";
}

@Override
public String getBotToken() {
    return "7434436728:AAGSSBb--F8Q9TZwkh1Ntdw7gWpEmXuX0Ps";
}

private static boolean IsMessagePresent(Update update) {
    return update.hasMessage() && update.getMessage().hasText();
}
}
