package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.push.d4;
import com.xiaomi.push.l4;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes11.dex */
public class MessageHandleService extends BaseService {
    private static ConcurrentLinkedQueue<a> a = new ConcurrentLinkedQueue<>();

    /* renamed from: a */
    private static ExecutorService f102a = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* loaded from: classes11.dex */
    public static class a {
        private PushMessageReceiver a;
        private Intent b;

        public a(Intent intent, PushMessageReceiver pushMessageReceiver) {
            this.a = pushMessageReceiver;
            this.b = intent;
        }

        public Intent a() {
            return this.b;
        }

        public PushMessageReceiver b() {
            return this.a;
        }
    }

    public static void a(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        b(context);
    }

    public static void a(Context context, a aVar) {
        String str;
        String[] stringArrayExtra;
        if (aVar == null) {
            return;
        }
        try {
            PushMessageReceiver b = aVar.b();
            Intent a2 = aVar.a();
            int intExtra = a2.getIntExtra(RemoteMessageConst.MSGTYPE, 1);
            if (intExtra == 1) {
                PushMessageHandler.a b2 = d0.e(context).b(a2);
                int intExtra2 = a2.getIntExtra("eventMessageType", -1);
                if (b2 == null) {
                    str = "no message from raw for receiver";
                } else if (b2 instanceof MiPushMessage) {
                    MiPushMessage miPushMessage = (MiPushMessage) b2;
                    if (!miPushMessage.isArrivedMessage()) {
                        b.onReceiveMessage(context, miPushMessage);
                    }
                    if (miPushMessage.getPassThrough() == 1) {
                        d4.a(context.getApplicationContext()).d(context.getPackageName(), a2, 2004, null);
                        g.j.a.a.a.c.C("MessageHandleService", "begin execute onReceivePassThroughMessage from " + miPushMessage.getMessageId());
                        b.onReceivePassThroughMessage(context, miPushMessage);
                        return;
                    } else if (!miPushMessage.isNotified()) {
                        g.j.a.a.a.c.C("MessageHandleService", "begin execute onNotificationMessageArrived from " + miPushMessage.getMessageId());
                        b.onNotificationMessageArrived(context, miPushMessage);
                        return;
                    } else {
                        if (intExtra2 == 1000) {
                            d4.a(context.getApplicationContext()).d(context.getPackageName(), a2, 1007, null);
                        } else {
                            d4.a(context.getApplicationContext()).d(context.getPackageName(), a2, 3007, null);
                        }
                        g.j.a.a.a.c.C("MessageHandleService", "begin execute onNotificationMessageClicked from\u3000" + miPushMessage.getMessageId());
                        b.onNotificationMessageClicked(context, miPushMessage);
                        return;
                    }
                } else if (b2 instanceof MiPushCommandMessage) {
                    MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) b2;
                    g.j.a.a.a.c.C("MessageHandleService", "begin execute onCommandResult, command=" + miPushCommandMessage.getCommand() + ", resultCode=" + miPushCommandMessage.getResultCode() + ", reason=" + miPushCommandMessage.getReason());
                    b.onCommandResult(context, miPushCommandMessage);
                    if (!TextUtils.equals(miPushCommandMessage.getCommand(), l4.COMMAND_REGISTER.f172a)) {
                        return;
                    }
                    b.onReceiveRegisterResult(context, miPushCommandMessage);
                    PushMessageHandler.a(context, miPushCommandMessage);
                    if (miPushCommandMessage.getResultCode() != 0) {
                        return;
                    }
                } else {
                    str = "unknown raw message: " + b2;
                }
                g.j.a.a.a.c.C("MessageHandleService", str);
                return;
            } else if (intExtra != 3) {
                if (intExtra == 5 && "error_lack_of_permission".equals(a2.getStringExtra("error_type")) && (stringArrayExtra = a2.getStringArrayExtra("error_message")) != null) {
                    g.j.a.a.a.c.E("begin execute onRequirePermissions, lack of necessary permissions");
                    b.onRequirePermissions(context, stringArrayExtra);
                    return;
                }
                return;
            } else {
                MiPushCommandMessage miPushCommandMessage2 = (MiPushCommandMessage) a2.getSerializableExtra("key_command");
                g.j.a.a.a.c.E("(Local) begin execute onCommandResult, command=" + miPushCommandMessage2.getCommand() + ", resultCode=" + miPushCommandMessage2.getResultCode() + ", reason=" + miPushCommandMessage2.getReason());
                b.onCommandResult(context, miPushCommandMessage2);
                if (!TextUtils.equals(miPushCommandMessage2.getCommand(), l4.COMMAND_REGISTER.f172a)) {
                    return;
                }
                b.onReceiveRegisterResult(context, miPushCommandMessage2);
                PushMessageHandler.a(context, miPushCommandMessage2);
                if (miPushCommandMessage2.getResultCode() != 0) {
                    return;
                }
            }
            v0.l(context);
        } catch (RuntimeException e2) {
            g.j.a.a.a.c.q("MessageHandleService", e2);
        }
    }

    public static void addJob(Context context, a aVar) {
        if (aVar != null) {
            a.add(aVar);
            b(context);
            startService(context);
        }
    }

    private static void b(Context context) {
        if (f102a.isShutdown()) {
            return;
        }
        f102a.execute(new m1(context));
    }

    public static void c(Context context) {
        try {
            a(context, a.poll());
        } catch (RuntimeException e2) {
            g.j.a.a.a.c.s(e2);
        }
    }

    public static void startService(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, MessageHandleService.class));
        com.xiaomi.push.i.b(context).g(new l1(context, intent));
    }

    @Override // com.xiaomi.mipush.sdk.BaseService
    /* renamed from: a */
    protected boolean mo29a() {
        ConcurrentLinkedQueue<a> concurrentLinkedQueue = a;
        return concurrentLinkedQueue != null && concurrentLinkedQueue.size() > 0;
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public void onStart(Intent intent, int i2) {
        super.onStart(intent, i2);
    }
}
