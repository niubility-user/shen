package com.jingdong.common.widget.video;

import android.net.TrafficStats;
import android.os.Handler;
import android.os.Message;
import com.jd.framework.json.JDJSON;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@Deprecated
/* loaded from: classes12.dex */
public class ReportVideoSpeedUtil {
    private static final int MSG_TYPE_NOTE = 1;
    private static final int MSG_TYPE_REPORT = 2;
    private static final int SPACE_NOTE = 2000;
    private static final int SPACE_REPORT = 10100;
    protected static final String TAG = "ReportSpeedUtil";
    private VideoSpeedDetector mVideoSpeedDetector;
    private long initTime = -1;
    private long sessionId = 0;
    private long startTime = -1;
    private ArrayList<SpeedEntity> mSpeedList = new ArrayList<>();
    private ReportSpeedEntity mReportSpeedEntity = new ReportSpeedEntity();
    private TrafficSpeedDetector sysDetector = new TrafficSpeedDetector();
    private Handler mHandler = new MyHandler(this);

    /* loaded from: classes12.dex */
    private static class MyHandler extends Handler {
        private WeakReference<ReportVideoSpeedUtil> reference;

        public MyHandler(ReportVideoSpeedUtil reportVideoSpeedUtil) {
            this.reference = new WeakReference<>(reportVideoSpeedUtil);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ReportVideoSpeedUtil reportVideoSpeedUtil;
            if (message == null || (reportVideoSpeedUtil = this.reference.get()) == null) {
                return;
            }
            int i2 = message.what;
            if (i2 == 1) {
                reportVideoSpeedUtil.noteSpeed();
                reportVideoSpeedUtil.mHandler.sendEmptyMessageDelayed(1, 2000L);
            } else if (i2 != 2) {
            } else {
                reportVideoSpeedUtil.reportSpeed();
                reportVideoSpeedUtil.resetStartTime();
                reportVideoSpeedUtil.mHandler.sendEmptyMessageDelayed(2, 10100L);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class SpeedEntity {
        public String as;
        public String ms;
        public String ts;
    }

    /* loaded from: classes12.dex */
    public static class TrafficSpeedDetector {
        private long lastRxBytes;
        private long lastRxSpeedTime;
        private long lastTxBytes;
        private long lastTxSpeedTime;

        public long getRxSpeedBPS() {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                long totalRxBytes = TrafficStats.getTotalRxBytes();
                long j2 = totalRxBytes - this.lastRxBytes;
                long j3 = currentTimeMillis - this.lastRxSpeedTime;
                if (j3 <= 0) {
                    return 0L;
                }
                this.lastRxBytes = totalRxBytes;
                this.lastRxSpeedTime = currentTimeMillis;
                return (j2 * 1000) / j3;
            } catch (Exception e2) {
                e2.printStackTrace();
                return 0L;
            }
        }

        public long getTxSpeedBPS() {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                long totalTxBytes = TrafficStats.getTotalTxBytes();
                long j2 = totalTxBytes - this.lastTxBytes;
                long j3 = currentTimeMillis - this.lastTxSpeedTime;
                if (j3 <= 0) {
                    return 0L;
                }
                this.lastTxBytes = totalTxBytes;
                this.lastTxSpeedTime = currentTimeMillis;
                return (j2 * 1000) / j3;
            } catch (Exception e2) {
                e2.printStackTrace();
                return 0L;
            }
        }

        public void start() {
            this.lastRxBytes = TrafficStats.getTotalRxBytes();
            this.lastTxBytes = TrafficStats.getTotalTxBytes();
            long currentTimeMillis = System.currentTimeMillis();
            this.lastRxSpeedTime = currentTimeMillis;
            this.lastTxSpeedTime = currentTimeMillis;
        }
    }

    /* loaded from: classes12.dex */
    public interface VideoSpeedDetector {
        long getVideoSpeed();
    }

    public ReportVideoSpeedUtil() {
        resetSessionId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void noteSpeed() {
        if (OKLog.D) {
            OKLog.d(TAG, "noteSpeed");
        }
        long videoSpeed = this.mVideoSpeedDetector.getVideoSpeed();
        if (videoSpeed < 0) {
            return;
        }
        SpeedEntity speedEntity = new SpeedEntity();
        speedEntity.ts = "" + (System.currentTimeMillis() / 1000);
        speedEntity.as = "" + (videoSpeed / 1000);
        speedEntity.ms = "" + (this.sysDetector.getRxSpeedBPS() / 1000);
        this.mSpeedList.add(speedEntity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportSpeed() {
        if (this.startTime == -1) {
            if (OKLog.D) {
                OKLog.d(TAG, "reportSpeed : not start");
                return;
            }
            return;
        }
        if (this.mSpeedList.isEmpty() && OKLog.D) {
            OKLog.d(TAG, "reportSpeed : speedList isEmpty");
        }
        ReportSpeedEntity reportSpeedEntity = this.mReportSpeedEntity;
        reportSpeedEntity.performInfo = this.mSpeedList;
        reportSpeedEntity.initTime = "" + this.initTime;
        this.initTime = -1L;
        this.mReportSpeedEntity.sessionId = "" + this.sessionId;
        long currentTimeMillis = System.currentTimeMillis() - this.startTime;
        this.mReportSpeedEntity.playTimes = ExceptionReporter.formatMillis(System.currentTimeMillis() - this.startTime);
        this.startTime = -1L;
        if (OKLog.D) {
            OKLog.d(TAG, "reportSpeed --> sessionId:" + this.sessionId + " playTimes :" + currentTimeMillis);
            StringBuilder sb = new StringBuilder();
            sb.append("reportSpeed : ");
            sb.append(JDJSON.toJSONString(this.mReportSpeedEntity));
            OKLog.d(TAG, sb.toString());
        }
        this.mSpeedList.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetStartTime() {
        this.startTime = System.currentTimeMillis();
        if (OKLog.D) {
            OKLog.d(TAG, "resetStartTime --> startTime: " + this.startTime);
        }
    }

    public void addLoadCostTime(long j2) {
        if (OKLog.D) {
            OKLog.d(TAG, "addLoadCostTime --> initTime:" + j2);
        }
        if (j2 > 0) {
            this.initTime = j2;
        }
    }

    public long getRxSpeedBPS() {
        return this.sysDetector.getRxSpeedBPS();
    }

    public long getVideoSpeed() {
        VideoSpeedDetector videoSpeedDetector = this.mVideoSpeedDetector;
        if (videoSpeedDetector != null) {
            return videoSpeedDetector.getVideoSpeed();
        }
        return 0L;
    }

    public void resetSessionId() {
        this.sessionId = (long) (Math.random() * 1.0E9d);
        if (OKLog.D) {
            OKLog.d(TAG, "resetSessionId --> sessionId:" + this.sessionId);
        }
    }

    public void setPlayType(String str) {
        this.mReportSpeedEntity.playType = str;
    }

    public void setReportParam(String str, String str2, String str3, String str4) {
        this.mReportSpeedEntity = new ReportSpeedEntity(str, str2, str3, str4);
    }

    public void setRoomNumber(String str) {
        this.mReportSpeedEntity.roomNumber = str;
    }

    public void setSource(String str) {
        this.mReportSpeedEntity.source = str;
    }

    public void setVideoSpeedDetector(VideoSpeedDetector videoSpeedDetector) {
        this.mVideoSpeedDetector = videoSpeedDetector;
    }

    public void setVideoType(String str) {
        this.mReportSpeedEntity.videoType = str;
    }

    public void start() {
        if (OKLog.D) {
            OKLog.d(TAG, "start");
        }
        resetStartTime();
        this.sysDetector.start();
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        this.mHandler.sendEmptyMessageDelayed(1, 2000L);
        this.mHandler.sendEmptyMessageDelayed(2, 10100L);
    }

    public void stop() {
        if (OKLog.D) {
            OKLog.d(TAG, "stop");
        }
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        reportSpeed();
    }

    /* loaded from: classes12.dex */
    public static class ReportSpeedEntity implements Serializable {
        public String initTime;
        public ArrayList<SpeedEntity> performInfo;
        public String playTimes;
        public String playType;
        public String roomNumber;
        public String sdkType = "jdtv";
        public String sessionId;
        public String source;
        public String videoType;

        public ReportSpeedEntity() {
        }

        public ReportSpeedEntity(String str, String str2, String str3, String str4) {
            this.roomNumber = str;
            this.playType = str2;
            this.videoType = str3;
            this.source = str4;
        }
    }
}
