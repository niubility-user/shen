package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import com.jingdong.app.mall.bundle.styleinfoview.entitys.PreferentialGuideEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.coupon.PDCouponCellEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.promotion.PdSuitEntry;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PromotionLayerItemEntity {
    public static final int COUPON = 2;
    public static final int COUPON_TITLE_RECEIVE = 11;
    public static final int COUPON_TITLE_USE = 12;
    public static final int PROMOTION_3C_GIFT_POOL = 7;
    public static final int PROMOTION_ACTIVITY = 3;
    public static final int PROMOTION_ATTACH = 4;
    public static final int PROMOTION_FANS_GIFT = 16;
    public static final int PROMOTION_FANS_GIFT_FOLD = 17;
    public static final int PROMOTION_FANS_GIFT_GUIDE = 15;
    public static final int PROMOTION_GIFT = 6;
    public static final int PROMOTION_PLUS_GIFT = 5;
    public static final int PROMOTION_SUIT = 8;
    public static final int PROMOTION_TITLE = 10;
    public static final int SECOND_GUIDE_BOTTOM = 14;
    public static final int SECOND_GUIDE_INFO = 13;
    public static final int WHITE_BAR = 1;
    public static final int WHITE_BAR_TITLE = 9;
    public ArrayList<PromotionLayerItemEntity> foldList;
    public String foldText;
    public String iconCode;
    public boolean isFold;
    public boolean isHidBestGuideInfo;
    public boolean isTag;
    public String jumpUrl;
    public PDCouponCellEntity mPdCouponCellEntity;
    public PdSuitEntry mPdSuitEntry;
    public WareBusinessPromotionGiftEntity mPromotionEntity;
    public WareBusinessGiftPools3C mWareBusinessGiftPools3C;
    public WareBusinessWhiteBarEntity mWhiteBarInfoEntity;
    public String plusTimes;
    public String rightText;
    public PreferentialGuideEntity.SecondLevelGuideInfo secondLevelGuideInfo;
    public int sheng = -1;
    public String subTitle;
    public String title;
    public int type;
    public int vipJumpType;
    public String vipJumpUrl;
    public int vipType;
    public String xiiconCode;
}
