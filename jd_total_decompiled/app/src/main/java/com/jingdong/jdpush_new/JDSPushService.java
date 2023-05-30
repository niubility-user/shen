package com.jingdong.jdpush_new;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import androidx.annotation.Nullable;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.jdpush_new.connect.MsgCenterReceiver;
import com.jingdong.jdpush_new.j.e;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.j.h;

/* loaded from: classes12.dex */
public class JDSPushService extends Service {
    private Handler mHandler;
    private Messenger mMessenger;

    /* loaded from: classes12.dex */
    class a extends Handler {
        a(JDSPushService jDSPushService) {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                int i2 = message.what;
                if (i2 == 0) {
                    Bundle data = message.getData();
                    long currentTimeMillis = data == null ? System.currentTimeMillis() : data.getLong("t");
                    com.jingdong.jdpush_new.mta.a.b().c(currentTimeMillis);
                    com.jingdong.jdpush_new.connect.c.c().i(currentTimeMillis);
                } else if (i2 == 1) {
                    Bundle data2 = message.getData();
                    long currentTimeMillis2 = data2 == null ? System.currentTimeMillis() : data2.getLong("t");
                    com.jingdong.jdpush_new.mta.a.b().d(currentTimeMillis2);
                    com.jingdong.jdpush_new.connect.c.c().j(currentTimeMillis2);
                } else if (i2 == 2) {
                    Bundle data3 = message.getData();
                    com.jingdong.jdpush_new.mta.a.b().h(data3 == null ? System.currentTimeMillis() : data3.getLong("t"));
                    g.a("received CMD_MAIN_FRAME_ACTIVITY_FINISHED_LOAD");
                } else if (i2 != 10) {
                } else {
                    Bundle data4 = message.getData();
                    String readDeviceUUID = data4 == null ? StatisticsReportUtil.readDeviceUUID() : data4.getString("UUID");
                    com.jingdong.jdpush_new.a.b = readDeviceUUID;
                    g.a("received uuid :\u3000" + readDeviceUUID);
                }
            } catch (Throwable th) {
                g.g(th);
            }
        }
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        g.a("onBind->Service");
        com.jingdong.jdpush_new.mta.b.b().l(102010);
        com.jingdong.jdpush_new.connect.c.c().k(this);
        h.b(this);
        return this.mMessenger.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        g.a("onCreate->Service");
        super.onCreate();
        com.jingdong.jdpush_new.mta.b.b().d(this, false);
        com.jingdong.jdpush_new.mta.b.b().l(102000);
        this.mHandler = new a(this);
        this.mMessenger = new Messenger(this.mHandler);
        e.a().b(this);
        MsgCenterReceiver.getReceiver().registBroadcastReceiver(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        g.a("onDestroy->Service");
        com.jingdong.jdpush_new.mta.b.b().l(102030);
        MsgCenterReceiver.getReceiver().unregisterReceiver(this);
        com.jingdong.jdpush_new.connect.c.c().interrupt();
        com.jingdong.jdpush_new.mta.a.b().i();
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        g.a("onStartCommand->Service");
        com.jingdong.jdpush_new.mta.b.b().l(102020);
        com.jingdong.jdpush_new.connect.c.c().k(this);
        h.b(this);
        return 1;
    }
}
