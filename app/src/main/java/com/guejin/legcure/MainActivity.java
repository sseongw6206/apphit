package com.guejin.legcure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    Button btn_reset;
    Button btn_start;
    Button btn_stop;
    Button btn_lock;
    Button btn_open;


    TextView tv;
    int count = 0;
    int num;
    int swichbut=0;
    int swichbut2=0;


    int swich = 0;
int openlock=0;
    SensorManager manager;
    Sensor sensor;

    int up = 0;
    int down = 0;

    //    double accel  = 0;
    double accel = 5.1;

SoundPool soundPool;
int sound;


    boolean flag = false;

    SensorEventListener listener = new SensorEventListener() {


        @Override
        public void onSensorChanged(SensorEvent event) {

            if (swich == 1) {

                if (count%10==0&&count>=10){


                    if(swichbut==0) {
                        soundPool.play(sound, 1f, 1f, 0, 0, 1f);
                    }
                    if(!flag){
                        flag = true;
                        new AlertDialog.Builder(MainActivity.this
                        )
                                .setTitle("경고")
                                .setMessage("벌칙을 수행하시오")
                                .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dlg, int sumthin) {
                                    }
                                })
                                .show(); // 팝업창 보여줌

                    }



                }


                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    float y = event.values[0];
                    float x = event.values[1];
                    float z = event.values[2];

                    accel = Math.sqrt(x * x + y * y + z * z);
                    if (accel > 15)
                        up = 1;

                    if (up == 1 && (accel < 5))
                        down = 1;

                    if (down == 1) {
                        num++;
                        count++;

                       if(swichbut2==0) {
                           Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                           vibrator.vibrate(500);
                       }

                        tv.setText("" + count);

                        up = 0;
                        down = 0;



                    }

                }
            }

        }


        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    @Override
    protected void onResume() {
        super.onResume();

        manager.registerListener(listener, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        manager.unregisterListener(listener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_start = (Button) findViewById(R.id.btn_start);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_lock = (Button) findViewById(R.id.btn_lock);
        btn_open = (Button) findViewById(R.id.btn_open);


        soundPool = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
        sound = soundPool.load(this,R.raw.uuu,1);

        btn_lock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                openlock =1;
            }
        });
        btn_open.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                openlock =0;
            }
        });

        btn_start.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v){

            if (openlock ==0){
             swich=1;



            }}
        });

        btn_stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (openlock ==0)
                swich = 0;
            }
        });
        btn_reset.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if (openlock ==0)
                count = 0;
                flag = false;

                tv.setText(String.valueOf(count));
            }
        });

        tv = (TextView) findViewById(R.id.tvCount);
        tv.setText("" + count);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);








        Switch switchButton = (Switch) findViewById(R.id.switch1);
        Switch switchButton2 = (Switch) findViewById(R.id.switch2);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                // 스위치 버튼이 체크되었는지 검사하여 텍스트뷰에 각 경우에 맞게 출력합니다.
                if (isChecked){

                    swichbut = 1;

                }else{
                    swichbut = 0;

                }
            }
        });



        switchButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked2) {


                // 스위치 버튼이 체크되었는지 검사하여 텍스트뷰에 각 경우에 맞게 출력합니다.
                if (isChecked2){

                    swichbut2 = 1;

                }else{

                    swichbut2 = 0;
                }
            }
        });




    }







    public void TipClick(View v) {
        if (openlock ==0){
        Intent i = new Intent(this, TipActivity.class);
        startActivity(i);
        }
    }

    public void RecClick(View v) {
        if (openlock ==0) {

            Intent i = new Intent(MainActivity.this, RecordActivity.class);
            i.putExtra("num",num);
            startActivity(i);
        }
    }

    public void PenClick(View v) {
        if (openlock ==0) {
            Intent i = new Intent(this, PenaltyActivity.class);
            startActivity(i);
        }
    }

}