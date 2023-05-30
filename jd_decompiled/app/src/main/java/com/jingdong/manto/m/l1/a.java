package com.jingdong.manto.m.l1;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class a extends d0 implements SensorEventListener, n.e0, n.d0, n.h0 {
    protected h a;
    protected int b;

    /* renamed from: c  reason: collision with root package name */
    private SensorManager f13432c;

    /* renamed from: f  reason: collision with root package name */
    private n f13434f;
    private boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    private int f13433e = 0;

    /* renamed from: g  reason: collision with root package name */
    private List<Sensor> f13435g = new ArrayList();

    private void a(h hVar) {
        try {
            n i2 = hVar.h().f13014f.getFirstPage().i();
            this.f13434f = i2;
            i2.a((n.h0) this);
            this.f13434f.a((n.d0) this);
            this.f13434f.a((n.e0) this);
        } catch (Exception e2) {
            MantoLog.e("betterSensor", e2);
        }
    }

    public void a(String str) {
        h hVar = this.a;
        if (hVar != null) {
            hVar.a(this.b, putErrMsg(str, null, getJsApiName()));
        }
    }

    @Override // com.jingdong.manto.q.n.h0
    public boolean a() {
        onBackground();
        this.f13432c = null;
        this.a = null;
        this.f13435g.clear();
        MantoLog.d("betterSensor", "onRemove: unregister");
        n nVar = this.f13434f;
        if (nVar != null) {
            nVar.b((n.e0) this);
            this.f13434f.b((n.d0) this);
            this.f13434f.b((n.h0) this);
        }
        this.f13434f = null;
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    abstract List<Integer> d();

    public void e() {
        try {
            this.f13432c.unregisterListener(this);
        } catch (Throwable th) {
            MantoLog.e("betterSensor", th.getMessage());
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        super.exec(hVar, jSONObject, i2, str);
        MantoLog.d("betterSensor", "" + jSONObject);
        this.a = hVar;
        this.b = i2;
        if (this.f13432c == null) {
            this.f13432c = (SensorManager) com.jingdong.manto.c.a().getSystemService("sensor");
        }
        if (this.f13432c == null) {
            str2 = "fail:null system service";
        } else {
            boolean optBoolean = jSONObject.optBoolean("enable", c());
            this.d = optBoolean;
            int optInt = jSONObject.optInt("interval", 200);
            this.f13435g.clear();
            Iterator<Integer> it = d().iterator();
            while (true) {
                if (!it.hasNext()) {
                    if (optBoolean) {
                        boolean z = false;
                        for (Sensor sensor : this.f13435g) {
                            int i3 = optInt != 0 ? optInt != 20 ? optInt != 60 ? 3 : 2 : 1 : 0;
                            this.f13433e = i3;
                            try {
                                z = this.f13432c.registerListener(this, sensor, i3);
                                if (!z) {
                                    this.f13432c.unregisterListener(this);
                                }
                            } catch (Exception e2) {
                                MantoLog.e("betterSensor", e2);
                            }
                            if (!z) {
                                str2 = "fail:registerListener failed";
                            }
                        }
                    } else {
                        try {
                            this.f13432c.unregisterListener(this);
                        } catch (Exception unused) {
                        }
                    }
                    if (!b()) {
                        hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
                    }
                    a(hVar);
                    return;
                }
                Sensor defaultSensor = this.f13432c.getDefaultSensor(it.next().intValue());
                if (defaultSensor == null) {
                    str2 = "fail:null sensor";
                    break;
                }
                this.f13435g.add(defaultSensor);
            }
        }
        hVar.a(i2, putErrMsg(str2, null, str));
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    @Override // com.jingdong.manto.q.n.d0
    public void onBackground() {
        try {
            SensorManager sensorManager = this.f13432c;
            if (sensorManager != null) {
                sensorManager.unregisterListener(this);
            }
        } catch (Exception unused) {
        }
        MantoLog.d("betterSensor", "bg unregister");
    }

    @Override // com.jingdong.manto.q.n.e0
    public void onForeground() {
        if (this.f13432c == null || !this.d || this.f13435g.isEmpty()) {
            return;
        }
        MantoLog.d("betterSensor", "ft register");
        try {
            Iterator<Sensor> it = this.f13435g.iterator();
            while (it.hasNext()) {
                this.f13432c.registerListener(this, it.next(), this.f13433e);
            }
        } catch (Exception unused) {
        }
    }
}
