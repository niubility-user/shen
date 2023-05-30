package com.jingdong.sdk.dialingtest.c.a;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* loaded from: classes7.dex */
public class b implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    private String f14713g;

    /* renamed from: h  reason: collision with root package name */
    private a f14714h;

    /* loaded from: classes7.dex */
    public interface a {
        void a(com.jingdong.sdk.dialingtest.c.a.a aVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f2 A[Catch: Exception -> 0x00ee, DONT_GENERATE, TryCatch #1 {Exception -> 0x00ee, blocks: (B:42:0x00ea, B:46:0x00f2, B:48:0x00f7), top: B:82:0x00ea }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f7 A[Catch: Exception -> 0x00ee, DONT_GENERATE, TRY_LEAVE, TryCatch #1 {Exception -> 0x00ee, blocks: (B:42:0x00ea, B:46:0x00f2, B:48:0x00f7), top: B:82:0x00ea }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0121 A[Catch: Exception -> 0x011d, TryCatch #8 {Exception -> 0x011d, blocks: (B:56:0x0119, B:60:0x0121, B:62:0x0126), top: B:87:0x0119 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0126 A[Catch: Exception -> 0x011d, TRY_LEAVE, TryCatch #8 {Exception -> 0x011d, blocks: (B:56:0x0119, B:60:0x0121, B:62:0x0126), top: B:87:0x0119 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00ea A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0119 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.lang.Process] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public com.jingdong.sdk.dialingtest.c.a.a b() {
        Throwable th;
        Process process;
        Exception e2;
        BufferedReader bufferedReader;
        ?? r1 = 0;
        r1 = 0;
        r1 = 0;
        if (TextUtils.isEmpty(this.f14713g)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("DtConsoleSetting", "cmd line is empty");
            return null;
        }
        ?? sb = new StringBuilder();
        sb.append("cmd: ");
        Process process2 = this.f14713g;
        sb.append(process2);
        com.jingdong.sdk.dialingtest.c.e.a.c("DtConsoleSetting", sb.toString());
        com.jingdong.sdk.dialingtest.c.a.a aVar = new com.jingdong.sdk.dialingtest.c.a.a();
        try {
            try {
                process = Runtime.getRuntime().exec(this.f14713g);
            } catch (Exception e3) {
                e2 = e3;
                process = null;
            } catch (Throwable th2) {
                th = th2;
                process = null;
            }
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(process.getInputStream()));
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    try {
                        StringBuilder sb2 = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb2.append(readLine);
                            sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                            com.jingdong.sdk.dialingtest.c.e.a.a("DtConsoleSetting", readLine);
                        }
                        aVar.a = sb2.toString();
                        StringBuilder sb3 = new StringBuilder();
                        while (true) {
                            String readLine2 = bufferedReader.readLine();
                            if (readLine2 == null) {
                                break;
                            }
                            sb3.append(readLine2);
                            com.jingdong.sdk.dialingtest.c.e.a.a("DtConsoleSetting", readLine2);
                        }
                        aVar.b = sb3.toString();
                        aVar.f14711c = process.waitFor();
                        com.jingdong.sdk.dialingtest.c.e.a.a("DtConsoleSetting", "" + aVar.f14711c);
                        try {
                            bufferedReader2.close();
                            bufferedReader.close();
                            if (process != null) {
                                process.destroy();
                            }
                        } catch (Exception e4) {
                            com.jingdong.sdk.dialingtest.c.e.a.a("DtConsoleSetting", e4.getMessage());
                        }
                    } catch (Exception e5) {
                        e2 = e5;
                        r1 = bufferedReader2;
                        process2 = process;
                        aVar.d = e2.getClass().getName();
                        aVar.f14712e = e2.toString();
                        if (r1 != 0) {
                        }
                        if (bufferedReader != null) {
                        }
                        if (process2 != 0) {
                        }
                        return aVar;
                    } catch (Throwable th3) {
                        th = th3;
                        r1 = bufferedReader2;
                        process2 = process;
                        aVar.d = th.getClass().getName();
                        aVar.f14712e = th.toString();
                        return aVar;
                    }
                } catch (Exception e6) {
                    bufferedReader = null;
                    r1 = bufferedReader2;
                    e2 = e6;
                    process2 = process;
                } catch (Throwable th4) {
                    bufferedReader = null;
                    r1 = bufferedReader2;
                    th = th4;
                    process2 = process;
                }
            } catch (Exception e7) {
                e2 = e7;
                bufferedReader = null;
                process2 = process;
                aVar.d = e2.getClass().getName();
                aVar.f14712e = e2.toString();
                if (r1 != 0) {
                    try {
                        r1.close();
                    } catch (Exception e8) {
                        r1 = e8.getMessage();
                        com.jingdong.sdk.dialingtest.c.e.a.a("DtConsoleSetting", r1);
                        return aVar;
                    }
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (process2 != 0) {
                    process2.destroy();
                }
                return aVar;
            } catch (Throwable th5) {
                th = th5;
                bufferedReader = null;
                process2 = process;
                aVar.d = th.getClass().getName();
                aVar.f14712e = th.toString();
                if (r1 != 0) {
                    try {
                    } catch (Exception e9) {
                        return aVar;
                    }
                }
                return aVar;
            }
            return aVar;
        } finally {
            if (r1 != 0) {
                try {
                    r1.close();
                } catch (Exception e10) {
                    com.jingdong.sdk.dialingtest.c.e.a.a("DtConsoleSetting", e10.getMessage());
                }
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (process2 != 0) {
                process2.destroy();
            }
        }
    }

    public void c(String str) {
        this.f14713g = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.jingdong.sdk.dialingtest.c.a.a b = b();
        a aVar = this.f14714h;
        if (aVar != null) {
            aVar.a(b);
        }
    }
}
