package com.jingdong.manto.p.f;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.jingdong.manto.message.MantoAcrossMessageCenter;
import com.jingdong.manto.message.d;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.sdk.threadpool.ThreadManager;

/* loaded from: classes16.dex */
public final class a {
    private BroadcastReceiver a = new C0650a();

    /* renamed from: com.jingdong.manto.p.f.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    class C0650a extends BroadcastReceiver {
        C0650a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            a.this.a(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements Runnable {
        final /* synthetic */ Context a;

        b(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                this.a.registerReceiver(a.this.a, intentFilter);
            } catch (Exception e2) {
                MantoLog.e("netState", "" + e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        MantoLog.d("MantoListenNetState", "ACROSSDATA_TYPE_NETWORK");
        MantoAcrossMessageCenter.notifyCommonData(new d().a(d.f13863c));
    }

    public void b(Context context) {
        ThreadManager.single().postDelay(new b(context), 500L);
    }
}
