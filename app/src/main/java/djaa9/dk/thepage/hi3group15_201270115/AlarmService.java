package djaa9.dk.thepage.hi3group15_201270115;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AlarmService extends IntentService {

    final String TAG = this.getClass().getName();
    long countdownInterval = 500;

    public AlarmService() {
        super("AlarmServiceThread");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int sec = Integer.valueOf(intent.getStringExtra(getString(R.string.INTENT_KEY_VALUE)));

        // SETUP PendingIntent to ChangeActivityHandler
        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(getApplicationContext().ALARM_SERVICE);
        Intent i = new Intent(getApplicationContext(), ChangeActivityHandler.class);
        i.putExtra(getString(R.string.INTENT_KEY_VALUE), sec);

        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0); // Why 0?
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + sec * 1000, pi);

        // Setup Timer to send Intent with countdown to Activity A every second until countdown is 0
        long endTime = System.currentTimeMillis() + sec*1000;
        long remainingTime;

        while (System.currentTimeMillis() < endTime) {
            remainingTime = endTime - System.currentTimeMillis();
            Log.d(TAG, "seconds remaining: " + remainingTime);

            Intent counterIntent = new Intent(getApplicationContext().getPackageName() + ActivityA.class.getName());
            counterIntent.putExtra(getApplicationContext().getString(R.string.INTENT_KEY_VALUE), remainingTime / 1000);
            getApplicationContext().sendBroadcast(counterIntent, null);

            /** Put Thread to sleep for countdownInterval number of ms*/
            synchronized (this) {
                try {
                    wait(countdownInterval);
                } catch (Exception e) {
                }
            }
        }
    }
}

