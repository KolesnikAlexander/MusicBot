package com.gmail.alex60070;

import com.gmail.alex60070.entity.Song;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by alex60070 on 15.08.17.
 */
public class Keyboards {

    public static String putCallbackDataSuffix(String data){
        return data + "(" + new Random().nextInt(10000) + ")";
    }

    public static InlineKeyboardMarkup menuKeyboard() {
        InlineKeyboardMarkup menuKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        String data = putCallbackDataSuffix("list");
        rowInline.add(new InlineKeyboardButton().setText("\t\t\t\t\tСписок\t\t\t\t\t").setCallbackData(data));

        data = putCallbackDataSuffix("options");
        rowInline.add(new InlineKeyboardButton()
                .setText("\t\t\t\t\tОпции\t\t\t\t\t")
                .setCallbackData(data));

        rowsInline.add(rowInline);
        menuKeyboard.setKeyboard(rowsInline);
        return menuKeyboard;
    }

    public static InlineKeyboardMarkup backKeyKeyboard(String returnPage) {
        InlineKeyboardMarkup menuBackKeyKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        String data = putCallbackDataSuffix(returnPage);
        rowInline.add(new InlineKeyboardButton()
                .setText("\t\t\t\t\t<< Назад\t\t\t\t\t").setCallbackData(data));

        rowsInline.add(rowInline);
        menuBackKeyKeyboard.setKeyboard(rowsInline);
        return menuBackKeyKeyboard;
    }
    public static InlineKeyboardMarkup optionsKeyboard() {
        InlineKeyboardMarkup menuBackKeyKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        String data = putCallbackDataSuffix("add_song");
        rowInline.add(new InlineKeyboardButton()
                .setText("\t\t\t\t\tДобавить песню\t\t\t\t\t")
                .setCallbackData(data));

        data = putCallbackDataSuffix("menu");
        rowInline.add(new InlineKeyboardButton()
                .setText("\t\t\t\t\t<< Назад\t\t\t\t\t")
                .setCallbackData(data));

        rowsInline.add(rowInline);
        menuBackKeyKeyboard.setKeyboard(rowsInline);
        return menuBackKeyKeyboard;
    }
    public static InlineKeyboardMarkup cancelKeyboard() {
        InlineKeyboardMarkup menuBackKeyKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        String data = putCallbackDataSuffix("add_song");
        rowInline.add(new InlineKeyboardButton()
                .setText("\t\t\t\t\tДобавить песню\t\t\t\t\t")
                .setCallbackData(data));

        data = putCallbackDataSuffix("menu");
        rowInline.add(new InlineKeyboardButton()
                .setText("\t\t\t\t\t<< Назад\t\t\t\t\t")
                .setCallbackData(data));

        rowsInline.add(rowInline);
        menuBackKeyKeyboard.setKeyboard(rowsInline);
        return menuBackKeyKeyboard;
    }

    public static InlineKeyboardMarkup cancelKeyBoard() {
        InlineKeyboardMarkup menuBackKeyKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        String data = putCallbackDataSuffix("cancel");
        rowInline.add(new InlineKeyboardButton()
                .setText("\t\t\t\t\tx Отмена\t\t\t\t\t").setCallbackData(data));

        rowsInline.add(rowInline);
        menuBackKeyKeyboard.setKeyboard(rowsInline);
        return menuBackKeyKeyboard;
    }
    public static InlineKeyboardMarkup submitionKeyboard() {
        InlineKeyboardMarkup menuBackKeyKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        String data = putCallbackDataSuffix("cancel");
        rowInline.add(new InlineKeyboardButton()
                .setText("\t\t\t\t\tОтмена\t\t\t\t\t").setCallbackData(data));

        data = putCallbackDataSuffix("ok");
        rowInline.add(new InlineKeyboardButton()
                .setText("\t\t\t\t\tOK\t\t\t\t\t").setCallbackData(data));

        rowsInline.add(rowInline);
        menuBackKeyKeyboard.setKeyboard(rowsInline);
        return menuBackKeyKeyboard;
    }
    public static InlineKeyboardMarkup songsKeyboard(LinkedList<Song> songs) {
        InlineKeyboardMarkup menuBackKeyKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline;
        String data;
        for (Song song : songs) {
            rowInline = new ArrayList<>(1);
            data = putCallbackDataSuffix("song/" + song.getName());
            rowInline.add(new InlineKeyboardButton()
                    .setText(song.getName())
                    .setCallbackData(data));
            rowsInline.add(rowInline);

        }
        rowInline = new ArrayList<>(1);
        data = putCallbackDataSuffix("menu");
        rowInline.add(new InlineKeyboardButton()
                .setText("<< Назад")
                .setCallbackData(data));
        rowsInline.add(rowInline);

        menuBackKeyKeyboard.setKeyboard(rowsInline);
        return menuBackKeyKeyboard;
    }

}
