package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.xiaomi.push.g8;
import com.xiaomi.push.p7;
import java.util.List;

/* loaded from: classes11.dex */
public class q {
    private static int a;

    public static MiPushCommandMessage a(String str, List<String> list, long j2, String str2, String str3, List<String> list2) {
        MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
        miPushCommandMessage.setCommand(str);
        miPushCommandMessage.setCommandArguments(list);
        miPushCommandMessage.setResultCode(j2);
        miPushCommandMessage.setReason(str2);
        miPushCommandMessage.setCategory(str3);
        miPushCommandMessage.setAutoMarkPkgs(list2);
        return miPushCommandMessage;
    }

    public static MiPushMessage b(g8 g8Var, p7 p7Var, boolean z) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.setMessageId(g8Var.m70a());
        if (!TextUtils.isEmpty(g8Var.d())) {
            miPushMessage.setMessageType(1);
            miPushMessage.setAlias(g8Var.d());
        } else if (!TextUtils.isEmpty(g8Var.c())) {
            miPushMessage.setMessageType(2);
            miPushMessage.setTopic(g8Var.c());
        } else if (TextUtils.isEmpty(g8Var.f())) {
            miPushMessage.setMessageType(0);
        } else {
            miPushMessage.setMessageType(3);
            miPushMessage.setUserAccount(g8Var.f());
        }
        miPushMessage.setCategory(g8Var.e());
        if (g8Var.a() != null) {
            miPushMessage.setContent(g8Var.a().c());
        }
        if (p7Var != null) {
            if (TextUtils.isEmpty(miPushMessage.getMessageId())) {
                miPushMessage.setMessageId(p7Var.m120a());
            }
            if (TextUtils.isEmpty(miPushMessage.getTopic())) {
                miPushMessage.setTopic(p7Var.m125b());
            }
            miPushMessage.setDescription(p7Var.d());
            miPushMessage.setTitle(p7Var.m128c());
            miPushMessage.setNotifyType(p7Var.a());
            miPushMessage.setNotifyId(p7Var.c());
            miPushMessage.setPassThrough(p7Var.b());
            miPushMessage.setExtra(p7Var.m121a());
        }
        miPushMessage.setNotified(z);
        return miPushMessage;
    }

    public static int c(Context context) {
        if (a == 0) {
            g(e(context) ? 1 : 2);
        }
        return a;
    }

    private static boolean d(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            if (queryBroadcastReceivers != null) {
                if (!queryBroadcastReceivers.isEmpty()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean e(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setClassName(context.getPackageName(), "com.xiaomi.mipush.sdk.PushServiceReceiver");
        return d(context, intent);
    }

    public static void f(Context context, MiPushCommandMessage miPushCommandMessage) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra(RemoteMessageConst.MSGTYPE, 3);
        intent.putExtra("key_command", miPushCommandMessage);
        new PushServiceReceiver().onReceive(context, intent);
    }

    private static void g(int i2) {
        a = i2;
    }
}
