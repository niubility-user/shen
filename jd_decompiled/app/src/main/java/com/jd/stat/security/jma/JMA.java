package com.jd.stat.security.jma;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.jd.dynamic.DYConstants;
import com.jd.stat.common.TriTouchUtil;
import com.jd.stat.common.b.a;
import com.jd.stat.common.b.b;
import com.jd.stat.common.f;
import com.jd.stat.common.t;
import com.jd.stat.common.w;
import com.jd.stat.security.c;
import com.jd.stat.security.d;
import com.jd.stat.security.jma.a.e;
import com.jd.stat.security.jma.b.h;
import com.jd.stat.security.jma.b.i;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JMA {
    private static final HashMap<String, Long> a = new HashMap<>();

    private static void a(JSONObject jSONObject) {
        if (c.f7045c || !c.k()) {
            return;
        }
        c.f7045c = true;
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.put("eventid", "PrivacyOn");
            jSONObject.put("caller", "0");
            jSONObject.put("devicecode", c.a());
            report(c.a, jSONObject2);
        } catch (Exception e2) {
            b.a("JDMob.Security.JMA", "error to reportPrivacyOnEvent:" + e2.toString());
        }
    }

    public static void addTrackView(Activity activity) {
        e.e().a(activity);
    }

    private static boolean b(String str, String str2) {
        com.jd.stat.security.b d;
        if (TextUtils.isEmpty(str2) || (d = d.a().d(str)) == null || !TextUtils.equals(d.e(), str2)) {
            return true;
        }
        if (c.a(4) || System.currentTimeMillis() - a(str) >= d.f() * 1000) {
            return d.a().f(str);
        }
        return false;
    }

    public static void disagreePrivacy(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eventid", "PrivacyOff");
            report(context, jSONObject);
        } catch (Exception unused) {
        }
    }

    public static void flush() {
        i.a();
    }

    @Deprecated
    public static void forceRefresh(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eventid", "PrivacyOn");
            jSONObject.put("caller", "1");
            forceRefresh(context, jSONObject);
        } catch (JSONException e2) {
            b.a("JDMob.Security.JMA", "JMA forceRefresh(ctx) error:" + e2.getMessage());
        }
    }

    public static void genSoftKey(Context context) {
        com.jd.stat.security.e.a(context).c();
    }

    public static JSONObject getClientInfo(Context context) {
        if (context == null) {
            b.a("JDMob.Security.JMA", "context is NULL!");
            return new JSONObject();
        }
        c.a(context);
        try {
            JSONObject d = e.d();
            com.jd.stat.common.b.c.a(d, e.b(), e.a(), e.c());
            return a.a(context, d);
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }

    public static JSONObject getConfigObj() {
        try {
            JSONObject c2 = d.a().c();
            return c2 == null ? new JSONObject() : c2;
        } catch (Exception unused) {
            return new JSONObject();
        }
    }

    public static String getJDKey(Context context) {
        return t.a(context);
    }

    public static String getRefererByRecentTask4(Activity activity) {
        if (b.a) {
            b.b("JDMob.Security.JMA", "getRefererByRecentTask() called with: openingActivity = [" + activity + "]");
            StringBuilder sb = new StringBuilder();
            sb.append("recentTaskStartTime = ");
            sb.append(System.currentTimeMillis());
            b.b("JDMob.Security.JMA", sb.toString());
        }
        List<ActivityManager.RecentTaskInfo> recentTasks = ((ActivityManager) activity.getSystemService("activity")).getRecentTasks(4, 0);
        StringBuilder sb2 = new StringBuilder();
        Iterator<ActivityManager.RecentTaskInfo> it = recentTasks.iterator();
        while (it.hasNext()) {
            ComponentName component = it.next().baseIntent.getComponent();
            if (component != null) {
                sb2.append(component.getPackageName());
            }
            sb2.append(DYConstants.DY_REGEX_COMMA);
        }
        if (b.a) {
            b.b("JDMob.Security.JMA", "recentTaskEndTime = " + System.currentTimeMillis());
        }
        return sb2.toString();
    }

    public static String getReferrers(Activity activity) {
        String refererByRecentTask4;
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 > 25) {
                refererByRecentTask4 = a(activity);
            } else if (i2 > 21) {
                refererByRecentTask4 = a(activity);
            } else {
                refererByRecentTask4 = getRefererByRecentTask4(activity);
            }
            return (!TextUtils.isEmpty(refererByRecentTask4) || i2 <= 14 || i2 >= 26) ? refererByRecentTask4 : b(activity);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getSoftFingerprint(Context context) {
        if (context == null) {
            b.a("JDMob.Security.JMA", "context is NULL!");
            return "";
        }
        c.a(context);
        try {
            return f.a() + "~" + com.jd.stat.security.e.a(context).a();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void handleTouchEvent(Activity activity, MotionEvent motionEvent) {
        if (activity == null || motionEvent == null) {
            return;
        }
        try {
            if (isNeedTrack(activity.getClass().getCanonicalName())) {
                b(activity, motionEvent);
            }
            a(activity, motionEvent);
        } catch (Throwable th) {
            b.b("JDMob.Security.JMA", th.getMessage());
        }
    }

    public static boolean isNeedTrack(String str) {
        return e.e().c(str);
    }

    public static boolean isTriTouch() {
        try {
            return d.a().q();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean needXposedDialog() {
        return w.b();
    }

    public static void report(Context context, List<JSONObject> list) {
        c.a(context);
        if (list == null) {
            b.a("JDMob.Security.JMA", "event param is NULL!");
        } else if (list.size() == 0) {
        } else {
            if (!a()) {
                b.b("JDMob.Security.JMA", "report has close!");
            } else {
                i.a(list);
            }
        }
    }

    public static void reportFixAndAlter(Context context, JSONObject jSONObject) {
        c.a(context);
        if (jSONObject == null) {
            b.a("JDMob.Security.JMA", "event param is NULL!");
        } else if (!a()) {
            b.b("JDMob.Security.JMA", "report has close!");
        } else {
            c.a(jSONObject.optString("devicecode", ""));
            i.c(jSONObject);
            i.d(jSONObject);
        }
    }

    public static void setConfig(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            d.a().a(new JSONObject(str));
        } catch (Exception unused) {
        }
    }

    public static void stopTrackView(String str) {
        e.e().d(str);
    }

    public static void triTouch(JSONObject jSONObject, Activity activity) {
        try {
            if (!d.a().q() || jSONObject == null) {
                return;
            }
            TriTouchUtil.getInstance().collectTouchEvent(jSONObject, activity);
        } catch (Throwable th) {
            b.b("JDMob.Security.JMA", th);
        }
    }

    public static void triggerEvent(Context context, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eventid", str);
            report(context, jSONObject, str2);
            if (b.a) {
                b.b("JDMob.Security.JMA", "triggerEvent:" + str + " sceneParams:" + str2);
            }
        } catch (Throwable th) {
            b.a("JMA", th);
        }
    }

    public static void triTouch(JSONObject jSONObject) {
        triTouch(jSONObject, null);
    }

    private static void b(Activity activity, MotionEvent motionEvent) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("devicecode", c.a());
            jSONObject.put("uid", c.f());
            jSONObject.put("pagename", activity.getClass().getCanonicalName());
            jSONObject.put("motionparam", motionEvent);
            jSONObject.put("eventid", "STouch");
            report(c.b, jSONObject);
        } catch (Throwable th) {
            b.b("JDMob.Security.JMA", th.getMessage());
        }
    }

    public static void forceRefresh(Context context, JSONObject jSONObject) {
        b.b("JDMob.Security.JMA", "forceRefresh called");
        try {
            com.jd.stat.security.e.a(context).b();
            f.a(context);
            h.a = 0L;
            h.b = 0L;
            com.jd.stat.common.b.f.a("lastfixinfotime", 0L);
            jSONObject.put("caller", "1");
            c.f7045c = c.k();
            report(context, jSONObject);
        } catch (Exception e2) {
            b.a("JDMob.Security.JMA", "JMA forceRefresh(ctx,obj) error:" + e2.getMessage());
        }
    }

    public static void report(Context context, JSONObject jSONObject) {
        report(context, jSONObject, "");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x012f, code lost:
        if (com.jd.stat.security.d.a().v() == false) goto L166;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void report(android.content.Context r4, org.json.JSONObject r5, java.lang.String r6) {
        /*
            Method dump skipped, instructions count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.stat.security.jma.JMA.report(android.content.Context, org.json.JSONObject, java.lang.String):void");
    }

    private static boolean a(String str, String str2) {
        com.jd.stat.security.b d;
        if (TextUtils.isEmpty(str2) || (d = d.a().d(str)) == null || !TextUtils.equals(d.e(), str2)) {
            return true;
        }
        if (c.a(4) || System.currentTimeMillis() - a(str) >= d.f() * 1000) {
            return d.a().e(str);
        }
        return false;
    }

    private static boolean a() {
        if (BaseInfo.isAppForeground()) {
            return d.a().j();
        }
        return false;
    }

    private static String b(Activity activity) throws Exception {
        if (b.a) {
            b.b("JDMob.Security.JMA", "getRefererByAM() called with: openingActivity = [" + activity + "]");
            StringBuilder sb = new StringBuilder();
            sb.append("getRefererByAMStartTime = ");
            sb.append(System.currentTimeMillis());
            b.b("JDMob.Security.JMA", sb.toString());
        }
        Class<?> cls = Class.forName("android.app.ActivityManagerNative");
        Field declaredField = Activity.class.getDeclaredField("mToken");
        declaredField.setAccessible(true);
        Method method = cls.getMethod("getLaunchedFromUid ", IBinder.class);
        Method method2 = cls.getMethod("getDefault", new Class[0]);
        method2.setAccessible(true);
        Object invoke = method.invoke(method2.invoke(null, null), (IBinder) declaredField.get(activity));
        String nameForUid = activity.getPackageManager().getNameForUid(((Integer) invoke).intValue());
        if (TextUtils.isEmpty(nameForUid)) {
            return "" + invoke;
        }
        if (b.a) {
            b.b("JDMob.Security.JMA", "getRefererByAMEndTime = " + System.currentTimeMillis());
        }
        return nameForUid == null ? "" : nameForUid;
    }

    private static void a(Activity activity, MotionEvent motionEvent) {
        try {
            if (isTriTouch()) {
                a(motionEvent, activity);
            }
        } catch (Throwable th) {
            b.b("JDMob.Security.JMA", th.getMessage());
        }
    }

    private static void a(MotionEvent motionEvent, Activity activity) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("motionparam", motionEvent);
        triTouch(jSONObject, activity);
    }

    private static String a(Activity activity) throws Exception {
        if (b.a) {
            b.b("JDMob.Security.JMA", "getRefererByAF() called with: openingActivity = [" + activity + "]");
            StringBuilder sb = new StringBuilder();
            sb.append("getRefererByActivityFieldStartTime = ");
            sb.append(System.currentTimeMillis());
            b.b("JDMob.Security.JMA", sb.toString());
        }
        Field declaredField = Activity.class.getDeclaredField("mReferrer");
        declaredField.setAccessible(true);
        String str = (String) declaredField.get(activity);
        if (b.a) {
            b.b("JDMob.Security.JMA", "getRefererByActivityFieldEndTime = " + System.currentTimeMillis());
        }
        return str == null ? "" : str;
    }

    private static long a(String str) {
        Long l2 = a.get(str);
        if (l2 == null) {
            return 0L;
        }
        return l2.longValue();
    }

    private static void a(String str, long j2) {
        a.put(str, Long.valueOf(j2));
    }
}
