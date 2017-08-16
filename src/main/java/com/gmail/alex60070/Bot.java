package com.gmail.alex60070;

import com.gmail.alex60070.request.AddSongRequest;
import com.gmail.alex60070.session.Session;
import com.gmail.alex60070.session.SessionManager;
import com.gmail.alex60070.util.Logger;
import com.gmail.alex60070.util.document.Documents;
import com.gmail.alex60070.util.message.Messages;
import org.telegram.telegrambots.api.objects.Document;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.*;
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
            System.out.println("Problem");
            Logger.log(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private void processSession(Update update, Session session) throws Exception {
        String status = session.getStatus();

        if (status == null){
            // TODO: 16.08.17 Create own exception
            throw new Exception("Status is null");
        }
        else if (status.equals("input_song_name")){
            AddSongRequest.addSongHandler(this, update);
        }
    }

    private void processMessage(Update update) throws Exception {
        Message message = update.getMessage();

        if (SessionManager.sessionExists(message.getChatId())){
            Session session = SessionManager.getSession(message.getChatId());
            processSession(update, session);
            return;
        }

        if(isRequest(message, "/start"))
            RequestHandler.startHandler(this, message);

        else if (message.hasDocument())
            processDocument(message);

        else
            processUnknown(message);
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
            RequestHandler.listHandler(this, update);
        else if (filteredData.equals("options"))
            RequestHandler.optionsHandler(this, update);
        else if (filteredData.equals("menu"))
            RequestHandler.menuBackHandler(this, update);
        else if (filteredData.equals("add_song"))
            RequestHandler.addSongHandler(this, update);
    }


    private String filterCallback(String callbackData) throws Exception {
        System.out.println("Matcher check");//<-------------------------
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

    private void processUnknown(Message message) {
        //same as /start
        RequestHandler.startHandler(this, message);
    }

    private void processDocument(Message message) {
        Document document = message.getDocument();
        Logger.userLog(message, "sent document \""
                +document.getFileName()+"\"");
        if (!document.getFileName().endsWith(".asm"))
            Messages.sendMessage(this, message, "Отправьте файл с расширением .asm");
        else{
            InputStream asmFileStream = Documents.downloadDocument(this, document); ///<------------------
            InputStream listing = getListing(asmFileStream); //<--------
            Documents.sendDocument(this, message, genDocName(document.getFileName()), listing);
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

    public InputStream getListing(InputStream asmFileStream) {
        InputStream result = null;
        ListingDownloader listingDownloader = new ListingDownloader();
        try {
            result = listingDownloader.listing(org.apache.commons.io.IOUtils.toByteArray(asmFileStream));
        } catch (IOException e) {
            System.out.println("Cannot make byte array");
        }
        return result;
    }
}
