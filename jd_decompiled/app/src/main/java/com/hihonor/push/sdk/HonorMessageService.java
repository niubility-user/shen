package com.hihonor.push.sdk;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import com.hihonor.push.sdk.common.data.DownMsgType;

/* loaded from: classes12.dex */
public abstract class HonorMessageService extends Service {

    /* renamed from: c  reason: collision with root package name */
    public static final /* synthetic */ int f1077c = 0;
    public final a a;
    public final Messenger b;

    /* loaded from: classes12.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            if (data != null) {
                Intent intent = new Intent();
                intent.putExtras(data);
                HonorMessageService honorMessageService = HonorMessageService.this;
                int i2 = HonorMessageService.f1077c;
                honorMessageService.a(intent);
            }
        }
    }

    public HonorMessageService() {
        a aVar = new a(Looper.getMainLooper());
        this.a = aVar;
        this.b = new Messenger(aVar);
    }

    public final void a(Intent intent) {
        try {
            if (TextUtils.equals(intent.getStringExtra("event_type"), DownMsgType.RECEIVE_TOKEN)) {
                String stringExtra = intent.getStringExtra("push_token");
                Context a2 = f0.f1091e.a();
                o oVar = o.b;
                if (!TextUtils.equals(stringExtra, oVar.c(a2))) {
                    oVar.b(a2, stringExtra);
                }
                onNewToken(stringExtra);
                return;
            }
            e b = i.b(new m0(intent));
            f fVar = new f(this);
            b.getClass();
            b.a(new q0(l0.f1101c.a, fVar));
        } catch (Exception e2) {
            String str = "dispatch message error. " + e2.getMessage();
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.b.getBinder();
    }

    public void onMessageReceived(c cVar) {
    }

    public void onNewToken(String str) {
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i2, int i3) {
        super.onStartCommand(intent, i2, i3);
        a(intent);
        return 2;
    }
}
