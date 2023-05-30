package tv.danmaku.ijk.media.example.widget.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import tv.danmaku.ijk.media.example.widget.media.IRenderView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.ISurfaceTextureHolder;
import tv.danmaku.ijk.media.player.ISurfaceTextureHost;
import tv.danmaku.ijk.media.utils.DebugLog;

@TargetApi(14)
/* loaded from: classes11.dex */
public class TextureRenderView extends TextureView implements IRenderView {
    private static final String TAG = "TextureRenderView";
    private SurfaceTexture extraTexture;
    private MeasureHelper mMeasureHelper;
    private SurfaceCallback mSurfaceCallback;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class InternalSurfaceHolder implements IRenderView.ISurfaceHolder {
        private SurfaceTexture mSurfaceTexture;
        private ISurfaceTextureHost mSurfaceTextureHost;
        private TextureRenderView mTextureView;

        public InternalSurfaceHolder(@NonNull TextureRenderView textureRenderView, @Nullable SurfaceTexture surfaceTexture, @NonNull ISurfaceTextureHost iSurfaceTextureHost) {
            this.mTextureView = textureRenderView;
            this.mSurfaceTexture = surfaceTexture;
            this.mSurfaceTextureHost = iSurfaceTextureHost;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.ISurfaceHolder
        public void bindToMediaPlayer(IMediaPlayer iMediaPlayer) {
            if (iMediaPlayer == null) {
                return;
            }
            if (iMediaPlayer instanceof ISurfaceTextureHolder) {
                ISurfaceTextureHolder iSurfaceTextureHolder = (ISurfaceTextureHolder) iMediaPlayer;
                this.mTextureView.mSurfaceCallback.setOwnSurfaceTexture(false);
                SurfaceTexture surfaceTexture = iSurfaceTextureHolder.getSurfaceTexture();
                if (surfaceTexture != null) {
                    try {
                        this.mTextureView.setSurfaceTexture(surfaceTexture);
                        return;
                    } catch (Throwable unused) {
                        return;
                    }
                }
                iSurfaceTextureHolder.setSurfaceTexture(this.mSurfaceTexture);
                iSurfaceTextureHolder.setSurfaceTextureHost(this.mTextureView.mSurfaceCallback);
                return;
            }
            iMediaPlayer.setSurface(openSurface());
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.ISurfaceHolder
        @NonNull
        public IRenderView getRenderView() {
            return this.mTextureView;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.ISurfaceHolder
        @Nullable
        public SurfaceHolder getSurfaceHolder() {
            return null;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.ISurfaceHolder
        @Nullable
        public SurfaceTexture getSurfaceTexture() {
            return this.mSurfaceTexture;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.ISurfaceHolder
        @Nullable
        public Surface openSurface() {
            if (this.mSurfaceTexture == null) {
                return null;
            }
            return new Surface(this.mSurfaceTexture);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class SurfaceCallback implements TextureView.SurfaceTextureListener, ISurfaceTextureHost {
        private int mHeight;
        private boolean mIsFormatChanged;
        private SurfaceTexture mSurfaceTexture;
        private WeakReference<TextureRenderView> mWeakRenderView;
        private int mWidth;
        private boolean mOwnSurfaceTexture = true;
        private boolean mWillDetachFromWindow = false;
        private boolean mDidDetachFromWindow = false;
        private Map<IRenderView.IRenderCallback, Object> mRenderCallbackMap = new ConcurrentHashMap();

        public SurfaceCallback(@NonNull TextureRenderView textureRenderView) {
            this.mWeakRenderView = new WeakReference<>(textureRenderView);
        }

        public void addRenderCallback(@NonNull IRenderView.IRenderCallback iRenderCallback) {
            InternalSurfaceHolder internalSurfaceHolder;
            this.mRenderCallbackMap.put(iRenderCallback, iRenderCallback);
            if (this.mSurfaceTexture != null) {
                internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get(), this.mSurfaceTexture, this);
                iRenderCallback.onSurfaceCreated(internalSurfaceHolder, this.mWidth, this.mHeight);
            } else {
                internalSurfaceHolder = null;
            }
            if (this.mIsFormatChanged) {
                if (internalSurfaceHolder == null) {
                    internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get(), this.mSurfaceTexture, this);
                }
                iRenderCallback.onSurfaceChanged(internalSurfaceHolder, 0, this.mWidth, this.mHeight);
            }
        }

        public void didDetachFromWindow() {
            DebugLog.d(TextureRenderView.TAG, "didDetachFromWindow()");
            this.mDidDetachFromWindow = true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
            if (this.mWeakRenderView.get() != null && this.mWeakRenderView.get().extraTexture != null) {
                this.mSurfaceTexture = this.mWeakRenderView.get().extraTexture;
                try {
                    this.mWeakRenderView.get().setSurfaceTexture(this.mSurfaceTexture);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                this.mSurfaceTexture = surfaceTexture;
            }
            this.mIsFormatChanged = false;
            this.mWidth = 0;
            this.mHeight = 0;
            InternalSurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get(), this.mSurfaceTexture, this);
            Iterator<IRenderView.IRenderCallback> it = this.mRenderCallbackMap.keySet().iterator();
            while (it.hasNext()) {
                it.next().onSurfaceCreated(internalSurfaceHolder, 0, 0);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            if (this.mWeakRenderView.get() != null && this.mWeakRenderView.get().extraTexture != null) {
                this.mSurfaceTexture = this.mWeakRenderView.get().extraTexture;
            } else {
                this.mSurfaceTexture = surfaceTexture;
            }
            this.mIsFormatChanged = false;
            this.mWidth = 0;
            this.mHeight = 0;
            InternalSurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get(), this.mSurfaceTexture, this);
            Iterator<IRenderView.IRenderCallback> it = this.mRenderCallbackMap.keySet().iterator();
            while (it.hasNext()) {
                it.next().onSurfaceDestroyed(internalSurfaceHolder);
            }
            DebugLog.d(TextureRenderView.TAG, "onSurfaceTextureDestroyed: destroy: " + this.mOwnSurfaceTexture);
            return this.mOwnSurfaceTexture;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
            if (this.mWeakRenderView.get() != null && this.mWeakRenderView.get().extraTexture != null) {
                this.mSurfaceTexture = this.mWeakRenderView.get().extraTexture;
            } else {
                this.mSurfaceTexture = surfaceTexture;
            }
            this.mIsFormatChanged = true;
            this.mWidth = i2;
            this.mHeight = i3;
            InternalSurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get(), this.mSurfaceTexture, this);
            Iterator<IRenderView.IRenderCallback> it = this.mRenderCallbackMap.keySet().iterator();
            while (it.hasNext()) {
                it.next().onSurfaceChanged(internalSurfaceHolder, 0, i2, i3);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            Iterator<IRenderView.IRenderCallback> it = this.mRenderCallbackMap.keySet().iterator();
            while (it.hasNext()) {
                it.next().onSurfaceTextureUpdated();
            }
        }

        @Override // tv.danmaku.ijk.media.player.ISurfaceTextureHost
        public void releaseSurfaceTexture(SurfaceTexture surfaceTexture) {
            if (surfaceTexture == null) {
                DebugLog.d(TextureRenderView.TAG, "releaseSurfaceTexture: null");
            } else if (this.mDidDetachFromWindow) {
                if (surfaceTexture != this.mSurfaceTexture) {
                    DebugLog.d(TextureRenderView.TAG, "releaseSurfaceTexture: didDetachFromWindow(): release different SurfaceTexture");
                    surfaceTexture.release();
                } else if (!this.mOwnSurfaceTexture) {
                    DebugLog.d(TextureRenderView.TAG, "releaseSurfaceTexture: didDetachFromWindow(): release detached SurfaceTexture");
                    surfaceTexture.release();
                } else {
                    DebugLog.d(TextureRenderView.TAG, "releaseSurfaceTexture: didDetachFromWindow(): already released by TextureView");
                }
            } else if (this.mWillDetachFromWindow) {
                if (surfaceTexture != this.mSurfaceTexture) {
                    DebugLog.d(TextureRenderView.TAG, "releaseSurfaceTexture: willDetachFromWindow(): release different SurfaceTexture");
                    surfaceTexture.release();
                } else if (!this.mOwnSurfaceTexture) {
                    DebugLog.d(TextureRenderView.TAG, "releaseSurfaceTexture: willDetachFromWindow(): re-attach SurfaceTexture to TextureView");
                    setOwnSurfaceTexture(true);
                } else {
                    DebugLog.d(TextureRenderView.TAG, "releaseSurfaceTexture: willDetachFromWindow(): will released by TextureView");
                }
            } else if (surfaceTexture != this.mSurfaceTexture) {
                DebugLog.d(TextureRenderView.TAG, "releaseSurfaceTexture: alive: release different SurfaceTexture");
                surfaceTexture.release();
            } else if (!this.mOwnSurfaceTexture) {
                DebugLog.d(TextureRenderView.TAG, "releaseSurfaceTexture: alive: re-attach SurfaceTexture to TextureView");
                setOwnSurfaceTexture(true);
            } else {
                DebugLog.d(TextureRenderView.TAG, "releaseSurfaceTexture: alive: will released by TextureView");
            }
        }

        public void removeRenderCallback(@NonNull IRenderView.IRenderCallback iRenderCallback) {
            this.mRenderCallbackMap.remove(iRenderCallback);
        }

        public void removeRenderCallbacks() {
            this.mRenderCallbackMap.clear();
        }

        public void setOwnSurfaceTexture(boolean z) {
            this.mOwnSurfaceTexture = z;
        }

        public void willDetachFromWindow() {
            DebugLog.d(TextureRenderView.TAG, "willDetachFromWindow()");
            this.mWillDetachFromWindow = true;
        }
    }

    public TextureRenderView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        this.mMeasureHelper = new MeasureHelper(this);
        SurfaceCallback surfaceCallback = new SurfaceCallback(this);
        this.mSurfaceCallback = surfaceCallback;
        setSurfaceTextureListener(surfaceCallback);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView
    public void addRenderCallback(IRenderView.IRenderCallback iRenderCallback) {
        this.mSurfaceCallback.addRenderCallback(iRenderCallback);
    }

    public SurfaceTexture getExtraTexture() {
        return this.extraTexture;
    }

    public MeasureHelper getMeasureHelper() {
        return this.mMeasureHelper;
    }

    public IRenderView.ISurfaceHolder getSurfaceHolder() {
        return new InternalSurfaceHolder(this, this.mSurfaceCallback.mSurfaceTexture, this.mSurfaceCallback);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView
    public View getView() {
        return this;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        this.mSurfaceCallback.willDetachFromWindow();
        try {
            super.onDetachedFromWindow();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.mSurfaceCallback.didDetachFromWindow();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(TextureRenderView.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(TextureRenderView.class.getName());
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        this.mMeasureHelper.doMeasure(i2, i3);
        setMeasuredDimension(this.mMeasureHelper.getMeasuredWidth(), this.mMeasureHelper.getMeasuredHeight());
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView
    public void release() {
        this.extraTexture = null;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView
    public void removeRenderCallback(IRenderView.IRenderCallback iRenderCallback) {
        this.mSurfaceCallback.removeRenderCallback(iRenderCallback);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView
    public void removeRenderCallbacks() {
        this.mSurfaceCallback.removeRenderCallbacks();
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView
    public void setAspectRatio(int i2) {
        this.mMeasureHelper.setAspectRatio(i2);
        requestLayout();
    }

    public void setExtraTexture(SurfaceTexture surfaceTexture) {
        this.extraTexture = surfaceTexture;
        try {
            setSurfaceTexture(surfaceTexture);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        SurfaceCallback surfaceCallback = this.mSurfaceCallback;
        if (surfaceCallback != null) {
            surfaceCallback.mSurfaceTexture = surfaceTexture;
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView
    public void setVideoRotation(int i2) {
        this.mMeasureHelper.setVideoRotation(i2);
        setRotation(i2);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView
    public void setVideoSampleAspectRatio(int i2, int i3) {
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        this.mMeasureHelper.setVideoSampleAspectRatio(i2, i3);
        requestLayout();
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView
    public void setVideoSize(int i2, int i3) {
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        this.mMeasureHelper.setVideoSize(i2, i3);
        requestLayout();
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView
    public boolean shouldWaitForResize() {
        return false;
    }

    public TextureRenderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public TextureRenderView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initView(context);
    }

    @TargetApi(21)
    public TextureRenderView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        initView(context);
    }
}
