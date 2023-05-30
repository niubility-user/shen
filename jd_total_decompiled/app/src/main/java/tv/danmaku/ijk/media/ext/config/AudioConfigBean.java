package tv.danmaku.ijk.media.ext.config;

import java.io.Serializable;

/* loaded from: classes11.dex */
public class AudioConfigBean implements Serializable {
    private boolean enableEq;
    private float enhanceRatio;
    private int maxFreq;
    private int minFreq;

    public float getEnhanceRatio() {
        return this.enhanceRatio;
    }

    public int getMaxFreq() {
        return this.maxFreq;
    }

    public int getMinFreq() {
        return this.minFreq;
    }

    public boolean isEnableEq() {
        return this.enableEq;
    }

    public void setEnableEq(boolean z) {
        this.enableEq = z;
    }

    public void setEnhanceRatio(float f2) {
        this.enhanceRatio = f2;
    }

    public void setMaxFreq(int i2) {
        this.maxFreq = i2;
    }

    public void setMinFreq(int i2) {
        this.minFreq = i2;
    }
}
