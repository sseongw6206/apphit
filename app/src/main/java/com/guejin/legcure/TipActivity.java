package com.guejin.legcure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tip);
    }
    public void MainClick(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}