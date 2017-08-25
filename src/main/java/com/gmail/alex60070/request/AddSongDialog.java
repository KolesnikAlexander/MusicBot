package com.gmail.alex60070.request;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.Keyboards;
import com.gmail.alex60070.dao.DaoSongResponse;
import com.gmail.alex60070.dao.SongDao;
import com.gmail.alex60070.entity.Photo;
import com.gmail.alex60070.entity.Song;
import com.gmail.alex60070.util.dialog.AbstractDialog;
import com.gmail.alex60070.util.dialog.Action;
import com.gmail.alex60070.util.dialog.DialogManager;
import com.gmail.alex60070.util.message.Messages;
import com.gmail.alex60070.util.photo.Photos;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;

public class AddSongDialog extends AbstractDialog {

    private static final String INPUT_SONG_NAME = "inputSongName";
    private static final String INPUT_PHOTO = "inputPhoto";
    private static final String SUBMIT = "submit";

    String name;
    PhotoSize photo;

    public AddSongDialog(Long chatId, DialogManager manager) {
        super(chatId, manager);
    }

    /*
        When the dialog starts, the update is always valid as long as
        dialog is started from the request handler that already
        verified the update.
    */
    @Override
    public void startDialog(Bot bot, Update update) {
        addSongClick(bot, update);
        setAction(INPUT_SONG_NAME);
    }

    public void addSongClick(Bot bot, Update update) {
        String text = "Добавить песню\n" +
                "Введите название песни:";
        Messages.editCurrentMessage(bot, update, text, Keyboards.cancelKeyBoard());
    }
// INPUT SONG NAME
    @Action.Validator(action = INPUT_SONG_NAME)
    public boolean inputSongNameValidate(Bot bot, Update update) {
        Message message = update.getMessage();
        if (message == null || !message.hasText() || !textIsValid(message.getText()))
            return false;
        else
            return true;
    }

    @Action.Handler(action = INPUT_SONG_NAME)
    public void inputSongName(Bot bot, Update update) {
        this.name = update.getMessage().getText();
        String text = "Название: \""+name+"\"\n"+
                "Пришлите фото с текстом:";
        Messages.sendKeyboardMessage(bot, update, text, Keyboards.cancelKeyBoard());//Keyboards.backKeyKeyboard("menu"));
        setAction(INPUT_PHOTO);
    }

    @Action.InvalidHandler(action = INPUT_SONG_NAME)
    public void nameIsSentInvalid(Bot bot, Update update) {
        String text = "Пришлите название песни. Запрещенный символ: \'/\'.";
        Messages.sendKeyboardMessage(bot, update, text, Keyboards.cancelKeyBoard());//Keyboards.backKeyKeyboard("options"));

    }

    private boolean textIsValid(String text) {
        // TODO: 16.08.17 Create validation pattern
        return true;
    }

// INPUT PHOTO
    @Action.Validator(action = INPUT_PHOTO)
    public boolean inputPhotoValidator(Bot bot, Update update) {
        Message message = Messages.retrieveMessage(update);
        if (message == null || !message.hasPhoto())
            return false;
        else
            return true;
    }

    @Action.Handler(action = INPUT_PHOTO)
    public void inputPhoto(Bot bot, Update update) {
        Message message = Messages.retrieveMessage(update);
        this.photo = Photos.getMax(message.getPhoto());
        String text = "Сохранить песню?";
        Messages.sendKeyboardMessage(bot, update, text, Keyboards.submitionKeyboard());//Keyboards.backKeyKeyboard("menu"));
        setAction(SUBMIT);
    }

    @Action.InvalidHandler(action = INPUT_PHOTO)
    public void inputPhotoInvalid(Bot bot, Update update) {
        String text = "Пришлите фото";
        Messages.sendKeyboardMessage(bot, update, text, Keyboards.cancelKeyBoard());//Keyboards.backKeyKeyboard("options"));

    }
//SUBMIT
    @Action.Validator(action = SUBMIT)
    public boolean submitValidator(Bot bot, Update update) {
        CallbackQuery callback = update.getCallbackQuery();
        if (callback == null)
            return false;
        else if (!com.gmail.alex60070.util.CallbackQuery
                .filterCallback(callback.getData())
                .equals("ok"))
            return false;
        else
            return true;
    }

    @Action.Handler(action = SUBMIT)
    public void submit(Bot bot, Update update) {
       // Photo.addPhotoToStorage(bot, this.name, photo.getFileId());
        Song song = new Song(this.name, this.photo);
        DaoSongResponse daoSongResponse = new SongDao()
                .addSong(bot, song);

        String text;
        if (daoSongResponse.isSuccessful())
            text = "Песня \""+ this.name +"\" сохранена";
        else
            text = daoSongResponse.getMessage();

        Messages.editCurrentMessage(bot, update, text, null);
        Messages.sendKeyboardMessage(bot, update, "Меню", Keyboards.menuKeyboard());
        this.destroy();
    }

    @Action.InvalidHandler(action = SUBMIT)
    public void submitInvalid(Bot bot, Update update) {
        String text = "Сохранить песню?";
        Messages.sendKeyboardMessage(bot, update, text, Keyboards.submitionKeyboard());
    }


}