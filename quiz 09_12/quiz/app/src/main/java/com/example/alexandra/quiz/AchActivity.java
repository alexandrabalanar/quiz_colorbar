package com.example.alexandra.quiz;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AchActivity extends AppCompatActivity {
    ImageView oneImage;
    ImageView fiveImage;
    ImageView tenImage;
    TextView oneText;
    TextView fiveText;
    TextView tenText;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ach);

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
        oneImage=findViewById(R.id.OneImage);
        fiveImage=findViewById(R.id.FiveImage);
        tenImage=findViewById(R.id.TenImage);

        oneText=findViewById(R.id.OneText);
        fiveText=findViewById(R.id.FiveText);
        tenText=findViewById(R.id.TenText);



        SharedPreferences mypref= getSharedPreferences("settings",MODE_PRIVATE);
         int g =mypref.getInt("oneimage",-1);
        if(g>-1)
            try {
                oneImage.setVisibility(View.VISIBLE);
                oneText.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        if(g>3)
            try {
                fiveImage.setVisibility(View.VISIBLE);
                fiveText.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        if(g>8)
            try {
                tenImage.setVisibility(View.VISIBLE);
                tenText.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }


    }
    public static int AddAchievement(int score)
    {
        return score;
    }
}
