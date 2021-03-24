package com.example.myapplication;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static com.example.myapplication.utils.NetworkUtils.getResponseFromURL;
import static com.example.myapplication.utils.NetworkUtils2.getResponseFromURL2;
import static com.example.myapplication.utils.NetworkUtils3.getResponseFromURL3;
import static com.example.myapplication.utils.NetworkUtils4.getResponseFromURL4;

public class ui_zapros {

    public static JSONObject jsonResponse=null;
    public static JSONObject sys;
    // Пепеременные БД

    public static String kol_ppc= "0";
    public static String pass= "0";
    public static String login= "0";
    public static String token= "0";

    public static String base_name1= "0";
    public static String base_name2= "0";
    public static String base_name3= "0";
    public static String base_name4= "0";
    public static String base_name5= "0";
    public static String base_name6= "0";
    public static String base_name7= "0";
    public static String base_name8= "0";
    public static String base_name9= "0";
    public static String base_name10= "0";
    public static String base_name11= "0";
    public static String base_name12= "0";
    public static String base_name13= "0";
    public static String base_name14= "0";
    public static String base_name15= "0";

    //public static String base_id=null;
    public static String base_id1 = "0";
    public static String base_id2 = "0";
    public static String base_id3 = "0";
    public static String base_id4 = "0";
    public static String base_id5 = "0";
    public static String base_id6 = "0";
    public static String base_id7 = "0";
    public static String base_id8 = "0";
    public static String base_id9 = "0";
    public static String base_id10 = "0";
    public static String base_id11 = "0";
    public static String base_id12 = "0";
    public static String base_id13 = "0";
    public static String base_id14 = "0";
    public static String base_id15 = "0";

    public static String eid;
    public static String item_temp;

    //Для запроса Utils3
    public static String lsmg=null;
    public static String x_pos = "0";
    public static String y_pos = "0";
    public static String speed = "0";
    public static String u_akb = "0";
    public static String can_r1 = "0";
    public static String can_r2 = "0";
    public static String can_r3 = "0";
    public static String can_r4 = "0";
    public static String can_r5 = "0";
    public static String can_r6 = "0";
    public static String can_r19 = "0";
    public static String can_r20 = "0";
    public static String time_online = "0";
    public static String nagr_os1_json = "0";
    public static String nagr_os2_json = "0";
    public static String nagr_os3_json = "0";
    public static String nagr_os4_json = "0";
    public static String dd1_json = "0";
    public static String dd2_json = "0";
    public static String dd3_json = "0";
    public static String dd4_json = "0";
    public static String dd5_json = "0";
    public static String dd6_json = "0";
    public static String dd7_json = "0";
    public static String dd8_json = "0";

    //Для запроса Utils4
    public static String reportresult="";
    public static String stats_json="";
    public static String kol_stoy_json="";
    public static String kol_stop_json="";
    public static String time_stoy_json="";
    public static String probeg_speed_json="";
    public static String time_speed_json="";
    public static String average_speed_json="";
    public static String max_speed_json="";
    public static String object_json="";

    //Запрос на получение json БД
    public static class iekTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response = "";
            try {
                response = getResponseFromURL(urls[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }
        protected void  onPostExecute(String response) {

            try {
                jsonResponse = new JSONObject(response);
                //Выделяем токен
                if (jsonResponse != null) {
                    token = jsonResponse.getString("token");
                    //Выделяем json данные под введенный логин (Client1)
                    sys = jsonResponse.getJSONObject(login);
                    //Выделяем в нем пароль
                    pass = sys.getString("password");
                    //Выделяем количество из БД
                    kol_ppc = sys.getString("kolichestvo");
                    //Выделяем названия списка из БД
                    base_name1 = sys.getString("name1");
                    base_name2 = sys.getString("name2");
                    base_name3 = sys.getString("name3");
                    base_name4 = sys.getString("name4");
                    base_name5 = sys.getString("name5");
                    base_name6 = sys.getString("name6");
                    base_name7 = sys.getString("name7");
                    base_name8 = sys.getString("name8");
                    base_name9 = sys.getString("name9");
                    base_name10 = sys.getString("name10");
                    base_name11 = sys.getString("name11");
                    base_name12 = sys.getString("name12");
                    base_name13 = sys.getString("name13");
                    base_name14 = sys.getString("name14");
                    base_name15 = sys.getString("name15");
                    //Выделяем id ППЦ из БД
                    base_id1 = sys.getString("id1");
                    base_id2 = sys.getString("id2");
                    base_id3 = sys.getString("id3");
                    base_id4 = sys.getString("id4");
                    base_id5 = sys.getString("id5");
                    base_id6 = sys.getString("id6");
                    base_id7 = sys.getString("id7");
                    base_id8 = sys.getString("id8");
                    base_id9 = sys.getString("id9");
                    base_id10 = sys.getString("id10");
                    base_id11 = sys.getString("id11");
                    base_id12 = sys.getString("id12");
                    base_id13 = sys.getString("id13");
                    base_id14 = sys.getString("id14");
                    base_id15 = sys.getString("id15");
                }

                //base_login = sys.toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    //Запрос на получение sid с wialon
    public static class iekTask2 extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response1 = "";
            try {
                response1 = getResponseFromURL2(urls[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return response1;
        }
        protected void  onPostExecute(String response) {

            try {
                JSONObject jsonResponse1 = new JSONObject(response);
                //Выделяем ied json файла
                eid  = jsonResponse1.getString("eid");

            } catch (JSONException r) {
                r.printStackTrace();
            }

        }
    }

    //Запрос на получение данных с wialon Utils3
    public static class iekTask3 extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response2 = "";
            try {
                response2 = getResponseFromURL3(urls[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return response2;
        }
        protected void  onPostExecute(String response) {

            try {
                JSONObject jsonResponse3 = new JSONObject(response);
                //Выделяем ied json файла
                item_temp  = jsonResponse3.getString("item");

                JSONObject sys2  = jsonResponse3.getJSONObject("item");
                JSONObject jsonResponse2 = sys2;


                lsmg = sys2.getString("lmsg");
                JSONObject sys_lmsg = jsonResponse2.getJSONObject("lmsg");  // Спускаемся нв уроваень lmsg{}
                JSONObject jsonResponse4 = sys_lmsg;
                time_online = sys_lmsg.getString("t");

                JSONObject xy_sys = jsonResponse2.getJSONObject("pos");  // Спускаемся на уроваень pos{}
                x_pos = xy_sys.getString("x");
                y_pos = xy_sys.getString("y");
                speed = xy_sys.getString("s");


                JSONObject dat_sys = jsonResponse4.getJSONObject("p");  // Спускаемся нв уроваень p{}
                u_akb = dat_sys.getString("pwr_ext");
                can_r1 = dat_sys.getString("can_r1"); // Тормоз
                can_r2 = dat_sys.getString("can_r2");
                can_r3 = dat_sys.getString("can_r3");
                can_r4 = dat_sys.getString("can_r4");
                can_r5 = dat_sys.getString("can_r5");
                can_r6 = dat_sys.getString("can_r6");  // Давление в пневмосистеме
                can_r19 = dat_sys.getString("can_r19");
                can_r20 = dat_sys.getString("can_r20"); // Суммарная масса осей
                nagr_os1_json = dat_sys.getString("user_d0"); // Нагрузка на оси
                nagr_os2_json = dat_sys.getString("user_d1");
                nagr_os3_json = dat_sys.getString("user_d2");
                nagr_os4_json = dat_sys.getString("user_d3");
                dd1_json = dat_sys.getString("user_d4"); // давление в первом колесе
                dd2_json = dat_sys.getString("user_d5");
                dd3_json = dat_sys.getString("user_d6"); // давление в третьем колесе
                dd4_json = dat_sys.getString("user_d7");
                dd5_json = dat_sys.getString("can32bitr6");
                dd6_json = dat_sys.getString("can32bitr7"); // давление в шестом колесе
                dd7_json = dat_sys.getString("can32bitr12");
                dd8_json = dat_sys.getString("can32bitr13"); // давление в шестом колесе


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    //Запрос на получение отчета "базовый"
    public static class iekTask4 extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response3 = "";
            try {
                response3 = getResponseFromURL4(urls[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return response3;
        }
        protected void  onPostExecute(String response) {

            try {
                JSONObject jsonResponse6 = new JSONObject(response);
                //Выделяем ied json файла
                reportresult  = jsonResponse6.getString("reportResult");

                JSONObject sys3  = jsonResponse6.getJSONObject("reportResult");
                JSONObject jsonResponse5 = sys3;

                stats_json = sys3.getString("stats");

                JSONArray sys_stats  = jsonResponse5.getJSONArray("stats"); // Спускаемся на уроваень stats{}
                kol_stop_json = sys_stats.getString(4);
                kol_stop_json = kol_stop_json.substring(1);  // Удаляем первый символ
                kol_stop_json = kol_stop_json.substring(0, kol_stop_json.length() - 1);  // Удаляем последний символ
                kol_stop_json = kol_stop_json.replaceAll("\"", " ");

                kol_stoy_json = sys_stats.getString(5);
                kol_stoy_json = kol_stoy_json.substring(1);  // Удаляем первый символ
                kol_stoy_json = kol_stoy_json.substring(0, kol_stoy_json.length() - 1);  // Удаляем последний символ
                kol_stoy_json = kol_stoy_json.replaceAll("\"", " ");

                time_stoy_json = sys_stats.getString(6);
                time_stoy_json = time_stoy_json.substring(1);  // Удаляем первый символ
                time_stoy_json = time_stoy_json.substring(0, time_stoy_json.length() - 1);  // Удаляем последний символ
                time_stoy_json = time_stoy_json.replaceAll("\"", " ");
                time_stoy_json = time_stoy_json.replaceAll("days", "дней");

                probeg_speed_json = sys_stats.getString(8);
                probeg_speed_json = probeg_speed_json.substring(1);  // Удаляем первый символ
                probeg_speed_json = probeg_speed_json.substring(0, probeg_speed_json.length() - 1);  // Удаляем последний символ
                probeg_speed_json = probeg_speed_json.replaceAll("\"", " ");
                probeg_speed_json = probeg_speed_json.replaceAll("km", "км");

                time_speed_json = sys_stats.getString(10);
                time_speed_json = time_speed_json.substring(1);  // Удаляем первый символ
                time_speed_json = time_speed_json.substring(0, time_speed_json.length() - 1);  // Удаляем последний символ
                time_speed_json = time_speed_json.replaceAll("\"", " ");
                time_speed_json = time_speed_json.replaceAll("days", "дней");

                average_speed_json = sys_stats.getString(11);
                average_speed_json = average_speed_json.substring(1);  // Удаляем первый символ
                average_speed_json = average_speed_json.substring(0, average_speed_json.length() - 1);  // Удаляем последний символ
                average_speed_json = average_speed_json.replaceAll("km\\\\/h", "км/ч");
                average_speed_json = average_speed_json.replaceAll("\"", " ");

                max_speed_json = sys_stats.getString(12);
                max_speed_json = max_speed_json.substring(1);  // Удаляем первый символ
                max_speed_json = max_speed_json.substring(0, max_speed_json.length() - 1);  // Удаляем последний символ
                max_speed_json = max_speed_json.replaceAll("km\\\\/h", "км/ч");
                max_speed_json = max_speed_json.replaceAll("\"", " ");

                object_json = sys_stats.getString(1);
                object_json = object_json.substring(1);  // Удаляем первый символ
                object_json = object_json.substring(0, object_json.length() - 1);  // Удаляем последний символ
                object_json = object_json.replaceAll("\"", " ");


            } catch (JSONException r) {
                r.printStackTrace();
            }

        }
    }
}
