package com.example.androidloginandregistrationactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText rgusername, rgpassword;
    private Button register;

    public static loginInfo credentials;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedpreferebcesseditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

         rgusername=findViewById(R.id.Regname);
         rgpassword=findViewById(R.id.Regpassword);
         register =findViewById(R.id.btregister);

         sharedPreferences =getApplicationContext().getSharedPreferences("CredentialsDB",MODE_PRIVATE);
         sharedpreferebcesseditor=sharedPreferences.edit();


         register.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String regusername= rgusername.getText().toString();
                 String regpassword= rgpassword.getText().toString();

                 if(validate(regusername,regpassword)){

                     credentials=new loginInfo(regusername,regpassword);

                     sharedpreferebcesseditor.putString("Username",regusername);
                     sharedpreferebcesseditor.putString("Password",regpassword);

                     sharedpreferebcesseditor.apply();

                     startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                     Toast.makeText(RegistrationActivity.this,"Registation Sucessful!",Toast.LENGTH_SHORT).show();

                 }

             }
         });
    }
    private boolean validate(String username, String password){
        if(username.isEmpty() || password.length()<8){
            Toast.makeText(this,"Please enter all details, Password should be atlast 8 characters",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}