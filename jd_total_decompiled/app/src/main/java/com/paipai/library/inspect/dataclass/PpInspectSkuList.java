package com.paipai.library.inspect.dataclass;

import java.util.List;

/* loaded from: classes9.dex */
public class PpInspectSkuList {
    private String endorsement;
    private List<PpInspectSkuProfileInfo> inspectSkuList;
    private String selectableCount;
    private String summaryTips;
    private String title;

    public String getEndorsement() {
        return this.endorsement;
    }

    public List<PpInspectSkuProfileInfo> getInspectSkuList() {
        return this.inspectSkuList;
    }

    public String getSelectableCount() {
        return this.selectableCount;
    }

    public String getSummaryTips() {
        return this.summaryTips;
    }

    public String getTitle() {
        return this.title;
    }

    public void setEndorsement(String str) {
        this.endorsement = str;
    }

    public void setInspectSkuList(List<PpInspectSkuProfileInfo> list) {
        this.inspectSkuList = list;
    }

    public void setSelectableCount(String str) {
        this.selectableCount = str;
    }

    public void setSummaryTips(String str) {
        this.summaryTips = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
