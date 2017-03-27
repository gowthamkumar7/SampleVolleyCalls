package application.com.myapplicationvolley;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Admin1 on 3/26/2017.
 */

public class VolleySingleton {


    private static VolleySingleton mInstance;
    private RequestQueue mQueue;
    private ImageLoader mImageLoader;

    private VolleySingleton() {
        mQueue = Volley.newRequestQueue(MyApplication.getMyContext());
        mImageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {

            private LruCache<String, Bitmap> mCache = new LruCache<>(1000);

            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
        });
    }

    public static VolleySingleton getInstance() {
        if (mInstance == null) {
            mInstance = new VolleySingleton();
        }

        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        return mQueue;
    }

    public ImageLoader getmImageLoader() {
        return mImageLoader;
    }
}
