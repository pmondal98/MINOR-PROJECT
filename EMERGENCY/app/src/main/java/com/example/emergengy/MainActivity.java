package com.example.emergengy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etname,etemail,etpassword,etconfirmpassword,etage,etaddress,etpin,etcontactnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname=findViewById(R.id.etname);
        etemail=findViewById(R.id.etemail);


    }
}
