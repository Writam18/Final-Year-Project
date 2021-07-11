package com.example.scratches.rooms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.scratches.R;
import com.example.scratches.Working;

public class Room4 extends AppCompatActivity {

    Working r4=new Working(this);

    private Switch s4[]=new Switch[5];
    ImageView backbtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room4);

        backbtn4 = findViewById(R.id.imageView34);
        backbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Room4.super.onBackPressed();
            }
        });

       s4[0] = findViewById(R.id.s1r4);
        s4[1] = findViewById(R.id.s2r4);
        s4[2] = findViewById(R.id.s3r4);
        s4[3] = findViewById(R.id.s4r4);
        s4[4] = findViewById(R.id.s5r4);


        SharedPreferences sp4 = getSharedPreferences("save", MODE_PRIVATE);

        for ( int i = 0; i < s4.length; i++) {

            final String str="value4"+Integer.valueOf(i).toString();
            s4[i].setChecked(sp4.getBoolean(str, false));
            final int finalI = i;
            s4[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (s4[finalI].isChecked()) {
                        SharedPreferences.Editor editor4 = getSharedPreferences("save", MODE_PRIVATE).edit();
                        editor4.putBoolean(str, true);
                        editor4.apply();
                        s4[finalI].setChecked(true);
                        r4.values(4,finalI+1,1);

                    } else {
                        SharedPreferences.Editor editor4 = getSharedPreferences("save", MODE_PRIVATE).edit();
                        editor4.putBoolean(str, false);
                        editor4.apply();
                        s4[finalI].setChecked(false);
                        r4.values(4,finalI+1,0);
                    }
                }
            });

        }

    }}

