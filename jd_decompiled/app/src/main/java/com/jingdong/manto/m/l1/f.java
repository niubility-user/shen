package com.jingdong.manto.m.l1;

import android.hardware.SensorEvent;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class f extends a {

    /* renamed from: l  reason: collision with root package name */
    private static final String f13444l = f.class.getName();

    /* renamed from: h  reason: collision with root package name */
    private float f13445h;

    /* renamed from: i  reason: collision with root package name */
    private float f13446i;

    /* renamed from: j  reason: collision with root package name */
    private float f13447j;

    /* renamed from: k  reason: collision with root package name */
    private long f13448k;

    @Override // com.jingdong.manto.m.l1.a
    public boolean b() {
        return true;
    }

    @Override // com.jingdong.manto.m.l1.a
    public boolean c() {
        return true;
    }

    @Override // com.jingdong.manto.m.l1.a
    List<Integer> d() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(1);
        return arrayList;
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "watchShake";
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        h hVar;
        if (sensorEvent.sensor.getType() == 1) {
            String str = f13444l;
            MantoLog.d(str, "onSensorChanged");
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = currentTimeMillis - this.f13448k;
            MantoLog.d(str, "timeInterval:" + j2);
            if (j2 < 100) {
                MantoLog.d(str, "timeInterval less:100");
                return;
            }
            this.f13448k = currentTimeMillis;
            float[] fArr = sensorEvent.values;
            float f2 = fArr[0];
            float f3 = fArr[1];
            float f4 = fArr[2];
            float f5 = f2 - this.f13445h;
            float f6 = f3 - this.f13446i;
            float f7 = f4 - this.f13447j;
            this.f13445h = f2;
            this.f13446i = f3;
            this.f13447j = f4;
            double sqrt = Math.sqrt((f5 * f5) + (f6 * f6) + (f7 * f7));
            MantoLog.d(str, "speed:" + sqrt);
            if (sqrt < 20.0d || (hVar = this.a) == null || !hVar.f()) {
                return;
            }
            a(IMantoBaseModule.SUCCESS);
            e();
        }
    }
}
