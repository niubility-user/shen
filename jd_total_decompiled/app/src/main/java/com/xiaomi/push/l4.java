package com.xiaomi.push;

import android.text.TextUtils;

/* loaded from: classes11.dex */
public enum l4 {
    COMMAND_REGISTER("register"),
    COMMAND_UNREGISTER("unregister"),
    COMMAND_SET_ALIAS("set-alias"),
    COMMAND_UNSET_ALIAS("unset-alias"),
    COMMAND_SET_ACCOUNT("set-account"),
    COMMAND_UNSET_ACCOUNT("unset-account"),
    COMMAND_SUBSCRIBE_TOPIC("subscribe-topic"),
    COMMAND_UNSUBSCRIBE_TOPIC("unsubscibe-topic"),
    COMMAND_SET_ACCEPT_TIME("accept-time"),
    COMMAND_CHK_VDEVID("check-vdeviceid");
    

    /* renamed from: a */
    public final String f172a;

    l4(String str) {
        this.f172a = str;
    }

    public static int a(String str) {
        int i2 = -1;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        for (l4 l4Var : values()) {
            if (l4Var.f172a.equals(str)) {
                i2 = c4.b(l4Var);
            }
        }
        return i2;
    }
}
