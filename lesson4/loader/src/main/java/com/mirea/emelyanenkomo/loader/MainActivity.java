package com.mirea.emelyanenkomo.loader;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.mirea.emelyanenkomo.loader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {

    private static final String TAG = "MainActivity";
    private static final int MY_LOADER_ID = 1;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonLoad.setOnClickListener(v -> {
            Log.d(TAG, "Кнопка нажата");
            LoaderManager.getInstance(this).restartLoader(MY_LOADER_ID, null, this);
        });
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");
        return new MyLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d(TAG, "onLoadFinished: " + data);
        binding.textViewResult.setText(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
        binding.textViewResult.setText("Данные сброшены");
    }
}