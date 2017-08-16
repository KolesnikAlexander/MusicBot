package com.gmail.alex60070.session;

import java.util.List;

 public class Session {
     Long chatId;
     String request;
     List<Long> messagesId; // messa
     String data;

     Session(Long chatId) {
         this.chatId = chatId;
     }

     public Long getChatId() {
         return chatId;
     }

     public String getRequest() {
         return request;
     }

     public List<Long> getMessagesId() {
         return messagesId;
     }

     public String getData() {
         return data;
     }

     public void setRequest(String request) {
         this.request = request;
     }

     public void setMessagesId(List<Long> messagesId) {
         this.messagesId = messagesId;
     }

     public void setData(String data) {
         this.data = data;
     }

     public void destroy() {
         SessionManager.destroySession(this);
     }

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;

         Session session = (Session) o;

         return chatId != null ? chatId.equals(session.chatId) : session.chatId == null;
     }

     @Override
     public int hashCode() {
         return chatId != null ? chatId.hashCode() : 0;
     }
 }