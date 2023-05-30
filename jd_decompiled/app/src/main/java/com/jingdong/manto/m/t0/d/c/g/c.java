package com.jingdong.manto.m.t0.d.c.g;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;

/* loaded from: classes15.dex */
public class c extends com.jingdong.manto.m.t0.d.d.c {
    public c() {
        String str = "BT.GetBLEDeviceRSSIAction#" + hashCode();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    @TargetApi(18)
    public final void b() {
        com.jingdong.manto.m.t0.d.d.e eVar;
        if (BTHelper.btEnabled()) {
            BluetoothGatt b = this.f13656f.b();
            if (b != null) {
                if (b.readRemoteRssi()) {
                    return;
                }
                b(com.jingdong.manto.m.t0.d.d.e.f13672m);
                c();
                return;
            }
            eVar = com.jingdong.manto.m.t0.d.d.e.f13670k;
        } else {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13666g;
        }
        b(eVar);
        c();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c, com.jingdong.manto.m.t0.d.c.a
    public final void b(BluetoothGatt bluetoothGatt, int i2, int i3) {
        super.b(bluetoothGatt, i2, i3);
        b((i3 == 0 && BTHelper.equals(this.f13656f.b(), bluetoothGatt)) ? com.jingdong.manto.m.t0.d.d.e.a(Integer.valueOf(i2)) : com.jingdong.manto.m.t0.d.d.e.f13664e);
        c();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public final String d() {
        return "GetBLEDeviceRSSIAction";
    }
}
