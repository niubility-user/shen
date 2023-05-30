package com.jingdong.app.mall.home.deploy.view.layout.corelive2x2;

import android.content.Context;
import com.jingdong.app.mall.home.floor.animation.lottie.HomeSimpleLottieView;

/* loaded from: classes4.dex */
public class LiveSkuLottie extends HomeSimpleLottieView {
    public LiveSkuLottie(Context context) {
        super(context);
        e("local/homeLiveSkuLottie.json", "HOME_LIVE_SKU_");
        setRepeatCount(Integer.MAX_VALUE);
    }
}
