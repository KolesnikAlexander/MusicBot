package com.gmail.alex60070;

import com.gmail.alex60070.request.UnknownRequest;
import com.gmail.alex60070.request.callback.*;
import com.gmail.alex60070.request.slash.HelloRequest;
import com.gmail.alex60070.request.slash.StartRequest;
import com.gmail.alex60070.util.Logger;
import com.gmail.alex60070.util.dialog.DialogManager;
import com.gmail.alex60070.util.request.RequestManager;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;



/**
 * Created by alex60070 on 28.07.17.
 */
public class Bot extends TelegramLongPollingBot{

    public static RequestManager commandsRequestManager;
    public static DialogManager dialogManager;
    public static RequestManager requestManager;

    static {
        initManagers();
    }
    public synchronized void onUpdateReceived(Update update)
    {
        try {
            if(commandsRequestManager.processRequest(this, update))
                return;
            else if(dialogManager.processDialog(this, update))
                return;
            else if(requestManager.processRequest(this, update))
                return;

           // new UnknownRequest().handle(this, update);
        }
        catch (Exception e){
            Logger.log(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void initManagers() {
       dialogManager = new DialogManager();//executed second
       commandsRequestManager = new RequestManager(Bot.dialogManager);//executed first
       requestManager = new RequestManager(Bot.dialogManager);// executed third

       commandsRequestManager.add(new HelloRequest());
       commandsRequestManager.add(new StartRequest());
       commandsRequestManager.add(new CancelRequest());

       requestManager.add(new ListRequest());
       requestManager.add(new MenuRequest());
       requestManager.add(new OptionsRequest());
       requestManager.add(new AddSongRequest());
       requestManager.add(new SongRequest());

       requestManager.add(new UnknownRequest());

    }

    private String genDocName(String fileName) {
        return fileName.substring(0, fileName.length() - 4) + ".lst";
    }

    public String getBotUsername() {
        return Settings.USER_NAME;
    }

    public String getBotToken() {
        return Settings.TOKEN;
    }

}
