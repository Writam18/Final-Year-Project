package com.example.scratches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.scratches.rooms.Room1;
import com.example.scratches.rooms.Room2;
import com.example.scratches.rooms.Room3;
import com.example.scratches.rooms.Room4;

public class activity_room extends AppCompatActivity {


    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        backbtn = findViewById(R.id.back_pressed);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_room.super.onBackPressed();
            }
        });
    }

    public void room1(View view) {
        Intent intent = new Intent(activity_room.this, Room1.class);
        startActivity(intent);

    }

    public void room2(View view) {
        Intent intent = new Intent(activity_room.this, Room2.class);
        startActivity(intent);
    }

    public void room3(View view) {
        Intent intent = new Intent(activity_room.this, Room3.class);
        startActivity(intent);
    }

    public void room4(View view) {
        Intent intent = new Intent(activity_room.this, Room4.class);
        startActivity(intent);
    }
}