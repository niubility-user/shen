package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.jd.framework.json.JDJSON;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.deeplinkhelper.DeepLinkChargeHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkFavouritesHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.UserAddress;
import com.jingdong.common.entity.UserInfo;
import com.jingdong.common.hybrid.utils.StartHybridActivityUtils;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.ConvertUtils;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactMessageUtils;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.utils.JsonParser;
import com.jingdong.common.utils.WeixinUtil;
import com.jingdong.common.utils.pay.PayUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDReactNativeJumpControllerListener implements NativeJumpControllerListener, JDFlutterCall, JDReactOnActivityResultCallBack {
    private static final String ACTIVITY_FLAG_ACTIVITY_CLEAR_TOP = "2";
    private static final String ACTIVITY_FLAG_NEW_TASK = "0";
    private static final String ACTIVITY_FLAG_SINGLETON = "1";
    public static final int FLAG_FOLLOW_CHARGE = 87;
    public static final int FLAG_GAME_CHARGE = 34;
    public static final int FLAG_MOVIE_ORDER = 43;
    public static final int FLAG_PHONE_CHARGE = 37;
    public static final int FLAG_TRAVEL_EX = 83;
    public static final int FLAG_TRAVEL_IN = 35;
    public static final String JUMPCHANNEL = "com.jd.jdflutter/nativejump";
    private static final String JUMP_DATA_KEY_ACTIVITY_FLAG = "activity_flag";
    private static final String JUMP_DATA_KEY_CLASSNAME = "class_name";
    private static final String JUMP_DATA_KEY_DEEPLINK_URL = "deeplink_url";
    private static final String JUMP_DATA_KEY_EXTRA = "data_extra_value";
    private static final String JUMP_DATA_KEY_EXTRA_KEY = "data_extra_key";
    private static final String JUMP_DATA_KEY_FORRESULT = "for_result";
    private static final String JUMP_DATA_KEY_IS_TOPBAR_GONE = "isTopBarGone";
    private static final String JUMP_DATA_KEY_OPENAPP_URL = "openapp_url";
    private static final String JUMP_DATA_KEY_PACKAGENAME = "package_name";
    private static final String JUMP_DATA_KEY_PARAMS_JSON = "params_json";
    private static final String JUMP_DATA_KEY_PARAMS_KEY = "params_key";
    private static final String JUMP_DATA_KEY_WEB_URL = "web_url";
    public static final int REQUEST_CODE_SELECT_ADDRESSLIST = 20000;
    public static final int RESULT_CODE_SELECT_ADDRESSLIST = 1000;
    private static final String TAG = "JDReactNativeJumpControllerModule";
    private LocalBroadcastManager localBroadcastManager;
    private WeixinReceiver weixinReceiver;
    public HashMap<String, WritableMap> mHashMap = new HashMap<>();
    public HashMap<String, WritableArray> mHashArray = new HashMap<>();

    /* loaded from: classes5.dex */
    public static class WeixinReceiver extends BroadcastReceiver {
        public static final String WX_MINIPROGRAM_CALLBACK_ACTION = "com.jd.wxMiniProgramAction";
        private JDCallback errorCB;
        private WeakReference<JDReactNativeJumpControllerListener> listener;
        private JDCallback successCB;

        public WeixinReceiver(JDCallback jDCallback, JDCallback jDCallback2, JDReactNativeJumpControllerListener jDReactNativeJumpControllerListener) {
            this.successCB = jDCallback;
            this.errorCB = jDCallback2;
            this.listener = new WeakReference<>(jDReactNativeJumpControllerListener);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (this.successCB != null) {
                this.successCB.invoke(intent.getStringExtra("extraData"));
            } else {
                JDCallback jDCallback = this.errorCB;
                if (jDCallback != null) {
                    jDCallback.invoke("successCB is null");
                }
            }
            WeakReference<JDReactNativeJumpControllerListener> weakReference = this.listener;
            if (weakReference != null) {
                weakReference.get();
                this.listener.get().unRegisterWeixinReceiver();
            }
        }
    }

    private WritableMap convertUserInfoToWritableMap(UserInfo userInfo) {
        WritableMap createMap = Arguments.createMap();
        if (userInfo != null && userInfo.getUserAddress() != null) {
            UserAddress userAddress = userInfo.getUserAddress();
            String str = userAddress.name;
            if (str != null) {
                createMap.putString("Name", str);
            }
            String str2 = userAddress.mobileReal;
            if (str2 != null) {
                createMap.putString("MobileReal", str2);
            }
            String str3 = userAddress.mobile;
            if (str3 != null) {
                createMap.putString("Mobile", str3);
            }
            String str4 = userAddress.ProvinceName;
            if (str4 != null) {
                createMap.putString("ProvinceName", str4);
            }
            String str5 = userAddress.CityName;
            if (str5 != null) {
                createMap.putString("CityName", str5);
            }
            String str6 = userAddress.CountryName;
            if (str6 != null) {
                createMap.putString("CountryName", str6);
            }
            String str7 = userAddress.TownName;
            if (str7 != null) {
                createMap.putString("TownName", str7);
            }
            String str8 = userAddress.addressDetail;
            if (str8 != null) {
                createMap.putString("addressDetail", str8);
            }
            String str9 = userAddress.where;
            if (str9 != null) {
                createMap.putString("Where", str9);
            }
            createMap.putString("latitudeString", String.valueOf(userAddress.latitudeDB));
            createMap.putString("longitudeString", String.valueOf(userAddress.longitudeDB));
            if (userAddress.addressTagMap != null) {
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putString("addressTagId", String.valueOf(userAddress.addressTagMap.addressTagId));
                createMap2.putString("addressTagType", String.valueOf(userAddress.addressTagMap.addressTagType));
                createMap2.putString("addressTagName", userAddress.addressTagMap.addressTagName);
                createMap.putMap("addressTagMap", createMap2);
            }
            Integer num = userAddress.idProvince;
            if (num != null) {
                createMap.putString("IdProvince", String.valueOf(num));
            }
            createMap.putString("IdCity", String.valueOf(userAddress.idCity));
            createMap.putString("IdArea", String.valueOf(userAddress.idArea));
            createMap.putString("IdTown", String.valueOf(userAddress.idTown));
            createMap.putString("areaCode", String.valueOf(userAddress.areaCode));
        }
        return createMap;
    }

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

    private void registerWeixinReceiver(JDCallback jDCallback, JDCallback jDCallback2) {
        if (this.localBroadcastManager == null) {
            this.localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getInstance().getBaseContext());
        }
        if (this.weixinReceiver == null) {
            this.weixinReceiver = new WeixinReceiver(jDCallback, jDCallback2, this);
            this.localBroadcastManager.registerReceiver(this.weixinReceiver, new IntentFilter("com.jd.wxMiniProgramAction"));
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public boolean back(Activity activity, HashMap hashMap) {
        if (activity == null) {
            return false;
        }
        activity.finish();
        return true;
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public WritableMap getState(String str) {
        return this.mHashMap.get(str);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public WritableArray getStateArray(String str) {
        return this.mHashArray.get(str);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void jumoToFavouritesActivity(HashMap hashMap) {
        String str;
        str = "";
        int i2 = 1;
        try {
            str = hashMap.containsKey("title") ? (String) hashMap.get("title") : "";
            if (hashMap.containsKey(NotificationMessageSummary.TAB)) {
                i2 = (int) ((Double) hashMap.get(NotificationMessageSummary.TAB)).doubleValue();
            }
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
        }
        Log.d(TAG, "jumoToFavouritesActivity()... title = " + str + ", tab = " + i2);
        DeepLinkFavouritesHelper.launch((BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity(), str, i2);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void jump(HashMap hashMap) {
        String str;
        String str2;
        Intent intent;
        if (hashMap == null || !hashMap.containsKey(JUMP_DATA_KEY_CLASSNAME)) {
            return;
        }
        String str3 = (String) hashMap.get(JUMP_DATA_KEY_CLASSNAME);
        String str4 = hashMap.containsKey("package_name") ? (String) hashMap.get("package_name") : null;
        if (hashMap.containsKey(JUMP_DATA_KEY_EXTRA_KEY)) {
            str = (String) hashMap.get(JUMP_DATA_KEY_EXTRA_KEY);
            str2 = (String) hashMap.get(JUMP_DATA_KEY_EXTRA);
        } else {
            str = null;
            str2 = null;
        }
        String str5 = hashMap.containsKey(JUMP_DATA_KEY_PARAMS_KEY) ? (String) hashMap.get(JUMP_DATA_KEY_PARAMS_KEY) : null;
        String str6 = hashMap.containsKey(JUMP_DATA_KEY_PARAMS_JSON) ? (String) hashMap.get(JUMP_DATA_KEY_PARAMS_JSON) : null;
        Log.d(TAG, "class_name=" + str3 + ",params_key=" + str5 + ",params_json=" + str6 + ",activity_flag=" + (hashMap.containsKey(JUMP_DATA_KEY_ACTIVITY_FLAG) ? (String) hashMap.get(JUMP_DATA_KEY_ACTIVITY_FLAG) : null));
        try {
            if (!TextUtils.isEmpty(str4)) {
                intent = new Intent();
                intent.setComponent(new ComponentName(str4, str3));
            } else {
                intent = new Intent(JdSdk.getInstance().getApplication(), Class.forName(str3));
            }
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra(str, str2);
            }
            Bundle bundle = new Bundle();
            bundle.putString(str5, str6);
            intent.putExtras(bundle);
            injectIntentActivityFlag(intent, hashMap);
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() != null) {
                AbstractJDReactInitialHelper.getCurrentMyActivity().startActivity(intent);
            }
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public boolean jumpJDRouter(HashMap hashMap) {
        HashMap hashMap2;
        int doubleValue;
        int doubleValue2;
        if (hashMap == null) {
            return false;
        }
        try {
            if (hashMap.containsKey("url")) {
                JDRouter build = JDRouter.build(AbstractJDReactInitialHelper.getCurrentMyActivity(), (String) hashMap.get("url"));
                if (hashMap.containsKey("requestCode") && (doubleValue2 = (int) ((Double) hashMap.get("requestCode")).doubleValue()) != 0) {
                    build.requestCode(doubleValue2);
                }
                if (hashMap.containsKey(RouterEntry.EXTRA_INTENTFLAG) && (doubleValue = (int) ((Double) hashMap.get(RouterEntry.EXTRA_INTENTFLAG)).doubleValue()) != 0) {
                    build.intentFlag(doubleValue);
                }
                if (hashMap.containsKey("extraObject") && (hashMap2 = (HashMap) hashMap.get("extraObject")) != null) {
                    String str = (String) hashMap2.get("key");
                    Bundle bundleFromJson = JumpUtil.getBundleFromJson(JsonParser.parseParamsJsonFromString((String) hashMap2.get("json")));
                    if (bundleFromJson != null) {
                        build.extraObject(str, bundleFromJson);
                    }
                }
                build.open();
                return true;
            }
            return false;
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
            return false;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void jumpJDRouterWithCallback(HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        HashMap hashMap2;
        int doubleValue;
        int doubleValue2;
        if (hashMap == null) {
            try {
                jDCallback2.invoke(new Object[0]);
            } catch (Exception e2) {
                Log.e(TAG, e2.toString());
                jDCallback2.invoke(new Object[0]);
                return;
            }
        }
        String str = "";
        if (hashMap.containsKey("url")) {
            str = (String) hashMap.get("url");
        } else {
            jDCallback2.invoke(new Object[0]);
        }
        JDRouter build = JDRouter.build(AbstractJDReactInitialHelper.getCurrentMyActivity(), str);
        if (hashMap.containsKey("requestCode") && (doubleValue2 = (int) ((Double) hashMap.get("requestCode")).doubleValue()) != 0) {
            build.requestCode(doubleValue2);
        }
        if (hashMap.containsKey(RouterEntry.EXTRA_INTENTFLAG) && (doubleValue = (int) ((Double) hashMap.get(RouterEntry.EXTRA_INTENTFLAG)).doubleValue()) != 0) {
            build.intentFlag(doubleValue);
        }
        if (hashMap.containsKey("extraObject") && (hashMap2 = (HashMap) hashMap.get("extraObject")) != null) {
            String str2 = (String) hashMap2.get("key");
            Bundle bundleFromJson = JumpUtil.getBundleFromJson(JsonParser.parseParamsJsonFromString((String) hashMap2.get("json")));
            if (bundleFromJson != null) {
                build.extraObject(str2, bundleFromJson);
            }
        }
        build.callBackListener(new CallBackListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeJumpControllerListener.1
            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onComplete() {
                JDCallback jDCallback3 = jDCallback;
                if (jDCallback3 != null) {
                    jDCallback3.invoke(new Object[0]);
                }
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onError(int i2) {
                JDCallback jDCallback3 = jDCallback2;
                if (jDCallback3 != null) {
                    jDCallback3.invoke(Integer.valueOf(i2));
                }
            }
        });
        build.callBackListener(new CallBackWithReturnListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeJumpControllerListener.2
            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onComplete() {
                JDCallback jDCallback3 = jDCallback;
                if (jDCallback3 != null) {
                    jDCallback3.invoke(new Object[0]);
                }
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onError(int i2) {
                JDCallback jDCallback3 = jDCallback2;
                if (jDCallback3 != null) {
                    jDCallback3.invoke(Integer.valueOf(i2));
                }
            }

            @Override // com.jingdong.common.unification.router.CallBackWithReturnListener
            public void onComplete(Object obj) {
                if (!(obj instanceof String) && !(obj instanceof Boolean)) {
                    if (obj instanceof Number) {
                        if (obj instanceof Integer) {
                            JDCallback jDCallback3 = jDCallback;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke((Integer) obj);
                                return;
                            }
                            return;
                        }
                        JDCallback jDCallback4 = jDCallback;
                        if (jDCallback4 != null) {
                            jDCallback4.invoke(Double.valueOf(((Number) obj).doubleValue()));
                            return;
                        }
                        return;
                    }
                    JDCallback jDCallback5 = jDCallback;
                    if (jDCallback5 != null) {
                        jDCallback5.invoke(new Object[0]);
                        return;
                    }
                    return;
                }
                JDCallback jDCallback6 = jDCallback;
                if (jDCallback6 != null) {
                    jDCallback6.invoke(obj);
                }
            }
        });
        build.open();
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void jumpParamJson(HashMap hashMap) {
        if (hashMap == null || !hashMap.containsKey(JUMP_DATA_KEY_CLASSNAME)) {
            return;
        }
        String str = (String) hashMap.get(JUMP_DATA_KEY_CLASSNAME);
        String str2 = hashMap.containsKey(JUMP_DATA_KEY_PARAMS_JSON) ? (String) hashMap.get(JUMP_DATA_KEY_PARAMS_JSON) : "";
        try {
            Intent intent = new Intent(JdSdk.getInstance().getApplication(), Class.forName(str));
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject(str2);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.getString(next));
            }
            intent.putExtras(bundle);
            injectIntentActivityFlag(intent, hashMap);
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() != null) {
                AbstractJDReactInitialHelper.getCurrentMyActivity().startActivity(intent);
            }
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public boolean jumpPayVC(HashMap hashMap) {
        if (hashMap != null && !TextUtils.isEmpty((String) hashMap.get(JUMP_DATA_KEY_WEB_URL))) {
            try {
                PayUtils.doPayWithWebURL(AbstractJDReactInitialHelper.getCurrentMyActivity(), (String) hashMap.get(JUMP_DATA_KEY_WEB_URL), "");
                return true;
            } catch (Exception e2) {
                Log.e(TAG, e2.toString());
            }
        }
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public boolean jumpRoute(HashMap hashMap) {
        try {
            JumpUtil.execJump(JdSdk.getInstance().getApplication(), (JumpEntity) JDJSON.parseObject(hashMap.toString(), JumpEntity.class), (int) ((Double) hashMap.get("jumpFrom")).doubleValue());
            return true;
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
            return false;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void jumpToDeeplink(HashMap hashMap) {
        Object obj;
        if (hashMap == null || !hashMap.containsKey(JUMP_DATA_KEY_DEEPLINK_URL)) {
            return;
        }
        try {
            String str = (String) hashMap.get(JUMP_DATA_KEY_DEEPLINK_URL);
            if (str == null) {
                return;
            }
            String str2 = hashMap.containsKey(JUMP_DATA_KEY_PARAMS_KEY) ? (String) hashMap.get(JUMP_DATA_KEY_PARAMS_KEY) : null;
            String str3 = hashMap.containsKey(JUMP_DATA_KEY_PARAMS_JSON) ? (String) hashMap.get(JUMP_DATA_KEY_PARAMS_JSON) : null;
            Set<String> keySet = hashMap.keySet();
            Bundle bundle = new Bundle();
            bundle.putString(str2, str3);
            if (keySet != null) {
                for (String str4 : keySet) {
                    if (!JUMP_DATA_KEY_PARAMS_JSON.equals(str4) && !JUMP_DATA_KEY_PARAMS_KEY.equals(str4) && (obj = hashMap.get(str4)) != null) {
                        if (obj instanceof Boolean) {
                            bundle.putBoolean(str4, ((Boolean) obj).booleanValue());
                        } else if (obj instanceof String) {
                            bundle.putString(str4, String.valueOf(obj));
                        } else if (obj instanceof Byte) {
                            bundle.putByte(str4, ((Byte) obj).byteValue());
                        } else if (obj instanceof Float) {
                            bundle.putFloat(str4, ((Float) obj).floatValue());
                        } else if (obj instanceof Integer) {
                            bundle.putInt(str4, ((Integer) obj).intValue());
                        } else if (obj instanceof Short) {
                            bundle.putShort(str4, ((Short) obj).shortValue());
                        } else if (obj instanceof Double) {
                            bundle.putDouble(str4, ((Double) obj).doubleValue());
                        } else if (obj instanceof Long) {
                            bundle.putLong(str4, ((Long) obj).longValue());
                        } else if (obj instanceof Map) {
                            bundle.putBundle(str4, ConvertUtils.paseMaptoBundle((Map) obj));
                        } else {
                            bundle.putString(str4, obj.toString());
                        }
                    }
                }
            }
            DeepLinkDispatch.startActivityDirect(JdSdk.getInstance().getApplication(), new DeepLinkUri.Builder().scheme("jingdong").host(str).toString(), bundle);
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void jumpToGameChargeActivity(HashMap hashMap) {
        try {
            String str = hashMap.containsKey("tabIndex") ? (String) hashMap.get("tabIndex") : "";
            if ("tap_flow".equals(str)) {
                DeepLinkChargeHelper.startFlowChargeActivity(JdSdk.getInstance().getApplicationContext());
            } else if ("tap_qq".equals(str)) {
                DeepLinkChargeHelper.startQQChargeActivity(JdSdk.getInstance().getApplicationContext());
            } else if ("tap_game".equals(str)) {
                DeepLinkChargeHelper.startGameChargeActivity(JdSdk.getInstance().getApplicationContext());
            } else if ("tap_life".equals(str)) {
                DeepLinkChargeHelper.startLifeChargeActivity(JdSdk.getInstance().getApplicationContext());
            } else {
                DeepLinkChargeHelper.startPhoneChargeActivity(JdSdk.getInstance().getApplicationContext());
            }
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0060  */
    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void jumpToJShopHome(HashMap hashMap) {
        String str;
        String str2;
        String str3;
        String str4 = "";
        try {
            str3 = hashMap.containsKey("shopId") ? (String) hashMap.get("shopId") : "";
            try {
                str2 = hashMap.containsKey("venderId") ? (String) hashMap.get("venderId") : "";
                try {
                    str = hashMap.containsKey("shopName") ? (String) hashMap.get("shopName") : "";
                    try {
                        if (hashMap.containsKey("targetTab")) {
                            str4 = (String) hashMap.get("targetTab");
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.e(TAG, e.toString());
                        String str5 = str;
                        String str6 = str2;
                        String str7 = str3;
                        String str8 = str4;
                        if (Log.D) {
                        }
                        DeepLinkJShopHomeHelper.gotoJShopHome(JdSdk.getInstance().getApplication(), str7, str6, str5, str8, null);
                    }
                } catch (Exception e3) {
                    e = e3;
                    str = "";
                }
            } catch (Exception e4) {
                e = e4;
                str = "";
                str2 = str;
            }
        } catch (Exception e5) {
            e = e5;
            str = "";
            str2 = str;
            str3 = str2;
        }
        String str52 = str;
        String str62 = str2;
        String str72 = str3;
        String str82 = str4;
        if (Log.D) {
            Log.d(TAG, "shopId: " + str72 + ", venderId: " + str62 + ", shopName: " + str52 + ", targetTab: " + str82);
        }
        DeepLinkJShopHomeHelper.gotoJShopHome(JdSdk.getInstance().getApplication(), str72, str62, str52, str82, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0088  */
    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void jumpToJShopSignUp(HashMap hashMap) {
        String str;
        Exception exc;
        String str2;
        String str3;
        int i2;
        String str4;
        int i3;
        String str5;
        String str6;
        boolean z;
        str = "";
        try {
            String str7 = hashMap.containsKey("shopId") ? (String) hashMap.get("shopId") : "";
            try {
                str3 = hashMap.containsKey("venderId") ? (String) hashMap.get("venderId") : "";
                try {
                    str = hashMap.containsKey("shopName") ? (String) hashMap.get("shopName") : "";
                    i2 = hashMap.containsKey("signType") ? (int) ((Double) hashMap.get("signType")).doubleValue() : 0;
                    try {
                        i3 = i2;
                        str5 = str3;
                        str6 = str7;
                        str4 = str;
                        z = hashMap.containsKey(JshopConst.FOLLOWED_KEY) ? ((Boolean) hashMap.get(JshopConst.FOLLOWED_KEY)).booleanValue() : false;
                    } catch (Exception e2) {
                        exc = e2;
                        str2 = str;
                        str = str7;
                        Log.e(TAG, exc.toString());
                        str4 = str2;
                        i3 = i2;
                        str5 = str3;
                        str6 = str;
                        z = false;
                        if (Log.D) {
                        }
                        DeepLinkJShopHomeHelper.gotoJShopSignUp(JdSdk.getInstance().getApplication(), str6, str5, str4, i3, "", z);
                    }
                } catch (Exception e3) {
                    exc = e3;
                    str2 = str;
                    i2 = 0;
                    str = str7;
                    Log.e(TAG, exc.toString());
                    str4 = str2;
                    i3 = i2;
                    str5 = str3;
                    str6 = str;
                    z = false;
                    if (Log.D) {
                    }
                    DeepLinkJShopHomeHelper.gotoJShopSignUp(JdSdk.getInstance().getApplication(), str6, str5, str4, i3, "", z);
                }
            } catch (Exception e4) {
                exc = e4;
                str2 = "";
                str3 = str2;
            }
        } catch (Exception e5) {
            exc = e5;
            str2 = "";
            str3 = str2;
            i2 = 0;
        }
        if (Log.D) {
            Log.d(TAG, "shopId: " + str6 + ", venderId: " + str5 + ", shopName: " + str4 + ", signType: " + i3 + ", isFollowed: " + z);
        }
        DeepLinkJShopHomeHelper.gotoJShopSignUp(JdSdk.getInstance().getApplication(), str6, str5, str4, i3, "", z);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public boolean jumpToJump(HashMap hashMap) {
        if (hashMap != null && hashMap.containsKey("jump")) {
            String str = (String) hashMap.get("jump");
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                JumpUtil.execJump(AbstractJDReactInitialHelper.getCurrentMyActivity(), (JumpEntity) JDJSON.parseObject(str, JumpEntity.class), (int) (hashMap.containsKey("jumpFrom") ? ((Double) hashMap.get("jumpFrom")).doubleValue() : -1.0d));
                return true;
            } catch (Exception e2) {
                Log.e(TAG, e2.toString());
                return false;
            }
        }
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void jumpToMiniProgram(Activity activity, HashMap<String, Object> hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (activity == null) {
            activity = AbstractJDReactInitialHelper.getCurrentMyActivity();
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(activity, "wxe75a2e68877315fb");
        if (!createWXAPI.isWXAppInstalled()) {
            if (jDCallback != null) {
                writableNativeMap.putString("code", "-2");
                writableNativeMap.putString("message", "weChat not found");
                jDCallback.invoke(writableNativeMap);
            }
        } else if (!WeixinUtil.isWXAppSupportAPI()) {
            if (jDCallback != null) {
                writableNativeMap.putString("code", "-1");
                writableNativeMap.putString("message", "update weChat");
                jDCallback.invoke(writableNativeMap);
            }
        } else {
            WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
            req.userName = (String) hashMap.get(MobileCertConstants.USERNAME);
            req.path = (String) hashMap.get("path");
            String str = (String) hashMap.get("miniProgramType");
            if (!TextUtils.isEmpty(str)) {
                try {
                    req.miniprogramType = Integer.valueOf(str).intValue();
                } catch (Exception e2) {
                    req.miniprogramType = 0;
                    JLog.e(TAG, e2.getMessage());
                }
            } else {
                req.miniprogramType = 0;
            }
            if (Boolean.valueOf(createWXAPI.sendReq(req)).booleanValue()) {
                if (jDCallback != null) {
                    writableNativeMap.putString("code", "0");
                    writableNativeMap.putString("message", "success");
                    jDCallback.invoke(writableNativeMap);
                }
            } else if (jDCallback != null) {
                writableNativeMap.putString("code", "-3");
                writableNativeMap.putString("message", "send request failed");
                jDCallback.invoke(writableNativeMap);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void jumpToOpenapp(HashMap hashMap) {
        toOpenapp(hashMap);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void jumpToOpenappClearStackAndroid(HashMap hashMap) {
        toHomePage();
        try {
            Thread.sleep(200L);
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
        }
        toOpenapp(hashMap);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void jumpToProductDetail(HashMap hashMap) {
        try {
            String str = hashMap.containsKey("wareId") ? (String) hashMap.get("wareId") : null;
            String str2 = hashMap.containsKey("title") ? (String) hashMap.get("title") : null;
            String str3 = hashMap.containsKey("image") ? (String) hashMap.get("image") : null;
            String str4 = hashMap.containsKey("price") ? (String) hashMap.get("price") : null;
            String str5 = hashMap.containsKey("targetUrl") ? (String) hashMap.get("targetUrl") : null;
            Log.d(TAG, "jump to jumpToProductDetail wareId =" + str);
            DeeplinkProductDetailHelper.startProductDetail(AbstractJDReactInitialHelper.getCurrentMyActivity(), Long.valueOf(Long.parseLong(str)).longValue(), str2, str3, str4, str5, null);
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void jumpToSelectAddress(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap != null && hashMap.containsKey(JUMP_DATA_KEY_DEEPLINK_URL)) {
            try {
                if (((String) hashMap.get(JUMP_DATA_KEY_DEEPLINK_URL)) == null) {
                    jDCallback2.invoke(new Object[0]);
                    return;
                }
                Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
                if (currentMyActivity != null && (currentMyActivity instanceof BaseActivity)) {
                    ReactMessageUtils.startSelectAddressList((BaseActivity) currentMyActivity, ConvertUtils.mapToBundle(hashMap), this, 20000, jDCallback, jDCallback2);
                    return;
                }
                jDCallback2.invoke(new Object[0]);
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                jDCallback2.invoke(new Object[0]);
                return;
            }
        }
        jDCallback2.invoke(new Object[0]);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void jumpToVirtualOrderDetail(HashMap hashMap) {
        int i2;
        String str = hashMap.containsKey("orderId") ? (String) hashMap.get("orderId") : null;
        String str2 = hashMap.containsKey("orderType") ? (String) hashMap.get("orderType") : null;
        Log.d(TAG, "jumpToActivity() -- 11>> orderType = " + str2);
        try {
            i2 = Integer.parseInt(str2);
        } catch (Exception unused) {
            i2 = 0;
        }
        BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
        if (i2 == 34) {
            if (baseActivity != null) {
                Bundle bundle = new Bundle();
                bundle.putString("orderId", str);
                DeepLinkCommonHelper.startActivityDirect(baseActivity, DeepLinkCommonHelper.HOST_GAMECHARGEDETAIL_ACTIVITY, bundle);
            }
        } else if (i2 == 35) {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.putExtra("pluginname", "flightDetail");
            intent.putExtra("orderId", str);
            intent.putExtra("type", "1");
            intent.putExtra("pauseBackRefresh", "yes");
            StartHybridActivityUtils.startHybridActivity(JdSdk.getInstance().getApplication(), intent);
        } else if (i2 == 37) {
            if (baseActivity != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("orderId", str);
                DeepLinkCommonHelper.startActivityDirect(baseActivity, DeepLinkCommonHelper.HOST_PHONECHARGEORDERDETAIL_ACTIVITY, bundle2);
            }
        } else if (i2 != 83) {
            if (i2 == 87 && baseActivity != null) {
                Bundle bundle3 = new Bundle();
                bundle3.putLong("orderId", Long.valueOf(Long.parseLong(str)).longValue());
                DeepLinkCommonHelper.startActivityDirect(baseActivity, DeepLinkCommonHelper.HOST_PHONECHARGEFLOWORDERDETAIL_ACTIVITY, bundle3);
            }
        } else {
            Intent intent2 = new Intent();
            intent2.addFlags(268435456);
            intent2.putExtra("pluginname", "flightDetail");
            intent2.putExtra("orderId", str);
            intent2.putExtra("type", "2");
            intent2.putExtra("pauseBackRefresh", "yes");
            StartHybridActivityUtils.startHybridActivity(JdSdk.getInstance().getApplication(), intent2);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public boolean jumpToWebPage(HashMap hashMap, Activity activity) {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        if (i2 != 20000) {
            return;
        }
        if (1000 == i3 && intent != null && intent.getParcelableExtra(UserInfo.class.getSimpleName()) != null) {
            saveState(String.valueOf(20000), convertUserInfoToWritableMap((UserInfo) intent.getParcelableExtra(UserInfo.class.getSimpleName())));
        } else {
            saveState(String.valueOf(20000), null);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void onCatalystInstanceDestroy() {
        unRegisterWeixinReceiver();
    }

    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if ("jump".equals(str)) {
            jump(hashMap);
            jDFlutterCallResult.success("");
        } else if ("jumpJDRouterWithCallback".equals(str)) {
            jumpJDRouterWithCallback(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeJumpControllerListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    if (objArr != null) {
                        if (objArr.length >= 1) {
                            jDFlutterCallResult.success(objArr[0].toString());
                        } else {
                            jDFlutterCallResult.success("");
                        }
                    }
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeJumpControllerListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public WritableMap removeState(String str) {
        return this.mHashMap.remove(str);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public WritableArray removeStateArray(String str) {
        return this.mHashArray.remove(str);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public void saveState(String str, WritableMap writableMap) {
        this.mHashMap.put(str, writableMap);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public void saveStateArray(String str, WritableArray writableArray) {
        this.mHashArray.put(str, writableArray);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void selectChargeCardCoupon(HashMap hashMap) {
        if (hashMap == null || !hashMap.containsKey(JUMP_DATA_KEY_DEEPLINK_URL)) {
            return;
        }
        try {
            String str = (String) hashMap.get(JUMP_DATA_KEY_DEEPLINK_URL);
            String str2 = hashMap.containsKey(JUMP_DATA_KEY_PARAMS_KEY) ? (String) hashMap.get(JUMP_DATA_KEY_PARAMS_KEY) : null;
            String str3 = hashMap.containsKey(JUMP_DATA_KEY_PARAMS_JSON) ? (String) hashMap.get(JUMP_DATA_KEY_PARAMS_JSON) : null;
            if (str == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(str2, str3);
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() != null) {
                DeepLinkCommonHelper.startActivityForResult(AbstractJDReactInitialHelper.getCurrentMyActivity(), str, bundle, 1003);
            }
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void selectChargeCity(HashMap hashMap) {
        if (hashMap == null || !hashMap.containsKey(JUMP_DATA_KEY_DEEPLINK_URL)) {
            return;
        }
        try {
            String str = (String) hashMap.get(JUMP_DATA_KEY_DEEPLINK_URL);
            String str2 = hashMap.containsKey(JUMP_DATA_KEY_PARAMS_KEY) ? (String) hashMap.get(JUMP_DATA_KEY_PARAMS_KEY) : null;
            String str3 = hashMap.containsKey(JUMP_DATA_KEY_PARAMS_JSON) ? (String) hashMap.get(JUMP_DATA_KEY_PARAMS_JSON) : null;
            if (str == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(str2, str3);
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() != null) {
                DeepLinkCommonHelper.startActivityForResult(AbstractJDReactInitialHelper.getCurrentMyActivity(), str, bundle, 1002);
            }
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener
    public void toHomePage() {
    }

    public void toOpenapp(HashMap hashMap) {
        if (hashMap == null || !hashMap.containsKey(JUMP_DATA_KEY_OPENAPP_URL)) {
            return;
        }
        String str = (String) hashMap.get(JUMP_DATA_KEY_OPENAPP_URL);
        try {
            Activity currentMyActivity = JDReactHelper.newInstance().getCurrentMyActivity();
            if (str != null) {
                if (str.startsWith(OpenAppConstant.SCHEME_OPENAPP_1) || str.startsWith("openapp.jdmobile")) {
                    if (JDReactHelper.newInstance() != null && currentMyActivity != null) {
                        new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(currentMyActivity);
                    } else {
                        new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(JDReactHelper.newInstance().getApplicationContext());
                    }
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
        }
    }

    public void unRegisterWeixinReceiver() {
        if (this.weixinReceiver == null || this.localBroadcastManager == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "unRegisterWeixinReceiver");
        }
        this.localBroadcastManager.unregisterReceiver(this.weixinReceiver);
        this.weixinReceiver = null;
        this.localBroadcastManager = null;
    }
}
