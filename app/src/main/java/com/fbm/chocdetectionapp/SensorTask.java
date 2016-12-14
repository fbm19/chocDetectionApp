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

    double ax, ay, az;   // these are the acceleration in x,y and z axis
    boolean msgSent = false;
    private Context context;
    private SensorManager sensorManager;

    public SensorTask(Context context) {
        this.context = context;
    }

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
                if ((int) az == 0) {
                    System.out.println("here i am");
                    //  GpsTracker gpsTracker=new GpsTracker(context);
                    // gpsTracker.getPosition();
                    // System.out.println("u r link is "+gpsTracker.getPosition().getLat());
                    //String linkMap="https://www.google.fr/maps/dir///@"+gpsTracker.getPosition().getLat()+","+gpsTracker.getPosition().getLon()+"z";
                    // System.out.println("u r link is "+linkMap);
                    try {
                        if (this.msgSent == false) {


                            SmsManager smsManager = SmsManager.getDefault();
                            DbHandler db = new DbHandler(context);
                            // String msgContent=db.getContact(1).get_sms()+"The position is   "+linkMap;
                            smsManager.sendTextMessage(db.getContact(1).get_number(), null, db.getContact(1).get_sms(), null, null);
                            msgSent = true;
                        }


                    } catch (Exception myExp) {
                        Log.e("No sold", "No sold");
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
}
