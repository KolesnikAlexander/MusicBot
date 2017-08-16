package com.gmail.alex60070.util;

import java.io.InputStream;

/**
 * Created by alex60070 on 31.07.17.
 */
public class JarUtils {
    public InputStream accessFileInJar(String uri){
                return getClass().getResourceAsStream(uri);
    }
}
