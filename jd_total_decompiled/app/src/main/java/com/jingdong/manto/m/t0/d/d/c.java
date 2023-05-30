package com.jingdong.manto.m.t0.d.d;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Handler;
import android.os.Looper;

/* loaded from: classes15.dex */
public abstract class c implements com.jingdong.manto.m.t0.d.c.a {
    public boolean a = com.jingdong.manto.m.t0.d.a.f13617j.a;
    protected final Handler b = new Handler(Looper.getMainLooper());

    /* renamed from: c  reason: collision with root package name */
    public boolean f13654c = false;
    public boolean d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f13655e;

    /* renamed from: f  reason: collision with root package name */
    public com.jingdong.manto.m.t0.d.c.d f13656f;

    /* renamed from: g  reason: collision with root package name */
    protected com.jingdong.manto.m.t0.d.c.c f13657g;

    /* renamed from: h  reason: collision with root package name */
    public d f13658h;

    /* renamed from: i  reason: collision with root package name */
    private Runnable f13659i;

    /* renamed from: j  reason: collision with root package name */
    public long f13660j;

    /* renamed from: k  reason: collision with root package name */
    public long f13661k;

    /* renamed from: l  reason: collision with root package name */
    public e f13662l;

    /* renamed from: m  reason: collision with root package name */
    protected int f13663m;

    /* loaded from: classes15.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            c cVar = c.this;
            if (cVar.f13654c) {
                return;
            }
            cVar.b(e.u);
            c.this.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        final /* synthetic */ e a;

        b(e eVar) {
            this.a = eVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            c.this.f13658h.a(this.a);
        }
    }

    public c() {
        com.jingdong.manto.m.t0.d.a aVar = com.jingdong.manto.m.t0.d.a.f13617j;
        this.d = aVar.f13622e;
        this.f13655e = aVar.f13623f;
        this.f13659i = new a();
        this.f13660j = aVar.d;
        this.f13661k = aVar.f13625h;
        this.f13663m = hashCode();
    }

    public final void a() {
        this.b.postDelayed(this.f13659i, this.f13660j);
        b();
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public void a(BluetoothGatt bluetoothGatt, int i2) {
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public void a(BluetoothGatt bluetoothGatt, int i2, int i3) {
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public void a(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
    }

    public final void a(com.jingdong.manto.m.t0.d.c.c cVar) {
        this.f13657g = cVar;
    }

    public void a(e eVar) {
    }

    public abstract void b();

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void b(BluetoothGatt bluetoothGatt, int i2) {
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public void b(BluetoothGatt bluetoothGatt, int i2, int i3) {
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public void b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void b(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
    }

    public final void b(e eVar) {
        this.f13662l = eVar;
        if (this.d) {
            this.b.post(new b(eVar));
        } else {
            this.f13658h.a(eVar);
        }
    }

    public final void c() {
        this.b.removeCallbacks(this.f13659i);
        this.f13654c = true;
        a(this.f13662l);
        if (this.f13655e) {
            this.f13657g.a();
        } else {
            this.f13657g.d.remove(this);
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public void c(BluetoothGatt bluetoothGatt, int i2, int i3) {
    }

    public abstract String d();

    public String toString() {
        return "Action#" + this.f13663m + "{action='" + d() + "', debug=" + this.a + ", mainThread=" + this.d + ", serial=" + this.f13655e + '}';
    }
}
