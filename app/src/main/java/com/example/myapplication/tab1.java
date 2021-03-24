package com.example.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.net.URL;

import static com.example.myapplication.ui_zapros.can_r1;
import static com.example.myapplication.ui_zapros.can_r2;
import static com.example.myapplication.ui_zapros.can_r20;
import static com.example.myapplication.ui_zapros.can_r6;
import static com.example.myapplication.ui_zapros.dd1_json;
import static com.example.myapplication.ui_zapros.dd2_json;
import static com.example.myapplication.ui_zapros.dd3_json;
import static com.example.myapplication.ui_zapros.dd4_json;
import static com.example.myapplication.ui_zapros.dd5_json;
import static com.example.myapplication.ui_zapros.dd6_json;
import static com.example.myapplication.ui_zapros.dd7_json;
import static com.example.myapplication.ui_zapros.dd8_json;
import static com.example.myapplication.ui_zapros.nagr_os1_json;
import static com.example.myapplication.ui_zapros.nagr_os2_json;
import static com.example.myapplication.ui_zapros.nagr_os3_json;
import static com.example.myapplication.ui_zapros.nagr_os4_json;
import static com.example.myapplication.ui_zapros.speed;
import static com.example.myapplication.ui_zapros.time_online;
import static com.example.myapplication.ui_zapros.u_akb;
import static com.example.myapplication.utils.NetworkUtils3.generateURL3;
import static com.example.myapplication.utils.NetworkUtils4.unixTime;


public class tab1 extends Fragment {

    public static TextView probeg_str;
    public static TextView speed_str;
    public static TextView voltage_str;
    public static TextView dop_os_str;
    public static TextView davl_str;
    public static TextView nagr1_str;
    public static TextView nagr2_str;
    public static TextView nagr3_str;
    public static TextView nagr4_str;
    public static TextView nagrsum_str;
    public static TextView dd1_str;
    public static TextView dd2_str;
    public static TextView dd3_str;
    public static TextView dd4_str;
    public static TextView dd5_str;
    public static TextView dd6_str;
    public static TextView dd7_str;
    public static TextView dd8_str;
    public static TextView tormoz_str;
    public static TextView time_refresh_str;

    Button button_svyaz_yes;
    Button button_svyaz_no;

    public ImageView dd7_image;
    public ImageView dd8_image;
    public ImageView os4_image;

    int temp_probeg = 3;
    int temp_speed = 0;
    double temp_voltage = 0;
    double temp_davl = 0;
    int temp_nagr1 = 0;
    int temp_nagr2 = 0;
    int temp_nagr3 = 0;
    int temp_nagr4 = 0;
    int temp_nagrsum = 0;
    int temp_dop_os = 0;
    double temp_dd1 = 0;
    double temp_dd2 = 0;
    double temp_dd3 = 0;
    double temp_dd4 = 0;
    double temp_dd5 = 0;
    double temp_dd6 = 0;
    double temp_dd7 = 0;
    double temp_dd8 = 0;
    int temp_tormoz = 0;

    String days_str = "";
    String chas_str = "";
    String min_str = "";
    String sec_str = "";

    long days = 0;
    long chas = 0;
    long min = 0;
    long sec = 0;

    public static int i;

    void data_vivod () {   //Преобразование и вывод значений с запроса ietask3

        speed_str.setText(speed  + " км/ч");

        temp_voltage= Double.parseDouble(u_akb);
        temp_voltage = Math.floor(temp_voltage*100)/100.0;
        voltage_str.setText(temp_voltage + " В");

        temp_davl = Double.parseDouble(can_r6)*0.05;
        temp_davl = Math.floor(temp_davl*100)/100.0;
        davl_str.setText(temp_davl + " бар");

        //Вывод суммарной нагрузки на оси
        temp_nagrsum = Integer.parseInt(can_r20)*2;
        if (temp_nagrsum<=120000) {
            nagrsum_str.setText(temp_nagrsum + " кг");
        } else {nagrsum_str.setText("н/д");}

        temp_tormoz = Integer.parseInt(can_r1);
        if (temp_tormoz == 204) {
            tormoz_str.setText("отпущен");
        } else {tormoz_str.setText("нажат");}

        //Состояние доп. оси
        temp_dop_os = Integer.parseInt(can_r2);
        if ((temp_dop_os == 253) || (temp_dop_os == 245)) {
            dop_os_str.setText("поднято");
        } else {dop_os_str.setText("отпущено");}

        unixTime = System.currentTimeMillis() / 1000L;
        long t_online = Long.parseLong(time_online);
        long t_refresh = unixTime - t_online;

        //Вывод времени последнего обновления
        days=t_refresh/86400;
        if (days>=1) {
            days=(int)days;
            days_str=days + "д";
        } else {
            days = 0;
            days_str="";
        }

        chas=t_refresh-days*86400;
        if (chas>=3600) {
            chas=(int)(chas/3600);
            chas_str=chas + "ч";
        } else {
            chas = 0;
            chas_str="";
        }

        min=t_refresh-days*86400-chas*3600;
        if (min>=60) {
            min=(int)(min/60);
            min_str=min + "мин";
        } else {
            min = 0;
            min_str="";
        }

        sec=t_refresh-days*86400-chas*3600-min*60;
        if (sec>=1) {
            sec=(int)sec;
            sec_str=sec + "с";
        } else {
            sec = 0;
            sec_str="";
        }

        time_refresh_str.setText("обновлено:" + days_str + chas_str + min_str + sec_str +" назад");

        // Нагрузка на оси
        if ((temp_dop_os == 253) || (temp_dop_os == 245)) {
            nagr1_str.setText("0 кг");
        } else {
        temp_nagr1 = Integer.parseInt(nagr_os1_json);
        if (temp_nagr1<30000) {
            nagr1_str.setText(temp_nagr1 + " кг");
        } else {nagr1_str.setText("н/д");}
        }

        if ( temp_nagr4 > 10 && temp_dop_os == 245) {
            nagr2_str.setText("0 кг");
        } else {
            temp_nagr2 = Integer.parseInt(nagr_os2_json);
            if (temp_nagr2 < 30000) {
                nagr2_str.setText(temp_nagr2 + " кг");
            } else {nagr2_str.setText("н/д"); }
        }

        temp_nagr3 = Integer.parseInt(nagr_os3_json);
        if (temp_nagr3<30000) {
            nagr3_str.setText(temp_nagr3 + " кг");
        } else {nagr3_str.setText("н/д");}

        temp_nagr4 = Integer.parseInt(nagr_os4_json);
        if (temp_nagr4<30000) {
            nagr4_str.setText(temp_nagr4 + " кг");
        } else {nagr4_str.setText("н/д");}


        temp_dd1 = Double.parseDouble(dd1_json)*0.1;
        temp_dd1 = Math.round(temp_dd1*10.0)/10.0;
        if (temp_dd1<=24) {
            dd1_str.setText(temp_dd1 + " бар");
        } else {dd1_str.setText("н/д");}

        temp_dd2 = Double.parseDouble(dd2_json)*0.1;
        temp_dd2 = Math.round(temp_dd2*10.0)/10.0;
        if (temp_dd2<=24) {
            dd2_str.setText(temp_dd2 + " бар");
        } else {dd2_str.setText("н/д");}

        temp_dd3 = Double.parseDouble(dd3_json)*0.1;
        temp_dd3 = Math.round(temp_dd3*10.0)/10.0;
        if (temp_dd3<=24) {
            dd3_str.setText(temp_dd3 + " бар");
        } else {dd3_str.setText("н/д");}

        temp_dd4 = Double.parseDouble(dd4_json)*0.1;
        temp_dd4 = Math.round(temp_dd4*10.0)/10.0;
        if (temp_dd4<=24) {
            dd4_str.setText(temp_dd4 + " бар");
        } else {dd4_str.setText("н/д");}

        temp_dd5 = Double.parseDouble(dd5_json)*0.1;
        temp_dd5 = Math.round(temp_dd5*10.0)/10.0;
        if (temp_dd5<=24) {
            dd5_str.setText(temp_dd5 + " бар");
        } else {dd5_str.setText("н/д");}

        temp_dd6 = Double.parseDouble(dd6_json)*0.1;
        temp_dd6 = Math.round(temp_dd6*10.0)/10.0;
        if (temp_dd6<=24) {
            dd6_str.setText(temp_dd6 + " бар");
        } else {dd6_str.setText("н/д");}

        temp_dd7 = Double.parseDouble(dd7_json)*0.1;
        temp_dd7 = Math.round(temp_dd7*10.0)/10.0;
        if (temp_dd7<=24) {
            dd7_str.setText(temp_dd7 + " бар");
        } else {dd7_str.setText("н/д");}

        temp_dd8 = Double.parseDouble(dd8_json)*0.1;
        temp_dd8 = Math.round(temp_dd8*10.0)/10.0;
        if (temp_dd8<=24) {
            dd8_str.setText(temp_dd8 + " бар");
        } else {dd8_str.setText("н/д");}

        //Вывод индикации связи
        if (t_refresh <= 15) {
            button_svyaz_yes.setVisibility(View.VISIBLE);
            button_svyaz_no.setVisibility(View.INVISIBLE);
        } else {
            button_svyaz_yes.setVisibility(View.INVISIBLE);
            button_svyaz_no.setVisibility(View.VISIBLE);
        }

        // Если трехосная ППЦ, то скрыть визуализацию четвертой оси
        if (temp_nagr4 == 0) {
            dd7_image.setVisibility(View.INVISIBLE);
            dd7_str.setText("");

            dd8_image.setVisibility(View.INVISIBLE);
            dd8_str.setText("");

            os4_image.setVisibility(View.INVISIBLE);
            nagr4_str.setText("");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
}

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.tab1, container, false);

        button_svyaz_no = root.findViewById(R.id.button_svyaz_no);
        button_svyaz_yes = root.findViewById(R.id.button_svyaz_yes);

                speed_str = root.findViewById(R.id.speed_string);
                voltage_str = root.findViewById(R.id.voltage_string);
                davl_str = root.findViewById(R.id.davl_string);
                nagrsum_str = root.findViewById(R.id.nagrsum_string);
                tormoz_str = root.findViewById(R.id.tormoz_string);
                dop_os_str = root.findViewById(R.id.dop_os_string);
                time_refresh_str = root.findViewById(R.id.time_refresh);
                //Вывод нагрузок на оси
                nagr1_str = root.findViewById(R.id.nagr1_string);
                nagr2_str = root.findViewById(R.id.nagr2_string);
                nagr3_str = root.findViewById(R.id.nagr3_string);
                nagr4_str = root.findViewById(R.id.nagr4_string);
                //Вывод давления в колесах
                dd1_str = root.findViewById(R.id.dd1_string);
                dd2_str = root.findViewById(R.id.dd2_string);
                dd3_str = root.findViewById(R.id.dd3_string);
                dd4_str = root.findViewById(R.id.dd4_string);
                dd5_str = root.findViewById(R.id.dd5_string);
                dd6_str = root.findViewById(R.id.dd6_string);
                dd7_str = root.findViewById(R.id.dd7_string);
                dd8_str = root.findViewById(R.id.dd8_string);


                dd7_image = root.findViewById(R.id.image_koleso7);
                dd8_image = root.findViewById(R.id.image_koleso8);
                os4_image = root.findViewById(R.id.image_os4);

                data_vivod();

                new CountDownTimer(990000000,1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                        URL generatedURL3 = generateURL3();
                        new ui_zapros.iekTask3().execute(generatedURL3);

                        speed_str = root.findViewById(R.id.speed_string);
                        voltage_str = root.findViewById(R.id.voltage_string);
                        davl_str = root.findViewById(R.id.davl_string);
                        nagrsum_str = root.findViewById(R.id.nagrsum_string);
                        tormoz_str = root.findViewById(R.id.tormoz_string);
                        dop_os_str = root.findViewById(R.id.dop_os_string);
                        time_refresh_str = root.findViewById(R.id.time_refresh);
                        //Вывод нагрузок на оси
                        nagr1_str = root.findViewById(R.id.nagr1_string);
                        nagr2_str = root.findViewById(R.id.nagr2_string);
                        nagr3_str = root.findViewById(R.id.nagr3_string);
                        nagr4_str = root.findViewById(R.id.nagr4_string);
                        //Вывод давления в колесах
                        dd1_str = root.findViewById(R.id.dd1_string);
                        dd2_str = root.findViewById(R.id.dd2_string);
                        dd3_str = root.findViewById(R.id.dd3_string);
                        dd4_str = root.findViewById(R.id.dd4_string);
                        dd5_str = root.findViewById(R.id.dd5_string);
                        dd6_str = root.findViewById(R.id.dd6_string);
                        dd7_str = root.findViewById(R.id.dd7_string);
                        dd8_str = root.findViewById(R.id.dd8_string);

                        data_vivod();
                    }

                    @Override
                    public void onFinish() {
                        i = 0;
                    }
        }.start();


        return root;

    }


}

