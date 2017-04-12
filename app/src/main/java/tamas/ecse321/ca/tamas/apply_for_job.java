package tamas.ecse321.ca.tamas;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class apply_for_job extends AppCompatActivity {
    Button apply_to_job;
    Button goBack;
    Button logout;

    EditText cv_field;
    TextView personal_information_textview;
    TextView job_posting_textview;


    String applicant_cv="";
    String applicant_id="";
    String applicant_fname="";
    String applicant_lname="";
    String applicant_email="";
    String status="";

    String job_posting_id="";
    String course_code="";
    String course_hour="";
    String instructor_name="";

    String jobInfo;
    private String user_id;
    private String pass_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle infoFromJobView = getIntent().getExtras();

        if (infoFromJobView != null) {
            jobInfo = infoFromJobView.getString("selectedItem");
            user_id = infoFromJobView.getString("id");
            pass_word = infoFromJobView.getString("password");
        } else {
            jobInfo = "Unable to fetch selection, return to last screen and try again";
        }

        setContentView(R.layout.activity_apply_for_job);
        cv_field = (EditText) findViewById(R.id.cv_field);


        int i = 1;
        for (String ret : jobInfo.split(" ")) {
            switch (i) {
                case 2:
                    job_posting_id = ret;
                case 4:
                    course_code = ret;
                case 6:
                    course_hour = ret;
                case 9:
                    instructor_name = ret;
                default:
                    i++;
            }
        }

        personal_information_textview = (TextView) findViewById(R.id.personal_information_textview);
        getStudentRecordByID();


        job_posting_textview = (TextView) findViewById(R.id.job_information_textview);
        String jobInfo =  "Job Posting ID: " + job_posting_id + "\n"
                + "Course: " + course_code + "\n" + "Hour: " + course_hour + "\n" + "Instructor: ";
        job_posting_textview.setText(jobInfo);

        goBack = (Button) findViewById(R.id.go_back_to_job_view);
        goBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_view_job);
                Intent intent = new Intent(apply_for_job.this, view_job.class);
                startActivity(intent);
            }
        });

        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
                Intent intent = new Intent(apply_for_job.this,MainActivity.class);
                startActivity(intent);
            }
        });

        apply_to_job = (Button) findViewById((R.id.submit_application_button));
        apply_to_job.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                personal_information_textview.setText("Email is: "+applicant_email);
                submitJobApplication(v);
            }
        });

    }

    public void submitJobApplication(View v) {
        String urlForID = "http://www.jamesgtang.com/tamas/submitJobAppplicationService.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        applicant_cv = cv_field.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                personal_information_textview.setText("");
                job_posting_textview.setTextColor(Color.parseColor("#C63D0F"));
                job_posting_textview.setTextSize(18);
                job_posting_textview.setText("Submission success! You can now logout or go back and apply for another job!");
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Error handling
                System.out.println("Something went wrong!");
                job_posting_textview.setText("Unable to submit!Try again in a moment.");
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
                params.put("STATUS", status);
                params.put("CV", applicant_cv);
                return params;
            }
        };
        queue.add(stringRequest);

    }

    public int getStudentRecordByID(){
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
                        status=c.getString("STATUS");
                        applicant_email=c.getString("EMAIL");

                    String studentInfo="Please verify your information below:\n" + "First Name: "+applicant_fname+"\n"
                            +"Last Name: "+applicant_lname+ "\n"+"Email: "+applicant_email+ "\n"+"Status: "+status+ "\n";
                    personal_information_textview.setText(studentInfo);

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
        return 0;

    }
}

