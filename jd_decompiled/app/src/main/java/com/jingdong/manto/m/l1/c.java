package com.jingdong.manto.m.l1;

import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import com.jingdong.manto.h;
import com.jingdong.manto.utils.MantoLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends com.jingdong.manto.m.l1.a {

    /* renamed from: h  reason: collision with root package name */
    String f13436h = "";

    /* renamed from: i  reason: collision with root package name */
    int f13437i = 0;

    /* renamed from: j  reason: collision with root package name */
    public float[] f13438j = new float[3];

    /* renamed from: k  reason: collision with root package name */
    public float[] f13439k = new float[3];

    /* loaded from: classes15.dex */
    class a extends com.jingdong.manto.m.d {
        a(c cVar) {
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onCompassChange";
        }
    }

    @Override // com.jingdong.manto.m.l1.a
    List<Integer> d() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(1);
        arrayList.add(2);
        return arrayList;
    }

    @Override // com.jingdong.manto.m.l1.a, com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        hVar.a(i2, putErrMsg("fail:api is forbidden", null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "enableCompass";
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        Object valueOf;
        String str;
        float[] fArr = sensorEvent.values;
        if (fArr == null || fArr.length < 3) {
            MantoLog.e("sensor", "Compass sensor callback data invalidate.");
            return;
        }
        if (sensorEvent.sensor.getType() == 2) {
            this.f13439k = sensorEvent.values;
            int i2 = sensorEvent.accuracy;
            if (i2 == -1) {
                str = "no-contact";
            } else if (i2 == 0) {
                str = "unreliable";
            } else if (i2 == 1) {
                str = "low";
            } else if (i2 == 2) {
                str = "medium";
            } else if (i2 != 3) {
                this.f13436h = "unknow";
                this.f13437i = i2;
            } else {
                str = "high";
            }
            this.f13436h = str;
        } else if (sensorEvent.sensor.getType() != 1) {
            return;
        } else {
            this.f13438j = sensorEvent.values;
        }
        float[] fArr2 = new float[9];
        SensorManager.getRotationMatrix(fArr2, null, this.f13438j, this.f13439k);
        SensorManager.getOrientation(fArr2, new float[3]);
        HashMap hashMap = new HashMap();
        float degrees = (float) Math.toDegrees(r6[0]);
        if (degrees < 0.0f) {
            degrees += 360.0f;
        }
        hashMap.put("direction", Float.valueOf(degrees));
        if ("unknow".equalsIgnoreCase(this.f13436h)) {
            valueOf = this.f13436h + "{value:" + this.f13437i + "}";
        } else {
            valueOf = Integer.valueOf(this.f13437i);
        }
        hashMap.put("accuracy", valueOf);
        MantoLog.d("betterSensor", this.f13437i + ", " + degrees);
        h hVar = this.a;
        if (hVar == null || !hVar.f()) {
            return;
        }
        new a(this).a(this.a).a(hashMap).a();
    }
}
