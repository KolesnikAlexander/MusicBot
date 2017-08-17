package com.gmail.alex60070.util.dialog;

import com.gmail.alex60070.util.dialog.Dialog;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex60070 on 17.08.17.
 */
public class DialogManager {
        private static List<Dialog> dialogs = new LinkedList<>();

        public static void add(Dialog dialog){
            dialogs.add(dialog);
        }

        /**
         * Returns session if it exists and null otherwise.
         * @return
         */
        public static Dialog getDialog(Long chatId){
            for (Dialog dialog : dialogs) {
                if (dialog.getChatId().equals(chatId))
                    return dialog;
            }
            return null;
        }
        public static boolean dialogExists(Long chatId){
            return getDialog(chatId) != null;
        }
        static void deleteDialog(Dialog dialog){
            dialogs.remove(dialog);
        }
}
