package com.intelart.pro_gre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DefinitionActivity extends AppCompatActivity {

    private final String TAG = "PlayActivityDebug";

    int wordsNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        wordsNumber = Integer.parseInt(getIntent().getStringExtra(Resources.WORDS_NUMBER_KEY));
        wordsNumber = 0;


    }
}
