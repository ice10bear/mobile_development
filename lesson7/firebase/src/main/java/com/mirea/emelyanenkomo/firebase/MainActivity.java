package com.mirea.emelyanenkomo.firebase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FirebaseAuthExample";
    private FirebaseAuth mAuth;

    private EditText emailField, passwordField;
    private TextView statusText;
    private Button signInButton, createAccountButton, signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        statusText = findViewById(R.id.statusText);
        signInButton = findViewById(R.id.signInButton);
        createAccountButton = findViewById(R.id.createAccountButton);
        signOutButton = findViewById(R.id.signOutButton);

        signInButton.setOnClickListener(v -> signIn(emailField.getText().toString(), passwordField.getText().toString()));
        createAccountButton.setOnClickListener(v -> createAccount(emailField.getText().toString(), passwordField.getText().toString()));
        signOutButton.setOnClickListener(v -> signOut());

        updateUI(mAuth.getCurrentUser());
    }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
                        updateUI(mAuth.getCurrentUser());
                    } else {
                        Toast.makeText(MainActivity.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Вход выполнен", Toast.LENGTH_SHORT).show();
                        updateUI(mAuth.getCurrentUser());
                    } else {
                        Toast.makeText(MainActivity.this, "Ошибка входа", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            statusText.setText("Пользователь: " + user.getEmail());
            signOutButton.setVisibility(View.VISIBLE);
        } else {
            statusText.setText("Не авторизован");
            signOutButton.setVisibility(View.GONE);
        }
    }
}