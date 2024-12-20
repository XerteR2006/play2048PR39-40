package com.example.a2048;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.a2048.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button button1, button2, button3, button4;
    private EditText editTextText, editTextText2;


    private Random random = new Random();
    private int clickCount = 0; // Счетчик нажатий

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Инициализация кнопок и текстового поля
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button);
        button3 = findViewById(R.id.button2);
        button4 = findViewById(R.id.button5);
        editTextText = findViewById(R.id.editTextText);
        editTextText2 = findViewById(R.id.editTextText2);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Установка слушателей на кнопки
        setButtonClickListener(button1);
        setButtonClickListener(button2);
        setButtonClickListener(button3);
        setButtonClickListener(button4);
    }

    private void setButtonClickListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = random.nextInt(9) + 0; // Генерация случайного числа от 0 до 9
                button.setText(String.valueOf(randomNumber)); // Установка текста кнопки

                clickCount++; // Увеличиваем счетчик нажатий
                editTextText2.setText("Всего попыток: " + clickCount); // Обновляем текстовое поле

                checkWinCondition(); // Проверка условия победы
            }
        });
    }

    private void checkWinCondition() {
        // Получение значений из кнопок с проверкой на пустоту
        int value1 = getButtonValue(button1);
        int value2 = getButtonValue(button2);
        int value3 = getButtonValue(button3);
        int value4 = getButtonValue(button4);

        // Проверка на победу (2048)
        if (value1 == 2 && value2 == 0 && value3 == 4 && value4 == 8) {
            editTextText.setText("Вы победили!");
            disableButtons(); // Блокировка кнопок
        }
    }

    private int getButtonValue(Button button) {
        String text = button.getText().toString();
        if (text.isEmpty()) {
            return 0; // Если текст пустой, возвращаем 0
        }
        return Integer.parseInt(text); // Преобразуем текст в число
    }

    private void disableButtons() {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
    }
}
