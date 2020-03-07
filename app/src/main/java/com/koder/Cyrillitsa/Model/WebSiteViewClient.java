package com.koder.Cyrillitsa.Model;

//Запустим приложение. В нашем распоряжении появился простейший вьювер веб-страниц, но с одним недостатком. Если вы щёлкнете
// на любой ссылке, то у вас автоматически запустится браузер по умолчанию и новая страница отобразится уже там. Точнее так
// было раньше. На новых устройствах при запуске приложения сразу открывается браузер.
//
//Чтобы решить данную проблему и открывать ссылки в своей программе, нужно переопределить класс WebViewClient и позволить
// нашему приложению обрабатывать ссылки. Добавим в коде вложенный класс:

//@Override
//    public boolean shouldOverrideUrlLoading(WebView view, String url)
//    {
//        //Проверка: если url не ноль и одновременно  url не стартует с ("http")и одновременно
//        // если  форматы содержимого веб-сайта не ("accounts.google.") и одновременно не ("cyrillitsa.")
//        if (url != null && url.startsWith("http") && !url.contains("accounts.google.") && !url.contains("cyrillitsa.")) {
//            view.getContext().startActivity(
//
//                    //объект Uri используется для указания ContentProvidera,к которому мыхотим получить доступ по ссылке .
//                    //Это взаимнооднозначное сопоставление ресурса или данных.
//                    //метод Uri.parse(Url) создает новый объект Uri из правильно сформированной String
//
//                    new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//            //Возвращая true, мы сообщаем системе, что берём на себя управление навигацией, поэтому в реализации нужно также
//            // вызывать метод loadUrl(), в противном случае страница не будет загружена.
//
//
//            return true;
//        }


//Затем в методе onCreate() класса MainActivity определим экземпляр WebSiteViewClient. Он может находиться в любом месте после инициализации
// объекта WebView:
//
//webView.setWebViewClient(new WebSiteViewClient(this, appWindow, clickListener,findViewById(R.id.statusBarSpace), findViewById(R.id.menu_main)));
;

//Теперь в нашем приложении создан WebSiteViewClient, который позволяет загружать любой указанный URL, выбранный в WebView,
// в сам контейнер WebView, а не запускать браузер.
//
// За данную функциональность отвечает метод shouldOverrideUrlLoading(WebView, String), в котором мы указываем текущий WebView
// и нужный URL. Возвращаемое значение true говорит о том, что мы не нуждаемся в запуске стороннего браузера,
// а самостоятельно загрузим контент по ссылке



/**
 * Created by Viktor Galkin/koder/on 01.02.2020
 */
// строки класса   начинаются со слова “import”. Это инструкции, которые дают указания включить другие пакеты в нашем проекте.
// Этот инструмент даёт нам возможность пользоваться тем кодом,который уже за нас написан другими программистами

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.koder.Cyrillitsa.R;

//открытый класс WebSiteViewClient расширяющий класс  WebViewClient(Клиент_Веб_Вьювера)

//класс WebViewClient(Клиент_Вьювера_Ю_Туб). Данный класс отвечает за все, что связано с отрисовкой страницы. У него есть методы для
// обработки ошибок, получения http-запросов, загрузки страницы и др. Все эти методы можно перегрузить.
// Это значит, что можно добавить какую-то логику на каждое из этих событий

public class WebSiteViewClient extends WebViewClient {

    //переменные класса

    //контекст
    Context context;

    //Слушатель вьювера
    View.OnClickListener clickListener;

    //Вьювер Окно_Приложения
    View appWindow;

    //Вьювер  Поверхности_Панели_Состояния
    View statusBarSpace;

    //Вьювер нижней панели
    View bottomBar;

    //конструктор класса  WebSiteViewClient

    public WebSiteViewClient(Context context, View appWindow, View.OnClickListener clickListener, View statusBarSpace, View bottomBar) {
        this.context = context;
        this.clickListener = clickListener;
        this.appWindow = appWindow;
        this.statusBarSpace = statusBarSpace;
        this.bottomBar = bottomBar;
    }


    //Открывать ссылки в окне браузера (кроме диалогов для входа и URL-адресов сайта)


    //открытый метод типа void Возможный_Перехват_Загрузки_Страницы  (Веб_вьювер,url) позволяет перехватить  загрузку URL
    // страницы в наш браузер



    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        //Проверка: если url не ноль и одновременно  url не стартует с ("http")и одновременно
        // если  форматы содержимого веб-сайта не ("accounts.google.") и одновременно не ("cyrillitsa.")
        if (url != null && url.startsWith("http") && !url.contains("accounts.google.") && !url.contains("cyrillitsa."))
        {
            //Вьювер.Получает_контекст()
            view.getContext()
                    //запускается активность,в которой .....
                    .startActivity(

                            //Действие намерения, action

                            //Действие action объекта Intent определяет, что нужно выполнить, например просмотр фото (view) или
                            // выбор фото (pick).
                            // В значительной степени действие определяет, каким образом описана остальная часть намерения Intent,
                            // в частности, что именно содержится в разделе данных. Действие для объекта Intent можно указать методом
                            // setAction()
                            // или определить в конструкторе Intent.
                            //

                            // ......формируется  неявное намерения для открытия определенной страницы сайта :

                            // создается намерение с действием ACTION_VIEW, означающим просмотр чего-либо.
                            // При вызове данного намерения текущая активность приостанавливает своё действие и переходит в
                            //
                            // фоновый режим.
                            // При нажатии пользователем кнопки Back исходная активность восстанавливется.

                            //объект Uri используется для указания ContentProvidera,к которому мыхотим получить доступ по ссылке .
                            //Это взаимнооднозначное сопоставление ресурса или данных.
                            //метод Uri.parse(Url) создает новый объект Uri из правильно сформированной String

                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

            //Возвращаемое значение true говорит о том, что мы не нуждаемся в запуске стороннего браузера,
            //// а самостоятельно загрузим контент по ссылке

            //Возвращая true, мы сообщаем системе, что берём на себя управление навигацией, поэтому в реализации нужно также
            // вызывать метод loadUrl(), в противном случае страница не будет загружена.


            return true;
        }

        // Если же вернуть false, то необходимость использовать loadUrl() в реализации отпадёт, WebView автоматически загрузит
        // любую страницу без какой-либо обработки.

        return false;
    }

    //Затем в методе onCreate() класса MainActivity определим экземпляр WebSiteViewClient.
    // Он может находиться в любом месте после инициализации объекта WebView:
//
//webView.setWebViewClient(new WebSiteViewClient(this, appWindow, clickListener,findViewById(R.id.statusBarSpace),
// findViewById(R.id.menu_main)));


//Теперь в нашем приложении создан WebSiteViewClient, который позволяет загружать любой указанный URL, выбранный в WebView,
// в сам контейнер WebView, а не запускать браузер.
//
// За данную функциональность отвечает метод shouldOverrideUrlLoading(WebView, String), в котором мы указываем текущий WebView
// и нужный URL. Возвращаемое значение true говорит о том, что мы не нуждаемся в запуске стороннего браузера,
// а самостоятельно загрузим контент по ссылке

    //---------------------------------------------------------------------------------------------------

    //открытый метод типа void Загрузка_Ресурсов(по арг. Веб_Вьювер,url)

    public void onLoadResource(WebView view, String url)
    {
        //Проверка: если  форматы содержимого веб-сайта не (".jpg") и одновременно не(".ico")и одновременно не(".css")
        // и одновременно не (".js") и одновременно не ("complete/search")
        if (!url.contains(".jpg") && !url.contains(".ico") && !url.contains(".css") && !url.contains(".js") && !url.contains("complete/search")) {

            //для Веб_Вьювера.Произвести Загрузкау странички ( в которой  Удалить все фреймы (для предотвращения эксплойтов WebRTC)
// Эксплойты — это подвид вредоносных программ. Они содержат данные или исполняемый код, способный воспользоваться одной
// или несколькими уязвимостями в программном обеспечении на локальном или удаленном компьютере. Например, у вас есть
// браузер, и есть уязвимость в нем,  которая позволяет исполнить «произвольный код», то есть установить и запустить некую
// вредоносную.
            view.loadUrl("javascript:(function() {" +
                    "var iframes = document.getElementsByTagName('iframe');" +
                    "for(i=0;i<=iframes.length;i++){" +
                    "if(typeof iframes[0] != 'undefined')" +
                    "iframes[0].outerHTML = '';" +
                    "}})()");

            //Проверка :Если версия SDK >= KITKAT
            //SDK -набор библиотек разработчика
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
// то устанавливаем Настройки css
                String css = "*, *:focus { " +
                        " outline: none !important; -webkit-tap-highlight-color: rgba(255,255,255,0) !important; -webkit-tap-highlight-color: transparent !important; }" +
                        " ._mfd { padding-top: 2px !important; } ";

                //и для Веб_Вьювера.Производим  Загрузку странички (в которой  вызвать функцию javascript из андроидной активности.

                //IIFE (Immediately Invoked Function Expression) это JavaScript функция, которая выполняется сразу же после того, как она
                //была определена.
                //
                //(function () {
                //    statements
                //})();
                //
                //Это тип выражений, также известный как Self-Executing Anonymous Function, который состоит из двух основных частей.
                //Первая - это сама анонимная функция с лексическим скоупом, заключеннным внутри Оператора группировки ().
                ////  (function () {}) (); .... это регулярное выражение функции.
                //Благодаря этому переменные IIFE замыкаются в его пределах, и глобальная область видимости ими не засоряется.
                //
                //Вторая часть создает мгновенно выполняющееся функциональное выражение  () , благодаря которому JavaScript-движок
                //выполняет функцию напрямую.
                //
                //Функция становится мгновенно выполняющимся функциональным выражением. Переменные внутри функции не могут быть использованы
                // за пределами ее области видимости.
                //
                //
                //Переменная, которой присвоено IIFE, хранит в себе результат выполнения функции, но не саму функцию.

                // Удалите все iframes (чтобы предотвратить использование WebRTC)

                view.loadUrl("javascript:(function() {" +
                        "if(document.getElementById('webTubeStyle') == null){" +
                        "var parent = document.getElementsByTagName('head').item(0);" +
                        "var style = document.createElement('style');" +
                        "style.id = 'webTubeStyle';" +
                        "style.type = 'text/css';" +
                        "style.innerHTML = '" + css + "';" +
                        "parent.appendChild(style);" +
                        "}})()");
            }


            //далее адаптировать цвет панели состояния

            //Проверка:Если версия SDK >= KITKAT
            //SDK -набор библиотек разработчика

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
            {
                //для_Панели_текущего_состояния.Установить_Видимость(с помощью Вьювера.ВИДИМЫЙ)
                statusBarSpace.setVisibility(View.VISIBLE);

                // // для  Вьювера.вызвать метод  EvaluateJavascript, который будет принимать строку для выполнения и
                // возвращать строку с результатом.
                view.evaluateJavascript("(function() { if(document.getElementById('player').style.visibility == 'hidden' || document.getElementById('player').innerHTML == '') { return 'not_video'; } else { return 'video'; } })();",
                        new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(final String value) {
                                int colorId = value.contains("not_video") ? R.color.colorPrimary : R.color.colorWatch;

                                //Панели_текущего_состояния.Установить_Цвет_Фона()
                                statusBarSpace.setBackgroundColor(ContextCompat.getColor(context, colorId));

                                //Нижней_панели.Установить_Цвет_Фона()
                                bottomBar.setBackgroundColor(ContextCompat.getColor(context, colorId));
                            }
                        });
            }
        }
    }



    //если веб-страница не загрузилась , то
    //Обработка сообщений - ошибок процесса с помощью всплывающих сообщений  класса Snackbar,
    // который переводится как "Закусочная".

    //------------------------------------------------------------------------

    //открытый метод  типа  void  Полученная_Ошибка(веб-вьювер,код_ошибки,описание,некорректный_Url) обрабатывает
    // сообщения - ошибки процесса с помощью всплывающих сообщений  класса Snackbar

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
    {
        //если(описание.содержит (флаг"СЕТЬ_ИЗМЕНЕНА")

        if (description.contains("NETWORK_CHANGED"))
        {
            // то Загрузка домашней странички для Вьювера
            view.loadUrl(PreferenceManager.getDefaultSharedPreferences(view.getContext()).getString("homepage", "https://cyrillitsa.ru/"));
        }

        //иначе если (описание.содержит (флаг"ИМЯ_НЕ_РАЗРЕШЕНО")
        else if (description.contains("NAME_NOT_RESOLVED"))
        {
            // то Панель_всплывающих_сообщений(Snackbar)выдает какое либо сообщение об ошибке  в окне приложения appWindow
            // и производит восстановление путем вызова метода .setAction(context.getString(R.string.refresh), clickListener)
            // и показывает это все в окне приложения методом .show()

// когда выводится сообщение, его можно смахнуть с экрана слева направо, не дожидаясь,
// когда оно само исчезнет.

            Snackbar.make(appWindow, context.getString(R.string.errorNoInternet), Snackbar.LENGTH_INDEFINITE).setAction(context.getString(R.string.refresh), clickListener).show();
        }

        //иначе
        else
        {
            //  Панель_всплывающих_сообщений(Snackbar)выдает какое либо сообщение об ошибке  в окне приложения appWindow
            // и производит восстановление путем вызова метода .setAction(context.getString(R.string.refresh), clickListener)
            // и показывает это все в окне приложения методом .show()

            // когда выводится сообщение, его можно смахнуть с экрана слева направо, не дожидаясь,
// когда оно само исчезнет.

            Snackbar.make(appWindow, context.getString(R.string.error) + " " + description, Snackbar.LENGTH_INDEFINITE).
                    setAction(context.getString(R.string.refresh), clickListener).show();
        }
    }

}


