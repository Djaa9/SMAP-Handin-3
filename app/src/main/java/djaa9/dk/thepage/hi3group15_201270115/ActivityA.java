package djaa9.dk.thepage.hi3group15_201270115;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ActivityA extends Activity implements View.OnClickListener{

    Button btnStartTimer;
    EditText etTimerValue;
    ProgressBar countdownProgressBar;
    Intent startServiceIntent;
    int progress;
    int remainingTime;
    boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_a);

        btnStartTimer = (Button) findViewById(R.id.btn_start_timer);
        etTimerValue = (EditText) findViewById(R.id.et_timer_inputValue);
        countdownProgressBar = (ProgressBar) findViewById(R.id.pgb_countdown);

        countdownProgressBar.setProgress(0);
        btnStartTimer.setOnClickListener(this);
        Toast.makeText(getApplicationContext(),"YI", Toast.LENGTH_SHORT).show();
}

    /** Register the BroadCastReceiver in onResume*/
    @Override
    protected void onResume() {
        registerReceiver(receiver, new IntentFilter(getApplicationContext().getPackageName() + ActivityA.class.getName()));
        countdownProgressBar.setProgress(0);
        super.onResume();
    }

    /** Unregister the BroadcastReceiver in onPause*/
    @Override
    protected void onPause() {
        unregisterReceiver(receiver);
        super.onPause();
    }

    /** btnStartTimer.setOnClickListener(this) */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_timer :
                startTimerBtnHandler();
                break;
        }
    }

    /**onClick btn_start_timer - Creates an intent to start AlarmService*/
    private void startTimerBtnHandler(){
        String value = etTimerValue.getText().toString();

        startServiceIntent = new Intent(getApplicationContext(), AlarmService.class);
        startServiceIntent.putExtra(getString(R.string.INTENT_KEY_VALUE), value);
        startService(startServiceIntent);
    }

    /** Receives and Handles intents from AlarmService*/
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            remainingTime = (int)intent.getLongExtra(getString(R.string.INTENT_KEY_VALUE),0);

            if (firstTime){
                countdownProgressBar.setMax(remainingTime);
                firstTime = false;
            }
            else{
                progress = countdownProgressBar.getMax () - remainingTime;
                countdownProgressBar.setProgress(progress);
            }
        }
    };
}