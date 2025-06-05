package com.mirea.emelyanenkomo.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mirea.emelyanenkomo.mireaproject.ApiService;
import com.mirea.emelyanenkomo.mireaproject.DataItem;
import com.mirea.emelyanenkomo.mireaproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkFragment extends Fragment {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_network, container, false);
        textView = view.findViewById(R.id.textView);
        fetchData();
        return view;
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        service.getUsers().enqueue(new Callback<List<DataItem>>() {
            @Override
            public void onResponse(Call<List<DataItem>> call, Response<List<DataItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    StringBuilder result = new StringBuilder();
                    for (DataItem item : response.body()) {
                        result.append("ID: ").append(item.getId())
                                .append(", Имя: ").append(item.getName())
                                .append(", Email: ").append(item.getEmail())
                                .append("\n");
                    }
                    textView.setText(result.toString());
                }
            }

            @Override
            public void onFailure(Call<List<DataItem>> call, Throwable t) {
                textView.setText("Ошибка загрузки данных");
            }
        });
    }
}