package com.jingdong.manto.m.t0.d.c.g;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import java.util.UUID;

/* loaded from: classes15.dex */
public class i extends com.jingdong.manto.m.t0.d.d.c {

    /* renamed from: n  reason: collision with root package name */
    private final String f13649n;
    private final String o;
    private final String p;
    private final String q;

    public i(String str, String str2, String str3, String str4) {
        this.o = str;
        this.f13649n = str2;
        this.p = str3;
        this.q = str4;
    }

    @Override // com.jingdong.manto.m.t0.d.d.c, com.jingdong.manto.m.t0.d.c.a
    public final void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        if (i2 == 0) {
            b(com.jingdong.manto.m.t0.d.d.e.d);
        }
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
        } else if (!BTHelper.isServiceValid(this.o) || (service = bluetoothGatt.getService(UUID.fromString(this.o))) == null) {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13668i;
        } else if (!BTHelper.isServiceValid(this.f13649n) || (characteristic = service.getCharacteristic(UUID.fromString(this.f13649n))) == null) {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13669j;
        } else if (BTHelper.supportWrite(characteristic.getProperties()) || BTHelper.supportWriteWithoutResponse(characteristic.getProperties())) {
            boolean z = false;
            int i2 = 1;
            if (TextUtils.equals("writeNoResponse", this.q)) {
                z = true;
            } else if (TextUtils.equals("write", this.q)) {
                z = true;
                i2 = 2;
            } else {
                i2 = 0;
            }
            characteristic.setValue(BTHelper.safeByteArray(Base64.decode(this.p, 2)));
            if (z) {
                characteristic.setWriteType(i2);
            }
            if (!bluetoothGatt.writeCharacteristic(characteristic)) {
                b(com.jingdong.manto.m.t0.d.d.e.f13672m);
                c();
                return;
            } else if (BTHelper.supportWrite(characteristic.getProperties()) || !BTHelper.supportWriteWithoutResponse(characteristic.getProperties())) {
                return;
            } else {
                eVar = com.jingdong.manto.m.t0.d.d.e.d;
            }
        } else {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13671l;
        }
        b(eVar);
        c();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public final String d() {
        return "WriteCharacteristicAction";
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public final String toString() {
        return "WriteCharacteristicAction#" + this.f13663m + "{serviceId='" + this.o + "', characteristicId='" + this.f13649n + "', value='" + this.p + "', debug=" + this.a + ", mainThread=" + this.d + ", serial=" + this.f13655e + ", writeType=" + this.q + '}';
    }
}
