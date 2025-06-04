package com.mirea.emelyanenkomo.securesharedpreferences;


import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    private TextView textViewPoetName;
    private ImageView imageViewPoet;
    private Button btnSavePoet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPoetName = findViewById(R.id.textViewPoetName);
        imageViewPoet = findViewById(R.id.imageViewPoet);
        btnSavePoet = findViewById(R.id.btnSavePoet);

        try {
            // Генерация мастер-ключа
            String mainKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            // Инициализация EncryptedSharedPreferences
            SharedPreferences secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secure_poet_prefs",
                    mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            // Чтение данных
            String poetName = secureSharedPreferences.getString("poet_name", "Неизвестный поэт");
            textViewPoetName.setText("Любимый поэт: " + poetName);

            // Кнопка для сохранения информации
            btnSavePoet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = secureSharedPreferences.edit();
                    editor.putString("poet_name", "Неизвестно");
                    editor.apply();

                    textViewPoetName.setText("Любимый поэт: Маяковский");
                }
            });

        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}