package com.jingdong.common.widget;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.airbnb.lottie.LottieAnimationView;

/* loaded from: classes12.dex */
public class UnLottieAnimationView extends LottieAnimationView {
    public UnLottieAnimationView(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.airbnb.lottie.LottieAnimationView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        try {
            super.onRestoreInstanceState(parcelable);
        } catch (Exception unused) {
        }
    }

    public UnLottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public UnLottieAnimationView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
