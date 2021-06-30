package com.example.weightrecord;

import androidx.fragment.app.Fragment;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.weightrecord.data.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static androidx.core.content.ContextCompat.getSystemService;


public class SettingFragment extends Fragment implements View.OnClickListener{


    Button btn_time,  btn_done;
    View view;
    String timeTonotify;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//
    }
//
//    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_setting, container, false);

        btn_time = view.findViewById(R.id.btn_time);
        btn_done = view.findViewById(R.id.btn_done);
        btn_time.setOnClickListener(this);
        btn_done.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_time:
                setnotifiacationTime();
                break;
            case R.id.btn_done:
                settingDone();
                break;
            

        }
    }

    private void settingDone() {
        if (btn_time.getText().toString().equals("Select Time") ) {
            Toast.makeText(getActivity(), "Please select time", Toast.LENGTH_SHORT).show();
        } else {
            User user1 = new User();
            String time = (btn_time.getText().toString().trim());

            user1.setEventtime(time);
            setAlarm(time);
            Toast.makeText(getActivity(), "Setting Successful", Toast.LENGTH_SHORT).show();


        }

    }

    private void setnotifiacationTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeTonotify = i + ":" + i1;
                btn_time.setText(FormatTime(i, i1));
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }

    public String FormatTime(int hour, int minute) {

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }


        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }


        return time;
    }

    private void setAlarm( String time) {
        AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getActivity().getApplicationContext(), AlarmBrodcast.class);

        intent.putExtra("time", time);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = timeTonotify;
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        try {
            Date date1 = formatter.parse(dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        getActivity().finish();

    }
//    private void scheduleNotification(Notification notification, int delay) {
//
//        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
//        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
//        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        long futureInMillis = SystemClock.elapsedRealtime() + delay;
//        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
//    }
//
//    private Notification getNotification(String content) {
//        Notification.Builder builder = new Notification.Builder(this);
//        builder.setContentTitle("Scheduled Notification");
//        builder.setContentText(content);
//        builder.setSmallIcon(R.drawable.ic_launcher);
//        return builder.build();
//    }
////

}