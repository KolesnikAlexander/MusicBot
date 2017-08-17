package com.gmail.alex60070.request.slash;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.Keyboards;
import com.gmail.alex60070.util.message.Messages;
import com.gmail.alex60070.util.request.SlashRequestAbs;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 17.08.17.
 */
public class StartRequest extends SlashRequestAbs {
    @Override
    protected String getRequestString() {
        return "/start";
    }

    @Override
    public void handleRequest(Bot bot, Update update) {
        String text = "Меню";
        Messages.sendKeyboardMessage(bot, update,text, Keyboards.menuKeyboard());
    }
}
