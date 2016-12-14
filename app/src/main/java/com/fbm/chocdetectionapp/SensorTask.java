package com.fbm.chocdetectionapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import android.util.Log;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by Asus on 26/11/2016.
 */

public class SensorTask extends AsyncTask implements SensorEventListener {
    boolean msgSent=false;
    private Context context;
    private SensorManager sensorManager;
    double ax, ay, az;   // these are the acceleration in x,y and z axis


    @Override
    protected Object doInBackground(Object[] objects) {
        if (!this.isCancelled()) {
            sensorManager = (SensorManager) this.context.getSystemService(SENSOR_SERVICE);
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        }

        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!this.isCancelled()) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                ax = event.values[0];
                ay = event.values[1];
                az = event.values[2];
                if(  (int)az==0){
                    System.out.println("here i am");
                    try {
                        if(msgSent==false){
                        SmsManager smsManager = SmsManager.getDefault();
                        DbHandler db=new DbHandler(context);
                        smsManager.sendTextMessage(  db.getContact(1).get_number(), null,   db.getContact(1).get_sms() , null, null);
                        msgSent=true;}

                    }
                    catch (Exception myExp){
                        Log.e("No sold","No sold");
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onCancelled() {

    }

    public SensorTask(Context context) {
        this.context = context;
    }
}
