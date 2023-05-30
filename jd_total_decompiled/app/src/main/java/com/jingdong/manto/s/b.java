package com.jingdong.manto.s;

import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeSuperMarketModule;
import com.jingdong.manto.f;
import com.jingdong.manto.q.n;
import com.jingdong.manto.sdk.thread.MantoHandler;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public final class b {

    /* renamed from: f  reason: collision with root package name */
    private static volatile b f14165f;

    /* renamed from: g  reason: collision with root package name */
    private static WeakReference<f> f14166g = new WeakReference<>(null);
    private boolean a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private ExecutorService f14167c;
    private Socket d;

    /* renamed from: e  reason: collision with root package name */
    MantoHandler f14168e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (b.this.d == null) {
                    b.this.d = new Socket();
                }
                if (!b.this.d.isClosed() && b.this.d.isConnected()) {
                    MantoLog.d("MantoRealtimeLocalDebugWebSocketMgr", "closed or isConnected");
                    return;
                }
                if (b.this.d.isClosed()) {
                    b.this.d = new Socket();
                }
                b.this.d.bind(new InetSocketAddress("127.0.0.1", (int) JDReactNativeSuperMarketModule.REQUEST_PICTURE));
                b.this.d.connect(new InetSocketAddress("127.0.0.1", (int) R2.drawable.libpdstyleinfoview_bg_styleinfo_pg), 60000);
                b.this.h();
                b.this.a = true;
                b.this.f14168e.b(4);
            } catch (Exception unused) {
                b.this.d = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.s.b$b  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class RunnableC0670b implements Runnable {
        RunnableC0670b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int read;
            try {
                byte[] bArr = new byte[65536];
                InputStream inputStream = b.this.d.getInputStream();
                while (b.this.d != null && !b.this.d.isClosed() && (read = inputStream.read(bArr)) != -1) {
                    byte[] bArr2 = new byte[read];
                    System.arraycopy(bArr, 0, bArr2, 0, read);
                    b.this.a(bArr2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                b.this.a();
                b.this.d = null;
                b.this.a = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class c implements Runnable {
        final /* synthetic */ byte[] a;

        c(byte[] bArr) {
            this.a = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (b.this.d == null || !b.this.d.isConnected()) {
                    return;
                }
                try {
                    OutputStream outputStream = b.this.d.getOutputStream();
                    if (outputStream != null) {
                        outputStream.write(this.a);
                        outputStream.flush();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Throwable unused) {
                b.this.a();
                b.this.d = null;
                b.this.a = false;
            }
        }
    }

    /* loaded from: classes16.dex */
    class d extends MantoHandler {
        d(Looper looper) {
            super(looper);
        }

        @Override // com.jingdong.manto.sdk.thread.MantoHandler, com.jingdong.manto.sdk.thread.d.a
        public final void a(Message message) {
            int i2 = message.what;
            if (i2 == 2) {
                b.this.b();
            } else if (i2 == 3) {
                b.this.g();
            } else if (i2 == 4) {
                b.this.f();
            } else if (i2 == 5 || i2 == 7) {
                b.this.a();
            }
        }
    }

    private b() {
        new HashMap(1);
        this.f14167c = Executors.newCachedThreadPool();
        this.f14168e = new d(Looper.getMainLooper());
    }

    public static int a(byte[] bArr, int i2) {
        return (bArr[i2 + 3] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        try {
            this.d.close();
        } catch (Exception unused) {
        }
    }

    private void a(f fVar) {
        f14166g = new WeakReference<>(fVar);
    }

    private void a(f fVar, String str) {
        if (fVar == null) {
            return;
        }
        try {
            fVar.f13015g.a("remoteDebugCommand", str, 0);
            n i2 = fVar.f13014f.getFirstPage().i();
            i2.a("remoteDebugCommand", str, i2.hashCode());
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        try {
            if (bArr.length == "native:server closed".getBytes().length && TextUtils.equals(new String(bArr), "native:server closed")) {
                this.f14168e.b(5);
                return;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        System.arraycopy(bArr, 4, bArr3, 0, 4);
        a(bArr2, 0);
        int a2 = a(bArr3, 0);
        byte[] bArr4 = new byte[a2];
        System.arraycopy(bArr, 8, bArr4, 0, a2);
        String str = new String(bArr4);
        MantoLog.d("MantoRealtimeLocalDebugWebSocketMgr", "handleData: " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.optString("code");
            a(f14166g.get(), jSONObject.optString("result"));
        } catch (Exception unused) {
        }
        int i2 = a2 + 8;
        if (bArr.length > i2) {
            int length = (bArr.length - 8) - a2;
            byte[] bArr5 = new byte[length];
            System.arraycopy(bArr, i2, bArr5, 0, length);
            a(bArr5);
        }
    }

    public static byte[] a(int i2) {
        return new byte[]{(byte) ((i2 >> 24) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255)};
    }

    private void b(byte[] bArr) {
        this.f14167c.execute(new c(bArr));
    }

    public static b e() {
        if (f14165f == null) {
            synchronized (b.class) {
                if (f14165f == null) {
                    f14165f = new b();
                }
            }
        }
        return f14165f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("phoneModel", BaseInfo.getDeviceModel());
            jSONObject2.put("phoneBrand", BaseInfo.getDeviceBrand());
            jSONObject2.put("system", Build.VERSION.SDK_INT);
            jSONObject2.put("appVersion", com.jingdong.manto.b.g().b("versionName"));
            jSONObject.put("debug", "2");
            jSONObject.put("msg", jSONObject2.toString());
        } catch (Exception unused) {
        }
        a(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.f14167c.execute(new RunnableC0670b());
    }

    public synchronized void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        byte[] bytes = jSONObject.toString().getBytes();
        byte[] a2 = a(9);
        byte[] a3 = a(bytes.length);
        byte[] bArr = new byte[bytes.length + 8];
        System.arraycopy(a2, 0, bArr, 0, a2.length);
        System.arraycopy(a3, 0, bArr, 4, a3.length);
        System.arraycopy(bytes, 0, bArr, 8, bytes.length);
        b(bArr);
    }

    public void a(boolean z) {
        this.b = z;
    }

    public void b() {
        this.f14167c.execute(new a());
    }

    public void b(f fVar, String str) {
        MantoHandler mantoHandler;
        int i2;
        a(fVar);
        Socket socket = this.d;
        if (socket != null) {
            if (!socket.isClosed()) {
                mantoHandler = this.f14168e;
                i2 = 4;
                mantoHandler.b(i2);
            }
            a();
            this.d = null;
        }
        mantoHandler = this.f14168e;
        i2 = 2;
        mantoHandler.b(i2);
    }

    public boolean c() {
        return this.a;
    }

    public boolean d() {
        return this.b;
    }
}
