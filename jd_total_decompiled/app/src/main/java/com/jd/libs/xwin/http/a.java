package com.jd.libs.xwin.http;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    */
    public void onSuccess(int i2, Map<String, List<String>> map, int i3, InputStream inputStream) {
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (i2 != 200 && i2 != 206) {
            InterfaceC0173a interfaceC0173a = this.f6235g;
            if (interfaceC0173a != null) {
                interfaceC0173a.onError(i2, map, "code is not 200");
            }
            if (inputStream != null) {
                inputStream.close();
            }
            disconnect();
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                if (this.mMethod != 261) {
                    BreakPointHelper breakPointHelper = this.f6238j;
                    if (breakPointHelper != null) {
                        breakPointHelper.addBreakPointInfo(this.f6237i, this.f6236h);
                    }
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(this.f6236h, i2 != 200));
                    try {
                        byte[] bArr = new byte[8192];
                        int i4 = 0;
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream2.write(bArr, 0, read);
                            i4 += read;
                            a(i3, i4);
                        }
                        bufferedOutputStream2.flush();
                        BreakPointHelper breakPointHelper2 = this.f6238j;
                        if (breakPointHelper2 != null) {
                            breakPointHelper2.removeBreakPointInfo(this.f6237i);
                        }
                        bufferedOutputStream = bufferedOutputStream2;
                    } catch (Exception e3) {
                        e = e3;
                        bufferedOutputStream = bufferedOutputStream2;
                        e.printStackTrace();
                        InterfaceC0173a interfaceC0173a2 = this.f6235g;
                        if (interfaceC0173a2 != null) {
                            interfaceC0173a2.onError(-1, map, "write file error: " + e.getMessage());
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                InterfaceC0173a interfaceC0173a3 = this.f6235g;
                if (interfaceC0173a3 != null) {
                    interfaceC0173a3.onSusses(i2, map, this.f6236h);
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        disconnect();
    }
}
