package com.gmail.alex60070.request.callback;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.Keyboards;
import com.gmail.alex60070.util.message.Messages;
import com.gmail.alex60070.util.request.CallbackRequestAbs;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070
 */
public class CancelRequest extends CallbackRequestAbs {

    @Override
    protected void handleRequest(Bot bot, Update update) {
        Messages.sendKeyboardMessage(bot, update, "Меню", Keyboards.menuKeyboard());
    }

    @Override
    protected String getRequestCallbackData() {
        return "cancel";
    }
}
