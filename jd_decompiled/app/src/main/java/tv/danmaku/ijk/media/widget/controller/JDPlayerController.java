package tv.danmaku.ijk.media.widget.controller;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import tv.danmaku.ijk.media.IMediaControllerExt;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.utils.PlayerNetworkUtil;
import tv.danmaku.ijk.media.utils.PlayerStringUtils;
import tv.danmaku.ijk.media.utils.PlayerSystemUtil;
import tv.danmaku.ijk.media.widget.JDPlayerView;
import tv.danmaku.ijk.media.widget.custom.SpeedControlButton;
import tv.danmaku.ijk.media.widget.custom.TipsView;
import tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger;

/* loaded from: classes11.dex */
public class JDPlayerController extends FrameLayout implements IMediaControllerExt, View.OnClickListener {
    private static final int DEFAULT_TIMEOUT = 3000;
    private static final int MSG_FADE_OUT = 2;
    private static final int MSG_UPDATE_SEEK_BAR = 1;
    private JDControllerOptions controllerOptions;
    private boolean forceHide;
    private boolean isComplete;
    private boolean isFullMode;
    private ImageView ivBack;
    private ImageView ivCenterBtn;
    private ImageView ivFullScreen;
    private ImageView ivMute;
    private ImageView ivPauseAndStartFull;
    private ImageView ivReplay;
    private int mBufferPercent;
    private Context mContext;
    private long mDuration;
    private FullPlayerChanger mFullPlayerChanger;
    private EventHandler mHandler;
    private OnControllerListener mOnControllerListener;
    private final IMediaPlayer.OnPlayerEventListener mOnPlayerEventListener;
    private MediaController.MediaPlayerControl mPlayer;
    private long mProgress;
    private int mProgressInterval;
    private final TipsView.OnTipsInvoke mRetryInvoke;
    private TipsView mTipsView;
    private boolean manualControlVisible;
    private boolean playError;
    private JDVideoView realPlayer;
    private RelativeLayout rlBottom;
    private SeekBar seekBar;
    private TextView tvCurPos;
    private SpeedControlButton tvSpeed;
    private TextView tvTotal;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class EventHandler extends Handler {
        private final WeakReference<JDPlayerController> mController;

        public EventHandler(JDPlayerController jDPlayerController) {
            this.mController = new WeakReference<>(jDPlayerController);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            JDPlayerController jDPlayerController = this.mController.get();
            super.handleMessage(message);
            if (jDPlayerController == null) {
                return;
            }
            int i2 = message.what;
            if (i2 == 1) {
                jDPlayerController.setVideoProgress();
            } else if (i2 != 2) {
            } else {
                jDPlayerController.hide();
            }
        }
    }

    public JDPlayerController(@NonNull Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeFullModeUI(boolean z) {
        this.isFullMode = z;
        boolean z2 = getVisibility() == 0;
        inflateLayout(z);
        if (z2 && this.manualControlVisible) {
            forceShow();
        } else {
            show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeLoadingState(boolean z) {
        ImageView imageView = this.ivCenterBtn;
        if (imageView != null) {
            if (this.isFullMode) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(z ? 8 : 0);
            }
        }
        ImageView imageView2 = this.ivReplay;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
    }

    private void inflateLayout(boolean z) {
        removeAllViews();
        LayoutInflater.from(this.mContext).inflate(getLayout(z), this);
        this.ivCenterBtn = (ImageView) findViewById(R.id.ivCenterBtn);
        this.ivMute = (ImageView) findViewById(R.id.ivMute);
        this.tvSpeed = (SpeedControlButton) findViewById(R.id.tvSpeed);
        this.rlBottom = (RelativeLayout) findViewById(R.id.rlBottom);
        this.tvCurPos = (TextView) findViewById(R.id.tvCurPos);
        this.tvTotal = (TextView) findViewById(R.id.tvTotal);
        this.ivReplay = (ImageView) findViewById(R.id.ivReplay);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        this.seekBar = seekBar;
        seekBar.setMax(100);
        this.tvSpeed.setFullMode(z);
        if (z) {
            this.ivPauseAndStartFull = (ImageView) findViewById(R.id.ivPauseAndStartFull);
            this.ivBack = (ImageView) findViewById(R.id.ivBack);
        } else {
            this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        }
        initListener();
        if (this.controllerOptions != null) {
            updateOptionUI();
        }
        updateMuteUI();
        setVisibility(8);
    }

    private void initFullPlayerChanger(JDPlayerView jDPlayerView) {
        Activity activity;
        JDControllerOptions jDControllerOptions = this.controllerOptions;
        if (jDControllerOptions == null || (activity = jDControllerOptions.activity) == null || jDPlayerView == null) {
            return;
        }
        FullPlayerChanger fullPlayerChanger = new FullPlayerChanger(activity, jDPlayerView, jDControllerOptions.fullMode);
        this.mFullPlayerChanger = fullPlayerChanger;
        fullPlayerChanger.setScreenChangeListener(new FullPlayerChanger.ScreenChangeListener() { // from class: tv.danmaku.ijk.media.widget.controller.JDPlayerController.3
            @Override // tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger.ScreenChangeListener
            public void onChange(boolean z, int i2) {
                JDPlayerController.this.changeFullModeUI(z);
                if (JDPlayerController.this.mOnControllerListener == null) {
                    return;
                }
                JDPlayerController.this.mOnControllerListener.onOrientationChanged(z, i2);
            }
        });
    }

    private void initListener() {
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: tv.danmaku.ijk.media.widget.controller.JDPlayerController.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                if (!z || JDPlayerController.this.mOnControllerListener == null || JDPlayerController.this.mPlayer == null) {
                    return;
                }
                JDPlayerController.this.mOnControllerListener.onProgressUpdate(JDPlayerController.this.mPlayer.isPlaying(), i2, JDPlayerController.this.mPlayer.getCurrentPosition(), true);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (JDPlayerController.this.getContext() == null || !PlayerNetworkUtil.isNetworkAvailable(JDPlayerController.this.getContext())) {
                    JDPlayerController.this.updateProgress();
                    return;
                }
                int progress = seekBar.getProgress();
                int max = seekBar.getMax();
                if (progress < 0 || progress > max) {
                    return;
                }
                int i2 = (int) (((float) JDPlayerController.this.mDuration) * (progress / max));
                if (JDPlayerController.this.mPlayer != null) {
                    JDPlayerController.this.mPlayer.seekTo(i2);
                }
                if (JDPlayerController.this.mOnControllerListener != null) {
                    JDPlayerController.this.mOnControllerListener.seekBarOnSeek(i2);
                }
            }
        });
        this.ivMute.setOnClickListener(this);
        this.ivCenterBtn.setOnClickListener(this);
        this.ivReplay.setOnClickListener(this);
        ImageView imageView = this.ivFullScreen;
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
        ImageView imageView2 = this.ivBack;
        if (imageView2 != null) {
            imageView2.setOnClickListener(this);
        }
        ImageView imageView3 = this.ivPauseAndStartFull;
        if (imageView3 != null) {
            imageView3.setOnClickListener(this);
        }
        this.tvSpeed.setSpeedCallback(this, new SpeedControlButton.SpeedButtonCallback() { // from class: tv.danmaku.ijk.media.widget.controller.JDPlayerController.2
            @Override // tv.danmaku.ijk.media.widget.custom.SpeedControlButton.SpeedButtonCallback
            public void onSpeedSelect(float f2) {
                if (JDPlayerController.this.realPlayer != null) {
                    JDPlayerController.this.realPlayer.setSpeed(f2);
                }
                JDPlayerController.this.show();
            }
        });
    }

    private void initView(Context context) {
        this.mContext = context;
        this.mHandler = new EventHandler(this);
        inflateLayout(this.isFullMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideoProgress() {
        MediaController.MediaPlayerControl mediaPlayerControl = this.mPlayer;
        if (mediaPlayerControl == null || mediaPlayerControl.getDuration() == -1) {
            return;
        }
        this.mProgress = this.mPlayer.getCurrentPosition();
        this.mBufferPercent = this.mPlayer.getBufferPercentage();
        long duration = this.mPlayer.getDuration();
        this.mDuration = duration;
        if (duration - this.mProgress < 800) {
            this.mProgress = duration;
        }
        updateProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorLayout() {
        JDControllerOptions jDControllerOptions = this.controllerOptions;
        if ((jDControllerOptions == null || jDControllerOptions.enableErrorTip) && getContext() != null) {
            this.mTipsView = new TipsView(getContext());
            if (!PlayerNetworkUtil.isNetworkAvailable(getContext())) {
                this.mTipsView.showTip(TipsView.TipType.NET_ERROR, this.realPlayer, this.mRetryInvoke);
            } else {
                this.mTipsView.showTip(TipsView.TipType.PLAY_ERROR, this.realPlayer, this.mRetryInvoke);
            }
        }
    }

    private void togglePlayState() {
        MediaController.MediaPlayerControl mediaPlayerControl = this.mPlayer;
        if (mediaPlayerControl == null) {
            return;
        }
        if (mediaPlayerControl.isPlaying()) {
            this.mPlayer.pause();
            togglePanelVisible(true);
            return;
        }
        this.mPlayer.start();
        togglePanelVisible(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCenterBtn(boolean z) {
        ImageView imageView = this.ivCenterBtn;
        if (imageView == null || this.mPlayer == null || this.forceHide) {
            return;
        }
        if (this.isFullMode) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
        }
        if (z) {
            this.ivCenterBtn.setImageResource(R.drawable.ijkandvrplayer_btn_pause);
            ImageView imageView2 = this.ivPauseAndStartFull;
            if (imageView2 != null) {
                imageView2.setImageResource(R.drawable.ijkandvrplayer_btn_pause_full);
            }
        } else {
            this.ivCenterBtn.setImageResource(R.drawable.ijkandvrplayer_btn_play_def);
            ImageView imageView3 = this.ivPauseAndStartFull;
            if (imageView3 != null) {
                imageView3.setImageResource(R.drawable.ijkandvrplayer_btn_start_full);
            }
        }
        if (this.isComplete) {
            this.ivCenterBtn.setVisibility(8);
            JDControllerOptions jDControllerOptions = this.controllerOptions;
            if (jDControllerOptions != null && jDControllerOptions.enableReplay) {
                this.ivReplay.setVisibility(0);
            }
            this.tvSpeed.dismiss();
        }
    }

    private void updateMuteUI() {
        ImageView imageView;
        JDVideoView jDVideoView = this.realPlayer;
        if (jDVideoView == null || (imageView = this.ivMute) == null) {
            return;
        }
        imageView.setImageResource(jDVideoView.getVolume() == 0.0f ? R.drawable.video_player_voice_off : R.drawable.video_player_voice_on);
    }

    private void updateOptionUI() {
        ImageView imageView;
        ImageView imageView2;
        JDControllerOptions jDControllerOptions = this.controllerOptions;
        if (jDControllerOptions == null) {
            return;
        }
        boolean z = jDControllerOptions.enableFullSwitch;
        if (!z && (imageView2 = this.ivFullScreen) != null) {
            imageView2.setVisibility(8);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.tvTotal.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.rightMargin = PlayerSystemUtil.dip2px(getContext(), 13.0f);
            this.tvTotal.setLayoutParams(layoutParams);
        } else if (z && (imageView = this.ivFullScreen) != null) {
            imageView.setVisibility(0);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.tvTotal.getLayoutParams();
            layoutParams2.addRule(11, 0);
            layoutParams2.rightMargin = 0;
            this.tvTotal.setLayoutParams(layoutParams2);
        }
        this.ivMute.setVisibility(this.controllerOptions.enableMuteSwitch ? 0 : 8);
        this.tvSpeed.setVisibility(this.controllerOptions.enableSpeedSwitch ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgress() {
        int i2;
        MediaController.MediaPlayerControl mediaPlayerControl;
        long j2 = this.mDuration;
        float f2 = j2 > 0 ? ((float) this.mProgress) / ((float) j2) : 1.0f;
        if (this.mProgress == 0) {
            f2 = 0.0f;
        }
        if (f2 < 0.0f || f2 > 1.0f) {
            i2 = 0;
        } else {
            int round = Math.round(f2 * this.seekBar.getMax());
            this.seekBar.setProgress(round);
            this.seekBar.setSecondaryProgress(this.mBufferPercent);
            i2 = round;
        }
        this.tvCurPos.setText(PlayerStringUtils.millisToString(this.mProgress));
        this.tvTotal.setText(PlayerStringUtils.millisToString(this.mDuration));
        if (this.isComplete) {
            return;
        }
        OnControllerListener onControllerListener = this.mOnControllerListener;
        if (onControllerListener != null && (mediaPlayerControl = this.mPlayer) != null) {
            onControllerListener.onProgressUpdate(mediaPlayerControl.isPlaying(), i2, this.mProgress, false);
        }
        EventHandler eventHandler = this.mHandler;
        if (eventHandler != null) {
            eventHandler.removeMessages(1);
            EventHandler eventHandler2 = this.mHandler;
            eventHandler2.sendMessageDelayed(eventHandler2.obtainMessage(1), this.mProgressInterval);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUIHandlerMsg() {
        MediaController.MediaPlayerControl mediaPlayerControl = this.mPlayer;
        if (mediaPlayerControl != null) {
            updateCenterBtn(mediaPlayerControl.isPlaying());
        }
        EventHandler eventHandler = this.mHandler;
        if (eventHandler != null) {
            eventHandler.removeMessages(1);
            this.mHandler.sendEmptyMessage(1);
            if (this.manualControlVisible) {
                return;
            }
            this.mHandler.removeMessages(2);
            EventHandler eventHandler2 = this.mHandler;
            eventHandler2.sendMessageDelayed(eventHandler2.obtainMessage(2), 3000L);
        }
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void changeOrientation(boolean z) {
        FullPlayerChanger fullPlayerChanger = this.mFullPlayerChanger;
        if (fullPlayerChanger == null) {
            return;
        }
        if (z) {
            fullPlayerChanger.changeToFullScreen();
        } else {
            fullPlayerChanger.changeToHalfScreen();
        }
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void forceHide() {
        this.forceHide = true;
        setVisibility(8);
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void forceShow() {
        this.forceHide = false;
        if (!isShowing()) {
            ImageView imageView = this.ivCenterBtn;
            if (imageView != null) {
                imageView.requestFocus();
            }
            if (this.playError) {
                if (this.isFullMode) {
                    setVisibility(0);
                    this.rlBottom.setVisibility(8);
                    return;
                }
                setVisibility(8);
                return;
            }
            setVisibility(0);
            this.rlBottom.setVisibility(0);
        }
        MediaController.MediaPlayerControl mediaPlayerControl = this.mPlayer;
        if (mediaPlayerControl != null) {
            updateCenterBtn(mediaPlayerControl.isPlaying());
        }
        EventHandler eventHandler = this.mHandler;
        if (eventHandler == null) {
            return;
        }
        eventHandler.removeMessages(1);
        this.mHandler.sendEmptyMessage(1);
        this.mHandler.removeMessages(2);
    }

    protected int getLayout(boolean z) {
        return z ? R.layout.ijkandvrplayer_view_player_controller_full : R.layout.ijkandvrplayer_view_player_controller;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IMediaController
    public void hide() {
        if (this.manualControlVisible) {
            return;
        }
        if ((this.isComplete || !isShowing()) && !this.playError) {
            return;
        }
        setVisibility(8);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IMediaController
    public boolean isShowing() {
        return getVisibility() == 0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MediaController.MediaPlayerControl mediaPlayerControl;
        if (view.getId() != R.id.ivCenterBtn && view.getId() != R.id.ivPauseAndStartFull) {
            if (view.getId() == R.id.ivMute) {
                boolean z = this.realPlayer.getVolume() != 0.0f;
                toggleMute(z);
                OnControllerListener onControllerListener = this.mOnControllerListener;
                if (onControllerListener != null) {
                    onControllerListener.onVoiceBtnClick(!z);
                    return;
                }
                return;
            } else if (view.getId() == R.id.ivFullScreen) {
                changeOrientation(true);
                return;
            } else if (view.getId() == R.id.ivBack) {
                changeOrientation(false);
                return;
            } else if (view.getId() == R.id.ivReplay) {
                replay();
                return;
            } else {
                return;
            }
        }
        togglePlayState();
        OnControllerListener onControllerListener2 = this.mOnControllerListener;
        if (onControllerListener2 == null || (mediaPlayerControl = this.mPlayer) == null) {
            return;
        }
        onControllerListener2.onPlayBtnClick(mediaPlayerControl.isPlaying());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventHandler eventHandler = this.mHandler;
        if (eventHandler != null) {
            eventHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void release() {
        if (getParent() != null && (getParent() instanceof ViewGroup)) {
            ((ViewGroup) getParent()).removeView(this);
        }
        SpeedControlButton speedControlButton = this.tvSpeed;
        if (speedControlButton != null) {
            speedControlButton.release();
        }
        this.isComplete = false;
        this.playError = false;
    }

    public void replay() {
        this.isComplete = false;
        togglePlayState();
        this.ivReplay.setVisibility(8);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IMediaController
    public void setAnchorView(View view) {
        if (getParent() != null && (getParent() instanceof ViewGroup)) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).addView(this, new FrameLayout.LayoutParams(-1, -1));
        }
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void setControllerOptions(JDPlayerView jDPlayerView, JDControllerOptions jDControllerOptions) {
        this.controllerOptions = jDControllerOptions;
        this.mProgressInterval = jDControllerOptions.progressInterval;
        this.manualControlVisible = jDControllerOptions.manualControlVisible;
        this.tvSpeed.setSpeedList(jDControllerOptions.defSpeedIndex, jDControllerOptions.speedList);
        initFullPlayerChanger(jDPlayerView);
        updateOptionUI();
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IMediaController
    public void setMediaPlayer(MediaController.MediaPlayerControl mediaPlayerControl) {
        if (mediaPlayerControl == null) {
            return;
        }
        this.mPlayer = mediaPlayerControl;
        if (mediaPlayerControl instanceof JDVideoView) {
            JDVideoView jDVideoView = (JDVideoView) mediaPlayerControl;
            this.realPlayer = jDVideoView;
            jDVideoView.setOnPlayerEventListener(this.mOnPlayerEventListener);
            updateMuteUI();
        }
        if (this.mPlayer.isPlaying()) {
            return;
        }
        changeLoadingState(true);
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void setOnControllerListener(OnControllerListener onControllerListener) {
        this.mOnControllerListener = onControllerListener;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IMediaController
    public void show(int i2) {
        if (this.manualControlVisible || this.forceHide) {
            return;
        }
        forceShow();
        if (i2 > 0) {
            EventHandler eventHandler = this.mHandler;
            eventHandler.sendMessageDelayed(eventHandler.obtainMessage(2), i2);
        }
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void showNonWifiTip() {
        JDControllerOptions jDControllerOptions;
        if (getContext() == null || JDPlayerSdk.dataFlowTipShow || this.isComplete || PlayerNetworkUtil.isWifiConnected(getContext()) || (jDControllerOptions = this.controllerOptions) == null || !jDControllerOptions.enableNonWifiTip) {
            return;
        }
        TipsView tipsView = new TipsView(getContext());
        this.mTipsView = tipsView;
        int i2 = this.controllerOptions.nonWifiStrRes;
        if (i2 != -1) {
            tipsView.showTip(TipsView.TipType.USER_DEFINE, this.realPlayer, i2);
        } else {
            tipsView.showTip(TipsView.TipType.NOT_WIFI, this.realPlayer);
        }
        JDPlayerSdk.dataFlowTipShow = true;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IMediaController
    public void showOnce(View view) {
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public boolean toggleMute(boolean z) {
        if (this.realPlayer == null) {
            return false;
        }
        OnControllerListener onControllerListener = this.mOnControllerListener;
        if (onControllerListener != null) {
            onControllerListener.onVoiceStateChange(!z);
        }
        this.realPlayer.setVolume(z ? 0.0f : 1.0f);
        this.ivMute.setImageResource(z ? R.drawable.video_player_voice_off : R.drawable.video_player_voice_on);
        return true;
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void togglePanelVisible(boolean z) {
        if (!this.manualControlVisible && !this.forceHide) {
            this.mHandler.removeMessages(2);
            if (z) {
                setVisibility(0);
            } else {
                EventHandler eventHandler = this.mHandler;
                eventHandler.sendMessageDelayed(eventHandler.obtainMessage(2), 3000L);
            }
        }
        updateCenterBtn(!z);
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void updateCommonUI(ImageView imageView, ProgressBar progressBar) {
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void updateFullBtn(boolean z) {
        JDControllerOptions jDControllerOptions = this.controllerOptions;
        if (jDControllerOptions == null) {
            return;
        }
        jDControllerOptions.enableFullSwitch = z;
        updateOptionUI();
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void updateFullMode(boolean z) {
        FullPlayerChanger fullPlayerChanger = this.mFullPlayerChanger;
        if (fullPlayerChanger == null) {
            return;
        }
        fullPlayerChanger.updateFullMode(z);
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void updateOrientationSensor(boolean z) {
        FullPlayerChanger fullPlayerChanger = this.mFullPlayerChanger;
        if (fullPlayerChanger == null) {
            return;
        }
        if (z) {
            fullPlayerChanger.sensorEnable();
        } else {
            fullPlayerChanger.sensorDisable();
        }
    }

    public JDPlayerController(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDPlayerController(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mProgress = 0L;
        this.mProgressInterval = 250;
        this.isComplete = false;
        this.forceHide = false;
        this.manualControlVisible = false;
        this.playError = false;
        this.mRetryInvoke = new TipsView.OnTipsInvoke() { // from class: tv.danmaku.ijk.media.widget.controller.JDPlayerController.4
            @Override // tv.danmaku.ijk.media.widget.custom.TipsView.OnTipsInvoke
            public void doRetry() {
                if (JDPlayerController.this.realPlayer == null) {
                    return;
                }
                JDPlayerController.this.realPlayer.retry(true);
            }
        };
        this.mOnPlayerEventListener = new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.widget.controller.JDPlayerController.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            public void onPlayEvent(int i3) {
                switch (i3) {
                    case 3:
                        JDPlayerController.this.isComplete = true;
                        JDPlayerController.this.updateCenterBtn(false);
                        JDPlayerController.this.show(0);
                        return;
                    case 4:
                        JDPlayerController.this.playError = true;
                        JDPlayerController.this.changeLoadingState(false);
                        JDPlayerController.this.showErrorLayout();
                        return;
                    case 5:
                    default:
                        return;
                    case 6:
                        JDPlayerController.this.isComplete = false;
                        JDPlayerController.this.playError = false;
                        JDPlayerController.this.changeLoadingState(false);
                        return;
                    case 7:
                        JDPlayerController.this.changeLoadingState(true);
                        return;
                    case 8:
                        if (JDPlayerController.this.mPlayer != null) {
                            JDPlayerController jDPlayerController = JDPlayerController.this;
                            jDPlayerController.isComplete = jDPlayerController.mPlayer.getDuration() == JDPlayerController.this.mPlayer.getCurrentPosition();
                        }
                        JDPlayerController.this.updateUIHandlerMsg();
                        return;
                    case 9:
                        JDPlayerController.this.playError = false;
                        JDPlayerController.this.isComplete = false;
                        JDPlayerController.this.changeLoadingState(false);
                        JDPlayerController.this.updateUIHandlerMsg();
                        return;
                }
            }
        };
        initView(context);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IMediaController
    public void show() {
        show(3000);
    }

    @TargetApi(21)
    public JDPlayerController(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mProgress = 0L;
        this.mProgressInterval = 250;
        this.isComplete = false;
        this.forceHide = false;
        this.manualControlVisible = false;
        this.playError = false;
        this.mRetryInvoke = new TipsView.OnTipsInvoke() { // from class: tv.danmaku.ijk.media.widget.controller.JDPlayerController.4
            @Override // tv.danmaku.ijk.media.widget.custom.TipsView.OnTipsInvoke
            public void doRetry() {
                if (JDPlayerController.this.realPlayer == null) {
                    return;
                }
                JDPlayerController.this.realPlayer.retry(true);
            }
        };
        this.mOnPlayerEventListener = new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.widget.controller.JDPlayerController.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            public void onPlayEvent(int i32) {
                switch (i32) {
                    case 3:
                        JDPlayerController.this.isComplete = true;
                        JDPlayerController.this.updateCenterBtn(false);
                        JDPlayerController.this.show(0);
                        return;
                    case 4:
                        JDPlayerController.this.playError = true;
                        JDPlayerController.this.changeLoadingState(false);
                        JDPlayerController.this.showErrorLayout();
                        return;
                    case 5:
                    default:
                        return;
                    case 6:
                        JDPlayerController.this.isComplete = false;
                        JDPlayerController.this.playError = false;
                        JDPlayerController.this.changeLoadingState(false);
                        return;
                    case 7:
                        JDPlayerController.this.changeLoadingState(true);
                        return;
                    case 8:
                        if (JDPlayerController.this.mPlayer != null) {
                            JDPlayerController jDPlayerController = JDPlayerController.this;
                            jDPlayerController.isComplete = jDPlayerController.mPlayer.getDuration() == JDPlayerController.this.mPlayer.getCurrentPosition();
                        }
                        JDPlayerController.this.updateUIHandlerMsg();
                        return;
                    case 9:
                        JDPlayerController.this.playError = false;
                        JDPlayerController.this.isComplete = false;
                        JDPlayerController.this.changeLoadingState(false);
                        JDPlayerController.this.updateUIHandlerMsg();
                        return;
                }
            }
        };
        initView(context);
    }
}
