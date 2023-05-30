package com.jd.manto.hd.udp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.refact.udp.JsApiUDPSocket;
import com.jingdong.manto.jsapi.refact.udp.RemoteInfo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes17.dex */
public class JsApiUDPSocketNew extends JsApiUDPSocket {
    private ConcurrentHashMap<String, SparseArray<DatagramSocket>> a = new ConcurrentHashMap<>();
    private ExecutorService b = Executors.newCachedThreadPool();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ DatagramSocket b;

        a(int i2, DatagramSocket datagramSocket) {
            this.a = i2;
            this.b = datagramSocket;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                JsApiUDPSocketNew.this.onUDPSocketListening(this.a);
                byte[] bArr = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(bArr, 1024);
                while (true) {
                    DatagramSocket datagramSocket = this.b;
                    if (datagramSocket == null || datagramSocket.isClosed()) {
                        return;
                    }
                    this.b.receive(datagramPacket);
                    int length = datagramPacket.getLength();
                    byte[] bArr2 = new byte[length];
                    System.arraycopy(bArr, 0, bArr2, 0, length);
                    String str = "udp receive data " + JsApiUDPSocketNew.this.a(bArr2);
                    RemoteInfo remoteInfo = new RemoteInfo();
                    remoteInfo.address = datagramPacket.getAddress().getHostAddress();
                    remoteInfo.port = datagramPacket.getPort();
                    remoteInfo.size = datagramPacket.getLength();
                    JsApiUDPSocketNew.this.onUDPSocketMessage(this.a, bArr2, remoteInfo);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                JsApiUDPSocketNew.this.onUDPSocketError(this.a, "receive socket fail: remote disconnect");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ DatagramPacket f6706c;

        b(String str, int i2, DatagramPacket datagramPacket) {
            this.a = str;
            this.b = i2;
            this.f6706c = datagramPacket;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DatagramSocket a = JsApiUDPSocketNew.this.a(this.a, this.b);
                if (a != null) {
                    a.send(this.f6706c);
                    String str = "sendUDPSocketMessage socketId: " + this.b;
                } else {
                    JsApiUDPSocketNew.this.onUDPSocketError(this.b, "send socket fail : socket send failed");
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                JsApiUDPSocketNew.this.onUDPSocketError(this.b, e2.getMessage());
                String str2 = "sendUDPSocketMessage e: " + e2.getMessage();
            }
        }
    }

    private Bundle a(boolean z, String str) {
        return a(z, (String) null, (String) null, str);
    }

    private Bundle a(boolean z, String str, String str2, String str3) {
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
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(byte[] bArr) {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        int length = bArr != null ? bArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(charArray[(bArr[i2] & 255) >> 4]);
            sb.append(charArray[bArr[i2] & 15]);
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DatagramSocket a(String str, int i2) {
        SparseArray<DatagramSocket> sparseArray = this.a.get(str);
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            this.a.put(str, sparseArray);
        }
        return sparseArray.get(i2);
    }

    private void a(int i2, DatagramSocket datagramSocket) {
        this.b.execute(new a(i2, datagramSocket));
    }

    private void a(String str, int i2, DatagramPacket datagramPacket) {
        this.b.execute(new b(str, i2, datagramPacket));
    }

    private void a(String str, int i2, DatagramSocket datagramSocket) {
        SparseArray<DatagramSocket> sparseArray = this.a.get(str);
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            this.a.put(str, sparseArray);
        }
        sparseArray.put(i2, datagramSocket);
    }

    private void b(String str, int i2) {
        SparseArray<DatagramSocket> sparseArray = this.a.get(str);
        if (sparseArray == null) {
            return;
        }
        sparseArray.remove(i2);
        if (sparseArray.size() == 0) {
            this.a.remove(sparseArray);
        }
    }

    @Override // com.jingdong.manto.jsapi.refact.udp.JsApiUDPSocket
    public Bundle bindUDPSocket(String str, int i2, int i3) {
        String str2;
        DatagramSocket a2;
        String str3 = "bindUDPSocket socketId: " + i2 + " port: " + i3;
        boolean z = false;
        try {
            a2 = a(str, i2);
        } catch (SocketException e2) {
            e2.printStackTrace();
            String message = e2.getMessage();
            String str4 = "bindUDPSocket e: " + e2.getMessage();
            str2 = message;
        }
        if (a2 == null) {
            return a(false, IMediaPlayer.OnNativeInvokeListener.ARG_PORT, String.valueOf(i3), "udpSocket is null");
        }
        if (a2.isBound()) {
            return a(false, IMediaPlayer.OnNativeInvokeListener.ARG_PORT, String.valueOf(i3), "udpSocket has binded");
        }
        if (i3 < 0 || i3 > 65535) {
            i3 = 0;
        }
        a2.bind(new InetSocketAddress(i3));
        i3 = a2.getLocalPort();
        String str5 = "bindUDPSocket port: " + i3;
        a(i2, a2);
        z = true;
        str2 = "";
        return a(z, IMediaPlayer.OnNativeInvokeListener.ARG_PORT, String.valueOf(i3), str2);
    }

    @Override // com.jingdong.manto.jsapi.refact.udp.JsApiUDPSocket
    public Bundle closeUDPSocket(String str, int i2) {
        boolean z;
        String str2;
        DatagramSocket a2 = a(str, i2);
        if (a2 != null) {
            a2.close();
            b(str, i2);
            onUDPSocketClose(i2);
            z = true;
            str2 = "";
        } else {
            z = false;
            str2 = "udpSocket is null";
        }
        return a(z, str2);
    }

    @Override // com.jingdong.manto.jsapi.refact.udp.JsApiUDPSocket
    public Bundle createUDPSocket(String str, int i2) {
        String str2;
        DatagramSocket a2 = a(str, i2);
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
            DatagramSocket datagramSocket = new DatagramSocket((SocketAddress) null);
            datagramSocket.setReuseAddress(true);
            String str3 = "createUDPSocket socketId: " + i2;
            a(str, i2, datagramSocket);
            str2 = "";
        } catch (SocketException e3) {
            e3.printStackTrace();
            String message = e3.getMessage();
            String str4 = "createUDPSocket e: " + e3.getMessage();
            z = false;
            str2 = message;
        }
        return a(z, str2);
    }

    @Override // com.jingdong.manto.jsapi.refact.udp.JsApiUDPSocket
    public void sendUDPSocketMessage(String str, int i2, int i3, String str2, String str3, MantoResultCallBack mantoResultCallBack) {
        byte[] bytes = str3 != null ? str3.getBytes() : new byte[0];
        sendUDPSocketMessage(str, i2, i3, str2, bytes, 0, bytes.length, mantoResultCallBack);
    }

    @Override // com.jingdong.manto.jsapi.refact.udp.JsApiUDPSocket
    public void sendUDPSocketMessage(String str, int i2, int i3, String str2, byte[] bArr, int i4, int i5, MantoResultCallBack mantoResultCallBack) {
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Exception e2) {
                String str3 = "sendUDPSocketMessage e " + e2;
                if (mantoResultCallBack != null) {
                    mantoResultCallBack.onFailed(a(false, e2.getMessage()));
                    return;
                }
                return;
            }
        }
        if (i5 <= 0) {
            i5 = bArr.length;
        }
        a(str, i2, new DatagramPacket(bArr, i4, i5, new InetSocketAddress(str2, i3)));
        if (mantoResultCallBack != null) {
            mantoResultCallBack.onSuccess(a(true, ""));
        }
    }
}
