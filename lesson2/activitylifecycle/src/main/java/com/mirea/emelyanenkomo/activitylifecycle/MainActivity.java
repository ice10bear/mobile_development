package com.mirea.emelyanenkomo.activitylifecycle;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_TEXT = "input_text";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate");

        editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            // Можно добавить действия при нажатии
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String text = editText.getText().toString();
        outState.putString(KEY_TEXT, text);
        Log.i(TAG, "onSaveInstanceState: " + text);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String restoredText = savedInstanceState.getString(KEY_TEXT, "");
        editText.setText(restoredText);
        Log.i(TAG, "onRestoreInstanceState: " + restoredText);
    }
}

/*
6. Ответы на вопросы
❓ 1. Будет ли вызван метод onCreate после нажатия на кнопку «Home» и возврата в приложение?
❌ Нет. После нажатия Home вызываются onPause и onStop. При возврате в приложение снова вызываются onRestart, onStart, onResume. Метод onCreate не вызывается.

❓ 2. Изменится ли значение поля EditText после нажатия на кнопку «Home» и возврата в приложение?
✅ Да, если вы используете onSaveInstanceState и onRestoreInstanceState, как показано выше. Тогда состояние сохраняется автоматически.

❓ 3. Изменится ли значение поля EditText после нажатия на кнопку «Back» и возврата в приложение?
❌ Нет, потому что при нажатии Back Activity уничтожается (onDestroy), и при повторном запуске будет вызван onCreate. Если не сохранять данные в onSaveInstanceState, то данные будут потеряны.



 */