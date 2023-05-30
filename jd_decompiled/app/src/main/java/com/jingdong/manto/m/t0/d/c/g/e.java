package com.jingdong.manto.m.t0.d.c.g;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import java.util.UUID;

/* loaded from: classes15.dex */
public class e extends com.jingdong.manto.m.t0.d.d.c {

    /* renamed from: n  reason: collision with root package name */
    private final boolean f13644n;
    private final String o;
    private final String p;

    public e(String str, String str2, boolean z) {
        this.p = str;
        this.o = str2;
        this.f13644n = z;
    }

    @Override // com.jingdong.manto.m.t0.d.d.c, com.jingdong.manto.m.t0.d.c.a
    public final void a(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
        c();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    @TargetApi(18)
    public final void b() {
        com.jingdong.manto.m.t0.d.d.e eVar;
        BluetoothGattService service;
        BluetoothGattCharacteristic characteristic;
        BluetoothGatt bluetoothGatt = this.f13656f.b;
        if (bluetoothGatt == null) {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13670k;
        } else if (!BTHelper.isServiceValid(this.p) || (service = bluetoothGatt.getService(UUID.fromString(this.p))) == null) {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13668i;
        } else if (!BTHelper.isServiceValid(this.o) || (characteristic = service.getCharacteristic(UUID.fromString(this.o))) == null) {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13669j;
        } else if (!BTHelper.supportNotify(characteristic.getProperties())) {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13671l;
        } else if (bluetoothGatt.setCharacteristicNotification(characteristic, this.f13644n)) {
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(BTHelper.notifyUuid);
            if (descriptor == null) {
                b(com.jingdong.manto.m.t0.d.d.e.o);
                c();
                return;
            }
            if (!descriptor.setValue(this.f13644n ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE)) {
                eVar = com.jingdong.manto.m.t0.d.d.e.p;
            } else if (bluetoothGatt.writeDescriptor(descriptor)) {
                b(com.jingdong.manto.m.t0.d.d.e.d);
                return;
            } else {
                eVar = com.jingdong.manto.m.t0.d.d.e.q;
            }
        } else {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13672m;
        }
        b(eVar);
        c();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public final String d() {
        return "NotifyCharacteristicAction";
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public final String toString() {
        return "NotifyCharacteristicAction#" + this.f13663m + "{serviceId='" + this.p + "', characteristicId='" + this.o + "', enable=" + this.f13644n + ", debug=" + this.a + ", mainThread=" + this.d + ", serial=" + this.f13655e + '}';
    }
}
