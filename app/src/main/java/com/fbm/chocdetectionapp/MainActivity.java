package com.fbm.chocdetectionapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gc.materialdesign.views.Switch;


public class MainActivity extends AppCompatActivity {
    SensorTask sensorTask;
    Switch switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchButton = (Switch) findViewById(R.id.switchView);


        MainActivity.this.switchButton.setOncheckListener(new Switch.OnCheckListener() {
            @Override
            public void onCheck(Switch aSwitch, boolean status) {
                if (status) {
                    sensorTask = new SensorTask(getApplicationContext());
                    sensorTask.execute();

                } else {
                    sensorTask.cancel(true);
                }

            }
        });


    }


}
