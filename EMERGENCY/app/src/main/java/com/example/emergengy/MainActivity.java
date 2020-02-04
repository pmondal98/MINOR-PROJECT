package com.example.emergengy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText inputname,inputemail,inputpassword,inputage,inputaddress,inputpin,inputcontactnumber;
    private Button btnsubmit;

    DatabaseReference ref;
    FirebaseAuth mfirebaseauth;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT>=21)
        {
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

        inputname=findViewById(R.id.inputname);
        inputemail=findViewById(R.id.inputemail);
        inputpassword=findViewById(R.id.inputpassword);
        inputage=findViewById(R.id.inputage);
        inputaddress=findViewById(R.id.inputaddress);
        inputpin=findViewById(R.id.inputpin);
        inputcontactnumber=findViewById(R.id.inputcontactnumber);
        btnsubmit=findViewById(R.id.btnsubmit);

        mfirebaseauth = FirebaseAuth.getInstance();
        member=new Member();
//        ref= FirebaseDatabase.getInstance().getReference().child("USERS");


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputname.getText().toString().trim().length()==0)
                    inputname.setError("Name is required");
                else if (inputemail.getText().toString().trim().length()==0)
                    inputemail.setError("Email is required");
                else if (inputpassword.getText().toString().trim().length()==0)
                    inputpassword.setError("Password is required");
                else if(inputage.getText().toString().trim().length()==0)
                    inputage.setError("Age is required");
                else if (inputaddress.getText().toString().trim().length()==0)
                    inputaddress.setError("Address is required");
                else if (inputpin.getText().toString().trim().length()==0)
                    inputpin.setError("Pic Code of your area is required");
                else if (inputcontactnumber.getText().toString().trim().length()==0)
                    inputcontactnumber.setError("Phone number is required");
                else {
                    CreateUserAndSaveData();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

        finish();
        startActivity(new Intent(MainActivity.this,login.class));
    }

    private void CreateUserAndSaveData() {
        mfirebaseauth.createUserWithEmailAndPassword(inputemail.getText().toString(),inputpassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    saveData();
                    Toast.makeText(MainActivity.this, "User Creation.....Successfull", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FirebaseAuthException e = (FirebaseAuthException)task.getException();
                    Log.i("ERROR",e.getMessage());
                    Toast.makeText(MainActivity.this, "User Creation Failed....Please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveData()
    {

        member.setName(inputname.getText().toString().trim());
        member.setEmail(inputemail.getText().toString().trim());
        member.setPassword(inputpassword.getText().toString().trim());
        member.setAge(inputage.getText().toString().trim());
        member.setAddress(inputaddress.getText().toString().trim());
        member.setPin(inputpin.getText().toString().trim());
        member.setContactnumber(inputcontactnumber.getText().toString().trim());

        ref= FirebaseDatabase.getInstance().getReference().child("USERS").child(mfirebaseauth.getCurrentUser().getUid());

        ref.setValue(member);

        Toast.makeText(this, "Registration Successful!!!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,login.class));

        inputname.setText(null);
        inputemail.setText(null);
        inputpassword.setText(null);
        inputage.setText(null);
        inputaddress.setText(null);
        inputpin.setText(null);
        inputcontactnumber.setText(null);
    }
}
