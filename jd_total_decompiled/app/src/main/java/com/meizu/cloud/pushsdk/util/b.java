package com.meizu.cloud.pushsdk.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;

/* loaded from: classes14.dex */
public class b {
    public static void A(Context context, String str, String str2) {
        g(context, PushConstants.PUSH_ID_PREFERENCE_NAME, str2 + CartConstant.KEY_YB_INFO_LINK + PushConstants.KEY_PUSH_ID, str);
    }

    public static String B(Context context, String str) {
        return t(context, PushConstants.PUSH_ID_PREFERENCE_NAME, str + CartConstant.KEY_YB_INFO_LINK + PushConstants.KEY_PUSH_ID);
    }

    public static boolean C(Context context, String str, String str2) {
        return F(context, str).edit().remove(str2).commit();
    }

    public static int D(Context context, String str) {
        return l(context, PushConstants.PUSH_ID_PREFERENCE_NAME, str + CartConstant.KEY_YB_INFO_LINK + PushConstants.KEY_PUSH_ID_EXPIRE_TIME);
    }

    public static boolean E(Context context, String str, String str2) {
        return C(context, "mz_push_preference", str + OrderISVUtil.MONEY_DECIMAL + str2);
    }

    private static SharedPreferences F(Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }

    public static void G(Context context, String str, String str2) {
        g(context, "mz_push_preference", "push_alias_" + str, str2);
    }

    public static boolean H(Context context, String str) {
        return j(context, "mz_push_preference", "switch_through_message_" + str);
    }

    public static boolean I(Context context, String str) {
        return w(context, "mz_push_preference", "switch_notification_message_" + str);
    }

    public static boolean J(Context context, String str) {
        return w(context, "mz_push_preference", "switch_through_message_" + str);
    }

    public static String a(Context context, String str) {
        return t(context, "mz_push_preference", "push_alias_" + str);
    }

    public static void b(Context context, int i2, String str) {
        e(context, PushConstants.PUSH_ID_PREFERENCE_NAME, str + CartConstant.KEY_YB_INFO_LINK + PushConstants.KEY_PUSH_ID_EXPIRE_TIME, i2);
    }

    public static void c(Context context, String str, int i2) {
        e(context, "mz_push_preference", str + ".notification_id", i2);
    }

    public static void d(Context context, String str, long j2) {
        f(context, "mz_push_preference_new", str + ".ad_last_close_time", j2);
    }

    public static void e(Context context, String str, String str2, int i2) {
        F(context, str).edit().putInt(str2, i2).apply();
    }

    public static void f(Context context, String str, String str2, long j2) {
        F(context, str).edit().putLong(str2, j2).apply();
    }

    public static void g(Context context, String str, String str2, String str3) {
        F(context, str).edit().putString(str2, str3).apply();
    }

    public static void h(Context context, String str, String str2, boolean z) {
        F(context, str).edit().putBoolean(str2, z).apply();
    }

    public static void i(Context context, String str, boolean z) {
        h(context, "mz_push_preference", str + ".first_request_publicKey", z);
    }

    public static boolean j(Context context, String str, String str2) {
        return F(context, str).getBoolean(str2, true);
    }

    public static int k(Context context, String str) {
        return F(context, "mz_push_preference").getInt(str + ".notification_id", 0);
    }

    public static int l(Context context, String str, String str2) {
        return F(context, str).getInt(str2, 0);
    }

    public static void m(Context context, String str, int i2) {
        e(context, "mz_push_preference", str + ".notification_push_task_id", i2);
    }

    public static void n(Context context, String str, String str2, int i2) {
        e(context, "mz_push_preference", str + OrderISVUtil.MONEY_DECIMAL + str2, i2);
    }

    public static void o(Context context, String str, boolean z) {
        h(context, "mz_push_preference", "switch_notification_message_" + str, z);
    }

    public static int p(Context context, String str) {
        return F(context, "mz_push_preference").getInt(str + ".notification_push_task_id", 0);
    }

    public static int q(Context context, String str, String str2) {
        return l(context, "mz_push_preference", str + OrderISVUtil.MONEY_DECIMAL + str2);
    }

    public static void r(Context context, String str, int i2) {
        e(context, "mz_push_preference", str + ".message_seq", i2);
    }

    public static void s(Context context, String str, boolean z) {
        h(context, "mz_push_preference", "switch_through_message_" + str, z);
    }

    public static String t(Context context, String str, String str2) {
        return F(context, str).getString(str2, "");
    }

    public static boolean u(Context context, String str) {
        return j(context, "mz_push_preference", str + ".first_request_publicKey");
    }

    public static int v(Context context, String str) {
        int l2 = l(context, "mz_push_preference", str + ".message_seq") + 1;
        r(context, str, l2);
        DebugLogger.e("mz_push_preference", "current messageSeq " + l2);
        return l2;
    }

    public static boolean w(Context context, String str, String str2) {
        return F(context, str).contains(str2);
    }

    public static void x(Context context, String str, String str2) {
        g(context, "mz_push_preference", str + ".encryption_public_key", str2);
    }

    public static boolean y(Context context, String str) {
        return j(context, "mz_push_preference", "switch_notification_message_" + str);
    }

    public static String z(Context context, String str) {
        return t(context, "mz_push_preference", str + ".encryption_public_key");
    }
}
