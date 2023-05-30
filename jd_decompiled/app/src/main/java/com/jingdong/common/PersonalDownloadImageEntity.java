package com.jingdong.common;

/* loaded from: classes5.dex */
public class PersonalDownloadImageEntity {
    public String downloadUrl;
    public int index;
    public boolean isSelected;
    public String savePath;

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public int getIndex() {
        return this.index;
    }

    public String getSavePath() {
        return this.savePath;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public void setIndex(int i2) {
        this.index = i2;
    }

    public void setSavePath(String str) {
        this.savePath = str;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
