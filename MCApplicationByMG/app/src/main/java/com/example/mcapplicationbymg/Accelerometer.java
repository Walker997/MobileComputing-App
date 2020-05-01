package com.example.mcapplicationbymg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    ImageView compass;

    private static SensorManager sensorService;
    private Sensor sensor;
    private float currentDegree = 0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        compass = (ImageView) findViewById(R.id.compass);

        sensorService = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorService.getDefaultSensor(Sensor.TYPE_ORIENTATION);

    }
    @Override
    protected void onResume(){
        super.onResume();

        if(sensor != null){
            sensorService.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
        else{
            Toast.makeText(Accelerometer.this, "Not supported!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorService.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int degree = Math.round(sensorEvent.values[0]);
        compass.setRotation(-sensorEvent.values[0]);
//        RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
//                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        ra.setDuration(500);
//        ra.setFillAfter(true);
//
//        compass.startAnimation(ra);
//        currentDegree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
