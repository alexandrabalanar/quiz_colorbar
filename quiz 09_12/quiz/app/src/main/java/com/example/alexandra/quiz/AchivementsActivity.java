package com.example.alexandra.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class AchivementsActivity extends AppCompatActivity {
ImageView oneImage;
ImageView fiveImage;
TextView oneText;
TextView fiveText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achivements);


        oneImage=findViewById(R.id.OneImage);
        fiveImage=findViewById(R.id.FiveImage);
        oneText=findViewById(R.id.OneText);
        fiveText=findViewById(R.id.FiveText);



        //SharedPreferences mypref= getSharedPreferences("settings",MODE_PRIVATE);
       // int g =mypref.getInt("oneimage",-1);
      /*  if(g>0)
            try {
                oneImage.setVisibility(View.VISIBLE);
                oneText.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        if(g>4)
            try {
                fiveImage.setVisibility(View.VISIBLE);
                fiveText.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        if(g>9)
            try {
                oneImage.setVisibility(View.VISIBLE);
                oneText.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        if(g>19)
            try {
                fiveImage.setVisibility(View.VISIBLE);
                fiveText.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
*/

    }
    public static int AddAchievement(int score)
    {
        return score;
    }
}
