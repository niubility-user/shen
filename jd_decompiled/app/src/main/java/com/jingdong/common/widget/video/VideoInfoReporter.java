package com.jingdong.common.widget.video;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.widget.video.VideoInfoEntity;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import tv.danmaku.ijk.media.example.widget.media.ListenDropFramesHelper;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: classes12.dex */
public class VideoInfoReporter {
    public static final String CH_ID = "3";
    static String TAG = "VideoInfoReporter";
    public static final String TYPE_ID = "4";
    private boolean isHasData;
    private ArrayList<VideoInfoEntity.LostFramesInfo> lostFramesInfoArrayList;
    private SpeedUtils mSpeedUtils;
    private VideoInfoEntity mVideoInfoEntity;
    private long requestUrlTime = 0;
    private boolean tcpIsConnected = false;
    private int ioErrRetCode = 0;
    private long bufferingStartTime = -1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class SpeedUtils {
        public long avgSpeed;
        public long maxSpeed;
        public long minSpeed;
        private int speedCounts;

        private SpeedUtils() {
            this.maxSpeed = -1L;
            this.minSpeed = -1L;
            this.avgSpeed = -1L;
            this.speedCounts = 0;
        }

        public void addVideoSpeed(long j2) {
            if (j2 <= 0) {
                return;
            }
            int i2 = this.speedCounts;
            if (i2 == 0) {
                this.maxSpeed = j2;
                this.minSpeed = j2;
                this.avgSpeed = j2;
            }
            long j3 = this.maxSpeed;
            if (j3 < j2) {
                j3 = j2;
            }
            this.maxSpeed = j3;
            long j4 = this.minSpeed;
            if (j4 > j2) {
                j4 = j2;
            }
            this.minSpeed = j4;
            int i3 = i2 + 1;
            this.speedCounts = i3;
            this.avgSpeed = ((this.avgSpeed * i2) + j2) / i3;
        }

        public boolean isHasSpeed() {
            return this.speedCounts > 0;
        }

        public void reset() {
            this.maxSpeed = -1L;
            this.minSpeed = -1L;
            this.avgSpeed = -1L;
            this.speedCounts = 0;
        }
    }

    public VideoInfoReporter(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4) {
        this.isHasData = false;
        this.mVideoInfoEntity = VideoInfoEntity.create(str, str2, str3, str4, "4", "3");
        resetSessionId();
        this.isHasData = false;
    }

    public void addVideoSpeed(long j2) {
        if (j2 <= 0) {
            return;
        }
        if (this.mSpeedUtils == null) {
            this.mSpeedUtils = new SpeedUtils();
        }
        this.mSpeedUtils.addVideoSpeed(j2);
    }

    public void onCompletion() {
        this.isHasData = true;
        this.mVideoInfoEntity.setStatus(0);
    }

    public void onCreatePlayer() {
        this.isHasData = true;
        this.tcpIsConnected = false;
        this.ioErrRetCode = 0;
        this.mVideoInfoEntity.setStatus(2);
    }

    public void onError(int i2, int i3) {
        this.isHasData = true;
        this.mVideoInfoEntity.setErrCode(i2);
        this.mVideoInfoEntity.setErrMsg((i2 != -2001 ? i2 != -1010 ? i2 != -1007 ? i2 != -1004 ? i2 != -110 ? i2 != 1 ? i2 != 100 ? i2 != 200 ? "" : "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK" : "MEDIA_ERROR_SERVER_DIED" : "MEDIA_ERROR_UNKNOWN" : "MEDIA_ERROR_TIMED_OUT" : "MEDIA_ERROR_IO" : "MEDIA_ERROR_MALFORMED" : "MEDIA_ERROR_UNSUPPORTED" : "MEDIA_ERROR_NET_BREAK") + "-TCP_CON:" + this.tcpIsConnected + "-IO_CODE:" + this.ioErrRetCode);
    }

    public void onInfo(int i2, int i3) {
        onInfo(null, i2, i3);
    }

    public void onPrepared(long j2) {
        this.isHasData = true;
        this.mVideoInfoEntity.setInitTime(j2);
        this.mVideoInfoEntity.setFirstPlayTime(j2 > 0 ? j2 + this.requestUrlTime : -1L);
        this.mVideoInfoEntity.setStatus(1);
    }

    public void report(long j2) {
        if (OKLog.D) {
            OKLog.d(TAG, "report isHasData:" + this.isHasData + " playDuration:" + (j2 - 1));
        }
        if (this.isHasData || j2 > 0) {
            VideoInfoEntity create = VideoInfoEntity.create(this.mVideoInfoEntity.getRoomNumber(), this.mVideoInfoEntity.getPlayType(), this.mVideoInfoEntity.getSource(), this.mVideoInfoEntity.getPageId(), this.mVideoInfoEntity.getSku(), this.mVideoInfoEntity.getArticleId(), this.mVideoInfoEntity.getReferPageId(), this.mVideoInfoEntity.getProjectId(), "4", "3");
            create.setStatus(this.mVideoInfoEntity.getStatus());
            create.setSessionId(this.mVideoInfoEntity.getSessionId());
            create.setVideoType(this.mVideoInfoEntity.getVideoType());
            create.setPageId(this.mVideoInfoEntity.getPageId());
            if (this.mVideoInfoEntity.getVideoDuration() > 0) {
                create.setVideoDuration(this.mVideoInfoEntity.getVideoDuration());
            }
            this.mVideoInfoEntity.setOccurTime(ExceptionReporter.formatMillis(System.currentTimeMillis()));
            if (j2 == 0) {
                if (this.mVideoInfoEntity.getStatus() != 2) {
                    return;
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "playDuration is  -1");
                }
                this.mVideoInfoEntity.setPlayDuration(-1L);
            } else {
                if (OKLog.D) {
                    OKLog.d(TAG, "playDuration is " + (j2 - 1));
                }
                this.mVideoInfoEntity.setPlayDuration(j2 - 1);
            }
            ArrayList<VideoInfoEntity.LostFramesInfo> arrayList = this.lostFramesInfoArrayList;
            if (arrayList != null && arrayList.size() > 0) {
                this.mVideoInfoEntity.setLostFramesCnt(this.lostFramesInfoArrayList.size());
                this.mVideoInfoEntity.setLostFramesInfo(JDJSON.toJSONString(this.lostFramesInfoArrayList));
            }
            SpeedUtils speedUtils = this.mSpeedUtils;
            if (speedUtils != null && speedUtils.isHasSpeed()) {
                this.mVideoInfoEntity.setMaxSpeed(this.mSpeedUtils.maxSpeed);
                this.mVideoInfoEntity.setMinSpeed(this.mSpeedUtils.minSpeed);
                this.mVideoInfoEntity.setAvgSpeed(this.mSpeedUtils.avgSpeed);
            }
            if (OKLog.D) {
                String jSONString = JDJSON.toJSONString(this.mVideoInfoEntity);
                OKLog.d(TAG, "reportStr:" + jSONString);
                OKLog.d(TAG, "map:" + this.mVideoInfoEntity.getParamsMap());
            }
            report(this.mVideoInfoEntity.getParamsMap());
            this.mVideoInfoEntity = create;
            this.isHasData = false;
        }
    }

    public void resetSessionId() {
        if (OKLog.D) {
            OKLog.d(TAG, "resetSessionId");
        }
        this.mVideoInfoEntity.setSessionId("" + ((long) (Math.random() * 1.0E10d)));
    }

    public void setRequestUrlTime(long j2) {
        this.requestUrlTime = j2;
    }

    public void setVideoLength(long j2) {
        this.mVideoInfoEntity.setVideoDuration(j2 >= 0 ? j2 / 1000 : -1L);
    }

    public void setVideoType(int i2) {
        this.mVideoInfoEntity.setVideoType(i2);
    }

    public void onInfo(IjkMediaPlayer ijkMediaPlayer, int i2, int i3) {
        this.isHasData = true;
        if (i2 == 701) {
            VideoInfoEntity videoInfoEntity = this.mVideoInfoEntity;
            videoInfoEntity.setStuckCnt(videoInfoEntity.getStuckCnt() + 1);
            this.bufferingStartTime = SystemClock.elapsedRealtime();
        } else if (i2 != 702) {
            switch (i2) {
                case 10303:
                    ListenDropFramesHelper.DropFramesEntity dropFramesDataFromPlayer = ListenDropFramesHelper.getDropFramesDataFromPlayer(ijkMediaPlayer);
                    if (dropFramesDataFromPlayer != null) {
                        VideoInfoEntity.LostFramesInfo lostFramesInfo = new VideoInfoEntity.LostFramesInfo();
                        lostFramesInfo.ad = dropFramesDataFromPlayer.audioDuration;
                        lostFramesInfo.vd = dropFramesDataFromPlayer.videoDuration;
                        lostFramesInfo.disad = dropFramesDataFromPlayer.audioDroppedDuration;
                        lostFramesInfo.disvd = dropFramesDataFromPlayer.videoDroppedDuration;
                        lostFramesInfo.ts = "" + (System.currentTimeMillis() / 1000);
                        lostFramesInfo.as = "" + (ijkMediaPlayer.getPropertyFloat(20200) / 1000.0f);
                        if (this.lostFramesInfoArrayList == null) {
                            this.lostFramesInfoArrayList = new ArrayList<>();
                        }
                        this.lostFramesInfoArrayList.add(lostFramesInfo);
                        return;
                    }
                    return;
                case 10304:
                    this.tcpIsConnected = true;
                    return;
                case 10305:
                    this.ioErrRetCode = i3;
                    return;
                default:
                    return;
            }
        } else if (this.bufferingStartTime != -1) {
            VideoInfoEntity videoInfoEntity2 = this.mVideoInfoEntity;
            videoInfoEntity2.setStuckTime(videoInfoEntity2.getStuckTime() + (SystemClock.elapsedRealtime() - this.bufferingStartTime));
            this.bufferingStartTime = -1L;
        }
    }

    public VideoInfoReporter(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8) {
        this.isHasData = false;
        this.mVideoInfoEntity = VideoInfoEntity.create(str, str2, str3, str4, str5, str6, str7, str8, "4", "3");
        resetSessionId();
        this.isHasData = false;
    }

    public VideoInfoReporter(@NonNull VideoInfoEntity videoInfoEntity) {
        this.isHasData = false;
        this.mVideoInfoEntity = videoInfoEntity;
        resetSessionId();
        this.isHasData = false;
    }

    private void report(HashMap<String, String> hashMap) {
        if (!PerformanceReporter.getIsNeedReport(JdSdk.getInstance().getApplicationContext(), "4", "3")) {
            if (OKLog.D) {
                OKLog.d(TAG, "not need report");
                return;
            }
            return;
        }
        PerformanceReporter.reportData(hashMap);
    }
}
