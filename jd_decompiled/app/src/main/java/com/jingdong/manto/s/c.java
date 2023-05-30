package com.jingdong.manto.s;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.pkg.PkgManager;
import com.jingdong.manto.pkg.b.e;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.f0;
import com.jingdong.manto.utils.t;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes16.dex */
public final class c {

    /* renamed from: j  reason: collision with root package name */
    private static volatile c f14170j;
    private ServerSocket a;
    private ExecutorService b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f14171c;
    FileOutputStream d;

    /* renamed from: e  reason: collision with root package name */
    int f14172e;

    /* renamed from: f  reason: collision with root package name */
    int f14173f;

    /* renamed from: g  reason: collision with root package name */
    private PkgDetailEntity f14174g;

    /* renamed from: h  reason: collision with root package name */
    private Socket f14175h;

    /* renamed from: i  reason: collision with root package name */
    private Socket f14176i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Runnable {
        final /* synthetic */ ServerSocket a;

        a(ServerSocket serverSocket) {
            this.a = serverSocket;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    try {
                        ServerSocket serverSocket = this.a;
                        if (serverSocket == null || serverSocket.isClosed()) {
                            break;
                        }
                        Socket accept = this.a.accept();
                        int port = accept.getPort();
                        String hostAddress = accept.getInetAddress().getHostAddress();
                        if (port == 54321) {
                            c.this.f14175h = accept;
                            c cVar = c.this;
                            cVar.a(cVar.f14175h);
                        } else {
                            c.this.f14176i = accept;
                            c cVar2 = c.this;
                            cVar2.b(cVar2.f14176i);
                            MantoLog.d("MantoRealtimeServerSocketMgr", "socket connected,  address:" + hostAddress + ", port:" + port);
                            c cVar3 = c.this;
                            cVar3.a(cVar3.f14176i, "socket connected", false);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } finally {
                    MantoLog.d("MantoRealtimeServerSocketMgr", "close server");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements Runnable {
        final /* synthetic */ Socket a;

        b(Socket socket) {
            this.a = socket;
        }

        @Override // java.lang.Runnable
        public void run() {
            int read;
            try {
                byte[] bArr = new byte[65536];
                InputStream inputStream = this.a.getInputStream();
                while (true) {
                    Socket socket = this.a;
                    if (socket != null && !socket.isClosed() && (read = inputStream.read(bArr)) != -1) {
                        byte[] bArr2 = new byte[read];
                        System.arraycopy(bArr, 0, bArr2, 0, read);
                        c.this.b(bArr2);
                    }
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.s.c$c  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class RunnableC0671c implements Runnable {
        final /* synthetic */ Socket a;

        RunnableC0671c(Socket socket) {
            this.a = socket;
        }

        @Override // java.lang.Runnable
        public void run() {
            int read;
            try {
                byte[] bArr = new byte[65536];
                InputStream inputStream = this.a.getInputStream();
                while (true) {
                    Socket socket = this.a;
                    if (socket != null && !socket.isClosed() && (read = inputStream.read(bArr)) != -1) {
                        byte[] bArr2 = new byte[read];
                        System.arraycopy(bArr, 0, bArr2, 0, read);
                        c.this.a(bArr2);
                    }
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class d implements Runnable {
        final /* synthetic */ Socket a;
        final /* synthetic */ byte[] b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ boolean f14177c;

        d(c cVar, Socket socket, byte[] bArr, boolean z) {
            this.a = socket;
            this.b = bArr;
            this.f14177c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Socket socket = this.a;
                if (socket == null || !socket.isConnected()) {
                    return;
                }
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    if (outputStream != null) {
                        outputStream.write(this.b);
                        outputStream.flush();
                    }
                    if (this.f14177c) {
                        this.a.close();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Throwable unused) {
            }
        }
    }

    private c() {
        new HashMap(1);
        this.b = Executors.newCachedThreadPool();
        this.d = null;
    }

    public static int a(byte[] bArr, int i2) {
        return (bArr[i2 + 3] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
    }

    private void a(File file) {
        LaunchParam launchParam = new LaunchParam();
        launchParam.debugType = "14";
        launchParam.sourcePath = file.getAbsolutePath();
        launchParam.appId = "12345";
        launchParam.scene = "native_debug";
        com.jingdong.a.o(launchParam);
    }

    private void a(ServerSocket serverSocket) {
        this.b.execute(new a(serverSocket));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Socket socket) {
        this.b.execute(new RunnableC0671c(socket));
    }

    public static c b() {
        if (f14170j == null) {
            synchronized (c.class) {
                if (f14170j == null) {
                    f14170j = new c();
                }
            }
        }
        return f14170j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Socket socket) {
        this.b.execute(new b(socket));
    }

    private boolean c(byte[] bArr) {
        if (this.d == null) {
            try {
                PkgDetailEntity pkgDetailEntity = new PkgDetailEntity();
                this.f14174g = pkgDetailEntity;
                pkgDetailEntity.appId = "123456";
                pkgDetailEntity.type = "14";
                pkgDetailEntity.build = "1";
                this.d = new FileOutputStream(new File(PkgManager.getPkgZipPath(this.f14174g)), false);
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        try {
            this.d.write(bArr, 0, bArr.length);
            if (this.f14173f >= this.f14172e) {
                t.a(this.d);
                this.d = null;
                this.f14173f = 0;
                this.f14172e = 0;
                this.f14171c = false;
                d();
            }
            return true;
        } catch (IOException unused) {
            if (this.f14173f >= this.f14172e) {
                t.a(this.d);
                this.d = null;
                this.f14173f = 0;
                this.f14172e = 0;
                this.f14171c = false;
                d();
            }
            return true;
        } catch (Throwable unused2) {
            if (this.f14173f >= this.f14172e) {
                t.a(this.d);
                this.d = null;
                this.f14173f = 0;
                this.f14172e = 0;
                this.f14171c = false;
                d();
            }
            return true;
        }
    }

    private void d() {
        File file = new File(PkgManager.getPkgZipPath(this.f14174g));
        List<File> a2 = f0.a(file, file.getParent(), true);
        if (a2 == null || a2.size() <= 0) {
            return;
        }
        File file2 = null;
        int i2 = 0;
        while (true) {
            if (i2 >= a2.size()) {
                break;
            }
            File file3 = a2.get(i2);
            if (file3.getName().endsWith(".jdapkg")) {
                file2 = new File(file.getParent(), file.getName().substring(0, file.getName().lastIndexOf(OrderISVUtil.MONEY_DECIMAL)) + OrderISVUtil.MONEY_DECIMAL + "jdapkg");
                file3.renameTo(file2);
                break;
            }
            i2++;
        }
        if (file2 == null || !a(file2.getAbsolutePath())) {
            MantoLog.d("MantoRealtimeServerSocketMgr", "parse pkg failed");
        } else {
            a(file2);
        }
    }

    public void a() {
        try {
            Socket socket = this.f14175h;
            if (socket != null) {
                a(socket, "native:server closed", true);
                this.f14175h = null;
            }
            Socket socket2 = this.f14176i;
            if (socket2 != null) {
                socket2.close();
                this.f14176i = null;
            }
            ServerSocket serverSocket = this.a;
            if (serverSocket != null) {
                serverSocket.close();
                this.a = null;
            }
        } catch (Throwable unused) {
        }
    }

    public void a(Socket socket, String str, boolean z) {
        a(socket, str != null ? str.getBytes() : new byte[0], z);
    }

    public void a(Socket socket, byte[] bArr, boolean z) {
        this.b.execute(new d(this, socket, bArr, z));
    }

    public void a(byte[] bArr) {
        Socket socket = this.f14176i;
        if (socket != null) {
            a(socket, bArr, false);
        }
    }

    protected boolean a(String str) {
        return new e(str).f13979c;
    }

    public void b(byte[] bArr) {
        int i2;
        if (this.f14171c && (i2 = this.f14173f) < this.f14172e) {
            this.f14173f = i2 + bArr.length;
            c(bArr);
        } else if (bArr.length < 8) {
        } else {
            byte[] bArr2 = new byte[4];
            byte[] bArr3 = new byte[4];
            System.arraycopy(bArr, 0, bArr2, 0, 4);
            System.arraycopy(bArr, 4, bArr3, 0, 4);
            int a2 = a(bArr2, 0);
            int a3 = a(bArr3, 0);
            this.f14172e = a3;
            if (a2 == 1) {
                int length = bArr.length - 8;
                byte[] bArr4 = new byte[length];
                System.arraycopy(bArr, 8, bArr4, 0, length);
                this.f14173f += length;
                this.f14171c = true;
                c(bArr4);
            } else if (a2 == 2) {
                int i3 = a3 + 8;
                byte[] bArr5 = new byte[i3];
                System.arraycopy(bArr, 0, bArr5, 0, i3);
                a(this.f14175h, bArr5, false);
                int length2 = bArr.length;
                int i4 = this.f14172e;
                int i5 = i4 + 8;
                if (length2 > i5) {
                    int length3 = (bArr.length - 8) - i4;
                    byte[] bArr6 = new byte[length3];
                    System.arraycopy(bArr, i5, bArr6, 0, length3);
                    b(bArr6);
                }
            }
        }
    }

    public void c() {
        if (this.a != null) {
            return;
        }
        try {
            ServerSocket serverSocket = new ServerSocket(R2.drawable.libpdstyleinfoview_bg_styleinfo_pg);
            this.a = serverSocket;
            serverSocket.setReuseAddress(true);
            this.a.getLocalPort();
            a(this.a);
        } catch (Throwable unused) {
        }
    }
}
