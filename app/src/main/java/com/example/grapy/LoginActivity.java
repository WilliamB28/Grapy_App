package com.example.grapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegis;
    EditText lg_email,lg_password;
    FloatingActionButton btnLogin;

    private FirebaseAuth mAuth;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegis = findViewById(R.id.btn_regis);
        btnRegis.setOnClickListener(this);

        btnLogin = findViewById(R.id.login);
        btnLogin.setOnClickListener(this);

        lg_email = findViewById(R.id.lg_email);
        lg_password = findViewById(R.id.lg_password);

        loading = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_regis:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login:
                userLogin();
        }
    }

    private void userLogin() {
        String email = lg_email.getText().toString().trim();
        String password = lg_password.getText().toString().trim();

        if (email.isEmpty()){
            lg_email.setError("Email is required");
            lg_email.requestFocus();
            return;
        }

        if (password.isEmpty()){
            lg_password.setError("Password is required");
            lg_password.requestFocus();
            return;
        }

        if (password.length() < 6){
            lg_password.setError("Min password is 6 character");
            lg_password.requestFocus();
            return;
        }

        loading.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Log In Successful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }else {
                    Toast.makeText(LoginActivity.this,"Failed to login! please check your credential", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}