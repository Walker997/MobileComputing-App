package com.example.mcapplicationbymg;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Views
    TextView mConStatusTv;
    Button mConStatusBtn;
    TextView mbtStatusTv;
    Button mbtStatusBtn;

    private Button accelerometer;
    private Button photos;
    private Button maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometer = (Button) findViewById(R.id.accelerometer);
        accelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccelerometer();
            }
        });

        photos = (Button) findViewById(R.id.photos);
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotos();
            }
        });

        maps = (Button) findViewById(R.id.maps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMaps();
            }
        });
        //link views with xml
        mConStatusTv = findViewById(R.id.conStatusTv);
        mConStatusBtn = findViewById(R.id.conStatusBtn);

        //button click to check network status
        mConStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //function call to check network connection status
                checkNetworkConnectionStatus();
            }
        });

        //link views with xml
        mbtStatusTv = findViewById(R.id.btStatusTv);
        mbtStatusBtn = findViewById(R.id.btStatusBtn);

        //button click to check network status
        mbtStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //function call to check network connection status
                checkBluetoothConnectionStatus();
            }
        });

    }

    public void openAccelerometer(){
        Intent intent = new Intent(this, Accelerometer.class);
        startActivity(intent);
    }

    public void openPhotos(){
        Intent intent = new Intent(this, Camera.class);
        startActivity(intent);
    }

    public void openMaps(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    private void checkNetworkConnectionStatus() {
        boolean wifiConnected;
        boolean mobileConnected;
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()){ //connected with either mobile or wifi
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected){ //wifi connected
                mConStatusTv.setText("Connected with Wifi");
            }
            else if (mobileConnected){ //mobile data connected
                mConStatusTv.setText("Connected with Mobile Data Connection");
            }
        }
        else { //no internet connection
            mConStatusTv.setText("No internet connection");
        }
    }

    private void checkBluetoothConnectionStatus() {
        BluetoothAdapter mBTAdapter = BluetoothAdapter.getDefaultAdapter();

        if(mBTAdapter == null){
            mbtStatusTv.setText("Bluetooth not available");
        }
        else{
            mbtStatusTv.setText("Bluetooth available");
        }
    }

    public void clickExit(View v)
    {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);}
}