package com.gmail.alex60070;

import com.gmail.alex60070.request.AddSongDialog;
import com.gmail.alex60070.request.Dialog;
import com.gmail.alex60070.request.DialogManager;
import com.gmail.alex60070.util.Logger;
import com.gmail.alex60070.util.message.Messages;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by alex60070 on 28.07.17.
 */
public class Bot extends TelegramLongPollingBot{
    public void onUpdateReceived(Update update)
    {
        try {
            if(update.hasCallbackQuery()){
                processCallback(update);
            }

            else if (update.hasMessage())
                processMessage(update);
        }
        catch (Exception e){
            Logger.log(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private void processMessage(Update update) throws Exception {
        Message message = update.getMessage();

        if (DialogManager.dialogExists(message.getChatId())){
            Dialog dialog = DialogManager.getDialog(message.getChatId());
            dialog.join(this, update);
        }

        else if(isRequest(message, "/start"))
            RequestHandler.start(this, update);

        else
            processUnknown(update);
        return;
    }

    private void processCallback(Update update){
        String filteredData;
        try {
            String callbackData = update.getCallbackQuery().getData();
            filteredData = filterCallback(callbackData);
        } catch (Exception e) {
            Logger.log(e.getMessage());
            return;
        }
        if (filteredData.equals("list"))
            RequestHandler.list(this, update);
        else if (filteredData.equals("options"))
            RequestHandler.options(this, update);
        else if (filteredData.equals("menu"))
            RequestHandler.menuBack(this, update);
        else if (filteredData.equals("add_song"))
        {
            Dialog dialog = new AddSongDialog(Messages.retrieveMessage(update).getChatId());
            dialog.start(this, update);
        }
    }


    private String filterCallback(String callbackData) throws Exception {
            //Pattern to validate
            Pattern usrIdPattern = Pattern.compile("^(.+)\\(.+\\)$",
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = usrIdPattern.matcher(callbackData);
            if(matcher.find()) {
                return matcher.group(1);
            }
            else
                // TODO: 16.08.17 Create own exception
                throw new Exception("Unknown callback data format: "+callbackData);
    }

    private boolean isRequest(Message message, String request){
        if(message.hasText() && message.getText().equals(request))
            return true;
        else
            return false;
    }

    private void processUnknown(Update update) {
        //same as /start
        RequestHandler.start(this, update);
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
