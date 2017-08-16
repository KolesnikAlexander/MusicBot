package com.gmail.alex60070.util.button;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by alex60070 on 15.08.17.
 */
public class Buttons {
    public static void sendButtons(TelegramLongPollingBot bot, Message message, String text, InlineKeyboardMarkup markup){
        SendMessage reply = new SendMessage() // Create a message object object
                .setChatId(message.getChatId())
                .setText(text);
        reply.setReplyMarkup(markup);
        try {
            bot.sendMessage(reply); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
