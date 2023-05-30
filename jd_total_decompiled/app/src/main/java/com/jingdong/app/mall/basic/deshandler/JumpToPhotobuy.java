package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.ui.CameraUtils;

@Des(des = "photobuy,native_photobuy")
/* loaded from: classes19.dex */
public class JumpToPhotobuy extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (CameraUtils.checkCameraHardware(context) && (context instanceof BaseActivity)) {
            bundle.putInt("position", 1);
            DeepLinkScanHelper.startCaptureActivityForOpenApp((BaseActivity) context, bundle);
        }
        c(context);
    }
}
