package com.mirea.emelyanenkomo.workmanager;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import com.mirea.emelyanenkomo.workmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonStartWork.setOnClickListener(v -> {
            WorkRequest workRequest = new OneTimeWorkRequest.Builder(NewWorker.class).build();
            WorkManager.getInstance(this).enqueue(workRequest);
        });
    }
}