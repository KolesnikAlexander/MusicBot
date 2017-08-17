package com.gmail.alex60070.util.request;

import com.gmail.alex60070.Bot;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 17.08.17.
 */
public abstract class SlashRequestAbs extends RequestAbs {
    @Override
    protected final boolean validateRequest(Update update) {
        Message message = update.getMessage();
        if ((message != null) && message.hasText()){
            String text = message.getText();
            if (text.startsWith("/") && text.equals(getRequestString()))
                    return true;
            else
                return false;
        }
        else
            return false;
    }
    protected abstract String getRequestString();

    @Override
    public abstract void  handleRequest(Bot bot, Update update);
}
