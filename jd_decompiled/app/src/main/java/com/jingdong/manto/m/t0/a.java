package com.jingdong.manto.m.t0;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.c;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.m.t0.b;
import com.jingdong.manto.m.t0.d.d.g;
import com.jingdong.manto.m.t0.d.d.h;
import com.jingdong.manto.m.t0.d.d.i;
import com.jingdong.manto.m.t0.d.e.e;
import com.jingdong.manto.m.t0.d.e.f;
import com.jingdong.manto.receiver.BleScanReceiver;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes15.dex */
public class a {
    public static boolean a;
    public static BroadcastReceiver b;

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, com.jingdong.manto.m.t0.b> f13587c = new ConcurrentHashMap();

    /* renamed from: com.jingdong.manto.m.t0.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0616a implements e {
        final /* synthetic */ f a;
        final /* synthetic */ com.jingdong.manto.m.t0.d.c.e b;

        C0616a(f fVar, com.jingdong.manto.m.t0.d.c.e eVar) {
            this.a = fVar;
            this.b = eVar;
        }

        @Override // com.jingdong.manto.m.t0.d.e.e
        public final void a(int i2) {
        }

        @Override // com.jingdong.manto.m.t0.d.e.e
        public final void a(int i2, com.jingdong.manto.m.t0.d.e.b bVar) {
            IPermission iPermission;
            if (bVar == null || bVar.a() == null || !this.a.a.get() || this.a.f13716j == null) {
                return;
            }
            String address = bVar.a().getAddress();
            boolean z = true;
            boolean z2 = !this.a.f13716j.containsKey(address) || com.jingdong.manto.m.t0.d.a.f13617j.f13621c;
            if (Build.VERSION.SDK_INT >= 31 && ((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) == null || !iPermission.hasPermission("android.permission.BLUETOOTH_CONNECT"))) {
                z = false;
            }
            if (z) {
                h hVar = new h(bVar);
                List<h> a = this.b.a();
                if ((a == null || !a.contains(hVar)) ? z2 : false) {
                    if (com.jingdong.manto.m.t0.d.a.f13617j.b > 0) {
                        List<h> list = this.a.f13713g;
                        if (list != null) {
                            list.add(hVar);
                        }
                    } else {
                        i iVar = this.a.f13715i;
                        if (iVar != null) {
                            iVar.a(hVar);
                        }
                    }
                }
                this.a.f13716j.put(address, hVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b extends BroadcastReceiver {
        b() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            BluetoothAdapter defaultAdapter;
            if (intent == null || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
                return;
            }
            boolean z = defaultAdapter.getState() == 12;
            boolean z2 = a.a;
            if (((z2 && !z) || (!z2 && z)) && a.f13587c != null) {
                Iterator it = a.f13587c.values().iterator();
                while (it.hasNext()) {
                    b.d dVar = ((com.jingdong.manto.m.t0.b) it.next()).f13588c;
                    if (dVar != null) {
                        dVar.a(z);
                    }
                }
            }
            a.a = z;
        }
    }

    public static com.jingdong.manto.m.t0.b a(String str) {
        return f13587c.get(str);
    }

    public static com.jingdong.manto.m.t0.d.d.e a(String str, b.d dVar, com.jingdong.manto.m.t0.d.d.a aVar, g gVar) {
        if (BTHelper.btSupport()) {
            if (!f13587c.containsKey(str)) {
                com.jingdong.manto.m.t0.b bVar = new com.jingdong.manto.m.t0.b(str);
                com.jingdong.manto.m.t0.d.b bVar2 = new com.jingdong.manto.m.t0.d.b(c.a());
                bVar.b = bVar2;
                com.jingdong.manto.m.t0.d.c.e eVar = bVar2.b;
                if (eVar.b == null) {
                    eVar.b = new ConcurrentHashMap();
                }
                eVar.b.clear();
                f fVar = bVar2.a;
                fVar.a.set(true);
                fVar.f13716j = new HashMap();
                fVar.f13713g = new ArrayList();
                fVar.f13711e = new C0616a(fVar, eVar);
                if (fVar.d == null) {
                    BleScanReceiver bleScanReceiver = new BleScanReceiver(fVar);
                    fVar.d = bleScanReceiver;
                    fVar.f13710c.registerReceiver(bleScanReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
                }
                bVar.f13588c = dVar;
                com.jingdong.manto.m.t0.d.c.e eVar2 = bVar2.b;
                if (eVar2 != null) {
                    eVar2.f13641c = aVar;
                }
                if (eVar2 != null) {
                    eVar2.d = gVar;
                }
                bVar.b();
                f13587c.put(str, bVar);
                AppLifeCycle.add(str, bVar);
                b();
            }
            return !BTHelper.btEnabled() ? com.jingdong.manto.m.t0.d.d.e.f13666g : com.jingdong.manto.m.t0.d.d.e.d;
        }
        return com.jingdong.manto.m.t0.d.d.e.r;
    }

    public static com.jingdong.manto.m.t0.d.d.e a(String str, boolean z) {
        BluetoothAdapter defaultAdapter;
        IPermission iPermission;
        if (z && (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) != null) {
            boolean z2 = false;
            if (Build.VERSION.SDK_INT < 31 || ((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) != null && iPermission.hasPermission("android.permission.BLUETOOTH_CONNECT"))) {
                z2 = true;
            }
            if (z2) {
                defaultAdapter.disable();
            }
        }
        if (f13587c.containsKey(str)) {
            com.jingdong.manto.m.t0.b remove = f13587c.remove(str);
            if (remove != null) {
                AppLifeCycle.remove(str, remove);
                remove.c();
            }
            if (f13587c.size() == 0) {
                c();
            }
            return com.jingdong.manto.m.t0.d.d.e.d;
        }
        return com.jingdong.manto.m.t0.d.d.e.d;
    }

    private static synchronized void b() {
        synchronized (a.class) {
            synchronized (a.class) {
                if (b == null) {
                    b = new b();
                    c.a().registerReceiver(b, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
                    a = BTHelper.btEnabled();
                }
            }
        }
    }

    private static synchronized void c() {
        synchronized (a.class) {
            synchronized (a.class) {
                if (b != null) {
                    c.a().unregisterReceiver(b);
                    b = null;
                }
            }
        }
    }
}
