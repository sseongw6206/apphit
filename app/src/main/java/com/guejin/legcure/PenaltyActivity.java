package com.guejin.legcure;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PenaltyActivity extends AppCompatActivity {

    private  TextView textView1;
    private  EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.penalty);

        editText = (EditText) findViewById(R.id.edit1);
        Button button = (Button) findViewById(R.id.button2);
        textView1 = (TextView) findViewById(R.id.resultText1);

        SharedPreferences sf = getSharedPreferences("sFile",MODE_PRIVATE);
        String text = sf.getString("text","");
        textView1.setText(text);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText(editText.getText());
            }
        });
    }


    public void MainClick(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    protected  void onStop() {
        super.onStop();


        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String text = editText.getText().toString();
        editor.putString("text", text);
        editor.commit();

    }

}