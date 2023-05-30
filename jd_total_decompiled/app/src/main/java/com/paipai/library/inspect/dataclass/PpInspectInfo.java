package com.paipai.library.inspect.dataclass;

/* loaded from: classes9.dex */
public class PpInspectInfo {
    private String inspectSkuId;
    private String ppSaleAttrId;
    private String price;
    private PpInspectSkuList queryInspectSkuList;
    private PpInspectSalesAttrInfo selectableSalesAttrInfo;
    private PpSpecificationInfo specificationInfo;
    private int status;
    private String youpinSkuId;

    public String getInspectSkuId() {
        return this.inspectSkuId;
    }

    public String getPpSaleAttrId() {
        return this.ppSaleAttrId;
    }

    public String getPrice() {
        return this.price;
    }

    public PpInspectSkuList getQueryInspectSkuList() {
        return this.queryInspectSkuList;
    }

    public PpInspectSalesAttrInfo getSelectableSalesAttrInfo() {
        return this.selectableSalesAttrInfo;
    }

    public PpSpecificationInfo getSpecificationInfo() {
        return this.specificationInfo;
    }

    public int getStatus() {
        return this.status;
    }

    public String getYoupinSkuId() {
        return this.youpinSkuId;
    }

    public void setInspectSkuId(String str) {
        this.inspectSkuId = str;
    }

    public void setPpSaleAttrId(String str) {
        this.ppSaleAttrId = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setQueryInspectSkuList(PpInspectSkuList ppInspectSkuList) {
        this.queryInspectSkuList = ppInspectSkuList;
    }

    public void setSelectableSalesAttrInfo(PpInspectSalesAttrInfo ppInspectSalesAttrInfo) {
        this.selectableSalesAttrInfo = ppInspectSalesAttrInfo;
    }

    public void setSpecificationInfo(PpSpecificationInfo ppSpecificationInfo) {
        this.specificationInfo = ppSpecificationInfo;
    }

    public void setStatus(int i2) {
        this.status = i2;
    }

    public void setYoupinSkuId(String str) {
        this.youpinSkuId = str;
    }
}
