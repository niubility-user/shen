package com.jdcloud.vsr.imaging;

import com.jdcloud.vsr.JDTBitmap;
import com.jdcloud.vsr.JDTContext;
import com.jdcloud.vsr.Task;

/* loaded from: classes18.dex */
public class UpscalerX2 extends Task {

    /* loaded from: classes18.dex */
    public enum Mode {
        NEAREST_NEIGHBOR,
        BOX,
        LINEAR,
        CUBIC,
        CONVNET
    }

    public UpscalerX2(JDTContext jDTContext) {
        super(jDTContext, newUpscaler(jDTContext));
    }

    private native float getCubicParameter(long j2);

    private native int getMode(long j2);

    private static native long newUpscaler(JDTContext jDTContext);

    private native void setCubicParameter(long j2, float f2);

    private native void setInput(long j2, JDTBitmap jDTBitmap);

    private native void setMode(long j2, int i2);

    private native void setOutput(long j2, JDTBitmap jDTBitmap);

    public float getCubicParameter() {
        return getCubicParameter(this.handle);
    }

    public Mode getMode() {
        return Mode.values()[getMode(this.handle)];
    }

    public void setCubicParameter(float f2) {
        setCubicParameter(this.handle, f2);
    }

    public void setInput(JDTBitmap jDTBitmap) {
        setInput(this.handle, jDTBitmap);
    }

    public void setMode(Mode mode) {
        setMode(this.handle, mode.ordinal());
    }

    public void setOutput(JDTBitmap jDTBitmap) {
        setOutput(this.handle, jDTBitmap);
    }
}
