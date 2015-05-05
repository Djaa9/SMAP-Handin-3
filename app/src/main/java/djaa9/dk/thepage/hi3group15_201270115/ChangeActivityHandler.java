package djaa9.dk.thepage.hi3group15_201270115;

        import android.app.PendingIntent;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.widget.Toast;

public class ChangeActivityHandler extends BroadcastReceiver {

    final String TAG = this.getClass().getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "timer trigger",Toast.LENGTH_SHORT).show();

        Intent i = new Intent();
        i.setClassName("djaa9.dk.thepage.hi3group15_201270115","djaa9.dk.thepage.hi3group15_201270115.ActivityB");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(context.getString(R.string.INTENT_KEY_VALUE), intent.getIntExtra(context.getString(R.string.INTENT_KEY_VALUE),0));

        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT).cancel();
        context.startActivity(i);
    }
}
