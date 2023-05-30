package com.jingdong.app.mall.home.floor.ctrl;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class g {
    private static String a;
    private static String b;

    /* renamed from: c  reason: collision with root package name */
    private static String f9404c;
    private static String d;

    /* renamed from: e  reason: collision with root package name */
    private static String f9405e;

    /* renamed from: f  reason: collision with root package name */
    private static String f9406f;

    public g(String str) {
        a = "home_xV_" + str + "_total_m_s_t";
        b = "home_xV_" + str + "_day_m_s_t";
        f9404c = "home_xV_" + str + "_total_s_t";
        d = "home_xV_" + str + "_day_s_t";
        f9405e = "home_xV_" + str + "_k_s_v";
        f9406f = "home_xV_" + str + "_s_date";
    }

    private void a() {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putInt(f9404c, 0);
        edit.putInt(d, 0);
        edit.apply();
    }

    private int c() {
        int currentTimeMillis = (int) (((System.currentTimeMillis() / 1000) / 3600) / 24);
        int intFromPreference = CommonBase.getIntFromPreference(f9406f, 0);
        int intFromPreference2 = CommonBase.getIntFromPreference(d, 0);
        if (Log.D) {
            Log.d("FloorXviewShowTimesCtrl", "getTodayShowTimes day:" + currentTimeMillis + " lastSaveDay:" + intFromPreference);
        }
        if (currentTimeMillis != intFromPreference) {
            SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
            edit.putInt(f9406f, currentTimeMillis);
            edit.putInt(d, 0);
            edit.apply();
            return 0;
        }
        return intFromPreference2;
    }

    private void d(String str, int i2, int i3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (i2 <= 0) {
            i2 = Integer.MAX_VALUE;
        }
        if (i3 <= 0) {
            i3 = Integer.MAX_VALUE;
        }
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putInt(a, i2);
        edit.putInt(b, i3);
        edit.putString(f9405e, str);
        edit.apply();
        if (Log.D) {
            Log.d("FloorXviewShowTimesCtrl", "save times maxTotal:" + i2 + " maxDaily:" + i3);
        }
    }

    public void b(String str, int i2, int i3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String stringFromPreference = CommonBase.getStringFromPreference(f9405e, "");
        if (TextUtils.isEmpty(stringFromPreference) || !str.equals(stringFromPreference)) {
            a();
        }
        d(str, i2, i3);
    }

    public void e() {
        int c2 = c() + 1;
        int intFromPreference = CommonBase.getIntFromPreference(f9404c, 0) + 1;
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putInt(d, c2);
        edit.putInt(f9404c, intFromPreference);
        edit.apply();
        if (Log.D) {
            Log.d("FloorXviewShowTimesCtrl", "update times todayTimes:" + c2 + " totalTimes:" + intFromPreference);
        }
    }
}
