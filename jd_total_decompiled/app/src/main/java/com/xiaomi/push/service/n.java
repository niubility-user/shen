package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.RemoteViews;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.jingdong.common.database.table.PushMessageTable;
import com.jingdong.jdsdk.constant.CartConstant;
import com.xiaomi.push.a8;
import com.xiaomi.push.c7;
import com.xiaomi.push.d4;
import com.xiaomi.push.e4;
import com.xiaomi.push.f4;
import com.xiaomi.push.g4;
import com.xiaomi.push.p7;
import com.xiaomi.push.s9;
import com.xiaomi.push.service.x;
import com.xiaomi.push.y4;
import com.xiaomi.push.y7;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class n {
    public static long a;
    private static final LinkedList<Pair<Integer, y7>> b = new LinkedList<>();

    /* renamed from: c */
    private static ExecutorService f19138c = Executors.newCachedThreadPool();
    private static volatile t d;

    /* loaded from: classes11.dex */
    public static class a implements Callable<Bitmap> {

        /* renamed from: g */
        private String f19139g;

        /* renamed from: h */
        private Context f19140h;

        /* renamed from: i */
        private boolean f19141i;

        public a(String str, Context context, boolean z) {
            this.f19140h = context;
            this.f19139g = str;
            this.f19141i = z;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Bitmap call() {
            Bitmap bitmap = null;
            if (TextUtils.isEmpty(this.f19139g)) {
                g.j.a.a.a.c.o("Failed get online picture/icon resource cause picUrl is empty");
                return null;
            }
            if (this.f19139g.startsWith("http")) {
                x.b d = x.d(this.f19140h, this.f19139g, this.f19141i);
                if (d != null) {
                    return d.a;
                }
            } else {
                bitmap = x.b(this.f19140h, this.f19139g);
                if (bitmap != null) {
                    return bitmap;
                }
            }
            g.j.a.a.a.c.o("Failed get online picture/icon resource");
            return bitmap;
        }
    }

    /* loaded from: classes11.dex */
    public static class b {
        Notification a;
        long b = 0;
    }

    /* loaded from: classes11.dex */
    public static class c {
        public String a;
        public long b = 0;

        /* renamed from: c */
        public boolean f19142c = false;
    }

    private static void A(Context context, String str, f4 f4Var, Map<String, String> map) {
        int b2 = b(context, str, "mipush_small_notification");
        int b3 = b(context, str, "mipush_notification");
        if (!a8.j(context)) {
            if (b2 > 0) {
                f4Var.setSmallIcon(b2);
            } else {
                T(context, str, f4Var, map);
            }
            if (b3 <= 0) {
                return;
            }
        } else if (b2 <= 0 || b3 <= 0) {
            T(context, str, f4Var, map);
            return;
        } else {
            f4Var.setSmallIcon(b2);
        }
        f4Var.setLargeIcon(l(context, b3));
    }

    public static void B(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        y e2 = y.e(context, str);
        List<StatusBarNotification> z = e2.z();
        if (s9.d(z)) {
            return;
        }
        LinkedList linkedList = new LinkedList();
        for (StatusBarNotification statusBarNotification : z) {
            Notification notification = statusBarNotification.getNotification();
            if (notification != null && !TextUtils.isEmpty(String.valueOf(statusBarNotification.getId()))) {
                int id = statusBarNotification.getId();
                String g2 = z.g(notification);
                String t = z.t(notification);
                if (!TextUtils.isEmpty(g2) && !TextUtils.isEmpty(t) && K(g2, str2) && K(t, str3)) {
                    linkedList.add(statusBarNotification);
                    e2.m(id);
                }
            }
        }
        C(context, linkedList);
    }

    public static void C(Context context, LinkedList<? extends Object> linkedList) {
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        e1.d(context, "category_clear_notification", "clear_notification", linkedList.size(), "");
    }

    private static void D(Intent intent) {
        if (intent == null) {
            return;
        }
        int flags = intent.getFlags() & (-2) & (-3) & (-65);
        if (Build.VERSION.SDK_INT >= 21) {
            flags &= -129;
        }
        intent.setFlags(flags);
    }

    @TargetApi(16)
    private static void E(f4 f4Var, Context context, String str, y7 y7Var, byte[] bArr, int i2) {
        PendingIntent h2;
        PendingIntent h3;
        PendingIntent h4;
        PendingIntent h5;
        Map<String, String> m121a = y7Var.m185a().m121a();
        if (TextUtils.equals("3", m121a.get("notification_style_type")) || TextUtils.equals("4", m121a.get("notification_style_type"))) {
            return;
        }
        if (W(m121a)) {
            for (int i3 = 1; i3 <= 3; i3++) {
                String str2 = m121a.get(String.format("cust_btn_%s_n", Integer.valueOf(i3)));
                if (!TextUtils.isEmpty(str2) && (h5 = h(context, str, y7Var, bArr, i2, i3)) != null) {
                    f4Var.addAction(0, str2, h5);
                }
            }
            return;
        }
        if (!TextUtils.isEmpty(m121a.get("notification_style_button_left_name")) && (h4 = h(context, str, y7Var, bArr, i2, 1)) != null) {
            f4Var.addAction(0, m121a.get("notification_style_button_left_name"), h4);
        }
        if (!TextUtils.isEmpty(m121a.get("notification_style_button_mid_name")) && (h3 = h(context, str, y7Var, bArr, i2, 2)) != null) {
            f4Var.addAction(0, m121a.get("notification_style_button_mid_name"), h3);
        }
        if (TextUtils.isEmpty(m121a.get("notification_style_button_right_name")) || (h2 = h(context, str, y7Var, bArr, i2, 3)) == null) {
            return;
        }
        f4Var.addAction(0, m121a.get("notification_style_button_right_name"), h2);
    }

    private static boolean F(Context context, y7 y7Var, String str) {
        if (y7Var != null && y7Var.m185a() != null && y7Var.m185a().m121a() != null && !TextUtils.isEmpty(str)) {
            return Boolean.parseBoolean(y7Var.m185a().m121a().get("use_clicked_activity")) && j2.b(context, i(str));
        }
        g.j.a.a.a.c.o("should clicked activity params are null.");
        return false;
    }

    public static boolean G(Context context, String str) {
        return y4.n(context, str);
    }

    public static boolean H(Context context, String str, boolean z) {
        return a8.i() && !z && G(context, str);
    }

    private static boolean I(p7 p7Var) {
        if (p7Var != null) {
            String m120a = p7Var.m120a();
            return !TextUtils.isEmpty(m120a) && m120a.length() == 22 && "satuigmo".indexOf(m120a.charAt(0)) >= 0;
        }
        return false;
    }

    public static boolean J(y7 y7Var) {
        p7 m185a = y7Var.m185a();
        return I(m185a) && m185a.l();
    }

    private static boolean K(String str, String str2) {
        return TextUtils.isEmpty(str) || str2.contains(str);
    }

    public static boolean L(Map<String, String> map) {
        if (map == null || !map.containsKey("notify_foreground")) {
            return true;
        }
        return "1".equals(map.get("notify_foreground"));
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x004e, code lost:
        if (android.text.TextUtils.isEmpty(r3) == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0070, code lost:
        if (android.text.TextUtils.isEmpty(r3) == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0072, code lost:
        r1 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String[] M(Context context, p7 p7Var) {
        String str;
        String m128c = p7Var.m128c();
        String d2 = p7Var.d();
        Map<String, String> m121a = p7Var.m121a();
        if (m121a != null) {
            int intValue = Float.valueOf((context.getResources().getDisplayMetrics().widthPixels / context.getResources().getDisplayMetrics().density) + 0.5f).intValue();
            if (intValue <= 320) {
                String str2 = m121a.get("title_short");
                if (!TextUtils.isEmpty(str2)) {
                    m128c = str2;
                }
                str = m121a.get("description_short");
            } else if (intValue > 360) {
                String str3 = m121a.get("title_long");
                if (!TextUtils.isEmpty(str3)) {
                    m128c = str3;
                }
                str = m121a.get("description_long");
            }
        }
        return new String[]{m128c, d2};
    }

    private static int N(Context context, String str) {
        int b2 = b(context, str, "mipush_notification");
        int b3 = b(context, str, "mipush_small_notification");
        if (b2 <= 0) {
            b2 = b3 > 0 ? b3 : context.getApplicationInfo().icon;
        }
        return b2 == 0 ? context.getApplicationInfo().logo : b2;
    }

    private static int O(Map<String, String> map) {
        if (map != null) {
            String str = map.get("channel_importance");
            if (TextUtils.isEmpty(str)) {
                return 3;
            }
            try {
                g.j.a.a.a.c.B("importance=" + str);
                return Integer.parseInt(str);
            } catch (Exception e2) {
                g.j.a.a.a.c.D("parsing channel importance error: " + e2);
                return 3;
            }
        }
        return 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:265:0x014e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Intent P(Context context, String str, Map<String, String> map, int i2) {
        Intent launchIntentForPackage;
        String str2;
        Intent intent;
        Intent intent2;
        if (map == null) {
            return null;
        }
        if (i2 != 0) {
            return j(context, str, map, i2);
        }
        if (map.containsKey("notify_effect")) {
            String str3 = map.get("notify_effect");
            int i3 = -1;
            String str4 = map.get("intent_flag");
            try {
                if (!TextUtils.isEmpty(str4)) {
                    i3 = Integer.parseInt(str4);
                }
            } catch (NumberFormatException e2) {
                g.j.a.a.a.c.D("Cause by intent_flag: " + e2.getMessage());
            }
            if (m0.a.equals(str3)) {
                try {
                    launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
                } catch (Exception e3) {
                    g.j.a.a.a.c.D("Cause: " + e3.getMessage());
                }
            } else {
                if (m0.b.equals(str3)) {
                    if (map.containsKey("intent_uri")) {
                        String str5 = map.get("intent_uri");
                        if (str5 != null) {
                            try {
                                intent2 = Intent.parseUri(str5, 1);
                                try {
                                    intent2.setPackage(str);
                                } catch (URISyntaxException e4) {
                                    e = e4;
                                    g.j.a.a.a.c.D("Cause: " + e.getMessage());
                                    launchIntentForPackage = intent2;
                                    if (launchIntentForPackage != null) {
                                    }
                                    return null;
                                }
                            } catch (URISyntaxException e5) {
                                e = e5;
                                intent2 = null;
                            }
                            launchIntentForPackage = intent2;
                        }
                    } else if (map.containsKey("class_name")) {
                        intent = new Intent();
                        intent.setComponent(new ComponentName(str, map.get("class_name")));
                    }
                    launchIntentForPackage = null;
                } else {
                    if (m0.f19126c.equals(str3) && (str2 = map.get("web_uri")) != null) {
                        String trim = str2.trim();
                        if (!trim.startsWith("http://") && !trim.startsWith("https://")) {
                            trim = "http://" + trim;
                        }
                        try {
                            String protocol = new URL(trim).getProtocol();
                            if ("http".equals(protocol) || "https".equals(protocol)) {
                                intent = new Intent("android.intent.action.VIEW");
                                try {
                                    intent.setData(Uri.parse(trim));
                                    z.m(context, str, intent);
                                } catch (MalformedURLException e6) {
                                    e = e6;
                                    g.j.a.a.a.c.D("Cause: " + e.getMessage());
                                    launchIntentForPackage = intent;
                                    if (launchIntentForPackage != null) {
                                    }
                                    return null;
                                }
                            }
                        } catch (MalformedURLException e7) {
                            e = e7;
                            intent = null;
                        }
                    }
                    launchIntentForPackage = null;
                }
                launchIntentForPackage = intent;
            }
            if (launchIntentForPackage != null) {
                if (i3 >= 0) {
                    launchIntentForPackage.setFlags(i3);
                }
                D(launchIntentForPackage);
                launchIntentForPackage.addFlags(268435456);
                try {
                    if (context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null) {
                        return launchIntentForPackage;
                    }
                    if (Build.VERSION.SDK_INT >= 30 && !a8.j(context) && m0.f19126c.equals(str3)) {
                        return launchIntentForPackage;
                    }
                    g.j.a.a.a.c.o("not resolve activity:" + launchIntentForPackage);
                } catch (Exception e8) {
                    g.j.a.a.a.c.D("Cause: " + e8.getMessage());
                }
            }
            return null;
        }
        return null;
    }

    public static String Q(y7 y7Var) {
        return J(y7Var) ? "E100002" : Z(y7Var) ? "E100000" : V(y7Var) ? "E100001" : a0(y7Var) ? "E100003" : "";
    }

    public static void R(Context context, String str) {
        if (!a8.j(context) || d == null || TextUtils.isEmpty(str)) {
            return;
        }
        d.c(str);
    }

    public static void S(Context context, String str, int i2) {
        context.getSharedPreferences("pref_notify_type", 0).edit().putInt(str, i2).commit();
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void T(Context context, String str, f4 f4Var, Map<String, String> map) {
        boolean z;
        int b2;
        if (!a8.j(context)) {
            String v = v(map, "fcm_icon_uri");
            String v2 = v(map, "fcm_icon_color");
            if (!TextUtils.isEmpty(v) && !TextUtils.isEmpty(v2) && (b2 = b(context, str, v)) > 0) {
                z = true;
                f4Var.setSmallIcon(b2);
                f4Var.f(v2);
                if (z) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        f4Var.setSmallIcon(Icon.createWithResource(str, z.b(context, str)));
                        return;
                    } else {
                        f4Var.setSmallIcon(N(context, str));
                        return;
                    }
                }
                return;
            }
        }
        z = false;
        if (z) {
        }
    }

    public static boolean U(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).contains(str);
    }

    public static boolean V(y7 y7Var) {
        p7 m185a = y7Var.m185a();
        return I(m185a) && m185a.b == 1 && !J(y7Var);
    }

    private static boolean W(Map<String, String> map) {
        if (map == null) {
            g.j.a.a.a.c.o("meta extra is null");
            return false;
        }
        return "6".equals(map.get("notification_style_type"));
    }

    private static int X(Map<String, String> map) {
        if (map != null) {
            String str = map.get("notification_priority");
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            try {
                g.j.a.a.a.c.B("priority=" + str);
                return Integer.parseInt(str);
            } catch (Exception e2) {
                g.j.a.a.a.c.D("parsing notification priority error: " + e2);
                return 0;
            }
        }
        return 0;
    }

    public static void Y(Context context, String str) {
        context.getSharedPreferences("pref_notify_type", 0).edit().remove(str).commit();
    }

    public static boolean Z(y7 y7Var) {
        p7 m185a = y7Var.m185a();
        return I(m185a) && m185a.b == 0 && !J(y7Var);
    }

    static int a(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).getInt(str, Integer.MAX_VALUE);
    }

    public static boolean a0(y7 y7Var) {
        return y7Var.a() == c7.Registration;
    }

    private static int b(Context context, String str, String str2) {
        if (str.equals(context.getPackageName())) {
            return context.getResources().getIdentifier(str2, "drawable", str);
        }
        return 0;
    }

    public static boolean b0(y7 y7Var) {
        return J(y7Var) || Z(y7Var) || V(y7Var);
    }

    private static int c(Context context, String str, Map<String, String> map, int i2) {
        ComponentName a2;
        Intent P = P(context, str, map, i2);
        if (P == null || (a2 = j2.a(context, P)) == null) {
            return 0;
        }
        return a2.hashCode();
    }

    private static int d(Map<String, String> map) {
        String str = map == null ? null : map.get("timeout");
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    private static Notification e(Notification notification) {
        Object d2 = com.xiaomi.push.k0.d(notification, "extraNotification");
        if (d2 != null) {
            com.xiaomi.push.k0.e(d2, "setCustomizedIcon", Boolean.TRUE);
        }
        return notification;
    }

    private static PendingIntent f(Context context, y7 y7Var, String str, byte[] bArr, int i2) {
        return g(context, y7Var, str, bArr, i2, 0, F(context, y7Var, str));
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static PendingIntent g(Context context, y7 y7Var, String str, byte[] bArr, int i2, int i3, boolean z) {
        Intent intent;
        String protocol;
        int i4 = Z(y7Var) ? 1000 : J(y7Var) ? 3000 : -1;
        p7 m185a = y7Var.m185a();
        String m120a = m185a != null ? m185a.m120a() : "";
        boolean J = J(y7Var);
        if (m185a == null || TextUtils.isEmpty(m185a.f18947e)) {
            if (J) {
                intent = new Intent();
                intent.setComponent(new ComponentName("com.xiaomi.xmsf", "com.xiaomi.mipush.sdk.PushMessageHandler"));
            } else {
                intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
                intent.setComponent(new ComponentName(str, "com.xiaomi.mipush.sdk.PushMessageHandler"));
            }
            intent.putExtra("mipush_payload", bArr);
            intent.putExtra("mipush_notified", true);
            intent.addCategory(String.valueOf(i2));
            intent.addCategory(String.valueOf(m120a));
            intent.putExtra("notification_click_button", i3);
            intent.putExtra("messageId", m120a);
            intent.putExtra("eventMessageType", i4);
            if (J || !z) {
                w(context, intent, y7Var, m185a, m120a, i3);
                return Build.VERSION.SDK_INT >= 31 ? PendingIntent.getService(context, 0, intent, 167772160) : PendingIntent.getService(context, 0, intent, 134217728);
            }
            Intent intent2 = new Intent();
            intent2.setComponent(i(str));
            intent2.addFlags(276824064);
            intent2.putExtra("mipush_serviceIntent", intent);
            intent2.addCategory(String.valueOf(i2));
            intent2.addCategory(String.valueOf(m120a));
            intent2.addCategory(String.valueOf(i3));
            w(context, intent2, y7Var, m185a, m120a, i3);
            return Build.VERSION.SDK_INT >= 31 ? PendingIntent.getActivity(context, 0, intent2, 167772160) : PendingIntent.getActivity(context, 0, intent2, 134217728);
        }
        Intent intent3 = new Intent("android.intent.action.VIEW");
        intent3.setData(Uri.parse(m185a.f18947e));
        try {
            protocol = new URL(m185a.f18947e).getProtocol();
        } catch (MalformedURLException unused) {
            g.j.a.a.a.c.o("meet URL exception : " + m185a.f18947e);
            intent3.setPackage(str);
        }
        if (!"http".equals(protocol) && !"https".equals(protocol)) {
            intent3.setPackage(str);
            intent3.addFlags(268435456);
            intent3.putExtra("messageId", m120a);
            intent3.putExtra("eventMessageType", i4);
            return Build.VERSION.SDK_INT < 31 ? PendingIntent.getActivity(context, 0, intent3, 167772160) : PendingIntent.getActivity(context, 0, intent3, 134217728);
        }
        z.m(context, str, intent3);
        intent3.addFlags(268435456);
        intent3.putExtra("messageId", m120a);
        intent3.putExtra("eventMessageType", i4);
        if (Build.VERSION.SDK_INT < 31) {
        }
    }

    private static PendingIntent h(Context context, String str, y7 y7Var, byte[] bArr, int i2, int i3) {
        Map<String, String> m121a = y7Var.m185a().m121a();
        if (m121a == null) {
            return null;
        }
        boolean F = F(context, y7Var, str);
        if (F) {
            return g(context, y7Var, str, bArr, i2, i3, F);
        }
        Intent j2 = j(context, str, m121a, i3);
        if (j2 != null) {
            return PendingIntent.getActivity(context, 0, j2, Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728);
        }
        return null;
    }

    public static ComponentName i(String str) {
        return new ComponentName(str, "com.xiaomi.mipush.sdk.NotificationClickedActivity");
    }

    public static Intent j(Context context, String str, Map<String, String> map, int i2) {
        if (W(map)) {
            return k(context, str, map, String.format("cust_btn_%s_ne", Integer.valueOf(i2)), String.format("cust_btn_%s_iu", Integer.valueOf(i2)), String.format("cust_btn_%s_ic", Integer.valueOf(i2)), String.format("cust_btn_%s_wu", Integer.valueOf(i2)));
        }
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return null;
                    }
                    return k(context, str, map, "notification_colorful_button_notify_effect", "notification_colorful_button_intent_uri", "notification_colorful_button_intent_class", "notification_colorful_button_web_uri");
                }
                return k(context, str, map, "notification_style_button_right_notify_effect", "notification_style_button_right_intent_uri", "notification_style_button_right_intent_class", "notification_style_button_right_web_uri");
            }
            return k(context, str, map, "notification_style_button_mid_notify_effect", "notification_style_button_mid_intent_uri", "notification_style_button_mid_intent_class", "notification_style_button_mid_web_uri");
        }
        return k(context, str, map, "notification_style_button_left_notify_effect", "notification_style_button_left_intent_uri", "notification_style_button_left_intent_class", "notification_style_button_left_web_uri");
    }

    /* JADX WARN: Removed duplicated region for block: B:218:0x0113  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Intent k(Context context, String str, Map<String, String> map, String str2, String str3, String str4, String str5) {
        Intent launchIntentForPackage;
        Intent intent;
        Intent intent2;
        String str6 = map.get(str2);
        if (TextUtils.isEmpty(str6)) {
            return null;
        }
        if (m0.a.equals(str6)) {
            try {
                launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            } catch (Exception e2) {
                g.j.a.a.a.c.D("Cause: " + e2.getMessage());
            }
        } else {
            if (m0.b.equals(str6)) {
                if (map.containsKey(str3)) {
                    String str7 = map.get(str3);
                    if (str7 != null) {
                        try {
                            intent2 = Intent.parseUri(str7, 1);
                            try {
                                intent2.setPackage(str);
                            } catch (URISyntaxException e3) {
                                e = e3;
                                g.j.a.a.a.c.D("Cause: " + e.getMessage());
                                launchIntentForPackage = intent2;
                                if (launchIntentForPackage != null) {
                                }
                                return null;
                            }
                        } catch (URISyntaxException e4) {
                            e = e4;
                            intent2 = null;
                        }
                        launchIntentForPackage = intent2;
                    }
                } else if (map.containsKey(str4)) {
                    intent = new Intent();
                    intent.setComponent(new ComponentName(str, map.get(str4)));
                }
                launchIntentForPackage = null;
            } else {
                if (m0.f19126c.equals(str6)) {
                    String str8 = map.get(str5);
                    if (!TextUtils.isEmpty(str8)) {
                        String trim = str8.trim();
                        if (!trim.startsWith("http://") && !trim.startsWith("https://")) {
                            trim = "http://" + trim;
                        }
                        try {
                            String protocol = new URL(trim).getProtocol();
                            if ("http".equals(protocol) || "https".equals(protocol)) {
                                intent = new Intent("android.intent.action.VIEW");
                                try {
                                    intent.setData(Uri.parse(trim));
                                    z.m(context, str, intent);
                                } catch (MalformedURLException e5) {
                                    e = e5;
                                    g.j.a.a.a.c.D("Cause: " + e.getMessage());
                                    launchIntentForPackage = intent;
                                    if (launchIntentForPackage != null) {
                                    }
                                    return null;
                                }
                            }
                        } catch (MalformedURLException e6) {
                            e = e6;
                            intent = null;
                        }
                    }
                }
                launchIntentForPackage = null;
            }
            launchIntentForPackage = intent;
        }
        if (launchIntentForPackage != null) {
            launchIntentForPackage.addFlags(268435456);
            try {
                if (context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null) {
                    return launchIntentForPackage;
                }
                if (Build.VERSION.SDK_INT >= 30 && !a8.j(context) && m0.f19126c.equals(str6)) {
                    return launchIntentForPackage;
                }
                g.j.a.a.a.c.o("not resolve activity:" + launchIntentForPackage + "for buttons");
            } catch (Exception e7) {
                g.j.a.a.a.c.D("Cause: " + e7.getMessage());
            }
        }
        return null;
    }

    private static Bitmap l(Context context, int i2) {
        return n(context.getResources().getDrawable(i2));
    }

    private static Bitmap m(Context context, String str, boolean z) {
        Future submit = f19138c.submit(new a(str, context, z));
        try {
            try {
                Bitmap bitmap = (Bitmap) submit.get(180L, TimeUnit.SECONDS);
                return bitmap == null ? bitmap : bitmap;
            } catch (InterruptedException | ExecutionException | TimeoutException e2) {
                g.j.a.a.a.c.s(e2);
                submit.cancel(true);
                return null;
            }
        } finally {
            submit.cancel(true);
        }
    }

    public static Bitmap n(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight > 0 ? intrinsicHeight : 1, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    private static RemoteViews o(Context context, y7 y7Var, byte[] bArr) {
        p7 m185a = y7Var.m185a();
        String t = t(y7Var);
        if (m185a != null && m185a.m121a() != null) {
            Map<String, String> m121a = m185a.m121a();
            String str = m121a.get("layout_name");
            String str2 = m121a.get("layout_value");
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                try {
                    Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(t);
                    int identifier = resourcesForApplication.getIdentifier(str, "layout", t);
                    if (identifier == 0) {
                        return null;
                    }
                    RemoteViews remoteViews = new RemoteViews(t, identifier);
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        if (jSONObject.has("text")) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("text");
                            Iterator<String> keys = jSONObject2.keys();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                String string = jSONObject2.getString(next);
                                int identifier2 = resourcesForApplication.getIdentifier(next, "id", t);
                                if (identifier2 > 0) {
                                    remoteViews.setTextViewText(identifier2, string);
                                }
                            }
                        }
                        if (jSONObject.has("image")) {
                            JSONObject jSONObject3 = jSONObject.getJSONObject("image");
                            Iterator<String> keys2 = jSONObject3.keys();
                            while (keys2.hasNext()) {
                                String next2 = keys2.next();
                                String string2 = jSONObject3.getString(next2);
                                int identifier3 = resourcesForApplication.getIdentifier(next2, "id", t);
                                int identifier4 = resourcesForApplication.getIdentifier(string2, "drawable", t);
                                if (identifier3 > 0) {
                                    remoteViews.setImageViewResource(identifier3, identifier4);
                                }
                            }
                        }
                        if (jSONObject.has("time")) {
                            JSONObject jSONObject4 = jSONObject.getJSONObject("time");
                            Iterator<String> keys3 = jSONObject4.keys();
                            while (keys3.hasNext()) {
                                String next3 = keys3.next();
                                String string3 = jSONObject4.getString(next3);
                                if (string3.length() == 0) {
                                    string3 = "yy-MM-dd hh:mm";
                                }
                                int identifier5 = resourcesForApplication.getIdentifier(next3, "id", t);
                                if (identifier5 > 0) {
                                    remoteViews.setTextViewText(identifier5, new SimpleDateFormat(string3).format(new Date(System.currentTimeMillis())));
                                }
                            }
                        }
                        return remoteViews;
                    } catch (JSONException e2) {
                        g.j.a.a.a.c.s(e2);
                        return null;
                    }
                } catch (PackageManager.NameNotFoundException e3) {
                    g.j.a.a.a.c.s(e3);
                }
            }
        }
        return null;
    }

    @TargetApi(16)
    private static f4 p(Context context, y7 y7Var, byte[] bArr, String str, int i2) {
        PendingIntent h2;
        String t = t(y7Var);
        Map<String, String> m121a = y7Var.m185a().m121a();
        String str2 = m121a.get("notification_style_type");
        f4 a2 = (!a8.j(context) || d == null) ? null : d.a(context, i2, t, m121a);
        if (a2 != null) {
            a2.g(m121a);
            return a2;
        } else if ("2".equals(str2)) {
            f4 f4Var = new f4(context);
            Bitmap m2 = TextUtils.isEmpty(m121a.get("notification_bigPic_uri")) ? null : m(context, m121a.get("notification_bigPic_uri"), false);
            if (m2 == null) {
                g.j.a.a.a.c.o("can not get big picture.");
                return f4Var;
            }
            Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle(f4Var);
            bigPictureStyle.bigPicture(m2);
            bigPictureStyle.setSummaryText(str);
            bigPictureStyle.bigLargeIcon((Bitmap) null);
            f4Var.setStyle(bigPictureStyle);
            return f4Var;
        } else if ("1".equals(str2)) {
            f4 f4Var2 = new f4(context);
            f4Var2.setStyle(new Notification.BigTextStyle().bigText(str));
            return f4Var2;
        } else if ("4".equals(str2) && a8.i()) {
            e4 e4Var = new e4(context, t);
            if (!TextUtils.isEmpty(m121a.get("notification_banner_image_uri"))) {
                e4Var.G(m(context, m121a.get("notification_banner_image_uri"), false));
            }
            if (!TextUtils.isEmpty(m121a.get("notification_banner_icon_uri"))) {
                e4Var.I(m(context, m121a.get("notification_banner_icon_uri"), false));
            }
            e4Var.g(m121a);
            return e4Var;
        } else if ("3".equals(str2) && a8.i()) {
            g4 g4Var = new g4(context, i2, t);
            if (!TextUtils.isEmpty(m121a.get("notification_colorful_button_text")) && (h2 = h(context, t, y7Var, bArr, i2, 4)) != null) {
                g4Var.I(m121a.get("notification_colorful_button_text"), h2);
                g4Var.J(m121a.get("notification_colorful_button_bg_color"));
            }
            if (!TextUtils.isEmpty(m121a.get("notification_colorful_bg_color"))) {
                g4Var.L(m121a.get("notification_colorful_bg_color"));
            } else if (!TextUtils.isEmpty(m121a.get("notification_colorful_bg_image_uri"))) {
                g4Var.H(m(context, m121a.get("notification_colorful_bg_image_uri"), false));
            }
            g4Var.g(m121a);
            return g4Var;
        } else {
            return new f4(context);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x0284 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:486:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:495:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:500:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x03d5  */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v29 */
    @SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static b q(Context context, y7 y7Var, byte[] bArr, RemoteViews remoteViews, PendingIntent pendingIntent, int i2) {
        f4 f4Var;
        boolean z;
        Bitmap m2;
        String str;
        boolean z2;
        boolean z3;
        long currentTimeMillis;
        String str2;
        boolean z4;
        b bVar;
        int i3;
        String str3;
        Notification notification;
        Bitmap b2;
        int i4;
        Bitmap m3;
        StringBuilder sb;
        String v;
        b bVar2 = new b();
        p7 m185a = y7Var.m185a();
        String t = t(y7Var);
        Map<String, String> m121a = m185a.m121a();
        String[] M = M(context, m185a);
        if (remoteViews != null) {
            f4Var = new f4(context);
            f4Var.e(remoteViews);
        } else {
            f4Var = (m121a == null || !m121a.containsKey("notification_style_type")) ? new f4(context) : p(context, y7Var, bArr, M[1], i2);
        }
        f4 f4Var2 = f4Var;
        E(f4Var2, context, y7Var.b(), y7Var, bArr, i2);
        f4Var2.setContentTitle(M[0]);
        f4Var2.setContentText(M[1]);
        long currentTimeMillis2 = System.currentTimeMillis();
        f4Var2.setWhen(currentTimeMillis2);
        String v2 = v(m121a, "notification_show_when");
        if (!TextUtils.isEmpty(v2)) {
            f4Var2.setShowWhen(Boolean.parseBoolean(v2));
        } else if (Build.VERSION.SDK_INT >= 24) {
            f4Var2.setShowWhen(true);
        }
        f4Var2.setContentIntent(pendingIntent);
        A(context, t, f4Var2, m121a);
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 23) {
            if (m121a == null) {
                i4 = 1;
                m3 = null;
            } else {
                i4 = 1;
                m3 = m(context, m121a.get("notification_small_icon_uri"), true);
            }
            if (m3 != null) {
                Object[] objArr = new Object[i4];
                objArr[0] = m3;
                Object g2 = com.xiaomi.push.k0.g("android.graphics.drawable.Icon", "createWithBitmap", objArr);
                if (g2 != null) {
                    Object[] objArr2 = new Object[i4];
                    objArr2[0] = g2;
                    com.xiaomi.push.k0.e(f4Var2, "setSmallIcon", objArr2);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("miui.isGrayscaleIcon", i4);
                    f4Var2.d(bundle);
                    f4Var2.f(v(m121a, "notification_small_icon_color"));
                } else {
                    sb = new StringBuilder();
                    sb.append("failed te get small icon with url:");
                    v = m121a.get("notification_small_icon_uri");
                }
            } else {
                sb = new StringBuilder();
                sb.append("failed to get small icon url:");
                v = v(m121a, "notification_small_icon_uri");
            }
            sb.append(v);
            g.j.a.a.a.c.o(sb.toString());
            f4Var2.f(v(m121a, "notification_small_icon_color"));
        }
        String v3 = v(m121a, "__dynamic_icon_uri");
        boolean z5 = Boolean.parseBoolean(v(m121a, "__adiom")) || !a8.i();
        if (!TextUtils.isEmpty(v3) && z5) {
            if (v3.startsWith("http")) {
                x.b d2 = x.d(context, v3, true);
                if (d2 != null) {
                    b2 = d2.a;
                    bVar2.b = d2.b;
                } else {
                    b2 = null;
                }
            } else {
                b2 = x.b(context, v3);
            }
            if (b2 != null) {
                f4Var2.setLargeIcon(b2);
                z = true;
                m2 = m121a != null ? null : m(context, m121a.get("notification_large_icon_uri"), true);
                if (m2 != null) {
                    f4Var2.setLargeIcon(m2);
                }
                if (m121a != null || i5 < 24) {
                    str = null;
                    z2 = false;
                } else {
                    String str4 = m121a.get("notification_group");
                    z2 = Boolean.parseBoolean(m121a.get("notification_is_summary"));
                    boolean parseBoolean = Boolean.parseBoolean(m121a.get("notification_group_disable_default"));
                    if (TextUtils.isEmpty(str4) && (a8.i() || !parseBoolean)) {
                        str4 = t(y7Var);
                    }
                    String str5 = str4;
                    com.xiaomi.push.k0.e(f4Var2, "setGroupSummary", Boolean.valueOf(z2));
                    String str6 = m121a.get("notification_style_type");
                    if ("com.xiaomi.xmsf".equals(context.getPackageName()) && ("4".equals(str6) || "3".equals(str6))) {
                        str = t(y7Var) + "_custom_" + currentTimeMillis2;
                        z3 = true;
                        f4Var2.setAutoCancel(true);
                        currentTimeMillis = System.currentTimeMillis();
                        if (m121a != null && m121a.containsKey(RemoteMessageConst.Notification.TICKER)) {
                            f4Var2.setTicker(m121a.get(RemoteMessageConst.Notification.TICKER));
                        }
                        if (currentTimeMillis - a > 10000) {
                            a = currentTimeMillis;
                            i3 = m185a.a;
                            if (U(context, t)) {
                                i3 = a(context, t);
                            }
                            f4Var2.setDefaults(i3);
                            if (m121a == null || (i3 & 1) == 0) {
                                str2 = "com.xiaomi.xmsf";
                                z4 = z;
                                bVar = bVar2;
                            } else {
                                bVar = bVar2;
                                String str7 = m121a.get("sound_uri");
                                if (TextUtils.isEmpty(str7)) {
                                    str2 = "com.xiaomi.xmsf";
                                    z4 = z;
                                } else {
                                    z4 = z;
                                    StringBuilder sb2 = new StringBuilder();
                                    str2 = "com.xiaomi.xmsf";
                                    sb2.append("android.resource://");
                                    sb2.append(t);
                                    if (str7.startsWith(sb2.toString())) {
                                        f4Var2.setDefaults(i3 ^ 1);
                                        f4Var2.setSound(Uri.parse(str7));
                                    }
                                }
                            }
                        } else {
                            str2 = "com.xiaomi.xmsf";
                            z4 = z;
                            bVar = bVar2;
                            i3 = -100;
                        }
                        if (m121a != null || i5 < 26) {
                            str3 = "0";
                            if (m121a != null && i5 < 26) {
                                com.xiaomi.push.k0.e(f4Var2, "setPriority", Integer.valueOf(X(m121a)));
                            }
                        } else {
                            y e2 = y.e(context, t);
                            if (d(m121a) > 0) {
                                str3 = "0";
                                com.xiaomi.push.k0.e(f4Var2, "setTimeoutAfter", Long.valueOf(r0 * 1000));
                            } else {
                                str3 = "0";
                            }
                            u.h(m185a);
                            String str8 = m121a.get("channel_id");
                            if (!TextUtils.isEmpty(str8) || context.getApplicationInfo().targetSdkVersion >= 26) {
                                String s = s(context, t, m121a);
                                int O = O(m121a);
                                int i6 = m185a.a;
                                h1.f(context, m121a, f4Var2, currentTimeMillis2);
                                com.xiaomi.push.k0.e(f4Var2, "setChannelId", u.d(e2, str8, s, m121a.get("channel_description"), i6, O, m121a.get("sound_uri"), m121a.get("channel_perm")));
                                if (i3 == -100 && z.r(m121a)) {
                                    z.o(f4Var2, z2);
                                }
                                if ("pulldown".equals(z.h(m121a)) && z.r(m121a) && defpackage.b.a(m121a.get("pull_down_pop_type"), str3)) {
                                    z.o(f4Var2, z2);
                                }
                                if ("tts".equals(z.h(m121a)) && z.r(m121a)) {
                                    z.o(f4Var2, z2);
                                }
                            }
                            String str9 = m121a.get("background_color");
                            if (!TextUtils.isEmpty(str9)) {
                                try {
                                    int parseInt = Integer.parseInt(str9);
                                    f4Var2.setOngoing(true);
                                    f4Var2.setColor(parseInt);
                                    com.xiaomi.push.k0.e(f4Var2, "setColorized", Boolean.TRUE);
                                } catch (Exception e3) {
                                    g.j.a.a.a.c.s(e3);
                                }
                            }
                        }
                        if (str != null) {
                            if (!z3) {
                                str = v.b().d(context, f4Var2, str);
                            }
                            com.xiaomi.push.k0.e(f4Var2, "setGroup", str);
                        }
                        if (a8.r() && str2.equals(context.getPackageName())) {
                            com.xiaomi.push.k0.g("miui.util.NotificationHelper", "setTargetPkg", context, f4Var2, t(y7Var));
                        }
                        notification = f4Var2.getNotification();
                        if (z4 && a8.i()) {
                            e(notification);
                        }
                        if (m121a != null) {
                            if (notification.extras == null) {
                                notification.extras = new Bundle();
                            }
                            if (!TextUtils.isEmpty(m121a.get("enable_keyguard"))) {
                                z.u(notification, Boolean.parseBoolean(m121a.get("enable_keyguard")));
                            }
                            if (!TextUtils.isEmpty(m121a.get("enable_float"))) {
                                z.l(notification, Boolean.parseBoolean(m121a.get("enable_float")));
                            }
                            if (!TextUtils.isEmpty(m121a.get("float_small_win")) && str3.equals(m121a.get("float_small_win")) && y4.q(context, t)) {
                                z.l(notification, false);
                            }
                            int a2 = s9.a(m121a.get("section_is_prr"), -1);
                            int a3 = s9.a(m121a.get("section_prr_cl"), -1);
                            if (a2 >= 0 && a3 >= 0) {
                                z.j(notification, a2, a3);
                            }
                        }
                        b bVar3 = bVar;
                        bVar3.a = notification;
                        return bVar3;
                    }
                    str = str5;
                }
                z3 = false;
                f4Var2.setAutoCancel(true);
                currentTimeMillis = System.currentTimeMillis();
                if (m121a != null) {
                    f4Var2.setTicker(m121a.get(RemoteMessageConst.Notification.TICKER));
                }
                if (currentTimeMillis - a > 10000) {
                }
                if (m121a != null) {
                }
                str3 = "0";
                if (m121a != null) {
                    com.xiaomi.push.k0.e(f4Var2, "setPriority", Integer.valueOf(X(m121a)));
                }
                if (str != null) {
                }
                if (a8.r()) {
                    com.xiaomi.push.k0.g("miui.util.NotificationHelper", "setTargetPkg", context, f4Var2, t(y7Var));
                }
                notification = f4Var2.getNotification();
                if (z4) {
                    e(notification);
                }
                if (m121a != null) {
                }
                b bVar32 = bVar;
                bVar32.a = notification;
                return bVar32;
            }
        }
        z = false;
        if (m121a != null) {
        }
        if (m2 != null) {
        }
        if (m121a != null) {
        }
        str = null;
        z2 = false;
        z3 = false;
        f4Var2.setAutoCancel(true);
        currentTimeMillis = System.currentTimeMillis();
        if (m121a != null) {
        }
        if (currentTimeMillis - a > 10000) {
        }
        if (m121a != null) {
        }
        str3 = "0";
        if (m121a != null) {
        }
        if (str != null) {
        }
        if (a8.r()) {
        }
        notification = f4Var2.getNotification();
        if (z4) {
        }
        if (m121a != null) {
        }
        b bVar322 = bVar;
        bVar322.a = notification;
        return bVar322;
    }

    public static c r(Context context, y7 y7Var, byte[] bArr) {
        int i2;
        Map<String, String> map;
        String str;
        c cVar = new c();
        y4.b f2 = y4.f(context, t(y7Var), true);
        p7 m185a = y7Var.m185a();
        if (m185a != null) {
            i2 = m185a.c();
            map = m185a.m121a();
        } else {
            i2 = 0;
            map = null;
        }
        int e2 = s9.e(t(y7Var), i2);
        if (a8.j(context) && f2 == y4.b.NOT_ALLOWED) {
            if (m185a != null) {
                d4.a(context.getApplicationContext()).h(y7Var.b(), Q(y7Var), m185a.m120a(), "10:" + t(y7Var));
            }
            str = "Do not notify because user block " + t(y7Var) + "\u2018s notification";
        } else if (a8.j(context) && d != null && d.d(context, e2, t(y7Var), map)) {
            if (m185a != null) {
                d4.a(context.getApplicationContext()).h(y7Var.b(), Q(y7Var), m185a.m120a(), "14:" + t(y7Var));
            }
            str = "Do not notify because card notification is canceled or sequence incorrect";
        } else {
            RemoteViews o = o(context, y7Var, bArr);
            PendingIntent f3 = f(context, y7Var, y7Var.b(), bArr, e2);
            if (f3 != null) {
                b q = q(context, y7Var, bArr, o, f3, e2);
                cVar.b = q.b;
                cVar.a = t(y7Var);
                Notification notification = q.a;
                if (a8.i()) {
                    if (!TextUtils.isEmpty(m185a.m120a())) {
                        notification.extras.putString(PushMessageTable.TB_CLOUMN_MESSAGE_ID, m185a.m120a());
                    }
                    notification.extras.putString("local_paid", y7Var.m186a());
                    z.n(map, notification.extras, "msg_busi_type");
                    z.n(map, notification.extras, "disable_notification_flags");
                    String str2 = m185a.m126b() == null ? null : m185a.m126b().get("score_info");
                    if (!TextUtils.isEmpty(str2)) {
                        notification.extras.putString("score_info", str2);
                    }
                    notification.extras.putString("pushUid", v(m185a.f195a, "n_stats_expose"));
                    int i3 = -1;
                    if (Z(y7Var)) {
                        i3 = 1000;
                    } else if (J(y7Var)) {
                        i3 = 3000;
                    }
                    notification.extras.putString("eventMessageType", String.valueOf(i3));
                    notification.extras.putString(HiAnalyticsConstant.BI_KEY_TARGET_PACKAGE, t(y7Var));
                }
                String str3 = m185a.m121a() == null ? null : m185a.m121a().get("message_count");
                if (a8.i() && str3 != null) {
                    try {
                        z.i(notification, Integer.parseInt(str3));
                    } catch (NumberFormatException e3) {
                        if (m185a != null) {
                            d4.a(context.getApplicationContext()).i(y7Var.b(), Q(y7Var), m185a.m120a(), "8");
                        }
                        g.j.a.a.a.c.D("fail to set message count. " + e3);
                    }
                }
                String t = t(y7Var);
                z.k(notification, t);
                y e4 = y.e(context, t);
                if (a8.j(context) && d != null) {
                    d.b(y7Var, m185a.m121a(), e2, notification);
                }
                if (a8.j(context) && d != null && d.e(m185a.m121a(), e2, notification)) {
                    g.j.a.a.a.c.y("consume this notificaiton by agent");
                } else {
                    e4.n(e2, notification);
                    cVar.f19142c = true;
                    g.j.a.a.a.c.o("notification: " + m185a.m120a() + " is notifyied");
                }
                if (a8.i() && a8.j(context)) {
                    v.b().f(context, e2, notification);
                    h1.e(context, t, e2, m185a.m120a(), notification);
                }
                if (J(y7Var)) {
                    d4.a(context.getApplicationContext()).g(y7Var.b(), Q(y7Var), m185a.m120a(), 3002, null);
                }
                if (Z(y7Var)) {
                    d4.a(context.getApplicationContext()).g(y7Var.b(), Q(y7Var), m185a.m120a(), 1002, null);
                }
                if (Build.VERSION.SDK_INT < 26) {
                    String m120a = m185a != null ? m185a.m120a() : null;
                    com.xiaomi.push.i b2 = com.xiaomi.push.i.b(context);
                    int d2 = d(m185a.m121a());
                    if (d2 > 0 && !TextUtils.isEmpty(m120a)) {
                        String str4 = "n_timeout_" + m120a;
                        b2.m(str4);
                        b2.n(new o(str4, e4, e2), d2);
                    }
                }
                Pair<Integer, y7> pair = new Pair<>(Integer.valueOf(e2), y7Var);
                LinkedList<Pair<Integer, y7>> linkedList = b;
                synchronized (linkedList) {
                    linkedList.add(pair);
                    if (linkedList.size() > 100) {
                        linkedList.remove();
                    }
                }
                return cVar;
            }
            if (m185a != null) {
                d4.a(context.getApplicationContext()).h(y7Var.b(), Q(y7Var), m185a.m120a(), "11");
            }
            str = "The click PendingIntent is null. ";
        }
        g.j.a.a.a.c.o(str);
        return cVar;
    }

    private static String s(Context context, String str, Map<String, String> map) {
        return (map == null || TextUtils.isEmpty(map.get("channel_name"))) ? y4.m(context, str) : map.get("channel_name");
    }

    public static String t(y7 y7Var) {
        p7 m185a;
        if ("com.xiaomi.xmsf".equals(y7Var.b) && (m185a = y7Var.m185a()) != null && m185a.m121a() != null) {
            String str = m185a.m121a().get("miui_package_name");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return y7Var.b;
    }

    public static String u(Map<String, String> map, int i2) {
        String format = i2 == 0 ? "notify_effect" : W(map) ? String.format("cust_btn_%s_ne", Integer.valueOf(i2)) : i2 == 1 ? "notification_style_button_left_notify_effect" : i2 == 2 ? "notification_style_button_mid_notify_effect" : i2 == 3 ? "notification_style_button_right_notify_effect" : i2 == 4 ? "notification_colorful_button_notify_effect" : null;
        if (map == null || format == null) {
            return null;
        }
        return map.get(format);
    }

    private static String v(Map<String, String> map, String str) {
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    private static void w(Context context, Intent intent, y7 y7Var, p7 p7Var, String str, int i2) {
        if (y7Var == null || p7Var == null || TextUtils.isEmpty(str)) {
            return;
        }
        String u = u(p7Var.m121a(), i2);
        if (TextUtils.isEmpty(u)) {
            return;
        }
        if (m0.a.equals(u) || m0.b.equals(u) || m0.f19126c.equals(u)) {
            intent.putExtra("messageId", str);
            intent.putExtra("local_paid", y7Var.f265a);
            if (!TextUtils.isEmpty(y7Var.b)) {
                intent.putExtra(HiAnalyticsConstant.BI_KEY_TARGET_PACKAGE, y7Var.b);
            }
            intent.putExtra("job_key", v(p7Var.m121a(), "jobkey"));
            intent.putExtra(i2 + CartConstant.KEY_YB_INFO_LINK + "target_component", c(context, y7Var.b, p7Var.m121a(), i2));
        }
    }

    public static void x(Context context, String str) {
        y(context, str, -1);
    }

    public static void y(Context context, String str, int i2) {
        z(context, str, i2, -1);
    }

    public static void z(Context context, String str, int i2, int i3) {
        int hashCode;
        if (context == null || TextUtils.isEmpty(str) || i2 < -1) {
            return;
        }
        y e2 = y.e(context, str);
        List<StatusBarNotification> z = e2.z();
        if (s9.d(z)) {
            return;
        }
        LinkedList linkedList = new LinkedList();
        boolean z2 = false;
        if (i2 == -1) {
            hashCode = 0;
            z2 = true;
        } else {
            hashCode = ((str.hashCode() / 10) * 10) + i2;
        }
        Iterator<StatusBarNotification> it = z.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            StatusBarNotification next = it.next();
            if (!TextUtils.isEmpty(String.valueOf(next.getId()))) {
                int id = next.getId();
                if (z2) {
                    linkedList.add(next);
                    e2.m(id);
                } else if (hashCode == id) {
                    a2.b(context, next, i3);
                    linkedList.add(next);
                    e2.m(id);
                    break;
                }
            }
        }
        C(context, linkedList);
    }
}
