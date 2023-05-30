package com.jingdong.app.mall.bundle.dolphinlib.floor.entity;

import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import java.util.List;

/* loaded from: classes19.dex */
public class GoodsEntity {
    public String addCarImage;
    public String copyWriting;
    public String goodsLineChart;
    public List<String> goodsPromotions;
    public String goodsStock;
    public String goodsTagImage;
    public String image;
    public boolean isCloneItem = false;
    public BabelJumpEntity jump;
    public String linePrice;
    public String naFlagImgM;
    public String name;
    public String pPrice;
    public String pcpPrice;
    public String purchasePrice;
    public String skuId;
    public List<DolphinTagEntity> skuTagList;
    public String tag;

    public GoodsEntity getClone() {
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.tag = this.tag;
        goodsEntity.skuId = this.skuId;
        goodsEntity.image = this.image;
        goodsEntity.goodsTagImage = this.goodsTagImage;
        goodsEntity.addCarImage = this.addCarImage;
        goodsEntity.name = this.name;
        goodsEntity.skuTagList = this.skuTagList;
        goodsEntity.goodsPromotions = this.goodsPromotions;
        goodsEntity.pcpPrice = this.pcpPrice;
        goodsEntity.pPrice = this.pPrice;
        goodsEntity.linePrice = this.linePrice;
        goodsEntity.purchasePrice = this.purchasePrice;
        goodsEntity.goodsStock = this.goodsStock;
        goodsEntity.jump = this.jump;
        goodsEntity.isCloneItem = true;
        goodsEntity.naFlagImgM = this.naFlagImgM;
        goodsEntity.copyWriting = this.copyWriting;
        return goodsEntity;
    }
}
