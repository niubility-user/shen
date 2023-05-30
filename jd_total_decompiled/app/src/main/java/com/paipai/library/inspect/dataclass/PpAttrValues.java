package com.paipai.library.inspect.dataclass;

import java.util.List;

/* loaded from: classes9.dex */
public class PpAttrValues {
    private String attrValueId;
    private String attrValueImg;
    private String attrValueName;
    private boolean enabled;
    private List<String> inspectSkuGroupIds;
    private boolean selected;
    private List<String> skuIds;

    public String getAttrValueId() {
        return this.attrValueId;
    }

    public String getAttrValueImg() {
        return this.attrValueImg;
    }

    public String getAttrValueName() {
        return this.attrValueName;
    }

    public List<String> getInspectSkuGroupIds() {
        return this.inspectSkuGroupIds;
    }

    public List<String> getSkuIds() {
        return this.skuIds;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setAttrValueId(String str) {
        this.attrValueId = str;
    }

    public void setAttrValueImg(String str) {
        this.attrValueImg = str;
    }

    public void setAttrValueName(String str) {
        this.attrValueName = str;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public void setInspectSkuGroupIds(List<String> list) {
        this.inspectSkuGroupIds = list;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public void setSkuIds(List<String> list) {
        this.skuIds = list;
    }
}
