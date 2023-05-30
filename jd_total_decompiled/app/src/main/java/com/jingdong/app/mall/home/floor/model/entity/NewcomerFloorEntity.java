package com.jingdong.app.mall.home.floor.model.entity;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.category.floor.base.d;
import com.jingdong.app.mall.home.category.floor.feedssub.a;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.cart.CartPromotion;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class NewcomerFloorEntity extends FloorEntity {
    private boolean isHeightFloor;
    private JDJSONArray mData;
    private boolean isRequestAsyncData = true;
    private boolean isShow = true;
    private JSONArray expoArray = b.d();

    /* loaded from: classes4.dex */
    public static class NewcomerBaseModel extends com.jingdong.app.mall.home.r.e.b {
        public static final int DEF_COLOR = -16777216;
        public String expoJson;
        public String img;
        public JumpEntity jump;
        public int moduleSize;
        public int positionType;

        public NewcomerBaseModel(JDJSONObject jDJSONObject) {
            super(jDJSONObject);
            this.positionType = getJsonInt("positionType");
            this.moduleSize = getJsonInt("moduleSize");
            this.img = getJsonString("img");
            this.expoJson = getJsonString("expoJson");
            try {
                JDJSONObject jSONObject = this.srcJson.getJSONObject("jump");
                if (jSONObject != null) {
                    this.jump = (JumpEntity) jSONObject.toJavaObject(JumpEntity.class);
                }
            } catch (Exception e2) {
                f.s0(this, e2);
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class NewcomerCardModel extends NewcomerBaseModel {
        public int[] btnColor;
        public String cardBenefit;
        private final String cardBtnText1;
        private final String cardBtnText2;
        public String cardInfo;
        public String cardPrice;
        private final String couponBenefit1;
        private final String couponBenefit2;
        private final String couponInfo1;
        private final String couponInfo2;
        public int couponTextColor;
        public int[] priceColor;
        public int selectedTextColor;

        public NewcomerCardModel(JDJSONObject jDJSONObject) {
            super(jDJSONObject);
            this.couponInfo1 = getJsonString("couponInfo1");
            this.couponBenefit1 = getJsonString("couponBenefit1");
            this.couponInfo2 = getJsonString("couponInfo2");
            this.couponBenefit2 = getJsonString("couponBenefit2");
            this.cardBtnText1 = getJsonString("cardBtnText1");
            this.cardBtnText2 = getJsonString("cardBtnText2");
            this.cardInfo = getJsonString("cardInfo");
            this.cardBenefit = getJsonString("cardBenefit");
            this.cardPrice = getJsonString("cardPrice");
            this.selectedTextColor = m.j(getJsonString("selectedTextColor"), -16777216);
            this.btnColor = m.o(getJsonString("btnColor"), -16777216);
            this.couponTextColor = m.j(getJsonString("couponTextColor"), -16777216);
            this.priceColor = m.o(getJsonString(CartPromotion.KEY_PRICECOLOR), -16777216);
        }

        public String getCardBtnText(int i2) {
            return i2 == 1 ? this.cardBtnText1 : this.cardBtnText2;
        }

        public String getCouponBenefit(int i2) {
            return i2 == 1 ? this.couponBenefit1 : this.couponBenefit2;
        }

        public String getCouponInfo(int i2) {
            return i2 == 1 ? this.couponInfo1 : this.couponInfo2;
        }
    }

    /* loaded from: classes4.dex */
    public static class NewcomerOrderModel extends NewcomerBaseModel {
        public int fontColor;
        public String iconImg;
        public JumpEntity iconJumpUrl;
        public String info0;
        public String info1;
        public String info2;
        public String infoText0;
        public String infoText1;
        public String infoText2;
        public int orderNum;

        public NewcomerOrderModel(JDJSONObject jDJSONObject) {
            super(jDJSONObject);
            this.orderNum = getJsonInt("orderNum");
            this.info0 = getJsonString("info0");
            this.info1 = getJsonString("info1");
            this.info2 = getJsonString("info2");
            this.infoText0 = getJsonString("infoText0");
            this.infoText1 = getJsonString("infoText1");
            this.infoText2 = getJsonString("infoText2");
            this.fontColor = m.j(getJsonString("fontColor"), -16777216);
            this.iconImg = getJsonString("iconImg");
            try {
                JDJSONObject jSONObject = this.srcJson.getJSONObject("iconJumpUrl");
                if (jSONObject != null) {
                    this.iconJumpUrl = (JumpEntity) jSONObject.toJavaObject(JumpEntity.class);
                }
            } catch (Exception e2) {
                f.s0(this, e2);
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class NewcomerRedPacketModel extends NewcomerBaseModel {
        public int[] btnColor;
        public String btnText;
        public String info;
        public String infoDetail;
        public String infoText;
        public int selectedTextColor;

        public NewcomerRedPacketModel(JDJSONObject jDJSONObject) {
            super(jDJSONObject);
            this.info = getJsonString("info");
            this.infoDetail = getJsonString("infoDetail");
            this.infoText = getJsonString("infoText");
            this.btnText = getJsonString("btnText");
            this.selectedTextColor = m.j(getJsonString("selectedTextColor"), -16777216);
            this.btnColor = m.o(getJsonString("btnColor"), -16777216);
        }
    }

    /* loaded from: classes4.dex */
    public static class NewcomerSkuModel extends NewcomerBaseModel {
        public String skuTagImg;
        public String skuText;
        public int[] textColor;

        public NewcomerSkuModel(JDJSONObject jDJSONObject) {
            super(jDJSONObject);
            this.skuTagImg = getJsonString("skuTagImg");
            this.skuText = getJsonString("skuText");
            this.textColor = m.o(getJsonString(DYConstants.DY_TEXT_COLOR), -16777216);
        }
    }

    public static SpannableString getPriceSpan(String str, String str2, float f2, float f3) {
        String e2 = a.e(str);
        SpannableString spannableString = new SpannableString(e2 + str2);
        if (TextUtils.isEmpty(spannableString)) {
            return spannableString;
        }
        if (!TextUtils.isEmpty(e2)) {
            spannableString.setSpan(new RelativeSizeSpan(f2), 0, 1, 17);
        }
        if (!TextUtils.isEmpty(str2)) {
            spannableString.setSpan(new RelativeSizeSpan(f3), spannableString.length() - str2.length(), spannableString.length(), 17);
            spannableString.setSpan(new d(-DPIUtil.dip2px(1.0f)), spannableString.length() - str2.length(), spannableString.length(), 17);
        }
        return spannableString;
    }

    public static boolean isValid(com.jingdong.app.mall.home.r.e.d dVar) {
        ArrayList<com.jingdong.app.mall.home.r.e.f> arrayList = dVar.f10682c;
        return arrayList != null && arrayList.size() > 0;
    }

    public static void onItemClick(Context context, NewcomerBaseModel newcomerBaseModel) {
        JumpEntity jumpEntity;
        if (newcomerBaseModel == null || l.k() || (jumpEntity = newcomerBaseModel.jump) == null) {
            return;
        }
        l.c(jumpEntity, newcomerBaseModel.img);
        l.onClickJsonEvent(context, jumpEntity, "Home_NewCouponFloor", "", "", jumpEntity.srvJson, 1, null);
    }

    public void addExpoMta() {
        if (this.mData == null) {
            return;
        }
        this.expoArray = b.d();
        for (int i2 = 0; i2 < this.mData.size(); i2++) {
            this.expoArray.put(b.c(new NewcomerBaseModel(this.mData.getJSONObject(i2)).expoJson));
        }
    }

    public JDJSONArray getData() {
        return this.mData;
    }

    public void initData(JDJSONArray jDJSONArray) {
        this.mData = jDJSONArray;
    }

    public boolean isHeightFloor() {
        return this.isHeightFloor;
    }

    public boolean isRequestAsyncData() {
        return this.isRequestAsyncData;
    }

    public boolean isShow() {
        return this.isShow;
    }

    public void sendExpoMta() {
        if (this.expoArray.length() < 1) {
            return;
        }
        com.jingdong.app.mall.home.r.c.a.y("Home_NewCouponFloorExpo", "", this.expoArray.toString());
    }

    public void setHeightFloor(boolean z) {
        this.isHeightFloor = z;
    }

    public void setRequestAsyncData(boolean z) {
        this.isRequestAsyncData = z;
    }

    public void setShow(boolean z) {
        this.isShow = z;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        JDJSONArray jDJSONArray = this.mData;
        return jDJSONArray != null && jDJSONArray.size() > 0;
    }
}
