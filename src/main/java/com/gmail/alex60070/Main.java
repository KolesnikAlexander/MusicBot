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
    public static RequestManager commandsRequestManager;
    public static DialogManager dialogManager;
    public static RequestManager requestManager;

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

        dialogManager = new DialogManager();
        commandsRequestManager = new RequestManager(dialogManager);
        requestManager = new RequestManager(dialogManager);

        commandsRequestManager.add(new HelloRequest());
        commandsRequestManager.add(new StartRequest());
        commandsRequestManager.add(new CancelRequest());

        // TODO: 19.08.17 No need in dialog manager here

        requestManager.add(new ListRequest());
        requestManager.add(new MenuRequest());
        requestManager.add(new OptionsRequest());
        requestManager.add(new AddSongRequest());
        requestManager.add(new UnknownRequest());

        System.out.println("Bot started");
    }
}
