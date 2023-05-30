package tv.danmaku.ijk.media.vsr;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdcloud.vsr.JDTVSRRender;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.constant.JshopConst;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import tv.danmaku.ijk.media.example.widget.media.IRenderView;
import tv.danmaku.ijk.media.example.widget.media.MeasureHelper;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.ISurfaceTextureHolder;
import tv.danmaku.ijk.media.player.ISurfaceTextureHost;
import tv.danmaku.ijk.media.utils.DebugLog;

@TargetApi(14)
/* loaded from: classes11.dex */
public class JDTVSRTextureView extends TextureView implements IRenderView {
    private static final String TAG = "JDTVSRTextureView";
    private boolean mEnableVSR;
    private MeasureHelper mMeasureHelper;
    private SurfaceCallback mSurfaceCallback;
    private JDTVSRRender mVSRRender;
    private int mVideoHeight;
    private int mVideoWidth;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class InternalSurfaceHolder implements IRenderView.ISurfaceHolder {
        private SurfaceTexture mSurfaceTexture;
        private ISurfaceTextureHost mSurfaceTextureHost;
        private JDTVSRTextureView mTextureView;

        public InternalSurfaceHolder(@NonNull JDTVSRTextureView jDTVSRTextureView, @Nullable SurfaceTexture surfaceTexture, @NonNull ISurfaceTextureHost iSurfaceTextureHost) {
            this.mTextureView = jDTVSRTextureView;
            this.mSurfaceTexture = surfaceTexture;
            this.mSurfaceTextureHost = iSurfaceTextureHost;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.ISurfaceHolder
        @TargetApi(16)
        public void bindToMediaPlayer(IMediaPlayer iMediaPlayer) {
            if (iMediaPlayer == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 16 && (iMediaPlayer instanceof ISurfaceTextureHolder)) {
                ISurfaceTextureHolder iSurfaceTextureHolder = (ISurfaceTextureHolder) iMediaPlayer;
                this.mTextureView.mSurfaceCallback.setOwnSurfaceTexture(false);
                SurfaceTexture surfaceTexture = iSurfaceTextureHolder.getSurfaceTexture();
                if (surfaceTexture != null) {
                    try {
                        DebugLog.e(JDTVSRTextureView.TAG, "holder mp: " + iMediaPlayer + LangUtils.SINGLE_SPACE + this.mTextureView + " setSurfaceTexture " + surfaceTexture + ":" + getSurfaceTexture());
                        if (surfaceTexture != getSurfaceTexture()) {
                            DebugLog.e(JDTVSRTextureView.TAG, "holder mp: " + iMediaPlayer + " TextureView " + this.mTextureView + " setSurfaceTexture " + surfaceTexture + " right now ");
                            return;
                        }
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
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
        private WeakReference<JDTVSRTextureView> mWeakRenderView;
        private int mWidth;
        private boolean mOwnSurfaceTexture = true;
        private boolean mWillDetachFromWindow = false;
        private boolean mDidDetachFromWindow = false;
        private Map<IRenderView.IRenderCallback, Object> mRenderCallbackMap = new ConcurrentHashMap();

        public SurfaceCallback(@NonNull JDTVSRTextureView jDTVSRTextureView) {
            this.mWeakRenderView = new WeakReference<>(jDTVSRTextureView);
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
            DebugLog.e(JDTVSRTextureView.TAG, "didDetachFromWindow()");
            this.mDidDetachFromWindow = true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
            DebugLog.e(JDTVSRTextureView.TAG, "onSurfaceTextureAvailable " + surfaceTexture + LangUtils.SINGLE_SPACE + this.mOwnSurfaceTexture);
            this.mIsFormatChanged = false;
            this.mWidth = 0;
            this.mHeight = 0;
            if (this.mWeakRenderView.get().mVSRRender != null) {
                this.mWeakRenderView.get().mVSRRender.bindSurface(surfaceTexture);
                this.mSurfaceTexture = this.mWeakRenderView.get().mVSRRender.getSurfaceTexture();
            } else {
                this.mSurfaceTexture = surfaceTexture;
            }
            InternalSurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get(), this.mSurfaceTexture, this);
            Iterator<IRenderView.IRenderCallback> it = this.mRenderCallbackMap.keySet().iterator();
            while (it.hasNext()) {
                it.next().onSurfaceCreated(internalSurfaceHolder, 0, 0);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            DebugLog.e(JDTVSRTextureView.TAG, "onSurfaceTextureDestroyed " + surfaceTexture + LangUtils.SINGLE_SPACE + this.mOwnSurfaceTexture);
            this.mIsFormatChanged = false;
            this.mWidth = 0;
            this.mHeight = 0;
            if (this.mWeakRenderView.get().mVSRRender != null) {
                this.mSurfaceTexture = this.mWeakRenderView.get().mVSRRender.getSurfaceTexture();
                this.mWeakRenderView.get().mVSRRender.unbindSurface();
            } else {
                this.mSurfaceTexture = surfaceTexture;
            }
            InternalSurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get(), this.mSurfaceTexture, this);
            Iterator<IRenderView.IRenderCallback> it = this.mRenderCallbackMap.keySet().iterator();
            while (it.hasNext()) {
                it.next().onSurfaceDestroyed(internalSurfaceHolder);
            }
            DebugLog.e(JDTVSRTextureView.TAG, "onSurfaceTextureDestroyed: destroy: " + surfaceTexture);
            return this.mOwnSurfaceTexture;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
            DebugLog.e(JDTVSRTextureView.TAG, "onSurfaceTextureSizeChanged " + surfaceTexture + " : " + i2 + " x " + i3 + LangUtils.SINGLE_SPACE + this.mOwnSurfaceTexture);
            this.mIsFormatChanged = true;
            this.mWidth = i2;
            this.mHeight = i3;
            if (this.mWeakRenderView.get().mVSRRender != null) {
                this.mWeakRenderView.get().mVSRRender.rebindSurface(surfaceTexture);
                this.mSurfaceTexture = this.mWeakRenderView.get().mVSRRender.getSurfaceTexture();
            } else {
                this.mSurfaceTexture = surfaceTexture;
            }
            InternalSurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get(), this.mSurfaceTexture, this);
            Iterator<IRenderView.IRenderCallback> it = this.mRenderCallbackMap.keySet().iterator();
            while (it.hasNext()) {
                it.next().onSurfaceChanged(internalSurfaceHolder, 0, i2, i3);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            if (this.mWeakRenderView.get().mVideoWidth == 0 || this.mWeakRenderView.get().mVSRRender == null) {
                return;
            }
            this.mWeakRenderView.get().mVSRRender.notifyUpdate(this.mWeakRenderView.get().mVideoWidth, this.mWeakRenderView.get().mVideoHeight);
        }

        @Override // tv.danmaku.ijk.media.player.ISurfaceTextureHost
        public void releaseSurfaceTexture(SurfaceTexture surfaceTexture) {
            DebugLog.e(JDTVSRTextureView.TAG, "releaseSurfaceTexture: " + surfaceTexture);
            if (surfaceTexture == null) {
                DebugLog.d(JDTVSRTextureView.TAG, "releaseSurfaceTexture: null");
            } else if (this.mDidDetachFromWindow) {
                if (surfaceTexture != this.mSurfaceTexture) {
                    DebugLog.d(JDTVSRTextureView.TAG, "releaseSurfaceTexture: didDetachFromWindow(): release different SurfaceTexture");
                    surfaceTexture.release();
                } else if (!this.mOwnSurfaceTexture) {
                    DebugLog.d(JDTVSRTextureView.TAG, "releaseSurfaceTexture: didDetachFromWindow(): release detached SurfaceTexture");
                    surfaceTexture.release();
                } else {
                    DebugLog.d(JDTVSRTextureView.TAG, "releaseSurfaceTexture: didDetachFromWindow(): already released by TextureView");
                }
            } else if (this.mWillDetachFromWindow) {
                SurfaceTexture surfaceTexture2 = this.mSurfaceTexture;
                if (surfaceTexture != surfaceTexture2) {
                    DebugLog.d(JDTVSRTextureView.TAG, "releaseSurfaceTexture: willDetachFromWindow(): release different SurfaceTexture");
                    surfaceTexture.release();
                } else if (!this.mOwnSurfaceTexture) {
                    surfaceTexture2.release();
                    DebugLog.d(JDTVSRTextureView.TAG, "releaseSurfaceTexture: willDetachFromWindow(): re-attach SurfaceTexture to TextureView");
                    setOwnSurfaceTexture(true);
                } else {
                    DebugLog.d(JDTVSRTextureView.TAG, "releaseSurfaceTexture: willDetachFromWindow(): will released by TextureView");
                }
            } else if (surfaceTexture != this.mSurfaceTexture) {
                DebugLog.d(JDTVSRTextureView.TAG, "releaseSurfaceTexture: alive: release different SurfaceTexture");
                surfaceTexture.release();
            } else if (!this.mOwnSurfaceTexture) {
                DebugLog.d(JDTVSRTextureView.TAG, "releaseSurfaceTexture: alive: re-attach SurfaceTexture to TextureView");
                setOwnSurfaceTexture(true);
            } else {
                DebugLog.d(JDTVSRTextureView.TAG, "releaseSurfaceTexture: alive: will released by TextureView");
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
            DebugLog.e(JDTVSRTextureView.TAG, "willDetachFromWindow()");
            this.mWillDetachFromWindow = true;
        }
    }

    public JDTVSRTextureView(Context context) {
        super(context);
        this.mEnableVSR = false;
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

    public boolean enableVsr(boolean z) {
        if (this.mEnableVSR == z) {
            return z;
        }
        JDTVSRRender jDTVSRRender = this.mVSRRender;
        if (jDTVSRRender == null) {
            return false;
        }
        this.mEnableVSR = z;
        if (this.mVideoWidth > 0 && this.mVideoHeight > 0) {
            jDTVSRRender.enableVSR(z);
        }
        return z;
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
        super.onDetachedFromWindow();
        this.mSurfaceCallback.didDetachFromWindow();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(JDTVSRTextureView.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(JDTVSRTextureView.class.getName());
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        this.mMeasureHelper.doMeasure(i2, i3);
        setMeasuredDimension(this.mMeasureHelper.getMeasuredWidth(), this.mMeasureHelper.getMeasuredHeight());
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView
    public void release() {
        JDTVSRRender jDTVSRRender = this.mVSRRender;
        if (jDTVSRRender != null) {
            jDTVSRRender.destroyEngine();
            this.mVSRRender = null;
        }
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

    public void setVSRRender(JDTVSRRender jDTVSRRender) {
        this.mVSRRender = jDTVSRRender;
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
        if (this.mVideoWidth == i2 && this.mVideoHeight == i3) {
            return;
        }
        this.mVideoHeight = i3;
        this.mVideoWidth = i2;
        DebugLog.e(TAG, "setVideoSize " + i2 + JshopConst.JSHOP_PROMOTIO_X + i3);
        JDTVSRRender jDTVSRRender = this.mVSRRender;
        if (jDTVSRRender != null) {
            jDTVSRRender.buildVSRPipeline(i2, i3, this.mEnableVSR);
        }
        this.mMeasureHelper.setVideoSize(i2, i3);
        requestLayout();
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView
    public boolean shouldWaitForResize() {
        return false;
    }

    public JDTVSRTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEnableVSR = false;
        initView(context);
    }

    public JDTVSRTextureView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mEnableVSR = false;
        initView(context);
    }

    @TargetApi(21)
    public JDTVSRTextureView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mEnableVSR = false;
        initView(context);
    }
}
