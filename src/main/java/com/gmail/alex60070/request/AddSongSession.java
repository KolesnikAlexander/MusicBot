package com.gmail.alex60070.request;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.Keyboards;
import com.gmail.alex60070.session.Session;
import com.gmail.alex60070.session.SessionManager;
import com.gmail.alex60070.util.message.Messages;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 16.08.17.
 */
public class AddSongSession {

    //Button initiated
    public static void addSongClick(Bot bot, Update update) {
        String text = "Добавить песню\n" +
                "Введите название песни:";
        Session session = SessionManager.createSesion(update.getCallbackQuery()//!!!!
                .getMessage()
                .getChatId());
        session.setRequest("add_song");

        Messages.editCurrentMessage(bot, update, text, null);
//        try {
//            bot.deleteMessage(new DeleteMessage().setMessageId(123));
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }
    //Message initiated
    public static void nameIsSent(Bot bot, Update update, Session session) {
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
