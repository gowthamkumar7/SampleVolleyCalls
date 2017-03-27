package application.com.myapplicationvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String mUrl = "https://api.github.com/users/gowthamkumar7";
    private String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button mButton = new Button(this);


        NetworkImageView mImageview = (NetworkImageView) findViewById(R.id.id_networkImage);
        //Adding queue to the
        RequestQueue mQueue = VolleySingleton.getInstance().getRequestQueue();

        ImageLoader mImageLoader = VolleySingleton.getInstance().getmImageLoader();
//        mImageLoader.get("http://s5.tinypic.com/21mrqjq_th.jpg", ImageLoader.getImageListener(mImageview, android.R.drawable.ic_menu_report_image, android.R.drawable.btn_star_big_off));
        //      mImageview.setImageUrl("http://s5.tinypic.com/21mrqjq_th.jpg", mImageLoader);

        JsonObjectRequest mRequest = new JsonObjectRequest(Request.Method.GET, mUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: " + mButton.toString());
                Log.i(TAG, "onResponse: " + response);
                //Your parsing will be done here.
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Error handling.
                    }
                }

        ) {

            //To set Headers to request.
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }


            @Override
            public Priority getPriority() {


                return Priority.HIGH;
            }

            //To get headers from response.
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Log.i(TAG, "parseNetworkResponse: " + response.headers.get("Cache-Control"));
                return super.parseNetworkResponse(response);
            }


        };
        mRequest.setTag("gtm");


        JsonObjectRequest mSecond = new JsonObjectRequest(Request.Method.GET, mUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Log.i(TAG, "onErrorResponse: " + error.getCause());
            }
        });
        mSecond.setTag("gtm");

        mQueue.add(mRequest);
        mQueue.add(mSecond);

        //mQueue.cancelAll("gtm");

    }
}
