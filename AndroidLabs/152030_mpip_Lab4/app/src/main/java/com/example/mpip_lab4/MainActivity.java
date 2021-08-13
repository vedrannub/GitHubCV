package com.example.mpip_lab4;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnFirst;

    private boolean checkLoggedUser(){
       return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnFirst = (Button) findViewById(R.id.buttonSignout);


        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                navigateUser();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!checkLoggedUser()){
            navigateUser();
        }
    }

    private void navigateUser(){
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        finish();
    }
}