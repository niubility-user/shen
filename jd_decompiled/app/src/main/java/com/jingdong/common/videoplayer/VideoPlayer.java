package com.jingdong.common.videoplayer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.UnLog;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Formatter;
import java.util.Locale;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.ext.mta.PlayerReportInvoke;
import tv.danmaku.ijk.media.player.threadpool.VideoPlayerThreadManager;

/* loaded from: classes6.dex */
public class VideoPlayer extends FrameLayout implements View.OnClickListener, View.OnTouchListener {
    private static final int HIDE_WHAT = 1;
    private static final int SHOW_PROGRESS = 2;
    private static final int SHOW_TIME_MAX = 5000;
    private static final String TAG = "VideoPlayer";
    private static final int TIME_OUT = 15000;
    private static final int TIME_OUT_WHAT = 3;
    private int bgState;
    private int buffProgress;
    private SimpleDraweeView cover;
    private IVideoPlayerCtrlViewListener ctrlViewListener;
    private JDDialog errorDialog;
    private IVideoPlayerClickListener iVideoPlayerClickListener;
    private IViewPlayerControl iViewPlayerControl;
    private boolean isDestory;
    private boolean isLandScape;
    private boolean isLoading;
    private boolean isShowBottom;
    public boolean isShowExtra;
    public boolean isShowMedia;
    public boolean isShowTop;
    public int jumpFrom;
    private ProgressBar loadingBar;
    private RelativeLayout mBottomView;
    private ImageButton mClose;
    private Context mContext;
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;
    private final Handler mHandler;
    private ProgressDialog mMsgDialog;
    private LinearLayout mNetErrorLayout;
    private ImageButton mPlayBtn;
    private TextView mPlayTime;
    private SeekBar mProgressBar;
    private ImageButton mRestart;
    private TextView mRetry;
    private ImageButton mScreenBtn;
    private TextView mTitle;
    private RelativeLayout mTopView;
    private TextView mTotalTIme;
    private Uri mUri;
    public JDVideoView mVideoView;
    private int oldProgress;
    private MediaPlayer.OnCompletionListener onCompletionListener;
    private MediaPlayer.OnPreparedListener onPreparedListener;
    public int playPostion;
    SeekBar.OnSeekBarChangeListener seekBarChangeListener;
    private int seekToPoint;
    private ImageButton shareBtn;
    private ShareInfo shareInfo;
    private boolean sharedEnable;
    Runnable showRunnable;
    private int thisProgress;
    public int totalTime;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public class AniListener implements Animation.AnimationListener {
        private int tag;

        public AniListener(int i2) {
            this.tag = -1;
            this.tag = i2;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            int i2 = this.tag;
            if (i2 == 0) {
                VideoPlayer.this.mTopView.setVisibility(0);
                VideoPlayer.this.mBottomView.setVisibility(0);
            } else if (i2 != 1) {
            } else {
                VideoPlayer.this.mTopView.setVisibility(8);
                VideoPlayer.this.mBottomView.setVisibility(8);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public VideoPlayer(Context context) {
        super(context);
        this.thisProgress = 0;
        this.oldProgress = 0;
        this.buffProgress = 0;
        this.isDestory = false;
        this.seekToPoint = 0;
        this.bgState = 0;
        this.isShowExtra = true;
        this.isShowTop = true;
        this.isShowMedia = true;
        this.sharedEnable = false;
        this.isShowBottom = true;
        this.mHandler = new Handler() { // from class: com.jingdong.common.videoplayer.VideoPlayer.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1) {
                    VideoPlayer.this.hide();
                } else if (i2 != 2) {
                    if (i2 != 3) {
                        return;
                    }
                    VideoPlayer.this.showErrorDialog();
                } else {
                    int progress = VideoPlayer.this.setProgress();
                    if (VideoPlayer.this.mVideoView.isPlaying()) {
                        sendMessageDelayed(obtainMessage(2), 1000 - (progress % 1000));
                    }
                }
            }
        };
        this.showRunnable = new Runnable() { // from class: com.jingdong.common.videoplayer.VideoPlayer.4
            @Override // java.lang.Runnable
            public void run() {
                VideoPlayerUtils.setActivityFullScreen(VideoPlayer.this.mVideoView);
            }
        };
        this.isLoading = false;
        this.seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.jingdong.common.videoplayer.VideoPlayer.6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                if (z) {
                    long duration = VideoPlayer.this.mVideoView.getDuration();
                    if (duration <= 0) {
                        return;
                    }
                    long j2 = (i2 * duration) / 1000;
                    if (duration - j2 < 1000) {
                        j2 = duration;
                    }
                    int i3 = j2 >= duration ? ((int) duration) - 500 : (int) j2;
                    VideoPlayer.this.mVideoView.seekTo(i3);
                    if (VideoPlayer.this.mPlayTime != null) {
                        VideoPlayer.this.mPlayTime.setText(VideoPlayer.this.stringForTime(i3));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                VideoPlayer.this.mHandler.removeMessages(2);
                VideoPlayer.this.mHandler.removeMessages(1);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                VideoPlayer.this.setProgress();
                VideoPlayer.this.mHandler.sendEmptyMessage(2);
                VideoPlayer.this.mHandler.sendMessageDelayed(VideoPlayer.this.mHandler.obtainMessage(1), Final.FIVE_SECOND);
            }
        };
        initView();
    }

    @SuppressLint({"WrongConstant"})
    private void displayChange() {
        if (((Activity) this.mContext).getRequestedOrientation() == 1) {
            ((Activity) this.mContext).setRequestedOrientation(0);
            this.mScreenBtn.setBackgroundResource(R.drawable.un_video_screen_v_to_h);
            this.isLandScape = true;
            if (this.jumpFrom == 1) {
                PlayerReportInvoke playerReport = JDPlayerSdk.getPlayerReport();
                Context context = this.mContext;
                playerReport.onMtaReportClick(context, "CommentsShare_VideoPause", context.getClass().getName());
            }
            IVideoPlayerClickListener iVideoPlayerClickListener = this.iVideoPlayerClickListener;
            if (iVideoPlayerClickListener != null) {
                iVideoPlayerClickListener.clickScreen(true);
            }
            this.shareBtn.setVisibility(4);
            this.mHandler.postDelayed(this.showRunnable, 300L);
            return;
        }
        ((Activity) this.mContext).setRequestedOrientation(1);
        this.mScreenBtn.setBackgroundResource(R.drawable.un_video_screen_h_to_v);
        this.isLandScape = false;
        if (this.sharedEnable && this.shareInfo != null) {
            this.shareBtn.setVisibility(0);
        }
        IVideoPlayerClickListener iVideoPlayerClickListener2 = this.iVideoPlayerClickListener;
        if (iVideoPlayerClickListener2 != null) {
            iVideoPlayerClickListener2.clickScreen(false);
        }
        this.mHandler.removeCallbacks(this.showRunnable);
        this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.videoplayer.VideoPlayer.3
            @Override // java.lang.Runnable
            public void run() {
                VideoPlayerUtils.setActivityNotFullScreen(VideoPlayer.this.mVideoView);
            }
        }, 300L);
    }

    private void doPauseResume() {
        hideLoading();
        this.mRestart.setVisibility(8);
        if (this.mVideoView.isPlaying()) {
            this.mVideoView.pause();
            if (this.jumpFrom == 1) {
                PlayerReportInvoke playerReport = JDPlayerSdk.getPlayerReport();
                Context context = this.mContext;
                playerReport.onMtaReportClick(context, "CommentsShare_VideoPause", context.getClass().getName());
            }
        } else {
            this.mVideoView.start();
        }
        updatePausePlay();
    }

    private Activity getActivity() {
        return (Activity) this.mContext;
    }

    private void hideNetErrorLayout() {
        this.mNetErrorLayout.setVisibility(8);
    }

    private void initView() {
        Context context = getContext();
        this.mContext = context;
        FrameLayout.inflate(context, R.layout.video_player, this);
        this.cover = (SimpleDraweeView) findViewById(R.id.loadingCover);
        this.mVideoView = (JDVideoView) findViewById(R.id.videoView);
        this.mTopView = (RelativeLayout) findViewById(R.id.topView);
        this.mBottomView = (RelativeLayout) findViewById(R.id.bottom);
        this.mPlayTime = (TextView) findViewById(R.id.thisTime);
        this.mTotalTIme = (TextView) findViewById(R.id.totalTime);
        this.mPlayBtn = (ImageButton) findViewById(R.id.start);
        this.mScreenBtn = (ImageButton) findViewById(R.id.screen);
        this.loadingBar = (ProgressBar) findViewById(R.id.loading);
        this.mNetErrorLayout = (LinearLayout) findViewById(R.id.netErrorLayout);
        this.mRetry = (TextView) findViewById(R.id.retry);
        ImageButton imageButton = (ImageButton) findViewById(R.id.share);
        this.shareBtn = imageButton;
        if (this.sharedEnable) {
            imageButton.setVisibility(0);
        } else {
            imageButton.setVisibility(8);
        }
        this.shareBtn.setOnClickListener(this);
        this.mRetry.setOnClickListener(this);
        this.mVideoView.setOnTouchListener(this);
        setOnTouchListener(this);
        this.mTopView.setOnTouchListener(this);
        this.mBottomView.setOnTouchListener(this);
        this.mPlayBtn.setOnClickListener(this);
        this.mScreenBtn.setOnClickListener(this);
        this.mVideoView.setOnPlayerStateListener(new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.videoplayer.VideoPlayer.1
            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                if (VideoPlayer.this.onCompletionListener != null) {
                    VideoPlayer.this.onCompletionListener.onCompletion(null);
                }
                VideoPlayer.this.showRestart();
                VideoPlayer.this.mVideoView.seekTo(0);
                VideoPlayer videoPlayer = VideoPlayer.this;
                videoPlayer.playPostion = videoPlayer.getDuration();
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                if (i3 != -1004) {
                    VideoPlayer.this.showErrorDialog();
                    return true;
                } else if (NetUtils.isNetworkAvailable()) {
                    return true;
                } else {
                    VideoPlayer.this.showNetErrorlayout();
                    return true;
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                VideoPlayer.this.optEnable(true);
                VideoPlayer.this.cover.setVisibility(8);
                VideoPlayer.this.mHandler.removeMessages(2);
                VideoPlayer.this.mHandler.sendEmptyMessage(2);
                VideoPlayer.this.hideLoading();
                VideoPlayer.this.mHandler.removeMessages(1);
                VideoPlayer.this.mHandler.sendMessageDelayed(VideoPlayer.this.mHandler.obtainMessage(1), Final.FIVE_SECOND);
                if (VideoPlayer.this.seekToPoint > 0) {
                    VideoPlayer videoPlayer = VideoPlayer.this;
                    videoPlayer.mVideoView.seekTo(videoPlayer.seekToPoint);
                }
                if (VideoPlayer.this.onPreparedListener != null) {
                    VideoPlayer.this.onPreparedListener.onPrepared(null);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
                if (VideoPlayer.this.seekToPoint > 0) {
                    VideoPlayer.this.mVideoView.start();
                    VideoPlayer.this.mHandler.sendEmptyMessage(2);
                    VideoPlayer.this.seekToPoint = 0;
                }
            }
        });
        SeekBar seekBar = (SeekBar) findViewById(R.id.progress);
        this.mProgressBar = seekBar;
        seekBar.setMax(1000);
        this.mProgressBar.setOnSeekBarChangeListener(this.seekBarChangeListener);
        this.mFormatBuilder = new StringBuilder();
        this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
        ProgressDialog progressDialog = new ProgressDialog(this.mContext);
        this.mMsgDialog = progressDialog;
        progressDialog.setMessage(this.mContext.getString(R.string.un_video_loading));
        this.mTitle = (TextView) findViewById(R.id.title);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.close);
        this.mClose = imageButton2;
        imageButton2.setOnClickListener(this);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.restart);
        this.mRestart = imageButton3;
        imageButton3.setOnClickListener(this);
        optEnable(false);
        if (!this.isShowExtra) {
            hide();
        }
        if (!this.isShowTop) {
            this.mClose.setVisibility(8);
        }
        if (!this.isShowMedia) {
            this.mRestart.setVisibility(8);
        }
        show(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void optEnable(boolean z) {
        if (z) {
            this.mProgressBar.setEnabled(true);
            this.mPlayBtn.setEnabled(true);
            return;
        }
        this.mProgressBar.setEnabled(false);
        this.mPlayBtn.setEnabled(false);
    }

    private void progressState() {
        if (this.mVideoView.isPlaying() && this.buffProgress != 1090) {
            int i2 = this.oldProgress;
            int i3 = this.thisProgress;
            if (i2 == i3 && !this.isLoading) {
                if (!NetUtils.isNetworkAvailable()) {
                    showNetErrorlayout();
                } else {
                    showLoading();
                }
            } else if (i2 != i3 && this.isLoading) {
                hideLoading();
            }
        }
        this.oldProgress = this.thisProgress;
    }

    private void reTry() {
        hideNetErrorLayout();
        showLoading();
        VideoPlayerThreadManager.getThreadPool().execute(new Runnable() { // from class: com.jingdong.common.videoplayer.VideoPlayer.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e2) {
                    UnLog.e(VideoPlayer.TAG, e2.toString());
                }
                VideoPlayer.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.videoplayer.VideoPlayer.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!NetUtils.isNetworkAvailable()) {
                            VideoPlayer.this.showNetErrorlayout();
                        } else {
                            VideoPlayer.this.start();
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int setProgress() {
        int currentPosition = this.mVideoView.getCurrentPosition();
        int duration = this.mVideoView.getDuration();
        this.totalTime = duration;
        SeekBar seekBar = this.mProgressBar;
        if (seekBar != null) {
            if (duration > 0) {
                seekBar.setProgress((currentPosition * 1000) / duration);
                this.thisProgress = currentPosition;
                if (currentPosition != 0) {
                    this.playPostion = currentPosition;
                }
            }
            int bufferPercentage = this.mVideoView.getBufferPercentage() * 10;
            this.mProgressBar.setSecondaryProgress(bufferPercentage);
            this.buffProgress = bufferPercentage;
        }
        progressState();
        TextView textView = this.mTotalTIme;
        if (textView != null) {
            textView.setText(stringForTime(duration));
        }
        TextView textView2 = this.mPlayTime;
        if (textView2 != null) {
            textView2.setText(stringForTime(currentPosition));
        }
        return currentPosition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorDialog() {
        if (this.isDestory || this.mContext == null) {
            return;
        }
        hideLoading();
        hideNetErrorLayout();
        this.mVideoView.releaseInThread(true);
        JDDialogFactory jDDialogFactory = JDDialogFactory.getInstance();
        Context context = this.mContext;
        JDDialog createJdDialogWithStyle1 = jDDialogFactory.createJdDialogWithStyle1(context, context.getString(R.string.un_video_load_error), this.mContext.getString(R.string.un_video_dialog_button_yes));
        this.errorDialog = createJdDialogWithStyle1;
        createJdDialogWithStyle1.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.videoplayer.VideoPlayer.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VideoPlayer.this.errorDialog.dismiss();
                if (VideoPlayer.this.iViewPlayerControl == null) {
                    ((Activity) VideoPlayer.this.mContext).finish();
                } else {
                    VideoPlayer.this.iViewPlayerControl.dialogClose();
                }
            }
        });
        this.errorDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNetErrorlayout() {
        hideLoading();
        this.mNetErrorLayout.setVisibility(0);
        this.mVideoView.pause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRestart() {
        hideLoading();
        if (this.isShowExtra) {
            if (this.isShowMedia) {
                this.mRestart.setVisibility(0);
            }
            show(false);
            updatePausePlay();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String stringForTime(int i2) {
        int i3 = i2 / 1000;
        int i4 = i3 % 60;
        int i5 = (i3 / 60) % 60;
        int i6 = i3 / R2.color.c_f2f3f3;
        this.mFormatBuilder.setLength(0);
        return i6 > 0 ? this.mFormatter.format("%d:%02d:%02d", Integer.valueOf(i6), Integer.valueOf(i5), Integer.valueOf(i4)).toString() : this.mFormatter.format("%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4)).toString();
    }

    public int getBottomViewHeight() {
        return (int) this.mContext.getResources().getDimension(R.dimen.ijkandvrplayer_un_video_default_bottom_height);
    }

    public int getBufferPercentage() {
        JDVideoView jDVideoView = this.mVideoView;
        if (jDVideoView != null) {
            return jDVideoView.getBufferPercentage();
        }
        return -1;
    }

    public int getDuration() {
        return this.totalTime;
    }

    public JDDialog getErrorDialog() {
        return this.errorDialog;
    }

    public int getVideoHeight() {
        JDVideoView jDVideoView = this.mVideoView;
        if (jDVideoView != null) {
            return jDVideoView.getHeight();
        }
        return 0;
    }

    public void hide() {
        if (this.mBottomView.getVisibility() != 0) {
            return;
        }
        IVideoPlayerCtrlViewListener iVideoPlayerCtrlViewListener = this.ctrlViewListener;
        if (iVideoPlayerCtrlViewListener != null) {
            iVideoPlayerCtrlViewListener.hide();
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.vd_option_leave_from_top);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.vd_option_leave_from_bottom);
        this.mTopView.clearAnimation();
        this.mBottomView.clearAnimation();
        loadAnimation.setAnimationListener(new AniListener(1));
        loadAnimation2.setAnimationListener(new AniListener(1));
        this.mTopView.startAnimation(loadAnimation);
        this.mBottomView.startAnimation(loadAnimation2);
    }

    public void hideLoading() {
        if (this.isLoading) {
            this.loadingBar.setVisibility(8);
            this.mHandler.removeMessages(3);
        }
        this.isLoading = false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.start) {
            this.mRestart.setVisibility(8);
            doPauseResume();
            this.mHandler.sendEmptyMessage(2);
        } else if (id == R.id.screen) {
            displayChange();
        } else if (id == R.id.close) {
            IViewPlayerControl iViewPlayerControl = this.iViewPlayerControl;
            if (iViewPlayerControl == null) {
                getActivity().finish();
            } else {
                iViewPlayerControl.close();
            }
        } else if (id == R.id.restart) {
            this.mVideoView.seekTo(0);
            doPauseResume();
            this.mHandler.sendEmptyMessage(2);
            this.mRestart.setVisibility(8);
        } else if (id == R.id.retry) {
            reTry();
        } else if (id == R.id.share) {
            IVideoPlayerClickListener iVideoPlayerClickListener = this.iVideoPlayerClickListener;
            if (iVideoPlayerClickListener != null) {
                iVideoPlayerClickListener.clickShare();
            }
            if (!this.sharedEnable || this.shareInfo == null) {
                return;
            }
            ShareUtil.panel(getActivity(), this.shareInfo);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onDestory() {
        this.isDestory = true;
        this.mHandler.removeMessages(3);
        JDVideoView jDVideoView = this.mVideoView;
        if (jDVideoView != null) {
            jDVideoView.releaseInThread(true);
        }
    }

    public void onPause() {
        boolean isPlaying = this.mVideoView.isPlaying();
        this.bgState = isPlaying ? 1 : 0;
        if (isPlaying) {
            this.mVideoView.pause();
            updatePausePlay();
        }
        show(false);
    }

    public void onResume() {
        this.mVideoView.seekTo(this.playPostion);
        if (this.bgState == 1) {
            showLoading();
            this.mVideoView.start();
            this.mHandler.removeMessages(2);
            this.mHandler.sendEmptyMessage(2);
            updatePausePlay();
        }
        show(true);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();
        if (id == R.id.topView || id == R.id.bottom) {
            return true;
        }
        if (id == R.id.videoView) {
            if (motionEvent.getAction() == 0) {
                if (this.mBottomView.getVisibility() == 0) {
                    hide();
                } else {
                    show(true);
                }
            }
            return true;
        }
        if (motionEvent.getAction() == 1) {
            IViewPlayerControl iViewPlayerControl = this.iViewPlayerControl;
            if (iViewPlayerControl != null) {
                iViewPlayerControl.close();
            } else {
                getActivity().finish();
            }
        }
        return true;
    }

    public void pause() {
        this.mVideoView.pause();
    }

    public void setCoverUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        int i2 = R.drawable.vd_player_fail_bg;
        JDImageUtils.displayImage(str, this.cover, jDDisplayImageOptions.showImageForEmptyUri(i2).showImageOnFail(i2).showImageOnLoading(i2));
    }

    public void setCtrlViewListener(IVideoPlayerCtrlViewListener iVideoPlayerCtrlViewListener) {
        this.ctrlViewListener = iVideoPlayerCtrlViewListener;
    }

    public void setFullScreen() {
        if (this.isLandScape) {
            this.mHandler.postDelayed(this.showRunnable, 300L);
        }
    }

    public void setIsShowBottom(boolean z) {
        this.isShowBottom = z;
        if (z) {
            return;
        }
        this.mBottomView.setVisibility(8);
    }

    public void setIsShowExtra(boolean z) {
        this.isShowExtra = z;
        if (z) {
            return;
        }
        hide();
    }

    public void setIsShowMid(boolean z) {
        this.isShowMedia = z;
        if (z) {
            return;
        }
        this.mRestart.setVisibility(8);
    }

    public void setIsShowTop(boolean z) {
        this.isShowTop = z;
        if (z) {
            return;
        }
        this.mClose.setVisibility(8);
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.onCompletionListener = onCompletionListener;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.onPreparedListener = onPreparedListener;
    }

    public void setSeekToPoint(int i2) {
        this.seekToPoint = i2;
    }

    public void setShareInfo(ShareInfo shareInfo) {
        this.shareInfo = shareInfo;
    }

    public void setSharedEnable(boolean z) {
        this.sharedEnable = z;
        if (z) {
            this.shareBtn.setVisibility(0);
        } else {
            this.shareBtn.setVisibility(8);
        }
    }

    public void setTitle(String str) {
        if (str != null) {
            this.mTitle.setText(str);
        }
    }

    public void setVideoUri(Uri uri) {
        if (uri == null) {
            return;
        }
        showLoading();
        this.mUri = uri;
        IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
        playerOptions.setPlayTypeId("149");
        playerOptions.setAspectRatio(0);
        this.mVideoView.setPlayerOptions(playerOptions);
        this.mVideoView.setVideoPath(this.mUri.toString());
    }

    public void setiVideoPlayerClickListener(IVideoPlayerClickListener iVideoPlayerClickListener) {
        this.iVideoPlayerClickListener = iVideoPlayerClickListener;
    }

    public void setiViewPlayerControl(IViewPlayerControl iViewPlayerControl) {
        this.iViewPlayerControl = iViewPlayerControl;
    }

    public void show(boolean z) {
        if (this.mBottomView.getVisibility() == 0) {
            return;
        }
        IVideoPlayerCtrlViewListener iVideoPlayerCtrlViewListener = this.ctrlViewListener;
        if (iVideoPlayerCtrlViewListener != null) {
            iVideoPlayerCtrlViewListener.show();
        }
        if (this.isShowExtra) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.vd_option_entry_from_top);
            loadAnimation.setAnimationListener(new AniListener(0));
            this.mTopView.startAnimation(loadAnimation);
            if (this.isShowBottom) {
                Animation loadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.vd_option_entry_from_bottom);
                loadAnimation2.setAnimationListener(new AniListener(0));
                this.mBottomView.startAnimation(loadAnimation2);
            }
            this.mHandler.removeMessages(1);
            if (z) {
                Handler handler = this.mHandler;
                handler.sendMessageDelayed(handler.obtainMessage(1), Final.FIVE_SECOND);
            }
        }
    }

    public void showLoading() {
        if (!this.isLoading) {
            this.loadingBar.setVisibility(0);
            this.mHandler.sendEmptyMessageDelayed(3, 15000L);
        }
        this.isLoading = true;
    }

    public void start() {
        this.oldProgress = 0;
        this.mRestart.setVisibility(8);
        if (this.seekToPoint <= 0) {
            this.mVideoView.start();
            this.mHandler.sendEmptyMessage(2);
        }
    }

    public void updatePausePlay() {
        if (!this.mVideoView.isPlaying() && this.mVideoView.getPlayState() != 3) {
            this.mPlayBtn.setBackgroundResource(R.drawable.vd_play_video);
        } else {
            this.mPlayBtn.setBackgroundResource(R.drawable.vd_pause_video);
        }
    }

    public VideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.thisProgress = 0;
        this.oldProgress = 0;
        this.buffProgress = 0;
        this.isDestory = false;
        this.seekToPoint = 0;
        this.bgState = 0;
        this.isShowExtra = true;
        this.isShowTop = true;
        this.isShowMedia = true;
        this.sharedEnable = false;
        this.isShowBottom = true;
        this.mHandler = new Handler() { // from class: com.jingdong.common.videoplayer.VideoPlayer.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1) {
                    VideoPlayer.this.hide();
                } else if (i2 != 2) {
                    if (i2 != 3) {
                        return;
                    }
                    VideoPlayer.this.showErrorDialog();
                } else {
                    int progress = VideoPlayer.this.setProgress();
                    if (VideoPlayer.this.mVideoView.isPlaying()) {
                        sendMessageDelayed(obtainMessage(2), 1000 - (progress % 1000));
                    }
                }
            }
        };
        this.showRunnable = new Runnable() { // from class: com.jingdong.common.videoplayer.VideoPlayer.4
            @Override // java.lang.Runnable
            public void run() {
                VideoPlayerUtils.setActivityFullScreen(VideoPlayer.this.mVideoView);
            }
        };
        this.isLoading = false;
        this.seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.jingdong.common.videoplayer.VideoPlayer.6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                if (z) {
                    long duration = VideoPlayer.this.mVideoView.getDuration();
                    if (duration <= 0) {
                        return;
                    }
                    long j2 = (i2 * duration) / 1000;
                    if (duration - j2 < 1000) {
                        j2 = duration;
                    }
                    int i3 = j2 >= duration ? ((int) duration) - 500 : (int) j2;
                    VideoPlayer.this.mVideoView.seekTo(i3);
                    if (VideoPlayer.this.mPlayTime != null) {
                        VideoPlayer.this.mPlayTime.setText(VideoPlayer.this.stringForTime(i3));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                VideoPlayer.this.mHandler.removeMessages(2);
                VideoPlayer.this.mHandler.removeMessages(1);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                VideoPlayer.this.setProgress();
                VideoPlayer.this.mHandler.sendEmptyMessage(2);
                VideoPlayer.this.mHandler.sendMessageDelayed(VideoPlayer.this.mHandler.obtainMessage(1), Final.FIVE_SECOND);
            }
        };
        initView();
    }

    public VideoPlayer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.thisProgress = 0;
        this.oldProgress = 0;
        this.buffProgress = 0;
        this.isDestory = false;
        this.seekToPoint = 0;
        this.bgState = 0;
        this.isShowExtra = true;
        this.isShowTop = true;
        this.isShowMedia = true;
        this.sharedEnable = false;
        this.isShowBottom = true;
        this.mHandler = new Handler() { // from class: com.jingdong.common.videoplayer.VideoPlayer.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i22 = message.what;
                if (i22 == 1) {
                    VideoPlayer.this.hide();
                } else if (i22 != 2) {
                    if (i22 != 3) {
                        return;
                    }
                    VideoPlayer.this.showErrorDialog();
                } else {
                    int progress = VideoPlayer.this.setProgress();
                    if (VideoPlayer.this.mVideoView.isPlaying()) {
                        sendMessageDelayed(obtainMessage(2), 1000 - (progress % 1000));
                    }
                }
            }
        };
        this.showRunnable = new Runnable() { // from class: com.jingdong.common.videoplayer.VideoPlayer.4
            @Override // java.lang.Runnable
            public void run() {
                VideoPlayerUtils.setActivityFullScreen(VideoPlayer.this.mVideoView);
            }
        };
        this.isLoading = false;
        this.seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.jingdong.common.videoplayer.VideoPlayer.6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i22, boolean z) {
                if (z) {
                    long duration = VideoPlayer.this.mVideoView.getDuration();
                    if (duration <= 0) {
                        return;
                    }
                    long j2 = (i22 * duration) / 1000;
                    if (duration - j2 < 1000) {
                        j2 = duration;
                    }
                    int i3 = j2 >= duration ? ((int) duration) - 500 : (int) j2;
                    VideoPlayer.this.mVideoView.seekTo(i3);
                    if (VideoPlayer.this.mPlayTime != null) {
                        VideoPlayer.this.mPlayTime.setText(VideoPlayer.this.stringForTime(i3));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                VideoPlayer.this.mHandler.removeMessages(2);
                VideoPlayer.this.mHandler.removeMessages(1);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                VideoPlayer.this.setProgress();
                VideoPlayer.this.mHandler.sendEmptyMessage(2);
                VideoPlayer.this.mHandler.sendMessageDelayed(VideoPlayer.this.mHandler.obtainMessage(1), Final.FIVE_SECOND);
            }
        };
        initView();
    }

    @TargetApi(21)
    public VideoPlayer(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.thisProgress = 0;
        this.oldProgress = 0;
        this.buffProgress = 0;
        this.isDestory = false;
        this.seekToPoint = 0;
        this.bgState = 0;
        this.isShowExtra = true;
        this.isShowTop = true;
        this.isShowMedia = true;
        this.sharedEnable = false;
        this.isShowBottom = true;
        this.mHandler = new Handler() { // from class: com.jingdong.common.videoplayer.VideoPlayer.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i22 = message.what;
                if (i22 == 1) {
                    VideoPlayer.this.hide();
                } else if (i22 != 2) {
                    if (i22 != 3) {
                        return;
                    }
                    VideoPlayer.this.showErrorDialog();
                } else {
                    int progress = VideoPlayer.this.setProgress();
                    if (VideoPlayer.this.mVideoView.isPlaying()) {
                        sendMessageDelayed(obtainMessage(2), 1000 - (progress % 1000));
                    }
                }
            }
        };
        this.showRunnable = new Runnable() { // from class: com.jingdong.common.videoplayer.VideoPlayer.4
            @Override // java.lang.Runnable
            public void run() {
                VideoPlayerUtils.setActivityFullScreen(VideoPlayer.this.mVideoView);
            }
        };
        this.isLoading = false;
        this.seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.jingdong.common.videoplayer.VideoPlayer.6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i22, boolean z) {
                if (z) {
                    long duration = VideoPlayer.this.mVideoView.getDuration();
                    if (duration <= 0) {
                        return;
                    }
                    long j2 = (i22 * duration) / 1000;
                    if (duration - j2 < 1000) {
                        j2 = duration;
                    }
                    int i32 = j2 >= duration ? ((int) duration) - 500 : (int) j2;
                    VideoPlayer.this.mVideoView.seekTo(i32);
                    if (VideoPlayer.this.mPlayTime != null) {
                        VideoPlayer.this.mPlayTime.setText(VideoPlayer.this.stringForTime(i32));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                VideoPlayer.this.mHandler.removeMessages(2);
                VideoPlayer.this.mHandler.removeMessages(1);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                VideoPlayer.this.setProgress();
                VideoPlayer.this.mHandler.sendEmptyMessage(2);
                VideoPlayer.this.mHandler.sendMessageDelayed(VideoPlayer.this.mHandler.obtainMessage(1), Final.FIVE_SECOND);
            }
        };
        initView();
    }
}
