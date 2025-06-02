package com.mirea.emelyanenkomo.workmanager;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NewWorker extends Worker {

    public NewWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("NewWorker", "Фоновая задача выполнена!");
        return Result.success();
    }
}