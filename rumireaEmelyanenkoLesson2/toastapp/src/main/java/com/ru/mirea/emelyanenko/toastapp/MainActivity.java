package com.ru.mirea.emelyanenko.toastapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Обработчик нажатия на кнопку
    public void onClickCountCharacters(View view) {
        // Получаем ссылку на поле ввода
        EditText editText = findViewById(R.id.editText_input);

        // Получаем текст из поля ввода
        String inputText = editText.getText().toString();

        // Подсчитываем количество символов
        int characterCount = inputText.length();

        // Формируем сообщение
        String message = "СТУДЕНТ № 11 ГРУППА БИСО-01-20 \nКоличество символов: " + characterCount;

        // Отображаем сообщение через Toast
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(android.view.Gravity.CENTER, 0, 0); // Позиционируем в центре экрана
        toast.show();
    }
}