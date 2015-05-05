package djaa9.dk.thepage.hi3group15_201270115;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.IBinder;

public class AlarmService extends IntentService {

    ChangeActivityHandler myAlarm;
    Runnable myCounter;
    Thread counterThread;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public AlarmService(String name) {
        super(name);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myAlarm = new ChangeActivityHandler();

        int sec =  Integer.valueOf(intent.getStringExtra(getString(R.string.INTENT_KEY_VALUE)));

        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(getApplicationContext().ALARM_SERVICE);
        Intent i = new Intent(getApplicationContext(), ChangeActivityHandler.class);
        i.putExtra(getString(R.string.INTENT_KEY_VALUE), sec);

        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(),0,i,0); // Why 0?
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+sec*1000, pi);

        myCounter = new Counter(getApplicationContext(),sec);
        counterThread = new Thread(myCounter);
        counterThread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
