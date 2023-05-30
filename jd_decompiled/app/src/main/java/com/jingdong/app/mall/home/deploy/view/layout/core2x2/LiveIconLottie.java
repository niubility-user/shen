package com.jingdong.app.mall.home.deploy.view.layout.core2x2;

import android.content.Context;
import com.jingdong.app.mall.home.floor.animation.lottie.HomeSimpleLottieView;

/* loaded from: classes4.dex */
public class LiveIconLottie extends HomeSimpleLottieView {
    public LiveIconLottie(Context context) {
        super(context);
        e("local/homeIconLive.json", "HOME_LIVE_ICON_LOTTIE");
        if (this.f9133g.get()) {
            setRepeatCount(Integer.MAX_VALUE);
        }
    }
}
