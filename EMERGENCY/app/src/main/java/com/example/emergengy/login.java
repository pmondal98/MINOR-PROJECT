package com.example.emergengy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText etemail,etpassword;
    Button btnlogin;
    TextView tvregister;
    FirebaseAuth mfirebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etemail=findViewById(R.id.etemail);
        etpassword=findViewById(R.id.etpassword);
        btnlogin=findViewById(R.id.btnlogin);
        tvregister=findViewById(R.id.tvregister);

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
                                Toast.makeText(login.this, "Login Successful....", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(login.this,HomeActivity.class));
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
    }
}
