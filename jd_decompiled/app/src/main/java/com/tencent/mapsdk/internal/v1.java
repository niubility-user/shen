package com.tencent.mapsdk.internal;

import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes9.dex */
public interface v1 {
    void a(float f2);

    int getHeight();

    View getView();

    int getWidth();

    void j();

    void onDestroy();

    void onPause();

    void onResume();

    void onSizeChanged(int i2, int i3, int i4, int i5);

    void onSurfaceChanged(Object obj, int i2, int i3);

    boolean onTouchEvent(MotionEvent motionEvent);

    void setMapOpaque(boolean z);

    void setZOrderMediaOverlay(boolean z);

    boolean z();
}
