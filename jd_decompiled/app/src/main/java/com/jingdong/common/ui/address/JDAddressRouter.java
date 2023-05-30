package com.jingdong.common.ui.address;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getCacheAddress(android.content.Context r1, org.json.JSONObject r2, android.os.Bundle r3, com.jingdong.common.unification.router.CallBackListener r4) {
        /*
            r0 = this;
            if (r4 == 0) goto L2e
            boolean r1 = r4 instanceof com.jingdong.common.unification.router.CallBackWithReturnListener
            if (r1 != 0) goto L7
            goto L2e
        L7:
            com.jingdong.common.unification.router.CallBackWithReturnListener r4 = (com.jingdong.common.unification.router.CallBackWithReturnListener) r4
            java.lang.String r1 = ""
            if (r2 == 0) goto L14
            java.lang.String r3 = "sceneId"
            java.lang.String r2 = r2.getString(r3)     // Catch: java.lang.Exception -> L14
            goto L15
        L14:
            r2 = r1
        L15:
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L1d
            java.lang.String r2 = "locService"
        L1d:
            com.jingdong.common.ui.address.entity.UnAddressInfo r2 = com.jingdong.common.ui.UnAddressSelectUtils.getAddressCacheAddressInfo(r2)
            if (r2 == 0) goto L2b
            java.lang.String r1 = com.jd.framework.json.JDJSON.toJSONString(r2)
            r4.onComplete(r1)
            goto L2e
        L2b:
            r4.onComplete(r1)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.ui.address.JDAddressRouter.getCacheAddress(android.content.Context, org.json.JSONObject, android.os.Bundle, com.jingdong.common.unification.router.CallBackListener):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getLocationAddress(android.content.Context r1, org.json.JSONObject r2, android.os.Bundle r3, com.jingdong.common.unification.router.CallBackListener r4) {
        /*
            r0 = this;
            if (r4 == 0) goto L24
            boolean r1 = r4 instanceof com.jingdong.common.unification.router.CallBackWithReturnListener
            if (r1 != 0) goto L7
            goto L24
        L7:
            if (r2 == 0) goto L10
            java.lang.String r1 = "sceneId"
            java.lang.String r1 = r2.getString(r1)     // Catch: java.lang.Exception -> L10
            goto L12
        L10:
            java.lang.String r1 = ""
        L12:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L1a
            java.lang.String r1 = "locService"
        L1a:
            com.jingdong.common.unification.router.CallBackWithReturnListener r4 = (com.jingdong.common.unification.router.CallBackWithReturnListener) r4
            com.jingdong.common.ui.address.JDAddressRouter$1 r2 = new com.jingdong.common.ui.address.JDAddressRouter$1
            r2.<init>()
            com.jingdong.common.ui.UnAddressSelectUtils.getLocAddress(r1, r2)
        L24:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.ui.address.JDAddressRouter.getLocationAddress(android.content.Context, org.json.JSONObject, android.os.Bundle, com.jingdong.common.unification.router.CallBackListener):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void hasLocationPermission(android.content.Context r1, org.json.JSONObject r2, android.os.Bundle r3, com.jingdong.common.unification.router.CallBackListener r4) {
        /*
            r0 = this;
            if (r4 == 0) goto L32
            boolean r1 = r4 instanceof com.jingdong.common.unification.router.CallBackWithReturnListener
            if (r1 != 0) goto L7
            goto L32
        L7:
            if (r2 == 0) goto L10
            java.lang.String r1 = "sceneId"
            java.lang.String r1 = r2.getString(r1)     // Catch: java.lang.Exception -> L10
            goto L12
        L10:
            java.lang.String r1 = ""
        L12:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L1a
            java.lang.String r1 = "locService"
        L1a:
            com.jingdong.common.unification.router.CallBackWithReturnListener r4 = (com.jingdong.common.unification.router.CallBackWithReturnListener) r4
            boolean r1 = com.jingdong.common.permission.LBSSceneSwitchHelper.getLbsSceneSwitch(r1)
            if (r1 == 0) goto L2a
            boolean r1 = com.jingdong.common.ui.UnAddressSelectUtils.hasLocationPermission()
            if (r1 == 0) goto L2a
            r1 = 1
            goto L2b
        L2a:
            r1 = 0
        L2b:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r4.onComplete(r1)
        L32:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.ui.address.JDAddressRouter.hasLocationPermission(android.content.Context, org.json.JSONObject, android.os.Bundle, com.jingdong.common.unification.router.CallBackListener):void");
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
