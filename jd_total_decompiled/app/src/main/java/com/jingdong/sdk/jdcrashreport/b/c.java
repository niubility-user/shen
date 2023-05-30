package com.jingdong.sdk.jdcrashreport.b;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.base.DynamicPrepareFetcher;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class c {
    private static volatile boolean a = false;
    private static volatile String b = "";

    /* renamed from: c  reason: collision with root package name */
    private static volatile long f14858c;
    private static volatile long d;

    /* renamed from: e  reason: collision with root package name */
    private static LinkedList<WeakReference<Activity>> f14859e = new LinkedList<>();

    /* renamed from: f  reason: collision with root package name */
    private static List<b> f14860f = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[com.jingdong.sdk.jdcrashreport.recover.a.values().length];
            a = iArr;
            try {
                iArr[com.jingdong.sdk.jdcrashreport.recover.a.RESTART.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[com.jingdong.sdk.jdcrashreport.recover.a.RECOVER_STACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes7.dex */
    public interface b {
        void a();

        void b();
    }

    public static String a(int i2) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + i2 + "/cmdline"));
        } catch (Throwable th) {
            th = th;
        }
        try {
            String readLine = bufferedReader.readLine();
            if (!TextUtils.isEmpty(readLine)) {
                readLine = readLine.trim();
            }
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                r.g("JDCrashReport.AppUtil", e2);
            }
            return readLine;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            try {
                r.g("JDCrashReport.AppUtil", th);
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                        return "";
                    } catch (IOException e3) {
                        r.g("JDCrashReport.AppUtil", e3);
                        return "";
                    }
                }
                return "";
            } catch (Throwable th3) {
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e4) {
                        r.g("JDCrashReport.AppUtil", e4);
                    }
                }
                throw th3;
            }
        }
    }

    public static String b(int i2, int i3) {
        BufferedReader bufferedReader;
        Throwable th;
        Process process;
        String[] strArr = {"logcat", "-b", NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL, "--pid=" + i2, "-d", "-v", "threadtime", "-t", String.valueOf(i3), "*:V"};
        StringBuilder sb = new StringBuilder();
        try {
            process = Runtime.getRuntime().exec(strArr);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            r.e("JDCrashReport.AppUtil", th);
                            sb.append("\n[read log error:");
                            sb.append(th.toString());
                            sb.append("]");
                            return sb.toString();
                        } finally {
                            if (process != null) {
                                try {
                                    process.getOutputStream().close();
                                } catch (IOException e2) {
                                    r.g("JDCrashReport.AppUtil", e2);
                                }
                                try {
                                    process.getInputStream().close();
                                } catch (IOException e3) {
                                    r.g("JDCrashReport.AppUtil", e3);
                                }
                                try {
                                    process.getErrorStream().close();
                                } catch (IOException e4) {
                                    r.g("JDCrashReport.AppUtil", e4);
                                }
                            }
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e5) {
                                    r.g("JDCrashReport.AppUtil", e5);
                                }
                            }
                        }
                    }
                }
                String sb2 = sb.toString();
                if (process != null) {
                    try {
                        process.getOutputStream().close();
                    } catch (IOException e6) {
                        r.g("JDCrashReport.AppUtil", e6);
                    }
                    try {
                        process.getInputStream().close();
                    } catch (IOException e7) {
                        r.g("JDCrashReport.AppUtil", e7);
                    }
                    try {
                        process.getErrorStream().close();
                    } catch (IOException e8) {
                        r.g("JDCrashReport.AppUtil", e8);
                    }
                }
                try {
                    bufferedReader.close();
                } catch (IOException e9) {
                    r.g("JDCrashReport.AppUtil", e9);
                }
                return sb2;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
            }
        } catch (Throwable th4) {
            bufferedReader = null;
            th = th4;
            process = null;
        }
    }

    public static Map<String, JSONObject> c(String str) {
        HashMap hashMap = new HashMap(12);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return hashMap;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            Thread key = entry.getKey();
            if (!key.getName().startsWith("YY_THREAD")) {
                StackTraceElement[] value = entry.getValue();
                sb.setLength(0);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("name", key.getName() + " (" + key.getId() + ")  " + key.getState());
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                for (StackTraceElement stackTraceElement : value) {
                    if ((TextUtils.isEmpty(str) || !key.getName().startsWith(str)) && sb.length() >= 2000) {
                        sb.append("    [Stack over limit size :");
                        sb.append(2000);
                        sb.append(", has been cut!]\n");
                        break;
                    }
                    sb.append("    ");
                    sb.append(stackTraceElement.toString());
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
                try {
                    jSONObject.put("stack", sb.toString());
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
                hashMap.put(String.valueOf(i2), jSONObject);
                i2++;
            }
        }
        return hashMap;
    }

    public static Map<String, JSONObject> d(Thread thread) {
        HashMap hashMap = new HashMap(12);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return hashMap;
        }
        allStackTraces.remove(thread);
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            Thread key = entry.getKey();
            if (!key.getName().startsWith("YY_THREAD")) {
                StackTraceElement[] value = entry.getValue();
                sb.setLength(0);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("name", key.getName() + "    [ id:" + key.getId() + "  state:" + key.getState() + " ]");
                } catch (JSONException unused) {
                }
                for (StackTraceElement stackTraceElement : value) {
                    if (sb.length() >= 2000) {
                        sb.append("[Stack over limit size :");
                        sb.append(2000);
                        sb.append(", has been cut!]\n");
                        break;
                    }
                    sb.append(stackTraceElement.toString());
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
                try {
                    jSONObject.put("stack", sb.toString().trim());
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                hashMap.put(String.valueOf(i2), jSONObject);
                i2++;
            }
        }
        return hashMap;
    }

    public static void e() {
        Iterator<WeakReference<Activity>> it = f14859e.iterator();
        while (it.hasNext()) {
            Activity activity = it.next().get();
            if (activity != null) {
                activity.finish();
            }
        }
        f14859e.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(Activity activity) {
        f14859e.add(new WeakReference<>(activity));
    }

    public static void g(Context context, com.jingdong.sdk.jdcrashreport.recover.a aVar, Intent[] intentArr) {
        int i2 = a.a[aVar.ordinal()];
        if (i2 == 1) {
            Intent x = x();
            x.addFlags(268468224);
            context.startActivity(x);
        } else if (i2 != 2) {
        } else {
            if (intentArr != null && intentArr.length > 0) {
                intentArr[0].addFlags(268468224);
                com.jingdong.sdk.jdcrashreport.d.I().startActivities(intentArr);
                return;
            }
            g(context, com.jingdong.sdk.jdcrashreport.recover.a.RESTART, null);
        }
    }

    public static void h(b bVar) {
        f14860f.add(bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void i(boolean z) {
        synchronized (c.class) {
            if (a != z) {
                a = z;
                try {
                    int size = f14860f.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        b bVar = f14860f.get(i2);
                        if (z) {
                            b = a(Process.myPid());
                            bVar.a();
                        } else {
                            bVar.b();
                        }
                    }
                } catch (Exception e2) {
                    r.g("Listener threw exception!", e2);
                }
            }
        }
    }

    public static boolean j(Context context) {
        return t() && m(context);
    }

    public static int k(int i2) {
        return (int) ((BaseInfo.getDensity() * i2) + 0.5f);
    }

    public static void l() {
        try {
            String a2 = a(Process.myPid());
            List<ActivityManager.RunningServiceInfo> runningServices = BaseInfo.getRunningServices(com.jingdong.sdk.jdcrashreport.d.I());
            r.f("JDCrashReport", "stopAllServices: runningServicesInfo:" + runningServices);
            if (runningServices != null && !runningServices.isEmpty()) {
                for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                    String str = runningServiceInfo.process;
                    if (str != null && str.equals(a2)) {
                        r.f("JDCrashReport", "stopAllServices: service:" + runningServiceInfo.service);
                        Intent intent = new Intent();
                        intent.setComponent(runningServiceInfo.service);
                        com.jingdong.sdk.jdcrashreport.d.I().stopService(intent);
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public static boolean m(Context context) {
        if (context == null) {
            return false;
        }
        String trim = a(Process.myPid()).trim();
        return TextUtils.equals(trim, context.getPackageName()) || TextUtils.equals(trim, b);
    }

    public static Context n(Context context) {
        Context applicationContext;
        if (context == null) {
            return null;
        }
        return ((context instanceof Application) || (applicationContext = context.getApplicationContext()) == null) ? context : applicationContext;
    }

    public static void o() {
        e();
        l();
        Process.killProcess(Process.myPid());
        System.exit(10);
    }

    public static String p(Context context) {
        String appVersionName = BaseInfo.getAppVersionName();
        return TextUtils.isEmpty(appVersionName) ? "unknown" : appVersionName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void q() {
        if (a) {
            f14858c = (SystemClock.uptimeMillis() - com.jingdong.sdk.jdcrashreport.d.W()) - d;
        } else {
            d = (SystemClock.uptimeMillis() - com.jingdong.sdk.jdcrashreport.d.W()) - f14858c;
        }
    }

    public static int r(Context context) {
        int appVersionCode = BaseInfo.getAppVersionCode();
        if (appVersionCode <= 0) {
            return -1;
        }
        return appVersionCode;
    }

    public static String s() {
        q();
        JSONObject jSONObject = new JSONObject();
        long elapsedRealtime = ((SystemClock.elapsedRealtime() + com.jingdong.sdk.jdcrashreport.d.W()) - com.jingdong.sdk.jdcrashreport.d.X()) - SystemClock.uptimeMillis();
        if (elapsedRealtime < 0) {
            elapsedRealtime = 0;
        }
        try {
            jSONObject.put(DynamicPrepareFetcher.KEY_PREPARE_MODEL_FOREGROUND, f14858c + "ms");
            jSONObject.put(AppStateModule.APP_STATE_BACKGROUND, d + "ms");
            jSONObject.put("deviceSleep", elapsedRealtime + "ms");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static boolean t() {
        return a;
    }

    public static boolean u() {
        return BaseInfo.isRoot();
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0087 A[Catch: IOException -> 0x0067, TRY_ENTER, TRY_LEAVE, TryCatch #6 {IOException -> 0x0067, blocks: (B:27:0x0063, B:47:0x0087), top: B:96:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0095 A[Catch: all -> 0x00c0, TRY_LEAVE, TryCatch #0 {all -> 0x00c0, blocks: (B:48:0x008a, B:50:0x0095), top: B:82:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b7 A[Catch: IOException -> 0x00bb, TRY_ENTER, TRY_LEAVE, TryCatch #11 {IOException -> 0x00bb, blocks: (B:80:0x00db, B:60:0x00b7), top: B:82:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0068 -> B:82:0x008a). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String v() {
        String str = "Unknown";
        BufferedReader bufferedReader = null;
        try {
            try {
                if (new File("/sys/class/power_supply/ac/online").exists()) {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/sys/class/power_supply/ac/online"));
                    try {
                        str = bufferedReader2.readLine();
                        if (str != null && "1".equals(str)) {
                            str = "ac";
                        }
                        bufferedReader2.close();
                        bufferedReader = bufferedReader2;
                    } catch (Throwable unused) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                try {
                                    bufferedReader.close();
                                } finally {
                                }
                            } catch (IOException e2) {
                                r.g("JDCrashReport.AppUtil", e2);
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                if (new File("/sys/class/power_supply/usb/online").exists()) {
                                }
                                if (bufferedReader != null) {
                                }
                                return str;
                            }
                        }
                        if (bufferedReader != null) {
                        }
                        try {
                            if (new File("/sys/class/power_supply/usb/online").exists()) {
                            }
                            if (bufferedReader != null) {
                            }
                        } catch (IOException e3) {
                            r.g("JDCrashReport.AppUtil", e3);
                        }
                        return str;
                    }
                } else if (new File("/sys/class/power_supply/ZIPBytes/online").exists()) {
                    BufferedReader bufferedReader3 = new BufferedReader(new FileReader("/sys/class/power_supply/ZIPBytes/online"));
                    try {
                        str = bufferedReader3.readLine();
                        if (str != null && "1".equals(str)) {
                            str = "ac";
                        }
                        bufferedReader3.close();
                        bufferedReader = bufferedReader3;
                    } catch (Throwable unused2) {
                        bufferedReader = bufferedReader3;
                        if (bufferedReader != null) {
                        }
                        if (bufferedReader != null) {
                        }
                        if (new File("/sys/class/power_supply/usb/online").exists()) {
                        }
                        if (bufferedReader != null) {
                        }
                        return str;
                    }
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Throwable unused3) {
            }
        } catch (IOException e4) {
            r.g("JDCrashReport.AppUtil", e4);
        }
        try {
            if (new File("/sys/class/power_supply/usb/online").exists()) {
                BufferedReader bufferedReader4 = new BufferedReader(new FileReader("/sys/class/power_supply/usb/online"));
                try {
                    str = bufferedReader4.readLine();
                    if (str != null && "1".equals(str)) {
                        str = "usb";
                    }
                    bufferedReader4.close();
                    bufferedReader = bufferedReader4;
                } catch (Throwable unused4) {
                    bufferedReader = bufferedReader4;
                    try {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e5) {
                                r.g("JDCrashReport.AppUtil", e5);
                            }
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return str;
                    } finally {
                    }
                }
            }
        } catch (Throwable unused5) {
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x009e A[Catch: IOException -> 0x00a2, TRY_ENTER, TRY_LEAVE, TryCatch #5 {IOException -> 0x00a2, blocks: (B:31:0x007f, B:49:0x009e), top: B:67:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0086 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String w() {
        BufferedReader bufferedReader = null;
        try {
            try {
                if (new File("/sys/class/power_supply/battery/capacity").exists()) {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/sys/class/power_supply/battery/capacity"));
                    try {
                        String readLine = bufferedReader2.readLine();
                        bufferedReader2.close();
                        if (readLine != null) {
                            String str = readLine + "%";
                            try {
                                bufferedReader2.close();
                            } catch (IOException e2) {
                                r.g("JDCrashReport.AppUtil", e2);
                            }
                            return str;
                        }
                        bufferedReader = bufferedReader2;
                    } catch (Throwable unused) {
                        bufferedReader = bufferedReader2;
                        try {
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e3) {
                                    r.g("JDCrashReport.AppUtil", e3);
                                }
                            }
                            if (bufferedReader == null) {
                                bufferedReader.close();
                                return "Unknown";
                            }
                            return "Unknown";
                        } catch (Throwable th) {
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e4) {
                                    r.g("JDCrashReport.AppUtil", e4);
                                }
                            }
                            throw th;
                        }
                    }
                } else if (new File("/sys/class/power_supply/Battery/capacity").exists()) {
                    BufferedReader bufferedReader3 = new BufferedReader(new FileReader("/sys/class/power_supply/Battery/capacity"));
                    try {
                        String readLine2 = bufferedReader3.readLine();
                        bufferedReader3.close();
                        if (readLine2 != null) {
                            String str2 = readLine2 + "%";
                            try {
                                bufferedReader3.close();
                            } catch (IOException e5) {
                                r.g("JDCrashReport.AppUtil", e5);
                            }
                            return str2;
                        }
                        bufferedReader = bufferedReader3;
                    } catch (Throwable unused2) {
                        bufferedReader = bufferedReader3;
                        if (bufferedReader != null) {
                        }
                        if (bufferedReader == null) {
                        }
                    }
                }
            } catch (Throwable unused3) {
            }
            if (bufferedReader != null) {
                bufferedReader.close();
                return "Unknown";
            }
            return "Unknown";
        } catch (IOException e6) {
            r.g("JDCrashReport.AppUtil", e6);
            return "Unknown";
        }
    }

    private static Intent x() {
        return com.jingdong.sdk.jdcrashreport.d.I().getPackageManager().getLaunchIntentForPackage(com.jingdong.sdk.jdcrashreport.d.I().getPackageName());
    }
}
