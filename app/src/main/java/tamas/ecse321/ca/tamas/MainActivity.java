package tamas.ecse321.ca.tamas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button toRegisterButton;
    Button toSigninButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toRegisterButton=(Button)findViewById(R.id.register_button);
        toRegisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switchToRegisterView(v);
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
        toSigninButton=(Button)findViewById(R.id.signin_button);
        toSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSigninView(v);
                Intent intent=new Intent(MainActivity.this,Signin.class);
                startActivity(intent);
            }
        });
    }

    public void switchToSigninView(View v){
        setContentView(R.layout.activity_signin);
    }
    public void switchToRegisterView(View v){
        setContentView(R.layout.activity_register);
    }
}
