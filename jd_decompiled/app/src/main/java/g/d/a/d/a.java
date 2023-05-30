package g.d.a.d;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.pushsdk.R;
import g.d.a.j.e;
import g.d.a.j.g;

/* loaded from: classes12.dex */
public class a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g.d.a.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class RunnableC0830a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f19430g;

        RunnableC0830a(Context context) {
            this.f19430g = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (e.b().e()) {
                return;
            }
            String string = this.f19430g.getString(R.string.system_default_channel);
            if (TextUtils.isEmpty(string)) {
                string = "System Default Channel";
            }
            e.b().g(a.this.b(this.f19430g, "Heytap PUSH", string, 3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(26)
    public boolean b(Context context, String str, String str2, int i2) {
        NotificationManager notificationManager;
        if (context == null || (notificationManager = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION)) == null) {
            return false;
        }
        notificationManager.createNotificationChannel(new NotificationChannel(str, str2, i2));
        return true;
    }

    public void c(Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        g.a(new RunnableC0830a(context));
    }
}
