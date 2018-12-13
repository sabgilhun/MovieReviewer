package org.sabgil.moviereviewer.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class MovieItemViewModel {
    public Context context;

    private HashMap<String, String> item;
    public final ObservableField<Spanned> title = new ObservableField<>();
    public final ObservableFloat rating = new ObservableFloat();
    public final ObservableField<String> pubData = new ObservableField<>();
    public final ObservableField<String> director = new ObservableField<>();
    public final ObservableField<String> actor = new ObservableField<>();
    public final ObservableField<Drawable> img = new ObservableField<>();

    public MovieItemViewModel(Context context, HashMap<String, String> item) {
        this.context = context;
        float rating = Float.parseFloat(item.get("userRating")) / 2;
        this.item = item;
        this.title.set(Html.fromHtml(item.get("title")));
        this.rating.set(rating);
        this.pubData.set(item.get("pubDate"));
        this.director.set(item.get("director"));
        this.actor.set(item.get("actor"));
    }

    public void loadImageView() {
        ImageLoad imgLoad = new ImageLoad();
        imgLoad.setItem(item);
        imgLoad.start();
    }

    public void onClickItem() {
        Uri uri = Uri.parse(item.get("link"));

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    class ImageLoad extends Thread {
        private HashMap<String, String> item;

        public void setItem(HashMap<String, String> item) {
            this.item = item;
        }

        @Override
        public void run() {
            super.run();

            try {
                Resources resources = context.getResources();
                URL url = new URL(item.get("image"));

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoInput(true);
                con.connect();

                InputStream is = con.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);

                img.set(new BitmapDrawable(resources, bitmap));
            } catch (Exception e) {

            }
        }
    }
}