package com.jingdong.manto.r;

import android.os.Process;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.utils.MantoLog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class a {
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005c, code lost:
        if (r4 == 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
        if (r4 == 0) goto L45;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:68:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(int r4) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "/sys/devices/system/cpu/cpu"
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = "/cpufreq/cpuinfo_max_freq"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r0 = 0
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4b java.io.FileNotFoundException -> L62
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4b java.io.FileNotFoundException -> L62
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3e java.io.FileNotFoundException -> L43
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3e java.io.FileNotFoundException -> L43
            java.lang.String r0 = r4.readLine()     // Catch: java.io.IOException -> L37 java.io.FileNotFoundException -> L39 java.lang.Throwable -> L7b
            java.lang.String r0 = r0.trim()     // Catch: java.io.IOException -> L37 java.io.FileNotFoundException -> L39 java.lang.Throwable -> L7b
            int r1 = java.lang.Integer.parseInt(r0)     // Catch: java.io.IOException -> L37 java.io.FileNotFoundException -> L39 java.lang.Throwable -> L7b
            r2.close()     // Catch: java.io.IOException -> L32
            goto L5e
        L32:
            r0 = move-exception
            r0.printStackTrace()
            goto L5e
        L37:
            r0 = move-exception
            goto L4f
        L39:
            r0 = move-exception
            goto L66
        L3b:
            r4 = move-exception
            r1 = r0
            goto L7e
        L3e:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
            goto L4f
        L43:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
            goto L66
        L48:
            r4 = move-exception
            r1 = r0
            goto L7f
        L4b:
            r4 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
        L4f:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L7b
            if (r2 == 0) goto L5c
            r2.close()     // Catch: java.io.IOException -> L58
            goto L5c
        L58:
            r0 = move-exception
            r0.printStackTrace()
        L5c:
            if (r4 == 0) goto L7a
        L5e:
            r4.close()     // Catch: java.io.IOException -> L76
            goto L7a
        L62:
            r4 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
        L66:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L7b
            if (r2 == 0) goto L73
            r2.close()     // Catch: java.io.IOException -> L6f
            goto L73
        L6f:
            r0 = move-exception
            r0.printStackTrace()
        L73:
            if (r4 == 0) goto L7a
            goto L5e
        L76:
            r4 = move-exception
            r4.printStackTrace()
        L7a:
            return r1
        L7b:
            r0 = move-exception
            r1 = r4
            r4 = r0
        L7e:
            r0 = r2
        L7f:
            if (r0 == 0) goto L89
            r0.close()     // Catch: java.io.IOException -> L85
            goto L89
        L85:
            r0 = move-exception
            r0.printStackTrace()
        L89:
            if (r1 == 0) goto L93
            r1.close()     // Catch: java.io.IOException -> L8f
            goto L93
        L8f:
            r0 = move-exception
            r0.printStackTrace()
        L93:
            goto L95
        L94:
            throw r4
        L95:
            goto L94
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.r.a.a(int):int");
    }

    public static List<Integer> a() {
        BufferedReader bufferedReader;
        String readLine;
        String str;
        int intValue;
        String str2;
        int intValue2;
        String str3;
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/task/" + Process.myTid() + "/status")), 1000);
        } catch (Throwable th) {
            th = th;
        }
        try {
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine != null) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    try {
                        th.printStackTrace();
                        if (bufferedReader2 != null) {
                            bufferedReader = bufferedReader2;
                            break;
                            bufferedReader.close();
                        }
                        return arrayList;
                    } catch (Throwable th3) {
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th3;
                    }
                }
                break;
            } while (!readLine.startsWith("Cpus_allowed_list"));
            break;
            bufferedReader.close();
        } catch (IOException unused2) {
        }
        String[] split = readLine.trim().split(":");
        if (split == null || split.length != 2) {
            str = "";
        } else {
            str = split[1];
            if (str.startsWith("\t")) {
                str = str.substring(1);
            }
        }
        if (!TextUtils.isEmpty(str)) {
            String[] split2 = str.split(DYConstants.DY_REGEX_COMMA);
            if (split2.length > 1) {
                for (String str4 : split2) {
                    String[] split3 = str4.split("-");
                    if (split3.length != 2) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused3) {
                        }
                        return null;
                    }
                    if (Integer.valueOf(split3[0]).intValue() < Integer.valueOf(split3[1]).intValue()) {
                        intValue2 = Integer.valueOf(split3[0]).intValue();
                        str3 = split3[1];
                    } else {
                        intValue2 = Integer.valueOf(split3[1]).intValue();
                        str3 = split3[0];
                    }
                    int intValue3 = Integer.valueOf(str3).intValue();
                    while (intValue2 < intValue3 + 1) {
                        arrayList.add(Integer.valueOf(intValue2));
                        intValue2++;
                    }
                }
            } else {
                String[] split4 = split2[0].split("-");
                if (split4.length > 1) {
                    if (Integer.valueOf(split4[0]).intValue() < Integer.valueOf(split4[1]).intValue()) {
                        intValue = Integer.valueOf(split4[0]).intValue();
                        str2 = split4[1];
                    } else {
                        intValue = Integer.valueOf(split4[1]).intValue();
                        str2 = split4[0];
                    }
                    int intValue4 = Integer.valueOf(str2).intValue();
                    while (intValue < intValue4 + 1) {
                        arrayList.add(Integer.valueOf(intValue));
                        intValue++;
                    }
                } else {
                    arrayList.add(Integer.valueOf(Integer.valueOf(split2[0]).intValue()));
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0074, code lost:
        if (r4 == null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0088, code lost:
        if (r4 == null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008a, code lost:
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x008e, code lost:
        r4 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x008f, code lost:
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0094, code lost:
        r6 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.Integer> a(java.util.List<java.lang.Integer> r7) {
        /*
            r0 = 0
            if (r7 != 0) goto L4
            return r0
        L4:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            r3 = 0
        Lb:
            int r4 = r7.size()
            if (r3 >= r4) goto Lb7
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "/sys/devices/system/cpu/cpu"
            r4.append(r5)
            java.lang.Object r5 = r7.get(r3)
            r4.append(r5)
            java.lang.String r5 = "/cpufreq/scaling_cur_freq"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.io.FileReader r5 = new java.io.FileReader     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L63 java.io.FileNotFoundException -> L77
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L63 java.io.FileNotFoundException -> L77
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L58 java.io.FileNotFoundException -> L5c
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L58 java.io.FileNotFoundException -> L5c
            java.lang.String r6 = r4.readLine()     // Catch: java.io.IOException -> L50 java.io.FileNotFoundException -> L52 java.lang.Throwable -> La0
            java.lang.String r6 = r6.trim()     // Catch: java.io.IOException -> L50 java.io.FileNotFoundException -> L52 java.lang.Throwable -> La0
            int r6 = java.lang.Integer.parseInt(r6)     // Catch: java.io.IOException -> L50 java.io.FileNotFoundException -> L52 java.lang.Throwable -> La0
            r5.close()     // Catch: java.io.IOException -> L45
            goto L49
        L45:
            r5 = move-exception
            r5.printStackTrace()
        L49:
            r4.close()     // Catch: java.io.IOException -> L4e
            goto L95
        L4e:
            r4 = move-exception
            goto L90
        L50:
            r6 = move-exception
            goto L67
        L52:
            r6 = move-exception
            goto L7b
        L54:
            r7 = move-exception
            r4 = r0
        L56:
            r0 = r5
            goto La2
        L58:
            r4 = move-exception
            r6 = r4
            r4 = r0
            goto L67
        L5c:
            r4 = move-exception
            r6 = r4
            r4 = r0
            goto L7b
        L60:
            r7 = move-exception
            r4 = r0
            goto La2
        L63:
            r4 = move-exception
            r6 = r4
            r4 = r0
            r5 = r4
        L67:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> La0
            if (r5 == 0) goto L74
            r5.close()     // Catch: java.io.IOException -> L70
            goto L74
        L70:
            r5 = move-exception
            r5.printStackTrace()
        L74:
            if (r4 == 0) goto L94
            goto L8a
        L77:
            r4 = move-exception
            r6 = r4
            r4 = r0
            r5 = r4
        L7b:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> La0
            if (r5 == 0) goto L88
            r5.close()     // Catch: java.io.IOException -> L84
            goto L88
        L84:
            r5 = move-exception
            r5.printStackTrace()
        L88:
            if (r4 == 0) goto L94
        L8a:
            r4.close()     // Catch: java.io.IOException -> L8e
            goto L94
        L8e:
            r4 = move-exception
            r6 = 0
        L90:
            r4.printStackTrace()
            goto L95
        L94:
            r6 = 0
        L95:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
            r1.add(r4)
            int r3 = r3 + 1
            goto Lb
        La0:
            r7 = move-exception
            goto L56
        La2:
            if (r0 == 0) goto Lac
            r0.close()     // Catch: java.io.IOException -> La8
            goto Lac
        La8:
            r0 = move-exception
            r0.printStackTrace()
        Lac:
            if (r4 == 0) goto Lb6
            r4.close()     // Catch: java.io.IOException -> Lb2
            goto Lb6
        Lb2:
            r0 = move-exception
            r0.printStackTrace()
        Lb6:
            throw r7
        Lb7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.r.a.a(java.util.List):java.util.List");
    }

    public static float b() {
        List<Integer> a = a();
        List<Integer> a2 = a(a);
        float f2 = 0.0f;
        if (a != null && a.size() != 0 && a2 != null && a2.size() != 0) {
            float size = 1.0f / a2.size();
            for (int i2 = 0; i2 < a.size(); i2++) {
                int intValue = a.get(i2).intValue();
                f2 += ((100.0f * size) * a2.get(i2).intValue()) / a(intValue);
            }
            MantoLog.d("cpuUtil", "rate: " + f2);
        }
        return f2;
    }
}
