package com.jdjr.risk.jdcn.common.camera;

import android.view.SurfaceHolder;

/* loaded from: classes18.dex */
public interface JDCNSurfaceViewCallback {
    void onSurfaceViewChanged(SurfaceHolder surfaceHolder);

    void onSurfaceViewCreated(SurfaceHolder surfaceHolder);

    void onSurfaceViewDestoryed();

    void previewBound(int i2, int i3);
}
