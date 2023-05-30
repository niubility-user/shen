package tv.danmaku.ijk.media.ext.mta;

import android.content.Context;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.video.mta.NewVideoPlayMtaUtil;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.alpha.IAlphaListener;
import tv.danmaku.ijk.media.alpha.IAlphaVideoView;
import tv.danmaku.ijk.media.alpha.player.AlphaPlayer;
import tv.danmaku.ijk.media.ext.mta.bean.AlphaReportInfoEntity;

/* loaded from: classes11.dex */
public class AlphaPlayerMtaReport {
    private static final String TAG = "AlphaPlayerMtaReport";
    private final IAlphaVideoView.AlphaOptions alphaOptions;
    private Context context;
    private boolean enableReport;
    private boolean hasCompletedReport;
    private long mDuration;
    private int mPlayCompleted;
    private long mPlayEndTime;
    private long mPlayStartTime;
    private String mPlayUrl;
    private AlphaPlayer mPlayer;
    private JDJSONObject mReportJson = new JDJSONObject();
    private AlphaReportInfoEntity playerReportInfoEntity;

    /* loaded from: classes11.dex */
    public interface Action {
        public static final int PLAY_COMPLETED = 1;
        public static final int PLAY_DESTROY = 2;
        public static final int PLAY_ERROR = 0;
    }

    public AlphaPlayerMtaReport(Context context, AlphaPlayer alphaPlayer, IAlphaVideoView.AlphaOptions alphaOptions, String str) {
        this.context = context;
        this.mPlayer = alphaPlayer;
        this.alphaOptions = alphaOptions;
        this.mPlayUrl = str;
        if (alphaOptions != null) {
            this.playerReportInfoEntity = alphaOptions.getAlphaReportInfoEntity();
        }
        buildCommonInfo();
        registerPlayerListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(int i2) {
        String str = "eventId = " + i2;
        if (i2 == 0) {
            this.mPlayCompleted = 0;
            this.mPlayEndTime = 0L;
            this.hasCompletedReport = false;
            this.mPlayStartTime = System.currentTimeMillis();
        } else if (i2 == 1) {
            this.mPlayEndTime = 0L;
            this.mPlayStartTime = System.currentTimeMillis();
            this.hasCompletedReport = false;
        } else if (i2 == 2) {
            this.mPlayCompleted = 1;
            doReport(1);
            this.mPlayCompleted = 0;
            this.hasCompletedReport = true;
        } else if (i2 != 3) {
            doReport(0);
            this.hasCompletedReport = true;
        } else if (this.hasCompletedReport) {
        } else {
            doReport(2);
        }
    }

    private void buildCommonInfo() {
        if (this.mPlayer == null || this.alphaOptions == null) {
            return;
        }
        JDJSONObject jDJSONObject = this.mReportJson;
        if (jDJSONObject == null) {
            this.mReportJson = new JDJSONObject();
        } else {
            jDJSONObject.clear();
        }
        this.mReportJson.put("appId", (Object) JDPlayerSdk.mAppId);
        this.mReportJson.put("playtypeId", (Object) this.alphaOptions.getPlayTypeId());
        AlphaReportInfoEntity alphaReportInfoEntity = this.playerReportInfoEntity;
        if (alphaReportInfoEntity != null) {
            this.mReportJson.put("content_id", (Object) alphaReportInfoEntity.getContentId());
            this.mReportJson.put("businessId", (Object) this.playerReportInfoEntity.getBusinessId());
            this.mReportJson.put("extString", (Object) this.playerReportInfoEntity.getExtString());
            this.mReportJson.put("url", (Object) this.mPlayUrl);
        }
    }

    private void doReport(int i2) {
        if (this.context == null || this.mPlayer == null || !this.enableReport) {
            return;
        }
        this.mPlayEndTime = System.currentTimeMillis();
        buildCommonInfo();
        this.mReportJson.put("status", (Object) String.valueOf(this.mPlayCompleted));
        this.mReportJson.put("startTime", (Object) Long.valueOf(this.mPlayStartTime));
        this.mReportJson.put("endTime", (Object) Long.valueOf(this.mPlayEndTime));
        this.mReportJson.put("playduration", (Object) String.valueOf(this.mPlayEndTime - this.mPlayStartTime));
        this.mReportJson.put("playAction", (Object) Integer.valueOf(i2));
        NewVideoPlayMtaUtil.reportMtaClickEvent(this.context, this.mReportJson);
        this.mPlayStartTime = System.currentTimeMillis();
    }

    private void registerPlayerListener() {
        if (this.mPlayer == null || this.alphaOptions == null) {
            return;
        }
        this.mPlayStartTime = System.currentTimeMillis();
        this.hasCompletedReport = false;
        this.mPlayer.setEventListener(new IAlphaListener.OnEventListener() { // from class: tv.danmaku.ijk.media.ext.mta.b
            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener.OnEventListener
            public final void onEvent(int i2) {
                AlphaPlayerMtaReport.this.b(i2);
            }
        });
    }

    public void enableReport(boolean z) {
        this.enableReport = z;
    }
}
