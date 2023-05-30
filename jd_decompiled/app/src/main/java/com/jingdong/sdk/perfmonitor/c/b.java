package com.jingdong.sdk.perfmonitor.c;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.RandomAccessFile;

/* loaded from: classes12.dex */
public class b extends com.jingdong.sdk.perfmonitor.c.a {

    /* renamed from: g  reason: collision with root package name */
    private a f15403g;

    /* renamed from: h  reason: collision with root package name */
    private RandomAccessFile f15404h;

    /* renamed from: i  reason: collision with root package name */
    private RandomAccessFile f15405i;

    /* renamed from: j  reason: collision with root package name */
    private Long f15406j;

    /* renamed from: k  reason: collision with root package name */
    private Long f15407k;

    /* loaded from: classes12.dex */
    public interface a {
        void c(float f2);
    }

    public b(Context context, long j2, long j3, a aVar) {
        super(context, j2, j3);
        this.f15403g = aVar;
    }

    private int i(String str) {
        if (str.contains("CPU")) {
            String[] split = str.split("\\s+");
            for (int i2 = 0; i2 < split.length; i2++) {
                if (split[i2].contains("CPU")) {
                    return i2;
                }
            }
            return -1;
        }
        return -1;
    }

    private float j() {
        long parseLong;
        long parseLong2;
        try {
            RandomAccessFile randomAccessFile = this.f15404h;
            if (randomAccessFile != null && this.f15405i != null) {
                randomAccessFile.seek(0L);
                this.f15405i.seek(0L);
                String readLine = this.f15404h.readLine();
                String readLine2 = this.f15405i.readLine();
                String[] split = readLine.split(LangUtils.SINGLE_SPACE);
                String[] split2 = readLine2.split(LangUtils.SINGLE_SPACE);
                parseLong = Long.parseLong(split[2]) + Long.parseLong(split[3]) + Long.parseLong(split[4]) + Long.parseLong(split[5]) + Long.parseLong(split[6]) + Long.parseLong(split[7]) + Long.parseLong(split[8]);
                parseLong2 = Long.parseLong(split2[13]) + Long.parseLong(split2[14]);
                if (this.f15406j != null && this.f15407k == null) {
                    this.f15406j = Long.valueOf(parseLong);
                    this.f15407k = Long.valueOf(parseLong2);
                    return 0.0f;
                }
                float longValue = (((float) (parseLong2 - this.f15407k.longValue())) / ((float) (parseLong - this.f15406j.longValue()))) * 100.0f;
                this.f15406j = Long.valueOf(parseLong);
                this.f15407k = Long.valueOf(parseLong2);
                return longValue;
            }
            this.f15404h = new RandomAccessFile("/proc/stat", "r");
            this.f15405i = new RandomAccessFile("/proc/" + Process.myPid() + "/stat", "r");
            String readLine3 = this.f15404h.readLine();
            String readLine22 = this.f15405i.readLine();
            String[] split3 = readLine3.split(LangUtils.SINGLE_SPACE);
            String[] split22 = readLine22.split(LangUtils.SINGLE_SPACE);
            parseLong = Long.parseLong(split3[2]) + Long.parseLong(split3[3]) + Long.parseLong(split3[4]) + Long.parseLong(split3[5]) + Long.parseLong(split3[6]) + Long.parseLong(split3[7]) + Long.parseLong(split3[8]);
            parseLong2 = Long.parseLong(split22[13]) + Long.parseLong(split22[14]);
            if (this.f15406j != null) {
            }
            float longValue2 = (((float) (parseLong2 - this.f15407k.longValue())) / ((float) (parseLong - this.f15406j.longValue()))) * 100.0f;
            this.f15406j = Long.valueOf(parseLong);
            this.f15407k = Long.valueOf(parseLong2);
            return longValue2;
        } catch (Exception e2) {
            OKLog.e("PerfMonitor", "getCPUData\u62a5\u9519", e2);
            return 0.0f;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0055, code lost:
        r3 = r7[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005b, code lost:
        if (r3.endsWith("%") == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005d, code lost:
        r3 = r3.substring(0, r3.lastIndexOf("%"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0072, code lost:
        r0 = java.lang.Float.parseFloat(r3) / java.lang.Runtime.getRuntime().availableProcessors();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0074, code lost:
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0078, code lost:
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0079, code lost:
        com.jingdong.sdk.oklog.OKLog.e("PerfMonitor", "readCpuDataOverO\u5173\u95edreader\u5931\u8d25", r3);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float k() {
        /*
            r9 = this;
            java.lang.String r0 = "%"
            java.lang.String r1 = "readCpuDataOverO\u5173\u95edreader\u5931\u8d25"
            java.lang.String r2 = "PerfMonitor"
            r3 = 0
            java.lang.Runtime r4 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L95 java.io.IOException -> L98
            java.lang.String r5 = "top -n 1"
            java.lang.Process r4 = r4.exec(r5)     // Catch: java.lang.Throwable -> L95 java.io.IOException -> L98
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.io.IOException -> L93 java.lang.Throwable -> Lb0
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch: java.io.IOException -> L93 java.lang.Throwable -> Lb0
            java.io.InputStream r7 = r4.getInputStream()     // Catch: java.io.IOException -> L93 java.lang.Throwable -> Lb0
            r6.<init>(r7)     // Catch: java.io.IOException -> L93 java.lang.Throwable -> Lb0
            r5.<init>(r6)     // Catch: java.io.IOException -> L93 java.lang.Throwable -> Lb0
            r3 = -1
            r6 = -1
        L21:
            java.lang.String r7 = r5.readLine()     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            if (r7 == 0) goto L82
            java.lang.String r7 = r7.trim()     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            if (r8 == 0) goto L32
            goto L21
        L32:
            int r8 = r9.i(r7)     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            if (r8 == r3) goto L3a
            r6 = r8
            goto L21
        L3a:
            int r8 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            boolean r8 = r7.startsWith(r8)     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            if (r8 == 0) goto L21
            if (r6 != r3) goto L4b
            goto L21
        L4b:
            java.lang.String r8 = "\\s+"
            java.lang.String[] r7 = r7.split(r8)     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            int r8 = r7.length     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            if (r8 > r6) goto L55
            goto L21
        L55:
            r3 = r7[r6]     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            boolean r6 = r3.endsWith(r0)     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            if (r6 == 0) goto L66
            r6 = 0
            int r0 = r3.lastIndexOf(r0)     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            java.lang.String r3 = r3.substring(r6, r0)     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
        L66:
            float r0 = java.lang.Float.parseFloat(r3)     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            int r3 = r3.availableProcessors()     // Catch: java.lang.Throwable -> L8d java.io.IOException -> L90
            float r3 = (float) r3
            float r0 = r0 / r3
            r5.close()     // Catch: java.io.IOException -> L78
            goto L7c
        L78:
            r3 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r2, r1, r3)
        L7c:
            if (r4 == 0) goto L81
            r4.destroy()
        L81:
            return r0
        L82:
            r5.close()     // Catch: java.io.IOException -> L86
            goto L8a
        L86:
            r0 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r2, r1, r0)
        L8a:
            if (r4 == 0) goto Lae
            goto Lab
        L8d:
            r0 = move-exception
            r3 = r5
            goto Lb1
        L90:
            r0 = move-exception
            r3 = r5
            goto L9a
        L93:
            r0 = move-exception
            goto L9a
        L95:
            r0 = move-exception
            r4 = r3
            goto Lb1
        L98:
            r0 = move-exception
            r4 = r3
        L9a:
            java.lang.String r5 = "readCpuDataOverO\u62a5\u9519"
            com.jingdong.sdk.oklog.OKLog.e(r2, r5, r0)     // Catch: java.lang.Throwable -> Lb0
            if (r3 == 0) goto La9
            r3.close()     // Catch: java.io.IOException -> La5
            goto La9
        La5:
            r0 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r2, r1, r0)
        La9:
            if (r4 == 0) goto Lae
        Lab:
            r4.destroy()
        Lae:
            r0 = 0
            return r0
        Lb0:
            r0 = move-exception
        Lb1:
            if (r3 == 0) goto Lbb
            r3.close()     // Catch: java.io.IOException -> Lb7
            goto Lbb
        Lb7:
            r3 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r2, r1, r3)
        Lbb:
            if (r4 == 0) goto Lc0
            r4.destroy()
        Lc0:
            goto Lc2
        Lc1:
            throw r0
        Lc2:
            goto Lc1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.perfmonitor.c.b.k():float");
    }

    @Override // com.jingdong.sdk.perfmonitor.c.a
    void e() {
        this.f15407k = null;
        this.f15406j = null;
    }

    @Override // com.jingdong.sdk.perfmonitor.c.a
    void f() {
        float j2;
        if (Build.VERSION.SDK_INT >= 26) {
            j2 = k();
        } else {
            j2 = j();
        }
        a aVar = this.f15403g;
        if (aVar != null) {
            aVar.c(j2);
        }
    }
}
