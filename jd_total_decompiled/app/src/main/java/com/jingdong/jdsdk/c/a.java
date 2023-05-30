package com.jingdong.jdsdk.c;

import android.content.Context;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.jdroom.JDDataBase;
import com.jingdong.sdk.jdroom.a.c;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes14.dex */
public class a {
    private static final String a = "a";

    public static final void a(Context context) {
        b(context);
    }

    private static void b(Context context) {
        if (OKLog.D) {
            OKLog.d(a, "\u5f00\u59cb\u6570\u636e\u8fc1\u79fb");
        }
        if (SharedPreferencesUtil.getBoolean("jd_sp_db_reminder_checked", false)) {
            if (OKLog.D) {
                OKLog.d(a, "SP\u6807\u8bb0\u5df2\u7ecf\u8fc1\u79fb\u8fc7\u6570\u636e");
                return;
            }
            return;
        }
        try {
            if (com.jingdong.jdsdk.c.b.a.d()) {
                if (OKLog.D) {
                    OKLog.d(a, "OldReminderTableEmpty");
                }
            } else if (JDDataBase.a(context).b().l() != 0) {
                if (OKLog.D) {
                    OKLog.d(a, "NewReminderTableIsNotEmpty");
                }
            } else {
                ArrayList<c> b = com.jingdong.jdsdk.c.b.a.b();
                if (b.isEmpty()) {
                    if (OKLog.D) {
                        OKLog.d(a, "\u67e5\u8be2\u65e7\u8868\u6570\u636e\u4e3a\u7a7a");
                        return;
                    }
                    return;
                }
                JDDataBase.a(context).beginTransaction();
                JDDataBase.a(context).b().b((c[]) b.toArray(new c[b.size()]));
                JDDataBase.a(context).setTransactionSuccessful();
                JDDataBase.a(context).endTransaction();
                com.jingdong.jdsdk.c.b.a.a();
                SharedPreferencesUtil.putBoolean("jd_sp_db_reminder_checked", true);
                if (OKLog.D) {
                    OKLog.d(a, "\u6570\u636e\u8fc1\u79fb\u7ed3\u675f");
                }
            }
        } catch (Throwable unused) {
            if (OKLog.D) {
                OKLog.d(a, "\u6570\u636e\u8fc1\u79fb\u5f02\u5e38");
            }
        }
    }
}
