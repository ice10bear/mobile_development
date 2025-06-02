package com.mirea.emelyanenkomo.lesson4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

// Импортируем сгенерированный ViewBinding класс
import com.mirea.emelyanenkomo.lesson4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализируем binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Обращаемся к существующим элементам интерфейса
        binding.textTitle.setText("Трек: Моя любимая песня");
        binding.textArtist.setText("Исполнитель: MIREA Band");

        // Обработчик кнопки "Воспроизвести"
        binding.buttonPlay.setOnClickListener(v -> {
            Log.d("MainActivity", "Кнопка 'Воспроизвести' нажата");
        });

        // Обработчик кнопки "Остановить"
        binding.buttonStop.setOnClickListener(v -> {
            Log.d("MainActivity", "Кнопка 'Остановить' нажата");
        });
    }
}