package tamas.ecse321.ca.tamas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText id_field;
    private EditText fname_field;
    private EditText lname_field;
    private EditText pwd_field;
    private EditText email_field;
    private TextView register_feedback;
    Button register_button;
    Button sign_in_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_button=(Button)findViewById(R.id.register_student_button);
        register_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                register(v);
            }
        });
        sign_in_button=(Button)findViewById(R.id.back_to_signin);
        sign_in_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_signin);
                Intent intent=new Intent(Register.this,Signin.class);
                startActivity(intent);
            }
        });
    }


    public void register(View v) {

        register_feedback = (TextView) findViewById(R.id.register_feedback);
        fname_field = (EditText) findViewById(R.id.first_name_field);
        lname_field = (EditText) findViewById(R.id.lastname_field);
        id_field = (EditText) findViewById(R.id.id_field);
        email_field = (EditText) findViewById(R.id.email_field);
        pwd_field = (EditText) findViewById(R.id.password_field);

        final String email = email_field.getText().toString();
        final String pwd = pwd_field.getText().toString();
        final String lname = lname_field.getText().toString();
        final String fname = fname_field.getText().toString();
        final String id = id_field.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String urlForID = "http://www.jamesgtang.com/tamas/registerStudent.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                register_feedback.setText(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("STUDENT_ID", id);
                params.put("FNAME", fname);
                params.put("LNAME", lname);
                params.put("STATUS", "UGRAD");
                params.put("PASSWORD", pwd);
                params.put("EMAIL", email);
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
