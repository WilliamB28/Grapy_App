package com.example.grapy;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "TAG";
    private FirebaseAuth mAuth;

    Button btnLogin;
    EditText etUsername,etPassword,etGender,edEmail, noHp;
    FloatingActionButton next;
    FirebaseFirestore fStore;
    String userID;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        next = findViewById(R.id.next2);
        next.setOnClickListener(this);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        etUsername = findViewById(R.id.tv_usernamer);
        etPassword = findViewById(R.id.tv_password);
        etGender = findViewById(R.id.ed_gender);
        edEmail = findViewById(R.id.tv_email);
        noHp = findViewById(R.id.nohp);
        progressBar = findViewById(R.id.progressbarR);



    }
    

    @Override
    public void onClick(View v) {
        switch (v.getId()){
         case R.id.btn_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;

                case R.id.next2:
                    registeruser();
                    break;
        }
    }

    private void registeruser() {
        String email = edEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String gender = etGender.getText().toString().trim();
        String noHP = noHp.getText().toString().trim();

        if (username.isEmpty()){
            etUsername.setError("fullname is required");
            etUsername.requestFocus();
            return;
        }

        if (gender.isEmpty()){
            etGender.setError("gender is required");
            etGender.requestFocus();
            return;
        }

        if (!gender.equals("Male") && !gender.equals("Female")){
            etGender.setError("choose between Male or Female");
            etGender.requestFocus();
            return;
        }

        if (noHP.isEmpty()){
            noHp.setError("Phone No is required");
            noHp.requestFocus();
            return;
        }

        if (email.isEmpty()){
            edEmail.setError("email is required");
            edEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edEmail.setError("please provide valid email");
            edEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            etPassword.setError("password is required");
            etPassword.requestFocus();
            return;
        }


        if (password.length() < 6){
            etPassword.setError("Min passeord 6 character!");
            etPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User has been registered successfull",Toast.LENGTH_LONG).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("userame",username);
                            user.put("gender",gender);
                            user.put("phone no",noHP);
                            user.put("email",email);
                            user.put("password",password);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user profile is created"+ userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));


                        } else {
                            Toast.makeText(RegisterActivity.this,"Failed to register!, Try again!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }
}