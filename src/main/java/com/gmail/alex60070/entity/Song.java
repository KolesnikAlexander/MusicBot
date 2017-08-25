package com.gmail.alex60070.entity;

import org.telegram.telegrambots.api.objects.PhotoSize;

import java.io.InputStream;

/**
 * Created by alex60070
 */
public class Song {
    private String name;
    private PhotoSize photo;
    private String photoPath;

    public Song(String name, PhotoSize photoSize) {
        this.name = name;
        this.photo = photoSize;
       // this.photoPath = path(photoSize);
    }
    public Song(String name) {
        this.name = name;
        // this.photoPath = path(photoSize);
    }

    public Song(String name, String photoPath) {
        this.name = name;
        this.photoPath = photoPath;
    }

    public InputStream getPhoto(){
        return null;
    }

    public PhotoSize getPhotoSize(){
        return this.photo;
    }

    private String path(PhotoSize photoSize) {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        return this.getName().equals(song.getName());
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (photoPath != null ? photoPath.hashCode() : 0);
        return result;
    }
}
