package com.jd.stat.common.process;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.jd.stat.common.process.LiveAppModel;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/* loaded from: classes18.dex */
public class b {
    static final Hashtable<String, String> a = new Hashtable<>();

    /* JADX WARN: Code restructure failed: missing block: B:61:0x0081, code lost:
        r1.put(r10.get(r6), "1");
        r10.remove(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0091, code lost:
        if (r10.isEmpty() == false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x009d, code lost:
        return com.jd.stat.common.b.g.a(com.jd.dynamic.DYConstants.DY_REGEX_COMMA, r1.values());
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00a1, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00a1, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.util.LinkedList<java.lang.String> r10) {
        /*
            java.lang.String r0 = ""
            if (r10 != 0) goto L5
            return r0
        L5:
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            r1.<init>()
            r2 = 0
            r3 = 0
        Lc:
            int r4 = r10.size()
            if (r3 >= r4) goto L1e
            java.lang.Object r4 = r10.get(r3)
            java.lang.String r5 = "0"
            r1.put(r4, r5)
            int r3 = r3 + 1
            goto Lc
        L1e:
            java.io.File r3 = new java.io.File
            java.lang.String r4 = "/proc"
            r3.<init>(r4)
            java.io.File[] r3 = r3.listFiles()
            r4 = 0
        L2a:
            int r5 = r3.length
            if (r4 >= r5) goto La4
            r5 = r3[r4]
            boolean r6 = r5.isDirectory()
            if (r6 == 0) goto La1
            java.lang.String r6 = r5.getName()
            boolean r6 = android.text.TextUtils.isDigitsOnly(r6)
            if (r6 == 0) goto La1
            java.lang.String r5 = r5.getName()     // Catch: java.lang.Exception -> La1
            int r5 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.Exception -> La1
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: java.lang.Exception -> La1
            java.io.FileReader r7 = new java.io.FileReader     // Catch: java.lang.Exception -> La1
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> La1
            r8.<init>()     // Catch: java.lang.Exception -> La1
            java.lang.String r9 = "/proc/"
            r8.append(r9)     // Catch: java.lang.Exception -> La1
            r8.append(r5)     // Catch: java.lang.Exception -> La1
            java.lang.String r5 = "/cmdline"
            r8.append(r5)     // Catch: java.lang.Exception -> La1
            java.lang.String r5 = r8.toString()     // Catch: java.lang.Exception -> La1
            r7.<init>(r5)     // Catch: java.lang.Exception -> La1
            r6.<init>(r7)     // Catch: java.lang.Exception -> La1
            java.lang.String r5 = r6.readLine()     // Catch: java.lang.Exception -> La1
            r6.close()     // Catch: java.lang.Exception -> La1
            r6 = 0
        L6f:
            int r7 = r10.size()     // Catch: java.lang.Exception -> La1
            if (r6 >= r7) goto La1
            java.lang.Object r7 = r10.get(r6)     // Catch: java.lang.Exception -> La1
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch: java.lang.Exception -> La1
            boolean r7 = r5.contains(r7)     // Catch: java.lang.Exception -> La1
            if (r7 == 0) goto L9e
            java.lang.Object r5 = r10.get(r6)     // Catch: java.lang.Exception -> La1
            java.lang.String r7 = "1"
            r1.put(r5, r7)     // Catch: java.lang.Exception -> La1
            r10.remove(r6)     // Catch: java.lang.Exception -> La1
            boolean r5 = r10.isEmpty()     // Catch: java.lang.Exception -> La1
            if (r5 == 0) goto La1
            java.lang.String r5 = ","
            java.util.Collection r6 = r1.values()     // Catch: java.lang.Exception -> La1
            java.lang.String r10 = com.jd.stat.common.b.g.a(r5, r6)     // Catch: java.lang.Exception -> La1
            return r10
        L9e:
            int r6 = r6 + 1
            goto L6f
        La1:
            int r4 = r4 + 1
            goto L2a
        La4:
            java.util.Collection r10 = r1.values()
            java.lang.String r10 = com.jd.stat.common.b.g.a(r0, r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.stat.common.process.b.a(java.util.LinkedList):java.lang.String");
    }

    public static List<LiveAppModel> b(Context context, Set<String> set) {
        if (context == null) {
            return new ArrayList();
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = BaseInfo.getRunningAppProcesses(context);
        ArrayList arrayList = new ArrayList();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            String str = runningAppProcessInfo.processName;
            if (a(str)) {
                if (set != null) {
                    LiveAppModel liveAppModel = new LiveAppModel();
                    liveAppModel.f7013e = runningAppProcessInfo.pid;
                    liveAppModel.b = runningAppProcessInfo.uid;
                    liveAppModel.f7012c = str;
                    String a2 = liveAppModel.a();
                    liveAppModel.d = a(context, a2);
                    if (set.contains(a2)) {
                        arrayList.add(liveAppModel);
                    }
                } else {
                    LiveAppModel liveAppModel2 = new LiveAppModel();
                    liveAppModel2.f7013e = runningAppProcessInfo.pid;
                    liveAppModel2.b = runningAppProcessInfo.uid;
                    liveAppModel2.f7012c = str;
                    liveAppModel2.d = a(context, liveAppModel2.a());
                    arrayList.add(liveAppModel2);
                }
            }
        }
        return arrayList;
    }

    public static List<LiveAppModel> c(Context context, Set<String> set) {
        if (context == null) {
            return new ArrayList();
        }
        List<ActivityManager.RunningServiceInfo> runningServices = BaseInfo.getRunningServices(context);
        ArrayList arrayList = new ArrayList();
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            String str = runningServiceInfo.process;
            if (a(str)) {
                if (set != null) {
                    LiveAppModel liveAppModel = new LiveAppModel();
                    liveAppModel.f7013e = runningServiceInfo.pid;
                    liveAppModel.b = runningServiceInfo.uid;
                    liveAppModel.f7012c = str;
                    liveAppModel.d = runningServiceInfo.service.getClassName();
                    if (set.contains(liveAppModel.a())) {
                        arrayList.add(liveAppModel);
                    }
                } else {
                    LiveAppModel liveAppModel2 = new LiveAppModel();
                    liveAppModel2.f7013e = runningServiceInfo.pid;
                    liveAppModel2.b = runningServiceInfo.uid;
                    liveAppModel2.f7012c = str;
                    liveAppModel2.d = runningServiceInfo.service.getClassName();
                    arrayList.add(liveAppModel2);
                }
            }
        }
        return arrayList;
    }

    public static List<LiveAppModel> a(Context context, Set<String> set) {
        ArrayList arrayList = new ArrayList();
        for (File file : new File("/proc").listFiles()) {
            if (file.isDirectory()) {
                try {
                    LiveAppModel liveAppModel = new LiveAppModel(Integer.parseInt(file.getName()));
                    if (set != null) {
                        if (set.contains(liveAppModel.a())) {
                            liveAppModel.d = a(context, liveAppModel.a());
                            if (a(liveAppModel.f7012c)) {
                                if (com.jd.stat.common.b.b.a) {
                                    com.jd.stat.common.b.b.b("add process = " + liveAppModel);
                                }
                                arrayList.add(liveAppModel);
                            }
                        }
                    } else {
                        liveAppModel.d = a(context, liveAppModel.a());
                        if (a(liveAppModel.f7012c)) {
                            if (com.jd.stat.common.b.b.a) {
                                com.jd.stat.common.b.b.b("add process = " + liveAppModel);
                            }
                            arrayList.add(liveAppModel);
                        }
                    }
                } catch (LiveAppModel.NotAndroidAppProcessException | IOException | NumberFormatException unused) {
                }
            }
        }
        return arrayList;
    }

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("com.android") || str.contains("system");
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return !b(str);
    }

    public static String a(Context context, String str) {
        try {
            Hashtable<String, String> hashtable = a;
            if (hashtable.containsKey(str)) {
                return hashtable.get(str);
            }
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            String charSequence = packageInfo.applicationInfo.loadLabel(packageManager).toString();
            hashtable.put(packageInfo.packageName, charSequence);
            return charSequence;
        } catch (PackageManager.NameNotFoundException unused) {
            return str;
        }
    }
}
