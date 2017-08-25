package com.gmail.alex60070.request.callback;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.Keyboards;
import com.gmail.alex60070.dao.DaoSongResponse;
import com.gmail.alex60070.dao.SongDao;
import com.gmail.alex60070.entity.Song;
import com.gmail.alex60070.util.CallbackQuery;
import com.gmail.alex60070.util.message.Messages;
import com.gmail.alex60070.util.photo.Photos;
import com.gmail.alex60070.util.request.CallbackRequestAbs;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070
 */
public class SongRequest extends CallbackRequestAbs{
    @Override
    protected void handleRequest(Bot bot, Update update) {
        DaoSongResponse daoSongResponse = new SongDao().getSong(songName(update));
        if (daoSongResponse.isSuccessful()){
            String name = daoSongResponse.getSong().getName();
            String photoPath = daoSongResponse.getSong().getPhotoPath();

            Messages.editCurrentMessage(bot, update, "\""+name+"\"", null);
            Photos.sendPhoto(bot, update, name, "db_images/"+photoPath);
            Messages.sendKeyboardMessage(bot, update, "Меню", Keyboards.menuKeyboard());
        }
        else {
            Messages.editCurrentMessage(bot, update, "Песня не найдена", null);
            Messages.sendKeyboardMessage(bot, update, "Меню", Keyboards.menuKeyboard());
        }
    }

    private String songName(Update update) {
        String data = CallbackQuery.filterCallback(update.getCallbackQuery().getData());
        String name = data.substring(5, data.length());
        return name;
    }

    @Override
    protected String getRequestCallbackData() {
        return "song/";
    }
}
