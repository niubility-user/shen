package com.laser.open.nfc.model.entity;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class CardArtEntity implements Serializable {
    private String encodedData;
    private String height;
    private String mimeType;
    private String width;

    public String getEncodedData() {
        return this.encodedData;
    }

    public String getHeight() {
        return this.height;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getWidth() {
        return this.width;
    }

    public void setEncodedData(String str) {
        this.encodedData = str;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public void setMimeType(String str) {
        this.mimeType = str;
    }

    public void setWidth(String str) {
        this.width = str;
    }
}
