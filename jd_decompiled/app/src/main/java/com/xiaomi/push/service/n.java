package com.xiaomi.push.service;

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
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.RemoteViews;
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

    /* JADX WARN: Code restructure failed: missing block: B:32:0x004e, code lost:
        if (android.text.TextUtils.isEmpty(r3) == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0070, code lost:
        if (android.text.TextUtils.isEmpty(r3) == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0072, code lost:
        r1 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String[] M(android.content.Context r3, com.xiaomi.push.p7 r4) {
        /*
            java.lang.String r0 = r4.m128c()
            java.lang.String r1 = r4.d()
            java.util.Map r4 = r4.m121a()
            if (r4 == 0) goto L73
            android.content.res.Resources r2 = r3.getResources()
            android.util.DisplayMetrics r2 = r2.getDisplayMetrics()
            int r2 = r2.widthPixels
            android.content.res.Resources r3 = r3.getResources()
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()
            float r3 = r3.density
            float r2 = (float) r2
            float r2 = r2 / r3
            r3 = 1056964608(0x3f000000, float:0.5)
            float r2 = r2 + r3
            java.lang.Float r3 = java.lang.Float.valueOf(r2)
            int r3 = r3.intValue()
            r2 = 320(0x140, float:4.48E-43)
            if (r3 > r2) goto L51
            java.lang.String r3 = "title_short"
            java.lang.Object r3 = r4.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            if (r2 != 0) goto L42
            r0 = r3
        L42:
            java.lang.String r3 = "description_short"
            java.lang.Object r3 = r4.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L73
            goto L72
        L51:
            r2 = 360(0x168, float:5.04E-43)
            if (r3 <= r2) goto L73
            java.lang.String r3 = "title_long"
            java.lang.Object r3 = r4.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            if (r2 != 0) goto L64
            r0 = r3
        L64:
            java.lang.String r3 = "description_long"
            java.lang.Object r3 = r4.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L73
        L72:
            r1 = r3
        L73:
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]
            r4 = 0
            r3[r4] = r0
            r4 = 1
            r3[r4] = r1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.n.M(android.content.Context, com.xiaomi.push.p7):java.lang.String[]");
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

    /* JADX WARN: Removed duplicated region for block: B:166:0x014e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.Intent P(android.content.Context r5, java.lang.String r6, java.util.Map<java.lang.String, java.lang.String> r7, int r8) {
        /*
            Method dump skipped, instructions count: 426
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.n.P(android.content.Context, java.lang.String, java.util.Map, int):android.content.Intent");
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

    /* JADX WARN: Removed duplicated region for block: B:33:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void T(android.content.Context r2, java.lang.String r3, com.xiaomi.push.f4 r4, java.util.Map<java.lang.String, java.lang.String> r5) {
        /*
            boolean r0 = com.xiaomi.push.a8.j(r2)
            if (r0 != 0) goto L2c
            java.lang.String r0 = "fcm_icon_uri"
            java.lang.String r0 = v(r5, r0)
            java.lang.String r1 = "fcm_icon_color"
            java.lang.String r5 = v(r5, r1)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L2c
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 != 0) goto L2c
            int r0 = b(r2, r3, r0)
            if (r0 <= 0) goto L2c
            r1 = 1
            r4.setSmallIcon(r0)
            r4.f(r5)
            goto L2d
        L2c:
            r1 = 0
        L2d:
            if (r1 != 0) goto L48
            int r5 = android.os.Build.VERSION.SDK_INT
            r0 = 23
            if (r5 < r0) goto L41
            int r2 = com.xiaomi.push.service.z.b(r2, r3)
            android.graphics.drawable.Icon r2 = android.graphics.drawable.Icon.createWithResource(r3, r2)
            r4.setSmallIcon(r2)
            goto L48
        L41:
            int r2 = N(r2, r3)
            r4.setSmallIcon(r2)
        L48:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.n.T(android.content.Context, java.lang.String, com.xiaomi.push.f4, java.util.Map):void");
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

    /* JADX WARN: Removed duplicated region for block: B:84:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.app.PendingIntent g(android.content.Context r16, com.xiaomi.push.y7 r17, java.lang.String r18, byte[] r19, int r20, int r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.n.g(android.content.Context, com.xiaomi.push.y7, java.lang.String, byte[], int, int, boolean):android.app.PendingIntent");
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

    /* JADX WARN: Removed duplicated region for block: B:136:0x0113  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.Intent k(android.content.Context r3, java.lang.String r4, java.util.Map<java.lang.String, java.lang.String> r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.n.k(android.content.Context, java.lang.String, java.util.Map, java.lang.String, java.lang.String, java.lang.String, java.lang.String):android.content.Intent");
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
    /* JADX WARN: Removed duplicated region for block: B:228:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0284 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:310:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x03d5  */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v29 */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.xiaomi.push.service.n.b q(android.content.Context r26, com.xiaomi.push.y7 r27, byte[] r28, android.widget.RemoteViews r29, android.app.PendingIntent r30, int r31) {
        /*
            Method dump skipped, instructions count: 1119
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.n.q(android.content.Context, com.xiaomi.push.y7, byte[], android.widget.RemoteViews, android.app.PendingIntent, int):com.xiaomi.push.service.n$b");
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
