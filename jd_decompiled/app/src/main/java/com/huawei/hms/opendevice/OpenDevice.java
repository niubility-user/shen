package com.huawei.hms.opendevice;

import android.content.Context;
import com.huawei.hms.utils.Checker;

/* loaded from: classes12.dex */
public class OpenDevice {
    private OpenDevice() {
    }

    public static OpenDeviceClient getOpenDeviceClient(Context context) {
        Checker.assertNonNull(context);
        return new OpenDeviceClientImpl(context);
    }
}
