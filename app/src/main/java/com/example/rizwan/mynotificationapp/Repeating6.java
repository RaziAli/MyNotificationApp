package com.example.rizwan.mynotificationapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Repeating6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeating6);
        TextView txt_done = (TextView) findViewById(R.id.txt_done);
        txt_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                backMainActivity(Repeating6.this);
                finish();
            }
        });
    }

    public static void backMainActivity(Context context) {
        if(!MainActivity.IS_APP_RUNNING) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }
}