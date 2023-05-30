package com.jingdong.manto.m.t0.d.c.g;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes15.dex */
public class h extends com.jingdong.manto.m.t0.d.d.c {

    /* renamed from: n  reason: collision with root package name */
    private final String f13648n;
    final AtomicBoolean o = new AtomicBoolean(false);
    private final Integer p;

    /* loaded from: classes15.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.o.set(true);
            h.this.b(com.jingdong.manto.m.t0.d.d.e.u);
        }
    }

    public h(Integer num, String str) {
        this.p = num;
        this.f13648n = str;
    }

    @Override // com.jingdong.manto.m.t0.d.d.c, com.jingdong.manto.m.t0.d.c.a
    public final void a(BluetoothGatt bluetoothGatt, int i2, int i3) {
        super.a(bluetoothGatt, i2, i3);
        if (this.o.get()) {
            this.o.set(false);
            return;
        }
        Integer num = this.p;
        if (num != null && i2 == num.intValue() && i3 == 0) {
            b(com.jingdong.manto.m.t0.d.d.e.d);
            c();
            return;
        }
        b(com.jingdong.manto.m.t0.d.d.e.f13664e);
        c();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    @TargetApi(21)
    public final void b() {
        com.jingdong.manto.m.t0.d.d.e eVar;
        if (BTHelper.getBTAdapter() == null) {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13670k;
        } else {
            String str = this.f13648n;
            if (str == null || !BluetoothAdapter.checkBluetoothAddress(str) || this.p == null) {
                eVar = com.jingdong.manto.m.t0.d.d.e.v;
            } else if (BTHelper.btEnabled()) {
                com.jingdong.manto.m.t0.d.c.d dVar = this.f13656f;
                if (dVar.b() == null) {
                    b(com.jingdong.manto.m.t0.d.d.e.f13670k);
                    c();
                    return;
                }
                BluetoothGatt b = dVar.b();
                if (this.p.intValue() > 512) {
                    eVar = new com.jingdong.manto.m.t0.d.d.e(10013, "fail:invalid data, DEFAULT_MAX_MTU = 512");
                } else if (this.p.intValue() < 23) {
                    eVar = new com.jingdong.manto.m.t0.d.d.e(10013, "fail:invalid data, DEFAULT_MIN_MTU = 23");
                } else if (b.requestMtu(this.p.intValue())) {
                    MantoThreadUtils.post(new a(), 500);
                    return;
                } else {
                    eVar = com.jingdong.manto.m.t0.d.d.e.f13672m;
                }
            } else {
                eVar = com.jingdong.manto.m.t0.d.d.e.f13666g;
            }
        }
        b(eVar);
        c();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public final String d() {
        return "SetMtuAction";
    }
}
