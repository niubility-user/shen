package tv.danmaku.ijk.media.ext.mta;

import android.content.Context;
import com.jingdong.common.unification.video.mta.INewVideoMtaInfo;
import com.jingdong.common.unification.video.mta.NewVideoPlayMtaUtil;
import com.jingdong.common.unification.video.mta.VideoPlayMtaUtil;
import tv.danmaku.ijk.media.ext.mta.bean.JDVideoPlayerMtaEntity;
import tv.danmaku.ijk.media.player.IJDVideoPlayer;
import tv.danmaku.ijk.media.utils.MediaInfoUtil;

/* loaded from: classes11.dex */
public class NewVideoMtaInfoImpl implements INewVideoMtaInfo {
    private int jumpFrom;
    public JDVideoPlayerMtaEntity mtaEntity = new JDVideoPlayerMtaEntity();
    private final NewVideoPlayMtaUtil.SpeedUtil speedUtil = new NewVideoPlayMtaUtil.SpeedUtil();
    private final IJDVideoPlayer videoView;

    public NewVideoMtaInfoImpl(IJDVideoPlayer iJDVideoPlayer) {
        this.videoView = iJDVideoPlayer;
        updateInitTime(System.currentTimeMillis(), -1L);
    }

    @Override // com.jingdong.common.unification.video.mta.INewVideoMtaInfo
    public long getAvgSpeed() {
        return this.speedUtil.getAvgSpeed();
    }

    @Override // com.jingdong.common.unification.video.mta.IVideoMtaInfo
    public int getCatonTotalTime() {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        if (jDVideoPlayerMtaEntity == null) {
            return -1;
        }
        return jDVideoPlayerMtaEntity.catonTotalTime;
    }

    @Override // com.jingdong.common.unification.video.mta.INewVideoMtaInfo
    public String getErrMsg() {
        return MediaInfoUtil.getErrorMessage(this.mtaEntity.errorCode);
    }

    @Override // com.jingdong.common.unification.video.mta.IVideoMtaInfo
    public int getErrorCode() {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        if (jDVideoPlayerMtaEntity == null) {
            return -1;
        }
        return jDVideoPlayerMtaEntity.errorCode;
    }

    @Override // com.jingdong.common.unification.video.mta.INewVideoMtaInfo
    public long getFirstPlayTime() {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        if (jDVideoPlayerMtaEntity == null) {
            return -1L;
        }
        return jDVideoPlayerMtaEntity.loadUrlTime + getInitTime();
    }

    @Override // com.jingdong.common.unification.video.mta.INewVideoMtaInfo
    public long getInitPlayerTime() {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        if (jDVideoPlayerMtaEntity == null) {
            return -1L;
        }
        return jDVideoPlayerMtaEntity.initPlayerTime;
    }

    @Override // com.jingdong.common.unification.video.mta.INewVideoMtaInfo
    public long getInitTime() {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        if (jDVideoPlayerMtaEntity == null) {
            return -1L;
        }
        long j2 = jDVideoPlayerMtaEntity.initEndTime - jDVideoPlayerMtaEntity.initStartTime;
        if (j2 <= 0) {
            return -1L;
        }
        return j2;
    }

    @Override // com.jingdong.common.unification.video.mta.IVideoMtaInfo
    public int getIsError() {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        if (jDVideoPlayerMtaEntity == null) {
            return -1;
        }
        return jDVideoPlayerMtaEntity.isError;
    }

    @Override // com.jingdong.common.unification.video.mta.IVideoMtaInfo
    public int getJumpFrom() {
        return this.jumpFrom;
    }

    @Override // com.jingdong.common.unification.video.mta.INewVideoMtaInfo
    public int getLostFramesCnt() {
        return 0;
    }

    @Override // com.jingdong.common.unification.video.mta.INewVideoMtaInfo
    public String getLostFramesInfo() {
        return null;
    }

    @Override // com.jingdong.common.unification.video.mta.IVideoMtaInfo
    public String getMark() {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        return jDVideoPlayerMtaEntity == null ? "" : jDVideoPlayerMtaEntity.mark;
    }

    @Override // com.jingdong.common.unification.video.mta.INewVideoMtaInfo
    public long getMaxSpeed() {
        return this.speedUtil.getMaxSpeed();
    }

    @Override // com.jingdong.common.unification.video.mta.INewVideoMtaInfo
    public long getMinSpeed() {
        return this.speedUtil.getMinSpeed();
    }

    @Override // com.jingdong.common.unification.video.mta.IVideoMtaInfo
    public int getPlayPosition() {
        return this.videoView.getCurrentPosition();
    }

    @Override // com.jingdong.common.unification.video.mta.IVideoMtaInfo
    public String getSku() {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        return jDVideoPlayerMtaEntity == null ? "" : jDVideoPlayerMtaEntity.sku;
    }

    @Override // com.jingdong.common.unification.video.mta.INewVideoMtaInfo
    public int getStuckCnt() {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        if (jDVideoPlayerMtaEntity == null) {
            return -1;
        }
        return jDVideoPlayerMtaEntity.catonCount;
    }

    @Override // com.jingdong.common.unification.video.mta.IVideoMtaInfo
    public int getVideoDuration() {
        return this.videoView.getDuration();
    }

    @Override // com.jingdong.common.unification.video.mta.IVideoMtaInfo
    public String getVideoId() {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        return jDVideoPlayerMtaEntity == null ? "" : jDVideoPlayerMtaEntity.videoId;
    }

    @Override // com.jingdong.common.unification.video.mta.INewVideoMtaInfo
    public int getVideoType() {
        return 1;
    }

    @Override // com.jingdong.common.unification.video.mta.IVideoMtaInfo
    public String getVideoUrl() {
        IJDVideoPlayer iJDVideoPlayer = this.videoView;
        return iJDVideoPlayer == null ? "" : iJDVideoPlayer.getOriginUrl();
    }

    @Override // com.jingdong.common.unification.video.mta.IVideoMtaInfo
    public int isCaton() {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        if (jDVideoPlayerMtaEntity == null) {
            return -1;
        }
        return jDVideoPlayerMtaEntity.isCaton;
    }

    public void onError(int i2, int i3) {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        if (jDVideoPlayerMtaEntity == null) {
            return;
        }
        jDVideoPlayerMtaEntity.isError = i2;
        jDVideoPlayerMtaEntity.errorCode = i3;
    }

    public void reset() {
        this.mtaEntity.resetMtaData();
    }

    public void setJumpFrom(int i2) {
        this.jumpFrom = i2;
    }

    public void setLoadUrlTime(long j2) {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        if (jDVideoPlayerMtaEntity != null) {
            jDVideoPlayerMtaEntity.loadUrlTime = j2;
        }
    }

    public void setMark(String str) {
        this.mtaEntity.mark = str;
    }

    public void setSku(String str) {
        this.mtaEntity.sku = str;
    }

    public void setVideoId(String str) {
        this.mtaEntity.videoId = str;
    }

    public void updateCaton() {
        long currentTimeMillis = System.currentTimeMillis();
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        long j2 = currentTimeMillis - jDVideoPlayerMtaEntity.loadingStartTime;
        if (j2 > 3000) {
            jDVideoPlayerMtaEntity.isCaton = 1;
            jDVideoPlayerMtaEntity.catonCount++;
            jDVideoPlayerMtaEntity.catonTotalTime = (int) (jDVideoPlayerMtaEntity.catonTotalTime + j2);
        }
    }

    public void updateInitTime(long j2, long j3) {
        if (j2 != -1) {
            this.mtaEntity.initPlayerTime = j2;
        }
        if (j3 != -1) {
            this.mtaEntity.initEndTime = j3;
        }
    }

    public void updateLoadStartTime() {
        this.mtaEntity.loadingStartTime = System.currentTimeMillis();
    }

    public void updateSpeed(long j2) {
        NewVideoPlayMtaUtil.SpeedUtil speedUtil = this.speedUtil;
        if (speedUtil == null) {
            return;
        }
        speedUtil.setSpeed(j2);
    }

    public void updateStartTime(long j2) {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        if (jDVideoPlayerMtaEntity.initStartTime == 0) {
            jDVideoPlayerMtaEntity.initStartTime = j2;
        }
    }

    public void upload(Context context) {
        JDVideoPlayerMtaEntity jDVideoPlayerMtaEntity = this.mtaEntity;
        if (jDVideoPlayerMtaEntity == null || jDVideoPlayerMtaEntity.isAlradyUploadMta) {
            return;
        }
        VideoPlayMtaUtil.upLoadMtaInfo(context, (INewVideoMtaInfo) this);
        this.mtaEntity.isAlradyUploadMta = true;
    }
}
