package tamas.ecse321.ca.tamas.util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class PersistenceChangeObserver extends Service {
    public int rowObserved;
    public int rowLocal;
    public boolean update;

    public boolean observe(Adapter aAdapter) {
        rowLocal=aAdapter.getCount();
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlForID = "http://www.jamesgtang.com/tamas/jobPostingServiceObserver.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlForID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    rowObserved = Integer.valueOf(response);
                    if (rowLocal == rowObserved) {
                        update=false;
                    } else {
                        update=true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);

        return update;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
