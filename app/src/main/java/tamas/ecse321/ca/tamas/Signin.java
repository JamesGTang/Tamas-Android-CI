package tamas.ecse321.ca.tamas;

import android.content.Intent;
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

public class Signin extends AppCompatActivity {
    EditText ID;
    EditText password;
    TextView feedback_view;
    Button sign_in_button;
    Button register_button;
    private String user_id;
    private String pass_word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        sign_in_button=(Button)findViewById(R.id.signin_button);
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin(v);
            }
        });
        register_button=(Button)findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_register);
                Intent intent=new Intent(Signin.this,Register.class);

                startActivity(intent);
            }
        });

    }
    private void signin(View v) {
        ID = (EditText) findViewById(R.id.id_field);
        password = (EditText) findViewById(R.id.password_field);
        feedback_view = (TextView) findViewById(R.id.signin_feedback);
        feedback_view.setText("Hello! Please enter your id and password");
        RequestQueue queue= Volley.newRequestQueue(this);
        user_id=ID.getText().toString();
        pass_word=password.getText().toString();

        String urlForID="http://www.jamesgtang.com/tamas/authentication.php?"+"user_id="+
                user_id+"&user_password="+pass_word;
        StringRequest stringRequest=new StringRequest(Request.Method.GET,urlForID,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                // Result handlings
                feedback_view.setText(response);
                if(response.equals("Success")){
                    setContentView(R.layout.activity_view_job);
                    Intent intent=new Intent(Signin.this,view_job.class);
                    intent.putExtra("id",user_id);
                    intent.putExtra("password",pass_word);
                    startActivity(intent);
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
    }
}
