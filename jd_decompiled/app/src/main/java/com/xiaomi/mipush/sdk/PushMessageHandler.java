package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.MessageHandleService;
import com.xiaomi.mipush.sdk.m;
import com.xiaomi.push.d4;
import com.xiaomi.push.g7;
import com.xiaomi.push.l4;
import com.xiaomi.push.m8;
import com.xiaomi.push.r9;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes11.dex */
public class PushMessageHandler extends BaseService {
    private static List<m.a> a = new ArrayList();
    private static List<m.b> b = new ArrayList();

    /* renamed from: a */
    private static ThreadPoolExecutor f104a = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* loaded from: classes11.dex */
    public interface a extends Serializable {
    }

    public static void a() {
        synchronized (b) {
            b.clear();
        }
    }

    public static void a(long j2, String str, String str2) {
        synchronized (b) {
            Iterator<m.b> it = b.iterator();
            while (it.hasNext()) {
                it.next().c(j2, str, str2);
            }
        }
    }

    public static void a(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, PushMessageHandler.class));
        try {
            context.startService(intent);
        } catch (Exception e2) {
            g.j.a.a.a.c.p("PushMessageHandler", e2.getMessage());
        }
    }

    public static void a(Context context, Intent intent) {
        g.j.a.a.a.c.z("PushMessageHandler", "addjob PushMessageHandler " + intent);
        if (intent != null) {
            c(context, intent);
            a(context);
        }
    }

    private static void a(Context context, Intent intent, ResolveInfo resolveInfo, boolean z) {
        try {
            MessageHandleService.a aVar = new MessageHandleService.a(intent, (PushMessageReceiver) r9.c(context, resolveInfo.activityInfo.name).newInstance());
            if (z) {
                MessageHandleService.a(context.getApplicationContext(), aVar);
            } else {
                MessageHandleService.addJob(context.getApplicationContext(), aVar);
            }
            MessageHandleService.a(context, new Intent(context.getApplicationContext(), MessageHandleService.class));
        } catch (Throwable th) {
            g.j.a.a.a.c.s(th);
        }
    }

    public static void a(Context context, MiPushCommandMessage miPushCommandMessage) {
        synchronized (a) {
            for (m.a aVar : a) {
            }
        }
    }

    public static void a(Context context, MiPushMessage miPushMessage) {
        synchronized (b) {
            for (m.b bVar : b) {
                if (a(miPushMessage.getCategory(), bVar.a())) {
                    bVar.e(miPushMessage.getContent(), miPushMessage.getAlias(), miPushMessage.getTopic(), miPushMessage.isNotified());
                    bVar.d(miPushMessage);
                }
            }
        }
    }

    public static void a(Context context, a aVar) {
        if (aVar instanceof MiPushMessage) {
            a(context, (MiPushMessage) aVar);
        } else if (aVar instanceof MiPushCommandMessage) {
            MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) aVar;
            String command = miPushCommandMessage.getCommand();
            String str = null;
            if (l4.COMMAND_REGISTER.f172a.equals(command)) {
                List<String> commandArguments = miPushCommandMessage.getCommandArguments();
                if (commandArguments != null && !commandArguments.isEmpty()) {
                    str = commandArguments.get(0);
                }
                a(miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            } else if (l4.COMMAND_SET_ALIAS.f172a.equals(command) || l4.COMMAND_UNSET_ALIAS.f172a.equals(command) || l4.COMMAND_SET_ACCEPT_TIME.f172a.equals(command)) {
                a(context, miPushCommandMessage.getCategory(), command, miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), miPushCommandMessage.getCommandArguments());
            } else if (l4.COMMAND_SUBSCRIBE_TOPIC.f172a.equals(command)) {
                List<String> commandArguments2 = miPushCommandMessage.getCommandArguments();
                if (commandArguments2 != null && !commandArguments2.isEmpty()) {
                    str = commandArguments2.get(0);
                }
                a(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            } else if (l4.COMMAND_UNSUBSCRIBE_TOPIC.f172a.equals(command)) {
                List<String> commandArguments3 = miPushCommandMessage.getCommandArguments();
                if (commandArguments3 != null && !commandArguments3.isEmpty()) {
                    str = commandArguments3.get(0);
                }
                b(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            }
        }
    }

    public static void a(Context context, String str, long j2, String str2, String str3) {
        synchronized (b) {
            for (m.b bVar : b) {
                if (a(str, bVar.a())) {
                    bVar.f(j2, str2, str3);
                }
            }
        }
    }

    public static void a(Context context, String str, String str2, long j2, String str3, List<String> list) {
        synchronized (b) {
            for (m.b bVar : b) {
                if (a(str, bVar.a())) {
                    bVar.b(str2, j2, str3, list);
                }
            }
        }
    }

    public static void a(m.a aVar) {
        synchronized (a) {
            if (!a.contains(aVar)) {
                a.add(aVar);
            }
        }
    }

    public static void a(m.b bVar) {
        synchronized (b) {
            if (!b.contains(bVar)) {
                b.add(bVar);
            }
        }
    }

    protected static boolean a(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || TextUtils.equals(str, str2);
    }

    public static void b() {
        synchronized (a) {
            a.clear();
        }
    }

    private static void b(Context context) {
        try {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setAction("action_clicked_activity_finish");
            context.sendBroadcast(intent, q0.a(context));
        } catch (Exception e2) {
            g.j.a.a.a.c.p("PushMessageHandler", "callback sync error" + e2);
        }
    }

    public static void b(Context context, Intent intent) {
        boolean z;
        try {
            z = intent.getBooleanExtra("is_clicked_activity_call", false);
        } catch (Throwable th) {
            g.j.a.a.a.c.p("PushMessageHandler", "intent unparcel error:" + th);
            z = false;
        }
        try {
            g.j.a.a.a.c.r("PushMessageHandler", "-->onHandleIntent(): action=", intent.getAction());
            ResolveInfo resolveInfo = null;
            if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
                b1.a(context, intent, null);
            } else if ("com.xiaomi.mipush.SEND_TINYDATA".equals(intent.getAction())) {
                g7 g7Var = new g7();
                m8.e(g7Var, intent.getByteArrayExtra("mipush_payload"));
                g.j.a.a.a.c.z("PushMessageHandler", "PushMessageHandler.onHandleIntent " + g7Var.d());
                o.a(context, g7Var);
            } else if (1 == q.c(context)) {
                if (m28b()) {
                    g.j.a.a.a.c.t("PushMessageHandler", "receive a message before application calling initialize");
                    if (z) {
                        b(context);
                        return;
                    }
                    return;
                }
                a b2 = d0.e(context).b(intent);
                if (b2 != null) {
                    a(context, b2);
                }
            } else if (!"com.xiaomi.mipush.sdk.SYNC_LOG".equals(intent.getAction())) {
                Intent intent2 = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
                intent2.setPackage(context.getPackageName());
                intent2.putExtras(intent);
                try {
                    List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent2, 32);
                    if (queryBroadcastReceivers != null) {
                        Iterator<ResolveInfo> it = queryBroadcastReceivers.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            ResolveInfo next = it.next();
                            ActivityInfo activityInfo = next.activityInfo;
                            if (activityInfo != null && activityInfo.packageName.equals(context.getPackageName()) && PushMessageReceiver.class.isAssignableFrom(r9.c(context, next.activityInfo.name))) {
                                resolveInfo = next;
                                break;
                            }
                        }
                    }
                    if (resolveInfo != null) {
                        a(context, intent2, resolveInfo, z);
                    } else {
                        g.j.a.a.a.c.t("PushMessageHandler", "cannot find the receiver to handler this message, check your manifest");
                        d4.a(context).e(context.getPackageName(), intent, "11");
                    }
                } catch (Exception e2) {
                    g.j.a.a.a.c.q("PushMessageHandler", e2);
                    d4.a(context).e(context.getPackageName(), intent, "9");
                }
            }
            if (!z) {
            }
        } catch (Throwable th2) {
            try {
                g.j.a.a.a.c.q("PushMessageHandler", th2);
                d4.a(context).e(context.getPackageName(), intent, "10");
            } finally {
                if (z) {
                    b(context);
                }
            }
        }
    }

    protected static void b(Context context, String str, long j2, String str2, String str3) {
        synchronized (b) {
            for (m.b bVar : b) {
                if (a(str, bVar.a())) {
                    bVar.g(j2, str2, str3);
                }
            }
        }
    }

    /* renamed from: b */
    public static boolean m28b() {
        return b.isEmpty();
    }

    private static void c(Context context, Intent intent) {
        if (intent != null && !f104a.isShutdown()) {
            f104a.execute(new c0(context, intent));
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("-->scheduleJob() fail, case");
        sb.append(intent == null ? "0" : "1");
        g.j.a.a.a.c.C("PushMessageHandler", sb.toString());
    }

    @Override // com.xiaomi.mipush.sdk.BaseService
    /* renamed from: a */
    protected boolean mo29a() {
        ThreadPoolExecutor threadPoolExecutor = f104a;
        return (threadPoolExecutor == null || threadPoolExecutor.getQueue() == null || f104a.getQueue().size() <= 0) ? false : true;
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public void onStart(Intent intent, int i2) {
        super.onStart(intent, i2);
        c(getApplicationContext(), intent);
    }
}
