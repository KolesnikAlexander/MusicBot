package com.gmail.alex60070.session;

import java.util.List;

 public class Session {
    Long chatId;
    String status;
    List<Long> messagesId; // messa
    String data;

     Session(Long chatId) {
        this.chatId = chatId;
    }

     public void setStatus(String status) {
         this.status = status;
     }

     public void setMessagesId(List<Long> messagesId) {
         this.messagesId = messagesId;
     }

     public void setData(String data) {
         this.data = data;
     }
 }
