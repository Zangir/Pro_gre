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

public class Play extends AppCompatActivity implements View.OnClickListener {


    Random randomQ;
    Button button_1, button_2, button_3, button_4;
    TextView data_text;
    String question, answer1, answer2, answer3, answer4;
    int rQ, rA2, rA3, rA4, x;
    int index1, index2, index3, index4;
    String ans1, ans2, ans3, ans4;
    Cursor cursor;

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

        data_text = (TextView) findViewById(R.id.data_text);
        button_1 = (Button) findViewById(R.id.button_1);
        button_2 = (Button) findViewById(R.id.button_2);
        button_3 = (Button) findViewById(R.id.button_3);
        button_4 = (Button) findViewById(R.id.button_4);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);


        WT(cursor);
    }


    public void WS (Cursor cursor) {
        randomQ = new Random();
        rQ = randomQ.nextInt(50);
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


        do {
            rA2 = randomQ.nextInt(50);
        } while (rA2 == rQ);

        do {
            rA3 = randomQ.nextInt(50);
        } while (rA3 == rQ || rA3 == rA2);

        do {
            rA4 = randomQ.nextInt(50);
        } while (rA4 == rQ || rA4 == rA2 || rA4 == rA3);
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
                List<String> myList = new ArrayList<String>();
                myList.add(answer1);
                myList.add(answer2);
                myList.add(answer3);
                myList.add(answer4);

                ans1 = myList.get(index1);
                ans2 = myList.get(index2);
                ans3 = myList.get(index3);
                ans4 = myList.get(index4);

                button_1.setText(ans1);
                button_2.setText(ans2);
                button_3.setText(ans3);
                button_4.setText(ans4);
            }
    }



    public void WT (Cursor cursor) {
        randomQ = new Random();
        rQ = randomQ.nextInt(50);
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


        do {
            rA2 = randomQ.nextInt(50);
        } while (rA2 == rQ);

        do {
            rA3 = randomQ.nextInt(50);
        } while (rA3 == rQ || rA3 == rA2);

        do {
            rA4 = randomQ.nextInt(50);
        } while (rA4 == rQ || rA4 == rA2 || rA4 == rA3);

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
            List<String> myList = new ArrayList<String>();
            myList.add(answer1);
            myList.add(answer2);
            myList.add(answer3);
            myList.add(answer4);

            ans1 = myList.get(index1);
            ans2 = myList.get(index2);
            ans3 = myList.get(index3);
            ans4 = myList.get(index4);

            button_1.setText(ans1);
            button_2.setText(ans2);
            button_3.setText(ans3);
            button_4.setText(ans4);
        }
    }




    public void TW (Cursor cursor) {
        randomQ = new Random();
        rQ = randomQ.nextInt(50);
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


        do {
            rA2 = randomQ.nextInt(50);
        } while (rA2 == rQ);

        do {
            rA3 = randomQ.nextInt(50);
        } while (rA3 == rQ || rA3 == rA2);

        do {
            rA4 = randomQ.nextInt(50);
        } while (rA4 == rQ || rA4 == rA2 || rA4 == rA3);

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
            List<String> myList = new ArrayList<String>();
            myList.add(answer1);
            myList.add(answer2);
            myList.add(answer3);
            myList.add(answer4);

            ans1 = myList.get(index1);
            ans2 = myList.get(index2);
            ans3 = myList.get(index3);
            ans4 = myList.get(index4);

            button_1.setText(ans1);
            button_2.setText(ans2);
            button_3.setText(ans3);
            button_4.setText(ans4);
        }
    }

    public void SW (Cursor cursor) {
        randomQ = new Random();
        rQ = randomQ.nextInt(50);
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


        do {
            rA2 = randomQ.nextInt(50);
        } while (rA2 == rQ);

        do {
            rA3 = randomQ.nextInt(50);
        } while (rA3 == rQ || rA3 == rA2);

        do {
            rA4 = randomQ.nextInt(50);
        } while (rA4 == rQ || rA4 == rA2 || rA4 == rA3);
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
            List<String> myList = new ArrayList<String>();
            myList.add(answer1);
            myList.add(answer2);
            myList.add(answer3);
            myList.add(answer4);

            ans1 = myList.get(index1);
            ans2 = myList.get(index2);
            ans3 = myList.get(index3);
            ans4 = myList.get(index4);

            button_1.setText(ans1);
            button_2.setText(ans2);
            button_3.setText(ans3);
            button_4.setText(ans4);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        x = new Random().nextInt(4);
        switch (v.getId()){
            case R.id.button_1:
                if (x == 0){WT(cursor);}
                if (x == 1){WS(cursor);}
                if (x == 2){TW(cursor);}
                if (x == 3){SW(cursor);}
                break;
            case R.id.button_2:
                if (x == 0){WT(cursor);}
                if (x == 1){WS(cursor);}
                if (x == 2){TW(cursor);}
                if (x == 3){SW(cursor);}
                break;
            case R.id.button_3:
                if (x == 0){WT(cursor);}
                if (x == 1){WS(cursor);}
                if (x == 2){TW(cursor);}
                if (x == 3){SW(cursor);}
                break;
            case R.id.button_4:
                if (x == 0){WT(cursor);}
                if (x == 1){WS(cursor);}
                if (x == 2){TW(cursor);}
                if (x == 3){SW(cursor);}
                break;
        }
    }
}