package com.mirea.emelyanenkomo.sharer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {

    private static final String TAG = "ShareActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        TextView textView = findViewById(R.id.textViewReceived);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                String receivedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (receivedText != null) {
                    Log.d(TAG, "Получен текст: " + receivedText);
                    textView.setText("Получено: " + receivedText);
                }
            }
        }
    }
}