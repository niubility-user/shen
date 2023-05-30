package com.jingdong.sdk.jshopsdk.common.favo;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.airbnb.lottie.LottieAnimationView;

/* loaded from: classes7.dex */
public class JShopLottieAnimationView extends LottieAnimationView {
    public JShopLottieAnimationView(Context context) {
        super(context);
    }

    public JShopLottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JShopLottieAnimationView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.airbnb.lottie.LottieAnimationView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        try {
            super.onRestoreInstanceState(parcelable);
        } catch (Exception unused) {
        }
    }
}
