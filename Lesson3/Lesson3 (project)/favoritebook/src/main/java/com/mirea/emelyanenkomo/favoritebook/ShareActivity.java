package com.mirea.emelyanenkomo.favoritebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {

    public static final String USER_MESSAGE = "MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        EditText editTextBook = findViewById(R.id.editTextBook);
        EditText editTextQuote = findViewById(R.id.editTextQuote);

        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(v -> sendDataBack(v));
    }

    public void sendDataBack(View view) {
        EditText editTextBook = findViewById(R.id.editTextBook);
        EditText editTextQuote = findViewById(R.id.editTextQuote);

        String book = editTextBook.getText().toString();
        String quote = editTextQuote.getText().toString();

        Intent returnIntent = new Intent();
        String message = "Название Вашей любимой книги: " + book + ". Цитата: " + quote;
        returnIntent.putExtra(USER_MESSAGE, message);

        setResult(RESULT_OK, returnIntent);
        finish(); // Закрываем ShareActivity и возвращаемся к MainActivity
    }
}