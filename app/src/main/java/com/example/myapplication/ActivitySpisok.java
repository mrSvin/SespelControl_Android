package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.utils.ConnectionDetector;

import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;

import static com.example.myapplication.ui_zapros.base_id1;
import static com.example.myapplication.ui_zapros.base_id10;
import static com.example.myapplication.ui_zapros.base_id11;
import static com.example.myapplication.ui_zapros.base_id12;
import static com.example.myapplication.ui_zapros.base_id13;
import static com.example.myapplication.ui_zapros.base_id14;
import static com.example.myapplication.ui_zapros.base_id15;
import static com.example.myapplication.ui_zapros.base_id2;
import static com.example.myapplication.ui_zapros.base_id3;
import static com.example.myapplication.ui_zapros.base_id4;
import static com.example.myapplication.ui_zapros.base_id5;
import static com.example.myapplication.ui_zapros.base_id6;
import static com.example.myapplication.ui_zapros.base_id7;
import static com.example.myapplication.ui_zapros.base_id8;
import static com.example.myapplication.ui_zapros.base_id9;
import static com.example.myapplication.ui_zapros.base_name1;
import static com.example.myapplication.ui_zapros.base_name10;
import static com.example.myapplication.ui_zapros.base_name11;
import static com.example.myapplication.ui_zapros.base_name12;
import static com.example.myapplication.ui_zapros.base_name13;
import static com.example.myapplication.ui_zapros.base_name14;
import static com.example.myapplication.ui_zapros.base_name15;
import static com.example.myapplication.ui_zapros.base_name2;
import static com.example.myapplication.ui_zapros.base_name3;
import static com.example.myapplication.ui_zapros.base_name4;
import static com.example.myapplication.ui_zapros.base_name5;
import static com.example.myapplication.ui_zapros.base_name6;
import static com.example.myapplication.ui_zapros.base_name7;
import static com.example.myapplication.ui_zapros.base_name8;
import static com.example.myapplication.ui_zapros.base_name9;
import static com.example.myapplication.ui_zapros.eid;
import static com.example.myapplication.ui_zapros.kol_ppc;
import static com.example.myapplication.ui_zapros.lsmg;
import static com.example.myapplication.utils.NetworkUtils2.generateURL2;
import static com.example.myapplication.utils.NetworkUtils3.generateURL3;

public class ActivitySpisok extends AppCompatActivity implements View.OnClickListener{

    public static String base_id;

    //Объявляем кнопки ППЦ
    public static Button button1;
    public static Button button2;
    public static Button button3;
    public static Button button4;
    public static Button button5;
    public static Button button6;
    public static Button button7;
    public static Button button8;
    public static Button button9;
    public static Button button10;
    public static Button button11;
    public static Button button12;
    public static Button button13;
    public static Button button14;
    public static Button button15;

    public static Button button_ok;
    public static Button Message_error;

    ConstraintLayout dlina_spisok;
    ConnectionDetector cd;  // переменная проверки соединения

    ProgressBar progressBar;

    public static int kol = 0; // переменная количества ППЦ объявленной в БД


    public static String time_string;
    public static String time_string2;
    public static String name_ppc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Убираем шапку

        setContentView(R.layout.activity_spisok);

        dlina_spisok=findViewById(R.id.SpisokLayout);

        //Присваиваем кнопки ППЦ
        button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(this);

        button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(this);

        button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(this);

        button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(this);

        button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(this);

        button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(this);

        button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(this);

        button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(this);

        button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(this);

        button10 = findViewById(R.id.button_10);
        button10.setOnClickListener(this);

        button11 = findViewById(R.id.button_11);
        button11.setOnClickListener(this);

        button12 = findViewById(R.id.button_12);
        button12.setOnClickListener(this);

        button13 = findViewById(R.id.button_13);
        button13.setOnClickListener(this);

        button14 = findViewById(R.id.button_14);
        button14.setOnClickListener(this);

        button15 = findViewById(R.id.button_15);
        button15.setOnClickListener(this);

        Message_error = findViewById(R.id.Message_error);
        Message_error.setOnClickListener(this);

        button_ok = findViewById(R.id.button_ok);
        button_ok.setOnClickListener(this);

        cd = new ConnectionDetector(this);

        Button[] btn_spisok = {button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13,button14,button15};
        String[] btn_name = {base_name1,base_name2,base_name3,base_name4,base_name5,base_name6,base_name7,base_name8,base_name9,base_name10,base_name11,base_name12,base_name13,base_name14,base_name15};

        for (int i=0; i<15; ++i) {
            //Скрываем кнопки ППЦ до сравнения с БД
            btn_spisok[i].setVisibility(View.INVISIBLE);
            //Присваиваем названия ППЦ C БД
            btn_spisok[i].setText(btn_name[i]);
        }
        //Обрезаем незадейственное поле
        int dp_size = Integer.valueOf(kol_ppc) * 60 + 30;
        int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp_size, getResources().getDisplayMetrics());
        dlina_spisok.setMaxHeight(dimensionInDp);

        //Определяем unix время
        long unixTime = System.currentTimeMillis() / 1000L;
        java.util.Date time=new java.util.Date(unixTime*1000);
        Format formatter = new SimpleDateFormat("ddMMyyyy");
        time_string = formatter.format(time);

        long time_sutki = unixTime-(24*60*60);
        java.util.Date time2=new java.util.Date(time_sutki*1000);
        time_string2 = formatter.format(time2);


        progressBar = findViewById(R.id.progress_bar_spisok);
        progressBar.setVisibility(View.INVISIBLE);

        Message_error = findViewById(R.id.Message_error);
        Message_error.setVisibility(View.INVISIBLE);

        button_ok = findViewById(R.id.button_ok);
        button_ok.setVisibility(View.INVISIBLE);


                //Выводим кнопки ППЦ привязанных к базе данных
                kol = Integer.valueOf(kol_ppc);
                for (int i=0; i<kol; ++i) {
                    btn_spisok[i].setVisibility(View.VISIBLE);
                }
            }
        //Конец

    public void open_data () {
            lsmg= null;
        final Intent intent2 = new Intent(this, ActivitySpisok.class); // Обращаемся к активити Spisok
            final Intent intent = new Intent(this, Activity_Data.class); // Обращаемся к активити Data

            URL generatedURL3 = generateURL3();
            new ui_zapros.iekTask3().execute(generatedURL3);

        new CountDownTimer(1000, 100) { //Запускаем первый таймер для выполнения запроса sid
            @Override
            public void onTick(long millisUntilFinished) {}
            @Override
            public void onFinish() {
                if (lsmg == null) { //Если за первый таймер запрос ui3 не выполнился
                    new CountDownTimer(1000, 100) { //Запускаем второй таймер для выполнения запроса ui3
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }
                        @Override
                        public void onFinish() {
                            if (lsmg== null) {
                                new CountDownTimer(1000, 100) { //Запускаем третий таймер для выполнения запроса ui3
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                    }
                                    @Override
                                    public void onFinish() {
                                        if (lsmg != null) {
                                            startActivity(intent);
                                            progressBar.setVisibility(View.INVISIBLE);
                                        } else { //Если запросы не прошли возвращаем в список
                                            startActivity(intent2);
                                            finish();
                                            progressBar.setVisibility(View.INVISIBLE);

                                            new CountDownTimer(500, 100) { //Запускаемтаймер для вывода таблички
                                                @Override
                                                public void onTick(long millisUntilFinished) {
                                                }

                                                @Override
                                                public void onFinish() {
                                                    Message_error.setVisibility(View.VISIBLE);
                                                    button_ok.setVisibility(View.VISIBLE);
                                                }
                                            }.start();
                                        }
                                    }
                                }.start();
                            } else {
                                startActivity(intent);
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    }.start();
                } else {
                    startActivity(intent);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        }.start();

    }

    //Последовательный запрос json данных c проверкой выполнения запроса
    public void button_zapros() {
        //Запрос на получение sid
        eid= null;
        URL generatedURL2 = generateURL2();
        new ui_zapros.iekTask2().execute(generatedURL2);

        //RememberEditText.clearCache(ActivitySpisok.this);
        progressBar.setVisibility(View.VISIBLE);

        Button[] btn_spisok = {button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13,button14,button15};
        for (int i=0; i<15; ++i) {
            btn_spisok[i].setVisibility(View.INVISIBLE);
        }

        Message_error.setVisibility(View.INVISIBLE);
        button_ok.setVisibility(View.INVISIBLE);

        new CountDownTimer(1000, 1000) { //Запускаем первый таймер для выполнения запроса sid
            @Override
            public void onTick(long millisUntilFinished) {}
            @Override
            public void onFinish() {
                if (eid == null) { //Если за первый таймер запрос sid не выполнился
                    new CountDownTimer(1000, 1000) { //Запускаем второй таймер для выполнения запроса sid
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }
                        @Override
                        public void onFinish() {
                            if (eid== null) {
                                new CountDownTimer(1000, 1000) { //Запускаем третий таймер для выполнения запроса sid
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                    }
                                    @Override
                                    public void onFinish() { //
                                        open_data();
                                    }
                                }.start();
                            } else {
                                open_data();
                            }
                        }
                    }.start();
                } else {
                    open_data();
                }
            }
        }.start();
    }

    //Описываем выполнение кнопок
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_1:
                        name_ppc=base_name1;
                        base_id=base_id1;
                        button_zapros();
                break;default:break;
        }

        switch (v.getId()) {
            case R.id.button_2:
                        name_ppc=base_name2;
                        base_id=base_id2;
                        button_zapros();
                        break;default:break;
                    }

        switch (v.getId()) {
            case R.id.button_3:
                        name_ppc=base_name3;
                        base_id=base_id3;
                        button_zapros();
                        break;default:break;
                    }
        switch (v.getId()) {
            case R.id.button_4:
                name_ppc=base_name4;
                base_id=base_id4;
                button_zapros();
                break;default:break;
        }
        switch (v.getId()) {
            case R.id.button_5:
                name_ppc=base_name5;
                base_id=base_id5;
                button_zapros();
                break;default:break;
        }
        switch (v.getId()) {
            case R.id.button_6:
                name_ppc=base_name6;
                base_id=base_id6;
                button_zapros();
                break;default:break;
        }
        switch (v.getId()) {
            case R.id.button_7:
                name_ppc=base_name7;
                base_id=base_id7;
                button_zapros();
                break;default:break;
        }
        switch (v.getId()) {
            case R.id.button_8:
                name_ppc=base_name8;
                base_id=base_id8;
                button_zapros();
                break;default:break;
        }
        switch (v.getId()) {
            case R.id.button_9:
                name_ppc=base_name9;
                base_id=base_id9;
                button_zapros();
                break;default:break;
        }
        switch (v.getId()) {
            case R.id.button_10:
                name_ppc=base_name10;
                base_id=base_id10;
                button_zapros();
                break;default:break;
        }
        switch (v.getId()) {
            case R.id.button_11:
                name_ppc=base_name11;
                base_id=base_id11;
                button_zapros();
                break;default:break;
        }
        switch (v.getId()) {
            case R.id.button_12:
                name_ppc=base_name12;
                base_id=base_id12;
                button_zapros();
                break;default:break;
        }
        switch (v.getId()) {
            case R.id.button_13:
                name_ppc=base_name13;
                base_id=base_id13;
                button_zapros();
                break;default:break;
        }
        switch (v.getId()) {
            case R.id.button_14:
                name_ppc=base_name14;
                base_id=base_id14;
                button_zapros();
                break;default:break;
        }
        switch (v.getId()) {
            case R.id.button_15:
                name_ppc=base_name15;
                base_id=base_id15;
                button_zapros();
                break;default:break;
        }

        switch (v.getId()) {
            case R.id.button_ok:
                Message_error.setVisibility(View.INVISIBLE);
                button_ok.setVisibility(View.INVISIBLE);
                break;default:break;
        }



    }

}
