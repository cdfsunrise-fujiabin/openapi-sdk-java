package org.cdfsunrise;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

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

    public String Get(String url, Map<String, String> headers) throws IOException {
        Request.Builder builder = new Request.Builder().url(url);
        headers.forEach(builder::addHeader);
        Request request = builder.build();

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

    public String Post(String url, Map<String, String> headers, String body) throws IOException {
        Request.Builder builder = new Request.Builder().url(url);
        headers.forEach(builder::addHeader);
        builder.post(okhttp3.RequestBody.create(body, okhttp3.MediaType.parse("application/json; charset=utf-8")));
        Request request = builder.build();

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