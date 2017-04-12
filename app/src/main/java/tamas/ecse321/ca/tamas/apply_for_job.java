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

import tamas.ecse321.ca.tamas.controller.ApplicantController;
import tamas.ecse321.ca.tamas.controller.ApplicationController;

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

    ApplicantController applicantController;
    ApplicationController applicationController;
    Map<String,String> applicantInfo;

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
        applicantController=new ApplicantController();
        applicantInfo= applicantController.getApplicantRecordById(pass_word,user_id);
        if(applicantInfo!=null){
            applicant_id=applicantInfo.get("applicant_id");
            applicant_fname=applicantInfo.get("applicant_fname");
            applicant_lname=applicantInfo.get("applicant_lname");
            applicant_email=applicantInfo.get("applicant_email");
            status=applicantInfo.get("applicant_status");

            String applicantInfo="Please verify your information below:\n" + "First Name: "+applicant_fname+"\n"
                    +"Last Name: "+applicant_lname+ "\n"+"Email: "+applicant_email+ "\n"+"Status: "+status+ "\n";
            personal_information_textview.setText(applicantInfo);

        }else{
            personal_information_textview.setText("Unable to fetch applicant information, please make sure your device has good internet connection!");
        }


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
                applicant_cv = cv_field.getText().toString();
                applicationController=new ApplicationController(applicant_cv,job_posting_id,applicant_fname,applicant_lname,applicant_email,status);
                String feedback;
                feedback=applicantController.vertifyApplicantInformation();
                if(feedback.equals("Verified!")){
                    if(applicationController.submitApplication().equals("Update Success!")){
                        job_posting_textview.setTextColor(Color.parseColor("#C63D0F"));
                        job_posting_textview.setTextSize(18);
                        job_posting_textview.setText("Submission success! You can now logout or go back and apply for another job!");
                    }else{
                        job_posting_textview.setTextColor(Color.parseColor("#C63D0F"));
                        job_posting_textview.setText("Unable to submit!Try again in a moment.");
                    }
                }else{
                    job_posting_textview.setText("feedback");
                }
                personal_information_textview.setText("");

            }
        });

    }




}

