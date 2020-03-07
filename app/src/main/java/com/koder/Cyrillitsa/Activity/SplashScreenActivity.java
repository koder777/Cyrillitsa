package com.koder.Cyrillitsa.Activity;

/**
 * Created by Viktor Galkin/koder/on 01.02.2020
 */

// строки класса   начинаются со слова “import”. Это инструкции, которые дают указания включить другие пакеты в нашем проекте.
// Этот инструмент даёт нам возможность пользоваться тем кодом,который уже за нас написан другими программистами

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

import com.koder.Cyrillitsa.MainActivity;
import com.koder.Cyrillitsa.R;

public class SplashScreenActivity extends Activity {


    // Время в милесекундах, в течение которого будет отображаться Splash Screen
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    //   МЕТОД  onCreate()  АКТИВНОСТИ Splash ScreenActivity ДЛЯ ЕЕ ИНИЦИАЛИЗАЦИИ

    // метод onCreate() – он вызывается, когда //приложение создаёт и отображает разметку активности.
    // Метод помечен как protected и сопровождается аннотацией @Override (переопределён из базового класса). Аннотация
    // может пригодиться вам. Если вы сделаете опечатку в имени метода, токомпилятор сможет предупредить вас, сообщив об отсутствии
    // такого метода у родительского класса Activity.

    //Класс bundle служит для передачи данных простых типов между Активити.

    // Передача осуществляется через различные механизмы

    // (1. Просто ключ-значение
    // 2. Интерфейс Parcelable
    // 3. механизм Serizilable
    // 4. библиотека GSON От гугла.)



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // По истечении времени, запускаем главный активити, а Splash Screen закрываем
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

