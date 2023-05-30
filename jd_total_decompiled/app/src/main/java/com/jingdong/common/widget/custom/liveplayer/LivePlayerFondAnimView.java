package com.jingdong.common.widget.custom.liveplayer;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.lottie.LottieAnimationView;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes12.dex */
public class LivePlayerFondAnimView extends LottieAnimationView {
    public LivePlayerFondAnimView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        try {
            setImageAssetsFolder("images");
            setAnimation("liveAnimLottie.json");
            setRepeatCount(-1);
            setRepeatMode(1);
            playAnimation();
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d("LivePlayerFondAnimView", "init LivePlayerFondAnimView error : " + th.getMessage());
            }
        }
    }

    public LivePlayerFondAnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public LivePlayerFondAnimView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context);
    }
}
