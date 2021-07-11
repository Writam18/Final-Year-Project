package com.example.scratches.rooms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.scratches.R;
import com.example.scratches.Working;

public class Room3 extends AppCompatActivity {

    Working r3=new Working(this);

    private Switch s3[] = new Switch[5];
    ImageView backbtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3);
        backbtn3 = findViewById(R.id.imageView33);
        backbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Room3.super.onBackPressed();
            }
        });


        s3[0] = findViewById(R.id.s1r3);
        s3[1] = findViewById(R.id.s2r3);
        s3[2] = findViewById(R.id.s3r3);
        s3[3] = findViewById(R.id.s4r3);
        s3[4] = findViewById(R.id.s5r3);


        SharedPreferences sp3 = getSharedPreferences("save", MODE_PRIVATE);

        for (int i = 0; i < s3.length; i++) {

            final String str = "value3" + Integer.valueOf(i).toString();
            s3[i].setChecked(sp3.getBoolean(str, false));
            final int finalI = i;
            s3[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (s3[finalI].isChecked()) {
                        SharedPreferences.Editor editor3 = getSharedPreferences("save", MODE_PRIVATE).edit();
                        editor3.putBoolean(str, true);
                        editor3.apply();
                        s3[finalI].setChecked(true);
                        r3.values(3,finalI+1,1);

                    } else {
                        SharedPreferences.Editor editor3 = getSharedPreferences("save", MODE_PRIVATE).edit();
                        editor3.putBoolean(str, false);
                        editor3.apply();
                        s3[finalI].setChecked(false);
                        r3.values(3,finalI+1,0);
                    }
                }
            });

        }


    }
}