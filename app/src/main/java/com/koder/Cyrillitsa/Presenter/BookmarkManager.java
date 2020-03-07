package com.koder.Cyrillitsa.Presenter;

/**
 * Created by Viktor Galkin/koder/on 01.02.2020
 */

// строки класса   начинаются со слова “import”. Это инструкции, которые дают указания включить другие пакеты в нашем проекте.
// Этот инструмент даёт нам возможность пользоваться тем кодом,который уже за нас написан другими программистами

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.koder.Cyrillitsa.R;

//открытый класс BookmarkManager(Управляющий_Закладками)

//класс  BookmarkManager состоит из:

//констант:

//глобальную статическую строковую переменную LOG_TAG = "Cyrillitsa"

//списков

//<Строковый>список закладок_Url private List<String> bookmarkUrls;

//<Строковый>список закладок_постоянных_Urls private List<String> bookmarkTimelessUrls;

//<Строковый>список закладок_заголовков  private List<String> bookmarkTitles;

//переменных

//контекст Context context;

//веб_вьювер  WebView webView-компонент, который отображает внутри приложения веб-страницу.


//меню_навигации NavigationView navigationView;

//настройки SharedPreferences sp;

//конструктора класса  BookmarkManager

// методов :
//открытый метод типа void Инициализация_Закладок(с помощью выдвижного меню NavigationView)
// public void initalizeBookmarks(NavigationView navigationView)


//открытый метод типа void Добавить_Закладку(по Заголовку веб_ресурса ,url веб_ресурса )
//public void addBookmark(String title,String url) {



//открытый метод типа void Переместить_Закладку(Заголовок)
// public void removeBookmark(String title) {


//открытый метод типа  String Получить_Url_Страницы_Ресурса(по Заголовку)

//public String getUrl(String title)
// {return bookmarkUrls.get(bookmarkTitles.indexOf(title))}

//открытый метод Сделать_Из_Массива_JSONArray ja_Список_List<JSONObject>
//public static List<JSONObject> asList(final JSONArray ja) {


public class BookmarkManager {

    //<Строковый>список закладок_Urls
    private List<String> bookmarkUrls;

    //<Строковый>список закладок_постоянных_Urls
    private List<String> bookmarkTimelessUrls;

    //<Строковый>список закладок_заголовков
    private List<String> bookmarkTitles;

    //контекст
    Context context;

    //веб_вьювер
    // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
    // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.

    WebView webView;

    //вьювер_навигации
    NavigationView navigation_View;

    //настройки
    SharedPreferences shared_preferences;


    //конструктор класса Управляющий_Закладок

    public BookmarkManager(Context context, WebView webView)
    {
        //контекст
        this.context = context;

        //веб_вьювер

        // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
        // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.

        this.webView = webView;

        //значение настроек для объекта Управляющий_настройками.Берется_из_Настроек_по_Умолчанию

        shared_preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }



    //открытый метод типа void Инициализация_Закладок(с помощью выдвижного меню NavigationView)


    public void initalizeBookmarks(NavigationView navigation_View)
    {
        //Значение_Вьювера_Навигации.для текущей активности (this)
        this.navigation_View = navigation_View;

        //Создание списка_Закладка_Url_ов(с содержимым_загружеными_веб_страницами)



        bookmarkUrls = new ArrayList<>();

        // Создание списка_Закладка_внеочередных_загруженных_Url_ов(с содержимым_загружеными_веб_страницами)
        bookmarkTimelessUrls = new ArrayList<>();

        //  Создание списка__Закладка_Заголовков_Веб-страниц(с содержимым_загружеными_заголовками_веб_страницами )
        bookmarkTitles = new ArrayList<>();

        //Вьювер_навигации.Получает_меню()
        final Menu menu = navigation_View.getMenu();

        //очистка меню
        menu.clear();

        //результат = "закладка"
        String result = shared_preferences.getString("bookmarks", "[]");
        try {
            JSONArray bookmarksArray = new JSONArray(result);
            for (int i = 0; i < bookmarksArray.length(); i++) {
                JSONObject bookmark = bookmarksArray.getJSONObject(i);
                menu.add(bookmark.getString("title")).setIcon(R.drawable.star);



                //  В_Список_Закладка_заголовков_веб_страниц.добавить(закладку )


                bookmarkTitles.add(bookmark.getString("title"));

                //  В_Список_Закладка_Url_ов_веб_страниц.добавить(закладку )
                bookmarkUrls.add(bookmark.getString("url"));

                //внеочередной_загруженный_Url = закладка.Получает("url"веб_страницы)
                String timeless = bookmark.getString("url");


                if (timeless.contains("&t=")) {
                    timeless = timeless.substring(0, timeless.indexOf("&t="));
                }

                //     В_Список_Закладка_внеочередных_загруженных_Url_ов.Добавить(внеочередные)
                bookmarkTimelessUrls.add(timeless);
            }
        }
        //ловим (исключение JSON)
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        try
        {
            //загрузить из Интернета  веб-страницу
            // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
            // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.
            String url = webView.getUrl();

            if (url.contains("&t=")) {
                url = url.substring(0, url.indexOf("&t="));
            }

            if (url.contains("/results")) {
                url = url.replace("+", "%20");
            }
//Если (Список_Закладка_Url_ов.с содержимым_загружеными_веб_страницами ИЛИ Список_Закладка_Url_ов.с содержимым_загружеными_заголовками_веб_страницами ИЛИ
//     Список_Закладка_наиболее_быстро_загруженных_Url_ов.с содержимым_загружеными_веб_страницами

            // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
            // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.
            if (bookmarkUrls.contains(webView.getUrl()) || bookmarkTitles.contains(webView.getTitle().replace("'", "\\'")) || bookmarkTimelessUrls.contains(url))
            {
                //то Закрыть веб-страницу
                menu.add(context.getString(R.string.removePage)).setIcon(R.drawable.close);
            }
            //иначе
            else
            {

                //добавить веб-страницу
                menu.add(context.getString(R.string.addPage)).setIcon(R.drawable.plus);
            }
        }
        //обработка исключения
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //открытый метод типа void Добавить_Закладку(по Заголовку веб_ресурса ,url веб_ресурса )

    public void addBookmark(String title, String url) {
        String result = shared_preferences.getString("bookmarks", "[]");
        try {
            JSONArray bookmarksArray = new JSONArray(result);
            bookmarksArray.put(new JSONObject("{'title':'" + title.replace("'", "\\'") + "','url':'" + url + "'}"));
            SharedPreferences.Editor editor = shared_preferences.edit();
            editor.putString("bookmarks", bookmarksArray.toString());
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Инициализация_закладок(вьювером_Навигации)
        initalizeBookmarks(navigation_View);
    }

    //открытый метод типа void Переместить_Закладку(Заголовок)

    public void removeBookmark(String title) {
        String result = shared_preferences.getString("bookmarks", "[]");
        try {
            JSONArray bookmarksArray = new JSONArray(result);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                bookmarksArray.remove(bookmarkTitles.indexOf(title));
            } else {
                final List<JSONObject> objs = asList(bookmarksArray);
                objs.remove(bookmarkTitles.indexOf(title));
                final JSONArray out = new JSONArray();
                for (final JSONObject obj : objs) {
                    out.put(obj);
                }
                bookmarksArray = out;
            }
            SharedPreferences.Editor editor = shared_preferences.edit();
            editor.putString("bookmarks", bookmarksArray.toString());
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initalizeBookmarks(navigation_View);
    }

    //открытый метод типа  String Получить_Url_Страницы_Ресурса(по Заголовку)


    public String getUrl(String title) {
        return bookmarkUrls.get(bookmarkTitles.indexOf(title));
    }

    public static List<JSONObject> asList(final JSONArray ja) {
        final int len = ja.length();
        final ArrayList<JSONObject> result = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            final JSONObject obj = ja.optJSONObject(i);
            if (obj != null) {
                result.add(obj);
            }
        }
        return result;
    }

}
