package com.mirea.emelyanenkomo.dialog;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Убедись, что activity_main.xml существует

        findViewById(R.id.buttonShare).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Mirea");
            startActivity(Intent.createChooser(intent, "Выбор за вами!"));
        });
    }
}