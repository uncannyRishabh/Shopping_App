package com.cse.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout name,email,password;
    private Button signup;
    private RelativeLayout parent;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_ShoppingApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.signup_name_tf);
        email = findViewById(R.id.signup_mail_tf);
        password = findViewById(R.id.signup_pass_tf);
        signup = findViewById(R.id.signup_btn);
        parent = findViewById(R.id.signup_parent);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate_fields()){
                    signup_user();
                }
            }
        });

    }

    private boolean validate_fields() {
        if(!Objects.equals(email.getEditText().getText().toString(),"") &&
                !Objects.equals(password.getEditText().getText().toString(),"") &&
                !Objects.equals(name.getEditText().getText().toString(),"")){
            if(password.getEditText().getText().toString().length()>8){
                return true;
            }
            else{
                Snackbar.make(parent,"Password too short",Snackbar.LENGTH_LONG).show();
                return false;
            }
        }
        else {
            Snackbar.make(parent,"Name, Email or Password cannot be empty",Snackbar.LENGTH_LONG).show();
            return false;
        }
    }

    private void signup_user() {
        String mail = email.getEditText().getText().toString().trim();
        String pass = password.getEditText().getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("DEBUG : ", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            uid = user.getUid();
                            updateUI(user);
                        } else {
                            Log.w("DEBUG : ", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user!=null){
            Intent i1 = new Intent(RegisterActivity.this, DashboardActivity.class);
            startActivity(i1);
        }
    }
}