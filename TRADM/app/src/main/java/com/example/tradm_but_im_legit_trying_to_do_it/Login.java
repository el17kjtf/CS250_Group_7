package com.example.tradm_but_im_legit_trying_to_do_it;


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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText emailaddr, password;
    Button btnSignIn;
    FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        configureCreateAccount();
        mFireBaseAuth = FirebaseAuth.getInstance();
        emailaddr = findViewById(R.id.email_addr);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.create_acc);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFireBaseUser = mFireBaseAuth.getCurrentUser();
                if ( mFireBaseUser != null) {
                    Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, Home.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Login.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailaddr.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()) {
                    emailaddr.setError("Please enter Email");
                    emailaddr.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter Password");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(Login.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFireBaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login.this, "Login Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Login.this, Home.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(Login.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFireBaseAuth.addAuthStateListener((mAuthStateListener));
    }

    private void configureCreateAccount() {
        TextView login = (TextView) findViewById(R.id.sign_in);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, CreateAccount.class));
            }
        });

    }
}
