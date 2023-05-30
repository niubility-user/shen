package com.jingdong.canvas.surface;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.canvas.JDCanvasJNI;
import com.jingdong.canvas.b.a;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class JDTextureViewCallback implements TextureView.SurfaceTextureListener {

    /* renamed from: g  reason: collision with root package name */
    private final String f12309g;

    /* renamed from: h  reason: collision with root package name */
    private String f12310h = JDDarkUtil.COLOR_FFFFFFF;

    /* renamed from: i  reason: collision with root package name */
    private Surface f12311i;

    /* renamed from: j  reason: collision with root package name */
    private TextureView f12312j;

    /* renamed from: k  reason: collision with root package name */
    private ArrayList<TextureView.SurfaceTextureListener> f12313k;

    static {
        JDCanvasJNI.load();
    }

    public JDTextureViewCallback(TextureView textureView, String str, int i2) {
        this.f12309g = str;
        this.f12312j = textureView;
        JDCanvasJNI.setWrapperContextType(str, i2);
    }

    private native void onRenderExit(String str);

    private native void onSurfaceChanged(String str, Surface surface, int i2, int i3, int i4, String str2);

    private native void onSurfaceCreated(String str, Surface surface);

    private native void onSurfaceDestroyed(String str, Surface surface);

    public void a() {
        a.a("on RequestExit");
        onRenderExit(this.f12309g);
        ArrayList<TextureView.SurfaceTextureListener> arrayList = this.f12313k;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f12310h = str;
    }

    public void c(int i2, int i3) {
        try {
            onSurfaceChanged(this.f12309g, this.f12311i, 0, i2, i3, this.f12310h);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    @TargetApi(16)
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        a.a("on surfaceTexture Available.");
        if (this.f12311i == null) {
            this.f12311i = new Surface(surfaceTexture);
        }
        onSurfaceChanged(this.f12309g, this.f12311i, 0, i2, i3, this.f12310h);
        JDCanvasJNI.refreshArguments(this.f12309g);
        if (JDCanvasJNI.sendEvent(this.f12309g) && (this.f12312j instanceof JDTextureView)) {
            a.a("start to send event in GSurfaceCallback.");
            ((JDTextureView) this.f12312j).c();
        }
        ArrayList<TextureView.SurfaceTextureListener> arrayList = this.f12313k;
        if (arrayList != null) {
            Iterator<TextureView.SurfaceTextureListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onSurfaceTextureAvailable(surfaceTexture, i2, i3);
            }
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        a.a("on surfaceTexture destroyed.");
        ArrayList<TextureView.SurfaceTextureListener> arrayList = this.f12313k;
        if (arrayList != null) {
            Iterator<TextureView.SurfaceTextureListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onSurfaceTextureDestroyed(surfaceTexture);
            }
        }
        onSurfaceDestroyed(this.f12309g, this.f12311i);
        Surface surface = this.f12311i;
        if (surface == null || !surface.isValid()) {
            return true;
        }
        this.f12311i.release();
        this.f12311i = null;
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        a.a("on surfaceTexture changed.");
        if (this.f12311i == null) {
            this.f12311i = new Surface(surfaceTexture);
        }
        onSurfaceChanged(this.f12309g, this.f12311i, 0, i2, i3, this.f12310h);
        ArrayList<TextureView.SurfaceTextureListener> arrayList = this.f12313k;
        if (arrayList != null) {
            Iterator<TextureView.SurfaceTextureListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onSurfaceTextureSizeChanged(surfaceTexture, i2, i3);
            }
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }
}
