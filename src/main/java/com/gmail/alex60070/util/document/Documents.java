package com.gmail.alex60070.util.document;

import com.gmail.alex60070.Bot;
import org.telegram.telegrambots.api.methods.GetFile;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Document;
import org.telegram.telegrambots.api.objects.File;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by alex60070 on 31.07.17.
 */
public class Documents {
    public static InputStream downloadDocument(Bot bot, Document document){
        GetFile getFile = new GetFile();
        getFile.setFileId(document.getFileId());
        File file = null;
        try {
            file = bot.execute(getFile);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        String filePath = file.getFilePath();
        InputStream inputFileStream = null;
        try {
            // TODO: 31.07.17  Throw exception
            inputFileStream = downloadUsingStream(bot.getBotToken(),filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputFileStream;
    }
    private static InputStream downloadUsingStream(String botToken, String filePath) throws IOException {
        String path = File.getFileUrl(botToken, filePath);
        URL url = new URL(path);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        return bis;
    }

    public static void sendDocument(TelegramLongPollingBot bot, Message message, String docName, InputStream docStream){
        SendDocument reply = new SendDocument() // Create a message object object
                .setChatId(message.getChatId())
                .setNewDocument(docName, docStream);
        try {
            bot.sendDocument(reply); // Sending our message object to user
        } catch (TelegramApiException e) {
            System.out.println("Failed to send document");
        }
    }
}
