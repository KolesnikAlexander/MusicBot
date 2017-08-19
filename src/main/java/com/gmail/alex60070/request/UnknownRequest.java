package com.gmail.alex60070.request;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.Keyboards;
import com.gmail.alex60070.util.message.Messages;
import com.gmail.alex60070.util.request.RequestAbs;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 17.08.17.
 */
public class UnknownRequest extends RequestAbs {
    @Override
    protected boolean validateRequest(Update update) {
        return true;
    }

    @Override
    protected void handleRequest(Bot bot, Update update) {
        Messages.sendKeyboardMessage(bot, update,"Неизвестное действие", null);
        Messages.sendKeyboardMessage(bot, update,"Меню", Keyboards.menuKeyboard());
    }
}
