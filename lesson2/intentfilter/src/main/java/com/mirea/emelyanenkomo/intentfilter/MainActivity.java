package com.mirea.emelyanenkomo.intentfilter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String studentInfo = "Емельяненко Мария Олеговна, МИРЭА";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Кнопка "Открыть сайт"
        Button btnOpenBrowser = findViewById(R.id.btnOpenBrowser);
        btnOpenBrowser.setOnClickListener(v -> openWebsite());

        // Кнопка "Поделиться ФИО"
        Button btnShareText = findViewById(R.id.btnShareText);
        btnShareText.setOnClickListener(v -> shareText());
    }

    private void shareText() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Информация о студенте");
        intent.putExtra(Intent.EXTRA_TEXT, studentInfo);

        Intent chooser = Intent.createChooser(intent, "Выберите приложение");
        startActivity(chooser);
    }

    private void openWebsite() {
        Uri webpage = Uri.parse("https://www.mirea.ru/ ");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        intent.setDataAndType(webpage, "text/html");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Нет приложения для открытия ссылок", Toast.LENGTH_SHORT).show();

        }
    }
}