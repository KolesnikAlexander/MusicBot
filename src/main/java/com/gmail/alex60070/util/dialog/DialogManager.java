package com.gmail.alex60070.util.dialog;

import com.gmail.alex60070.Bot;
import com.gmail.alex60070.util.message.Messages;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex60070 on 17.08.17.
 */
public class DialogManager {
        private  List<AbstractDialog> abstractDialogs = new LinkedList<>();

        public void add(AbstractDialog abstractDialog){
            abstractDialogs.add(abstractDialog);
        }

        /**
         * Returns session if it exists and null otherwise.
         * @return
         */
        public AbstractDialog getDialog(Long chatId){
            for (AbstractDialog abstractDialog : abstractDialogs) {
                if (abstractDialog.getChatId().equals(chatId))
                    return abstractDialog;
            }
            return null;
        }
        public boolean processDialog(Bot bot, Update update){

            Message message = Messages.retrieveMessage(update);

            if (this.dialogExists(message.getChatId())) {
                AbstractDialog abstractDialog = this.getDialog(message.getChatId());
                abstractDialog.join(bot, update);
                return true;
            }
            else
                return false;
        }
        public  boolean dialogExists(Long chatId){
            return getDialog(chatId) != null;
        }
         void deleteDialog(AbstractDialog abstractDialog){
            abstractDialogs.remove(abstractDialog);
        }
}
