package com.mirea.emelyanenkomo.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mirea.emelyanenkomo.mireaproject.R;

public class ProfileFragment extends Fragment {

    private EditText editName, editAge, editFavColor;
    private SharedPreferences sharedPref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        editName = view.findViewById(R.id.editName);
        editAge = view.findViewById(R.id.editAge);
        editFavColor = view.findViewById(R.id.editFavColor);
        Button btnSave = view.findViewById(R.id.btnSave);

        sharedPref = requireContext().getSharedPreferences("user_profile", Context.MODE_PRIVATE);

        // Загрузка сохранённых данных
        editName.setText(sharedPref.getString("name", ""));
        editAge.setText(sharedPref.getString("age", ""));
        editFavColor.setText(sharedPref.getString("fav_color", ""));

        btnSave.setOnClickListener(v -> saveProfile());

        return view;
    }

    private void saveProfile() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", editName.getText().toString());
        editor.putString("age", editAge.getText().toString());
        editor.putString("fav_color", editFavColor.getText().toString());
        editor.apply();
    }
}