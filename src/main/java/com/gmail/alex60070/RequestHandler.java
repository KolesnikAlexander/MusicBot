package com.gmail.alex60070;

import com.gmail.alex60070.session.Session;
import com.gmail.alex60070.session.SessionManager;
import com.gmail.alex60070.util.Logger;
import com.gmail.alex60070.util.button.Buttons;
import com.gmail.alex60070.util.message.Messages;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.io.InputStream;

import static com.gmail.alex60070.session.SessionManager.createSesion;

/**
 * Created by alex60070 on 28.07.17.
 */
public class RequestHandler {


    public static void exampleHandler(Bot bot, Message message) {
        InputStream in = new RequestHandler().getClass().getResourceAsStream("/res/example.asm");
        SendDocument document = new SendDocument();
        document.setNewDocument("example.asm", in)
                .setCaption("Пример .asm файла")
                .setChatId(message.getChatId());
        try {
            bot.sendDocument(document);
        } catch (TelegramApiException e) {
            System.out.println("Cannot send example document");
        }
    }

    public static void startHandler(Bot bot, Message message) {
       // Logger.userLog(message, "start request");
        String text = "Меню";
        Buttons.sendButtons(bot, message,text, Keyboards.menuKeyboard());
    }

    public static void listHandler(Bot bot, Update update) {
        Messages.editMessage(bot, update, "Список", Keyboards.backKeyKeyboard("menu"));
    }

    public static void menuBackHandler(Bot bot, Update update){
        Messages.editMessage(bot, update, "Меню", Keyboards.menuKeyboard());
    }

    public static void optionsHandler(Bot bot, Update update) {
       Messages.editMessage(bot, update, "Опции", Keyboards.optionsKeyboard());
    }
    public static void addSongHandler(Bot bot, Update update) {
        String text = "Добавить песню\n" +
                "Введите название песни:";
        Session session = SessionManager.createSesion(update.getMessage().getChatId());
        session.setStatus("input_song_name");

        Messages.editMessage(bot, update, text, Keyboards.backKeyKeyboard("options"));
        try {
            bot.deleteMessage(new DeleteMessage().setMessageId(123));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
