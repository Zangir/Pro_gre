package com.intelart.pro_gre;

import android.animation.PropertyValuesHolder;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Play extends AppCompatActivity  {

    private final String TAG = "PlayActivityDebug";

    Random randomQ;
    Button button_1, button_2, button_3, button_4;
    TextView data_text, rigthCount, wrongCount;
    String question, answer1, answer2, answer3, answer4, answer;
    int rQ, rA2, rA3, rA4, x = 0, right = 0, wrong = 0;
    int index1, index2, index3, index4, wordIndex;
    String ans1, ans2, ans3, ans4;
    Cursor cursor;
    ArrayList<ArrayList<String>> allListQ, allListQUnchanged, allListA, allListAUnchanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        DataBaseHelper myDbHelper = new DataBaseHelper(Play.this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to crate database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        cursor = myDbHelper.query("translationData", null, null, null, null, null, null);

        View.OnClickListener createQuestionsClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (v.getId()){
                    case R.id.button_1:
                        checkCorrect(button_1.getText().toString());
//                newWord();
                        createQuestion(cursor);
                        break;
                    case R.id.button_2:
                        checkCorrect(button_2.getText().toString());
//                newWord();
                        createQuestion(cursor);
                        break;
                    case R.id.button_3:
                        checkCorrect(button_3.getText().toString());
//                newWord();
                        createQuestion(cursor);
                        break;
                    case R.id.button_4:
                        checkCorrect(button_4.getText().toString());
//                newWord();
                        createQuestion(cursor);
                        break;
                }
            }
        };


        initUI();
        initArrays();

        showWords();

//        createQuestion(cursor);
//        WT(cursor);
    }

    private void showWords() {
        cursor.moveToPosition(wordIndex);
        data_text.setText(cursor.getString(1));
        button_1.setText("Synonim: " + cursor.getString(2));
        button_2.setText("Translation: " + cursor.getString(4));
        button_3.setClickable(false);
        button_4.setClickable(false);
        wordIndex++;
        final View.OnClickListener createQuestionsClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (v.getId()){
                    case R.id.button_1:
                        checkCorrect(button_1.getText().toString());
//                newWord();
                        createQuestion(cursor);
                        break;
                    case R.id.button_2:
                        checkCorrect(button_2.getText().toString());
//                newWord();
                        createQuestion(cursor);
                        break;
                    case R.id.button_3:
                        checkCorrect(button_3.getText().toString());
//                newWord();
                        createQuestion(cursor);
                        break;
                    case R.id.button_4:
                        checkCorrect(button_4.getText().toString());
//                newWord();
                        createQuestion(cursor);
                        break;
                }
            }
        };
        View.OnClickListener showNewWord = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wordIndex<4){
                    showWords();
                }
                else{
                    button_3.setClickable(true);
                    button_4.setClickable(true);
                    button_1.setOnClickListener(createQuestionsClick);
                    button_2.setOnClickListener(createQuestionsClick);
                    button_3.setOnClickListener(createQuestionsClick);
                    button_4.setOnClickListener(createQuestionsClick);
                    createQuestion(cursor);
                }
            }
        };
        button_1.setOnClickListener(showNewWord);
        button_2.setOnClickListener(showNewWord);
    }

    private void createQuestion(Cursor cursor){

        randomQ = new Random();

        int ranNumber = randomQ.nextInt(allListQ.get(x).size());

        data_text.setText(allListQ.get(x).get(ranNumber));
        Log.d(TAG, data_text.getText().toString());
        answer = allListA.get(x).get(ranNumber);
        Log.d(TAG, answer);

        index1 = randomQ.nextInt(4);

        do {
            index2 = randomQ.nextInt(4);
        } while (index2 == index1);

        do {
            index3 = randomQ.nextInt(4);
        } while (index3 == index1 || index3 == index2);

        do {
            index4 = randomQ.nextInt(4);
        } while (index4 == index1 || index4 == index2 || index4 == index3);

        button_1.setText(allListAUnchanged.get(x).get(index1));
        button_2.setText(allListAUnchanged.get(x).get(index2));
        button_3.setText(allListAUnchanged.get(x).get(index3));
        button_4.setText(allListAUnchanged.get(x).get(index4));



    }
/*

    public void WT (Cursor cursor) {                      //x=0
        randomIndexes();

if (cursor != null && cursor.getCount() > 0) {
    cursor.moveToPosition(rQ);
    question = cursor.getString(1);
    data_text.setText(question);
    answer1 = cursor.getString(4);
    cursor.moveToPosition(rA2);
    answer2 = cursor.getString(4);
    cursor.moveToPosition(rA3);
    answer3 = cursor.getString(4);
    cursor.moveToPosition(rA4);
    answer4 = cursor.getString(4);
//            setOnButtonText();
}

    }
*/

    public void WS (Cursor cursor) {                       //x=1
        randomIndexes();

        if(cursor != null && cursor.getCount()>0) {
            cursor.moveToPosition(rQ);
            question = cursor.getString(1);
            data_text.setText(question);
            answer1 = cursor.getString(2);
            cursor.moveToPosition(rA2);
            answer2 = cursor.getString(2);
            cursor.moveToPosition(rA3);
            answer3 = cursor.getString(2);
            cursor.moveToPosition(rA4);
            answer4 = cursor.getString(2);

//            setOnButtonText();

        }
    }

    public void TW (Cursor cursor) {                          //x=2
        randomIndexes();


        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToPosition(rQ);
            question = cursor.getString(4);
            data_text.setText(question);
            answer1 = cursor.getString(1);
            cursor.moveToPosition(rA2);
            answer2 = cursor.getString(1);
            cursor.moveToPosition(rA3);
            answer3 = cursor.getString(1);
            cursor.moveToPosition(rA4);
            answer4 = cursor.getString(1);

//            setOnButtonText();
        }
    }

    public void SW (Cursor cursor) {                    //x=3

        randomIndexes();

        if(cursor != null && cursor.getCount()>0) {

            cursor.moveToPosition(rQ);
            question = cursor.getString(2);
            data_text.setText(question);
            answer1 = cursor.getString(1);
            cursor.moveToPosition(rA2);
            answer2 = cursor.getString(1);
            cursor.moveToPosition(rA3);
            answer3 = cursor.getString(1);
            cursor.moveToPosition(rA4);
            answer4 = cursor.getString(1);

//            setOnButtonText();
        }
    }



    private void checkCorrect(String ans){
        if (ans.equals(answer)){
            int number;
            right++;
            rigthCount.setText("Right: " + right);
            number = allListA.get(x).indexOf(answer);
            allListQ.get(x).remove(number);
            allListA.get(x).remove(number);
        }
        else{
            wrong++;
            wrongCount.setText("Wrong: " + wrong);
        }


        do {
            x = new Random().nextInt(4);
        }while(allListQ.get(x).isEmpty());
    }

//    private void newWord(){
//        do {
//            x = new Random().nextInt(4);
//            Log.d("tag", x + "");
//        }while(allListQ.get(x).isEmpty());
////        if (x == 0){WT(cursor);}
////        if (x == 1){WS(cursor);}
////        if (x == 2){TW(cursor);}
////        if (x == 3){SW(cursor);}
//    }

    private void randomIndexes(){
        randomQ = new Random();

        index1 = randomQ.nextInt(4);

        do {
            index2 = randomQ.nextInt(4);
        } while (index2 == index1);

        do {
            index3 = randomQ.nextInt(4);
        } while (index3 == index1 || index3 == index2);

        do {
            index4 = randomQ.nextInt(4);
        } while (index4 == index1 || index4 == index2 || index4 == index3);

        rQ = randomQ.nextInt(4);

        do {
            rA2 = randomQ.nextInt(4);
        } while (rA2 == rQ);

        do {
            rA3 = randomQ.nextInt(4);
        } while (rA3 == rQ || rA3 == rA2);

        do {
            rA4 = randomQ.nextInt(4);
        } while (rA4 == rQ || rA4 == rA2 || rA4 == rA3);

    }

    private void initUI(){

        data_text = (TextView) findViewById(R.id.data_text);
        rigthCount = (TextView) findViewById(R.id.rigthCount);
        wrongCount = (TextView) findViewById(R.id.wrongCount);
        button_1 = (Button) findViewById(R.id.button_1);
        button_2 = (Button) findViewById(R.id.button_2);
        button_3 = (Button) findViewById(R.id.button_3);
        button_4 = (Button) findViewById(R.id.button_4);
    }

    private void initArrays(){

        // Answers

        allListA = new ArrayList<>();

        //WT

        allListA.add( new ArrayList<String>());
        for (int j = 0; j < 4; j++) {
            cursor.moveToPosition(j);
            allListA.get(0).add(cursor.getString(4));
            Log.d("tag", cursor.getString(4));
        }

        //WS

        allListA.add( new ArrayList<String>());
        for (int j = 0; j < 4; j++) {
            cursor.moveToPosition(j);
            allListA.get(1).add(cursor.getString(2));
            Log.d("tag", cursor.getString(2));
        }

        //TW

        allListA.add( new ArrayList<String>());
        for (int j = 0; j < 4; j++) {
            cursor.moveToPosition(j);
            allListA.get(2).add(cursor.getString(1));
            Log.d("tag", cursor.getString(1));
        }

        //SW

        allListA.add( new ArrayList<String>());
        for (int j = 0; j < 4; j++) {
            cursor.moveToPosition(j);
            allListA.get(3).add(cursor.getString(1));
            Log.d("tag", cursor.getString(1));
        }

//        allListAUnchanged = (ArrayList<ArrayList<String>>) allListA.clone();
        allListAUnchanged = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            allListAUnchanged.add(new ArrayList<String>());
            for (int j = 0; j < 4; j++) {
                allListAUnchanged.get(i).add(allListA.get(i).get(j));
            }
        }

        //Questions

        allListQ = new ArrayList<>();

        //WT

        allListQ.add(new ArrayList<String>());
        for (int i = 0; i < 4; i++) {
            cursor.moveToPosition(i);
            allListQ.get(0).add(cursor.getString(1));
        }

        //WS

        allListQ.add(new ArrayList<String>());
        for (int i = 0; i < 4; i++) {
            cursor.moveToPosition(i);
            allListQ.get(1).add(cursor.getString(1));
        }

        //TW

        allListQ.add(new ArrayList<String>());
        for (int i = 0; i < 4; i++) {
            cursor.moveToPosition(i);
            allListQ.get(2).add(cursor.getString(4));
        }

        //SW

        allListQ.add(new ArrayList<String>());
        for (int i = 0; i < 4; i++) {
            cursor.moveToPosition(i);
            allListQ.get(3).add(cursor.getString(2));
        }
        allListQUnchanged = new ArrayList<>();
//        allListQUnchanged = (ArrayList<ArrayList<String>>) allListQ.clone();
        for (int i = 0; i < 4; i++) {
            allListQUnchanged.add(new ArrayList<String>());
            for (int j = 0; j < 4; j++) {
                allListQUnchanged.get(i).add(allListQ.get(i).get(j));
            }
        }
    }

//    private void setOnButtonText() {
//        List<String> myList = new ArrayList<String>();
//        myList.add(answer1);
//        myList.add(answer2);
//        myList.add(answer3);
//        myList.add(answer4);
//
//        ans1 = myList.get(index1);
//        ans2 = myList.get(index2);
//        ans3 = myList.get(index3);
//        ans4 = myList.get(index4);
//
//        button_1.setText(ans1);
//        button_2.setText(ans2);
//        button_3.setText(ans3);
//        button_4.setText(ans4);
//    }

}