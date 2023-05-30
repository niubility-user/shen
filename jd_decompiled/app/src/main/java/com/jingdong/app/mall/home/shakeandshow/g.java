package com.jingdong.app.mall.home.shakeandshow;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.jingdong.app.mall.home.o.a.l;
import com.jingdong.sdk.log.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class g {

    /* renamed from: c  reason: collision with root package name */
    private final SensorManager f10855c;
    private final Sensor d;

    /* renamed from: f  reason: collision with root package name */
    private SensorEventListener f10857f;

    /* renamed from: g  reason: collision with root package name */
    private long f10858g;

    /* renamed from: i  reason: collision with root package name */
    private float f10860i;
    private final com.jingdong.app.mall.home.shakeandshow.b a = new com.jingdong.app.mall.home.shakeandshow.b();
    private final AtomicBoolean b = new AtomicBoolean(true);

    /* renamed from: e  reason: collision with root package name */
    private final ArrayList<b> f10856e = new ArrayList<>();

    /* renamed from: h  reason: collision with root package name */
    private float f10859h = 28.0f;

    /* renamed from: j  reason: collision with root package name */
    private final List<Float> f10861j = new ArrayList(32);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements SensorEventListener {
        a() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] fArr = sensorEvent.values;
            float f2 = fArr[0];
            float f3 = fArr[1];
            float f4 = fArr[2];
            float sqrt = (float) Math.sqrt((f2 * f2) + (f3 * f3) + (f4 * f4));
            if (g.this.b.get()) {
                g.this.i(sqrt);
                return;
            }
            f.d(sqrt);
            f.c(g.this.f10859h + g.this.f10860i);
            if (sqrt > g.this.f10859h + g.this.f10860i || g.this.a.d(sensorEvent)) {
                long currentTimeMillis = System.currentTimeMillis();
                if (g.this.f10858g + 500 > currentTimeMillis) {
                    return;
                }
                f.f(1);
                g.this.f10858g = currentTimeMillis;
                for (int i2 = 0; i2 < g.this.f10856e.size(); i2++) {
                    if (g.this.f10856e.get(i2) != null) {
                        ((b) g.this.f10856e.get(i2)).a(sqrt);
                    }
                }
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface b {
        void a(float f2);
    }

    public g(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        this.f10855c = sensorManager;
        this.d = sensorManager.getDefaultSensor(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(float f2) {
        float f3;
        float f4;
        if (this.f10861j.size() < 20) {
            this.f10861j.add(Float.valueOf(f2));
            return;
        }
        Iterator<Float> it = this.f10861j.iterator();
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        while (it.hasNext()) {
            float floatValue = it.next().floatValue();
            if (floatValue > f7) {
                f7 = floatValue;
            }
            f6 += floatValue;
        }
        float size = f6 / this.f10861j.size();
        float f8 = f7 / size;
        boolean z = ((double) f8) < 1.2d && size < 18.0f;
        boolean z2 = f7 > 50.0f || size > 20.0f;
        boolean z3 = f8 > 2.0f;
        if (z) {
            f5 = (size * 2.0f) - this.f10859h;
        } else {
            if (z2) {
                f3 = (f7 + (size * 2.0f)) / 3.0f;
                f4 = this.f10859h;
            } else if (z3) {
                f3 = ((f7 * 2.0f) + size) / 3.0f;
                f4 = this.f10859h;
            }
            f5 = f3 - f4;
        }
        m(f5);
        this.b.set(false);
        this.f10861j.clear();
    }

    public static boolean j(Context context) {
        List<Sensor> sensorList;
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        return (sensorManager == null || (sensorList = sensorManager.getSensorList(1)) == null || sensorList.size() == 0) ? false : true;
    }

    private void m(float f2) {
        if (f2 > this.f10860i) {
            this.f10860i = Math.min(f2, 10.0f);
        }
    }

    private void o() {
        if (this.f10857f == null) {
            this.f10857f = new a();
        }
        if (Log.D) {
            Log.i("ShakeSensorUtil", "registerSensorListener");
        }
        this.f10855c.registerListener(this.f10857f, this.d, 3);
    }

    private void r() {
        this.f10855c.unregisterListener(this.f10857f);
    }

    public boolean k(b bVar) {
        return this.f10856e.contains(bVar);
    }

    public void l() {
        m(this.f10860i + 2.0f);
        this.a.f();
    }

    public void n(b bVar) {
        o();
        if (this.f10856e.contains(bVar)) {
            return;
        }
        this.f10856e.add(bVar);
    }

    public void p(float f2) {
        this.f10859h = f2;
        int l2 = l.l();
        if (l2 > 10) {
            this.f10859h = l2;
        }
    }

    public void q(b bVar) {
        this.f10856e.remove(bVar);
        if (this.f10856e.isEmpty()) {
            r();
        }
    }
}
