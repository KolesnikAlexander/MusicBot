package com.gmail.alex60070;

import com.gmail.alex60070.request.AddSongSession;
import com.gmail.alex60070.session.Session;
import com.gmail.alex60070.session.SessionManager;
import com.gmail.alex60070.util.Logger;
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

    private void processSession(Update update, Session session) throws Exception {
        String request = session.getRequest();

        if (request == null){
            // TODO: 16.08.17 Create own exception
            throw new Exception("Session request is null");
        }
        else if (request.equals("add_song")){
            AddSongSession.nameIsSent(this, update, session);
        }
    }

    private void processMessage(Update update) throws Exception {
        Message message = update.getMessage();

        if (SessionManager.sessionExists(message.getChatId())){
            Session session = SessionManager.getSession(message.getChatId());
            processSession(update, session);
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
            AddSongSession.addSongClick(this, update);
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
