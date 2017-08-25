package com.gmail.alex60070.dao;

import com.gmail.alex60070.entity.Song;

/**
 * Created by alex60070
 */
public class DaoSongResponse {
    boolean successful;
    String message;
    Song song;

    public DaoSongResponse(boolean successful, Song song, String message) {
        this.successful = successful;
        this.message = message;
        this.song = song;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }

    public Song getSong() {
        return song;
    }
}
