package djaa9.dk.thepage.hi3group15_201270115;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityA extends Activity implements View.OnClickListener{

    final String TAG = this.getClass().getName();

    Button btnStartTimer;
    EditText etTimerValue;
    Intent toService;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(),intent.getStringExtra(getString(R.string.INTENT_KEY_VALUE)),Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_a);

        btnStartTimer = (Button) findViewById(R.id.btn_start_timer);
        etTimerValue = (EditText) findViewById(R.id.et_timer_inputValue);

        btnStartTimer.setOnClickListener(this);
}

    @Override
    protected void onResume() {
        registerReceiver(receiver, new IntentFilter());
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(receiver);
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_timer :
                startTimerBtnHandler();
                break;
        }
    }

    private void startTimerBtnHandler(){
        Log.d(TAG, String.valueOf(R.string.btn_start_timer_pressed_msg));
        String value = etTimerValue.getText().toString();
        //Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

        toService = new Intent(getApplicationContext(), AlarmService.class);

        toService.putExtra(getString(R.string.INTENT_KEY_VALUE), value);

        startService(toService);
    }
}