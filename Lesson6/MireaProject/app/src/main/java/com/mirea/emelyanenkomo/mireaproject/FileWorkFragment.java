package com.mirea.emelyanenkomo.mireaproject;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileWorkFragment extends Fragment {

    private EditText editFileName, editTextContent;
    private TextView tvFileContent;
    private Button btnSave, btnLoad;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_work, container, false);

        editFileName = view.findViewById(R.id.editFileName);
        editTextContent = view.findViewById(R.id.editTextContent);
        tvFileContent = view.findViewById(R.id.tvFileContent);
        btnSave = view.findViewById(R.id.btnSaveToFile);
        btnLoad = view.findViewById(R.id.btnLoadFromFile);

        btnSave.setOnClickListener(v -> saveToFile());
        btnLoad.setOnClickListener(v -> loadFromFile());

        return view;
    }

    private void saveToFile() {
        String fileName = editFileName.getText().toString();
        String text = editTextContent.getText().toString();

        if (fileName.isEmpty()) {
            Toast.makeText(requireContext(), "Введите имя файла", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            FileOutputStream fos = requireContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(requireContext(), "Файл сохранён", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Ошибка при сохранении", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        String fileName = editFileName.getText().toString();

        if (fileName.isEmpty()) {
            Toast.makeText(requireContext(), "Введите имя файла", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            FileInputStream fis = requireContext().openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            fis.close();
            tvFileContent.setText(sb.toString());
            editTextContent.setText(sb.toString());
            Toast.makeText(requireContext(), "Файл загружен", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            tvFileContent.setText("Файл не найден или ошибка чтения.");
            Toast.makeText(requireContext(), "Ошибка при чтении файла", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}