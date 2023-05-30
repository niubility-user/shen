package com.vivo.push.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.AttributionReporter;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.platform.business.personal.R2;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes11.dex */
public final class z {
    private static String[] a = {"com.vivo.push.sdk.RegistrationReceiver", "com.vivo.push.sdk.service.PushService", "com.vivo.push.sdk.service.CommonJobService"};
    private static String[] b = {"android.permission.INTERNET", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WRITE_SETTINGS", "android.permission.VIBRATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WAKE_LOCK", "android.permission.GET_ACCOUNTS", "com.bbk.account.permission.READ_ACCOUNTINFO", "android.permission.AUTHENTICATE_ACCOUNTS", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS", "android.permission.GET_TASKS"};

    /* renamed from: c */
    private static String[] f18317c = {"com.vivo.push.sdk.service.CommandService", "com.vivo.push.sdk.service.CommonJobService"};
    private static String[] d = {"com.vivo.push.sdk.RegistrationReceiver"};

    /* renamed from: e */
    private static String[] f18318e = new String[0];

    /* renamed from: f */
    private static Map<String, Bundle> f18319f = new ConcurrentHashMap();

    public static long a(Context context) {
        String b2 = t.b(context);
        if (TextUtils.isEmpty(b2)) {
            p.a("Utility", "systemPushPkgName is null");
            return -1L;
        }
        return a(context, b2);
    }

    public static String b(Context context, String str) {
        Object a2 = a(context, str, "com.vivo.push.app_id");
        if (a2 != null) {
            return a2.toString();
        }
        Object a3 = a(context, str, "app_id");
        return a3 != null ? a3.toString() : "";
    }

    public static String c(Context context, String str) {
        Object a2 = a(context, str, "verification_status");
        return a2 != null ? a2.toString() : "";
    }

    private static void d(Context context, String str) throws VivoPushException {
        try {
            if (context.getPackageManager() != null) {
                ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services;
                if (serviceInfoArr != null) {
                    for (String str2 : f18317c) {
                        a(str2, serviceInfoArr, str);
                    }
                    return;
                }
                throw new VivoPushException("serviceInfos is null");
            }
            throw new VivoPushException("localPackageManager is null");
        } catch (Exception e2) {
            throw new VivoPushException("error " + e2.getMessage());
        }
    }

    private static void e(Context context, String str) throws VivoPushException {
        if (f18318e.length <= 0) {
            return;
        }
        try {
            if (context.getPackageManager() != null) {
                ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
                if (activityInfoArr != null) {
                    for (String str2 : f18318e) {
                        a(str2, activityInfoArr, str);
                    }
                    return;
                }
                throw new VivoPushException("activityInfos is null");
            }
            throw new VivoPushException("localPackageManager is null");
        } catch (Exception e2) {
            throw new VivoPushException("error " + e2.getMessage());
        }
    }

    private static void f(Context context, String str) throws VivoPushException {
        try {
            if (context.getPackageManager() != null) {
                ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 2).receivers;
                if (activityInfoArr != null) {
                    for (String str2 : d) {
                        a(str2, activityInfoArr, str);
                    }
                    return;
                }
                throw new VivoPushException("receivers is null");
            }
            throw new VivoPushException("localPackageManager is null");
        } catch (Exception e2) {
            throw new VivoPushException(e2.getMessage());
        }
    }

    public static PublicKey c(Context context) {
        Cursor query = context.getContentResolver().query(com.vivo.push.p.a, null, null, null, null);
        if (query == null) {
            return null;
        }
        while (query.moveToNext()) {
            try {
                try {
                    if ("pushkey".equals(query.getString(query.getColumnIndex("name")))) {
                        String string = query.getString(query.getColumnIndex("value"));
                        p.d("Utility", "result key : ".concat(String.valueOf(string)));
                        PublicKey a2 = u.a(string);
                        try {
                            query.close();
                        } catch (Exception unused) {
                        }
                        return a2;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    query.close();
                } catch (Exception unused2) {
                }
                throw th;
            }
        }
        try {
            query.close();
        } catch (Exception unused3) {
            return null;
        }
    }

    public static long a(Context context, String str) {
        Object a2 = a(context, str, "com.vivo.push.sdk_version");
        if (a2 == null) {
            a2 = a(context, str, "sdk_version");
        }
        if (a2 != null) {
            try {
                return Long.parseLong(a2.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
                p.a("Utility", "getSdkVersionCode error ", e2);
                return -1L;
            }
        }
        p.a("Utility", "getSdkVersionCode sdk version is null");
        return -1L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:184:0x00f4, code lost:
        r7 = r7 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void b(Context context) throws VivoPushException {
        String obj;
        int i2;
        p.d("Utility", "check PushService AndroidManifest declearation !");
        String b2 = t.b(context);
        boolean d2 = t.d(context, context.getPackageName());
        boolean e2 = t.e(context, context.getPackageName());
        boolean c2 = t.c(context, context.getPackageName());
        if (e2) {
            a = new String[]{"com.vivo.push.sdk.RegistrationReceiver", "com.vivo.push.sdk.service.PushService", "com.vivo.push.sdk.service.CommonJobService"};
            b = new String[]{"android.permission.INTERNET", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WRITE_SETTINGS", "android.permission.VIBRATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WAKE_LOCK", "android.permission.GET_ACCOUNTS", "com.bbk.account.permission.READ_ACCOUNTINFO", "android.permission.AUTHENTICATE_ACCOUNTS", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS", "android.permission.GET_TASKS"};
            f18317c = new String[]{"com.vivo.push.sdk.service.CommandService", "com.vivo.push.sdk.service.CommonJobService"};
            d = new String[]{"com.vivo.push.sdk.RegistrationReceiver"};
        } else if (!c2 && !d2) {
            throw new VivoPushException("AndroidManifest.xml\u4e2dreceiver\u914d\u7f6e\u9879\u9519\u8bef\uff0c\u8be6\u89c1\u63a5\u5165\u6587\u6863");
        } else {
            if (c2) {
                f18317c = new String[]{"com.vivo.push.sdk.service.CommandClientService"};
            } else {
                f18317c = new String[]{"com.vivo.push.sdk.service.CommandService"};
            }
            d = new String[0];
            a = new String[0];
            if (d2) {
                b = new String[]{"android.permission.INTERNET", "android.permission.WRITE_SETTINGS"};
            } else {
                b = new String[]{"android.permission.INTERNET"};
            }
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                String[] strArr = packageManager.getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
                if (strArr != null) {
                    String[] strArr2 = b;
                    int length = strArr2.length;
                    int i3 = 0;
                    while (i3 < length) {
                        String str = strArr2[i3];
                        int length2 = strArr.length;
                        while (i2 < length2) {
                            i2 = str.equals(strArr[i2]) ? 0 : i2 + 1;
                        }
                        throw new VivoPushException("permission : " + str + "  check fail : " + Arrays.toString(strArr));
                    }
                    f(context, b2);
                    d(context, b2);
                    e(context, b2);
                    try {
                        if (a(context, context.getPackageName(), "local_iv") != null) {
                            String packageName = context.getPackageName();
                            Object a2 = a(context, packageName, "com.vivo.push.api_key");
                            if (a2 != null) {
                                obj = a2.toString();
                            } else {
                                Object a3 = a(context, packageName, "api_key");
                                obj = a3 != null ? a3.toString() : "";
                            }
                            if (!TextUtils.isEmpty(obj)) {
                                if (!TextUtils.isEmpty(b(context, context.getPackageName()))) {
                                    if ((d2 || e2) && a(context, context.getPackageName()) == -1) {
                                        throw new VivoPushException("sdkversion is null");
                                    }
                                    if (e2) {
                                        a(context, "com.vivo.pushservice.action.METHOD", "com.vivo.push.sdk.RegistrationReceiver", true);
                                        a(context, "com.vivo.pushservice.action.PUSH_SERVICE", "com.vivo.push.sdk.service.PushService", false);
                                        return;
                                    }
                                    return;
                                }
                                throw new VivoPushException("com.vivo.push.app_id is null");
                            }
                            throw new VivoPushException("com.vivo.push.api_key is null");
                        }
                        throw new VivoPushException("AndroidManifest.xml\u4e2d\u672a\u914d\u7f6e".concat("local_iv"));
                    } catch (Exception e3) {
                        throw new VivoPushException("getMetaValue error " + e3.getMessage());
                    }
                }
                throw new VivoPushException("Permissions is null!");
            }
            throw new VivoPushException("localPackageManager is null");
        } catch (Exception e4) {
            throw new VivoPushException(e4.getMessage());
        }
    }

    public static boolean d(Context context) {
        Cursor cursor = null;
        try {
            try {
                try {
                } catch (Exception e2) {
                    p.a("Utility", "isSupport", e2);
                    if (0 != 0) {
                        cursor.close();
                    }
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        cursor.close();
                    } catch (Exception e3) {
                        p.a("Utility", "close", e3);
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            p.a("Utility", "close", e4);
        }
        if (context == null) {
            p.a("Utility", "context is null");
            return false;
        }
        String packageName = context.getPackageName();
        Cursor query = context.getContentResolver().query(com.vivo.push.p.b, null, "pushVersion = ? and appPkgName = ? and appCode = ? ", new String[]{"323", packageName, String.valueOf(context.getPackageManager().getPackageInfo(packageName, 0).versionCode)}, null);
        if (query == null) {
            p.a("Utility", "cursor is null");
            if (query != null) {
                try {
                    query.close();
                } catch (Exception e5) {
                    p.a("Utility", "close", e5);
                }
            }
            return false;
        } else if (!query.moveToFirst() || (query.getInt(query.getColumnIndex(AttributionReporter.SYSTEM_PERMISSION)) & 1) == 0) {
            if (query != null) {
                query.close();
            }
            return false;
        } else {
            if (query != null) {
                try {
                    query.close();
                } catch (Exception e6) {
                    p.a("Utility", "close", e6);
                }
            }
            return true;
        }
    }

    public static Object a(Context context, String str, String str2) {
        Object obj;
        Bundle bundle;
        if (context == null || str2 == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Map<String, Bundle> map = f18319f;
            Object obj2 = (map == null || map.size() <= 0 || (bundle = f18319f.get(str)) == null) ? null : bundle.get(str2);
            if (obj2 != null) {
                return obj2;
            }
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
                r0 = applicationInfo != null ? applicationInfo.metaData : null;
                obj = r0 != null ? r0.get(str2) : obj2;
            } catch (Exception e2) {
                e = e2;
                r0 = obj2;
            }
            try {
                if (f18319f.size() <= 300) {
                    f18319f.put(str, r0);
                    return obj;
                }
                return obj;
            } catch (Exception e3) {
                r0 = obj;
                e = e3;
                p.a("Utility", "getMetaValue::".concat(String.valueOf(e)));
                return r0;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    public static Object a(String str, String str2) throws Exception {
        Class<?> cls = Class.forName(str);
        return cls.getField(str2).get(cls);
    }

    private static void a(String str, ComponentInfo[] componentInfoArr, String str2) throws VivoPushException {
        for (ComponentInfo componentInfo : componentInfoArr) {
            if (str.equals(componentInfo.name)) {
                if (componentInfo.enabled) {
                    a(componentInfo, str2);
                    return;
                }
                throw new VivoPushException(componentInfo.name + " module Push-SDK need is illegitmacy !");
            }
        }
        throw new VivoPushException(str + " module Push-SDK need is not exist");
    }

    private static void a(ComponentInfo componentInfo, String str) throws VivoPushException {
        if (componentInfo.applicationInfo.packageName.equals(str)) {
            return;
        }
        for (String str2 : a) {
            if (str2.equals(componentInfo.name) && !componentInfo.processName.contains(":pushservice")) {
                throw new VivoPushException("module : " + componentInfo.name + " process :" + componentInfo.processName + "  check process fail");
            }
        }
    }

    private static void a(Context context, String str, String str2, boolean z) throws VivoPushException {
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                throw new VivoPushException("localPackageManager is null");
            }
            if (z) {
                List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, R2.attr.checked_is_bold);
                if (queryBroadcastReceivers != null && queryBroadcastReceivers.size() > 0) {
                    Iterator<ResolveInfo> it = queryBroadcastReceivers.iterator();
                    while (it.hasNext()) {
                        if (str2.equals(it.next().activityInfo.name)) {
                            return;
                        }
                    }
                    throw new VivoPushException(str2 + " is missing");
                }
                throw new VivoPushException("checkModule " + intent + " has no receivers");
            }
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, R2.attr.checked_is_bold);
            if (queryIntentServices != null && queryIntentServices.size() > 0) {
                for (ResolveInfo resolveInfo : queryIntentServices) {
                    if (str2.equals(resolveInfo.serviceInfo.name)) {
                        if (resolveInfo.serviceInfo.exported) {
                            return;
                        }
                        throw new VivoPushException(resolveInfo.serviceInfo.name + " exported is false");
                    }
                }
                throw new VivoPushException(str2 + " is missing");
            }
            throw new VivoPushException("checkModule " + intent + " has no services");
        } catch (Exception e2) {
            p.a("Utility", "error  " + e2.getMessage());
            throw new VivoPushException("checkModule error" + e2.getMessage());
        }
    }

    public static String b(String str, String str2) {
        String str3;
        try {
            str3 = (String) Class.forName("android.os.SystemProperties").getMethod(IMantoServerRequester.GET, String.class).invoke(null, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            str3 = str2;
        }
        return (str3 == null || str3.length() == 0) ? str2 : str3;
    }

    public static boolean b(Context context, String str, String str2) {
        Cursor cursor = null;
        try {
            try {
                try {
                } catch (Exception e2) {
                    p.a("Utility", "isOverdue", e2);
                    if (0 != 0) {
                        cursor.close();
                    }
                }
            } catch (Exception e3) {
                p.a("Utility", "close", e3);
            }
            if (context == null) {
                p.a("Utility", "context is null");
                return false;
            }
            Cursor query = context.getContentResolver().query(com.vivo.push.p.f18289c, null, "appPkgName = ? and regId = ? sdkVersion = ? ", new String[]{str, str2, "323"}, null);
            if (query == null) {
                p.a("Utility", "cursor is null");
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception e4) {
                        p.a("Utility", "close", e4);
                    }
                }
                return false;
            } else if (!query.moveToFirst()) {
                if (query != null) {
                    query.close();
                }
                return false;
            } else {
                boolean parseBoolean = Boolean.parseBoolean(query.getString(query.getColumnIndex("clientState")));
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception e5) {
                        p.a("Utility", "close", e5);
                    }
                }
                return parseBoolean;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    cursor.close();
                } catch (Exception e6) {
                    p.a("Utility", "close", e6);
                }
            }
            throw th;
        }
    }

    public static void a(Context context, Intent intent) {
        String b2 = t.b(context);
        String stringExtra = intent.getStringExtra("client_pkgname");
        if (TextUtils.isEmpty(b2)) {
            p.a("Utility", "illegality abe adapter : push pkg is null");
        } else if (TextUtils.isEmpty(stringExtra)) {
            p.a("Utility", "illegality abe adapter : src pkg is null");
        } else if (b2.equals(context.getPackageName())) {
            p.a("Utility", "illegality abe adapter : abe is not pushservice");
        } else if (!b2.equals(stringExtra)) {
            p.d("Utility", "proxy to core : intent pkg : " + intent.getPackage() + " ; src pkg : " + stringExtra + " ; push pkg : " + b2);
            intent.setPackage(b2);
            intent.setClassName(b2, "com.vivo.push.sdk.service.PushService");
            context.startService(intent);
        } else {
            p.a("Utility", "illegality abe adapter : pushPkg = " + b2 + " ; srcPkg = " + stringExtra);
        }
    }
}
