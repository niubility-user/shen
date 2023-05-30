package tv.danmaku.ijk.media.ext.mta.bean;

/* loaded from: classes11.dex */
public class JDVideoPlayerMtaEntity {
    public int catonCount;
    public int catonTotalTime;
    public int errorCode;
    public long initEndTime;
    public long initPlayerTime;
    public long initStartTime;
    public int isCaton;
    public int isError;
    public long loadUrlTime;
    public long loadingStartTime;
    public String mark;
    public String sku = "";
    public String videoId = "";
    public boolean isAlradyUploadMta = false;

    public void resetMtaData() {
        this.isCaton = 0;
        this.catonTotalTime = 0;
        this.loadingStartTime = 0L;
        this.isError = 0;
        this.errorCode = 0;
        this.mark = null;
        this.loadUrlTime = 0L;
        this.initStartTime = 0L;
        this.initEndTime = 0L;
        this.initPlayerTime = System.currentTimeMillis();
        this.isAlradyUploadMta = false;
    }
}
