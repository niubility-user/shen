package com.jingdong.aura.core.util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* loaded from: classes4.dex */
public class FakeService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }
}
