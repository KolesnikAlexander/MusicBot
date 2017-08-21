package com.gmail.alex60070.request.callback;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.Keyboards;
import com.gmail.alex60070.dao.SongDao;
import com.gmail.alex60070.entity.Song;
import com.gmail.alex60070.util.message.Messages;
import com.gmail.alex60070.util.photo.Photos;
import com.gmail.alex60070.util.request.CallbackRequestAbs;
import org.telegram.telegrambots.api.objects.Update;

import java.util.LinkedList;

/**
 * Created by alex60070 on 17.08.17.
 */
public class ListRequest extends CallbackRequestAbs {
    @Override
    protected void handleRequest(Bot bot, Update update) {
        //Messages.editCurrentMessage(bot, update, ", Keyboards.backKeyKeyboard("menu"));
        LinkedList<Song> songsList = new SongDao().getSongsList();
        Messages.editCurrentMessage(bot, update,"Список:", Keyboards.songsKeyboard(songsList) );
        Photos.sendPhoto(bot, update, "ТЕСТ", "test.jpg");

    }

    @Override
    protected String getRequestCallbackData() {
        return "list";
    }
}