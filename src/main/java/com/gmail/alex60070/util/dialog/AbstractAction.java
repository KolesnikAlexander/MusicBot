package com.gmail.alex60070.util.dialog;

import com.gmail.alex60070.util.Logger;
import org.telegram.telegrambots.api.objects.Update;

import java.lang.reflect.Type;

/**
 * Created by alex60070 on 17.08.17.
 */
public abstract class AbstractAction {
    private Class validData;
    private String errorMessage;

    public AbstractAction() {
    }

    public final void handle(Update update){
        try {
            if (!dataIsValid(validData) || !validate(update)){
                invalidDataReply();
                return;
            }
            else {
                handleAction();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Logger.log("Illegal access exception when handling action");
        } catch (InstantiationException e) {
            e.printStackTrace();
            Logger.log("Instantiation exception when handling action");
        }
    }
    public abstract Class validData();

    public abstract void invalidDataReply();

    public abstract void handleAction();

    public abstract boolean validate(Update update);

    public boolean dataIsValid(Class c) throws IllegalAccessException, InstantiationException {
        return c.equals(validData);
    }


}
