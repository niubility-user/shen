package com.jingdong.jdma.common.utils;

import androidx.annotation.NonNull;
import com.jingdong.jdma.minterface.ISwitchQuery;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;

/* loaded from: classes12.dex */
public class n {
    private static n b;
    private ISwitchQuery a;

    private n() {
    }

    public static n a() {
        if (b == null) {
            synchronized (n.class) {
                if (b == null) {
                    b = new n();
                }
            }
        }
        return b;
    }

    public boolean b() {
        if (c()) {
            return a(RuntimeConfigHelper.HTTPS_SWITCH, "0").equalsIgnoreCase("0");
        }
        return false;
    }

    public boolean c() {
        ISwitchQuery iSwitchQuery = this.a;
        if (iSwitchQuery == null) {
            return false;
        }
        try {
            return iSwitchQuery.isXTime();
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean d() {
        return a("isMaReport", false);
    }

    public void a(ISwitchQuery iSwitchQuery) {
        this.a = iSwitchQuery;
    }

    public String a(@NonNull String str, String str2) {
        ISwitchQuery iSwitchQuery = this.a;
        if (iSwitchQuery == null) {
            return str2;
        }
        try {
            return iSwitchQuery.getValueByKey(str, str2);
        } catch (Throwable unused) {
            return str2;
        }
    }

    public boolean a(@NonNull String str, boolean z) {
        ISwitchQuery iSwitchQuery = this.a;
        if (iSwitchQuery == null) {
            return z;
        }
        try {
            return iSwitchQuery.getValueByKey(str, z);
        } catch (Throwable unused) {
            return z;
        }
    }
}
