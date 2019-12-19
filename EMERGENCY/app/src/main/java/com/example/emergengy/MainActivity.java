package com.example.emergengy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText etname,etemail,etpassword,etage,etaddress,etpin,etcontactnumber;
    private Button btnsubmit;

    DatabaseReference ref;
    FirebaseAuth mfirebaseauth;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname=findViewById(R.id.etname);
        etemail=findViewById(R.id.etemail);
        etpassword=findViewById(R.id.etpassword);
        etage=findViewById(R.id.etage);
        etaddress=findViewById(R.id.etaddress);
        etpin=findViewById(R.id.etpin);
        etcontactnumber=findViewById(R.id.etcontactnumber);
        btnsubmit=findViewById(R.id.btnsubmit);

        mfirebaseauth = FirebaseAuth.getInstance();
        member=new Member();
//        ref= FirebaseDatabase.getInstance().getReference().child("USERS");


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etname.getText().toString().trim().length()==0)
                    etname.setError("Name is required");
                else if (etemail.getText().toString().trim().length()==0)
                    etemail.setError("Email is required");
                else if (etpassword.getText().toString().trim().length()==0)
                    etpassword.setError("Password is required");
                else if(etage.getText().toString().trim().length()==0)
                    etage.setError("Age is required");
                else if (etaddress.getText().toString().trim().length()==0)
                    etaddress.setError("Address is required");
                else if (etpin.getText().toString().trim().length()==0)
                    etpin.setError("Pic Code of your area is required");
                else if (etcontactnumber.getText().toString().trim().length()==0)
                    etcontactnumber.setError("Phone number is required");
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
        mfirebaseauth.createUserWithEmailAndPassword(etemail.getText().toString(),etpassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    saveData();
                    Toast.makeText(MainActivity.this, "User Creation.....Sucessfull", Toast.LENGTH_SHORT).show();
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

        member.setName(etname.getText().toString().trim());
        member.setEmail(etemail.getText().toString().trim());
        member.setPassword(etpassword.getText().toString().trim());
        member.setAge(etage.getText().toString().trim());
        member.setAddress(etaddress.getText().toString().trim());
        member.setPin(etpin.getText().toString().trim());
        member.setContactnumber(etcontactnumber.getText().toString().trim());

        ref= FirebaseDatabase.getInstance().getReference().child("USERS").child(mfirebaseauth.getCurrentUser().getUid());

        ref.setValue(member);

        Toast.makeText(this, "Registration Successful!!!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,login.class));

        etname.setText(null);
        etemail.setText(null);
        etemail.setText(null);
        etpassword.setText(null);
        etage.setText(null);
        etaddress.setText(null);
        etpin.setText(null);
        etcontactnumber.setText(null);
    }
}
