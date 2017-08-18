package com.gmail.alex60070.util.dialog;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.util.message.Messages;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex60070 on 17.08.17.
 */
public abstract class AbstractDialog {
    private Long chatId;
    private String action; //action that is awaited
    private List<Integer> messagesId; // messages in dialog to ve deleted sequentially

    public AbstractDialog(Long chatId, DialogManager manager) {
        this.chatId = chatId;
        this.action = "start";
        messagesId = new LinkedList<>();
        manager.add(this);
    }

    public final Long getChatId() {
        return chatId;
    }

    public final String getAction() {
        return action;
    }

    public final List<Integer> getMessagesId() {
        return messagesId;
    }

    public final void setAction(String action) {
        this.action = action;
    }

    public final void start(Bot bot, Update update){
        if (action.equals("start")){
            startDialog(bot, update);
        }
        else
            // TODO: 17.08.17 Create own exception
            throw new RuntimeException("AbstractDialog is already started");
    }


    public final void join(Bot bot, Update update){
        Message message = Messages.retrieveMessage(update);
        messagesId.add(message.getMessageId());
        // TODO: 19.08.17 ValidateAction -> Handle/Error

        joinDialog(bot, update); // handles certain dialog
        joinAction();
    }

    protected void joinAction(){
        Class cl = this.getClass();
        Method[] method = null;
        for(Method md: method){
            ActionAnnotations.Validator annotation = md.getAnnotation(ActionAnnotations.Validator.class);
            if((annotation != null)) {
                // TODO: 19.08.17  Finish finally that stuff
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractDialog abstractDialog = (AbstractDialog) o;

        return chatId.equals(abstractDialog.chatId);
    }

    protected abstract void startDialog(Bot bot, Update update);

    protected abstract void joinDialog(Bot bot, Update update);

    protected abstract void getActions(Bot bot, Update update);

    @Override
    public int hashCode() {
        return chatId.hashCode();
    }
}
