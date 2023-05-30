package tv.danmaku.ijk.media.ext.mta;

import android.content.Context;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.video.mta.NewVideoPlayMtaUtil;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.alpha.IAlphaListener;
import tv.danmaku.ijk.media.alpha.download.DownloadOptions;

/* loaded from: classes11.dex */
public class AlphaDownloadMtaReport {
    private static final String TAG = "AlphaDownloadMtaReport";
    private Context context;
    private long downloadEndTime;
    private DownloadOptions downloadOptions;
    private long downloadStartTime;
    private IAlphaListener.OnEventListener eventListener;
    private boolean hasCompletedReport;
    private int mDownloadCompleted;
    private JDJSONObject reportJson = new JDJSONObject();
    private String url;

    /* loaded from: classes11.dex */
    private interface Action {
        public static final int DOWNLOAD_COMPLETE = 11;
        public static final int DOWNLOAD_ERROR = 10;
    }

    public AlphaDownloadMtaReport(Context context, DownloadOptions downloadOptions) {
        this.context = context;
        this.downloadOptions = downloadOptions;
        this.url = downloadOptions.getUrl();
        buildCommonInfo();
        initEventListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(int i2) {
        String str = "eventId = " + i2;
        switch (i2) {
            case 10:
                this.downloadEndTime = 0L;
                this.downloadStartTime = System.currentTimeMillis();
                this.hasCompletedReport = false;
                return;
            case 11:
            case 12:
                this.mDownloadCompleted = 1;
                doReport(11);
                this.mDownloadCompleted = 0;
                this.hasCompletedReport = true;
                return;
            case 13:
                doReport(10);
                this.hasCompletedReport = true;
                return;
            default:
                return;
        }
    }

    private void buildCommonInfo() {
        if (this.downloadOptions == null) {
            return;
        }
        JDJSONObject jDJSONObject = this.reportJson;
        if (jDJSONObject == null) {
            this.reportJson = new JDJSONObject();
        } else {
            jDJSONObject.clear();
        }
        this.reportJson.put("appId", (Object) JDPlayerSdk.mAppId);
        this.reportJson.put("playTypeId", (Object) this.downloadOptions.getPlayTypeId());
        this.reportJson.put("url", (Object) this.url);
    }

    private void doReport(int i2) {
        if (this.context != null) {
            this.downloadEndTime = System.currentTimeMillis();
            buildCommonInfo();
            this.reportJson.put("status", (Object) String.valueOf(this.mDownloadCompleted));
            this.reportJson.put("startTime", (Object) Long.valueOf(this.downloadStartTime));
            this.reportJson.put("endTime", (Object) Long.valueOf(this.downloadEndTime));
            long j2 = this.downloadStartTime;
            this.downloadEndTime = j2;
            this.reportJson.put("downloadduration", (Object) String.valueOf(j2));
            this.reportJson.put("downloadAction", (Object) Integer.valueOf(i2));
            NewVideoPlayMtaUtil.reportMtaClickEvent(this.context, this.reportJson);
            this.downloadStartTime = System.currentTimeMillis();
        }
    }

    private void initEventListener() {
        this.eventListener = new IAlphaListener.OnEventListener() { // from class: tv.danmaku.ijk.media.ext.mta.a
            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener.OnEventListener
            public final void onEvent(int i2) {
                AlphaDownloadMtaReport.this.b(i2);
            }
        };
    }

    public IAlphaListener.OnEventListener getEventListener() {
        this.downloadStartTime = System.currentTimeMillis();
        this.hasCompletedReport = false;
        return this.eventListener;
    }
}
