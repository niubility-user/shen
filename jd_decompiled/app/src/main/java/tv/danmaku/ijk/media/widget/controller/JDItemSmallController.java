package tv.danmaku.ijk.media.widget.controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.common.unification.video.player.VideoPlayUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import tv.danmaku.ijk.media.IMediaControllerExt;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.utils.PlayerSystemUtil;
import tv.danmaku.ijk.media.widget.JDPlayerView;

/* loaded from: classes11.dex */
public class JDItemSmallController extends FrameLayout implements IMediaControllerExt, View.OnClickListener {
    private JDControllerOptions controllerOptions;
    private LinearLayout errorLayoutSmall;
    private TextView errorTipTvSmall;
    private ImageView ivCenterBtn;
    private ImageView ivMute;
    private Context mContext;
    private OnControllerListener mOnControllerListener;
    private final IMediaPlayer.OnPlayerEventListener mOnPlayerEventListener;
    private MediaController.MediaPlayerControl mPlayer;
    private boolean playError;
    private JDVideoView realPlayer;
    private TextView retrySmall;

    public JDItemSmallController(@NonNull Context context) {
        this(context, null);
    }

    private void hideErrorView() {
        this.errorLayoutSmall.setVisibility(8);
    }

    private void inflateLayout() {
        LayoutInflater.from(this.mContext).inflate(R.layout.ijkandvrplayer_view_player_controller_small, this);
        this.ivMute = (ImageView) findViewById(R.id.ivMute);
        this.ivCenterBtn = (ImageView) findViewById(R.id.ivCenterBtn);
        this.errorLayoutSmall = (LinearLayout) findViewById(R.id.errorLayoutSmall);
        this.retrySmall = (TextView) findViewById(R.id.retrySmall);
        this.errorTipTvSmall = (TextView) findViewById(R.id.errorTipTvSmall);
        updateMuteUI();
        initListener();
    }

    private void initListener() {
        this.ivMute.setOnClickListener(this);
        this.ivCenterBtn.setOnClickListener(this);
        this.retrySmall.setOnClickListener(this);
    }

    private void initView(Context context) {
        this.mContext = context;
        inflateLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorView() {
        JDControllerOptions jDControllerOptions = this.controllerOptions;
        if (jDControllerOptions == null || jDControllerOptions.enableErrorTip) {
            this.errorLayoutSmall.setVisibility(0);
            this.retrySmall.setEnabled(true);
            if (!NetUtils.isNetworkAvailable()) {
                this.retrySmall.setBackgroundResource(R.drawable.video_player_retry_small);
                this.errorTipTvSmall.setText(this.mContext.getResources().getString(R.string.video_player_net_error_small));
                return;
            }
            this.retrySmall.setBackgroundResource(R.drawable.video_player_error_icon_small);
            this.errorTipTvSmall.setText(this.mContext.getResources().getString(R.string.video_player_load_error_small));
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

    private void updateMuteUI() {
        ImageView imageView;
        JDVideoView jDVideoView = this.realPlayer;
        if (jDVideoView == null || (imageView = this.ivMute) == null) {
            return;
        }
        imageView.setImageResource(jDVideoView.getVolume() == 0.0f ? R.drawable.video_player_voice_off : R.drawable.video_player_voice_on);
    }

    private void updateOptionUI() {
        JDControllerOptions jDControllerOptions = this.controllerOptions;
        if (jDControllerOptions == null) {
            return;
        }
        this.ivMute.setVisibility(jDControllerOptions.enableMuteSwitch ? 0 : 8);
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void changeOrientation(boolean z) {
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void forceHide() {
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void forceShow() {
        if (isShowing()) {
            return;
        }
        ImageView imageView = this.ivCenterBtn;
        if (imageView != null) {
            imageView.requestFocus();
        }
        if (this.playError) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IMediaController
    public void hide() {
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IMediaController
    public boolean isShowing() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.retrySmall) {
            hideErrorView();
            JDVideoView jDVideoView = this.realPlayer;
            if (jDVideoView != null) {
                jDVideoView.retry(true);
            }
        } else if (view.getId() == R.id.ivMute) {
            boolean z = this.realPlayer.getVolume() != 0.0f;
            toggleMute(z);
            OnControllerListener onControllerListener = this.mOnControllerListener;
            if (onControllerListener != null) {
                onControllerListener.onVoiceBtnClick(!z);
            }
        }
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void release() {
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
        updateOptionUI();
        show();
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
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void setOnControllerListener(OnControllerListener onControllerListener) {
        this.mOnControllerListener = onControllerListener;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IMediaController
    public void show() {
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IMediaController
    public void show(int i2) {
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void showNonWifiTip() {
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
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void updateCommonUI(ImageView imageView, ProgressBar progressBar) {
        if (progressBar != null) {
            progressBar.setIndeterminateDrawable(getResources().getDrawable(R.drawable.video_player_loading_small));
            ViewGroup.LayoutParams layoutParams = progressBar.getLayoutParams();
            layoutParams.height = PlayerSystemUtil.dip2px(getContext(), 26.0f);
            layoutParams.width = PlayerSystemUtil.dip2px(getContext(), 26.0f);
            progressBar.setLayoutParams(layoutParams);
        }
        if (imageView != null) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.video_player_center_play_middle));
            ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
            layoutParams2.height = -2;
            layoutParams2.width = -2;
            imageView.setLayoutParams(layoutParams2);
        }
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void updateFullBtn(boolean z) {
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void updateFullMode(boolean z) {
    }

    @Override // tv.danmaku.ijk.media.IMediaControllerExt
    public void updateOrientationSensor(boolean z) {
    }

    public JDItemSmallController(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDItemSmallController(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.playError = false;
        this.mOnPlayerEventListener = new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.widget.controller.JDItemSmallController.1
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            public void onPlayEvent(int i3) {
                if (i3 == 3) {
                    if (JDItemSmallController.this.realPlayer != null) {
                        JDItemSmallController.this.realPlayer.seekTo(0);
                    }
                    VideoPlayUtil.muteAudioFocus(JDItemSmallController.this.getContext(), false);
                } else if (i3 == 4) {
                    JDItemSmallController.this.playError = true;
                    JDItemSmallController.this.showErrorView();
                } else if (i3 == 6 || i3 == 9) {
                    JDItemSmallController.this.playError = false;
                }
            }
        };
        initView(context);
    }

    @TargetApi(21)
    public JDItemSmallController(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.playError = false;
        this.mOnPlayerEventListener = new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.widget.controller.JDItemSmallController.1
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            public void onPlayEvent(int i32) {
                if (i32 == 3) {
                    if (JDItemSmallController.this.realPlayer != null) {
                        JDItemSmallController.this.realPlayer.seekTo(0);
                    }
                    VideoPlayUtil.muteAudioFocus(JDItemSmallController.this.getContext(), false);
                } else if (i32 == 4) {
                    JDItemSmallController.this.playError = true;
                    JDItemSmallController.this.showErrorView();
                } else if (i32 == 6 || i32 == 9) {
                    JDItemSmallController.this.playError = false;
                }
            }
        };
        initView(context);
    }
}
