package com.jd.lib.productdetail.core.entitys.warebusiness;

/* loaded from: classes15.dex */
public class AddCartParamsEntity {
    public String businessName;

    /* loaded from: classes15.dex */
    public enum BusinessNameEnum {
        PD_NORMAL("PD_addCart"),
        PD_INTEREST("PD_interest"),
        PD_COUPON_RECOMMEND("PD_couponRecommend"),
        PD_REFRESH_RECOMMEND("PD_freshRecommend"),
        PD_NO_STOCK("PD_noStock"),
        PD_PACK_SUCCESS("PD_packSuccess"),
        PD_3CRENEW("PD_3cRenew"),
        PD_RECOMMEND("PD_recommend"),
        PD_FEEDS_BOTTOM("PD_feedsBottom"),
        PD_MINI_BOTTOM("PD_miniBottom"),
        PD_MINI_SELECTED_BOTTOM("PD_miniSelectedBottom");
        
        private String mName;

        BusinessNameEnum(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }
}
