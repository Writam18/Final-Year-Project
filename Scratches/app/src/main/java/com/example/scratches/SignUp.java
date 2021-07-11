package com.example.scratches;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;
     private FirebaseAuth mAuth;
     FirebaseDatabase rootnode;
     DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Hooks
        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhoneNo = findViewById(R.id.phnno);
        regPassword = findViewById(R.id.password);
        regBtn = findViewById(R.id.regBtn);
        regToLoginBtn = findViewById(R.id.regToLoginbtn);

        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivityLogin.class);
                startActivity(intent);
            }
        });


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode=FirebaseDatabase.getInstance();
                ref=rootnode.getReference("users");
                mAuth = FirebaseAuth.getInstance();
                registerNewUser();
            }
        });
    }

    public Boolean validateName() {
        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);//remove the error which was generated first time
            regName.setErrorEnabled(false);//removes the space that came while displaying error
            return true;
        }
    }

    private void registerNewUser()
    {
        //Get all the values from text fields

        if(!validateName())
        {
            return;
        }

        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phnno = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        UserHelper helper=new UserHelper(name,username,email,phnno,password);
        ref.child(email).setValue(helper);

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                                    .show();



                           // if the user created intent to login activity
                            Intent intent
                                    = new Intent(SignUp.this,
                                   MainActivityLogin.class);
                            startActivity(intent);
                            finish();
                        } else {

                            // Registration failed
                           if(task.getException() instanceof FirebaseAuthUserCollisionException) {
                               Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_LONG).show();

                               String s = "Sign up Failed" + task.getException();
                               Toast.makeText(SignUp.this, s,
                                       Toast.LENGTH_SHORT).show();
                           }

                        }
                    }
                });
    }
}


// [END create_user_with_email]

