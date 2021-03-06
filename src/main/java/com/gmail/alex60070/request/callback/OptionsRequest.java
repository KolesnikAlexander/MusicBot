package com.gmail.alex60070.request.callback;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.Keyboards;
import com.gmail.alex60070.util.message.Messages;
import com.gmail.alex60070.util.request.CallbackRequestAbs;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 17.08.17.
 */
public class OptionsRequest extends CallbackRequestAbs {
    @Override
    protected void handleRequest(Bot bot, Update update) {
        Messages.editCurrentMessage(bot, update, "Опции", Keyboards.optionsKeyboard());
    }

    @Override
    protected String getRequestCallbackData() {
        return "options";
    }
}
