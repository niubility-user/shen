package tv.danmaku.ijk.media.widget.window;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import tv.danmaku.ijk.media.player.IJDVideoPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.utils.PlayerSystemUtil;
import tv.danmaku.ijk.media.widget.controller.JDWindowPlayerController;

/* loaded from: classes11.dex */
public class JDWindowPlayer extends FrameLayout implements WindowPlayerControl {
    private boolean isLive;
    private float mBorderRadius;
    private int mBorderWidth;
    private JDWindowPlayerCallback mCallback;
    private JDWindowPlayerController mController;
    private IJDVideoPlayer mRealVideoView;
    private ViewGroup mRootView;
    private int mStatusBarHeight;
    private ViewGroup mVideoView;
    private float mXDownInScreen;
    private float mXInScreen;
    private float mXInView;
    private float mYDownInScreen;
    private float mYInScreen;
    private float mYInView;
    private boolean preMute;

    /* loaded from: classes11.dex */
    public interface JDWindowPlayerCallback {
        void closeWindow();

        boolean onWindowClick();

        void onWindowPosChange(int i2, int i3);
    }

    public JDWindowPlayer(@NonNull Context context) {
        this(context, null);
    }

    private Drawable createBorder(int i2, float f2, String str) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setStroke(PlayerSystemUtil.dip2px(getContext(), i2), Color.parseColor(str));
        gradientDrawable.setColor(0);
        gradientDrawable.setCornerRadius(PlayerSystemUtil.dip2px(getContext(), f2));
        return gradientDrawable;
    }

    private void initView(Context context) {
        this.mRootView = this;
        this.mStatusBarHeight = PlayerSystemUtil.getStatusBarHeight(context);
    }

    private void updateViewPosition() {
        int i2 = (int) (this.mXInScreen - this.mXInView);
        int i3 = (int) (this.mYInScreen - this.mYInView);
        JDWindowPlayerCallback jDWindowPlayerCallback = this.mCallback;
        if (jDWindowPlayerCallback != null) {
            jDWindowPlayerCallback.onWindowPosChange(i2, i3);
        }
    }

    public void attachController(JDWindowPlayerController jDWindowPlayerController) {
        this.mController = jDWindowPlayerController;
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void close() {
        JDWindowPlayerCallback jDWindowPlayerCallback = this.mCallback;
        if (jDWindowPlayerCallback == null) {
            return;
        }
        jDWindowPlayerCallback.closeWindow();
    }

    public ViewGroup getVideoView() {
        ViewGroup viewGroup = this.mVideoView;
        if (viewGroup != null && viewGroup.getParent() != null && (this.mVideoView.getParent() instanceof ViewGroup)) {
            ((ViewGroup) this.mVideoView.getParent()).removeView(this.mVideoView);
            if (this.mBorderRadius > 0.0f && Build.VERSION.SDK_INT >= 21) {
                this.mVideoView.setOutlineProvider(null);
                this.mVideoView.setClipToOutline(false);
            }
        }
        return this.mVideoView;
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void hide() {
        if (this.mRealVideoView == null) {
            return;
        }
        this.preMute = isMute();
        this.mRealVideoView.setVolume(0.0f);
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public boolean isMute() {
        IJDVideoPlayer iJDVideoPlayer = this.mRealVideoView;
        return iJDVideoPlayer != null && iJDVideoPlayer.getVolume() == 0.0f;
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public boolean isPlaying() {
        IJDVideoPlayer iJDVideoPlayer = this.mRealVideoView;
        return iJDVideoPlayer != null && iJDVideoPlayer.isPlaying();
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public boolean mute() {
        if (this.mRealVideoView == null) {
            return false;
        }
        boolean z = !isMute();
        this.mRealVideoView.setVolume(z ? 0.0f : 1.0f);
        return z;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        JDWindowPlayerCallback jDWindowPlayerCallback;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mXInView = motionEvent.getX();
            this.mYInView = motionEvent.getY();
            this.mXDownInScreen = motionEvent.getRawX();
            this.mYDownInScreen = motionEvent.getRawY() - this.mStatusBarHeight;
            this.mXInScreen = motionEvent.getRawX();
            this.mYInScreen = motionEvent.getRawY() - this.mStatusBarHeight;
        } else if (action != 1) {
            if (action == 2) {
                this.mXInScreen = motionEvent.getRawX();
                this.mYInScreen = motionEvent.getRawY() - this.mStatusBarHeight;
                updateViewPosition();
            }
        } else if (this.mXDownInScreen == this.mXInScreen && this.mYDownInScreen == this.mYInScreen && (jDWindowPlayerCallback = this.mCallback) != null && jDWindowPlayerCallback.onWindowClick()) {
            this.mCallback.closeWindow();
        }
        return true;
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void pause() {
        IJDVideoPlayer iJDVideoPlayer = this.mRealVideoView;
        if (iJDVideoPlayer == null) {
            return;
        }
        if (this.isLive) {
            iJDVideoPlayer.suspend();
        } else {
            iJDVideoPlayer.pause();
        }
    }

    public void releasePlayer() {
        IJDVideoPlayer iJDVideoPlayer = this.mRealVideoView;
        if (iJDVideoPlayer != null) {
            iJDVideoPlayer.release();
            this.mRealVideoView = null;
        }
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void reload() {
        IJDVideoPlayer iJDVideoPlayer = this.mRealVideoView;
        if (iJDVideoPlayer == null) {
            return;
        }
        iJDVideoPlayer.retry(true);
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void resume() {
        IJDVideoPlayer iJDVideoPlayer = this.mRealVideoView;
        if (iJDVideoPlayer == null) {
            return;
        }
        if (this.isLive) {
            iJDVideoPlayer.resume();
        } else {
            iJDVideoPlayer.start();
        }
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void setOnPlayerEventListener(IMediaPlayer.OnPlayerEventListener onPlayerEventListener) {
        IJDVideoPlayer iJDVideoPlayer = this.mRealVideoView;
        if (iJDVideoPlayer == null || onPlayerEventListener == null) {
            return;
        }
        iJDVideoPlayer.setOnPlayerEventListener(onPlayerEventListener);
    }

    public void setVideoView(ViewGroup viewGroup, boolean z) {
        if (viewGroup != null && (viewGroup instanceof IJDVideoPlayer)) {
            this.mRealVideoView = (IJDVideoPlayer) viewGroup;
            this.isLive = z;
            this.mVideoView = viewGroup;
            if (this.mBorderRadius > 0.0f && Build.VERSION.SDK_INT >= 21) {
                viewGroup.setOutlineProvider(new JDPlayerTextureOutlineProvider(PlayerSystemUtil.dip2px(getContext(), this.mBorderRadius - (this.mBorderWidth - 1))));
                this.mVideoView.setClipToOutline(true);
            }
            this.mRootView.addView(this.mVideoView, 0, new FrameLayout.LayoutParams(-1, -1));
            JDWindowPlayerController jDWindowPlayerController = this.mController;
            if (jDWindowPlayerController != null) {
                jDWindowPlayerController.setAnchorView(this.mRootView);
                this.mController.setWindowPlayer(this);
            }
            requestLayout();
        }
    }

    public void setWindowPlayerCallback(JDWindowPlayerCallback jDWindowPlayerCallback) {
        this.mCallback = jDWindowPlayerCallback;
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void show() {
        IJDVideoPlayer iJDVideoPlayer = this.mRealVideoView;
        if (iJDVideoPlayer != null && this.preMute) {
            iJDVideoPlayer.setVolume(1.0f);
        }
    }

    public void showBorder(int i2, float f2, String str) {
        this.mBorderRadius = f2;
        this.mBorderWidth = i2;
        int dip2px = PlayerSystemUtil.dip2px(getContext(), i2);
        setPadding(dip2px, dip2px, dip2px, dip2px);
        setBackgroundDrawable(createBorder(i2, f2, str));
    }

    public JDWindowPlayer(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDWindowPlayer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initView(context);
    }

    @TargetApi(21)
    public JDWindowPlayer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        initView(context);
    }
}
