package com.mirea.emelyanenkomo.dialogapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showTimeDialog(View view) {
        FragmentManager fm = getSupportFragmentManager();
        MyTimeDialogFragment timeDialog = new MyTimeDialogFragment();
        timeDialog.show(fm, "timePicker");
    }

    public void showDateDialog(View view) {
        FragmentManager fm = getSupportFragmentManager();
        MyDateDialogFragment dateDialog = new MyDateDialogFragment();
        dateDialog.show(fm, "datePicker");
    }

    public void showProgressDialog(View view) {
        FragmentManager fm = getSupportFragmentManager();
        MyProgressDialogFragment progress = new MyProgressDialogFragment();
        progress.show(fm, "progressDialog");
    }
}