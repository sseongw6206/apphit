package com.guejin.legcure;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    Button btn_reset;
    Button btn_start;
    Button btn_stop;


    TextView tv;
    int count = 0;

    SensorManager manager;
    Sensor sensor;

    int up = 0;
    int down = 0;

//    double accel  = 0;
    double accel = 9.8;



    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
            {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                accel = Math.sqrt(x*x + y*y + z*z);
                if( accel > 15)
                    up = 1;

                if(up==1 && (accel < 5))
                    down = 1;

                if(down==1)
                {
                    count++;
                    tv.setText(""+count);

                    up = 0;
                    down = 0;
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
    protected void onPause(){
        super.onPause();

        manager.unregisterListener(listener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);








        btn_start = (Button) findViewById(R.id.btn_start);
        btn_reset = (Button)findViewById(R.id.btn_reset);

        btn_start.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                count = 0;

            }
        });

        btn_reset.setOnClickListener(new OnClickListener() {

                                         public void onClick(View v) {

                                             count = 0;

                                             tv.setText(String.valueOf(count));
                                         }
                                     });



        tv = (TextView) findViewById(R.id.tvCount);
        tv.setText(""+count);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }


    public void TipClick(View v){
        Intent i = new Intent(this, TipActivity.class);
        startActivity(i);
    }
    public void RecClick(View v){
        Intent i = new Intent(this, RecordActivity.class);
        startActivity(i);

    }
    public void PenClick(View v){
        Intent i = new Intent(this, PenaltyActivity.class);
        startActivity(i);
    }


}
