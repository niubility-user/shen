package com.jingdong.common.widget.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.example.widget.media.PlayDurationStatistics;

/* loaded from: classes12.dex */
public class IjkVideoViewWithReport extends IjkVideoView {
    public static final int MEDIA_ERROR_NET_BREAK = -2001;
    private static final int MSG_SPEED_ADD = 1;
    private BackForegroundWatcher.BackForegroundListener mBackForegroundListener;
    private PlayDurationStatistics.PlayDurationStatisticsListener mPlayDurationStatisticsListener;
    private IPlayerControl.OnPlayerStateListener mPlayerListener;
    private String mRoomNumber;
    private String mUrl;
    private VideoInfoReporter mVideoInfoReporter;
    private IPlayerControl.OnVideoSizeChangedListener mVideoSizeChangedListener;
    private MyHandler myHandler;
    private int playTime;
    private static HashMap<String, Long> playPositionMap = new HashMap<>();
    private static boolean isSavePosition = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class MyBackForegroundListener implements BackForegroundWatcher.BackForegroundListener {
        private WeakReference<IjkVideoViewWithReport> videoWeakReference;

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onBackToForeground() {
            if (OKLog.D) {
                OKLog.d(VideoInfoReporter.TAG, "onBackToForeground");
            }
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onForeToBackground() {
            if (OKLog.D) {
                OKLog.d(VideoInfoReporter.TAG, "onForeToBackground");
            }
            WeakReference<IjkVideoViewWithReport> weakReference = this.videoWeakReference;
            IjkVideoViewWithReport ijkVideoViewWithReport = weakReference != null ? weakReference.get() : null;
            if (ijkVideoViewWithReport != null) {
                ijkVideoViewWithReport.report();
            } else {
                BackForegroundWatcher.getInstance().unRegisterListener(this);
            }
        }

        private MyBackForegroundListener(IjkVideoViewWithReport ijkVideoViewWithReport) {
            this.videoWeakReference = new WeakReference<>(ijkVideoViewWithReport);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class MyHandler extends Handler {
        private WeakReference<IjkVideoViewWithReport> videoWeakReference;

        public MyHandler(IjkVideoViewWithReport ijkVideoViewWithReport) {
            this.videoWeakReference = new WeakReference<>(ijkVideoViewWithReport);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            IjkVideoViewWithReport ijkVideoViewWithReport;
            if (message.what == 1 && (ijkVideoViewWithReport = this.videoWeakReference.get()) != null) {
                ijkVideoViewWithReport.myHandler.sendEmptyMessageDelayed(1, 2000L);
                ijkVideoViewWithReport.addVideoSpeed(ijkVideoViewWithReport.getTcpSpeed() / 1000);
            }
        }
    }

    public IjkVideoViewWithReport(Context context) {
        super(context);
        this.playTime = 0;
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addVideoSpeed(long j2) {
        VideoInfoReporter videoInfoReporter = this.mVideoInfoReporter;
        if (videoInfoReporter != null) {
            videoInfoReporter.addVideoSpeed(j2);
        }
    }

    public static void clearPlayPosition() {
        playPositionMap.clear();
    }

    public static long getPlayPosition(String str) {
        Long l2;
        isSavePosition = true;
        if (TextUtils.isEmpty(str) || (l2 = playPositionMap.get(str)) == null || l2.longValue() <= 0) {
            return 0L;
        }
        playPositionMap.remove(str);
        return l2.longValue();
    }

    private void init() {
        super.setOnVideoSizeChangedListener(new IPlayerControl.OnVideoSizeChangedListener() { // from class: com.jingdong.common.widget.video.IjkVideoViewWithReport.1
            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnVideoSizeChangedListener
            public void onVideoSizeChanged(int i2, int i3) {
                if (IjkVideoViewWithReport.this.mVideoSizeChangedListener != null) {
                    IjkVideoViewWithReport.this.mVideoSizeChangedListener.onVideoSizeChanged(i2, i3);
                }
            }
        });
        super.setOnPlayerStateListener(new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.widget.video.IjkVideoViewWithReport.2
            /* JADX WARN: Removed duplicated region for block: B:13:0x0030  */
            /* JADX WARN: Removed duplicated region for block: B:18:0x004f  */
            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onCompletion() {
                /*
                    r9 = this;
                    com.jingdong.common.widget.video.IjkVideoViewWithReport r0 = com.jingdong.common.widget.video.IjkVideoViewWithReport.this
                    int r0 = r0.getDuration()
                    com.jingdong.common.widget.video.IjkVideoViewWithReport r1 = com.jingdong.common.widget.video.IjkVideoViewWithReport.this
                    int r1 = r1.getCurrentPosition()
                    r2 = 0
                    if (r0 <= 0) goto L27
                    if (r1 <= 0) goto L27
                    double r3 = (double) r1
                    double r5 = (double) r0
                    r7 = 4606732058837280358(0x3fee666666666666, double:0.95)
                    java.lang.Double.isNaN(r5)
                    double r5 = r5 * r7
                    int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                    if (r7 >= 0) goto L27
                    int r1 = r1 + 3000
                    if (r1 >= r0) goto L27
                    r0 = 1
                    goto L28
                L27:
                    r0 = 0
                L28:
                    com.jingdong.common.widget.video.IjkVideoViewWithReport r1 = com.jingdong.common.widget.video.IjkVideoViewWithReport.this
                    com.jingdong.common.widget.video.VideoInfoReporter r1 = com.jingdong.common.widget.video.IjkVideoViewWithReport.access$200(r1)
                    if (r1 == 0) goto L47
                    if (r0 == 0) goto L3e
                    com.jingdong.common.widget.video.IjkVideoViewWithReport r0 = com.jingdong.common.widget.video.IjkVideoViewWithReport.this
                    com.jingdong.common.widget.video.VideoInfoReporter r0 = com.jingdong.common.widget.video.IjkVideoViewWithReport.access$200(r0)
                    r1 = -2001(0xfffffffffffff82f, float:NaN)
                    r0.onError(r1, r2)
                    goto L47
                L3e:
                    com.jingdong.common.widget.video.IjkVideoViewWithReport r0 = com.jingdong.common.widget.video.IjkVideoViewWithReport.this
                    com.jingdong.common.widget.video.VideoInfoReporter r0 = com.jingdong.common.widget.video.IjkVideoViewWithReport.access$200(r0)
                    r0.onCompletion()
                L47:
                    com.jingdong.common.widget.video.IjkVideoViewWithReport r0 = com.jingdong.common.widget.video.IjkVideoViewWithReport.this
                    tv.danmaku.ijk.media.example.widget.media.IPlayerControl$OnPlayerStateListener r0 = com.jingdong.common.widget.video.IjkVideoViewWithReport.access$100(r0)
                    if (r0 == 0) goto L58
                    com.jingdong.common.widget.video.IjkVideoViewWithReport r0 = com.jingdong.common.widget.video.IjkVideoViewWithReport.this
                    tv.danmaku.ijk.media.example.widget.media.IPlayerControl$OnPlayerStateListener r0 = com.jingdong.common.widget.video.IjkVideoViewWithReport.access$100(r0)
                    r0.onCompletion()
                L58:
                    com.jingdong.common.widget.video.IjkVideoViewWithReport r0 = com.jingdong.common.widget.video.IjkVideoViewWithReport.this
                    r0.report()
                    com.jingdong.common.widget.video.IjkVideoViewWithReport r0 = com.jingdong.common.widget.video.IjkVideoViewWithReport.this
                    r0.resetSessionId()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.widget.video.IjkVideoViewWithReport.AnonymousClass2.onCompletion():void");
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
                if (IjkVideoViewWithReport.this.mPlayerListener != null) {
                    IjkVideoViewWithReport.this.mPlayerListener.onCreatePlayer();
                }
                if (IjkVideoViewWithReport.this.mVideoInfoReporter != null) {
                    IjkVideoViewWithReport.this.mVideoInfoReporter.onCreatePlayer();
                }
                BackForegroundWatcher.getInstance().registerListener(IjkVideoViewWithReport.this.mBackForegroundListener);
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                if (IjkVideoViewWithReport.this.mVideoInfoReporter != null) {
                    IjkVideoViewWithReport.this.mVideoInfoReporter.onError(i2, i3);
                }
                boolean onError = IjkVideoViewWithReport.this.mPlayerListener != null ? IjkVideoViewWithReport.this.mPlayerListener.onError(i2, i3) : false;
                IjkVideoViewWithReport.this.report();
                IjkVideoViewWithReport.this.resetSessionId();
                return onError;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                boolean onInfo = IjkVideoViewWithReport.this.mPlayerListener != null ? IjkVideoViewWithReport.this.mPlayerListener.onInfo(i2, i3) : false;
                if (IjkVideoViewWithReport.this.mVideoInfoReporter != null) {
                    IjkVideoViewWithReport.this.mVideoInfoReporter.onInfo(IjkVideoViewWithReport.this.getIjkMediaPlayer(), i2, i3);
                }
                if (i2 == 3 && IjkVideoViewWithReport.this.mVideoInfoReporter != null) {
                    IPlayerControl.PlayerOptions playerOptions = IjkVideoViewWithReport.this.getPlayerOptions();
                    if (playerOptions == null || !playerOptions.getIsLive()) {
                        IjkVideoViewWithReport.this.mVideoInfoReporter.setVideoLength(IjkVideoViewWithReport.this.getDuration());
                    } else {
                        IjkVideoViewWithReport.this.mVideoInfoReporter.setVideoLength(-1L);
                    }
                }
                return onInfo;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                if (IjkVideoViewWithReport.this.mPlayerListener != null) {
                    IjkVideoViewWithReport.this.mPlayerListener.onPrepared(j2);
                }
                if (IjkVideoViewWithReport.this.mVideoInfoReporter != null) {
                    IjkVideoViewWithReport.this.mVideoInfoReporter.onPrepared(j2);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
                if (IjkVideoViewWithReport.this.mPlayerListener != null) {
                    IjkVideoViewWithReport.this.mPlayerListener.onSeekComplete();
                }
            }
        });
        PlayDurationStatistics.PlayDurationStatisticsListener playDurationStatisticsListener = new PlayDurationStatistics.PlayDurationStatisticsListener() { // from class: com.jingdong.common.widget.video.IjkVideoViewWithReport.3
            @Override // tv.danmaku.ijk.media.example.widget.media.PlayDurationStatistics.PlayDurationStatisticsListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnStatisticsStateListener
            public void pause() {
                super.pause();
                if (IjkVideoViewWithReport.this.myHandler != null) {
                    IjkVideoViewWithReport.this.myHandler.removeMessages(1);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.PlayDurationStatistics.PlayDurationStatisticsListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnStatisticsStateListener
            public void start() {
                super.start();
                if (IjkVideoViewWithReport.this.myHandler != null) {
                    IjkVideoViewWithReport.this.myHandler.sendEmptyMessageDelayed(1, 1L);
                }
            }
        };
        this.mPlayDurationStatisticsListener = playDurationStatisticsListener;
        super.addOnStatisticsStateListener(playDurationStatisticsListener);
        this.mBackForegroundListener = new MyBackForegroundListener();
    }

    public static void savePlayPosition(String str, long j2) {
        if (playPositionMap.size() > 200) {
            playPositionMap.clear();
        }
        playPositionMap.put(str, Long.valueOf(j2));
    }

    private void savePosition() {
        if (isSavePosition) {
            isSavePosition = false;
            long currentPosition = getCurrentPosition();
            if (1000 + currentPosition < getDuration()) {
                if (!TextUtils.isEmpty(this.mUrl)) {
                    savePlayPosition(this.mUrl, currentPosition);
                }
                if (TextUtils.isEmpty(this.mRoomNumber)) {
                    return;
                }
                savePlayPosition(this.mRoomNumber, currentPosition);
            }
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IjkVideoView, tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public void pause() {
        super.pause();
        report();
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IjkVideoView, tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void release() {
        super.release();
        report();
        resetSessionId();
        savePosition();
        BackForegroundWatcher.getInstance().unRegisterListener(this.mBackForegroundListener);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IjkVideoView, tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void releaseInThread(boolean z) {
        super.releaseInThread(z);
        report();
        resetSessionId();
        savePosition();
        BackForegroundWatcher.getInstance().unRegisterListener(this.mBackForegroundListener);
    }

    public void report() {
        VideoInfoReporter videoInfoReporter = this.mVideoInfoReporter;
        if (videoInfoReporter != null) {
            videoInfoReporter.report(this.playTime);
            this.playTime = 0;
        }
    }

    public void resetSessionId() {
        VideoInfoReporter videoInfoReporter = this.mVideoInfoReporter;
        if (videoInfoReporter != null) {
            videoInfoReporter.resetSessionId();
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IjkVideoView, tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void resume() {
        super.resume();
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IjkVideoView, tv.danmaku.ijk.media.player.IJDVideoPlayer, tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setOnPlayerStateListener(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
        this.mPlayerListener = onPlayerStateListener;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IjkVideoView, tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void setOnVideoSizeChangedListener(IPlayerControl.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.mVideoSizeChangedListener = onVideoSizeChangedListener;
    }

    public void setRealPlayTime(int i2) {
        this.playTime = i2;
    }

    public void setReportParams(VideoInfoEntity videoInfoEntity) {
        if (videoInfoEntity != null) {
            setBusinessId(videoInfoEntity.getPlayType());
        }
        report();
        this.mVideoInfoReporter = new VideoInfoReporter(videoInfoEntity);
        this.myHandler = new MyHandler(this);
    }

    public void setRequestUrlTime(long j2) {
        VideoInfoReporter videoInfoReporter = this.mVideoInfoReporter;
        if (videoInfoReporter != null) {
            videoInfoReporter.setRequestUrlTime(j2);
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IjkVideoView, tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setVideoPath(String str) {
        report();
        super.setVideoPath(str);
        this.mUrl = str;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IjkVideoView
    public void setVideoPathWithoutOpen(String str) {
        super.setVideoPathWithoutOpen(str);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IjkVideoView, tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setVideoURI(Uri uri) {
        report();
        super.setVideoURI(uri);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IjkVideoView, tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public void start() {
        super.start();
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IjkVideoView, tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void suspend() {
        super.suspend();
        report();
        savePosition();
    }

    public IjkVideoViewWithReport(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.playTime = 0;
        init();
    }

    public void setReportParams(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8) {
        setBusinessId(str2);
        report();
        this.mVideoInfoReporter = new VideoInfoReporter(str, str2, str3, str4, str5, str6, str7, str8);
        this.myHandler = new MyHandler(this);
    }

    public IjkVideoViewWithReport(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.playTime = 0;
        init();
    }

    public void setReportParams(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4) {
        setReportParams(str, str2, str3, str4, null, null, null, null);
    }

    @TargetApi(21)
    public IjkVideoViewWithReport(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.playTime = 0;
        init();
    }

    public void setReportParams(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        setReportParams(str, str2, str3, null);
        this.mRoomNumber = str;
    }
}
