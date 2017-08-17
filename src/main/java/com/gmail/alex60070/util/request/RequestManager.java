package com.gmail.alex60070.util.request;

import com.gmail.alex60070.util.dialog.Dialog;
import org.telegram.telegrambots.api.objects.Update;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex60070 on 17.08.17.
 */
public class RequestManager {
    private static List<Request> requests = new LinkedList<>();

    public static void add(Request reqest){
        requests.add(reqest);
    }

    /**
     * Returns session if it exists and null otherwise.
     * @return
     */
    public static Request getRequest(Update update){
        for (Request request : requests) {
            if (request.validRequest(update))
                return request;
        }
        return null;
    }
}
