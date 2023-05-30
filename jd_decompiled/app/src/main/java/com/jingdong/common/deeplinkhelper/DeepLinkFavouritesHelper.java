package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkFavouritesHelper {
    public static final String CURRENT_TAB = "currentTab";
    public static final String HOST_FAVOURITES = "favourites";
    public static final String HOST_NOTIFY_PRICE = "notifyprice";
    public static final int PRODUCT = 0;
    public static final int SHOP = 1;
    public static boolean hasAddFavorite;
    public static boolean hasCacelFavorite;
    public static boolean isCartToFavorite;

    public static void launch(IMyActivity iMyActivity, String str, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putInt(CURRENT_TAB, i2);
        startFavouritesActivity(iMyActivity, bundle);
    }

    public static void launchFavouritActivity(IMyActivity iMyActivity, String str, int i2) {
        launch(iMyActivity, str, i2);
    }

    public static void startFavouritesActivity(final IMyActivity iMyActivity, final Bundle bundle) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkFavouritesHelper.1
            @Override // java.lang.Runnable
            public void run() {
                if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(8))) {
                    DeepLinkDispatch.startActivityDirect(IMyActivity.this.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkFavouritesHelper.HOST_FAVOURITES).toString(), bundle);
                } else if (OKLog.D) {
                    OKLog.d("DeepLinkFavouritesHelper", "\u6211\u7684\u5173\u6ce8\u5f00\u5173\u5173\u95ed\u4e86");
                }
            }
        });
    }

    public static void startFavouritesActivity2(Activity activity, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(8))) {
            DeepLinkDispatch.startActivityDirect(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_FAVOURITES).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkFavouritesHelper", "\u6211\u7684\u5173\u6ce8\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }

    public static void startNotifyPriceActivity(final IMyActivity iMyActivity, final Bundle bundle) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkFavouritesHelper.2
            @Override // java.lang.Runnable
            public void run() {
                if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(8))) {
                    DeepLinkDispatch.startActivityDirect(IMyActivity.this.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkFavouritesHelper.HOST_NOTIFY_PRICE).toString(), bundle);
                } else if (OKLog.D) {
                    OKLog.d("DeepLinkFavouritesHelper", "\u6211\u7684\u5173\u6ce8\u5f00\u5173\u5173\u95ed\u4e86");
                }
            }
        });
    }

    public static void startNotifyPriceActivity2(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(8))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_NOTIFY_PRICE).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkFavouritesHelper", "\u6211\u7684\u5173\u6ce8\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }

    public static void startNotifyPriceActivityForResult(IMyActivity iMyActivity, Bundle bundle, int i2) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(8))) {
            DeepLinkDispatch.startActivityForResult(iMyActivity.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(HOST_NOTIFY_PRICE).toString(), bundle, i2);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkFavouritesHelper", "\u6211\u7684\u5173\u6ce8\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }
}
