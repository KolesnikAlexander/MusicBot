package com.gmail.alex60070.entity;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.Settings;
import com.gmail.alex60070.util.document.Documents;

import java.io.*;

/**
 * Created by alex60070
 */
public class Photo {
    public static synchronized void addPhotoToStorage(Bot bot, String name, String fileId){
        InputStream inputStream = Documents.downloadDocument(bot, fileId);
        byte[] buffer;
        OutputStream outStream;

        try {
            /*
                Create file
             */
            File targetFile = new File("db_images/"+name);
            if (!targetFile.exists()) {
                PrintWriter out = new PrintWriter(Settings.LOG_PATH, "UTF-8");
                out.close();
            }
            /*
                Downloading the file
             */
            outStream = new FileOutputStream(targetFile);
            int val;
            while ((val = inputStream.read()) != -1){
                outStream.write(val);
            }

            inputStream.close();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
