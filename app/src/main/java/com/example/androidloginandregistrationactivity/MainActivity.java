package com.example.androidloginandregistrationactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText uName;
    private EditText Password;
    private TextView info;
    private Button Login;
    private Button reg;
    private CheckBox remember;
    private int counter = 5;

    boolean isValid = false;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedpreferenceseditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uName = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        info = findViewById(R.id.textView);
        Login = findViewById(R.id.btn_login);
        reg=findViewById(R.id.bt_reg);
        remember=findViewById(R.id.checkBox);

        sharedPreferences=getApplicationContext().getSharedPreferences("CredentialsDB",MODE_PRIVATE);
        sharedpreferenceseditor=sharedPreferences.edit();
        if(sharedPreferences !=null){
            String savedUsername =sharedPreferences.getString("Username","");
            String savedPassword =sharedPreferences.getString("Password","");

            RegistrationActivity.credentials=new loginInfo(savedUsername,savedPassword);

            if(sharedPreferences.getBoolean("RememberMeCheckbox",false)){
               uName.setText(savedUsername);
               Password.setText(savedPassword);
               remember.setChecked(true);
            }
        }
        remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferenceseditor.putBoolean("RememberMeCheckbox",remember.isChecked());
                sharedpreferenceseditor.apply();
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uName.getText().toString().isEmpty() || Password.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();
                }else {
                    isValid = validate(uName.getText().toString(), Password.getText().toString());
                    if (!isValid) {
                        counter--;
                        info.setText("Attempts Remaining: " + String.valueOf(counter));
                        if (counter == 0) {
                            Login.setEnabled(false);
                            Toast.makeText(MainActivity.this, "You have used all your attempts try again later!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "try again!", Toast.LENGTH_LONG).show();

                        }
                    }
                    else {
                        startActivity(new Intent(MainActivity.this, New_Activity.class));
                        Toast.makeText(MainActivity.this,"Login Sucess",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private boolean validate(String userName, String userPassword) {
        if (RegistrationActivity.credentials != null) {

            if (userName.equals(RegistrationActivity.credentials.getName()) && userPassword.equals(RegistrationActivity.credentials.getPassword())) {
                return true;
            }
        }

            return false;
        }

}