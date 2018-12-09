package org.sabgil.moviereviewer.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class NaverSearcher {
    private final static String TAG = NaverSearcher.class.getSimpleName();

    String mClientId;
    String mClientSecret;
    String mRequestMethod;
    String mApiUrl;
    String resultXML;


    public NaverSearcher() {
    }

    public void setClientId(String mClientId) {
        this.mClientId = mClientId;
    }

    public void setClientSecret(String mClientSecret) {
        this.mClientSecret = mClientSecret;
    }

    public void setRequestMethod(String mRequestMethod) {
        this.mRequestMethod = mRequestMethod;
    }

    public void setApiUrl(String mApiUrl) {
        this.mApiUrl = mApiUrl;
    }

    public void search(String text) {

        final String searchUrl;

        try {
            searchUrl = mApiUrl + URLEncoder.encode(text, "UTF-8");
        } catch (Exception e) {
            Log.e(TAG, "encode error", e);
            return;
        }

        new Thread() {

            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(searchUrl);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod(mRequestMethod);
                    con.setRequestProperty("X-Naver-Client-Id", mClientId);
                    con.setRequestProperty("X-Naver-Client-Secret", mClientSecret);
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
                    Log.e(TAG, "naver api search error", e);
                }
            }
        }.start();
    }
}
