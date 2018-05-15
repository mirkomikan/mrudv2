# mrudv2
MRU-D-v2-TimerNotifications

MRU-Dv2 GLOBAL TIMER (Specs part 10)
------------------------------------
NotificationManager  
Timer  
alarmSound   

DONE:
+ Global Timer to create a local notification that will pop up on every 5 minutes (shortened to 10 seconds for easier testing).
+ Used AlarmManager and TimerTask to schedule generateNotification
+ Notification will pop-up at the Notification Bar every 10 seconds, and will NOT be grouped, so overloading is possible.
+ Tapping on Notification will clear it and open main app view.
