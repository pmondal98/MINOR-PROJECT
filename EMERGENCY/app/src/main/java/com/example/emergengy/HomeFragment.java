package com.example.emergengy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    Fragment selectedFragment=null;


    RadioGroup rgcategory,rgcondition;
    RadioButton radioButtonCondition,radioButtonCategory;
    Button btnnext;

    String condition,category;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home,container,false);

        btnnext=root.findViewById(R.id.btnnext);
        rgcategory = root.findViewById(R.id.rgcategory);
        rgcondition = root.findViewById(R.id.rgcondition);


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedCondition = rgcondition.getCheckedRadioButtonId();
                int selectedCategory = rgcategory.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButtonCondition = root.findViewById(selectedCondition);
                condition= (String)radioButtonCondition.getText();

                // find the radiobutton by returned id
                radioButtonCategory = root.findViewById(selectedCategory);
                category= (String)radioButtonCategory.getText();

               Intent intent=new Intent(getContext(),HospitalActivity.class);
               intent.putExtra("Condition",condition);
               intent.putExtra("Category",category);
                Toast.makeText(getContext(), condition+"\n"+category, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        return root;
}}
