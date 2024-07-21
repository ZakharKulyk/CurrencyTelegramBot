package AppLauncher;


import Parce.MonoParcer;
import Parce.NBUParcer;
import Parce.PrivatParcer;
import TelegramBot.TelegramCurrencyBot;
import dto.MonoDto;
import dto.NbuDto;
import dto.PrivatDto;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        PrivatParcer privatParcer = new PrivatParcer();
        List<PrivatDto> request = privatParcer.getRequest();
        request.stream().forEach(System.out::println);

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramCurrencyBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
