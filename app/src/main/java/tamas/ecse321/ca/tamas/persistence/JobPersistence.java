package tamas.ecse321.ca.tamas.persistence;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;

public class JobPersistence extends Service {

    private LinkedList<String> jobLinkedList;

    public LinkedList getAllJobPosting(){

        RequestQueue queue= Volley.newRequestQueue(this);
        String urlForID="http://www.jamesgtang.com/tamas/jobPostingService.php";

        StringRequest stringRequest=new StringRequest(Request.Method.GET,urlForID,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    // use JSON Parser to tokenize data
                    JSONArray listing=new JSONArray(response);
                    for(int i=0;i<listing.length();i++){
                        JSONObject c=listing.getJSONObject(i);
                        String instructor_name=c.getString("INSTRUCTOR_NAME");
                        String course_name=c.getString("COURSE");
                        String post_id=c.getString("POST_ID");
                        String hour=c.getString("HOUR");

                        String jobInfor="Post_ID: "+post_id+" Course: "+course_name+ " Hour: "+hour
                                +" Instructor_Name: "+instructor_name;

                        jobLinkedList.add(jobInfor);

                        HashMap<String, String> map=new HashMap<String,String>();
                        map.put("INSTRUCTOR_NAME",instructor_name);
                        map.put("COURSE_NAME",course_name);
                        map.put("POST_ID",post_id);
                        map.put("HOUR",hour);

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                // handle response here
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
        return jobLinkedList;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
