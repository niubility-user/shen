package com.jd.framework.retrofit.widget;

import android.app.Activity;
import android.view.ViewGroup;
import com.jingdong.common.network.EffectHttpListener;

/* loaded from: classes13.dex */
public class RxLoadingController extends EffectHttpListener {
    private RxLoadingController(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
    }

    public static RxLoadingController newInstance(Activity activity) {
        return new RxLoadingController(activity, null);
    }

    public void hideLoadingView() {
        missionComplete();
    }

    public void showLoadingView() {
        missionBegins();
    }

    public static RxLoadingController newInstance(Activity activity, ViewGroup viewGroup) {
        return new RxLoadingController(activity, viewGroup);
    }
}
