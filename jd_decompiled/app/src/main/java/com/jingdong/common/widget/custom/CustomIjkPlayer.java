package com.jingdong.common.widget.custom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.R;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.widget.video.IjkVideoViewWithReport;
import com.jingdong.common.widget.video.VideoInfoEntity;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.ref.WeakReference;
import java.util.Formatter;
import java.util.Locale;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.example.widget.media.PlayDurationStatistics;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: classes12.dex */
public class CustomIjkPlayer extends FrameLayout {
    private static final int BAR_MAX = 1000;
    public static final int DEFAULT_LOADING_STYLE = 0;
    public static final int LINGTING_LOADING_STYLE = 1;
    private static final int MSG_PLAY_STATUS = 1;
    public static final String REPORT_TYPE_ARTICLE = "4";
    public static final String REPORT_TYPE_BABEL = "5";
    public static final String REPORT_TYPE_SHORT_VIDEO = "6";
    public static final String REPORT_TYPE_SHORT_VIDEO_DETAIL = "7";
    public static final String REPORT_TYPE_VIDEO_BUY = "3";
    public static final int STATUS_COMPLETE = 3;
    public static final int STATUS_ERROR = 4;
    public static final int STATUS_IDLE = 0;
    public static final int STATUS_PAUSE = 2;
    public static final int STATUS_PLAYING = 1;
    private static final String TAG = "CustomIjkPlayer";
    private SimpleDraweeView bgImg;
    private View bottomLineProgressView;
    private View bottomProgressView;
    private View bottomViewProgressLayout;
    private ViewGroup controlViewParent;
    private String event_param;
    private View ijkBtnBg;
    private View ijkLightingView;
    private ImageView ijkPlayFullBtn;
    private ProgressBar ijkPlayLineProgress;
    protected SeekBar ijkPlayStatusBar;
    private View ijkPlayStatusBtn;
    private ImageView ijkPlayStatusImg;
    private TextView ijkPlayStatusLineTimeCountDown;
    private TextView ijkPlayStatusText;
    private TextView ijkPlayStatusTimeDuration;
    private TextView ijkPlayStatusTimePosition;
    private ImageView ijkPlayVoiceBtn;
    private boolean isComplete;
    private boolean isCouldAutoHide;
    private boolean isCouldPlayWithStart;
    private boolean isForceLayout;
    private boolean isFullScreen;
    private boolean isHasRendered;
    private boolean isLivePlay;
    private boolean isScreenOn;
    private boolean isVoiceOn;
    private long lastPosition;
    private AnimatorSet loadingAnimatorSet;
    private View loadingView;
    private View loadingViewCustom;
    private int mAspectRatio;
    private boolean mBgImgSuccess;
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;
    private Handler mHandler;
    private IjkVideoViewWithReport mIjkVideoView;
    private FrameLayout mIjkVideoViewWrapper;
    private int mLoadingStyle;
    private MtaListener mMtaListener;
    private IPlayerControl.OnPlayerStateListener mOnPlayerStateListener;
    private OnUpdatePositionListener mOnUpdatePositionListener;
    private String mPath;
    private PlayDurationStatistics.PlayDurationStatisticsListener mPlayDurationStatisticsListener;
    @PlayStatus
    private int mPlayStatus;
    private final Runnable measureAndLayout;
    private BroadcastReceiver myNetReceiver;
    private boolean needCountPlayTime;
    private View.OnClickListener onPlayOrPauseListener;
    private String page_param;
    private ViewGroup playStatusParent;
    private int playTime;
    private IPlayerControl.PlayerOptions playerOptions;
    private WeakReference<Activity> screenOnActivity;
    private WeakReference<Dialog> screenOnDialog;
    private boolean showIjkBtnBg;
    private int timesToHideControlView;
    private View topHeaderView;
    private long voiceRegisterTime;
    private BroadcastReceiver voiceStateReceiver;
    private String withoutPlayPath;

    /* loaded from: classes12.dex */
    public static abstract class MtaListener {
        public String customNonWifiNetTip() {
            return null;
        }

        public void netChange() {
        }

        @Deprecated
        public void playBtnOnClick(boolean z) {
        }

        public void playBtnOnClick(boolean z, @PlayStatus int i2) {
        }

        public void playStatusChange(@PlayStatus int i2) {
        }

        public void seekBarOnSeek(long j2) {
        }

        public boolean tryToShow3GTip() {
            return false;
        }

        public void voiceBtnOnClick(boolean z) {
        }

        public void voiceStateChange(boolean z) {
        }
    }

    /* loaded from: classes12.dex */
    public static class MyHandler extends Handler {
        private WeakReference<CustomIjkPlayer> playerWeakReference;

        public MyHandler(CustomIjkPlayer customIjkPlayer) {
            this.playerWeakReference = new WeakReference<>(customIjkPlayer);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            CustomIjkPlayer customIjkPlayer;
            if (message.what == 1 && (customIjkPlayer = this.playerWeakReference.get()) != null) {
                customIjkPlayer.updatePosition(-1L);
                customIjkPlayer.mHandler.sendEmptyMessageDelayed(1, 1000L);
                if (!customIjkPlayer.isCouldAutoHide || customIjkPlayer.timesToHideControlView < 0 || CustomIjkPlayer.access$2610(customIjkPlayer) > 0 || !customIjkPlayer.mIjkVideoView.isPlaying()) {
                    return;
                }
                customIjkPlayer.hideControlView();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class OnUpdatePositionListener {
        @Deprecated
        public void updatePosition(boolean z, long j2, long j3) {
        }

        public void updatePosition(boolean z, long j2, long j3, boolean z2) {
        }
    }

    public CustomIjkPlayer(Context context) {
        super(context);
        this.mPlayStatus = 0;
        this.topHeaderView = null;
        this.withoutPlayPath = null;
        this.mPath = null;
        this.isComplete = false;
        this.isHasRendered = false;
        this.isCouldAutoHide = true;
        this.isCouldPlayWithStart = true;
        this.isVoiceOn = true;
        this.showIjkBtnBg = true;
        this.playTime = 0;
        this.needCountPlayTime = false;
        this.isLivePlay = false;
        this.mBgImgSuccess = false;
        this.timesToHideControlView = -1;
        this.mAspectRatio = -1;
        this.voiceRegisterTime = -1L;
        this.isScreenOn = false;
        this.screenOnActivity = null;
        this.screenOnDialog = null;
        this.event_param = null;
        this.page_param = "";
        this.isForceLayout = false;
        this.measureAndLayout = new Runnable() { // from class: com.jingdong.common.widget.custom.CustomIjkPlayer.9
            {
                CustomIjkPlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                CustomIjkPlayer customIjkPlayer = CustomIjkPlayer.this;
                customIjkPlayer.measure(View.MeasureSpec.makeMeasureSpec(customIjkPlayer.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(CustomIjkPlayer.this.getHeight(), 1073741824));
                CustomIjkPlayer customIjkPlayer2 = CustomIjkPlayer.this;
                customIjkPlayer2.layout(customIjkPlayer2.getLeft(), CustomIjkPlayer.this.getTop(), CustomIjkPlayer.this.getRight(), CustomIjkPlayer.this.getBottom());
            }
        };
        initView(context);
    }

    static /* synthetic */ int access$2610(CustomIjkPlayer customIjkPlayer) {
        int i2 = customIjkPlayer.timesToHideControlView;
        customIjkPlayer.timesToHideControlView = i2 - 1;
        return i2;
    }

    public static void clearPlayPosition() {
        IjkVideoViewWithReport.clearPlayPosition();
    }

    private void doLoadingAnimation() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.ijkLightingView, "scaleX", 1.0f, 50.0f);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        AnimatorSet animatorSet = new AnimatorSet();
        this.loadingAnimatorSet = animatorSet;
        animatorSet.setDuration(600L);
        this.loadingAnimatorSet.setInterpolator(new LinearInterpolator());
        this.loadingAnimatorSet.play(ofFloat);
        this.loadingAnimatorSet.start();
    }

    public static long getPlayPosition(String str) {
        return IjkVideoViewWithReport.getPlayPosition(str);
    }

    private Activity getScreenOnActivity() {
        Activity activity;
        WeakReference<Activity> weakReference = this.screenOnActivity;
        if (weakReference == null || (activity = weakReference.get()) == null) {
            Context context = getContext();
            if (context == null || !(context instanceof Activity)) {
                return null;
            }
            return (Activity) context;
        }
        return activity;
    }

    private Dialog getScreenOnDialog() {
        WeakReference<Dialog> weakReference = this.screenOnDialog;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private void initView(Context context) {
        this.isLivePlay = false;
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        this.bgImg = simpleDraweeView;
        simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(this.bgImg, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout.inflate(context, R.layout.fx_ijk_view_video_player, this);
        this.mIjkVideoView = (IjkVideoViewWithReport) findViewById(R.id.ijk_video_view);
        this.mIjkVideoViewWrapper = (FrameLayout) findViewById(R.id.ijk_video_view_wrapper);
        this.mIjkVideoView.addSurfaceHolderCallback(new SurfaceHolder.Callback() { // from class: com.jingdong.common.widget.custom.CustomIjkPlayer.1
            {
                CustomIjkPlayer.this = this;
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            }
        });
        this.ijkBtnBg = findViewById(R.id.ijk_btn_bg);
        this.loadingView = findViewById(R.id.ijk_loading_view);
        this.loadingViewCustom = findViewById(R.id.ijk_loading_view_custom);
        this.ijkLightingView = findViewById(R.id.ijk_lighting_view);
        this.bottomProgressView = findViewById(R.id.ijk_bottom_view);
        this.bottomViewProgressLayout = findViewById(R.id.ijk_bottom_view_progress_layout);
        this.bottomProgressView.setVisibility(8);
        this.ijkPlayStatusBtn = findViewById(R.id.ijk_play_status_btn);
        this.ijkPlayStatusImg = (ImageView) findViewById(R.id.ijk_play_status_img);
        TextView textView = (TextView) findViewById(R.id.ijk_play_status_text);
        this.ijkPlayStatusText = textView;
        textView.setVisibility(8);
        SeekBar seekBar = (SeekBar) findViewById(R.id.ijk_play_status_bar);
        this.ijkPlayStatusBar = seekBar;
        seekBar.setMax(1000);
        this.bottomLineProgressView = findViewById(R.id.ijk_bottom_line_view);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.ijk_play_status_progress_line);
        this.ijkPlayLineProgress = progressBar;
        progressBar.setMax(1000);
        this.ijkPlayStatusLineTimeCountDown = (TextView) findViewById(R.id.ijk_play_status_time_count_down_line);
        this.bottomLineProgressView.setVisibility(8);
        this.ijkPlayLineProgress.setVisibility(8);
        this.ijkPlayStatusLineTimeCountDown.setVisibility(8);
        this.ijkPlayFullBtn = (ImageView) findViewById(R.id.ijk_play_full_btn);
        this.ijkPlayStatusTimePosition = (TextView) findViewById(R.id.ijk_play_status_time_position);
        this.ijkPlayStatusTimeDuration = (TextView) findViewById(R.id.ijk_play_status_time_duration);
        ImageView imageView = (ImageView) findViewById(R.id.ijk_play_voice_btn);
        this.ijkPlayVoiceBtn = imageView;
        imageView.setVisibility(8);
        setPlayerListener();
        setViewListener();
        registerNetworkStatus();
    }

    public void mta(String str) {
        if (this.event_param == null) {
            return;
        }
        JDMtaUtils.onClickWithPageId(getContext(), str, getContext().getClass().getSimpleName(), this.event_param, this.page_param, "DemoVideo_List");
    }

    private void registerNetworkStatus() {
        if (this.myNetReceiver != null) {
            return;
        }
        this.myNetReceiver = new BroadcastReceiver() { // from class: com.jingdong.common.widget.custom.CustomIjkPlayer.7
            {
                CustomIjkPlayer.this = this;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    if (CustomIjkPlayer.this.mIjkVideoView.getIjkMediaPlayer() != null && !CustomIjkPlayer.this.isComplete()) {
                        CustomIjkPlayer.this.tryToShow3GTip();
                    }
                    if (CustomIjkPlayer.this.mMtaListener != null) {
                        CustomIjkPlayer.this.mMtaListener.netChange();
                    }
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        JdSdk.getInstance().getApplication().registerReceiver(this.myNetReceiver, intentFilter);
    }

    public static void savePlayPosition(String str, long j2) {
        IjkVideoViewWithReport.savePlayPosition(str, j2);
    }

    public void setPlayStatus(@PlayStatus int i2) {
        this.mPlayStatus = i2;
        if (i2 == 0) {
            this.needCountPlayTime = false;
            this.ijkPlayStatusImg.setImageResource(R.drawable.ic_ijk_player_play);
            this.ijkPlayStatusText.setVisibility(8);
        } else if (i2 == 1) {
            this.needCountPlayTime = true;
            this.ijkPlayStatusImg.setImageResource(R.drawable.ic_ijk_player_pause);
            this.ijkPlayStatusText.setVisibility(8);
            showVideoView_hideBgImg();
        } else if (i2 == 2) {
            this.needCountPlayTime = false;
            this.mIjkVideoView.setRealPlayTime(this.playTime);
            this.playTime = 0;
            this.ijkPlayStatusImg.setImageResource(R.drawable.ic_ijk_player_play);
            this.ijkPlayStatusText.setVisibility(8);
        } else if (i2 == 3) {
            this.needCountPlayTime = false;
            this.mIjkVideoView.setRealPlayTime(this.playTime);
            this.mIjkVideoView.report();
            this.playTime = 0;
            this.ijkPlayStatusImg.setImageResource(R.drawable.ic_ijk_player_replay);
            this.ijkPlayStatusText.setVisibility(0);
            this.ijkPlayStatusText.setText(R.string.custom_ijk_player_replay);
        } else if (i2 == 4) {
            this.needCountPlayTime = false;
            this.mIjkVideoView.setRealPlayTime(this.playTime);
            this.playTime = 0;
            this.ijkPlayStatusImg.setImageResource(R.drawable.ic_ijk_player_refresh);
            this.ijkPlayStatusText.setVisibility(0);
            this.ijkPlayStatusText.setText(R.string.custom_ijk_player_refresh);
        } else {
            this.needCountPlayTime = false;
            throw new RuntimeException("not define playStatus");
        }
        MtaListener mtaListener = this.mMtaListener;
        if (mtaListener != null) {
            mtaListener.playStatusChange(i2);
        }
        if (this.isForceLayout) {
            requestLayout();
        }
    }

    private void setPlayStatusBtnVisibility() {
        View view = this.ijkPlayStatusBtn;
        int i2 = 8;
        if ((this.loadingView.getVisibility() == 8 || this.loadingView.getVisibility() == 4) && this.bottomProgressView.getVisibility() == 0) {
            i2 = 0;
        }
        view.setVisibility(i2);
    }

    private void setPlayStatusTimeText(long j2, long j3) {
        if (j2 < 0) {
            j2 = 0;
        }
        if (j3 < 0) {
            j3 = 0;
        }
        this.ijkPlayStatusTimePosition.setText(String.format("%s", stringForTime(j2)));
        this.ijkPlayStatusTimeDuration.setText(String.format("%s", stringForTime(j3)));
        if (this.bottomLineProgressView.getVisibility() == 0) {
            long j4 = j3 - j2;
            this.ijkPlayStatusLineTimeCountDown.setText(String.format("%s", stringForTime(j4 > 0 ? j4 : 0L)));
        }
    }

    private void setPlayerListener() {
        this.mIjkVideoView.setOnPlayerStateListener(new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.widget.custom.CustomIjkPlayer.6
            {
                CustomIjkPlayer.this = this;
            }

            /* JADX WARN: Removed duplicated region for block: B:43:0x0034  */
            /* JADX WARN: Removed duplicated region for block: B:44:0x0037  */
            /* JADX WARN: Removed duplicated region for block: B:47:0x003d  */
            /* JADX WARN: Removed duplicated region for block: B:50:0x004d  */
            /* JADX WARN: Removed duplicated region for block: B:51:0x004f  */
            /* JADX WARN: Removed duplicated region for block: B:54:0x006a  */
            /* JADX WARN: Removed duplicated region for block: B:57:0x007c  */
            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onCompletion() {
                /*
                    r9 = this;
                    com.jingdong.common.widget.custom.CustomIjkPlayer r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    com.jingdong.common.widget.video.IjkVideoViewWithReport r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.access$400(r0)
                    int r0 = r0.getDuration()
                    com.jingdong.common.widget.custom.CustomIjkPlayer r1 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    com.jingdong.common.widget.video.IjkVideoViewWithReport r1 = com.jingdong.common.widget.custom.CustomIjkPlayer.access$400(r1)
                    int r1 = r1.getCurrentPosition()
                    r2 = 1
                    if (r0 <= 0) goto L2f
                    if (r1 <= 0) goto L2f
                    double r3 = (double) r1
                    double r5 = (double) r0
                    r7 = 4606732058837280358(0x3fee666666666666, double:0.95)
                    java.lang.Double.isNaN(r5)
                    double r5 = r5 * r7
                    int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                    if (r7 >= 0) goto L2f
                    int r1 = r1 + 3000
                    if (r1 >= r0) goto L2f
                    r1 = 1
                    goto L30
                L2f:
                    r1 = 0
                L30:
                    com.jingdong.common.widget.custom.CustomIjkPlayer r3 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    if (r1 == 0) goto L37
                    r4 = -1
                    goto L38
                L37:
                    long r4 = (long) r0
                L38:
                    com.jingdong.common.widget.custom.CustomIjkPlayer.access$1000(r3, r4)
                    if (r1 != 0) goto L44
                    com.jingdong.common.widget.custom.CustomIjkPlayer r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    r3 = 0
                    com.jingdong.common.widget.custom.CustomIjkPlayer.access$902(r0, r3)
                L44:
                    com.jingdong.common.widget.custom.CustomIjkPlayer r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    com.jingdong.common.widget.custom.CustomIjkPlayer.access$302(r0, r2)
                    com.jingdong.common.widget.custom.CustomIjkPlayer r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    if (r1 == 0) goto L4f
                    r1 = 4
                    goto L50
                L4f:
                    r1 = 3
                L50:
                    com.jingdong.common.widget.custom.CustomIjkPlayer.access$2100(r0, r1)
                    com.jingdong.common.widget.custom.CustomIjkPlayer r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    com.jingdong.common.widget.custom.CustomIjkPlayer.access$1600(r0)
                    com.jingdong.common.widget.custom.CustomIjkPlayer r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    r0.showControlView()
                    com.jingdong.common.widget.custom.CustomIjkPlayer r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    com.jingdong.common.widget.custom.CustomIjkPlayer.access$2200(r0)
                    com.jingdong.common.widget.custom.CustomIjkPlayer r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    android.widget.ProgressBar r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.access$1800(r0)
                    if (r0 == 0) goto L74
                    com.jingdong.common.widget.custom.CustomIjkPlayer r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    android.widget.ProgressBar r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.access$1800(r0)
                    r1 = 0
                    r0.setAlpha(r1)
                L74:
                    com.jingdong.common.widget.custom.CustomIjkPlayer r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    tv.danmaku.ijk.media.example.widget.media.IPlayerControl$OnPlayerStateListener r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.access$1500(r0)
                    if (r0 == 0) goto L85
                    com.jingdong.common.widget.custom.CustomIjkPlayer r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    tv.danmaku.ijk.media.example.widget.media.IPlayerControl$OnPlayerStateListener r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.access$1500(r0)
                    r0.onCompletion()
                L85:
                    com.jingdong.common.widget.custom.CustomIjkPlayer r0 = com.jingdong.common.widget.custom.CustomIjkPlayer.this
                    java.lang.String r1 = "DemoVideo_ListEndExpo_Player"
                    com.jingdong.common.widget.custom.CustomIjkPlayer.access$2000(r0, r1)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.widget.custom.CustomIjkPlayer.AnonymousClass6.onCompletion():void");
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
                if (CustomIjkPlayer.this.mOnPlayerStateListener != null) {
                    CustomIjkPlayer.this.mOnPlayerStateListener.onCreatePlayer();
                }
                CustomIjkPlayer.this.isHasRendered = false;
                CustomIjkPlayer.this.showLoadingView();
                CustomIjkPlayer.this.hideControlView();
                CustomIjkPlayer.this.stopUpdateStatusHandler();
                if (CustomIjkPlayer.this.bottomLineProgressView.getVisibility() == 0) {
                    CustomIjkPlayer.this.ijkPlayLineProgress.setVisibility(8);
                    CustomIjkPlayer.this.ijkPlayStatusLineTimeCountDown.setVisibility(8);
                }
                CustomIjkPlayer.this.mta("DemoVideo_ListURL_Player");
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                CustomIjkPlayer.this.isComplete = true;
                CustomIjkPlayer.this.setPlayStatus(4);
                CustomIjkPlayer.this.hideLoadingView();
                CustomIjkPlayer.this.stopUpdateStatusHandler();
                CustomIjkPlayer.this.showControlView();
                if (CustomIjkPlayer.this.mOnPlayerStateListener == null || !CustomIjkPlayer.this.mOnPlayerStateListener.onError(i2, i3)) {
                    if (!NetUtils.isNetworkAvailable()) {
                        ToastUtils.shortToast(R.string.custom_ijk_player_error_net);
                    } else {
                        ToastUtils.shortToast(R.string.custom_ijk_player_error_source);
                    }
                    return true;
                }
                return true;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                if (OKLog.D) {
                    OKLog.d(CustomIjkPlayer.TAG, "aaaa: onInfo" + i2 + "  " + i3);
                }
                if (i2 == 3) {
                    CustomIjkPlayer.this.needCountPlayTime = true;
                    CustomIjkPlayer.this.isHasRendered = true;
                    CustomIjkPlayer.this.startUpdateStatusHandler();
                    if (!CustomIjkPlayer.this.isCouldAutoHide) {
                        CustomIjkPlayer.this.showControlView();
                    }
                    CustomIjkPlayer.this.hideLoadingView();
                    if (CustomIjkPlayer.this.lastPosition > 0) {
                        CustomIjkPlayer.this.mIjkVideoView.seekTo((int) CustomIjkPlayer.this.lastPosition);
                    }
                    if (!CustomIjkPlayer.this.isCouldPlayWithStart) {
                        CustomIjkPlayer.this.pause();
                    }
                } else if (i2 == 701) {
                    CustomIjkPlayer.this.needCountPlayTime = false;
                    CustomIjkPlayer.this.showLoadingView();
                } else if (i2 == 702) {
                    CustomIjkPlayer.this.needCountPlayTime = true;
                    CustomIjkPlayer.this.hideLoadingView();
                    if (!CustomIjkPlayer.this.isHasRendered) {
                        CustomIjkPlayer.this.isHasRendered = true;
                        CustomIjkPlayer.this.startUpdateStatusHandler();
                        if (!CustomIjkPlayer.this.isCouldAutoHide) {
                            CustomIjkPlayer.this.showControlView();
                        }
                        if (!CustomIjkPlayer.this.isCouldPlayWithStart) {
                            CustomIjkPlayer.this.pause();
                        }
                    }
                }
                if (CustomIjkPlayer.this.mOnPlayerStateListener == null || CustomIjkPlayer.this.mOnPlayerStateListener.onInfo(i2, i3)) {
                }
                return true;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                if (CustomIjkPlayer.this.mOnPlayerStateListener != null) {
                    CustomIjkPlayer.this.mOnPlayerStateListener.onPrepared(j2);
                }
                if (OKLog.D) {
                    OKLog.d(CustomIjkPlayer.TAG, "onPrepared " + j2);
                }
                if (CustomIjkPlayer.this.bottomLineProgressView.getVisibility() == 0) {
                    CustomIjkPlayer.this.ijkPlayLineProgress.setVisibility(0);
                    CustomIjkPlayer.this.ijkPlayLineProgress.setAlpha(1.0f);
                    CustomIjkPlayer.this.ijkPlayStatusLineTimeCountDown.setVisibility(0);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
                CustomIjkPlayer.this.updatePosition(r0.mIjkVideoView.getCurrentPosition());
                if (CustomIjkPlayer.this.mOnPlayerStateListener != null) {
                    CustomIjkPlayer.this.mOnPlayerStateListener.onSeekComplete();
                }
            }
        });
        PlayDurationStatistics.PlayDurationStatisticsListener playDurationStatisticsListener = new PlayDurationStatistics.PlayDurationStatisticsListener();
        this.mPlayDurationStatisticsListener = playDurationStatisticsListener;
        this.mIjkVideoView.addOnStatisticsStateListener(playDurationStatisticsListener);
    }

    private void setProgress(int i2) {
        this.ijkPlayStatusBar.setProgress(i2);
        if (this.bottomLineProgressView.getVisibility() == 8) {
            return;
        }
        this.ijkPlayLineProgress.setProgress(i2);
        IjkMediaPlayer ijkMediaPlayer = this.mIjkVideoView.getIjkMediaPlayer();
        if (ijkMediaPlayer == null) {
            return;
        }
        long propertyLong = ijkMediaPlayer.getPropertyLong(20005);
        int duration = this.mIjkVideoView.getDuration();
        if (propertyLong <= 0 || duration < 1) {
            return;
        }
        this.ijkPlayLineProgress.setSecondaryProgress(i2 + ((int) ((propertyLong * 1000) / this.mIjkVideoView.getDuration())));
    }

    private void setScreenOff() {
        if (this.isScreenOn) {
            Dialog screenOnDialog = getScreenOnDialog();
            Activity screenOnActivity = getScreenOnActivity();
            if (screenOnDialog == null) {
                if (screenOnActivity != null) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "setScreenOffActivity");
                    }
                    this.isScreenOn = false;
                    screenOnActivity.getWindow().clearFlags(128);
                    return;
                }
                return;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "setScreenOffDialog");
            }
            this.isScreenOn = false;
            if (screenOnActivity == null || screenOnActivity.isFinishing()) {
                return;
            }
            screenOnDialog.getWindow().clearFlags(128);
        }
    }

    private void setScreenOn() {
        if (this.isScreenOn) {
            return;
        }
        Dialog screenOnDialog = getScreenOnDialog();
        Activity screenOnActivity = getScreenOnActivity();
        if (screenOnDialog == null) {
            if (screenOnActivity != null) {
                if (OKLog.D) {
                    OKLog.d(TAG, "setScreenOnActivity");
                }
                this.isScreenOn = true;
                screenOnActivity.getWindow().addFlags(128);
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "setScreenOnDialog");
        }
        this.isScreenOn = true;
        if (screenOnActivity == null || screenOnActivity.isFinishing()) {
            return;
        }
        screenOnDialog.getWindow().addFlags(128);
    }

    private void setViewListener() {
        this.ijkPlayStatusBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.CustomIjkPlayer.2
            {
                CustomIjkPlayer.this = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v0 */
            /* JADX WARN: Type inference failed for: r1v1 */
            /* JADX WARN: Type inference failed for: r1v2, types: [int, boolean] */
            /* JADX WARN: Type inference failed for: r1v3 */
            /* JADX WARN: Type inference failed for: r1v4 */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i2 = CustomIjkPlayer.this.mPlayStatus;
                ?? r1 = 1;
                r1 = 1;
                r1 = 1;
                CustomIjkPlayer.this.isCouldPlayWithStart = true;
                if (TextUtils.isEmpty(CustomIjkPlayer.this.withoutPlayPath)) {
                    if (!CustomIjkPlayer.this.isComplete) {
                        if (CustomIjkPlayer.this.mIjkVideoView.isPlaying()) {
                            r1 = 0;
                            CustomIjkPlayer.this.pause();
                        } else {
                            CustomIjkPlayer.this.start();
                        }
                    } else {
                        CustomIjkPlayer.this.resume();
                    }
                } else {
                    CustomIjkPlayer customIjkPlayer = CustomIjkPlayer.this;
                    customIjkPlayer.setVideoPath(customIjkPlayer.withoutPlayPath);
                }
                if (CustomIjkPlayer.this.onPlayOrPauseListener != null) {
                    CustomIjkPlayer.this.ijkPlayStatusBtn.setTag(Integer.valueOf((int) r1));
                    CustomIjkPlayer.this.onPlayOrPauseListener.onClick(CustomIjkPlayer.this.ijkPlayStatusBtn);
                }
                if (CustomIjkPlayer.this.mMtaListener != null) {
                    CustomIjkPlayer.this.mMtaListener.playBtnOnClick(r1);
                    CustomIjkPlayer.this.mMtaListener.playBtnOnClick(r1, i2);
                }
                CustomIjkPlayer.this.showControlView();
            }
        });
        this.ijkPlayStatusBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.jingdong.common.widget.custom.CustomIjkPlayer.3
            {
                CustomIjkPlayer.this = this;
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                if (z) {
                    if (OKLog.D) {
                        OKLog.d(CustomIjkPlayer.TAG, "onProgressChanged fromUser:" + z);
                    }
                    CustomIjkPlayer.this.updatePosition((r6.mIjkVideoView.getDuration() * seekBar.getProgress()) / 1000, true);
                    CustomIjkPlayer.this.showControlView();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (OKLog.D) {
                    OKLog.d(CustomIjkPlayer.TAG, "onStartTrackingTouch");
                }
                CustomIjkPlayer.this.showControlView();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                CustomIjkPlayer.this.lastPosition = (r0.mIjkVideoView.getDuration() * seekBar.getProgress()) / 1000;
                if (OKLog.D) {
                    OKLog.d(CustomIjkPlayer.TAG, "getDuration " + CustomIjkPlayer.this.mIjkVideoView.getDuration() + " lastPosition " + CustomIjkPlayer.this.lastPosition);
                }
                CustomIjkPlayer customIjkPlayer = CustomIjkPlayer.this;
                customIjkPlayer.updatePosition(customIjkPlayer.lastPosition);
                CustomIjkPlayer.this.mIjkVideoView.seekTo((int) CustomIjkPlayer.this.lastPosition);
                if (CustomIjkPlayer.this.mMtaListener != null) {
                    CustomIjkPlayer.this.mMtaListener.seekBarOnSeek(CustomIjkPlayer.this.lastPosition);
                }
                if (CustomIjkPlayer.this.isComplete && CustomIjkPlayer.this.mPlayStatus == 3) {
                    CustomIjkPlayer.this.isComplete = false;
                    CustomIjkPlayer.this.pause();
                }
            }
        });
        setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.widget.custom.CustomIjkPlayer.4
            {
                CustomIjkPlayer.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (CustomIjkPlayer.this.isCouldAutoHide && CustomIjkPlayer.this.isHasRendered && motionEvent.getAction() == 0) {
                    if (CustomIjkPlayer.this.bottomProgressView.getVisibility() != 8) {
                        if (CustomIjkPlayer.this.mIjkVideoView.isPlaying()) {
                            CustomIjkPlayer.this.hideControlView();
                        }
                    } else {
                        CustomIjkPlayer.this.showControlView();
                    }
                }
                if (OKLog.D) {
                    OKLog.d(CustomIjkPlayer.TAG, "onTouch " + CustomIjkPlayer.this.isCouldAutoHide + LangUtils.SINGLE_SPACE + CustomIjkPlayer.this.isHasRendered + motionEvent.getAction() + LangUtils.SINGLE_SPACE + CustomIjkPlayer.this.bottomProgressView.getVisibility() + LangUtils.SINGLE_SPACE + CustomIjkPlayer.this.mIjkVideoView.isPlaying());
                    return false;
                }
                return false;
            }
        });
        this.ijkPlayVoiceBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.CustomIjkPlayer.5
            {
                CustomIjkPlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomIjkPlayer.this.changeVoiceState(!r2.isVoiceOn);
                if (CustomIjkPlayer.this.mMtaListener != null) {
                    CustomIjkPlayer.this.mMtaListener.voiceBtnOnClick(!CustomIjkPlayer.this.isVoiceOn);
                }
            }
        });
    }

    public void showBgImg_hideVideoView() {
        IjkVideoViewWithReport ijkVideoViewWithReport = this.mIjkVideoView;
        if (ijkVideoViewWithReport != null && this.mBgImgSuccess && ijkVideoViewWithReport.getVisibility() == 0) {
            this.mIjkVideoView.setVisibility(4);
        }
    }

    private void showVideoView_hideBgImg() {
        IjkVideoViewWithReport ijkVideoViewWithReport = this.mIjkVideoView;
        if (ijkVideoViewWithReport == null || ijkVideoViewWithReport.getVisibility() == 0) {
            return;
        }
        this.mIjkVideoView.setVisibility(0);
    }

    public void startUpdateStatusHandler() {
        if (this.mHandler == null) {
            this.mHandler = new MyHandler(this);
        }
        updatePosition(-1L);
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, 1000L);
    }

    public void stopUpdateStatusHandler() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(1);
        }
        setScreenOff();
    }

    private String stringForTime(long j2) {
        if (this.mFormatBuilder == null) {
            this.mFormatBuilder = new StringBuilder();
            this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
        }
        long j3 = j2 / 1000;
        long j4 = j3 % 60;
        long j5 = (j3 / 60) % 60;
        long j6 = j3 / 3600;
        this.mFormatBuilder.setLength(0);
        return j6 > 0 ? this.mFormatter.format("%d:%02d:%02d", Long.valueOf(j6), Long.valueOf(j5), Long.valueOf(j4)).toString() : this.mFormatter.format("%02d:%02d", Long.valueOf(j5), Long.valueOf(j4)).toString();
    }

    public void tryToShow3GTip() {
        MtaListener mtaListener = this.mMtaListener;
        if (mtaListener == null || !mtaListener.tryToShow3GTip()) {
            if (!NetUtils.isNetworkAvailable()) {
                ToastUtils.shortToast(R.string.custom_ijk_player_error_net);
            } else if (NetUtils.isWifi()) {
            } else {
                MtaListener mtaListener2 = this.mMtaListener;
                String customNonWifiNetTip = mtaListener2 != null ? mtaListener2.customNonWifiNetTip() : null;
                if (TextUtils.isEmpty(customNonWifiNetTip)) {
                    customNonWifiNetTip = getResources().getString(R.string.custom_ijk_player_net_tip);
                }
                ToastUtils.shortToast(customNonWifiNetTip);
            }
        }
    }

    private void unregisterNetworkStatus() {
        if (this.myNetReceiver == null) {
            return;
        }
        try {
            JdSdk.getInstance().getApplication().unregisterReceiver(this.myNetReceiver);
            this.myNetReceiver = null;
        } catch (IllegalArgumentException e2) {
            OKLog.e(TAG, e2);
        }
    }

    public void updatePosition(long j2) {
        updatePosition(j2, false);
    }

    public void addBottomControlView() {
        if (this.controlViewParent != null && this.bottomProgressView.getParent() == null) {
            this.controlViewParent.addView(this.bottomProgressView);
        }
        if (this.playStatusParent != null && this.ijkPlayStatusBtn.getParent() == null) {
            this.playStatusParent.addView(this.ijkPlayStatusBtn);
        }
        this.ijkBtnBg.setAlpha(1.0f);
    }

    public void addOnStatisticsStateListener(IPlayerControl.OnStatisticsStateListener onStatisticsStateListener) {
        this.mIjkVideoView.addOnStatisticsStateListener(onStatisticsStateListener);
    }

    public void changeVoiceState(boolean z) {
        if (this.isVoiceOn == z) {
            return;
        }
        MtaListener mtaListener = this.mMtaListener;
        if (mtaListener != null) {
            mtaListener.voiceStateChange(z);
        }
        this.isVoiceOn = z;
        this.ijkPlayVoiceBtn.setImageResource(z ? R.drawable.ic_ijk_player_voice_on : R.drawable.ic_ijk_player_voice_off);
        if (this.mIjkVideoView.getPlayerOptions() != null) {
            this.mIjkVideoView.setVolume(z ? 1.0f : 0.0f);
        }
        getVodPlayerOptions().setVolume(z ? 1.0f : 0.0f);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (this.isCouldAutoHide) {
            return true;
        }
        return dispatchTouchEvent;
    }

    public void forceRelease(boolean z) {
        this.mIjkVideoView.forceRelase(z);
    }

    public View getBgImg() {
        return this.bgImg;
    }

    public long getCurrentPosition() {
        return this.mIjkVideoView.getCurrentPosition();
    }

    public long getDuration() {
        return this.mIjkVideoView.getDuration();
    }

    public SeekBar getIjkPlayStatusBar() {
        return this.ijkPlayStatusBar;
    }

    public boolean getIsForceLayout() {
        return this.isForceLayout;
    }

    public TextView getLoadingText() {
        return (TextView) findViewById(R.id.ijk_loading_text);
    }

    public long getPlayDuration() {
        PlayDurationStatistics.PlayDurationStatisticsListener playDurationStatisticsListener = this.mPlayDurationStatisticsListener;
        if (playDurationStatisticsListener == null) {
            return 0L;
        }
        return playDurationStatisticsListener.getPlayDuration();
    }

    public long getTcpSpeed() {
        return this.mIjkVideoView.getTcpSpeed();
    }

    public View getVideoBtnBg() {
        return this.ijkBtnBg;
    }

    public IjkVideoView getVideoView() {
        return this.mIjkVideoView;
    }

    public FrameLayout getVideoViewWrapper() {
        return this.mIjkVideoViewWrapper;
    }

    public IPlayerControl.PlayerOptions getVodPlayerOptions() {
        if (OKLog.D) {
            OKLog.d(TAG, "getVodPlayerOptions");
        }
        IPlayerControl.PlayerOptions playerOptions = this.playerOptions;
        if (playerOptions != null) {
            return playerOptions;
        }
        IPlayerControl.PlayerOptions playerOptions2 = new IPlayerControl.PlayerOptions(this.isLivePlay);
        this.playerOptions = playerOptions2;
        if (OKLog.D) {
            playerOptions2.setDebugLog(true);
        }
        this.playerOptions.setIpv6VideoPlay(SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.VIDEO_IPV6_SWITCH, false));
        int i2 = this.mAspectRatio;
        if (i2 != -1) {
            this.playerOptions.setAspectRatio(i2);
        }
        return this.playerOptions;
    }

    public void hideControlView() {
        this.ijkBtnBg.setVisibility(8);
        this.bottomProgressView.setVisibility(8);
        View view = this.topHeaderView;
        if (view != null) {
            view.setVisibility(8);
        }
        setPlayStatusBtnVisibility();
    }

    public void hideFullBtn() {
        this.ijkPlayFullBtn.getLayoutParams().width = 1;
        this.ijkPlayFullBtn.requestLayout();
        this.ijkPlayFullBtn.setClickable(false);
    }

    public void hideLineStyleProgress() {
        this.bottomLineProgressView.setVisibility(8);
        addBottomControlView();
    }

    public void hideLineStyleProgressText() {
        this.ijkPlayStatusLineTimeCountDown.setAlpha(0.0f);
    }

    public void hideLoadingView() {
        if (this.mLoadingStyle == 1) {
            this.loadingViewCustom.setVisibility(8);
            AnimatorSet animatorSet = this.loadingAnimatorSet;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
        } else {
            this.loadingView.setVisibility(this.isForceLayout ? 4 : 8);
        }
        setPlayStatusBtnVisibility();
    }

    public void hideVoiceBtn() {
        this.ijkPlayVoiceBtn.setVisibility(8);
    }

    public void initRenders() {
        if (OKLog.D) {
            OKLog.d(TAG, "initRenders");
        }
        this.mIjkVideoView.initRenders();
        if (this.isForceLayout) {
            requestLayout();
        }
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    @Deprecated
    public boolean isFullScreen() {
        return this.isFullScreen;
    }

    public boolean isHasRendered() {
        return this.isHasRendered;
    }

    public boolean isPlaying() {
        return this.mIjkVideoView.isPlaying();
    }

    @Deprecated
    public void noFullScreen(Activity activity, FrameLayout frameLayout, int i2) {
        if (activity == null || frameLayout == null) {
            return;
        }
        this.isFullScreen = false;
        showFullBtn();
        setUiFullScreenState(false);
        activity.setRequestedOrientation(1);
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.flags &= -1025;
        activity.getWindow().setAttributes(attributes);
        activity.getWindow().clearFlags(512);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = i2;
        frameLayout.setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0 && this.isForceLayout) {
            requestLayout();
        }
    }

    public void pause() {
        if (OKLog.D) {
            OKLog.d(TAG, "pause");
        }
        this.isCouldPlayWithStart = false;
        setPlayStatus(2);
        this.mIjkVideoView.pause();
        if (this.withoutPlayPath == null) {
            showControlView();
        }
    }

    public void registerVoiceReceiver() {
        if (this.voiceStateReceiver != null) {
            return;
        }
        this.voiceStateReceiver = new BroadcastReceiver() { // from class: com.jingdong.common.widget.custom.CustomIjkPlayer.8
            {
                CustomIjkPlayer.this = this;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if (action.equals("android.intent.action.HEADSET_PLUG")) {
                    int intExtra = intent.getIntExtra(XView2Constants.STATE, -1);
                    if (intExtra != 1) {
                        if (intExtra == 0 && (CustomIjkPlayer.this.voiceRegisterTime <= 0 || System.currentTimeMillis() - CustomIjkPlayer.this.voiceRegisterTime >= 500)) {
                            CustomIjkPlayer.this.changeVoiceState(false);
                        }
                    } else {
                        CustomIjkPlayer.this.changeVoiceState(true);
                    }
                    CustomIjkPlayer.this.voiceRegisterTime = -1L;
                } else if (action.equals("android.media.VOLUME_CHANGED_ACTION")) {
                    CustomIjkPlayer.this.changeVoiceState(true);
                }
                if (OKLog.D) {
                    OKLog.d(CustomIjkPlayer.TAG, "action: " + action);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
        this.voiceRegisterTime = System.currentTimeMillis();
        JdSdk.getInstance().getApplication().registerReceiver(this.voiceStateReceiver, intentFilter);
    }

    @Deprecated
    public void release() {
        if (OKLog.D) {
            OKLog.d(TAG, "release");
        }
        releaseInThread(false);
    }

    public void releaseInThread() {
        if (OKLog.D) {
            OKLog.d(TAG, "releaseInThread");
        }
        releaseInThread(true);
    }

    public void removeBottomControlView() {
        ViewParent parent = this.bottomProgressView.getParent();
        if (parent != null) {
            ViewGroup viewGroup = (ViewGroup) parent;
            this.controlViewParent = viewGroup;
            viewGroup.removeView(this.bottomProgressView);
        }
        ViewParent parent2 = this.ijkPlayStatusBtn.getParent();
        if (parent2 != null) {
            ViewGroup viewGroup2 = (ViewGroup) parent2;
            this.playStatusParent = viewGroup2;
            viewGroup2.removeView(this.ijkPlayStatusBtn);
        }
        this.ijkBtnBg.setAlpha(0.0f);
    }

    @Deprecated
    public void removePlayBtn() {
        ViewParent parent = this.ijkPlayStatusBtn.getParent();
        if (parent != null) {
            ViewGroup viewGroup = (ViewGroup) parent;
            this.playStatusParent = viewGroup;
            viewGroup.removeView(this.ijkPlayStatusBtn);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        if (this.isForceLayout) {
            post(this.measureAndLayout);
        }
    }

    public void resetCountdown() {
        this.timesToHideControlView = 2;
    }

    public void resume() {
        if (OKLog.D) {
            OKLog.d(TAG, JsApiLivePlayer.CM_RESUME);
        }
        this.isCouldPlayWithStart = true;
        this.isComplete = false;
        this.isHasRendered = false;
        suspend();
        initRenders();
        tryToShow3GTip();
        showLoadingView();
        hideControlView();
        setPlayStatus(1);
        setProgress(0);
        setPlayStatusTimeText(0L, 0L);
    }

    public void seekTo(int i2) {
        updatePosition(this.lastPosition);
        this.mIjkVideoView.seekTo(i2);
    }

    public void setAspectRatio(int i2) {
        this.mAspectRatio = i2;
        this.mIjkVideoView.setAspectRatio(i2);
    }

    public void setAutoHideHeaderBar(View view) {
        View view2;
        this.topHeaderView = view;
        if (view == null || (view2 = this.bottomProgressView) == null) {
            return;
        }
        view.setVisibility(view2.getVisibility());
    }

    public void setBgImg(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.showImageOnFail(i2);
        jDDisplayImageOptions.showImageOnLoading(i2);
        jDDisplayImageOptions.showImageForEmptyUri(i2);
        JDImageUtils.displayImage(str, this.bgImg, jDDisplayImageOptions);
        this.mBgImgSuccess = true;
    }

    public void setBottomControlViewRightPadding(int i2) {
        this.bottomProgressView.setPadding(0, 0, i2, 0);
    }

    public void setCouldAutoHide(boolean z) {
        this.isCouldAutoHide = z;
    }

    public void setEnableVideoBtnBg(boolean z) {
        this.showIjkBtnBg = z;
        this.ijkBtnBg.setVisibility(z ? 0 : 8);
    }

    public void setFullBtnOnClickListener(View.OnClickListener onClickListener) {
        this.ijkPlayFullBtn.setOnClickListener(onClickListener);
    }

    @Deprecated
    public void setFullScreen(Activity activity, FrameLayout frameLayout) {
        if (activity == null || frameLayout == null) {
            return;
        }
        this.isFullScreen = true;
        activity.setRequestedOrientation(0);
        activity.getWindow().setFlags(1024, 1024);
        activity.getWindow().getDecorView().invalidate();
        hideFullBtn();
        setUiFullScreenState(true);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = DPIUtil.getDefaultDisplay().getHeight();
        frameLayout.setLayoutParams(layoutParams);
    }

    public void setIsForceLayout(boolean z) {
        this.isForceLayout = z;
    }

    public void setKeepScreenOnActivity(Activity activity) {
        this.isScreenOn = false;
        if (activity == null) {
            this.screenOnActivity = null;
        } else {
            this.screenOnActivity = new WeakReference<>(activity);
        }
    }

    public void setKeepScreenOnDialog(Dialog dialog) {
        this.isScreenOn = false;
        if (dialog == null) {
            this.screenOnDialog = null;
        } else {
            this.screenOnDialog = new WeakReference<>(dialog);
        }
    }

    public void setLivePlay(boolean z) {
        this.isLivePlay = z;
    }

    public void setLoadingStyle(int i2) {
        this.mLoadingStyle = i2;
    }

    public void setMtaListener(MtaListener mtaListener) {
        this.mMtaListener = mtaListener;
    }

    public void setMtaPageParams(String str, String str2, String str3) {
        this.page_param = str + CartConstant.KEY_YB_INFO_LINK + str2 + CartConstant.KEY_YB_INFO_LINK + str3;
    }

    public void setMtaParams(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            if (OKLog.D) {
                OKLog.e(TAG, "must set mtaSource");
                return;
            }
            return;
        }
        this.event_param = str2 + CartConstant.KEY_YB_INFO_LINK + str3 + CartConstant.KEY_YB_INFO_LINK + str4 + CartConstant.KEY_YB_INFO_LINK + str;
    }

    @Deprecated
    public void setOnPlayOrPauseListener(View.OnClickListener onClickListener) {
        this.onPlayOrPauseListener = onClickListener;
    }

    public void setOnPlayerStateListener(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
        this.mOnPlayerStateListener = onPlayerStateListener;
    }

    public void setOnUpdatePositionListener(OnUpdatePositionListener onUpdatePositionListener) {
        this.mOnUpdatePositionListener = onUpdatePositionListener;
    }

    public void setReportParams(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4) {
        this.mIjkVideoView.setRealPlayTime(this.playTime);
        this.mIjkVideoView.setReportParams(str, str2, str3, str4);
        this.playTime = 0;
    }

    public void setRequestUrlTime(long j2) {
        this.mIjkVideoView.setRequestUrlTime(j2);
    }

    public void setUiFullScreenState(boolean z) {
        this.ijkPlayStatusText.setTextSize(z ? 16.0f : 13.0f);
        this.ijkPlayStatusImg.getLayoutParams().height = DPIUtil.dip2px(z ? 60.0f : 50.0f);
        this.ijkPlayStatusImg.requestLayout();
        this.bottomViewProgressLayout.getLayoutParams().height = DPIUtil.dip2px(z ? 64.0f : 35.0f);
        this.bottomViewProgressLayout.requestLayout();
        this.ijkPlayVoiceBtn.getLayoutParams().height = DPIUtil.dip2px(z ? 33.0f : 26.0f);
        this.ijkPlayVoiceBtn.requestLayout();
        this.ijkPlayFullBtn.setImageResource(z ? R.drawable.ic_ijk_player_full_quit : R.drawable.ic_ijk_player_full);
        this.ijkPlayStatusBar.setThumb(getResources().getDrawable(z ? R.drawable.ic_ijk_player_bar_thumb_big : R.drawable.ic_ijk_player_bar_thumb));
    }

    public void setVideoPath(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "setVideoPath " + str);
        }
        setVideoPath(str, 0L);
    }

    public void setVideoPathWithOutAutoPlay(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "setVideoPathWithOutAutoPlay " + str);
        }
        this.withoutPlayPath = str;
        this.isCouldPlayWithStart = true;
        this.isHasRendered = false;
        stopUpdateStatusHandler();
        showControlView();
        hideLoadingView();
        this.bottomProgressView.setVisibility(8);
        setPlayStatus(0);
        setProgress(0);
        setPlayStatusTimeText(0L, 0L);
    }

    public void showControlView() {
        this.timesToHideControlView = 2;
        if (this.showIjkBtnBg) {
            this.ijkBtnBg.setVisibility(0);
        }
        this.bottomProgressView.setVisibility(0);
        View view = this.topHeaderView;
        if (view != null) {
            view.setVisibility(0);
        }
        setPlayStatusBtnVisibility();
    }

    public void showFullBtn() {
        this.ijkPlayFullBtn.getLayoutParams().width = DPIUtil.dip2px(35.0f);
        this.ijkPlayFullBtn.requestLayout();
        this.ijkPlayFullBtn.setClickable(true);
    }

    public void showLineStyleProgerss() {
        this.bottomLineProgressView.setVisibility(0);
        ViewParent parent = this.bottomProgressView.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(this.bottomProgressView);
        }
    }

    public void showLoadingView() {
        if (this.mLoadingStyle == 1) {
            this.loadingView.setVisibility(8);
            this.loadingViewCustom.setVisibility(0);
            doLoadingAnimation();
        } else {
            this.loadingViewCustom.setVisibility(8);
            this.loadingView.setVisibility(0);
        }
        setPlayStatusBtnVisibility();
    }

    public void showVoiceBtn() {
        this.ijkPlayVoiceBtn.setVisibility(0);
    }

    public void start() {
        if (OKLog.D) {
            OKLog.d(TAG, "start");
        }
        this.isCouldPlayWithStart = true;
        this.mIjkVideoView.start();
        setPlayStatus(1);
        this.timesToHideControlView = 2;
    }

    public void suspend() {
        if (OKLog.D) {
            OKLog.d(TAG, "suspend");
        }
        stopUpdateStatusHandler();
        setPlayStatus(2);
        this.mIjkVideoView.suspend();
        showControlView();
    }

    public void unregisterVoiceReceiver() {
        if (this.voiceStateReceiver == null) {
            return;
        }
        try {
            JdSdk.getInstance().getApplication().unregisterReceiver(this.voiceStateReceiver);
            this.voiceStateReceiver = null;
        } catch (IllegalArgumentException e2) {
            OKLog.e(TAG, e2);
        }
    }

    public void updatePosition(long j2, boolean z) {
        OnUpdatePositionListener onUpdatePositionListener;
        boolean isPlaying = this.mIjkVideoView.isPlaying();
        int duration = this.mIjkVideoView.getDuration();
        if (duration <= 0) {
            duration = 1;
        }
        if (j2 == -1) {
            if (this.needCountPlayTime && isPlaying) {
                this.playTime++;
            }
            this.lastPosition = this.mIjkVideoView.getCurrentPosition();
        } else {
            this.lastPosition = j2;
        }
        long j3 = duration;
        int i2 = (int) ((this.lastPosition * 1000) / j3);
        if (OKLog.D) {
            OKLog.d(TAG, "updatePosition " + this.lastPosition + " progress " + i2);
        }
        setProgress(i2);
        setPlayStatusTimeText(this.lastPosition, j3);
        if (isPlaying) {
            setScreenOn();
        } else {
            setScreenOff();
        }
        if (!z && (onUpdatePositionListener = this.mOnUpdatePositionListener) != null) {
            onUpdatePositionListener.updatePosition(isPlaying, this.lastPosition, j3);
        }
        OnUpdatePositionListener onUpdatePositionListener2 = this.mOnUpdatePositionListener;
        if (onUpdatePositionListener2 != null) {
            onUpdatePositionListener2.updatePosition(isPlaying, this.lastPosition, j3, z);
        }
    }

    private void releaseInThread(boolean z) {
        stopUpdateStatusHandler();
        this.mIjkVideoView.setRealPlayTime(this.playTime);
        if (z) {
            this.mIjkVideoView.releaseInThread(true);
        } else {
            this.mIjkVideoView.release();
        }
        this.playTime = 0;
        unregisterNetworkStatus();
        unregisterVoiceReceiver();
    }

    public void setVideoPath(String str, long j2) {
        if (OKLog.D) {
            OKLog.d(TAG, "setVideoPath a " + str);
        }
        stopUpdateStatusHandler();
        this.lastPosition = 0L;
        this.isCouldPlayWithStart = true;
        this.isComplete = false;
        this.isHasRendered = false;
        if (this.mPath == null) {
            this.mIjkVideoView.setPlayerOptions(getVodPlayerOptions());
            this.mIjkVideoView.setRealPlayTime(this.playTime);
            this.mIjkVideoView.setVideoPath(str);
            this.playTime = 0;
        } else {
            suspend();
            this.mIjkVideoView.setVideoPathWithoutOpen(str);
            initRenders();
        }
        this.mIjkVideoView.seekTo((int) j2);
        this.mPath = str;
        this.withoutPlayPath = null;
        tryToShow3GTip();
        setPlayStatus(1);
        setProgress(0);
        setPlayStatusTimeText(0L, 0L);
    }

    public void setReportParams(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8) {
        setMtaPageParams(str2, str8, str7);
        this.mIjkVideoView.setRealPlayTime(this.playTime);
        this.mIjkVideoView.setReportParams(str, str2, str3, str4, str5, str6, str7, str8);
        this.playTime = 0;
    }

    public void setBgImg(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDImageUtils.displayImage(str, this.bgImg);
        this.mBgImgSuccess = true;
    }

    public void setReportParams(VideoInfoEntity videoInfoEntity) {
        this.mIjkVideoView.setRealPlayTime(this.playTime);
        this.mIjkVideoView.setReportParams(videoInfoEntity);
        this.playTime = 0;
    }

    public CustomIjkPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPlayStatus = 0;
        this.topHeaderView = null;
        this.withoutPlayPath = null;
        this.mPath = null;
        this.isComplete = false;
        this.isHasRendered = false;
        this.isCouldAutoHide = true;
        this.isCouldPlayWithStart = true;
        this.isVoiceOn = true;
        this.showIjkBtnBg = true;
        this.playTime = 0;
        this.needCountPlayTime = false;
        this.isLivePlay = false;
        this.mBgImgSuccess = false;
        this.timesToHideControlView = -1;
        this.mAspectRatio = -1;
        this.voiceRegisterTime = -1L;
        this.isScreenOn = false;
        this.screenOnActivity = null;
        this.screenOnDialog = null;
        this.event_param = null;
        this.page_param = "";
        this.isForceLayout = false;
        this.measureAndLayout = new Runnable() { // from class: com.jingdong.common.widget.custom.CustomIjkPlayer.9
            {
                CustomIjkPlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                CustomIjkPlayer customIjkPlayer = CustomIjkPlayer.this;
                customIjkPlayer.measure(View.MeasureSpec.makeMeasureSpec(customIjkPlayer.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(CustomIjkPlayer.this.getHeight(), 1073741824));
                CustomIjkPlayer customIjkPlayer2 = CustomIjkPlayer.this;
                customIjkPlayer2.layout(customIjkPlayer2.getLeft(), CustomIjkPlayer.this.getTop(), CustomIjkPlayer.this.getRight(), CustomIjkPlayer.this.getBottom());
            }
        };
        initView(context);
    }

    public CustomIjkPlayer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mPlayStatus = 0;
        this.topHeaderView = null;
        this.withoutPlayPath = null;
        this.mPath = null;
        this.isComplete = false;
        this.isHasRendered = false;
        this.isCouldAutoHide = true;
        this.isCouldPlayWithStart = true;
        this.isVoiceOn = true;
        this.showIjkBtnBg = true;
        this.playTime = 0;
        this.needCountPlayTime = false;
        this.isLivePlay = false;
        this.mBgImgSuccess = false;
        this.timesToHideControlView = -1;
        this.mAspectRatio = -1;
        this.voiceRegisterTime = -1L;
        this.isScreenOn = false;
        this.screenOnActivity = null;
        this.screenOnDialog = null;
        this.event_param = null;
        this.page_param = "";
        this.isForceLayout = false;
        this.measureAndLayout = new Runnable() { // from class: com.jingdong.common.widget.custom.CustomIjkPlayer.9
            {
                CustomIjkPlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                CustomIjkPlayer customIjkPlayer = CustomIjkPlayer.this;
                customIjkPlayer.measure(View.MeasureSpec.makeMeasureSpec(customIjkPlayer.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(CustomIjkPlayer.this.getHeight(), 1073741824));
                CustomIjkPlayer customIjkPlayer2 = CustomIjkPlayer.this;
                customIjkPlayer2.layout(customIjkPlayer2.getLeft(), CustomIjkPlayer.this.getTop(), CustomIjkPlayer.this.getRight(), CustomIjkPlayer.this.getBottom());
            }
        };
        initView(context);
    }

    @TargetApi(21)
    public CustomIjkPlayer(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mPlayStatus = 0;
        this.topHeaderView = null;
        this.withoutPlayPath = null;
        this.mPath = null;
        this.isComplete = false;
        this.isHasRendered = false;
        this.isCouldAutoHide = true;
        this.isCouldPlayWithStart = true;
        this.isVoiceOn = true;
        this.showIjkBtnBg = true;
        this.playTime = 0;
        this.needCountPlayTime = false;
        this.isLivePlay = false;
        this.mBgImgSuccess = false;
        this.timesToHideControlView = -1;
        this.mAspectRatio = -1;
        this.voiceRegisterTime = -1L;
        this.isScreenOn = false;
        this.screenOnActivity = null;
        this.screenOnDialog = null;
        this.event_param = null;
        this.page_param = "";
        this.isForceLayout = false;
        this.measureAndLayout = new Runnable() { // from class: com.jingdong.common.widget.custom.CustomIjkPlayer.9
            {
                CustomIjkPlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                CustomIjkPlayer customIjkPlayer = CustomIjkPlayer.this;
                customIjkPlayer.measure(View.MeasureSpec.makeMeasureSpec(customIjkPlayer.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(CustomIjkPlayer.this.getHeight(), 1073741824));
                CustomIjkPlayer customIjkPlayer2 = CustomIjkPlayer.this;
                customIjkPlayer2.layout(customIjkPlayer2.getLeft(), CustomIjkPlayer.this.getTop(), CustomIjkPlayer.this.getRight(), CustomIjkPlayer.this.getBottom());
            }
        };
        initView(context);
    }
}
