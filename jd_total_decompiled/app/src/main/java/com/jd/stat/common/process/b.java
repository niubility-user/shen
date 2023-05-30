package com.jd.stat.common.process;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.jd.stat.common.b.g;
import com.jd.stat.common.process.LiveAppModel;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* loaded from: classes18.dex */
public class b {
    static final Hashtable<String, String> a = new Hashtable<>();

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0081, code lost:
        r1.put(r10.get(r6), "1");
        r10.remove(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0091, code lost:
        if (r10.isEmpty() == false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x009d, code lost:
        return com.jd.stat.common.b.g.a(com.jd.dynamic.DYConstants.DY_REGEX_COMMA, r1.values());
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x00a1, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x00a1, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(LinkedList<String> linkedList) {
        if (linkedList == null) {
            return "";
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i2 = 0; i2 < linkedList.size(); i2++) {
            linkedHashMap.put(linkedList.get(i2), "0");
        }
        for (File file : new File("/proc").listFiles()) {
            if (file.isDirectory() && TextUtils.isDigitsOnly(file.getName())) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/" + Integer.parseInt(file.getName()) + "/cmdline"));
                    String readLine = bufferedReader.readLine();
                    bufferedReader.close();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= linkedList.size()) {
                            break;
                        } else if (readLine.contains(linkedList.get(i3))) {
                            break;
                        } else {
                            i3++;
                        }
                    }
                } catch (Exception unused) {
                    continue;
                }
            }
        }
        return g.a("", linkedHashMap.values());
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
