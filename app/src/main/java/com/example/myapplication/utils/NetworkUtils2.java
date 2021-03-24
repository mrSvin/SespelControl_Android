package com.example.myapplication.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static com.example.myapplication.ui_zapros.token;

// считывание json с сервера wialon для получения eid

public class NetworkUtils2 {


    public static URL generateURL2() {
        String API_BASE_URL2 = "https://hst-api.wialon.com/wialon/ajax.html?svc=token/login&params={\"token\":\""+ token + "\"}";
        Uri builtUri = Uri.parse(API_BASE_URL2)
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

    public static String getResponseFromURL2(URL url) throws IOException {
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
