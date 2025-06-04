package com.mirea.emelyanenkomo.internalfilestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "historical_date.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText dateEditText = findViewById(R.id.editTextDate);
        EditText descriptionEditText = findViewById(R.id.editTextDescription);
        Button saveButton = findViewById(R.id.btnSave);

        saveButton.setOnClickListener(v -> {
            String date = dateEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            saveToFile(date, description);
        });

        // Проверка существования файла и чтение
        if (fileExists(FILE_NAME)) {
            String content = readFromFile();
            String[] parts = content.split("\n");
            if (parts.length >= 2) {
                dateEditText.setText(parts[0]);
                descriptionEditText.setText(parts[1]);
            }
        }
    }

    private void saveToFile(String date, String description) {
        try (FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
             OutputStreamWriter osw = new OutputStreamWriter(fos)) {
            osw.write(date + "\n" + description);
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile() {
        StringBuilder content = new StringBuilder();
        try (FileInputStream fis = openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private boolean fileExists(String filename) {
        File file = getFileStreamPath(filename);
        return file != null && file.exists();
    }
}