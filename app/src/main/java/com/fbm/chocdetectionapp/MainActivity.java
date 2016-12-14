package com.fbm.chocdetectionapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.Switch;


public class MainActivity extends AppCompatActivity {
    SensorTask sensorTask;
    Switch switchButton;
    Button butEdit, butEdidText;
    EditText txtMsg;
    EditText txtPhone;
    boolean msgSent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMsg = (EditText) findViewById(R.id.txtMsg);
        butEdidText = (Button) findViewById(R.id.butEdidText);
        butEdit = (Button) findViewById(R.id.butEdit);
        switchButton = (Switch) findViewById(R.id.switchView);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        ChangeData();
        changeStatus();
        loadData();

     /*   FallHandler fl = new FallHandler(getApplicationContext());
        Fall fall = new Fall(2, "test");
        fl.addFall(fall);
        System.out.println("your fall length is" + fl.getAllFalls().size());*/
        // check if GPS enabled


    }

    public void ChangeData() {
        txtMsg = (EditText) findViewById(R.id.txtMsg);
        butEdidText = (Button) findViewById(R.id.butEdidText);
        final DbHandler db = new DbHandler(getApplicationContext());
        butEdidText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Receiver r = new Receiver(1, txtMsg.getText().toString(), txtPhone.getText().toString());
                if (db.getAllContacts().size() == 0) {


                    db.addContact(r);
                    Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_LONG).show();
                    System.out.println(db.getContact(1).get_number());
                    switchButton.setEnabled(true);
                } else {
                    db.updateContact(r);

                    Toast.makeText(getApplicationContext(), "data updated", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    void loadData() {
        DbHandler db = new DbHandler(getApplicationContext());
        if (db.getAllContacts().size() != 0) {
            txtMsg.setText(db.getContact(1).get_sms());
            txtPhone.setText(db.getContact(1).get_number());
        } else {
            switchButton.setEnabled(false);
            Toast.makeText(getApplicationContext(), "Please add your contact", Toast.LENGTH_LONG).show();
        }
    }

    public void changeStatus() {

        MainActivity.this.switchButton.setOncheckListener(new Switch.OnCheckListener() {
            @Override
            public void onCheck(Switch aSwitch, boolean status) {
                if (status) {
                    if (msgSent == false) {
                        sensorTask = new SensorTask(getApplicationContext());
                        sensorTask.execute();
                        msgSent = true;
                    }


                } else {
                    sensorTask.cancel(true);
                }

            }
        });
    }


}
