package com.fbm.chocdetectionapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gc.materialdesign.views.Switch;


public class MainActivity extends AppCompatActivity {
    SensorTask sensorTask;
    Switch switchButton;
    Button butEdit;
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

      MyDBHandler db=new MyDBHandler(this);
        Receiver r=new Receiver(1,"124455","hello");
        db.addReceiver(r);
        Receiver r2=new Receiver(1,"000000000","hello");
        db.replace(r2);
        EditText txtPhone= (EditText) findViewById(R.id.txtPhone);
        txtPhone.setText(db.findReceiver().get_number());
        butEdit= (Button) findViewById(R.id.butEdit);
        butEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
