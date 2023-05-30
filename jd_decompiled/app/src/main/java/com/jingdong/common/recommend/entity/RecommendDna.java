package com.jingdong.common.recommend.entity;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.anotation.JSONField;
import com.jingdong.common.address.AddressConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RecommendDna extends RecommendMaterialData {
    public String buttonImg;
    public String channelJumpUrl;
    public String couponIcon;
    public String couponNumText;
    public String crownPicUrl;
    public String description;
    public String descriptionMore;
    @JSONField(name = "wareId")
    public String dnaId;
    @JSONField(name = "wname")
    public String dnaName;
    public boolean expoNumAdd;
    public String exposureJsonSourceValue;
    public String exposureSourceValue;
    public boolean feminine;
    public String fontColor;
    public String goCouponBtnImg;
    public String heightStyle;
    public String icon618;
    public String imageurl;
    public int imageurlType;
    public String img;
    public int isBackUp;
    public boolean isNewBanner;
    public String isOpenApp;
    public String jump;
    public String markedImg;
    public String mergePicLeftLineUrl;
    public String mergePicMainTitle;
    public String mergePicRightLineUrl;
    public String mergePicUrl;
    public String nonWareIcon;
    public int opmShowTimes;
    public String picNum;
    public String source;
    public String sourceValue;
    public String themeBgcolorEnd;
    public String themeBgcolorStart;
    @JSONField(name = "subWareList")
    public List<RecommendProduct> wareList;
    public String wname;

    public RecommendDna() {
        this.isOpenApp = "1";
        this.expoNumAdd = true;
        this.opmShowTimes = 0;
        this.isBackUp = 0;
        this.isNewBanner = true;
    }

    public void dealBannerData() {
        List<RecommendProduct> list;
        if (TextUtils.isEmpty(this.markedImg) || (list = this.wareList) == null || list.isEmpty()) {
            return;
        }
        for (RecommendProduct recommendProduct : this.wareList) {
            if (recommendProduct != null && "1".equals(recommendProduct.isShowTag)) {
                recommendProduct.markImg = this.markedImg;
            }
        }
    }

    public String getDnaName() {
        return TextUtils.isEmpty(this.dnaName) ? "" : this.dnaName;
    }

    public String getSkus() {
        StringBuilder sb = new StringBuilder();
        if (this.wareList != null) {
            for (int i2 = 0; i2 < this.wareList.size(); i2++) {
                if (this.wareList.get(i2) != null) {
                    if (sb.length() > 0) {
                        sb.append(DYConstants.DY_REGEX_COMMA);
                    }
                    sb.append(this.wareList.get(i2).wareId);
                    if (i2 >= 3) {
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }

    public boolean isShowDot() {
        return "1".equals(this.source);
    }

    public RecommendDna(JSONObjectProxy jSONObjectProxy) {
        int length;
        this.isOpenApp = "1";
        this.expoNumAdd = true;
        this.opmShowTimes = 0;
        this.isBackUp = 0;
        this.isNewBanner = true;
        this.dnaId = jSONObjectProxy.optString("wareId");
        this.dnaName = jSONObjectProxy.optString("wname");
        this.icon618 = jSONObjectProxy.optString("icon618");
        this.sourceValue = jSONObjectProxy.optString("sourceValue");
        this.exposureSourceValue = jSONObjectProxy.optString("exposureSourceValue");
        this.description = jSONObjectProxy.optString("description");
        this.imageurl = jSONObjectProxy.optString("imageurl");
        this.descriptionMore = jSONObjectProxy.optString("descriptionMore");
        this.channelJumpUrl = jSONObjectProxy.optString("channelJumpUrl");
        this.mergePicUrl = jSONObjectProxy.optString("mergePicUrl");
        this.wname = jSONObjectProxy.optString("wname");
        this.crownPicUrl = jSONObjectProxy.optString("crownPicUrl");
        this.isOpenApp = jSONObjectProxy.optString("isOpenApp", "1");
        this.fontColor = jSONObjectProxy.optString("fontColor");
        this.buttonImg = jSONObjectProxy.optString("buttonImg");
        this.source = jSONObjectProxy.optString("source");
        this.themeBgcolorStart = jSONObjectProxy.optString("themeBgcolorStart");
        this.themeBgcolorEnd = jSONObjectProxy.optString("themeBgcolorEnd");
        JSONArrayPoxy jSONArrayOrNull = jSONObjectProxy.getJSONArrayOrNull("subWareList");
        if (jSONArrayOrNull != null && (length = jSONArrayOrNull.length()) > 0) {
            this.wareList = new ArrayList();
            for (int i2 = 0; i2 < length; i2++) {
                JSONObjectProxy jSONObjectOrNull = jSONArrayOrNull.getJSONObjectOrNull(i2);
                if (jSONObjectOrNull != null) {
                    this.wareList.add(new RecommendProduct(jSONObjectOrNull.optString("wareId"), jSONObjectOrNull.optString("wareTitle"), jSONObjectOrNull.optString("imageUrl"), jSONObjectOrNull.optString(JshopConst.JSKEY_PRODUCT_JDPRICE), jSONObjectOrNull.optString("isOpenApp"), jSONObjectOrNull.optString("channelJumpUrl"), jSONObjectOrNull.optString("client_exposal_url"), jSONObjectOrNull.optString("client_click_url"), jSONObjectOrNull.optString("exposureJsonSourceValue"), jSONObjectOrNull.optString("sourceValue"), jSONObjectOrNull.optString("extension_id"), jSONObjectOrNull.optString(AddressConstant.ADDRESS_LIST_SOURCE_TAG_KEY)));
                }
            }
        }
        this.imageurlType = jSONObjectProxy.optInt("imageurlType", 0);
    }
}
