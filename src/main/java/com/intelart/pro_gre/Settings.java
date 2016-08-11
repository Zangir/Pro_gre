package com.intelart.pro_gre;

import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class Settings extends AppCompatActivity {

    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ((Button) findViewById(R.id.button_db)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper myDbHelper = new DataBaseHelper(Settings.this);
                try{
                    myDbHelper.createDataBase();
                } catch (IOException ioe) {
                   throw new Error("Unable to crate database");
                }
                try {
                    myDbHelper.openDataBase();
                } catch (SQLException sqle) {
                    throw sqle;
                }
                Toast.makeText(Settings.this, "Success", Toast.LENGTH_SHORT).show();
                c = myDbHelper.query("translateData", null, null, null, null, null, null);
                if(c.moveToFirst()){
                    do{
                        Toast.makeText(Settings.this,
                                "_id: " + c.getString(0) + "\n" +
                                        "Translation1: " + c.getString(4),
                                Toast.LENGTH_LONG).show();
                    } while (c.moveToNext());
                }
            }
        });
    }
}
