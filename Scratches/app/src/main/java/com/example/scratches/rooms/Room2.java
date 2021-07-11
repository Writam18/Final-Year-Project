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


public class Room2 extends AppCompatActivity  {
    private Switch s2[]=new Switch[5];
    ImageView backbtn2;

    Working r2=new Working(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        backbtn2 = findViewById(R.id.imageView32);
        backbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Room2.super.onBackPressed();
            }
        });


        s2[0] = findViewById(R.id.s1r2);
          s2[1] = findViewById(R.id.s2r2);
        s2[2] = findViewById(R.id.s3r2);
        s2[3] = findViewById(R.id.s4r2);
        s2[4] = findViewById(R.id.s5r2);


        SharedPreferences sp2 = getSharedPreferences("save", MODE_PRIVATE);

        for ( int i = 0; i < s2.length; i++) {

            final String str="value2"+Integer.valueOf(i).toString();
            s2[i].setChecked(sp2.getBoolean(str, false));
            final int finalI = i;
            s2[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (s2[finalI].isChecked()) {
                        SharedPreferences.Editor editor2 = getSharedPreferences("save", MODE_PRIVATE).edit();
                        editor2.putBoolean(str, true);
                        editor2.apply();
                        s2[finalI].setChecked(true);
                        r2.values(2,finalI+1,1);

                    } else {
                        SharedPreferences.Editor editor2 = getSharedPreferences("save", MODE_PRIVATE).edit();
                        editor2.putBoolean(str, false);
                        editor2.apply();
                        s2[finalI].setChecked(false);
                        r2.values(2,finalI+1,0);
                    }
                }
            });

        }


    }
}