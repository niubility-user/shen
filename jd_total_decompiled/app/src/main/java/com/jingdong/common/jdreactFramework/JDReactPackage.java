package com.jingdong.common.jdreactFramework;

import android.os.Build;
import android.text.TextUtils;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.jingdong.common.jdreactExtension.listener.JDReactReminderListener;
import com.jingdong.common.jdreactExtension.modules.JDReactNativeAbilityUtilModule;
import com.jingdong.common.jdreactExtension.modules.JDReactReminderModule;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeAlbumPickerListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonPayListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeDeviceInfoListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeImagePickerListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeLBSListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeMapListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeMessageListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeMesssageEventListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeMtaReportListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeNetworkListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativePermissionListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeSetShareUrlListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeSharedDataListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeToastModuleListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeUploadExceptionListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeUserLoginListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeVerifyListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeVideoRecorderListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeVoiceRecognizeListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener;
import com.jingdong.common.jdreactFramework.modules.JDReactAppearanceModule;
import com.jingdong.common.jdreactFramework.modules.JDReactBiometricModule;
import com.jingdong.common.jdreactFramework.modules.JDReactCPUArchHelper;
import com.jingdong.common.jdreactFramework.modules.JDReactCookieModule;
import com.jingdong.common.jdreactFramework.modules.JDReactDebugModule;
import com.jingdong.common.jdreactFramework.modules.JDReactElderModeModule;
import com.jingdong.common.jdreactFramework.modules.JDReactImagePickerModule;
import com.jingdong.common.jdreactFramework.modules.JDReactLBSModule;
import com.jingdong.common.jdreactFramework.modules.JDReactLocationModule;
import com.jingdong.common.jdreactFramework.modules.JDReactMapModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeAlbumPickerModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeCameraPickerModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeCommonPayModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeCommonShareModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeCustomKeyboardModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeDatePickerModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeDeviceInfoModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeHelperModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeLoadingModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeMessageEventModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeMessageModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeMtaReportModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeMultiMediaModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeNetworkModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeNetworkWithSignModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeReminderModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeSetShareUrlModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeSharedDataModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeToastModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeUploadExceptionModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeUserLoginModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeVerifyMoudle;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeVoiceRecognizeModule;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeXViewControllerModule;
import com.jingdong.common.jdreactFramework.modules.JDReactPermissionModule;
import com.jingdong.common.jdreactFramework.modules.JDReactTextToSpeechModule;
import com.jingdong.common.jdreactFramework.views.JDLottieViewManager;
import com.jingdong.common.jdreactFramework.views.videoview.JDCustomIjkNewPlayerManager;
import com.jingdong.common.jdreactFramework.views.videoview.JDVideoPlayerManager;
import com.jingdong.common.jdreactFramework.views.webview.JDVideoViewManager;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdreact.plugin.audio.JDReactNativeAudioRecordListener;
import com.jingdong.jdreact.plugin.authority.JDReactNativeAuthorityModule;
import com.jingdong.jdreact.plugin.banner.JDCardbannerViewManager;
import com.jingdong.jdreact.plugin.banner.JDCustomCardbannerItemViewManager;
import com.jingdong.jdreact.plugin.banner.JDCustomCardbannerViewManager;
import com.jingdong.jdreact.plugin.banner.JDCustomCardbannerWithOutTouchViewManager;
import com.jingdong.jdreact.plugin.banner.JDCustomCardbannerWithOutTouchViewManager2;
import com.jingdong.jdreact.plugin.banner.JDCustomImageViewManager;
import com.jingdong.jdreact.plugin.banner.JDCustombannerViewManagerType1;
import com.jingdong.jdreact.plugin.download.JDReactDownloadManagerModule;
import com.jingdong.jdreact.plugin.fileUpload.JDReactNativeFileUploadListener;
import com.jingdong.jdreact.plugin.gradient.JDReactLinearGradientManager;
import com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager;
import com.jingdong.jdreact.plugin.language.RNI18nModule;
import com.jingdong.jdreact.plugin.map.JDReactMapCalloutManager;
import com.jingdong.jdreact.plugin.map.JDReactMapCircleManager;
import com.jingdong.jdreact.plugin.map.JDReactMapManager;
import com.jingdong.jdreact.plugin.map.JDReactMapManagerWith18;
import com.jingdong.jdreact.plugin.map.JDReactMapMarkerManager;
import com.jingdong.jdreact.plugin.map.JDReactMapPolygonManager;
import com.jingdong.jdreact.plugin.map.JDReactMapPolylineManager;
import com.jingdong.jdreact.plugin.map.JDReactMapUrlTileManager;
import com.jingdong.jdreact.plugin.network.JDReactNativeNetworkWithSignListener;
import com.jingdong.jdreact.plugin.network.LoginHelper;
import com.jingdong.jdreact.plugin.sensors.JDReactSensor;
import com.jingdong.jdreact.plugin.shadow.JDReactShadowViewManager;
import com.jingdong.jdreact.plugin.viewshot.JDReactViewShot;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.List;
import jd.wjlogin_sdk.common.WJLoginExtendProxy;

/* loaded from: classes5.dex */
public class JDReactPackage implements ReactPackage {
    private LoginHelper createLoginHelper() {
        new WJLoginExtendProxy() { // from class: com.jingdong.common.jdreactFramework.JDReactPackage.1
            {
                JDReactPackage.this = this;
            }

            @Override // jd.wjlogin_sdk.common.WJLoginExtendProxy
            public String getArea() {
                return CartConstant.KEY_YB_INFO_LINK;
            }

            @Override // jd.wjlogin_sdk.common.WJLoginExtendProxy
            public String getDeviceFinger() {
                return "";
            }

            @Override // jd.wjlogin_sdk.common.WJLoginExtendProxy
            public String getJMAFinger() {
                return "";
            }

            @Override // jd.wjlogin_sdk.common.WJLoginExtendProxy
            public String getUuid() {
                try {
                    TextUtils.isEmpty("");
                } catch (Throwable unused) {
                }
                return "";
            }
        };
        UserUtil.getWJLoginHelper();
        return new LoginHelper
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000e: RETURN 
              (wrap: com.jingdong.jdreact.plugin.network.LoginHelper : 0x000b: CONSTRUCTOR 
              (r2v0 'this' com.jingdong.common.jdreactFramework.JDReactPackage A[IMMUTABLE_TYPE, THIS])
              (r0 I:jd.wjlogin_sdk.common.WJLoginHelper A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.common.jdreactFramework.JDReactPackage, jd.wjlogin_sdk.common.WJLoginHelper):void (m), WRAPPED] (LINE:3) call: com.jingdong.common.jdreactFramework.JDReactPackage.2.<init>(com.jingdong.common.jdreactFramework.JDReactPackage, jd.wjlogin_sdk.common.WJLoginHelper):void type: CONSTRUCTOR)
             (LINE:3) in method: com.jingdong.common.jdreactFramework.JDReactPackage.createLoginHelper():com.jingdong.jdreact.plugin.network.LoginHelper, file: classes5.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            this = this;
            com.jingdong.common.jdreactFramework.JDReactPackage$1 r0 = new com.jingdong.common.jdreactFramework.JDReactPackage$1
            r0.<init>()
            jd.wjlogin_sdk.common.WJLoginHelper r0 = com.jingdong.common.utils.UserUtil.getWJLoginHelper()
            com.jingdong.common.jdreactFramework.JDReactPackage$2 r1 = new com.jingdong.common.jdreactFramework.JDReactPackage$2
            r1.<init>()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.JDReactPackage.createLoginHelper():com.jingdong.jdreact.plugin.network.LoginHelper");
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new JDReactNativeMessageEventModule(reactApplicationContext, new JDReactNativeMesssageEventListener()));
        arrayList.add(new JDReactNativeMtaReportModule(reactApplicationContext, new JDReactNativeMtaReportListener()));
        arrayList.add(new JDReactNativeUserLoginModule(reactApplicationContext, new JDReactNativeUserLoginListener()));
        arrayList.add(new JDReactNativeNetworkModule(reactApplicationContext, new JDReactNativeNetworkListener()));
        arrayList.add(new JDReactNativeCommonShareModule(reactApplicationContext, new JDReactNativeCommonShareListener()));
        arrayList.add(new JDReactNativeUploadExceptionModule(reactApplicationContext, new JDReactNativeUploadExceptionListener()));
        arrayList.add(new JDReactNativeXViewControllerModule(reactApplicationContext, new JDReactNativeXViewControllerListener()));
        arrayList.add(new JDReactNativeSharedDataModule(reactApplicationContext, new JDReactNativeSharedDataListener()));
        arrayList.add(new JDReactNativeSetShareUrlModule(reactApplicationContext, new JDReactNativeSetShareUrlListener()));
        arrayList.add(new JDReactNativeDeviceInfoModule(reactApplicationContext, new JDReactNativeDeviceInfoListener()));
        arrayList.add(new JDReactNativeToastModule(reactApplicationContext, new JDReactNativeToastModuleListener()));
        arrayList.add(new JDReactNativeCommonPayModule(reactApplicationContext, new JDReactNativeCommonPayListener()));
        arrayList.add(new JDReactNativeVerifyMoudle(reactApplicationContext, new JDReactNativeVerifyListener()));
        arrayList.add(new JDReactMapModule(reactApplicationContext, new JDReactNativeMapListener()));
        arrayList.add(new JDReactNativeVoiceRecognizeModule(reactApplicationContext, new JDReactNativeVoiceRecognizeListener()));
        arrayList.add(new JDReactLocationModule(reactApplicationContext));
        arrayList.add(new JDReactCookieModule(reactApplicationContext, new JDReactNativeCookieListener()));
        arrayList.add(new JDReactPermissionModule(reactApplicationContext, new JDReactNativePermissionListener()));
        arrayList.add(new JDReactNativeMessageModule(reactApplicationContext, new JDReactNativeMessageListener()));
        arrayList.add(new JDReactNativeReminderModule(reactApplicationContext, new JDReactNativeReminderListener()));
        arrayList.add(new JDReactNativeAbilityUtilModule(reactApplicationContext));
        arrayList.add(new JDReactImagePickerModule(reactApplicationContext, new JDReactNativeImagePickerListener()));
        arrayList.add(new JDReactNativeHelperModule(reactApplicationContext, new JDReactNativeHelperListener()));
        arrayList.add(new JDReactNativeDatePickerModule(reactApplicationContext, new JDReactNativeDatePickerListener()));
        arrayList.add(new JDReactNativeMultiMediaModule(reactApplicationContext, new JDReactNativeMultiMediaModuleListener(), new JDReactNativeFileUploadListener(), new JDReactNativeAudioRecordListener()));
        arrayList.add(new JDReactReminderModule(reactApplicationContext, new JDReactReminderListener()));
        arrayList.add(new RNI18nModule(reactApplicationContext));
        arrayList.add(new JDReactNativeAlbumPickerModule(reactApplicationContext, new JDReactNativeAlbumPickerListener()));
        arrayList.add(new JDReactNativeCameraPickerModule(reactApplicationContext, new JDReactNativeVideoRecorderListener()));
        arrayList.add(new JDReactNativeNetworkWithSignModule(reactApplicationContext, new JDReactNativeNetworkWithSignListener(createLoginHelper())));
        arrayList.add(new JDReactDownloadManagerModule(reactApplicationContext));
        arrayList.add(new JDReactNativeAuthorityModule(reactApplicationContext));
        arrayList.add(new JDReactViewShot(reactApplicationContext));
        arrayList.add(new JDReactNativeLoadingModule(reactApplicationContext));
        arrayList.add(new JDReactCPUArchHelper(reactApplicationContext));
        arrayList.add(new JDReactLBSModule(reactApplicationContext, new JDReactNativeLBSListener()));
        arrayList.add(new JDReactAppearanceModule(reactApplicationContext));
        arrayList.add(new JDReactElderModeModule(reactApplicationContext));
        arrayList.add(new JDReactTextToSpeechModule(reactApplicationContext));
        arrayList.add(new JDReactSensor(reactApplicationContext, "JDReactGyroscope", 4));
        arrayList.add(new JDReactSensor(reactApplicationContext, "JDReactAccelerometer", 1));
        arrayList.add(new JDReactSensor(reactApplicationContext, "JDReactMagnetometer", 2));
        arrayList.add(new JDReactSensor(reactApplicationContext, "JDReactBarometer", 6));
        arrayList.add(new JDReactSensor(reactApplicationContext, "JDReactOrientation", 11));
        arrayList.add(new JDReactDebugModule(reactApplicationContext));
        arrayList.add(new JDReactNativeCustomKeyboardModule(reactApplicationContext, new JDReactNativeCustomKeyboardModuleListener()));
        arrayList.add(new JDReactBiometricModule(reactApplicationContext));
        return arrayList;
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new JDVideoViewManager());
        arrayList.add(new JDCardbannerViewManager());
        arrayList.add(new JDCustomCardbannerViewManager());
        arrayList.add(new JDCustomCardbannerWithOutTouchViewManager());
        arrayList.add(new JDCustomCardbannerItemViewManager());
        arrayList.add(new JDCustombannerViewManagerType1());
        arrayList.add(new JDReactLinearGradientManager());
        arrayList.add(new JDVideoPlayerManager());
        arrayList.add(new JDCustomIjkNewPlayerManager());
        arrayList.add(new JDCustomCardbannerWithOutTouchViewManager2());
        arrayList.add(new JDCustomImageViewManager());
        if (Build.VERSION.SDK_INT >= 18 && JDReactHelper.newInstance().isAgreedPrivacy()) {
            arrayList.add(new JDReactMapManager(reactApplicationContext));
        } else {
            arrayList.add(new JDReactMapManagerWith18());
        }
        arrayList.add(new JDReactMapMarkerManager());
        arrayList.add(new JDReactMapCalloutManager());
        arrayList.add(new JDReactMapCircleManager(reactApplicationContext));
        arrayList.add(new JDReactMapPolylineManager(reactApplicationContext));
        arrayList.add(new JDReactMapPolygonManager(reactApplicationContext));
        arrayList.add(new JDReactMapUrlTileManager(reactApplicationContext));
        arrayList.add(new JDLottieViewManager());
        arrayList.add(new JDReactModalHostManager());
        arrayList.add(new JDReactShadowViewManager());
        return arrayList;
    }
}
