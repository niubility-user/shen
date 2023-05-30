package com.jingdong.common.widget.custom.livewidget.holder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.widget.custom.livewidget.bean.StatusCode;
import com.jingdong.common.widget.video.IjkVideoViewWithReport;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.oklog.OKLog;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.ext.report.bean.PlayerPerformInfo;

/* loaded from: classes12.dex */
public class JDIjkLiveVideoViewHolder extends IJDVideoViewHolder {
    private IjkVideoView mIjkVideoView;
    private IPlayerControl.OnPlayerStateListener mOnPlayerStateListener;
    private boolean mReplayer;
    private String url;
    private final String TAG = "==JDIjkLiveVideoViewHolder";
    private Handler mHandler = null;
    private final int MSG_OVER_TIME = 1;
    private final int OVER_TIME_DURATION = 2000;

    public JDIjkLiveVideoViewHolder(Context context, String str, boolean z, String str2, boolean z2) {
        this.mReplayer = false;
        this.mReplayer = z;
        this.mIjkVideoView = new IjkVideoViewWithReport(context);
        IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(!z);
        if (OKLog.D) {
            playerOptions.setDebugLog(true);
        }
        playerOptions.setPlayerPerformInfo(new PlayerPerformInfo(str2));
        playerOptions.addCustomOption(str);
        playerOptions.setVolume(z2 ? 0.0f : 1.0f);
        playerOptions.setCouldMediaCodec(true);
        if (!z) {
            playerOptions.setIsAdvanceDns(false);
        }
        this.mIjkVideoView.setPlayerOptions(playerOptions);
        this.mIjkVideoView.addSurfaceHolderCallback(new SurfaceHolder.Callback() { // from class: com.jingdong.common.widget.custom.livewidget.holder.JDIjkLiveVideoViewHolder.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
                OKLog.d("==JDIjkLiveVideoViewHolder", "surfaceChanged:width:" + i3 + ",height:" + i4);
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                OKLog.d("==JDIjkLiveVideoViewHolder", "surface create");
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                OKLog.d("==JDIjkLiveVideoViewHolder", "surface destroy");
            }
        });
    }

    private void createOverTimeHandler() {
        this.mHandler = new Handler() { // from class: com.jingdong.common.widget.custom.livewidget.holder.JDIjkLiveVideoViewHolder.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null || message.what != 1 || JDIjkLiveVideoViewHolder.this.mIjkVideoView == null) {
                    return;
                }
                if (JDIjkLiveVideoViewHolder.this.mOnPlayerStateListener != null) {
                    JDIjkLiveVideoViewHolder.this.mOnPlayerStateListener.onError(StatusCode.PLAY_ERROR_STREAM_TIMEOUT, 0);
                }
                Log.d("MMM", "execute MSG_OVER_TIME");
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void noticeOverTimeHandler(int i2) {
        if (i2 == 701) {
            if (this.mHandler == null) {
                createOverTimeHandler();
            }
            this.mHandler.sendEmptyMessageDelayed(1, 2000L);
            Log.d("MMM", "send MSG_OVER_TIME");
        } else if (i2 == 702 || i2 == 3) {
            removeTimeHandlerMsg();
        }
    }

    private void noticeOverTimerHandlerWithVideoInit() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(1, 4000L);
            Log.d("MMM", "send MSG_OVER_TIME");
        }
    }

    private void removeTimeHandlerMsg() {
        Log.d("MMM", "remove MSG_OVER_TIME");
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(1);
        }
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public long getTcpSpeed() {
        OKLog.d("==JDIjkLiveVideoViewHolder", "getTcpSpeed");
        return this.mIjkVideoView.getTcpSpeed();
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public IjkVideoView getView() {
        OKLog.d("==JDIjkLiveVideoViewHolder", "getView");
        return this.mIjkVideoView;
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public void initRenders() {
        OKLog.d("==JDIjkLiveVideoViewHolder", "initRenders");
        this.mIjkVideoView.postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.livewidget.holder.JDIjkLiveVideoViewHolder.3
            @Override // java.lang.Runnable
            public void run() {
                JDIjkLiveVideoViewHolder.this.mIjkVideoView.initRenders();
            }
        }, 50L);
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public void onDestroy() {
        OKLog.d("==JDIjkLiveVideoViewHolder", "onDestroy");
        super.onDestroy();
        removeTimeHandlerMsg();
        this.mIjkVideoView.forceRelase(true);
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public void onResume() {
        OKLog.d("==JDIjkLiveVideoViewHolder", "onResume");
        super.onResume();
        this.mIjkVideoView.suspend();
        initRenders();
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public void onStart() {
        super.onStart();
        onResume();
        noticeOverTimerHandlerWithVideoInit();
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public void removeListener() {
        removeTimeHandlerMsg();
        this.mOnPlayerStateListener = null;
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public void setDataSource(String str) {
        OKLog.d("==JDIjkLiveVideoViewHolder", "setDataSource " + str);
        this.url = str;
        this.mIjkVideoView.setVideoPath(str);
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public void setMutePlay(boolean z) {
        this.mIjkVideoView.setVolume(z ? 0.0f : 1.0f);
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public void setOnPlayerStateListener(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
        this.mOnPlayerStateListener = onPlayerStateListener;
        this.mIjkVideoView.setOnPlayerStateListener(new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.widget.custom.livewidget.holder.JDIjkLiveVideoViewHolder.2
            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                if (JDIjkLiveVideoViewHolder.this.mReplayer) {
                    JDIjkLiveVideoViewHolder.this.mIjkVideoView.start();
                } else if (JDIjkLiveVideoViewHolder.this.mOnPlayerStateListener != null) {
                    JDIjkLiveVideoViewHolder.this.mOnPlayerStateListener.onCompletion();
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
                if (JDIjkLiveVideoViewHolder.this.mOnPlayerStateListener != null) {
                    JDIjkLiveVideoViewHolder.this.mOnPlayerStateListener.onCreatePlayer();
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                OKLog.d("MMM", "onError " + i2 + LangUtils.SINGLE_SPACE + i3);
                if (JDIjkLiveVideoViewHolder.this.mOnPlayerStateListener != null) {
                    return JDIjkLiveVideoViewHolder.this.mOnPlayerStateListener.onError(i2, i3);
                }
                return true;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                OKLog.d("MMM", "onInfo " + i2 + LangUtils.SINGLE_SPACE + i3);
                JDIjkLiveVideoViewHolder.this.noticeOverTimeHandler(i2);
                if (JDIjkLiveVideoViewHolder.this.mOnPlayerStateListener != null) {
                    return JDIjkLiveVideoViewHolder.this.mOnPlayerStateListener.onInfo(i2, i3);
                }
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                if (JDIjkLiveVideoViewHolder.this.mOnPlayerStateListener != null) {
                    JDIjkLiveVideoViewHolder.this.mOnPlayerStateListener.onPrepared(j2);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
            }
        });
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public void setOnVideoSizeChanged(IPlayerControl.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.mIjkVideoView.setOnVideoSizeChangedListener(onVideoSizeChangedListener);
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public void setReplayUrl(String str) {
        OKLog.d("==JDIjkLiveVideoViewHolder", "setReplayUrl");
        this.mReplayer = true;
        this.mIjkVideoView.setVideoPath(str);
        onResume();
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public void stopAndRelease() {
        OKLog.d("==JDIjkLiveVideoViewHolder", "stopAndRelease");
        super.stopAndRelease();
        this.mIjkVideoView.suspend();
        removeTimeHandlerMsg();
    }

    @Override // com.jingdong.common.widget.custom.livewidget.holder.IJDVideoViewHolder
    public void suspendPlay() {
        this.mIjkVideoView.suspend();
    }
}
