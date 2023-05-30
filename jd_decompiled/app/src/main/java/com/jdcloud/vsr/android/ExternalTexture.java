package com.jdcloud.vsr.android;

import android.graphics.SurfaceTexture;
import com.jdcloud.vsr.JDTBitmap;
import com.jdcloud.vsr.JDTContext;

/* loaded from: classes18.dex */
public final class ExternalTexture extends JDTBitmap {
    private SurfaceTexture surfaceTexture;

    public ExternalTexture(JDTContext jDTContext, boolean z) {
        super(jDTContext, z ? newExternalTexture(jDTContext) : -100L);
        bind(this.handle);
    }

    private native void bind(long j2);

    private static native long newExternalTexture(JDTContext jDTContext);

    private native void notifyUpdate(long j2, int i2, int i3);

    public SurfaceTexture getSurfaceTexture() {
        return this.surfaceTexture;
    }

    public void notifyUpdate(int i2, int i3) {
        notifyUpdate(this.handle, i2, i3);
    }
}
