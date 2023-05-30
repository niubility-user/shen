package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Intent;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeJumpControllerListener {
    boolean back(Activity activity, HashMap hashMap);

    void jumoToFavouritesActivity(HashMap hashMap);

    void jump(HashMap hashMap);

    boolean jumpJDRouter(HashMap hashMap);

    void jumpJDRouterWithCallback(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void jumpParamJson(HashMap hashMap);

    boolean jumpPayVC(HashMap hashMap);

    boolean jumpRoute(HashMap hashMap);

    void jumpToDeeplink(HashMap hashMap);

    void jumpToGameChargeActivity(HashMap hashMap);

    void jumpToJShopHome(HashMap hashMap);

    void jumpToJShopSignUp(HashMap hashMap);

    boolean jumpToJump(HashMap hashMap);

    void jumpToMiniProgram(Activity activity, HashMap<String, Object> hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void jumpToOpenapp(HashMap hashMap);

    void jumpToOpenappClearStackAndroid(HashMap hashMap);

    void jumpToProductDetail(HashMap hashMap);

    void jumpToSelectAddress(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void jumpToVirtualOrderDetail(HashMap hashMap);

    boolean jumpToWebPage(HashMap hashMap, Activity activity);

    void onActivityResult(Activity activity, int i2, int i3, Intent intent);

    void onCatalystInstanceDestroy();

    void selectChargeCardCoupon(HashMap hashMap);

    void selectChargeCity(HashMap hashMap);

    void toHomePage();
}
