package com.jdcloud.vsr.visual.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import com.jdcloud.vsr.JDTContext;
import com.jdcloud.vsr.exceptions.CoreException;
import com.jdcloud.vsr.rendering.SceneRenderer;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes18.dex */
public class BaseVSRTextureView extends TextureView implements TextureView.SurfaceTextureListener {
    protected ArrayList<OnBindingListener> bindingListeners;
    private boolean mDidDetachFromWindow;
    private int mHeight;
    private boolean mIsFormatChanged;
    private boolean mOwnSurfaceTexture;
    private SurfaceTexture mSurfaceTexture;
    private int mWidth;
    private boolean mWillDetachFromWindow;
    protected SceneRenderer renderer;
    protected ArrayList<OnSizeChangeListener> sizeChangeListeners;
    protected ArrayList<OnUpdateListener> updateListeners;

    /* loaded from: classes18.dex */
    public interface OnBindingListener {
        void afterBinding(boolean z);

        void beforeBinding(boolean z);
    }

    /* loaded from: classes18.dex */
    public interface OnSizeChangeListener {
        void sizeChanged(int i2, int i3);
    }

    /* loaded from: classes18.dex */
    public interface OnUpdateListener {
        void update(int i2, int i3);
    }

    public BaseVSRTextureView(Context context) {
        super(context);
        this.mOwnSurfaceTexture = true;
        this.mWillDetachFromWindow = false;
        this.mDidDetachFromWindow = false;
        this.sizeChangeListeners = new ArrayList<>();
        this.bindingListeners = new ArrayList<>();
        this.updateListeners = new ArrayList<>();
        setSurfaceTextureListener(this);
    }

    private native boolean bindSurfaceToContext(JDTContext jDTContext, Surface surface) throws CoreException;

    private void makeCurrent() {
        Surface surface = new Surface(this.mSurfaceTexture);
        if (this.renderer == null || !surface.isValid()) {
            return;
        }
        Iterator<OnBindingListener> it = this.bindingListeners.iterator();
        while (it.hasNext()) {
            it.next().beforeBinding(true);
        }
        try {
            bindSurfaceToContext(this.renderer.getContext(), surface);
        } catch (CoreException e2) {
            e2.printStackTrace();
        }
        Iterator<OnBindingListener> it2 = this.bindingListeners.iterator();
        while (it2.hasNext()) {
            it2.next().afterBinding(true);
        }
    }

    private void unmakeCurrent() {
        if (this.renderer != null) {
            Iterator<OnBindingListener> it = this.bindingListeners.iterator();
            while (it.hasNext()) {
                it.next().beforeBinding(false);
            }
            try {
                bindSurfaceToContext(this.renderer.getContext(), null);
            } catch (CoreException e2) {
                e2.printStackTrace();
            }
            Iterator<OnBindingListener> it2 = this.bindingListeners.iterator();
            while (it2.hasNext()) {
                it2.next().afterBinding(false);
            }
        }
    }

    public void addBindingListener(OnBindingListener onBindingListener) {
        this.bindingListeners.add(onBindingListener);
    }

    public void addSizeChangeListener(OnSizeChangeListener onSizeChangeListener) {
        this.sizeChangeListeners.add(onSizeChangeListener);
    }

    public void addUpdateListener(OnUpdateListener onUpdateListener) {
        this.updateListeners.add(onUpdateListener);
    }

    public SceneRenderer getRenderer() {
        return this.renderer;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        String str = "onSurfaceTextureAvailable " + surfaceTexture;
        if (this.mSurfaceTexture == null) {
            this.mSurfaceTexture = surfaceTexture;
            makeCurrent();
        }
        this.mIsFormatChanged = false;
        this.mWidth = 0;
        this.mHeight = 0;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        String str = "onSurfaceTextureDestroyed " + surfaceTexture;
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        String str = "onSurfaceTextureSizeChanged " + i2 + JshopConst.JSHOP_PROMOTIO_X + i3;
        this.mIsFormatChanged = true;
        this.mWidth = i2;
        this.mHeight = i3;
        if (this.mSurfaceTexture == null) {
            this.mSurfaceTexture = surfaceTexture;
            makeCurrent();
        }
        Iterator<OnSizeChangeListener> it = this.sizeChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().sizeChanged(i2, i3);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        Iterator<OnUpdateListener> it = this.updateListeners.iterator();
        while (it.hasNext()) {
            it.next().update(this.mWidth, this.mHeight);
        }
    }

    public boolean removeBindingListener(OnBindingListener onBindingListener) {
        return this.bindingListeners.remove(onBindingListener);
    }

    public boolean removeSizeChangeListener(OnSizeChangeListener onSizeChangeListener) {
        return this.sizeChangeListeners.remove(onSizeChangeListener);
    }

    public void setRenderer(SceneRenderer sceneRenderer) {
        this.renderer = sceneRenderer;
    }

    public BaseVSRTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOwnSurfaceTexture = true;
        this.mWillDetachFromWindow = false;
        this.mDidDetachFromWindow = false;
        this.sizeChangeListeners = new ArrayList<>();
        this.bindingListeners = new ArrayList<>();
        this.updateListeners = new ArrayList<>();
        setSurfaceTextureListener(this);
    }

    public BaseVSRTextureView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOwnSurfaceTexture = true;
        this.mWillDetachFromWindow = false;
        this.mDidDetachFromWindow = false;
    }

    @TargetApi(21)
    public BaseVSRTextureView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mOwnSurfaceTexture = true;
        this.mWillDetachFromWindow = false;
        this.mDidDetachFromWindow = false;
    }
}
