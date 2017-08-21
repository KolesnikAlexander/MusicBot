package com.gmail.alex60070.dao;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.entity.Photo;
import com.gmail.alex60070.entity.Song;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex60070
 */
public class SongDao {
    private static LinkedList<Song> songs = new LinkedList<>();
    static {
        songs.add(new Song("Hello", "123"));
        songs.add(new Song("Bro", "123456"));
        songs.add(new Song("Привет", "123456"));
    }

    public synchronized DaoSongResponse addSong(Bot bot, Song song) {
        if (songs.contains(song))
            return new DaoSongResponse(false, null, "Песня с этим названием уже существует");
        else{
            songs.add(song);
            Photo.addPhotoToStorage(bot, song.getName(),song.getPhotoSize().getFileId());
            return new DaoSongResponse(true, null, "Песня успешно добавлена");
        }
    }

    public synchronized DaoSongResponse getSong(String name) {
        for (Song song: songs) {
            if(song.getName().equals(name)){
                Song result = new Song(name, "path of song");
                return new DaoSongResponse(true, result, name);
            }
        }
    return new DaoSongResponse(false, null, "Песня не найдена");
    }

    public synchronized LinkedList<Song> getSongsList() {
        return songs;
    }
}