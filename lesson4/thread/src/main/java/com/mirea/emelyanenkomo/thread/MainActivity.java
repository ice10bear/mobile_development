package com.mirea.emelyanenkomo.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.mirea.emelyanenkomo.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int counter = 0; // Счетчик запущенных потоков

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Получаем ссылку на основной поток
        Thread mainThread = Thread.currentThread();
        Log.d("MainActivity", "Имя основного потока: " + mainThread.getName());

        // Меняем имя потока
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БИСО-01-20, НОМЕР ПО СПИСКУ: 11");
        Log.d("MainActivity", "Новое имя потока: " + mainThread.getName());
        Log.d("MainActivity", "Группа потока: " + mainThread.getThreadGroup());
        Log.d("MainActivity", "Стек вызовов: " + java.util.Arrays.toString(mainThread.getStackTrace()));

        // Обработчик нажатия кнопки
        binding.buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int threadNumber = counter++;
                        Log.d("ThreadProject", String.format("Запущен поток № %d", threadNumber));

                        try {
                            String totalLessonsStr = binding.editTotalLessons.getText().toString();
                            String schoolDaysStr = binding.editSchoolDays.getText().toString();

                            if (totalLessonsStr.isEmpty() || schoolDaysStr.isEmpty()) {
                                updateResultOnUiThread("Введите оба числа");
                                return;
                            }

                            int totalLessons = Integer.parseInt(totalLessonsStr);
                            int schoolDays = Integer.parseInt(schoolDaysStr);

                            if (schoolDays == 0) {
                                updateResultOnUiThread("Дни не должны быть равны 0");
                                return;
                            }

                            double average = (double) totalLessons / schoolDays;

                            // Логируем выполнение
                            for (int i = 0; i < 5; i++) {
                                Thread.sleep(1000); // имитация длительной операции
                                Log.d("ThreadProject", "Вычисление... " + (i + 1));
                            }

                            String resultText = String.format("Среднее количество пар в день: %.2f", average);
                            updateResultOnUiThread(resultText);

                        } catch (NumberFormatException e) {
                            updateResultOnUiThread("Введите корректные числа");
                        } catch (InterruptedException e) {
                            Log.e("ThreadProject", "Ошибка в потоке № " + threadNumber, e);
                        }

                        Log.d("ThreadProject", "Выполнен поток № " + threadNumber);
                    }
                }).start();
            }
        });
    }

    // Метод для обновления текста в UI из фонового потока
    private void updateResultOnUiThread(final String text) {
        runOnUiThread(() -> binding.textResult.setText(text));
    }
}