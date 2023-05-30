package com.jingdong.common.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.cart.CartBaseUtil;
import com.jingdong.common.cart.CartCommonUtil;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.entity.cart.subcart.SubCartNumParam;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.union.common.config.UnionConstants;
import de.greenrobot.event.EventBus;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class SubShoppingBaseController {
    public static final String SHARED_PREFERENCES_7FRESH_CART_COUNT = "shoppingCart7freshListCount";
    public static final String SHARED_PREFERENCES_ECARD_CART_COUNT = "shoppingCartEcardListCount";
    public static final String SHARED_PREFERENCES_HOMEWISH_CART_COUNT = "shoppingCartHomeWishListCount";
    public static final String SHOPPING_7FRESH_CART_FLAG = "7fresh";
    public static final String SHOPPING_DRUGLIST_CART_FLAG = "prescription";
    public static final String SHOPPING_ECARD_CART_FLAG = "ecard";
    public static final String SHOPPING_HOMEWISHLIST_CART_FLAG = "homewishlist";
    private static final String TAG = "SubShoppingBaseController";
    private static volatile SubCartNumParam globalSubCartNumParam = new SubCartNumParam();
    public static Map<String, Integer> subCartMap = new ConcurrentHashMap();

    /* loaded from: classes5.dex */
    public interface SubShoppingCartListener {
        void onEnd(SubCartNumParam subCartNumParam);

        void onError(String str);

        void onReady();
    }

    public static void clearSubCartNum() {
        globalSubCartNumParam.sevenFreshNum = 0;
        globalSubCartNumParam.ecardNum = 0;
        globalSubCartNumParam.homeWishListNum = 0;
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        jdSharedPreferences.edit().putInt(SHARED_PREFERENCES_7FRESH_CART_COUNT, 0).commit();
        jdSharedPreferences.edit().putInt(SHARED_PREFERENCES_ECARD_CART_COUNT, 0).commit();
        jdSharedPreferences.edit().putInt(SHARED_PREFERENCES_HOMEWISH_CART_COUNT, 0).commit();
        subCartMap.clear();
    }

    public static int get7FreshCartNum() {
        return getSubCartNum(SHOPPING_7FRESH_CART_FLAG);
    }

    public static int getDrugListCartNum() {
        try {
            return getSubCartNum(SHOPPING_DRUGLIST_CART_FLAG);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.i(TAG, e2);
            }
            ExceptionReporter.reportExceptionToBugly(e2);
            return -1;
        }
    }

    public static int getEcardCartNum() {
        return getSubCartNum(SHOPPING_ECARD_CART_FLAG);
    }

    public static int getHomeWishListCartNum() {
        return getSubCartNum(SHOPPING_HOMEWISHLIST_CART_FLAG);
    }

    public static int getSubCartNum(String str) {
        if (!SHOPPING_DRUGLIST_CART_FLAG.equals(str)) {
            if (SHOPPING_7FRESH_CART_FLAG.equals(str)) {
                if (globalSubCartNumParam.sevenFreshNum == -1) {
                    return CommonBase.getJdSharedPreferences().getInt(SHARED_PREFERENCES_7FRESH_CART_COUNT, -1);
                }
                return globalSubCartNumParam.sevenFreshNum;
            } else if (SHOPPING_ECARD_CART_FLAG.equals(str)) {
                if (globalSubCartNumParam.ecardNum == -1) {
                    return CommonBase.getJdSharedPreferences().getInt(SHARED_PREFERENCES_ECARD_CART_COUNT, -1);
                }
                return globalSubCartNumParam.ecardNum;
            } else if (SHOPPING_HOMEWISHLIST_CART_FLAG.equals(str)) {
                if (globalSubCartNumParam.homeWishListNum == -1) {
                    return CommonBase.getJdSharedPreferences().getInt(SHARED_PREFERENCES_HOMEWISH_CART_COUNT, -1);
                }
                return globalSubCartNumParam.homeWishListNum;
            }
        }
        return -1;
    }

    public static JSONObject getSubCartNumForH5(IRouterParams iRouterParams) {
        JDJSONObject parseObject;
        if (iRouterParams != null) {
            String routerParam = iRouterParams.getRouterParam();
            if (TextUtils.isEmpty(routerParam) || (parseObject = JDJSON.parseObject(routerParam)) == null) {
                return null;
            }
            String optString = parseObject.optString("flag", "");
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            int subCartNum = getSubCartNum(optString);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("num", subCartNum);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }
        return null;
    }

    public static void initSubCartNum() {
        try {
            querySubCartNum(null, false, "", null);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.i(TAG, e2);
            }
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    private static void querySubCartNum(IMyActivity iMyActivity, boolean z, String str, final SubShoppingCartListener subShoppingCartListener) {
        HttpGroup.OnAllListener onAllListener = new HttpGroup.OnAllListener() { // from class: com.jingdong.common.controller.SubShoppingBaseController.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject optJSONObject;
                if (OKLog.D) {
                    OKLog.i(SubShoppingBaseController.TAG, "querySubCartNum-->onEnd");
                }
                SubCartNumParam subCartNumParam = null;
                if (httpResponse != null) {
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    if (fastJsonObject != null && (optJSONObject = fastJsonObject.optJSONObject("resultInfo")) != null) {
                        int optInt = optJSONObject.optInt(SubShoppingBaseController.SHOPPING_HOMEWISHLIST_CART_FLAG, -1);
                        if (optInt != -1) {
                            SubShoppingBaseController.updateSubCartMap(SubShoppingBaseController.SHARED_PREFERENCES_HOMEWISH_CART_COUNT, optInt);
                            SubShoppingBaseController.globalSubCartNumParam.homeWishListNum = optInt;
                            CommonBase.getJdSharedPreferences().edit().putInt(SubShoppingBaseController.SHARED_PREFERENCES_HOMEWISH_CART_COUNT, optInt).commit();
                        }
                        int optInt2 = optJSONObject.optInt(SubShoppingBaseController.SHOPPING_ECARD_CART_FLAG, -1);
                        if (optInt2 != -1) {
                            SubShoppingBaseController.updateSubCartMap(SubShoppingBaseController.SHARED_PREFERENCES_ECARD_CART_COUNT, optInt2);
                            SubShoppingBaseController.globalSubCartNumParam.ecardNum = optInt2;
                            CommonBase.getJdSharedPreferences().edit().putInt(SubShoppingBaseController.SHARED_PREFERENCES_ECARD_CART_COUNT, optInt2).commit();
                        }
                        int optInt3 = optJSONObject.optInt(SubShoppingBaseController.SHOPPING_7FRESH_CART_FLAG, -1);
                        if (optInt3 != -1) {
                            SubShoppingBaseController.updateSubCartMap(SubShoppingBaseController.SHARED_PREFERENCES_7FRESH_CART_COUNT, optInt3);
                            SubShoppingBaseController.globalSubCartNumParam.sevenFreshNum = optInt3;
                            CommonBase.getJdSharedPreferences().edit().putInt(SubShoppingBaseController.SHARED_PREFERENCES_7FRESH_CART_COUNT, optInt3).commit();
                            if (optInt3 == 0) {
                                CommonBase.putIntToPreference("Cart7freshNumCache", 0);
                            }
                        }
                        BaseEvent baseEvent = new BaseEvent(CartBaseUtil.EVENT_TYPE_SUBCART_NUM);
                        SubCartNumParam subCartNumParam2 = new SubCartNumParam();
                        subCartNumParam2.sevenFreshNum = optInt3;
                        subCartNumParam2.ecardNum = optInt2;
                        subCartNumParam2.homeWishListNum = optInt;
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("subCartNumParam", subCartNumParam2);
                        baseEvent.setBundle(bundle);
                        EventBus.getDefault().post(baseEvent);
                        subCartNumParam = subCartNumParam2;
                    }
                    SubShoppingCartListener subShoppingCartListener2 = SubShoppingCartListener.this;
                    if (subShoppingCartListener2 != null) {
                        subShoppingCartListener2.onEnd(subCartNumParam);
                        return;
                    }
                    return;
                }
                SubShoppingCartListener subShoppingCartListener3 = SubShoppingCartListener.this;
                if (subShoppingCartListener3 != null) {
                    subShoppingCartListener3.onError(null);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (OKLog.D) {
                    OKLog.i(SubShoppingBaseController.TAG, "querySubCartNum-->OnError");
                }
                SubShoppingCartListener subShoppingCartListener2 = SubShoppingCartListener.this;
                if (subShoppingCartListener2 != null) {
                    subShoppingCartListener2.onError(null);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                SubShoppingCartListener subShoppingCartListener2 = SubShoppingCartListener.this;
                if (subShoppingCartListener2 != null) {
                    subShoppingCartListener2.onReady();
                }
            }
        };
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CartConstant.KEY_CART_UUID, StatisticsReportUtil.readCartUUID());
            if (!TextUtils.isEmpty(str) && str.contains(SHOPPING_7FRESH_CART_FLAG)) {
                JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
                jDLocationCacheOption.setBusinessId("62b9d3a760861ecdf52e18ba7b5e2478");
                JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
                jSONObject.put(PdLVBody.LONGITUDE, location.getLng());
                jSONObject.put(PdLVBody.LATITUDE, location.getLat());
            } else {
                AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
                if (addressGlobal != null) {
                    jSONObject.put(PdLVBody.LONGITUDE, addressGlobal.getLongitude());
                    jSONObject.put(PdLVBody.LATITUDE, addressGlobal.getLatitude());
                    jSONObject.put("coord_type", addressGlobal.getCoordType());
                }
            }
            jSONObject.put(UnionConstants.BUNDLE_REFER, "1");
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("type", str);
            }
            jSONObject.put(CartConstant.KEY_USER_TYPE, CartCommonUtil.getUserType());
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setEncryptBody(CartCommonUtil.isEncryptBody());
        httpSetting.setFunctionId(CartConstant.FUNCTION_ID_SUBCART_COUNT);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(Configuration.getCartHost());
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setNotifyUser(false);
        if (z) {
            httpSetting.setEffect(1);
        } else {
            httpSetting.setEffect(0);
        }
        httpSetting.setListener(onAllListener);
        if (iMyActivity != null) {
            iMyActivity.getHttpGroupaAsynPool().add(httpSetting);
        } else {
            new HttpGroupUtil().getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public static void set7FreshCartNum(int i2) {
        setSubCartNum(SHOPPING_7FRESH_CART_FLAG, i2);
    }

    public static void setDrugListCartNum(int i2) {
        try {
            setSubCartNum(SHOPPING_DRUGLIST_CART_FLAG, i2);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.i(TAG, e2);
            }
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static void setEcardCartNum(int i2) {
        setSubCartNum(SHOPPING_ECARD_CART_FLAG, i2);
    }

    public static void setHomeWishListCartNum(int i2) {
        setSubCartNum(SHOPPING_HOMEWISHLIST_CART_FLAG, i2);
    }

    public static void setSubCartNum(String str, int i2) {
        String str2;
        if (SHOPPING_7FRESH_CART_FLAG.equals(str)) {
            globalSubCartNumParam.sevenFreshNum = i2;
            str2 = SHARED_PREFERENCES_7FRESH_CART_COUNT;
        } else if (SHOPPING_ECARD_CART_FLAG.equals(str)) {
            globalSubCartNumParam.ecardNum = i2;
            str2 = SHARED_PREFERENCES_ECARD_CART_COUNT;
        } else if (SHOPPING_HOMEWISHLIST_CART_FLAG.equals(str)) {
            globalSubCartNumParam.homeWishListNum = i2;
            str2 = SHARED_PREFERENCES_HOMEWISH_CART_COUNT;
        } else {
            str2 = "";
        }
        if (!TextUtils.isEmpty(str2)) {
            updateSubCartMap(str2, i2);
            CommonBase.getJdSharedPreferences().edit().putInt(str2, i2).commit();
        }
        BaseEvent baseEvent = new BaseEvent(CartBaseUtil.EVENT_TYPE_SUBCART_NUM);
        SubCartNumParam subCartNumParam = new SubCartNumParam();
        subCartNumParam.sevenFreshNum = globalSubCartNumParam.sevenFreshNum;
        subCartNumParam.ecardNum = globalSubCartNumParam.ecardNum;
        subCartNumParam.homeWishListNum = globalSubCartNumParam.homeWishListNum;
        Bundle bundle = new Bundle();
        bundle.putParcelable("subCartNumParam", subCartNumParam);
        baseEvent.setBundle(bundle);
        EventBus.getDefault().post(baseEvent);
    }

    public static void setSubCartNumForH5(IRouterParams iRouterParams) {
        JDJSONObject parseObject;
        if (iRouterParams != null) {
            String routerParam = iRouterParams.getRouterParam();
            if (TextUtils.isEmpty(routerParam) || (parseObject = JDJSON.parseObject(routerParam)) == null) {
                return;
            }
            String optString = parseObject.optString("flag", "");
            int optInt = parseObject.optInt("num", -1);
            if (TextUtils.isEmpty(optString) || optInt == -1) {
                return;
            }
            setSubCartNum(optString, optInt);
        }
    }

    public static void update7FreshAndHomeWishListCartNum() {
        updateSubCartNum(SHOPPING_7FRESH_CART_FLAG + DYConstants.DY_REGEX_COMMA + SHOPPING_HOMEWISHLIST_CART_FLAG);
    }

    public static void update7FreshCartNum() {
        updateSubCartNum(SHOPPING_7FRESH_CART_FLAG);
    }

    public static void updateDrugListCartNum() {
        try {
            updateSubCartNum(SHOPPING_DRUGLIST_CART_FLAG);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.i(TAG, e2);
            }
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static void updateDrugListCartNumForH5(IRouterParams iRouterParams) {
        try {
            updateSubCartNum(SHOPPING_DRUGLIST_CART_FLAG);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.i(TAG, e2);
            }
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static void updateEcardCartNum() {
        updateSubCartNum(SHOPPING_ECARD_CART_FLAG);
    }

    public static void updateHomeWishListCartNum() {
        updateSubCartNum(SHOPPING_HOMEWISHLIST_CART_FLAG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateSubCartMap(String str, int i2) {
        if (subCartMap.containsKey(str)) {
            if (SHARED_PREFERENCES_HOMEWISH_CART_COUNT.equals(str)) {
                if (i2 < 0) {
                    subCartMap.remove(str);
                    return;
                }
            } else if (i2 <= 0) {
                subCartMap.remove(str);
                return;
            }
        }
        if (SHARED_PREFERENCES_HOMEWISH_CART_COUNT.equals(str)) {
            if (i2 >= 0) {
                subCartMap.put(str, Integer.valueOf(i2));
            }
        } else if (i2 > 0) {
            subCartMap.put(str, Integer.valueOf(i2));
        }
    }

    public static void updateSubCartNum(String str) {
        try {
            querySubCartNum(null, false, str, null);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.i(TAG, e2);
            }
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }
}
