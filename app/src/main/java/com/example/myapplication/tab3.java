package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.net.URL;

import static com.example.myapplication.ui_zapros.average_speed_json;
import static com.example.myapplication.ui_zapros.kol_stop_json;
import static com.example.myapplication.ui_zapros.kol_stoy_json;
import static com.example.myapplication.ui_zapros.max_speed_json;
import static com.example.myapplication.ui_zapros.object_json;
import static com.example.myapplication.ui_zapros.probeg_speed_json;
import static com.example.myapplication.ui_zapros.reportresult;
import static com.example.myapplication.ui_zapros.time_speed_json;
import static com.example.myapplication.ui_zapros.time_stoy_json;
import static com.example.myapplication.utils.NetworkUtils4.generateURL4;
import static com.example.myapplication.utils.NetworkUtils4.unixTime;

public class tab3 extends Fragment implements View.OnClickListener{


    public static TextView temp;
    Button button_sutki;
    Button button_nedelya;
    Button button_mesyac;
    Button button_god;
    Button button_refresh_tab3;
    public static long unix_time_zadan = unixTime-(24*60*60);
    ProgressBar progressBar;

    public void zaproz_otchet() {
        reportresult=null;
        new CountDownTimer(1500, 1000) { //Запускаем первый таймер для выполнения запроса sid
            @Override
            public void onTick(long millisUntilFinished) {}
            @Override
            public void onFinish() {
                if (reportresult == null) { //Если за первый таймер запрос ui3 не выполнился
                    new CountDownTimer(1000, 1000) { //Запускаем второй таймер для выполнения запроса ui3
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }
                        @Override
                        public void onFinish() {
                            if (reportresult== null) {
                                new CountDownTimer(1000, 1000) { //Запускаем третий таймер для выполнения запроса ui3
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                    }
                                    @Override
                                    public void onFinish() { //
                                        progressBar.setVisibility(View.INVISIBLE);
                                        temp.setVisibility(View.VISIBLE);
                                        temp.setText(kol_stop_json + "\n" + "\n" + kol_stoy_json + "\n" + "\n" + time_stoy_json + "\n" + "\n" + probeg_speed_json + "\n" +
                                                "\n" + time_speed_json + "\n" + "\n" + average_speed_json + "\n" + "\n" + max_speed_json + "\n" + "\n" + object_json);
                                    }
                                }.start();
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                temp.setVisibility(View.VISIBLE);
                                temp.setText(kol_stop_json + "\n" + "\n" + kol_stoy_json + "\n" + "\n" + time_stoy_json + "\n" + "\n" + probeg_speed_json + "\n" +
                                        "\n" + time_speed_json + "\n" + "\n" + average_speed_json + "\n" + "\n" + max_speed_json + "\n" + "\n" + object_json);
                            }
                        }
                    }.start();
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    temp.setVisibility(View.VISIBLE);
                    temp.setText(kol_stop_json + "\n" + "\n" + kol_stoy_json + "\n" + "\n" + time_stoy_json + "\n" + "\n" + probeg_speed_json + "\n" +
                            "\n" + time_speed_json + "\n" + "\n" + average_speed_json + "\n" + "\n" + max_speed_json + "\n" + "\n" + object_json);
                }
            }
        }.start();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root3 = inflater.inflate(R.layout.tab3, container, false);

        //Присваиваем кнопки
        button_sutki = root3.findViewById(R.id.button_sutki);
        button_sutki.setOnClickListener(this);

        button_nedelya = root3.findViewById(R.id.button_nedelya);
        button_nedelya.setOnClickListener(this);

        button_mesyac = root3.findViewById(R.id.button_mesyac);
        button_mesyac.setOnClickListener(this);

        button_god = root3.findViewById(R.id.button_god);
        button_god.setOnClickListener(this);

        button_refresh_tab3 = root3.findViewById(R.id.button_refresh_tab3);
        button_refresh_tab3.setOnClickListener(this);


        temp = root3.findViewById(R.id.temp_time);
        unixTime = System.currentTimeMillis() / 1000L;
        //temp.setText(unixTime + "х" + unix_time_zadan);

        button_sutki.setBackgroundColor(Color.argb(50,200,250,100));

        progressBar = root3.findViewById(R.id.progress_bar_tab3);
        progressBar.setVisibility(View.INVISIBLE);

        unix_time_zadan = unixTime-(24*60*60);

        URL generatedURL4 = generateURL4();
        new ui_zapros.iekTask4().execute(generatedURL4);
        progressBar.setVisibility(View.VISIBLE);
        temp.setVisibility(View.INVISIBLE);

        zaproz_otchet();

        return root3;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_sutki:
                button_sutki.setBackgroundColor(Color.argb(50,200,250,100));
                button_nedelya.setBackgroundColor(Color.parseColor("#D1E2F0"));
                button_mesyac.setBackgroundColor(Color.parseColor("#D1E2F0"));
                button_god.setBackgroundColor(Color.parseColor("#D1E2F0"));
                unixTime = System.currentTimeMillis() / 1000L;
                unix_time_zadan = unixTime-(24*60*60);

        }

        switch (v.getId()) {
            case R.id.button_nedelya:
                button_sutki.setBackgroundColor(Color.parseColor("#D1E2F0"));
                button_nedelya.setBackgroundColor(Color.argb(50,200,250,100));
                button_mesyac.setBackgroundColor(Color.parseColor("#D1E2F0"));
                button_god.setBackgroundColor(Color.parseColor("#D1E2F0"));
                unixTime = System.currentTimeMillis() / 1000L;
                unix_time_zadan = unixTime-(7*24*60*60);

        }

        switch (v.getId()) {
            case R.id.button_mesyac:
                button_sutki.setBackgroundColor(Color.parseColor("#D1E2F0"));
                button_nedelya.setBackgroundColor(Color.parseColor("#D1E2F0"));
                button_mesyac.setBackgroundColor(Color.argb(50,200,250,100));
                button_god.setBackgroundColor(Color.parseColor("#D1E2F0"));
                unixTime = System.currentTimeMillis() / 1000L;
                unix_time_zadan = unixTime-(31*24*60*60);

        }

        switch (v.getId()) {
            case R.id.button_god:
                button_sutki.setBackgroundColor(Color.parseColor("#D1E2F0"));
                button_nedelya.setBackgroundColor(Color.parseColor("#D1E2F0"));
                button_mesyac.setBackgroundColor(Color.parseColor("#D1E2F0"));
                button_god.setBackgroundColor(Color.argb(50,200,250,100));
                unixTime = System.currentTimeMillis() / 1000L;
                unix_time_zadan = unixTime-(365*24*60*60);

        }


        switch (v.getId()) {
            case R.id.button_refresh_tab3:
                progressBar.setVisibility(View.VISIBLE);
                temp.setVisibility(View.INVISIBLE);
                URL generatedURL4 = generateURL4();
                new ui_zapros.iekTask4().execute(generatedURL4);

                zaproz_otchet();
                break;
            default:
                break;

        }

    }
}
