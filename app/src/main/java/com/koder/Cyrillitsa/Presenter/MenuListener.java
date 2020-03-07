package com.koder.Cyrillitsa.Presenter;

/**
 * Created by Viktor Galkin/koder/on 01.02.2020
 */

// строки класса   начинаются со слова “import”. Это инструкции, которые дают указания включить другие пакеты в нашем проекте.
// Этот инструмент даёт нам возможность пользоваться тем кодом,который уже за нас написан другими программистами

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ActionMenuView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import com.koder.Cyrillitsa.R;

//открытый класс MenuListener(Слушающий_Меню) включающий  интерфейс
// Слушателя_Нажатия_Вкладки_Меню_Нижней_Панели.для Меню_Действия_Вьювера

//класс MenuCreatorListener состоит из:

//переменных:

//контекст Context context;

//Веб_вьювер WebView webView;

//Помощник_фонового_воспроизведения ExhibitionManager

//вьювер Окно_приложения View appWindow;

//вьювер Меню_кнопок ActionMenuView actionMenu;

//Настройки SharedPreferences shared_preferences;

//Рисовальщик_разметки DrawerLayout drawerLayout;

//вьювер Панель_закладок View bookmarksPanel;


//конструктора класса MenuCreatorListener

// методов :

//открытый метод типа void Инструкция по изменению домашней страницы() использует объект класса AlertDialog(Диалог_Уведомлений)
//    для инструкций по изменению домашней страницы

//открытый метод типа void Установить_Меню_Кнопок():Воспроизведение_Медиа,Принять_куки,Tor(Тор),Kodi(Кодировать)

//открытый метод типа void Показ_Диалога_Уведомлений_Об_Отсутствии_Выбора_Видео()

//открытый метод типа bool Действия_При_Нажатии_Кнопок_Меню(аргумент Кнопка_меню)


//кнопка Веб button_web

// кнопка Обновить  button_refresh

// кнопка Домой button_home

//кнопка Сделать_Домашней_Страницей button_set_as_home

//кнопка Закладка  button_bookmarks


//кнопка Воспроизведение_медиа  button_media_play




public class MenuListener implements ActionMenuView.OnMenuItemClickListener {

    //контекст

    Context context;

    //Веб_вьювер
    // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
    // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.

    WebView webView;

    ExhibitionManager exhibitionManager;

    //вьювер Окно_приложения
    View appWindow;

    //вьювер Меню_действия
    ActionMenuView actionMenu;

    //Настройки
    SharedPreferences shared_preferences;

    //Рисовальщик_разметки
    DrawerLayout drawerLayout;

    //вьювер Панель_закладок
    View bookmarksPanel;


    //конструктор класса

    public MenuListener(Context context, WebView webView,  ExhibitionManager exhibitionManager, View appWindow) {
        this.context = context;
        this.webView = webView;

        this.exhibitionManager = exhibitionManager;
        this.appWindow = appWindow;

        shared_preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    //открытый метод типа void Инструкция по изменению домашней страницы() использует объект класса AlertDialog(Диалог_Уведомлений)
    //для инструкций по изменению домашней страницы

    public void homepageTutorial() {
        if (!shared_preferences.getBoolean("homepageLearned", false))
        {
            // создать объект класса Диалог_Сообщений_Инструкций
            //
            // Диалоговое окно AlertDialog является расширением класса Dialog, Очень часто требуется показать диалог с кнопками Да и Нет.
            // В создаваемых диалоговых окнах можно задавать  следующие элементы:

            //заголовок
            //текстовое сообщение
            //кнопки: от одной до трех
            //список
            //флажки
            //переключатели

            AlertDialog dialog = new AlertDialog.Builder(context).create();

            //установить заголовок объекта dialog
            dialog.setTitle(context.getString(R.string.home));

            //установить сообщение  объекта dialog
            dialog.setMessage(context.getString(R.string.homePageHelp));

            //диалог не прерывается
            dialog.setCancelable(false);

            //Установить на кнопке надпись "OK"
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int buttonId)
                        {
//метод dismiss() закрывает диалог.
                            dialog.dismiss();
                            SharedPreferences.Editor editor = shared_preferences.edit();
                            editor.putBoolean("homepageLearned", true);


                            // Завершение диалога
                            // Метод фиксации commit() завершает все изменения в БД, проделанные SQL-выражением, и снимает также все блокировки,
// установленные транзакцией.
                            editor.commit();
                        }
                    });
            dialog.show();
        }
    }




//открытый метод типа void Установить_Меню_Кнопок():Воспроизведение_Медиа,Принять_куки,Tor(Тор),Kodi(Кодировать)

    public void setUpMenu(final ActionMenuView actionMenu, final DrawerLayout drawerLayout, final View bookmarksPanel )
    {
        this.drawerLayout = drawerLayout;
        this.bookmarksPanel = bookmarksPanel;

        this.actionMenu = actionMenu;


        // Включить специальные кнопки в меню

        //создать меню Меню_Кнопок
        Menu menu = actionMenu.getMenu();

        //создаем Управляющего_упаковки

        // управляющий_упаковки  = контекст.Получить_Управляющего_упаковки()
        PackageManager pm = context.getPackageManager();


    }

    //открытый метод типа bool onMenuItemClick(final MenuItem item) Действия_При_Нажатии_Кнопок_Меню(аргумент Кнопка_меню)
    @Override
    public boolean onMenuItemClick(final MenuItem item)
    {
        switch(item.getItemId())
        {
            //кнопка Загрузка_Веб_Ресурса
            case R.id.action_web:

                //для_контекста.Запустить_Активность(в Активности создать Интент связывающий  строку в виде строковой константы
                //ACTION_VIEW с  новый объектом Uri из правильно сформированной String,который используется для указания ContentProvidera,
                // к которому мыхотим получить доступ по ссылке

                //объект Uri используется для указания ContentProvidera,к которому мыхотим получить доступ по ссылке .
                //Это взаимнооднозначное сопоставление ресурса или данных.
                //метод Uri.parse(webView.getUrl()) создает новый объект Uri из правильно сформированной String

                // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
                // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.

                //Вы можете представить свою строку в виде строковой константы и сообщить всем потенциальным пользователям вашего браузера,
                // как они могут запустить его у себя. Но в Android уже есть такая готовая константа ACTION_VIEW, которая по справке
                // документации представляет собой следующее:

                //public static final java.lang.String ACTION_VIEW = "android.intent.action.VIEW";

                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl())));
                return true;

            // кнопка Обновить

            case R.id.action_refresh:

                //Перезагрузка() Веб_Вьювера

                // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
                // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.

                webView.reload();

                return true;

            // кнопка Домой
            case R.id.action_home:

                //Инструкция_домашней_страницы()

                homepageTutorial();

                //в Веб_вьювер загружаем Url(sp.getString("домашней страницы", с адресом "https://......../"))

                // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
                // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.

                webView.loadUrl(shared_preferences.getString("homepage", "https://cyrillitsa.ru/"));

                return true;



            //кнопка Сделать_Текущую_Страницу_Домашней_Страницей

            case R.id.action_set_as_home:

                //панель_раздачи.Делает().Показывает()
                Snackbar.make(appWindow, context.getString(R.string.homePageSet), Snackbar.LENGTH_LONG).show();

                //Редактор.Настроек=sp.Редактирует()
                SharedPreferences.Editor editor = shared_preferences.edit();
                //Редактор.Получает_строку("домашней_страницы", Веб_вьювер.Получает_Url()
                editor.putString("homepage", webView.getUrl());

                // Завершение диалога.Редактора
                // Метод фиксации commit() завершает все изменения в БД, проделанные SQL-выражением, и снимает также все блокировки,
// установленные транзакцией.
                editor.commit();
                return true;

                //кнопка Закладка

            case R.id.action_bookmarks:

                // новый способ навигации при помощи новой разметки android.support.v4.widget.DrawerLayout,

                //
                //За несколько лет использование нового компонента упростилось с появлением Material Design и шаблона Navigation Drawer
                // Activity.
                //
                //По английски drawer обозначает выдвижной ящик и данная разметка ведёт себя схожим образом. Пользователь может
                // лёгким движением руки с края экрана выдвинуть панель навигации для быстрого переключения
                // на другое содержимое, например, фрагмент с новым рисунком.

                drawerLayout.openDrawer(bookmarksPanel);
                return true;



        }

        return false;
    }

}
