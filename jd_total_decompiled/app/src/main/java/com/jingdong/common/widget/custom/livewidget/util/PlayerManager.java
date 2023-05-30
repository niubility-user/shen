package com.jingdong.common.widget.custom.livewidget.util;

import android.app.Activity;
import android.content.Context;
import com.jingdong.common.network.ReportVideoSpeedUtil;
import com.jingdong.common.widget.custom.livewidget.bean.PlayerParamsUtils;
import com.jingdong.common.widget.custom.livewidget.bean.StatusCode;
import com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder;
import com.jingdong.common.widget.custom.livewidget.holder.JDIjkLiveVideoViewHolder;
import com.jingdong.common.widget.custom.livewidget.util.NetWorkManager;
import com.jingdong.sdk.oklog.OKLog;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;

/* loaded from: classes12.dex */
public class PlayerManager implements NetWorkManager.NetCall {
    public static long FIRST_FRAME_COST_TIME = 0;
    private static final String TAG = "==PlayerManager";
    private IPlayVideoStatusCallBack mCallBack;
    private INetChangeRestartListener mINetChangeRestartListener;
    private PerfReportUtil mPerfReportUtil;
    private PlayerParamsUtils.PlayerParamsEntity mPlayerParamsEntity;
    private String mUrl;
    private IJDVideoViewHolder videoView;
    private boolean isJoinAnchor = false;
    private int weight = 0;
    private int height = 0;
    private boolean netIsClosed = false;
    private IPlayerControl.OnVideoSizeChangedListener onVideoSizeChangedListener = new IPlayerControl.OnVideoSizeChangedListener() { // from class: com.jingdong.common.widget.custom.livewidget.util.PlayerManager.2
        {
            PlayerManager.this = this;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnVideoSizeChangedListener
        public void onVideoSizeChanged(int i2, int i3) {
            if (PlayerManager.this.mCallBack != null) {
                if (PlayerManager.this.weight == i2 && PlayerManager.this.height == i3) {
                    return;
                }
                PlayerManager.this.weight = i2;
                PlayerManager.this.height = i3;
                if (i2 * 8 == i3 * 9) {
                    PlayerManager.this.isJoinAnchor = true;
                    PlayerManager.this.mCallBack.onStateResult(210, 0);
                    return;
                }
                if (PlayerManager.this.isJoinAnchor) {
                    PlayerManager.this.mCallBack.onStateResult(211, 0);
                }
                PlayerManager.this.isJoinAnchor = false;
            }
        }
    };
    private IPlayerControl.OnPlayerStateListener onPlayerStateListener = new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.widget.custom.livewidget.util.PlayerManager.3
        {
            PlayerManager.this = this;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
            PlayerManager.this.onStateResult(202, 0);
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCreatePlayer() {
            OKLog.d(PlayerManager.TAG, "onCreatePlayer");
            PlayerManager.this.onStateResult(200, 0);
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            OKLog.d(PlayerManager.TAG, "frameworkErr:" + i2 + "---implErr:" + i3);
            int i4 = StatusCode.PLAY_ERROR_STREAM_TIMEOUT;
            if (i2 != -1010 && i2 != -1007) {
                if (i2 == -1004) {
                    i4 = StatusCode.PLAY_ERROR_NONETWORK;
                } else if (i2 != -110) {
                    if (i2 == 1) {
                        i4 = StatusCode.PLAY_ERROR_UNKNOWN;
                    } else if (i2 != 100 && i2 != 200) {
                        if (i2 != 510000) {
                            if (i3 == -858797304) {
                                i4 = StatusCode.MEDIADATA_AUTH_FAILED;
                            }
                        }
                    }
                }
                PlayerManager.this.onStateResult(205, i4);
                return true;
            }
            i4 = 0;
            PlayerManager.this.onStateResult(205, i4);
            return true;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onInfo(int i2, int i3) {
            if (i2 == 3) {
                PerfReportUtil unused = PlayerManager.this.mPerfReportUtil;
                PlayerManager.this.onStateResult(206, StatusCode.PLAY_INFO_VIDEO_RENDERING_START);
                return false;
            } else if (i2 == 701) {
                PlayerManager.this.onStateResult(206, StatusCode.PLAY_INFO_BUFFERING_START);
                return false;
            } else if (i2 != 702) {
                return false;
            } else {
                PlayerManager.this.onStateResult(206, StatusCode.PLAY_INFO_BUFFERING_END);
                return false;
            }
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onPrepared(long j2) {
            PlayerManager.FIRST_FRAME_COST_TIME = j2;
            OKLog.d(PlayerManager.TAG, "onPrepared");
            OKLog.d(PlayerManager.TAG, "\u62c9\u6d41\u8017\u65f6:" + PlayerManager.FIRST_FRAME_COST_TIME);
            PlayerManager.this.onStateResult(208, 0);
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onSeekComplete() {
        }
    };

    /* loaded from: classes12.dex */
    public interface INetChangeRestartListener {
        void onNetChangeRestart();
    }

    /* loaded from: classes12.dex */
    public interface IPlayVideoStatusCallBack {
        void onStateResult(int i2, int i3);
    }

    public PlayerManager(Context context, PlayerParamsUtils.PlayerParamsEntity playerParamsEntity, IPlayVideoStatusCallBack iPlayVideoStatusCallBack, String str) {
        if (playerParamsEntity == null) {
            return;
        }
        this.mPlayerParamsEntity = playerParamsEntity;
        this.mUrl = playerParamsEntity.url;
        buildVideoView(context, playerParamsEntity.playMode, str);
        this.mCallBack = iPlayVideoStatusCallBack;
    }

    private void buildVideoView(Context context, int i2, String str) {
        if (i2 != 10000 && i2 != 10003) {
            PlayerParamsUtils.PlayerParamsEntity playerParamsEntity = this.mPlayerParamsEntity;
            boolean z = playerParamsEntity.isReplay;
            this.videoView = new JDIjkLiveVideoViewHolder(context, z ? playerParamsEntity.replayerOptionJsonStr : playerParamsEntity.playerOptionJsonStr, z, str, playerParamsEntity.mMutePlay);
        } else {
            if (OKLog.D) {
                OKLog.d(TAG, "new JDLiveVideoView()");
            }
            PlayerParamsUtils.PlayerParamsEntity playerParamsEntity2 = this.mPlayerParamsEntity;
            boolean z2 = playerParamsEntity2.isReplay;
            this.videoView = new JDIjkLiveVideoViewHolder(context, z2 ? playerParamsEntity2.replayerOptionJsonStr : playerParamsEntity2.playerOptionJsonStr, z2, str, playerParamsEntity2.mMutePlay);
        }
        ReportSpeedHelp.getDefault().setVideoSpeedDetector(new ReportVideoSpeedUtil.VideoSpeedDetector() { // from class: com.jingdong.common.widget.custom.livewidget.util.PlayerManager.1
            {
                PlayerManager.this = this;
            }

            @Override // com.jingdong.common.network.ReportVideoSpeedUtil.VideoSpeedDetector
            public long getVideoSpeed() {
                if (PlayerManager.this.videoView == null) {
                    return -1L;
                }
                return PlayerManager.this.videoView.getTcpSpeed();
            }
        });
        this.videoView.setOnPlayerStateListener(this.onPlayerStateListener);
        this.videoView.setDataSource(this.mUrl);
        this.videoView.setOnVideoSizeChanged(this.onVideoSizeChangedListener);
    }

    private void notifyNetChangeRestart() {
        INetChangeRestartListener iNetChangeRestartListener = this.mINetChangeRestartListener;
        if (iNetChangeRestartListener != null) {
            iNetChangeRestartListener.onNetChangeRestart();
        }
    }

    public IJDVideoViewHolder getIJDVideoView() {
        return this.videoView;
    }

    public IjkVideoView getVideoView() {
        IJDVideoViewHolder iJDVideoViewHolder = this.videoView;
        if (iJDVideoViewHolder == null) {
            return null;
        }
        return iJDVideoViewHolder.getView();
    }

    public boolean isPlaying() {
        if (getVideoView() != null) {
            return getVideoView().isPlaying();
        }
        return false;
    }

    @Override // com.jingdong.common.widget.custom.livewidget.util.NetWorkManager.NetCall
    public void netCall(Context context, int i2) {
    }

    public void onDestroy() {
        if (OKLog.D) {
            OKLog.d("MMM", "mPlayEngin.onDestroy()");
        }
        if (this.videoView != null) {
            if (OKLog.D) {
                OKLog.d("MMM", "videoView.onDestroy()");
            }
            this.videoView.onDestroy();
            this.videoView = null;
        }
    }

    public void onResume() {
        if (this.videoView != null) {
            if (OKLog.D) {
                OKLog.d("MMM", "videoView.onResume()");
            }
            this.videoView.onResume();
        }
    }

    public void onStateResult(int i2, int i3) {
        IPlayVideoStatusCallBack iPlayVideoStatusCallBack = this.mCallBack;
        if (iPlayVideoStatusCallBack != null) {
            iPlayVideoStatusCallBack.onStateResult(i2, i3);
        }
    }

    public void onStop() {
        if (OKLog.D) {
            OKLog.d("MMM", "mPlayEngin.onStop()");
        }
        if (this.videoView != null) {
            if (OKLog.D) {
                OKLog.d("MMM", "videoView.stopAndRelease()");
            }
            this.videoView.stopAndRelease();
        }
    }

    public void removeListener() {
        IJDVideoViewHolder iJDVideoViewHolder = this.videoView;
        if (iJDVideoViewHolder != null) {
            iJDVideoViewHolder.removeListener();
        }
    }

    public void retry() {
        if (this.videoView != null) {
            if (OKLog.D) {
                OKLog.d("MMM", "videoView.retry()");
            }
            this.videoView.onStart();
        }
    }

    public void setINetChangeRestartListener(INetChangeRestartListener iNetChangeRestartListener) {
        this.mINetChangeRestartListener = iNetChangeRestartListener;
    }

    public void setMutePlay(boolean z) {
        IJDVideoViewHolder iJDVideoViewHolder = this.videoView;
        if (iJDVideoViewHolder != null) {
            iJDVideoViewHolder.setMutePlay(z);
        }
    }

    public void setPerfReportUtil(PerfReportUtil perfReportUtil) {
        this.mPerfReportUtil = perfReportUtil;
    }

    public void setReplayUri(Activity activity, String str, boolean z, String str2) {
        if (this.videoView != null) {
            if (OKLog.D) {
                OKLog.d("MMM", "videoView.onDestroy()");
            }
            this.videoView.onDestroy();
            this.videoView = null;
        }
        PlayerParamsUtils.PlayerParamsEntity playerParamsEntity = this.mPlayerParamsEntity;
        JDIjkLiveVideoViewHolder jDIjkLiveVideoViewHolder = new JDIjkLiveVideoViewHolder(activity, z ? playerParamsEntity.replayerOptionJsonStr : playerParamsEntity.playerOptionJsonStr, z, str2, this.mPlayerParamsEntity.mMutePlay);
        this.videoView = jDIjkLiveVideoViewHolder;
        jDIjkLiveVideoViewHolder.setOnPlayerStateListener(this.onPlayerStateListener);
        this.videoView.setOnVideoSizeChanged(this.onVideoSizeChangedListener);
        if (z) {
            this.videoView.setReplayUrl(str);
        } else {
            this.videoView.setDataSource(str);
        }
    }

    public void suspendPlay() {
        IJDVideoViewHolder iJDVideoViewHolder = this.videoView;
        if (iJDVideoViewHolder != null) {
            iJDVideoViewHolder.suspendPlay();
        }
    }

    public void update(PlayerParamsUtils.PlayerParamsEntity playerParamsEntity) {
        if (playerParamsEntity == null) {
            return;
        }
        this.mPlayerParamsEntity = playerParamsEntity;
        String str = playerParamsEntity.url;
        this.mUrl = str;
        IJDVideoViewHolder iJDVideoViewHolder = this.videoView;
        if (iJDVideoViewHolder != null) {
            iJDVideoViewHolder.setDataSource(str);
        }
    }
}
