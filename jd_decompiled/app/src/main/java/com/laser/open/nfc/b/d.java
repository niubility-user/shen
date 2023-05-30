package com.laser.open.nfc.b;

import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes12.dex */
public enum d {
    STATUS_10001(1001, 10001),
    STATUS_10002(1002, 10002),
    STATUS_10003(1003, 10003),
    STATUS_10004(1004, 10004),
    STATUS_10005(1005, 10005),
    STATUS_10006(1006, 10006),
    STATUS_10007(1018, 10007),
    STATUS_10101(1010, R2.drawable.live_list_like_05),
    STATUS_10202(R2.attr.ifTagNotSet, R2.drawable.login_wx_selected),
    STATUS_10299(1013, R2.drawable.manto_selector_actionbar_favorite_white),
    STATUS_10301(1016, R2.drawable.manto_selector_actionbar_option_black),
    STATUS_10302(1017, R2.drawable.manto_selector_actionbar_option_white),
    STATUS_10401(1007, R2.drawable.miaosha_tag_meizhuang),
    STATUS_10402(1008, R2.drawable.miniprogram_default_avatar),
    STATUS_10404(1009, R2.drawable.mm_checkbox_btn_grey),
    STATUS_10405(1015, R2.drawable.mm_checkbox_btn_grey_small),
    STATUS_10406(1019, R2.drawable.mm_checkbox_btn_red),
    STATUS_10408(R2.attr.ifTagSet, R2.drawable.mm_checkbox_btn_small),
    STATUS_10501(1011, 10501),
    STATUS_10502(1020, R2.drawable.oval_green),
    STATUS_10503(1021, R2.drawable.oval_red),
    STATUS_10601(1012, R2.drawable.productdetailcard_praise_item_price_bg),
    STATUS_10603(1022, R2.drawable.progress),
    STATUS_10701(1014, R2.drawable.recommend_belt_benefit_bg);
    
    private int newVersionCode;
    private int oldVersionCode;

    d(int i2, int i3) {
        this.oldVersionCode = i2;
        this.newVersionCode = i3;
    }

    public static int getStatusCode(int i2) {
        for (d dVar : values()) {
            if (dVar.oldVersionCode == i2) {
                return dVar.newVersionCode;
            }
        }
        return i2;
    }

    public int getNewVersionCode() {
        return this.newVersionCode;
    }

    public int getOldVersionCode() {
        return this.oldVersionCode;
    }

    public void setNewVersionCode(int i2) {
        this.newVersionCode = i2;
    }

    public void setOldVersionCode(int i2) {
        this.oldVersionCode = i2;
    }
}
