package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.promotion.PdSuitEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessPromotionEntity {
    private static final String SUIT = "suit";
    public String activityTip;
    public WareBusinessBenefitBeltEntity benefitBeltInfo;
    public List<WareBusinessPromotionGiftEntity> bestProList;
    public String domain;
    public PromotionVipGiftEntity fsGiftInfo;
    public boolean isTwoLine;
    public PromotionVipGiftEntity memberGiftInfo;
    public ArrayList<HashMap> multiplePaymentInfo;
    public String normalMark;
    public int packABTest;
    public WareBusinessPromotionPlusEntity plusGiftInfo;
    public String plusMark;
    public PromotionVipGiftEntity preSaleGiftInfo;
    public WareBusinessPromotionNoticeEntity proSalesInfo;
    public JDJSONObject promotionJson;
    public String prompt;
    public PromotionVipGiftEntity spotGiftInfo;
    public PdSuitEntry suitEntry;
    public String tip;
    public String tipImage;
    public List<WareBusinessPromotionGiftEntity> gift = new ArrayList();
    public List<WareBusinessPromotionGiftEntity> activity = new ArrayList();
    public List<WareBusinessPromotionGiftEntity> attach = new ArrayList();
    public List<String> tips = new ArrayList();

    public void dealData() {
        List<WareBusinessPromotionGiftEntity> list = this.activity;
        if (list != null && list.size() > 0) {
            for (int size = this.activity.size() - 1; size >= 0; size--) {
                WareBusinessPromotionGiftEntity wareBusinessPromotionGiftEntity = this.activity.get(size);
                if (wareBusinessPromotionGiftEntity == null || TextUtils.isEmpty(wareBusinessPromotionGiftEntity.getValue())) {
                    this.activity.remove(size);
                }
            }
        }
        List<WareBusinessPromotionGiftEntity> list2 = this.activity;
        if (list2 == null || list2.size() <= 1) {
            return;
        }
        Collections.sort(this.activity, new Comparator<WareBusinessPromotionGiftEntity>() { // from class: com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPromotionEntity.1
            @Override // java.util.Comparator
            public int compare(WareBusinessPromotionGiftEntity wareBusinessPromotionGiftEntity2, WareBusinessPromotionGiftEntity wareBusinessPromotionGiftEntity3) {
                if (wareBusinessPromotionGiftEntity2 == null) {
                    return -1;
                }
                if (wareBusinessPromotionGiftEntity3 == null) {
                    return 1;
                }
                if (wareBusinessPromotionGiftEntity3.getProSortNum() == wareBusinessPromotionGiftEntity2.getProSortNum()) {
                    return 0;
                }
                return wareBusinessPromotionGiftEntity2.getProSortNum() - wareBusinessPromotionGiftEntity3.getProSortNum() > 0 ? 1 : -1;
            }
        });
    }

    public void dealSuit() {
        JDJSONObject jDJSONObject = this.promotionJson;
        if (jDJSONObject != null && jDJSONObject.optJSONArray("suit") != null) {
            this.suitEntry = new PdSuitEntry(this.packABTest, this.domain, this.promotionJson.optJSONArray("suit"));
        } else {
            this.suitEntry = null;
        }
    }

    public boolean hasSuit() {
        PdSuitEntry pdSuitEntry = this.suitEntry;
        return (pdSuitEntry == null || pdSuitEntry.getSuit() == null || this.suitEntry.getSuit().isEmpty()) ? false : true;
    }

    public boolean isShowPromotionBanner() {
        WareBusinessPromotionNoticeEntity wareBusinessPromotionNoticeEntity = this.proSalesInfo;
        return (wareBusinessPromotionNoticeEntity == null || TextUtils.isEmpty(wareBusinessPromotionNoticeEntity.imgUrl)) ? false : true;
    }

    public void dealSuit(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.suitEntry = new PdSuitEntry(this.packABTest, jDJSONObject);
        } else {
            this.suitEntry = null;
        }
    }
}
