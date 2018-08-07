package com.poppin.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.poppin.movies.exibefilme.ExibeFilmeActivity;

public class IntroActivity extends AppCompatActivity {
    Thread splashTread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        startAnimation();

    }
    public void startAnimation() {


        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;

                    //

                    while (waited < 2000) {

                        sleep(100);
                        waited += 100;


                    }
                    startActivity(new Intent(IntroActivity.this, ExibeFilmeActivity.class));

                } catch (Exception e) {

                }


            }
        };
        splashTread.start();
    }
}
