package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myapplication.utils.ConnectionDetector;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;

import java.net.URL;


import static com.example.myapplication.ui_zapros.jsonResponse;
import static com.example.myapplication.ui_zapros.login;
import static com.example.myapplication.ui_zapros.pass;
import static com.example.myapplication.ui_zapros.sys;
import static com.example.myapplication.ui_zapros.token;
import static com.example.myapplication.utils.NetworkUtils.generateURL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    // Переменные объектов MainActivity
    public static Button button_voiti1;
    public static EditText edit_log;
    public static EditText edit_pas;
    public static TextView text_internet;
    ImageView eye;
    ImageView remember;
    boolean state_check = true;
    boolean state_flag = true;

    public static String log_in=null;
    public static String json=null;

    ProgressBar progressBar;
    Toolbar toolbar;

    ConnectionDetector cd;  // переменная проверки соединения

    //final String SAVED_LOGIN = "";
    //final String SAVED_PASSWORD = "";
    //private SharedPreferences save_login;
    //SharedPreferences save_pass;
    SharedPreferences mSharedPref;
    //Парочка строковых ресурсов, для сохранения двух текстовых значений:
    final String USERNAME = "Сохраненное имя";
    final String PASSWORD = "Сохраненный пароль";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide(); //Убираем шапку

        button_voiti1 = findViewById(R.id.button_voiti);
        button_voiti1.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= 21){ //Различный ввод для разных api
            findViewById(R.id.edit_login_low_api).setVisibility(View.INVISIBLE);
            edit_log = findViewById(R.id.edit_login);
        } else {
            findViewById(R.id.edit_login).setVisibility(View.INVISIBLE);
            edit_log = findViewById(R.id.edit_login_low_api);

        }

        edit_pas = findViewById(R.id.edit_pass);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);

        text_internet = findViewById(R.id.text_1);
        eye = findViewById(R.id.show_pass_btn);
        remember = findViewById(R.id.btn_remember);

        cd = new ConnectionDetector(this);

        mSharedPref = getPreferences(MODE_PRIVATE);
        //Создаем 2 строковых объекта, берем для них значение из сохраненных данных:
        String saved_name = mSharedPref.getString(USERNAME, "");
        String saved_pass = mSharedPref.getString(PASSWORD, "");

        //Присваиваем вытащенные значения полям ввода:
        edit_log.setText(saved_name);
        edit_pas.setText(saved_pass);

        //Меню
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_icon1);

        navigationView.setNavigationItemSelectedListener(this);

    }
    // Убрать вызов меню без закрытия приложения
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, ActivityDescript.class);
            startActivity(intent);
        } else if (id == R.id.nav_home) {
            Intent intent = new Intent(this,  ActivityContacts.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(this, ActivityWeb.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Показать/скрыть пароль
    public void ShowHidePass(View view){
        if(view.getId()==R.id.show_pass_btn){
            if(edit_pas.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                eye.setImageResource(R.drawable.hidden);
                //Показать
                edit_pas.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                eye.setImageResource(R.drawable.eye);
                //Скрыть
                edit_pas.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

    //Кнопка запомнить пароль
    public void RememberMe(View view){
        if(view.getId()==R.id.btn_remember){
            if (state_check==true) {
                remember.setImageResource(R.drawable.check_true);
                state_flag=true;
                state_check=false;

            }
            else{
                remember.setImageResource(R.drawable.check_false);
                state_flag=false;
                state_check=true;
            }
        }
    }
    //Показываем фронтенд логина
    void visible_login ()
    {
        progressBar.setVisibility(View.INVISIBLE);
        button_voiti1.setVisibility(View.VISIBLE);
        edit_log.setVisibility(View.VISIBLE);
        edit_pas.setVisibility(View.VISIBLE);
        text_internet.setVisibility(View.VISIBLE);
        eye.setVisibility(View.VISIBLE);
        remember.setVisibility(View.VISIBLE);
        findViewById(R.id.text_zap).setVisibility(View.VISIBLE);
        findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
    }

    void open_activity_spisok()
    {
        json = jsonResponse.toString();
        log_in = "noone";
        try {
            sys = jsonResponse.getJSONObject(login);
            log_in = sys.toString();
            pass = sys.getString("password");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (log_in != "noone") {
            String push_pass = edit_pas.getText().toString();
            if (push_pass.equals(pass)) {  // сверяем введенный пароль
                final Intent intent = new Intent(this, ActivitySpisok.class); // Переход в активити списка
                startActivity(intent);
                progressBar.setVisibility(View.INVISIBLE);
                text_internet.setText("");

                if (state_flag == false) {
                    edit_pas.setText("");
                }

                new CountDownTimer(500, 500) { // Возвращаем видимость фронтенда по окончанию загрузки
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        visible_login();
                    }
                }
                        .start();
            } else {
                text_internet.setText("Пароль не верный.");
                visible_login();
            }
        } else {
            text_internet.setText("Данного пользователя не существует.");
            visible_login();
        }
    }

    @Override
    public void onClick(View v) {
        login = edit_log.getText().toString();
        if (state_flag==true) {
            //Сохраняем
            //Создаем экземпляр для хранения данных SharedPreferences, выставляем доступ MODE_PRIVATE:
            mSharedPref = getPreferences(MODE_PRIVATE);

            //Создаем объект Editor для создания пар имя-значение:
            //Сохраняем введенные символы в поля ввода, задавая имена USERNAME,
            //PASSWORD и соответствующие им значения, получаемые из полей ввода:
            SharedPreferences.Editor mEditor = mSharedPref.edit();
            mEditor.putString(USERNAME, edit_log.getText().toString());
            mEditor.putString(PASSWORD, edit_pas.getText().toString());
            mEditor.commit();

        } else {
            remember.setImageResource(R.drawable.check_false);
            //Очищаем
            SharedPreferences.Editor mEditor = mSharedPref.edit();
            mEditor.putString(USERNAME, "");
            mEditor.putString(PASSWORD, "");
            mEditor.commit();
        }

        switch (v.getId()) {
            case R.id.button_voiti:
                if (cd.isConnected()) { // если интернет включен
                    token= null;

                    final Intent intent_main = new Intent(this, MainActivity.class); // Обращаемся к активити Spisok

                    //Считываем JSON БД с UCOZ
                    URL generatedURL = generateURL();
                    new ui_zapros.iekTask().execute(generatedURL);

                    new CountDownTimer(1500, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            //Причем фронтенд при загрузке
                            progressBar.setVisibility(View.VISIBLE);
                            button_voiti1.setVisibility(View.INVISIBLE);
                            edit_log.setVisibility(View.INVISIBLE);
                            edit_pas.setVisibility(View.INVISIBLE);
                            text_internet.setVisibility(View.INVISIBLE);
                            eye.setVisibility(View.INVISIBLE);
                            remember.setVisibility(View.INVISIBLE);
                            findViewById(R.id.text_zap).setVisibility(View.INVISIBLE);
                            findViewById(R.id.toolbar).setVisibility(View.INVISIBLE);
                        }
                        @Override
                        public void onFinish() {

                            if (token != null) { //Если за первый таймер запрос выполнился
                                open_activity_spisok();
                            } else {
                                new CountDownTimer(1500, 1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    @Override
                                    public void onFinish() {
                                        if (token != null) {
                                            open_activity_spisok();
                                        } else { //Если запросы не прошли возвращаем в список
                                            text_internet.setText("Подключение не удалось. Попробуйте снова.");
                                            visible_login();
                                        }
                                    }
                                }.start();
                            }
                        }
                                    }.start();

                }  else {
                    text_internet.setText("Нет подключения к интернету");
                }
                break;
            default:
                break;
        }
    }


}
