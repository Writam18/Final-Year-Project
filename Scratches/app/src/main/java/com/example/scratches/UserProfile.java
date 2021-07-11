package com.example.scratches;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

     FirebaseUser user;
    DatabaseReference reference;
     String userId;//from databse
    private MaterialButton logout;
     TextInputLayout emailTextView,fullnameTextView,phnnoTextView;
    TextView usernameTextView_label,fullnameTextView_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        logout=findViewById(R.id.logout_profile);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserProfile.this,MainActivityLogin.class));
            }
        });
        user=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userId=user.getUid();
        usernameTextView_label=findViewById(R.id.username_profilelabel);
        fullnameTextView_label=findViewById(R.id.fullname_profilelabel);

        if(user!=null)
        {
            usernameTextView_label.setText(user.getDisplayName());
            System.out.println(usernameTextView_label);
        }




        System.out.println("USERID"+userId);
        emailTextView=findViewById(R.id.email_profile);
        fullnameTextView=findViewById(R.id.fullname_profile);
        phnnoTextView=findViewById(R.id.phnno_profile);
        usernameTextView_label=findViewById(R.id.username_profilelabel);
        fullnameTextView_label=findViewById(R.id.fullname_profilelabel);


        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
          
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelper userHelper = null;
                for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                     userHelper = childSnapshot.getValue(UserHelper.class);
                }
             //   UserHelper userHelper=snapshot.getValue(UserHelper.class);
                System.out.println(userHelper);
                if(userHelper!=null)
                {
                    //Rettrieving current users data
                    String fullname=userHelper.getName();
                    String username=userHelper.getUsername();
                    String email=userHelper.getEmail();
                    String phnno=userHelper.getPhnno();

                    System.out.println(fullname+" "+" "+username+" "+email+" "+phnno);

                    //Setting the fields value in USER PROFILE
                    fullnameTextView_label.setText(fullname);
                   usernameTextView_label.setText(username);
                   fullnameTextView.setTag(fullname);
                   emailTextView.setTag(email);
                   phnnoTextView.setTag(phnno);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfile.this,"Something Wrong hapenened",Toast.LENGTH_LONG).show();
            }
        });
    }
}