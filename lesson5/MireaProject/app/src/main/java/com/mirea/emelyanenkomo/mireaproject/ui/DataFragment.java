package com.mirea.emelyanenkomo.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.mirea.emelyanenkomo.mireaproject.MyWorker;
import com.mirea.emelyanenkomo.mireaproject.R;

public class DataFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        Button startWorkButton = view.findViewById(R.id.startWorkButton);
        startWorkButton.setOnClickListener(v -> {
            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
            WorkManager.getInstance(requireContext()).enqueue(workRequest);
        });

        return view;
    }
}