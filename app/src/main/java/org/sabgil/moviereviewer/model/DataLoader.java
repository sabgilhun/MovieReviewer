package org.sabgil.moviereviewer.model;

import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;


public class DataLoader extends Observable {
    private final static String TAG = DataLoader.class.getSimpleName();
    private Searcher movieSearcher;
    private NaverApiParser xmlParser;
    private ArrayList<MovieItem> movieItems = new ArrayList<>();

    public DataLoader(Observer o) {
        String id = "N5HWwaV7Q7Ih4NsIulvK";
        String secret = "tpQBkkyj_w";
        String url = "https://openapi.naver.com/v1/search/movie.xml";

        addObserver(o);

        movieSearcher = new Searcher(id, secret, url);
        String path = "//channel/item";

        ArrayList<String> needInfoList = new ArrayList<>();
        addNeedInfo(needInfoList);
        xmlParser = new NaverApiParser(path, needInfoList);
        addParam("display", "20");
    }

    public void searchMovieData(final String text) {

        new Thread() {
            @Override
            public void run() {
                super.run();
                InputStream resultXmlStream;

                resultXmlStream = movieSearcher.search(text);
                ArrayList<HashMap<String, String>> items = xmlParser.parse(resultXmlStream);

                movieItems.clear();
                for (HashMap<String, String> data : items) {
                    movieItems.add(new MovieItem(data));
                }

                setChanged();
                notifyObservers();
            }
        }.start();
    }

    private void addNeedInfo(ArrayList<String> list) {
        list.add("title");
        list.add("link");
        list.add("image");
        list.add("pubDate");
        list.add("director");
        list.add("actor");
        list.add("userRating");
    }

    public ArrayList<MovieItem> getItems() {
        return movieItems;
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

        movieSearcher.requestParam.put(parameter, value);
    }
}
