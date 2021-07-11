package com.example.scratches;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityLogin extends AppCompatActivity {

   MaterialButton callsignup, login_btn;
    MaterialCheckBox remember_me;
    private FirebaseAuth mAuth;
    TextInputLayout username, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);
        setContentView(R.layout.activity_main_login);

        mAuth = FirebaseAuth.getInstance();

        callsignup = findViewById(R.id.signup);
        login_btn = findViewById(R.id.login_button);
        remember_me = findViewById(R.id.remember_me);
        username = findViewById(R.id.emailid);
        pass = findViewById(R.id.pass);

       SharedPreferences preferences=getSharedPreferences("remember",MODE_PRIVATE);

       String checkbox=((SharedPreferences) preferences).getString("remember","");
       if(checkbox.equals("true"))
       {
           Intent intent=new Intent(MainActivityLogin.this,MainActivity.class);
           startActivity(intent);
           finish();//error
       }
       else
       {
           Toast.makeText(this,"Please Sign in",Toast.LENGTH_SHORT).show();
       }




        callsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityLogin.this, SignUp.class);
                startActivity(intent);
            }
        });

        remember_me.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked())
                {
                    SharedPreferences preferences=getSharedPreferences("remember",MODE_PRIVATE);

                    SharedPreferences.Editor editor =preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                }
                else
                {
                    SharedPreferences preferences=getSharedPreferences("remember",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                }
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });


    }


    public void loginUser() {
        String email, password;
        email = username.getEditText().getText().toString().trim();
        password = pass.getEditText().getText().toString().trim();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            username.setError("Field cannot be empty");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            pass.setError("Field cannot be empty");
            return;
        }

        // signin existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {


                                    Toast.makeText(getApplicationContext(),
                                            "Login successful!!",
                                            Toast.LENGTH_LONG)
                                            .show();
                                    // if sign-in is successful intent to home activity
                                    Intent intent
                                            = new Intent(MainActivityLogin.this,
                                            MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {

                                    // sign-in failed

                                    Toast.makeText(getApplicationContext(),
                                            "Login failed!!",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                    String s = "Sign up Failed" + task.getException();
                                    Toast.makeText(MainActivityLogin.this, s,
                                            Toast.LENGTH_LONG).show();
                                    //finish();

                                    // hide the progress bar

                                }
                            }
                        });
    }}

