package application.com.myapplicationvolley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView mImageVew = (ImageView) findViewById(R.id.id_networkImage);
        RequestQueue mQueue = Volley.newRequestQueue(this);
        ImageLoader mLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            LruCache<String, Bitmap> mCache = new LruCache<>(100);

            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
        });

        mLoader.get("http://s5.tinypic.com/21mrqjq_th.jpg", ImageLoader.getImageListener(mImageVew, R.mipmap.ic_launcher, R.mipmap.ic_launcher));

        //mImageVew.setImageUR("http://s5.tinypic.com/21mrqjq_th.jpg",mLoader);


    }

}
