package com.jingdong.manto.p.d;

import com.jingdong.manto.utils.MantoLog;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import okhttp3.Dns;

/* loaded from: classes16.dex */
public class d {
    private HashMap<String, String> a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Callable<List<InetAddress>> {
        final /* synthetic */ String a;

        a(d dVar, String str) {
            this.a = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public List<InetAddress> call() {
            try {
                return Dns.SYSTEM.lookup(this.a);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    /* loaded from: classes16.dex */
    public static class b {
        public static d a = new d(null);
    }

    private d() {
        this.a = new HashMap<>(2);
    }

    /* synthetic */ d(a aVar) {
        this();
    }

    private synchronized void a() {
        this.a.clear();
    }

    public static d b() {
        return b.a;
    }

    List<InetAddress> a(String str) {
        try {
            return (List) ((ExecutorService) com.jingdong.manto.b.d().networkIO()).submit(new a(this, str)).get(250L, TimeUnit.MILLISECONDS);
        } catch (Exception unused) {
            return null;
        }
    }

    public synchronized String b(String str) {
        if (this.a.containsKey(str)) {
            return this.a.get(str);
        }
        long currentTimeMillis = System.currentTimeMillis();
        List<InetAddress> a2 = a(str);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (a2 != null && a2.size() >= 2) {
            MantoLog.d("LocalDNSDailer", "\u57df\u540d\uff1a " + str + " DNS\u67e5\u8be2\u8017\u65f6: " + currentTimeMillis2 + " \u6beb\u79d2.");
            ArrayList arrayList = new ArrayList();
            if (a2 != null && a2.size() > 0) {
                for (InetAddress inetAddress : a2) {
                    c cVar = new c();
                    cVar.f13916c = inetAddress.getHostAddress();
                    if (inetAddress instanceof Inet4Address) {
                        cVar.b = false;
                    } else if (inetAddress instanceof Inet6Address) {
                        cVar.b = true;
                    }
                    arrayList.add(cVar);
                }
            }
            MantoLog.d("LocalDNSDailer", "\u57df\u540d\uff1a " + str + " \u5373\u5c06\u8fdb\u884c\u63a2\u6d4b\u7684IP\u5730\u5740\u4e3a : " + arrayList);
            long currentTimeMillis3 = System.currentTimeMillis();
            c a3 = com.jingdong.manto.p.d.b.a(arrayList, 2000);
            if (a3 == null) {
                return null;
            }
            String str2 = a3.f13916c;
            MantoLog.d("LocalDNSDailer", "\u57df\u540d: " + str + " \u5f97\u5230\u6700\u4f18\u7ed3\u679c\u8017\u65f6: " + (System.currentTimeMillis() - currentTimeMillis3) + " \u6beb\u79d2.");
            this.a.put(str, str2);
            MantoLog.d("LocalDNSDailer", "\u57df\u540d: " + str + " \u6700\u4f73IP\u5730\u5740\u4e3a: " + str2);
            return str2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\u57df\u540d\uff1a ");
        sb.append(str);
        sb.append(" DNS\u67e5\u8be2\u8017\u65f6: ");
        sb.append(currentTimeMillis2);
        sb.append(" \u6beb\u79d2, size: ");
        sb.append(a2 != null ? a2.size() : 0);
        MantoLog.d("LocalDNSDailer", sb.toString());
        return null;
    }

    public void c() {
        a();
    }
}
