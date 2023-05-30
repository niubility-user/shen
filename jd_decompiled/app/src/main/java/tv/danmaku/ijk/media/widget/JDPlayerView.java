package tv.danmaku.ijk.media.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import tv.danmaku.ijk.media.IMediaControllerExt;
import tv.danmaku.ijk.media.alpha.widget.AlphaVideoView;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.player.IJDVideoPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.utils.PlayerNetworkUtil;
import tv.danmaku.ijk.media.utils.PlayerSystemUtil;
import tv.danmaku.ijk.media.widget.controller.JDControllerOptions;
import tv.danmaku.ijk.media.widget.controller.JDItemSmallController;
import tv.danmaku.ijk.media.widget.controller.JDPlayerController;
import tv.danmaku.ijk.media.widget.controller.OnControllerListener;
import tv.danmaku.ijk.media.widget.window.JDPlayerTextureOutlineProvider;

/* loaded from: classes11.dex */
public class JDPlayerView extends FrameLayout implements MediaController.MediaPlayerControl {
    private PlayerControllerCallback controllerCallback;
    private JDControllerOptions controllerOptions;
    private boolean disIntercept;
    private boolean enableLoading;
    private boolean enableSensor;
    private boolean forceLayout;
    private boolean hasError;
    private SimpleDraweeView imgCover;
    private boolean isCompleted;
    private boolean isFullScreen;
    private boolean isLandVideo;
    private ImageView ivPlay;
    private Context mContext;
    private String mPlayPath;
    private final Runnable measureAndLayout;
    private IMediaControllerExt mediaController;
    private final PlayerNetworkUtil.NetChangeListener netChangeListener;
    private IMediaPlayer.OnPlayerEventListener onPlayerEventListener;
    private final IPlayerControl.OnPlayerStateListener onPlayerStateListener;
    private boolean outControlPlayIcon;
    private int outControlVisible;
    private IPlayerControl.PlayerOptions playerOptions;
    private float playerRadius;
    private IPlayerControl.OnPlayerStateListener playerStateListener;
    private ProgressBar progressBar;
    private FrameLayout rlContainer;
    private IJDVideoPlayer videoView;

    /* renamed from: tv.danmaku.ijk.media.widget.JDPlayerView$7 */
    /* loaded from: classes11.dex */
    public static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$tv$danmaku$ijk$media$widget$JDPlayerView$PlayMode;
        static final /* synthetic */ int[] $SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$FullMode;
        static final /* synthetic */ int[] $SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$UIMode;

        static {
            int[] iArr = new int[JDControllerOptions.FullMode.values().length];
            $SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$FullMode = iArr;
            try {
                iArr[JDControllerOptions.FullMode.FULL_PORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$FullMode[JDControllerOptions.FullMode.FULL_LAND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$FullMode[JDControllerOptions.FullMode.FULL_AUTO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[PlayMode.values().length];
            $SwitchMap$tv$danmaku$ijk$media$widget$JDPlayerView$PlayMode = iArr2;
            try {
                iArr2[PlayMode.AUTO_PLAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$JDPlayerView$PlayMode[PlayMode.CLICK_PLAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$JDPlayerView$PlayMode[PlayMode.WIFI_PLAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[JDControllerOptions.UIMode.values().length];
            $SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$UIMode = iArr3;
            try {
                iArr3[JDControllerOptions.UIMode.UI_ITEM_SMALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$UIMode[JDControllerOptions.UIMode.UI_DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$UIMode[JDControllerOptions.UIMode.UI_ITEM_BIG.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public enum PlayMode {
        WIFI_PLAY,
        AUTO_PLAY,
        CLICK_PLAY
    }

    /* loaded from: classes11.dex */
    public interface PlayerControllerCallback {
        void onNetChanged();

        void onOrientationChanged(boolean z, int i2);

        void onPlayBtnClick(boolean z);

        void onProgressUpdate(boolean z, int i2, long j2, boolean z2);

        void onVoiceBtnClick(boolean z);

        void onVoiceStateChange(boolean z);

        void seekBarOnSeek(int i2);
    }

    public JDPlayerView(@NonNull Context context) {
        this(context, null);
    }

    private void applyRoundRadius() {
        if (this.playerRadius > 0.0f && Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new JDPlayerTextureOutlineProvider(PlayerSystemUtil.dip2px(getContext(), this.playerRadius)));
            setClipToOutline(true);
            requestLayout();
        }
    }

    private void attachController(boolean z, JDControllerOptions jDControllerOptions) {
        if (this.videoView == null || this.mContext == null) {
            return;
        }
        this.controllerOptions = jDControllerOptions;
        if (jDControllerOptions != null) {
            this.enableLoading = jDControllerOptions.enableLoading;
        }
        if (z) {
            if (this.mediaController == null) {
                createController();
            }
            this.mediaController.setControllerOptions(this, jDControllerOptions);
            this.mediaController.setOnControllerListener(new OnControllerListener() { // from class: tv.danmaku.ijk.media.widget.JDPlayerView.4
                {
                    JDPlayerView.this = this;
                }

                @Override // tv.danmaku.ijk.media.widget.controller.OnControllerListener
                public void onOrientationChanged(boolean z2, int i2) {
                    JDPlayerView.this.isFullScreen = z2;
                    if (JDPlayerView.this.controllerCallback == null) {
                        return;
                    }
                    JDPlayerView.this.controllerCallback.onOrientationChanged(z2, i2);
                }

                @Override // tv.danmaku.ijk.media.widget.controller.OnControllerListener
                public void onPlayBtnClick(boolean z2) {
                    if (JDPlayerView.this.controllerCallback == null) {
                        return;
                    }
                    JDPlayerView.this.controllerCallback.onPlayBtnClick(z2);
                }

                @Override // tv.danmaku.ijk.media.widget.controller.OnControllerListener
                public void onProgressUpdate(boolean z2, int i2, long j2, boolean z3) {
                    if (JDPlayerView.this.controllerCallback == null) {
                        return;
                    }
                    JDPlayerView.this.controllerCallback.onProgressUpdate(z2, i2, j2, z3);
                }

                @Override // tv.danmaku.ijk.media.widget.controller.OnControllerListener
                public void onVoiceBtnClick(boolean z2) {
                    if (JDPlayerView.this.controllerCallback == null) {
                        return;
                    }
                    JDPlayerView.this.controllerCallback.onVoiceBtnClick(z2);
                }

                @Override // tv.danmaku.ijk.media.widget.controller.OnControllerListener
                public void onVoiceStateChange(boolean z2) {
                    if (JDPlayerView.this.controllerCallback == null) {
                        return;
                    }
                    JDPlayerView.this.controllerCallback.onVoiceStateChange(z2);
                }

                @Override // tv.danmaku.ijk.media.widget.controller.OnControllerListener
                public void seekBarOnSeek(int i2) {
                    if (JDPlayerView.this.controllerCallback == null) {
                        return;
                    }
                    JDPlayerView.this.controllerCallback.seekBarOnSeek(i2);
                }
            });
            this.videoView.setMediaController(this.mediaController);
            this.mediaController.updateOrientationSensor(this.enableSensor);
        }
    }

    private void changeFullScreen(JDControllerOptions.FullMode fullMode) {
        int i2 = AnonymousClass7.$SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$FullMode[fullMode.ordinal()];
        if (i2 == 1) {
            doFull(false);
        } else if (i2 == 2) {
            doFull(true);
        } else if (i2 != 3) {
        } else {
            doFull(this.isLandVideo);
        }
    }

    private void createController() {
        JDControllerOptions.UIMode uIMode = JDControllerOptions.UIMode.UI_DEFAULT;
        JDControllerOptions jDControllerOptions = this.controllerOptions;
        if (jDControllerOptions != null) {
            uIMode = jDControllerOptions.uiMode;
        }
        if (AnonymousClass7.$SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$UIMode[uIMode.ordinal()] != 1) {
            this.mediaController = new JDPlayerController(this.mContext);
        } else {
            this.mediaController = new JDItemSmallController(this.mContext);
        }
        this.mediaController.updateCommonUI(this.ivPlay, this.progressBar);
    }

    private void doFull(boolean z) {
        if (this.isFullScreen) {
            return;
        }
        IMediaControllerExt iMediaControllerExt = this.mediaController;
        if (iMediaControllerExt != null) {
            iMediaControllerExt.changeOrientation(z);
        }
        if (this.playerRadius <= 0.0f || Build.VERSION.SDK_INT < 21) {
            return;
        }
        setOutlineProvider(null);
        setClipToOutline(false);
    }

    private void initListener() {
        if (this.videoView == null) {
            return;
        }
        this.ivPlay.setOnClickListener(new View.OnClickListener() { // from class: tv.danmaku.ijk.media.widget.JDPlayerView.2
            {
                JDPlayerView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDPlayerView.this.videoView.getPlayState() == 5) {
                    JDPlayerView.this.ivPlay.setVisibility(8);
                    JDPlayerView.this.videoView.start();
                    return;
                }
                JDPlayerView jDPlayerView = JDPlayerView.this;
                jDPlayerView.setVideoPath(jDPlayerView.mPlayPath, PlayMode.AUTO_PLAY);
            }
        });
        this.videoView.setOnVideoSizeChangedListener(new IPlayerControl.OnVideoSizeChangedListener() { // from class: tv.danmaku.ijk.media.widget.JDPlayerView.3
            {
                JDPlayerView.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnVideoSizeChangedListener
            public void onVideoSizeChanged(int i2, int i3) {
                JDPlayerView.this.isLandVideo = i2 > i3;
                if (JDPlayerView.this.mediaController != null) {
                    JDPlayerView.this.mediaController.updateFullMode(JDPlayerView.this.isLandVideo);
                }
            }
        });
        this.videoView.setOnPlayerStateListener(this.onPlayerStateListener);
        IMediaPlayer.OnPlayerEventListener onPlayerEventListener = this.onPlayerEventListener;
        if (onPlayerEventListener != null) {
            addPlayerEventCallback(onPlayerEventListener);
        }
    }

    private void initView(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.JDPlayerView);
            this.playerRadius = obtainStyledAttributes.getDimension(R.styleable.JDPlayerView_player_radius, 0.0f);
            obtainStyledAttributes.recycle();
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.ijkandvrplayer_view_player, this);
        this.videoView = (IJDVideoPlayer) inflate.findViewById(R.id.videoView);
        this.rlContainer = (FrameLayout) inflate.findViewById(R.id.rlContainer);
        this.imgCover = (SimpleDraweeView) inflate.findViewById(R.id.imgCover);
        this.ivPlay = (ImageView) inflate.findViewById(R.id.ivPlay);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        setClickable(true);
        initListener();
        applyRoundRadius();
        PlayerNetworkUtil.registerNetworkCallback(this.netChangeListener);
    }

    public void updateScreenOnState(boolean z) {
        Context context = this.mContext;
        if (context == null || !(context instanceof Activity) || ((Activity) context).getWindow() == null) {
            return;
        }
        if (z) {
            ((Activity) this.mContext).getWindow().addFlags(128);
        } else {
            ((Activity) this.mContext).getWindow().clearFlags(128);
        }
    }

    public void addPlayerControllerCallback(PlayerControllerCallback playerControllerCallback) {
        this.controllerCallback = playerControllerCallback;
    }

    public void addPlayerEventCallback(IMediaPlayer.OnPlayerEventListener onPlayerEventListener) {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null) {
            this.onPlayerEventListener = onPlayerEventListener;
        } else {
            iJDVideoPlayer.setOnPlayerEventListener(onPlayerEventListener);
        }
    }

    public void attachVideoView(View view) {
        if (view instanceof JDVideoView) {
            this.videoView = (JDVideoView) view;
            addView(view, 1, new ViewGroup.LayoutParams(-1, -1));
            IMediaControllerExt iMediaControllerExt = this.mediaController;
            if (iMediaControllerExt == null) {
                return;
            }
            this.videoView.setMediaController(iMediaControllerExt);
            JDControllerOptions jDControllerOptions = this.controllerOptions;
            jDControllerOptions.manualControlVisible = false;
            this.mediaController.setControllerOptions(this, jDControllerOptions);
            this.mediaController.show();
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        return iJDVideoPlayer != null && iJDVideoPlayer.canPause();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        return iJDVideoPlayer != null && iJDVideoPlayer.canSeekBackward();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        return iJDVideoPlayer != null && iJDVideoPlayer.canSeekForward();
    }

    public void changeStartIconVisible(int i2) {
        ImageView imageView = this.ivPlay;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility(i2);
        this.outControlVisible = i2;
        this.outControlPlayIcon = true;
    }

    public void doClickPlay() {
        if (TextUtils.isEmpty(this.mPlayPath)) {
            return;
        }
        setVideoPath(this.mPlayPath, PlayMode.AUTO_PLAY);
    }

    public void enableFullBtn(boolean z) {
        IMediaControllerExt iMediaControllerExt = this.mediaController;
        if (iMediaControllerExt != null) {
            iMediaControllerExt.updateFullBtn(z);
        }
    }

    public void forceChangeControlVisible(int i2) {
        forceChangeControlVisible(i2, false);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null) {
            return 0;
        }
        return iJDVideoPlayer.getBufferPercentage();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null) {
            return 0;
        }
        return iJDVideoPlayer.getCurrentPosition();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null) {
            return 0;
        }
        return iJDVideoPlayer.getDuration();
    }

    public JDVideoView getVideoView(boolean z) {
        if (z) {
            IJDVideoPlayer iJDVideoPlayer = this.videoView;
            if (iJDVideoPlayer != null && iJDVideoPlayer.getParent() != null) {
                ((ViewGroup) this.videoView.getParent()).removeView(this.videoView);
            }
            this.mediaController.forceHide();
            JDControllerOptions jDControllerOptions = this.controllerOptions;
            jDControllerOptions.manualControlVisible = true;
            this.mediaController.setControllerOptions(this, jDControllerOptions);
        }
        IJDVideoPlayer iJDVideoPlayer2 = this.videoView;
        if (iJDVideoPlayer2 == null || !(iJDVideoPlayer2 instanceof JDVideoView)) {
            return null;
        }
        return (JDVideoView) iJDVideoPlayer2;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public boolean isForceLayout() {
        return this.forceLayout;
    }

    public boolean isFullScreen() {
        return this.isFullScreen;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        return iJDVideoPlayer != null && iJDVideoPlayer.isPlaying();
    }

    public boolean onBackPress() {
        if (this.isFullScreen) {
            IMediaControllerExt iMediaControllerExt = this.mediaController;
            if (iMediaControllerExt != null) {
                iMediaControllerExt.changeOrientation(false);
                return true;
            }
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.disIntercept) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        IMediaControllerExt iMediaControllerExt;
        if (motionEvent.getAction() == 0 && (iMediaControllerExt = this.mediaController) != null && this.hasError) {
            iMediaControllerExt.show();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0 && this.forceLayout) {
            requestLayout();
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null || !iJDVideoPlayer.isPlaying()) {
            return;
        }
        this.videoView.pause();
        IMediaControllerExt iMediaControllerExt = this.mediaController;
        if (iMediaControllerExt != null) {
            iMediaControllerExt.togglePanelVisible(true);
        }
    }

    public void release() {
        PlayerNetworkUtil.unregisterNetworkCallback(this.netChangeListener);
        updateScreenOnState(false);
        IMediaControllerExt iMediaControllerExt = this.mediaController;
        if (iMediaControllerExt != null) {
            iMediaControllerExt.release();
            this.mediaController = null;
        }
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer != null) {
            iJDVideoPlayer.releaseInThread(true);
        }
        this.controllerOptions = null;
        this.isFullScreen = false;
        this.isLandVideo = false;
        this.hasError = false;
        this.playerRadius = 0.0f;
    }

    public void replay() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null) {
            return;
        }
        iJDVideoPlayer.retry(false);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        if (this.forceLayout) {
            post(this.measureAndLayout);
        }
    }

    public void requestScreenChange(int i2) {
        boolean z = i2 == 0;
        if (z == this.isFullScreen) {
            return;
        }
        if (!z) {
            IMediaControllerExt iMediaControllerExt = this.mediaController;
            if (iMediaControllerExt != null) {
                iMediaControllerExt.changeOrientation(false);
                return;
            }
            return;
        }
        IMediaControllerExt iMediaControllerExt2 = this.mediaController;
        if (iMediaControllerExt2 != null) {
            iMediaControllerExt2.changeOrientation(true);
            return;
        }
        JDControllerOptions.FullMode fullMode = JDControllerOptions.FullMode.FULL_LAND;
        JDControllerOptions jDControllerOptions = this.controllerOptions;
        if (jDControllerOptions != null) {
            fullMode = jDControllerOptions.fullMode;
        }
        changeFullScreen(fullMode);
    }

    public void requestUnHandleTouchEvent(boolean z) {
        this.disIntercept = z;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i2) {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null) {
            return;
        }
        iJDVideoPlayer.seekTo(i2);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        this.rlContainer.setBackgroundColor(i2);
    }

    public void setForceLayout(boolean z) {
        this.forceLayout = z;
    }

    public void setImgCover(String str) {
        setImgCover(str, -1);
    }

    public void setLoop(boolean z) {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null || iJDVideoPlayer.getIjkMediaPlayer() == null) {
            return;
        }
        this.videoView.getMediaPlayer().setLooping(z);
    }

    public void setPlayerOptions(IPlayerControl.PlayerOptions playerOptions) {
        setPlayerOptions(playerOptions, new JDControllerOptions.Builder().build());
    }

    public void setPlayerStateListener(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
        this.playerStateListener = onPlayerStateListener;
    }

    public void setSpeed(float f2) {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null) {
            return;
        }
        iJDVideoPlayer.setSpeed(f2);
    }

    public void setVideoPath(String str, PlayMode playMode) {
        setVideoPath(str, 0, playMode);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null || iJDVideoPlayer.isPlaying()) {
            return;
        }
        this.videoView.start();
        IMediaControllerExt iMediaControllerExt = this.mediaController;
        if (iMediaControllerExt != null) {
            iMediaControllerExt.togglePanelVisible(false);
        }
    }

    public void stop() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null) {
            return;
        }
        iJDVideoPlayer.suspend();
    }

    public boolean toggleMute(boolean z) {
        IMediaControllerExt iMediaControllerExt = this.mediaController;
        if (iMediaControllerExt != null) {
            return iMediaControllerExt.toggleMute(z);
        }
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null || iJDVideoPlayer.getPlayerOptions() == null) {
            return false;
        }
        this.videoView.setVolume(z ? 0.0f : 1.0f);
        return true;
    }

    public void updateControllerSensor(boolean z) {
        this.enableSensor = z;
        IMediaControllerExt iMediaControllerExt = this.mediaController;
        if (iMediaControllerExt != null) {
            iMediaControllerExt.updateOrientationSensor(z);
        }
    }

    public JDPlayerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void forceChangeControlVisible(int i2, boolean z) {
        IMediaControllerExt iMediaControllerExt = this.mediaController;
        if (iMediaControllerExt == null) {
            return;
        }
        if (i2 == 0) {
            iMediaControllerExt.forceShow();
            if (z) {
                this.mediaController.show();
            }
        } else if (i2 == 4 || i2 == 8) {
            iMediaControllerExt.forceHide();
        }
    }

    public void setImgCover(String str, int i2) {
        if (this.imgCover == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.imgCover.setVisibility(0);
        JDDisplayImageOptions jDDisplayImageOptions = null;
        if (i2 != -1) {
            jDDisplayImageOptions = new JDDisplayImageOptions();
            jDDisplayImageOptions.showImageOnFail(i2);
        }
        JDImageUtils.displayImage(str, this.imgCover, jDDisplayImageOptions);
    }

    public void setPlayerOptions(IPlayerControl.PlayerOptions playerOptions, JDControllerOptions jDControllerOptions) {
        if (playerOptions == null) {
            return;
        }
        this.playerOptions = playerOptions;
        if (playerOptions.isAlpha()) {
            IJDVideoPlayer iJDVideoPlayer = this.videoView;
            if (iJDVideoPlayer != null && iJDVideoPlayer.getParent() != null && (this.videoView.getParent() instanceof ViewGroup)) {
                ViewGroup viewGroup = (ViewGroup) this.videoView.getParent();
                int indexOfChild = viewGroup.indexOfChild(this.videoView);
                viewGroup.removeView(this.videoView);
                this.videoView.removePlayerStateListener(this.onPlayerStateListener);
                AlphaVideoView alphaVideoView = new AlphaVideoView(getContext());
                this.videoView = alphaVideoView;
                viewGroup.addView(alphaVideoView, indexOfChild);
            }
            this.rlContainer.setBackgroundColor(getResources().getColor(17170445));
            initListener();
        }
        boolean z = !playerOptions.getIsLive();
        if (playerOptions.isAlpha()) {
            z = false;
        }
        attachController(z, jDControllerOptions);
        this.videoView.setPlayerOptions(playerOptions);
    }

    public void setVideoPath(String str, int i2, PlayMode playMode) {
        if (this.videoView == null) {
            return;
        }
        if (!TextUtils.isEmpty(this.mPlayPath) && !this.mPlayPath.equals(str)) {
            stop();
        }
        this.mPlayPath = str;
        int i3 = AnonymousClass7.$SwitchMap$tv$danmaku$ijk$media$widget$JDPlayerView$PlayMode[playMode.ordinal()];
        if (i3 == 1) {
            this.ivPlay.setVisibility(8);
            this.videoView.setVideoPath(str);
            if (i2 > 0) {
                this.videoView.seekTo(i2);
            }
        } else if (i3 != 2) {
            if (i3 != 3) {
                return;
            }
            if (PlayerNetworkUtil.isWifiConnected(getContext())) {
                setVideoPath(str, PlayMode.AUTO_PLAY);
            } else {
                setVideoPath(str, PlayMode.CLICK_PLAY);
            }
        } else {
            IMediaControllerExt iMediaControllerExt = this.mediaController;
            if (iMediaControllerExt != null) {
                iMediaControllerExt.hide();
            }
            if (this.outControlPlayIcon) {
                this.ivPlay.setVisibility(this.outControlVisible);
            } else {
                this.ivPlay.setVisibility(0);
            }
        }
    }

    public JDPlayerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isFullScreen = false;
        this.isLandVideo = false;
        this.hasError = false;
        this.isCompleted = false;
        this.outControlPlayIcon = false;
        this.outControlVisible = 0;
        this.disIntercept = false;
        this.enableLoading = true;
        this.forceLayout = false;
        this.enableSensor = false;
        this.netChangeListener = new PlayerNetworkUtil.NetChangeListener() { // from class: tv.danmaku.ijk.media.widget.JDPlayerView.1
            {
                JDPlayerView.this = this;
            }

            @Override // tv.danmaku.ijk.media.utils.PlayerNetworkUtil.NetChangeListener
            public void onNetworkChange(boolean z, boolean z2) {
                if (!z || JDPlayerView.this.controllerCallback == null) {
                    return;
                }
                JDPlayerView.this.controllerCallback.onNetChanged();
            }
        };
        this.measureAndLayout = new Runnable() { // from class: tv.danmaku.ijk.media.widget.JDPlayerView.5
            {
                JDPlayerView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                JDPlayerView jDPlayerView = JDPlayerView.this;
                jDPlayerView.measure(View.MeasureSpec.makeMeasureSpec(jDPlayerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(JDPlayerView.this.getHeight(), 1073741824));
                JDPlayerView jDPlayerView2 = JDPlayerView.this;
                jDPlayerView2.layout(jDPlayerView2.getLeft(), JDPlayerView.this.getTop(), JDPlayerView.this.getRight(), JDPlayerView.this.getBottom());
            }
        };
        this.onPlayerStateListener = new IPlayerControl.OnPlayerStateListener() { // from class: tv.danmaku.ijk.media.widget.JDPlayerView.6
            private boolean loopCompleted;

            {
                JDPlayerView.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                JDPlayerView.this.hasError = false;
                JDPlayerView.this.isCompleted = true;
                JDPlayerView.this.progressBar.setVisibility(8);
                if (JDPlayerView.this.controllerOptions != null && JDPlayerView.this.playerOptions != null && JDPlayerView.this.controllerOptions.uiMode == JDControllerOptions.UIMode.UI_ITEM_SMALL && !JDPlayerView.this.playerOptions.isLoop() && JDPlayerView.this.outControlPlayIcon) {
                    JDPlayerView jDPlayerView = JDPlayerView.this;
                    jDPlayerView.changeStartIconVisible(jDPlayerView.outControlVisible);
                }
                if (JDPlayerView.this.playerStateListener != null) {
                    JDPlayerView.this.playerStateListener.onCompletion();
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
                if (JDPlayerView.this.enableLoading) {
                    JDPlayerView.this.progressBar.setVisibility(0);
                }
                if (JDPlayerView.this.playerStateListener != null) {
                    JDPlayerView.this.playerStateListener.onCreatePlayer();
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i3, int i4) {
                JDPlayerView.this.progressBar.setVisibility(8);
                if (JDPlayerView.this.mediaController != null) {
                    JDPlayerView.this.mediaController.hide();
                }
                JDPlayerView.this.hasError = true;
                if (JDPlayerView.this.playerStateListener != null) {
                    JDPlayerView.this.playerStateListener.onError(i3, i4);
                    return false;
                }
                return false;
            }

            /* JADX WARN: Code restructure failed: missing block: B:40:0x001f, code lost:
                if (r3 != 702) goto L55;
             */
            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public boolean onInfo(int r3, int r4) {
                /*
                    r2 = this;
                    tv.danmaku.ijk.media.widget.JDPlayerView r0 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    tv.danmaku.ijk.media.example.widget.media.IPlayerControl$OnPlayerStateListener r0 = tv.danmaku.ijk.media.widget.JDPlayerView.access$900(r0)
                    if (r0 == 0) goto L11
                    tv.danmaku.ijk.media.widget.JDPlayerView r0 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    tv.danmaku.ijk.media.example.widget.media.IPlayerControl$OnPlayerStateListener r0 = tv.danmaku.ijk.media.widget.JDPlayerView.access$900(r0)
                    r0.onInfo(r3, r4)
                L11:
                    r4 = 3
                    r0 = 0
                    if (r3 == r4) goto L3e
                    r1 = 30003(0x7533, float:4.2043E-41)
                    if (r3 == r1) goto L3a
                    r1 = 701(0x2bd, float:9.82E-43)
                    if (r3 == r1) goto L22
                    r1 = 702(0x2be, float:9.84E-43)
                    if (r3 == r1) goto L3e
                    goto L66
                L22:
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    boolean r3 = tv.danmaku.ijk.media.widget.JDPlayerView.access$700(r3)
                    if (r3 == 0) goto L37
                    boolean r3 = r2.loopCompleted
                    if (r3 != 0) goto L37
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    android.widget.ProgressBar r3 = tv.danmaku.ijk.media.widget.JDPlayerView.access$800(r3)
                    r3.setVisibility(r0)
                L37:
                    r2.loopCompleted = r0
                    goto L66
                L3a:
                    r3 = 1
                    r2.loopCompleted = r3
                    goto L66
                L3e:
                    r1 = 8
                    if (r3 != r4) goto L53
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    com.facebook.drawee.view.SimpleDraweeView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.access$1700(r3)
                    if (r3 == 0) goto L53
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    com.facebook.drawee.view.SimpleDraweeView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.access$1700(r3)
                    r3.setVisibility(r1)
                L53:
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    tv.danmaku.ijk.media.widget.JDPlayerView.access$1002(r3, r0)
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    tv.danmaku.ijk.media.widget.JDPlayerView.access$1102(r3, r0)
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    android.widget.ProgressBar r3 = tv.danmaku.ijk.media.widget.JDPlayerView.access$800(r3)
                    r3.setVisibility(r1)
                L66:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.widget.JDPlayerView.AnonymousClass6.onInfo(int, int):boolean");
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                JDPlayerView.this.hasError = false;
                JDPlayerView.this.isCompleted = false;
                if (JDPlayerView.this.ivPlay != null) {
                    JDPlayerView.this.ivPlay.setVisibility(8);
                }
                JDPlayerView.this.progressBar.setVisibility(8);
                if (JDPlayerView.this.mediaController != null) {
                    JDPlayerView.this.mediaController.showNonWifiTip();
                }
                if (JDPlayerView.this.playerStateListener != null) {
                    JDPlayerView.this.playerStateListener.onPrepared(j2);
                }
                JDPlayerView.this.updateScreenOnState(true);
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
                if (JDPlayerView.this.playerStateListener == null) {
                    return;
                }
                JDPlayerView.this.playerStateListener.onSeekComplete();
            }
        };
        initView(context, attributeSet);
    }

    @TargetApi(21)
    public JDPlayerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.isFullScreen = false;
        this.isLandVideo = false;
        this.hasError = false;
        this.isCompleted = false;
        this.outControlPlayIcon = false;
        this.outControlVisible = 0;
        this.disIntercept = false;
        this.enableLoading = true;
        this.forceLayout = false;
        this.enableSensor = false;
        this.netChangeListener = new PlayerNetworkUtil.NetChangeListener() { // from class: tv.danmaku.ijk.media.widget.JDPlayerView.1
            {
                JDPlayerView.this = this;
            }

            @Override // tv.danmaku.ijk.media.utils.PlayerNetworkUtil.NetChangeListener
            public void onNetworkChange(boolean z, boolean z2) {
                if (!z || JDPlayerView.this.controllerCallback == null) {
                    return;
                }
                JDPlayerView.this.controllerCallback.onNetChanged();
            }
        };
        this.measureAndLayout = new Runnable() { // from class: tv.danmaku.ijk.media.widget.JDPlayerView.5
            {
                JDPlayerView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                JDPlayerView jDPlayerView = JDPlayerView.this;
                jDPlayerView.measure(View.MeasureSpec.makeMeasureSpec(jDPlayerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(JDPlayerView.this.getHeight(), 1073741824));
                JDPlayerView jDPlayerView2 = JDPlayerView.this;
                jDPlayerView2.layout(jDPlayerView2.getLeft(), JDPlayerView.this.getTop(), JDPlayerView.this.getRight(), JDPlayerView.this.getBottom());
            }
        };
        this.onPlayerStateListener = new IPlayerControl.OnPlayerStateListener() { // from class: tv.danmaku.ijk.media.widget.JDPlayerView.6
            private boolean loopCompleted;

            {
                JDPlayerView.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                JDPlayerView.this.hasError = false;
                JDPlayerView.this.isCompleted = true;
                JDPlayerView.this.progressBar.setVisibility(8);
                if (JDPlayerView.this.controllerOptions != null && JDPlayerView.this.playerOptions != null && JDPlayerView.this.controllerOptions.uiMode == JDControllerOptions.UIMode.UI_ITEM_SMALL && !JDPlayerView.this.playerOptions.isLoop() && JDPlayerView.this.outControlPlayIcon) {
                    JDPlayerView jDPlayerView = JDPlayerView.this;
                    jDPlayerView.changeStartIconVisible(jDPlayerView.outControlVisible);
                }
                if (JDPlayerView.this.playerStateListener != null) {
                    JDPlayerView.this.playerStateListener.onCompletion();
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
                if (JDPlayerView.this.enableLoading) {
                    JDPlayerView.this.progressBar.setVisibility(0);
                }
                if (JDPlayerView.this.playerStateListener != null) {
                    JDPlayerView.this.playerStateListener.onCreatePlayer();
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i32, int i4) {
                JDPlayerView.this.progressBar.setVisibility(8);
                if (JDPlayerView.this.mediaController != null) {
                    JDPlayerView.this.mediaController.hide();
                }
                JDPlayerView.this.hasError = true;
                if (JDPlayerView.this.playerStateListener != null) {
                    JDPlayerView.this.playerStateListener.onError(i32, i4);
                    return false;
                }
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public boolean onInfo(int r3, int r4) {
                /*
                    r2 = this;
                    tv.danmaku.ijk.media.widget.JDPlayerView r0 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    tv.danmaku.ijk.media.example.widget.media.IPlayerControl$OnPlayerStateListener r0 = tv.danmaku.ijk.media.widget.JDPlayerView.access$900(r0)
                    if (r0 == 0) goto L11
                    tv.danmaku.ijk.media.widget.JDPlayerView r0 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    tv.danmaku.ijk.media.example.widget.media.IPlayerControl$OnPlayerStateListener r0 = tv.danmaku.ijk.media.widget.JDPlayerView.access$900(r0)
                    r0.onInfo(r3, r4)
                L11:
                    r4 = 3
                    r0 = 0
                    if (r3 == r4) goto L3e
                    r1 = 30003(0x7533, float:4.2043E-41)
                    if (r3 == r1) goto L3a
                    r1 = 701(0x2bd, float:9.82E-43)
                    if (r3 == r1) goto L22
                    r1 = 702(0x2be, float:9.84E-43)
                    if (r3 == r1) goto L3e
                    goto L66
                L22:
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    boolean r3 = tv.danmaku.ijk.media.widget.JDPlayerView.access$700(r3)
                    if (r3 == 0) goto L37
                    boolean r3 = r2.loopCompleted
                    if (r3 != 0) goto L37
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    android.widget.ProgressBar r3 = tv.danmaku.ijk.media.widget.JDPlayerView.access$800(r3)
                    r3.setVisibility(r0)
                L37:
                    r2.loopCompleted = r0
                    goto L66
                L3a:
                    r3 = 1
                    r2.loopCompleted = r3
                    goto L66
                L3e:
                    r1 = 8
                    if (r3 != r4) goto L53
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    com.facebook.drawee.view.SimpleDraweeView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.access$1700(r3)
                    if (r3 == 0) goto L53
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    com.facebook.drawee.view.SimpleDraweeView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.access$1700(r3)
                    r3.setVisibility(r1)
                L53:
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    tv.danmaku.ijk.media.widget.JDPlayerView.access$1002(r3, r0)
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    tv.danmaku.ijk.media.widget.JDPlayerView.access$1102(r3, r0)
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = tv.danmaku.ijk.media.widget.JDPlayerView.this
                    android.widget.ProgressBar r3 = tv.danmaku.ijk.media.widget.JDPlayerView.access$800(r3)
                    r3.setVisibility(r1)
                L66:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.widget.JDPlayerView.AnonymousClass6.onInfo(int, int):boolean");
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                JDPlayerView.this.hasError = false;
                JDPlayerView.this.isCompleted = false;
                if (JDPlayerView.this.ivPlay != null) {
                    JDPlayerView.this.ivPlay.setVisibility(8);
                }
                JDPlayerView.this.progressBar.setVisibility(8);
                if (JDPlayerView.this.mediaController != null) {
                    JDPlayerView.this.mediaController.showNonWifiTip();
                }
                if (JDPlayerView.this.playerStateListener != null) {
                    JDPlayerView.this.playerStateListener.onPrepared(j2);
                }
                JDPlayerView.this.updateScreenOnState(true);
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
                if (JDPlayerView.this.playerStateListener == null) {
                    return;
                }
                JDPlayerView.this.playerStateListener.onSeekComplete();
            }
        };
        initView(context, attributeSet);
    }
}
