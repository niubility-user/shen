package com.jingdong.app.mall.privacy;

import android.app.Activity;
import com.jingdong.app.mall.JDApp;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.ad.c;
import com.jingdong.app.mall.ad.d;
import com.jingdong.app.mall.home.b;
import com.jingdong.app.mall.privacy.JDPrivacyManager;
import com.jingdong.app.mall.widget.WidgetUtils;
import com.jingdong.common.utils.FireEyeUtils;
import com.jingdong.common.utils.X5InitUtil;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.common.xbridge.BridgeManager;
import com.jingdong.common.xbridge.XWidgetManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class PrivacyBridge {
    private static final AtomicBoolean isAgree = new AtomicBoolean(false);
    private static final AtomicBoolean isSimpleAgree = new AtomicBoolean(false);
    private static final AtomicBoolean isDisagree = new AtomicBoolean(false);
    private static final AtomicBoolean sHomeRequest = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: private */
    public static void initAfterAgreePrivacy(boolean z) {
        if (isAgree.getAndSet(true)) {
            return;
        }
        initCommon(z);
        WidgetUtils.q();
    }

    public static void initAfterDisagreePrivacy(boolean z) {
        if (isDisagree.getAndSet(true)) {
            return;
        }
        initSimpleListener();
        initCommon(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initAfterSimpleAgree() {
        if (isSimpleAgree.getAndSet(true)) {
            return;
        }
        requestOAID();
        FireEyeUtils.reportFireEyeS(JdSdk.getInstance().getApplication(), false);
        X5InitUtil.preloadX5(JdSdk.getInstance().getApplication());
        XRender.getInstance().initXRender();
        WebHybridUtils.initHybrid(false);
        WebHybridUtils.loadBuildInConfig();
        BridgeManager.registerPlugin();
        XWidgetManager.registerWidget();
    }

    private static void initCommon(boolean z) {
        requestOAID();
        JDMtaUtils.acceptPrivacyProtocol(true);
        if (!JDApp.getInstance().initStatus()) {
            JDApp.getInstance().reInit();
        }
        if (z) {
            return;
        }
        requestHomeDataAfterPrivacy();
    }

    private static void initSimpleListener() {
        JDPrivacyManager.sSimpleStateListener = new JDPrivacyStateListener() { // from class: com.jingdong.app.mall.privacy.PrivacyBridge.2
            @Override // com.jingdong.app.mall.privacy.JDPrivacyStateListener
            public void afterAgree(boolean z) {
                PrivacyBridge.initAfterSimpleAgree();
            }

            @Override // com.jingdong.app.mall.privacy.JDPrivacyStateListener
            public void afterDisagree(boolean z) {
            }
        };
    }

    public static void launchPrivacyDialog(Activity activity, JDPrivacyManager.PrivacyCallback privacyCallback) {
        launchPrivacyDialog(true, true, activity, privacyCallback);
    }

    public static void requestHomeData() {
        X5InitUtil.preloadX5(JdSdk.getInstance().getApplicationContext());
        d i2 = c.l().i();
        MainFrameActivity.setAdObject(i2);
        if (sHomeRequest.get()) {
            return;
        }
        b.m().z(i2, true, "0");
    }

    public static void requestHomeDataAfterPrivacy() {
        requestHomeData();
        sHomeRequest.set(true);
    }

    private static void requestOAID() {
        com.jingdong.app.mall.utils.d.k();
    }

    public static void launchPrivacyDialog(boolean z, Activity activity, JDPrivacyManager.PrivacyCallback privacyCallback) {
        launchPrivacyDialog(true, z, activity, privacyCallback);
    }

    public static void launchPrivacyDialog(boolean z, final boolean z2, Activity activity, JDPrivacyManager.PrivacyCallback privacyCallback) {
        JDPrivacyManager.sStateListener = new JDPrivacyStateListener() { // from class: com.jingdong.app.mall.privacy.PrivacyBridge.1
            @Override // com.jingdong.app.mall.privacy.JDPrivacyStateListener
            public void afterAgree(boolean z3) {
                if (z2) {
                    PrivacyBridge.initAfterAgreePrivacy(z3);
                }
            }

            @Override // com.jingdong.app.mall.privacy.JDPrivacyStateListener
            public void afterDisagree(boolean z3) {
                PrivacyBridge.initAfterDisagreePrivacy(z3);
            }
        };
        b.m().t();
        JDPrivacyManager.getInstance().openPrivacyDialog(z, activity, privacyCallback);
    }
}
