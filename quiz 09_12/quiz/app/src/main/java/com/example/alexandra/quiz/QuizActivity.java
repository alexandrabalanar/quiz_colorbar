package com.example.alexandra.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private QuestionBank mQuestionLibrary = new QuestionBank();
    MyDataBaseHelper dataBaseHelper;

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    private String mAnswer;
    private int mScore = 0;
    private int fScore=0;
    private int mQuestionNumber = 0;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        mButtonChoice4 = (Button)findViewById(R.id.choice4);

        mQuestionLibrary.initQuestions(getApplicationContext());
        updateQuestion();

        updateScore(mScore);
        updateScore(fScore);
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

    private void updateQuestion(){

        if(mQuestionNumber<mQuestionLibrary.getLength() ){
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber, 4));




            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        }
        else {
            Toast.makeText(QuizActivity.this, "It was the last question!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuizActivity.this, HighestScoreActivity.class);
            intent.putExtra("score", mScore);


            intent.putExtra("fscore", fScore);
            startActivity(intent);
        }
    }


    private void updateScore(int point) {
        mScoreView.setText(""+mScore+"/"+mQuestionLibrary.getLength());
    }

    public void onClick(View view) {

        Button answer = (Button) view;

        if (answer.getText().equals(mAnswer)){
            mScore = mScore + 1;
            Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(QuizActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
            fScore= fScore+1;
        }

        updateScore(mScore);
        updateScore(fScore);

        updateQuestion();

        if (fScore==3)
        {
            Toast.makeText(QuizActivity.this, "It was the last question!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuizActivity.this, HighestScoreActivity.class);
            intent.putExtra("score", mScore);
            intent.putExtra("fscore", fScore);
            startActivity(intent);
        }
    }
}
