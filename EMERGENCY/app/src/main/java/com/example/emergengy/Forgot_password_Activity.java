package com.example.emergengy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_password_Activity extends AppCompatActivity {
private EditText etforgotemail;
private Button btnsendemail;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_);
        etforgotemail=findViewById(R.id.etforgotemail);
        btnsendemail=findViewById(R.id.btnsendemail);
        firebaseAuth=FirebaseAuth.getInstance();
        btnsendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=etforgotemail.getText().toString().trim();
                if(useremail.equals(""))
                {
                    Toast.makeText(Forgot_password_Activity.this, "Enter your registered Email-id", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Forgot_password_Activity.this, "Password Reset Email send ", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Forgot_password_Activity.this,login.class));
                            }
                            else{
                                Toast.makeText(Forgot_password_Activity.this, "Sorry! Password Reset Email send failed ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });
    }
}
