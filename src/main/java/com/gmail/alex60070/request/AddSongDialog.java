package com.gmail.alex60070.request;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.Keyboards;
import com.gmail.alex60070.util.dialog.AbstractDialog;
import com.gmail.alex60070.util.dialog.DialogManager;
import com.gmail.alex60070.util.message.Messages;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 17.08.17.
 */
public class AddSongDialog extends AbstractDialog {

    public AddSongDialog(Long chatId, DialogManager manager) {
        super(chatId, manager);
    }

    @Override
    public void startDialog(Bot bot, Update update) {
        addSongClick(bot, update);
        setAction("inputSongName");
    }

    @Override
    public void joinDialog(Bot bot, Update update) {
        if (getAction().equals("inputSongName")){
            inputSongName(bot, update);
            setAction("sendSongPhoto");
        }
    }

    public static void addSongClick(Bot bot, Update update) {
        String text = "Добавить песню\n" +
                "Введите название песни:";
        Messages.editCurrentMessage(bot, update, text, null);
    }
    //Message initiated
    public static void inputSongName(Bot bot, Update update) {
        //if (cancellButtonPressed())
        if (!nameIsSentValidate(bot, update)){
            return;
        }
        String text = "Название: \""+update.getMessage().getText() +"\"\n"+
                "Пришлите фото с текстом:";
        Messages.sendKeyboardMessage(bot, update, text, Keyboards.backKeyKeyboard("menu"));
    }

    private static boolean nameIsSentValidate(Bot bot, Update update) {
        Message message = update.getMessage();
        if (message == null || !message.hasText() || !textIsValid(message.getText())) {
            String text = "Пришлите название песни. Запрещенный символ: \'/\'.";
            Messages.sendKeyboardMessage(bot, update, text, null);//Keyboards.backKeyKeyboard("options"));
            return false;
        }
        else {
            return true;
        }
    }

    private static boolean textIsValid(String text) {
        // TODO: 16.08.17 Create validation pattern
        return true;
    }


}
