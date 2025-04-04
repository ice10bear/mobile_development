package com.ru.mirea.emelyanenko.intentfilter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Обработчик нажатия для открытия веб-страницы
    public void onClickOpenBrowser(View view) {
        // Создаем URI с адресом страницы
        Uri address = Uri.parse("https://rttf.ru/");
        // Создаем намерение для просмотра веб-страницы
        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
        // Запускаем активность
        startActivity(openLinkIntent);
    }

    // Обработчик нажатия для передачи данных
    public void onClickShareData(View view) {
        // Создаем намерение для передачи текстовых данных
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MIREA");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "ФАМИЛИЯ ИМЯ ОТЧЕСТВО");

        // Создаем диалог выбора приложения
        startActivity(Intent.createChooser(shareIntent, "МОИ ФИО"));
    }
}