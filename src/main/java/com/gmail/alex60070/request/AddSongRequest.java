package com.gmail.alex60070.request;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.Keyboards;
import com.gmail.alex60070.session.Session;
import com.gmail.alex60070.session.SessionManager;
import com.gmail.alex60070.util.message.Messages;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 16.08.17.
 */
public class AddSongRequest {

    public static void addSongHandler(Bot bot, Update update) {

        String text = "Название: \""+update.getMessage().getText() +"\"\n"+
                "Пришлите фото с текстом:";

        Messages.sendMarkupMessage(bot, update, text, Keyboards.backKeyKeyboard("menu"));
    }
}
