package com.jingdong.common.unification.uniwidget;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.airbnb.lottie.LottieAnimationView;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes6.dex */
public class JDLottieAnimationView extends LottieAnimationView {
    public JDLottieAnimationView(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.airbnb.lottie.LottieAnimationView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        try {
            super.onRestoreInstanceState(parcelable);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public JDLottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JDLottieAnimationView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
