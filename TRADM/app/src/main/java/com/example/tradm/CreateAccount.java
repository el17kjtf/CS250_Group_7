package com.example.tradm;


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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity {
    EditText emailaddr, password, stud_ID;
    Button btnSignIn;
    FirebaseAuth mFireBaseAuth;
    DatabaseReference reff;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        configureLogin();
        mFireBaseAuth = FirebaseAuth.getInstance();
        emailaddr = findViewById(R.id.email_addr);
        password = findViewById(R.id.password);
        stud_ID = findViewById(R.id.stud_id);
        member = new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        btnSignIn = findViewById(R.id.create_acc);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailaddr.getText().toString();
                String pwd = password.getText().toString();
                String ID = stud_ID.getText().toString();
                if (email.isEmpty()) {
                    emailaddr.setError("Please enter Email");
                    emailaddr.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter Password");
                    password.requestFocus();
                } else if (ID.isEmpty()) {
                    stud_ID.setError("Please enter ID");
                    stud_ID.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty() && ID.isEmpty()) {
                    Toast.makeText(CreateAccount.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty() && ID.isEmpty())) {
                    mFireBaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(CreateAccount.this, "Sign Up Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(CreateAccount.this, Home.class));
                                member.setID(stud_ID.getText().toString());
                                member.setEmail(emailaddr.getText().toString());
                                reff.push().setValue(member);
                            }
                        }
                    });
                } else {
                    Toast.makeText(CreateAccount.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void configureLogin() {
        TextView login = (TextView) findViewById(R.id.sign_in);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAccount.this, Login.class));
            }
        });
    }
}
