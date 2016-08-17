package com.intelart.pro_gre;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Locale;

public class WordListActivity extends AppCompatActivity {

    TextView[][] words;
    Button testBtn;
    Cursor cursor;
    int wordsNumber, extraNumber;
    TextToSpeech say;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        extraNumber = Integer.parseInt(getIntent().getStringExtra(Resources.WORDS_NUMBER_KEY));

        wordsNumber = (extraNumber - 1) * 4;

        DataBaseHelper myDbHelper = new DataBaseHelper(WordListActivity.this);
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



        initUI();

        cursor = myDbHelper.query("translationData", null, null, null, null, null, null);

        for (int i = wordsNumber; i < wordsNumber + 4; i++) {

            cursor.moveToPosition(i);

            words[i-wordsNumber][0].setText(cursor.getString(1));
            words[i-wordsNumber][1].setText(cursor.getString(4).substring(1));
            words[i-wordsNumber][2].setText(cursor.getString(2));
        }

        setClickers();

    }

    private void setClickers() {
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goTest = new Intent(WordListActivity.this, PlayActivity.class);
                goTest.putExtra(Resources.WORDS_NUMBER_KEY, extraNumber+"");
                say.shutdown();
                startActivity(goTest);
            }
        });

        for (int i = 0; i < 4; i++) {
            words[i][0].setClickable(true);
            final int x = i;
            words[i][0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    say = new TextToSpeech(WordListActivity.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status == TextToSpeech.SUCCESS) {
                                int result = say.setLanguage(Locale.US);
                                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                    Log.e("error", "This Language is not supported");
                                } else {
                                    say.speak(words[x][0].getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                                }

                            }
                        }
                    });

                }
            });
        }


    }

    private void initUI() {
        words = new TextView[4][3];

        words[0][0] = (TextView) findViewById(R.id.word1WordListActivity);
        words[1][0] = (TextView) findViewById(R.id.word2WordListActivity);
        words[2][0] = (TextView) findViewById(R.id.word3WordListActivity);
        words[3][0] = (TextView) findViewById(R.id.word4WordListActivity);



        words[0][1] = (TextView) findViewById(R.id.translate1WordListActivity);
        words[1][1] = (TextView) findViewById(R.id.translate2WordListActivity);
        words[2][1] = (TextView) findViewById(R.id.translate3WordListActivity);
        words[3][1] = (TextView) findViewById(R.id.translate4WordListActivity);

        words[0][2] = (TextView) findViewById(R.id.synonim1WordListActivity);
        words[1][2] = (TextView) findViewById(R.id.synonim2WordListActivity);
        words[2][2] = (TextView) findViewById(R.id.synonim3WordListActivity);
        words[3][2] = (TextView) findViewById(R.id.synonim4WordListActivity);

        testBtn = (Button) findViewById(R.id.testBtnWordListActivity);
    }
}
