package com.example.mpip_lab4;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etMail,etPassword;
    private Button btnLogin,btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivty);

        mAuth = FirebaseAuth.getInstance();

        etMail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = etMail.getText().toString();
                String Password = etPassword.getText().toString();

                if(Email.isEmpty() || Password.isEmpty()){
                    return;
                }


                login(Email,Password);

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = etMail.getText().toString();
                String Password = etPassword.getText().toString();

                if(Email.isEmpty() || Password.isEmpty()){
                    return;
                }

                register(Email,Password);

            }
        });
    }

    private void register(String email , String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Registration done", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Auth failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void login(String email , String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            navigateLoggedInUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }});
    };




    @Override
    protected void onStart() {
        super.onStart();
        if(checkLoggedUser()){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
    }



    private boolean checkLoggedUser(){
        return mAuth.getCurrentUser() != null;
    }

    private void navigateLoggedInUser(){
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

}