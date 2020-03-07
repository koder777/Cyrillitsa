package com.koder.Cyrillitsa;

/**
 * Created by Viktor Galkin/koder/on 01.02.2020
 */

// строки класса   начинаются со слова “import”. Это инструкции, которые дают указания включить другие пакеты в нашем проекте.
// Этот инструмент даёт нам возможность пользоваться тем кодом,который уже за нас написан другими программистами

//AppCompatActivity – это код (класс), написанный разработчиками Android, который позволяет поместить наш Java код в Android.
// класс AppCompatActivity,хранит в себе методы, позволяющие нам взаимодействовать с жизненным циклом Android приложения

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.ActionMenuView;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;

//Основой всех гибридных приложений является элемент WebView. Данный элемент отображает веб-страницы с помощью WebKit-движка.
// Это значит,что вы можете создать обычный HTML-файл, содержащий <style> или <link> теги для подключения CSS,  и <script>
// теги для добавления JS-кода, а затем передать его в элемент WebView. В результате получится практически такое же изображение,
// как если бы вы открыли данную страницу в браузере .

// самый простой способ создать гибридное приложение для андроид — просто открыть существующий веб-сайт в элементе WebView.
// Нам просто нужно его создать, развернуть на весь экран, и передать ему URL веб-сайта


import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.koder.Cyrillitsa.Model.WebSiteChromeClient;
import com.koder.Cyrillitsa.Model.WebSiteViewClient;

import com.koder.Cyrillitsa.Presenter.ExhibitionManager;
import com.koder.Cyrillitsa.Presenter.BookmarkManager;

import com.koder.Cyrillitsa.Presenter.MenuListener;

import com.koder.Cyrillitsa.Presenter.BookmarkSelectedListener;

import com.koder.Cyrillitsa.Presenter.MediaButtonIntentReceiver;

//открытый класс MainActivity(Главная_Активность) расширяющий стандартный суперкласс AppCompatActivity

//класс MainActivity состоит из:

//констант:

//глобальной статической строковую переменную LOG_TAG = "Cyrillitsa"

//переменных:

//Веб_вьювер WebView webView;

//время String time;

//окно_приложения View appWindow;

//панель_индикатора_загрузки_ресурса ProgressBar progress;

//заданный_контейнер_для_вьювера FrameLayout customViewContainer;

//разметка Выдвижной_Ящик для нового способа навигации DrawerLayout drawerLayout;

//Меню_навигации NavigationView navigationView;

//настройки SharedPreferences sp;


// Слушатель Для вьювера панели_раздачи с сообщением об ошибке  для загрузки  домашней страницы "homepage",
//
// по адресу "https://cyrillitsa.ru/"

//Для_вьювера.Создается_Слушатель(View.OnClickListener clickListener = new View.OnClickListener()) ,которому присваивается
// Веб_Вьювер,отображающий  внутри приложения веб-страницу.Который_Производит загрузку  домашней страницы("homepage",
// по адресу "https://cyrillitsa.ru/"))
//@Override
//public void onClick(View v) {webView.loadUrl(sp.getString("homepage", "https://cyrillitsa.ru/"));}};

//объект контекст_приложения класса Context mApplicationContext;

//объект  Создатель_Видеовоспроизведения класса  ExhibitionManager

//объект управляющий_закладками класса BookmarkManager

//объект создатель_меню класса MenuListener



// методов :

// открытый метод типа  void  Переключатель_Видео_на_Воспроизведение_или_Паузу()

//открытый метод типа void



//Дословно:создай мне public class  с названием MainActivity, основанием которого (extends) является AppCompatActivity.
// MainActivity – это имя, которые мы выбрали, когда создавали проект. AppCompatActivity – это код (класс), написанный
// разработчиками Android, который позволяет поместить наш Java код в Android.

// класс AppCompatActivity,хранит в себе методы, позволяющие нам взаимодействовать с жизненным циклом Android приложения



public class MainActivity extends AppCompatActivity {

    // принято объявлять глобальную статическую строковую переменную TAG в начале кода:

    //private static final String LOG_TAG = "Cyrillitsa";

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

    private static final String LOG_TAG = "Cyrillitsa";

    //Веб_вьювер
    private static WebView webView;

    //время
    String time;

    //окно_приложения
    private View appWindow;

    //панель_индикатора_загрузки_ресурса
    private ProgressBar progress;

    //заданный_контейнер_для_вьювера
    private FrameLayout customViewContainer;

    //новый способ навигации при помощи новой разметки android.support.v4.widget.DrawerLayout,
//
//За несколько лет использование нового компонента упростилось с появлением Material Design и шаблона
// Navigation Drawer Activity.
//
//По английски drawer обозначает выдвижной ящик и данная разметка ведёт себя схожим образом. Пользователь может лёгким движением
// руки с края экрана выдвинуть панель навигации для быстрого переключения на другое содержимое, например, фрагмент с новым
// рисунком.
//
//DrawerLayout входит в состав библиотеки Support Library v4 (release 13), который вышел в мае 2013 года и используется по
// умолчанию в новых проектах, создаваемых в Android Studio
    private DrawerLayout drawerLayout;

    //Вьывер_навигации

    private NavigationView navigation_View;

    //настройки
    private SharedPreferences shared_preferences;

    //----------------

    //Создается  Слушатель Для панели_раздачи с сообщением об ошибке  для загрузки  домашней страницы "homepage",


    // в котором Для_вьювера.Создается_Слушатель ,которому присваивается значение URLa Загруженного_ресурса_интернета
    // .Веб_Вьювера  с адресом "https://cyrillitsa.ru/"


    //Класс View представляет собой основной строительный блок для компонентов пользовательского интерфейса.
    // Вид занимает прямоугольную область на экране и отвечает за рисование и обработку событий.
    // Вид – это базовый класс для виджетов, которые используются для создания компонентов интерактивного интерфейса (кнопки, текстовые поля и т. Д.).


    //View – это основной строительный блок UI (User Interface) в android. Вид представляет собой небольшой прямоугольный блок, который реагирует на
    // пользовательские входы. Например: EditText , Button , CheckBox


    View.OnClickListener clickListener = new View.OnClickListener() {


    @Override
    public void onClick(View v) {

        //Веб_вьювер.Производит загрузку  домашней страницы("homepage", по адресу "https://cyrillitsa.ru/"))

        // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
        // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.
        webView.loadUrl(shared_preferences.getString("homepage", "https://cyrillitsa.ru/"));
    }
};

//объект  Создатель_Видеовоспроизведения класса MediaCreator

        ExhibitionManager exhibitionManager;
        BookmarkManager bookmarkManager;

                //объект создатель_меню класса MenuCreatorListener
        MenuListener menuHelper;


//объект контекст_приложения класса Context

    private Context mApplicationContext;


    // открытый метод типа  void  Переключатель_Видео_на_Воспроизведение_или_Паузу()

    public static void toggleVideo()
    {

        //в коде  провести проверку на версию и выполнять код в зависимости от условия:

        //Проверка:Если версия SDK >= KITKAT(версия Android 4.4 под кодовым названием KitKat)
        //SDK -набор библиотек разработчика

        //

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            //в_Веб_Вьювере.вызвать метод EvaluateJavascript(), которая будет принимать строку для выполнения и возвращать строку
            // с результатом возможности или невозможности воспроизведения видео
            //
            // Оценить_Javascript_на предмет Воспроизведения_Видео или Паузы_Видео
            //(функция,возвращающая документ с _элементами_с_названием_видео.который тормозится

            // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
            // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.


            webView.evaluateJavascript("(function() { return document.getElementsByTagName('video')[0].paused; })();",
                    new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            if (value.equals("true")) {
                                //вызов метода  Воспроизведение_Видео()
                                playVideo();
                            }
                            else
                            {
                                //вызов метода  Пауза_Видео()
                                pauseVideo();
                            }
                        }
                    });
        }
        //иначе Видео ставим на паузу
        else {
            //вызов метода  Пауза_Видео()
            pauseVideo();
        }
    }

    //---------------------------------------------------------------------------------------------

    // открытый метод типа  void  pauseVideo() загружает веб-страницу  ресурса с видео на паузе с помощью webView

    public static void pauseVideo()
    {
        //Веб+вьювер.Загружает веб-страницу  ресурса с видео на паузе

        webView.loadUrl("javascript:document.getElementsByTagName('video')[0].pause();");
    }



    //открытый метод типа  void  playVideo() загружает веб-страницу  ресурса с воспроизводящимся  видео с помощью webView

    public static void playVideo()
    {
        //Веб+вьювер.Загружает веб-страницу  ресурса с воспроизводящимся  видео

        webView.loadUrl("javascript:document.getElementsByTagName('video')[0].play();");
    }


    //

    // чтобы использовать компонет WebView , нужно в Главной_Активности  инициализировать экземпляр класса WebView.
//--------------------------------------------------------------------------

//   МЕТОД  onCreate()  АКТИВНОСТИ MainActivity ДЛЯ ЕЕ ИНИЦИАЛИЗАЦИИ

    //---------------------------------------------------------------------

    // метод onCreate() вызывается, когда приложение создаёт и отображает разметку активности.
    // Метод помечен как protected и сопровождается аннотацией @Override (переопределён из базового класса). Аннотация
    // может пригодиться вам. Если вы сделаете опечатку в имени метода, токомпилятор сможет предупредить вас, сообщив об отсутствии
    // такого метода у родительского класса Activity.

    //Класс bundle служит для передачи данных простых типов между Активити.

    // Передача осуществляется через различные механизмы

    // (1. Просто ключ-значение
    // 2. Интерфейс Parcelable
    // 3. механизм Serizilable
    // 4. библиотека GSON От гугла.)

    //Слово @Override указывает нам то, что метод, который следует далее, переопределён.

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Получить значение контекста приложения

        //в  инициализации контекста  я прописал  mApplicationContext = getApplicationContext()
        // , то инициализировать этот объект можно откуда угодно,
        // главное чтобы был доступ к контексту, можно даже в активити, передав саму активити в качестве параметра.
        //// метод GetApplicationContext () возвращает контекст для всех действий, выполняемых в приложении

        mApplicationContext = getApplicationContext();

        //Context – это объект, который предоставляет доступ к базовым функциям приложения: доступ к ресурсам, к файловой системе,
        // вызов активности и т.д.
        //
        // Activity является подклассом Context, поэтому в коде мы можем использовать её как ИмяАктивности.this,
        // или укороченную запись this///



        // Получить_окно_приложения().для которого Устанавить_флаги_вывокоскоростного_ускорения  FLAG_HARDWARE_ACCELERATED

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        // чтобы использовать компонет WebView , нужно в Главной_Активности  инициализировать экземпляр класса WebView.

//   Строка super.onCreate(savedInstanceState); – это конструктор cупер класса AppCompatActivity, выполняющий необходимые
// операции для работы Главной_ активности.
// ключевое слова “super” ссылается на код в исходном методе onCreate, который находится там, несмотря на то, что мы его не видим.
// Код гласит: несмотря на то, что я переопределяю тебя, я хочу, чтобы ты всё сделал так, как обычно.

        super.onCreate(savedInstanceState);

//Здесь мы указываем операционной системе Android установить основной вид (наш пользовательский экран), которым в нашем случае
// является меню главной  активности , созданное ранее.Чтобы ОС Android это поняла, мы в скобках указали, на что ей ссылаться
// (R.layout.activity_main).

        setContentView(R.layout.activity_main);

        // чтобы использовать Веб_вьювер , нужно в Главной_Активности  инициализировать экземпляр класса WebView методом
//
// onCreate()



        // Метод findViews() выбирает и сохраняет WebView

        // WebView представляет собой компонент, который отображает внутри приложения веб-страницу.

        webView = (WebView) findViewById(R.id.webView);

        // Метод findViews() выбирает и сохраняет вьювер для  окна_приложения



        appWindow = findViewById(R.id.appWindow);

        // Метод findViews() выбирает и сохраняет вьювер для   панели_индикации


        progress = (ProgressBar) findViewById(R.id.progress);

        // Метод findViews() выбирает и сохраняет вьювер для   заданного_контейнера_вьювера
        /* находим customViewContainer элемент по его id */
        customViewContainer = (FrameLayout) findViewById(R.id.customViewContainer);

        //получить настройки по умолчанию для текущей активности(this)
        shared_preferences  = PreferenceManager.getDefaultSharedPreferences(this);

        // Метод findViews() выбирает и сохраняет вьювер для экземпляра  DrawerLayout
        /* находим drawerLayout элемент по его id */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Метод findViews() выбирает и сохраняет вьювер для   экземпляра панели навигации NavigationView
        /* находим navigationView  элемент по его id */
        navigation_View = (NavigationView) findViewById(R.id.bookmarks_panel);

        // Установим приемник медиа_кнопок

        //(Аудио_менеджер)Получает_системный_сервис(с флагом АУДИО_СЕРВИС).для которого Установим приемник медиа_кнопок(
        // создаем Название_Компонента(Получить_Название_Компонента,Интент_приемника медиа_кнопок.для_класса.Возвратить_имя())

        ((AudioManager) getSystemService(AUDIO_SERVICE)).registerMediaButtonEventReceiver(
                new ComponentName(getPackageName(), MediaButtonIntentReceiver.class.getName()));

        //В Android есть два важных класса: WebViewClient и WebChromeClient, которые взаимодействуют с WebView.

        // WebViewClient требуется для того, чтобы контролировать процесс загрузки страницы,
        // а WebChromeClient — чтобы взаимодействовать с этой страницей после того, как она успешно загружена.
        //
        // До завершения загрузки страницы работает WebViewClient, а после — WebChromeClient.
        // для того чтобы взаимодействовать с WebView, нам нужно создать собственные инстансы этих классов и передать их в WebView.
        // Далее WebView при определенных условиях вызывает разные методы,которые мы переопределили в наших инстансах, и так мы узнаем
        // о событиях в системе.

        // Установить WebChromeClient.для Веб_Вьювера

        // класс WebChromeClient(Веб_Клиент_Браузера_Хром)— отвечает за все, что связано с UI браузера.
        // С его помощью можно управлять историей посещений, создавать новые окна, работать с сообщениями и иконками

        webView.setWebChromeClient(new WebSiteChromeClient(webView, progress, customViewContainer,drawerLayout,getWindow().getDecorView()));

        //класс WebViewClient(Клиент_Вьювера_Ю_Туб). Данный класс отвечает за все, что связано с отрисовкой страницы. У него есть методы
        // для обработки ошибок, получения http-запросов, загрузки страницы и др. Все эти методы можно перегрузить.
        // Это значит, что можно добавить какую-то логику на каждое из этих событий

        // Установить WebViewClient.для Веб_Вьювера

        //Затем в методе onCreate() определим экземпляр MyWebViewClient. Он может находиться в любом месте после инициализации
// объекта WebView:
//
//
//webView.setWebViewClient(new MyWebViewClient());

//Теперь в нашем приложении создан WebViewClient, который позволяет загружать любой указанный URL, выбранный в WebView,
// в сам контейнер WebView, а не запускать браузер.
//
// За данную функциональность отвечает метод shouldOverrideUrlLoading(WebView, String) класса WebSiteViewClient, в котором мы
// указываем текущий WebView  и нужный URL. Возвращаемое значение true говорит о том, что мы не нуждаемся в запуске
// стороннего браузера,а самостоятельно загрузим веб-ресурс  по ссылке


        // WebView представляет собой компонент, который отображает внутри приложения веб-страницу.
        webView.setWebViewClient(new WebSiteViewClient(this, appWindow, clickListener,findViewById(R.id.statusBarSpace), findViewById(R.id.menu_main)));

        // Установить  WebView
        setUpWebview();

        // Инициализация меню навигации navigationView

        //Создание Управляющего_Закладками(для текущей_активности (this) и Веб_Вьювера webView)
        bookmarkManager = new BookmarkManager(this, webView);

        // Инициализация Панели_закладок(Управляющего_Закладками) значением Вьювера_навигации(navigationView)
        bookmarkManager.initalizeBookmarks(navigation_View);

        //Реализация выдвижного меню NavigationView при помощи DrawerLayout с
        //Использованием произвольной разметки

        // Инициализация разметки Выдвижной_ящик

        //Установить Слушатель.для_Разметки_Выдвижной_ящик

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener()
        {
            //открытый метод  типа void Рисовальщик_слайдов(рисовальщик_вьювера,установить_слайд)

            //Слово @Override указывает нам то, что метод, который следует далее, переопределён.
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                // Инициализация Панели_закладок(Управляющего_Закладками) значением Вьювера_навигации(navigationView)
                bookmarkManager.initalizeBookmarks(navigation_View);
            }

            //открытый метод  типа void  Рисовальщик_открыт(рисовальщик_вьювера)

            //Слово @Override указывает нам то, что метод, который следует далее, переопределён.
            @Override
            public void onDrawerOpened(View drawerView)
            {
                // Инициализация Панели_закладок(Управляющего_Закладками) значением Вьювера_навигации(navigationView)
                bookmarkManager.initalizeBookmarks(navigation_View);
            }

            //открытый метод  типа void Рисовальщик_закрыт(рисовальщик_вьювера)

            //Слово @Override указывает нам то, что метод, который следует далее, переопределён.
            @Override
            public void onDrawerClosed(View drawerView)
            {
                /* тело метода пусто */
            }



            //открытый метод  типа void Рисовальщик_изменен(новое_сосотояние_рисовальщика)
            @Override
            public void onDrawerStateChanged(int newState)
            {
                /* тело метода пусто */
            }
        });



        //для_Вьювера_навигации.Установить_Слушателя_Вкладки_Меню_Навигации(создать Слушателя_Закладок(для текущей активности this,
        //веб_вьювера  webView,управляющего_Закладками bookmarkManager, drawerLayout)

        navigation_View.setNavigationItemSelectedListener(new BookmarkSelectedListener(this, webView, bookmarkManager, drawerLayout));

        // Создание объекта  Создатель_Тора с помощью контекста приложения mApplicationContext и webView

        //Context – это объект, который предоставляет доступ к базовым функциям приложения: доступ к ресурсам, к файловой системе,
// вызов активности.Контекст web приложения - это по сути само web приложение в  текущий момент времени




        // Создание объекта  Создатель_Видеовоспроизведения

        //Context – это объект, который предоставляет доступ к базовым функциям приложения: доступ к ресурсам, к файловой системе,
// вызов активности и т.д.

        exhibitionManager = new ExhibitionManager(mApplicationContext, webView);

        // Связываем Кнопочное_Меню с ресурсом кнопочного меню menu_main.xml

        ActionMenuView actionMenu = (ActionMenuView) findViewById(R.id.menu_main);

        //Создание Слушателя_Меню((для текущей активности this,
        //  веб_вьювера  webView,Управляющего_Тором,Управляющего_медиа_воспроизведением,окно_приложения)


        menuHelper = new MenuListener(this, webView,  exhibitionManager, appWindow);
//Метод getMenuInflater получает объект MenuInflater и вызываем его метод inflate().
// Этот метод в качестве первого параметра принимает ресурс, представляющий наше декларативное описание меню в xml,
// и наполняет им объект menu, переданный в качестве второго параметра.

        getMenuInflater().inflate(R.menu.menu_main,actionMenu.getMenu());

        //Установить Меню_Кнопок из ресурса R.id.bookmarks_panel
        menuHelper.setUpMenu(actionMenu, drawerLayout, findViewById(R.id.bookmarks_panel));


        //меню кнопок получает иконку dots_vertical из drawable

        actionMenu.setOverflowIcon(getResources().getDrawable(R.drawable.dots_vertical));

        // Загрузка домашней странички  Cyrillitsa

        //если загрузка нашей странички из интента не произошла

        // Определение намерения, запустившего активность с помощью метода getIntent()
        //При старте активности с помощью неявного намерения необходимо определить действие, которое следует выполнить,
        // и данные для этого действия.
        // Метод getIntent() активности позволяет получить объект намерения
        if (!loadUrlFromIntent(getIntent()))
        {
            //то  загрузим нашу домашнюю страничку в Веб_Вьювер

            // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
            // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.

            webView.loadUrl(shared_preferences.getString("homepage", "https://cyrillitsa.ru/"));
        }
    }

    //-------------------------------------------------------------------------------------

    //открытый метод  типа void Алгоритм_Когда_Поставить_На_Паузу_Mедиа_Ресурс_Интернета

    // Слово @Override указывает нам то, что метод, который следует далее, переопределён.


    @Override
    public void onPause() {
        super.onPause();

        //если  exhibitionManager(cоздатель_Медиа_Воспроизведения) получает настройки ,допускаюшие воспроизведение медиа
        //    //ресурса интернета полученные из метода типа bool Допустимо_ли_Медиа_Воспроизведение() /класс MediaCreator/



        if(exhibitionManager.isExhibitionEnabled()) {

        }


        //то cоздатель_Медиа_Воспроизведения.Производит Воспроизведение_Медиа
        // путем вызова метода типа void Фоновое_Воспроизведение_Медиа() /класс MediaCreator/

        else {
            pauseVideo();

        }



    }

//открытый метод  типа void Алгоритм_Когда_Подвести_итоги_для_Mедиа_Ресурса_Интернета()

    @Override

    public void onResume()
    {

        super.onResume();

        //Скрыть_Уведомление_о_Воспроизведении_Видео().для Создателя_Воспроизведения_Видео
        exhibitionManager.hideExhibitionNotification();
    }




    //открытый метод  типа void Алгоритм_Когда_Уничтожить_Mедиа_Ресурс_Интернета()

    @Override
    public void onDestroy() {
        super.onDestroy();
        exhibitionManager.hideExhibitionNotification();
        ((AudioManager) getSystemService(AUDIO_SERVICE)).unregisterMediaButtonEventReceiver(
                new ComponentName(getPackageName(), MediaButtonIntentReceiver.class.getName()));
    }

    //открытый метод  типа void Создать_Интент_и_Загрузить_из_Него_Страницу_Ресурса_Интернета(арг.интент)

    //Слово @Override указывает нам то, что метод, который следует далее, переопределён.
    @Override
    protected void onNewIntent(final Intent intent)
    {
        super.onNewIntent(intent);

        //вызов метода Загрузить_Страницу_из_Интента(арг.интент)
        loadUrlFromIntent(intent);
    }




    //закрытый метод типа bool  Загрузить_Страничку__Ресурса_Интернета_Из_Интента(арг интент)

    private boolean loadUrlFromIntent(final Intent intent) {

        //метод equals() класса Object используется для сравнения объектов
//метод equals() сравнивает ссылки на адреса в памяти ,которые хранят переменные и возвращает
//true только в том случае,если адреса совпадают(т.е.переменные ссылаются на один и тот же объект)

        //Действие намерения, action

        //Действие action объекта Intent определяет, что нужно выполнить, например просмотр фото (view) или
        // выбор фото (pick).
        // В значительной степени действие определяет, каким образом описана остальная часть намерения Intent,
        // в частности, что именно содержится в разделе данных. Действие для объекта Intent можно указать методом
        // setAction() или определить в конструкторе Intent.
        //

        // ......формируется  неявное намерения для открытия определенной страницы сайта :

        // создается намерение с действием ACTION_VIEW, означающим просмотр чего-либо.
        // При вызове данного намерения текущая активность приостанавливает своё действие и переходит в
        //
        // фоновый режим.
        // При нажатии пользователем кнопки Back исходная активность восстанавливется.

        if (Intent.ACTION_VIEW.equals(intent.getAction()) && intent.getData() != null)
        {
            // значение url страницы из интента
            final String url = intent.getData().toString();

            //метод equals() класса Object используется для сравнения объектов
//метод equals() сравнивает ссылки на адреса в памяти ,которые хранят переменные и возвращает
//true только в том случае,если адреса совпадают(т.е.переменные ссылаются на один и тот же объект)

            if (url != null && !url.equals(webView.getUrl()))
            {
                //то загрузка странички в Веб_Вьювер

                // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
                // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.
                webView.loadUrl(url);
            }

            return true;
        } else {
            return false;
        }
    }


    //открытый метод типа void Установить_НастройкиВеб_Вьювер()

    public void setUpWebview()
    {

        // Настройки

        //Для начала начнем с основы — WebView с запущенной HTML страничкой на весь экран.


        WebSettings webSettings = webView.getSettings();

        // включили Установку_JavaScript
        webSettings.setJavaScriptEnabled(true);

        //отключили Aвтоматическое открытие окна приложения при_установке  JavaScript
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);

        //отключили  Установить_Допуск_для_Файлов_Access
        webSettings.setAllowFileAccess(false);

        // включили  Установку_Баз_Данных
        webSettings.setDatabaseEnabled(true);

        //Определяем Путь Кэша_Приложения через Контекст_Приложения

        String cachePath = mApplicationContext.getDir("cache", Context.MODE_PRIVATE).getPath();

        //Установить_Пути_Кэша_Приложения
        webSettings.setAppCachePath(cachePath);

        // включили  Установку_Допуск_для_Файлов_Access
        webSettings.setAllowFileAccess(true);

        // включили Установку_Кэша_Приложения
        webSettings.setAppCacheEnabled(true);

        // включили Установку_Хранения_Dom
        webSettings.setDomStorageEnabled(true);

        //Установили кэш Веб_настроек ПО_УМОЛЧАНИЮ
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        //отключили
        // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
        // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.

        webView.setHorizontalScrollBarEnabled(false);

        // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
        // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.

        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        //Установили цвет фона :БЕЛЫЙ

        // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
        // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.

        webView.setBackgroundColor(Color.WHITE);

        // включили Установку_Исчезновение_Меню_Прокрутки

        // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
        // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.

        webView.setScrollbarFadingEnabled(true);

        // включили Установку_доступных_ресурсов

        // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
        // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.

        webView.setNetworkAvailable(true);

    }



    //открытый метод типа void Нажать_Кнопку_для_Возврата_Назад()

    //Повторно запустите программу и убедитесь, что ссылки загружаются теперь в самом приложении. Но теперь возникла ещё одна
    // проблема. Мы не можем вернуться к предыдущей странице. Если мы нажмём на кнопку BACK (Назад) на своем устройстве, то
    // просто закроем свое приложение.
    // Для решения новой проблемы нам необходимо обрабатывать нажатие кнопки BACK. Добавляем новый метод:


    @Override
    public void onBackPressed()
    {
        //Мы должны проверить, что WebView поддерживает навигацию на предыдущую страницу.

        // WebView представляет собой компонент, который отображает внутри приложения веб-страницу. С его помощью можно
        // загрузить из Интернета любую веб-страницу или HTML-документ, или  превратить приложение в полноценный браузер.
        //
        // Если условие верно, тогда вызывается метод goBack(),который возвращает нас на предыдущую страницу на один шаг назад.
        //
        // Если таких страниц набралось несколько, то мы можем последовательно вернуться к самой первой странице. При этом метод
        // всегда будет возвращать значение true.
        //
        // Когда мы вернёмся на самую первую страницу, с которой начали путешествие по интернету, то вернётся значение false и
        // обработкой нажатия кнопки BACK займётся уже сама система, которая закроет экран приложения.
        //

        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            //С помощью finish () мы закрываем текущую Activity для возвращения обратно в MainActivity
            finish();
        }

    }
}


