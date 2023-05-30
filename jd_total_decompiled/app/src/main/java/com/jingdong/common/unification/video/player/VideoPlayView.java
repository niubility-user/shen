package com.jingdong.common.unification.video.player;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.videoplayer.IVideoPlayerCtrlViewListener;
import com.jingdong.common.videoplayer.IViewPlayerControl;
import com.jingdong.common.videoplayer.VideoPlayerUtils;
import com.jingdong.jdsdk.mta.ExposureRvUtils;
import com.jingdong.jdsdk.utils.NetUtils;
import java.util.List;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.ext.mta.NewVideoMtaInfoImpl;
import tv.danmaku.ijk.media.ext.mta.PlayerReportInvoke;
import tv.danmaku.ijk.media.ext.mta.bean.JDVideoPlayerMtaEntity;
import tv.danmaku.ijk.media.player.IJDVideoPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.threadpool.VideoPlayerThreadManager;

/* loaded from: classes6.dex */
public class VideoPlayView extends FrameLayout implements View.OnClickListener, View.OnTouchListener, IPlayerControl.OnPlayerStateListener, IJDVideoPlayerBiz {
    private static final int HIDE_WHAT = 3;
    private static final int MAX_PROGRESS = 1000;
    private static final int MESSAGE_HANDLER_SPEED = 4;
    private static final int MESSAGE_SHOW_PROGRESS = 1;
    public static final int SCREEN_BIG = 2;
    public static final int SCREEN_ITEM = 3;
    public static final int SCREEN_NORMAL = 0;
    public static final int SCREEN_NO_UI = 4;
    public static final int SCREEN_SMALL = 1;
    private static final int SHOW_TIME_MAX = 3000;
    public static final int STATE_COMPLETED = 336;
    public static final int STATE_ERROR = 331;
    public static final int STATE_IDLE = 330;
    public static final int STATE_LOAD_ERROR = 401;
    public static final int STATE_NET_ERROR = 400;
    public static final int STATE_NO_WIFI_TIP = 402;
    public static final int STATE_PAUSED = 335;
    public static final int STATE_PLAYING = 334;
    public static final int STATE_PREPARED = 333;
    public static final int STATE_PREPARING = 332;
    public static final int STATE_STOP = 337;
    private static final String TAG = VideoPlayView.class.getSimpleName();
    private static final int TIME_OUT = 15000;
    private static final int TIME_OUT_WHAT = 2;
    private int beforeScreenState;
    private int bgState;
    private TextView bigBackBt;
    private View bottomBarLayout;
    private FrameLayout bottomBarLayoutCopy;
    private ProgressBar bottomProgressBar;
    private ProgressBar bottomProgressBarSmall;
    private VideoSeekBarBgDrawable bottomSeekBarProgrssBg;
    private VideoSeekBarBgDrawable bottomSeekBarSecondProgressBg;
    private TextView bottomShareIcon;
    private boolean bottomSharedEnable;
    private ImageView bottomVoice;
    private int bottomVoiceOffRes;
    private int bottomVoiceOnRes;
    private ImageView bottomVoicePlaceHolder;
    private int buffProgress;
    private ImageView centerPlayIv;
    private ImageButton closeIb;
    private ImageView corverIv;
    private int coverBitmapHeight;
    private int coverBitmapWidth;
    private IVideoPlayerCtrlViewListener ctrlViewListener;
    private int currentPoint;
    private int currentPosition;
    private TextView currentTimeTv;
    private int currentTipState;
    private int degree;
    private int dragPosition;
    private int duration;
    private boolean enableItemViewTime;
    private TextView endTimeTv;
    private LinearLayout errorLayoutSmall;
    private TextView errorTipTv;
    private TextView errorTipTvSmall;
    private boolean flowToastAlert;
    private int fullScreenLandResourceId;
    private int fullScreenPortResourceId;
    private ImageView fullscreenIv;
    private GestureDetector gestureDetector;
    private IViewPlayerControl iViewPlayerControl;
    private boolean isAlreadyShowNoWifi;
    private boolean isAlreadyStartPlay;
    private boolean isAutoPlay;
    private boolean isDestory;
    private boolean isFromComment;
    private boolean isHideBottomBar;
    private boolean isHideCenterPlayer;
    private boolean isHideControlPanl;
    private boolean isHideRetryBt;
    private boolean isHideTopBar;
    private boolean isKeepBottomProgressBarVisi;
    private boolean isLandScape;
    private boolean isLive;
    private boolean isLoading;
    private boolean isLocalVideo;
    private boolean isLoopPlay;
    private boolean isNeedAccessChangeToWifi;
    private boolean isNeedJudgeNetOnStart;
    private boolean isNeedKeepScreenOn;
    private boolean isPlayingTail;
    private boolean isResumeBgMusicOnPause;
    private boolean isScreenOn;
    private boolean isSetSource;
    private boolean isShowBottomProgressBar;
    private boolean isShowErrorLayout;
    private boolean isVoiceFirstClicked;
    private boolean isVoiceIconKeepVisiInFullScreen;
    private boolean isVoiceOff;
    private ItemVideoPlayerController itemCtrl;
    private View itemVideo;
    public int jumpFrom;
    private ImageView liveIconIv;
    private ImageView loadErrorIv;
    private boolean loadErrorRetry;
    private LinearLayout loadingLayout;
    private LinearLayout loadingLayoutSmall;
    private TextView mClickToRetry;
    private Context mContext;
    private Handler mHandler;
    private LinearLayout mNetErrorLayout;
    private IPlayerControl.PlayerOptions mPlayerOptions;
    private View mPointView;
    private SeekBar mSeekBar;
    private final SeekBar.OnSeekBarChangeListener mSeekListener;
    private AVideoMtaListener mtaListener;
    private VideoSeekBarBgDrawable nomalSeekBarProgrssBg;
    private VideoSeekBarBgDrawable nomalSeekBarSecondProgressBg;
    private int normalVideoDuration;
    private int oldOrientation;
    private int oldProgress;
    private AVideoPlayStateListener onPlayerStateListener;
    private OrientationEventListener orientationEventListener;
    private int pauseCount;
    private ImageView playIvOnBottomBar;
    public int playPostion;
    private boolean pointViewAnimFinish;
    private List<Integer> points;
    private IProgrssChangeListener progrssChangeListener;
    private TextView replayIcon;
    private int resumeCount;
    private TextView retryTvSmall;
    public RelativeLayout rootLayout;
    private int screenState;
    private ImageButton shareIb;
    private ShareInfo shareInfo;
    private boolean sharedEnable;
    Runnable showRunnable;
    private ImageButton smallCloseIb;
    private View smallTopBarLayout;
    private int status;
    private String tailCoverUrl;
    private String tailSource;
    private int thisProgress;
    private TextView titleTv;
    private View topBarLayout;
    private boolean useNew;
    private NewVideoMtaInfoImpl videoMtaInfoImpl;
    public String videoPath;
    private IJDVideoPlayer videoView;
    private AVideoViewBtClickListener videoViewBtClickListener;
    private IVideoViewOnTouchListener videoViewOnTouchListener;
    private TextView voiceIcon;
    private TextView voiceIconSmall;
    private int voiceIconState;
    private LinearLayout voiceParent;

    public VideoPlayView(Context context, IPlayerControl.PlayerOptions playerOptions) {
        super(context);
        this.status = 330;
        this.currentTipState = -1;
        this.isLoading = false;
        this.sharedEnable = false;
        this.bottomSharedEnable = false;
        this.thisProgress = 0;
        this.oldProgress = 0;
        this.buffProgress = 0;
        this.isVoiceOff = true;
        this.isKeepBottomProgressBarVisi = false;
        this.isAutoPlay = true;
        this.bgState = -1;
        this.isHideCenterPlayer = true;
        this.isDestory = false;
        this.jumpFrom = -1;
        this.screenState = 0;
        this.beforeScreenState = 0;
        this.isAlreadyShowNoWifi = false;
        this.pointViewAnimFinish = false;
        this.currentPoint = -1;
        this.isNeedJudgeNetOnStart = true;
        this.isResumeBgMusicOnPause = true;
        this.isSetSource = true;
        this.isAlreadyStartPlay = true;
        this.pauseCount = 0;
        this.resumeCount = 0;
        this.isScreenOn = false;
        this.isNeedKeepScreenOn = true;
        this.flowToastAlert = false;
        this.isShowErrorLayout = true;
        this.enableItemViewTime = true;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.jingdong.common.unification.video.player.VideoPlayView.1
            {
                VideoPlayView.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1) {
                    VideoPlayView.this.syncProgress();
                    if (VideoPlayView.this.status == 334) {
                        sendMessageDelayed(obtainMessage(1), 100L);
                    }
                } else if (i2 == 2) {
                    VideoPlayView.this.showLoadErrorLayout(4, true);
                } else if (i2 == 3) {
                    VideoPlayView.this.hide();
                } else if (i2 != 4) {
                } else {
                    long tcpSpeed = VideoPlayView.this.videoView.getTcpSpeed() / 1000;
                    if (VideoPlayView.this.videoMtaInfoImpl != null) {
                        VideoPlayView.this.videoMtaInfoImpl.updateSpeed(tcpSpeed);
                    }
                    if (VideoPlayView.this.status == 334) {
                        sendMessageDelayed(obtainMessage(4), 2000L);
                    }
                }
            }
        };
        this.mSeekListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.4
            {
                VideoPlayView.this = this;
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                if (z) {
                    int duration = VideoPlayView.this.getDuration();
                    float f2 = duration;
                    int i3 = (int) ((i2 * f2) / 1000.0f);
                    if (duration - i3 < 1000) {
                        i3 = duration;
                    }
                    int i4 = i3 >= duration ? duration - 500 : i3;
                    int i5 = (int) ((i4 * 1000.0f) / f2);
                    VideoPlayView.this.mSeekBar.setProgress(i5);
                    VideoPlayView.this.setSeekBarBgProgress(i5, false);
                    if (VideoPlayView.this.mtaListener != null) {
                        VideoPlayView.this.mtaListener.progressChangedFromUser(i5);
                    }
                    if (VideoPlayView.this.isShowBottomProgressBar) {
                        VideoPlayView.this.bottomProgressBar.setProgress(i5);
                        VideoPlayView.this.setSeekBarBgProgress(i5, true);
                    }
                    if (VideoPlayView.this.screenState == 1) {
                        VideoPlayView.this.bottomProgressBarSmall.setProgress(i5);
                    }
                    if (VideoPlayView.this.progrssChangeListener != null) {
                        VideoPlayView.this.progrssChangeListener.onProgressChange(i5, 1000);
                    }
                    VideoPlayView.this.dragPosition = i4;
                    if (VideoPlayView.this.currentTimeTv != null) {
                        VideoPlayView.this.currentTimeTv.setText(VideoPlayUtil.generateTime(i3));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                VideoPlayView.this.mHandler.removeMessages(1);
                VideoPlayView.this.mHandler.removeMessages(3);
                VideoPlayView.this.mHandler.removeMessages(4);
                if (VideoPlayView.this.mtaListener != null) {
                    VideoPlayView.this.mtaListener.startTrackingTouch();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (UnLog.D) {
                    UnLog.d(VideoPlayView.TAG, "dragPosition:" + VideoPlayView.this.dragPosition);
                }
                if (VideoPlayView.this.mtaListener != null) {
                    VideoPlayView.this.mtaListener.stopTrackingTouch();
                }
                VideoPlayView.this.videoView.seekTo(VideoPlayView.this.dragPosition);
                VideoPlayView videoPlayView = VideoPlayView.this;
                videoPlayView.setSelectPoint(videoPlayView.dragPosition, false);
                VideoPlayView.this.syncProgress();
                VideoPlayView.this.mHandler.sendEmptyMessage(1);
                VideoPlayView.this.mHandler.sendEmptyMessage(4);
                VideoPlayView.this.mHandler.sendMessageDelayed(VideoPlayView.this.mHandler.obtainMessage(3), 3000L);
            }
        };
        this.showRunnable = new Runnable() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.8
            {
                VideoPlayView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                VideoPlayerUtils.setActivityFullScreen(VideoPlayView.this.videoView);
            }
        };
        this.mPlayerOptions = playerOptions;
        initView(null);
    }

    private void changeToBigUI() {
        if (UnLog.D) {
            String str = TAG;
            UnLog.d(str, "degree:" + this.degree);
            UnLog.d(str, "width:" + getVideoWidth());
            UnLog.d(str, "height:" + getVideoHeight());
        }
        if (this.beforeScreenState == 1) {
            changeToNormalUI();
        }
        this.closeIb.setVisibility(8);
        this.bigBackBt.setVisibility(0);
        this.fullscreenIv.setBackgroundResource(this.fullScreenLandResourceId);
        this.bottomSeekBarProgrssBg.setPoints(null);
        this.isLandScape = true;
        setVideoTailUi();
    }

    private void changeToItemUI() {
        if (this.itemCtrl == null) {
            ItemVideoPlayerController itemVideoPlayerController = new ItemVideoPlayerController(getContext());
            this.itemCtrl = itemVideoPlayerController;
            itemVideoPlayerController.enableTimeDisplay(this.enableItemViewTime);
            View view = this.itemCtrl.getView();
            this.itemVideo = view;
            addView(view);
        }
        setScaleType("fillParent");
        setCoverImageScaleType(ImageView.ScaleType.CENTER_CROP);
        this.isNeedJudgeNetOnStart = false;
        this.loadErrorRetry = false;
        hideAllUI();
        View view2 = this.itemVideo;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        setShowVoice(false, true);
        setShowBottomVoice(false, true);
    }

    private void changeToNoUI() {
        this.isLandScape = false;
        hideAllUI();
        hideCenterPlayer(false);
        this.centerPlayIv.setBackgroundResource(R.drawable.video_player_center_play_middle);
        this.centerPlayIv.setVisibility(0);
        if (this.currentTipState != -1) {
            this.mNetErrorLayout.setVisibility(8);
            switch (this.currentTipState) {
                case 400:
                    showNetErrorlayout();
                    break;
                case 401:
                    if (this.videoMtaInfoImpl != null) {
                        showLoadErrorLayout(false);
                        break;
                    }
                    break;
                case 402:
                    showNetChangelayout();
                    break;
            }
        }
        if (this.isLoading) {
            this.loadingLayout.setVisibility(8);
            this.loadingLayoutSmall.setVisibility(0);
        }
        hide();
        setVideoTailUi();
    }

    private void changeToNormalUI() {
        this.isLandScape = false;
        show(true);
        int i2 = this.voiceIconState;
        if (i2 != -1 && !this.isVoiceFirstClicked) {
            if (i2 == 0) {
                this.voiceIcon.setVisibility(0);
            } else {
                this.bottomVoice.setVisibility(0);
            }
        }
        this.closeIb.setVisibility(0);
        this.bigBackBt.setVisibility(8);
        this.topBarLayout.setBackgroundDrawable(null);
        this.fullscreenIv.setBackgroundResource(this.fullScreenPortResourceId);
        this.bottomSeekBarProgrssBg.setPoints(this.points);
        if (this.beforeScreenState == 1) {
            this.smallTopBarLayout.setVisibility(8);
            this.bottomProgressBarSmall.setVisibility(8);
            if (this.currentTipState != -1) {
                this.errorLayoutSmall.setVisibility(8);
                switch (this.currentTipState) {
                    case 400:
                        showNetErrorlayout();
                        break;
                    case 401:
                        showLoadErrorLayout(false);
                        break;
                    case 402:
                        showNetChangelayout();
                        break;
                }
            }
        }
        if (this.isLoading) {
            this.loadingLayout.setVisibility(0);
            this.loadingLayoutSmall.setVisibility(8);
        }
        setVideoTailUi();
    }

    private void changeToSmallUI() {
        this.isLandScape = false;
        this.smallTopBarLayout.setVisibility(0);
        if (this.isVoiceOff) {
            this.voiceIconSmall.setVisibility(0);
        } else {
            this.voiceIconSmall.setVisibility(8);
        }
        this.bottomProgressBarSmall.setVisibility(0);
        if (this.voiceIcon.getVisibility() == 0) {
            this.voiceIcon.setVisibility(8);
        }
        if (this.bottomVoice.getVisibility() == 0) {
            this.bottomVoice.setVisibility(8);
        }
        if (this.currentTipState != -1) {
            this.mNetErrorLayout.setVisibility(8);
            switch (this.currentTipState) {
                case 400:
                    showNetErrorlayout();
                    break;
                case 401:
                    showLoadErrorLayout(false);
                    break;
                case 402:
                    showNetChangelayout();
                    break;
            }
        }
        if (this.isLoading) {
            this.loadingLayout.setVisibility(8);
            this.loadingLayoutSmall.setVisibility(0);
        }
        hide();
        setVideoTailUi();
    }

    private void changeVoiceState() {
        if (UnLog.D) {
            UnLog.d(TAG, "changeVoiceState:" + this.isVoiceOff);
        }
        if (this.isVoiceOff) {
            setVoiceState(false);
            if (this.status == 334) {
                resumeOtherVoice();
            }
        } else {
            setVoiceState(true);
            if (this.status == 334) {
                pauseOtherVoice();
            }
            if (UnLog.D) {
                UnLog.d(TAG, "changeVoiceState: pauseOtherVoice");
            }
        }
        if (this.isVoiceOff) {
            this.voiceIconSmall.setBackgroundResource(R.drawable.video_player_voice_off_small);
            this.voiceIcon.setBackgroundResource(R.drawable.video_player_voice_off);
            this.bottomVoice.setBackgroundResource(this.bottomVoiceOffRes);
            return;
        }
        this.voiceIconSmall.setBackgroundResource(R.drawable.video_player_voice_on_small);
        this.voiceIcon.setBackgroundResource(R.drawable.video_player_voice_on);
        this.bottomVoice.setBackgroundResource(this.bottomVoiceOnRes);
    }

    private void handleAttrs(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.VideoPlayView);
        this.useNew = obtainStyledAttributes.getBoolean(R.styleable.VideoPlayView_useNew, false);
        obtainStyledAttributes.recycle();
    }

    private void hideErrorLayout() {
        this.mNetErrorLayout.setVisibility(8);
        this.errorLayoutSmall.setVisibility(8);
        this.currentTipState = -1;
        optEnable(true);
    }

    private void initGestureDetector() {
        this.gestureDetector = new GestureDetector(this.mContext, new GestureDetector.SimpleOnGestureListener() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.9
            {
                VideoPlayView.this = this;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (UnLog.D) {
                    UnLog.d(VideoPlayView.TAG, "onDoubleTap");
                }
                if (VideoPlayView.this.status != 334) {
                    if (VideoPlayView.this.status == 335 && VideoPlayView.this.currentTipState == -1) {
                        VideoPlayView.this.startPlay();
                        if (VideoPlayView.this.mtaListener != null) {
                            VideoPlayView.this.mtaListener.doubleClick(true);
                        }
                    }
                } else {
                    VideoPlayView.this.pausePlay();
                    if (VideoPlayView.this.mtaListener != null) {
                        VideoPlayView.this.mtaListener.doubleClick(false);
                    }
                }
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (UnLog.D) {
                    UnLog.d(VideoPlayView.TAG, "onSingleTapConfirmed");
                }
                if (VideoPlayView.this.bottomBarLayout.getVisibility() == 0) {
                    VideoPlayView.this.hide();
                } else {
                    VideoPlayView.this.show(true);
                }
                return true;
            }
        });
    }

    private void initPlayView() {
        if (this.mPlayerOptions == null && !this.useNew) {
            this.videoView = new IjkVideoView(this.mContext);
            this.mPlayerOptions = new IPlayerControl.PlayerOptions(false);
        } else {
            this.videoView = new JDVideoView(this.mContext);
        }
        this.rootLayout.addView(this.videoView, 0, new RelativeLayout.LayoutParams(-1, -1));
        this.videoView.setOnTouchListener(this);
        this.videoView.setOnPlayerStateListener(this);
        setPlayerOpts(this.mPlayerOptions);
        this.videoMtaInfoImpl = new NewVideoMtaInfoImpl(this.videoView);
    }

    private void initView(AttributeSet attributeSet) {
        handleAttrs(attributeSet);
        Context context = getContext();
        this.mContext = context;
        FrameLayout.inflate(context, R.layout.ijkandvrplayer_video_play_view, this);
        this.rootLayout = (RelativeLayout) findViewById(R.id.app_video_box);
        this.loadingLayout = (LinearLayout) findViewById(R.id.loadingLayout);
        this.liveIconIv = (ImageView) findViewById(R.id.liveIcon);
        this.topBarLayout = findViewById(R.id.app_video_top_box);
        this.bottomBarLayout = findViewById(R.id.ll_bottom_bar);
        this.titleTv = (TextView) findViewById(R.id.app_video_title);
        this.corverIv = (ImageView) findViewById(R.id.iv_corver);
        this.closeIb = (ImageButton) findViewById(R.id.app_video_finish);
        this.shareIb = (ImageButton) findViewById(R.id.app_video_share);
        this.bottomShareIcon = (TextView) findViewById(R.id.shareIcon);
        this.playIvOnBottomBar = (ImageView) findViewById(R.id.app_video_play);
        this.centerPlayIv = (ImageView) findViewById(R.id.play_icon_center);
        this.fullscreenIv = (ImageView) findViewById(R.id.app_video_fullscreen);
        this.replayIcon = (TextView) findViewById(R.id.app_video_replay_icon);
        this.voiceIcon = (TextView) findViewById(R.id.voiceIcon);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.app_video_bottom_progressbar);
        this.bottomProgressBar = progressBar;
        progressBar.setMax(1000);
        this.bottomProgressBar.setProgress(0);
        this.currentTimeTv = (TextView) findViewById(R.id.app_video_currentTime_full);
        this.endTimeTv = (TextView) findViewById(R.id.app_video_endTime_full);
        this.mSeekBar = (SeekBar) findViewById(R.id.app_video_seekBar);
        this.voiceParent = (LinearLayout) findViewById(R.id.voiceParent);
        this.bottomVoice = (ImageView) findViewById(R.id.iv_bottom_voice);
        this.bottomVoicePlaceHolder = (ImageView) findViewById(R.id.iv_bottom_voice_copy);
        this.bottomBarLayoutCopy = (FrameLayout) findViewById(R.id.fl_bottom_bar);
        Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.video_player_seek_bg);
        this.nomalSeekBarSecondProgressBg = new VideoSeekBarBgDrawable(null, Color.parseColor("#cbcbcb"), Color.parseColor("#cbcbcb"), 1000);
        VideoSeekBarBgDrawable videoSeekBarBgDrawable = new VideoSeekBarBgDrawable(null, Color.parseColor("#fd9274"), Color.parseColor("#f93841"), 1000);
        this.nomalSeekBarProgrssBg = videoSeekBarBgDrawable;
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, this.nomalSeekBarSecondProgressBg, videoSeekBarBgDrawable});
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setId(2, 16908301);
        this.mSeekBar.setProgressDrawable(layerDrawable);
        Drawable drawable2 = this.mContext.getResources().getDrawable(R.drawable.video_player_bottom_seek_bg);
        this.bottomSeekBarSecondProgressBg = new VideoSeekBarBgDrawable(null, Color.parseColor("#cbcbcb"), Color.parseColor("#cbcbcb"), 1000);
        VideoSeekBarBgDrawable videoSeekBarBgDrawable2 = new VideoSeekBarBgDrawable(null, Color.parseColor("#fd9274"), Color.parseColor("#f93841"), 1000);
        this.bottomSeekBarProgrssBg = videoSeekBarBgDrawable2;
        LayerDrawable layerDrawable2 = new LayerDrawable(new Drawable[]{drawable2, this.bottomSeekBarSecondProgressBg, videoSeekBarBgDrawable2});
        layerDrawable2.setId(0, 16908288);
        layerDrawable2.setId(1, 16908303);
        layerDrawable2.setId(2, 16908301);
        this.bottomProgressBar.setProgressDrawable(layerDrawable2);
        this.mSeekBar.setMax(1000);
        this.mSeekBar.setOnSeekBarChangeListener(this.mSeekListener);
        this.mNetErrorLayout = (LinearLayout) findViewById(R.id.errorLayout);
        this.errorTipTv = (TextView) findViewById(R.id.errorTipTv);
        this.mClickToRetry = (TextView) findViewById(R.id.retry);
        this.loadErrorIv = (ImageView) findViewById(R.id.loadErrorIv);
        this.smallTopBarLayout = findViewById(R.id.app_video_top_box_small);
        this.smallCloseIb = (ImageButton) findViewById(R.id.app_video_finish_small);
        this.voiceIconSmall = (TextView) findViewById(R.id.play_icon_voice_small);
        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.app_video_bottom_progressbar_small);
        this.bottomProgressBarSmall = progressBar2;
        progressBar2.setMax(1000);
        this.smallCloseIb.setOnClickListener(this);
        this.voiceIconSmall.setOnClickListener(this);
        this.errorLayoutSmall = (LinearLayout) findViewById(R.id.errorLayoutSmall);
        this.retryTvSmall = (TextView) findViewById(R.id.retrySmall);
        this.errorTipTvSmall = (TextView) findViewById(R.id.errorTipTvSmall);
        this.retryTvSmall.setOnClickListener(this);
        this.loadingLayoutSmall = (LinearLayout) findViewById(R.id.loadingLayoutSmall);
        TextView textView = (TextView) findViewById(R.id.app_video_back);
        this.bigBackBt = textView;
        textView.setOnClickListener(this);
        setOnTouchListener(this);
        this.topBarLayout.setOnTouchListener(this);
        this.bottomBarLayout.setOnTouchListener(this);
        this.mClickToRetry.setOnClickListener(this);
        this.playIvOnBottomBar.setOnClickListener(this);
        this.centerPlayIv.setOnClickListener(this);
        this.fullscreenIv.setOnClickListener(this);
        this.closeIb.setOnClickListener(this);
        this.shareIb.setOnClickListener(this);
        this.bottomShareIcon.setOnClickListener(this);
        this.replayIcon.setOnClickListener(this);
        this.voiceIcon.setOnClickListener(this);
        this.bottomVoice.setOnClickListener(this);
        this.orientationEventListener = new OrientationEventListener(this.mContext, 3) { // from class: com.jingdong.common.unification.video.player.VideoPlayView.2
            {
                VideoPlayView.this = this;
            }

            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i2) {
                int i3;
                if (i2 == -1) {
                    return;
                }
                if (i2 > 350 || i2 < 10) {
                    i3 = 0;
                } else if (i2 > 80 && i2 < 100) {
                    i3 = 90;
                } else if (i2 > 170 && i2 < 190) {
                    i3 = 180;
                } else if (i2 <= 260 || i2 >= 280) {
                    return;
                } else {
                    i3 = 270;
                }
                VideoPlayView.this.optOrientation(i3);
            }
        };
        optEnable(false);
        if (this.isHideControlPanl) {
            hide();
        } else {
            show(true);
        }
        this.shareIb.setVisibility(this.sharedEnable ? 0 : 8);
        this.fullScreenPortResourceId = R.drawable.un_video_screen_v_to_h;
        this.fullScreenLandResourceId = R.drawable.un_video_screen_h_to_v;
        this.bottomVoiceOnRes = R.drawable.video_player_voice_on;
        this.bottomVoiceOffRes = R.drawable.video_player_voice_off;
        initPlayView();
    }

    private void optEnable(boolean z) {
        this.mSeekBar.setEnabled(z);
        this.playIvOnBottomBar.setEnabled(z);
    }

    public void optOrientation(int i2) {
        if (this.oldOrientation == i2 || getActivity() == null) {
            return;
        }
        int requestedOrientation = getActivity().getRequestedOrientation();
        if (UnLog.D) {
            UnLog.d("h-v", i2 + "---orientation   screenOrientation--" + requestedOrientation);
        }
        this.oldOrientation = i2;
        if (i2 == 0) {
            if (requestedOrientation != 1) {
                toggleFullScreen(0);
            }
        } else if (90 == i2) {
            if (requestedOrientation != 8) {
                toggleFullScreen(2);
            }
        } else if (180 == i2) {
            if (requestedOrientation != 9) {
                toggleFullScreen(3);
            }
        } else if (requestedOrientation != 0) {
            toggleFullScreen(1);
        }
    }

    private void playVideoTail() {
        this.playPostion = 0;
        this.duration = 0;
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null) {
            newVideoMtaInfoImpl.reset();
        }
        if (!TextUtils.isEmpty(this.tailCoverUrl)) {
            setCoverUrl(this.tailCoverUrl);
        }
        this.isPlayingTail = true;
        setPlaySource(this.tailSource);
        setVideoTailUi();
        AVideoMtaListener aVideoMtaListener = this.mtaListener;
        if (aVideoMtaListener != null) {
            aVideoMtaListener.changeToVideoTail();
        }
    }

    private void progressState() {
        if (this.isLive) {
            return;
        }
        if (this.videoView.isPlaying() && this.buffProgress != 1090 && this.oldProgress == this.thisProgress && !this.isLoading && !this.isLocalVideo && !NetUtils.isNetworkAvailable()) {
            showNetErrorlayout();
        }
        this.oldProgress = this.thisProgress;
    }

    private void reTry() {
        hideErrorLayout();
        if (UnLog.D) {
            UnLog.d(TAG, "reTry showloading");
        }
        showLoading();
        if (this.isLocalVideo && !NetUtils.isNetworkAvailable()) {
            VideoPlayerThreadManager.getThreadPool().execute(new Runnable() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.5
                {
                    VideoPlayView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    VideoPlayView.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.5.1
                        {
                            AnonymousClass5.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            VideoPlayView.this.showNetErrorlayout();
                        }
                    });
                }
            });
            return;
        }
        if (UnLog.D) {
            UnLog.d(TAG, "playPostion:" + this.playPostion);
        }
        this.videoView.suspend();
        this.videoView.initRenders();
        this.videoView.seekTo(this.playPostion);
    }

    private void setPointViewParams() {
        View view = this.mPointView;
        if (view != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            if (this.isHideBottomBar) {
                layoutParams.bottomMargin = DpiUtil.dip2px(this.mContext, 20.0f);
            } else {
                layoutParams.bottomMargin = DpiUtil.dip2px(this.mContext, 50.0f);
            }
            this.mPointView.setLayoutParams(layoutParams);
        }
    }

    private void setScreenOff() {
        Activity activity;
        if (this.isNeedKeepScreenOn && this.isScreenOn && (activity = getActivity()) != null) {
            this.isScreenOn = false;
            activity.getWindow().clearFlags(128);
        }
    }

    private void setScreenOn() {
        Activity activity;
        if (!this.isNeedKeepScreenOn || this.isScreenOn || (activity = getActivity()) == null) {
            return;
        }
        this.isScreenOn = true;
        activity.getWindow().addFlags(128);
    }

    public void setSeekBarBgProgress(int i2, boolean z) {
        if (z) {
            this.bottomSeekBarProgrssBg.setProgress(i2);
            this.bottomProgressBar.invalidate();
            return;
        }
        this.nomalSeekBarProgrssBg.setProgress(i2);
    }

    public void setSelectPoint(int i2, boolean z) {
        List<Integer> list = this.points;
        if (list == null || list.size() == 0 || this.progrssChangeListener == null) {
            return;
        }
        if (z) {
            for (int i3 = 0; i3 < this.points.size(); i3++) {
                if (i2 >= this.points.get(i3).intValue() && i2 <= this.points.get(i3).intValue() + 1000) {
                    if (this.currentPoint != i3) {
                        this.progrssChangeListener.onProgressPointSelect(i3);
                        this.currentPoint = i3;
                        return;
                    }
                    return;
                }
            }
            return;
        }
        for (int i4 = 1; i4 < this.points.size(); i4++) {
            int i5 = i4 - 1;
            if (i2 >= this.points.get(i5).intValue() && i2 < this.points.get(i4).intValue()) {
                if (this.currentPoint != i5) {
                    this.progrssChangeListener.onProgressPointSelect(i5);
                    this.currentPoint = i5;
                    return;
                }
                return;
            } else if (i4 == this.points.size() - 1 && i2 >= this.points.get(i4).intValue()) {
                if (this.currentPoint != i4) {
                    this.progrssChangeListener.onProgressPointSelect(i4);
                    this.currentPoint = i4;
                    return;
                }
                return;
            }
        }
    }

    private void setVideoTailUi() {
        if (this.isPlayingTail) {
            View view = this.mPointView;
            if (view != null) {
                view.setVisibility(8);
            }
            int i2 = this.screenState;
            if (i2 != 0 && i2 != 2) {
                if (i2 == 1) {
                    this.voiceIconSmall.setVisibility(8);
                    this.bottomProgressBarSmall.setVisibility(8);
                    return;
                }
                return;
            }
            hideTopBar(false);
            hideBottomBar(true);
            this.mHandler.removeMessages(3);
            this.voiceIcon.setVisibility(8);
            this.bottomVoice.setVisibility(8);
            this.bottomShareIcon.setVisibility(8);
            this.bottomProgressBar.setVisibility(8);
        }
    }

    private void showLoadErrorLayout(boolean z) {
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null) {
            showLoadErrorLayout(newVideoMtaInfoImpl.getErrorCode(), z);
        }
    }

    private void showNetChangelayout() {
        if (UnLog.D) {
            UnLog.d(TAG, "showNetChangelayout hideLoading");
        }
        hideLoading();
        int i2 = this.screenState;
        if (i2 != 1 && i2 != 4 && i2 != 3) {
            this.mNetErrorLayout.setVisibility(0);
            this.errorTipTv.setText(this.mContext.getResources().getString(R.string.video_player_no_wifi));
            this.loadErrorIv.setVisibility(8);
            Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.video_player_play_icon);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.mClickToRetry.setCompoundDrawables(drawable, null, null, null);
            this.mClickToRetry.setText(this.mContext.getResources().getString(R.string.video_player_continue_play));
            this.mClickToRetry.setVisibility(0);
        } else {
            this.errorLayoutSmall.setVisibility(0);
            this.retryTvSmall.setBackgroundResource(R.drawable.video_player_play_icon_small);
            this.retryTvSmall.setEnabled(true);
            this.errorTipTvSmall.setText(this.mContext.getResources().getString(R.string.video_player_no_wifi_small));
        }
        this.currentTipState = 402;
        pausePlay();
        this.isAlreadyShowNoWifi = true;
        optEnable(false);
    }

    public void showNetErrorlayout() {
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null) {
            newVideoMtaInfoImpl.onError(1, 3);
        }
        if (UnLog.D) {
            UnLog.d(TAG, "showNetErrorlayout hideLoading");
        }
        hideLoading();
        if (this.isShowErrorLayout) {
            int i2 = this.screenState;
            if (i2 != 1 && i2 != 4 && i2 != 3) {
                this.mNetErrorLayout.setVisibility(0);
                this.errorTipTv.setText(this.mContext.getResources().getString(R.string.video_player_net_error));
                this.loadErrorIv.setVisibility(8);
                Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.video_player_fresh_icon);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                this.mClickToRetry.setCompoundDrawables(drawable, null, null, null);
                this.mClickToRetry.setText(this.mContext.getResources().getString(R.string.video_player_net_error_small));
                this.mClickToRetry.setVisibility(0);
            } else {
                this.errorLayoutSmall.setVisibility(0);
                this.retryTvSmall.setBackgroundResource(R.drawable.video_player_retry_small);
                this.retryTvSmall.setEnabled(true);
                this.errorTipTvSmall.setText(this.mContext.getResources().getString(R.string.video_player_net_error_small));
            }
        }
        this.currentTipState = 400;
        pausePlay();
        optEnable(false);
    }

    public long syncProgress() {
        if (this.currentTipState != -1) {
            pausePlay();
        }
        boolean z = this.isVoiceOff;
        if (!z && this.status == 334 && this.pauseCount < 8) {
            pauseOtherVoice();
        } else if (z && this.status == 334 && this.resumeCount < 8) {
            resumeOtherVoice();
        }
        int currentPosition = this.videoView.getCurrentPosition();
        int duration = getDuration();
        SeekBar seekBar = this.mSeekBar;
        if (seekBar != null) {
            if (duration > 0) {
                int i2 = (int) ((currentPosition * 1000.0f) / duration);
                seekBar.setProgress(i2);
                this.nomalSeekBarProgrssBg.setDuration(duration);
                setSeekBarBgProgress(i2, false);
                setSelectPoint(currentPosition, true);
                if (i2 == 0) {
                    this.mSeekBar.invalidate();
                }
                IProgrssChangeListener iProgrssChangeListener = this.progrssChangeListener;
                if (iProgrssChangeListener != null) {
                    iProgrssChangeListener.onProgressChange(i2, 1000);
                }
                if (this.isShowBottomProgressBar) {
                    this.bottomProgressBar.setProgress(i2);
                    this.bottomSeekBarProgrssBg.setDuration(duration);
                    setSeekBarBgProgress(i2, true);
                    setSelectPoint(currentPosition, true);
                    if (i2 == 0) {
                        this.bottomProgressBar.invalidate();
                    }
                }
                if (this.screenState == 1) {
                    this.bottomProgressBarSmall.setProgress(i2);
                }
                this.thisProgress = currentPosition;
                if (currentPosition != 0 && this.status != 336) {
                    this.playPostion = currentPosition;
                }
            }
            int bufferPercentage = this.videoView.getBufferPercentage() * 10;
            this.buffProgress = bufferPercentage;
            this.mSeekBar.setSecondaryProgress(bufferPercentage);
            this.nomalSeekBarSecondProgressBg.setProgress(this.buffProgress);
            if (this.isShowBottomProgressBar) {
                this.bottomProgressBar.setSecondaryProgress(this.buffProgress);
                this.bottomSeekBarSecondProgressBg.setProgress(this.buffProgress);
            }
            if (this.screenState == 1) {
                this.bottomProgressBarSmall.setSecondaryProgress(this.buffProgress);
            }
        }
        ItemVideoPlayerController itemVideoPlayerController = this.itemCtrl;
        if (itemVideoPlayerController != null) {
            itemVideoPlayerController.setProgress(duration, this.playPostion);
        }
        progressState();
        this.currentTimeTv.setText(VideoPlayUtil.generateTime(currentPosition));
        this.endTimeTv.setText(VideoPlayUtil.generateTime(duration));
        return currentPosition;
    }

    private void toggleFullScreen(int i2) {
        if (getActivity() == null) {
            return;
        }
        int requestedOrientation = getActivity().getRequestedOrientation();
        if ((i2 == -1 && (requestedOrientation == 0 || requestedOrientation == 8)) || i2 == 0 || i2 == 3) {
            if (i2 == 3) {
                getActivity().setRequestedOrientation(9);
            } else {
                getActivity().setRequestedOrientation(1);
            }
            this.isLandScape = false;
            if (this.sharedEnable && this.shareInfo != null) {
                this.shareIb.setVisibility(0);
            }
            this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.7
                {
                    VideoPlayView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    VideoPlayerUtils.setActivityNotFullScreen(VideoPlayView.this.videoView);
                }
            }, 300L);
        } else if ((i2 == -1 && (requestedOrientation == 1 || requestedOrientation == 9)) || i2 == 1 || i2 == 2) {
            if (i2 == 2) {
                getActivity().setRequestedOrientation(8);
            } else {
                getActivity().setRequestedOrientation(0);
            }
            this.isLandScape = true;
            if (this.jumpFrom == 1) {
                PlayerReportInvoke playerReport = JDPlayerSdk.getPlayerReport();
                Context context = this.mContext;
                playerReport.onMtaReportClick(context, "CommentsShare_VideoFullScreen", context.getClass().getName());
            }
            this.shareIb.setVisibility(4);
            this.mHandler.postDelayed(this.showRunnable, 300L);
        }
        AVideoMtaListener aVideoMtaListener = this.mtaListener;
        if (aVideoMtaListener != null) {
            aVideoMtaListener.clickScreen(this.isLandScape);
        }
        updateFullScreenButton();
    }

    private void updateFullScreenButton() {
        if (getActivity() == null) {
            return;
        }
        if (getActivity().getRequestedOrientation() == 0) {
            this.fullscreenIv.setBackgroundResource(this.fullScreenLandResourceId);
        } else {
            this.fullscreenIv.setBackgroundResource(this.fullScreenPortResourceId);
        }
    }

    private void updatePausePlay() {
        if (this.status == 334) {
            this.playIvOnBottomBar.setBackgroundResource(R.drawable.vd_pause_video);
            this.playIvOnBottomBar.setContentDescription(getResources().getString(R.string.un_video_screen_pause));
            return;
        }
        this.playIvOnBottomBar.setBackgroundResource(R.drawable.vd_play_video);
        this.playIvOnBottomBar.setContentDescription(getResources().getString(R.string.un_video_screen_start));
    }

    public void addViewAlignVideoRightEnd(View view, RelativeLayout.LayoutParams layoutParams) {
        if (view == null) {
            return;
        }
        if (layoutParams == null) {
            layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        }
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.videoView.getLayoutParams();
        layoutParams2.height = -2;
        layoutParams2.width = -2;
        int i2 = R.id.video_view;
        layoutParams.addRule(19, i2);
        layoutParams.addRule(8, i2);
        this.rootLayout.addView(view, layoutParams);
    }

    public VideoPlayView changeToScreen(int i2) {
        this.screenState = i2;
        if (i2 == 0) {
            changeToNormalUI();
        } else if (i2 == 1) {
            changeToSmallUI();
        } else if (i2 == 2) {
            changeToBigUI();
        } else if (i2 == 3) {
            changeToItemUI();
        } else if (i2 == 4) {
            changeToNoUI();
        }
        this.beforeScreenState = this.screenState;
        return this;
    }

    public void enableItemVideoTime(boolean z) {
        this.enableItemViewTime = z;
        ItemVideoPlayerController itemVideoPlayerController = this.itemCtrl;
        if (itemVideoPlayerController != null) {
            itemVideoPlayerController.enableTimeDisplay(z);
        }
    }

    public Activity getActivity() {
        Context context = this.mContext;
        if (context == null || !(context instanceof Activity)) {
            return null;
        }
        return (Activity) context;
    }

    public ImageView getBarPlayerView() {
        return this.playIvOnBottomBar;
    }

    public View getBottomBarView() {
        return this.bottomBarLayout;
    }

    public int getBufferPercentage() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer != null) {
            return iJDVideoPlayer.getBufferPercentage();
        }
        return -1;
    }

    public ImageView getCenterPlayerView() {
        return this.centerPlayIv;
    }

    public ImageView getCloseView() {
        return this.closeIb;
    }

    public int getCurrentPosition() {
        if (!this.isLive) {
            this.currentPosition = this.videoView.getCurrentPosition();
        } else {
            this.currentPosition = -1;
        }
        return this.currentPosition;
    }

    public int getDuration() {
        int duration = this.videoView.getDuration();
        this.duration = duration;
        if (!this.isPlayingTail) {
            this.normalVideoDuration = duration;
        }
        return duration;
    }

    public ImageView getFullScreenView() {
        return this.fullscreenIv;
    }

    public int getNormalVideoDuration() {
        return this.normalVideoDuration;
    }

    public ImageView getShareView() {
        return this.shareIb;
    }

    public View getTopBarView() {
        return this.topBarLayout;
    }

    public int getVideoHeight() {
        int i2;
        IjkMediaPlayer ijkMediaPlayer;
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null || (ijkMediaPlayer = iJDVideoPlayer.getIjkMediaPlayer()) == null) {
            i2 = 0;
        } else {
            int i3 = this.degree;
            if (i3 != 90 && i3 != 270) {
                i2 = ijkMediaPlayer.getVideoHeight();
            } else {
                i2 = ijkMediaPlayer.getVideoWidth();
            }
        }
        return i2 == 0 ? this.coverBitmapHeight : i2;
    }

    public NewVideoMtaInfoImpl getVideoMta() {
        return this.videoMtaInfoImpl;
    }

    public int getVideoState() {
        return this.status;
    }

    public String getVideoUrl() {
        return this.videoPath;
    }

    public int getVideoWidth() {
        int i2;
        IjkMediaPlayer ijkMediaPlayer;
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer == null || (ijkMediaPlayer = iJDVideoPlayer.getIjkMediaPlayer()) == null) {
            i2 = 0;
        } else {
            int i3 = this.degree;
            if (i3 != 90 && i3 != 270) {
                i2 = ijkMediaPlayer.getVideoWidth();
            } else {
                i2 = ijkMediaPlayer.getVideoHeight();
            }
        }
        return i2 == 0 ? this.coverBitmapWidth : i2;
    }

    public RelativeLayout.LayoutParams getVoiceParentLP() {
        return (RelativeLayout.LayoutParams) this.voiceParent.getLayoutParams();
    }

    public void hide() {
        if (UnLog.D) {
            UnLog.d(TAG, ExposureRvUtils.HIDE);
        }
        hide(true);
    }

    public VideoPlayView hideAllUI() {
        this.isHideControlPanl = true;
        this.isHideTopBar = true;
        this.isHideBottomBar = true;
        this.isHideCenterPlayer = true;
        this.isHideRetryBt = true;
        this.topBarLayout.setVisibility(8);
        this.bottomBarLayout.setVisibility(8);
        this.bottomBarLayoutCopy.setVisibility(8);
        this.centerPlayIv.setVisibility(8);
        this.replayIcon.setVisibility(8);
        return this;
    }

    public VideoPlayView hideBottomBar(boolean z) {
        this.isHideBottomBar = z;
        setPointViewParams();
        this.bottomBarLayout.setVisibility(this.isHideBottomBar ? 8 : 0);
        this.bottomBarLayoutCopy.setVisibility(this.isHideBottomBar ? 8 : 0);
        return this;
    }

    public VideoPlayView hideCenterPlayer(boolean z) {
        this.isHideCenterPlayer = z;
        return this;
    }

    public VideoPlayView hideCloseBt(boolean z) {
        this.closeIb.setVisibility(z ? 8 : 0);
        return this;
    }

    public VideoPlayView hideControlPanl(boolean z) {
        this.isHideControlPanl = z;
        hideBottomBar(z);
        hideTopBar(z);
        return this;
    }

    public VideoPlayView hideFullscreen(boolean z) {
        this.fullscreenIv.setVisibility(z ? 8 : 0);
        return this;
    }

    public void hideLoading() {
        int i2 = this.screenState;
        if (i2 != 1 && i2 != 4 && i2 != 3) {
            this.loadingLayout.setVisibility(8);
        } else {
            this.loadingLayoutSmall.setVisibility(8);
        }
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null) {
            newVideoMtaInfoImpl.updateCaton();
        }
        this.mHandler.removeMessages(2);
        this.isLoading = false;
    }

    public VideoPlayView hideTitle(boolean z) {
        this.titleTv.setVisibility(z ? 8 : 0);
        return this;
    }

    public VideoPlayView hideTopBar(boolean z) {
        this.isHideTopBar = z;
        this.topBarLayout.setVisibility(z ? 8 : 0);
        return this;
    }

    public VideoPlayView isAutoChangeScreen(boolean z) {
        if (z) {
            OrientationEventListener orientationEventListener = this.orientationEventListener;
            if (orientationEventListener != null && orientationEventListener.canDetectOrientation()) {
                this.orientationEventListener.enable();
            }
        } else {
            OrientationEventListener orientationEventListener2 = this.orientationEventListener;
            if (orientationEventListener2 != null && orientationEventListener2.canDetectOrientation()) {
                this.orientationEventListener.disable();
            }
        }
        return this;
    }

    public boolean isBottomBarVisible() {
        return this.bottomBarLayout.getVisibility() == 0;
    }

    public boolean isPlaying() {
        return this.status == 334;
    }

    public boolean isPlayingTail() {
        return this.isPlayingTail;
    }

    public boolean isStop() {
        return this.status == 337;
    }

    public void itemStart() {
        ItemVideoPlayerController itemVideoPlayerController = this.itemCtrl;
        if (itemVideoPlayerController != null) {
            itemVideoPlayerController.start();
        }
        setPlaySource(this.videoPath);
    }

    public VideoPlayView loadErrorRetry(boolean z) {
        this.loadErrorRetry = z;
        return this;
    }

    public void mobileNetChangeToWifi() {
        if (UnLog.D) {
            UnLog.d(TAG, "mobileNetChangeToWifi");
        }
        int i2 = this.status;
        if (i2 == 334) {
            this.videoView.pause();
            reTry();
        } else if (i2 == 335 && this.currentTipState == -1) {
            this.isNeedAccessChangeToWifi = true;
        } else if (this.currentTipState == 402) {
            hideErrorLayout();
            this.isNeedAccessChangeToWifi = true;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AVideoViewBtClickListener aVideoViewBtClickListener;
        if (view.getId() == R.id.app_video_fullscreen) {
            AVideoViewBtClickListener aVideoViewBtClickListener2 = this.videoViewBtClickListener;
            if (aVideoViewBtClickListener2 == null || !aVideoViewBtClickListener2.clickFullScreen(this.isLandScape)) {
                toggleFullScreen(-1);
            }
        } else if (view.getId() != R.id.app_video_play && view.getId() != R.id.play_icon_center) {
            if (view.getId() == R.id.app_video_finish) {
                IViewPlayerControl iViewPlayerControl = this.iViewPlayerControl;
                if (iViewPlayerControl == null) {
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                } else {
                    iViewPlayerControl.close();
                }
                AVideoMtaListener aVideoMtaListener = this.mtaListener;
                if (aVideoMtaListener != null) {
                    aVideoMtaListener.clickClose();
                }
            } else if (view.getId() == R.id.app_video_back) {
                AVideoViewBtClickListener aVideoViewBtClickListener3 = this.videoViewBtClickListener;
                if ((aVideoViewBtClickListener3 == null || !aVideoViewBtClickListener3.bigBackClick()) && getActivity() != null) {
                    getActivity().finish();
                }
            } else if (view.getId() == R.id.app_video_finish_small) {
                AVideoViewBtClickListener aVideoViewBtClickListener4 = this.videoViewBtClickListener;
                if ((aVideoViewBtClickListener4 == null || !aVideoViewBtClickListener4.smallCloseClick()) && getActivity() != null) {
                    getActivity().finish();
                }
            } else if (view.getId() != R.id.app_video_share && view.getId() != R.id.shareIcon) {
                if (view.getId() != R.id.retry && view.getId() != R.id.retrySmall) {
                    if (view.getId() == R.id.app_video_replay_icon) {
                        this.videoView.seekTo(0);
                        startPlay();
                    } else if (view.getId() == R.id.voiceIcon || view.getId() == R.id.play_icon_voice_small || view.getId() == R.id.iv_bottom_voice) {
                        boolean z = !this.isVoiceOff;
                        this.isVoiceOff = z;
                        if (!z) {
                            this.voiceIconSmall.setVisibility(8);
                        }
                        if (!this.isVoiceFirstClicked && this.screenState != 1) {
                            if (this.bottomBarLayout.getVisibility() == 8 && !this.isVoiceIconKeepVisiInFullScreen) {
                                this.voiceIcon.setVisibility(8);
                            }
                            this.isVoiceFirstClicked = true;
                        }
                        changeVoiceState();
                        AVideoMtaListener aVideoMtaListener2 = this.mtaListener;
                        if (aVideoMtaListener2 != null) {
                            aVideoMtaListener2.clickVoice(this.isVoiceOff);
                        }
                    }
                } else if (!this.isSetSource) {
                    hideErrorLayout();
                    setPlaySource(this.videoPath, this.playPostion);
                } else if (!this.isAlreadyStartPlay) {
                    hideErrorLayout();
                    startPlay();
                } else {
                    reTry();
                }
            } else {
                AVideoMtaListener aVideoMtaListener3 = this.mtaListener;
                if (aVideoMtaListener3 != null) {
                    aVideoMtaListener3.clickShare();
                }
                if ((!this.sharedEnable && !this.bottomSharedEnable) || this.shareInfo == null || getActivity() == null) {
                    return;
                }
                ShareUtil.panel(getActivity(), this.shareInfo);
            }
        } else if (view.getId() == R.id.play_icon_center && (aVideoViewBtClickListener = this.videoViewBtClickListener) != null && aVideoViewBtClickListener.centerPlayClick()) {
        } else {
            if (this.videoView.isPlaying()) {
                pausePlay();
                if (this.jumpFrom == 1) {
                    PlayerReportInvoke playerReport = JDPlayerSdk.getPlayerReport();
                    Context context = this.mContext;
                    playerReport.onMtaReportClick(context, "CommentsShare_VideoPause", context.getClass().getName());
                }
                AVideoMtaListener aVideoMtaListener4 = this.mtaListener;
                if (aVideoMtaListener4 != null) {
                    aVideoMtaListener4.clickPauseOrPlay(false);
                }
                AVideoViewBtClickListener aVideoViewBtClickListener5 = this.videoViewBtClickListener;
                if (aVideoViewBtClickListener5 != null) {
                    aVideoViewBtClickListener5.pauseOrPlayClick(true);
                }
            } else if (VideoPlayUtil.isMobileNet() && !this.isAlreadyShowNoWifi && this.isNeedJudgeNetOnStart) {
                this.centerPlayIv.setVisibility(8);
                showNetChangelayout();
            } else {
                startPlay();
                AVideoMtaListener aVideoMtaListener5 = this.mtaListener;
                if (aVideoMtaListener5 != null) {
                    aVideoMtaListener5.clickPauseOrPlay(true);
                }
                AVideoViewBtClickListener aVideoViewBtClickListener6 = this.videoViewBtClickListener;
                if (aVideoViewBtClickListener6 != null) {
                    aVideoViewBtClickListener6.pauseOrPlayClick(false);
                }
            }
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public void onCompletion() {
        setScreenOff();
        if (this.isLive) {
            return;
        }
        if (UnLog.D) {
            UnLog.d(TAG, "onCompletion");
        }
        this.playPostion = getDuration();
        this.status = 336;
        this.mHandler.removeMessages(4);
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null) {
            newVideoMtaInfoImpl.upload(this.mContext);
        }
        if (!this.isPlayingTail && NetUtils.isNetworkAvailable() && !TextUtils.isEmpty(this.tailSource)) {
            playVideoTail();
            return;
        }
        AVideoPlayStateListener aVideoPlayStateListener = this.onPlayerStateListener;
        if (aVideoPlayStateListener != null) {
            aVideoPlayStateListener.onCompletion();
            if (this.onPlayerStateListener.onCustomCompletion()) {
                return;
            }
        }
        this.currentPosition = 0;
        this.videoView.seekTo(0);
        show(false);
        hideLoading();
        if (UnLog.D) {
            UnLog.d(TAG, "isHideCenterPlayer:" + this.isHideCenterPlayer);
        }
        if (this.isHideCenterPlayer) {
            this.centerPlayIv.setVisibility(8);
            if (this.isHideRetryBt) {
                this.replayIcon.setVisibility(8);
            } else {
                this.replayIcon.setVisibility(0);
            }
        } else {
            this.centerPlayIv.setVisibility(0);
            this.replayIcon.setVisibility(8);
        }
        ItemVideoPlayerController itemVideoPlayerController = this.itemCtrl;
        if (itemVideoPlayerController != null) {
            itemVideoPlayerController.completion(this.duration);
        }
        updatePausePlay();
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public void onCreatePlayer() {
        AVideoPlayStateListener aVideoPlayStateListener = this.onPlayerStateListener;
        if (aVideoPlayStateListener != null) {
            aVideoPlayStateListener.onCreatePlayer();
        }
        if (UnLog.D) {
            UnLog.d(TAG, "onCreatePlayer");
        }
    }

    public void onDestroy() {
        if (this.isDestory) {
            return;
        }
        this.isDestory = true;
        setScreenOff();
        OrientationEventListener orientationEventListener = this.orientationEventListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
        if (this.status != 336 && this.currentTipState != 401) {
            this.videoMtaInfoImpl.upload(this.mContext);
        }
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(3);
        this.mHandler.removeMessages(3);
        this.mHandler.removeMessages(4);
        this.mHandler.removeCallbacksAndMessages(null);
        VideoPlayUtil.clearAnimationCache();
        releaseInThread();
        resumeOtherVoice();
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public boolean onError(int i2, int i3) {
        if (UnLog.D) {
            UnLog.d(TAG, "onError frameworkErr:" + i2 + ",implErr:" + i3);
        }
        this.status = 331;
        AVideoPlayStateListener aVideoPlayStateListener = this.onPlayerStateListener;
        if (aVideoPlayStateListener != null ? aVideoPlayStateListener.onError(i2, i3) : false) {
            return true;
        }
        if (!this.isLocalVideo && !NetUtils.isNetworkAvailable()) {
            showNetErrorlayout();
        } else {
            showLoadErrorLayout(i2, true);
        }
        ItemVideoPlayerController itemVideoPlayerController = this.itemCtrl;
        if (itemVideoPlayerController != null) {
            itemVideoPlayerController.error();
        }
        return true;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public boolean onInfo(int i2, int i3) {
        if (UnLog.D) {
            UnLog.d(TAG, "onInfo mediaInfo:" + i2);
        }
        if (i2 == 3) {
            int i4 = this.status;
            if (i4 == 335) {
                postDelayed(new Runnable() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.10
                    {
                        VideoPlayView.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        VideoPlayView.this.pausePlay();
                    }
                }, 100L);
            } else if (i4 == 337) {
                postDelayed(new Runnable() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.11
                    {
                        VideoPlayView.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        VideoPlayView.this.stopPlay();
                    }
                }, 100L);
            } else {
                hideLoading();
                this.corverIv.setVisibility(8);
                this.status = 334;
                this.centerPlayIv.setVisibility(8);
                this.mHandler.sendEmptyMessage(1);
                if (this.currentTipState != -1) {
                    pausePlay();
                } else if (this.isNeedJudgeNetOnStart && VideoPlayUtil.isMobileNet() && !this.isAlreadyShowNoWifi) {
                    if (UnLog.D) {
                        UnLog.d(TAG, "onInfo show wifi");
                    }
                    showNetChangelayout();
                } else {
                    changeVoiceState();
                    setScreenOn();
                }
                updatePausePlay();
                NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
                if (newVideoMtaInfoImpl != null) {
                    newVideoMtaInfoImpl.updateInitTime(-1L, System.currentTimeMillis());
                }
                this.mHandler.sendEmptyMessage(4);
                if (UnLog.D) {
                    UnLog.d(TAG, "\u89c6\u9891\u6e32\u67d3\u7684\u5f00\u59cb");
                }
            }
        } else if (i2 != 701) {
            if (i2 == 702) {
                hideLoading();
            } else if (i2 == 10001) {
                this.degree = i3;
            } else if (i2 == 10002) {
                int i5 = this.status;
                if (i5 == 335) {
                    postDelayed(new Runnable() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.12
                        {
                            VideoPlayView.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            VideoPlayView.this.pausePlay();
                        }
                    }, 100L);
                } else if (i5 == 337) {
                    postDelayed(new Runnable() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.13
                        {
                            VideoPlayView.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            VideoPlayView.this.stopPlay();
                        }
                    }, 100L);
                }
            }
        } else if (this.status != 336) {
            showLoading();
        }
        AVideoPlayStateListener aVideoPlayStateListener = this.onPlayerStateListener;
        if (aVideoPlayStateListener != null) {
            return aVideoPlayStateListener.onInfo(i2, i3);
        }
        return true;
    }

    public void onPause() {
        NewVideoMtaInfoImpl newVideoMtaInfoImpl;
        if (UnLog.D) {
            UnLog.d(TAG, "onPause: isPlay" + this.videoView.isPlaying());
        }
        int i2 = this.status == 334 ? 1 : 0;
        this.bgState = i2;
        if (i2 == 1) {
            pausePlay();
        }
        if (this.status == 336 || this.currentTipState == 401 || (newVideoMtaInfoImpl = this.videoMtaInfoImpl) == null) {
            return;
        }
        newVideoMtaInfoImpl.upload(this.mContext);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public void onPrepared(long j2) {
        if (UnLog.D) {
            UnLog.d(TAG, "onPrepared");
        }
        if (this.isDestory) {
            return;
        }
        int i2 = 0;
        if (this.mSeekBar.getProgress() > 0 && this.isFromComment) {
            i2 = (this.mSeekBar.getProgress() * getDuration()) / 1000;
        }
        if (getDuration() > 0) {
            if (i2 > 0) {
                this.currentTimeTv.setText(VideoPlayUtil.generateTime(i2));
            }
            this.endTimeTv.setText(VideoPlayUtil.generateTime(getDuration()));
        }
        if (i2 > 0) {
            seekToPosition(i2);
        }
        optEnable(true);
        AVideoPlayStateListener aVideoPlayStateListener = this.onPlayerStateListener;
        if (aVideoPlayStateListener != null) {
            aVideoPlayStateListener.onPrepared(j2);
        }
        this.status = 333;
        if (UnLog.D) {
            UnLog.d(TAG, "statusChange PLAYING hideLoading");
        }
        if (this.isLive) {
            show(true);
        }
        this.mHandler.removeMessages(3);
        if (!this.isPlayingTail) {
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(3), 3000L);
        }
        IjkMediaPlayer ijkMediaPlayer = this.videoView.getIjkMediaPlayer();
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.setLooping(this.isLoopPlay);
        }
    }

    public void onResume() {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity;
        if (UnLog.D) {
            UnLog.d(TAG, "onResume: bgState" + this.bgState);
        }
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null && (jDVideoPlayerMtaEntity = newVideoMtaInfoImpl.mtaEntity) != null) {
            jDVideoPlayerMtaEntity.isAlradyUploadMta = false;
        }
        if (this.bgState != -1) {
            this.videoView.initRenders();
            if (this.bgState == 1) {
                startPlay();
            }
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public void onSeekComplete() {
        if (UnLog.D) {
            UnLog.d(TAG, "onSeekComplete position:" + this.videoView.getCurrentPosition());
        }
        AVideoPlayStateListener aVideoPlayStateListener = this.onPlayerStateListener;
        if (aVideoPlayStateListener != null) {
            aVideoPlayStateListener.onSeekComplete();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();
        if (id == R.id.app_video_top_box || id == R.id.ll_bottom_bar) {
            return true;
        }
        IVideoViewOnTouchListener iVideoViewOnTouchListener = this.videoViewOnTouchListener;
        if (iVideoViewOnTouchListener != null) {
            return iVideoViewOnTouchListener.onTouch(view, motionEvent);
        }
        if (this.isPlayingTail) {
            return true;
        }
        if (this.isLive) {
            if (motionEvent.getAction() == 0) {
                if (this.bottomBarLayout.getVisibility() == 0) {
                    this.mHandler.removeMessages(3);
                    hide();
                } else {
                    show(true);
                }
            }
        } else {
            if (this.gestureDetector == null) {
                initGestureDetector();
            }
            this.gestureDetector.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void pauseOtherVoice() {
        Context context = this.mContext;
        if (context == null) {
            return;
        }
        VideoPlayUtil.muteAudioFocus(context, true);
        this.resumeCount = 0;
        this.pauseCount++;
    }

    public VideoPlayView pausePlay() {
        if (UnLog.D) {
            UnLog.d(TAG, "pausePlay: " + this.status + LangUtils.SINGLE_SPACE + isPlaying());
        }
        this.mHandler.removeMessages(4);
        hideLoading();
        setScreenOff();
        if (this.status == 335) {
            return this;
        }
        this.status = 335;
        getCurrentPosition();
        this.videoView.pause();
        if (this.isResumeBgMusicOnPause && this.currentTipState == -1) {
            resumeOtherVoice();
        }
        if (!this.isHideCenterPlayer && this.currentTipState == -1) {
            this.centerPlayIv.setVisibility(0);
        } else {
            this.centerPlayIv.setVisibility(8);
        }
        updatePausePlay();
        return this;
    }

    public void releaseInThread() {
        try {
            this.videoView.releaseInThread(true);
        } catch (Exception e2) {
            if (UnLog.E) {
                e2.printStackTrace();
            }
        }
    }

    public void resetState() {
        if (this.currentTipState != -1) {
            hideErrorLayout();
        }
        pausePlay();
    }

    public void resumeOtherVoice() {
        Context context = this.mContext;
        if (context == null) {
            return;
        }
        VideoPlayUtil.muteAudioFocus(context, false);
        this.resumeCount++;
        this.pauseCount = 0;
    }

    public VideoPlayView seekToPosition(int i2) {
        if (i2 >= 0) {
            this.playPostion = i2;
            int duration = getDuration();
            this.duration = duration;
            if (duration > 0) {
                int i3 = (this.playPostion * 1000) / duration;
                this.mSeekBar.setProgress(i3);
                setSeekBarBgProgress(i3, false);
                if (this.isShowBottomProgressBar) {
                    this.bottomProgressBar.setProgress(i3);
                    setSeekBarBgProgress(i3, true);
                }
            }
            this.videoView.seekTo(i2);
        }
        return this;
    }

    public VideoPlayView setAutoPlay(boolean z) {
        this.isAutoPlay = z;
        if (!z && this.videoView.isPlaying()) {
            pausePlay();
        }
        return this;
    }

    public VideoPlayView setBottomProgressBarVisible(boolean z) {
        if (z) {
            this.bottomProgressBar.setVisibility(0);
        } else {
            this.bottomProgressBar.setVisibility(8);
        }
        return this;
    }

    public VideoPlayView setBottomSharedEnable(boolean z) {
        this.bottomSharedEnable = z;
        this.bottomShareIcon.setVisibility(z ? 0 : 8);
        return this;
    }

    public VideoPlayView setBottomSharedState(boolean z) {
        this.bottomSharedEnable = z;
        return this;
    }

    public VideoPlayView setBottomVioceRes(int i2, int i3) {
        if (i2 != 0) {
            this.bottomVoiceOnRes = i2;
        }
        if (i3 != 0) {
            this.bottomVoiceOffRes = i3;
        }
        return this;
    }

    public VideoPlayView setColseBtInfo(Drawable drawable, int i2, int i3) {
        ImageButton imageButton = this.closeIb;
        if (imageButton != null) {
            if (drawable != null) {
                imageButton.setBackgroundDrawable(drawable);
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.closeIb.getLayoutParams();
            layoutParams.rightMargin = i3;
            layoutParams.topMargin = i2;
            this.closeIb.setLayoutParams(layoutParams);
        }
        return this;
    }

    public VideoPlayView setCoverBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            this.corverIv.setVisibility(0);
            this.corverIv.setImageBitmap(bitmap);
        }
        return this;
    }

    public VideoPlayView setCoverImageScaleType(ImageView.ScaleType scaleType) {
        ImageView imageView = this.corverIv;
        if (imageView != null) {
            imageView.setScaleType(scaleType);
        }
        return this;
    }

    public VideoPlayView setCoverUrl(String str) {
        this.corverIv.setVisibility(0);
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.showImageOnLoading(getContext().getResources().getDrawable(17170445));
        JDImageUtils.displayImage(str, this.corverIv, jDDisplayImageOptions, true, new JDSimpleImageLoadingListener() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.6
            {
                VideoPlayView.this = this;
            }

            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                VideoPlayView.this.coverBitmapWidth = bitmap.getWidth();
                VideoPlayView.this.coverBitmapHeight = bitmap.getHeight();
            }
        }, null);
        return this;
    }

    public void setCtrlViewListener(IVideoPlayerCtrlViewListener iVideoPlayerCtrlViewListener) {
        this.ctrlViewListener = iVideoPlayerCtrlViewListener;
    }

    public VideoPlayView setFlowToastAlert(boolean z) {
        this.flowToastAlert = z;
        return this;
    }

    public void setFullScreen() {
        if (this.isLandScape) {
            this.mHandler.postDelayed(this.showRunnable, 300L);
        }
    }

    public VideoPlayView setFullScreenBtResource(int i2, int i3) {
        if (i2 != 0) {
            this.fullScreenLandResourceId = i3;
        }
        if (i3 != 0) {
            this.fullScreenPortResourceId = i2;
        }
        updateFullScreenButton();
        return this;
    }

    public VideoPlayView setHideRetryBt(boolean z) {
        this.isHideRetryBt = z;
        return this;
    }

    public VideoPlayView setIsLocalVideo(boolean z) {
        this.isLocalVideo = z;
        return this;
    }

    public VideoPlayView setItemDuration(int i2) {
        this.itemCtrl.setProgress(i2 * 1000, 0L);
        return this;
    }

    public VideoPlayView setItemVideoClickListener(OnItemVideoPlayerClick onItemVideoPlayerClick) {
        ItemVideoPlayerController itemVideoPlayerController = this.itemCtrl;
        if (itemVideoPlayerController != null) {
            itemVideoPlayerController.setOnItemVideoPlayerClick(onItemVideoPlayerClick);
        }
        return this;
    }

    @Override // com.jingdong.common.unification.video.player.IJDVideoPlayerBiz
    public VideoPlayView setJumpFrom(int i2) {
        this.jumpFrom = i2;
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null) {
            newVideoMtaInfoImpl.setJumpFrom(i2);
        }
        return this;
    }

    public VideoPlayView setKeepBottomProgressBarVisi(boolean z) {
        this.isKeepBottomProgressBarVisi = z;
        return this;
    }

    public VideoPlayView setLive(boolean z) {
        this.isLive = z;
        if (z) {
            hide();
            this.centerPlayIv.setVisibility(8);
            this.playIvOnBottomBar.setVisibility(8);
            this.endTimeTv.setVisibility(8);
            this.mSeekBar.setVisibility(8);
            this.bottomBarLayout.setBackgroundResource(R.drawable.uni_video_live_bottom_bg);
        }
        this.isAlreadyShowNoWifi = true;
        return this;
    }

    public VideoPlayView setLiveIcon(Bitmap bitmap) {
        ImageView imageView = this.liveIconIv;
        if (imageView != null && bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        return this;
    }

    public void setLoadUrlTime(long j2) {
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null) {
            newVideoMtaInfoImpl.setLoadUrlTime(j2);
        }
    }

    public void setLoadingView(View view) {
        if (view != null) {
            this.loadingLayout.setBackgroundColor(0);
            this.loadingLayout.removeAllViews();
            this.loadingLayout.addView(view);
        }
    }

    public void setLoop(boolean z) {
        this.isLoopPlay = z;
    }

    @Override // com.jingdong.common.unification.video.player.IJDVideoPlayerBiz
    public VideoPlayView setMark(String str) {
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null) {
            newVideoMtaInfoImpl.setMark(str);
        }
        return this;
    }

    public VideoPlayView setMtaListener(AVideoMtaListener aVideoMtaListener) {
        this.mtaListener = aVideoMtaListener;
        return this;
    }

    public VideoPlayView setNeedJudgeNetOnStart(boolean z) {
        this.isNeedJudgeNetOnStart = z;
        return this;
    }

    public VideoPlayView setNeedKeepScreenOn(boolean z) {
        this.isNeedKeepScreenOn = z;
        return this;
    }

    public VideoPlayView setOnPlayerStateListener(AVideoPlayStateListener aVideoPlayStateListener) {
        this.onPlayerStateListener = aVideoPlayStateListener;
        return this;
    }

    public VideoPlayView setPlaySource(String str) {
        return setPlaySource(str, 0);
    }

    public VideoPlayView setPlaySourceWithoutPlay(String str) {
        this.isAutoPlay = false;
        this.videoView.getPlayerOptions().setStartOnPrepared(false);
        setPlaySource(str);
        if (this.isHideCenterPlayer) {
            this.centerPlayIv.setVisibility(8);
        } else {
            this.centerPlayIv.setVisibility(0);
        }
        this.isAlreadyStartPlay = false;
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null) {
            newVideoMtaInfoImpl.updateStartTime(0L);
        }
        return this;
    }

    public void setPlayerOpts(IPlayerControl.PlayerOptions playerOptions) {
        if (playerOptions != null) {
            this.mPlayerOptions = playerOptions;
        }
        IPlayerControl.PlayerOptions playerOptions2 = this.mPlayerOptions;
        if (playerOptions2 != null) {
            playerOptions2.setAspectRatio(0);
            this.mPlayerOptions.setIsRequestAudioFocus(false);
            this.mPlayerOptions.addCustomOption(2, "skip_loop_filter", 0L);
            this.videoView.setPlayerOptions(this.mPlayerOptions);
        }
    }

    public void setPointPositions(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        this.points = list;
        this.nomalSeekBarProgrssBg.setPoints(list);
        this.bottomSeekBarProgrssBg.setPoints(this.points);
    }

    public void setPointView(View view) {
        if (view == null || view.getParent() != null) {
            return;
        }
        this.mPointView = view;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(12);
        layoutParams.addRule(14);
        if (this.isHideBottomBar) {
            layoutParams.bottomMargin = DpiUtil.dip2px(this.mContext, 20.0f);
        } else {
            layoutParams.bottomMargin = DpiUtil.dip2px(this.mContext, 50.0f);
        }
        this.rootLayout.addView(view, layoutParams);
    }

    public void setProgrssChangeListener(IProgrssChangeListener iProgrssChangeListener) {
        this.progrssChangeListener = iProgrssChangeListener;
    }

    public VideoPlayView setResumeBgMusicOnPause(boolean z) {
        this.isResumeBgMusicOnPause = z;
        return this;
    }

    public VideoPlayView setScaleType(String str) {
        if ("fitParent".equals(str)) {
            this.videoView.setAspectRatio(0);
        } else if ("fillParent".equals(str)) {
            this.videoView.setAspectRatio(1);
        } else if ("wrapContent".equals(str)) {
            this.videoView.setAspectRatio(2);
        } else if ("fitXY".equals(str)) {
            this.videoView.setAspectRatio(3);
        } else if ("16:9".equals(str)) {
            this.videoView.setAspectRatio(4);
        } else if ("4:3".equals(str)) {
            this.videoView.setAspectRatio(5);
        }
        return this;
    }

    public void setScreenState(boolean z) {
        if (z) {
            toggleFullScreen(1);
        } else {
            toggleFullScreen(0);
        }
    }

    @Override // com.jingdong.common.unification.video.player.IJDVideoPlayerBiz
    public void setShareInfo(ShareInfo shareInfo) {
        this.shareInfo = shareInfo;
    }

    public VideoPlayView setSharedEnable(boolean z) {
        this.sharedEnable = z;
        this.shareIb.setVisibility(z ? 0 : 8);
        return this;
    }

    public VideoPlayView setShowBottomProgressBar(boolean z) {
        this.isShowBottomProgressBar = z;
        if (!z) {
            this.bottomProgressBar.setVisibility(8);
        }
        return this;
    }

    public VideoPlayView setShowBottomVoice(boolean z, boolean z2) {
        this.isVoiceOff = z2;
        if (z) {
            this.bottomVoice.setVisibility(0);
            this.bottomVoicePlaceHolder.setVisibility(4);
            this.bottomBarLayoutCopy.setVisibility(0);
            this.voiceIcon.setVisibility(8);
        } else {
            this.bottomVoice.setVisibility(8);
            this.bottomVoicePlaceHolder.setVisibility(8);
            this.bottomBarLayoutCopy.setVisibility(8);
        }
        if (this.isVoiceOff) {
            this.bottomVoice.setBackgroundResource(this.bottomVoiceOffRes);
            this.voiceIconSmall.setBackgroundResource(R.drawable.video_player_voice_off_small);
        } else {
            this.bottomVoice.setBackgroundResource(this.bottomVoiceOnRes);
            this.voiceIconSmall.setBackgroundResource(R.drawable.video_player_voice_on_small);
            this.isVoiceFirstClicked = true;
        }
        this.voiceIconState = z ? 1 : -1;
        return this;
    }

    public VideoPlayView setShowErrorLayout(boolean z) {
        this.isShowErrorLayout = z;
        return this;
    }

    public VideoPlayView setShowVoice(boolean z, boolean z2) {
        this.voiceIconState = !z ? -1 : 0;
        this.isVoiceOff = z2;
        if (z) {
            this.voiceIcon.setVisibility(0);
            this.bottomVoice.setVisibility(8);
            this.bottomVoicePlaceHolder.setVisibility(8);
            this.bottomBarLayoutCopy.setVisibility(8);
        } else {
            this.voiceIcon.setVisibility(8);
        }
        if (this.isVoiceOff) {
            this.voiceIconSmall.setBackgroundResource(R.drawable.video_player_voice_off_small);
            this.voiceIcon.setBackgroundResource(R.drawable.video_player_voice_off);
        } else {
            this.voiceIconSmall.setBackgroundResource(R.drawable.video_player_voice_on_small);
            this.voiceIcon.setBackgroundResource(R.drawable.video_player_voice_on);
            this.isVoiceFirstClicked = true;
        }
        return this;
    }

    @Override // com.jingdong.common.unification.video.player.IJDVideoPlayerBiz
    public VideoPlayView setSku(String str) {
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null) {
            newVideoMtaInfoImpl.setSku(str);
        }
        return this;
    }

    public VideoPlayView setTailCoverUrl(String str) {
        this.tailCoverUrl = str;
        return this;
    }

    public VideoPlayView setTailSource(String str) {
        this.tailSource = str;
        return this;
    }

    public VideoPlayView setTitle(String str) {
        if (str != null) {
            this.titleTv.setText(str);
        }
        return this;
    }

    @Override // com.jingdong.common.unification.video.player.IJDVideoPlayerBiz
    public VideoPlayView setVideoId(String str) {
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null) {
            newVideoMtaInfoImpl.setVideoId(str);
        }
        return this;
    }

    public VideoPlayView setVideoUrl(String str) {
        this.videoPath = str;
        return this;
    }

    public VideoPlayView setVideoViewBtClickListener(AVideoViewBtClickListener aVideoViewBtClickListener) {
        this.videoViewBtClickListener = aVideoViewBtClickListener;
        return this;
    }

    public void setVideoViewOnClickListener(View.OnClickListener onClickListener) {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        if (iJDVideoPlayer != null) {
            iJDVideoPlayer.setOnTouchListener(null);
            this.videoView.setOnClickListener(onClickListener);
        }
    }

    public void setVideoViewOnTouchListener(IVideoViewOnTouchListener iVideoViewOnTouchListener) {
        this.videoViewOnTouchListener = iVideoViewOnTouchListener;
    }

    public VideoPlayView setVoiceIconKeepVisiInFullScreen(boolean z) {
        this.isVoiceIconKeepVisiInFullScreen = z;
        return this;
    }

    public void setVoiceParentLP(RelativeLayout.LayoutParams layoutParams) {
        if (layoutParams != null) {
            this.voiceParent.setLayoutParams(layoutParams);
        }
    }

    public void setVoiceState(boolean z) {
        this.videoView.setVolume(z ? 1.0f : 0.0f);
    }

    public void setiViewPlayerControl(IViewPlayerControl iViewPlayerControl) {
        this.iViewPlayerControl = iViewPlayerControl;
    }

    public void show(boolean z) {
        View view;
        if (UnLog.D) {
            UnLog.d(TAG, "show");
        }
        IVideoPlayerCtrlViewListener iVideoPlayerCtrlViewListener = this.ctrlViewListener;
        if (iVideoPlayerCtrlViewListener != null) {
            iVideoPlayerCtrlViewListener.show();
        }
        if (this.voiceIconState == 0 && this.isVoiceFirstClicked && this.voiceIcon.getVisibility() != 0) {
            Animation animation = VideoPlayUtil.getAnimation(this.mContext, R.anim.vd_option_entry_from_bottom, null);
            this.voiceIcon.clearAnimation();
            this.voiceIcon.startAnimation(animation);
            this.voiceIcon.setVisibility(0);
        }
        if (this.voiceIconState == 1 && this.isVoiceFirstClicked && this.bottomVoice.getVisibility() != 0) {
            Animation animation2 = VideoPlayUtil.getAnimation(this.mContext, R.anim.vd_option_entry_from_bottom, null);
            this.bottomVoice.clearAnimation();
            this.bottomVoice.startAnimation(animation2);
            this.bottomVoice.setVisibility(0);
        }
        if (!this.isHideTopBar && this.topBarLayout.getVisibility() != 0) {
            this.topBarLayout.setVisibility(0);
            Animation animation3 = VideoPlayUtil.getAnimation(this.mContext, R.anim.vd_option_entry_from_top, null);
            this.topBarLayout.clearAnimation();
            this.topBarLayout.startAnimation(animation3);
            if (this.isLive) {
                this.liveIconIv.setVisibility(0);
                this.liveIconIv.clearAnimation();
                this.liveIconIv.startAnimation(animation3);
            }
        }
        if (!this.isHideBottomBar && this.bottomBarLayout.getVisibility() != 0) {
            Animation animation4 = VideoPlayUtil.getAnimation(this.mContext, R.anim.vd_option_entry_from_bottom, null);
            this.bottomBarLayout.setVisibility(0);
            this.bottomBarLayout.clearAnimation();
            this.bottomBarLayout.startAnimation(animation4);
        }
        if (!this.isHideBottomBar && this.voiceIconState == 1 && this.isVoiceFirstClicked && this.bottomBarLayoutCopy.getVisibility() != 0) {
            Animation animation5 = VideoPlayUtil.getAnimation(this.mContext, R.anim.vd_option_entry_from_bottom, null);
            this.bottomBarLayoutCopy.setVisibility(0);
            this.bottomBarLayoutCopy.clearAnimation();
            this.bottomBarLayoutCopy.startAnimation(animation5);
        }
        this.mHandler.removeMessages(3);
        if (z && !this.isHideControlPanl) {
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(3), 3000L);
        }
        if (!this.isHideBottomBar && (view = this.mPointView) != null && view.getVisibility() == 0) {
            ObjectAnimator.ofFloat(this.mPointView, "translationY", DpiUtil.dip2px(this.mContext, 30.0f), 0.0f).setDuration(300L).start();
            this.pointViewAnimFinish = false;
        }
        if (this.bottomSharedEnable && this.bottomShareIcon.getVisibility() != 0) {
            Animation animation6 = VideoPlayUtil.getAnimation(this.mContext, R.anim.vd_option_entry_from_bottom, null);
            this.bottomShareIcon.clearAnimation();
            this.bottomShareIcon.startAnimation(animation6);
            this.bottomShareIcon.setVisibility(0);
        }
        if (this.bottomProgressBar.getVisibility() != 0 || this.isKeepBottomProgressBarVisi) {
            return;
        }
        this.bottomProgressBar.setVisibility(8);
    }

    public void showLoading() {
        hideErrorLayout();
        if (!this.isLoading) {
            int i2 = this.screenState;
            if (i2 != 1 && i2 != 4 && i2 != 3) {
                this.loadingLayout.setVisibility(0);
            } else {
                this.loadingLayoutSmall.setVisibility(0);
            }
            NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
            if (newVideoMtaInfoImpl != null) {
                newVideoMtaInfoImpl.updateLoadStartTime();
            }
            this.mHandler.sendEmptyMessageDelayed(2, 15000L);
        }
        this.isLoading = true;
    }

    public VideoPlayView singleHideBottomBar(boolean z) {
        this.bottomBarLayout.setVisibility(z ? 8 : 0);
        return this;
    }

    public void startAfterUrl() {
        setPlaySource(this.videoPath);
    }

    public void startAndRetry() {
        if (this.status == 331) {
            reTry();
        } else {
            startPlay();
        }
    }

    public VideoPlayView startPlay() {
        if (this.currentTipState != -1) {
            pausePlay();
        } else if (this.isNeedAccessChangeToWifi) {
            reTry();
            this.isNeedAccessChangeToWifi = false;
        } else {
            this.status = 334;
            NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
            if (newVideoMtaInfoImpl != null) {
                newVideoMtaInfoImpl.updateStartTime(System.currentTimeMillis());
            }
            changeVoiceState();
            this.oldProgress = 0;
            if (this.isLive) {
                this.videoView.seekTo(0);
            }
            if (UnLog.D) {
                UnLog.d(TAG, "startPlay showloading");
            }
            this.centerPlayIv.setVisibility(8);
            this.replayIcon.setVisibility(8);
            this.videoView.start();
            setScreenOn();
            this.isAlreadyStartPlay = true;
            ItemVideoPlayerController itemVideoPlayerController = this.itemCtrl;
            if (itemVideoPlayerController != null) {
                itemVideoPlayerController.start();
            }
            updatePausePlay();
            this.mHandler.sendEmptyMessage(1);
            this.mHandler.sendEmptyMessage(4);
        }
        return this;
    }

    public VideoPlayView stopPlay() {
        this.status = 337;
        ItemVideoPlayerController itemVideoPlayerController = this.itemCtrl;
        if (itemVideoPlayerController != null) {
            itemVideoPlayerController.stop();
        }
        this.videoView.suspend();
        setScreenOff();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(1);
            this.mHandler.removeMessages(4);
        }
        return this;
    }

    public void wifiChangeTo4G() {
        if (UnLog.D) {
            UnLog.d(TAG, "wifiChangeTo4G");
        }
        int i2 = this.status;
        if (i2 == 334) {
            if (!this.isAlreadyShowNoWifi) {
                showNetChangelayout();
                return;
            }
            this.videoView.pause();
            reTry();
        } else if (i2 == 335 && this.currentTipState == -1) {
            this.isNeedAccessChangeToWifi = true;
        }
    }

    public VideoPlayView setPlaySource(String str, int i2) {
        if (UnLog.D) {
            UnLog.d(TAG, "setPlaySource:" + str + ",isSetSource:" + this.isSetSource);
        }
        changeVoiceState();
        this.videoPath = str;
        NewVideoMtaInfoImpl newVideoMtaInfoImpl = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl != null) {
            newVideoMtaInfoImpl.updateStartTime(System.currentTimeMillis());
        }
        if (this.isAutoPlay) {
            if (this.isLocalVideo && this.isNeedJudgeNetOnStart && VideoPlayUtil.isMobileNet() && !this.isAlreadyShowNoWifi) {
                this.isSetSource = false;
                if (!this.flowToastAlert) {
                    showNetChangelayout();
                    return this;
                }
                Context context = this.mContext;
                Toast makeText = Toast.makeText(context, context.getResources().getString(R.string.video_player_no_wifi_toast), 0);
                makeText.setGravity(17, 0, 0);
                makeText.show();
                this.isAlreadyShowNoWifi = true;
            }
            if (this.centerPlayIv.getVisibility() == 0) {
                this.centerPlayIv.setVisibility(8);
            }
            showLoading();
        }
        this.videoView.setVideoPath(str);
        if (this.isPlayingTail || this.isSetSource) {
            releaseInThread();
            this.videoView.initRenders();
        }
        if (i2 > 0) {
            this.videoView.seekTo(i2);
        }
        this.isSetSource = true;
        return this;
    }

    public void showLoadErrorLayout(int i2, boolean z) {
        NewVideoMtaInfoImpl newVideoMtaInfoImpl;
        NewVideoMtaInfoImpl newVideoMtaInfoImpl2 = this.videoMtaInfoImpl;
        if (newVideoMtaInfoImpl2 != null) {
            newVideoMtaInfoImpl2.onError(1, i2);
        }
        if (this.isDestory || this.mContext == null) {
            return;
        }
        if (UnLog.D) {
            UnLog.d(TAG, "showErrorDiaLog hideLoading");
        }
        hideLoading();
        if (this.isShowErrorLayout) {
            int i3 = this.screenState;
            if (i3 != 1 && i3 != 4 && i3 != 3) {
                this.mNetErrorLayout.setVisibility(0);
                this.errorTipTv.setText(this.mContext.getResources().getString(R.string.video_player_load_error));
                this.loadErrorIv.setVisibility(this.loadErrorRetry ? 8 : 0);
                this.mClickToRetry.setVisibility(this.loadErrorRetry ? 0 : 8);
                Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.video_player_fresh_icon);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                this.mClickToRetry.setCompoundDrawables(drawable, null, null, null);
            } else {
                this.errorLayoutSmall.setVisibility(0);
                this.retryTvSmall.setBackgroundResource(this.loadErrorRetry ? R.drawable.video_player_retry_small : R.drawable.video_player_error_icon_small);
                this.retryTvSmall.setEnabled(this.loadErrorRetry);
                this.errorTipTvSmall.setText(this.mContext.getResources().getString(R.string.video_player_load_error_small));
            }
        }
        this.currentTipState = 401;
        pausePlay();
        optEnable(false);
        if (!z || (newVideoMtaInfoImpl = this.videoMtaInfoImpl) == null) {
            return;
        }
        newVideoMtaInfoImpl.upload(this.mContext);
    }

    public void hide(boolean z) {
        Animation animation;
        View view;
        this.mHandler.removeMessages(3);
        IVideoPlayerCtrlViewListener iVideoPlayerCtrlViewListener = this.ctrlViewListener;
        if (iVideoPlayerCtrlViewListener != null) {
            iVideoPlayerCtrlViewListener.hide();
        }
        if (this.voiceIconState == 0 && this.isVoiceFirstClicked && this.voiceIcon.getVisibility() == 0 && !this.isVoiceIconKeepVisiInFullScreen) {
            if (z) {
                Animation animation2 = VideoPlayUtil.getAnimation(this.mContext, R.anim.vd_option_leave_from_bottom, null);
                this.voiceIcon.clearAnimation();
                this.voiceIcon.startAnimation(animation2);
            }
            this.voiceIcon.setVisibility(8);
        }
        if (this.voiceIconState == 1 && this.isVoiceFirstClicked && this.bottomVoice.getVisibility() == 0 && !this.isVoiceIconKeepVisiInFullScreen) {
            if (z) {
                Animation animation3 = VideoPlayUtil.getAnimation(this.mContext, R.anim.vd_option_leave_from_bottom, null);
                this.bottomVoice.clearAnimation();
                this.bottomVoice.startAnimation(animation3);
            }
            this.bottomVoice.setVisibility(8);
        }
        if (z) {
            animation = VideoPlayUtil.getAnimation(this.mContext, R.anim.vd_option_leave_from_top, new Animation.AnimationListener() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.3
                {
                    VideoPlayView.this = this;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation4) {
                    if (UnLog.D) {
                        UnLog.d(VideoPlayView.TAG, "isShowBottomProgressBar:" + VideoPlayView.this.isShowBottomProgressBar);
                    }
                    if (VideoPlayView.this.isShowBottomProgressBar) {
                        VideoPlayView.this.bottomProgressBar.setVisibility(0);
                    } else {
                        VideoPlayView.this.bottomProgressBar.setVisibility(8);
                    }
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation4) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation4) {
                }
            });
        } else {
            if (this.isShowBottomProgressBar) {
                this.bottomProgressBar.setVisibility(0);
            } else {
                this.bottomProgressBar.setVisibility(8);
            }
            animation = null;
        }
        if (this.topBarLayout.getVisibility() == 0) {
            if (z) {
                this.topBarLayout.clearAnimation();
                this.topBarLayout.startAnimation(animation);
            }
            this.topBarLayout.setVisibility(8);
        }
        if (this.bottomBarLayout.getVisibility() == 0) {
            if (z) {
                this.bottomBarLayout.clearAnimation();
                this.bottomBarLayout.startAnimation(VideoPlayUtil.getAnimation(this.mContext, R.anim.vd_option_leave_from_bottom, null));
            }
            this.bottomBarLayout.setVisibility(8);
        }
        if (this.isLive && this.liveIconIv.getVisibility() == 0 && z) {
            this.liveIconIv.clearAnimation();
            this.liveIconIv.startAnimation(animation);
            this.liveIconIv.setVisibility(8);
        }
        if (z && !this.isHideBottomBar && (view = this.mPointView) != null && view.getVisibility() == 0 && !this.pointViewAnimFinish) {
            ObjectAnimator.ofFloat(this.mPointView, "translationY", 0.0f, DpiUtil.dip2px(this.mContext, 30.0f)).setDuration(300L).start();
            this.pointViewAnimFinish = true;
        }
        if (this.bottomSharedEnable && this.bottomShareIcon.getVisibility() == 0) {
            if (z) {
                Animation animation4 = VideoPlayUtil.getAnimation(this.mContext, R.anim.vd_option_leave_from_bottom, null);
                this.bottomShareIcon.clearAnimation();
                this.bottomShareIcon.startAnimation(animation4);
            }
            this.bottomShareIcon.setVisibility(8);
        }
    }

    public VideoPlayView setPlaySourceWithoutPlay(String str, boolean z) {
        this.isFromComment = z;
        setPlaySourceWithoutPlay(str);
        return this;
    }

    public VideoPlayView(Context context) {
        super(context);
        this.status = 330;
        this.currentTipState = -1;
        this.isLoading = false;
        this.sharedEnable = false;
        this.bottomSharedEnable = false;
        this.thisProgress = 0;
        this.oldProgress = 0;
        this.buffProgress = 0;
        this.isVoiceOff = true;
        this.isKeepBottomProgressBarVisi = false;
        this.isAutoPlay = true;
        this.bgState = -1;
        this.isHideCenterPlayer = true;
        this.isDestory = false;
        this.jumpFrom = -1;
        this.screenState = 0;
        this.beforeScreenState = 0;
        this.isAlreadyShowNoWifi = false;
        this.pointViewAnimFinish = false;
        this.currentPoint = -1;
        this.isNeedJudgeNetOnStart = true;
        this.isResumeBgMusicOnPause = true;
        this.isSetSource = true;
        this.isAlreadyStartPlay = true;
        this.pauseCount = 0;
        this.resumeCount = 0;
        this.isScreenOn = false;
        this.isNeedKeepScreenOn = true;
        this.flowToastAlert = false;
        this.isShowErrorLayout = true;
        this.enableItemViewTime = true;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.jingdong.common.unification.video.player.VideoPlayView.1
            {
                VideoPlayView.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1) {
                    VideoPlayView.this.syncProgress();
                    if (VideoPlayView.this.status == 334) {
                        sendMessageDelayed(obtainMessage(1), 100L);
                    }
                } else if (i2 == 2) {
                    VideoPlayView.this.showLoadErrorLayout(4, true);
                } else if (i2 == 3) {
                    VideoPlayView.this.hide();
                } else if (i2 != 4) {
                } else {
                    long tcpSpeed = VideoPlayView.this.videoView.getTcpSpeed() / 1000;
                    if (VideoPlayView.this.videoMtaInfoImpl != null) {
                        VideoPlayView.this.videoMtaInfoImpl.updateSpeed(tcpSpeed);
                    }
                    if (VideoPlayView.this.status == 334) {
                        sendMessageDelayed(obtainMessage(4), 2000L);
                    }
                }
            }
        };
        this.mSeekListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.4
            {
                VideoPlayView.this = this;
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                if (z) {
                    int duration = VideoPlayView.this.getDuration();
                    float f2 = duration;
                    int i3 = (int) ((i2 * f2) / 1000.0f);
                    if (duration - i3 < 1000) {
                        i3 = duration;
                    }
                    int i4 = i3 >= duration ? duration - 500 : i3;
                    int i5 = (int) ((i4 * 1000.0f) / f2);
                    VideoPlayView.this.mSeekBar.setProgress(i5);
                    VideoPlayView.this.setSeekBarBgProgress(i5, false);
                    if (VideoPlayView.this.mtaListener != null) {
                        VideoPlayView.this.mtaListener.progressChangedFromUser(i5);
                    }
                    if (VideoPlayView.this.isShowBottomProgressBar) {
                        VideoPlayView.this.bottomProgressBar.setProgress(i5);
                        VideoPlayView.this.setSeekBarBgProgress(i5, true);
                    }
                    if (VideoPlayView.this.screenState == 1) {
                        VideoPlayView.this.bottomProgressBarSmall.setProgress(i5);
                    }
                    if (VideoPlayView.this.progrssChangeListener != null) {
                        VideoPlayView.this.progrssChangeListener.onProgressChange(i5, 1000);
                    }
                    VideoPlayView.this.dragPosition = i4;
                    if (VideoPlayView.this.currentTimeTv != null) {
                        VideoPlayView.this.currentTimeTv.setText(VideoPlayUtil.generateTime(i3));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                VideoPlayView.this.mHandler.removeMessages(1);
                VideoPlayView.this.mHandler.removeMessages(3);
                VideoPlayView.this.mHandler.removeMessages(4);
                if (VideoPlayView.this.mtaListener != null) {
                    VideoPlayView.this.mtaListener.startTrackingTouch();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (UnLog.D) {
                    UnLog.d(VideoPlayView.TAG, "dragPosition:" + VideoPlayView.this.dragPosition);
                }
                if (VideoPlayView.this.mtaListener != null) {
                    VideoPlayView.this.mtaListener.stopTrackingTouch();
                }
                VideoPlayView.this.videoView.seekTo(VideoPlayView.this.dragPosition);
                VideoPlayView videoPlayView = VideoPlayView.this;
                videoPlayView.setSelectPoint(videoPlayView.dragPosition, false);
                VideoPlayView.this.syncProgress();
                VideoPlayView.this.mHandler.sendEmptyMessage(1);
                VideoPlayView.this.mHandler.sendEmptyMessage(4);
                VideoPlayView.this.mHandler.sendMessageDelayed(VideoPlayView.this.mHandler.obtainMessage(3), 3000L);
            }
        };
        this.showRunnable = new Runnable() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.8
            {
                VideoPlayView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                VideoPlayerUtils.setActivityFullScreen(VideoPlayView.this.videoView);
            }
        };
        initView(null);
    }

    public VideoPlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.status = 330;
        this.currentTipState = -1;
        this.isLoading = false;
        this.sharedEnable = false;
        this.bottomSharedEnable = false;
        this.thisProgress = 0;
        this.oldProgress = 0;
        this.buffProgress = 0;
        this.isVoiceOff = true;
        this.isKeepBottomProgressBarVisi = false;
        this.isAutoPlay = true;
        this.bgState = -1;
        this.isHideCenterPlayer = true;
        this.isDestory = false;
        this.jumpFrom = -1;
        this.screenState = 0;
        this.beforeScreenState = 0;
        this.isAlreadyShowNoWifi = false;
        this.pointViewAnimFinish = false;
        this.currentPoint = -1;
        this.isNeedJudgeNetOnStart = true;
        this.isResumeBgMusicOnPause = true;
        this.isSetSource = true;
        this.isAlreadyStartPlay = true;
        this.pauseCount = 0;
        this.resumeCount = 0;
        this.isScreenOn = false;
        this.isNeedKeepScreenOn = true;
        this.flowToastAlert = false;
        this.isShowErrorLayout = true;
        this.enableItemViewTime = true;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.jingdong.common.unification.video.player.VideoPlayView.1
            {
                VideoPlayView.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1) {
                    VideoPlayView.this.syncProgress();
                    if (VideoPlayView.this.status == 334) {
                        sendMessageDelayed(obtainMessage(1), 100L);
                    }
                } else if (i2 == 2) {
                    VideoPlayView.this.showLoadErrorLayout(4, true);
                } else if (i2 == 3) {
                    VideoPlayView.this.hide();
                } else if (i2 != 4) {
                } else {
                    long tcpSpeed = VideoPlayView.this.videoView.getTcpSpeed() / 1000;
                    if (VideoPlayView.this.videoMtaInfoImpl != null) {
                        VideoPlayView.this.videoMtaInfoImpl.updateSpeed(tcpSpeed);
                    }
                    if (VideoPlayView.this.status == 334) {
                        sendMessageDelayed(obtainMessage(4), 2000L);
                    }
                }
            }
        };
        this.mSeekListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.4
            {
                VideoPlayView.this = this;
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                if (z) {
                    int duration = VideoPlayView.this.getDuration();
                    float f2 = duration;
                    int i3 = (int) ((i2 * f2) / 1000.0f);
                    if (duration - i3 < 1000) {
                        i3 = duration;
                    }
                    int i4 = i3 >= duration ? duration - 500 : i3;
                    int i5 = (int) ((i4 * 1000.0f) / f2);
                    VideoPlayView.this.mSeekBar.setProgress(i5);
                    VideoPlayView.this.setSeekBarBgProgress(i5, false);
                    if (VideoPlayView.this.mtaListener != null) {
                        VideoPlayView.this.mtaListener.progressChangedFromUser(i5);
                    }
                    if (VideoPlayView.this.isShowBottomProgressBar) {
                        VideoPlayView.this.bottomProgressBar.setProgress(i5);
                        VideoPlayView.this.setSeekBarBgProgress(i5, true);
                    }
                    if (VideoPlayView.this.screenState == 1) {
                        VideoPlayView.this.bottomProgressBarSmall.setProgress(i5);
                    }
                    if (VideoPlayView.this.progrssChangeListener != null) {
                        VideoPlayView.this.progrssChangeListener.onProgressChange(i5, 1000);
                    }
                    VideoPlayView.this.dragPosition = i4;
                    if (VideoPlayView.this.currentTimeTv != null) {
                        VideoPlayView.this.currentTimeTv.setText(VideoPlayUtil.generateTime(i3));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                VideoPlayView.this.mHandler.removeMessages(1);
                VideoPlayView.this.mHandler.removeMessages(3);
                VideoPlayView.this.mHandler.removeMessages(4);
                if (VideoPlayView.this.mtaListener != null) {
                    VideoPlayView.this.mtaListener.startTrackingTouch();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (UnLog.D) {
                    UnLog.d(VideoPlayView.TAG, "dragPosition:" + VideoPlayView.this.dragPosition);
                }
                if (VideoPlayView.this.mtaListener != null) {
                    VideoPlayView.this.mtaListener.stopTrackingTouch();
                }
                VideoPlayView.this.videoView.seekTo(VideoPlayView.this.dragPosition);
                VideoPlayView videoPlayView = VideoPlayView.this;
                videoPlayView.setSelectPoint(videoPlayView.dragPosition, false);
                VideoPlayView.this.syncProgress();
                VideoPlayView.this.mHandler.sendEmptyMessage(1);
                VideoPlayView.this.mHandler.sendEmptyMessage(4);
                VideoPlayView.this.mHandler.sendMessageDelayed(VideoPlayView.this.mHandler.obtainMessage(3), 3000L);
            }
        };
        this.showRunnable = new Runnable() { // from class: com.jingdong.common.unification.video.player.VideoPlayView.8
            {
                VideoPlayView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                VideoPlayerUtils.setActivityFullScreen(VideoPlayView.this.videoView);
            }
        };
        initView(attributeSet);
    }
}
