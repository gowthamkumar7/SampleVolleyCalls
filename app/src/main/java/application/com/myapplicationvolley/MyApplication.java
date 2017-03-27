package application.com.myapplicationvolley;

import android.app.Application;
import android.content.Context;

/**
 * Created by Admin1 on 3/26/2017.
 */

public class MyApplication extends Application {


    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }


    static Context getMyContext() {
        return myApplication.getApplicationContext();
    }
}
