package com.jingdong.manto.m.l1;

import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.manto.h;
import com.jingdong.manto.utils.MantoLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes15.dex */
public final class d extends com.jingdong.manto.m.l1.a {

    /* renamed from: h  reason: collision with root package name */
    private float[] f13440h = new float[3];

    /* renamed from: i  reason: collision with root package name */
    private float[] f13441i = new float[3];

    /* renamed from: j  reason: collision with root package name */
    private final float[] f13442j = new float[9];

    /* renamed from: k  reason: collision with root package name */
    private final float[] f13443k = new float[3];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a extends com.jingdong.manto.m.d {
        a(d dVar) {
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onDeviceMotionChange";
        }
    }

    @Override // com.jingdong.manto.m.l1.a
    List<Integer> d() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(1);
        arrayList.add(2);
        return arrayList;
    }

    public void f() {
        SensorManager.getRotationMatrix(this.f13442j, null, this.f13440h, this.f13441i);
        SensorManager.getOrientation(this.f13442j, this.f13443k);
        float[] fArr = this.f13443k;
        if (fArr == null || fArr.length < 3) {
            MantoLog.e("sensor", "DeviceMotionChange sensor callback data invalidate.");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("alpha", Float.valueOf(this.f13443k[0]));
        hashMap.put("beta", Float.valueOf(this.f13443k[1]));
        hashMap.put("gamma", Float.valueOf(this.f13443k[2]));
        StringBuilder sb = new StringBuilder();
        sb.append("z\u8f74:" + this.f13443k[0] + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("x\u8f74:" + this.f13443k[1] + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("y\u8f74:" + this.f13443k[2] + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.toString();
        h hVar = this.a;
        if (hVar == null || !hVar.f()) {
            return;
        }
        new a(this).a(this.a).a(hashMap).a();
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "enableDeviceMotionChangeListening";
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            this.f13440h = sensorEvent.values;
        } else if (sensorEvent.sensor.getType() == 2) {
            this.f13441i = sensorEvent.values;
        }
        f();
    }
}
