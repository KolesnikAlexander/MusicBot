package com.gmail.alex60070.util.request;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.util.dialog.AbstractDialog;
import com.gmail.alex60070.util.dialog.DialogManager;
import com.gmail.alex60070.util.message.Messages;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 17.08.17.
 */
public abstract class RequestAbs {

    private RequestManager manager;

    public final void handle(Bot bot, Update update){
        destroyDialogIfExists(update);
        handleRequest(bot, update);
    }

    private void destroyDialogIfExists(Update update){
        DialogManager dialogManager = manager.getDialogManager();
        System.out.println(dialogManager);
        Message message = Messages.retrieveMessage(update);
        if (dialogManager.dialogExists(message.getChatId()))
        {
            AbstractDialog dialog = dialogManager.getDialog(message.getChatId());
            dialog.destroy();
        }
    }

    public void setManager(RequestManager manager) {
        this.manager = manager;
    }

    protected abstract boolean validateRequest(Update update);

    protected abstract void handleRequest(Bot bot, Update update);
}