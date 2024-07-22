package TelegramBot;

import CurrencySort.CurrencySort;
import UserConfiguration.UserConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Constants.ConstansDev.*;

public class TelegramCurrencyBot extends TelegramLongPollingBot {

    private final Map<Long, CreatingKeyboards> userKeyboards = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        if (IsMessagePresent(update) && update.getMessage().getText().equalsIgnoreCase("/start")){
            String chatId = update.getMessage().getChatId().toString();
            message.setChatId(chatId);
            message.setText(CreatingKeyboards.stringWrapper("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют"));
            message.setReplyMarkup(getOrCreateKeyboard(chatId).createMainKeyboard());

            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String chatId = callbackQuery.getMessage().getChatId().toString();
            message.setChatId(chatId);

            CreatingKeyboards keyboards = getOrCreateKeyboard(chatId);

            if (callbackQuery.getData().equals("Settings")){
                message.setText(CreatingKeyboards.stringWrapper("Налаштування"));
                message.setReplyMarkup(keyboards.createSettingsKeyboard());
            } else if (callbackQuery.getData().equals("Currencies")) {
                message.setText(CreatingKeyboards.stringWrapper("Виберіть валюту:"));
                message.setReplyMarkup(keyboards.createCurrencyKeyboard());
            } else if (callbackQuery.getData().startsWith("Currency_")) {
                String currencyCode = callbackQuery.getData().replace("Currency_", "");
                keyboards.updateSelectedCurrencies(currencyCode);
                message.setText(CreatingKeyboards.stringWrapper("Вибрано: " + currencyCode));
                message.setReplyMarkup(keyboards.createCurrencyKeyboard());
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
        return "TesterCurrencyTestBot";
    }

    @Override
    public String getBotToken() {
        return "6750993641:AAEi7hm5H8TPjiy5rTdgAaBdlsT1KyMZAcQ";
    }

    private static boolean IsMessagePresent(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    private CreatingKeyboards getOrCreateKeyboard(String chatId) {
        return userKeyboards.computeIfAbsent(Long.valueOf(chatId), k -> new CreatingKeyboards());
    }
}