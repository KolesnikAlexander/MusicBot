package com.gmail.alex60070;

import com.gmail.alex60070.request.UnknownRequest;
import com.gmail.alex60070.util.Logger;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;



/**
 * Created by alex60070 on 28.07.17.
 */
public class Bot extends TelegramLongPollingBot{
    public void onUpdateReceived(Update update)
    {
        try {
            if(Main.commandsRequestManager.processRequest(this, update))
                return;
            else if(Main.dialogManager.processDialog(this, update))
                return;
            else if(Main.requestManager.processRequest(this, update))
                return;

            new UnknownRequest().handle(this, update);
        }
        catch (Exception e){
            Logger.log(e.getMessage());
            e.printStackTrace();
            return;
        }
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
