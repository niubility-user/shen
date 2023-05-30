package com.jingdong.manto.m.l1;

import android.hardware.SensorEvent;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.h;
import com.jingdong.manto.utils.MantoLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes15.dex */
public final class b extends com.jingdong.manto.m.l1.a {

    /* loaded from: classes15.dex */
    class a extends com.jingdong.manto.m.d {
        a(b bVar) {
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onAccelerometerChange";
        }
    }

    @Override // com.jingdong.manto.m.l1.a
    List<Integer> d() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(1);
        return arrayList;
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "enableAccelerometer";
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            float[] fArr = sensorEvent.values;
            if (fArr == null || fArr.length < 3) {
                MantoLog.e("sensor", "Accelerometer sensor callback data invalidate.");
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put(JshopConst.JSHOP_PROMOTIO_X, Float.valueOf((-fArr[0]) / 10.0f));
            hashMap.put(JshopConst.JSHOP_PROMOTIO_Y, Float.valueOf((-fArr[1]) / 10.0f));
            hashMap.put("z", Float.valueOf((-fArr[2]) / 10.0f));
            h hVar = this.a;
            if (hVar == null || !hVar.f()) {
                return;
            }
            new a(this).a(this.a).a(hashMap).a();
        }
    }
}
