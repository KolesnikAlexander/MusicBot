package com.gmail.alex60070.util.photo;

import com.gmail.alex60070.util.Logger;
import com.gmail.alex60070.util.message.Messages;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;

/**
 * Created by alex60070 on 28.07.17.
 */
public class Photos {
    public static PhotoSize getMax(List<PhotoSize> photos){
        return photos.stream() //get exact photo
                .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                .findFirst()
                .orElse(null);
    }
    public static void sendPhoto(TelegramLongPollingBot bot, Update update,String name, String path) {
        Message message = Messages.retrieveMessage(update);
        SendPhoto photo = null;
        try {
            photo = new SendPhoto()
                    .setChatId(message.getChatId())
                    .setNewPhoto(name, inputStreamFromFile(new File(path)));
            bot.sendPhoto(photo); // Sending our message object to user
        } catch (TelegramApiException e) {
            Logger.log("Failed to sendPhoto");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            Logger.log("Failed to send photo: no file");
        }
    }

    public static InputStream inputStreamFromFile(File file) throws FileNotFoundException {
            return new FileInputStream(file);
    }
}
