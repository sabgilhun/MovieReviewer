package org.sabgil.moviereviewer.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Searcher {
    private final static String TAG = Searcher.class.getSimpleName();

    String clientId;
    String clientSecret;
    String apiUrl;
    String resultXML;
    protected String additionParam = "";

    public Searcher() {
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl + "?query=";
    }

    public void clearParam() {
        additionParam = "";
    }

    public void search(final String method, String text) {
        final String searchUrl;

        try {
            searchUrl = apiUrl + URLEncoder.encode(text, "UTF-8") + additionParam;
            Log.i(TAG, searchUrl);
        } catch (Exception e) {
            Log.e(TAG, "encoding error");
            return;
        }

        new Thread() {

            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(searchUrl);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod(method);
                    con.setRequestProperty("X-Naver-Client-Id", clientId);
                    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                    int responseCode = con.getResponseCode();

                    BufferedReader br;
                    if (responseCode == 200) {
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    } else {
                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }

                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = br.readLine()) != null) {
                        response.append(inputLine);
                    }

                    br.close();
                    resultXML = response.toString();

                    Log.i(TAG, resultXML);
                } catch (Exception e) {
                    Log.e(TAG, "search error", e);
                }
            }
        }.start();
    }
}
