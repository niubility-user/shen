package com.jd.lib.productdetail.mainimage.holder.gyroscope;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes15.dex */
public class a implements SensorEventListener {

    /* renamed from: h */
    public SensorManager f4869h;

    /* renamed from: i */
    public long f4870i;

    /* renamed from: g */
    public Map<PdMImageGyroscopeImageView, Boolean> f4868g = new HashMap(9);

    /* renamed from: j */
    public double f4871j = 1.0471975511965976d;

    /* renamed from: com.jd.lib.productdetail.mainimage.holder.gyroscope.a$a */
    /* loaded from: classes15.dex */
    public static class C0157a {
        public static final a a = new a();
    }

    public void a(Activity activity) {
        if (this.f4869h == null && activity != null) {
            this.f4869h = (SensorManager) activity.getSystemService("sensor");
        }
        SensorManager sensorManager = this.f4869h;
        if (sensorManager != null) {
            this.f4869h.registerListener(this, sensorManager.getDefaultSensor(4), 1);
            this.f4870i = 0L;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 4) {
            if (this.f4870i != 0) {
                for (Map.Entry<PdMImageGyroscopeImageView, Boolean> entry : this.f4868g.entrySet()) {
                    if (entry.getValue().booleanValue()) {
                        PdMImageGyroscopeImageView key = entry.getKey();
                        double d = key.f4863n;
                        double d2 = sensorEvent.values[0] * ((float) (sensorEvent.timestamp - this.f4870i)) * 1.0E-9f * 2.0f;
                        Double.isNaN(d2);
                        key.f4863n = d + d2;
                        PdMImageGyroscopeImageView key2 = entry.getKey();
                        double d3 = key2.f4862m;
                        double d4 = sensorEvent.values[1] * ((float) (sensorEvent.timestamp - this.f4870i)) * 1.0E-9f * 2.0f;
                        Double.isNaN(d4);
                        key2.f4862m = d3 + d4;
                        if (entry.getKey().f4863n > this.f4871j) {
                            entry.getKey().f4863n = this.f4871j;
                        }
                        if (entry.getKey().f4863n < (-this.f4871j)) {
                            entry.getKey().f4863n = -this.f4871j;
                        }
                        if (entry.getKey().f4862m > this.f4871j) {
                            entry.getKey().f4862m = this.f4871j;
                        }
                        if (entry.getKey().f4862m < (-this.f4871j)) {
                            entry.getKey().f4862m = -this.f4871j;
                        }
                        PdMImageGyroscopeImageView key3 = entry.getKey();
                        key3.f4856g = entry.getKey().f4862m / this.f4871j;
                        key3.f4857h = entry.getKey().f4863n / this.f4871j;
                        key3.invalidate();
                    }
                }
            }
            this.f4870i = sensorEvent.timestamp;
        }
    }
}
