package com.jingdong.manto.p.d;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* loaded from: classes16.dex */
public class b {

    /* loaded from: classes16.dex */
    public class a implements Callable<c> {
        final /* synthetic */ c a;
        final /* synthetic */ int b;

        a(c cVar, int i2) {
            this.a = cVar;
            this.b = i2;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public c call() {
            return b.a(this.a, this.b);
        }
    }

    public static long a(String str, int i2, int i3) {
        long j2;
        if (i2 == 0) {
            i2 = 443;
        }
        Socket socket = null;
        r0 = null;
        socket = null;
        Socket socket2 = null;
        long j3 = 0;
        try {
            try {
                try {
                    j2 = System.currentTimeMillis();
                    try {
                        Socket socket3 = new Socket();
                        try {
                            SocketAddress inetSocketAddress = new InetSocketAddress(str, i2);
                            socket3.connect(inetSocketAddress, i3);
                            socket3.sendUrgentData(255);
                            j3 = System.currentTimeMillis();
                            if (!socket3.isClosed()) {
                                socket3.shutdownInput();
                            }
                            socket3.shutdownOutput();
                            socket3.close();
                            socket = inetSocketAddress;
                        } catch (IOException e2) {
                            e = e2;
                            socket2 = socket3;
                            e.printStackTrace();
                            if (socket2 != null && !socket2.isClosed()) {
                                socket2.shutdownInput();
                            }
                            socket2.shutdownOutput();
                            socket2.close();
                            socket = socket2;
                            return j3 - j2;
                        } catch (Throwable th) {
                            th = th;
                            socket = socket3;
                            if (socket != null) {
                                try {
                                    if (!socket.isClosed()) {
                                        socket.shutdownInput();
                                    }
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    throw th;
                                }
                            }
                            socket.shutdownOutput();
                            socket.close();
                            throw th;
                        }
                    } catch (IOException e4) {
                        e = e4;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e5) {
                e = e5;
                j2 = 0;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
        return j3 - j2;
    }

    public static c a(c cVar, int i2) {
        cVar.d = a(cVar.f13916c, 443, i2);
        return cVar;
    }

    public static c a(ArrayList<c> arrayList, int i2) {
        c cVar;
        c cVar2 = null;
        if (arrayList != null && arrayList.size() > 0) {
            int size = arrayList.size();
            Future[] futureArr = new Future[size];
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                futureArr[i3] = ((ExecutorService) com.jingdong.manto.b.d().networkIO()).submit(new a(arrayList.get(i3), i2));
            }
            ArrayList arrayList2 = new ArrayList();
            for (int i4 = 0; i4 < size; i4++) {
                try {
                    cVar = (c) futureArr[i4].get();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    cVar = null;
                }
                if (cVar != null && cVar.d > 0) {
                    arrayList2.add(cVar);
                }
            }
            if (arrayList2.size() > 0) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    c cVar3 = (c) it.next();
                    if (cVar2 == null || cVar2.d > cVar3.d) {
                        cVar2 = cVar3;
                    }
                }
            }
        }
        return cVar2;
    }
}
