package com.gmail.alex60070.util.request;

import com.gmail.alex60070.Bot;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 17.08.17.
 */
public abstract class Request {
    public void handle(Bot bot, Update update){
        handleRequest(bot, update);
    }
    protected abstract boolean validRequest(Update update);
    protected abstract RequestType getRequestType();
    protected abstract void handleRequest(Bot bot, Update update);

}
