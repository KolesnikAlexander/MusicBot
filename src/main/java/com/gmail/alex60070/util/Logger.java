package com.gmail.alex60070.util;

import com.gmail.alex60070.Settings;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * Created by alex60070 on 31.07.17.
 */
public class Logger {
    public static synchronized void log(String message) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date).toString();//2016/11/16 12:08:43

        try {
            Files.write(Paths.get(Settings.LOG_PATH), (time+":   " + message+"\n").getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            //nop
        }
    }
    public static synchronized void userLog(Message message, String action) {
        User user = message.getFrom();
        log(user.getFirstName() + " "
                + user.getLastName()+ " "
                + "(usrName: "+user.getUserName()+")" + "\n\t\t\t\t Action: "+ action);
    }


    public static void createLog() {
        try {
            File f = new File(Settings.LOG_PATH);
            if (!f.exists()) {

                PrintWriter out = new PrintWriter(Settings.LOG_PATH, "UTF-8");
                out.close();
            }
            log("Server Started");

        } catch (FileNotFoundException e) {

        } catch (UnsupportedEncodingException e) {

        }
    }
}
