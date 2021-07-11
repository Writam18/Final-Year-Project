package com.example.scratches.rooms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.scratches.R;
import com.example.scratches.Working;

public class Room1 extends AppCompatActivity {
    Working r1=new Working(this);

    private Switch s1[]=new Switch[5];
    ImageView backbtn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        backbtn1 = findViewById(R.id.imageView31);
        backbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Room1.super.onBackPressed();
            }
        });





        s1[0] = findViewById(R.id.s1r1);
        s1[1] = findViewById(R.id.s2r1);
        s1[2] = findViewById(R.id.s3r1);
        s1[3] = findViewById(R.id.s4r1);
        s1[4] = findViewById(R.id.s5r1);


        SharedPreferences sp1 = getSharedPreferences("save", MODE_PRIVATE);

        for ( int i = 0; i < s1.length; i++) {

            final String str="value1"+Integer.valueOf(i).toString();
            s1[i].setChecked(((SharedPreferences) sp1).getBoolean(str, false));
            final int finalI = i;
            s1[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (s1[finalI].isChecked()) {
                        SharedPreferences.Editor editor1 = getSharedPreferences("save", MODE_PRIVATE).edit();
                        editor1.putBoolean(str, true);
                        editor1.apply();
                        s1[finalI].setChecked(true);
                        r1.values(1,finalI+1,1);

                    } else {
                        SharedPreferences.Editor editor1 = getSharedPreferences("save", MODE_PRIVATE).edit();
                        editor1.putBoolean(str, false);
                        editor1.apply();
                        s1[finalI].setChecked(false);
                        r1.values(1,finalI+1,0);
                    }
                }
            });

        }


    }
}