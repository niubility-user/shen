package com.jd.lib.un.basewidget.widget.watermark;

/* loaded from: classes16.dex */
public class WatermarkConfig {
    private static WatermarkConfig config;
    private boolean canAdd;
    private String mText = "";
    private int mTextColor = -1579033;
    private float mTextSize = 10.0f;
    private float mRotation = -30.0f;

    public static WatermarkConfig getConfig() {
        WatermarkConfig watermarkConfig;
        WatermarkConfig watermarkConfig2 = config;
        if (watermarkConfig2 != null) {
            return watermarkConfig2;
        }
        synchronized (WatermarkConfig.class) {
            if (config == null) {
                config = new WatermarkConfig();
            }
            watermarkConfig = config;
        }
        return watermarkConfig;
    }

    public float getmRotation() {
        return this.mRotation;
    }

    public String getmText() {
        return this.mText;
    }

    public int getmTextColor() {
        return this.mTextColor;
    }

    public float getmTextSize() {
        return this.mTextSize;
    }

    public boolean isCanAdd() {
        return this.canAdd;
    }

    public WatermarkConfig setCanAdd(boolean z) {
        this.canAdd = z;
        return this;
    }

    public WatermarkConfig setRotation(float f2) {
        this.mRotation = f2;
        return this;
    }

    public WatermarkConfig setmText(String str) {
        this.mText = str;
        return this;
    }

    public WatermarkConfig setmTextColor(int i2) {
        this.mTextColor = i2;
        return this;
    }

    public WatermarkConfig setmTextSize(float f2) {
        this.mTextSize = f2;
        return this;
    }
}
