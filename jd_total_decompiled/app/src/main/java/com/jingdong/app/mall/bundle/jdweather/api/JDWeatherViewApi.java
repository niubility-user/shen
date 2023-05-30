package com.jingdong.app.mall.bundle.jdweather.api;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.app.mall.bundle.jdweather.R;
import com.jingdong.app.mall.bundle.jdweather.config.JDWeatherAnimConfig;
import com.jingdong.app.mall.bundle.jdweather.config.JDWeatherConfig;
import com.jingdong.app.mall.bundle.jdweather.e.a;
import com.jingdong.app.mall.bundle.jdweather.view.JDWeatherView;
import java.util.Map;

/* loaded from: classes.dex */
public class JDWeatherViewApi {
    private static final String TAG = "JDWeatherViewApi";

    /* JADX INFO: Access modifiers changed from: private */
    public static void attachToWindow(final FragmentActivity fragmentActivity, final ViewGroup viewGroup, final JDWeatherAnimConfig jDWeatherAnimConfig) {
        if (a.a(fragmentActivity)) {
            if (a.b()) {
                exeAttachToWindow(fragmentActivity, viewGroup, jDWeatherAnimConfig);
            } else {
                fragmentActivity.runOnUiThread(new Runnable() { // from class: com.jingdong.app.mall.bundle.jdweather.api.JDWeatherViewApi.2
                    @Override // java.lang.Runnable
                    public void run() {
                        JDWeatherViewApi.exeAttachToWindow(FragmentActivity.this, viewGroup, jDWeatherAnimConfig);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void exeAttachToWindow(FragmentActivity fragmentActivity, ViewGroup viewGroup, JDWeatherAnimConfig jDWeatherAnimConfig) {
        if (!a.a(fragmentActivity) || jDWeatherAnimConfig == null || viewGroup == null) {
            return;
        }
        int i2 = R.id.jdweather_lottie_view_id;
        View findViewById = viewGroup.findViewById(i2);
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
        }
        JDWeatherView jDWeatherView = new JDWeatherView(fragmentActivity);
        jDWeatherView.setId(i2);
        jDWeatherView.initAndShowLottieAnimation(fragmentActivity, jDWeatherAnimConfig);
        viewGroup.addView(jDWeatherView);
    }

    public static void showWeatherView(final FragmentActivity fragmentActivity, final ViewGroup viewGroup, final JDWeatherConfig jDWeatherConfig) {
        if (jDWeatherConfig != null && !TextUtils.isEmpty(jDWeatherConfig.getAppId()) && !TextUtils.isEmpty(jDWeatherConfig.getModuleId())) {
            if (fragmentActivity == null || fragmentActivity.isFinishing()) {
                return;
            }
            if (!TextUtils.isEmpty(jDWeatherConfig.getLottieJson())) {
                JDWeatherAnimConfig jDWeatherAnimConfig = new JDWeatherAnimConfig();
                jDWeatherAnimConfig.lottieJson = jDWeatherConfig.getLottieJson();
                jDWeatherAnimConfig.animationRepeatCount = jDWeatherConfig.getAnimationRepeatCount();
                attachToWindow(fragmentActivity, viewGroup, jDWeatherAnimConfig);
                return;
            }
            com.jingdong.app.mall.bundle.jdweather.d.a aVar = new com.jingdong.app.mall.bundle.jdweather.d.a();
            aVar.a = jDWeatherConfig.getAppVersion();
            Map<String, String> dynamicParam = jDWeatherConfig.getDynamicParam();
            aVar.b = dynamicParam;
            if (dynamicParam != null) {
                dynamicParam.put("appId", jDWeatherConfig.getAppId());
                aVar.b.put("moduleId", jDWeatherConfig.getModuleId());
            }
            com.jingdong.app.mall.bundle.jdweather.action.a aVar2 = new com.jingdong.app.mall.bundle.jdweather.action.a();
            aVar2.f(new com.jingdong.app.mall.bundle.jdweather.b.a<com.jingdong.app.mall.bundle.jdweather.a.a>() { // from class: com.jingdong.app.mall.bundle.jdweather.api.JDWeatherViewApi.1
                @Override // com.jingdong.app.mall.bundle.jdweather.b.a
                public void callBack(com.jingdong.app.mall.bundle.jdweather.a.a aVar3) {
                    if (aVar3 == null || TextUtils.isEmpty(aVar3.a)) {
                        return;
                    }
                    JDWeatherAnimConfig jDWeatherAnimConfig2 = new JDWeatherAnimConfig();
                    jDWeatherAnimConfig2.lottieJson = aVar3.a;
                    jDWeatherAnimConfig2.animationRepeatCount = JDWeatherConfig.this.getAnimationRepeatCount();
                    JDWeatherViewApi.attachToWindow(fragmentActivity, viewGroup, jDWeatherAnimConfig2);
                }
            });
            aVar2.d(aVar);
            return;
        }
        com.jingdong.app.mall.bundle.jdweather.c.a.b(TAG, "the appId and moduleId is empty");
    }
}
