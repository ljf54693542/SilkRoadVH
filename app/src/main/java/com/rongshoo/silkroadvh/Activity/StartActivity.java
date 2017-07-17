package com.rongshoo.silkroadvh.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.rongshoo.silkroadvh.MainActivity;
import com.rongshoo.silkroadvh.R;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        //MainActivity LoginActivity
        // final Intent main = new Intent(this, LoginActivity.class); //你要转向的Activity
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // startActivity(main); //执行
                //LoginActivity.StartLoginActivity(StartActivity.this, 0);
                 Intent main = new Intent(StartActivity.this, MainActivity.class); //你要转向的Activity
                 startActivity(main);
                StartActivity.this.finish();
            }

        };
        timer.schedule(task, 1000 * 2); //2秒后
    }
}
