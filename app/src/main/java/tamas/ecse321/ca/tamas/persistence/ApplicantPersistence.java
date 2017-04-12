
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
import java.util.Map;

public class ApplicantPersistence extends Service {

    String responseFromDB=null;
    Map <String,String> infoMap=new HashMap<>();
    private String applicant_lname;
    private String applicant_fname;
    private String applicant_email;
    private String applicant_status;

    public String authenticateStudentInforinDB(String username,String password) {

        RequestQueue queue= Volley.newRequestQueue(this);

        String urlForID="http://www.jamesgtang.com/tamas/authentication.php?"+"user_id="+
                username+"&user_password="+password;
        StringRequest stringRequest=new StringRequest(Request.Method.GET,urlForID,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                responseFromDB=response;
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
        return responseFromDB;
    }


    public String registerStudenttoDB(final String applicant_lname, final String applicant_fname,
                                      final String applicant_email, final String applicant_id,
                                      final String applicant_password, final String applicant_status) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String urlForID = "http://www.jamesgtang.com/tamas/registerStudent.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                responseFromDB=response;
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();
                responseFromDB=error.toString();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("STUDENT_ID", applicant_id);
                params.put("FNAME",applicant_fname);
                params.put("LNAME",applicant_lname);
                params.put("STATUS",applicant_status);
                params.put("PASSWORD", applicant_password);
                params.put("EMAIL", applicant_email);
                return params;
            }
        };
        queue.add(stringRequest);
        return responseFromDB;
    }


    public Map getStudentRecordByID(final String user_id, String pass_word){

        // clear all exsiting entrie
        RequestQueue queue= Volley.newRequestQueue(this);
        String urlForID="http://www.jamesgtang.com/tamas/studentRecordService.php?user_id="+user_id+"&user_password="+pass_word;


        StringRequest stringRequest=new StringRequest(Request.Method.GET,urlForID,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    // use JSON Parser to tokenize dataS
                    JSONArray listing=new JSONArray(response);

                    JSONObject c=listing.getJSONObject(0);
                    applicant_fname=c.getString("FNAME");
                    applicant_lname=c.getString("LNAME");
                    applicant_status=c.getString("STATUS");
                    applicant_email=c.getString("EMAIL");


                    infoMap.put("applciant_fname",applicant_fname);
                    infoMap.put("applicant_lname",applicant_lname);
                    infoMap.put("applicant_status",applicant_status);
                    infoMap.put("applicant_email",applicant_email);
                    infoMap.put("applicant_id",user_id);


                    String studentInfo="Please verify your information below:\n" + "First Name: "+applicant_fname+"\n"
                            +"Last Name: "+applicant_lname+ "\n"+"Email: "+applicant_email+ "\n"+"Status: "+applicant_status+"\n";

                }catch (JSONException e){
                    e.printStackTrace();
                }
                // handle response here
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Error handling
                System.out.println("Something went wrong!Unable to fetch record");
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
        return infoMap;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
