package com.mirea.emelyanenkomo.lesson3;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Получаем текущее время
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());

        // 2. Номер по списку
        int myNumberInGroup = 11;
        int square = myNumberInGroup * myNumberInGroup;

        // 3. Создаем Intent для запуска второй Activity
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("CURRENT_TIME", currentTime);
        intent.putExtra("SQUARE", square);

        // 4. Запускаем вторую Activity
        startActivity(intent);
    }
}