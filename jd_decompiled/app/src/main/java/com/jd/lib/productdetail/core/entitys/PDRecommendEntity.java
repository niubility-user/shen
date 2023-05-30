package com.jd.lib.productdetail.core.entitys;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeepLink3DProductHelper;
import com.jingdong.common.recommend.entity.RecommendLabel;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDRecommendEntity {
    public String abt;
    public String arrivePriceIconcColor;
    public String arrivePriceIconcText;
    public String benefitImageUrl;
    public String benefitTitle;
    public String canAddCart;
    public String cartSourceValue;
    public String channelJumpUrl;
    public String client_click_url;
    public String client_exposal_url;
    public String clkUrl;
    public RecommendProduct.DoublePriceUp doublePriceUpMap;
    public String expid;
    public JSONObject expoJsonObject;
    public String exposureJsonSourceValue;
    public String exposureSourceValue;
    public String extension_id;
    public String flow;
    public boolean hasRealExpo = false;
    public String icon1;
    public String icon2;
    public String iconAd;
    public String iconC;
    public String image;
    public String index;
    public String isAd;
    public String isJumpApp;
    public boolean isMtaValueChanged;
    public String isOpenApp;
    public boolean isPlusWare;
    public String jdPrice;
    public String jumpDesUrl;
    public List<RecommendLabel> labelList;
    public String name;
    public String newGoodsIcon2Color;
    public String newGoodsIcon2Text;
    public String nonJdPriceText;
    public String popUnit;
    public String popUrl;
    public String presaleInfo;
    public String presaleText;
    public String presaleTextColor;
    public String purchasePrice;
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
    public String storeId;
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
            this.newGoodsIcon2Text = jDJSONObject.optString("newGoodsIcon2Text");
            this.newGoodsIcon2Color = jDJSONObject.optString("newGoodsIcon2Color");
            this.canAddCart = jDJSONObject.optString(DeepLink3DProductHelper.EXTRA_CAN_ADD_CART);
            this.storeId = jDJSONObject.getString("storeId");
            this.cartSourceValue = jDJSONObject.optString("cartSourceValue");
            this.sourceValue = jDJSONObject.optString("sourceValue");
            this.client_click_url = jDJSONObject.optString("client_click_url");
            this.client_exposal_url = jDJSONObject.optString("client_exposal_url");
            String optString = jDJSONObject.optString("exposureJsonSourceValue");
            this.exposureJsonSourceValue = optString;
            try {
                if (!TextUtils.isEmpty(optString)) {
                    this.expoJsonObject = new JSONObject(this.exposureJsonSourceValue);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.salesVolume = jDJSONObject.optString("salesVolume");
            this.benefitTitle = jDJSONObject.optString("benefitTitle");
            this.benefitImageUrl = jDJSONObject.optString("benefitImageUrl");
            this.nonJdPriceText = jDJSONObject.optString("nonJdPriceText");
            this.isJumpApp = jDJSONObject.optString("isJumpApp");
            this.jumpDesUrl = jDJSONObject.optString("jumpDesUrl");
            this.purchasePrice = jDJSONObject.optString("purchasePrice");
            this.arrivePriceIconcText = jDJSONObject.optString("arrivePriceIconcText");
            this.arrivePriceIconcColor = jDJSONObject.optString("arrivePriceIconcColor");
            this.iconAd = jDJSONObject.optString("iconAd");
            String optString2 = jDJSONObject.optString("labelList");
            if (!TextUtils.isEmpty(optString2)) {
                this.labelList = JDJSON.parseArray(optString2, RecommendLabel.class);
            }
            String optString3 = jDJSONObject.optString("doublePriceUpMap");
            if (!TextUtils.isEmpty(optString3)) {
                this.doublePriceUpMap = (RecommendProduct.DoublePriceUp) JDJSON.parseObject(optString3, RecommendProduct.DoublePriceUp.class);
            }
            this.channelJumpUrl = jDJSONObject.optString("channelJumpUrl");
            this.isOpenApp = jDJSONObject.optString("isOpenApp");
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
            this.newGoodsIcon2Text = jDJSONObject.optString("newGoodsIcon2Text");
            this.newGoodsIcon2Color = jDJSONObject.optString("newGoodsIcon2Color");
            this.canAddCart = jDJSONObject.optString(DeepLink3DProductHelper.EXTRA_CAN_ADD_CART);
            this.storeId = jDJSONObject.getString("storeId");
            this.cartSourceValue = jDJSONObject.optString("cartSourceValue");
            this.sourceValue = jDJSONObject.optString("sourceValue");
            this.client_click_url = jDJSONObject.optString("client_click_url");
            this.client_exposal_url = jDJSONObject.optString("client_exposal_url");
            String optString = jDJSONObject.optString("exposureJsonSourceValue");
            this.exposureJsonSourceValue = optString;
            try {
                if (!TextUtils.isEmpty(optString)) {
                    this.expoJsonObject = new JSONObject(this.exposureJsonSourceValue);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.benefitTitle = jDJSONObject.optString("benefitTitle");
            this.benefitImageUrl = jDJSONObject.optString("benefitImageUrl");
            this.nonJdPriceText = jDJSONObject.optString("nonJdPriceText");
            this.isJumpApp = jDJSONObject.optString("isJumpApp");
            this.jumpDesUrl = jDJSONObject.optString("jumpDesUrl");
            this.purchasePrice = jDJSONObject.optString("purchasePrice");
            this.arrivePriceIconcText = jDJSONObject.optString("arrivePriceIconcText");
            this.arrivePriceIconcColor = jDJSONObject.optString("arrivePriceIconcColor");
            this.iconAd = jDJSONObject.optString("iconAd");
            String optString2 = jDJSONObject.optString("labelList");
            if (!TextUtils.isEmpty(optString2)) {
                this.labelList = JDJSON.parseArray(optString2, RecommendLabel.class);
            }
            String optString3 = jDJSONObject.optString("doublePriceUpMap");
            if (!TextUtils.isEmpty(optString3)) {
                this.doublePriceUpMap = (RecommendProduct.DoublePriceUp) JDJSON.parseObject(optString3, RecommendProduct.DoublePriceUp.class);
            }
            this.channelJumpUrl = jDJSONObject.optString("channelJumpUrl");
            this.isOpenApp = jDJSONObject.optString("isOpenApp");
        }
    }
}
