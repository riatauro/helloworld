package com.user.phaseshift;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;
    ImageView img;
    AnimationDrawable animation;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        img=(ImageView)findViewById(R.id.im1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
//       // Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ltor);
//        img.setAnimation(rotate);
//        btn1.setAnimation(rotate);
//        btn2.setAnimation(rotate);
//        btn3.setAnimation(rotate);
//        btn4.setAnimation(rotate);

//        TranslateAnimation animation = new TranslateAnimation(0.0f, 400.0f,
//                0.0f, 0.0f);
//        animation.setDuration(5000);
//        animation.setRepeatCount(5);
//        animation.setRepeatMode(2);
//        animation.setFillAfter(true);
//        img.startAnimation(animation);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent i = new Intent(getApplicationContext(), Events.class);
                startActivity(i);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent i=new Intent(getApplicationContext(),About.class);
                startActivity(i);
            }
        });

        //btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent i=new Intent(getApplicationContext(),appTeam.class);
                startActivity(i);
            }
        });

       // btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent i=new Intent(getApplicationContext(),reach.class);
                startActivity(i);
            }
        });
    }
}

