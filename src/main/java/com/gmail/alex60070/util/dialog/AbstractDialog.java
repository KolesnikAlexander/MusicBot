package com.gmail.alex60070.util.dialog;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.util.message.Messages;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.lang.reflect.InvocationTargetException;
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
    private DialogManager manager;

    public AbstractDialog(Long chatId, DialogManager manager) {
        this.chatId = chatId;
        this.action = "start";
        messagesId = new LinkedList<>();
        this.manager = manager;
        manager.add(this);
    }

    protected abstract void startDialog(Bot bot, Update update);

    public final void start(Bot bot, Update update){
        if (action.equals("start")){
            startDialog(bot, update);
        }
        else
            // TODO: 17.08.17 Create own exception
            throw new RuntimeException("AbstractDialog is already started");
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

    public final void join(Bot bot, Update update){
        Message message = Messages.retrieveMessage(update);
        messagesId.add(message.getMessageId());
        joinAction(bot, update); // handling the action
    }

    protected void joinAction(Bot bot, Update update){
        try {
            if (validateAction(bot, update))
                handleAction(bot, update);
            else
                handleInvalid(bot, update);

        } catch (InvocationTargetException e) {
            // TODO: 19.08.17 Make output to log
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    private boolean validateAction(Bot bot, Update update) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Class cl = this.getClass();
        Method[] method = cl.getMethods();
        for(Method md: method){
            Action.Validator annotation = md.getAnnotation(Action.Validator.class);
            if((annotation != null) &&
                    this.getAction().equals(annotation.action())) {
                //returns true if validation is successful
                return (boolean) md.invoke(this,bot, update);
            }
        }
        return false;
    }
    private boolean handleAction(Bot bot, Update update) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Class cl = this.getClass();
        Method[] method = cl.getMethods();
        for(Method md: method){
            Action.Handler annotation = md.getAnnotation(Action.Handler.class);
            if((annotation != null) &&
                    this.getAction().equals(annotation.action())) {
                md.invoke(this, bot, update);
                return true;
            }
        }
        return false;
    }
    private boolean handleInvalid(Bot bot, Update update) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Class cl = this.getClass();
        Method[] method = cl.getMethods();
        for(Method md: method){
            Action.InvalidHandler annotation = md.getAnnotation(Action.InvalidHandler.class);
            if((annotation != null) &&
                    this.getAction().equals(annotation.action())) {
                md.invoke(this, bot, update);
                return true;
            }
        }
        return false;
    }

    public void destroy(){
        manager.deleteDialog(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractDialog abstractDialog = (AbstractDialog) o;

        return chatId.equals(abstractDialog.chatId);
    }

    @Override
    public int hashCode() {
        return chatId.hashCode();
    }
}
