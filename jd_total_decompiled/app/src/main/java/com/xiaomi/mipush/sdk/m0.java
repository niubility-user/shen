package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.xiaomi.push.c8;
import com.xiaomi.push.h7;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* loaded from: classes11.dex */
public class m0 {
    public static void b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        long j2 = sharedPreferences.getLong("last_sync_info", -1L);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long a = com.xiaomi.push.service.b0.d(context).a(h7.SyncInfoFrequency.a(), 1209600);
        if (j2 != -1) {
            if (Math.abs(currentTimeMillis - j2) <= a) {
                return;
            }
            d(context, true);
        }
        sharedPreferences.edit().putLong("last_sync_info", currentTimeMillis).commit();
    }

    public static void c(Context context, c8 c8Var) {
        g.j.a.a.a.c.o("need to update local info with: " + c8Var.m35a());
        String str = c8Var.m35a().get("accept_time");
        if (str != null) {
            m.L(context);
            String[] split = str.split("-");
            if (split.length == 2) {
                m.d(context, split[0], split[1]);
                if ("00:00".equals(split[0]) && "00:00".equals(split[1])) {
                    o0.c(context).j(true);
                } else {
                    o0.c(context).j(false);
                }
            }
        }
        String str2 = c8Var.m35a().get("aliases");
        if (str2 != null) {
            m.P(context);
            if (!"".equals(str2)) {
                for (String str3 : str2.split(DYConstants.DY_REGEX_COMMA)) {
                    m.f(context, str3);
                }
            }
        }
        String str4 = c8Var.m35a().get("topics");
        if (str4 != null) {
            m.Q(context);
            if (!"".equals(str4)) {
                for (String str5 : str4.split(DYConstants.DY_REGEX_COMMA)) {
                    m.i(context, str5);
                }
            }
        }
        String str6 = c8Var.m35a().get("user_accounts");
        if (str6 != null) {
            m.O(context);
            if ("".equals(str6)) {
                return;
            }
            for (String str7 : str6.split(DYConstants.DY_REGEX_COMMA)) {
                m.e(context, str7);
            }
        }
    }

    public static void d(Context context, boolean z) {
        com.xiaomi.push.i.b(context).g(new n0(context, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String f(List<String> list) {
        String b = com.xiaomi.push.p0.b(g(list));
        return (TextUtils.isEmpty(b) || b.length() <= 4) ? "" : b.substring(0, 4).toLowerCase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String g(List<String> list) {
        String str = "";
        if (com.xiaomi.push.d.a(list)) {
            return "";
        }
        ArrayList<String> arrayList = new ArrayList(list);
        Collections.sort(arrayList, Collator.getInstance(Locale.CHINA));
        for (String str2 : arrayList) {
            if (!TextUtils.isEmpty(str)) {
                str = str + DYConstants.DY_REGEX_COMMA;
            }
            str = str + str2;
        }
        return str;
    }
}
