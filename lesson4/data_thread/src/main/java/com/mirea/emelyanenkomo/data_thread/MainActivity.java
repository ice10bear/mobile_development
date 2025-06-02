package com.mirea.emelyanenkomo.data_thread;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mirea.emelyanenkomo.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView tvInfo = binding.tvInfo;
        tvInfo.setText("Ожидание запуска потока...\n");

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                tvInfo.post(() -> tvInfo.append("\nВыполнен runn1"));

                Thread.sleep(1000);
                tvInfo.post(() -> tvInfo.append("\nВыполнен runn2"));
                tvInfo.postDelayed(() -> tvInfo.append("\nВыполнен runn3"), 2000);

            } catch (InterruptedException e) {
                Log.e("MainActivity", "Ошибка в потоке", e);
            }
        }).start();
    }
}