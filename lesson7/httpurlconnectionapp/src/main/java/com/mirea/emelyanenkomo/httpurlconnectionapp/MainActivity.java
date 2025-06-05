package com.mirea.emelyanenkomo.httpurlconnectionapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView ipText, cityText, regionText, weatherText;
    private Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipText = findViewById(R.id.ipText);
        cityText = findViewById(R.id.cityText);
        regionText = findViewById(R.id.regionText);
        weatherText = findViewById(R.id.weatherText);
        btnCheck = findViewById(R.id.btnCheck);

        btnCheck.setOnClickListener(v -> new DownloadDataTask().execute());
    }

    private class DownloadDataTask extends AsyncTask<Void, Void, String> {

        private final String TAG = "DownloadDataTask";

        @Override
        protected String doInBackground(Void... voids) {
            try {
                return downloadData("https://ipinfo.io/json");
            } catch (Exception e) {
                Log.e(TAG, "Ошибка загрузки данных", e);
                return null;
            }
        }

        private String downloadData(String address) throws Exception {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setDoInput(true);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read);
                }
                inputStream.close();
                return bos.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONObject json = new JSONObject(result);
                    String ip = json.getString("ip");
                    String city = json.optString("city", "Неизвестно");
                    String region = json.optString("region", "Неизвестно");
                    String country = json.optString("country", "Неизвестно");

                    ipText.setText("IP: " + ip);
                    cityText.setText("Город: " + city);
                    regionText.setText("Регион: " + region);

                    String loc = json.optString("loc", "52.52,13.41");
                    String[] coords = loc.split(",");
                    String latitude = coords[0];
                    String longitude = coords.length > 1 ? coords[1] : "13.41";

                    new FetchWeatherTask(latitude, longitude).execute();

                } catch (Exception e) {
                    Log.e(TAG, "Ошибка парсинга JSON", e);
                }
            } else {
                ipText.setText("Ошибка получения данных");
            }
        }
    }

    private class FetchWeatherTask extends AsyncTask<Void, Void, String> {

        private final String latitude;
        private final String longitude;

        public FetchWeatherTask(String lat, String lon) {
            this.latitude = lat;
            this.longitude = lon;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                        "&longitude=" + longitude + "&current_weather=true";
                return downloadData(weatherUrl);
            } catch (Exception e) {
                Log.e("FetchWeatherTask", "Ошибка получения погоды", e);
                return null;
            }
        }

        private String downloadData(String address) throws Exception {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setDoInput(true);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read);
                }
                inputStream.close();
                return bos.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject currentWeather = json.getJSONObject("current_weather");
                    double temperature = currentWeather.getDouble("temperature");
                    weatherText.setText("Температура: " + temperature + "°C");
                } catch (Exception e) {
                    Log.e("FetchWeatherTask", "Ошибка парсинга погоды", e);
                }
            } else {
                weatherText.setText("Ошибка получения погоды");
            }
        }
    }
}