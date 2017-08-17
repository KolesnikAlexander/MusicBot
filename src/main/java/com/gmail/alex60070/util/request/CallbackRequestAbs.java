package com.gmail.alex60070.util.request;

import com.gmail.alex60070.Bot;
import org.telegram.telegrambots.api.objects.Update;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alex60070 on 17.08.17.
 */
public abstract class CallbackRequestAbs extends RequestAbs {
    @Override
    protected boolean validateRequest(Update update) {
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            String filteredData = filterCallback(callbackData);
            if (filteredData.equals(getRequestCallbackData()))
                return true;
            else
                return false;
        }
        else
            return false;
    }
    @Override
    protected abstract void handleRequest(Bot bot, Update update);

    protected abstract String getRequestCallbackData();

    private String filterCallback(String callbackData) {
        //Pattern to validate
        Pattern usrIdPattern = Pattern.compile("^(.+)\\(.+\\)$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = usrIdPattern.matcher(callbackData);
        if(matcher.find()) {
            return matcher.group(1);
        }
        else
            // TODO: 16.08.17 Create own exception
            throw new RuntimeException("Unknown callback data format: "+callbackData);
    }
}
