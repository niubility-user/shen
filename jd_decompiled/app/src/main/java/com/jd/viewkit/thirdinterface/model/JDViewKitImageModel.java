package com.jd.viewkit.thirdinterface.model;

import android.widget.ImageView;

/* loaded from: classes18.dex */
public class JDViewKitImageModel {
    private String borderColor;
    private int borderWidth;
    private ImageView.ScaleType defaultScaleType;
    private String imageUrlStr;
    private boolean isUseDefault;
    private int radius;
    private ImageView.ScaleType scaleType;

    public JDViewKitImageModel(String str, boolean z, int i2, int i3, String str2, ImageView.ScaleType scaleType, ImageView.ScaleType scaleType2) {
        this.imageUrlStr = str;
        this.isUseDefault = z;
        this.radius = i2;
        this.borderWidth = i3;
        this.borderColor = str2;
        this.scaleType = scaleType;
        this.defaultScaleType = scaleType2;
    }

    public String getBorderColor() {
        return this.borderColor;
    }

    public int getBorderWidth() {
        return this.borderWidth;
    }

    public ImageView.ScaleType getDefaultScaleType() {
        return this.defaultScaleType;
    }

    public String getImageUrlStr() {
        return this.imageUrlStr;
    }

    public int getRadius() {
        return this.radius;
    }

    public ImageView.ScaleType getScaleType() {
        return this.scaleType;
    }

    public boolean isUseDefault() {
        return this.isUseDefault;
    }
}
