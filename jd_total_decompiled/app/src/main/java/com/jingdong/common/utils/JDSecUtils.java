package com.jingdong.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.os.UserHandle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.jdsec.c.b;
import com.jd.jdsec.c.d;
import com.jd.jdsec.c.e;
import com.jd.jdsec.c.f;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.a.a;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDSecUtils {
    private static String TAG = "JDSec.Security.JDSecUtils";
    private static boolean isInit;

    private static String getRecentTasksReflect(Context context, int i2, int i3) {
        int i4;
        Object invoke;
        int i5;
        List list;
        if (context == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            invoke = Class.forName("android.app.ActivityManagerNative").getMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
            i5 = Build.VERSION.SDK_INT;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (i5 >= 28) {
            return "";
        }
        if (i5 <= 16) {
            Class<?> cls = invoke.getClass();
            Class<?> cls2 = Integer.TYPE;
            Method declaredMethod = cls.getDeclaredMethod("getRecentTasks", cls2, cls2);
            declaredMethod.setAccessible(true);
            list = (List) declaredMethod.invoke(invoke, Integer.valueOf(i2), Integer.valueOf(i3));
        } else {
            Method declaredMethod2 = UserHandle.class.getDeclaredMethod("myUserId", new Class[0]);
            declaredMethod2.setAccessible(true);
            Object invoke2 = declaredMethod2.invoke(null, new Object[0]);
            Class<?> cls3 = invoke.getClass();
            Class<?> cls4 = Integer.TYPE;
            Method declaredMethod3 = cls3.getDeclaredMethod("getRecentTasks", cls4, cls4, cls4);
            declaredMethod3.setAccessible(true);
            list = (List) declaredMethod3.invoke(invoke, Integer.valueOf(i2), Integer.valueOf(i3), invoke2);
        }
        if (list != null && list.size() != 0) {
            for (i4 = 0; i4 < list.size(); i4++) {
                stringBuffer.append(((ActivityManager.RecentTaskInfo) list.get(i4)).baseIntent.getComponent().getPackageName());
                stringBuffer.append(DYConstants.DY_REGEX_COMMA);
            }
        }
        return stringBuffer.toString();
    }

    private static String getRefererByAM(Activity activity) throws Exception {
        Class<?> cls = Class.forName("android.app.ActivityManagerNative");
        Field declaredField = Activity.class.getDeclaredField("mToken");
        declaredField.setAccessible(true);
        Method method = cls.getMethod("getLaunchedFromUid ", IBinder.class);
        Method method2 = cls.getMethod("getDefault", new Class[0]);
        method2.setAccessible(true);
        Object invoke = method.invoke(method2.invoke(null, null), (IBinder) declaredField.get(activity));
        String nameForUid = activity.getPackageManager().getNameForUid(((Integer) invoke).intValue());
        if (!TextUtils.isEmpty(nameForUid)) {
            return nameForUid == null ? "" : nameForUid;
        }
        return "" + invoke;
    }

    private static String getRefererByActivityField(Activity activity) throws Exception {
        Field declaredField = Activity.class.getDeclaredField("mReferrer");
        declaredField.setAccessible(true);
        String str = (String) declaredField.get(activity);
        return str == null ? "" : str;
    }

    private static String getRefererByRecentTask4(Activity activity) {
        List<ActivityManager.RecentTaskInfo> recentTasks = ((ActivityManager) activity.getSystemService("activity")).getRecentTasks(4, 0);
        StringBuilder sb = new StringBuilder();
        Iterator<ActivityManager.RecentTaskInfo> it = recentTasks.iterator();
        while (it.hasNext()) {
            ComponentName component = it.next().baseIntent.getComponent();
            if (component != null) {
                sb.append(component.getPackageName());
            }
            sb.append(DYConstants.DY_REGEX_COMMA);
        }
        return sb.toString();
    }

    public static String getReferrers(Activity activity) {
        String refererByRecentTask4;
        if (activity == null) {
            return "";
        }
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 > 25) {
                refererByRecentTask4 = getRefererByActivityField(activity);
            } else if (i2 > 21) {
                refererByRecentTask4 = getRefererByActivityField(activity);
            } else {
                refererByRecentTask4 = getRefererByRecentTask4(activity);
            }
            return (!TextUtils.isEmpty(refererByRecentTask4) || i2 <= 14 || i2 >= 26) ? refererByRecentTask4 : getRefererByAM(activity);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void init() {
        if (isInit) {
            return;
        }
        isInit = true;
        String property = Configuration.getProperty(Configuration.UNION_ID);
        String property2 = Configuration.getProperty(Configuration.SUB_UNION_ID);
        String readDeviceUUID = StatisticsReportUtil.readDeviceUUID();
        e.a aVar = new e.a();
        aVar.h(readDeviceUUID);
        aVar.k(property2);
        aVar.m(property);
        aVar.l(new f() { // from class: com.jingdong.common.utils.JDSecUtils.3
            @Override // com.jd.jdsec.c.f
            public String getUserAgent() {
                return a.b(JdSdk.getInstance().getApplication());
            }
        });
        aVar.j(new b() { // from class: com.jingdong.common.utils.JDSecUtils.2
            @Override // com.jd.jdsec.c.b
            public boolean isOpen() {
                return JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication());
            }
        });
        aVar.i(new com.jd.jdsec.c.a() { // from class: com.jingdong.common.utils.JDSecUtils.1
            @Override // com.jd.jdsec.c.a
            public boolean hasPermission() {
                return PermissionHelper.hasLocationPermissionWithScene("basicShoppingProcess", "");
            }
        });
        e g2 = aVar.g();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("openall", 1);
            jSONObject.put("fixedinfo", 480);
            jSONObject.put("cprs", 1);
            jSONObject.put("phoneStatusReadCmd", 1);
            jSONObject.put("ev", "");
            OKLog.i(TAG, String.format("init with config : \n%s\nin jdLib", jSONObject));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        Application application = JdSdk.getInstance().getApplication();
        boolean z = OKLog.I;
        d.a(application, jSONObject, g2, z, z);
        OKLog.i(TAG, "init finished");
    }

    private static String reRealPackage(Activity activity) {
        try {
            Object invoke = ActivityManager.class.getMethod("getService", new Class[0]).invoke(null, new Object[0]);
            return activity.getPackageManager().getPackagesForUid(((Integer) invoke.getClass().getMethod("getLaunchedFromUid", IBinder.class).invoke(invoke, (IBinder) Activity.class.getMethod("getActivityToken", new Class[0]).invoke(activity, new Object[0]))).intValue())[0];
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void report(String str, Activity activity) {
        init();
        com.jd.jdsec.c.h.a.e(str, getReferrers(activity), getRecentTasksReflect(activity, 4, 0), reRealPackage(activity));
    }
}
