package com.jingdong.b.a;

import android.text.TextUtils;
import g.c.a.b;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.conn.util.InetAddressUtils;

/* loaded from: classes14.dex */
public class a implements Interceptor {
    private boolean a;

    public a(boolean z) {
        this.a = false;
        this.a = z;
    }

    private InetAddress a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            if (InetAddressUtils.isIPv4Address(str2)) {
                String[] split = str2.split("\\.");
                byte[] bArr = new byte[4];
                for (int i2 = 0; i2 < 4; i2++) {
                    bArr[i2] = (byte) (Integer.parseInt(split[i2]) & 255);
                }
                try {
                    return InetAddress.getByAddress(str, bArr);
                } catch (UnknownHostException e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            if (str2.startsWith("[") && str2.endsWith("]")) {
                str2 = str2.substring(1, str2.length() - 1);
            }
            byte[] bArr2 = new byte[16];
            System.arraycopy(b.fromString(str2).toByteArray(), 0, bArr2, 0, 16);
            try {
                return Inet6Address.getByAddress(str, bArr2);
            } catch (UnknownHostException e3) {
                e3.printStackTrace();
                return null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
        th.printStackTrace();
        return null;
    }

    private boolean b(String str) {
        if (str.startsWith("[") && str.endsWith("]")) {
            str = str.substring(1, str.length() - 1);
        }
        return InetAddressUtils.isIPv4Address(str) || InetAddressUtils.isIPv6Address(str);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String header = request.header("host");
        String host = request.url().host();
        if (!TextUtils.isEmpty(header) && b(host)) {
            if (this.a) {
                String str = "\u62e6\u622a\u5230HttpDns\u8bf7\u6c42\uff0cip\u5730\u5740\u4e3a " + host + ",-> url : " + request.url();
            }
            InetAddress a = a(header, host);
            if (a != null) {
                Request.Builder newBuilder = request.newBuilder();
                HttpUrl build = request.url().newBuilder().host(header).externalAddress(a).build();
                Headers.Builder newBuilder2 = request.headers().newBuilder();
                newBuilder2.removeAll("host");
                request = newBuilder.headers(newBuilder2.build()).url(build).build();
            }
        }
        return chain.proceed(request);
    }
}
