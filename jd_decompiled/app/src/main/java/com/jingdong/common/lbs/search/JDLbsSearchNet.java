package com.jingdong.common.lbs.search;

import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.common.lbs.http.JDLbsHttpConfig;
import com.jingdong.common.lbs.http.JDLbsHttpError;
import com.jingdong.common.lbs.http.JDLbsHttpListListener;
import com.jingdong.common.lbs.http.JDLbsHttpListener;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
class JDLbsSearchNet {
    private static JDLbsSearchNet instance;

    JDLbsSearchNet() {
    }

    public static JDLbsSearchNet getInstance() {
        JDLbsSearchNet jDLbsSearchNet;
        JDLbsSearchNet jDLbsSearchNet2 = instance;
        if (jDLbsSearchNet2 != null) {
            return jDLbsSearchNet2;
        }
        synchronized (JDLbsSearchNet.class) {
            if (instance == null) {
                instance = new JDLbsSearchNet();
            }
            jDLbsSearchNet = instance;
        }
        return jDLbsSearchNet;
    }

    public void getAddressByKeyWord(JDLbsSearchOption jDLbsSearchOption, final JDLbsHttpListListener<JDLbsSearchAddress> jDLbsHttpListListener) {
        try {
            if (TextUtils.isEmpty(jDLbsSearchOption.getKeyword())) {
                return;
            }
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("new_pois_by_keyword");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDLbsSearchOption.getBusinessId());
            httpSetting.putJsonParam("city", jDLbsSearchOption.getCity());
            httpSetting.putJsonParam("keyword", jDLbsSearchOption.getKeyword());
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.search.JDLbsSearchNet.4
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=new_pois_by_keyword getAddressByKeyWord resp:" + jSONObject.toString();
                                }
                                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                                if (optJSONArray != null) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                                        JDLbsSearchAddress jDLbsSearchAddress = new JDLbsSearchAddress();
                                        jDLbsSearchAddress.setProvince(jSONObject2.optString("province"));
                                        jDLbsSearchAddress.setCity(jSONObject2.optString("city"));
                                        jDLbsSearchAddress.setDistrict(jSONObject2.optString("district"));
                                        jDLbsSearchAddress.setAddress(jSONObject2.optString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID));
                                        jDLbsSearchAddress.setTitle(jSONObject2.optString("title"));
                                        jDLbsSearchAddress.setCategory(jSONObject2.optString("category"));
                                        jDLbsSearchAddress.setLat(jSONObject2.optDouble("lat", 0.0d));
                                        jDLbsSearchAddress.setLng(jSONObject2.optDouble(HybridSDK.LNG, 0.0d));
                                        arrayList.add(jDLbsSearchAddress);
                                    }
                                    jDLbsHttpListListener.onSuccess(arrayList);
                                    return;
                                }
                                jDLbsHttpListListener.onFail(new JDLbsHttpError());
                                return;
                            }
                            jDLbsHttpListListener.onFail(new JDLbsHttpError());
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

    public void getAddressByLatLng(JDLbsSearchOption jDLbsSearchOption, final JDLbsHttpListener<JDLbsSearchAddress> jDLbsHttpListener) {
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("new_gis");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDLbsSearchOption.getBusinessId());
            httpSetting.putJsonParam(HybridSDK.LNG, String.valueOf(jDLbsSearchOption.getLng()));
            httpSetting.putJsonParam("lat", String.valueOf(jDLbsSearchOption.getLat()));
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.search.JDLbsSearchNet.3
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=new_gis getAddressByLatLng resp:" + jSONObject.toString();
                                }
                                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                                if (optJSONObject != null) {
                                    JDLbsSearchAddress jDLbsSearchAddress = new JDLbsSearchAddress();
                                    jDLbsSearchAddress.setProvince(optJSONObject.optString("province"));
                                    jDLbsSearchAddress.setCity(optJSONObject.optString("city"));
                                    jDLbsSearchAddress.setDistrict(optJSONObject.optString("district"));
                                    jDLbsSearchAddress.setTown(optJSONObject.optString("town"));
                                    jDLbsHttpListener.onSuccess(jDLbsSearchAddress);
                                    return;
                                }
                                jDLbsHttpListener.onFail(new JDLbsHttpError());
                                return;
                            }
                            jDLbsHttpListener.onFail(new JDLbsHttpError());
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

    public void getCityByKeyWord(JDLbsSearchOption jDLbsSearchOption, final JDLbsHttpListListener<JDLbsSearchCity> jDLbsHttpListListener) {
        try {
            if (TextUtils.isEmpty(jDLbsSearchOption.getKeyword())) {
                return;
            }
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("region_by_keyword");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDLbsSearchOption.getBusinessId());
            httpSetting.putJsonParam("keyword", jDLbsSearchOption.getKeyword());
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.search.JDLbsSearchNet.6
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=region_by_keyword getCityByKeyWord resp:" + jSONObject.toString();
                                }
                                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                                if (optJSONArray != null) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                                        JDLbsSearchCity jDLbsSearchCity = new JDLbsSearchCity();
                                        jDLbsSearchCity.setId(jSONObject2.optString("id"));
                                        jDLbsSearchCity.setName(jSONObject2.optString("name"));
                                        jDLbsSearchCity.setFullname(jSONObject2.optString("fullname"));
                                        jDLbsSearchCity.setLevel(jSONObject2.optInt("level"));
                                        arrayList.add(jDLbsSearchCity);
                                    }
                                    jDLbsHttpListListener.onSuccess(arrayList);
                                    return;
                                }
                                jDLbsHttpListListener.onFail(new JDLbsHttpError());
                                return;
                            }
                            jDLbsHttpListListener.onFail(new JDLbsHttpError());
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

    public void getCityList(JDLbsSearchOption jDLbsSearchOption, final JDLbsHttpListListener<JDLbsSearchCity> jDLbsHttpListListener) {
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("region");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDLbsSearchOption.getBusinessId());
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.search.JDLbsSearchNet.5
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=region getCityList resp:" + jSONObject.toString();
                                }
                                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                                if (optJSONArray != null) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                                        JDLbsSearchCity jDLbsSearchCity = new JDLbsSearchCity();
                                        jDLbsSearchCity.setId(jSONObject2.optString("id"));
                                        jDLbsSearchCity.setName(jSONObject2.optString("name"));
                                        jDLbsSearchCity.setFullname(jSONObject2.optString("fullname"));
                                        jDLbsSearchCity.setLevel(jSONObject2.optInt("level"));
                                        jDLbsSearchCity.setPinyin(jSONObject2.optString("pinyin"));
                                        arrayList.add(jDLbsSearchCity);
                                    }
                                    jDLbsHttpListListener.onSuccess(arrayList);
                                    return;
                                }
                                jDLbsHttpListListener.onFail(new JDLbsHttpError());
                                return;
                            }
                            jDLbsHttpListListener.onFail(new JDLbsHttpError());
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

    public void getLocationByLatLng(JDLbsSearchOption jDLbsSearchOption, final JDLbsHttpListener<JDLbsSearchLocation> jDLbsHttpListener) {
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("new_gis_with_detail");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDLbsSearchOption.getBusinessId());
            httpSetting.putJsonParam(HybridSDK.LNG, String.valueOf(jDLbsSearchOption.getLng()));
            httpSetting.putJsonParam("lat", String.valueOf(jDLbsSearchOption.getLat()));
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.search.JDLbsSearchNet.1
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=new_gis_with_detail getLocationByLatLng resp:" + jSONObject.toString();
                                }
                                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                                if (optJSONObject != null) {
                                    JDLbsSearchLocation jDLbsSearchLocation = new JDLbsSearchLocation();
                                    jDLbsSearchLocation.setProvince(optJSONObject.optString("province"));
                                    jDLbsSearchLocation.setCity(optJSONObject.optString("city"));
                                    jDLbsSearchLocation.setDistrict(optJSONObject.optString("district"));
                                    jDLbsSearchLocation.setTown(optJSONObject.optString("town"));
                                    jDLbsSearchLocation.setDetailAddress(optJSONObject.optString("detailAddress"));
                                    jDLbsSearchLocation.setProvinceCode(optJSONObject.optInt("provinceCode"));
                                    jDLbsSearchLocation.setCityCode(optJSONObject.optInt("cityCode"));
                                    jDLbsSearchLocation.setDistrictCode(optJSONObject.optInt(Constant.KEY_DISTRICT_CODE));
                                    jDLbsSearchLocation.setTownCode(optJSONObject.optInt("townCode"));
                                    jDLbsHttpListener.onSuccess(jDLbsSearchLocation);
                                    return;
                                }
                                jDLbsHttpListener.onFail(new JDLbsHttpError());
                                return;
                            }
                            jDLbsHttpListener.onFail(new JDLbsHttpError());
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

    public void getPOIsByLatLng(JDLbsSearchOption jDLbsSearchOption, final JDLbsHttpListListener<JDLbsSearchAddress> jDLbsHttpListListener) {
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(JDLbsHttpConfig.getHost());
            httpSetting.setFunctionId("new_pois");
            httpSetting.setEncryptBody(true);
            httpSetting.putJsonParam("appid", jDLbsSearchOption.getBusinessId());
            httpSetting.putJsonParam(HybridSDK.LNG, String.valueOf(jDLbsSearchOption.getLng()));
            httpSetting.putJsonParam("lat", String.valueOf(jDLbsSearchOption.getLat()));
            httpSetting.putJsonParam(JDPureVideoManager.SourceKey.RADIUS, String.valueOf(jDLbsSearchOption.getRadius()));
            httpSetting.putJsonParam("pagesize", String.valueOf(jDLbsSearchOption.getPageSize()));
            httpSetting.putJsonParam("pageindex", String.valueOf(jDLbsSearchOption.getPageIndex()));
            httpSetting.putJsonParam("keyword", jDLbsSearchOption.getKeyword());
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.lbs.search.JDLbsSearchNet.2
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        if (jDLbsHttpListListener != null) {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject != null && jSONObject.optInt("subcode") == 0) {
                                if (JDLbsHttpConfig.isDebug) {
                                    String str = "FunctionId=new_pois getPOIsByLatLng resp:" + jSONObject.toString();
                                }
                                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                                if (optJSONArray != null) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                                        JDLbsSearchAddress jDLbsSearchAddress = new JDLbsSearchAddress();
                                        jDLbsSearchAddress.setProvince(jSONObject2.optString("province"));
                                        jDLbsSearchAddress.setCity(jSONObject2.optString("city"));
                                        jDLbsSearchAddress.setDistrict(jSONObject2.optString("district"));
                                        jDLbsSearchAddress.setAddress(jSONObject2.optString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID));
                                        jDLbsSearchAddress.setTitle(jSONObject2.optString("title"));
                                        jDLbsSearchAddress.setCategory(jSONObject2.optString("category"));
                                        jDLbsSearchAddress.setLat(jSONObject2.optDouble("lat", 0.0d));
                                        jDLbsSearchAddress.setLng(jSONObject2.optDouble(HybridSDK.LNG, 0.0d));
                                        arrayList.add(jDLbsSearchAddress);
                                    }
                                    jDLbsHttpListListener.onSuccess(arrayList);
                                    return;
                                }
                                jDLbsHttpListListener.onFail(new JDLbsHttpError());
                                return;
                            }
                            jDLbsHttpListListener.onFail(new JDLbsHttpError());
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
}
