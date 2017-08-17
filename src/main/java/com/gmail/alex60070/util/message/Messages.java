package com.gmail.alex60070.util.message;

import com.gmail.alex60070.util.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import static java.lang.Math.toIntExact;

/**
 * Created by alex60070 on 28.07.17.
 */
public class Messages {
    public static void sendMessage(TelegramLongPollingBot bot, Message message, String text) {
        SendMessage reply = new SendMessage() // Create a message object object
                .setChatId(message.getChatId())
                .setText(text);
        try {
            bot.sendMessage(reply); // Sending our message object to user
        } catch (TelegramApiException e) {
            Logger.log("Failed to sendMessage");
            e.printStackTrace();
        }
    }

    public static void sendKeyboardMessage(TelegramLongPollingBot bot,
                                           Update update,
                                           String text, InlineKeyboardMarkup markup) {
        SendMessage reply = new SendMessage() // Create a message object
                .setChatId(retrieveMessage(update).getChatId())
                .setText(text)
                .setReplyMarkup(markup);
        try {
            bot.sendMessage(reply); // Sending our message object to user
        } catch (TelegramApiException e) {
            Logger.log("Failed to sendKeyboardMessage");
            e.printStackTrace();
        }
    }

    public static void editCurrentMessage(TelegramLongPollingBot bot, Update update, String text, InlineKeyboardMarkup markup) {
        Message message = retrieveMessage(update);
        long messageId = message.getMessageId();
        long chatId = message.getChatId();

        EditMessageText newMessage = new EditMessageText()
                .setChatId(chatId)
                .setMessageId(toIntExact(messageId))
                .setText(text)
                .setReplyMarkup(markup);
        try {
            bot.editMessageText(newMessage);
        } catch (TelegramApiException e) {
            Logger.log("Failed to editCurrentMessage");
            e.printStackTrace();
        }
    }

    /**
     * If message (text, document, photo, etc) was sent than retrieve <code>Message</code>
     * from <code>Update</code>. If callback query was sent(button is clicked)
     * than retrieve <code>Message</code> from <code>CallbackQuery</code>
     * @param update
     * @return
     */
    public static Message retrieveMessage(Update update){
        if(update.hasCallbackQuery())
            return update.getCallbackQuery().getMessage();
        else
            return update.getMessage();
    }
}