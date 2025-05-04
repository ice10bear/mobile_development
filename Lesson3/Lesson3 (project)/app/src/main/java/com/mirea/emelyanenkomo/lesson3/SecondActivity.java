package com.mirea.emelyanenkomo.lesson3;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second); // ✅ Убедись, что это верный layout

        // Получаем данные из Intent
        String currentTime = getIntent().getStringExtra("CURRENT_TIME");
        int square = getIntent().getIntExtra("SQUARE", 0);

        // Ищем TextView
        TextView textViewResult = findViewById(R.id.textViewResult);

        // 🔍 Отладка: проверяем, найден ли TextView
        if (textViewResult == null) {
            Log.e("SecondActivity", "textViewResult == null");
            Toast.makeText(this, "textViewResult не найден!", Toast.LENGTH_LONG).show();
        } else {
            String message = "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ ЧИСЛО " + square + ", а текущее время " + currentTime;
            textViewResult.setText(message);
        }
    }
}