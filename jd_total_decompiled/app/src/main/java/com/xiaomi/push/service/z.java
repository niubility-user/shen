package com.xiaomi.push.service;

import android.app.Notification;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.jd.dynamic.DYConstants;
import com.xiaomi.push.y4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes11.dex */
public class z {
    private static final String[] a = {"com.mi.globalbrowser", "com.android.browser"};
    public static final a<String, String, String> b;

    /* renamed from: c  reason: collision with root package name */
    public static final a<String, String, String> f19206c;
    public static final a<String, String, String> d;

    /* renamed from: e  reason: collision with root package name */
    public static final a<String, String, String> f19207e;

    /* renamed from: f  reason: collision with root package name */
    public static final a<String, String, String> f19208f;

    /* loaded from: classes11.dex */
    public static class a<F, S, T> {
        F a;
        S b;

        /* renamed from: c  reason: collision with root package name */
        T f19209c;

        private a(F f2, S s, T t) {
            this.a = f2;
            this.b = s;
            this.f19209c = t;
        }
    }

    static {
        String str = "canSound";
        b = new a<>("setSound", str, str);
        String str2 = "canVibrate";
        f19206c = new a<>("setVibrate", str2, str2);
        String str3 = "canLights";
        d = new a<>("setLights", str3, str3);
        String str4 = "canShowOnKeyguard";
        f19207e = new a<>("setShowOnKeyguard", str4, str4);
        f19208f = new a<>("setFloat", "canFloat", "canShowFloat");
    }

    public static int a(ContentResolver contentResolver) {
        try {
            return Settings.Global.getInt(contentResolver, "user_aggregate", 0);
        } catch (Exception e2) {
            g.j.a.a.a.c.o("get user aggregate failed, " + e2);
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(Context context, String str) {
        return y4.l(context, str);
    }

    public static int c(Context context, String str, String str2, a<String, String, String> aVar) {
        if (aVar != null) {
            try {
                Bundle d2 = d(context, aVar.b, str, str2, null);
                if (d2 == null || !d2.containsKey(aVar.f19209c)) {
                    return -1;
                }
                return d2.getBoolean(aVar.f19209c) ? 1 : 0;
            } catch (Exception unused) {
                return -1;
            }
        }
        return -1;
    }

    private static Bundle d(Context context, String str, String str2, String str3, Bundle bundle) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("call notification provider failed!");
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("package", str2);
        if (!TextUtils.isEmpty(str3)) {
            bundle2.putString("channel_id", str3);
        }
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        return context.getContentResolver().call(Uri.parse("content://statusbar.notification"), str, (String) null, bundle2);
    }

    public static <T> T e(Notification notification, String str) {
        Bundle bundle = notification.extras;
        if (bundle != null) {
            try {
                return (T) bundle.get(str);
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <T> T f(Object obj, String str, T t) {
        T t2;
        T t3 = null;
        try {
        } catch (Exception e2) {
            g.j.a.a.a.c.o("get value error " + e2);
        }
        if (obj instanceof Notification) {
            t2 = e((Notification) obj, str);
        } else if (obj instanceof Map) {
            t2 = ((Map) obj).get(str);
        } else if (!(obj instanceof Bundle)) {
            g.j.a.a.a.c.o("not support get value from classType:" + obj);
            return t3 != null ? t : t3;
        } else {
            t2 = ((Bundle) obj).get(str);
        }
        t3 = t2;
        if (t3 != null) {
        }
    }

    public static String g(Notification notification) {
        CharSequence charSequence;
        Bundle bundle = notification.extras;
        if (bundle != null) {
            charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TITLE);
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE_BIG);
            }
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = notification.extras.getCharSequence("mipush.customTitle");
            }
        } else {
            charSequence = null;
        }
        return charSequence != null ? charSequence.toString() : "";
    }

    public static String h(Object obj) {
        return (String) f(obj, "msg_busi_type", "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void i(Notification notification, int i2) {
        try {
            Bundle bundle = notification.extras;
            if (bundle != null) {
                bundle.putInt("miui.messageCount", i2);
            }
            Object d2 = com.xiaomi.push.k0.d(notification, "extraNotification");
            if (d2 != null) {
                com.xiaomi.push.k0.e(d2, "setMessageCount", Integer.valueOf(i2));
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void j(Notification notification, int i2, int i3) {
        if (notification != null) {
            if (notification.extras == null) {
                notification.extras = new Bundle();
            }
            notification.extras.putInt("is_priority", i2);
            notification.extras.putInt("mipush_class", i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void k(Notification notification, String str) {
        try {
            Bundle bundle = notification.extras;
            if (bundle != null) {
                bundle.putString(HiAnalyticsConstant.BI_KEY_TARGET_PACKAGE, str);
            }
            Object d2 = com.xiaomi.push.k0.d(notification, "extraNotification");
            if (d2 != null) {
                com.xiaomi.push.k0.e(d2, "setTargetPkg", str);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void l(Notification notification, boolean z) {
        try {
            Bundle bundle = notification.extras;
            if (bundle != null) {
                bundle.putBoolean("miui.enableFloat", z);
            }
            Object d2 = com.xiaomi.push.k0.d(notification, "extraNotification");
            if (d2 != null) {
                com.xiaomi.push.k0.e(d2, "setEnableFloat", Boolean.valueOf(z));
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void m(Context context, String str, Intent intent) {
        if (intent == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(str);
        }
        arrayList.addAll(Arrays.asList(a));
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            String str2 = (String) arrayList.get(i2);
            if (!TextUtils.isEmpty(str2)) {
                Intent intent2 = new Intent(intent);
                intent2.setPackage(str2);
                try {
                    if (context.getPackageManager().resolveActivity(intent2, 65536) != null) {
                        intent.setPackage(str2);
                        break;
                    }
                    continue;
                } catch (Exception e2) {
                    g.j.a.a.a.c.o("can't match url intent. " + e2);
                }
            }
        }
        intent.setPackage(intent.getPackage());
    }

    public static void n(Map<String, String> map, Bundle bundle, String str) {
        if (map == null || bundle == null || TextUtils.isEmpty(str)) {
            g.j.a.a.a.c.o("cp map to b fail:" + str);
        } else if (TextUtils.isEmpty(map.get(str))) {
            bundle.remove(str);
        } else {
            bundle.putString(str, map.get(str));
        }
    }

    public static boolean o(Notification.Builder builder, boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setGroupAlertBehavior(z ? 2 : 1);
            return true;
        }
        g.j.a.a.a.c.y("not support setGroupAlertBehavior");
        return false;
    }

    public static boolean p(ContentResolver contentResolver) {
        int a2 = a(contentResolver);
        return a2 == 1 || a2 == 2;
    }

    public static boolean q(Context context, String str, String str2, a<String, String, String> aVar, boolean z) {
        if (aVar != null) {
            try {
                Bundle bundle = new Bundle();
                bundle.putBoolean(aVar.f19209c, z);
                d(context, aVar.a, str, str2, bundle);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static boolean r(Map<String, String> map) {
        return Boolean.parseBoolean((String) f(map, "not_suppress", DYConstants.DY_TRUE));
    }

    public static Notification.Action[] s(Notification notification) {
        Parcelable[] parcelableArray;
        Notification.Action[] actionArr = notification.actions;
        if (actionArr != null) {
            return actionArr;
        }
        Bundle bundle = notification.extras;
        if (bundle == null || (parcelableArray = bundle.getParcelableArray("mipush.customActions")) == null) {
            return null;
        }
        return (Notification.Action[]) Arrays.copyOf(parcelableArray, parcelableArray.length, Notification.Action[].class);
    }

    public static String t(Notification notification) {
        CharSequence charSequence;
        Bundle bundle = notification.extras;
        if (bundle != null) {
            charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
            if (TextUtils.isEmpty(charSequence) && Build.VERSION.SDK_INT >= 21) {
                charSequence = notification.extras.getCharSequence(NotificationCompat.EXTRA_BIG_TEXT);
            }
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = notification.extras.getCharSequence("mipush.customContent");
            }
        } else {
            charSequence = null;
        }
        return charSequence != null ? charSequence.toString() : "";
    }

    public static void u(Notification notification, boolean z) {
        try {
            Bundle bundle = notification.extras;
            if (bundle != null) {
                bundle.putBoolean("miui.enableKeyguard", z);
            }
            Object d2 = com.xiaomi.push.k0.d(notification, "extraNotification");
            if (d2 != null) {
                com.xiaomi.push.k0.e(d2, "setEnableKeyguard", Boolean.valueOf(z));
            }
        } catch (Exception unused) {
        }
    }

    public static String v(Notification notification) {
        Object d2;
        try {
            Bundle bundle = notification.extras;
            r0 = bundle != null ? bundle.getString(HiAnalyticsConstant.BI_KEY_TARGET_PACKAGE) : null;
            return (!TextUtils.isEmpty(r0) || (d2 = com.xiaomi.push.k0.d(notification, "extraNotification")) == null) ? r0 : (String) com.xiaomi.push.k0.e(d2, "getTargetPkg", new Object[0]);
        } catch (Exception unused) {
            return r0;
        }
    }
}
