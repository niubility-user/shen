package tv.danmaku.ijk.media.ext.mta.bean;

import java.io.Serializable;

/* loaded from: classes11.dex */
public class PlayerReportInfoEntity implements Serializable {
    public static final String EVENT_ID = "player_busiData";
    public static final String PAGE_ID = "player";
    private final String businessId;
    private final String contentId;
    private final String extString;

    public PlayerReportInfoEntity(String str, String str2) {
        this(str, str2, "");
    }

    public String getBusinessId() {
        return this.businessId;
    }

    public String getContentId() {
        return this.contentId;
    }

    public String getExtString() {
        return this.extString;
    }

    public PlayerReportInfoEntity(String str, String str2, String str3) {
        this.contentId = str;
        this.businessId = str2;
        this.extString = str3;
    }
}
