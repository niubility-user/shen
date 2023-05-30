package com.jingdong.common.web.util;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.web.CommonMHybridHelper;
import com.jingdong.common.web.WebOfflineLoaderManager;
import com.jingdong.common.web.WebPreLoadHelper;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.ui.JDWebViewBuilder;

/* loaded from: classes12.dex */
public class HybridCreator {
    private HybridCreator() {
    }

    public static CommonMFragment createCommonMFragment(@NonNull Bundle bundle) {
        CommonMFragment commonMFragment = new CommonMFragment();
        WebOfflineLoaderManager.createOfflineLoaderForBundle(bundle, CommonMHybridHelper.sOfflineParamGetter);
        WebPreLoadHelper.startPreLoadForBundle(bundle, CommonMHybridHelper.sPreloadParamGetter);
        bundle.putBoolean(MBaseKeyNames.KEY_USE_HYBRID, true);
        return commonMFragment;
    }

    public static JDWebView createJDWebView(@NonNull Context context, @NonNull String str) {
        String createOfflineLoader = WebOfflineLoaderManager.createOfflineLoader(str);
        String preLoad = WebPreLoadHelper.preLoad(str);
        JDWebViewBuilder jDWebViewBuilder = new JDWebViewBuilder(context);
        jDWebViewBuilder.setShouldReportInitRecord(true).setCanUseDarkMode(true).setEnableHybrid(true).setHybridStarted(true).setHybridUrl(str).setOfflineId(createOfflineLoader).setPreloadId(preLoad);
        return jDWebViewBuilder.build();
    }
}
