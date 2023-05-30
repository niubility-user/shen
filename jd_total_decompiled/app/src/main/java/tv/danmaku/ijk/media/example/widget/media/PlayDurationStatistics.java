package tv.danmaku.ijk.media.example.widget.media;

import android.os.SystemClock;
import java.util.ArrayList;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public class PlayDurationStatistics {
    private ArrayList<IPlayerControl.OnStatisticsStateListener> listenerList = new ArrayList<>();

    /* loaded from: classes11.dex */
    public static class PlayDurationStatisticsListener implements IPlayerControl.OnStatisticsStateListener {
        private long startTime = -1;
        private long allTime = 0;

        public long getPlayDuration() {
            if (this.startTime > 0) {
                this.allTime += SystemClock.elapsedRealtime() - this.startTime;
                this.startTime = SystemClock.elapsedRealtime();
            }
            long j2 = this.allTime;
            this.allTime = 0L;
            return j2;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnStatisticsStateListener
        public void pause() {
            if (this.startTime <= 0) {
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.startTime;
            this.allTime += elapsedRealtime;
            DebugLog.d(IjkVideoView.TAG, "addPlayDuration allTime " + this.allTime + " addTime " + elapsedRealtime);
            this.startTime = -1L;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnStatisticsStateListener
        public void start() {
            this.startTime = SystemClock.elapsedRealtime();
        }
    }

    private void addPlayDuration() {
        noticePause();
    }

    private void noticePause() {
        for (int i2 = 0; i2 < this.listenerList.size(); i2++) {
            this.listenerList.get(i2).pause();
        }
    }

    private void noticeStart() {
        for (int i2 = 0; i2 < this.listenerList.size(); i2++) {
            this.listenerList.get(i2).start();
        }
    }

    public void addOnStatisticsStateListener(IPlayerControl.OnStatisticsStateListener onStatisticsStateListener) {
        if (this.listenerList.contains(onStatisticsStateListener)) {
            return;
        }
        this.listenerList.add(onStatisticsStateListener);
    }

    public void complete() {
        DebugLog.d(IjkVideoView.TAG, "PlayDurationStatistics complete");
        addPlayDuration();
    }

    public void error() {
        DebugLog.d(IjkVideoView.TAG, "PlayDurationStatistics error");
        addPlayDuration();
    }

    public void pause() {
        DebugLog.d(IjkVideoView.TAG, "PlayDurationStatistics pause");
        addPlayDuration();
    }

    public void release() {
        DebugLog.d(IjkVideoView.TAG, "PlayDurationStatistics release");
        addPlayDuration();
    }

    public void start() {
        DebugLog.d(IjkVideoView.TAG, "PlayDurationStatistics start");
        noticeStart();
    }
}
