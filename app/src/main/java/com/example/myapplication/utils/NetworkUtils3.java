package com.example.myapplication.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static com.example.myapplication.ActivitySpisok.base_id;
import static com.example.myapplication.ui_zapros.eid;

// считывание json id ППЦ с сервера wialon
public class NetworkUtils3 {
    private static String date;
    public static String sid;
    public static String id;

    public static URL generateURL3() {
        id = base_id;
        sid = eid;
        String API_BASE_URL3 = "https://hst-api.wialon.com/wialon/ajax.html?svc=core/search_item&params={\"id\":"+ id + ",\"flags\":1025}&sid=" + eid;
        Uri builtUri = Uri.parse(API_BASE_URL3)
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

    public static String getResponseFromURL3(URL url) throws IOException {
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
