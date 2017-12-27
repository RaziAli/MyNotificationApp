package com.example.rizwan.mynotificationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Repeating5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeating5);
        TextView txt_done = (TextView) findViewById(R.id.txt_done);
        txt_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repeating6.backMainActivity(Repeating5.this);
                finish();
            }
        });
    }


}