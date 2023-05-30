package com.jingdong.app.mall.home.floor.animation.lottie;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.JsonReader;
import androidx.annotation.NonNull;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.l;
import java.io.StringReader;

/* loaded from: classes4.dex */
public class LottieAnimationViewCatchDraw extends LottieAnimationView {
    public LottieAnimationViewCatchDraw(Context context) {
        super(context);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        try {
            super.draw(canvas);
        } catch (Exception e2) {
            f.s0(this, e2);
        }
    }

    public boolean isValid(String str) {
        boolean z;
        boolean z2 = true;
        try {
            z = !l.p();
        } catch (Throwable th) {
            th = th;
            z2 = false;
        }
        try {
        } catch (Throwable th2) {
            th = th2;
            z2 = z;
            th.printStackTrace();
            return z2;
        }
        if (!TextUtils.isEmpty(str) && z) {
            JsonReader jsonReader = new JsonReader(new StringReader(str));
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                jsonReader.skipValue();
            }
            jsonReader.endObject();
            return z2;
        }
        return false;
    }

    @Override // com.airbnb.lottie.LottieAnimationView
    public void pauseAnimation() {
        try {
            super.pauseAnimation();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.airbnb.lottie.LottieAnimationView
    public void playAnimation() {
        try {
            super.playAnimation();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.airbnb.lottie.LottieAnimationView
    public void setComposition(@NonNull LottieComposition lottieComposition) {
        try {
            super.setComposition(lottieComposition);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setLottieJson(String str, String str2) {
        setAnimationFromJson(str, str2);
    }

    public LottieAnimationViewCatchDraw(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LottieAnimationViewCatchDraw(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
