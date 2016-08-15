package com.intelart.pro_gre;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button play_button;
    Button achievements_button;
    Button settings_button;
    Button quit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_button = (Button)findViewById(R.id.play_button);
        play_button.setOnClickListener(this);

        settings_button = (Button)findViewById(R.id.settings_button);
        settings_button.setOnClickListener(this);
        quit_button = (Button)findViewById(R.id.quit_button);
        quit_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.play_button:
                Intent selectGroup = new Intent(this, SelectGroupActivity.class);
                startActivity(selectGroup);
                break;
            case R.id.settings_button:
                Intent settings = new Intent(this, Settings.class);
                startActivity(settings);
                break;
            default:
                break;
        }
    }
}
