package com.jingdong.common.lbs;

import android.content.Context;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class ProductInfoUtil {
    private static final String ID = "1";
    private static final String NAME = "\u5317\u4eac";
    private String addressDetail;
    private String cityId;
    private String cityName;
    private String districtId;
    private String districtName;
    private HttpGroup httpGroup;
    private UpdateDataListener localListener;
    private String proviceId;
    private String proviceName;
    private String townId;
    private String townName;

    /* loaded from: classes5.dex */
    public interface UpdateDataListener {
        void onFinish(ProductInfoUtil productInfoUtil, String str);
    }

    public ProductInfoUtil() {
    }

    public String getAddressDetail() {
        return this.addressDetail;
    }

    public String getCityId() {
        return TextUtils.isEmpty(this.cityId) ? "-1" : this.cityId;
    }

    public String getCityName() {
        return this.cityName;
    }

    public String getDistrictId() {
        return TextUtils.isEmpty(this.districtId) ? "-1" : this.districtId;
    }

    public String getDistrictName() {
        return this.districtName;
    }

    public String getProviceId() {
        return TextUtils.isEmpty(this.proviceId) ? "1" : this.proviceId;
    }

    public String getProviceName() {
        return this.proviceName;
    }

    public String getTownId() {
        return this.townId;
    }

    public String getTownName() {
        return this.townName;
    }

    public void queryProductInfo(Context context, double d, double d2) {
        if (OKLog.D) {
            OKLog.d("Temp", " -->> queryProductInfo lbs");
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.setFunctionId("lbs");
        if (d != -1.0d && d2 != -1.0d) {
            httpSetting.putJsonParam("lat", String.valueOf(d));
            httpSetting.putJsonParam(HybridSDK.LNG, String.valueOf(d2));
            httpSetting.putJsonParam("encrypted", Boolean.FALSE);
        }
        httpSetting.setEffect(0);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.lbs.ProductInfoUtil.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                if (OKLog.D) {
                    OKLog.d("Temp", " -->>lbs test jsonObject 2\uff1a" + jSONObject);
                }
                String stringOrNull = jSONObject.getStringOrNull("msg");
                if (!TextUtils.isEmpty(stringOrNull)) {
                    if (ProductInfoUtil.this.localListener != null) {
                        ProductInfoUtil.this.localListener.onFinish(null, stringOrNull);
                    }
                } else if (jSONObject.getStringOrNull(JDWeatherActionKey.PROVINCE_ID) != null) {
                    ProductInfoUtil.this.setProviceId(jSONObject.getStringOrNull(JDWeatherActionKey.PROVINCE_ID));
                    ProductInfoUtil.this.setCityId(jSONObject.getStringOrNull(JDWeatherActionKey.CITY_ID));
                    ProductInfoUtil.this.setDistrictId(jSONObject.getStringOrNull("districtId"));
                    ProductInfoUtil.this.setProviceName(jSONObject.getStringOrNull("provinceName"));
                    ProductInfoUtil.this.setCityName(jSONObject.getStringOrNull("cityName"));
                    ProductInfoUtil.this.setDistrictName(jSONObject.getStringOrNull("districtName"));
                    if (OKLog.D) {
                        OKLog.d("Temp", " -->> onEnd proviceName:" + ProductInfoUtil.this.getProviceName());
                    }
                    if (ProductInfoUtil.this.localListener != null) {
                        ProductInfoUtil.this.localListener.onFinish(new ProductInfoUtil(ProductInfoUtil.this.getProviceId(), ProductInfoUtil.this.getCityId(), ProductInfoUtil.this.getDistrictId(), ProductInfoUtil.this.getProviceName(), ProductInfoUtil.this.getCityName(), ProductInfoUtil.this.getDistrictName()), null);
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (ProductInfoUtil.this.localListener != null) {
                    ProductInfoUtil.this.localListener.onFinish(null, StringUtil.gps_location_fail);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroup httpGroup = this.httpGroup;
        if (httpGroup == null) {
            HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
            createNewSettings.setType(1000);
            HttpGroup httpGroup2 = HttpGroup.getHttpGroup(createNewSettings);
            this.httpGroup = httpGroup2;
            httpGroup2.add(httpSetting);
            return;
        }
        httpGroup.add(httpSetting);
    }

    public void setAddressDetail(String str) {
        this.addressDetail = str;
    }

    public void setCityId(String str) {
        this.cityId = str;
    }

    public void setCityName(String str) {
        if (TextUtils.isEmpty(str)) {
            this.cityName = "";
        } else {
            this.cityName = str;
        }
    }

    public void setDistrictId(String str) {
        this.districtId = str;
    }

    public void setDistrictName(String str) {
        if (TextUtils.isEmpty(str)) {
            this.districtName = "";
        } else {
            this.districtName = str;
        }
    }

    public void setInfoListener(UpdateDataListener updateDataListener) {
        this.localListener = updateDataListener;
    }

    public void setProviceId(String str) {
        this.proviceId = str;
    }

    public void setProviceName(String str) {
        if (TextUtils.isEmpty(str)) {
            this.proviceName = "\u5317\u4eac";
        } else {
            this.proviceName = str;
        }
    }

    public void setTownId(String str) {
        this.townId = str;
    }

    public void setTownName(String str) {
        this.townName = str;
    }

    public ProductInfoUtil(String str, String str2, String str3, String str4, String str5, String str6) {
        this.proviceId = str;
        this.cityId = str2;
        this.districtId = str3;
        this.proviceName = str4;
        this.cityName = str5;
        this.districtName = str6;
    }

    public ProductInfoUtil(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.proviceId = str;
        this.cityId = str2;
        this.districtId = str3;
        this.proviceName = str4;
        this.cityName = str5;
        this.districtName = str6;
        this.townId = str7;
        this.townName = str8;
    }
}
