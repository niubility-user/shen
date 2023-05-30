package performance.jd.jdreportperformance.entity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import performance.jd.jdreportperformance.b.b.d;

/* loaded from: classes.dex */
public class NetworkChangedReceiver extends BroadcastReceiver {
    private static NetworkChangedReceiver a;

    public static NetworkChangedReceiver a() {
        if (a == null) {
            synchronized (NetworkChangedReceiver.class) {
                if (a == null) {
                    a = new NetworkChangedReceiver();
                }
            }
        }
        return a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            d.d(context);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
