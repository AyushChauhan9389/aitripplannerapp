package com.mad.tripon;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class volley {

    private RequestQueue requestQueue;
    private static volley mInstance;

    private volley(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public  static synchronized volley getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new volley(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
