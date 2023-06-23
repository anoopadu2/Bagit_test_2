package com.example.bagit_test_2.Tools;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;

public  class Notifications {
    public static void send(String msg, String no, Custom_Activity From){
        Intent intent=new Intent(From.getApplicationContext(),From.getClass());
        PendingIntent pi=PendingIntent.getActivity(From.getApplicationContext(), 0, intent,0);
        SmsManager sms= SmsManager.getDefault();
        sms.sendTextMessage(no, null, msg, pi,null);
    }
}
