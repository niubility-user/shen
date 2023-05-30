package com.jingdong.app.mall;

import android.app.Application;
import android.content.Context;
import com.jingdong.app.mall.aura.n;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.k.a;
import com.jingdong.app.mall.k.f;
import com.jingdong.app.mall.widget.WidgetUtils;
import com.jingdong.aura.DownGradeUtils;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
class JDAppLikeInitImpl implements IJDAppLikeInit {
    f processInitLifeCycle;
    private final AtomicBoolean initStatus = new AtomicBoolean(false);
    private boolean lazyInit = true;
    private boolean visitModeInit = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements JDMobileConfig.IXTime {
        a(JDAppLikeInitImpl jDAppLikeInitImpl) {
        }

        @Override // com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig.IXTime
        public boolean isXTime() {
            return DownGradeUtils.isDownGrade();
        }
    }

    private void initJDMobileConfig() {
        try {
            JDMobileConfig.Builder xTimeInterFace = new JDMobileConfig.Builder(JdSdk.getInstance().getApplication()).setUserId(UserUtil.getWJLoginHelper().getPin()).setFetchDataWithInit(false).setIsDebug(JdSdk.getInstance().getBuildConfigDebug()).setFetcher(new n()).setDefaultAssetsJsonPath("defaultMobileConfig.json").setXTimeInterFace(new a(this));
            if (Configuration.isBeta()) {
                xTimeInterFace.setAppId("939bf410447f47b9928b1c281a38206f");
            } else {
                xTimeInterFace.setAppId("fba8ae5a5078417d90ae1355af234d4f");
            }
            JDMobileConfig.getInstance().init(xTimeInterFace);
        } catch (Throwable unused) {
        }
    }

    private void initJdsdk(Application application) {
        JdSdk.getInstance().setApplication(application);
        JdSdk.getInstance().setBuildConfigDebug(false);
        JdSdk.getInstance().setGoogleChannel(false);
        JdSdk.getInstance().setArm64Only(false);
        JdSdk.getInstance().setPkgType(3);
        JdSdk.getInstance().setPrivacyOfflineStatus(false);
    }

    @Override // com.jingdong.app.mall.IJDAppLikeInit
    public void init(Application application) {
        initJdsdk(application);
        OpenLinkTimeManager.getInstance().jdSdkInited();
        com.jingdong.app.mall.log.e.b().f();
        com.jingdong.app.mall.utils.d.d(application);
        initJDMobileConfig();
        com.jingdong.app.mall.log.e.b().g();
        this.processInitLifeCycle = a.h.a();
        BackForegroundWatcher.getInstance().init(application);
    }

    @Override // com.jingdong.app.mall.IJDAppLikeInit
    public boolean initStatus() {
        return this.initStatus.get();
    }

    @Override // com.jingdong.app.mall.IJDAppLikeInit
    public boolean isLazyInit() {
        return this.lazyInit;
    }

    @Override // com.jingdong.app.mall.IJDAppLikeInit, com.jingdong.app.mall.k.f
    public void onBaseContextAttached(Context context) {
        boolean isAcceptPrivacy = JDPrivacyHelper.isAcceptPrivacy(context);
        if (isAcceptPrivacy || !ProcessUtil.isMainProcess()) {
            this.initStatus.set(true);
            this.lazyInit = false;
        }
        if (!isAcceptPrivacy) {
            this.visitModeInit = CommonBase.getBooleanFromPreference("privacy_dialog_dismiss", Boolean.FALSE).booleanValue();
        }
        if (this.processInitLifeCycle == null || !this.initStatus.get()) {
            return;
        }
        this.processInitLifeCycle.onBaseContextAttached(context);
    }

    @Override // com.jingdong.app.mall.IJDAppLikeInit
    public void onConfigurationChanged(android.content.res.Configuration configuration) {
        if (OKLog.D && configuration != null) {
            OKLog.d("DeepDarkChangeManager", "JDApp_onConfigurationChanged_uiMode=" + configuration.uiMode);
        }
        WidgetUtils.q();
        DeepDarkChangeManager.getInstance().handleUIModeConfiguration(configuration);
        JDElderModeUtils.onConfigurationChanged(configuration);
    }

    @Override // com.jingdong.app.mall.IJDAppLikeInit, com.jingdong.app.mall.k.f
    public void onCreate() {
        if (this.processInitLifeCycle != null && this.initStatus.get()) {
            this.processInitLifeCycle.onCreate();
        }
        if (this.initStatus.get() || !this.visitModeInit) {
            return;
        }
        this.lazyInit = false;
        reInit();
    }

    @Override // com.jingdong.app.mall.IJDAppLikeInit
    public void reInit() {
        if (this.processInitLifeCycle == null || this.initStatus.get()) {
            return;
        }
        this.processInitLifeCycle.onBaseContextAttached(JdSdk.getInstance().getApplicationContext());
        this.processInitLifeCycle.onCreate();
        this.initStatus.set(true);
    }
}
