package com.kurzandroidu.zakladyandroidu;

import android.content.Intent;

public class Intents {
    public static final String INTENT_ACTION_SEND_MSG1 = "com.kurzandroidu.zakladyandroidu.action.SEND_MSG1";
    public static final String INTENT_ACTION_SEND_MSG2 = "com.kurzandroidu.zakladyandroidu.action.SEND_MSG2";

    public static class ShowMsg1Intent extends Intent {
        public ShowMsg1Intent(String text) {
            setAction(INTENT_ACTION_SEND_MSG1);
            putExtra("msg", text);
        }
    }

    public static class ShowMsg2Intent extends Intent {
        public ShowMsg2Intent(String text) {
            setAction(INTENT_ACTION_SEND_MSG2);
            putExtra("msg", text);
        }
    }
}
