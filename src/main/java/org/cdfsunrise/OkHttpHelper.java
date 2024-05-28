package org.cdfsunrise;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpHelper {
    OkHttpClient client = new OkHttpClient();

    public String Get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null)  {
                return response.body().string();
            }
            else {
                return "";
            }
        }
    }

    public String Post(String url, String body) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(okhttp3.RequestBody.create(body, okhttp3.MediaType.parse("application/json; charset=utf-8")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null)  {
                return response.body().string();
            }
            else {
                return "";
            }
        }
    }
}