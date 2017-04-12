package tamas.ecse321.ca.tamas;

import android.content.Intent;;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import tamas.ecse321.ca.tamas.controller.ApplicantController;

public class Register extends AppCompatActivity {

    private EditText id_field;
    private EditText fname_field;
    private EditText lname_field;
    private EditText pwd_field;
    private EditText email_field;
    private TextView register_feedback;
    private RadioButton ugradRadioButton;
    private RadioButton gradRadioButton;
    private RadioGroup statusGroup;
    private RadioButton checkedRadioButtton;

    Button register_button;
    Button sign_in_button;

    ApplicantController applicantController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_feedback = (TextView) findViewById(R.id.register_feedback);
        fname_field = (EditText) findViewById(R.id.first_name_field);
        lname_field = (EditText) findViewById(R.id.lastname_field);
        id_field = (EditText) findViewById(R.id.id_field);
        email_field = (EditText) findViewById(R.id.email_field);
        pwd_field = (EditText) findViewById(R.id.password_field);
        ugradRadioButton=(RadioButton)findViewById(R.id.ugrad);
        gradRadioButton=(RadioButton)findViewById(R.id.grad);

        statusGroup=(RadioGroup)findViewById(R.id.radiogroup);
        statusGroup.check(R.id.ugrad);
        checkedRadioButtton=(RadioButton)statusGroup.findViewById(statusGroup.getCheckedRadioButtonId());

        final String email = email_field.getText().toString();
        final String pwd = pwd_field.getText().toString();
        final String lname = lname_field.getText().toString();
        final String fname = fname_field.getText().toString();
        final String id = id_field.getText().toString();
        final String status=checkedRadioButtton.getText().toString();

        register_button=(Button)findViewById(R.id.register_student_button);
        register_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                applicantController=new ApplicantController();
                applicantController=new ApplicantController(fname,lname,email,pwd,id,status);
                String feedback=applicantController.vertifyApplicantInformation();
                feedback="Verified!";
                if(feedback.equals("Verified!")){
                    applicantController.addApplicant();
                    feedback=applicantController.registerApplicant();
                        if(feedback.equals("Success")){
                            register_feedback.setText("Registration Success! Please return to signin");
                        }else if(feedback.equals("Failed")){
                            register_feedback.setText("You have registered already, please sign in!");
                        }else{
                            register_feedback.setText("Check your network connectivity!");
                        }

                }else{
                    register_feedback.setText(feedback);
                }

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



}
