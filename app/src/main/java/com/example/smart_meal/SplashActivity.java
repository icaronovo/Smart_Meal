package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout file for this activity
        setContentView(R.layout.activity_splash);

        // Find the ImageView in the layout and start the rotation animation
        ImageView imageView = findViewById(R.id.imgRotate);
        imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotation));

        // Create a TimerTask to finish this activity and start the main activity after 1 second
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                finish(); // Finish this activity
                startActivity(new Intent(SplashActivity.this, MainActivity.class)); // Start the main activity
            }

        };
        Timer opening = new Timer();
        opening.schedule(task, 3000); // Schedule the task to run after 1 second
    }
}
