package djaa9.dk.thepage.hi3group15_201270115;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityB extends Activity {

    TextView tvMsgValue;
    int intentExtra;

    /* Created by PendingIntent, sets ContentView, and show intent Extra**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_b);

        tvMsgValue = (TextView) findViewById(R.id.tv_show_recMsgVal);

        // Get Value from Intent Extra and display in TextView
        intentExtra = getIntent().getIntExtra(getString(R.string.INTENT_KEY_VALUE),0);
        tvMsgValue.setText(String.valueOf(intentExtra));
    }

}
