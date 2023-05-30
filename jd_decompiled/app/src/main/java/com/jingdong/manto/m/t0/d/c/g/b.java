package com.jingdong.manto.m.t0.d.c.g;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.os.Build;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;

/* loaded from: classes15.dex */
public class b extends com.jingdong.manto.m.t0.d.d.c {

    /* renamed from: n  reason: collision with root package name */
    public String f13642n;
    public boolean o;
    public String p = "LE";
    public long q = 0;

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ int a;

        a(int i2) {
            this.a = i2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            b.this.f13656f.a(false);
            b.this.b(new com.jingdong.manto.m.t0.d.d.e(10003, "fail:connection fail status:" + this.a));
            b.this.c();
        }
    }

    /* renamed from: com.jingdong.manto.m.t0.d.c.g.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class RunnableC0623b implements Runnable {
        final /* synthetic */ BluetoothGatt a;

        RunnableC0623b(b bVar, BluetoothGatt bluetoothGatt) {
            this.a = bluetoothGatt;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.a.discoverServices();
        }
    }

    public b(String str) {
        this.f13642n = str;
    }

    @Override // com.jingdong.manto.m.t0.d.d.c, com.jingdong.manto.m.t0.d.c.a
    public final void a(BluetoothGatt bluetoothGatt, int i2) {
        this.f13656f.b = bluetoothGatt;
        b(i2 == 0 ? com.jingdong.manto.m.t0.d.d.e.d : com.jingdong.manto.m.t0.d.d.e.f13673n);
        c();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public final void a(com.jingdong.manto.m.t0.d.d.e eVar) {
        if (eVar.a != 10012) {
            return;
        }
        this.f13656f.a(false);
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    @TargetApi(18)
    public final void b() {
        com.jingdong.manto.m.t0.d.d.e eVar;
        BluetoothGatt connectGatt;
        Context context;
        boolean z;
        com.jingdong.manto.m.t0.d.c.f fVar;
        Context context2;
        boolean z2;
        com.jingdong.manto.m.t0.d.c.f fVar2;
        int i2;
        BluetoothAdapter bTAdapter = BTHelper.getBTAdapter();
        if (bTAdapter == null) {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13670k;
        } else {
            String str = this.f13642n;
            if (str == null || !BluetoothAdapter.checkBluetoothAddress(str)) {
                eVar = com.jingdong.manto.m.t0.d.d.e.v;
            } else if (!BTHelper.btEnabled()) {
                eVar = com.jingdong.manto.m.t0.d.d.e.f13666g;
            } else if (this.f13656f.b != null) {
                eVar = com.jingdong.manto.m.t0.d.d.e.s;
            } else {
                BluetoothDevice remoteDevice = bTAdapter.getRemoteDevice(this.f13642n);
                if (remoteDevice != null) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        String upperCase = this.p.toUpperCase();
                        if (upperCase.equals("LE")) {
                            context = this.f13656f.f13635c;
                            z = this.o;
                            fVar = new com.jingdong.manto.m.t0.d.c.f(this.f13656f);
                        } else {
                            if (upperCase.equals("AUTO")) {
                                context2 = this.f13656f.f13635c;
                                z2 = this.o;
                                fVar2 = new com.jingdong.manto.m.t0.d.c.f(this.f13656f);
                                i2 = 0;
                            } else if (upperCase.equals("BREDR")) {
                                context2 = this.f13656f.f13635c;
                                z2 = this.o;
                                fVar2 = new com.jingdong.manto.m.t0.d.c.f(this.f13656f);
                                i2 = 1;
                            } else {
                                context = this.f13656f.f13635c;
                                z = this.o;
                                fVar = new com.jingdong.manto.m.t0.d.c.f(this.f13656f);
                            }
                            connectGatt = remoteDevice.connectGatt(context2, z2, fVar2, i2);
                        }
                        connectGatt = remoteDevice.connectGatt(context, z, fVar, 2);
                    } else {
                        connectGatt = remoteDevice.connectGatt(this.f13656f.f13635c, this.o, new com.jingdong.manto.m.t0.d.c.f(this.f13656f));
                    }
                    if (connectGatt != null) {
                        this.f13656f.b = connectGatt;
                        return;
                    }
                }
                eVar = com.jingdong.manto.m.t0.d.d.e.f13667h;
            }
        }
        b(eVar);
        c();
    }

    @Override // com.jingdong.manto.m.t0.d.d.c, com.jingdong.manto.m.t0.d.c.a
    @TargetApi(18)
    public final void c(BluetoothGatt bluetoothGatt, int i2, int i3) {
        this.f13656f.b = bluetoothGatt;
        if (i3 != 2) {
            if (i3 == 0) {
                this.b.post(new a(i2));
            }
        } else if (bluetoothGatt == null) {
        } else {
            this.b.postDelayed(new RunnableC0623b(this, bluetoothGatt), this.q);
        }
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public final String d() {
        return "ConnectAction";
    }

    @Override // com.jingdong.manto.m.t0.d.d.c
    public final String toString() {
        return "ConnectAction#" + this.f13663m + "{deviceId='" + this.f13642n + "', debug=" + this.a + ", mainThread=" + this.d + ", serial=" + this.f13655e + '}';
    }
}
