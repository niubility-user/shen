package com.jingdong.manto.m.t0.d.c;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import androidx.core.content.ContextCompat;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.m.t0.d.d.g;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public class d implements a {
    public String a;
    public BluetoothGatt b;

    /* renamed from: c */
    public Context f13635c;
    public com.jingdong.manto.m.t0.d.d.a d;

    /* renamed from: e */
    public g f13636e;

    /* renamed from: f */
    public c f13637f;

    /* renamed from: g */
    private List<b> f13638g;

    /* renamed from: h */
    public Map<String, List<com.jingdong.manto.m.t0.d.d.b>> f13639h;

    /* renamed from: i */
    private e f13640i;

    public d(Context context, String str, e eVar) {
        String str2 = "BT.ConnectWorker#" + hashCode();
        this.f13635c = context;
        this.a = str;
        this.f13640i = eVar;
    }

    private void a(String str, boolean z) {
        com.jingdong.manto.m.t0.d.d.a aVar = this.d;
        if (aVar != null) {
            aVar.a(str, z);
        }
    }

    @TargetApi(18)
    private synchronized void b(boolean z) {
        if (this.b != null) {
            if (z) {
                a(this.a, false);
            }
            if (com.jingdong.manto.m.t0.d.a.f13617j.f13626i) {
                BTHelper.refreshGatt(this.b);
            }
            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(com.jingdong.a.g(), "android.permission.BLUETOOTH_CONNECT") == 0) {
                this.b.close();
            }
            this.b = null;
        }
    }

    public final Context a() {
        return this.f13635c;
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    @TargetApi(18)
    public void a(BluetoothGatt bluetoothGatt, int i2) {
        this.f13637f.a(bluetoothGatt, i2);
        if (i2 != 0 || bluetoothGatt == null || bluetoothGatt.getDevice() == null) {
            return;
        }
        a(bluetoothGatt.getDevice().getAddress(), true);
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    @TargetApi(18)
    public void a(BluetoothGatt bluetoothGatt, int i2, int i3) {
        if (bluetoothGatt != null) {
            bluetoothGatt.getDevice().getAddress();
        }
        this.f13637f.a(bluetoothGatt, i2, i3);
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    @TargetApi(18)
    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        String address = bluetoothGatt != null ? bluetoothGatt.getDevice().getAddress() : "";
        String upperCase = bluetoothGattCharacteristic != null ? bluetoothGattCharacteristic.getUuid().toString().toUpperCase() : "";
        this.f13637f.a(bluetoothGatt, bluetoothGattCharacteristic);
        if (this.f13636e != null) {
            this.f13636e.b(address, bluetoothGattCharacteristic.getService().getUuid().toString().toUpperCase(), upperCase, new String(Base64.encode(bluetoothGattCharacteristic.getValue(), 2)));
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    @TargetApi(18)
    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        if (bluetoothGatt != null) {
            bluetoothGatt.getDevice().getAddress();
        }
        if (bluetoothGattCharacteristic != null) {
            bluetoothGattCharacteristic.getUuid().toString().toUpperCase();
        }
        if (bluetoothGattCharacteristic != null) {
            new String(bluetoothGattCharacteristic.getValue());
        }
        this.f13637f.a(bluetoothGatt, bluetoothGattCharacteristic, i2);
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    @TargetApi(18)
    public void a(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
        if (bluetoothGatt != null) {
            bluetoothGatt.getDevice().getAddress();
        }
        if (bluetoothGattDescriptor != null) {
            bluetoothGattDescriptor.getUuid().toString().toUpperCase();
        }
        this.f13637f.a(bluetoothGatt, bluetoothGattDescriptor, i2);
    }

    public final synchronized void a(boolean z) {
        b(z);
        c cVar = this.f13637f;
        if (cVar != null) {
            cVar.b();
            this.f13637f = null;
        }
        List<b> list = this.f13638g;
        if (list != null) {
            list.clear();
            this.f13638g = null;
        }
        Map<String, List<com.jingdong.manto.m.t0.d.d.b>> map = this.f13639h;
        if (map != null) {
            map.clear();
            this.f13639h = null;
        }
        Map<String, d> map2 = this.f13640i.b;
        if (map2 != null) {
            map2.remove(this.a);
        }
    }

    public final BluetoothGatt b() {
        return this.b;
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    @TargetApi(18)
    public void b(BluetoothGatt bluetoothGatt, int i2) {
        if (bluetoothGatt != null) {
            bluetoothGatt.getDevice().getAddress();
        }
        this.f13637f.b(bluetoothGatt, i2);
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    @TargetApi(18)
    public void b(BluetoothGatt bluetoothGatt, int i2, int i3) {
        if (bluetoothGatt != null) {
            bluetoothGatt.getDevice().getAddress();
        }
        this.f13637f.b(bluetoothGatt, i2, i3);
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    @TargetApi(18)
    public void b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        try {
            this.f13637f.b(bluetoothGatt, bluetoothGattCharacteristic, i2);
            if (i2 == 0) {
                String str = new String(Base64.encode(bluetoothGattCharacteristic.getValue(), 2));
                this.f13636e.a(bluetoothGatt.getDevice().getAddress(), bluetoothGattCharacteristic.getService().getUuid().toString().toUpperCase(), bluetoothGattCharacteristic.getUuid().toString().toUpperCase(), str);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    @TargetApi(18)
    public void b(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
        if (bluetoothGatt != null) {
            bluetoothGatt.getDevice().getAddress();
        }
        if (bluetoothGattDescriptor != null) {
            bluetoothGattDescriptor.getUuid().toString().toUpperCase();
        }
        this.f13637f.b(bluetoothGatt, bluetoothGattDescriptor, i2);
    }

    @TargetApi(18)
    public final List<b> c() {
        if (this.b == null || TextUtils.isEmpty(this.a)) {
            return null;
        }
        if (this.f13638g == null) {
            List<BluetoothGattService> services = this.b.getServices();
            if (services == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (BluetoothGattService bluetoothGattService : services) {
                b bVar = new b();
                bVar.b = bluetoothGattService.getUuid().toString().toUpperCase();
                bVar.a = bluetoothGattService.getType() == 0;
                if (bVar.b.equals("00001800-0000-1000-8000-00805F9B34FB") || bVar.b.equals("00001801-0000-1000-8000-00805F9B34FB")) {
                    arrayList2.add(bVar);
                } else {
                    arrayList.add(bVar);
                }
            }
            if (arrayList2.size() > 0) {
                arrayList.addAll(arrayList2);
            }
            this.f13638g = arrayList;
        }
        return this.f13638g;
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    @TargetApi(18)
    public void c(BluetoothGatt bluetoothGatt, int i2, int i3) {
        this.f13637f.c(bluetoothGatt, i2, i3);
        if (i2 != 0 || i3 == 0) {
            a(true);
        }
    }

    public final synchronized void d() {
        b(true);
        c cVar = this.f13637f;
        if (cVar != null) {
            cVar.b();
            this.f13637f = null;
        }
        List<b> list = this.f13638g;
        if (list != null) {
            list.clear();
            this.f13638g = null;
        }
        Map<String, List<com.jingdong.manto.m.t0.d.d.b>> map = this.f13639h;
        if (map != null) {
            map.clear();
            this.f13639h = null;
        }
    }
}
