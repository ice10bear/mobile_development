package com.mirea.emelyanenkomo.simplefragmentapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private Fragment firstFragment, secondFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();

        fragmentManager = getSupportFragmentManager();

        Button btnFirst = findViewById(R.id.btnFirstFragment);
        Button btnSecond = findViewById(R.id.btnSecondFragment);

        if (savedInstanceState == null) {
            loadFragment(firstFragment);
        }

        btnFirst.setOnClickListener(v -> loadFragment(firstFragment));
        btnSecond.setOnClickListener(v -> loadFragment(secondFragment));
    }

    private void loadFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack(null)
                .commit();
    }
}