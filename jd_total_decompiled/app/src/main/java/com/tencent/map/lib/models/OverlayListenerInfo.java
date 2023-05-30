package com.tencent.map.lib.models;

import android.os.Handler;
import android.os.Looper;
import com.tencent.mapsdk.internal.la;
import com.tencent.mapsdk.internal.ma;
import com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay;

/* loaded from: classes9.dex */
public class OverlayListenerInfo {
    private VectorOverlay.OnVectorOverlayLoadListener innerListener = new a();
    public VectorOverlay.OnVectorOverlayClickListener outterVectorOverlayClickListener;
    public VectorOverlay.OnVectorOverlayLoadListener outterVectorOverlayLoadListener;

    /* loaded from: classes9.dex */
    public class a implements VectorOverlay.OnVectorOverlayLoadListener {

        /* renamed from: com.tencent.map.lib.models.OverlayListenerInfo$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public class RunnableC0779a implements Runnable {
            public final /* synthetic */ boolean a;

            public RunnableC0779a(boolean z) {
                this.a = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                VectorOverlay.OnVectorOverlayLoadListener onVectorOverlayLoadListener = OverlayListenerInfo.this.outterVectorOverlayLoadListener;
                if (onVectorOverlayLoadListener != null) {
                    onVectorOverlayLoadListener.onVectorOverlayLoaded(this.a);
                    ma.a(la.x, "TMS vetorOverlay loaded status: " + this.a);
                }
            }
        }

        public a() {
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay.OnVectorOverlayLoadListener
        public void onVectorOverlayLoaded(boolean z) {
            new Handler(Looper.getMainLooper()).post(new RunnableC0779a(z));
        }
    }

    public VectorOverlay.OnVectorOverlayClickListener getOutterVectorOverlayClickListener() {
        return this.outterVectorOverlayClickListener;
    }

    public void setOutterVectorOverlayClickListener(VectorOverlay.OnVectorOverlayClickListener onVectorOverlayClickListener) {
        this.outterVectorOverlayClickListener = onVectorOverlayClickListener;
    }

    public void setOutterVectorOverlayLoadListener(VectorOverlay.OnVectorOverlayLoadListener onVectorOverlayLoadListener) {
        this.outterVectorOverlayLoadListener = onVectorOverlayLoadListener;
    }
}
