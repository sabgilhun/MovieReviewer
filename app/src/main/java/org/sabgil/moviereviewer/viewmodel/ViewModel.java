package org.sabgil.moviereviewer.viewmodel;

import java.util.Observer;

public interface ViewModel extends Observer {

    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();

}
