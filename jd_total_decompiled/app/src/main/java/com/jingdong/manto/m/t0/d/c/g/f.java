package com.jingdong.manto.m.t0.d.c.g;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes15.dex */
public class f extends com.jingdong.manto.m.t0.d.d.c {

    /* renamed from: n  reason: collision with root package name */
    private final String f13645n;
    final String o = "BT.PairAction#" + hashCode();
    private a p;
    final byte[] q;

    @TargetApi(19)
    /* loaded from: classes15.dex */
    final class a extends BroadcastReceiver {
        final Context a;
        private final BluetoothDevice b;

        /* renamed from: c  reason: collision with root package name */
        final f f13646c;

        public a(f fVar, f fVar2, Context context, BluetoothDevice bluetoothDevice) {
            this.f13646c = fVar2;
            this.a = context;
            this.b = bluetoothDevice;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (BTHelper.equals(this.b, intent != null ? intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE") : null)) {
                if (TextUtils.equals("android.bluetooth.device.action.PAIRING_REQUEST", intent.getAction())) {
                    if (intent.hasExtra("android.bluetooth.device.extra.PAIRING_VARIANT") && intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", -1) == 0) {
                        MantoLog.i(this.f13646c.o, "PAIRING_VARIANT_PIN");
                        f fVar = this.f13646c;
                        byte[] bArr = fVar.q;
                        if (bArr != null) {
                            this.b.setPin(bArr);
                            return;
                        }
                        fVar.b(com.jingdong.manto.m.t0.d.d.e.t);
                        this.f13646c.c();
                    }
                } else if (TextUtils.equals("android.bluetooth.device.action.BOND_STATE_CHANGED", intent.getAction()) && intent.hasExtra("android.bluetooth.device.extra.BOND_STATE")) {
                    int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
                    if (intExtra == 10) {
                        this.f13646c.b(com.jingdong.manto.m.t0.d.d.e.f13664e);
                        this.f13646c.c();
                    } else if (intExtra != 12) {
                    } else {
                        this.f13646c.b(com.jingdong.manto.m.t0.d.d.e.d);
                        this.f13646c.c();
                    }
                }
            }
        }
    }

    public f(String str, byte[] bArr, long j2) {
        this.f13645n = str;
        this.q = bArr;
        this.f13660j = j2;
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public void a(com.jingdong.manto.m.t0.d.d.e eVar) {
        super.a(eVar);
        a aVar = this.p;
        if (aVar != null) {
            aVar.a.unregisterReceiver(aVar);
        }
        this.p = null;
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    @TargetApi(19)
    public void b() {
        Context a2;
        com.jingdong.manto.m.t0.d.d.e eVar;
        if (!BTHelper.btEnabled()) {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13666g;
        } else if (BluetoothAdapter.checkBluetoothAddress(this.f13645n)) {
            BluetoothAdapter bTAdapter = BTHelper.getBTAdapter();
            if (bTAdapter == null) {
                return;
            }
            BluetoothDevice remoteDevice = bTAdapter.getRemoteDevice(this.f13645n);
            if (!remoteDevice.createBond() || (a2 = this.f13656f.a()) == null) {
                b(com.jingdong.manto.m.t0.d.d.e.f13672m);
                c();
                return;
            }
            this.p = new a(this, this, a2, remoteDevice);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
            intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
            a aVar = this.p;
            aVar.a.registerReceiver(aVar, intentFilter);
            return;
        } else {
            eVar = com.jingdong.manto.m.t0.d.d.e.v;
        }
        b(eVar);
        c();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public String d() {
        return "PairAction";
    }
}
