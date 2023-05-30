package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes3.dex */
public class PDRecommendEntity {
    public String abt;
    public String benefitImageUrl;
    public String benefitTitle;
    public String client_click_url;
    public String client_exposal_url;
    public String clkUrl;
    public String expid;
    public String exposureJsonSourceValue;
    public String exposureSourceValue;
    public String extension_id;
    public String flow;
    public boolean hasRealExpo = false;
    public String icon1;
    public String icon2;
    public String iconC;
    public String image;
    public String index;
    public String isAd;
    public boolean isPlusWare;
    public String jdPrice;
    public String name;
    public String nonJdPriceText;
    public String popUnit;
    public String popUrl;
    public String presaleInfo;
    public String presaleText;
    public String presaleTextColor;
    public int recommendType;
    public String reqsig;
    public String resUR;
    public String rid;
    public String salesVolume;
    public String sid;
    public String skuId;
    public String source;
    public String sourceValue;
    public String stagesKinds;
    public String sv;

    public PDRecommendEntity() {
    }

    public boolean isAd() {
        return TextUtils.equals("1", this.isAd);
    }

    public PDRecommendEntity(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.skuId = jDJSONObject.optString("skuId");
            this.jdPrice = jDJSONObject.optString("jprice");
            this.image = jDJSONObject.optString("image");
            this.expid = jDJSONObject.optString(PDConstant.EXTRA_EXPID);
            this.rid = jDJSONObject.optString(PDConstant.EXTRA_RID);
            this.name = jDJSONObject.optString("name");
            this.index = jDJSONObject.optString("index");
            this.clkUrl = jDJSONObject.optString("clkUrl");
            this.sv = jDJSONObject.optString("sv");
            this.isAd = jDJSONObject.optString("isAd");
            this.reqsig = jDJSONObject.optString("reqsig");
            this.abt = jDJSONObject.optString(JDMtaUtils.ABTKEY);
            this.exposureSourceValue = jDJSONObject.optString("exposureSourceValue");
            this.iconC = jDJSONObject.optString("iconC");
            this.stagesKinds = jDJSONObject.optString("stagesKinds");
            this.popUnit = jDJSONObject.optString("popUnit");
            this.popUrl = jDJSONObject.optString("popUrl");
            this.extension_id = jDJSONObject.optString("extension_id");
            this.resUR = jDJSONObject.optString("resUR");
            this.presaleInfo = jDJSONObject.optString("presaleInfo");
            this.presaleText = jDJSONObject.optString("presaleText");
            this.presaleTextColor = jDJSONObject.optString("presaleTextColor");
            this.icon1 = jDJSONObject.optString("icon1");
            this.icon2 = jDJSONObject.optString("icon2");
            this.sourceValue = jDJSONObject.optString("sourceValue");
            this.client_click_url = jDJSONObject.optString("client_click_url");
            this.client_exposal_url = jDJSONObject.optString("client_exposal_url");
            this.exposureJsonSourceValue = jDJSONObject.optString("exposureJsonSourceValue");
            this.salesVolume = jDJSONObject.optString("salesVolume");
            this.benefitTitle = jDJSONObject.optString("benefitTitle");
            this.benefitImageUrl = jDJSONObject.optString("benefitImageUrl");
            this.nonJdPriceText = jDJSONObject.optString("nonJdPriceText");
        }
    }

    public PDRecommendEntity(JDJSONObject jDJSONObject, String str) {
        if (jDJSONObject != null) {
            this.index = str;
            this.skuId = jDJSONObject.optString("wareId");
            this.jdPrice = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_JDPRICE);
            this.image = jDJSONObject.optString("imageurl");
            this.name = jDJSONObject.optString("wname");
            this.clkUrl = jDJSONObject.optString("clickUrl");
            this.sv = jDJSONObject.optString("sourceValue");
            this.isAd = jDJSONObject.optString("source");
            this.reqsig = jDJSONObject.optString("reqsig");
            this.abt = jDJSONObject.optString(JDMtaUtils.ABTKEY);
            this.sid = jDJSONObject.optString("sid");
            this.isPlusWare = jDJSONObject.optBoolean("isPlusWare");
            this.flow = jDJSONObject.optString("flow");
            this.source = jDJSONObject.optString("source");
            this.exposureSourceValue = jDJSONObject.optString("exposureSourceValue");
            this.iconC = jDJSONObject.optString("iconC");
            this.stagesKinds = jDJSONObject.optString("stagesKinds");
            this.popUnit = jDJSONObject.optString("popUnit");
            this.popUrl = jDJSONObject.optString("popUrl");
            this.extension_id = jDJSONObject.optString("extension_id");
            this.resUR = jDJSONObject.optString("resUR");
            this.presaleInfo = jDJSONObject.optString("presaleInfo");
            this.presaleText = jDJSONObject.optString("presaleText");
            this.presaleTextColor = jDJSONObject.optString("presaleTextColor");
            this.icon1 = jDJSONObject.optString("icon1");
            this.icon2 = jDJSONObject.optString("icon2");
            this.sourceValue = jDJSONObject.optString("sourceValue");
            this.client_click_url = jDJSONObject.optString("client_click_url");
            this.client_exposal_url = jDJSONObject.optString("client_exposal_url");
            this.exposureJsonSourceValue = jDJSONObject.optString("exposureJsonSourceValue");
            this.benefitTitle = jDJSONObject.optString("benefitTitle");
            this.benefitImageUrl = jDJSONObject.optString("benefitImageUrl");
            this.nonJdPriceText = jDJSONObject.optString("nonJdPriceText");
        }
    }
}
