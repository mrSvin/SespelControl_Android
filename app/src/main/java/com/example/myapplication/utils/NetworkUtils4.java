package com.example.myapplication.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static com.example.myapplication.ActivitySpisok.base_id;
import static com.example.myapplication.tab3.unix_time_zadan;
import static com.example.myapplication.ui_zapros.eid;


// считывание json id ППЦ с сервера wialon шаблона "базовый"
public class NetworkUtils4 {
    static String sid = eid;
    public static String id;
    public static long unixTime = System.currentTimeMillis() / 1000L;

    public static URL generateURL4() {
        id = base_id;
        String API_BASE_URL4 = "https://hst-api.wialon.com/wialon/ajax.html?svc=report/exec_report&params={\"reportResourceId\":17376348,\"reportTemplateId\":1,\"reportObjectId\":" + id + ",\"reportObjectSecId\":0,\"interval\":{\"from\":" + unix_time_zadan + ",\"to\":" + unixTime + ",\"flags\":0}}&sid=" + sid;
        Uri builtUri = Uri.parse(API_BASE_URL4)
                .buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        }   catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromURL4(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        InputStream in = urlConnection.getInputStream();

        try {
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
