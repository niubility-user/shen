package com.jingdong.common.recommend.entity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.json.anotation.JSONField;
import com.jingdong.common.deeplinkhelper.DeepLink3DProductHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.cart.CartPromotion;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class RecommendProduct {
    private static final String TAG = "RecommendProduct";
    public String abt;
    public String activityBtnUrl;
    public int afterPriceIconWidth;
    public JSONObject appendMtaJson;
    public JSONObject appendMtaJson_real;
    public String arrivalPriceBeltImg;
    public String beltBgImg;
    public String beltContent;
    public String beltTimeText;
    public String beltType;
    public String benefitImageUrl;
    public String benefitPoint;
    public String benefitPointTab;
    public String benefitTime;
    public String benefitTitle;
    public String brokerInfo;
    public String buttonAreaImgUrl;
    public String canAddCart;
    public boolean canClipTitleImg;
    public boolean canInteractive;
    public String canNegFeedback;
    public String cartSourceValue;
    public String category1;
    public String category2;
    public String category3;
    public String channelJumpUrl;
    public String channelUnderIcon;
    public String channelUnderName;
    public String channelUnderSourceValue;
    public String channelUnderUrl;
    public String client_click_url;
    public String client_exposal_url;
    public DoublePriceUp doublePriceUpMap;
    public int duration;
    public String exFxamount;
    public String expoUrl;
    public String exposureJsonSourceValue;
    public String exposureSourceValue;
    public String extension_id;
    public List<FeedBackReason> feedBackReason;
    public String feedBackStrategy;
    @JSONField(name = "sourceValueFeedback")
    public String feedbackSourceValue;
    public String flow;
    public String freeShipping;
    public String freeShippingTab;
    public String fxlabel;
    public String gbMemCount;
    public String gbPrice;
    public boolean hasBeenExposured;
    public boolean hasRealExpo;
    public String hideCart;
    public String hyjPrice;
    public String icon1;
    public String icon2;
    public List<RecommendIcon> icon2List;
    public String icon3;
    public String icon3Price;
    public String icon618;
    public String icon618Type;
    public String iconAd;
    public String iconC;
    public int imageurlType;
    @JSONField(name = "imageurl")
    public String imgUrl;
    public String impStyle;
    public String innerImg;
    public String innerWareBgImg;
    public String innerWareFont;
    public String interactive;
    public String isArrivalPriceBelt;
    public int isBackUp;
    public boolean isDotScheme;
    public String isJumpApp;
    public boolean isMonetized;
    public boolean isMtaValueChanged;
    public String isOpenApp;
    public boolean isPlusWare;
    public boolean isRecomInfoDown;
    public boolean isShowFeedbackUi;
    public String isShowTag;
    public String isSimilarGoods;
    public String isStoreGoods;
    public String jdPrice;
    public transient JDJSONObject jdjsonObject;
    public String jumpDesUrl;
    public JumpEntity jumpObj;
    public List<RecommendLabel> labelList;
    public String liveSkuStatus;
    public String longImageScale;
    @JSONField(name = "similarEnter")
    public String lookSimilar;
    public String lowestPriceDays;
    public String lowestPriceDaysTab;
    public String mainVideoId;
    public String mainVideoImg;
    public String markImg;
    @JSONField(name = "wname")
    public String name;
    public String nationImg;
    public String nationName;
    public NewCardStyle newCardMap;
    public String newCardType;
    public String newSingRow;
    public String nonJdPriceText;
    public String numOfSnapingup;
    public String p;
    public String playUrl;
    public String popUnit;
    public String popUrl;
    public String prePrice;
    public String preTitle;
    public String presaleInfo;
    public String presaleText;
    public String presaleTextColor;
    public String priceColor;
    public String priceIcon;
    public transient View productItemImage;
    public String purchasePrice;
    public String purchasePriceText;
    public String quickBuy;
    public String quickBuyJumpUrl;
    public String quickBuySourceValue;
    public String recomReason;
    public String recomReasonFontStyle;
    public String recomReasonIcon;
    public String recomReasonStyle;
    public String recomReasonTab;
    public String recomText;
    public String recomTips;
    public String recomTipsIndex;
    public RecommendVideo recommendVideo;
    public String[] relationSkus;
    public String reqsig;
    public transient JDJSONObject rootUETJson;
    public String saleCount;
    public String samPrice;
    public String secPriceType;
    public String seedIndex;
    public String seedPage;
    public String sid;
    @JSONField(name = "sourceValueSimilar")
    public String similarSourceValue;
    public String source;
    public String sourceTag;
    public String sourceValue;
    public String spu;
    public String stageDescription;
    public String stagesKinds;
    public String staticIcon;
    public String storeDistanceText;
    public String storeName;
    public String storeTimeTab;
    public String storeTimeText;
    public List<RecommendTag> symbolList;
    public String symbolTab;
    public String tagIds;
    @JSONField(name = "clickUrl")
    public String targetUrl;
    public RecommendTendency tendency;
    public TitleOpt titleOpt;
    public String totalBenefit;
    public ArrayList<String> totalBenefitBgColor;
    public String totalBenefitColor;
    public int[] totalBenefitColorLocal;
    public String totalBenefitText;
    public String totalBenefitTextColor;
    public String trackRecomTips;
    public String underlinePrice;
    public String videoBtnUrl3X;
    public String wareBgUrl;
    public String wareId;
    public String wareTitle;
    public List<RecommendLabel> worthList;

    /* loaded from: classes6.dex */
    public static class DoublePriceUp {
        public String bybt;
        public String doublePrice;
        public String doublePriceColor;
        public String doublePriceResourceCode;
        public String doublePriceResourceCodeDegrade;
        public String doublePriceText;
        public String doublePriceUpNewFlag;
        public String jdPriceColor;
        public String notSfStyle;
        public String secPriceType;
        public String secondPrice;
    }

    /* loaded from: classes6.dex */
    public static class NewCardStyle {
        public String backgroundImg;
        public String icon;
        public String jdPriceColor;
        public String subPriceColor;
    }

    public RecommendProduct() {
        this.feedBackStrategy = "A";
        this.isOpenApp = "1";
        this.isBackUp = 0;
        this.canClipTitleImg = true;
        this.tagIds = "-100";
        this.canInteractive = true;
        this.isMtaValueChanged = false;
        this.hasBeenExposured = false;
        this.jdjsonObject = null;
        this.newSingRow = "0";
        this.isRecomInfoDown = false;
        this.appendMtaJson_real = new JSONObject();
        this.appendMtaJson = new JSONObject();
    }

    public boolean firstPriceShowDoubleUp() {
        DoublePriceUp doublePriceUp = this.doublePriceUpMap;
        return (doublePriceUp == null || "1".equals(doublePriceUp.notSfStyle)) ? false : true;
    }

    public void generateLocalColors() {
        ArrayList<String> arrayList = this.totalBenefitBgColor;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.totalBenefitColorLocal = new int[this.totalBenefitBgColor.size()];
        for (int i2 = 0; i2 < this.totalBenefitBgColor.size(); i2++) {
            try {
                this.totalBenefitColorLocal[i2] = Color.parseColor(this.totalBenefitBgColor.get(i2));
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                    return;
                }
                return;
            }
        }
    }

    public String getDoubleUpPrice() {
        return getDoubleUpPrice(true);
    }

    public String getJdPrice() {
        return getJdPrice(true);
    }

    public String getMainSku() {
        String[] strArr = this.relationSkus;
        return (strArr == null || strArr.length <= 0) ? "" : strArr[0];
    }

    public String getName() {
        TitleOpt titleOpt = this.titleOpt;
        if (titleOpt != null && !TextUtils.isEmpty(titleOpt.title)) {
            return this.titleOpt.title;
        }
        if (!TextUtils.isEmpty(this.name)) {
            return this.name;
        }
        return JdSdk.getInstance().getApplication().getString(R.string.recommend_product_no_name);
    }

    public String getPrice(String str) {
        String string = JdSdk.getInstance().getApplication().getString(R.string.recommend_product_no_price);
        if (TextUtils.isEmpty(str)) {
            return string;
        }
        try {
            double doubleValue = Double.valueOf(str).doubleValue();
            return doubleValue > 0.0d ? String.format("%.2f", Double.valueOf(doubleValue)) : string;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return string;
            }
            return string;
        }
    }

    public String getSecondPrice() {
        String string = JdSdk.getInstance().getApplication().getString(R.string.recommend_product_no_price);
        if (TextUtils.isEmpty(this.icon3Price)) {
            return string;
        }
        try {
            double doubleValue = Double.valueOf(this.icon3Price).doubleValue();
            return doubleValue > 0.0d ? String.format("%.2f", Double.valueOf(doubleValue)) : string;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return string;
            }
            return string;
        }
    }

    public String getUnderLinePrice() {
        String string = JdSdk.getInstance().getApplication().getString(R.string.recommend_product_no_price);
        if (TextUtils.isEmpty(this.underlinePrice)) {
            return string;
        }
        try {
            double doubleValue = Double.valueOf(this.underlinePrice).doubleValue();
            return doubleValue > 0.0d ? String.format("%.2f", Double.valueOf(doubleValue)) : string;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return string;
            }
            return string;
        }
    }

    public RecommendVideo getVideoData() {
        if (this.recommendVideo == null) {
            RecommendVideo recommendVideo = new RecommendVideo();
            this.recommendVideo = recommendVideo;
            recommendVideo.wareId = this.wareId;
            recommendVideo.reqsig = this.reqsig;
            recommendVideo.playUrl = this.playUrl;
            recommendVideo.isProduct = 1;
            String str = this.imgUrl;
            recommendVideo.img = str;
            recommendVideo.imageurl = str;
            recommendVideo.videoDuration = String.valueOf(this.duration);
            this.recommendVideo.brokerInfo = this.brokerInfo;
        }
        return this.recommendVideo;
    }

    public boolean hasAddCartButton() {
        return !TextUtils.isEmpty(this.canAddCart) && "1".equals(this.canAddCart);
    }

    public boolean hasButton() {
        return !TextUtils.isEmpty(this.lookSimilar) && "1".equals(this.lookSimilar);
    }

    public boolean hasChannelUnder() {
        return (TextUtils.isEmpty(this.channelUnderIcon) || TextUtils.isEmpty(this.channelUnderName) || TextUtils.isEmpty(this.channelUnderUrl)) ? false : true;
    }

    public boolean hasRecommendReason() {
        return (TextUtils.isEmpty(this.recomReasonTab) || TextUtils.isEmpty(this.recomReason)) ? false : true;
    }

    public boolean isAd() {
        return "1".equals(this.source);
    }

    public boolean isCanNegFeedback() {
        return !TextUtils.isEmpty(this.canNegFeedback) && "1".equals(this.canNegFeedback);
    }

    public boolean isNewPlusCard() {
        return "1".equals(this.newCardType);
    }

    public boolean isNewRecommendStyle() {
        return "1".equals(this.recomReasonStyle);
    }

    public boolean isNewUserCard() {
        return this.newCardMap != null && "2".equals(this.newCardType);
    }

    public boolean isO2OProduct() {
        return "1".equals(this.isStoreGoods);
    }

    public boolean isPrice2Style() {
        DoublePriceUp doublePriceUp = this.doublePriceUpMap;
        return doublePriceUp != null && "1".equals(doublePriceUp.doublePriceUpNewFlag);
    }

    public boolean isSecondPriceNewStyle() {
        DoublePriceUp doublePriceUp = this.doublePriceUpMap;
        return doublePriceUp != null && "1".equals(doublePriceUp.doublePriceUpNewFlag);
    }

    public boolean isShowDot() {
        return "1".equals(this.source);
    }

    public boolean isSingleRow() {
        List<RecommendIcon> list = this.icon2List;
        if (list == null || list.size() <= 1) {
            if ((TextUtils.isEmpty(this.icon1) || TextUtils.isEmpty(this.icon2)) && "1".equals(this.newSingRow)) {
                TitleOpt titleOpt = this.titleOpt;
                return titleOpt == null || 1 == titleOpt.truncation;
            }
            return false;
        }
        return false;
    }

    public void setRootUETJson(JDJSONObject jDJSONObject) {
        this.rootUETJson = jDJSONObject;
    }

    public boolean showAdDot() {
        return "1".equals(this.source) && !this.isMonetized;
    }

    public boolean showCartButton() {
        if (isO2OProduct() || isPrice2Style()) {
            return false;
        }
        DoublePriceUp doublePriceUp = this.doublePriceUpMap;
        if (doublePriceUp == null || !"1".equals(doublePriceUp.bybt)) {
            return hasAddCartButton();
        }
        return false;
    }

    public boolean showMonetizedDot() {
        return "1".equals(this.source) && this.isMonetized;
    }

    public boolean showSimilarButton() {
        if (isO2OProduct() || isPrice2Style()) {
            return false;
        }
        DoublePriceUp doublePriceUp = this.doublePriceUpMap;
        return (doublePriceUp == null || !"1".equals(doublePriceUp.bybt)) && hasButton() && !isNewPlusCard() && !isNewUserCard();
    }

    public String getDoubleUpPrice(boolean z) {
        String string = JdSdk.getInstance().getApplication().getString(R.string.recommend_product_no_price);
        DoublePriceUp doublePriceUp = this.doublePriceUpMap;
        if (doublePriceUp == null || TextUtils.isEmpty(doublePriceUp.doublePrice)) {
            return string;
        }
        try {
            double doubleValue = Double.valueOf(this.doublePriceUpMap.doublePrice).doubleValue();
            if (doubleValue > 0.0d) {
                return z ? String.format("%.2f", Double.valueOf(doubleValue)) : this.doublePriceUpMap.doublePrice;
            }
            return string;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return string;
            }
            return string;
        }
    }

    public String getJdPrice(boolean z) {
        String illegalPrice = RecommendUtils.getIllegalPrice();
        if (TextUtils.isEmpty(this.jdPrice)) {
            return illegalPrice;
        }
        try {
            double doubleValue = Double.valueOf(this.jdPrice).doubleValue();
            if (doubleValue > 0.0d) {
                return z ? String.format("%.2f", Double.valueOf(doubleValue)) : this.jdPrice;
            }
            return illegalPrice;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return illegalPrice;
            }
            return illegalPrice;
        }
    }

    public RecommendProduct(JSONObjectProxy jSONObjectProxy) {
        this.feedBackStrategy = "A";
        this.isOpenApp = "1";
        this.isBackUp = 0;
        this.canClipTitleImg = true;
        this.tagIds = "-100";
        this.canInteractive = true;
        this.isMtaValueChanged = false;
        this.hasBeenExposured = false;
        this.jdjsonObject = null;
        this.newSingRow = "0";
        this.isRecomInfoDown = false;
        this.appendMtaJson_real = new JSONObject();
        this.appendMtaJson = new JSONObject();
        this.wareId = jSONObjectProxy.optString("wareId");
        this.name = jSONObjectProxy.optString("wname");
        this.jdPrice = jSONObjectProxy.optString(JshopConst.JSKEY_PRODUCT_JDPRICE);
        this.imgUrl = jSONObjectProxy.optString("imageurl");
        this.sourceValue = jSONObjectProxy.optString("sourceValue", "");
        this.similarSourceValue = jSONObjectProxy.optString("sourceValueSimilar", "");
        this.feedbackSourceValue = jSONObjectProxy.optString("sourceValueFeedback", "");
        this.lookSimilar = jSONObjectProxy.optString("similarEnter");
        this.canAddCart = jSONObjectProxy.optString(DeepLink3DProductHelper.EXTRA_CAN_ADD_CART);
        this.targetUrl = jSONObjectProxy.optString("client_click_url");
        this.recomText = jSONObjectProxy.optString("recomText");
        this.canNegFeedback = jSONObjectProxy.optString("canNegFeedback");
        this.source = jSONObjectProxy.optString("source");
        this.samPrice = jSONObjectProxy.optString("samPrice");
        this.reqsig = jSONObjectProxy.optString("reqsig");
        this.abt = jSONObjectProxy.optString(JDMtaUtils.ABTKEY);
        this.cartSourceValue = jSONObjectProxy.optString("cartSourceValue");
        this.isDotScheme = jSONObjectProxy.optBoolean("isDotScheme", false);
        this.presaleText = jSONObjectProxy.optString("presaleText");
        this.presaleTextColor = jSONObjectProxy.optString("presaleTextColor");
        this.priceColor = jSONObjectProxy.optString(CartPromotion.KEY_PRICECOLOR);
        this.priceIcon = jSONObjectProxy.optString(CartPromotion.KEY_PRICEICON);
        this.recomTips = jSONObjectProxy.optString("recomTips");
        this.sid = jSONObjectProxy.optString("sid");
        this.icon1 = jSONObjectProxy.optString("icon1");
        this.icon2 = jSONObjectProxy.optString("icon2");
        this.icon3 = jSONObjectProxy.optString("icon3");
        this.iconC = jSONObjectProxy.optString("iconC");
        this.recomReason = jSONObjectProxy.optString("recomReason");
        this.recomReasonTab = jSONObjectProxy.optString("recomReasonTab");
        this.icon3Price = jSONObjectProxy.optString("icon3Price");
        this.flow = jSONObjectProxy.optString("flow");
        this.isPlusWare = jSONObjectProxy.optBoolean("isPlusWare", false);
        this.presaleInfo = jSONObjectProxy.optString("presaleInfo");
        this.icon618 = jSONObjectProxy.optString("icon618");
        this.exposureSourceValue = jSONObjectProxy.optString("exposureSourceValue");
        this.benefitTime = jSONObjectProxy.optString("benefitTime");
        this.benefitTitle = jSONObjectProxy.optString("benefitTitle");
        this.prePrice = jSONObjectProxy.optString("prePrice");
        this.preTitle = jSONObjectProxy.optString("preTitle");
        this.underlinePrice = jSONObjectProxy.optString("underlinePrice");
        this.stagesKinds = jSONObjectProxy.optString("stagesKinds");
        this.stageDescription = jSONObjectProxy.optString("stageDescription");
        this.mainVideoId = jSONObjectProxy.optString("mainVideoId");
        this.popUnit = jSONObjectProxy.optString("popUnit");
        this.popUrl = jSONObjectProxy.optString("popUrl");
        if (jSONObjectProxy.has("feedBackReason")) {
            try {
                this.feedBackReason = JDJSON.parseArray(jSONObjectProxy.getString("feedBackReason"), FeedBackReason.class);
            } catch (JSONException e2) {
                OKLog.e(TAG, e2);
            }
        }
        this.isSimilarGoods = jSONObjectProxy.optString("isSimilarGoods");
        this.imageurlType = jSONObjectProxy.optInt("imageurlType", 0);
        this.channelUnderIcon = jSONObjectProxy.optString("channelUnderIcon");
        this.channelUnderName = jSONObjectProxy.optString("channelUnderName");
        this.channelUnderUrl = jSONObjectProxy.optString("channelUnderUrl");
        this.channelUnderSourceValue = jSONObjectProxy.optString("channelUnderSourceValue");
        this.isOpenApp = jSONObjectProxy.optString("isOpenApp");
        this.wareBgUrl = jSONObjectProxy.optString("wareBgUrl");
        this.wareTitle = jSONObjectProxy.optString("wareTitle");
        this.channelJumpUrl = jSONObjectProxy.optString("channelJumpUrl");
        this.spu = jSONObjectProxy.optString("spu");
        this.category3 = jSONObjectProxy.optString("category3");
        this.symbolTab = jSONObjectProxy.optString("symbolTab");
        this.nationName = jSONObjectProxy.optString("nationName");
        this.nationImg = jSONObjectProxy.optString("nationImg");
        this.nonJdPriceText = jSONObjectProxy.optString("nonJdPriceText");
        this.isJumpApp = jSONObjectProxy.optString("isJumpApp");
        this.jumpDesUrl = jSONObjectProxy.optString("jumpDesUrl");
        if (jSONObjectProxy.has("symbolList")) {
            try {
                this.symbolList = JDJSON.parseArray(jSONObjectProxy.getString("symbolList"), RecommendTag.class);
            } catch (JSONException unused) {
            }
        }
        if (jSONObjectProxy.has("labelList")) {
            try {
                this.labelList = JDJSON.parseArray(jSONObjectProxy.getString("labelList"), RecommendLabel.class);
            } catch (JSONException unused2) {
            }
        }
        if (jSONObjectProxy.has("worthList")) {
            try {
                this.worthList = JDJSON.parseArray(jSONObjectProxy.getString("worthList"), RecommendLabel.class);
            } catch (JSONException unused3) {
            }
        }
        this.isMonetized = jSONObjectProxy.optBoolean("isMonetized", false);
        this.innerImg = jSONObjectProxy.optString("innerImg");
        this.impStyle = jSONObjectProxy.optString("impStyle");
        this.innerWareFont = jSONObjectProxy.optString("innerWareFont");
        this.innerWareBgImg = jSONObjectProxy.optString("innerWareBgImg");
        this.p = jSONObjectProxy.optString("p");
        this.brokerInfo = jSONObjectProxy.optString("brokerInfo");
        if (jSONObjectProxy.optJSONArray("icon2List") != null) {
            try {
                this.icon2List = JDJSON.parseArray(jSONObjectProxy.getString("icon2List"), RecommendIcon.class);
            } catch (JSONException unused4) {
            }
        }
        this.hideCart = jSONObjectProxy.optString("hideCart");
    }

    public RecommendProduct(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.feedBackStrategy = "A";
        this.isOpenApp = "1";
        this.isBackUp = 0;
        this.canClipTitleImg = true;
        this.tagIds = "-100";
        this.canInteractive = true;
        this.isMtaValueChanged = false;
        this.hasBeenExposured = false;
        this.jdjsonObject = null;
        this.newSingRow = "0";
        this.isRecomInfoDown = false;
        this.appendMtaJson_real = new JSONObject();
        this.appendMtaJson = new JSONObject();
        this.wareId = str;
        this.name = str2;
        this.jdPrice = str3;
        this.sourceValue = str4;
        this.imgUrl = str5;
        this.lookSimilar = str6;
        this.canAddCart = str7;
        this.targetUrl = str8;
    }

    public RecommendProduct(String str, String str2) {
        this.feedBackStrategy = "A";
        this.isOpenApp = "1";
        this.isBackUp = 0;
        this.canClipTitleImg = true;
        this.tagIds = "-100";
        this.canInteractive = true;
        this.isMtaValueChanged = false;
        this.hasBeenExposured = false;
        this.jdjsonObject = null;
        this.newSingRow = "0";
        this.isRecomInfoDown = false;
        this.appendMtaJson_real = new JSONObject();
        this.appendMtaJson = new JSONObject();
        this.wareId = str;
        this.imgUrl = str2;
    }

    public RecommendProduct(String str, String str2, String str3, String str4, String str5, String str6) {
        this.feedBackStrategy = "A";
        this.isOpenApp = "1";
        this.isBackUp = 0;
        this.canClipTitleImg = true;
        this.tagIds = "-100";
        this.canInteractive = true;
        this.isMtaValueChanged = false;
        this.hasBeenExposured = false;
        this.jdjsonObject = null;
        this.newSingRow = "0";
        this.isRecomInfoDown = false;
        this.appendMtaJson_real = new JSONObject();
        this.appendMtaJson = new JSONObject();
        this.wareId = str;
        this.name = str2;
        this.imgUrl = str3;
        this.jdPrice = str4;
        this.isOpenApp = str5;
        this.channelJumpUrl = str6;
    }

    public RecommendProduct(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        this.feedBackStrategy = "A";
        this.isOpenApp = "1";
        this.isBackUp = 0;
        this.canClipTitleImg = true;
        this.tagIds = "-100";
        this.canInteractive = true;
        this.isMtaValueChanged = false;
        this.hasBeenExposured = false;
        this.jdjsonObject = null;
        this.newSingRow = "0";
        this.isRecomInfoDown = false;
        this.appendMtaJson_real = new JSONObject();
        this.appendMtaJson = new JSONObject();
        this.wareId = str;
        this.name = str2;
        this.imgUrl = str3;
        this.jdPrice = str4;
        this.isOpenApp = str5;
        this.channelJumpUrl = str6;
        this.client_exposal_url = str7;
        this.client_click_url = str8;
        this.exposureJsonSourceValue = str9;
        this.sourceValue = str10;
        this.extension_id = str11;
        this.sourceTag = str12;
    }
}
