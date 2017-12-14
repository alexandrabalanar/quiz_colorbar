package com.example.alexandra.quiz;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {


    List<Question> list = new ArrayList<>();
    MyDataBaseHelper myDataBaseHelper;

    // возвращаем количество вопросов
    public int getLength(){
        return list.size();
    }


    public String getQuestion(int a) {
       return list.get(a).getQuestion();
    }


    public String getChoice(int index, int num) {
        return list.get(index).getChoice(num-1);
    }


    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer();
    }



    public void initQuestions(Context context) {
        myDataBaseHelper = new MyDataBaseHelper(context);
        list = myDataBaseHelper.getAllQuestionsList();

        if (list.isEmpty()) {
            myDataBaseHelper.addInitialQuestion(new Question("1. 4",
                    new String[]{"1", "2", "3", "4"}, "4"));
            myDataBaseHelper.addInitialQuestion(new Question("2. 2",
                    new String[]{"1", "2", "3", "4"}, "2"));
            myDataBaseHelper.addInitialQuestion(new Question("3. 2",
                    new String[]{"1", "2", "3", "4"}, "2"));
            myDataBaseHelper.addInitialQuestion(new Question("4. 2",
                    new String[]{"1", "2", "3", "4"}, "2"));
            myDataBaseHelper.addInitialQuestion(new Question("5. 2",
                    new String[]{"1", "2", "3", "4"}, "2"));
            myDataBaseHelper.addInitialQuestion(new Question("6. 2",
                    new String[]{"1", "2", "3", "4"}, "2"));

            myDataBaseHelper.addInitialQuestion(new Question("7. 2",
                    new String[]{"1", "2", "3", "4"}, "2"));

            myDataBaseHelper.addInitialQuestion(new Question("8. 2",
                    new String[]{"1", "2", "3", "4"}, "2"));

            myDataBaseHelper.addInitialQuestion(new Question("9. 2",
                    new String[]{"1", "2", "3", "4"}, "2"));
            myDataBaseHelper.addInitialQuestion(new Question("10. 2",
                    new String[]{"1", "2", "3", "4"}, "2"));

            list = myDataBaseHelper.getAllQuestionsList();

        }
    }

}