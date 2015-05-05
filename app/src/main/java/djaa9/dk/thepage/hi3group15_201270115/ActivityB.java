package djaa9.dk.thepage.hi3group15_201270115;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityB extends Activity {

    TextView tvMsgValue;
    int recMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_b);

        tvMsgValue = (TextView) findViewById(R.id.tv_show_recMsgVal);

        Intent myIntent = getIntent();
        recMsg = myIntent.getIntExtra(getString(R.string.INTENT_KEY_VALUE),0);

        tvMsgValue.setText(String.valueOf(recMsg));

    }


}
