package com.tencent.tencentmap.mapsdk.maps;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.maps.model.MapKernel;
import com.tencent.tencentmap.mapsdk.maps.model.MapViewType;

/* loaded from: classes9.dex */
public abstract class BaseMapView extends FrameLayout {

    /* loaded from: classes9.dex */
    public interface MapViewProxy {
        TencentMap getMap();

        boolean isOpaque();

        boolean isTouchable();

        void onCreated();

        void onDestroy();

        void onPause();

        void onRestart();

        void onResume();

        void onSizeChanged(int i2, int i3, int i4, int i5);

        void onStart();

        void onStop();

        void onSurfaceChanged(Object obj, int i2, int i3);

        boolean onTouchEvent(MotionEvent motionEvent);

        void onUpdateOptions(TencentMapOptions tencentMapOptions);

        void setOnTop(boolean z);

        void setOpaque(boolean z);
    }

    public BaseMapView(@NonNull Context context) {
        super(context);
    }

    public BaseMapView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BaseMapView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public abstract TencentMap getMap();

    public abstract TencentMap getMap(TencentMapOptions tencentMapOptions);

    public TencentMapOptions.IMapKernel getMapKernel() {
        return MapKernel.Vector;
    }

    public MapViewType getViewType() {
        return MapViewType.SurfaceView;
    }

    public abstract void onDestroy();

    public abstract void onPause();

    public abstract void onRestart();

    public abstract void onResume();

    public abstract void onStart();

    public abstract void onStop();
}
