package com.ru.mirea.emelyanenko.notificationapp;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "com.mirea.notification.CHANNEL";
    private static final int PERMISSION_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Проверка разрешений (для Android 13 и выше)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                Log.d("NotificationApp", "Запрос разрешения на уведомления");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, PERMISSION_CODE);
            } else {
                Log.d("NotificationApp", "Разрешение уже предоставлено");
            }
        }
    }

    // Метод для отправки уведомления
    public void onClickSendNotification(View view) {
        // Проверка разрешений
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("NotificationApp", "Нет разрешения на уведомления");
            return;
        }

        // Создание канала уведомлений (для Android 8 и выше)
        createNotificationChannel();

        // Создание уведомления
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info) // Иконка
                .setContentTitle("MIREA") // Заголовок
                .setContentText("Поздравляем!") // Текст
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Приоритет
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Это тестовое уведомление от приложения NotificationApp.")); // Расширенный текст

        // Отправка уведомления
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }

    // Метод для создания канала уведомлений
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MIREA Channel"; // Название канала
            String description = "Канал уведомлений для приложения MIREA"; // Описание канала
            int importance = NotificationManager.IMPORTANCE_DEFAULT; // Важность

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Регистрация канала
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}