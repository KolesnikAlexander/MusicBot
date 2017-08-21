package com.gmail.alex60070.request.callback;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.request.AddSongDialog;
import com.gmail.alex60070.util.dialog.AbstractDialog;
import com.gmail.alex60070.util.message.Messages;
import com.gmail.alex60070.util.request.CallbackRequestAbs;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 17.08.17.
 */
public class AddSongRequest extends CallbackRequestAbs {
    @Override
    protected void handleRequest(Bot bot, Update update) {
        AbstractDialog abstractDialog = new AddSongDialog(Messages.retrieveMessage(update).getChatId(), Bot.dialogManager);
        abstractDialog.start(bot, update);
    }

    @Override
    protected String getRequestCallbackData() {
        return "add_song";
    }
}
