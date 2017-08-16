package com.gmail.alex60070.util.photo;

import org.telegram.telegrambots.api.objects.PhotoSize;

import java.util.Comparator;
import java.util.List;

/**
 * Created by alex60070 on 28.07.17.
 */
public class Photos {
    public static PhotoSize getMax(List<PhotoSize> photos){
        return photos.stream() //get exact photo
                .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                .findFirst()
                .orElse(null);
    }
}
