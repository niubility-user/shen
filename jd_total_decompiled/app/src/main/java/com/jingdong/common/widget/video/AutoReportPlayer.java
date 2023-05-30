package com.jingdong.common.widget.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.jingdong.common.widget.custom.CustomIjkPlayer;
import com.jingdong.common.widget.video.ReportVideoSpeedUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;

@Deprecated
/* loaded from: classes12.dex */
public class AutoReportPlayer extends CustomIjkPlayer {
    private static HashMap<String, Long> playPositionMap = new HashMap<>();
    private String mArticleId;
    private OnPlayDurationListener mOnPlayDurationListener;
    private IPlayerControl.OnPlayerStateListener mOnPlayerStateListener;
    private ReportVideoSpeedUtil mReportVideoSpeedUtil;
    private TotalReporterManager mVideoBuyReporterManager;

    /* loaded from: classes12.dex */
    public interface OnPlayDurationListener {
        void onProduceDuration(long j2, long j3);
    }

    public AutoReportPlayer(Context context) {
        super(context);
        init();
    }

    public static long getPlayPosition(String str) {
        Long l2;
        if (TextUtils.isEmpty(str) || (l2 = playPositionMap.get(str)) == null || l2.longValue() <= 0) {
            return 0L;
        }
        playPositionMap.remove(str);
        return l2.longValue();
    }

    private void init() {
        super.setOnPlayerStateListener(new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.widget.video.AutoReportPlayer.1
            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                if (AutoReportPlayer.this.mOnPlayerStateListener != null) {
                    AutoReportPlayer.this.mOnPlayerStateListener.onCompletion();
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
                if (AutoReportPlayer.this.mOnPlayerStateListener != null) {
                    AutoReportPlayer.this.mOnPlayerStateListener.onCreatePlayer();
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                if (AutoReportPlayer.this.mOnPlayerStateListener != null) {
                    return AutoReportPlayer.this.mOnPlayerStateListener.onError(i2, i3);
                }
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                if (AutoReportPlayer.this.mOnPlayerStateListener != null) {
                    return AutoReportPlayer.this.mOnPlayerStateListener.onInfo(i2, i3);
                }
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                if (AutoReportPlayer.this.mOnPlayerStateListener != null) {
                    AutoReportPlayer.this.mOnPlayerStateListener.onPrepared(j2);
                }
                if (AutoReportPlayer.this.mVideoBuyReporterManager != null) {
                    AutoReportPlayer.this.mVideoBuyReporterManager.playerPrepared(j2);
                    AutoReportPlayer.this.mVideoBuyReporterManager.setVideoLength(AutoReportPlayer.this.getDuration());
                }
                AutoReportPlayer.this.mReportVideoSpeedUtil.addLoadCostTime(j2);
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
                if (AutoReportPlayer.this.mOnPlayerStateListener != null) {
                    AutoReportPlayer.this.mOnPlayerStateListener.onSeekComplete();
                }
                if (OKLog.D) {
                    OKLog.d("IjkVideoPlayer", "onSeekComplete " + AutoReportPlayer.this.isPlaying());
                }
            }
        });
        ReportVideoSpeedUtil reportVideoSpeedUtil = new ReportVideoSpeedUtil();
        this.mReportVideoSpeedUtil = reportVideoSpeedUtil;
        reportVideoSpeedUtil.setVideoSpeedDetector(new ReportVideoSpeedUtil.VideoSpeedDetector() { // from class: com.jingdong.common.widget.video.AutoReportPlayer.2
            @Override // com.jingdong.common.widget.video.ReportVideoSpeedUtil.VideoSpeedDetector
            public long getVideoSpeed() {
                return AutoReportPlayer.this.getTcpSpeed();
            }
        });
        super.addOnStatisticsStateListener(new IPlayerControl.OnStatisticsStateListener() { // from class: com.jingdong.common.widget.video.AutoReportPlayer.3
            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnStatisticsStateListener
            public void pause() {
                AutoReportPlayer.this.mReportVideoSpeedUtil.stop();
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnStatisticsStateListener
            public void start() {
                AutoReportPlayer.this.mReportVideoSpeedUtil.start();
            }
        });
    }

    private void reportPlayDuration() {
        if (this.mVideoBuyReporterManager == null) {
            return;
        }
        long playDuration = getPlayDuration();
        if (playDuration <= 0) {
            return;
        }
        long duration = getDuration();
        long currentPosition = getCurrentPosition();
        if (1000 + currentPosition < duration && !TextUtils.isEmpty(this.mVideoBuyReporterManager.getArticleId())) {
            savePlayPosition(this.mVideoBuyReporterManager.getArticleId(), currentPosition);
        }
        this.mVideoBuyReporterManager.reportPlayInfo(playDuration);
        OnPlayDurationListener onPlayDurationListener = this.mOnPlayDurationListener;
        if (onPlayDurationListener != null) {
            onPlayDurationListener.onProduceDuration(playDuration, duration);
        }
    }

    public static void savePlayPosition(String str, long j2) {
        playPositionMap.put(str, Long.valueOf(j2));
    }

    public boolean isSameToLast(String str) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(this.mArticleId) || !str.equals(this.mArticleId)) ? false : true;
    }

    @Override // com.jingdong.common.widget.custom.CustomIjkPlayer
    public void release() {
        reportPlayDuration();
        if (OKLog.D) {
            OKLog.d("AutoReportPlayer", "releasePlayer");
        }
        super.release();
    }

    @Override // com.jingdong.common.widget.custom.CustomIjkPlayer
    public void releaseInThread() {
        reportPlayDuration();
        if (OKLog.D) {
            OKLog.d("AutoReportPlayer", "releasePlayer inThread");
        }
        super.releaseInThread();
    }

    public void removePlayerParent() {
        ViewParent parent = getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(this);
        }
    }

    public void resetSessionId() {
        this.mReportVideoSpeedUtil.resetSessionId();
    }

    public void setOnPlayDurationListener(OnPlayDurationListener onPlayDurationListener) {
        this.mOnPlayDurationListener = onPlayDurationListener;
    }

    @Override // com.jingdong.common.widget.custom.CustomIjkPlayer
    public void setOnPlayerStateListener(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
        this.mOnPlayerStateListener = onPlayerStateListener;
    }

    public void setVideoPath(String str, String str2, String str3) {
        setVideoPath(str, str2, str3, 0L, 0L, true);
    }

    public void setVideoPathNoAutoPlay(String str, String str2, String str3) {
        setVideoPath(str, str2, str3, 0L, 0L, false);
    }

    @Override // com.jingdong.common.widget.custom.CustomIjkPlayer
    @Deprecated
    public void setVideoPathWithOutAutoPlay(String str) {
        super.setVideoPathWithOutAutoPlay(str);
    }

    @Override // com.jingdong.common.widget.custom.CustomIjkPlayer
    public void suspend() {
        reportPlayDuration();
        super.suspend();
    }

    public void setVideoPath(String str, String str2, String str3, long j2) {
        setVideoPath(str, str2, str3, j2, 0L, true);
    }

    public AutoReportPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public void setVideoPath(String str, String str2, String str3, long j2, long j3) {
        setVideoPath(str, str2, str3, j2, j3, true);
    }

    public void setVideoPath(String str, String str2, String str3, long j2, long j3, boolean z) {
        this.mArticleId = str2;
        reportPlayDuration();
        this.mVideoBuyReporterManager = new TotalReporterManager(str, str2, str3, j2);
        this.mReportVideoSpeedUtil.setReportParam(str2, str3, "1", str);
        resetSessionId();
        if (z) {
            super.setVideoPath(str, j3);
        } else {
            super.setVideoPathWithOutAutoPlay(str);
        }
    }

    public AutoReportPlayer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }

    @TargetApi(21)
    public AutoReportPlayer(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        init();
    }

    @Override // com.jingdong.common.widget.custom.CustomIjkPlayer
    @Deprecated
    public void setVideoPath(String str) {
        super.setVideoPath(str);
    }

    @Override // com.jingdong.common.widget.custom.CustomIjkPlayer
    @Deprecated
    public void setVideoPath(String str, long j2) {
        super.setVideoPath(str, j2);
    }
}
