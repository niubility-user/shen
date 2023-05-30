package com.jd.manto.hd.tcp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.refact.tcp.JsApiTcpServerSocket;
import com.jingdong.manto.jsapi.refact.tcp.LocalInfo;
import com.jingdong.manto.jsapi.refact.udp.RemoteInfo;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes17.dex */
public class JsApiTcpServerNew extends JsApiTcpServerSocket {
    private ConcurrentHashMap<String, SparseArray<ServerSocket>> a = new ConcurrentHashMap<>();
    private ConcurrentHashMap<ServerSocket, ConcurrentHashMap<String, Socket>> b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private ExecutorService f6694c = Executors.newCachedThreadPool();
    private String d = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements Runnable {
        final /* synthetic */ ServerSocket a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f6695c;

        a(ServerSocket serverSocket, int i2, String str) {
            this.a = serverSocket;
            this.b = i2;
            this.f6695c = str;
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
                        JsApiTcpServerNew.this.a(this.a, accept, hostAddress, port);
                        JsApiTcpServerNew.this.a(this.b, this.a, accept, hostAddress, port);
                        JsApiTcpServerNew.this.onTCPServerConnected(this.b, hostAddress, port);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } finally {
                    JsApiTcpServerNew.this.closeTCPServer(this.f6695c, this.b);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements Runnable {
        final /* synthetic */ Socket a;
        final /* synthetic */ ServerSocket b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f6696c;
        final /* synthetic */ int d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f6697e;

        b(Socket socket, ServerSocket serverSocket, String str, int i2, int i3) {
            this.a = socket;
            this.b = serverSocket;
            this.f6696c = str;
            this.d = i2;
            this.f6697e = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                byte[] bArr = new byte[1024];
                InputStream inputStream = this.a.getInputStream();
                while (true) {
                    Socket socket = this.a;
                    if (socket == null || socket.isClosed()) {
                        return;
                    }
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        JsApiTcpServerNew.this.b(this.b, this.f6696c, this.d);
                        JsApiTcpServerNew.this.onTCPServerDisconnected(this.f6697e, this.f6696c, this.d);
                        return;
                    }
                    byte[] bArr2 = new byte[read];
                    System.arraycopy(bArr, 0, bArr2, 0, read);
                    RemoteInfo remoteInfo = new RemoteInfo();
                    remoteInfo.address = this.a.getInetAddress().getHostAddress();
                    remoteInfo.port = this.a.getPort();
                    remoteInfo.size = read;
                    remoteInfo.family = JsApiTcpServerNew.this.c(remoteInfo.address);
                    LocalInfo localInfo = new LocalInfo();
                    localInfo.address = this.a.getLocalAddress().getHostAddress();
                    localInfo.port = this.a.getLocalPort();
                    localInfo.family = JsApiTcpServerNew.this.c(localInfo.address);
                    JsApiTcpServerNew.this.onTCPServerMessage(this.f6697e, bArr2, remoteInfo, localInfo);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements Runnable {
        final /* synthetic */ Socket a;
        final /* synthetic */ byte[] b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f6699c;

        c(Socket socket, byte[] bArr, int i2) {
            this.a = socket;
            this.b = bArr;
            this.f6699c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Socket socket = this.a;
                if (socket == null || !socket.isConnected()) {
                    JsApiTcpServerNew.this.onTCPServerError(this.f6699c, -2, "tcpServer is null");
                } else {
                    try {
                        OutputStream outputStream = socket.getOutputStream();
                        if (outputStream != null) {
                            outputStream.write(this.b);
                            outputStream.flush();
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        JsApiTcpServerNew.this.onTCPServerError(this.f6699c, -2, "write socket fail : socket write failed");
                    }
                }
            } catch (Throwable th) {
                JsApiTcpServerNew.this.onTCPServerError(this.f6699c, -2, th.getMessage());
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

    private synchronized ServerSocket a(String str, int i2) {
        SparseArray<ServerSocket> sparseArray;
        sparseArray = this.a.get(str);
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            this.a.put(str, sparseArray);
        }
        return sparseArray.get(i2);
    }

    private synchronized Socket a(ServerSocket serverSocket, String str, int i2) {
        ConcurrentHashMap<String, Socket> concurrentHashMap;
        concurrentHashMap = this.b.get(serverSocket);
        if (concurrentHashMap == null) {
            concurrentHashMap = new ConcurrentHashMap<>();
            this.b.put(serverSocket, concurrentHashMap);
        }
        return concurrentHashMap.get(str + ":" + i2);
    }

    private synchronized JSONArray a(ServerSocket serverSocket) {
        JSONArray jSONArray;
        jSONArray = new JSONArray();
        ConcurrentHashMap<String, Socket> concurrentHashMap = this.b.get(serverSocket);
        if (concurrentHashMap != null) {
            try {
                for (Socket socket : concurrentHashMap.values()) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, socket.getInetAddress().getHostAddress());
                    jSONObject.put(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, socket.getPort());
                    jSONArray.put(jSONObject);
                }
            } catch (Exception unused) {
            }
        }
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, ServerSocket serverSocket, Socket socket, String str, int i3) {
        this.f6694c.execute(new b(socket, serverSocket, str, i3, i2));
    }

    private synchronized void a(String str, int i2, ServerSocket serverSocket) {
        SparseArray<ServerSocket> sparseArray = this.a.get(str);
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            this.a.put(str, sparseArray);
        }
        sparseArray.put(i2, serverSocket);
    }

    private void a(String str, int i2, Socket socket, byte[] bArr) {
        this.f6694c.execute(new c(socket, bArr, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(ServerSocket serverSocket, Socket socket, String str, int i2) {
        ConcurrentHashMap<String, Socket> concurrentHashMap = this.b.get(serverSocket);
        if (concurrentHashMap == null) {
            concurrentHashMap = new ConcurrentHashMap<>();
            this.b.put(serverSocket, concurrentHashMap);
        }
        concurrentHashMap.put(str + ":" + i2, socket);
    }

    private boolean a(String str) {
        SparseArray<ServerSocket> sparseArray = this.a.get(str);
        return sparseArray != null && sparseArray.size() > 20;
    }

    private void b(String str) {
        SparseArray<ServerSocket> sparseArray = this.a.get(str);
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            ServerSocket valueAt = sparseArray.valueAt(i2);
            if (valueAt != null) {
                ConcurrentHashMap<String, Socket> concurrentHashMap = this.b.get(valueAt);
                if (concurrentHashMap != null) {
                    for (Socket socket : concurrentHashMap.values()) {
                        if (socket != null) {
                            try {
                                socket.close();
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
                try {
                    valueAt.close();
                } catch (Exception unused2) {
                }
            }
        }
        sparseArray.clear();
    }

    private synchronized void b(String str, int i2) {
        SparseArray<ServerSocket> sparseArray = this.a.get(str);
        if (sparseArray == null) {
            return;
        }
        sparseArray.remove(i2);
        if (sparseArray.size() == 0) {
            this.a.remove(this.d);
        }
    }

    private void b(String str, int i2, ServerSocket serverSocket) {
        this.f6694c.execute(new a(serverSocket, i2, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(ServerSocket serverSocket, String str, int i2) {
        ConcurrentHashMap<String, Socket> concurrentHashMap = this.b.get(serverSocket);
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str + ":" + i2);
        if (concurrentHashMap.size() == 0) {
            this.b.remove(serverSocket);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(String str) {
        return (!InetAddressUtils.isIPv4Address(str) && InetAddressUtils.isIPv6Address(str)) ? "IPv6" : "IPv4";
    }

    @Override // com.jingdong.manto.jsapi.refact.tcp.JsApiTcpServerSocket
    public Bundle closeTCPServer(String str, int i2) {
        boolean z;
        String message;
        try {
            ServerSocket a2 = a(str, i2);
            if (a2 != null) {
                b(str, i2);
                ConcurrentHashMap<String, Socket> concurrentHashMap = this.b.get(a2);
                if (concurrentHashMap != null) {
                    for (Socket socket : concurrentHashMap.values()) {
                        if (socket != null) {
                            try {
                                socket.close();
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
                try {
                    a2.close();
                } catch (Exception unused2) {
                }
                onTCPServerClose(i2);
                z = true;
                message = "";
            } else {
                message = "tcpServer is null";
                z = false;
            }
            try {
                ConcurrentHashMap<String, SparseArray<ServerSocket>> concurrentHashMap2 = this.a;
                if (concurrentHashMap2 == null || concurrentHashMap2.get(str) == null || this.a.get(str).size() == 0) {
                    unRegisterLifecycle(str);
                }
            } catch (Exception e2) {
                e = e2;
                message = e.getMessage();
                return a(z, 0, message);
            }
        } catch (Exception e3) {
            e = e3;
            z = false;
        }
        return a(z, 0, message);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:(10:22|23|24|25|27|28|29|(1:31)(1:34)|32|33)|39|23|24|25|27|28|29|(0)(0)|32|33) */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005c, code lost:
        r2 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0073  */
    @Override // com.jingdong.manto.jsapi.refact.tcp.JsApiTcpServerSocket
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Bundle createTCPServer(String str, int i2, int i3) {
        String str2;
        int i4;
        ServerSocket serverSocket;
        boolean z = false;
        if (a(str)) {
            return a(false, -3, "create socket too much");
        }
        ServerSocket a2 = a(str, i2);
        if (a2 != null) {
            try {
                b(str, i2);
                a2.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        boolean z2 = true;
        try {
            str2 = getWifiIpAddress();
            try {
            } catch (Throwable unused) {
                i4 = 0;
                z2 = z;
                Bundle bundle = new Bundle();
                bundle.putString(IMantoBaseModule.ERROR_CODE, !z2 ? "1" : "fail");
                bundle.putString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, str2);
                bundle.putInt(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, i4);
                return bundle;
            }
        } catch (Throwable unused2) {
            str2 = "";
        }
        if (!TextUtils.isEmpty(str2) && !TextUtils.equals(str2, "UNKNOWN_TEXT")) {
            if (i3 > 0 && i3 <= 65535) {
                serverSocket = new ServerSocket(i3);
                serverSocket.setReuseAddress(true);
                i4 = serverSocket.getLocalPort();
                a(str, i2, serverSocket);
                b(str, i2, serverSocket);
                this.d = str;
                registerLifecycle(str);
                Bundle bundle2 = new Bundle();
                bundle2.putString(IMantoBaseModule.ERROR_CODE, !z2 ? "1" : "fail");
                bundle2.putString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, str2);
                bundle2.putInt(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, i4);
                return bundle2;
            }
            serverSocket = new ServerSocket(0, 50);
            serverSocket.setReuseAddress(true);
            i4 = serverSocket.getLocalPort();
            a(str, i2, serverSocket);
            b(str, i2, serverSocket);
            this.d = str;
            registerLifecycle(str);
            Bundle bundle22 = new Bundle();
            bundle22.putString(IMantoBaseModule.ERROR_CODE, !z2 ? "1" : "fail");
            bundle22.putString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, str2);
            bundle22.putInt(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, i4);
            return bundle22;
        }
        return a(false, -3, "TCPServer\u53ea\u5141\u8bb8\u5c40\u57df\u7f51wifi\u6a21\u5f0f\u4e0b\u521b\u5efa");
    }

    @Override // com.jingdong.manto.jsapi.refact.tcp.JsApiTcpServerSocket
    public Bundle disconnectTCPClient(String str, int i2, String str2, int i3) {
        boolean z;
        ServerSocket a2;
        String str3 = "";
        try {
            a2 = a(str, i2);
        } catch (Exception unused) {
        }
        if (a2 == null) {
            str3 = "tcpServer is null";
            z = false;
            return a(z, 0, str3);
        }
        Socket a3 = a(a2, str2, i3);
        b(a2, str2, i3);
        a3.close();
        onTCPServerDisconnected(i2, str2, i3);
        z = true;
        return a(z, 0, str3);
    }

    @Override // com.jingdong.manto.jsapi.refact.tcp.JsApiTcpServerSocket
    public Bundle getTCPServerConnectedList(String str, int i2) {
        String str2;
        ServerSocket a2;
        Bundle bundle = new Bundle();
        try {
            a2 = a(str, i2);
        } catch (Exception unused) {
        }
        if (a2 != null) {
            str2 = a(a2).toString();
            bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
            bundle.putInt("serverId", i2);
            bundle.putString("clients", str2);
            return bundle;
        }
        str2 = "[]";
        bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
        bundle.putInt("serverId", i2);
        bundle.putString("clients", str2);
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.refact.tcp.JsApiTcpServerSocket
    public void onDestroy() {
        b(this.d);
        unRegisterLifecycle(this.d);
        this.d = "";
    }

    @Override // com.jingdong.manto.jsapi.refact.tcp.JsApiTcpServerSocket
    public void sendTCPServerMessage(String str, int i2, String str2, int i3, String str3, MantoResultCallBack mantoResultCallBack) {
        byte[] bytes = str3 != null ? str3.getBytes() : new byte[0];
        sendTCPServerMessage(str, i2, str2, i3, bytes, 0, bytes.length, mantoResultCallBack);
    }

    @Override // com.jingdong.manto.jsapi.refact.tcp.JsApiTcpServerSocket
    public void sendTCPServerMessage(String str, int i2, String str2, int i3, byte[] bArr, int i4, int i5, MantoResultCallBack mantoResultCallBack) {
        try {
            a(str, i2, a(a(str, i2), str2, i3), bArr);
            if (mantoResultCallBack != null) {
                mantoResultCallBack.onSuccess(a(true, 0, ""));
            }
        } catch (Exception e2) {
            if (mantoResultCallBack != null) {
                mantoResultCallBack.onFailed(a(false, -2, e2.getMessage()));
            }
            onTCPServerError(i2, -1, e2.getMessage());
        }
    }
}
