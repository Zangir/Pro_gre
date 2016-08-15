package com.intelart.pro_gre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectGroupActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView_sGr;
    Button button_group1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_group);

        textView_sGr = (TextView)findViewById(R.id.textView_sGr);
        button_group1 = (Button)findViewById(R.id.button_group1);
        button_group1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.play_button:
                Intent selectWord = new Intent(this, SelectWordsActivity.class);
                startActivity(selectWord);
                break;
            default:
                break;
    }
}
}
