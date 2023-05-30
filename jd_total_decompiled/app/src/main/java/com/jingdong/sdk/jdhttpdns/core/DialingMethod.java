package com.jingdong.sdk.jdhttpdns.core;

import com.jingdong.sdk.jdhttpdns.utils.DNSLog;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/* loaded from: classes7.dex */
public class DialingMethod {
    public static final String TAG = "DialingMethod";

    /* JADX WARN: Removed duplicated region for block: B:33:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static long connect(String str, int i2) {
        long j2;
        if (i2 == 0) {
            i2 = 443;
        }
        Socket socket = null;
        r0 = null;
        Socket socket2 = null;
        socket = null;
        long j3 = 0;
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            try {
                j2 = System.currentTimeMillis();
                try {
                    Socket socket3 = new Socket();
                    try {
                        SocketAddress inetSocketAddress = new InetSocketAddress(str, i2);
                        socket3.connect(inetSocketAddress, 2000);
                        socket3.sendUrgentData(255);
                        j3 = System.currentTimeMillis();
                        if (!socket3.isClosed()) {
                            socket3.shutdownInput();
                        }
                        socket3.shutdownOutput();
                        socket3.close();
                        socket = inetSocketAddress;
                    } catch (IOException e3) {
                        e = e3;
                        socket2 = socket3;
                        e.printStackTrace();
                        if (socket2 != null && !socket2.isClosed()) {
                            socket2.shutdownInput();
                        }
                        socket2.shutdownOutput();
                        socket2.close();
                        socket = socket2;
                        long j4 = j3 - j2;
                        if (DNSLog.D) {
                        }
                        return j4;
                    } catch (Throwable th) {
                        th = th;
                        socket = socket3;
                        if (socket != null) {
                            try {
                                if (!socket.isClosed()) {
                                    socket.shutdownInput();
                                }
                            } catch (IOException e4) {
                                e4.printStackTrace();
                                throw th;
                            }
                        }
                        socket.shutdownOutput();
                        socket.close();
                        throw th;
                    }
                } catch (IOException e5) {
                    e = e5;
                }
            } catch (IOException e6) {
                e = e6;
                j2 = 0;
            }
            long j42 = j3 - j2;
            if (DNSLog.D) {
                String str2 = "ip : " + str + ", rtt time : " + j42;
            }
            return j42;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
