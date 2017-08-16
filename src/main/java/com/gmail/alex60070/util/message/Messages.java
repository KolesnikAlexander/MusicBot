package com.gmail.alex60070.util.message;

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
            System.out.println("Failed to send message");
        }
    }

    public static void editMessage(TelegramLongPollingBot bot, Update update, String text, InlineKeyboardMarkup markup) {
        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();

        EditMessageText newMessage = new EditMessageText()
                .setChatId(chatId)
                .setMessageId(toIntExact(messageId))
                .setText(text)
                .setReplyMarkup(markup);
        try {
            bot.editMessageText(newMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}