package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.app.mall.e;
import com.jingdong.app.mall.utils.CommonUtilEx;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeJumpController extends JDReactNativeJumpControllerListener {
    private static final String ACTIVITY_FLAG_ACTIVITY_CLEAR_TOP = "2";
    private static final String ACTIVITY_FLAG_NEW_TASK = "0";
    private static final String ACTIVITY_FLAG_SINGLETON = "1";
    public static final String JUMPCHANNEL = "com.jd.jdflutter/jump";
    private static final String JUMP_DATA_KEY_ACTIVITY_FLAG = "activity_flag";
    private static final String JUMP_DATA_KEY_IS_TOPBAR_GONE = "isTopBarGone";
    private static final String JUMP_DATA_KEY_WEB_URL = "web_url";
    private static final String TAG = "JDReactNativeJumpControllerModule";

    public static void injectIntentActivityFlag(Intent intent, HashMap hashMap) {
        if (intent == null) {
            return;
        }
        if (hashMap.containsKey(JUMP_DATA_KEY_ACTIVITY_FLAG) && !TextUtils.isEmpty((String) hashMap.get(JUMP_DATA_KEY_ACTIVITY_FLAG))) {
            String str = (String) hashMap.get(JUMP_DATA_KEY_ACTIVITY_FLAG);
            if ("0".equalsIgnoreCase(str)) {
                intent.setFlags(268435456);
                return;
            } else if ("1".equalsIgnoreCase(str)) {
                intent.setFlags(SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING);
                return;
            } else if ("2".equalsIgnoreCase(str)) {
                intent.setFlags(67108864);
                return;
            } else {
                intent.setFlags(268435456);
                return;
            }
        }
        intent.setFlags(268435456);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.JDReactNativeJumpControllerListener, com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public boolean jumpToWebPage(HashMap hashMap, Activity activity) {
        if (hashMap != null && hashMap.containsKey(JUMP_DATA_KEY_WEB_URL)) {
            boolean parseBoolean = hashMap.containsKey("isTopBarGone") ? Boolean.parseBoolean((String) hashMap.get("isTopBarGone")) : false;
            try {
                String str = (String) hashMap.get(JUMP_DATA_KEY_WEB_URL);
                Log.d(TAG, "web_url=" + str);
                Log.d(TAG, "isTopBarGone=" + parseBoolean);
                if (TextUtils.isEmpty(str)) {
                    return false;
                }
                URLParamMap uRLParamMap = new URLParamMap();
                if (!TextUtils.isEmpty(str)) {
                    uRLParamMap.put(RemoteMessageConst.TO, str);
                }
                if (activity == null) {
                    activity = JDReactHelper.newInstance().getCurrentMyActivity();
                }
                Intent intent = new Intent(activity, WebActivity.class);
                SerializableContainer serializableContainer = new SerializableContainer();
                serializableContainer.setMap(uRLParamMap);
                intent.putExtra("isTopBarGone", parseBoolean);
                intent.putExtra("urlParamMap", serializableContainer);
                intent.putExtra("urlAction", RemoteMessageConst.TO);
                injectIntentActivityFlag(intent, hashMap);
                if (activity != null) {
                    DeepLinkMHelper.startWebActivity(activity, intent.getExtras(), intent.getFlags());
                    return true;
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.listener.JDReactNativeJumpControllerListener, com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("jumpToWebPage")) {
            jumpToWebPage(hashMap, activity);
        } else if (str.equals("jumptoHomePage")) {
            toHomePage();
        } else if (str.equals("jump")) {
            jump(hashMap);
        } else if (str.equals("jumpToGameChargeActivity")) {
            jumpToGameChargeActivity(hashMap);
        } else if (str.equals("jumpToVirtualOrderDetail")) {
            jumpToVirtualOrderDetail(hashMap);
        } else if (str.equals("jumpToProductDetail")) {
            jumpToProductDetail(hashMap);
        } else if (str.equals("jumpToOpenapp")) {
            jumpToOpenapp(hashMap);
        } else if (str.equals("jumpToDeeplink")) {
            jumpToDeeplink(hashMap);
        } else if (str.equals("selectChargeCity")) {
            selectChargeCity(hashMap);
        } else if (str.equals("selectChargeCardCoupon")) {
            selectChargeCardCoupon(hashMap);
        } else if (str.equals("jumpToJShopHome")) {
            jumpToJShopHome(hashMap);
        } else if (str.equals("jumpToJShopSignUp")) {
            jumpToJShopSignUp(hashMap);
        } else if (str.equals("jumoToFavouritesActivity")) {
            jumoToFavouritesActivity(hashMap);
        } else if (str.equals("jumpPayVC")) {
            jumpPayVC(hashMap);
        } else if (str.equals("jumpRoute")) {
            jumpRoute(hashMap);
        } else if (str.equals("jumpToJump")) {
            jumpToJump(hashMap);
        } else if (str.equals("jumpJDRouter")) {
            jumpJDRouter(hashMap);
        } else if (str.equals("jumpParamJson")) {
            jumpParamJson(hashMap);
        } else if (str.equals("jumpParamJson")) {
            jumpToOpenappClearStackAndroid(hashMap);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.JDReactNativeJumpControllerListener, com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void toHomePage() {
        try {
            Log.d(TAG, "jump to toHomePage");
            if (e.b().a() != null) {
                CommonUtilEx.getInstance().gotoMainFrameClearAllTask(e.b().a());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
