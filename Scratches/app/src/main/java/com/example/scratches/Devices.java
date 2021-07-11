package com.example.scratches;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class Devices extends AppCompatActivity {

    ImageView backbtn_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        backbtn_d=findViewById(R.id.back_pressed_devices);
        backbtn_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Devices.super.onBackPressed();
            }
        });

    }
}