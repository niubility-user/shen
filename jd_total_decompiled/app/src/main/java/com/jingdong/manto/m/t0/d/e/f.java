package com.jingdong.manto.m.t0.d.e;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.m.t0.d.d.h;
import com.jingdong.manto.m.t0.d.d.i;
import com.jingdong.manto.m.t0.d.e.c;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes15.dex */
public class f {

    /* renamed from: c  reason: collision with root package name */
    public Context f13710c;
    public BroadcastReceiver d;

    /* renamed from: e  reason: collision with root package name */
    public e f13711e;

    /* renamed from: g  reason: collision with root package name */
    public List<h> f13713g;

    /* renamed from: i  reason: collision with root package name */
    public i f13715i;

    /* renamed from: j  reason: collision with root package name */
    public Map<String, h> f13716j;
    public AtomicBoolean a = new AtomicBoolean(false);
    public final String b = "BT.ScanWorker#" + hashCode();

    /* renamed from: f  reason: collision with root package name */
    public AtomicBoolean f13712f = new AtomicBoolean(false);

    /* renamed from: h  reason: collision with root package name */
    Runnable f13714h = new a();

    /* renamed from: k  reason: collision with root package name */
    final Handler f13717k = new Handler();

    /* loaded from: classes15.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (f.this.f13712f.get()) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(f.this.f13713g);
                f.this.f13713g.clear();
                if (f.this.f13715i != null && arrayList.size() > 0) {
                    f.this.f13715i.a(arrayList);
                }
                f fVar = f.this;
                fVar.f13717k.postDelayed(fVar.f13714h, com.jingdong.manto.m.t0.d.a.f13617j.b);
            }
        }
    }

    public f(Context context) {
        this.f13710c = context;
    }

    private static c a(String str) {
        c.b bVar;
        int i2;
        if (TextUtils.equals("medium", str)) {
            bVar = new c.b();
            i2 = 1;
        } else if (TextUtils.equals("high", str)) {
            bVar = new c.b();
            i2 = 2;
        } else {
            bVar = new c.b();
            i2 = 0;
        }
        return bVar.a(i2).a();
    }

    public final synchronized com.jingdong.manto.m.t0.d.d.e a() {
        com.jingdong.manto.m.t0.d.d.e eVar;
        if (this.a.get()) {
            if (this.f13712f.get()) {
                BluetoothAdapter bTAdapter = BTHelper.getBTAdapter();
                if (bTAdapter != null && BTHelper.btEnabled()) {
                    this.f13712f.set(false);
                    g.a(bTAdapter, this.f13711e);
                }
                eVar = com.jingdong.manto.m.t0.d.d.e.f13666g;
            }
            eVar = com.jingdong.manto.m.t0.d.d.e.d;
        } else {
            eVar = com.jingdong.manto.m.t0.d.d.e.f13665f;
        }
        return eVar;
    }

    public final synchronized void a(com.jingdong.manto.m.t0.d.d.d dVar, List<d> list, i iVar) {
        com.jingdong.manto.m.t0.d.d.e eVar;
        if (this.a.get()) {
            if (!this.f13712f.get()) {
                BluetoothAdapter bTAdapter = BTHelper.getBTAdapter();
                if (bTAdapter != null && BTHelper.btEnabled()) {
                    IPermission iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
                    if (Build.VERSION.SDK_INT >= 23 && iPermission != null) {
                        if (!iPermission.hasPermission("android.permission.ACCESS_COARSE_LOCATION")) {
                            iPermission.hasPermission("android.permission.ACCESS_FINE_LOCATION");
                        }
                        LocationManager locationManager = (LocationManager) com.jingdong.manto.c.a().getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
                        if (locationManager != null) {
                            locationManager.isProviderEnabled("gps");
                            locationManager.isProviderEnabled("network");
                        }
                    }
                    this.f13712f.set(true);
                    if (list != null && list.size() == 0) {
                        list = null;
                    }
                    if (g.a(bTAdapter, list, a(com.jingdong.manto.m.t0.d.a.f13617j.f13624g), this.f13711e)) {
                        this.f13715i = iVar;
                        int i2 = com.jingdong.manto.m.t0.d.a.f13617j.b;
                        if (i2 > 0) {
                            this.f13717k.postDelayed(this.f13714h, i2);
                        }
                    }
                }
                eVar = com.jingdong.manto.m.t0.d.d.e.f13666g;
                dVar.a(eVar);
            }
            eVar = com.jingdong.manto.m.t0.d.d.e.d;
            dVar.a(eVar);
        }
        eVar = com.jingdong.manto.m.t0.d.d.e.f13672m;
        dVar.a(eVar);
    }
}
