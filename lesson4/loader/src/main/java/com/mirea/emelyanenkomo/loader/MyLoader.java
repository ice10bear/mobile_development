package com.mirea.emelyanenkomo.loader;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

public class MyLoader extends AsyncTaskLoader<String> {

    private static final String TAG = "MyLoader";

    public MyLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        Log.d(TAG, "onStartLoading");
        forceLoad(); // Запуск загрузки
    }

    @Override
    public String loadInBackground() {
        Log.d(TAG, "loadInBackground");

        // Имитация длительной операции
        SystemClock.sleep(3000); // 3 секунды
        return "Данные загружены в фоновом потоке";
    }
}