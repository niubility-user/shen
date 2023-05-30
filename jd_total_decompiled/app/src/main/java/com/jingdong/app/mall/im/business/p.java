package com.jingdong.app.mall.im.business;

import android.view.View;
import com.jingdong.common.BaseApplication;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.impl.IMLottie;

/* loaded from: classes4.dex */
public class p extends IMLottie {
    private static final String a = "p";

    @Override // com.jingdong.service.impl.IMLottie, com.jingdong.service.service.LottieService
    public View getLottieLoadingView() {
        OKLog.d("bundleicssdkservice", a + "---getLottieLoadingView");
        return BaseApplication.getLottieLoadingView();
    }
}
