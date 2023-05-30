package com.jingdong.common.jdreactFramework.views.webview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.uimanager.ThemedReactContext;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.ToastUtil;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Formatter;
import java.util.Locale;

/* loaded from: classes5.dex */
public class JDReactVideoPlayer extends FrameLayout implements View.OnClickListener, View.OnTouchListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, EventListener {
    public static final String FROM_BABEL = "babel";
    private static final int HIDE_WHAT = 1;
    private static final int SHOW_PROGRESS = 0;
    private static final int SHOW_TIME_MAX = 4000;
    private ViewGroup container;
    public int currentPlayPosition;
    int duration;
    private JDDialog error3GDialog;
    private View errorView;
    int errorview_bottom;
    int errorview_des_bottom;
    int errorview_des_left;
    int errorview_des_right;
    int errorview_des_top;
    int errorview_img_bottom;
    int errorview_img_left;
    int errorview_img_right;
    int errorview_img_top;
    int errorview_left;
    int errorview_right;
    int errorview_top;
    private boolean firstRun;
    int fullscreen_bottom;
    int fullscreen_left;
    int fullscreen_right;
    int fullscreen_top;
    Handler handleProgress;
    private Handler handler;
    public boolean isForcePlay;
    private boolean isFullScreen;
    private boolean isInitScreen;
    private boolean isOnPrepared;
    private boolean isOver;
    private OrientationSensorListener listener;
    private OrientationSensorListener2 listener1;
    private ProgressBar loadingBar;
    private Activity mActivity;
    private LinearLayout mBottomView;
    int mBottomView_bottom;
    int mBottomView_left;
    int mBottomView_right;
    int mBottomView_top;
    private RelativeLayout mClose;
    private Context mContext;
    private TextView mCurrentTime;
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;
    private ImageButton mFullScreen;
    private HideFullScreenListener mHideFullScreenListener;
    private Bitmap mNormalBitmap;
    private ImageButton mPlay;
    private SeekBar mPlaySeekbar;
    private PopupWindow mPopupWindow;
    private LinearLayout mRestart;
    private View mRightArea;
    private TextView mShowTime;
    private RelativeLayout mTopView;
    private TextView mTotalTime;
    private Uri mUri;
    private JDReactVideoView mVideoView;
    private SimpleDraweeView mVidoeCover;
    private boolean onWindowVisibility;
    private String paramId;
    JDReactVideoPlayer player;
    int restart_bottom;
    int restart_left;
    int restart_right;
    int restart_top;
    int rightArea_bottom;
    int rightArea_left;
    int rightArea_right;
    int rightArea_top;
    private RelativeLayout rootView;
    int rootview_bottom;
    int rootview_left;
    int rootview_right;
    int rootview_top;
    int seekbar_bottom;
    int seekbar_left;
    int seekbar_right;
    int seekbar_top;
    private Sensor sensor;
    private Sensor sensor1;
    private boolean sensor_flag;
    private SensorManager sm;
    private SensorManager sm1;
    public String sourceFrom;
    private String sourcePage;
    private boolean stretch_flag;
    int totaltime_bottom;
    int totaltime_left;
    int totaltime_right;
    int totaltime_top;
    int videocover_bottom;
    int videocover_left;
    int videocover_right;
    int videocover_top;
    int videoview_bottom;
    int videoview_left;
    int videoview_right;
    int videoview_top;
    private String vidoeId;

    /* loaded from: classes5.dex */
    public class AniListener implements Animation.AnimationListener {
        private int tag;

        public AniListener(int i2) {
            JDReactVideoPlayer.this = r1;
            this.tag = -1;
            this.tag = i2;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            int i2 = this.tag;
            if (i2 == 0) {
                JDReactVideoPlayer.this.mTopView.setVisibility(0);
                JDReactVideoPlayer.this.mBottomView.setVisibility(0);
            } else if (i2 != 1) {
            } else {
                JDReactVideoPlayer.this.mTopView.setVisibility(8);
                JDReactVideoPlayer.this.mBottomView.setVisibility(8);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* loaded from: classes5.dex */
    public interface HideFullScreenListener {
        void closeClicked();
    }

    /* loaded from: classes5.dex */
    public class OrientationSensorListener implements SensorEventListener {
        public static final int ORIENTATION_UNKNOWN = -1;
        private static final int _DATA_X = 0;
        private static final int _DATA_Y = 1;
        private static final int _DATA_Z = 2;
        private Handler rotateHandler;

        public OrientationSensorListener(Handler handler) {
            JDReactVideoPlayer.this = r1;
            this.rotateHandler = handler;
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (JDReactVideoPlayer.this.sensor_flag != JDReactVideoPlayer.this.stretch_flag) {
                float[] fArr = sensorEvent.values;
                int i2 = -1;
                float f2 = -fArr[0];
                float f3 = -fArr[1];
                float f4 = -fArr[2];
                if (((f2 * f2) + (f3 * f3)) * 4.0f >= f4 * f4) {
                    int round = 90 - Math.round(((float) Math.atan2(-f3, f2)) * 57.29578f);
                    while (round >= 360) {
                        round -= 360;
                    }
                    i2 = round;
                    while (i2 < 0) {
                        i2 += R2.attr.additionBottom;
                    }
                }
                Handler handler = this.rotateHandler;
                if (handler != null) {
                    handler.obtainMessage(R2.attr.fat_aar_excluded_headerBackground, i2, 0).sendToTarget();
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    public class OrientationSensorListener2 implements SensorEventListener {
        public static final int ORIENTATION_UNKNOWN = -1;
        private static final int _DATA_X = 0;
        private static final int _DATA_Y = 1;
        private static final int _DATA_Z = 2;

        public OrientationSensorListener2() {
            JDReactVideoPlayer.this = r1;
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            int i2;
            float[] fArr = sensorEvent.values;
            float f2 = -fArr[0];
            float f3 = -fArr[1];
            float f4 = -fArr[2];
            if (((f2 * f2) + (f3 * f3)) * 4.0f >= f4 * f4) {
                i2 = 90 - Math.round(((float) Math.atan2(-f3, f2)) * 57.29578f);
                while (i2 >= 360) {
                    i2 -= 360;
                }
                while (i2 < 0) {
                    i2 += R2.attr.additionBottom;
                }
            } else {
                i2 = -1;
            }
            if (i2 > 45 && i2 < 135) {
                JDReactVideoPlayer.this.sensor_flag = false;
            } else if (i2 > 135 && i2 < 225) {
                JDReactVideoPlayer.this.sensor_flag = true;
            } else if (i2 > 225 && i2 < 315) {
                JDReactVideoPlayer.this.sensor_flag = false;
            } else if ((i2 > 315 && i2 < 360) || (i2 > 0 && i2 < 45)) {
                JDReactVideoPlayer.this.sensor_flag = true;
            }
            if (JDReactVideoPlayer.this.stretch_flag != JDReactVideoPlayer.this.sensor_flag || JDReactVideoPlayer.this.sm == null) {
                return;
            }
            JDReactVideoPlayer.this.sm.registerListener(JDReactVideoPlayer.this.listener, JDReactVideoPlayer.this.sensor, 2);
        }
    }

    /* loaded from: classes5.dex */
    public class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int[] location = new int[2];
        int progress;

        SeekBarChangeEvent() {
            JDReactVideoPlayer.this = r1;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            JDReactVideoPlayer jDReactVideoPlayer = JDReactVideoPlayer.this;
            if (jDReactVideoPlayer.duration < 0) {
                jDReactVideoPlayer.duration = jDReactVideoPlayer.mVideoView.getDuration();
            }
            this.progress = (JDReactVideoPlayer.this.duration * i2) / seekBar.getMax();
            OKLog.d("Player", "onProgressChanged" + this.progress + LangUtils.SINGLE_SPACE + i2);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            OKLog.d("Player", "onStartTrackingTouch");
            JDReactVideoPlayer.this.handleProgress.removeMessages(0);
            JDReactVideoPlayer.this.handleProgress.removeMessages(1);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            OKLog.d("Player", "onStopTrackingTouch" + this.progress);
            JDReactVideoPlayer jDReactVideoPlayer = JDReactVideoPlayer.this;
            jDReactVideoPlayer.currentPlayPosition = this.progress;
            jDReactVideoPlayer.mVideoView.seekTo(this.progress);
            JDReactVideoPlayer.this.mCurrentTime.setText(JDReactVideoPlayer.this.stringForTime(r2.mVideoView.getCurrentPosition()));
            OKLog.d("Player", "onStopTrackingTouch" + JDReactVideoPlayer.this.mVideoView.getCurrentPosition());
            JDReactVideoPlayer.this.handleProgress.sendEmptyMessage(0);
            Handler handler = JDReactVideoPlayer.this.handleProgress;
            handler.sendMessageDelayed(handler.obtainMessage(1), 4001L);
            if ("babel".equals(JDReactVideoPlayer.this.sourceFrom) && JDReactVideoPlayer.this.mActivity != null) {
                JDMtaUtils.onClick(JDReactVideoPlayer.this.mActivity, "Babel_VideoDrag", JDReactVideoPlayer.this.sourcePage, JDReactVideoPlayer.this.vidoeId, JDReactVideoPlayer.this.paramId);
            }
            if (JDReactVideoPlayer.this.getVideoView().isPause()) {
                if (JDReactVideoPlayer.this.isOver) {
                    JDReactVideoPlayer.this.play();
                    JDReactVideoPlayer.this.isOver = false;
                }
            } else if (JDReactVideoPlayer.this.getVideoView().isPlaying() || !JDReactVideoPlayer.this.isOver) {
            } else {
                JDReactVideoPlayer.this.isOver = false;
                JDReactVideoPlayer.this.start();
            }
        }
    }

    public JDReactVideoPlayer(Context context) {
        super(context);
        this.rightArea_top = 0;
        this.rightArea_bottom = 0;
        this.rightArea_left = 0;
        this.rightArea_right = 0;
        this.rootview_top = 0;
        this.rootview_bottom = 0;
        this.rootview_left = 0;
        this.rootview_right = 0;
        this.restart_top = 0;
        this.restart_bottom = 0;
        this.restart_left = 0;
        this.restart_right = 0;
        this.mBottomView_top = 0;
        this.mBottomView_bottom = 0;
        this.mBottomView_left = 0;
        this.mBottomView_right = 0;
        this.seekbar_top = 0;
        this.seekbar_bottom = 0;
        this.seekbar_left = 0;
        this.seekbar_right = 0;
        this.videoview_top = 0;
        this.videoview_bottom = 0;
        this.videoview_left = 0;
        this.videoview_right = 0;
        this.fullscreen_top = 0;
        this.fullscreen_bottom = 0;
        this.fullscreen_left = 0;
        this.fullscreen_right = 0;
        this.videocover_top = 0;
        this.videocover_bottom = 0;
        this.videocover_left = 0;
        this.videocover_right = 0;
        this.totaltime_top = 0;
        this.totaltime_bottom = 0;
        this.totaltime_left = 0;
        this.totaltime_right = 0;
        this.errorview_top = 0;
        this.errorview_bottom = 0;
        this.errorview_left = 0;
        this.errorview_right = 0;
        this.errorview_img_top = 0;
        this.errorview_img_bottom = 0;
        this.errorview_img_left = 0;
        this.errorview_img_right = 0;
        this.errorview_des_top = 0;
        this.errorview_des_bottom = 0;
        this.errorview_des_left = 0;
        this.errorview_des_right = 0;
        this.isFullScreen = false;
        this.firstRun = true;
        this.duration = 0;
        this.handleProgress = new Handler() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer.3
            {
                JDReactVideoPlayer.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 != 0) {
                    if (i2 == 1 && !JDReactVideoPlayer.this.isOver) {
                        JDReactVideoPlayer.this.hide();
                        return;
                    }
                    return;
                }
                int currentPosition = JDReactVideoPlayer.this.mVideoView.getCurrentPosition();
                int duration = JDReactVideoPlayer.this.mVideoView.getDuration();
                if (duration > 0) {
                    JDReactVideoPlayer.this.mPlaySeekbar.setProgress((JDReactVideoPlayer.this.mPlaySeekbar.getMax() * currentPosition) / duration);
                    JDReactVideoPlayer.this.mCurrentTime.setText(JDReactVideoPlayer.this.stringForTime(currentPosition));
                    JDReactVideoPlayer.this.mTotalTime.setText(JDReactVideoPlayer.this.stringForTime(duration));
                }
                if (!JDReactVideoPlayer.this.mVideoView.isPlaying() || JDReactVideoPlayer.this.mPlaySeekbar.isPressed()) {
                    return;
                }
                sendMessageDelayed(obtainMessage(0), 1000 - (currentPosition % 1000));
            }
        };
        this.isForcePlay = false;
        this.player = null;
        this.sensor_flag = true;
        this.stretch_flag = true;
        this.handler = new Handler() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer.7
            {
                JDReactVideoPlayer.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (JDReactVideoPlayer.this.mActivity != null && message.what == 888) {
                    int i2 = message.arg1;
                    if (i2 > 45 && i2 < 135) {
                        JDReactVideoPlayer.this.visibleBottomView();
                        JDReactVideoPlayer.this.mActivity.setRequestedOrientation(8);
                        JDReactVideoPlayer.this.sensor_flag = false;
                        JDReactVideoPlayer.this.stretch_flag = false;
                    } else if (i2 > 135 && i2 < 225) {
                        JDReactVideoPlayer.this.visibleBottomView();
                        JDReactVideoPlayer.this.mActivity.setRequestedOrientation(9);
                        JDReactVideoPlayer.this.sensor_flag = true;
                        JDReactVideoPlayer.this.stretch_flag = true;
                    } else if (i2 > 225 && i2 < 315) {
                        JDReactVideoPlayer.this.visibleBottomView();
                        JDReactVideoPlayer.this.mActivity.setRequestedOrientation(0);
                        JDReactVideoPlayer.this.sensor_flag = false;
                        JDReactVideoPlayer.this.stretch_flag = false;
                    } else if ((i2 <= 315 || i2 >= 360) && (i2 <= 0 || i2 >= 45)) {
                    } else {
                        JDReactVideoPlayer.this.visibleBottomView();
                        JDReactVideoPlayer.this.mActivity.setRequestedOrientation(1);
                        JDReactVideoPlayer.this.sensor_flag = true;
                        JDReactVideoPlayer.this.stretch_flag = true;
                    }
                }
            }
        };
        initView(context);
    }

    private boolean checkNet() {
        if (this.isForcePlay) {
            return true;
        }
        if (!NetUtils.isNetworkAvailable()) {
            showNetError("\u7f51\u7edc\u8fde\u63a5\u9519\u8bef\uff0c\u8bf7\u67e5\u770b\u60a8\u7684\u7f51\u7edc");
            return false;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 0) {
            show3GTip();
            return false;
        }
        return true;
    }

    private void doPlayOrPause() {
        if (checkNet()) {
            View view = this.errorView;
            if (view != null) {
                view.setVisibility(4);
            }
            this.mVidoeCover.setVisibility(8);
            this.mRestart.setVisibility(8);
            if (this.mVideoView.isPlaying()) {
                this.mVideoView.pause();
                this.mPlay.setBackgroundResource(R.drawable.jdreact_icon_play_seekbar);
                this.handleProgress.removeMessages(0);
                this.handleProgress.removeMessages(1);
            } else {
                if (this.mRestart.getVisibility() == 0) {
                    this.mVideoView.resume();
                } else {
                    this.mVideoView.start();
                }
                this.mPlay.setBackgroundResource(R.drawable.jdreact_icon_pause_seekbar);
                this.handleProgress.sendEmptyMessage(0);
                this.handleProgress.removeMessages(1);
                Handler handler = this.handleProgress;
                handler.sendMessageDelayed(handler.obtainMessage(1), 4000L);
            }
            if (this.isOver) {
                this.currentPlayPosition = 0;
                this.mVideoView.seekTo(0);
            }
            this.isOver = false;
        }
    }

    private void forceShowBottom() {
        this.mTopView.clearAnimation();
        this.mBottomView.clearAnimation();
        this.handleProgress.removeMessages(0);
        this.handleProgress.removeMessages(1);
        if (this.isOver) {
            this.mVidoeCover.setVisibility(0);
        }
        this.mPlay.setBackgroundResource(R.drawable.jdreact_icon_play_seekbar);
        if (!this.isOver && this.isOnPrepared) {
            this.mRestart.setVisibility(8);
        } else {
            this.mRestart.setVisibility(0);
        }
        if (this.mClose.getVisibility() == 0) {
            this.mShowTime.setText(this.mTotalTime.getText().toString());
        }
        hideLoading();
        this.mTopView.setVisibility(0);
        this.mBottomView.setVisibility(0);
    }

    public void hideFullScreen(Activity activity) {
        if (activity == null) {
            return;
        }
        this.isFullScreen = false;
        this.mFullScreen.setVisibility(0);
        if (this.mVideoView.isPause()) {
            stopPlay();
        }
        this.mActivity.setRequestedOrientation(1);
        WindowManager.LayoutParams attributes = this.mActivity.getWindow().getAttributes();
        attributes.flags &= -1025;
        activity.getWindow().setAttributes(attributes);
        activity.getWindow().clearFlags(512);
        this.container.addView(this.rootView);
        this.rootView.layout(this.rootview_left, this.rootview_top, this.rootview_right, this.rootview_bottom);
        getVideoView().layout(this.videoview_left, this.videoview_top, this.videoview_right, this.videoview_bottom);
        this.mBottomView.layout(this.mBottomView_left, this.mBottomView_top, this.mBottomView_right, this.mBottomView_bottom);
        this.mPlaySeekbar.layout(this.seekbar_left, this.seekbar_top, this.seekbar_right, this.seekbar_bottom);
        this.mRestart.layout(this.restart_left, this.restart_top, this.restart_right, this.restart_bottom);
        this.mRightArea.layout(this.rightArea_left, this.rightArea_top, this.rightArea_right, this.rightArea_bottom);
        this.mTotalTime.layout(this.totaltime_left, this.totaltime_top, this.totaltime_right, this.totaltime_bottom);
        this.mFullScreen.layout(this.fullscreen_left, this.fullscreen_top, this.fullscreen_right, this.fullscreen_bottom);
        this.mVidoeCover.layout(this.videocover_left, this.videocover_top, this.videocover_right, this.videocover_bottom);
        this.errorView.layout(this.errorview_left, this.errorview_top, this.errorview_right, this.errorview_bottom);
        this.errorView.findViewById(R.id.img).layout(this.errorview_img_left, this.errorview_img_top, this.errorview_img_right, this.errorview_img_bottom);
        this.errorView.findViewById(R.id.description).layout(this.errorview_des_left, this.errorview_des_top, this.errorview_des_right, this.errorview_des_bottom);
        this.mFullScreen.setVisibility(0);
        this.mActivity.getWindow().getDecorView().requestLayout();
        this.mActivity.getWindow().getDecorView().invalidate();
        onStateChangeEvent(3);
    }

    private void initView(Context context) {
        this.mContext = context;
        if (context instanceof ThemedReactContext) {
            this.mActivity = ((ThemedReactContext) context).getCurrentActivity();
        }
        FrameLayout.inflate(this.mContext, R.layout.jdreact_videoview, this);
        this.container = this;
        this.rootView = (RelativeLayout) findViewById(R.id.root_view);
        JDReactVideoView jDReactVideoView = (JDReactVideoView) findViewById(R.id.video_view);
        this.mVideoView = jDReactVideoView;
        jDReactVideoView.setPlayerStateListener(new PlayerStateListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer.1
            {
                JDReactVideoPlayer.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.views.webview.PlayerStateListener
            public void onStateChangeEventPlayer(int i2) {
                JDReactVideoPlayer.this.onStateChangeEvent(i2);
            }
        });
        this.mVidoeCover = (SimpleDraweeView) findViewById(R.id.video_cover);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.restart);
        this.mRestart = linearLayout;
        linearLayout.setOnClickListener(this);
        this.mRestart.setVisibility(4);
        this.mShowTime = (TextView) findViewById(R.id.video_show_duration);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.close);
        this.mClose = relativeLayout;
        relativeLayout.setVisibility(8);
        this.mPlay = (ImageButton) findViewById(R.id.play);
        this.mCurrentTime = (TextView) findViewById(R.id.currentTime);
        this.mTotalTime = (TextView) findViewById(R.id.totalTime);
        this.mPlaySeekbar = (SeekBar) findViewById(R.id.play_bar);
        ImageButton imageButton = (ImageButton) findViewById(R.id.full_screen);
        this.mFullScreen = imageButton;
        imageButton.setOnClickListener(this);
        this.loadingBar = (ProgressBar) findViewById(R.id.loading);
        View findViewById = findViewById(R.id.container);
        this.errorView = findViewById;
        findViewById.setOnClickListener(this);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.topView);
        this.mTopView = relativeLayout2;
        relativeLayout2.setVisibility(8);
        this.mBottomView = (LinearLayout) findViewById(R.id.bottomView);
        this.mVideoView.setOnPreparedListener(this);
        this.mVideoView.setOnErrorListener(this);
        this.mVideoView.setOnCompletionListener(this);
        this.mVideoView.setOnBufferingUpdateListener(this);
        this.mRestart.setOnClickListener(this);
        this.mClose.setOnClickListener(this);
        this.mPlay.setOnClickListener(this);
        View findViewById2 = findViewById(R.id.right_area);
        this.mRightArea = findViewById2;
        findViewById2.setOnClickListener(this);
        this.mCurrentTime.setOnClickListener(this);
        setOnTouchListener(this);
        this.rootView.setOnTouchListener(this);
        this.mBottomView.setOnTouchListener(this);
        this.mTopView.setOnTouchListener(this);
        this.mVideoView.setOnTouchListener(this);
        this.mPlaySeekbar.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        this.mVideoView.setIViewVisible(new JDReactVideoView.IViewVisible() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer.2
            {
                JDReactVideoPlayer.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.IViewVisible
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
                JDReactVideoPlayer.this.onWindowVisibility = true;
                if ("babel".equals(JDReactVideoPlayer.this.sourceFrom) && JDReactVideoPlayer.this.firstRun) {
                    JDReactVideoPlayer.this.firstRun = false;
                    JDReactVideoPlayer.this.startFullScreen();
                }
                if (!JDReactVideoPlayer.this.mVideoView.isPlaying() && !JDReactVideoPlayer.this.mVideoView.isPause() && !JDReactVideoPlayer.this.isOver) {
                    JDReactVideoPlayer.this.mVideoView.seekTo(JDReactVideoPlayer.this.currentPlayPosition);
                }
                if (JDReactVideoPlayer.this.isFullScreen) {
                    if (JDReactVideoPlayer.this.mVideoView.isPlaying()) {
                        JDReactVideoPlayer.this.handleProgress.removeMessages(1);
                        Handler handler = JDReactVideoPlayer.this.handleProgress;
                        handler.sendMessageDelayed(handler.obtainMessage(1), 4000L);
                    }
                    JDReactVideoPlayer.this.mTopView.setVisibility(0);
                    JDReactVideoPlayer.this.mBottomView.setVisibility(0);
                    JDReactVideoPlayer.this.updatePausePlay();
                }
                if (JDReactVideoPlayer.this.isFullScreen) {
                    JDReactVideoPlayer.this.mClose.setVisibility(0);
                } else {
                    JDReactVideoPlayer.this.mClose.setVisibility(8);
                }
            }

            @Override // com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.IViewVisible
            public boolean onSurfaceTextureDestroyed() {
                JDReactVideoPlayer.this.onWindowVisibility = false;
                if (JDReactVideoPlayer.this.mVideoView.getCurrentPosition() > 0) {
                    JDReactVideoPlayer jDReactVideoPlayer = JDReactVideoPlayer.this;
                    jDReactVideoPlayer.currentPlayPosition = jDReactVideoPlayer.mVideoView.getCurrentPosition();
                }
                if (JDReactVideoPlayer.this.isOver) {
                    JDReactVideoPlayer.this.mVideoView.stopPlayback();
                }
                if (JDReactVideoPlayer.this.isFullScreen || (JDReactVideoPlayer.this.errorView != null && JDReactVideoPlayer.this.errorView.getVisibility() == 0)) {
                    return false;
                }
                JDReactVideoPlayer.this.resetUI(true);
                boolean unused = JDReactVideoPlayer.this.isOver;
                JDReactVideoPlayer.this.mTopView.setVisibility(8);
                JDReactVideoPlayer.this.mBottomView.setVisibility(8);
                return false;
            }
        });
    }

    private void showNetError() {
        this.errorView.setVisibility(0);
        this.mRestart.setVisibility(4);
    }

    public String stringForTime(long j2) {
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

    public void updatePausePlay() {
        if (this.mVideoView.isPlaying()) {
            this.mPlay.setBackgroundResource(R.drawable.jdreact_icon_pause_seekbar);
        } else {
            this.mPlay.setBackgroundResource(R.drawable.jdreact_icon_play_seekbar);
        }
    }

    public void visibleBottomView() {
        this.mBottomView.clearAnimation();
        this.mTopView.clearAnimation();
        this.mBottomView.setVisibility(0);
        this.mTopView.setVisibility(0);
        Handler handler = this.handleProgress;
        handler.sendMessageDelayed(handler.obtainMessage(1), 4000L);
    }

    public void closeVolume() {
        getVideoView().closeVolume();
    }

    public JDReactVideoView getVideoView() {
        return this.mVideoView;
    }

    public void hide() {
        if (this.mBottomView.getVisibility() != 0) {
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.jdreact_vd_option_leave_from_top);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.jdreact_vd_option_leave_from_bottom);
        this.mTopView.clearAnimation();
        this.mBottomView.clearAnimation();
        loadAnimation.setAnimationListener(new AniListener(1));
        loadAnimation2.setAnimationListener(new AniListener(1));
        this.mTopView.startAnimation(loadAnimation);
        this.mBottomView.startAnimation(loadAnimation2);
    }

    public void hideFullScreenButton() {
        this.mFullScreen.setVisibility(8);
    }

    public void hideLoading() {
        this.loadingBar.setVisibility(8);
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
        OKLog.d("Player", "onBufferingUpdate");
        this.mPlaySeekbar.setSecondaryProgress(i2);
        checkNet();
    }

    @Override // com.jingdong.common.jdreactFramework.views.webview.EventListener
    public void onButtonClick(String str) {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Activity activity;
        Activity activity2;
        int id = view.getId();
        if (id != R.id.play && id != R.id.container && id != R.id.currentTime) {
            if (id == R.id.close) {
                if ("babel".equals(this.sourceFrom) && (activity2 = this.mActivity) != null) {
                    JDMtaUtils.onClick(activity2, "Babel_VideoClose", this.sourcePage, this.vidoeId, this.paramId);
                    this.mActivity.onBackPressed();
                    return;
                }
                HideFullScreenListener hideFullScreenListener = this.mHideFullScreenListener;
                if (hideFullScreenListener != null) {
                    hideFullScreenListener.closeClicked();
                }
                PopupWindow popupWindow = this.mPopupWindow;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                    return;
                }
                return;
            } else if (id == R.id.restart) {
                onButtonClick("start");
                start();
                return;
            } else if (id == R.id.full_screen || id == R.id.right_area) {
                onButtonClick("fullscreen");
                showFullScreen();
                return;
            } else {
                return;
            }
        }
        if ("babel".equals(this.sourceFrom) && (activity = this.mActivity) != null) {
            JDMtaUtils.onClick(activity, "Babel_VideoPause", this.sourcePage, this.vidoeId, this.paramId);
        }
        if (this.errorView.getVisibility() == 0) {
            start();
            hide();
            return;
        }
        doPlayOrPause();
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        OKLog.d("Player", "onCompletion");
        this.isOver = true;
        this.mCurrentTime.setText(stringForTime(this.mVideoView.getDuration()));
        SeekBar seekBar = this.mPlaySeekbar;
        seekBar.setProgress(seekBar.getMax());
        forceShowBottom();
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
        OKLog.d("Player", "onError-->" + i2 + "  " + i3);
        this.currentPlayPosition = this.mVideoView.getCurrentPosition();
        this.mVideoView.stopPlayback();
        this.mVideoView.setStateIdle();
        resetUI(true);
        if (i3 != -1004) {
            if (i3 != -19) {
                showNetError();
                return false;
            }
            return false;
        } else if (NetUtils.isNetworkAvailable()) {
            return false;
        } else {
            showNetError("\u7f51\u7edc\u8fde\u63a5\u9519\u8bef\uff0c\u8bf7\u67e5\u770b\u60a8\u7684\u7f51\u7edc");
            return false;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.views.webview.EventListener
    public void onNetWorkStateChangeedEvent(int i2) {
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        OKLog.d("Player", "onPrepared");
        this.isOnPrepared = true;
        this.mVideoView.startPlaying();
        this.mVideoView.seekTo(this.currentPlayPosition);
        show(true);
        this.handleProgress.sendEmptyMessage(0);
        hideLoading();
        this.duration = this.mVideoView.getDuration();
        this.mVideoView.forceLayout();
        measure(View.MeasureSpec.makeMeasureSpec(getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getHeight(), 1073741824));
        layout(getLeft(), getTop(), getRight(), getBottom());
    }

    @Override // android.view.View
    public void onScreenStateChanged(int i2) {
        super.onScreenStateChanged(i2);
        if (i2 == 0) {
            if (this.isOver || !this.mVideoView.isPlaying()) {
                return;
            }
            doPlayOrPause();
        } else if (i2 == 1 && this.onWindowVisibility) {
            show(false);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.views.webview.EventListener
    public void onStateChangeEvent(int i2) {
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();
        if (id != R.id.topView && id != R.id.bottomView && this.mVidoeCover.getVisibility() != 0 && motionEvent.getAction() == 0) {
            if (this.mBottomView.getVisibility() == 0) {
                hide();
            } else if (this.isInitScreen) {
                this.isInitScreen = false;
                this.mTopView.setVisibility(0);
                this.mBottomView.setVisibility(0);
            } else {
                show(true);
            }
        }
        return true;
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (this.isFullScreen && i2 == 8 && !this.isOver && this.mVideoView.isPlaying() && this.mClose.getVisibility() == 0) {
            doPlayOrPause();
        }
    }

    public void openVolume() {
        getVideoView().openVolume();
    }

    public void pause() {
        if (checkNet() && this.mVideoView.isPlaying()) {
            this.mVidoeCover.setVisibility(8);
            this.mRestart.setVisibility(8);
            this.mVideoView.pause();
            this.mPlay.setBackgroundResource(R.drawable.jdreact_icon_play_seekbar);
            this.handleProgress.removeMessages(0);
            this.handleProgress.removeMessages(1);
            if (this.isOver) {
                this.currentPlayPosition = 0;
                this.mVideoView.seekTo(0);
            }
            this.isOver = false;
        }
    }

    public void play() {
        if (checkNet()) {
            View view = this.errorView;
            if (view != null) {
                view.setVisibility(4);
            }
            this.mVidoeCover.setVisibility(8);
            this.mRestart.setVisibility(8);
            this.mVideoView.start();
            this.mPlay.setBackgroundResource(R.drawable.jdreact_icon_pause_seekbar);
            this.handleProgress.removeMessages(0);
            this.handleProgress.sendEmptyMessage(0);
            this.handleProgress.removeMessages(1);
            Handler handler = this.handleProgress;
            handler.sendMessageDelayed(handler.obtainMessage(1), 4000L);
            if (this.isOver) {
                this.currentPlayPosition = 0;
                this.mVideoView.seekTo(0);
            }
            this.isOver = false;
        }
    }

    public void registerSensor(Activity activity) {
        if (activity == null || !JDReactHelper.newInstance().isAgreedPrivacy()) {
            return;
        }
        SensorManager sensorManager = (SensorManager) activity.getSystemService("sensor");
        this.sm = sensorManager;
        this.sensor = sensorManager.getDefaultSensor(1);
        OrientationSensorListener orientationSensorListener = new OrientationSensorListener(this.handler);
        this.listener = orientationSensorListener;
        this.sm.registerListener(orientationSensorListener, this.sensor, 2);
        SensorManager sensorManager2 = (SensorManager) activity.getSystemService("sensor");
        this.sm1 = sensorManager2;
        this.sensor1 = sensorManager2.getDefaultSensor(1);
        OrientationSensorListener2 orientationSensorListener2 = new OrientationSensorListener2();
        this.listener1 = orientationSensorListener2;
        this.sm1.registerListener(orientationSensorListener2, this.sensor1, 2);
    }

    public void resetUI(boolean z) {
        resetUI(z, false);
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public void setCoverBitmap(Bitmap bitmap) {
        this.loadingBar.setVisibility(4);
        this.errorView.setVisibility(4);
        this.mRestart.setVisibility(0);
        this.mVidoeCover.setImageBitmap(bitmap);
    }

    public void setCoverUrl(String str) {
        this.loadingBar.setVisibility(4);
        this.errorView.setVisibility(4);
        this.mRestart.setVisibility(0);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        int i2 = R.drawable.jdreact_vd_player_fail_bg;
        JDImageUtils.displayImage(str, this.mVidoeCover, jDDisplayImageOptions.showImageForEmptyUri(i2).showImageOnFail(i2).showImageOnLoading(i2));
    }

    public void setMta(String str, String str2, String str3) {
        this.sourcePage = str;
        this.vidoeId = str2;
        this.paramId = str3;
    }

    public void setShowTime(String str) {
        try {
            this.mShowTime.setText(stringForTime(Integer.valueOf(Integer.parseInt(str)).intValue() * 1000));
        } catch (Exception unused) {
            this.mShowTime.setText(str);
        }
        this.mRestart.setVisibility(0);
    }

    public void setShowTimeSecond(String str) {
        try {
            this.mShowTime.setText(stringForTime(Integer.valueOf(Integer.parseInt(str)).intValue()));
        } catch (Exception unused) {
            this.mShowTime.setText(str);
        }
    }

    public void setVideoUri(Uri uri) {
        if (uri == null) {
            return;
        }
        this.mUri = uri;
        hide();
    }

    public void setVolume(int i2) {
        getVideoView().setVolume(i2);
    }

    public void setfullScreenListener(HideFullScreenListener hideFullScreenListener) {
        this.isFullScreen = true;
        this.mHideFullScreenListener = hideFullScreenListener;
    }

    public void show(boolean z) {
        show(z, false);
    }

    public void show3GTip() {
        if (this.onWindowVisibility) {
            JDDialog jDDialog = this.error3GDialog;
            if (jDDialog == null || !jDDialog.isShowing()) {
                this.mVideoView.stopPlayback();
                resetUI(false);
                JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(this.mContext, "\u60a8\u6b63\u5728\u4f7f\u7528\u975eWIFI\u7f51\u7edc\uff0c\u89c2\u770b\u89c6\u9891\u5c06\u4ea7\u751f\u6d41\u91cf\u8d39\u7528\uff0c\u786e\u8ba4\u7ee7\u7eed\uff1f", "\u53d6\u6d88", "\u786e\u8ba4");
                this.error3GDialog = createJdDialogWithStyle2;
                createJdDialogWithStyle2.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer.4
                    {
                        JDReactVideoPlayer.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDReactVideoPlayer.this.error3GDialog.dismiss();
                    }
                });
                this.error3GDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer.5
                    {
                        JDReactVideoPlayer.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDReactVideoPlayer.this.error3GDialog.dismiss();
                        JDReactVideoPlayer jDReactVideoPlayer = JDReactVideoPlayer.this;
                        jDReactVideoPlayer.isForcePlay = true;
                        jDReactVideoPlayer.start();
                    }
                });
                this.error3GDialog.show();
            }
        }
    }

    public void showFullScreen() {
        if (this.mActivity == null) {
            return;
        }
        this.isInitScreen = true;
        this.isFullScreen = true;
        this.totaltime_top = this.mTotalTime.getTop();
        this.totaltime_bottom = this.mTotalTime.getBottom();
        this.totaltime_left = this.mTotalTime.getLeft();
        this.totaltime_right = this.mTotalTime.getRight();
        this.fullscreen_top = this.mFullScreen.getTop();
        this.fullscreen_bottom = this.mFullScreen.getBottom();
        this.fullscreen_left = this.mFullScreen.getLeft();
        this.fullscreen_right = this.mFullScreen.getRight();
        this.rightArea_top = this.mRightArea.getTop();
        this.rightArea_bottom = this.mRightArea.getBottom();
        this.rightArea_left = this.mRightArea.getLeft();
        this.rightArea_right = this.mRightArea.getRight();
        this.videoview_top = getVideoView().getTop();
        this.videoview_bottom = getVideoView().getBottom();
        this.videoview_left = getVideoView().getLeft();
        this.videoview_right = getVideoView().getRight();
        this.rootview_top = this.rootView.getTop();
        this.rootview_bottom = this.rootView.getBottom();
        this.rootview_left = this.rootView.getLeft();
        this.rootview_right = this.rootView.getRight();
        this.restart_top = this.mRestart.getTop();
        this.restart_bottom = this.mRestart.getBottom();
        this.restart_left = this.mRestart.getLeft();
        this.restart_right = this.mRestart.getRight();
        this.mBottomView_top = this.mBottomView.getTop();
        this.mBottomView_bottom = this.mBottomView.getBottom();
        this.mBottomView_left = this.mBottomView.getLeft();
        this.mBottomView_right = this.mBottomView.getRight();
        this.seekbar_top = this.mPlaySeekbar.getTop();
        this.seekbar_bottom = this.mPlaySeekbar.getBottom();
        this.seekbar_left = this.mPlaySeekbar.getLeft();
        this.seekbar_right = this.mPlaySeekbar.getRight();
        this.videocover_top = this.mVidoeCover.getTop();
        this.videocover_bottom = this.mVidoeCover.getBottom();
        this.videocover_left = this.mVidoeCover.getLeft();
        this.videocover_right = this.mVidoeCover.getRight();
        this.errorview_top = this.errorView.getTop();
        this.errorview_bottom = this.errorView.getBottom();
        this.errorview_left = this.errorView.getLeft();
        this.errorview_right = this.errorView.getRight();
        View view = this.errorView;
        int i2 = R.id.img;
        this.errorview_img_top = view.findViewById(i2).getTop();
        this.errorview_img_bottom = this.errorView.findViewById(i2).getBottom();
        this.errorview_img_left = this.errorView.findViewById(i2).getLeft();
        this.errorview_img_right = this.errorView.findViewById(i2).getRight();
        View view2 = this.errorView;
        int i3 = R.id.description;
        this.errorview_des_top = view2.findViewById(i3).getTop();
        this.errorview_des_bottom = this.errorView.findViewById(i3).getBottom();
        this.errorview_des_left = this.errorView.findViewById(i3).getLeft();
        this.errorview_des_right = this.errorView.findViewById(i3).getRight();
        this.mFullScreen.setVisibility(8);
        this.mTopView.setVisibility(8);
        this.mBottomView.setVisibility(8);
        if (this.mVideoView.isPause()) {
            doPlayOrPause();
        }
        this.mActivity.setRequestedOrientation(0);
        if (this.mPopupWindow == null) {
            PopupWindow popupWindow = new PopupWindow(this.rootView, -1, -1);
            this.mPopupWindow = popupWindow;
            popupWindow.setOutsideTouchable(false);
            this.mPopupWindow.setFocusable(true);
            this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
            this.mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer.6
                {
                    JDReactVideoPlayer.this = this;
                }

                @Override // android.widget.PopupWindow.OnDismissListener
                public void onDismiss() {
                    JDReactVideoPlayer jDReactVideoPlayer = JDReactVideoPlayer.this;
                    jDReactVideoPlayer.hideFullScreen(jDReactVideoPlayer.mActivity);
                }
            });
        }
        this.container.removeView(this.rootView);
        this.mActivity.getWindow().setFlags(1024, 1024);
        this.mActivity.getWindow().getDecorView().invalidate();
        this.mPopupWindow.showAtLocation(this.rootView, 17, 0, 0);
        onStateChangeEvent(2);
    }

    public void showLoading() {
        this.loadingBar.setVisibility(0);
    }

    public void start() {
        if (checkNet()) {
            View view = this.errorView;
            if (view != null) {
                view.setVisibility(4);
            }
            if (this.mUri == null) {
                return;
            }
            if (this.isOver) {
                this.currentPlayPosition = 0;
            }
            this.mVidoeCover.setVisibility(8);
            this.mRestart.setVisibility(8);
            showLoading();
            this.mVideoView.setVideoUri(this.mUri);
            this.mVideoView.setPlaystatus();
            if (this.isOver) {
                this.handleProgress.removeMessages(1);
                Handler handler = this.handleProgress;
                handler.sendMessageDelayed(handler.obtainMessage(1), 4000L);
            }
            this.isOver = false;
            this.mPlay.setBackgroundResource(R.drawable.jdreact_icon_pause_seekbar);
        }
    }

    public void startFullScreen() {
        if (this.mActivity == null) {
            return;
        }
        this.isInitScreen = true;
        this.isFullScreen = true;
        this.mFullScreen.setVisibility(8);
        start();
    }

    public void stopPlay() {
        if (getVideoView() == null) {
            return;
        }
        if (getVideoView().isPlaying() || getVideoView().isPause()) {
            this.currentPlayPosition = getVideoView().getCurrentPosition();
            getVideoView().stopPlayback();
            resetUI(true);
        }
    }

    public void unregisterSensor() {
        try {
            SensorManager sensorManager = this.sm;
            if (sensorManager != null) {
                sensorManager.unregisterListener(this.listener, this.sensor);
            }
            SensorManager sensorManager2 = this.sm1;
            if (sensorManager2 != null) {
                sensorManager2.unregisterListener(this.listener1, this.sensor1);
            }
        } catch (Exception unused) {
        }
    }

    private void resetUI(boolean z, boolean z2) {
        if (z) {
            this.mVidoeCover.setVisibility(0);
            hide();
        } else {
            this.mVidoeCover.setVisibility(8);
            show(false, z2);
        }
        this.mPlay.setBackgroundResource(R.drawable.jdreact_icon_play_seekbar);
        this.mRestart.setVisibility(0);
        if (this.mClose.getVisibility() == 0) {
            this.mShowTime.setText(this.mTotalTime.getText().toString());
        }
        hideLoading();
        this.handleProgress.removeMessages(0);
        this.handleProgress.removeMessages(1);
    }

    public void show(boolean z, boolean z2) {
        if (this.mTopView.getVisibility() == 0) {
            return;
        }
        if (!this.isOnPrepared && !z2) {
            hide();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.jdreact_vd_option_entry_from_top);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.jdreact_vd_option_entry_from_bottom);
        loadAnimation.setAnimationListener(new AniListener(0));
        loadAnimation2.setAnimationListener(new AniListener(0));
        this.mBottomView.clearAnimation();
        this.mTopView.clearAnimation();
        this.mTopView.startAnimation(loadAnimation);
        this.mBottomView.startAnimation(loadAnimation2);
        this.handleProgress.removeMessages(1);
        if (z) {
            Handler handler = this.handleProgress;
            handler.sendMessageDelayed(handler.obtainMessage(1), 4000L);
        }
        updatePausePlay();
    }

    private void showNetError(String str) {
        ToastUtil.showToast(str);
    }

    public JDReactVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.rightArea_top = 0;
        this.rightArea_bottom = 0;
        this.rightArea_left = 0;
        this.rightArea_right = 0;
        this.rootview_top = 0;
        this.rootview_bottom = 0;
        this.rootview_left = 0;
        this.rootview_right = 0;
        this.restart_top = 0;
        this.restart_bottom = 0;
        this.restart_left = 0;
        this.restart_right = 0;
        this.mBottomView_top = 0;
        this.mBottomView_bottom = 0;
        this.mBottomView_left = 0;
        this.mBottomView_right = 0;
        this.seekbar_top = 0;
        this.seekbar_bottom = 0;
        this.seekbar_left = 0;
        this.seekbar_right = 0;
        this.videoview_top = 0;
        this.videoview_bottom = 0;
        this.videoview_left = 0;
        this.videoview_right = 0;
        this.fullscreen_top = 0;
        this.fullscreen_bottom = 0;
        this.fullscreen_left = 0;
        this.fullscreen_right = 0;
        this.videocover_top = 0;
        this.videocover_bottom = 0;
        this.videocover_left = 0;
        this.videocover_right = 0;
        this.totaltime_top = 0;
        this.totaltime_bottom = 0;
        this.totaltime_left = 0;
        this.totaltime_right = 0;
        this.errorview_top = 0;
        this.errorview_bottom = 0;
        this.errorview_left = 0;
        this.errorview_right = 0;
        this.errorview_img_top = 0;
        this.errorview_img_bottom = 0;
        this.errorview_img_left = 0;
        this.errorview_img_right = 0;
        this.errorview_des_top = 0;
        this.errorview_des_bottom = 0;
        this.errorview_des_left = 0;
        this.errorview_des_right = 0;
        this.isFullScreen = false;
        this.firstRun = true;
        this.duration = 0;
        this.handleProgress = new Handler() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer.3
            {
                JDReactVideoPlayer.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 != 0) {
                    if (i2 == 1 && !JDReactVideoPlayer.this.isOver) {
                        JDReactVideoPlayer.this.hide();
                        return;
                    }
                    return;
                }
                int currentPosition = JDReactVideoPlayer.this.mVideoView.getCurrentPosition();
                int duration = JDReactVideoPlayer.this.mVideoView.getDuration();
                if (duration > 0) {
                    JDReactVideoPlayer.this.mPlaySeekbar.setProgress((JDReactVideoPlayer.this.mPlaySeekbar.getMax() * currentPosition) / duration);
                    JDReactVideoPlayer.this.mCurrentTime.setText(JDReactVideoPlayer.this.stringForTime(currentPosition));
                    JDReactVideoPlayer.this.mTotalTime.setText(JDReactVideoPlayer.this.stringForTime(duration));
                }
                if (!JDReactVideoPlayer.this.mVideoView.isPlaying() || JDReactVideoPlayer.this.mPlaySeekbar.isPressed()) {
                    return;
                }
                sendMessageDelayed(obtainMessage(0), 1000 - (currentPosition % 1000));
            }
        };
        this.isForcePlay = false;
        this.player = null;
        this.sensor_flag = true;
        this.stretch_flag = true;
        this.handler = new Handler() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer.7
            {
                JDReactVideoPlayer.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (JDReactVideoPlayer.this.mActivity != null && message.what == 888) {
                    int i2 = message.arg1;
                    if (i2 > 45 && i2 < 135) {
                        JDReactVideoPlayer.this.visibleBottomView();
                        JDReactVideoPlayer.this.mActivity.setRequestedOrientation(8);
                        JDReactVideoPlayer.this.sensor_flag = false;
                        JDReactVideoPlayer.this.stretch_flag = false;
                    } else if (i2 > 135 && i2 < 225) {
                        JDReactVideoPlayer.this.visibleBottomView();
                        JDReactVideoPlayer.this.mActivity.setRequestedOrientation(9);
                        JDReactVideoPlayer.this.sensor_flag = true;
                        JDReactVideoPlayer.this.stretch_flag = true;
                    } else if (i2 > 225 && i2 < 315) {
                        JDReactVideoPlayer.this.visibleBottomView();
                        JDReactVideoPlayer.this.mActivity.setRequestedOrientation(0);
                        JDReactVideoPlayer.this.sensor_flag = false;
                        JDReactVideoPlayer.this.stretch_flag = false;
                    } else if ((i2 <= 315 || i2 >= 360) && (i2 <= 0 || i2 >= 45)) {
                    } else {
                        JDReactVideoPlayer.this.visibleBottomView();
                        JDReactVideoPlayer.this.mActivity.setRequestedOrientation(1);
                        JDReactVideoPlayer.this.sensor_flag = true;
                        JDReactVideoPlayer.this.stretch_flag = true;
                    }
                }
            }
        };
        initView(context);
    }

    public JDReactVideoPlayer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.rightArea_top = 0;
        this.rightArea_bottom = 0;
        this.rightArea_left = 0;
        this.rightArea_right = 0;
        this.rootview_top = 0;
        this.rootview_bottom = 0;
        this.rootview_left = 0;
        this.rootview_right = 0;
        this.restart_top = 0;
        this.restart_bottom = 0;
        this.restart_left = 0;
        this.restart_right = 0;
        this.mBottomView_top = 0;
        this.mBottomView_bottom = 0;
        this.mBottomView_left = 0;
        this.mBottomView_right = 0;
        this.seekbar_top = 0;
        this.seekbar_bottom = 0;
        this.seekbar_left = 0;
        this.seekbar_right = 0;
        this.videoview_top = 0;
        this.videoview_bottom = 0;
        this.videoview_left = 0;
        this.videoview_right = 0;
        this.fullscreen_top = 0;
        this.fullscreen_bottom = 0;
        this.fullscreen_left = 0;
        this.fullscreen_right = 0;
        this.videocover_top = 0;
        this.videocover_bottom = 0;
        this.videocover_left = 0;
        this.videocover_right = 0;
        this.totaltime_top = 0;
        this.totaltime_bottom = 0;
        this.totaltime_left = 0;
        this.totaltime_right = 0;
        this.errorview_top = 0;
        this.errorview_bottom = 0;
        this.errorview_left = 0;
        this.errorview_right = 0;
        this.errorview_img_top = 0;
        this.errorview_img_bottom = 0;
        this.errorview_img_left = 0;
        this.errorview_img_right = 0;
        this.errorview_des_top = 0;
        this.errorview_des_bottom = 0;
        this.errorview_des_left = 0;
        this.errorview_des_right = 0;
        this.isFullScreen = false;
        this.firstRun = true;
        this.duration = 0;
        this.handleProgress = new Handler() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer.3
            {
                JDReactVideoPlayer.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i22 = message.what;
                if (i22 != 0) {
                    if (i22 == 1 && !JDReactVideoPlayer.this.isOver) {
                        JDReactVideoPlayer.this.hide();
                        return;
                    }
                    return;
                }
                int currentPosition = JDReactVideoPlayer.this.mVideoView.getCurrentPosition();
                int duration = JDReactVideoPlayer.this.mVideoView.getDuration();
                if (duration > 0) {
                    JDReactVideoPlayer.this.mPlaySeekbar.setProgress((JDReactVideoPlayer.this.mPlaySeekbar.getMax() * currentPosition) / duration);
                    JDReactVideoPlayer.this.mCurrentTime.setText(JDReactVideoPlayer.this.stringForTime(currentPosition));
                    JDReactVideoPlayer.this.mTotalTime.setText(JDReactVideoPlayer.this.stringForTime(duration));
                }
                if (!JDReactVideoPlayer.this.mVideoView.isPlaying() || JDReactVideoPlayer.this.mPlaySeekbar.isPressed()) {
                    return;
                }
                sendMessageDelayed(obtainMessage(0), 1000 - (currentPosition % 1000));
            }
        };
        this.isForcePlay = false;
        this.player = null;
        this.sensor_flag = true;
        this.stretch_flag = true;
        this.handler = new Handler() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer.7
            {
                JDReactVideoPlayer.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (JDReactVideoPlayer.this.mActivity != null && message.what == 888) {
                    int i22 = message.arg1;
                    if (i22 > 45 && i22 < 135) {
                        JDReactVideoPlayer.this.visibleBottomView();
                        JDReactVideoPlayer.this.mActivity.setRequestedOrientation(8);
                        JDReactVideoPlayer.this.sensor_flag = false;
                        JDReactVideoPlayer.this.stretch_flag = false;
                    } else if (i22 > 135 && i22 < 225) {
                        JDReactVideoPlayer.this.visibleBottomView();
                        JDReactVideoPlayer.this.mActivity.setRequestedOrientation(9);
                        JDReactVideoPlayer.this.sensor_flag = true;
                        JDReactVideoPlayer.this.stretch_flag = true;
                    } else if (i22 > 225 && i22 < 315) {
                        JDReactVideoPlayer.this.visibleBottomView();
                        JDReactVideoPlayer.this.mActivity.setRequestedOrientation(0);
                        JDReactVideoPlayer.this.sensor_flag = false;
                        JDReactVideoPlayer.this.stretch_flag = false;
                    } else if ((i22 <= 315 || i22 >= 360) && (i22 <= 0 || i22 >= 45)) {
                    } else {
                        JDReactVideoPlayer.this.visibleBottomView();
                        JDReactVideoPlayer.this.mActivity.setRequestedOrientation(1);
                        JDReactVideoPlayer.this.sensor_flag = true;
                        JDReactVideoPlayer.this.stretch_flag = true;
                    }
                }
            }
        };
        initView(context);
    }
}
