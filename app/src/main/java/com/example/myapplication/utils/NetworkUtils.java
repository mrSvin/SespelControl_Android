package com.example.myapplication.utils;


import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
// считывание json БД с домена UCOZ
public class NetworkUtils {

    public static URL generateURL() {
        String API_BASE_URL = "https://raw.githubusercontent.com/mrSvin/SespelControl/main/sespel_control.json";
        Uri builtUri = Uri.parse(API_BASE_URL)
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

    public static String getResponseFromURL(URL url) throws IOException {
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
