package com.gmail.alex60070;

import com.gmail.alex60070.request.HelloSlashRequest;
import com.gmail.alex60070.util.Logger;
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

        RequestManager.add(new HelloSlashRequest());

        System.out.println("Bot started");
    }
}
