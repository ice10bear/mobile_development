package com.mirea.emelyanenkomo.looper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread {
    private Handler handler;

    @Override
    public void run() {
        Looper.prepare();

        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d("MyLooper", "Получено сообщение: " + msg.obj);
                // Отправляем ответ обратно
                Message response = Message.obtain(null, 0);
                response.obj = "Ответ от потока: " + msg.obj;
                if (msg.replyTo != null) {
                    try {
                        msg.replyTo.send(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Looper.loop();
    }

    public Handler getHandler() {
        return handler;
    }

    public void quit() {
        if (handler != null) {
            handler.getLooper().quit();
        }
    }
}