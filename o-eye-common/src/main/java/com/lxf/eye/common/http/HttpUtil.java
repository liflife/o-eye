package com.lxf.eye.common.http;

import okhttp3.*;

import java.io.IOException;

public class HttpUtil {
    private static String accessToken = "b96f0273ce84293b474a998f7bbcd5e9238a90aa";

    public static void setAccessToken(String token) {
        accessToken = token;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String call(String url) {
        String string = "";
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .build();
            final Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            string = response.body().string();
            System.out.println("run: " + string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }
    public static String callJson(String url,String token) {
        String string = "";
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            final Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "token " + token)
                    .get()
                    .build();

            final Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            string = response.body().string();
            System.out.println("run: " + string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }
}
