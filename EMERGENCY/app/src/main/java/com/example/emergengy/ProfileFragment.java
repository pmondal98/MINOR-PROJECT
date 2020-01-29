package com.example.emergengy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment{

    private TextView textname,textphone,textemail,textpin,textage,textaddress;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_profile,container,false);

        textname=root.findViewById(R.id.textname);
        textage=root.findViewById(R.id.textage);
        textemail=root.findViewById(R.id.textemail);
        textpin=root.findViewById(R.id.textpin);
        textphone=root.findViewById(R.id.textphone);
        textaddress=root.findViewById(R.id.textaddress);

        textemail.setMovementMethod(new ScrollingMovementMethod());
        textaddress.setMovementMethod(new ScrollingMovementMethod());

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("USERS").child(firebaseAuth.getCurrentUser().getUid());;

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Member u=dataSnapshot.getValue(Member.class);
                textname.setText(u.getName());
                textemail.setText(u.getEmail());
                textage.setText(u.getAge());
                textaddress.setText(u.getAddress());
                textpin.setText(u.getPin());
                textphone.setText(u.getContactnumber());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }
}
