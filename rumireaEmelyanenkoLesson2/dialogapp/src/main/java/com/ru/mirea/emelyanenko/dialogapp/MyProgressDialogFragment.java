package com.ru.mirea.emelyanenko.dialogapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Создаем AlertDialog с ProgressBar
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Устанавливаем заголовок и сообщение
        builder.setTitle("Загрузка")
                .setMessage("Пожалуйста, подождите...")
                .setView(getActivity().getLayoutInflater().inflate(R.layout.progress_dialog, null)) // Загружаем макет progress_dialog.xml
                .setCancelable(false); // Запрещаем закрытие диалога при нажатии вне его области

        // Возвращаем созданный диалог
        return builder.create();
    }
}