package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLink3DProductHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.ui.CameraUtils;

@Des(des = "scan,native_scan,saoasao")
/* loaded from: classes19.dex */
public class JumpToScan extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (CameraUtils.checkCameraHardware(context) && (context instanceof BaseActivity)) {
            if (bundle.getBoolean(DeepLink3DProductHelper.EXTRA_AR, false)) {
                bundle.putInt("position", 2);
            }
            DeepLinkScanHelper.startCaptureActivityForOpenApp((BaseActivity) context, bundle);
        }
    }
}
