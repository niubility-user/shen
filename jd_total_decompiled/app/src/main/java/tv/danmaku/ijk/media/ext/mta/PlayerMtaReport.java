package tv.danmaku.ijk.media.ext.mta;

import android.content.Context;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.video.mta.NewVideoPlayMtaUtil;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.example.BuildConfig;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;
import tv.danmaku.ijk.media.ext.report.ReportConstant;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes11.dex */
public class PlayerMtaReport {
    private static final int ACTION_COMPLETED = 2;
    private static final int ACTION_DESTROY = 0;
    private static final int ACTION_ERROR = 1;
    private static final int ACTION_PAUSE = 3;
    private boolean enableReport;
    private boolean hasReport;
    private final Context mContext;
    private final IMediaPlayer mMediaPlayer;
    private int mPlayCompleted;
    private long mPlayEndTime;
    private long mPlayStartTime;
    private final String mPlayUrl;
    private final IPlayerControl.PlayerOptions mPlayerOptions;
    private JDJSONObject mReportJson = new JDJSONObject();
    private PlayerReportInfoEntity playerReportInfoEntity;

    public PlayerMtaReport(Context context, IMediaPlayer iMediaPlayer, IPlayerControl.PlayerOptions playerOptions, String str) {
        this.mContext = context;
        this.mMediaPlayer = iMediaPlayer;
        this.mPlayerOptions = playerOptions;
        this.mPlayUrl = str;
        if (playerOptions != null) {
            this.playerReportInfoEntity = playerOptions.getPlayerReportInfoEntity();
        }
        buildCommonInfo();
        registerPlayerListener();
    }

    private void buildCommonInfo() {
        if (this.mMediaPlayer == null || this.mPlayerOptions == null) {
            return;
        }
        JDJSONObject jDJSONObject = this.mReportJson;
        if (jDJSONObject == null) {
            this.mReportJson = new JDJSONObject();
        } else {
            jDJSONObject.clear();
        }
        this.mReportJson.put("appId", (Object) JDPlayerSdk.mAppId);
        this.mReportJson.put("playtypeId", (Object) this.mPlayerOptions.getPlayTypeId());
        this.mReportJson.put(ReportConstant.CommonInfo.PLAYER_VERSION, (Object) BuildConfig.SDK_VERSION);
        this.mReportJson.put(ReportConstant.CommonInfo.PLAY_MODE, this.mPlayerOptions.getIsLive() ? "1" : "0");
        this.mReportJson.put("sessionId", (Object) String.valueOf(this.mMediaPlayer.hashCode()));
        this.mReportJson.put(ReportConstant.CommonInfo.PLAYER_TYPE, (Object) (this.mMediaPlayer instanceof AndroidMediaPlayer ? "1" : "0"));
        PlayerReportInfoEntity playerReportInfoEntity = this.playerReportInfoEntity;
        if (playerReportInfoEntity != null) {
            this.mReportJson.put("content_id", (Object) playerReportInfoEntity.getContentId());
            this.mReportJson.put("businessId", (Object) this.playerReportInfoEntity.getBusinessId());
            this.mReportJson.put("extString", (Object) this.playerReportInfoEntity.getExtString());
            this.mReportJson.put("url", (Object) this.mPlayUrl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doReport(int i2) {
        if (this.mContext == null || this.mMediaPlayer == null || !this.enableReport || this.hasReport) {
            return;
        }
        this.mPlayEndTime = System.currentTimeMillis();
        buildCommonInfo();
        this.mReportJson.put("status", (Object) String.valueOf(this.mPlayCompleted));
        long duration = this.mMediaPlayer.getDuration();
        this.mReportJson.put("videoduration", (Object) String.valueOf(duration));
        this.mReportJson.put("startTime", (Object) Long.valueOf(this.mPlayStartTime));
        this.mReportJson.put("endTime", (Object) Long.valueOf(this.mPlayEndTime));
        long j2 = this.mPlayEndTime - this.mPlayStartTime;
        if (duration == 0) {
            j2 = 0;
        }
        if (j2 <= duration) {
            duration = j2;
        }
        this.mReportJson.put("playduration", (Object) String.valueOf(duration));
        this.mReportJson.put("playerAction", (Object) Integer.valueOf(i2));
        NewVideoPlayMtaUtil.reportMtaClickEvent(this.mContext, this.mReportJson);
        this.mPlayStartTime = System.currentTimeMillis();
        this.mPlayEndTime = 0L;
        this.hasReport = true;
    }

    private void registerPlayerListener() {
        if (this.mMediaPlayer == null) {
            return;
        }
        this.mPlayStartTime = System.currentTimeMillis();
        this.hasReport = false;
        this.mMediaPlayer.setOnPlayerEventListener(new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.ext.mta.PlayerMtaReport.1
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            public void onPlayEvent(int i2) {
                if (i2 == 1) {
                    PlayerMtaReport.this.mPlayStartTime = System.currentTimeMillis();
                    PlayerMtaReport.this.hasReport = false;
                } else if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 == 4) {
                            PlayerMtaReport.this.doReport(1);
                            return;
                        } else if (i2 == 9) {
                            PlayerMtaReport.this.mPlayCompleted = 0;
                            PlayerMtaReport.this.hasReport = false;
                            PlayerMtaReport.this.mPlayStartTime = System.currentTimeMillis();
                            return;
                        } else if (i2 != 12) {
                            if (i2 != 14) {
                                return;
                            }
                            PlayerMtaReport.this.doReport(0);
                            return;
                        }
                    }
                    PlayerMtaReport.this.mPlayCompleted = 1;
                    PlayerMtaReport.this.doReport(2);
                    PlayerMtaReport.this.hasReport = false;
                    PlayerMtaReport.this.mPlayStartTime = System.currentTimeMillis();
                    PlayerMtaReport.this.mPlayCompleted = 0;
                } else {
                    PlayerMtaReport.this.doReport(3);
                }
            }
        });
    }

    public void enableReport(boolean z) {
        this.enableReport = z;
    }
}
