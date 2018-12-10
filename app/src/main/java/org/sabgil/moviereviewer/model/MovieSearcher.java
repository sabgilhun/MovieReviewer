package org.sabgil.moviereviewer.model;

import android.util.Log;

public class MovieSearcher extends Searcher {
    private final static String TAG = MovieSearcher.class.getSimpleName();

    public MovieSearcher() {
        setClientId("N5HWwaV7Q7Ih4NsIulvK");
        setClientSecret("tpQBkkyj_w");
        //setApiUrl("https://openapi.naver.com/v1/search/movie.xml?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&genre=1");
        setApiUrl("https://openapi.naver.com/v1/search/movie.xml");
    }

    public void addParam(String parameter, String value) {

        if (parameter.equals("display")) {
            if (Integer.parseInt(value) < 10 ||
                    Integer.parseInt(value) > 100) {
                Log.w(TAG, "invalid parameter value");
                return;
            }
        } else if (parameter.equals("start")) {
            if (Integer.parseInt(value) < 1 ||
                    Integer.parseInt(value) > 1000) {
                Log.w(TAG, "invalid parameter value");
                return;
            }
        } else if (parameter.equals("genre")) {
            if (Integer.parseInt(value) < 1 ||
                    Integer.parseInt(value) > 28) {
                Log.w(TAG, "invalid parameter value");
                return;
            }
        } else if (parameter.equals("country")) {
            if (value.length() < 1 || value.length() > 3) {
                Log.w(TAG, "invalid parameter value");
                return;
            }
        } else if (parameter.equals("yearfrom")) {

        } else if (parameter.equals("yearto")) {

        } else {
            Log.w(TAG, "invalid parameter");
            return;
        }

        if (!additionParam.contains(parameter)) {
            additionParam += ("&" + parameter + "=" + value);
        }
    }
}
