package com.jingdong.app.mall.bundle.styleinfoview.entitys;

/* loaded from: classes3.dex */
public class PDOperAppointEntity {
    public String addCartContent;
    public String addCartTitle;
    public PdAddRiskCheckInfo addRiskCheckInfo;
    public String appFailToast;
    public boolean appSuccess;
    public String content;
    public CouponContent couponContent;
    public String drawMoreTitle;
    public String drawMoreUrl;
    public String drawTaskType;
    public boolean flag;
    public FollowShopContent followShopContent;
    public String guide;
    public boolean isAddCartSuccess;
    public String link;
    public String msg;
    public boolean needVerify;
    public OfficeAccount officeAccount;
    public boolean showButton;
    public String title;

    /* loaded from: classes3.dex */
    public static class CouponContent {
        public String expiryDate;
        public String receiveImg;
        public String sum;
        public String text;
        public String type;
        public String useCondition;
        public String useScope;

        public String toString() {
            return "CouponContent{sum='" + this.sum + "', useCondition='" + this.useCondition + "', text='" + this.text + "', type='" + this.type + "', receiveImg='" + this.receiveImg + "', useScope='" + this.useScope + "', expiryDate='" + this.expiryDate + "'}";
        }
    }

    /* loaded from: classes3.dex */
    public static class FollowShopContent {
        public String color;
        public String keyWord;
        public String text;

        public String toString() {
            return "FollowShopContent{text='" + this.text + "', color='" + this.color + "', keyWord='" + this.keyWord + "'}";
        }
    }

    /* loaded from: classes3.dex */
    public static class OfficeAccount {
        public String color;
        public String text;

        public String toString() {
            return "OfficeAccount{color='" + this.color + "', text='" + this.text + "'}";
        }
    }

    public String toString() {
        return "PDOperAppointEntity{content='" + this.content + "', title='" + this.title + "', drawTaskType='" + this.drawTaskType + "', drawMoreTitle='" + this.drawMoreTitle + "', drawMoreUrl='" + this.drawMoreUrl + "', showButton=" + this.showButton + ", link='" + this.link + "', flag=" + this.flag + ", msg='" + this.msg + "', needVerify=" + this.needVerify + ", guide='" + this.guide + "', addCartTitle='" + this.addCartTitle + "', addCartContent='" + this.addCartContent + "', appFailToast='" + this.appFailToast + "', appSuccess=" + this.appSuccess + ", isAddCartSuccess=" + this.isAddCartSuccess + ", addRiskCheckInfo=" + this.addRiskCheckInfo + ", couponContent=" + this.couponContent + ", followShopContent=" + this.followShopContent + ", officeAccount=" + this.officeAccount + '}';
    }
}
