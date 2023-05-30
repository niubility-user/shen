package com.jingdong.sdk.platform.lib.entity.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes10.dex */
public class PackInfo implements Serializable {
    public static final int CART = 1;
    public static final int PACKS = 0;
    private String discount;
    private String finalPrice;
    private Long id;
    private Long mainId;
    private AwareInfo mainProduct;
    private String mainSkuName;
    private String mainSkuPicUrl;
    private String name;
    private Integer num;
    private String originalPrice;

    /* renamed from: point  reason: collision with root package name */
    private Long f15458point;
    private Integer productCount;
    private String rePrice;
    private SourceEntityInfo sourceEntity;
    private Integer suitType;
    private List<AwareInfo> productList = new ArrayList();
    private List<AwareInfo> giftList = new ArrayList();

    public static int getCART() {
        return 1;
    }

    public static int getPACKS() {
        return 0;
    }

    public String getDiscount() {
        return this.discount;
    }

    public String getFinalPrice() {
        return this.finalPrice;
    }

    public List<AwareInfo> getGiftList() {
        return this.giftList;
    }

    public Long getId() {
        return this.id;
    }

    public Long getMainId() {
        return this.mainId;
    }

    public AwareInfo getMainProduct() {
        return this.mainProduct;
    }

    public String getMainSkuName() {
        return this.mainSkuName;
    }

    public String getMainSkuPicUrl() {
        return this.mainSkuPicUrl;
    }

    public String getName() {
        return this.name;
    }

    public Integer getNum() {
        return this.num;
    }

    public String getOriginalPrice() {
        return this.originalPrice;
    }

    public Long getPoint() {
        return this.f15458point;
    }

    public Integer getProductCount() {
        return this.productCount;
    }

    public List<AwareInfo> getProductList() {
        return this.productList;
    }

    public String getRePrice() {
        return this.rePrice;
    }

    public SourceEntityInfo getSourceEntity() {
        return this.sourceEntity;
    }

    public Integer getSuitType() {
        return this.suitType;
    }

    public void setDiscount(String str) {
        this.discount = str;
    }

    public void setFinalPrice(String str) {
        this.finalPrice = str;
    }

    public void setGiftList(List<AwareInfo> list) {
        this.giftList = list;
    }

    public void setId(Long l2) {
        this.id = l2;
    }

    public void setMainId(Long l2) {
        this.mainId = l2;
    }

    public void setMainProduct(AwareInfo awareInfo) {
        this.mainProduct = awareInfo;
    }

    public void setMainSkuName(String str) {
        this.mainSkuName = str;
    }

    public void setMainSkuPicUrl(String str) {
        this.mainSkuPicUrl = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setOriginalPrice(String str) {
        this.originalPrice = str;
    }

    public void setPoint(Long l2) {
        this.f15458point = l2;
    }

    public void setProductCount(Integer num) {
        this.productCount = num;
    }

    public void setProductList(List<AwareInfo> list) {
        this.productList = list;
    }

    public void setRePrice(String str) {
        this.rePrice = str;
    }

    public void setSourceEntity(SourceEntityInfo sourceEntityInfo) {
        this.sourceEntity = sourceEntityInfo;
    }

    public void setSuitType(Integer num) {
        this.suitType = num;
    }
}
