package com.guejin.legcure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TipTwoActivity extends Activity {

    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiptwo);
    }
    public void MainClick(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}
