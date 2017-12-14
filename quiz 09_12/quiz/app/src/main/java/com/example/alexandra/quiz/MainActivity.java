package com.example.alexandra.quiz;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vk.sdk.util.VKUtil;

import java.sql.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Intent serv;
    final String TAG = "myLogs";
    ImageButton playButton;
    ImageButton achivementButton;
    ImageButton setingsButton;

   // public MediaPlayer mySong;
    SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton=(ImageButton) findViewById(R.id.playButton);
        achivementButton=(ImageButton) findViewById(R.id.achivementButton);
        setingsButton=(ImageButton) findViewById(R.id.setingsButton);
        playButton.setOnClickListener(this);
        achivementButton.setOnClickListener(this);
        setingsButton.setOnClickListener(this);

        Log.d(TAG, "MainActivity OnCreate");

     //   k=1;
       // Intent intent = new Intent(MainActivity.this, Settings.class);
    //    intent.putExtra("k", k);
        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        System.out.println(Arrays.asList(fingerprints));

        stopService(new Intent(this, MyService.class));
        settings = getSharedPreferences("Color", MODE_PRIVATE);
        switch (settings.getString("Color","не определено")){
            case "Dark blue":
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
                break;
            case "Black":
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.vk_black)));
                break;

            case "Pink":
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
                break;
            case "Grey":
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.vk_share_link_color)));
                break;

        }


    }



    @Override
    public void onUserLeaveHint() {


        super.onUserLeaveHint();

    }


    protected void onRestart()
    {
        super.onRestart();
        Log.d(TAG, "MainActivity OnRestart");
        stopService(new Intent(this, MyService.class));


    }
    protected void onStart()
    {
        super.onStart();
        Log.d(TAG, "MainActivity OnStart");
        stopService(new Intent(this, MyService.class));

    }
    protected void onResume()
    {

        super.onResume();
        Log.d(TAG, "MainActivity OnResume");
        stopService(new Intent(this, MyService.class));


    }
    protected void onPause()
    {

        super.onPause();
        Log.d(TAG, "MainActivity OnPause");
        stopService(new Intent(this, MyService.class));

    }
    protected void onStop()
    {

        super.onStop();
        startService(new Intent(this, MyService.class));
        Log.d(TAG, "MainActivity OnStop");



    }
    @Override
    public void onDestroy(){
        super.onDestroy();

    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        switch (v.getId())
        {
            case R.id.achivementButton:
                  intent = new Intent(MainActivity.this, AchActivity.class);
               startActivity(intent);
                break;
            case R.id.playButton:
                intent = new Intent(this, QuizActivity.class);
                startActivity(intent);
                break;
           case R.id.setingsButton:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;

        }
    }
}
