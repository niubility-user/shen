package com.tencent.mapsdk.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;

/* loaded from: classes9.dex */
public class pi extends Handler {
    public static final int b = 0;

    /* renamed from: c  reason: collision with root package name */
    public static final int f17002c = 1;
    public static final int d = 2;
    private final xi a;

    public pi(xi xiVar, Looper looper) {
        super(looper);
        this.a = xiVar;
        qa.h(pa.T);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        CameraPosition J;
        super.handleMessage(message);
        xi xiVar = this.a;
        if (xiVar == null || xiVar.getMap() == null || !xiVar.getMap().g() || (J = xiVar.J()) == null) {
            return;
        }
        if (message.what == 2) {
            xiVar.Z();
            qa.i(pa.T);
        }
        int i2 = message.what;
        if (i2 == 0) {
            xiVar.onCameraChange(J);
        } else if (i2 == 1) {
            xiVar.a0();
            xiVar.onCameraChangeFinished(J);
        }
        xiVar.d0();
    }
}
