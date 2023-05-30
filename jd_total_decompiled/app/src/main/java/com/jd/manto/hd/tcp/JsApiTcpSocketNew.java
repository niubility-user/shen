package com.jd.manto.hd.tcp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.refact.tcp.JsApiTcpSocket;
import com.jingdong.manto.jsapi.refact.tcp.LocalInfo;
import com.jingdong.manto.jsapi.refact.udp.RemoteInfo;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.conn.util.InetAddressUtils;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes17.dex */
public class JsApiTcpSocketNew extends JsApiTcpSocket {
    private ConcurrentHashMap<String, SparseArray<Socket>> a = new ConcurrentHashMap<>();
    private ExecutorService b = Executors.newCachedThreadPool();

    /* loaded from: classes17.dex */
    class a implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f6700c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6701e;

        a(int i2, String str, int i3, String str2, MantoResultCallBack mantoResultCallBack) {
            this.a = i2;
            this.b = str;
            this.f6700c = i3;
            this.d = str2;
            this.f6701e = mantoResultCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = this.f6700c;
            try {
                Socket a = JsApiTcpSocketNew.this.a(this.d, this.a);
                if (a == null) {
                    a = new Socket();
                    JsApiTcpSocketNew.this.a(this.d, this.a, a);
                }
                if (!a.isClosed() && a.isConnected()) {
                    Bundle bundle = new Bundle(1);
                    bundle.putInt(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, i2);
                    bundle.putString("errMsg", "socket has connected");
                    this.f6701e.onFailed(bundle);
                    JsApiTcpSocketNew.this.onTCPSocketError(this.a, -2, "socket has connected");
                    return;
                }
                if (a.isClosed()) {
                    a = new Socket();
                    JsApiTcpSocketNew.this.a(this.d, this.a, a);
                }
                if (i2 < 0 || i2 > 65535) {
                    i2 = 0;
                }
                a.connect(new InetSocketAddress(this.b, i2), 60000);
                JsApiTcpSocketNew.this.b(this.d, this.a, a);
                Bundle bundle2 = new Bundle(1);
                bundle2.putInt(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, i2);
                this.f6701e.onSuccess(bundle2);
            } catch (Exception e2) {
                JsApiTcpSocketNew.this.b(this.d, this.a);
                String message = e2.getMessage();
                Bundle bundle3 = new Bundle(1);
                bundle3.putInt(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, i2);
                bundle3.putString("errMsg", message);
                this.f6701e.onFailed(bundle3);
                JsApiTcpSocketNew.this.onTCPSocketError(this.a, -2, message);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ Socket b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f6703c;

        b(int i2, Socket socket, String str) {
            this.a = i2;
            this.b = socket;
            this.f6703c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                int i2 = this.a;
                JsApiTcpSocketNew.this.onTCPSocketConnect(i2);
                byte[] bArr = new byte[1024];
                InputStream inputStream = this.b.getInputStream();
                while (true) {
                    Socket socket = this.b;
                    if (socket == null || socket.isClosed()) {
                        return;
                    }
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        JsApiTcpSocketNew.this.b(this.f6703c, i2);
                        JsApiTcpSocketNew.this.onTCPSocketError(this.a, -2, "receive socket fail: remote disconnect");
                        return;
                    }
                    byte[] bArr2 = new byte[read];
                    System.arraycopy(bArr, 0, bArr2, 0, read);
                    RemoteInfo remoteInfo = new RemoteInfo();
                    remoteInfo.address = this.b.getInetAddress().getHostAddress();
                    remoteInfo.port = this.b.getPort();
                    remoteInfo.size = read;
                    remoteInfo.family = JsApiTcpSocketNew.this.b(remoteInfo.address);
                    LocalInfo localInfo = new LocalInfo();
                    localInfo.address = this.b.getLocalAddress().getHostAddress();
                    localInfo.port = this.b.getLocalPort();
                    localInfo.family = JsApiTcpSocketNew.this.b(localInfo.address);
                    JsApiTcpSocketNew.this.onTCPSocketMessage(i2, bArr2, remoteInfo, localInfo);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ byte[] f6704c;

        c(String str, int i2, byte[] bArr) {
            this.a = str;
            this.b = i2;
            this.f6704c = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Socket a = JsApiTcpSocketNew.this.a(this.a, this.b);
                if (a == null || !a.isConnected()) {
                    JsApiTcpSocketNew.this.onTCPSocketError(this.b, -3, "tcpSocket is null");
                } else {
                    try {
                        OutputStream outputStream = a.getOutputStream();
                        if (outputStream != null) {
                            outputStream.write(this.f6704c);
                            outputStream.flush();
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        JsApiTcpSocketNew.this.onTCPSocketError(this.b, -3, "write socket fail : socket write failed");
                    }
                }
            } catch (Throwable th) {
                JsApiTcpSocketNew.this.onTCPSocketError(this.b, -3, th.getMessage());
            }
        }
    }

    private Bundle a(boolean z, int i2, String str) {
        return a(z, i2, (String) null, (String) null, str);
    }

    private Bundle a(boolean z, int i2, String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
        } else {
            bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            bundle.putString("message", str3);
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            bundle.putString(str, str2);
        }
        if (i2 != 0) {
            bundle.putInt("errCode", i2);
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Socket a(String str, int i2) {
        SparseArray<Socket> sparseArray;
        sparseArray = this.a.get(str);
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            this.a.put(str, sparseArray);
        }
        return sparseArray.get(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(String str, int i2, Socket socket) {
        SparseArray<Socket> sparseArray = this.a.get(str);
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            this.a.put(str, sparseArray);
        }
        sparseArray.put(i2, socket);
    }

    private void a(String str, int i2, byte[] bArr) {
        this.b.execute(new c(str, i2, bArr));
    }

    private boolean a(String str) {
        SparseArray<Socket> sparseArray = this.a.get(str);
        return sparseArray != null && sparseArray.size() > 20;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(String str) {
        return (!InetAddressUtils.isIPv4Address(str) && InetAddressUtils.isIPv6Address(str)) ? "IPv6" : "IPv4";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(String str, int i2) {
        SparseArray<Socket> sparseArray = this.a.get(str);
        if (sparseArray == null) {
            return;
        }
        sparseArray.remove(i2);
        if (sparseArray.size() == 0) {
            this.a.remove(sparseArray);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, int i2, Socket socket) {
        this.b.execute(new b(i2, socket, str));
    }

    @Override // com.jingdong.manto.jsapi.refact.tcp.JsApiTcpSocket
    public Bundle closeTCPSocket(String str, int i2) {
        boolean z;
        Socket a2;
        String str2 = "";
        try {
            a2 = a(str, i2);
        } catch (Exception unused) {
        }
        if (a2 == null) {
            str2 = "tcpSocket is null";
            z = false;
            return a(z, 0, str2);
        }
        b(str, i2);
        a2.close();
        onTCPSocketClose(i2);
        z = true;
        return a(z, 0, str2);
    }

    @Override // com.jingdong.manto.jsapi.refact.tcp.JsApiTcpSocket
    public void connectTCPSocket(String str, int i2, String str2, int i3, MantoResultCallBack mantoResultCallBack) {
        this.b.execute(new a(i2, str2, i3, str, mantoResultCallBack));
    }

    @Override // com.jingdong.manto.jsapi.refact.tcp.JsApiTcpSocket
    public Bundle createTCPSocket(String str, int i2) {
        if (a(str)) {
            return a(false, -4, "create socket too much");
        }
        Socket a2 = a(str, i2);
        if (a2 != null) {
            try {
                b(str, i2);
                a2.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        boolean z = true;
        try {
            Socket socket = new Socket();
            socket.setReuseAddress(true);
            a(str, i2, socket);
        } catch (Throwable unused) {
            z = false;
        }
        return a(z, 0, "");
    }

    @Override // com.jingdong.manto.jsapi.refact.tcp.JsApiTcpSocket
    public void sendTCPSocketMessage(String str, int i2, String str2, MantoResultCallBack mantoResultCallBack) {
        byte[] bytes = str2 != null ? str2.getBytes() : new byte[0];
        sendTCPSocketMessage(str, i2, bytes, 0, bytes.length, mantoResultCallBack);
    }

    @Override // com.jingdong.manto.jsapi.refact.tcp.JsApiTcpSocket
    public void sendTCPSocketMessage(String str, int i2, byte[] bArr, int i3, int i4, MantoResultCallBack mantoResultCallBack) {
        try {
            a(str, i2, bArr);
            if (mantoResultCallBack != null) {
                mantoResultCallBack.onSuccess(a(true, 0, ""));
            }
        } catch (Exception e2) {
            if (mantoResultCallBack != null) {
                mantoResultCallBack.onFailed(a(false, -3, e2.getMessage()));
            }
            onTCPSocketError(i2, -1, e2.getMessage());
        }
    }
}
