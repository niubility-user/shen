package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ARTSurfaceViewShadowNode extends LayoutShadowNode implements TextureView.SurfaceTextureListener {
    @Nullable
    private Integer mBackgroundColor;
    @Nullable
    private Surface mSurface;

    private void drawOutput() {
        Surface surface = this.mSurface;
        if (surface != null && surface.isValid()) {
            try {
                Canvas lockCanvas = this.mSurface.lockCanvas(null);
                lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                Integer num = this.mBackgroundColor;
                if (num != null) {
                    lockCanvas.drawColor(num.intValue());
                }
                Paint paint = new Paint();
                for (int i2 = 0; i2 < getChildCount(); i2++) {
                    ARTVirtualNode aRTVirtualNode = (ARTVirtualNode) getChildAt(i2);
                    aRTVirtualNode.draw(lockCanvas, paint, 1.0f);
                    aRTVirtualNode.markUpdateSeen();
                }
                Surface surface2 = this.mSurface;
                if (surface2 == null) {
                    return;
                }
                surface2.unlockCanvasAndPost(lockCanvas);
                return;
            } catch (IllegalArgumentException | IllegalStateException e2) {
                FLog.e(ReactConstants.TAG, e2.getClass().getSimpleName() + " in Surface.unlockCanvasAndPost");
                return;
            }
        }
        markChildrenUpdatesSeen(this);
    }

    private void markChildrenUpdatesSeen(ReactShadowNode reactShadowNode) {
        for (int i2 = 0; i2 < reactShadowNode.getChildCount(); i2++) {
            ReactShadowNode childAt = reactShadowNode.getChildAt(i2);
            childAt.markUpdateSeen();
            markChildrenUpdatesSeen(childAt);
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return false;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtualAnchor() {
        return true;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void onCollectExtraUpdates(UIViewOperationQueue uIViewOperationQueue) {
        super.onCollectExtraUpdates(uIViewOperationQueue);
        drawOutput();
        uIViewOperationQueue.enqueueUpdateExtraData(getReactTag(), this);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.mSurface = new Surface(surfaceTexture);
        drawOutput();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        surfaceTexture.release();
        this.mSurface = null;
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @ReactProp(customType = "Color", name = ViewProps.BACKGROUND_COLOR)
    public void setBackgroundColor(Integer num) {
        this.mBackgroundColor = num;
        markUpdated();
    }
}
