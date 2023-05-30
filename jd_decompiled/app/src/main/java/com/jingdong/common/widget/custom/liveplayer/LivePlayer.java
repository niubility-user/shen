package com.jingdong.common.widget.custom.liveplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.R;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.unification.video.player.VideoPlayUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.widget.custom.liveplayer.callback.LiveSmallWindowCallback;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.LiveSmallWindow;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ParamsRunnable;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.utils.DPIUtil;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.example.widget.media.PlayDurationStatistics;

/* loaded from: classes12.dex */
public class LivePlayer extends FrameLayout {
    public static final int ACTIVITY_STATUS_GO_BACK = 201;
    public static final int ACTIVITY_STATUS_GO_BACK_FROM_BACKGROUND = 203;
    public static final int ACTIVITY_STATUS_GO_OUT = 200;
    public static final int ACTIVITY_STATUS_GO_TO_BACKGROUND = 202;
    public static final int IMAGE_STATUS_ONE2TWO = 100;
    public static final int IMAGE_STATUS_THREE2TWO = 102;
    public static final int IMAGE_STATUS_TWO2ONE = 103;
    public static final int IMAGE_STATUS_TWO2THREE = 101;
    public static final String LIVE_ROOM_LIVE_TYPE = "2";
    public static final String LIVE_ROOM_REPLAY_TYPE = "3";
    public static final String LIVE_ROOM_SHOP_LIVE_TYPE = "0";
    public static final String LIVE_ROOM_STATUS_HAS_VIDEO = "1";
    public static final String LIVE_ROOM_STATUS_PLAYING = "2";
    public static final String LIVE_ROOM_STATUS_PLAYING_WITH_VIDEO_FILE = "3";
    public static final String LIVE_ROOM_STATUS_UNKNOWN = "-1";
    private static final int MSG_HIDE_GO_INTO_LIVE_ROOM_BTN = 9001;
    private static final int MSG_SHOW_GO_INTO_LIVE_ROOM_BTN = 9000;
    private static final String TAG = LivePlayer.class.getSimpleName();
    private AudioManager audioManager;
    private boolean autoPlay;
    private ImageView btnMute;
    private TextView btnRefresh;
    private ImageView btnVideoPlay;
    private SimpleDraweeView coverIcon;
    private SimpleDraweeView coverIconSmall;
    private int curFrame;
    private String currentLivingRoomStatus;
    private String currentUrl;
    private LiveDataEntity dataEntity;
    private FrameLayout failToRefreshView;
    private FrameLayout failToRefreshViewSmall;
    private LinearLayout finishViewInfo;
    private FrameLayout finishedView;
    private SimpleDraweeView finishedViewBackground;
    private SimpleDraweeView finishedViewBackgroundSmall;
    private FrameLayout finishedViewSmall;
    private boolean firstPlay;
    private FloatingWindowCloseListener floatingWindowClose;
    private LivePlayerFondAnimView fondAnimViewCenter;
    private LivePlayerFondAnimView fondAnimViewRight;
    private View goIntoLiveRoomClickArea;
    private View goIntoLiveRoomMask;
    private boolean isDestroyed;
    private boolean isPlaying;
    private boolean isShowInSmall;
    private boolean isVoiceOn;
    private JumpIntoLiveRoomListener jumpIntoLiveRoom;
    private FrameLayout loadingView;
    private FrameLayout loadingViewSmall;
    private PlayerHandler mHandler;
    private LiveSmallWindowCallback mPlayCallBack;
    private PlayDurationStatistics.PlayDurationStatisticsListener mPlayDurationStatisticsListener;
    private int mPlayTimeoutMillis;
    private final Runnable mTimeoutRunnable;
    private View.OnClickListener onMuteClickListener;
    private IPlayerControl.OnPlayerStateListener onPlayerStateListener;
    private View.OnClickListener onRefreshClickListener;
    private LinearLayout playBtn;
    private FrameLayout playBtnContainer;
    private TextView playBtnLabel;
    private View playBtnLineLeft;
    private View playBtnLineRight;
    private View.OnClickListener playBtnOnClickListener;
    private IjkVideoView player;
    private SimpleDraweeView playerBackground;
    private SimpleDraweeView playerBackgroundSmall;
    private FrameLayout playerContainer;
    private int retryTimes;
    private Runnable startPlayRunnable;
    private ParamsRunnable startPlayRunnableMute;
    private Runnable startReplaySeekToRunnable;
    private PlayerStatus status;
    private StatusChangedListener statusChanged;
    private StatusChangedListener statusChangedSmall;
    private TextView textViewWatchingCount;
    private FrameLayout videoControlPanel;
    private SimpleDraweeView videoCover;
    private SimpleDraweeView videoCoverSmall;
    private View.OnClickListener videoPlayBtnOnClickListener;
    private Point videoViewSize;
    private LinearLayout watchingCountContainer;

    /* renamed from: com.jingdong.common.widget.custom.liveplayer.LivePlayer$10 */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$widget$custom$liveplayer$LivePlayer$PlayerStatus;

        static {
            int[] iArr = new int[PlayerStatus.values().length];
            $SwitchMap$com$jingdong$common$widget$custom$liveplayer$LivePlayer$PlayerStatus = iArr;
            try {
                iArr[PlayerStatus.STATE_IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$widget$custom$liveplayer$LivePlayer$PlayerStatus[PlayerStatus.STATE_LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$widget$custom$liveplayer$LivePlayer$PlayerStatus[PlayerStatus.STATE_PLAYING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$common$widget$custom$liveplayer$LivePlayer$PlayerStatus[PlayerStatus.STATE_PAUSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$jingdong$common$widget$custom$liveplayer$LivePlayer$PlayerStatus[PlayerStatus.STATE_FINISHED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$jingdong$common$widget$custom$liveplayer$LivePlayer$PlayerStatus[PlayerStatus.STATE_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public interface FloatingWindowCloseListener {
        void onClosed();
    }

    /* loaded from: classes12.dex */
    public interface JumpIntoLiveRoomListener {
        void onJumped();
    }

    /* loaded from: classes12.dex */
    public static class PlayerHandler extends Handler {
        private WeakReference<LivePlayer> livePlayerWeakReference;

        public PlayerHandler(LivePlayer livePlayer) {
            this.livePlayerWeakReference = new WeakReference<>(livePlayer);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LivePlayer livePlayer = this.livePlayerWeakReference.get();
            if (livePlayer == null || livePlayer.isShowInSmall()) {
                return;
            }
            int i2 = message.what;
            if (i2 == 9000) {
                livePlayer.showGoIntoLiveRoomBtn();
            } else if (i2 != 9001) {
            } else {
                livePlayer.hideGoIntoLiveRoomBtn();
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum PlayerStatus {
        STATE_IDLE(1000),
        STATE_ERROR(1001),
        STATE_PREPARING(1002),
        STATE_PREPARED(1003),
        STATE_PLAYING(1004),
        STATE_PAUSED(1005),
        STATE_FINISHED(1006),
        STATE_LOADING(1007);
        
        private int mStatus;

        PlayerStatus(int i2) {
            this.mStatus = i2;
        }

        public int getStatus() {
            return this.mStatus;
        }
    }

    /* loaded from: classes12.dex */
    public interface StatusChangedListener {
        void onStatusChanged(PlayerStatus playerStatus);
    }

    public LivePlayer(@NonNull Context context) {
        super(context);
        this.isDestroyed = false;
        this.mPlayTimeoutMillis = 5000;
        this.startPlayRunnable = new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.1
            {
                LivePlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LivePlayer.this.player != null) {
                    LivePlayer.this.player.initRenders();
                    LivePlayer.this.player.start();
                    LivePlayer.this.beginTimeoutCheck();
                }
            }
        };
        this.mTimeoutRunnable = new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.2
            {
                LivePlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                LivePlayer.this.suspend();
                if (LivePlayer.this.mPlayCallBack != null) {
                    LivePlayer.this.mPlayCallBack.onError(30002, LiveSmallWindow.sErrMap.get(30002));
                }
            }
        };
        this.startPlayRunnableMute = new ParamsRunnable() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.3
            boolean mute;

            {
                LivePlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LivePlayer.this.player != null) {
                    LivePlayer.this.player.initRenders();
                    LivePlayer.this.player.start();
                    LivePlayer.this.changeVoiceState(this.mute);
                    LivePlayer.this.beginTimeoutCheck();
                }
            }

            @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ParamsRunnable
            public ParamsRunnable setParams(boolean z) {
                this.mute = z;
                return this;
            }
        };
        this.startReplaySeekToRunnable = new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.4
            {
                LivePlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LivePlayer.this.player == null || LivePlayer.this.dataEntity == null || !"3".equals(LivePlayer.this.dataEntity.liveType)) {
                    return;
                }
                LivePlayer.this.player.seekTo(((int) LivePlayer.this.dataEntity.explainTime) * 1000);
            }
        };
        this.isPlaying = false;
        this.retryTimes = 3;
        this.firstPlay = true;
        this.status = PlayerStatus.STATE_IDLE;
        this.autoPlay = false;
        this.isVoiceOn = false;
        this.currentLivingRoomStatus = "-1";
        this.isShowInSmall = false;
        this.mPlayDurationStatisticsListener = new PlayDurationStatistics.PlayDurationStatisticsListener();
        this.onPlayerStateListener = new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.5
            {
                LivePlayer.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                LivePlayer.this.setPlayStatus(PlayerStatus.STATE_FINISHED);
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                if (Log.D) {
                    Log.e("Arthur", "framworkErr = " + i2 + ", implErr1" + i3);
                }
                LivePlayer.this.isPlaying = false;
                if (NetUtils.isOffNetwork()) {
                    LivePlayer.this.showFailedView();
                    LivePlayer.this.setPlayStatus(PlayerStatus.STATE_ERROR);
                } else if (LivePlayer.this.retryTimes <= 0) {
                    LivePlayer.this.showFinishView();
                    LivePlayer.this.setPlayStatus(PlayerStatus.STATE_FINISHED);
                } else if (i2 == -110 || i2 == -1004 || i2 == -10000) {
                    LivePlayer.this.showFailedView();
                    LivePlayer.this.setPlayStatus(PlayerStatus.STATE_ERROR);
                } else {
                    LivePlayer.this.showFinishView();
                    LivePlayer.this.setPlayStatus(PlayerStatus.STATE_FINISHED);
                }
                if (LivePlayer.this.mPlayCallBack != null) {
                    LivePlayer.this.mPlayCallBack.onError(30001, LiveSmallWindow.sErrMap.get(30001));
                    return true;
                }
                return true;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                if (Log.D) {
                    Log.e("Arthur", "onInfo, mediaInfo = " + i2);
                }
                if (i2 != 3) {
                    if (i2 != 701) {
                        if (i2 == 702 && Log.D) {
                            Log.e("Arthur", "onInfo, buffering end ...");
                            return true;
                        }
                        return true;
                    } else if (Log.D) {
                        Log.e("Arthur", "onInfo, buffering start ...");
                        return true;
                    } else {
                        return true;
                    }
                }
                if (LivePlayer.this.mPlayCallBack != null) {
                    LivePlayer.this.mPlayCallBack.onPlay();
                }
                LivePlayer livePlayer = LivePlayer.this;
                livePlayer.removeCallbacks(livePlayer.mTimeoutRunnable);
                if (Log.D) {
                    Log.e("Arthur", "onInfo, rendering start ...");
                }
                if ("3".equals(LivePlayer.this.currentLivingRoomStatus) && PlayerStatus.STATE_PAUSED == LivePlayer.this.status) {
                    LivePlayer.this.pause();
                    return true;
                }
                LivePlayer.this.hideLoadingView();
                LivePlayer.this.setPlayStatus(PlayerStatus.STATE_PLAYING);
                if (LivePlayer.this.isShowInSmall) {
                    LivePlayer.this.hidePlayingDecoration();
                } else {
                    LivePlayer.this.showPlayingDecoration();
                }
                if (LivePlayer.this.mHandler != null) {
                    LivePlayer.this.mHandler.sendEmptyMessageDelayed(9000, Final.FIVE_SECOND);
                    return true;
                }
                return true;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                if (Log.D) {
                    Log.e("Arthur", "onPrepared ...");
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
            }
        };
        this.playBtnOnClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.6
            {
                LivePlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Log.D) {
                    Log.e("Arthur", "player is playing = " + LivePlayer.this.player.isPlaying());
                }
                if (LivePlayer.this.player == null) {
                    return;
                }
                if (LivePlayer.this.player.isPlaying()) {
                    if (Log.D) {
                        Log.e("Arthur", "player is playing = " + LivePlayer.this.player.isPlaying() + ", try jump into living room");
                    }
                    LivePlayer.this.sendClickMTA("2");
                    if (!TextUtils.isEmpty(LivePlayer.this.dataEntity.openapp)) {
                        Uri parse = Uri.parse(LivePlayer.this.dataEntity.openapp);
                        JSONObject string2JsonObject = JumpUtil.string2JsonObject(parse.getQueryParameter("params"), parse);
                        try {
                            string2JsonObject.put("liveOrigin", "30");
                            string2JsonObject.put("id", LivePlayer.this.dataEntity.liveId);
                            String str = "openApp.jdMobile://virtual?params=" + string2JsonObject.toString();
                            if (Log.D) {
                                Log.d("Arthur", str);
                            }
                            new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(LivePlayer.this.getContext());
                        } catch (JSONException unused) {
                            new OpenAppJumpBuilder.Builder(Uri.parse(LivePlayer.this.dataEntity.openapp)).build().jump(LivePlayer.this.getContext());
                        }
                    }
                    if (LivePlayer.this.jumpIntoLiveRoom != null) {
                        LivePlayer.this.jumpIntoLiveRoom.onJumped();
                        return;
                    }
                    return;
                }
                LivePlayer.this.play();
            }
        };
        this.videoPlayBtnOnClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.7
            {
                LivePlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!LivePlayer.this.player.isPlaying()) {
                    if (LivePlayer.this.status == PlayerStatus.STATE_PAUSED) {
                        LivePlayer.this.start();
                    } else {
                        LivePlayer.this.play();
                    }
                    LivePlayer.this.btnVideoPlay.setImageResource(R.drawable.live_player_video_control_pause);
                    LivePlayer.this.hideVideoControlPanel();
                    return;
                }
                LivePlayer.this.pause();
                LivePlayer.this.btnVideoPlay.setImageResource(R.drawable.live_player_video_control_play);
            }
        };
        this.onMuteClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.8
            {
                LivePlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LivePlayer.this.changeVoiceState(!r2.isVoiceOn);
            }
        };
        this.onRefreshClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.9
            {
                LivePlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LivePlayer.this.retryPlay();
            }
        };
        initView(context);
    }

    public void beginTimeoutCheck() {
        removeCallbacks(this.mTimeoutRunnable);
        postDelayed(this.mTimeoutRunnable, Math.max(1000, this.mPlayTimeoutMillis));
    }

    public void changeVoiceState(boolean z) {
        if (Log.D) {
            Log.e("Arthur", "changeVoiceState, this.isVoiceOn = " + this.isVoiceOn + ", isVoiceOn = " + z);
        }
        this.isVoiceOn = z;
        if (this.player.getPlayerOptions() != null) {
            this.player.setVolume(z ? 1.0f : 0.0f);
        }
        this.btnMute.setImageResource(z ? R.drawable.live_player_voice_on : R.drawable.live_player_mute);
        this.player.setVolume(z ? 1.0f : 0.0f);
        changeOtherVoice();
    }

    private void clearHandlerMsg() {
        PlayerHandler playerHandler = this.mHandler;
        if (playerHandler != null) {
            playerHandler.removeMessages(9000);
            this.mHandler.removeMessages(9001);
        }
    }

    private void hideFailedView() {
        if (Log.D) {
            Log.e("Arthur", "hideFailedView ...");
        }
        if (this.isDestroyed) {
            return;
        }
        this.failToRefreshView.setVisibility(8);
        this.failToRefreshViewSmall.setVisibility(8);
        this.videoCover.setVisibility(8);
        this.videoCoverSmall.setVisibility(8);
        this.loadingView.setVisibility(8);
        this.loadingViewSmall.setVisibility(8);
        this.playBtnContainer.setVisibility(8);
        this.videoControlPanel.setVisibility(8);
        if (this.isShowInSmall) {
            hidePlayingDecoration();
        } else {
            showPlayingDecoration();
        }
    }

    public void hideGoIntoLiveRoomBtn() {
        this.playBtnContainer.setBackgroundResource(R.drawable.live_player_loading_refresh_mask);
        this.goIntoLiveRoomMask.setVisibility(8);
        this.playBtnLabel.setText("");
        this.playBtn.setVisibility(8);
        this.playBtnContainer.setVisibility(8);
    }

    public void hidePlayingDecoration() {
        if (this.isDestroyed) {
            return;
        }
        this.fondAnimViewRight.setVisibility(8);
        this.btnMute.setVisibility(8);
        this.watchingCountContainer.setVisibility(8);
        this.goIntoLiveRoomClickArea.setVisibility(8);
    }

    public void hideVideoControlPanel() {
        this.failToRefreshView.setVisibility(8);
        this.failToRefreshViewSmall.setVisibility(8);
        this.videoCover.setVisibility(8);
        this.videoCoverSmall.setVisibility(8);
        this.loadingView.setVisibility(8);
        this.loadingViewSmall.setVisibility(8);
        this.playBtnContainer.setVisibility(8);
        this.videoCoverSmall.setVisibility(8);
        this.videoCover.setVisibility(8);
        this.failToRefreshView.setVisibility(8);
        this.failToRefreshViewSmall.setVisibility(8);
        showPlayingDecoration();
        this.videoControlPanel.setVisibility(8);
    }

    private void initView(Context context) {
        FrameLayout.inflate(context, R.layout.layout_live_player, this);
        this.playerContainer = (FrameLayout) findViewById(R.id.player_container);
        this.playerBackground = (SimpleDraweeView) findViewById(R.id.live_player_background);
        this.videoCover = (SimpleDraweeView) findViewById(R.id.live_player_video_cover);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.live_player_background_small);
        this.playerBackgroundSmall = simpleDraweeView;
        simpleDraweeView.setVisibility(8);
        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) findViewById(R.id.live_player_video_cover_small);
        this.videoCoverSmall = simpleDraweeView2;
        simpleDraweeView2.setVisibility(8);
        this.playBtnContainer = (FrameLayout) findViewById(R.id.live_player_play_btn_container);
        this.fondAnimViewCenter = (LivePlayerFondAnimView) findViewById(R.id.live_player_favorite_anim_center);
        this.playBtn = (LinearLayout) findViewById(R.id.live_player_play_btn);
        this.playBtnLabel = (TextView) findViewById(R.id.live_player_play_btn_label);
        this.btnRefresh = (TextView) findViewById(R.id.live_player_refresh);
        this.watchingCountContainer = (LinearLayout) findViewById(R.id.live_player_watching_count_container);
        this.textViewWatchingCount = (TextView) findViewById(R.id.live_player_watching_count);
        View findViewById = findViewById(R.id.go_into_live_room_area);
        this.goIntoLiveRoomClickArea = findViewById;
        findViewById.setVisibility(8);
        View findViewById2 = findViewById(R.id.live_player_go_into_live_room_mask);
        this.goIntoLiveRoomMask = findViewById2;
        findViewById2.setVisibility(8);
        this.btnMute = (ImageView) findViewById(R.id.live_player_mute);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.live_player_loading);
        this.loadingView = frameLayout;
        frameLayout.setVisibility(8);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.live_player_loading_small);
        this.loadingViewSmall = frameLayout2;
        frameLayout2.setVisibility(8);
        this.finishedView = (FrameLayout) findViewById(R.id.live_player_finished);
        this.finishedViewBackground = (SimpleDraweeView) findViewById(R.id.live_player_finished_background);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.live_player_finished_info);
        this.finishViewInfo = linearLayout;
        linearLayout.setVisibility(8);
        this.coverIcon = (SimpleDraweeView) findViewById(R.id.video_cover_icon);
        FrameLayout frameLayout3 = (FrameLayout) findViewById(R.id.live_player_finished_small);
        this.finishedViewSmall = frameLayout3;
        frameLayout3.setVisibility(8);
        this.finishedViewBackgroundSmall = (SimpleDraweeView) findViewById(R.id.live_player_finished_background_small);
        this.coverIconSmall = (SimpleDraweeView) findViewById(R.id.video_cover_icon_small);
        FrameLayout frameLayout4 = (FrameLayout) findViewById(R.id.live_player_fail_to_refresh);
        this.failToRefreshView = frameLayout4;
        frameLayout4.setVisibility(8);
        this.failToRefreshView.setOnClickListener(this.onRefreshClickListener);
        FrameLayout frameLayout5 = (FrameLayout) findViewById(R.id.live_player_fail_to_refresh_small);
        this.failToRefreshViewSmall = frameLayout5;
        frameLayout5.setVisibility(8);
        this.failToRefreshViewSmall.setOnClickListener(this.onRefreshClickListener);
        this.fondAnimViewRight = (LivePlayerFondAnimView) findViewById(R.id.live_player_favorite_anim_right);
        this.playBtn.setOnClickListener(this.playBtnOnClickListener);
        this.goIntoLiveRoomClickArea.setOnClickListener(this.playBtnOnClickListener);
        this.btnMute.setOnClickListener(this.onMuteClickListener);
        this.playBtnLineLeft = findViewById(R.id.live_player_play_btn_line_left);
        this.playBtnLineRight = findViewById(R.id.live_player_play_btn_line_right);
        this.videoControlPanel = (FrameLayout) findViewById(R.id.live_player_play_control_panel);
        ImageView imageView = (ImageView) findViewById(R.id.live_player_play_control_play_btn);
        this.btnVideoPlay = imageView;
        imageView.setOnClickListener(this.videoPlayBtnOnClickListener);
        this.mHandler = new PlayerHandler(this);
        hidePlayingDecoration();
        this.isVoiceOn = !isMuted();
        this.isPlaying = false;
        this.retryTimes = 3;
        if (Log.D) {
            Log.e("Arthur", "initView firstPlay = true");
        }
        this.firstPlay = true;
    }

    private boolean isMuted() {
        AudioManager audioManager = (AudioManager) getContext().getSystemService("audio");
        this.audioManager = audioManager;
        return audioManager.getStreamVolume(3) == 0;
    }

    private void pauseVideo() {
        pause();
        this.btnVideoPlay.setImageResource(R.drawable.live_player_video_control_play);
        showVideoControlPanel();
    }

    private void refreshVoiceIcon() {
        this.btnMute.setImageResource(this.isVoiceOn ? R.drawable.live_player_voice_on : R.drawable.live_player_mute);
    }

    private void resumeVideo() {
        this.btnVideoPlay.setImageResource(R.drawable.live_player_video_control_pause);
        hideVideoControlPanel();
        start();
    }

    public void sendClickMTA(String str) {
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("liveid", (Object) this.dataEntity.liveId);
            jDJSONObject.put("sku", (Object) this.dataEntity.skuId);
            jDJSONObject.put("liveType", (Object) this.dataEntity.liveType);
            jDJSONObject.put("frame", (Object) str);
            JDMtaUtils.sendClickDataWithExt(getContext(), "LiveVideo_ProDetail_LiveEntrance", "", "", "Live_Room", "", "", "", jDJSONObject.toString(), null);
        } catch (Exception unused) {
        }
    }

    public void setPlayStatus(PlayerStatus playerStatus) {
        if (Log.D) {
            Log.e("Arthur", "setPlayStatus = " + playerStatus);
        }
        this.status = playerStatus;
        switch (AnonymousClass10.$SwitchMap$com$jingdong$common$widget$custom$liveplayer$LivePlayer$PlayerStatus[playerStatus.ordinal()]) {
            case 1:
                showWatchBtn("\u89c2\u770b\u76f4\u64ad", 0);
                break;
            case 2:
                showLoadingView();
                break;
            case 3:
            case 4:
                break;
            case 5:
                this.isPlaying = false;
                if ("3".equals(this.currentLivingRoomStatus)) {
                    showWatchBtn();
                    break;
                } else {
                    showFinishView();
                    break;
                }
            case 6:
                this.isPlaying = false;
                break;
            default:
                showWatchBtn("\u89c2\u770b\u76f4\u64ad", 0);
                break;
        }
        StatusChangedListener statusChangedListener = this.statusChanged;
        if (statusChangedListener != null) {
            statusChangedListener.onStatusChanged(playerStatus);
        }
        StatusChangedListener statusChangedListener2 = this.statusChangedSmall;
        if (statusChangedListener2 != null) {
            statusChangedListener2.onStatusChanged(playerStatus);
        }
    }

    public void showFailedView() {
        if (Log.D) {
            Log.e("Arthur", "showFailedView ...");
        }
        if (this.isDestroyed) {
            return;
        }
        clearHandlerMsg();
        this.playBtnContainer.setBackgroundResource(R.drawable.live_player_loading_refresh_mask);
        if (Log.D) {
            Log.e("Arthur", "playerContainer.setVisibility(GONE) ...");
        }
        this.playerContainer.setVisibility(8);
        if (this.isShowInSmall) {
            this.videoCoverSmall.setVisibility(0);
            this.videoCover.setVisibility(8);
            this.failToRefreshView.setVisibility(8);
            this.failToRefreshViewSmall.setVisibility(0);
        } else {
            this.videoCover.setVisibility(0);
            this.videoCoverSmall.setVisibility(8);
            this.failToRefreshView.setVisibility(0);
            this.failToRefreshViewSmall.setVisibility(8);
        }
        this.loadingView.setVisibility(8);
        this.loadingViewSmall.setVisibility(8);
        this.playBtnContainer.setVisibility(8);
        this.videoControlPanel.setVisibility(8);
        hidePlayingDecoration();
    }

    public void showGoIntoLiveRoomBtn() {
        if (Log.D) {
            Log.e("Arthur", "showGoIntoLiveRoomBtn, state = " + this.status);
        }
        PlayerStatus playerStatus = this.status;
        if (playerStatus != PlayerStatus.STATE_PLAYING || playerStatus == PlayerStatus.STATE_LOADING || this.isShowInSmall) {
            return;
        }
        this.fondAnimViewCenter.setVisibility(8);
        this.playBtnContainer.setBackgroundResource(R.drawable.live_player_transparent_mask);
        this.goIntoLiveRoomMask.setVisibility(0);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.playBtnLineLeft.getLayoutParams();
        layoutParams.width = DPIUtil.dip2px(50.0f);
        this.playBtnLineLeft.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.playBtnLineRight.getLayoutParams();
        layoutParams2.width = DPIUtil.dip2px(50.0f);
        this.playBtnLineRight.setLayoutParams(layoutParams2);
        this.playBtnLabel.setText("\u70b9\u51fb\u8fdb\u5165\u76f4\u64ad\u95f4");
        this.playBtn.setVisibility(0);
        this.playBtnContainer.setVisibility(0);
        PlayerHandler playerHandler = this.mHandler;
        if (playerHandler != null) {
            playerHandler.sendEmptyMessageDelayed(9001, Final.FIVE_SECOND);
        }
    }

    public void showPlayingDecoration() {
        if (this.isDestroyed) {
            return;
        }
        this.fondAnimViewRight.setVisibility(0);
        refreshVoiceIcon();
        this.btnMute.setVisibility(0);
        String str = this.dataEntity.pageView;
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        long parseLong = str.matches("\\d+(?:\\.\\d+)?") ? Long.parseLong(str) : 0L;
        if (parseLong >= 10000) {
            StringBuilder sb = new StringBuilder();
            DecimalFormat decimalFormat = new DecimalFormat("0.#");
            double d = parseLong;
            Double.isNaN(d);
            sb.append(decimalFormat.format(d / 10000.0d));
            sb.append("\u4e07");
            str = sb.toString();
        }
        this.textViewWatchingCount.setText(str + "\u4eba\u89c2\u770b");
        this.watchingCountContainer.setVisibility(0);
        this.goIntoLiveRoomClickArea.setVisibility(0);
    }

    private void showVideoControlPanel() {
        this.failToRefreshView.setVisibility(8);
        this.failToRefreshViewSmall.setVisibility(8);
        this.videoCover.setVisibility(8);
        this.videoCoverSmall.setVisibility(8);
        this.loadingView.setVisibility(8);
        this.loadingViewSmall.setVisibility(8);
        this.playBtnContainer.setVisibility(8);
        this.videoCoverSmall.setVisibility(8);
        this.videoCover.setVisibility(8);
        this.failToRefreshView.setVisibility(8);
        this.failToRefreshViewSmall.setVisibility(8);
        hidePlayingDecoration();
        this.videoControlPanel.setVisibility(0);
        this.watchingCountContainer.setVisibility(0);
    }

    private boolean tryRestorePlayNeeded() {
        PlayerStatus playerStatus = PlayerStatus.STATE_PLAYING;
        PlayerStatus playerStatus2 = this.status;
        return playerStatus == playerStatus2 || PlayerStatus.STATE_PAUSED == playerStatus2 || PlayerStatus.STATE_LOADING == playerStatus2;
    }

    private void tryRestorePlayStatus(boolean z) {
        if (this.status == PlayerStatus.STATE_FINISHED) {
            showFinishView();
        } else if ("2".equals(this.dataEntity.liveType)) {
            retryPlay();
        } else if ("2".equals(this.currentLivingRoomStatus)) {
            showWatchBtn();
        } else if (z && NetUtils.isWifi() && "3".equals(this.currentLivingRoomStatus) && PlayerStatus.STATE_PAUSED == this.status) {
            resumeVideo();
        } else {
            showWatchBtn();
        }
    }

    public void changeOtherVoice() {
        if (this.isVoiceOn) {
            if (this.isPlaying) {
                if (Log.D) {
                    Log.e(TAG, "changeVoiceState: pauseOtherVoice");
                }
                pauseOtherVoice();
                return;
            }
            return;
        }
        PlayerStatus playerStatus = this.status;
        if (playerStatus == PlayerStatus.STATE_PLAYING || playerStatus == PlayerStatus.STATE_LOADING) {
            resumeOtherVoice();
        }
    }

    public void destroy() {
        if (this.isDestroyed) {
            return;
        }
        this.isDestroyed = true;
        this.statusChanged = null;
        this.floatingWindowClose = null;
        this.jumpIntoLiveRoom = null;
        this.dataEntity = null;
        this.currentUrl = null;
        this.playerBackground = null;
        this.playerBackgroundSmall = null;
        this.videoCover = null;
        this.videoCoverSmall = null;
        this.coverIcon = null;
        this.coverIconSmall = null;
        this.finishedViewBackground = null;
        this.finishedViewBackgroundSmall = null;
        this.playBtnOnClickListener = null;
        this.onMuteClickListener = null;
        this.onRefreshClickListener = null;
        PlayerHandler playerHandler = this.mHandler;
        if (playerHandler != null) {
            playerHandler.removeMessages(9000);
            this.mHandler.removeMessages(9001);
            this.mHandler = null;
        }
        IjkVideoView ijkVideoView = this.player;
        if (ijkVideoView != null) {
            ijkVideoView.removeCallbacks(this.startPlayRunnable);
            this.player.removeCallbacks(this.startPlayRunnableMute);
            this.player.releaseInThread(true);
            this.player = null;
        }
        removeCallbacks(this.mTimeoutRunnable);
        this.startPlayRunnableMute = null;
        this.startPlayRunnable = null;
        this.mPlayCallBack = null;
    }

    public PlayerStatus getStatus() {
        return this.status;
    }

    public SimpleDraweeView getVideoCover() {
        return this.videoCover;
    }

    public Point getVideoViewSize() {
        return this.videoViewSize;
    }

    public void hideLoadingView() {
        if (Log.D) {
            Log.e("Arthur", "hideLoadingView ...");
        }
        if (this.isDestroyed) {
            return;
        }
        this.videoCover.setVisibility(8);
        this.videoCoverSmall.setVisibility(8);
        this.loadingView.setVisibility(8);
        this.loadingViewSmall.setVisibility(8);
        this.playBtnContainer.setVisibility(8);
        this.failToRefreshView.setVisibility(8);
        this.videoControlPanel.setVisibility(8);
    }

    public void init() {
        IPlayerControl.PlayerOptions playerOptions;
        int i2;
        if (Log.D) {
            Log.e("Arthur", "Doing init ...");
        }
        if (!"2".equals(this.currentLivingRoomStatus) && !"2".equals(this.dataEntity.liveType)) {
            playerOptions = new IPlayerControl.PlayerOptions(false);
        } else {
            playerOptions = new IPlayerControl.PlayerOptions(true);
        }
        playerOptions.setIsAdvanceDns(false);
        playerOptions.setAspectRatio(1);
        playerOptions.setCouldMediaCodec(false);
        playerOptions.setLoop(true);
        playerOptions.addCustomOption(2, "skip_loop_filter", 0L);
        if (this.player == null) {
            this.player = new IjkVideoView(getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            this.playerContainer.addView(this.player, 0, layoutParams);
        }
        this.player.setPlayerOptions(playerOptions);
        this.player.setOnPlayerStateListener(this.onPlayerStateListener);
        this.player.addOnStatisticsStateListener(this.mPlayDurationStatisticsListener);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.playerContainer.getLayoutParams();
        int width = DPIUtil.getWidth(getContext());
        if (!"2".equals(this.dataEntity.liveType)) {
            if ("1".equals(this.dataEntity.screen)) {
                layoutParams2.gravity = 16;
                double d = width;
                Double.isNaN(d);
                i2 = (int) (((d * 1.1d) * 9.0d) / 16.0d);
                layoutParams2.width = width;
                layoutParams2.height = i2;
            } else {
                layoutParams2.gravity = 1;
                double d2 = width;
                Double.isNaN(d2);
                int i3 = (int) (((d2 * 1.2d) * 9.0d) / 16.0d);
                layoutParams2.width = i3;
                layoutParams2.height = width;
                width = i3;
                i2 = width;
            }
        } else {
            if ("1".equals(this.dataEntity.screen)) {
                layoutParams2.gravity = 16;
                layoutParams2.width = DPIUtil.dip2px(176.0f);
                layoutParams2.height = DPIUtil.dip2px(99.0f);
            } else {
                layoutParams2.gravity = 1;
                layoutParams2.width = DPIUtil.dip2px(99.0f);
                layoutParams2.height = DPIUtil.dip2px(176.0f);
            }
            i2 = width;
        }
        this.videoViewSize = new Point(width, i2);
        this.playerContainer.setLayoutParams(layoutParams2);
        this.retryTimes = 3;
    }

    public void initRenders() {
        IjkVideoView ijkVideoView = this.player;
        if (ijkVideoView != null) {
            ijkVideoView.initRenders();
        }
    }

    public boolean isAutoPlay() {
        return this.autoPlay;
    }

    public boolean isFirstPlay() {
        return this.firstPlay;
    }

    public boolean isJumpOut() {
        return (this.status == PlayerStatus.STATE_ERROR && this.failToRefreshViewSmall.getVisibility() == 0) ? false : true;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public boolean isShowInSmall() {
        return this.isShowInSmall;
    }

    public boolean isVoiceOn() {
        return this.isVoiceOn;
    }

    public void jump(String str) {
        LiveDataEntity liveDataEntity = this.dataEntity;
        if (liveDataEntity == null || TextUtils.isEmpty(liveDataEntity.openapp)) {
            return;
        }
        Uri parse = Uri.parse(this.dataEntity.openapp);
        sendClickMTA(str);
        new OpenAppJumpBuilder.Builder(parse).build().jump(getContext());
    }

    public void onSmallClick() {
        if (!isJumpOut()) {
            retryPlay();
        } else {
            jump("2");
        }
    }

    public void onSmallStarClick() {
        if (!isJumpOut()) {
            retryPlay();
        } else {
            jump("1");
        }
    }

    public void pause() {
        if (Log.D) {
            Log.e("Arthur", "pause ...");
        }
        this.isPlaying = false;
        IjkVideoView ijkVideoView = this.player;
        if (ijkVideoView != null) {
            ijkVideoView.pause();
            setPlayStatus(PlayerStatus.STATE_PAUSED);
        }
    }

    public void pauseOtherVoice() {
        VideoPlayUtil.muteAudioFocus(getContext(), true);
    }

    public void play() {
        this.playerContainer.setVisibility(0);
        if (TextUtils.isEmpty(this.currentUrl)) {
            return;
        }
        this.firstPlay = false;
        this.isPlaying = true;
        if (this.player == null) {
            init();
        }
        this.player.setVideoPath(this.currentUrl);
        this.player.suspend();
        this.player.post(this.startReplaySeekToRunnable);
        this.player.postDelayed(this.startPlayRunnable, 50L);
        showLoadingView();
        setPlayStatus(PlayerStatus.STATE_LOADING);
        changeOtherVoice();
    }

    public void release() {
        IjkVideoView ijkVideoView = this.player;
        if (ijkVideoView != null) {
            ijkVideoView.releaseInThread(true);
        }
    }

    public void releaseInThread() {
        IjkVideoView ijkVideoView = this.player;
        if (ijkVideoView != null) {
            ijkVideoView.suspend();
            this.player.releaseInThread(true);
        }
    }

    public void reportExpo() {
        LiveDataEntity liveDataEntity = this.dataEntity;
        if (liveDataEntity == null) {
            return;
        }
        try {
            String str = "";
            if (liveDataEntity.liveType.equals("0")) {
                str = String.valueOf(this.curFrame);
            } else if (this.dataEntity.liveType.equals("2")) {
                str = "1";
            } else if (this.dataEntity.status.equals("1") || this.dataEntity.status.equals("2")) {
                str = "2";
            }
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("liveid", (Object) this.dataEntity.liveId);
            jDJSONObject.put("sku", (Object) this.dataEntity.skuId);
            jDJSONObject.put("frame", (Object) str);
            jDJSONObject.put("liveType", (Object) this.dataEntity.liveType);
            if (Log.D) {
                Log.e("Arthur", "reportExpo: " + jDJSONObject.toString());
            }
            JDMtaUtils.sendExposureDataWithExt(getContext(), "LiveVideo_ProDetail_LiveEntrance_Expo", "", "Live_Room", "", "", jDJSONObject.toString(), "", "", "", null);
        } catch (Exception unused) {
        }
    }

    public void request(int i2) {
        if (Log.D) {
            Log.e("Arthur", "this.status = " + this.status);
        }
        PlayerHandler playerHandler = this.mHandler;
        if (playerHandler != null) {
            playerHandler.removeMessages(9001);
        }
        if (i2 == 200) {
            if (this.status == PlayerStatus.STATE_FINISHED) {
                showFinishView();
            } else if ("2".equals(this.currentLivingRoomStatus)) {
                stopPlay();
            } else if ("3".equals(this.currentLivingRoomStatus) && tryRestorePlayNeeded()) {
                pauseVideo();
            } else {
                stopPlay();
            }
            sendMta();
        } else if (i2 != 201) {
            switch (i2) {
                case 100:
                    if (!"2".equals(this.currentLivingRoomStatus) && !"2".equals(this.dataEntity.liveType)) {
                        if (this.player == null) {
                            init();
                        }
                        if (this.firstPlay) {
                            setPlayerSource();
                            return;
                        } else {
                            tryRestorePlayStatus(true);
                            return;
                        }
                    }
                    this.player = null;
                    init();
                    if (this.status == PlayerStatus.STATE_FINISHED) {
                        showFinishView();
                        return;
                    } else {
                        setPlayerSource();
                        return;
                    }
                case 101:
                    if (this.status == PlayerStatus.STATE_FINISHED) {
                        showFinishView();
                    } else if ("2".equals(this.currentLivingRoomStatus)) {
                        stopPlay();
                    } else if ("3".equals(this.currentLivingRoomStatus) && tryRestorePlayNeeded()) {
                        pauseVideo();
                    } else {
                        stopPlay();
                    }
                    sendMta();
                    return;
                case 102:
                    tryRestorePlayStatus(true);
                    return;
                case 103:
                    if (!"2".equals(this.currentLivingRoomStatus) && this.status != PlayerStatus.STATE_FINISHED) {
                        if ("3".equals(this.currentLivingRoomStatus) && tryRestorePlayNeeded()) {
                            pauseVideo();
                        } else {
                            suspend();
                            if ("2".equals(this.currentLivingRoomStatus)) {
                                release();
                            }
                        }
                    } else {
                        suspend();
                        release();
                    }
                    sendMta();
                    return;
                default:
                    return;
            }
        } else {
            tryRestorePlayStatus(false);
        }
    }

    public void reset() {
        this.isPlaying = false;
        suspend();
        setPlayerSource();
        setPlayStatus(PlayerStatus.STATE_IDLE);
    }

    public void resizeVideoView(Point point2) {
        if (point2 == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.playerContainer.getLayoutParams();
        layoutParams.width = point2.x;
        layoutParams.height = point2.y;
        layoutParams.gravity = 17;
        this.playerContainer.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.playerBackgroundSmall.getLayoutParams();
        layoutParams2.width = point2.x;
        layoutParams2.height = point2.y;
        this.playerBackgroundSmall.setLayoutParams(layoutParams2);
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) this.videoCoverSmall.getLayoutParams();
        layoutParams3.width = point2.x;
        layoutParams3.height = point2.y;
        layoutParams3.gravity = 17;
        this.videoCoverSmall.setLayoutParams(layoutParams3);
        FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) this.loadingViewSmall.getLayoutParams();
        layoutParams4.width = point2.x;
        layoutParams4.height = point2.y;
        layoutParams4.gravity = 17;
        this.loadingViewSmall.setLayoutParams(layoutParams4);
        FrameLayout.LayoutParams layoutParams5 = (FrameLayout.LayoutParams) this.failToRefreshViewSmall.getLayoutParams();
        layoutParams5.width = point2.x;
        layoutParams5.height = point2.y;
        layoutParams5.gravity = 17;
        this.failToRefreshViewSmall.setLayoutParams(layoutParams5);
        FrameLayout.LayoutParams layoutParams6 = (FrameLayout.LayoutParams) this.finishedViewSmall.getLayoutParams();
        layoutParams6.width = point2.x;
        layoutParams6.height = point2.y;
        layoutParams6.gravity = 17;
        this.finishedViewSmall.setLayoutParams(layoutParams6);
        FrameLayout.LayoutParams layoutParams7 = (FrameLayout.LayoutParams) this.finishedViewBackgroundSmall.getLayoutParams();
        layoutParams7.width = point2.x;
        layoutParams7.height = point2.y;
        layoutParams7.gravity = 17;
        this.finishedViewBackgroundSmall.setLayoutParams(layoutParams7);
    }

    public void restoreVideoView() {
        if (this.isDestroyed || this.videoViewSize == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.playerContainer.getLayoutParams();
        Point point2 = this.videoViewSize;
        layoutParams.width = point2.x;
        layoutParams.height = point2.y;
        layoutParams.gravity = 17;
        this.playerContainer.setLayoutParams(layoutParams);
    }

    public void resume() {
        this.isPlaying = true;
        IjkVideoView ijkVideoView = this.player;
        if (ijkVideoView != null) {
            ijkVideoView.resume();
        }
    }

    public void resumeOtherVoice() {
        VideoPlayUtil.muteAudioFocus(getContext(), false);
    }

    public void retryPlay() {
        if (TextUtils.isEmpty(this.currentUrl)) {
            return;
        }
        this.retryTimes--;
        this.isPlaying = true;
        showLoadingView();
        if (this.player == null) {
            init();
        }
        if (Log.D) {
            Log.e("Arthur", "playerContainer.setVisibility(VISIBLE) ...");
        }
        this.playerContainer.setVisibility(0);
        this.player.suspend();
        this.player.postDelayed(this.startPlayRunnable, 50L);
        setPlayStatus(PlayerStatus.STATE_LOADING);
    }

    protected void sendMta() {
        PlayDurationStatistics.PlayDurationStatisticsListener playDurationStatisticsListener = this.mPlayDurationStatisticsListener;
        long playDuration = playDurationStatisticsListener == null ? 0L : playDurationStatisticsListener.getPlayDuration();
        if (playDuration < 0) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("liveid", (Object) this.dataEntity.liveId);
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        double d = playDuration;
        Double.isNaN(d);
        jDJSONObject.put("duration", (Object) decimalFormat.format(d / 1000.0d));
        jDJSONObject.put("sku", (Object) this.dataEntity.skuId);
        JDMtaUtils.sendExposureDataWithExt(getContext(), "LiveVideo_ProDetail_DurationExpo", "", "Live_Room", "", "", jDJSONObject.toString(), null);
    }

    public void setAspectRatio(int i2) {
        IjkVideoView ijkVideoView = this.player;
        if (ijkVideoView != null) {
            ijkVideoView.setAspectRatio(i2);
        }
    }

    public void setAutoPlay(boolean z) {
        this.autoPlay = z;
    }

    public void setData(LiveDataEntity liveDataEntity) {
        if (Log.D) {
            Log.e("Arthur", "Doing setData ...");
        }
        this.dataEntity = liveDataEntity;
        String str = liveDataEntity.status;
        this.currentLivingRoomStatus = str;
        if (!"2".equals(str) && !"2".equals(this.dataEntity.liveType)) {
            if ("1".equals(this.currentLivingRoomStatus)) {
                this.currentUrl = this.dataEntity.videoUrl;
            } else if ("3".equals(this.currentLivingRoomStatus)) {
                this.currentUrl = this.dataEntity.videoUrl;
            } else {
                this.currentUrl = "rtmp://jdpull.jd.com/live/" + this.dataEntity.liveId;
            }
        } else {
            this.currentUrl = this.dataEntity.h5PullUrl;
        }
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.setPlaceholder(17);
        JDImageUtils.displayImage(this.dataEntity.indexImage, this.videoCover, jDDisplayImageOptions);
        JDImageUtils.displayImage(this.dataEntity.indexImage, (ImageView) this.playerBackground, new JDDisplayImageOptions().setBitmapConfig(Bitmap.Config.ARGB_8888).setPostProcessor(new IterativeBoxBlurPostProcessor(10)), false);
        if (Log.D) {
            Log.e("Arthur", "show videoCover ...");
        }
        this.videoCover.setVisibility(0);
        JDImageUtils.displayImage(this.dataEntity.indexImage, (ImageView) this.finishedViewBackground, new JDDisplayImageOptions().setBitmapConfig(Bitmap.Config.ARGB_8888).setPostProcessor(new IterativeBoxBlurPostProcessor(20)), false);
        JDImageUtils.displayImage(this.dataEntity.indexImage, this.coverIcon, new JDDisplayImageOptions().setPlaceholder(17).setRoundingParams(RoundingParams.fromCornersRadius(41.5f)));
        JDImageUtils.displayImage(this.dataEntity.indexImage, (ImageView) this.finishedViewBackgroundSmall, new JDDisplayImageOptions().setBitmapConfig(Bitmap.Config.ARGB_8888).setPostProcessor(new IterativeBoxBlurPostProcessor(20)), false);
        JDImageUtils.displayImage(this.dataEntity.indexImage, this.coverIconSmall, new JDDisplayImageOptions().setPlaceholder(17).setRoundingParams(RoundingParams.fromCornersRadius(20.75f)));
        this.isPlaying = false;
        this.retryTimes = 3;
    }

    public void setFloatingWindowCloseListener(FloatingWindowCloseListener floatingWindowCloseListener) {
        this.floatingWindowClose = floatingWindowCloseListener;
    }

    public void setJumpIntoLiveRoomListener(JumpIntoLiveRoomListener jumpIntoLiveRoomListener) {
        this.jumpIntoLiveRoom = jumpIntoLiveRoomListener;
    }

    public void setPlayCallBack(LiveSmallWindowCallback liveSmallWindowCallback) {
        this.mPlayCallBack = liveSmallWindowCallback;
    }

    public void setPlayTimeOutMillis(int i2) {
        this.mPlayTimeoutMillis = i2;
    }

    public void setPlayerSource() {
        if (this.autoPlay && NetUtils.isWifi() && this.firstPlay) {
            this.firstPlay = false;
            changeVoiceState(false);
            play();
            return;
        }
        if (this.firstPlay) {
            this.firstPlay = false;
            changeVoiceState(true);
        } else {
            if (Log.D) {
                Log.e("Arthur", "setPlayerSource, this.isVoiceOn = " + this.isVoiceOn);
            }
            changeVoiceState(this.isVoiceOn);
        }
        setPlayStatus(PlayerStatus.STATE_IDLE);
    }

    public void setShowInSmall(boolean z) {
        if (Log.D) {
            Log.e("Arthur", "setShowInSmall ... showInSmall = " + z);
        }
        if (this.isDestroyed) {
            return;
        }
        this.isShowInSmall = z;
        SimpleDraweeView simpleDraweeView = this.videoCover;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(8);
        }
        if (z) {
            PlayerStatus playerStatus = this.status;
            if (playerStatus == PlayerStatus.STATE_ERROR) {
                showFailedView();
            } else if (playerStatus == PlayerStatus.STATE_LOADING) {
                showLoadingView();
            } else {
                hideFailedView();
                hideLoadingView();
            }
            hideGoIntoLiveRoomBtn();
            hidePlayingDecoration();
            return;
        }
        if (this.status == PlayerStatus.STATE_ERROR) {
            showFailedView();
        } else {
            hideFailedView();
        }
        setPlayStatus(this.status);
    }

    public void setStatusChangedListener(StatusChangedListener statusChangedListener) {
        this.statusChanged = statusChangedListener;
    }

    public void setStatusChangedListenerSmall(StatusChangedListener statusChangedListener) {
        this.statusChangedSmall = statusChangedListener;
    }

    public void setVoiceStatus(boolean z) {
        changeVoiceState(z);
    }

    public void showFinishView() {
        if (Log.D) {
            Log.e("Arthur", "showFinishView ...");
        }
        if (this.isDestroyed) {
            return;
        }
        clearHandlerMsg();
        IjkVideoView ijkVideoView = this.player;
        if (ijkVideoView != null) {
            ijkVideoView.setVisibility(8);
        }
        this.coverIcon.setVisibility(0);
        if (this.isShowInSmall) {
            this.finishedViewSmall.setVisibility(0);
            this.finishedView.setVisibility(8);
            this.finishViewInfo.setVisibility(8);
        } else {
            this.finishedView.setVisibility(0);
            this.finishViewInfo.setVisibility(0);
            this.finishedViewSmall.setVisibility(8);
        }
        this.videoCover.setVisibility(8);
        this.playerBackground.setVisibility(8);
        this.loadingView.setVisibility(8);
        this.loadingViewSmall.setVisibility(8);
        this.failToRefreshView.setVisibility(8);
        this.failToRefreshViewSmall.setVisibility(8);
        this.playBtnContainer.setVisibility(8);
        this.videoControlPanel.setVisibility(8);
        hidePlayingDecoration();
    }

    public void showLoadingView() {
        if (Log.D) {
            Log.e("Arthur", "showLoadingView ...");
        }
        if (this.isDestroyed) {
            return;
        }
        if (this.isShowInSmall) {
            this.loadingViewSmall.setVisibility(0);
            this.videoCoverSmall.setVisibility(0);
            this.loadingView.setVisibility(8);
            this.videoCover.setVisibility(8);
        } else {
            this.loadingView.setVisibility(0);
            this.videoCover.setVisibility(0);
            this.loadingViewSmall.setVisibility(8);
            this.videoCoverSmall.setVisibility(8);
        }
        if (Log.D) {
            Log.e("Arthur", "hide videoCover ...");
        }
        if (this.isShowInSmall) {
            this.videoCoverSmall.setVisibility(0);
        } else {
            this.videoCover.setVisibility(0);
        }
        this.playBtnContainer.setVisibility(8);
        this.failToRefreshView.setVisibility(8);
        this.failToRefreshViewSmall.setVisibility(8);
        this.videoControlPanel.setVisibility(8);
        hidePlayingDecoration();
    }

    public void showWatchBtn() {
        suspend();
        setPlayStatus(PlayerStatus.STATE_IDLE);
    }

    public void start() {
        if (Log.D) {
            Log.e("Arthur", "start ...");
        }
        this.isPlaying = true;
        if (this.player != null) {
            setPlayStatus(PlayerStatus.STATE_LOADING);
            showLoadingView();
            this.player.start();
            setPlayStatus(PlayerStatus.STATE_PLAYING);
            hideLoadingView();
            showPlayingDecoration();
            changeOtherVoice();
        }
    }

    public void stopPlay() {
        this.isPlaying = false;
        suspend();
        showWatchBtn();
    }

    public void suspend() {
        this.isPlaying = false;
        IjkVideoView ijkVideoView = this.player;
        if (ijkVideoView != null) {
            ijkVideoView.suspend();
        }
    }

    private void showWatchBtn(String str, int i2) {
        if (Log.D) {
            Log.e("Arthur", "showWatchBtn: btnLabel = " + str + ", btnFlag = " + i2);
        }
        if (this.isDestroyed) {
            return;
        }
        if (Log.D) {
            Log.e("Arthur", "playerContainer.setVisibility(GONE) ...");
        }
        this.playerContainer.setVisibility(8);
        hideLoadingView();
        this.failToRefreshView.setVisibility(8);
        this.failToRefreshViewSmall.setVisibility(8);
        this.videoControlPanel.setVisibility(8);
        this.playBtnLabel.setText(str);
        if (i2 == 0) {
            if (this.isShowInSmall) {
                this.videoCoverSmall.setVisibility(0);
                this.videoCover.setVisibility(8);
            } else {
                this.videoCover.setVisibility(0);
                this.videoCoverSmall.setVisibility(8);
            }
            this.fondAnimViewCenter.setVisibility(0);
            hidePlayingDecoration();
            this.playBtn.setVisibility(0);
            this.playBtnContainer.setBackgroundResource(R.drawable.live_player_loading_refresh_mask);
            this.playBtnContainer.setVisibility(0);
            this.goIntoLiveRoomClickArea.setVisibility(8);
            this.goIntoLiveRoomMask.setVisibility(8);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.playBtnLineLeft.getLayoutParams();
            layoutParams.width = DPIUtil.dip2px(79.0f);
            this.playBtnLineLeft.setLayoutParams(layoutParams);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.playBtnLineRight.getLayoutParams();
            layoutParams2.width = DPIUtil.dip2px(79.0f);
            this.playBtnLineRight.setLayoutParams(layoutParams2);
            return;
        }
        this.fondAnimViewCenter.setVisibility(8);
        this.playBtn.setVisibility(8);
        this.playBtnContainer.setVisibility(8);
        this.goIntoLiveRoomMask.setVisibility(0);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.playBtnLineLeft.getLayoutParams();
        layoutParams3.width = DPIUtil.dip2px(50.0f);
        this.playBtnLineLeft.setLayoutParams(layoutParams3);
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.playBtnLineRight.getLayoutParams();
        layoutParams4.width = DPIUtil.dip2px(50.0f);
        this.playBtnLineRight.setLayoutParams(layoutParams4);
    }

    public void releaseInThread(boolean z) {
        IjkVideoView ijkVideoView = this.player;
        if (ijkVideoView != null) {
            ijkVideoView.releaseInThread(z);
        }
    }

    public void changeVoiceState() {
        changeVoiceState(!this.isVoiceOn);
    }

    public void play(boolean z, int i2) {
        this.playerContainer.setVisibility(0);
        if (TextUtils.isEmpty(this.currentUrl)) {
            return;
        }
        this.firstPlay = false;
        this.isPlaying = true;
        this.curFrame = i2;
        if (this.player == null) {
            init();
        }
        this.player.setVideoPath(this.currentUrl);
        this.player.suspend();
        this.startPlayRunnableMute.setParams(z);
        this.player.post(this.startReplaySeekToRunnable);
        this.player.postDelayed(this.startPlayRunnableMute, 50L);
        showLoadingView();
        setPlayStatus(PlayerStatus.STATE_LOADING);
        changeOtherVoice();
    }

    public LivePlayer(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isDestroyed = false;
        this.mPlayTimeoutMillis = 5000;
        this.startPlayRunnable = new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.1
            {
                LivePlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LivePlayer.this.player != null) {
                    LivePlayer.this.player.initRenders();
                    LivePlayer.this.player.start();
                    LivePlayer.this.beginTimeoutCheck();
                }
            }
        };
        this.mTimeoutRunnable = new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.2
            {
                LivePlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                LivePlayer.this.suspend();
                if (LivePlayer.this.mPlayCallBack != null) {
                    LivePlayer.this.mPlayCallBack.onError(30002, LiveSmallWindow.sErrMap.get(30002));
                }
            }
        };
        this.startPlayRunnableMute = new ParamsRunnable() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.3
            boolean mute;

            {
                LivePlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LivePlayer.this.player != null) {
                    LivePlayer.this.player.initRenders();
                    LivePlayer.this.player.start();
                    LivePlayer.this.changeVoiceState(this.mute);
                    LivePlayer.this.beginTimeoutCheck();
                }
            }

            @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ParamsRunnable
            public ParamsRunnable setParams(boolean z) {
                this.mute = z;
                return this;
            }
        };
        this.startReplaySeekToRunnable = new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.4
            {
                LivePlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LivePlayer.this.player == null || LivePlayer.this.dataEntity == null || !"3".equals(LivePlayer.this.dataEntity.liveType)) {
                    return;
                }
                LivePlayer.this.player.seekTo(((int) LivePlayer.this.dataEntity.explainTime) * 1000);
            }
        };
        this.isPlaying = false;
        this.retryTimes = 3;
        this.firstPlay = true;
        this.status = PlayerStatus.STATE_IDLE;
        this.autoPlay = false;
        this.isVoiceOn = false;
        this.currentLivingRoomStatus = "-1";
        this.isShowInSmall = false;
        this.mPlayDurationStatisticsListener = new PlayDurationStatistics.PlayDurationStatisticsListener();
        this.onPlayerStateListener = new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.5
            {
                LivePlayer.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                LivePlayer.this.setPlayStatus(PlayerStatus.STATE_FINISHED);
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                if (Log.D) {
                    Log.e("Arthur", "framworkErr = " + i2 + ", implErr1" + i3);
                }
                LivePlayer.this.isPlaying = false;
                if (NetUtils.isOffNetwork()) {
                    LivePlayer.this.showFailedView();
                    LivePlayer.this.setPlayStatus(PlayerStatus.STATE_ERROR);
                } else if (LivePlayer.this.retryTimes <= 0) {
                    LivePlayer.this.showFinishView();
                    LivePlayer.this.setPlayStatus(PlayerStatus.STATE_FINISHED);
                } else if (i2 == -110 || i2 == -1004 || i2 == -10000) {
                    LivePlayer.this.showFailedView();
                    LivePlayer.this.setPlayStatus(PlayerStatus.STATE_ERROR);
                } else {
                    LivePlayer.this.showFinishView();
                    LivePlayer.this.setPlayStatus(PlayerStatus.STATE_FINISHED);
                }
                if (LivePlayer.this.mPlayCallBack != null) {
                    LivePlayer.this.mPlayCallBack.onError(30001, LiveSmallWindow.sErrMap.get(30001));
                    return true;
                }
                return true;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                if (Log.D) {
                    Log.e("Arthur", "onInfo, mediaInfo = " + i2);
                }
                if (i2 != 3) {
                    if (i2 != 701) {
                        if (i2 == 702 && Log.D) {
                            Log.e("Arthur", "onInfo, buffering end ...");
                            return true;
                        }
                        return true;
                    } else if (Log.D) {
                        Log.e("Arthur", "onInfo, buffering start ...");
                        return true;
                    } else {
                        return true;
                    }
                }
                if (LivePlayer.this.mPlayCallBack != null) {
                    LivePlayer.this.mPlayCallBack.onPlay();
                }
                LivePlayer livePlayer = LivePlayer.this;
                livePlayer.removeCallbacks(livePlayer.mTimeoutRunnable);
                if (Log.D) {
                    Log.e("Arthur", "onInfo, rendering start ...");
                }
                if ("3".equals(LivePlayer.this.currentLivingRoomStatus) && PlayerStatus.STATE_PAUSED == LivePlayer.this.status) {
                    LivePlayer.this.pause();
                    return true;
                }
                LivePlayer.this.hideLoadingView();
                LivePlayer.this.setPlayStatus(PlayerStatus.STATE_PLAYING);
                if (LivePlayer.this.isShowInSmall) {
                    LivePlayer.this.hidePlayingDecoration();
                } else {
                    LivePlayer.this.showPlayingDecoration();
                }
                if (LivePlayer.this.mHandler != null) {
                    LivePlayer.this.mHandler.sendEmptyMessageDelayed(9000, Final.FIVE_SECOND);
                    return true;
                }
                return true;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                if (Log.D) {
                    Log.e("Arthur", "onPrepared ...");
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
            }
        };
        this.playBtnOnClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.6
            {
                LivePlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Log.D) {
                    Log.e("Arthur", "player is playing = " + LivePlayer.this.player.isPlaying());
                }
                if (LivePlayer.this.player == null) {
                    return;
                }
                if (LivePlayer.this.player.isPlaying()) {
                    if (Log.D) {
                        Log.e("Arthur", "player is playing = " + LivePlayer.this.player.isPlaying() + ", try jump into living room");
                    }
                    LivePlayer.this.sendClickMTA("2");
                    if (!TextUtils.isEmpty(LivePlayer.this.dataEntity.openapp)) {
                        Uri parse = Uri.parse(LivePlayer.this.dataEntity.openapp);
                        JSONObject string2JsonObject = JumpUtil.string2JsonObject(parse.getQueryParameter("params"), parse);
                        try {
                            string2JsonObject.put("liveOrigin", "30");
                            string2JsonObject.put("id", LivePlayer.this.dataEntity.liveId);
                            String str = "openApp.jdMobile://virtual?params=" + string2JsonObject.toString();
                            if (Log.D) {
                                Log.d("Arthur", str);
                            }
                            new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(LivePlayer.this.getContext());
                        } catch (JSONException unused) {
                            new OpenAppJumpBuilder.Builder(Uri.parse(LivePlayer.this.dataEntity.openapp)).build().jump(LivePlayer.this.getContext());
                        }
                    }
                    if (LivePlayer.this.jumpIntoLiveRoom != null) {
                        LivePlayer.this.jumpIntoLiveRoom.onJumped();
                        return;
                    }
                    return;
                }
                LivePlayer.this.play();
            }
        };
        this.videoPlayBtnOnClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.7
            {
                LivePlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!LivePlayer.this.player.isPlaying()) {
                    if (LivePlayer.this.status == PlayerStatus.STATE_PAUSED) {
                        LivePlayer.this.start();
                    } else {
                        LivePlayer.this.play();
                    }
                    LivePlayer.this.btnVideoPlay.setImageResource(R.drawable.live_player_video_control_pause);
                    LivePlayer.this.hideVideoControlPanel();
                    return;
                }
                LivePlayer.this.pause();
                LivePlayer.this.btnVideoPlay.setImageResource(R.drawable.live_player_video_control_play);
            }
        };
        this.onMuteClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.8
            {
                LivePlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LivePlayer.this.changeVoiceState(!r2.isVoiceOn);
            }
        };
        this.onRefreshClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.9
            {
                LivePlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LivePlayer.this.retryPlay();
            }
        };
        initView(context);
    }

    public LivePlayer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isDestroyed = false;
        this.mPlayTimeoutMillis = 5000;
        this.startPlayRunnable = new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.1
            {
                LivePlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LivePlayer.this.player != null) {
                    LivePlayer.this.player.initRenders();
                    LivePlayer.this.player.start();
                    LivePlayer.this.beginTimeoutCheck();
                }
            }
        };
        this.mTimeoutRunnable = new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.2
            {
                LivePlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                LivePlayer.this.suspend();
                if (LivePlayer.this.mPlayCallBack != null) {
                    LivePlayer.this.mPlayCallBack.onError(30002, LiveSmallWindow.sErrMap.get(30002));
                }
            }
        };
        this.startPlayRunnableMute = new ParamsRunnable() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.3
            boolean mute;

            {
                LivePlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LivePlayer.this.player != null) {
                    LivePlayer.this.player.initRenders();
                    LivePlayer.this.player.start();
                    LivePlayer.this.changeVoiceState(this.mute);
                    LivePlayer.this.beginTimeoutCheck();
                }
            }

            @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ParamsRunnable
            public ParamsRunnable setParams(boolean z) {
                this.mute = z;
                return this;
            }
        };
        this.startReplaySeekToRunnable = new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.4
            {
                LivePlayer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LivePlayer.this.player == null || LivePlayer.this.dataEntity == null || !"3".equals(LivePlayer.this.dataEntity.liveType)) {
                    return;
                }
                LivePlayer.this.player.seekTo(((int) LivePlayer.this.dataEntity.explainTime) * 1000);
            }
        };
        this.isPlaying = false;
        this.retryTimes = 3;
        this.firstPlay = true;
        this.status = PlayerStatus.STATE_IDLE;
        this.autoPlay = false;
        this.isVoiceOn = false;
        this.currentLivingRoomStatus = "-1";
        this.isShowInSmall = false;
        this.mPlayDurationStatisticsListener = new PlayDurationStatistics.PlayDurationStatisticsListener();
        this.onPlayerStateListener = new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.5
            {
                LivePlayer.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                LivePlayer.this.setPlayStatus(PlayerStatus.STATE_FINISHED);
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i22, int i3) {
                if (Log.D) {
                    Log.e("Arthur", "framworkErr = " + i22 + ", implErr1" + i3);
                }
                LivePlayer.this.isPlaying = false;
                if (NetUtils.isOffNetwork()) {
                    LivePlayer.this.showFailedView();
                    LivePlayer.this.setPlayStatus(PlayerStatus.STATE_ERROR);
                } else if (LivePlayer.this.retryTimes <= 0) {
                    LivePlayer.this.showFinishView();
                    LivePlayer.this.setPlayStatus(PlayerStatus.STATE_FINISHED);
                } else if (i22 == -110 || i22 == -1004 || i22 == -10000) {
                    LivePlayer.this.showFailedView();
                    LivePlayer.this.setPlayStatus(PlayerStatus.STATE_ERROR);
                } else {
                    LivePlayer.this.showFinishView();
                    LivePlayer.this.setPlayStatus(PlayerStatus.STATE_FINISHED);
                }
                if (LivePlayer.this.mPlayCallBack != null) {
                    LivePlayer.this.mPlayCallBack.onError(30001, LiveSmallWindow.sErrMap.get(30001));
                    return true;
                }
                return true;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i22, int i3) {
                if (Log.D) {
                    Log.e("Arthur", "onInfo, mediaInfo = " + i22);
                }
                if (i22 != 3) {
                    if (i22 != 701) {
                        if (i22 == 702 && Log.D) {
                            Log.e("Arthur", "onInfo, buffering end ...");
                            return true;
                        }
                        return true;
                    } else if (Log.D) {
                        Log.e("Arthur", "onInfo, buffering start ...");
                        return true;
                    } else {
                        return true;
                    }
                }
                if (LivePlayer.this.mPlayCallBack != null) {
                    LivePlayer.this.mPlayCallBack.onPlay();
                }
                LivePlayer livePlayer = LivePlayer.this;
                livePlayer.removeCallbacks(livePlayer.mTimeoutRunnable);
                if (Log.D) {
                    Log.e("Arthur", "onInfo, rendering start ...");
                }
                if ("3".equals(LivePlayer.this.currentLivingRoomStatus) && PlayerStatus.STATE_PAUSED == LivePlayer.this.status) {
                    LivePlayer.this.pause();
                    return true;
                }
                LivePlayer.this.hideLoadingView();
                LivePlayer.this.setPlayStatus(PlayerStatus.STATE_PLAYING);
                if (LivePlayer.this.isShowInSmall) {
                    LivePlayer.this.hidePlayingDecoration();
                } else {
                    LivePlayer.this.showPlayingDecoration();
                }
                if (LivePlayer.this.mHandler != null) {
                    LivePlayer.this.mHandler.sendEmptyMessageDelayed(9000, Final.FIVE_SECOND);
                    return true;
                }
                return true;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                if (Log.D) {
                    Log.e("Arthur", "onPrepared ...");
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
            }
        };
        this.playBtnOnClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.6
            {
                LivePlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Log.D) {
                    Log.e("Arthur", "player is playing = " + LivePlayer.this.player.isPlaying());
                }
                if (LivePlayer.this.player == null) {
                    return;
                }
                if (LivePlayer.this.player.isPlaying()) {
                    if (Log.D) {
                        Log.e("Arthur", "player is playing = " + LivePlayer.this.player.isPlaying() + ", try jump into living room");
                    }
                    LivePlayer.this.sendClickMTA("2");
                    if (!TextUtils.isEmpty(LivePlayer.this.dataEntity.openapp)) {
                        Uri parse = Uri.parse(LivePlayer.this.dataEntity.openapp);
                        JSONObject string2JsonObject = JumpUtil.string2JsonObject(parse.getQueryParameter("params"), parse);
                        try {
                            string2JsonObject.put("liveOrigin", "30");
                            string2JsonObject.put("id", LivePlayer.this.dataEntity.liveId);
                            String str = "openApp.jdMobile://virtual?params=" + string2JsonObject.toString();
                            if (Log.D) {
                                Log.d("Arthur", str);
                            }
                            new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(LivePlayer.this.getContext());
                        } catch (JSONException unused) {
                            new OpenAppJumpBuilder.Builder(Uri.parse(LivePlayer.this.dataEntity.openapp)).build().jump(LivePlayer.this.getContext());
                        }
                    }
                    if (LivePlayer.this.jumpIntoLiveRoom != null) {
                        LivePlayer.this.jumpIntoLiveRoom.onJumped();
                        return;
                    }
                    return;
                }
                LivePlayer.this.play();
            }
        };
        this.videoPlayBtnOnClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.7
            {
                LivePlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!LivePlayer.this.player.isPlaying()) {
                    if (LivePlayer.this.status == PlayerStatus.STATE_PAUSED) {
                        LivePlayer.this.start();
                    } else {
                        LivePlayer.this.play();
                    }
                    LivePlayer.this.btnVideoPlay.setImageResource(R.drawable.live_player_video_control_pause);
                    LivePlayer.this.hideVideoControlPanel();
                    return;
                }
                LivePlayer.this.pause();
                LivePlayer.this.btnVideoPlay.setImageResource(R.drawable.live_player_video_control_play);
            }
        };
        this.onMuteClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.8
            {
                LivePlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LivePlayer.this.changeVoiceState(!r2.isVoiceOn);
            }
        };
        this.onRefreshClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.LivePlayer.9
            {
                LivePlayer.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LivePlayer.this.retryPlay();
            }
        };
        initView(context);
    }
}
