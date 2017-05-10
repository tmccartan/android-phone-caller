package com.example.tmccartan.phonecaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSApp extends BroadcastReceiver {

    private SharedPreferences preferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                        Toast.makeText(context, msg_from, Toast.LENGTH_SHORT).show();

                        if(msg_from.equals("INSERT_SMS_SENDER_PHONE_NUMBER_HERE") && msgBody.contains("gateopen")){
                            Intent callIntent = new Intent(Intent.ACTION_CALL,  Uri.parse("tel: INSERT_TARGET_NUMBER_HERE"));
                            try{
                                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(callIntent);
                            } catch (Exception ex) {
                                Log.e("Terry", ex.getMessage());
                                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                } catch (Exception e){
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }
}