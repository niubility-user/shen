package com.jingdong.common.lbs.jdlocation;

import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.google.common.net.HttpHeaders;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.lbs.R;
import com.jingdong.common.lbs.proxy.a;
import com.jingdong.common.lbs.utils.AESUtil;
import com.jingdong.common.lbs.utils.DeviceUtil;
import com.jingdong.common.lbs.utils.a;
import com.jingdong.common.lbs.utils.d;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.mapsdk.internal.l4;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class JDLocationNet {
    public static final String HOST = "https://lbsapi.m.jd.com/o";
    public static final String HOST_BETA = "https://beta-lbsapi.m.jd.com/o";
    private static JDLocationNet instance;

    JDLocationNet() {
    }

    public static JDLocationNet getInstance() {
        JDLocationNet jDLocationNet;
        JDLocationNet jDLocationNet2 = instance;
        if (jDLocationNet2 != null) {
            return jDLocationNet2;
        }
        synchronized (JDLocationNet.class) {
            if (instance == null) {
                instance = new JDLocationNet();
            }
            jDLocationNet = instance;
        }
        return jDLocationNet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void httpLog(String str, String str2) {
        DeviceUtil.isHostDebug();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int str2Int(String str) {
        try {
            if (!TextUtils.isEmpty(str) && !DYConstants.DY_NULL_STR.equalsIgnoreCase(str)) {
                return Integer.parseInt(str);
            }
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getRealAddress(final JDLocationOption jDLocationOption, final JDLocationInnerListener jDLocationInnerListener) {
        if (jDLocationOption == null || jDLocationInnerListener == null) {
            return;
        }
        d.a();
        d.a(new Runnable() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationNet.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
                    hashMap.put("User-Agent", DeviceUtil.SDK_VERSION);
                    String string = a.a.getString(R.string.lbs_api_key);
                    String valueOf = String.valueOf(jDLocationOption.getLat());
                    String valueOf2 = String.valueOf(jDLocationOption.getLng());
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("appid", jDLocationOption.getBusinessId());
                    jSONObject.put("lat", valueOf);
                    jSONObject.put(HybridSDK.LNG, valueOf2);
                    jSONObject.put("ifdetail", jDLocationOption.isNeedDetail() ? "1" : "0");
                    jSONObject.put("ifip", jDLocationOption.isNeedIP() ? "1" : "0");
                    jSONObject.put("isdefaultipaddr", "1");
                    jSONObject.put("appkey", DeviceUtil.getAppKey());
                    jSONObject.put("uuid", DeviceUtil.getUUID());
                    jSONObject.put("pin", DeviceUtil.getPin());
                    jSONObject.put("eid", "");
                    jSONObject.put("client", "android");
                    jSONObject.put(HybridSDK.APP_VERSION, DeviceUtil.getAppVersionName());
                    jSONObject.put(HybridSDK.APP_VERSION_CODE, DeviceUtil.getAppVersionCode());
                    jSONObject.put(HybridSDK.OS_VERSION, Build.VERSION.RELEASE);
                    jSONObject.put(l4.f16791e, DeviceUtil.getSDKVersion());
                    jSONObject.put(HybridSDK.D_BRAND, BaseInfo.getDeviceBrand());
                    jSONObject.put(HybridSDK.D_MODEL, BaseInfo.getDeviceModel());
                    jSONObject.put("screen", "");
                    jSONObject.put("source", "android");
                    jSONObject.put("ifgrid", "1");
                    String str = DeviceUtil.getSDKVersion() + "|" + AESUtil.encryptWithVersion(jSONObject.toString(), string, DeviceUtil.getSDKVersion());
                    String str2 = (DeviceUtil.isHostDebug() ? JDLocationNet.HOST_BETA : JDLocationNet.HOST) + "?d=" + URLEncoder.encode(str, "UTF-8");
                    JDLocationNet.this.httpLog("LBS", "getRealAddress url: ".concat(String.valueOf(str2)));
                    a.C0428a c0428a = new a.C0428a();
                    c0428a.b = 1;
                    c0428a.a = str2;
                    c0428a.f12387c = hashMap;
                    c0428a.f12388e = 1;
                    Pair<Integer, byte[]> a = c0428a.a().a();
                    if (((Integer) a.first).intValue() != 200) {
                        JDLocationError jDLocationError = new JDLocationError();
                        jDLocationError.setCode(((Integer) a.first).intValue());
                        jDLocationError.setMsg(new String((byte[]) a.second));
                        jDLocationInnerListener.onFail(jDLocationError);
                        return;
                    }
                    String str3 = new String((byte[]) a.second);
                    JDLocationNet.this.httpLog("LBS", "getRealAddress respData: ".concat(str3));
                    JSONObject jSONObject2 = new JSONObject(str3);
                    if (jSONObject2.optInt("code") != 0) {
                        JDLocationError jDLocationError2 = new JDLocationError();
                        jDLocationError2.setCode(jSONObject2.optInt("code"));
                        jDLocationError2.setMsg(jSONObject2.optString("message"));
                        jDLocationInnerListener.onFail(jDLocationError2);
                    } else if (jSONObject2.optInt("subcode") != 0) {
                        JDLocationError jDLocationError3 = new JDLocationError();
                        jDLocationError3.setCode(jSONObject2.optInt("subcode"));
                        jDLocationError3.setMsg(jSONObject2.optString("message"));
                        jDLocationInnerListener.onFail(jDLocationError3);
                    } else {
                        String decryptWithVersion = AESUtil.decryptWithVersion(jSONObject2.optString("data"), string, DeviceUtil.SDK_VERSION);
                        JDLocationNet.this.httpLog("LBS", "getRealAddress decryptResp: ".concat(String.valueOf(decryptWithVersion)));
                        JSONObject jSONObject3 = TextUtils.isEmpty(decryptWithVersion) ? null : new JSONObject(decryptWithVersion);
                        if (jSONObject3 == null) {
                            JDLocationError jDLocationError4 = new JDLocationError();
                            jDLocationError4.setCode(400);
                            jDLocationError4.setMsg(JDLocationError.MSG_NET_DATA_ERROR);
                            jDLocationInnerListener.onFail(jDLocationError4);
                            return;
                        }
                        int str2Int = JDLocationNet.this.str2Int(jSONObject3.optString("regionid"));
                        if (str2Int != 0 && str2Int != 1) {
                            JDLocationError jDLocationError5 = new JDLocationError();
                            jDLocationError5.setCode(100);
                            jDLocationError5.setMsg(JDLocationError.MSG_REGION_ERROR);
                            jDLocationInnerListener.onFail(jDLocationError5);
                            return;
                        }
                        JDLocation jDLocation = new JDLocation();
                        jDLocation.setType(1);
                        jDLocation.setLat(jSONObject3.optDouble("srclat"));
                        jDLocation.setLng(jSONObject3.optDouble("srclng"));
                        jDLocation.setGeohashLat(jSONObject3.optDouble("geohashLat"));
                        jDLocation.setGeohashLng(jSONObject3.optDouble("geohashLng"));
                        jDLocation.setGridId(jSONObject3.optLong("gridId"));
                        jDLocation.setRegionName(jSONObject3.optString("region"));
                        jDLocation.setRegionId(JDLocationNet.this.str2Int(jSONObject3.optString("regionid")));
                        jDLocation.setProvinceName(jSONObject3.optString("province"));
                        jDLocation.setProvinceId(JDLocationNet.this.str2Int(jSONObject3.optString("provinceid")));
                        jDLocation.setCityName(jSONObject3.optString("city"));
                        jDLocation.setCityId(JDLocationNet.this.str2Int(jSONObject3.optString("cityid")));
                        jDLocation.setDistrictName(jSONObject3.optString("district"));
                        jDLocation.setDistrictId(JDLocationNet.this.str2Int(jSONObject3.optString("districtid")));
                        jDLocation.setTownName(jSONObject3.optString("town"));
                        jDLocation.setTownId(JDLocationNet.this.str2Int(jSONObject3.optString("townid")));
                        jDLocation.setDetailAddress(jSONObject3.optString("detailaddr"));
                        jDLocation.setOversea(jSONObject3.optString("oversea"));
                        jDLocation.setCallType(jSONObject3.optString("callType"));
                        jDLocation.setUpdateTime(System.currentTimeMillis());
                        JDLocationSDK.getInstance().setLastLocation(jDLocation);
                        jDLocationInnerListener.onSuccess(jDLocation);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    if (jDLocationInnerListener != null) {
                        JDLocationError jDLocationError6 = new JDLocationError();
                        jDLocationError6.setCode(300);
                        jDLocationError6.setMsg(th.getMessage());
                        jDLocationInnerListener.onFail(jDLocationError6);
                    }
                }
            }
        });
    }
}
