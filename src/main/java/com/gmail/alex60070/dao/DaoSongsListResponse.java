package com.gmail.alex60070.dao;

import com.gmail.alex60070.entity.Song;

import java.util.List;

/**
 * Created by alex60070
 */
public class DaoSongsListResponse {
    boolean successful;
    String message;
    List<Song> songs;

    public DaoSongsListResponse(boolean successful, String message, List<Song> songs) {
        this.successful = successful;
        this.message = message;
        this.songs = songs;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }

    public List<Song> getSongs() {
        return songs;
    }
}
