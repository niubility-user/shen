package com.jingdong.sdk.perfmonitor.c;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    */
    private float k() {
        Process process;
        float parseFloat;
        BufferedReader bufferedReader = 0;
        try {
            try {
                process = Runtime.getRuntime().exec("top -n 1");
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    bufferedReader = -1;
                    int i2 = -1;
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine != null) {
                                String trim = readLine.trim();
                                if (!TextUtils.isEmpty(trim)) {
                                    int i3 = i(trim);
                                    if (i3 == -1) {
                                        if (trim.startsWith(String.valueOf(Process.myPid())) && i2 != -1) {
                                            String[] split = trim.split("\\s+");
                                            if (split.length > i2) {
                                                break;
                                            }
                                        }
                                    } else {
                                        i2 = i3;
                                    }
                                }
                            } else {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e2) {
                                    OKLog.e("PerfMonitor", "readCpuDataOverO\u5173\u95edreader\u5931\u8d25", e2);
                                }
                                if (process == null) {
                                    return 0.0f;
                                }
                            }
                        } catch (IOException e3) {
                            e = e3;
                            bufferedReader = bufferedReader2;
                            OKLog.e("PerfMonitor", "readCpuDataOverO\u62a5\u9519", e);
                            if (bufferedReader != 0) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e4) {
                                    OKLog.e("PerfMonitor", "readCpuDataOverO\u5173\u95edreader\u5931\u8d25", e4);
                                }
                            }
                            if (process == null) {
                                return 0.0f;
                            }
                            process.destroy();
                            return 0.0f;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e5) {
                                    OKLog.e("PerfMonitor", "readCpuDataOverO\u5173\u95edreader\u5931\u8d25", e5);
                                }
                            }
                            if (process != null) {
                                process.destroy();
                            }
                            throw th;
                        }
                    }
                } catch (IOException e6) {
                    e = e6;
                }
            } catch (IOException e7) {
                e = e7;
                process = null;
            } catch (Throwable th2) {
                th = th2;
                process = null;
            }
            process.destroy();
            return 0.0f;
            if (process != null) {
                process.destroy();
            }
            return parseFloat;
        } catch (Throwable th3) {
            th = th3;
        }
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
