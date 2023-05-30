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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String v() {
        /*
            Method dump skipped, instructions count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdcrashreport.b.c.v():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x009e A[Catch: IOException -> 0x00a2, TRY_ENTER, TRY_LEAVE, TryCatch #5 {IOException -> 0x00a2, blocks: (B:31:0x007f, B:49:0x009e), top: B:67:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0086 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String w() {
        /*
            java.lang.String r0 = "/sys/class/power_supply/Battery/capacity"
            java.lang.String r1 = "/sys/class/power_supply/battery/capacity"
            java.lang.String r2 = "JDCrashReport.AppUtil"
            r3 = 0
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L83
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L83
            boolean r4 = r4.exists()     // Catch: java.lang.Throwable -> L83
            java.lang.String r5 = "%"
            if (r4 == 0) goto L43
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L83
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L83
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L83
            r0.<init>(r4)     // Catch: java.lang.Throwable -> L83
            java.lang.String r1 = r0.readLine()     // Catch: java.lang.Throwable -> L41
            r0.close()     // Catch: java.lang.Throwable -> L41
            if (r1 == 0) goto L3f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L41
            r3.<init>()     // Catch: java.lang.Throwable -> L41
            r3.append(r1)     // Catch: java.lang.Throwable -> L41
            r3.append(r5)     // Catch: java.lang.Throwable -> L41
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Throwable -> L41
            r0.close()     // Catch: java.io.IOException -> L3a
            goto L3e
        L3a:
            r0 = move-exception
            com.jingdong.sdk.jdcrashreport.b.r.g(r2, r0)
        L3e:
            return r1
        L3f:
            r3 = r0
            goto L7d
        L41:
            r3 = r0
            goto L84
        L43:
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L83
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L83
            boolean r1 = r1.exists()     // Catch: java.lang.Throwable -> L83
            if (r1 == 0) goto L7d
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L83
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L83
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L83
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L83
            java.lang.String r0 = r1.readLine()     // Catch: java.lang.Throwable -> L7b
            r1.close()     // Catch: java.lang.Throwable -> L7b
            if (r0 == 0) goto L79
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7b
            r3.<init>()     // Catch: java.lang.Throwable -> L7b
            r3.append(r0)     // Catch: java.lang.Throwable -> L7b
            r3.append(r5)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L7b
            r1.close()     // Catch: java.io.IOException -> L74
            goto L78
        L74:
            r1 = move-exception
            com.jingdong.sdk.jdcrashreport.b.r.g(r2, r1)
        L78:
            return r0
        L79:
            r3 = r1
            goto L7d
        L7b:
            r3 = r1
            goto L84
        L7d:
            if (r3 == 0) goto La6
            r3.close()     // Catch: java.io.IOException -> La2
            goto La6
        L83:
        L84:
            if (r3 == 0) goto L9c
            r3.close()     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            goto L9c
        L8a:
            r0 = move-exception
            goto L91
        L8c:
            r0 = move-exception
            com.jingdong.sdk.jdcrashreport.b.r.g(r2, r0)     // Catch: java.lang.Throwable -> L8a
            goto L9c
        L91:
            if (r3 == 0) goto L9b
            r3.close()     // Catch: java.io.IOException -> L97
            goto L9b
        L97:
            r1 = move-exception
            com.jingdong.sdk.jdcrashreport.b.r.g(r2, r1)
        L9b:
            throw r0
        L9c:
            if (r3 == 0) goto La6
            r3.close()     // Catch: java.io.IOException -> La2
            goto La6
        La2:
            r0 = move-exception
            com.jingdong.sdk.jdcrashreport.b.r.g(r2, r0)
        La6:
            java.lang.String r0 = "Unknown"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdcrashreport.b.c.w():java.lang.String");
    }

    private static Intent x() {
        return com.jingdong.sdk.jdcrashreport.d.I().getPackageManager().getLaunchIntentForPackage(com.jingdong.sdk.jdcrashreport.d.I().getPackageName());
    }
}
