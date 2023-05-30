package com.jingdong.manto.m.t0;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.m.t0.d.c.e;
import com.jingdong.manto.m.t0.d.d.g;
import com.jingdong.manto.m.t0.d.d.h;
import com.jingdong.manto.m.t0.d.d.i;
import com.jingdong.manto.m.t0.d.e.f;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes15.dex */
public class b extends AppLifeCycle.Listener {
    private String a;
    public com.jingdong.manto.m.t0.d.b b;

    /* renamed from: c */
    d f13588c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements g {
        final /* synthetic */ e a;

        a(b bVar, e eVar) {
            this.a = eVar;
        }

        @Override // com.jingdong.manto.m.t0.d.d.g
        public void a(String str, String str2, String str3, String str4) {
            g gVar = this.a.d;
            if (gVar != null) {
                gVar.a(str, str2, str3, str4);
            }
        }

        @Override // com.jingdong.manto.m.t0.d.d.g
        public final void b(String str, String str2, String str3, String str4) {
            g gVar = this.a.d;
            if (gVar != null) {
                gVar.b(str, str2, str3, str4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.t0.b$b */
    /* loaded from: classes15.dex */
    public class C0617b implements com.jingdong.manto.m.t0.d.d.a {
        final /* synthetic */ e a;

        C0617b(b bVar, e eVar) {
            this.a = eVar;
        }

        @Override // com.jingdong.manto.m.t0.d.d.a
        public final void a(String str, boolean z) {
            com.jingdong.manto.m.t0.d.d.a aVar = this.a.f13641c;
            if (aVar != null) {
                aVar.a(str, z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class c implements Runnable {
        final /* synthetic */ com.jingdong.manto.m.t0.d.d.c a;

        c(b bVar, com.jingdong.manto.m.t0.d.d.c cVar) {
            this.a = cVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.a.a();
        }
    }

    /* loaded from: classes15.dex */
    public interface d {
        void a(boolean z);
    }

    public b(String str) {
        this.a = str;
    }

    public final List<h> a() {
        e eVar;
        com.jingdong.manto.m.t0.d.b bVar = this.b;
        if (bVar == null || (eVar = bVar.b) == null) {
            return null;
        }
        return eVar.a();
    }

    public final List<com.jingdong.manto.m.t0.d.c.b> a(String str) {
        e eVar;
        com.jingdong.manto.m.t0.d.c.d dVar;
        com.jingdong.manto.m.t0.d.b bVar = this.b;
        if (bVar == null || (eVar = bVar.b) == null || (dVar = eVar.b.get(str)) == null) {
            return null;
        }
        return dVar.c();
    }

    @TargetApi(18)
    public final List<com.jingdong.manto.m.t0.d.d.b> a(String str, String str2) {
        e eVar;
        com.jingdong.manto.m.t0.d.c.d dVar;
        BluetoothGatt bluetoothGatt;
        List<BluetoothGattCharacteristic> characteristics;
        com.jingdong.manto.m.t0.d.b bVar = this.b;
        if (bVar == null || (eVar = bVar.b) == null || (dVar = eVar.b.get(str)) == null || (bluetoothGatt = dVar.b) == null || TextUtils.isEmpty(str2) || !BTHelper.isServiceValid(str2)) {
            return null;
        }
        List<com.jingdong.manto.m.t0.d.d.b> list = dVar.f13639h.get(str2);
        if (list == null) {
            BluetoothGattService service = bluetoothGatt.getService(UUID.fromString(str2));
            if (service == null || (characteristics = service.getCharacteristics()) == null) {
                return null;
            }
            list = new ArrayList<>();
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : characteristics) {
                com.jingdong.manto.m.t0.d.d.b bVar2 = new com.jingdong.manto.m.t0.d.d.b();
                bVar2.a = bluetoothGattCharacteristic.getUuid().toString().toUpperCase();
                int properties = bluetoothGattCharacteristic.getProperties();
                bVar2.f13650c = BTHelper.supportRead(properties);
                bVar2.d = BTHelper.supportWrite(properties);
                bVar2.f13651e = BTHelper.supportWriteWithoutResponse(properties);
                bVar2.b = BTHelper.supportNotify(properties);
                bVar2.f13652f = BTHelper.supportIndicate(properties);
                list.add(bVar2);
            }
            dVar.f13639h.put(str2, list);
        }
        if (dVar.b == null) {
            return null;
        }
        return list;
    }

    @TargetApi(18)
    public final List<h> a(List<String> list) {
        e eVar;
        List<com.jingdong.manto.m.t0.d.c.b> c2;
        com.jingdong.manto.m.t0.d.b bVar = this.b;
        if (bVar == null || (eVar = bVar.b) == null || eVar == null) {
            return null;
        }
        if (list == null || list.isEmpty()) {
            return eVar.a();
        }
        ArrayList arrayList = new ArrayList();
        Map<String, com.jingdong.manto.m.t0.d.c.d> map = eVar.b;
        if (map != null) {
            for (com.jingdong.manto.m.t0.d.c.d dVar : map.values()) {
                if (dVar.b != null && (c2 = dVar.c()) != null && !c2.isEmpty()) {
                    ArrayList arrayList2 = new ArrayList(c2.size());
                    for (com.jingdong.manto.m.t0.d.c.b bVar2 : c2) {
                        if (bVar2.a) {
                            arrayList2.add(bVar2.b);
                        }
                    }
                    if (e.a(list, arrayList2)) {
                        arrayList.add(new h(dVar.b.getDevice().getName(), dVar.a));
                    }
                }
            }
            return arrayList;
        }
        return null;
    }

    public void a(com.jingdong.manto.m.t0.d.a aVar, com.jingdong.manto.m.t0.d.d.d dVar, i iVar, ArrayList<com.jingdong.manto.m.t0.d.e.d> arrayList) {
        f fVar;
        com.jingdong.manto.m.t0.d.b bVar = this.b;
        if (bVar == null || (fVar = bVar.a) == null) {
            return;
        }
        com.jingdong.manto.m.t0.d.a.a(aVar);
        fVar.a(dVar, arrayList, iVar);
    }

    public final void a(String str, com.jingdong.manto.m.t0.d.d.c cVar, com.jingdong.manto.m.t0.d.d.d dVar) {
        e eVar;
        com.jingdong.manto.m.t0.d.b bVar = this.b;
        if (bVar == null || (eVar = bVar.b) == null) {
            return;
        }
        com.jingdong.manto.m.t0.d.c.d dVar2 = eVar.b.get(str);
        if (dVar2 == null) {
            dVar2 = new com.jingdong.manto.m.t0.d.c.d(eVar.a, str, eVar);
            if (dVar2.f13637f == null) {
                com.jingdong.manto.m.t0.d.c.c cVar2 = new com.jingdong.manto.m.t0.d.c.c();
                dVar2.f13637f = cVar2;
                cVar2.b = new ConcurrentLinkedQueue();
                dVar2.f13637f.d = new ArrayList();
            }
            dVar2.f13639h = new ConcurrentHashMap();
            dVar2.f13636e = new a(this, eVar);
            dVar2.d = new C0617b(this, eVar);
            eVar.b.put(str, dVar2);
        }
        cVar.f13656f = dVar2;
        if (dVar2.f13637f == null) {
            com.jingdong.manto.m.t0.d.c.c cVar3 = new com.jingdong.manto.m.t0.d.c.c();
            dVar2.f13637f = cVar3;
            cVar3.b = new ConcurrentLinkedQueue();
            dVar2.f13637f.d = new ArrayList();
        }
        com.jingdong.manto.m.t0.d.c.c cVar4 = dVar2.f13637f;
        cVar.a(cVar4);
        cVar.f13658h = dVar;
        if (!cVar.f13655e) {
            if (cVar.d) {
                cVar4.a.postDelayed(new c(this, cVar), cVar.f13661k);
                return;
            } else {
                cVar.a();
                return;
            }
        }
        Queue<com.jingdong.manto.m.t0.d.d.c> queue = cVar4.b;
        if (queue == null) {
            return;
        }
        queue.add(cVar);
        cVar4.a();
    }

    public void b() {
        BluetoothAdapter bTAdapter = BTHelper.getBTAdapter();
        if (bTAdapter != null) {
            bTAdapter.enable();
        }
    }

    public final synchronized void c() {
        Context context;
        IPermission iPermission;
        Map<String, com.jingdong.manto.m.t0.d.c.d> map;
        com.jingdong.manto.m.t0.d.b bVar = this.b;
        if (bVar != null) {
            e eVar = bVar.b;
            if (eVar != null && (map = eVar.b) != null) {
                Iterator<com.jingdong.manto.m.t0.d.c.d> it = map.values().iterator();
                while (it.hasNext()) {
                    it.next().d();
                }
                eVar.b.clear();
                eVar.b = null;
            }
            f fVar = bVar.a;
            if (fVar != null) {
                fVar.a();
                boolean z = false;
                fVar.a.set(false);
                Map<String, h> map2 = fVar.f13716j;
                if (map2 != null) {
                    map2.clear();
                }
                List<h> list = fVar.f13713g;
                if (list != null) {
                    list.clear();
                }
                if (Build.VERSION.SDK_INT < 31 || ((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) != null && iPermission.hasPermission("android.permission.BLUETOOTH_SCAN"))) {
                    z = true;
                }
                if (z && BTHelper.getBTAdapter() != null && BTHelper.getBTAdapter().isDiscovering()) {
                    BTHelper.getBTAdapter().cancelDiscovery();
                }
                BroadcastReceiver broadcastReceiver = fVar.d;
                if (broadcastReceiver != null && (context = fVar.f13710c) != null) {
                    context.unregisterReceiver(broadcastReceiver);
                    fVar.d = null;
                }
                fVar.f13711e = null;
                fVar.f13710c = null;
            }
            this.b = null;
        }
    }

    @Override // com.jingdong.manto.AppLifeCycle.Listener
    public void onAppDestroy() {
        super.onAppDestroy();
        AppLifeCycle.remove(this.a, this);
        com.jingdong.manto.m.t0.a.a(this.a, false);
    }
}
