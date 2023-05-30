package com.paipai.library.inspect.dataclass;

import java.util.List;

/* loaded from: classes9.dex */
public class PpInspectSalesAttrs {
    private String attrId;
    private String attrName;
    private String attrProfileTitle;
    private String attrProfileUrl;
    private List<PpAttrValues> attrValues;
    private boolean isLinkage;

    public String getAttrId() {
        return this.attrId;
    }

    public String getAttrName() {
        return this.attrName;
    }

    public String getAttrProfileTitle() {
        return this.attrProfileTitle;
    }

    public String getAttrProfileUrl() {
        return this.attrProfileUrl;
    }

    public List<PpAttrValues> getAttrValues() {
        return this.attrValues;
    }

    public boolean isLinkage() {
        return this.isLinkage;
    }

    public void setAttrId(String str) {
        this.attrId = str;
    }

    public void setAttrName(String str) {
        this.attrName = str;
    }

    public void setAttrProfileTitle(String str) {
        this.attrProfileTitle = str;
    }

    public void setAttrProfileUrl(String str) {
        this.attrProfileUrl = str;
    }

    public void setAttrValues(List<PpAttrValues> list) {
        this.attrValues = list;
    }

    public void setLinkage(boolean z) {
        this.isLinkage = z;
    }
}
