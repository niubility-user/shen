package com.jingdong.manto.m.t0.d.e;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import android.os.ParcelUuid;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public class g {
    static final a a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public interface a {
        boolean a(BluetoothAdapter bluetoothAdapter, com.jingdong.manto.m.t0.d.e.e eVar);

        boolean a(BluetoothAdapter bluetoothAdapter, List<com.jingdong.manto.m.t0.d.e.d> list, com.jingdong.manto.m.t0.d.e.c cVar, com.jingdong.manto.m.t0.d.e.e eVar);
    }

    @TargetApi(18)
    /* loaded from: classes15.dex */
    static class b implements BluetoothAdapter.LeScanCallback {
        private final WeakReference<com.jingdong.manto.m.t0.d.e.e> a;
        private final List<com.jingdong.manto.m.t0.d.e.d> b;

        b(List<com.jingdong.manto.m.t0.d.e.d> list, com.jingdong.manto.m.t0.d.e.e eVar) {
            this.b = list;
            this.a = new WeakReference<>(eVar);
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public final void onLeScan(BluetoothDevice bluetoothDevice, int i2, byte[] bArr) {
            boolean z;
            com.jingdong.manto.m.t0.d.e.e eVar = this.a.get();
            if (eVar == null) {
                return;
            }
            com.jingdong.manto.m.t0.d.e.b bVar = new com.jingdong.manto.m.t0.d.e.b(bluetoothDevice, com.jingdong.manto.m.t0.d.e.a.a(bArr), i2, System.currentTimeMillis());
            List<com.jingdong.manto.m.t0.d.e.d> list = this.b;
            if (list == null) {
                eVar.a(1, bVar);
                return;
            }
            for (com.jingdong.manto.m.t0.d.e.d dVar : list) {
                BluetoothDevice a = bVar.a();
                String str = dVar.a;
                if (str == null || (a != null && str.equals(a.getAddress()))) {
                    com.jingdong.manto.m.t0.d.e.a aVar = bVar.b;
                    if (aVar != null || (dVar.f13702j == null && dVar.b == null && dVar.f13700h == null && dVar.f13697e == null && dVar.d == null && dVar.f13699g < 0)) {
                        String str2 = dVar.f13702j;
                        if (str2 == null || str2.equals(aVar.f13694g)) {
                            ParcelUuid parcelUuid = dVar.b;
                            if (parcelUuid != null) {
                                ParcelUuid parcelUuid2 = dVar.f13696c;
                                List<ParcelUuid> list2 = aVar.b;
                                if (parcelUuid == null) {
                                    z = true;
                                } else {
                                    if (list2 != null) {
                                        Iterator<ParcelUuid> it = list2.iterator();
                                        while (it.hasNext()) {
                                            if (com.jingdong.manto.m.t0.d.e.d.a(parcelUuid.getUuid(), parcelUuid2 == null ? null : parcelUuid2.getUuid(), it.next().getUuid())) {
                                                break;
                                            }
                                        }
                                    }
                                    z = false;
                                }
                                if (!z) {
                                    continue;
                                }
                            }
                            ParcelUuid parcelUuid3 = dVar.d;
                            if (parcelUuid3 != null) {
                                if (!com.jingdong.manto.m.t0.d.e.d.a(dVar.f13697e, dVar.f13698f, parcelUuid3 != null ? aVar.d.get(parcelUuid3) : null)) {
                                    continue;
                                }
                            }
                            int i3 = dVar.f13699g;
                            if (i3 < 0 || aVar == null || com.jingdong.manto.m.t0.d.e.d.a(dVar.f13700h, dVar.f13701i, aVar.f13691c.get(i3))) {
                                eVar.a(1, bVar);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @TargetApi(21)
    /* loaded from: classes15.dex */
    static class c extends ScanCallback {
        private final WeakReference<com.jingdong.manto.m.t0.d.e.e> a;

        c(com.jingdong.manto.m.t0.d.e.e eVar) {
            this.a = new WeakReference<>(eVar);
        }

        @Override // android.bluetooth.le.ScanCallback
        public final void onScanFailed(int i2) {
            com.jingdong.manto.m.t0.d.e.e eVar = this.a.get();
            if (eVar != null) {
                eVar.a(i2);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public final void onScanResult(int i2, ScanResult scanResult) {
            com.jingdong.manto.m.t0.d.e.e eVar = this.a.get();
            if (eVar != null) {
                eVar.a(i2, new com.jingdong.manto.m.t0.d.e.b(scanResult));
            }
        }
    }

    @TargetApi(18)
    /* loaded from: classes15.dex */
    static class d implements a {
        static final Map<com.jingdong.manto.m.t0.d.e.e, b> a = new HashMap();

        d() {
        }

        @Override // com.jingdong.manto.m.t0.d.e.g.a
        public final boolean a(BluetoothAdapter bluetoothAdapter, com.jingdong.manto.m.t0.d.e.e eVar) {
            IPermission iPermission;
            b remove = a.remove(eVar);
            boolean z = false;
            if (remove == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT < 31 || ((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) != null && iPermission.hasPermission("android.permission.BLUETOOTH_SCAN"))) {
                z = true;
            }
            if (z) {
                bluetoothAdapter.stopLeScan(remove);
            }
            return true;
        }

        @Override // com.jingdong.manto.m.t0.d.e.g.a
        public final boolean a(BluetoothAdapter bluetoothAdapter, List<com.jingdong.manto.m.t0.d.e.d> list, com.jingdong.manto.m.t0.d.e.c cVar, com.jingdong.manto.m.t0.d.e.e eVar) {
            IPermission iPermission;
            Map<com.jingdong.manto.m.t0.d.e.e, b> map = a;
            b bVar = map.get(eVar);
            if (bVar == null) {
                bVar = new b(list, eVar);
                map.put(eVar, bVar);
            }
            if (Build.VERSION.SDK_INT < 31 || ((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) != null && iPermission.hasPermission("android.permission.BLUETOOTH_SCAN"))) {
                return bluetoothAdapter.startLeScan(bVar);
            }
            return false;
        }
    }

    @SuppressLint({"MissingPermission"})
    @TargetApi(21)
    /* loaded from: classes15.dex */
    static class e implements a {
        static final Map<com.jingdong.manto.m.t0.d.e.e, c> a = new HashMap();

        e() {
        }

        @Override // com.jingdong.manto.m.t0.d.e.g.a
        public final boolean a(BluetoothAdapter bluetoothAdapter, com.jingdong.manto.m.t0.d.e.e eVar) {
            c remove = a.remove(eVar);
            if (remove == null) {
                return false;
            }
            if (bluetoothAdapter.getBluetoothLeScanner() == null) {
                MantoLog.e("BT.ScannerCompat", "bluetooth scanner is null, return");
                return false;
            }
            bluetoothAdapter.getBluetoothLeScanner().stopScan(remove);
            return true;
        }

        @Override // com.jingdong.manto.m.t0.d.e.g.a
        public final boolean a(BluetoothAdapter bluetoothAdapter, List<com.jingdong.manto.m.t0.d.e.d> list, com.jingdong.manto.m.t0.d.e.c cVar, com.jingdong.manto.m.t0.d.e.e eVar) {
            ArrayList arrayList;
            if (list != null) {
                arrayList = new ArrayList(list.size());
                for (com.jingdong.manto.m.t0.d.e.d dVar : list) {
                    ScanFilter.Builder builder = new ScanFilter.Builder();
                    String str = dVar.f13702j;
                    if (str != null) {
                        builder.setDeviceName(str);
                    }
                    ParcelUuid parcelUuid = dVar.b;
                    if (parcelUuid != null) {
                        builder.setServiceUuid(parcelUuid, dVar.f13696c);
                    }
                    String str2 = dVar.a;
                    if (str2 != null) {
                        builder.setDeviceAddress(str2);
                    }
                    ParcelUuid parcelUuid2 = dVar.d;
                    if (parcelUuid2 != null) {
                        builder.setServiceData(parcelUuid2, dVar.f13697e, dVar.f13698f);
                    }
                    int i2 = dVar.f13699g;
                    if (i2 < 0) {
                        builder.setManufacturerData(i2, dVar.f13700h, dVar.f13701i);
                    }
                    arrayList.add(builder.build());
                }
            } else {
                arrayList = null;
            }
            if (cVar != null) {
                ScanSettings build = new ScanSettings.Builder().setReportDelay(cVar.b).setScanMode(cVar.a).build();
                if (bluetoothAdapter.getBluetoothLeScanner() == null) {
                    MantoLog.e("BT.ScannerCompat", "bluetooth scanner is null, return");
                    return false;
                }
                BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
                Map<com.jingdong.manto.m.t0.d.e.e, c> map = a;
                c cVar2 = map.get(eVar);
                if (cVar2 == null) {
                    cVar2 = new c(eVar);
                    map.put(eVar, cVar2);
                }
                bluetoothLeScanner.startScan(arrayList, build, cVar2);
                return true;
            }
            throw new IllegalStateException("Scan settings are null");
        }
    }

    static {
        a dVar;
        if (Build.VERSION.SDK_INT >= 21) {
            MantoLog.i("BT.ScannerCompat", "use 21");
            dVar = new e();
        } else {
            MantoLog.i("BT.ScannerCompat", "use 18");
            dVar = new d();
        }
        a = dVar;
    }

    public static boolean a(BluetoothAdapter bluetoothAdapter, com.jingdong.manto.m.t0.d.e.e eVar) {
        return a.a(bluetoothAdapter, eVar);
    }

    public static boolean a(BluetoothAdapter bluetoothAdapter, List<com.jingdong.manto.m.t0.d.e.d> list, com.jingdong.manto.m.t0.d.e.c cVar, com.jingdong.manto.m.t0.d.e.e eVar) {
        MantoLog.i("BT.ScannerCompat", "scanMode: " + cVar.a);
        return a.a(bluetoothAdapter, list, cVar, eVar);
    }
}
