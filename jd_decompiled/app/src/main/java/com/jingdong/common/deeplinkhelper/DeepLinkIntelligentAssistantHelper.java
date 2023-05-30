package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkIntelligentAssistantHelper {
    public static final String CURRENT_PRICE = "currentPrice";
    public static final String FROM = "from";
    public static final String FROM_KEY_AFTER_SALE = "afterSale";
    public static final String FROM_KEY_ARRIVAL_GOODS = "arrivalGoods";
    public static final String FROM_KEY_DEPRECIATE_NOTIFICATION = "depreciateNotification";
    public static final String FROM_KEY_FROM_SHARED = "fromShared";
    public static final String FROM_KEY_GUESSING_GAME = "fromGuessGame";
    public static final String FROM_KEY_HOME_PAGE = "homePage";
    public static final String FROM_KEY_LOGISTICS_DETAILS = "logisticsDetails";
    public static final String FROM_KEY_SEARCH = "search";
    public static final String ORDER_ID = "orderId";
    public static final String SKU = "sku";
    public static final String SUBSCIBE_STATE = "subscibeState";
    public static final byte SUBSCIBE_STATE_FAIL = 1;
    public static final byte SUBSCIBE_STATE_SUCCEED = 0;

    private DeepLinkIntelligentAssistantHelper() {
    }

    @Deprecated
    public static void afterSaleStartIntelligentAssistantActivity(IMyActivity iMyActivity, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("from", FROM_KEY_DEPRECIATE_NOTIFICATION);
        bundle.putString("orderId", str);
        bundle.putString("sku", str2);
        startIntelligentAssistantActivity(iMyActivity, bundle);
    }

    public static void arrivalGoodsStartIntelligentAssistantActivity(IMyActivity iMyActivity, byte b, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("from", FROM_KEY_ARRIVAL_GOODS);
        bundle.putInt(SUBSCIBE_STATE, b);
        bundle.putString("sku", str);
        startIntelligentAssistantActivity(iMyActivity, bundle);
    }

    public static void depreciateNotificationStartIntelligentAssistantActivity(IMyActivity iMyActivity, String str, String str2) {
        depreciateNotificationStartIntelligentAssistantActivity(iMyActivity, str, str2, 0);
    }

    public static void guessingGameStartIntelligentAssistantActivity(IMyActivity iMyActivity) {
        Bundle bundle = new Bundle();
        bundle.putString("from", FROM_KEY_GUESSING_GAME);
        startIntelligentAssistantActivity(iMyActivity, bundle);
    }

    public static void startChatBackgroundSettingActivity(final IMyActivity iMyActivity, final Bundle bundle) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkIntelligentAssistantHelper.2
            @Override // java.lang.Runnable
            public void run() {
                DeepLinkDispatch.startActivityDirect((Context) IMyActivity.this, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_CHAT_BACKGROUND_SETTING_ACTIVITY).toString(), bundle);
            }
        });
    }

    public static void startForResultChatBackgroundSettingActivity(final IMyActivity iMyActivity, final Bundle bundle, final int i2) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkIntelligentAssistantHelper.3
            @Override // java.lang.Runnable
            public void run() {
                DeepLinkDispatch.startActivityForResult((Activity) IMyActivity.this, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_CHAT_BACKGROUND_SETTING_ACTIVITY).toString(), bundle, i2);
            }
        });
    }

    public static void startForResultIntelligentAssistantActivity(final IMyActivity iMyActivity, final Bundle bundle, final int i2) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkIntelligentAssistantHelper.4
            @Override // java.lang.Runnable
            public void run() {
                DeepLinkDispatch.startActivityForResult((Activity) IMyActivity.this, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_INTELLIGENT_ASSISTANT).toString(), bundle, i2);
            }
        });
    }

    public static void startIntelligentAssistantActivity(IMyActivity iMyActivity, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("from", str);
        if (LoginUserBase.hasLogin()) {
            startIntelligentAssistantActivity(iMyActivity, bundle);
        } else {
            DeepLinkIntelligentAssistantLoginGuideHelper.startIntelligentAssistantLoginGuideActivity(iMyActivity, bundle);
        }
    }

    public static void depreciateNotificationStartIntelligentAssistantActivity(IMyActivity iMyActivity, String str, String str2, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("from", FROM_KEY_DEPRECIATE_NOTIFICATION);
        bundle.putString("sku", str);
        bundle.putString(CURRENT_PRICE, str2);
        startForResultIntelligentAssistantActivity(iMyActivity, bundle, i2);
    }

    public static void startIntelligentAssistantActivity(final IMyActivity iMyActivity, final Bundle bundle) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkIntelligentAssistantHelper.1
            @Override // java.lang.Runnable
            public void run() {
                DeepLinkDispatch.startActivityDirect((Context) IMyActivity.this, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_INTELLIGENT_ASSISTANT).toString(), bundle);
            }
        });
    }
}
