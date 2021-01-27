package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    float x1Position, y1Position , x2Position , y2Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch (touchEvent.getAction()){
            case MotionEvent.ACTION_UP:
            {
                x1Position = touchEvent.getX();
                y1Position = touchEvent.getY();
                break;
            }
            case MotionEvent.ACTION_DOWN:
            {
                x2Position = touchEvent.getX();
                y2Position = touchEvent.getY();
                break;
            }

        }
        if(y1Position < y2Position){
            intent = new Intent(MainActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }
}
/*
        intent = new Intent(MainActivity.this, MainActivity2.class);
        string = "First book description ";
        intent.putExtra("Value",string);
*/