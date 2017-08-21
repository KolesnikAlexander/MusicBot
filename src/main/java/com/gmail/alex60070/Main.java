package com.gmail.alex60070;

import com.gmail.alex60070.request.UnknownRequest;
import com.gmail.alex60070.request.callback.*;
import com.gmail.alex60070.request.slash.HelloRequest;
import com.gmail.alex60070.request.slash.StartRequest;
import com.gmail.alex60070.util.Logger;
import com.gmail.alex60070.util.dialog.DialogManager;
import com.gmail.alex60070.util.request.RequestManager;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Main
{

    public static void main( String[] args )
    {
        Logger.createLog();

        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

//        Bot.dialogManager = new DialogManager();
//        Bot.commandsRequestManager = new RequestManager(Bot.dialogManager);
//        Bot.requestManager = new RequestManager(Bot.dialogManager);
//
//        Bot.commandsRequestManager.add(new HelloRequest());
//        Bot.commandsRequestManager.add(new StartRequest());
//        Bot.commandsRequestManager.add(new CancelRequest());
//
//        // TODO: 19.08.17 No need in dialog manager here
//
//        Bot.requestManager.add(new ListRequest());
//        Bot.requestManager.add(new MenuRequest());
//        Bot.requestManager.add(new OptionsRequest());
//        Bot.requestManager.add(new AddSongRequest());
//        Bot.requestManager.add(new UnknownRequest());

        System.out.println("Bot started");
    }
}
