package com.intelart.pro_gre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectWordsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView_sw;
    Button button_SW1;
    Button button_SW2;
    Button button_SW3;
    Button button_SW4;
    Button button_SW5;
    Button button_SW6;
    Button button_SW7;
    Button button_SW8;
    Button button_SW9;
    Button button_SW10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_words);

        textView_sw = (TextView)findViewById(R.id.textView_sw);
        button_SW1 = (Button)findViewById(R.id.button_SW1);
        button_SW2 = (Button)findViewById(R.id.button_SW2);
        button_SW3 = (Button)findViewById(R.id.button_SW3);
        button_SW4 = (Button)findViewById(R.id.button_SW4);
        button_SW5 = (Button)findViewById(R.id.button_SW5);
        button_SW6 = (Button)findViewById(R.id.button_SW6);
        button_SW7 = (Button)findViewById(R.id.button_SW7);
        button_SW8 = (Button)findViewById(R.id.button_SW8);
        button_SW9 = (Button)findViewById(R.id.button_SW9);
        button_SW10 = (Button)findViewById(R.id.button_SW10);
        button_SW1.setOnClickListener(this);
        button_SW2.setOnClickListener(this);
        button_SW3.setOnClickListener(this);
        button_SW4.setOnClickListener(this);
        button_SW5.setOnClickListener(this);
        button_SW6.setOnClickListener(this);
        button_SW7.setOnClickListener(this);
        button_SW8.setOnClickListener(this);
        button_SW9.setOnClickListener(this);
        button_SW10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Play.class);
        switch (v.getId()){
            case R.id.button_SW1:
                intent.putExtra("1", "");
                startActivity(intent);
                break;
            case R.id.button_SW2:
                intent.putExtra("2", "");
                startActivity(intent);
                break;
            case R.id.button_SW3:
                intent.putExtra("3", "");
                startActivity(intent);
                break;
            case R.id.button_SW4:
                intent.putExtra("4", "");
                startActivity(intent);
                break;
            case R.id.button_SW5:
                intent.putExtra("5", "");
                startActivity(intent);
                break;
            case R.id.button_SW6:
                intent.putExtra("6", "");
                startActivity(intent);
                break;
            case R.id.button_SW7:
                intent.putExtra("7", "");
                startActivity(intent);
                break;
            case R.id.button_SW8:
                intent.putExtra("8", "");
                startActivity(intent);
                break;
            case R.id.button_SW9:
                intent.putExtra("9", "");
                startActivity(intent);
                break;
            case R.id.button_SW10:
                intent.putExtra("10", "");
                startActivity(intent);
                break;
        }

    }
}
