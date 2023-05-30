package com.jingdong.manto.p.d;

import android.text.TextUtils;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Dns;
import org.apache.http.conn.util.InetAddressUtils;

/* loaded from: classes16.dex */
public class a implements Dns {
    public static Dns a() {
        return new a();
    }

    InetAddress a(String str, String str2) {
        InetAddress byAddress;
        InetAddress inetAddress = null;
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
                byAddress = InetAddress.getByAddress(str, bArr);
            } else {
                byte[] bArr2 = new byte[0];
                try {
                    bArr2 = InetAddress.getByName(str2).getAddress();
                } catch (UnknownHostException e2) {
                    e2.printStackTrace();
                }
                byte[] bArr3 = new byte[16];
                System.arraycopy(bArr2, 0, bArr3, 0, 16);
                byAddress = Inet6Address.getByAddress(str, bArr3);
            }
            inetAddress = byAddress;
            return inetAddress;
        } catch (UnknownHostException e3) {
            e3.printStackTrace();
            return inetAddress;
        }
    }

    @Override // okhttp3.Dns
    public List<InetAddress> lookup(String str) {
        try {
            String b = d.b().b(str);
            if (!TextUtils.isEmpty(b)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(a(str, b));
                return arrayList;
            }
        } catch (Throwable unused) {
        }
        return Dns.SYSTEM.lookup(str);
    }
}
