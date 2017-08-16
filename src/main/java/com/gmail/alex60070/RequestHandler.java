package com.gmail.alex60070;

import com.gmail.alex60070.util.message.Messages;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by alex60070 on 28.07.17.
 */
public class RequestHandler {

    public static void start(Bot bot, Update update) {
       // Logger.userLog(message, "start request");
        String text = "Меню";
        //Buttons.sendButtons(bot, message,text, Keyboards.menuKeyboard());
        Messages.sendKeyboardMessage(bot, update,text, Keyboards.menuKeyboard());

    }

    public static void list(Bot bot, Update update) {
        Messages.editCurrentMessage(bot, update, "Список", Keyboards.backKeyKeyboard("menu"));
    }

    public static void menuBack(Bot bot, Update update){
        Messages.editCurrentMessage(bot, update, "Меню", Keyboards.menuKeyboard());
    }

    public static void options(Bot bot, Update update) {
       Messages.editCurrentMessage(bot, update, "Опции", Keyboards.optionsKeyboard());
    }
}
