package AppLauncher;

import Parce.PrivatParcer;
import TelegramBot.TelegramCurrencyBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        /*MonoParcer mono = new MonoParcer();
        System.out.println(mono.getRequest());
        NBUParcer nbuParcer = new NBUParcer();
        System.out.println(nbuParcer.getRequest())*/;
        try
        {
            TelegramBotsApi BOTS = new TelegramBotsApi(DefaultBotSession.class);
            BOTS.registerBot(new TelegramCurrencyBot());

        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }
}
