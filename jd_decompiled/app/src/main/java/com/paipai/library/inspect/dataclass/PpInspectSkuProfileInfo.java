package com.paipai.library.inspect.dataclass;

import java.util.List;

/* loaded from: classes9.dex */
public class PpInspectSkuProfileInfo {
    private boolean excellentReport;
    private List<String> imageList;
    private String inspectResult;
    private String inspectSkuId;
    private String inspectSkuName;
    private String price;
    private String reportLink;
    private boolean selected;
    private int status;
    private List<String> tags;
    private String youpinSkuId;

    public List<String> getImageList() {
        return this.imageList;
    }

    public String getInspectResult() {
        return this.inspectResult;
    }

    public String getInspectSkuId() {
        return this.inspectSkuId;
    }

    public String getInspectSkuName() {
        return this.inspectSkuName;
    }

    public String getPrice() {
        return this.price;
    }

    public String getReportLink() {
        return this.reportLink;
    }

    public int getStatus() {
        return this.status;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public String getYoupinSkuId() {
        return this.youpinSkuId;
    }

    public boolean isExcellentReport() {
        return this.excellentReport;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setExcellentReport(boolean z) {
        this.excellentReport = z;
    }

    public void setImageList(List<String> list) {
        this.imageList = list;
    }

    public void setInspectResult(String str) {
        this.inspectResult = str;
    }

    public void setInspectSkuId(String str) {
        this.inspectSkuId = str;
    }

    public void setInspectSkuName(String str) {
        this.inspectSkuName = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setReportLink(String str) {
        this.reportLink = str;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public void setStatus(int i2) {
        this.status = i2;
    }

    public void setTags(List<String> list) {
        this.tags = list;
    }

    public void setYoupinSkuId(String str) {
        this.youpinSkuId = str;
    }
}
