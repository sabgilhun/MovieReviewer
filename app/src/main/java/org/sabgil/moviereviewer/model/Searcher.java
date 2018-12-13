package org.sabgil.moviereviewer.model;

import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class Searcher {
    private final static String TAG = Searcher.class.getSimpleName();

    public String clientId;
    public String clientSecret;
    public String apiUrl;
    protected HashMap<String, String> requestParam;

    public Searcher(String id, String secret, String url) {
        clientId = id;
        clientSecret = secret;
        apiUrl = url;
        requestParam = new HashMap<>();
    }

    private String convertParamToUrl() {
        String param = "";

        if (requestParam.isEmpty())
            return "";

        for (String key : requestParam.keySet()) {
            param += ("&" + key + "=" + requestParam.get(key));
        }

        return param;
    }

    private String makeUrl(String searchWord) {
        String url;

        try {
            url = apiUrl + "?query=" + URLEncoder.encode(searchWord, "UTF-8");
            Log.i(TAG, url);
        } catch (Exception e) {
            Log.e(TAG, "encoding error");
            return null;
        }

        return url;
    }

    public void clearParam() {
        requestParam.clear();
    }

    public InputStream search(String searchWord) {
        String searchUrl;
        InputStream resultStream;
        HttpURLConnection connection;

        searchUrl = makeUrl(searchWord) + convertParamToUrl();
        try {
            URL url = new URL(searchUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Naver-Client-Id", clientId);
            connection.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                resultStream = connection.getInputStream();
            } else {
                resultStream = connection.getErrorStream();
            }
            Log.i(TAG, resultStream.toString());

        } catch (Exception e) {
            resultStream = null;
            Log.e(TAG, "search error", e);
        }

        return resultStream;
    }
}
