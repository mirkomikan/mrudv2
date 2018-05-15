package info.mik.mrud;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import static android.support.v4.app.NotificationManagerCompat.IMPORTANCE_DEFAULT;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "info.mik.mrud.channelId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyTimerTask myTask = new MyTimerTask();
        Timer myTimer = new Timer();

        myTimer.schedule(myTask, 10000, 10000);
    }

    class MyTimerTask extends TimerTask {
        public void run() {
            generateNotification(getApplicationContext(), "New Notification from MRU-D");
        }
    }

    private void generateNotification(Context context, String message) {

        int icon = R.mipmap.ic_launcher;
        long when = System.currentTimeMillis();
        String appName = context.getResources().getString(R.string.app_name);

        Notification myNotification;
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0,
                new Intent(context, MainActivity.class),
                0);

        // This builder is used to build a Notification object which can be used by
        // NotificationManger to notify the user.
        Notification.Builder builder = new Notification.Builder(context);

        // NotificationManager will do the job of sending the notification to the user
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }

        // Let's add the sound to our Notification
        Uri alarmSound = RingtoneManager.getDefaultUri(
                RingtoneManager.TYPE_NOTIFICATION);

        myNotification = builder.setContentIntent(contentIntent)
                    .setSmallIcon(icon)
                    .setSound(alarmSound)
                    .setTicker("New Notification Alert!")
                    .setWhen(0)
                    .setAutoCancel(true)
                    .setContentTitle("MRU-D Notification")
                    .setContentText(message)
                    .build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "MRU-D Notification",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify((int) when, myNotification);
    }
}