package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkJShopCustomHelper {
    private static final String TAG = "DeepLinkJShopCustomHelper";

    private static boolean isJShopCustomSwicthOpen() {
        return DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(27));
    }

    public static void startJShopCustomDetailPage(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_JSHOP_DYNAMIC_DETAIL).toString(), bundle);
    }

    public static void startJShopCustomLabelPage(Context context, Bundle bundle) {
        if (!isJShopCustomSwicthOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, " === bundle-jshopcustom aura switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, " === bundle-jshopcustom aura switch is open ");
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_JSHOP_LABEL_ACTIVITY).toString(), bundle);
    }

    public static void startJShopCustomMainPage(Context context, Bundle bundle) {
        if (!isJShopCustomSwicthOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, " === bundle-jshopcustom aura switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, " === bundle-jshopcustom aura switch is open ");
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_JSHOP_DYNA_FRAGMENT_ACTIVITY).toString(), bundle);
    }

    public static void startJShopCustomPraisedDynamicPage(Context context, Bundle bundle) {
        if (!isJShopCustomSwicthOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, " === bundle-jshopcustom aura switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, " === bundle-jshopcustom aura switch is open ");
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_JSHOP_PRAISED_DYNAMIC_ACTIVITY).toString(), bundle);
    }

    public static void startJShopDynamicSetPage(Context context, Bundle bundle) {
        if (!isJShopCustomSwicthOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, " === bundle-jshopcustom aura switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, " === bundle-jshopcustom aura switch is open ");
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_JSHOP_DYNAMIC_SET_ACTIVITY).toString(), bundle);
    }
}
