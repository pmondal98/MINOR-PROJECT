package com.example.emergengy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText etemail,etpassword;
    Button btnlogin;
    TextView tvregister,tvforgotpassword;

    FirebaseAuth mfirebaseauth;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etemail=findViewById(R.id.etemail);
        etpassword=findViewById(R.id.etpassword);
        btnlogin=findViewById(R.id.btnsendemail);
        tvregister=findViewById(R.id.tvregister);
        tvforgotpassword=findViewById(R.id.tvforgotpassword);

        mfirebaseauth = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etemail.getText().toString().trim();
                String password=etpassword.getText().toString().trim();
                if(email.isEmpty())
                {
                    etemail.setError("Please enter email id");
                    etemail.requestFocus();
                }
                else if(password.isEmpty())
                {
                    etpassword.setError("Please enter password");
                    etpassword.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty())
                {
                    Toast.makeText(login.this, "Login failed....please fill up the details", Toast.LENGTH_LONG).show();
                }
                else if(!(email.isEmpty() && password.isEmpty()))
                {
                    mfirebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(login.this, "Login Unsuccessful....please try again later", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                startActivity(new Intent(login.this,CategoryActivity.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(login.this, "Error Occured....please try again later", Toast.LENGTH_LONG).show();
                }
            }
        });

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login.this,MainActivity.class);
                startActivity(i);
            }
        });

        tvforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,Forgot_password_Activity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis())
        {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
        else
        {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime=System.currentTimeMillis();
    }
}
