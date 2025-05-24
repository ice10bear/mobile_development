package com.mirea.emelyanenkomo.toastapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Gravity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputText = findViewById(R.id.editTextInput);
        Button button = findViewById(R.id.btnShowToast);

        // Обработчик нажатия на кнопку
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString();
                int length = text.length();

                // Формируем сообщение
                String message = "Студент №11 Группа БИСО-01-20 Количество символов - " + length;

                // Создаем Toast
                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0); // Устанавливаем позицию по центру

                // Отображаем Toast
                toast.show();
            }
        });
    }
}