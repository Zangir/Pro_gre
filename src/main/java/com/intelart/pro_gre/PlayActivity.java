package com.intelart.pro_gre;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;

public class PlayActivity extends AppCompatActivity {

    private final String TAG = "PlayActivityDebug";

    CountDownTimer timer;
    AlertDialog.Builder builder;
    Random randomQ;
    Button button_1, button_2, button_3, button_4;
    TextView data_text, rightCount, wrongCount, timerView;
    String answer;
    int  x = 0, right = 0, wrong = 0;
    int index1, index2, index3, index4, wordsNumber, extraNumber;
    Cursor cursor;
    ArrayList<ArrayList<String>> allListQ, allListQUnchanged, allListA, allListAUnchanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        DataBaseHelper myDbHelper = new DataBaseHelper(PlayActivity.this);
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


        extraNumber = Integer.parseInt(getIntent().getStringExtra(Resources.WORDS_NUMBER_KEY));
        Log.d(TAG, extraNumber+"");
        wordsNumber = (extraNumber - 1) * 4;

        cursor = myDbHelper.query("translationData", null, null, null, null, null, null);

        initUI();
        initArrays();


        setTimer();
        programButtons();
        createQuestion();

    }

    private void setTimer() {
        timer = new CountDownTimer(300000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String timerText;
                timerText = millisUntilFinished/60000 + ":" + millisUntilFinished%60000/1000;
                timerView.setText(timerText);
            }

            @Override
            public void onFinish() {
                finishDialog();
            }
        };
        timer.start();
    }

    private void programButtons() {
        final View.OnClickListener createQuestionsClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_SW1:
                        checkCorrect(button_1.getText().toString());
                        createQuestion();
                        break;
                    case R.id.button_2PlayActivity:
                        checkCorrect(button_2.getText().toString());
                        createQuestion();
                        break;
                    case R.id.button_3PlayActivity:
                        checkCorrect(button_3.getText().toString());
                        createQuestion();
                        break;
                    case R.id.button_4PlayActivity:
                        checkCorrect(button_4.getText().toString());
                        createQuestion();
                        break;
                }
            }
        };

        button_1.setOnClickListener(createQuestionsClick);
        button_2.setOnClickListener(createQuestionsClick);
        button_3.setOnClickListener(createQuestionsClick);
        button_4.setOnClickListener(createQuestionsClick);
    }

    private void createQuestion() {
        if (right==2) {
            timer.cancel();
            finishDialog();
        }
        else {

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

    }

    private void finishDialog() {

        if (wrong>5){
         builder.setMessage(Resources.YOU_HAVE_EXCEED_AMOUNT_OF_MISTAKES);
        }
        else {
            builder.setMessage(Resources.YOU_HAVE_MADE + " " + wrong + " " + Resources.MISTAKES).setNegativeButton(R.string.next_level, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(PlayActivity.this, WordListActivity.class);
                    intent.putExtra(Resources.WORDS_NUMBER_KEY, extraNumber+1+"");
                    startActivity(intent);
                }
            });
        }
        builder.setPositiveButton(R.string.MainMenu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }).setNeutralButton(R.string.Restart, new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(PlayActivity.this, PlayActivity.class);
                intent.putExtra(Resources.WORDS_NUMBER_KEY, extraNumber+"");
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void checkCorrect(String ans) {
        if (ans.equals(answer)) {
            int number;
            right++;
            rightCount.setText("Right: " + right);
            number = allListA.get(x).indexOf(answer);
            allListQ.get(x).remove(number);
            allListA.get(x).remove(number);
        } else {
            wrong++;
            wrongCount.setText("Wrong: " + wrong);
        }

        do {
            x = new Random().nextInt(4);
        } while (right!=16 && allListQ.get(x).isEmpty());
    }

    private void initUI() {

        data_text = (TextView) findViewById(R.id.data_textPlayActivity);
        rightCount = (TextView) findViewById(R.id.rigthCount);
        wrongCount = (TextView) findViewById(R.id.wrongCount);
        timerView = (TextView) findViewById(R.id.timerPlayActivity);
        button_1 = (Button) findViewById(R.id.button_SW1);
        button_2 = (Button) findViewById(R.id.button_2PlayActivity);
        button_3 = (Button) findViewById(R.id.button_3PlayActivity);
        button_4 = (Button) findViewById(R.id.button_4PlayActivity);
        builder = new AlertDialog.Builder(PlayActivity.this);
    }

    private void initArrays() {

        // Answers

        allListA = new ArrayList<>();

        //WT

        allListA.add(new ArrayList<String>());
        for (int j = 0; j < 4; j++) {
            cursor.moveToPosition(wordsNumber+j);
            allListA.get(0).add(cursor.getString(4));
        }

        //WS

        allListA.add(new ArrayList<String>());
        for (int j = 0; j < 4; j++) {
            cursor.moveToPosition(wordsNumber+j);
            allListA.get(1).add(cursor.getString(2));
        }

        //TW

        allListA.add(new ArrayList<String>());
        for (int j = 0; j < 4; j++) {
            cursor.moveToPosition(wordsNumber+j);
            allListA.get(2).add(cursor.getString(1));
        }

        //SW

        allListA.add(new ArrayList<String>());
        for (int j = 0; j < 4; j++) {
            cursor.moveToPosition(wordsNumber+j);
            allListA.get(3).add(cursor.getString(1));
        }

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
            cursor.moveToPosition(wordsNumber+i);
            allListQ.get(0).add(cursor.getString(1));
        }

        //WS

        allListQ.add(new ArrayList<String>());
        for (int i = 0; i < 4; i++) {
            cursor.moveToPosition(wordsNumber+i);
            allListQ.get(1).add(cursor.getString(1));
        }

        //TW

        allListQ.add(new ArrayList<String>());
        for (int i = 0; i < 4; i++) {
            cursor.moveToPosition(wordsNumber+i);
            allListQ.get(2).add(cursor.getString(4));
        }

        //SW

        allListQ.add(new ArrayList<String>());
        for (int i = 0; i < 4; i++) {
            cursor.moveToPosition(wordsNumber+i);
            allListQ.get(3).add(cursor.getString(2));
        }
        allListQUnchanged = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            allListQUnchanged.add(new ArrayList<String>());
            for (int j = 0; j < 4; j++) {
                allListQUnchanged.get(i).add(allListQ.get(i).get(j));
            }
        }

    }
}