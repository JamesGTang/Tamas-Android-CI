package tamas.ecse321.ca.tamas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import tamas.ecse321.ca.tamas.controller.ApplicationController;


public class Signin extends AppCompatActivity {
    EditText ID;
    EditText password;
    TextView feedback_view;
    Button sign_in_button;
    Button register_button;
    private String user_id;
    private String pass_word;

    ApplicationController applicationController=new ApplicationController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        feedback_view = (TextView) findViewById(R.id.signin_feedback);
        feedback_view.setText("Hello! Please enter your id and password");

        ID = (EditText) findViewById(R.id.id_field);
        password = (EditText) findViewById(R.id.password_field);
        user_id=ID.getText().toString();
        pass_word=password.getText().toString();

        sign_in_button=(Button)findViewById(R.id.signin_button);
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response=applicationController.authenticateStudent(user_id,pass_word);
                feedback_view.setText(response);
                if(response.equals("Success")){
                    setContentView(R.layout.activity_view_job);
                    Intent intent=new Intent(Signin.this,view_job.class);
                    intent.putExtra("id",user_id);
                    intent.putExtra("password",pass_word);
                    startActivity(intent);
                }
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

}
