package com.jingdong.manto.m.t0.d.c.g;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import java.util.UUID;

/* loaded from: classes15.dex */
public class g extends com.jingdong.manto.m.t0.d.d.c {

    /* renamed from: n  reason: collision with root package name */
    private String f13647n;
    private String o;

    public g(String str, String str2) {
        this.o = str;
        this.f13647n = str2;
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
        } else if (!BTHelper.isServiceValid(this.o) || (service = bluetoothGatt.getService(UUID.fromString(this.o))) == null) {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13668i;
        } else if (!BTHelper.isServiceValid(this.f13647n) || (characteristic = service.getCharacteristic(UUID.fromString(this.f13647n))) == null) {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13669j;
        } else if (!BTHelper.supportRead(characteristic.getProperties())) {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13671l;
        } else if (bluetoothGatt.readCharacteristic(characteristic)) {
            b(com.jingdong.manto.m.t0.d.d.e.d);
            return;
        } else {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13672m;
        }
        b(eVar);
        c();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c, com.jingdong.manto.m.t0.d.c.a
    public final void b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        c();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public final String d() {
        return "ReadCharacteristicAction";
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public final String toString() {
        return "ReadCharacteristicAction#" + this.f13663m + "{serviceId='" + this.o + "', characteristicId='" + this.f13647n + "', debug=" + this.a + ", mainThread=" + this.d + ", serial=" + this.f13655e + '}';
    }
}
