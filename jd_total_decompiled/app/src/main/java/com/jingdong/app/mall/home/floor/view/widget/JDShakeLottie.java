package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jingdong.app.mall.home.floor.animation.lottie.LottieAnimationViewCatchDraw;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.o.a.k;

/* loaded from: classes4.dex */
public class JDShakeLottie extends LottieAnimationViewCatchDraw {

    /* renamed from: h  reason: collision with root package name */
    private static String f10108h;

    /* renamed from: g  reason: collision with root package name */
    private boolean f10109g;

    public JDShakeLottie(Context context) {
        super(context);
        a();
    }

    private void a() {
        setImageAssetsFolder("assets/");
        if (TextUtils.isEmpty(f10108h)) {
            f10108h = k.o("local/homeShakeLottie.json");
        }
        if (!TextUtils.isEmpty(f10108h) && isValid(f10108h)) {
            this.f10109g = true;
            setLottieJson(f10108h, "HOME_SHAKE_");
            setRepeatMode(1);
            setRepeatCount(10);
            return;
        }
        this.f10109g = false;
        setVisibility(8);
    }

    public boolean b() {
        return this.f10109g;
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        layoutParams.width = d.d(300);
        layoutParams.height = d.d(300);
        super.setLayoutParams(layoutParams);
    }
}
