package com.jd.lib.productdetail.mainimage.holder.comment;

import android.animation.ValueAnimator;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentUGCInfo;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class j implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: g  reason: collision with root package name */
    public boolean f4773g = false;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ PdMImageCommentUGCNewView f4774h;

    public j(PdMImageCommentUGCNewView pdMImageCommentUGCNewView, int i2) {
        this.f4774h = pdMImageCommentUGCNewView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (ViewTreeLifecycleOwner.get(this.f4774h) == null || ViewTreeLifecycleOwner.get(this.f4774h).getLifecycle() == null || !ViewTreeLifecycleOwner.get(this.f4774h).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            return;
        }
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        int height = this.f4774h.getHeight();
        float f2 = 0.0f;
        if (floatValue > 0.0f) {
            float f3 = 400;
            if (floatValue < f3) {
                float f4 = floatValue / f3;
                this.f4774h.setAlpha(1.0f - f4);
                f2 = (-height) * f4;
                this.f4774h.setTranslationY(f2);
            }
        }
        float f5 = 400;
        if (floatValue >= f5 && floatValue <= 800) {
            if (!this.f4773g) {
                PdMImageCommentUGCNewView pdMImageCommentUGCNewView = this.f4774h;
                ArrayList<PdCommentUGCInfo> arrayList = pdMImageCommentUGCNewView.f4724h;
                int i2 = pdMImageCommentUGCNewView.f4725i + 1;
                pdMImageCommentUGCNewView.f4725i = i2;
                pdMImageCommentUGCNewView.c(arrayList.get(i2 % arrayList.size()));
                this.f4773g = true;
            }
            float f6 = (floatValue - f5) / f5;
            this.f4774h.setAlpha(f6);
            f2 = height * (1.0f - f6);
        }
        this.f4774h.setTranslationY(f2);
    }
}
