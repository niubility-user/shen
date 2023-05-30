package com.jd.libs.xwin.http;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/* loaded from: classes16.dex */
public class a extends BaseRequest {

    /* renamed from: g  reason: collision with root package name */
    private InterfaceC0173a f6235g;

    /* renamed from: h  reason: collision with root package name */
    private String f6236h;

    /* renamed from: i  reason: collision with root package name */
    private String f6237i;

    /* renamed from: j  reason: collision with root package name */
    private BreakPointHelper f6238j;

    /* renamed from: com.jd.libs.xwin.http.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public interface InterfaceC0173a {
        void onError(int i2, Map<String, List<String>> map, String str);

        void onProgress(int i2);

        void onStart();

        void onSusses(int i2, Map<String, List<String>> map, String str);
    }

    public a(String str) {
        super(str);
        this.f6237i = str;
        this.f6238j = BreakPointHelper.breakPointSwitch ? BreakPointHelper.getInstance() : null;
    }

    private void a(int i2, int i3) {
        InterfaceC0173a interfaceC0173a = this.f6235g;
        if (interfaceC0173a == null || i2 <= 0) {
            return;
        }
        interfaceC0173a.onProgress(new BigDecimal(i3).multiply(new BigDecimal(100)).divide(new BigDecimal(i2), 0, 4).intValue());
    }

    public void b(InterfaceC0173a interfaceC0173a) {
        this.f6235g = interfaceC0173a;
    }

    public void c(String str) {
        this.f6236h = str;
        BreakPointHelper breakPointHelper = this.f6238j;
        if (breakPointHelper != null) {
            String breakPointBytes = breakPointHelper.getBreakPointBytes(this.f6237i, str);
            if (breakPointBytes.contains("bytes")) {
                addHeader("RANGE", breakPointBytes);
            }
        }
    }

    @Override // com.jd.libs.xwin.http.BaseRequest
    public void onError(int i2, String str) {
        InterfaceC0173a interfaceC0173a = this.f6235g;
        if (interfaceC0173a != null) {
            interfaceC0173a.onError(i2, null, str);
        }
    }

    @Override // com.jd.libs.xwin.http.BaseRequest
    public void onStart() {
        InterfaceC0173a interfaceC0173a = this.f6235g;
        if (interfaceC0173a != null) {
            interfaceC0173a.onStart();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0071, code lost:
        if (r1 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0073, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x009a, code lost:
        if (r1 != null) goto L43;
     */
    @Override // com.jd.libs.xwin.http.BaseRequest
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onSuccess(int r7, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r8, int r9, java.io.InputStream r10) {
        /*
            r6 = this;
            r0 = 200(0xc8, float:2.8E-43)
            if (r7 == r0) goto L1c
            r1 = 206(0xce, float:2.89E-43)
            if (r7 != r1) goto L9
            goto L1c
        L9:
            com.jd.libs.xwin.http.a$a r9 = r6.f6235g
            if (r9 == 0) goto L12
            java.lang.String r0 = "code is not 200"
            r9.onError(r7, r8, r0)
        L12:
            if (r10 == 0) goto La0
            r10.close()     // Catch: java.io.IOException -> L19
            goto La0
        L19:
            r7 = move-exception
            goto L9d
        L1c:
            r1 = 0
            r2 = -1
            int r3 = r6.mMethod     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
            r4 = 261(0x105, float:3.66E-43)
            if (r3 == r4) goto L68
            com.jd.libs.xwin.http.BreakPointHelper r3 = r6.f6238j     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
            if (r3 == 0) goto L2f
            java.lang.String r4 = r6.f6237i     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
            java.lang.String r5 = r6.f6236h     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
            r3.addBreakPointInfo(r4, r5)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
        L2f:
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
            java.lang.String r4 = r6.f6236h     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
            r5 = 0
            if (r7 == r0) goto L38
            r0 = 1
            goto L39
        L38:
            r0 = 0
        L39:
            r3.<init>(r4, r0)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
            java.io.BufferedOutputStream r0 = new java.io.BufferedOutputStream     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
            r0.<init>(r3)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
            r1 = 8192(0x2000, float:1.14794E-41)
            byte[] r1 = new byte[r1]     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L65
            r3 = 0
        L46:
            int r4 = r10.read(r1)     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L65
            if (r4 == r2) goto L54
            r0.write(r1, r5, r4)     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L65
            int r3 = r3 + r4
            r6.a(r9, r3)     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L65
            goto L46
        L54:
            r0.flush()     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L65
            com.jd.libs.xwin.http.BreakPointHelper r9 = r6.f6238j     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L65
            if (r9 == 0) goto L60
            java.lang.String r10 = r6.f6237i     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L65
            r9.removeBreakPointInfo(r10)     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L65
        L60:
            r1 = r0
            goto L68
        L62:
            r7 = move-exception
            r1 = r0
            goto La4
        L65:
            r7 = move-exception
            r1 = r0
            goto L7b
        L68:
            com.jd.libs.xwin.http.a$a r9 = r6.f6235g     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
            if (r9 == 0) goto L71
            java.lang.String r10 = r6.f6236h     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
            r9.onSusses(r7, r8, r10)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L79
        L71:
            if (r1 == 0) goto La0
        L73:
            r1.close()     // Catch: java.io.IOException -> L19
            goto La0
        L77:
            r7 = move-exception
            goto L7b
        L79:
            r7 = move-exception
            goto La4
        L7b:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L79
            com.jd.libs.xwin.http.a$a r9 = r6.f6235g     // Catch: java.lang.Throwable -> L79
            if (r9 == 0) goto L9a
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79
            r10.<init>()     // Catch: java.lang.Throwable -> L79
            java.lang.String r0 = "write file error: "
            r10.append(r0)     // Catch: java.lang.Throwable -> L79
            java.lang.String r7 = r7.getMessage()     // Catch: java.lang.Throwable -> L79
            r10.append(r7)     // Catch: java.lang.Throwable -> L79
            java.lang.String r7 = r10.toString()     // Catch: java.lang.Throwable -> L79
            r9.onError(r2, r8, r7)     // Catch: java.lang.Throwable -> L79
        L9a:
            if (r1 == 0) goto La0
            goto L73
        L9d:
            r7.printStackTrace()
        La0:
            r6.disconnect()
            return
        La4:
            if (r1 == 0) goto Lae
            r1.close()     // Catch: java.io.IOException -> Laa
            goto Lae
        Laa:
            r8 = move-exception
            r8.printStackTrace()
        Lae:
            goto Lb0
        Laf:
            throw r7
        Lb0:
            goto Laf
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.xwin.http.a.onSuccess(int, java.util.Map, int, java.io.InputStream):void");
    }
}
