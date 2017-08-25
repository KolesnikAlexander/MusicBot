package com.gmail.alex60070.dao;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.Settings;
import com.gmail.alex60070.entity.Photo;
import com.gmail.alex60070.entity.Song;
import com.gmail.alex60070.util.Logger;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by alex60070
 */
public class SongDao {
    private static Connection connection;
    private static Map<String, String > songs = new HashMap<>();
    static {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager
                    .getConnection(Settings.dbUrl, Settings.dbUser, Settings.dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log("Cannot create JDBC connection");

        }finally {
            //Logger.log("Successfully connected to the database");
        }
    }

    public synchronized DaoSongResponse addSong(Bot bot, Song song) {
        if (songExists(song.getName()))
            return new DaoSongResponse(false, null, "Песня с этим названием уже существует");
        else{
            String photoPath = addNameToDb(song.getName());
            Photo.addPhotoToStorage(bot, photoPath, song.getPhotoSize().getFileId()); // TODO: 22.08.17 set name to ID!!!
            return new DaoSongResponse(true, null, "Песня успешно добавлена");
        }
    }
    private boolean songExists(String name) {
        return getFilePath(name) != null;
    }
    private String getFilePath(String name) {
        String query = "SELECT photoPath FROM music_bot_db.songs WHERE name = '"+name+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (!resultSet.first())
                return null;
            else {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songs.get(name);
    }
    private String addNameToDb(String name) {
        String photoPath = "ph_"+name;
        String userName = "null";
        String query = "INSERT INTO music_bot_db.songs(name, photoPath, userName) values ('"+
                        name+"','"+
                        photoPath+"',"+
                        userName+")";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //songs.put(name, name); // TODO: 22.08.17 change to path!!!
        return photoPath;
    }

    public synchronized DaoSongResponse getSong(String name) {
       String songPath = getFilePath(name);
       if (songPath != null){
           Song result = new Song(name, songPath);
           return new DaoSongResponse(true, result, name);
       }
        return new DaoSongResponse(false, null, "Песня не найдена");
    }

    public synchronized LinkedList<Song> getSongsList() { // TODO: 22.08.17 CHANGE FOR DB!!!
        LinkedList<Song> songsList = new LinkedList<>();
        String query ="SELECT name FROM music_bot_db.songs";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                songsList.add(new Song(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            Logger.log("Cannot create statement");
        }

//        LinkedList<Song> songsList = new LinkedList<>();
//        Collection<String> songsCollection = songs.values();
//        for (String song : songsCollection) {
//            songsList.add(new Song(song));
//        }
        return songsList;
    }
}