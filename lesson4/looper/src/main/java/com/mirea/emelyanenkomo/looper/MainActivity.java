package com.mirea.emelyanenkomo.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mirea.emelyanenkomo.looper.databinding.ActivityMainBinding; // Убедитесь в правильном пути

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MyLooper myLooper;
    private Messenger messenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textViewResult.setText("Ожидание...");

        // Создаем и запускаем поток с Looper
        myLooper = new MyLooper();
        myLooper.start();

        // Ждём, пока Looper инициализируется
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Получаем Handler фонового потока
        Handler backgroundHandler = myLooper.getHandler();

        // Создаем Messenger для ответа от фонового потока
        messenger = new Messenger(new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String result = (String) msg.obj;
                binding.textViewResult.append("\n" + result);
            }
        });

        // Пример отправки сообщения в поток
        binding.buttonMirea.setOnClickListener(v -> {
            String messageText = "Hello from UI";
            Message msg = Message.obtain(null, 1);
            msg.obj = messageText;
            msg.replyTo = messenger;
            backgroundHandler.sendMessage(msg);
        });
    }

    @Override
    protected void onDestroy() {
        // Останавливаем Looper
        if (myLooper != null && myLooper.isAlive()) {
            myLooper.quit(); // Теперь метод quit() существует
        }
        super.onDestroy();
    }
}