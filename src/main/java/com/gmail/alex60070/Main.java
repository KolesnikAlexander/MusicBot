package com.gmail.alex60070;

import com.gmail.alex60070.request.callback.AddSongRequest;
import com.gmail.alex60070.request.callback.ListRequest;
import com.gmail.alex60070.request.callback.MenuRequest;
import com.gmail.alex60070.request.callback.OptionsRequest;
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

        commandsRequestManager = new RequestManager();
        commandsRequestManager.add(new HelloRequest());
        commandsRequestManager.add(new StartRequest());

        dialogManager = new DialogManager();

        requestManager = new RequestManager();
        requestManager.add(new ListRequest());
        requestManager.add(new MenuRequest());
        requestManager.add(new OptionsRequest());
        requestManager.add(new AddSongRequest());

        System.out.println("Bot started");
    }
}
