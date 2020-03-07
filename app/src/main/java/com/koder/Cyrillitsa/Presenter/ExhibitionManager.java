package com.koder.Cyrillitsa.Presenter;

/**
 * Created by Viktor Galkin/koder/on 01.02.2020
 */

// строки класса   начинаются со слова “import”. Это инструкции, которые дают указания включить другие пакеты в нашем проекте.
// Этот инструмент даёт нам возможность пользоваться тем кодом,который уже за нас написан другими программистами

import android.app.NotificationManager;

import android.content.Context;

import android.content.SharedPreferences;

import android.preference.PreferenceManager;

import android.webkit.WebView;

//открытый класс MediaCreator(Создатель_Медиа_Воспроизведения) отвечает за воспроизведение медиаресурсов интернета

//и предоставления уведомлений о ходе процесса воспроизведения

//класс MediaCreator состоит из:

//констант:

//переменных:контекста,Веб_вьювера,Настроек_раздач_по_умолчанию

// методов :

//открытый метод типа void Воспроизведение_Медиаресурса_Возможно()

//открытый метод типа void Воспроизведение_Медиаресурса_Невозможно()

//открытый метод типа bool Допустимо_ли_Медиа_Воспроизведение() возвращает настройки ,допускаюшие воспроизведение медиа
//ресурса интернета

//открытый метод типа void Фоновое_Воспроизведение_Медиа()

//открытый метод типа void Показать_Уведомление_о_МедиаВоспроизведении()

//открытый метод типа void Скрыть_Уведомление_Медиа_Воспроизведения()



public class ExhibitionManager {

    //используется строка, называемая тегом. Обычно принято объявлять глобальную статическую строковую переменную TAG в начале кода:
    //
    //    private static final String TAG = "MyApp";
    //
    //    private  делает его не доступным для других классов.
    //    static делает это равномерным значением во всех экземплярах класса.
    //    final делает это немодифицируемым значением. Таким образом, в основном это значение " константа", которое одинаково
    // для всех экземпляров класса и не может быть изменено
    //    Далее уже в любом месте вашей программы вы вызываете нужный метод журналирования с этим тегом:
    //
    //        Log.i(TAG, "Это мое сообщение для записи в журнале");
    //    Также используется в исключениях:
    //
    //        try {
    //// ...
    //} catch (Exception exception) {
    //    Log.e(TAG, "Получено исключение", exception);
    //}

    public static final String PREF_BACKGROUND_PLAY_ENABLED = "exhibitionEnabled";


    //константа-флаг  ID_УВЕДОМЛЕНИЯ = 1337 - 420 * 69
    private static final int NOTIFICATION_ID = 1337 - 420 * 69;

    //контекст
    Context context;

    //Веб_вьювер
    WebView webView;

    //Настройки_раздачи_по_умолчанию
    SharedPreferences shared_preferences;

    //конструктор класса

    public ExhibitionManager (Context context, WebView webView) {
        this.context = context;
        this.webView = webView;

        shared_preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

//открытый метод типа bool Допустим_ли_Показ_Ресурса() возвращает настройки ,допускаюшие воспроизведение медиа
    //ресурса интернета


    public boolean isExhibitionEnabled() {
        return shared_preferences.getBoolean(PREF_BACKGROUND_PLAY_ENABLED, true);
    }



    //открытый метод типа void Скрыть_Уведомление_Показа_Ресурса()


    public void hideExhibitionNotification() {
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.cancel(NOTIFICATION_ID);
    }

}
