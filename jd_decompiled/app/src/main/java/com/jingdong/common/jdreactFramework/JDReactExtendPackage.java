package com.jingdong.common.jdreactFramework;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.uimanager.ViewManager;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeJumpController;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem;
import com.jingdong.common.jdreactFramework.modules.JDReactAuraInstallModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeBusinessAddressModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeJdBeanModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeJumpControllerModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeKocDraftModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeMyJDModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeSuperMarketModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeSystemModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeThemeModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeUnionModule;
import com.jingdong.common.jdreactFramework.modules.JdcnReactRouterModule;
import com.jingdong.common.jdreactFramework.modules.community.JDRNKocCommunityModule;
import com.jingdong.common.jdreactFramework.modules.community.JDReactNativeCommunityModule;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativePerformanceReporterModule;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule;
import com.jingdong.common.jdreactFramework.views.channel.ChannelFollowViewManager;
import com.jingdong.common.jdreactFramework.views.memberCode.JDReactMemberCodeViewManager;
import com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshControlManger;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.common.jdreactFramework.views.task.JDTaskViewManager;
import com.jingdong.common.jdreactFramework.views.webview.JDReactHybridModule;
import com.jingdong.common.jdreactFramework.views.webview.JDReactWebView2ViewManager;
import com.jingdong.common.jdreactFramework.views.webview.JDReactWebViewManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import java.util.List;

/* loaded from: classes5.dex */
public class JDReactExtendPackage extends JDReactPackage {
    @Override // com.jingdong.common.jdreactFramework.JDReactPackage, com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        List<NativeModule> createNativeModules = super.createNativeModules(reactApplicationContext);
        createNativeModules.add(new JDReactNativeSystemModule(reactApplicationContext, new JDReactNativeSystem(reactApplicationContext)));
        createNativeModules.add(new JDReactNativeJumpControllerModule(reactApplicationContext, new JDReactNativeJumpController()));
        createNativeModules.add(new JDReactNativeSuperMarketModule(reactApplicationContext));
        createNativeModules.add(new JDReactNativeJdBeanModule(reactApplicationContext));
        createNativeModules.add(new JDReactNativeThemeModule(reactApplicationContext));
        createNativeModules.add(new JDReactNativeCommunityModule(reactApplicationContext));
        createNativeModules.add(new JDReactNativeKocDraftModule(reactApplicationContext));
        createNativeModules.add(new JDRNKocCommunityModule(reactApplicationContext));
        createNativeModules.add(new JDReactNativeUphoneModule(reactApplicationContext));
        createNativeModules.add(new JDReactNativeMyJDModule(reactApplicationContext));
        createNativeModules.add(new JdcnReactRouterModule(reactApplicationContext));
        createNativeModules.add(new JDReactNativePerformanceReporterModule(reactApplicationContext));
        createNativeModules.add(new JDReactAuraInstallModule(reactApplicationContext));
        createNativeModules.add(new JDReactNativeBusinessAddressModule(reactApplicationContext));
        createNativeModules.add(new JDReactNativeUnionModule(reactApplicationContext));
        createNativeModules.add(new JDReactHybridModule(reactApplicationContext));
        try {
            if (AuraConfig.isBundlePrepered(AuraBundleInfos.getBundleNameFromBundleId(105))) {
                Object newInstance = JdSdk.getInstance().getApplicationContext().getClassLoader().loadClass("com.jd.lib.virtualhuman.customer.react.VirtualReactModule").getConstructor(ReactApplicationContext.class).newInstance(reactApplicationContext);
                if (newInstance instanceof ReactContextBaseJavaModule) {
                    createNativeModules.add((NativeModule) newInstance);
                }
            }
        } catch (Throwable unused) {
        }
        return createNativeModules;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactPackage, com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ViewManager> createViewManagers = super.createViewManagers(reactApplicationContext);
        createViewManagers.add(new JDReactPullToRefreshControlManger());
        createViewManagers.add(new JDReactWebViewManager());
        createViewManagers.add(new JDReactMemberCodeViewManager(reactApplicationContext));
        createViewManagers.add(new ChannelFollowViewManager());
        createViewManagers.add(new JDReactWebView2ViewManager());
        createViewManagers.add(new JDPureVideoManager());
        createViewManagers.add(new JDTaskViewManager());
        try {
            if (AuraConfig.isBundlePrepered(AuraBundleInfos.getBundleNameFromBundleId(105))) {
                Object newInstance = JdSdk.getInstance().getApplicationContext().getClassLoader().loadClass("com.jd.lib.virtualhuman.customer.react.VirtualReactViewManager").getConstructor(ReactApplicationContext.class).newInstance(reactApplicationContext);
                if (newInstance instanceof ViewManager) {
                    createViewManagers.add((ViewManager) newInstance);
                }
            }
        } catch (Throwable unused) {
        }
        return createViewManagers;
    }
}
