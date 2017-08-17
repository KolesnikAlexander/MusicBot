package com.gmail.alex60070.util.request;

import com.gmail.alex60070.Bot;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 17.08.17.
 */
public abstract class SlashRequest extends Request{
    @Override
    protected final boolean validRequest(Update update) {
        Message message = update.getMessage();
        if ((message != null) && message.hasText()){
            String text = message.getText();
            if (text.startsWith("/") && text.equals(getRequest()))
                    return true;
            else
                return false;
        }
        else
            return false;
    }

    @Override
    protected RequestType getRequestType() {
        return RequestType.SLASH;
    }
    protected abstract String getRequest();

    @Override
    public abstract void  handleRequest(Bot bot, Update update);
}
