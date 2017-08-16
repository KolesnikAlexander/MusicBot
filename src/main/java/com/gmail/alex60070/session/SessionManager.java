package com.gmail.alex60070.session;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex60070 on 16.08.17.
 */
public class SessionManager {
    private static List<Session> sessions = new LinkedList<>();

    public static Session createSesion(Long chatId){
        Session session = new Session(chatId);
        sessions.add(session);
        return session;
    }

    /**
     * Returns session if it exists and null otherwise.
     * @return
     */
    public static Session getSession(Long chatId){
        for (Session session : sessions) {
            if (session.chatId.equals(chatId))
                return session;
        }
        return null;
    }
    public static boolean sessionExists(Long chatId){
        return getSession(chatId) != null;
    }
}
