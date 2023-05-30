package com.jd.aips.common.camera;

import android.view.SurfaceHolder;

/* loaded from: classes12.dex */
public interface JDCNSurfaceViewCallback {
    void onSurfaceViewChanged(SurfaceHolder surfaceHolder);

    void onSurfaceViewCreated(SurfaceHolder surfaceHolder);

    void onSurfaceViewDestoryed();

    void previewBound(int i2, int i3);
}
