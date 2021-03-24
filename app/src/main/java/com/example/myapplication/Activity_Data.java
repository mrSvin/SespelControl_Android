package com.example.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import static com.example.myapplication.ActivitySpisok.button1;
import static com.example.myapplication.ActivitySpisok.button10;
import static com.example.myapplication.ActivitySpisok.button11;
import static com.example.myapplication.ActivitySpisok.button12;
import static com.example.myapplication.ActivitySpisok.button13;
import static com.example.myapplication.ActivitySpisok.button14;
import static com.example.myapplication.ActivitySpisok.button15;
import static com.example.myapplication.ActivitySpisok.button2;
import static com.example.myapplication.ActivitySpisok.button3;
import static com.example.myapplication.ActivitySpisok.button4;
import static com.example.myapplication.ActivitySpisok.button5;
import static com.example.myapplication.ActivitySpisok.button6;
import static com.example.myapplication.ActivitySpisok.button7;
import static com.example.myapplication.ActivitySpisok.button8;
import static com.example.myapplication.ActivitySpisok.button9;
import static com.example.myapplication.ActivitySpisok.kol;
import static com.example.myapplication.ActivitySpisok.name_ppc;
import static com.example.myapplication.ui_zapros.kol_ppc;

public class Activity_Data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.data_ppc);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        setTitle(name_ppc );

        //Отображаем  кнопки ППЦ привязанных к базе данных в предыдущем layout  через секунду
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                kol = Integer.valueOf(kol_ppc);

                Button[] btn_spisok = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15};

                //Выводим кнопки ППЦ привязанных к базе данных
                kol = Integer.valueOf(kol_ppc);
                for (int i=0; i<kol; ++i) {
                    btn_spisok[i].setVisibility(View.VISIBLE);
                }

            }
        }
                .start();


    }


}
