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

import java.util.HashMap;
import java.util.Map;


public class JobApplicationPersistence extends Service {

    String applicant_cv="";
    String job_posting_id="";
    String applicant_fname="";
    String applicant_lname="";
    String applicant_email="";
    String applicant_status="";

    private String feedback;

    public String submitJobApplicationtoDB(String id,String fname,String lname,String email,String cv,String status) {

        applicant_cv=cv;
        job_posting_id=id;
        applicant_fname=fname;
        applicant_lname=lname;
        applicant_email=email;
        applicant_status=status;

        String urlForID = "http://www.jamesgtang.com/tamas/submitJobAppplicationService.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                feedback=response;
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Error handling
                feedback="Failed";
                System.out.println("Something went wrong!");
                error.printStackTrace();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("JOB_POSTING_ID", job_posting_id);
                params.put("APPLICANT_FNAME", applicant_fname);
                params.put("APPLICANT_LNAME", applicant_lname);
                params.put("APPLICANT_EMAIL", applicant_email);
                params.put("STATUS", applicant_status);
                params.put("CV", applicant_cv);
                return params;
            }
        };
        queue.add(stringRequest);
        return feedback;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
