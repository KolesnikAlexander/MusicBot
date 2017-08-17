package com.gmail.alex60070.request;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.util.message.Messages;
import com.gmail.alex60070.util.request.SlashRequest;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 17.08.17.
 */
public class HelloSlashRequest extends SlashRequest{

    @Override
    protected String getRequest() {
        return "/hello";
    }

    @Override
    public void handleRequest(Bot bot, Update update) {
        Messages.sendKeyboardMessage(bot, update, "Hello bro", null);
    }

}
