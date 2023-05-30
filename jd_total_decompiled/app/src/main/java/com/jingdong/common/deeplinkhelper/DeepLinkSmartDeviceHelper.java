package com.jingdong.common.deeplinkhelper;

import android.os.Bundle;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.ILogin;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkSmartDeviceHelper {
    private static final String HOST_DEVCIE_DETAIL = "wl_device_details";
    private static final String HOST_DEVCIE_MANAGER = "wl_manage_devices";
    private static final String HOST_HANDLER_QRCODE = "wl_handler_qrcode";
    private static final String HOST_MAIN_ACTIVTTY = "wl_mydeviceslist";
    private static final String HOST_MODELLIST = "wl_modellist";
    private static final String HOST_WAITADD_LIST = "wl_waitadd_list";

    private static void commonStartActivity(final IMyActivity iMyActivity, final Bundle bundle, String str) {
        DeepLinkLoginHelper.startLoginActivity(iMyActivity.getThisActivity(), null, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkSmartDeviceHelper.2
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str2) {
                if (DeepLinkSmartDeviceHelper.HOST_MODELLIST.equals(str2) || "wl_waitadd_list".equals(str2) || DeepLinkSmartDeviceHelper.HOST_DEVCIE_MANAGER.equals(str2) || DeepLinkSmartDeviceHelper.HOST_MAIN_ACTIVTTY.equals(str2) || "wl_handler_qrcode".equals(str2)) {
                    if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(43))) {
                        DeepLinkDispatch.startActivityDirect(IMyActivity.this.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(str2).toString(), bundle);
                        return;
                    }
                    OKLog.d("DeepLinkSmartDeviceHelper", "\u667a\u80fd\u8bbe\u5907\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
                }
            }
        }, str);
    }

    public static void startDeviceDetailActivity(final IMyActivity iMyActivity, final String str) {
        DeepLinkLoginHelper.startLoginActivity(iMyActivity.getThisActivity(), null, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkSmartDeviceHelper.1
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str2) {
                if ("wl_device_details".equals(str2)) {
                    if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(43))) {
                        Bundle bundle = new Bundle();
                        bundle.putString("feed_id", str);
                        bundle.putInt("type", 1);
                        DeepLinkDispatch.startActivityDirect(iMyActivity.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host("wl_device_details").toString(), bundle);
                        return;
                    }
                    OKLog.d("DeepLinkSmartDeviceHelper", "\u667a\u80fd\u8bbe\u5907\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
                }
            }
        }, "wl_device_details");
    }

    public static void startHanderQRCodeActivity(IMyActivity iMyActivity, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("qrString", bundle.getString("qrcodeString", ""));
        commonStartActivity(iMyActivity, bundle2, "wl_handler_qrcode");
    }

    public static void startManageDevicesActivity(IMyActivity iMyActivity, Bundle bundle) {
        commonStartActivity(iMyActivity, bundle, HOST_DEVCIE_MANAGER);
    }

    public static void startModelListActivity(IMyActivity iMyActivity, Bundle bundle) {
        commonStartActivity(iMyActivity, bundle, HOST_MODELLIST);
    }

    public static void startSmartDeviceActivity(IMyActivity iMyActivity, Bundle bundle) {
        commonStartActivity(iMyActivity, bundle, HOST_MAIN_ACTIVTTY);
    }

    public static void startWaitAddListActivity(IMyActivity iMyActivity, Bundle bundle) {
        commonStartActivity(iMyActivity, bundle, "wl_waitadd_list");
    }
}
