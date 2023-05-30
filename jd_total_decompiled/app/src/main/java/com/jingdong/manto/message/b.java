package com.jingdong.manto.message;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import com.jingdong.manto.a;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes15.dex */
public class b {
    private static com.jingdong.manto.a a;
    private static ServiceConnection b;

    /* renamed from: c  reason: collision with root package name */
    private static final Set<com.jingdong.manto.message.a> f13859c = Collections.newSetFromMap(new ConcurrentHashMap());
    private static Map<String, c> d = new ConcurrentHashMap();

    /* renamed from: e  reason: collision with root package name */
    private static Handler f13860e = new a(Looper.getMainLooper());

    /* renamed from: f  reason: collision with root package name */
    private static Messenger f13861f = new Messenger(f13860e);

    /* renamed from: g  reason: collision with root package name */
    private static final LinkedList<Parcel> f13862g = new LinkedList<>();

    /* loaded from: classes15.dex */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            c a = b.a(message.getData());
            if (a == null) {
                MantoLog.e("MantoAcrossProcessSub", "cannot parse work");
                return;
            }
            c a2 = b.a(a.b);
            if (a2 == null) {
                MantoLog.e("MantoAcrossProcessSub", String.format("cannot find work code %s", a.b));
                return;
            }
            b.a(a, a2);
            a2.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.message.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class ServiceConnectionC0641b implements ServiceConnection {
        ServiceConnectionC0641b() {
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            com.jingdong.manto.a unused = b.a = a.AbstractBinderC0502a.a(iBinder);
            b.b();
            b.b(com.jingdong.manto.c.b());
            try {
                b.a.a(new Binder(), MantoProcessUtil.getProcessName());
            } catch (Exception e2) {
                MantoLog.e("MantoAcrossProcessSub", "onServiceConnected error ", e2);
            }
            MantoLog.i("MantoAcrossProcessSub", String.format("onServiceConnected(%s)", com.jingdong.manto.c.b()));
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            com.jingdong.manto.a unused = b.a = null;
            b.c(com.jingdong.manto.c.b());
            MantoLog.i("MantoAcrossProcessSub", String.format("onServiceDisconnected(%s)", com.jingdong.manto.c.b()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Bundle a(c cVar, boolean z) {
        Bundle bundle = new Bundle(3);
        bundle.putParcelable("workContent", cVar);
        if (z) {
            bundle.putParcelable("workMsg", f13861f);
        }
        bundle.putString("workCode", cVar.b);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static c a(Bundle bundle) {
        bundle.setClassLoader(c.class.getClassLoader());
        c cVar = (c) bundle.getParcelable("workContent");
        if (cVar == null) {
            return null;
        }
        cVar.b = bundle.getString("workCode");
        if (bundle.containsKey("workMsg")) {
            cVar.a = (Messenger) bundle.getParcelable("workMsg");
        }
        return cVar;
    }

    static c a(c cVar) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeParcelable(cVar, 0);
            obtain.setDataPosition(0);
            c cVar2 = (c) obtain.readParcelable(cVar.getClass().getClassLoader());
            cVar2.b = cVar.b;
            return cVar2;
        } finally {
            obtain.recycle();
        }
    }

    static c a(String str) {
        if (d.containsKey(str)) {
            return d.get(str);
        }
        return null;
    }

    public static void a(com.jingdong.manto.message.a aVar) {
        f13859c.add(aVar);
    }

    static void a(c cVar, c cVar2) {
        Parcel obtain = Parcel.obtain();
        cVar.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        cVar2.a(obtain);
        obtain.recycle();
    }

    static void b() {
        if (a != null) {
            LinkedList<Parcel> linkedList = f13862g;
            synchronized (linkedList) {
                Iterator<Parcel> it = linkedList.iterator();
                while (it.hasNext()) {
                    Parcel next = it.next();
                    Bundle bundle = new Bundle();
                    bundle.setClassLoader(c.class.getClassLoader());
                    next.setDataPosition(0);
                    bundle.readFromParcel(next);
                    b(bundle);
                    next.recycle();
                }
                f13862g.clear();
            }
        }
    }

    private static void b(Bundle bundle) {
        com.jingdong.manto.a aVar = a;
        if (aVar != null) {
            try {
                aVar.b(bundle);
                return;
            } catch (Exception e2) {
                MantoLog.e("MantoAcrossProcessSub", e2.getMessage());
                return;
            }
        }
        c();
        LinkedList<Parcel> linkedList = f13862g;
        synchronized (linkedList) {
            Parcel obtain = Parcel.obtain();
            bundle.writeToParcel(obtain, 0);
            linkedList.add(obtain);
        }
    }

    public static void b(com.jingdong.manto.message.a aVar) {
        f13859c.remove(aVar);
    }

    public static void b(c cVar) {
        if (cVar == null || !d.containsKey(cVar.b)) {
            return;
        }
        d.remove(cVar.b);
    }

    public static void b(c cVar, boolean z) {
        if (z) {
            d.put(cVar.b, cVar);
        }
        if (MantoProcessUtil.isMainProcess()) {
            cVar = a(cVar);
        }
        b(a(cVar, z));
    }

    static void b(String str) {
        Iterator<com.jingdong.manto.message.a> it = f13859c.iterator();
        while (it.hasNext()) {
            it.next().b(str);
        }
    }

    public static void c() {
        if (b == null) {
            b = new ServiceConnectionC0641b();
        }
        Context a2 = com.jingdong.manto.c.a();
        a2.bindService(new Intent(a2, MantoAcrossProcessMain.class), b, 1);
    }

    static void c(String str) {
        Iterator<com.jingdong.manto.message.a> it = f13859c.iterator();
        while (it.hasNext()) {
            it.next().a(str);
        }
    }

    private static boolean c(Bundle bundle) {
        try {
            a.a(bundle);
            return true;
        } catch (Exception e2) {
            MantoLog.e("MantoAcrossProcessSub", e2.getMessage());
            return false;
        }
    }

    public static boolean c(c cVar) {
        c a2;
        Bundle a3 = a(cVar, false);
        if (c(a3) && (a2 = a(a3)) != null) {
            a(a2, cVar);
            cVar.c();
            return true;
        }
        return false;
    }
}
