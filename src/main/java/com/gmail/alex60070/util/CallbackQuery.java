package com.gmail.alex60070.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alex60070
 */
public class CallbackQuery {
    public static String filterCallback(String callbackData) {
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
