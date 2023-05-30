package com.paipai.library.inspect.dataclass;

import java.util.List;

/* loaded from: classes9.dex */
public class PpInspectSalesAttrInfo {
    private List<PpInspectSalesAttrs> filterAttrs;
    private boolean hideJdSalesAttr;
    private List<PpInspectSalesAttrs> inspectSaleAttrs;
    private int inspectSkuCount;
    private List<String> selectableDiagrams;
    private String selectableTips;

    public List<PpInspectSalesAttrs> getFilterAttrs() {
        return this.filterAttrs;
    }

    public List<PpInspectSalesAttrs> getInspectSaleAttrs() {
        return this.inspectSaleAttrs;
    }

    public int getInspectSkuCount() {
        return this.inspectSkuCount;
    }

    public List<String> getSelectableDiagrams() {
        return this.selectableDiagrams;
    }

    public String getSelectableTips() {
        return this.selectableTips;
    }

    public boolean isHideJdSalesAttr() {
        return this.hideJdSalesAttr;
    }

    public void setFilterAttrs(List<PpInspectSalesAttrs> list) {
        this.filterAttrs = list;
    }

    public void setHideJdSalesAttr(boolean z) {
        this.hideJdSalesAttr = z;
    }

    public void setInspectSaleAttrs(List<PpInspectSalesAttrs> list) {
        this.inspectSaleAttrs = list;
    }

    public void setInspectSkuCount(int i2) {
        this.inspectSkuCount = i2;
    }

    public void setSelectableDiagrams(List<String> list) {
        this.selectableDiagrams = list;
    }

    public void setSelectableTips(String str) {
        this.selectableTips = str;
    }
}
