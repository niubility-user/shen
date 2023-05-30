package com.tencent.tencentmap.mapsdk.maps.model;

import java.util.ArrayList;

/* loaded from: classes9.dex */
public class TencentMapGestureListenerList implements TencentMapGestureListener {
    private ArrayList<TencentMapGestureListener> mListeners = new ArrayList<>();

    public synchronized void addListener(TencentMapGestureListener tencentMapGestureListener) {
        if (tencentMapGestureListener != null) {
            if (!this.mListeners.contains(tencentMapGestureListener)) {
                this.mListeners.add(tencentMapGestureListener);
            }
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener
    public synchronized boolean onDoubleTap(float f2, float f3) {
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            this.mListeners.get(size).onDoubleTap(f2, f3);
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener
    public synchronized boolean onDown(float f2, float f3) {
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            this.mListeners.get(size).onDown(f2, f3);
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener
    public synchronized boolean onFling(float f2, float f3) {
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            this.mListeners.get(size).onFling(f2, f3);
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener
    public synchronized boolean onLongPress(float f2, float f3) {
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            this.mListeners.get(size).onLongPress(f2, f3);
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener
    public synchronized void onMapStable() {
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            this.mListeners.get(size).onMapStable();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener
    public synchronized boolean onScroll(float f2, float f3) {
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            this.mListeners.get(size).onScroll(f2, f3);
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener
    public synchronized boolean onSingleTap(float f2, float f3) {
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            this.mListeners.get(size).onSingleTap(f2, f3);
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener
    public synchronized boolean onUp(float f2, float f3) {
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            this.mListeners.get(size).onUp(f2, f3);
        }
        return false;
    }

    public synchronized void removeListener(TencentMapGestureListener tencentMapGestureListener) {
        this.mListeners.remove(tencentMapGestureListener);
    }
}
