package com.jingdong.common.ui.address;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.permission.LBSSceneSwitchHelper;
import com.jingdong.common.ui.UnAddressSelectUtils;
import com.jingdong.common.ui.address.entity.AddressPageParams;
import com.jingdong.common.ui.address.entity.UnAddressInfo;
import com.jingdong.common.ui.address.listener.OnAddressInfoListener;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDAddressRouter {
    public static final String PARAM_SCENE_ID = "sceneId";
    private static final String defaultSceneId = "locService";

    /* JADX WARN: Removed duplicated region for block: B:13:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void getCacheAddress(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        String string;
        UnAddressInfo addressCacheAddressInfo;
        if (callBackListener == null || !(callBackListener instanceof CallBackWithReturnListener)) {
            return;
        }
        CallBackWithReturnListener callBackWithReturnListener = (CallBackWithReturnListener) callBackListener;
        if (jSONObject != null) {
            try {
                string = jSONObject.getString("sceneId");
            } catch (Exception unused) {
            }
            if (TextUtils.isEmpty(string)) {
                string = "locService";
            }
            addressCacheAddressInfo = UnAddressSelectUtils.getAddressCacheAddressInfo(string);
            if (addressCacheAddressInfo == null) {
                callBackWithReturnListener.onComplete(JDJSON.toJSONString(addressCacheAddressInfo));
                return;
            } else {
                callBackWithReturnListener.onComplete("");
                return;
            }
        }
        string = "";
        if (TextUtils.isEmpty(string)) {
        }
        addressCacheAddressInfo = UnAddressSelectUtils.getAddressCacheAddressInfo(string);
        if (addressCacheAddressInfo == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void getLocationAddress(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        String string;
        if (callBackListener == null || !(callBackListener instanceof CallBackWithReturnListener)) {
            return;
        }
        if (jSONObject != null) {
            try {
                string = jSONObject.getString("sceneId");
            } catch (Exception unused) {
            }
            if (TextUtils.isEmpty(string)) {
                string = "locService";
            }
            final CallBackWithReturnListener callBackWithReturnListener = (CallBackWithReturnListener) callBackListener;
            UnAddressSelectUtils.getLocAddress(string, new OnAddressInfoListener() { // from class: com.jingdong.common.ui.address.JDAddressRouter.1
                @Override // com.jingdong.common.ui.address.listener.OnAddressInfoListener
                public void onResult(UnAddressInfo unAddressInfo) {
                    if (unAddressInfo != null) {
                        callBackWithReturnListener.onComplete(JDJSON.toJSONString(unAddressInfo));
                    } else {
                        callBackWithReturnListener.onComplete("");
                    }
                }
            });
        }
        string = "";
        if (TextUtils.isEmpty(string)) {
        }
        final CallBackWithReturnListener callBackWithReturnListener2 = (CallBackWithReturnListener) callBackListener;
        UnAddressSelectUtils.getLocAddress(string, new OnAddressInfoListener() { // from class: com.jingdong.common.ui.address.JDAddressRouter.1
            @Override // com.jingdong.common.ui.address.listener.OnAddressInfoListener
            public void onResult(UnAddressInfo unAddressInfo) {
                if (unAddressInfo != null) {
                    callBackWithReturnListener2.onComplete(JDJSON.toJSONString(unAddressInfo));
                } else {
                    callBackWithReturnListener2.onComplete("");
                }
            }
        });
    }

    public void getShowAddressWithStore(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        String str;
        String str2;
        String str3;
        JSONException jSONException;
        String str4;
        String str5;
        String str6;
        String str7 = "";
        if (callBackListener == null || !(callBackListener instanceof CallBackWithReturnListener)) {
            return;
        }
        final CallBackWithReturnListener callBackWithReturnListener = (CallBackWithReturnListener) callBackListener;
        UnAddressInfo unAddressInfo = new UnAddressInfo();
        unAddressInfo.addressType = 4;
        if (jSONObject == null) {
            callBackWithReturnListener.onComplete(JDJSON.toJSONString(unAddressInfo));
            return;
        }
        try {
            String string = jSONObject.getString("venderId");
            try {
                str = jSONObject.getString("shopId");
                try {
                    str2 = jSONObject.getString("shopType");
                } catch (JSONException e2) {
                    e = e2;
                    str2 = "";
                    str3 = str2;
                    str7 = string;
                    jSONException = e;
                    str4 = str3;
                    jSONException.printStackTrace();
                    str5 = str7;
                    str6 = str4;
                    UnAddressSelectUtils.getAddressInfo(str6, str5, str, str2, str3, new OnAddressInfoListener() { // from class: com.jingdong.common.ui.address.JDAddressRouter.2
                        @Override // com.jingdong.common.ui.address.listener.OnAddressInfoListener
                        public void onResult(UnAddressInfo unAddressInfo2) {
                            if (unAddressInfo2 == null) {
                                callBackWithReturnListener.onComplete("");
                            } else {
                                callBackWithReturnListener.onComplete(JDJSON.toJSONString(unAddressInfo2));
                            }
                        }
                    });
                }
                try {
                    str3 = jSONObject.getString("sku");
                } catch (JSONException e3) {
                    e = e3;
                    str3 = "";
                    str7 = string;
                    jSONException = e;
                    str4 = str3;
                    jSONException.printStackTrace();
                    str5 = str7;
                    str6 = str4;
                    UnAddressSelectUtils.getAddressInfo(str6, str5, str, str2, str3, new OnAddressInfoListener() { // from class: com.jingdong.common.ui.address.JDAddressRouter.2
                        @Override // com.jingdong.common.ui.address.listener.OnAddressInfoListener
                        public void onResult(UnAddressInfo unAddressInfo2) {
                            if (unAddressInfo2 == null) {
                                callBackWithReturnListener.onComplete("");
                            } else {
                                callBackWithReturnListener.onComplete(JDJSON.toJSONString(unAddressInfo2));
                            }
                        }
                    });
                }
                try {
                    str7 = jSONObject.getString("sceneId");
                    if (TextUtils.isEmpty(str7)) {
                        str7 = "locService";
                    }
                    str6 = str7;
                    str5 = string;
                } catch (JSONException e4) {
                    str4 = str7;
                    str7 = string;
                    jSONException = e4;
                    jSONException.printStackTrace();
                    str5 = str7;
                    str6 = str4;
                    UnAddressSelectUtils.getAddressInfo(str6, str5, str, str2, str3, new OnAddressInfoListener() { // from class: com.jingdong.common.ui.address.JDAddressRouter.2
                        @Override // com.jingdong.common.ui.address.listener.OnAddressInfoListener
                        public void onResult(UnAddressInfo unAddressInfo2) {
                            if (unAddressInfo2 == null) {
                                callBackWithReturnListener.onComplete("");
                            } else {
                                callBackWithReturnListener.onComplete(JDJSON.toJSONString(unAddressInfo2));
                            }
                        }
                    });
                }
            } catch (JSONException e5) {
                e = e5;
                str = "";
                str2 = str;
            }
        } catch (JSONException e6) {
            e = e6;
            str = "";
            str2 = str;
            str3 = str2;
        }
        UnAddressSelectUtils.getAddressInfo(str6, str5, str, str2, str3, new OnAddressInfoListener() { // from class: com.jingdong.common.ui.address.JDAddressRouter.2
            @Override // com.jingdong.common.ui.address.listener.OnAddressInfoListener
            public void onResult(UnAddressInfo unAddressInfo2) {
                if (unAddressInfo2 == null) {
                    callBackWithReturnListener.onComplete("");
                } else {
                    callBackWithReturnListener.onComplete(JDJSON.toJSONString(unAddressInfo2));
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void hasLocationPermission(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        String string;
        if (callBackListener == null || !(callBackListener instanceof CallBackWithReturnListener)) {
            return;
        }
        if (jSONObject != null) {
            try {
                string = jSONObject.getString("sceneId");
            } catch (Exception unused) {
            }
            if (TextUtils.isEmpty(string)) {
                string = "locService";
            }
            ((CallBackWithReturnListener) callBackListener).onComplete(Boolean.valueOf(!LBSSceneSwitchHelper.getLbsSceneSwitch(string) && UnAddressSelectUtils.hasLocationPermission()));
        }
        string = "";
        if (TextUtils.isEmpty(string)) {
        }
        ((CallBackWithReturnListener) callBackListener).onComplete(Boolean.valueOf(!LBSSceneSwitchHelper.getLbsSceneSwitch(string) && UnAddressSelectUtils.hasLocationPermission()));
    }

    public void isGpsOpen(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (callBackListener == null || !(callBackListener instanceof CallBackWithReturnListener)) {
            return;
        }
        ((CallBackWithReturnListener) callBackListener).onComplete(Boolean.valueOf(UnAddressSelectUtils.isOpenGps()));
    }

    public void jumpToMap(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            return;
        }
        if (!(context instanceof Activity)) {
            if (callBackListener != null) {
                callBackListener.onError(-1);
                return;
            }
            return;
        }
        String str = null;
        if (jSONObject != null) {
            str = jSONObject.optString("sceneId");
            if (TextUtils.isEmpty(str)) {
                str = "locService";
            }
        }
        UnAddressSelectUtils.jumpToMap(context, str, callBackListener);
    }

    public void saveAddress(Context context, final JSONObject jSONObject, Bundle bundle, final CallBackListener callBackListener) {
        if (jSONObject == null) {
            return;
        }
        new Thread() { // from class: com.jingdong.common.ui.address.JDAddressRouter.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                String jSONObject2;
                try {
                    if (jSONObject.has(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID)) {
                        jSONObject2 = jSONObject.getString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID).replace("\\", "");
                    } else {
                        jSONObject2 = jSONObject.toString();
                    }
                    UnAddressInfo unAddressInfo = (UnAddressInfo) JDJSON.parseObject(jSONObject2, UnAddressInfo.class);
                    JDJSON.toJSONString(unAddressInfo);
                    if (unAddressInfo != null) {
                        UnAddressSelectUtils.saveAddress(unAddressInfo);
                        CallBackListener callBackListener2 = callBackListener;
                        if (callBackListener2 != null) {
                            callBackListener2.onComplete();
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }.start();
    }

    public void showAddressSelectPage(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null || jSONObject == null) {
            return;
        }
        AddressPageParams addressPageParams = null;
        if (jSONObject != null && (addressPageParams = (AddressPageParams) JDJSON.parseObject(jSONObject.toString(), AddressPageParams.class)) != null && TextUtils.isEmpty(addressPageParams.sceneId)) {
            addressPageParams.sceneId = "locService";
        }
        if (callBackListener instanceof CallBackWithReturnListener) {
            UnAddressSelectUtils.listener = (CallBackWithReturnListener) callBackListener;
        }
        UnAddressSelectUtils.startSelectActivity(context, 1, addressPageParams, callBackListener);
    }
}
