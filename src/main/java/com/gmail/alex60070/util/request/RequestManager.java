package com.gmail.alex60070.util.request;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.util.dialog.DialogManager;
import org.telegram.telegrambots.api.objects.Update;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex60070 on 17.08.17.
 */
public class RequestManager {
    private  List<RequestAbs> requests = new LinkedList<>();
    private DialogManager dialogManager;

    public RequestManager(DialogManager dialogManager) {
        this.dialogManager = dialogManager;
    }

    public DialogManager getDialogManager() {
        return dialogManager;
    }

    public void add(RequestAbs reqest)
    {
        reqest.setManager(this);
        requests.add(reqest);
    }

    /**
     * Returns session if it exists and null otherwise.
     * @return
     */
    public RequestAbs getRequest(Update update){
        for (RequestAbs request : requests) {
            if (request.validateRequest(update))
                return request;
        }
        return null;
    }

    /**
     * Processes request if update's data matches any of the request
     * @param update
     * @return true if request is handled and false otherwise
     */
    public boolean processRequest(Bot bot, Update update){
        RequestAbs request = this.getRequest(update);
        if (request != null){
            request.handle(bot, update);
            return true;
        }
        else
            return false;
    }
}
