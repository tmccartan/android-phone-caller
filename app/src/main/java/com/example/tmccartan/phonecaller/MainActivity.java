package com.example.tmccartan.phonecaller;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionManager.check(this, Manifest.permission.RECEIVE_SMS, 1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode== 1 ){//response for SMS permission request
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                //What to do if User allowed SMS permission
                PermissionManager.check(this, Manifest.permission.CALL_PHONE, 2);

            } else {
                //Shouldnt happen for this example
            }
        } else if (requestCode == 2) {
            //What to do if User allowed Call permission
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                BroadcastReceiver br = new SMSApp();
                IntentFilter filter = new IntentFilter();
                filter.addAction("android.provider.Telephony.SMS_RECEIVED");
                this.registerReceiver(br, filter);
            } else {
                // Shouldnt happen for this example
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
