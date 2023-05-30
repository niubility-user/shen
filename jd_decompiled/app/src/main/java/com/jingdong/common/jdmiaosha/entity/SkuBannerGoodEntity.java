package com.jingdong.common.jdmiaosha.entity;

import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes5.dex */
public class SkuBannerGoodEntity {
    private String imageUrl;
    private JumpEntity jump;
    private String sku;

    public String getImageUrl() {
        return this.imageUrl;
    }

    public JumpEntity getJump() {
        return this.jump;
    }

    public String getSku() {
        return this.sku;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setJump(JumpEntity jumpEntity) {
        this.jump = jumpEntity;
    }

    public void setSku(String str) {
        this.sku = str;
    }
}
