package djaa9.dk.thepage.hi3group15_201270115;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

public class Counter implements Runnable {
    private final String TAG = this.getClass().getName();
    private int timeInMilliSec;
    private Context context;

    CountDownTimer myTimer;

    @Override
    public void run() {

        new CountDownTimer(timeInMilliSec, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "seconds remaining: " + millisUntilFinished / 1000);

                Intent counterIntent = new Intent(context, ActivityA.class);
                counterIntent.putExtra(context.getString(R.string.INTENT_KEY_VALUE), millisUntilFinished/1000);

                context.sendBroadcast(counterIntent, null);
            }

            public void onFinish() {
                Log.d(TAG, "Counter done!");
            }
        }.start();
    }

    public Counter(Context _context, int sec) {
        this.timeInMilliSec = sec * 1000;
        this.context = _context;
    }

}
