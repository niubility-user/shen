package com.jingdong.app.mall.location;

import android.os.Bundle;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.deeplinkhelper.DeepLinkBusinessAddressHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddress;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddressListener;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddressSelectedListener;
import com.jingdong.common.lbs.businessAddress.JDGlobalAddressManager;
import com.jingdong.common.lbs.businessAddress.JDUserCityAddress;
import com.jingdong.common.lbs.businessAddress.JDUserCityAddressListener;
import com.jingdong.common.lbs.businessAddress.JDUserCityAddressSelectedListener;
import com.jingdong.common.lbs.businessAddress.JDYFAddress;
import com.jingdong.common.lbs.businessAddress.JDYFAddressListener;
import com.jingdong.common.lbs.http.JDLbsHttpOption;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.sdk.platform.business.personal.R2;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class JSBusinessAddressManager {

    /* loaded from: classes4.dex */
    class a implements JDBusinessAddressListener {
        final /* synthetic */ IRouterParams a;

        a(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // com.jingdong.common.lbs.businessAddress.JDBusinessAddressListener
        public void onEnd(JDBusinessAddress jDBusinessAddress) {
            if (jDBusinessAddress != null) {
                this.a.onCallBack(JSBusinessAddressManager.genBusinessAddressObj(jDBusinessAddress));
            } else {
                this.a.onCallBack(new JSONObject());
            }
        }
    }

    /* loaded from: classes4.dex */
    class b implements JDBusinessAddressSelectedListener {
        final /* synthetic */ IRouterParams a;

        b(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // com.jingdong.common.lbs.businessAddress.JDBusinessAddressSelectedListener
        public void onAddressSelected(String str) {
            try {
                this.a.onCallBack(new JSONObject(str));
            } catch (Exception e2) {
                e2.printStackTrace();
                this.a.onCallBack(new JSONObject());
            }
        }
    }

    /* loaded from: classes4.dex */
    class c implements JDUserCityAddressSelectedListener {
        final /* synthetic */ IRouterParams a;

        c(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // com.jingdong.common.lbs.businessAddress.JDUserCityAddressSelectedListener
        public void onAddressSelected(String str) {
            try {
                this.a.onCallBack(new JSONObject(str));
            } catch (Exception e2) {
                e2.printStackTrace();
                this.a.onCallBack(new JSONObject());
            }
        }
    }

    /* loaded from: classes4.dex */
    class d implements JDUserCityAddressListener {
        final /* synthetic */ IRouterParams a;

        d(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // com.jingdong.common.lbs.businessAddress.JDUserCityAddressListener
        public void onEnd(JDUserCityAddress jDUserCityAddress) {
            if (jDUserCityAddress != null) {
                this.a.onCallBack(JSBusinessAddressManager.genUserCityAddressObj(jDUserCityAddress));
            } else {
                this.a.onCallBack(new JSONObject());
            }
        }
    }

    /* loaded from: classes4.dex */
    class e implements JDYFAddressListener {
        final /* synthetic */ IRouterParams a;

        e(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // com.jingdong.common.lbs.businessAddress.JDYFAddressListener
        public void onEnd(JDYFAddress jDYFAddress) {
            if (jDYFAddress != null) {
                this.a.onCallBack(JSBusinessAddressManager.genYFAddressObj(jDYFAddress));
            } else {
                this.a.onCallBack(new JSONObject());
            }
        }
    }

    public static JSONObject compareAddress(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null && !TextUtils.isEmpty(iRouterParams.getRouterParam())) {
            try {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                JDBusinessAddress jDBusinessAddress = new JDBusinessAddress();
                jDBusinessAddress.setAddressID(jSONObject.optLong("addressID1"));
                jDBusinessAddress.setLat(jSONObject.optDouble("lat1"));
                jDBusinessAddress.setLng(jSONObject.optDouble("lng1"));
                jDBusinessAddress.setFullAddress(jSONObject.optString("fullAddress1"));
                JDBusinessAddress jDBusinessAddress2 = new JDBusinessAddress();
                jDBusinessAddress2.setAddressID(jSONObject.optLong("addressID2"));
                jDBusinessAddress2.setLat(jSONObject.optDouble("lat2"));
                jDBusinessAddress2.setLng(jSONObject.optDouble("lng2"));
                jDBusinessAddress2.setFullAddress(jSONObject.optString("fullAddress2"));
                int compareAddress = JDBusinessAddressManager.getInstance().compareAddress(jDBusinessAddress, jDBusinessAddress2);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("code", 0);
                if (compareAddress == 0) {
                    jSONObject2.put("message", "\u4e00\u81f4");
                    jSONObject2.put("result", 0);
                } else if (compareAddress == 1) {
                    jSONObject2.put("message", "\u4e0d\u4e00\u81f4");
                    jSONObject2.put("result", 1);
                } else {
                    jSONObject2.put("message", "\u4e0d\u53ef\u6bd4\u8f83");
                    jSONObject2.put("result", 2);
                }
                return jSONObject2;
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    private static JSONObject genAddressGlobalObj(JDBusinessAddress jDBusinessAddress) {
        if (jDBusinessAddress == null) {
            return new JSONObject();
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(jDBusinessAddress.getAddressGlobalJson());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject genBusinessAddressObj(JDBusinessAddress jDBusinessAddress) {
        if (jDBusinessAddress == null) {
            return new JSONObject();
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(jDBusinessAddress.getBusinessAddressJson());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject genUserCityAddressObj(JDUserCityAddress jDUserCityAddress) {
        if (jDUserCityAddress == null) {
            return new JSONObject();
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(jDUserCityAddress.getUserCityAddressJson());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject genYFAddressObj(JDYFAddress jDYFAddress) {
        if (jDYFAddress == null) {
            return new JSONObject();
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(jDYFAddress.getJsonStr());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    public static JSONObject getAddressGlobal(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
                if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                    String optString = jSONObject.optString("businessId");
                    if (!TextUtils.isEmpty(optString)) {
                        jDLbsHttpOption.setBusinessId(optString);
                    }
                    String optString2 = jSONObject.optString("sceneId");
                    if (!TextUtils.isEmpty(optString2)) {
                        jDLbsHttpOption.setSceneId(optString2);
                    }
                }
                return genAddressGlobalObj(JDGlobalAddressManager.createJDBusinessAddressWithGlobal(jDLbsHttpOption));
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    public static void getBusinessAddress(IRouterParams iRouterParams) {
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
            if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                String optString = jSONObject.optString("businessId");
                if (!TextUtils.isEmpty(optString)) {
                    jDLbsHttpOption.setBusinessId(optString);
                }
                String optString2 = jSONObject.optString("sceneId");
                if (!TextUtils.isEmpty(optString2)) {
                    jDLbsHttpOption.setSceneId(optString2);
                }
                if (jSONObject.optBoolean("justToastOnce")) {
                    jDLbsHttpOption.setJustToastOnce(true);
                }
            }
            JDBusinessAddressManager.getInstance().getBusinessAddress(jDLbsHttpOption, new a(iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static JSONObject getCachedBusinessAddress(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
                if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                    String optString = jSONObject.optString("businessId");
                    if (!TextUtils.isEmpty(optString)) {
                        jDLbsHttpOption.setBusinessId(optString);
                    }
                    String optString2 = jSONObject.optString("sceneId");
                    if (!TextUtils.isEmpty(optString2)) {
                        jDLbsHttpOption.setSceneId(optString2);
                    }
                }
                return genBusinessAddressObj(JDBusinessAddressManager.getInstance().getCachedBusinessAddress(jDLbsHttpOption));
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    public static JSONObject getCachedUserCityAddress(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                return genUserCityAddressObj(JDBusinessAddressManager.getInstance().getCachedUserCityAddress());
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    public static JSONObject getDefaultBusinessAddress(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
                if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                    String optString = jSONObject.optString("businessId");
                    if (!TextUtils.isEmpty(optString)) {
                        jDLbsHttpOption.setBusinessId(optString);
                    }
                    String optString2 = jSONObject.optString("sceneId");
                    if (!TextUtils.isEmpty(optString2)) {
                        jDLbsHttpOption.setSceneId(optString2);
                    }
                }
                return genBusinessAddressObj(JDBusinessAddressManager.getInstance().getDefaultBusinessAddress(jDLbsHttpOption));
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    public static JSONObject getDefaultYFAddress(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                return genYFAddressObj(JDBusinessAddressManager.getInstance().getDefaultYFAddress());
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    public static void getUserCityAddress(IRouterParams iRouterParams) {
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
            if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                String optString = jSONObject.optString("businessId");
                if (!TextUtils.isEmpty(optString)) {
                    jDLbsHttpOption.setBusinessId(optString);
                }
                String optString2 = jSONObject.optString("sceneId");
                if (!TextUtils.isEmpty(optString2)) {
                    jDLbsHttpOption.setSceneId(optString2);
                }
            }
            JDBusinessAddressManager.getInstance().getUserCityAddress(jDLbsHttpOption, new d(iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void getYFAddress(IRouterParams iRouterParams) {
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
            if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                String optString = jSONObject.optString("businessId");
                if (!TextUtils.isEmpty(optString)) {
                    jDLbsHttpOption.setBusinessId(optString);
                }
                String optString2 = jSONObject.optString("sceneId");
                if (!TextUtils.isEmpty(optString2)) {
                    jDLbsHttpOption.setSceneId(optString2);
                }
            }
            JDBusinessAddressManager.getInstance().getYFAddress(jDLbsHttpOption, new e(iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void jumpToBusinessAddressSelect(IRouterParams iRouterParams) {
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                bundle.putString("business_id", jSONObject.optString("businessId"));
                bundle.putString("sceneId", jSONObject.optString("sceneId"));
                bundle.putInt("sourceId", jSONObject.optInt("sourceId"));
                bundle.putInt(DeeplinkProductDetailHelper.LAYER_STYLE, jSONObject.optInt(DeeplinkProductDetailHelper.LAYER_STYLE));
                bundle.putString("saveBusiness", jSONObject.optString("saveBusiness"));
                bundle.putString("source", jSONObject.optString("source"));
            }
            DeepLinkBusinessAddressHelper.startBusinessAddressSelectForResult(com.jingdong.app.mall.e.b().a(), bundle, 505, new b(iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void jumpToBusinessMap(IRouterParams iRouterParams) {
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                bundle.putString("businessId", jSONObject.optString("businessId"));
                bundle.putString("sceneId", jSONObject.optString("sceneId"));
                bundle.putString("keyword", jSONObject.optString("keyword"));
                bundle.putBoolean("showSearch", jSONObject.optBoolean("showSearch", true));
                bundle.putString("businessType", jSONObject.optString("businessType"));
                bundle.putString("scopeModel", jSONObject.optString("scopeModel"));
            }
            DeepLinkBusinessAddressHelper.startBusinessMap(com.jingdong.app.mall.e.b().a(), bundle, R2.attr.buttonPanelSideLayout);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void jumpToLbsCheck(IRouterParams iRouterParams) {
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                TextUtils.isEmpty(jSONObject.optString("businessId"));
                TextUtils.isEmpty(jSONObject.optString("sceneId"));
            }
            DeepLinkBusinessAddressHelper.startLbsCheck(com.jingdong.app.mall.e.b().a(), bundle, 510);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void jumpToUserCityAddressSelect(IRouterParams iRouterParams) {
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                String optString = jSONObject.optString("businessId");
                if (!TextUtils.isEmpty(optString)) {
                    bundle.putString("businessId", optString);
                }
                String optString2 = jSONObject.optString("sceneId");
                if (!TextUtils.isEmpty(optString2)) {
                    bundle.putString("sceneId", optString2);
                }
            }
            DeepLinkBusinessAddressHelper.startUserCityAddressSelect(com.jingdong.app.mall.e.b().a(), bundle, R2.attr.layout_constraintCircleAngle, new c(iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static JSONObject setUserCloseToast(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    JSONObject jSONObject2 = new JSONObject(iRouterParams.getRouterParam());
                    JDBusinessAddress jDBusinessAddress = new JDBusinessAddress();
                    jDBusinessAddress.setAddressID(jSONObject2.optLong("addressID"));
                    jDBusinessAddress.setProvinceCode(jSONObject2.optInt("provinceCode"));
                    jDBusinessAddress.setProvince(jSONObject2.optString("province"));
                    jDBusinessAddress.setCityCode(jSONObject2.optInt("cityCode"));
                    jDBusinessAddress.setCity(jSONObject2.optString("city"));
                    jDBusinessAddress.setDistrictCode(jSONObject2.optInt(Constant.KEY_DISTRICT_CODE));
                    jDBusinessAddress.setDistrict(jSONObject2.optString("district"));
                    jDBusinessAddress.setTownCode(jSONObject2.optInt("townCode"));
                    jDBusinessAddress.setTown(jSONObject2.optString("town"));
                    jDBusinessAddress.setFullAddress(jSONObject2.optString("fullAddress"));
                    jDBusinessAddress.setDetailAddress(jSONObject2.optString("detailAddress"));
                    jDBusinessAddress.setLng(jSONObject2.optDouble(HybridSDK.LNG));
                    jDBusinessAddress.setLat(jSONObject2.optDouble("lat"));
                    JDBusinessAddressManager.getInstance().setUserCloseToast(jDBusinessAddress);
                    jSONObject.put("code", 0);
                    jSONObject.put("message", "Toast\u5730\u5740\u5199\u5165\u6210\u529f");
                } else {
                    jSONObject.put("code", 601);
                    jSONObject.put("message", "\u5199\u5165\u6570\u636e\u83b7\u53d6\u5931\u8d25");
                }
                return jSONObject;
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    public static JSONObject updateAddressGlobal(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    JSONObject jSONObject2 = new JSONObject(iRouterParams.getRouterParam());
                    JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
                    JDBusinessAddress jDBusinessAddress = new JDBusinessAddress();
                    String optString = jSONObject2.optString("businessId");
                    if (!TextUtils.isEmpty(optString)) {
                        jDLbsHttpOption.setBusinessId(optString);
                    }
                    String optString2 = jSONObject2.optString("sceneId");
                    if (!TextUtils.isEmpty(optString2)) {
                        jDLbsHttpOption.setSceneId(optString2);
                    }
                    jDBusinessAddress.setAddressID(jSONObject2.optLong("addressID"));
                    jDBusinessAddress.setProvinceCode(jSONObject2.optInt("provinceCode"));
                    jDBusinessAddress.setProvince(jSONObject2.optString("province"));
                    jDBusinessAddress.setCityCode(jSONObject2.optInt("cityCode"));
                    jDBusinessAddress.setCity(jSONObject2.optString("city"));
                    jDBusinessAddress.setDistrictCode(jSONObject2.optInt(Constant.KEY_DISTRICT_CODE));
                    jDBusinessAddress.setDistrict(jSONObject2.optString("district"));
                    jDBusinessAddress.setTownCode(jSONObject2.optInt("townCode"));
                    jDBusinessAddress.setTown(jSONObject2.optString("town"));
                    jDBusinessAddress.setFullAddress(jSONObject2.optString("fullAddress"));
                    jDBusinessAddress.setDetailAddress(jSONObject2.optString("detailAddress"));
                    jDBusinessAddress.setLng(jSONObject2.optDouble(HybridSDK.LNG));
                    jDBusinessAddress.setLat(jSONObject2.optDouble("lat"));
                    jDBusinessAddress.setAddressTitle(jSONObject2.optString("addressTitle"));
                    jDBusinessAddress.setSaveBusiness(jSONObject2.optString("saveBusiness"));
                    jDBusinessAddress.setSource(jSONObject2.optString("source"));
                    if (JDGlobalAddressManager.updateAddressGlobal(jDLbsHttpOption, jDBusinessAddress)) {
                        jSONObject.put("code", 0);
                        jSONObject.put("message", "\u5168\u7ad9\u5730\u5740\u5199\u5165\u6210\u529f");
                        jSONObject.put("isSuccess", true);
                    } else {
                        jSONObject.put("code", 600);
                        jSONObject.put("message", "\u5168\u7ad9\u5730\u5740\u5199\u5165\u5931\u8d25");
                        jSONObject.put("isSuccess", false);
                    }
                } else {
                    jSONObject.put("code", 601);
                    jSONObject.put("message", "\u5199\u5165\u6570\u636e\u83b7\u53d6\u5931\u8d25");
                    jSONObject.put("isSuccess", false);
                }
                return jSONObject;
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    public static JSONObject updateCachedUserCityAddress(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    JSONObject jSONObject2 = new JSONObject(iRouterParams.getRouterParam());
                    JDUserCityAddress jDUserCityAddress = new JDUserCityAddress();
                    jDUserCityAddress.setCode(jSONObject2.optString("code"));
                    jDUserCityAddress.setMessage(jSONObject2.optString("message"));
                    jDUserCityAddress.setLng(jSONObject2.optDouble(HybridSDK.LNG));
                    jDUserCityAddress.setLat(jSONObject2.optDouble("lat"));
                    jDUserCityAddress.setProvinceCode(jSONObject2.optInt("provinceCode"));
                    jDUserCityAddress.setProvince(jSONObject2.optString("province"));
                    jDUserCityAddress.setCityCode(jSONObject2.optInt("cityCode"));
                    jDUserCityAddress.setCity(jSONObject2.optString("city"));
                    jDUserCityAddress.setDistrictCode(jSONObject2.optInt(Constant.KEY_DISTRICT_CODE));
                    jDUserCityAddress.setDistrict(jSONObject2.optString("district"));
                    jDUserCityAddress.setTownCode(jSONObject2.optInt("townCode"));
                    jDUserCityAddress.setTown(jSONObject2.optString("town"));
                    jDUserCityAddress.setType(jSONObject2.optString("type"));
                    jDUserCityAddress.setAddressTitle(jSONObject2.optString("addressTitle"));
                    jDUserCityAddress.setDifferent(jSONObject2.optBoolean("isDifferent"));
                    jDUserCityAddress.setLevel(jSONObject2.optInt("level"));
                    JDBusinessAddressManager.getInstance().updateCachedUserCityAddress(jDUserCityAddress);
                    jSONObject.put("code", 0);
                    jSONObject.put("message", "\u6210\u529f\u66f4\u65b0\u672c\u5730\u6301\u4e45\u5b58\u50a8\u7684\u7528\u6237\u57ce\u5e02\u5730\u5740");
                } else {
                    jSONObject.put("code", 601);
                    jSONObject.put("message", "\u672a\u6210\u529f\u66f4\u65b0\u672c\u5730\u6301\u4e45\u5b58\u50a8\u7684\u7528\u6237\u57ce\u5e02\u5730\u5740");
                }
                return jSONObject;
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }
}
