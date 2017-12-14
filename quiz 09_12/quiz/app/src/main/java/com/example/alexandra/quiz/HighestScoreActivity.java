package com.example.alexandra.quiz;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexandra.quiz.QuizActivity;
import com.example.alexandra.quiz.R;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKApiWikiPage;
import com.vk.sdk.api.model.VKAttachments;
import com.vk.sdk.api.model.VKList;
import com.vk.sdk.api.model.VKUsersArray;

import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;

public class HighestScoreActivity extends AppCompatActivity implements View.OnClickListener {
public static int Score;
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);
        Button HomeButton;
        Button shareButton;
        TextView falsescore = (TextView) findViewById(R.id.falseScore);
        TextView txtScore = (TextView) findViewById(R.id.textScore);
        HomeButton=(Button) findViewById(R.id.homeButton);
        shareButton=(Button) findViewById(R.id.ShareButton);
        shareButton.setOnClickListener(this);
        TextView txtHighScore = (TextView) findViewById(R.id.textHighScore);
        HomeButton.setOnClickListener(this);

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



        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        // display current score
        int fscore = intent.getIntExtra("fscore", 0);
        txtScore.setText("Your score: " + score);
        falsescore.setText("False answer: " + fscore);

        Score=score;
        SharedPreferences mypref = getSharedPreferences("settings",MODE_PRIVATE);
        int highscore = mypref.getInt("highscore",0);

        SharedPreferences.Editor editor = mypref.edit();
        if(highscore>= score)
        { txtHighScore.setText("High score: "+highscore);
        /*Intent hscore = new Intent(HighestScoreActivity.this, AchivementsActivity.class);
        hscore.putExtra("score", highscore);
        startActivity(hscore);*/
            int b=AchActivity.AddAchievement(highscore);
            editor.putInt("oneimage",b);
            editor.commit();

            }
        else
        {
            txtHighScore.setText("New highscore: "+score);

            editor.putInt("highscore", score);
            editor.commit();
            /*Intent hscore = new Intent(HighestScoreActivity.this, AchivementsActivity.class);
            hscore.putExtra("score", highscore);
            startActivity(hscore);*/
            int b=AchActivity.AddAchievement(score);
            editor.putInt("oneimage",b);
            editor.commit();
        }


    }

    public void onRepeatClick(View view) {
        Intent intent = new Intent(HighestScoreActivity.this, QuizActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        switch (v.getId())
        {
            case R.id.homeButton:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.ShareButton: {
                VKAccessToken res=SettingsActivity.getToken();
                if(res==null)

                    break;

                VKRequest rq =VKApi.users().get();
                rq.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        //response.json.get("")
                        int id = 0;
                        try {
                            VKList<VKApiUser> users = (VKList<VKApiUser>) response.parsedModel;
                            id=users.get(0).id;
                            Toast.makeText(getApplicationContext(),"You shared in VK. You id:"+id, Toast.LENGTH_LONG).show();
                        }
                        catch(Exception e ) {

                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                        }
//                        VKAttachments attachments = new VKAttachments();
//                        VKAttachments.VKApiAttachment vkApiAttachment = new VKApiWikiPage();
//                        vkApiAttachment.
//                        attachments.add();
                        VKRequest postrq= VKApi.wall().post(VKParameters.from(VKApiConst.OWNER_ID,String.valueOf(id),VKApiConst.MESSAGE,"Мой счет в супер игре Quiz : "+String.valueOf(Score), VKApiConst.ATTACHMENTS, "https://vk.com/club157013652"));
                        postrq.attempts =10;
                        postrq.executeWithListener(new VKRequest.VKRequestListener() {
                            @Override
                            public void onComplete(VKResponse response) {
                                //Toast.makeText(getApplicationContext(),"SDELANO",Toast.LENGTH_SHORT).show();
                                super.onComplete(response);
                            }
                        });
                        super.onComplete(response);
                    }
                });
              /*VKParameters parameters = new VKParameters();
              parameters.put(VKApiConst.OWNER_ID,String.Valueof())
                VKApi.wall().post(VKParameters.from())executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        Toast.makeText(getApplicationContext(),"SDELANO",Toast.LENGTH_SHORT).show();
                        super.onComplete(response);
                    }
                });*/
                break;
            }
        }
    }
}

