package com.jingdong.sdk.jdcrashreport.b;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.jdcrashreport.JDCrashReportListener;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class j {
    private static final Object a = new Object();
    private static File b;

    /* renamed from: c  reason: collision with root package name */
    private static File f14865c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x00d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0093 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x00bb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0089 A[Catch: all -> 0x00b7, TryCatch #16 {all -> 0x00b7, blocks: (B:20:0x0037, B:58:0x0083, B:60:0x0089, B:61:0x008c), top: B:117:0x0024 }] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v15, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.io.Reader, java.io.InputStreamReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.sdk.jdcrashreport.common.CrashInfo a(java.io.File r7) {
        /*
            Method dump skipped, instructions count: 227
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdcrashreport.b.j.a(java.io.File):com.jingdong.sdk.jdcrashreport.common.CrashInfo");
    }

    public static File b() {
        String f2 = i.f("CRASH_LOG_DIR_SP", "");
        if (!TextUtils.isEmpty(f2.trim())) {
            File file = new File(f2.trim());
            b = file;
            if ((file.exists() && b.isDirectory()) || b.mkdirs()) {
                return b;
            }
            b = null;
        }
        if (b == null) {
            b = o.d("crash");
        }
        if (b == null) {
            b = com.jingdong.sdk.jdcrashreport.d.I().getFileStreamPath("crash");
        }
        if ((b.exists() && b.isDirectory()) || b.mkdirs()) {
            i.d("CRASH_LOG_DIR_SP", b.getAbsolutePath());
            return b;
        }
        return null;
    }

    public static void c(CrashInfo crashInfo, JDCrashReportListener jDCrashReportListener) {
        if (crashInfo == null) {
            r.h("JDCrashReport", "crashInfo is null");
        } else {
            l.a(crashInfo, jDCrashReportListener);
        }
    }

    public static void d(File file, CrashInfo crashInfo) {
        JSONObject jSONObject;
        String str;
        if (file == null || crashInfo == null) {
            return;
        }
        r.b("JDCrashReport", "saveCrash2File: " + file.getAbsolutePath());
        synchronized (a) {
            if (!file.exists()) {
                try {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                } catch (IOException e2) {
                    r.g("JDCrashReport", e2);
                }
            }
            if (file.isFile() && file.canWrite()) {
                try {
                    JSONObject jSONObject2 = new JSONObject(crashInfo.toString());
                    try {
                        jSONObject = new JSONObject(jSONObject2.optString("feedback"));
                    } catch (Exception unused) {
                        jSONObject = new JSONObject();
                    }
                    jSONObject.put("cache", DYConstants.DY_TRUE);
                    jSONObject2.put("feedback", jSONObject);
                    BufferedWriter bufferedWriter = null;
                    try {
                        BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                        try {
                            bufferedWriter2.append((CharSequence) jSONObject2.toString());
                            bufferedWriter2.flush();
                        } catch (Throwable th) {
                            th = th;
                            bufferedWriter = bufferedWriter2;
                            try {
                                r.g("JDCrashReport", th);
                                if (bufferedWriter != null) {
                                    try {
                                        bufferedWriter.close();
                                    } catch (IOException e3) {
                                        e = e3;
                                        str = "JDCrashReport";
                                        r.g(str, e);
                                        return;
                                    }
                                }
                                return;
                            } catch (Throwable th2) {
                                if (bufferedWriter != null) {
                                    try {
                                        bufferedWriter.close();
                                    } catch (IOException e4) {
                                        r.g("JDCrashReport", e4);
                                    }
                                }
                                throw th2;
                            }
                        }
                        try {
                            bufferedWriter2.close();
                        } catch (IOException e5) {
                            e = e5;
                            str = "JDCrashReport";
                            r.g(str, e);
                            return;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Exception e6) {
                    r.g("JDCrashReport", e6);
                }
                return;
            }
            r.b("JDCrashReport", "cannot write");
        }
    }

    public static void e(List<CrashInfo> list) {
        l.b(list);
    }

    public static File f() {
        String f2 = i.f("TOMBSTONES_LOG_DIR_SP", "");
        if (!TextUtils.isEmpty(f2.trim())) {
            File file = new File(f2.trim());
            f14865c = file;
            if ((file.exists() && f14865c.isDirectory()) || f14865c.mkdirs()) {
                return f14865c;
            }
            f14865c = null;
        }
        if (f14865c == null) {
            f14865c = o.d("tombstones");
        }
        if (f14865c == null) {
            f14865c = com.jingdong.sdk.jdcrashreport.d.I().getFileStreamPath("tombstones");
        }
        if ((f14865c.exists() && f14865c.isDirectory()) || f14865c.mkdirs()) {
            i.d("TOMBSTONES_LOG_DIR_SP", f14865c.getAbsolutePath());
            return f14865c;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void g(CrashInfo crashInfo, JDCrashReportListener jDCrashReportListener) {
        l.a(crashInfo, jDCrashReportListener);
    }

    public static File[] h() {
        File d = o.d("crash");
        File fileStreamPath = com.jingdong.sdk.jdcrashreport.d.I().getFileStreamPath("crash");
        File[] listFiles = fileStreamPath == null ? new File[0] : fileStreamPath.listFiles();
        File[] listFiles2 = d == null ? new File[0] : d.listFiles();
        if (listFiles == null) {
            listFiles = new File[0];
        }
        if (listFiles2 == null) {
            listFiles2 = new File[0];
        }
        File[] fileArr = (File[]) Arrays.copyOf(listFiles, listFiles.length + listFiles2.length);
        System.arraycopy(listFiles2, 0, fileArr, listFiles.length, listFiles2.length);
        return fileArr;
    }

    public static void i() {
        i.g("crash_times", i.a("crash_times", 0) + 1);
        j();
    }

    private static void j() {
        if (x.b().equals(i.f("currentDate", ""))) {
            i.g("javaCrashTimes", i.a("javaCrashTimes", 0) + 1);
            return;
        }
        i.d("currentDate", x.b());
        i.g("javaCrashTimes", 1);
    }
}
