package com.guejin.legcure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class RecordActivity extends AppCompatActivity {

    TextView tx2;

int num2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);


        tx2 = (TextView)findViewById(R.id.tv2);

     Intent i = getIntent();
     final int num = i.getExtras().getInt("num");

     if(num2 != num){
         num2 =num;
     }

     tx2.setText(String.valueOf(num2));

     if(savedInstanceState != null){
        num2 = savedInstanceState.getInt("number");
        tx2.setText(num2+"");
     }

    }

public void clickSetBt(View view){

    SharedPreferences sharedPreferences= getSharedPreferences("test", MODE_PRIVATE);    // test 이름의 기본모드 설정
    SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
    editor.putString("inputText", tx2.getText().toString()); // key,value 형식으로 저장
    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
    Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
}
    public void clickGetBt(View view) {     // Get버튼 클릭 시   SharedPreferences에 값 불러오기.
        SharedPreferences sharedPreferences= getSharedPreferences("test", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        String inputText = sharedPreferences.getString("inputText","");

int i = Integer.parseInt(inputText)+num2;
        tx2.setText(inputText+"+"+num2+"="+i);    // TextView에 SharedPreferences에 저장되어있던 값 찍기.
        Toast.makeText(this, "불러오기 하였습니다..", Toast.LENGTH_SHORT).show();
    }// clickGetBt()..

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("number",num2);
    }

    public void MainClick(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}