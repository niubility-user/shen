package com.jingdong.common.lbs.businessAddress;

import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.R;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.http.JDLbsHttpConfig;
import com.jingdong.common.lbs.http.JDLbsHttpError;
import com.jingdong.common.lbs.http.JDLbsHttpListListener;
import com.jingdong.common.lbs.http.JDLbsHttpListener;
import com.jingdong.common.lbs.http.JDLbsHttpOption;
import com.jingdong.common.lbs.report.LBSReportManager;
import com.jingdong.common.lbs.utils.AESUtil;
import com.jingdong.common.lbs.utils.GPSUtil;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDBusinessAddressNet {
    private static JDBusinessAddressNet instance;
    private boolean hasRequestYF;

    JDBusinessAddressNet() {
    }

    public boolean checkIsNearBy(JDYFAddress jDYFAddress, JDYFAddress jDYFAddress2, int i2) {
        if (jDYFAddress != null && jDYFAddress2 != null) {
            try {
                return GPSUtil.calculateDistance(jDYFAddress.getLat(), jDYFAddress.getLng(), jDYFAddress2.getLat(), jDYFAddress2.getLng()) <= i2;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static JDBusinessAddressNet getInstance() {
        JDBusinessAddressNet jDBusinessAddressNet;
        JDBusinessAddressNet jDBusinessAddressNet2 = instance;
        if (jDBusinessAddressNet2 != null) {
            return jDBusinessAddressNet2;
        }
        synchronized (JDBusinessAddressNet.class) {
            if (instance == null) {
                instance = new JDBusinessAddressNet();
            }
            jDBusinessAddressNet = instance;
        }
        return jDBusinessAddressNet;
    }

    public boolean isSameAddress(AddressGlobal addressGlobal, JDBusinessAddress jDBusinessAddress) {
        if (addressGlobal != null && jDBusinessAddress != null) {
            try {
                return GPSUtil.calculateDistance(Double.parseDouble(addressGlobal.getLatitude()), Double.parseDouble(addressGlobal.getLongitude()), jDBusinessAddress.getLat(), jDBusinessAddress.getLng()) < LBSReportManager.getInstance().getSameAddressDistance();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public void checkAddressAvailable(JDLbsHttpOption jDLbsHttpOption, final JDLbsHttpListener<JSONObject> jDLbsHttpListener) {
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId(ThemeTitleConstant.TITLE_SHOP_DRAWABLE_ID);
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDLbsHttpOption.getBusinessId());
            httpSetting.putJsonParam(HybridSDK.LNG, String.valueOf(jDLbsHttpOption.getLng()));
            httpSetting.putJsonParam("lat", String.valueOf(jDLbsHttpOption.getLat()));
            httpSetting.putJsonParam("source_id", String.valueOf(jDLbsHttpOption.getSourceId()));
            httpSetting.putJsonParam("version", JDLbsHttpConfig.SDK_VERSION);
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressNet.6
                {
                    JDBusinessAddressNet.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=shop checkAddressAvailable resp:" + jSONObject.toString();
                                }
                                JSONObject jSONObject2 = null;
                                if (jSONObject.optBoolean("enc_flag")) {
                                    String decryptWithVersion = AESUtil.decryptWithVersion(jSONObject.optString("enc_data"), JDBusinessAddressNet.this.getApiKey(), JDLbsHttpConfig.SDK_VERSION);
                                    if (JDLbsHttpConfig.isDebug) {
                                        String str2 = "FunctionId=shop decryptData:" + decryptWithVersion;
                                    }
                                    if (!TextUtils.isEmpty(decryptWithVersion)) {
                                        jSONObject2 = new JSONObject(decryptWithVersion).optJSONObject("data");
                                    }
                                } else {
                                    jSONObject2 = jSONObject.optJSONObject("data");
                                }
                                if (jSONObject2 != null) {
                                    jDLbsHttpListener.onSuccess(jSONObject2);
                                    return;
                                } else {
                                    jDLbsHttpListener.onFail(new JDLbsHttpError());
                                    return;
                                }
                            }
                            JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                            jDLbsHttpError.setCode(301);
                            jDLbsHttpError.setMessage(JDLbsHttpError.MSG_SUBCODE_ERROR);
                            jDLbsHttpListener.onFail(jDLbsHttpError);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    try {
                        if (jDLbsHttpListener != null) {
                            JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                            jDLbsHttpError.setCode(httpError.getErrorCode());
                            jDLbsHttpError.setMessage(httpError.getMessage());
                            jDLbsHttpListener.onFail(jDLbsHttpError);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    String getApiKey() {
        try {
            return JdSdk.getInstance().getApplicationContext().getString(R.string.lbs_api_key);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public void getBusinessAddress(final JDLbsHttpOption jDLbsHttpOption, final JDLbsHttpListener<JDBusinessAddress> jDLbsHttpListener) {
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("nearest");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDLbsHttpOption.getBusinessId());
            httpSetting.putJsonParam(HybridSDK.LNG, String.valueOf(jDLbsHttpOption.getLng()));
            httpSetting.putJsonParam("lat", String.valueOf(jDLbsHttpOption.getLat()));
            httpSetting.putJsonParam("version", JDLbsHttpConfig.SDK_VERSION);
            httpSetting.putJsonParam("need_name", "1");
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressNet.1
                {
                    JDBusinessAddressNet.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=nearest getBusinessAddress resp:" + jSONObject.toString();
                                }
                                JSONObject jSONObject2 = null;
                                if (jSONObject.optBoolean("enc_flag")) {
                                    String decryptWithVersion = AESUtil.decryptWithVersion(jSONObject.optString("enc_data"), JDBusinessAddressNet.this.getApiKey(), JDLbsHttpConfig.SDK_VERSION);
                                    if (JDLbsHttpConfig.isDebug) {
                                        String str2 = "FunctionId=nearest decryptData:" + decryptWithVersion;
                                    }
                                    if (!TextUtils.isEmpty(decryptWithVersion)) {
                                        jSONObject2 = new JSONObject(decryptWithVersion).optJSONObject("data");
                                    }
                                } else {
                                    jSONObject2 = jSONObject.optJSONObject("data");
                                }
                                if (jSONObject2 != null) {
                                    JDBusinessAddress jDBusinessAddress = new JDBusinessAddress();
                                    if (jSONObject2.optBoolean("useGlobal")) {
                                        jDBusinessAddress = JDGlobalAddressManager.createJDBusinessAddressWithGlobal(jDLbsHttpOption);
                                        jDBusinessAddress.setCode(500);
                                        jDBusinessAddress.setMessage(JDLbsHttpError.MSG_DEFAULT_ADDRESS);
                                    } else {
                                        jDBusinessAddress.setAddressID(jSONObject2.optLong("addressID"));
                                        jDBusinessAddress.setProvinceCode(jSONObject2.optInt("provinceCode"));
                                        jDBusinessAddress.setCityCode(jSONObject2.optInt("cityCode"));
                                        jDBusinessAddress.setDistrictCode(jSONObject2.optInt(Constant.KEY_DISTRICT_CODE));
                                        jDBusinessAddress.setTownCode(jSONObject2.optInt("townCode"));
                                        jDBusinessAddress.setProvince(jSONObject2.optString("province"));
                                        jDBusinessAddress.setCity(jSONObject2.optString("city"));
                                        jDBusinessAddress.setDistrict(jSONObject2.optString("district"));
                                        jDBusinessAddress.setTown(jSONObject2.optString("town"));
                                        jDBusinessAddress.setFullAddress(jSONObject2.optString("fullAddress"));
                                        jDBusinessAddress.setDetailAddress(jSONObject2.optString("detailAddress"));
                                        jDBusinessAddress.setName(jSONObject2.optString("name"));
                                        jDBusinessAddress.setTag(jSONObject2.optString("addressName"));
                                        jDBusinessAddress.setMobile(jSONObject2.optString("mobile"));
                                        jDBusinessAddress.setAddressDefault(jSONObject2.optBoolean("addressDefault"));
                                        jDBusinessAddress.setOverseas(jSONObject2.optBoolean("overseas"));
                                        jDBusinessAddress.setLng(jSONObject2.optDouble(HybridSDK.LNG));
                                        jDBusinessAddress.setLat(jSONObject2.optDouble("lat"));
                                        jDBusinessAddress.setGridId(jSONObject2.optLong("gridId"));
                                        jDBusinessAddress.setAddressTitle(jSONObject2.optString("addressTitle"));
                                        jDBusinessAddress.setUpdateGlobal(jSONObject2.optBoolean("updateGlobal"));
                                        jDBusinessAddress.setUseGlobal(jSONObject2.optBoolean("useGlobal"));
                                        jDBusinessAddress.setType(jSONObject2.optString("type"));
                                        AddressGlobal addressGlobal = JDGlobalAddressManager.getAddressGlobal(jDLbsHttpOption);
                                        if (addressGlobal != null && addressGlobal.getId() == jDBusinessAddress.getAddressID() && JDBusinessAddressNet.this.isSameAddress(addressGlobal, jDBusinessAddress)) {
                                            jDBusinessAddress.setShowToast(false);
                                        } else {
                                            jDBusinessAddress.setShowToast(true);
                                        }
                                        if ("gis".equals(jDBusinessAddress.getType())) {
                                            jDBusinessAddress.setToast("\u5df2\u5207\u6362\u4e3a\u5f53\u524d\u5b9a\u4f4d\u5730\u5740");
                                        } else if ("jsf".equals(jDBusinessAddress.getType())) {
                                            jDBusinessAddress.setToast("\u5df2\u5207\u6362\u4e3a\u9644\u8fd1\u6536\u8d27\u5730\u5740");
                                        }
                                        JDBusinessAddressManager.getInstance().setCachedBusinessAddress(jDLbsHttpOption, jDBusinessAddress);
                                        if (jDBusinessAddress.isUpdateGlobal()) {
                                            JDGlobalAddressManager.updateAddressGlobal(jDLbsHttpOption, jDBusinessAddress);
                                        }
                                    }
                                    jDLbsHttpListener.onSuccess(jDBusinessAddress);
                                    return;
                                }
                                jDLbsHttpListener.onFail(new JDLbsHttpError());
                                return;
                            }
                            JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                            jDLbsHttpError.setCode(301);
                            jDLbsHttpError.setMessage(JDLbsHttpError.MSG_SUBCODE_ERROR);
                            jDLbsHttpListener.onFail(jDLbsHttpError);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    try {
                        if (jDLbsHttpListener != null) {
                            JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                            jDLbsHttpError.setCode(httpError.getErrorCode());
                            jDLbsHttpError.setMessage(httpError.getMessage());
                            jDLbsHttpListener.onFail(jDLbsHttpError);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void getBusinessAddressList(JDLbsHttpOption jDLbsHttpOption, final JDLbsHttpListListener<JDBusinessAddress> jDLbsHttpListListener) {
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("addresses");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDLbsHttpOption.getBusinessId());
            httpSetting.putJsonParam(HybridSDK.LNG, String.valueOf(jDLbsHttpOption.getLng()));
            httpSetting.putJsonParam("lat", String.valueOf(jDLbsHttpOption.getLat()));
            httpSetting.putJsonParam("version", JDLbsHttpConfig.SDK_VERSION);
            httpSetting.putJsonParam("need_name", "1");
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressNet.5
                {
                    JDBusinessAddressNet.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=addresses getBusinessAddressList resp:" + jSONObject.toString();
                                }
                                JSONArray jSONArray = null;
                                if (jSONObject.optBoolean("enc_flag")) {
                                    String decryptWithVersion = AESUtil.decryptWithVersion(jSONObject.optString("enc_data"), JDBusinessAddressNet.this.getApiKey(), JDLbsHttpConfig.SDK_VERSION);
                                    if (JDLbsHttpConfig.isDebug) {
                                        String str2 = "FunctionId=addresses decryptData:" + decryptWithVersion;
                                    }
                                    if (!TextUtils.isEmpty(decryptWithVersion)) {
                                        jSONArray = new JSONObject(decryptWithVersion).optJSONArray("data");
                                    }
                                } else {
                                    jSONArray = jSONObject.optJSONArray("data");
                                }
                                if (jSONArray != null) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                                        JDBusinessAddress jDBusinessAddress = new JDBusinessAddress();
                                        jDBusinessAddress.setAddressID(jSONObject2.optLong("addressID"));
                                        jDBusinessAddress.setProvinceCode(jSONObject2.optInt("provinceCode"));
                                        jDBusinessAddress.setCityCode(jSONObject2.optInt("cityCode"));
                                        jDBusinessAddress.setDistrictCode(jSONObject2.optInt(Constant.KEY_DISTRICT_CODE));
                                        jDBusinessAddress.setTownCode(jSONObject2.optInt("townCode"));
                                        jDBusinessAddress.setProvince(jSONObject2.optString("province"));
                                        jDBusinessAddress.setCity(jSONObject2.optString("city"));
                                        jDBusinessAddress.setDistrict(jSONObject2.optString("district"));
                                        jDBusinessAddress.setTown(jSONObject2.optString("town"));
                                        jDBusinessAddress.setFullAddress(jSONObject2.optString("fullAddress"));
                                        jDBusinessAddress.setDetailAddress(jSONObject2.optString("detailAddress"));
                                        jDBusinessAddress.setName(jSONObject2.optString("name"));
                                        jDBusinessAddress.setTag(jSONObject2.optString("addressName"));
                                        jDBusinessAddress.setMobile(jSONObject2.optString("mobile"));
                                        jDBusinessAddress.setAddressDefault(jSONObject2.optBoolean("addressDefault"));
                                        jDBusinessAddress.setOverseas(jSONObject2.optBoolean("overseas"));
                                        jDBusinessAddress.setLng(jSONObject2.optDouble(HybridSDK.LNG));
                                        jDBusinessAddress.setLat(jSONObject2.optDouble("lat"));
                                        jDBusinessAddress.setUpdateGlobal(jSONObject2.optBoolean("updateGlobal"));
                                        jDBusinessAddress.setAddressValid(jSONObject2.optBoolean("addressValid"));
                                        arrayList.add(jDBusinessAddress);
                                    }
                                    jDLbsHttpListListener.onSuccess(arrayList);
                                    return;
                                }
                                jDLbsHttpListListener.onFail(new JDLbsHttpError());
                                return;
                            }
                            JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                            jDLbsHttpError.setCode(301);
                            jDLbsHttpError.setMessage(JDLbsHttpError.MSG_SUBCODE_ERROR);
                            jDLbsHttpListListener.onFail(jDLbsHttpError);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                            jDLbsHttpError.setCode(httpError.getErrorCode());
                            jDLbsHttpError.setMessage(httpError.getMessage());
                            jDLbsHttpListListener.onFail(jDLbsHttpError);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void getChildAreaAddressList(JDLbsHttpOption jDLbsHttpOption, final JDLbsHttpListListener<JDAreaAddress> jDLbsHttpListListener) {
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("area");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDLbsHttpOption.getBusinessId());
            httpSetting.putJsonParam("parentid", jDLbsHttpOption.getParentId());
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressNet.4
                {
                    JDBusinessAddressNet.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=area getChildAreaAddressList resp:" + jSONObject.toString();
                                }
                                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                                if (optJSONArray != null) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                                        JDAreaAddress jDAreaAddress = new JDAreaAddress();
                                        jDAreaAddress.setName(jSONObject2.optString("name"));
                                        jDAreaAddress.setId(jSONObject2.optInt("id"));
                                        jDAreaAddress.setParentId(jSONObject2.optInt("parentId"));
                                        jDAreaAddress.setSortId(jSONObject2.optInt(CartConstant.KEY_YB_SORTID));
                                        arrayList.add(jDAreaAddress);
                                    }
                                    jDLbsHttpListListener.onSuccess(arrayList);
                                    return;
                                }
                                JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                                jDLbsHttpError.setCode(500);
                                jDLbsHttpError.setMessage(JDLbsHttpError.MSG_DEFAULT_ADDRESS);
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                                return;
                            }
                            JDLbsHttpError jDLbsHttpError2 = new JDLbsHttpError();
                            jDLbsHttpError2.setCode(301);
                            jDLbsHttpError2.setMessage(JDLbsHttpError.MSG_SUBCODE_ERROR);
                            jDLbsHttpListListener.onFail(jDLbsHttpError2);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        if (jDLbsHttpListListener != null) {
                            JDLbsHttpError jDLbsHttpError3 = new JDLbsHttpError();
                            jDLbsHttpError3.setCode(300);
                            jDLbsHttpError3.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                            jDLbsHttpListListener.onFail(jDLbsHttpError3);
                        }
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                            jDLbsHttpError.setCode(httpError.getErrorCode());
                            jDLbsHttpError.setMessage(httpError.getMessage());
                            jDLbsHttpListListener.onFail(jDLbsHttpError);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void getNearByStoreList(JDBusinessStoreOption jDBusinessStoreOption, final JDLbsHttpListListener<JDBusinessStore> jDLbsHttpListListener) {
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("nearByStoreList");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDBusinessStoreOption.getBusinessId());
            httpSetting.putJsonParam(HybridSDK.LNG, String.valueOf(jDBusinessStoreOption.getLng()));
            httpSetting.putJsonParam("lat", String.valueOf(jDBusinessStoreOption.getLat()));
            httpSetting.putJsonParam("locationLng", String.valueOf(jDBusinessStoreOption.getLocationLng()));
            httpSetting.putJsonParam("locationLat", String.valueOf(jDBusinessStoreOption.getLocationLat()));
            httpSetting.putJsonParam("businessType", jDBusinessStoreOption.getBusinessType());
            httpSetting.putJsonParam("scopeModel", jDBusinessStoreOption.getScopeModel());
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressNet.7
                {
                    JDBusinessAddressNet.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=nearByStoreList getNearByStoreList resp:" + jSONObject.toString();
                                }
                                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                                if (optJSONArray != null && optJSONArray.length() > 0) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                                        JDBusinessStore jDBusinessStore = new JDBusinessStore();
                                        jDBusinessStore.setId(jSONObject2.optLong("id"));
                                        jDBusinessStore.setName(jSONObject2.optString("name"));
                                        jDBusinessStore.setVenderId(jSONObject2.optLong("venderId"));
                                        jDBusinessStore.setBusinessStartHours(jSONObject2.optString("businessStartHours"));
                                        jDBusinessStore.setBusinessEndHours(jSONObject2.optString("businessEndHours"));
                                        jDBusinessStore.setLng(jSONObject2.optDouble(HybridSDK.LNG));
                                        jDBusinessStore.setLat(jSONObject2.optDouble("lat"));
                                        jDBusinessStore.setAddressProvinceCode(jSONObject2.optString("addressProvinceCode"));
                                        jDBusinessStore.setAddressCityCode(jSONObject2.optString("addressCityCode"));
                                        jDBusinessStore.setAddressCountryCode(jSONObject2.optString("addressCountryCode"));
                                        jDBusinessStore.setAddressStreetCode(jSONObject2.optString("addressStreetCode"));
                                        jDBusinessStore.setAddressProvinceName(jSONObject2.optString("addressProvinceName"));
                                        jDBusinessStore.setAddressCityName(jSONObject2.optString("addressCityName"));
                                        jDBusinessStore.setAddressCountryName(jSONObject2.optString("addressCountryName"));
                                        jDBusinessStore.setAddressStreetName(jSONObject2.optString("addressStreetName"));
                                        jDBusinessStore.setAddressDetail(jSONObject2.optString("addressDetail"));
                                        jDBusinessStore.setStatus(jSONObject2.optInt("status"));
                                        jDBusinessStore.setVenderSource(jSONObject2.optInt(CartConstant.KEY_SHOP_VENDERSOURCE));
                                        jDBusinessStore.setStorePic(jSONObject2.optString("storePic"));
                                        jDBusinessStore.setCategoryId2(jSONObject2.optString("categoryId2"));
                                        jDBusinessStore.setCategoryId(jSONObject2.optString("categoryId"));
                                        jDBusinessStore.setDistance(jSONObject2.optDouble("distance"));
                                        arrayList.add(jDBusinessStore);
                                    }
                                    jDLbsHttpListListener.onSuccess(arrayList);
                                    return;
                                }
                                JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                                jDLbsHttpError.setCode(0);
                                jDLbsHttpError.setMessage("\u95e8\u5e97\u5165\u9a7b\u4e2d~");
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                            } else if (jSONObject != null && jSONObject.optInt("subcode") == 1001) {
                                JDLbsHttpError jDLbsHttpError2 = new JDLbsHttpError();
                                jDLbsHttpError2.setCode(1001);
                                jDLbsHttpError2.setMessage(JDLbsHttpError.MSG_NOT_SAME_CITY);
                                jDLbsHttpListListener.onFail(jDLbsHttpError2);
                            } else {
                                JDLbsHttpError jDLbsHttpError3 = new JDLbsHttpError();
                                jDLbsHttpError3.setCode(jSONObject.optInt("subcode"));
                                jDLbsHttpError3.setMessage(jSONObject.optString("message"));
                                jDLbsHttpListListener.onFail(jDLbsHttpError3);
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                            jDLbsHttpError.setCode(httpError.getErrorCode());
                            jDLbsHttpError.setMessage(httpError.getMessage());
                            jDLbsHttpListListener.onFail(jDLbsHttpError);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void getSearchStoreList(JDBusinessStoreOption jDBusinessStoreOption, final JDLbsHttpListListener<JDBusinessStore> jDLbsHttpListListener) {
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("searchStoreList");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDBusinessStoreOption.getBusinessId());
            httpSetting.putJsonParam(HybridSDK.LNG, String.valueOf(jDBusinessStoreOption.getLng()));
            httpSetting.putJsonParam("lat", String.valueOf(jDBusinessStoreOption.getLat()));
            httpSetting.putJsonParam("locationLng", String.valueOf(jDBusinessStoreOption.getLocationLng()));
            httpSetting.putJsonParam("locationLat", String.valueOf(jDBusinessStoreOption.getLocationLat()));
            httpSetting.putJsonParam("key", jDBusinessStoreOption.getKey());
            httpSetting.putJsonParam("page", Integer.valueOf(jDBusinessStoreOption.getPage()));
            httpSetting.putJsonParam("pageSize", 10);
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressNet.8
                {
                    JDBusinessAddressNet.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=searchStoreList getSearchStoreList resp:" + jSONObject.toString();
                                }
                                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                                if (optJSONArray != null && optJSONArray.length() > 0) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                                        JDBusinessStore jDBusinessStore = new JDBusinessStore();
                                        jDBusinessStore.setId(jSONObject2.optLong("id"));
                                        jDBusinessStore.setName(jSONObject2.optString("name"));
                                        jDBusinessStore.setVenderId(jSONObject2.optLong("venderId"));
                                        jDBusinessStore.setLng(jSONObject2.optDouble(HybridSDK.LNG));
                                        jDBusinessStore.setLat(jSONObject2.optDouble("lat"));
                                        jDBusinessStore.setDistance(jSONObject2.optDouble("distance"));
                                        jDBusinessStore.setStorePic(jSONObject2.optString("storePic"));
                                        jDBusinessStore.setAddressDetail(jSONObject2.optString("addressDetail"));
                                        jDBusinessStore.setCategoryId2(jSONObject2.optString("categoryId2"));
                                        jDBusinessStore.setCategoryId(jSONObject2.optString("categoryId"));
                                        jDBusinessStore.setCategoryId2Name(jSONObject2.optString("categoryId2Name"));
                                        arrayList.add(jDBusinessStore);
                                    }
                                    jDLbsHttpListListener.onSuccess(arrayList);
                                    return;
                                }
                                JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                                jDLbsHttpError.setCode(0);
                                jDLbsHttpError.setMessage("\u95e8\u5e97\u5165\u9a7b\u4e2d~");
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                            } else if (jSONObject != null && jSONObject.optInt("subcode") == 1001) {
                                JDLbsHttpError jDLbsHttpError2 = new JDLbsHttpError();
                                jDLbsHttpError2.setCode(1001);
                                jDLbsHttpError2.setMessage(JDLbsHttpError.MSG_NOT_SAME_CITY);
                                jDLbsHttpListListener.onFail(jDLbsHttpError2);
                            } else {
                                JDLbsHttpError jDLbsHttpError3 = new JDLbsHttpError();
                                jDLbsHttpError3.setCode(jSONObject.optInt("subcode"));
                                jDLbsHttpError3.setMessage(jSONObject.optString("message"));
                                jDLbsHttpListListener.onFail(jDLbsHttpError3);
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                            jDLbsHttpError.setCode(httpError.getErrorCode());
                            jDLbsHttpError.setMessage(httpError.getMessage());
                            jDLbsHttpListListener.onFail(jDLbsHttpError);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void getUserCityAddressList(JDLbsHttpOption jDLbsHttpOption, final JDLbsHttpListListener<JDUserCityAddress> jDLbsHttpListListener) {
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("cityList");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDLbsHttpOption.getBusinessId());
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressNet.3
                {
                    JDBusinessAddressNet.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=cityList getUserCityAddressList resp:" + jSONObject.toString();
                                }
                                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                                if (optJSONArray != null) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                                        JDUserCityAddress jDUserCityAddress = new JDUserCityAddress();
                                        jDUserCityAddress.setCode("0");
                                        jDUserCityAddress.setMessage("OK");
                                        jDUserCityAddress.setLat(jSONObject2.optDouble("lat"));
                                        jDUserCityAddress.setLng(jSONObject2.optDouble(HybridSDK.LNG));
                                        jDUserCityAddress.setProvince(jSONObject2.optString("province"));
                                        jDUserCityAddress.setProvinceCode(jSONObject2.optInt("provinceCode"));
                                        jDUserCityAddress.setCity(jSONObject2.optString("city"));
                                        jDUserCityAddress.setCityCode(jSONObject2.optInt("cityCode"));
                                        jDUserCityAddress.setDistrict(jSONObject2.optString("district"));
                                        jDUserCityAddress.setDistrictCode(jSONObject2.optInt(Constant.KEY_DISTRICT_CODE));
                                        jDUserCityAddress.setTown(jSONObject2.optString("town"));
                                        jDUserCityAddress.setTownCode(jSONObject2.optInt("townCode"));
                                        jDUserCityAddress.setType("choose");
                                        int optInt = jSONObject2.optInt("level");
                                        jDUserCityAddress.setLevel(optInt);
                                        if (optInt == 2) {
                                            jDUserCityAddress.setAddressTitle(jSONObject2.optString("city"));
                                        } else if (optInt == 3) {
                                            jDUserCityAddress.setAddressTitle(jSONObject2.optString("district"));
                                        } else if (optInt != 4) {
                                            jDUserCityAddress.setAddressTitle(jSONObject2.optString("province"));
                                        } else {
                                            jDUserCityAddress.setAddressTitle(jSONObject2.optString("town"));
                                        }
                                        arrayList.add(jDUserCityAddress);
                                    }
                                    jDLbsHttpListListener.onSuccess(arrayList);
                                    return;
                                }
                                JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                                jDLbsHttpError.setCode(500);
                                jDLbsHttpError.setMessage(JDLbsHttpError.MSG_DEFAULT_ADDRESS);
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                                return;
                            }
                            JDLbsHttpError jDLbsHttpError2 = new JDLbsHttpError();
                            jDLbsHttpError2.setCode(301);
                            jDLbsHttpError2.setMessage(JDLbsHttpError.MSG_SUBCODE_ERROR);
                            jDLbsHttpListListener.onFail(jDLbsHttpError2);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        if (jDLbsHttpListListener != null) {
                            JDLbsHttpError jDLbsHttpError3 = new JDLbsHttpError();
                            jDLbsHttpError3.setCode(300);
                            jDLbsHttpError3.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                            jDLbsHttpListListener.onFail(jDLbsHttpError3);
                        }
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                            jDLbsHttpError.setCode(httpError.getErrorCode());
                            jDLbsHttpError.setMessage(httpError.getMessage());
                            jDLbsHttpListListener.onFail(jDLbsHttpError);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void getYFAddress(final JDLbsHttpOption jDLbsHttpOption, final JDLbsHttpListener<JDYFAddress> jDLbsHttpListener) {
        String str = "1";
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("nearest");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDLbsHttpOption.getBusinessId());
            httpSetting.putJsonParam(HybridSDK.LNG, String.valueOf(jDLbsHttpOption.getLng()));
            httpSetting.putJsonParam("lat", String.valueOf(jDLbsHttpOption.getLat()));
            httpSetting.putJsonParam("version", JDLbsHttpConfig.SDK_VERSION);
            httpSetting.putJsonParam("need_name", "1");
            httpSetting.putJsonParam("source_id", "" + jDLbsHttpOption.getSourceId());
            if (!jDLbsHttpOption.isNeedDefault()) {
                str = "0";
            }
            httpSetting.putJsonParam("needDefault", str);
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressNet.2
                {
                    JDBusinessAddressNet.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str2 = "FunctionId=nearest getYFAddress resp:" + jSONObject.toString();
                                }
                                JSONObject jSONObject2 = null;
                                if (jSONObject.optBoolean("enc_flag")) {
                                    String decryptWithVersion = AESUtil.decryptWithVersion(jSONObject.optString("enc_data"), JDBusinessAddressNet.this.getApiKey(), JDLbsHttpConfig.SDK_VERSION);
                                    if (JDLbsHttpConfig.isDebug) {
                                        String str3 = "FunctionId=nearest decryptData:" + decryptWithVersion;
                                    }
                                    if (!TextUtils.isEmpty(decryptWithVersion)) {
                                        jSONObject2 = new JSONObject(decryptWithVersion).optJSONObject("data");
                                    }
                                } else {
                                    jSONObject2 = jSONObject.optJSONObject("data");
                                }
                                if (jSONObject2 != null && !jSONObject2.optBoolean("useGlobal")) {
                                    JDYFAddress jDYFAddress = new JDYFAddress();
                                    jDYFAddress.setCode("0");
                                    jDYFAddress.setMessage("OK");
                                    jDYFAddress.setAddressID(jSONObject2.optLong("addressID"));
                                    jDYFAddress.setProvinceCode(jSONObject2.optInt("provinceCode"));
                                    jDYFAddress.setCityCode(jSONObject2.optInt("cityCode"));
                                    jDYFAddress.setDistrictCode(jSONObject2.optInt(Constant.KEY_DISTRICT_CODE));
                                    jDYFAddress.setTownCode(jSONObject2.optInt("townCode"));
                                    jDYFAddress.setProvince(jSONObject2.optString("province"));
                                    jDYFAddress.setCity(jSONObject2.optString("city"));
                                    jDYFAddress.setDistrict(jSONObject2.optString("district"));
                                    jDYFAddress.setTown(jSONObject2.optString("town"));
                                    jDYFAddress.setFullAddress(jSONObject2.optString("fullAddress"));
                                    jDYFAddress.setDetailAddress(jSONObject2.optString("detailAddress"));
                                    jDYFAddress.setLng(jSONObject2.optDouble(HybridSDK.LNG));
                                    jDYFAddress.setLat(jSONObject2.optDouble("lat"));
                                    jDYFAddress.setGridId(jSONObject2.optLong("gridId"));
                                    jDYFAddress.setAddressTitle(jSONObject2.optString("addressTitle"));
                                    jDYFAddress.setType(jSONObject2.optString("type"));
                                    JDLbsHttpOption jDLbsHttpOption2 = jDLbsHttpOption;
                                    jDYFAddress.setPermission(jDLbsHttpOption2 != null ? PermissionHelper.hasLocationPermissionWithScene(jDLbsHttpOption2.getSceneId(), jDLbsHttpOption.getBusinessId()) : false);
                                    if (OKLog.D) {
                                        OKLog.d("LBS", "getYFAddress hasRequestYF=" + JDBusinessAddressNet.this.hasRequestYF);
                                    }
                                    if (!JDBusinessAddressNet.this.hasRequestYF) {
                                        JDBusinessAddressNet.this.hasRequestYF = true;
                                        jDYFAddress.setNearby(false);
                                    } else {
                                        int strToInt = JDBusinessAddressNet.this.strToInt(jSONObject2.optString("homepageDistance"));
                                        JDYFAddress defaultYFAddress = JDBusinessAddressManager.getInstance().getDefaultYFAddress();
                                        boolean checkIsNearBy = JDBusinessAddressNet.this.checkIsNearBy(jDYFAddress, defaultYFAddress, strToInt);
                                        if (OKLog.D) {
                                            OKLog.d("LBS", "getYFAddress homepageDistance=" + strToInt + " isNearBy=" + checkIsNearBy);
                                        }
                                        if (checkIsNearBy) {
                                            jDYFAddress.setType("default");
                                            jDYFAddress.setNearby(true);
                                            jDYFAddress.setCode(defaultYFAddress.getCode());
                                            jDYFAddress.setMessage(defaultYFAddress.getMessage());
                                            jDYFAddress.setAddressID(defaultYFAddress.getAddressID());
                                            jDYFAddress.setProvinceCode(defaultYFAddress.getProvinceCode());
                                            jDYFAddress.setCityCode(defaultYFAddress.getCityCode());
                                            jDYFAddress.setDistrictCode(defaultYFAddress.getDistrictCode());
                                            jDYFAddress.setTownCode(defaultYFAddress.getTownCode());
                                            jDYFAddress.setProvince(defaultYFAddress.getProvince());
                                            jDYFAddress.setCity(defaultYFAddress.getCity());
                                            jDYFAddress.setDistrict(defaultYFAddress.getDistrict());
                                            jDYFAddress.setTown(defaultYFAddress.getTown());
                                            jDYFAddress.setFullAddress(defaultYFAddress.getFullAddress());
                                            jDYFAddress.setDetailAddress(defaultYFAddress.getDetailAddress());
                                            jDYFAddress.setLng(defaultYFAddress.getLng());
                                            jDYFAddress.setLat(defaultYFAddress.getLat());
                                            jDYFAddress.setGridId(defaultYFAddress.getGridId());
                                            jDYFAddress.setAddressTitle(defaultYFAddress.getAddressTitle());
                                            jDYFAddress.setPermission(defaultYFAddress.isPermission());
                                        } else {
                                            jDYFAddress.setNearby(false);
                                        }
                                    }
                                    jDLbsHttpListener.onSuccess(jDYFAddress);
                                    return;
                                }
                                JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                                jDLbsHttpError.setCode(500);
                                jDLbsHttpError.setMessage(JDLbsHttpError.MSG_DEFAULT_ADDRESS);
                                jDLbsHttpListener.onFail(jDLbsHttpError);
                                return;
                            }
                            JDLbsHttpError jDLbsHttpError2 = new JDLbsHttpError();
                            jDLbsHttpError2.setCode(301);
                            jDLbsHttpError2.setMessage(JDLbsHttpError.MSG_SUBCODE_ERROR);
                            jDLbsHttpListener.onFail(jDLbsHttpError2);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        if (jDLbsHttpListener != null) {
                            JDLbsHttpError jDLbsHttpError3 = new JDLbsHttpError();
                            jDLbsHttpError3.setCode(300);
                            jDLbsHttpError3.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                            jDLbsHttpListener.onFail(jDLbsHttpError3);
                        }
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    try {
                        if (jDLbsHttpListener != null) {
                            JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                            jDLbsHttpError.setCode(httpError.getErrorCode());
                            jDLbsHttpError.setMessage(httpError.getMessage());
                            jDLbsHttpListener.onFail(jDLbsHttpError);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    int strToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 0;
        }
    }
}
