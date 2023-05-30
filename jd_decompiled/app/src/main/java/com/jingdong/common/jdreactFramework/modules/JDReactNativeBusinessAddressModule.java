package com.jingdong.common.jdreactFramework.modules;

import android.app.Activity;
import android.os.Bundle;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.deeplinkhelper.DeepLinkBusinessAddressHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddress;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddressListener;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddressSelectedListener;
import com.jingdong.common.lbs.businessAddress.JDGlobalAddressManager;
import com.jingdong.common.lbs.http.JDLbsHttpOption;
import com.unionpay.tsmservice.data.Constant;
import javax.annotation.Nonnull;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JDReactNativeBusinessAddressModule extends ReactContextBaseJavaModule {
    private static final String BUSINESS_ID = "business_id";
    private static final String BUSINESS_ID_NEW = "businessId";
    private static final String SCENE_ID = "sceneId";
    private ReactContext reactContext;

    public JDReactNativeBusinessAddressModule(@Nonnull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @ReactMethod
    public void compareAddress(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() != null && readableMap != null) {
                JDBusinessAddress jDBusinessAddress = new JDBusinessAddress();
                if (readableMap.hasKey("addressID1")) {
                    jDBusinessAddress.setAddressID(readableMap.getInt("addressID1"));
                }
                if (readableMap.hasKey("lat1")) {
                    jDBusinessAddress.setLat(readableMap.getDouble("lat1"));
                }
                if (readableMap.hasKey("lng1")) {
                    jDBusinessAddress.setLng(readableMap.getDouble("lng1"));
                }
                if (readableMap.hasKey("fullAddress1")) {
                    jDBusinessAddress.setFullAddress(readableMap.getString("fullAddress1"));
                }
                JDBusinessAddress jDBusinessAddress2 = new JDBusinessAddress();
                if (readableMap.hasKey("addressID2")) {
                    jDBusinessAddress2.setAddressID(readableMap.getInt("addressID2"));
                }
                if (readableMap.hasKey("lat2")) {
                    jDBusinessAddress2.setLat(readableMap.getDouble("lat2"));
                }
                if (readableMap.hasKey("lng2")) {
                    jDBusinessAddress2.setLng(readableMap.getDouble("lng2"));
                }
                if (readableMap.hasKey("fullAddress2")) {
                    jDBusinessAddress2.setFullAddress(readableMap.getString("fullAddress2"));
                }
                int compareAddress = JDBusinessAddressManager.getInstance().compareAddress(jDBusinessAddress, jDBusinessAddress2);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("code", 0);
                if (compareAddress == 0) {
                    jSONObject.put("message", "\u4e00\u81f4");
                    jSONObject.put("result", 0);
                } else if (compareAddress == 1) {
                    jSONObject.put("message", "\u4e0d\u4e00\u81f4");
                    jSONObject.put("result", 1);
                } else {
                    jSONObject.put("message", "\u4e0d\u53ef\u6bd4\u8f83");
                    jSONObject.put("result", 2);
                }
                callback.invoke(jSONObject.toString());
                return;
            }
            callback2.invoke(new Object[0]);
        } catch (Exception unused) {
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void getAddressGlobal(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() == null) {
                callback2.invoke(new Object[0]);
                return;
            }
            JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
            if (readableMap != null && readableMap.hasKey(BUSINESS_ID)) {
                jDLbsHttpOption.setBusinessId(readableMap.getString(BUSINESS_ID));
            }
            if (readableMap != null && readableMap.hasKey("sceneId")) {
                jDLbsHttpOption.setSceneId(readableMap.getString("sceneId"));
            }
            callback.invoke(JDGlobalAddressManager.createJDBusinessAddressWithGlobal(jDLbsHttpOption).getAddressGlobalJson());
        } catch (Exception unused) {
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void getBusinessAddress(ReadableMap readableMap, final Callback callback, final Callback callback2) {
        try {
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() == null) {
                callback2.invoke(new Object[0]);
                return;
            }
            JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
            if (readableMap != null && readableMap.hasKey(BUSINESS_ID)) {
                jDLbsHttpOption.setBusinessId(readableMap.getString(BUSINESS_ID));
            }
            if (readableMap != null && readableMap.hasKey("sceneId")) {
                jDLbsHttpOption.setSceneId(readableMap.getString("sceneId"));
            }
            JDBusinessAddressManager.getInstance().getBusinessAddress(jDLbsHttpOption, new JDBusinessAddressListener() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactNativeBusinessAddressModule.1
                {
                    JDReactNativeBusinessAddressModule.this = this;
                }

                @Override // com.jingdong.common.lbs.businessAddress.JDBusinessAddressListener
                public void onEnd(JDBusinessAddress jDBusinessAddress) {
                    if (jDBusinessAddress != null) {
                        callback.invoke(jDBusinessAddress.getBusinessAddressJson());
                        return;
                    }
                    callback2.invoke(new Object[0]);
                }
            });
        } catch (Exception unused) {
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void getCachedBusinessAddress(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() == null) {
                callback2.invoke(new Object[0]);
                return;
            }
            JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
            if (readableMap != null && readableMap.hasKey(BUSINESS_ID)) {
                jDLbsHttpOption.setBusinessId(readableMap.getString(BUSINESS_ID));
            }
            if (readableMap != null && readableMap.hasKey("sceneId")) {
                jDLbsHttpOption.setSceneId(readableMap.getString("sceneId"));
            }
            callback.invoke(JDBusinessAddressManager.getInstance().getCachedBusinessAddress(jDLbsHttpOption).getBusinessAddressJson());
        } catch (Exception unused) {
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void getCachedUserCityAddress(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() == null) {
                callback2.invoke(new Object[0]);
                return;
            }
            if (readableMap != null) {
                readableMap.hasKey("businessId");
            }
            if (readableMap != null) {
                readableMap.hasKey("sceneId");
            }
            callback.invoke(JDBusinessAddressManager.getInstance().getCachedUserCityAddress().getUserCityAddressJson());
        } catch (Exception unused) {
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void getDefaultBusinessAddress(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() == null) {
                callback2.invoke(new Object[0]);
                return;
            }
            JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
            if (readableMap != null && readableMap.hasKey(BUSINESS_ID)) {
                jDLbsHttpOption.setBusinessId(readableMap.getString(BUSINESS_ID));
            }
            if (readableMap != null && readableMap.hasKey("sceneId")) {
                jDLbsHttpOption.setSceneId(readableMap.getString("sceneId"));
            }
            callback.invoke(JDBusinessAddressManager.getInstance().getDefaultBusinessAddress(jDLbsHttpOption).getBusinessAddressJson());
        } catch (Exception unused) {
            callback2.invoke(new Object[0]);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeBusinessAddressModule";
    }

    @ReactMethod
    public void jumpToBusinessAddressSelect(ReadableMap readableMap, final Callback callback, final Callback callback2) {
        try {
            Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (readableMap != null && currentMyActivity != null) {
                Bundle bundle = new Bundle();
                if (readableMap.hasKey(BUSINESS_ID)) {
                    bundle.putString(BUSINESS_ID, readableMap.getString(BUSINESS_ID));
                }
                if (readableMap != null && readableMap.hasKey("sceneId")) {
                    bundle.putString("sceneId", readableMap.getString("sceneId"));
                }
                if (readableMap.hasKey("sourceId")) {
                    bundle.putInt("sourceId", readableMap.getInt("sourceId"));
                }
                if (readableMap.hasKey(DeeplinkProductDetailHelper.LAYER_STYLE)) {
                    bundle.putInt(DeeplinkProductDetailHelper.LAYER_STYLE, readableMap.getInt(DeeplinkProductDetailHelper.LAYER_STYLE));
                }
                if (readableMap.hasKey("saveBusiness")) {
                    bundle.putString("saveBusiness", readableMap.getString("saveBusiness"));
                }
                if (readableMap.hasKey("source")) {
                    bundle.putString("source", readableMap.getString("source"));
                }
                DeepLinkBusinessAddressHelper.startBusinessAddressSelectForResult(currentMyActivity, bundle, 505, new JDBusinessAddressSelectedListener() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactNativeBusinessAddressModule.2
                    {
                        JDReactNativeBusinessAddressModule.this = this;
                    }

                    @Override // com.jingdong.common.lbs.businessAddress.JDBusinessAddressSelectedListener
                    public void onAddressSelected(String str) {
                        try {
                            callback.invoke(str);
                        } catch (Exception e2) {
                            Callback callback3 = callback2;
                            if (callback3 != null) {
                                callback3.invoke(e2.getMessage());
                            }
                        }
                    }
                });
                return;
            }
            callback2.invoke(new Object[0]);
        } catch (Exception unused) {
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void jumpToLbsCheck(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (currentMyActivity == null) {
                callback2.invoke(new Object[0]);
                return;
            }
            Bundle bundle = new Bundle();
            if (readableMap.hasKey(BUSINESS_ID)) {
                bundle.putString(BUSINESS_ID, readableMap.getString(BUSINESS_ID));
            }
            if (readableMap != null && readableMap.hasKey("sceneId")) {
                bundle.putString("sceneId", readableMap.getString("sceneId"));
            }
            DeepLinkBusinessAddressHelper.startLbsCheck(currentMyActivity, bundle, 510);
            callback.invoke("");
        } catch (Exception unused) {
            callback2.invoke(new Object[0]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.facebook.react.bridge.Callback] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r27v0, types: [com.facebook.react.bridge.Callback] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v4 */
    @ReactMethod
    public void updateAddressGlobal(ReadableMap readableMap, Callback callback, Callback callback2) {
        JSONObject jSONObject;
        ?? r1 = callback2;
        Callback callback3 = "sceneId";
        try {
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() == null) {
                r1.invoke(new Object[0]);
            } else {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    if (readableMap != null) {
                        try {
                            JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
                            if (readableMap.hasKey(BUSINESS_ID)) {
                                jDLbsHttpOption.setBusinessId(readableMap.getString(BUSINESS_ID));
                            }
                            if (readableMap.hasKey("sceneId")) {
                                jDLbsHttpOption.setSceneId(readableMap.getString("sceneId"));
                            }
                            JDBusinessAddress jDBusinessAddress = new JDBusinessAddress();
                            if (readableMap.hasKey("addressID")) {
                                jSONObject = jSONObject2;
                                jDBusinessAddress.setAddressID(readableMap.getInt("addressID"));
                            } else {
                                jSONObject = jSONObject2;
                            }
                            if (readableMap.hasKey("provinceCode")) {
                                jDBusinessAddress.setProvinceCode(readableMap.getInt("provinceCode"));
                            }
                            if (readableMap.hasKey("province")) {
                                jDBusinessAddress.setProvince(readableMap.getString("province"));
                            }
                            if (readableMap.hasKey("cityCode")) {
                                jDBusinessAddress.setCityCode(readableMap.getInt("cityCode"));
                            }
                            if (readableMap.hasKey("city")) {
                                jDBusinessAddress.setCity(readableMap.getString("city"));
                            }
                            if (readableMap.hasKey(Constant.KEY_DISTRICT_CODE)) {
                                jDBusinessAddress.setDistrictCode(readableMap.getInt(Constant.KEY_DISTRICT_CODE));
                            }
                            if (readableMap.hasKey("district")) {
                                jDBusinessAddress.setDistrict(readableMap.getString("district"));
                            }
                            if (readableMap.hasKey("townCode")) {
                                jDBusinessAddress.setTownCode(readableMap.getInt("townCode"));
                            }
                            if (readableMap.hasKey("town")) {
                                jDBusinessAddress.setTown(readableMap.getString("town"));
                            }
                            if (readableMap.hasKey("fullAddress")) {
                                jDBusinessAddress.setFullAddress(readableMap.getString("fullAddress"));
                            }
                            if (readableMap.hasKey("detailAddress")) {
                                jDBusinessAddress.setDetailAddress(readableMap.getString("detailAddress"));
                            }
                            if (readableMap.hasKey(HybridSDK.LNG)) {
                                jDBusinessAddress.setLng(readableMap.getDouble(HybridSDK.LNG));
                            }
                            if (readableMap.hasKey("lat")) {
                                jDBusinessAddress.setLat(readableMap.getDouble("lat"));
                            }
                            if (readableMap.hasKey("addressTitle")) {
                                jDBusinessAddress.setAddressTitle(readableMap.getString("addressTitle"));
                            }
                            if (readableMap.hasKey("saveBusiness")) {
                                jDBusinessAddress.setSaveBusiness(readableMap.getString("saveBusiness"));
                            }
                            if (readableMap.hasKey("source")) {
                                jDBusinessAddress.setSource(readableMap.getString("source"));
                            }
                            if (JDGlobalAddressManager.updateAddressGlobal(jDLbsHttpOption, jDBusinessAddress)) {
                                JSONObject jSONObject3 = jSONObject;
                                jSONObject3.put("code", 0);
                                jSONObject3.put("message", "\u5168\u7ad9\u5730\u5740\u5199\u5165\u6210\u529f");
                                jSONObject3.put("isSuccess", true);
                                r1 = new Object[]{jSONObject3.toString()};
                                callback.invoke(r1);
                            } else {
                                JSONObject jSONObject4 = jSONObject;
                                jSONObject4.put("code", 600);
                                jSONObject4.put("message", "\u5168\u7ad9\u5730\u5740\u5199\u5165\u5931\u8d25");
                                r1 = 0;
                                jSONObject4.put("isSuccess", false);
                                Callback callback4 = callback2;
                                callback4.invoke(jSONObject4.toString());
                                callback3 = callback4;
                            }
                        } catch (Exception unused) {
                            callback3 = callback2;
                            callback3.invoke(new Object[0]);
                        }
                    } else {
                        Callback callback5 = callback2;
                        jSONObject2.put("code", 601);
                        jSONObject2.put("message", "\u5199\u5165\u6570\u636e\u83b7\u53d6\u5931\u8d25");
                        r1 = 0;
                        jSONObject2.put("isSuccess", false);
                        callback5.invoke(jSONObject2.toString());
                        callback3 = callback5;
                    }
                } catch (Exception unused2) {
                }
            }
        } catch (Exception unused3) {
            callback3 = r1;
        }
    }
}
