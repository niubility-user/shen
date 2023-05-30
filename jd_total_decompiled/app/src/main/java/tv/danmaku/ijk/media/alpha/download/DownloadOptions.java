package tv.danmaku.ijk.media.alpha.download;

/* loaded from: classes11.dex */
public class DownloadOptions {
    private boolean enableMta = false;
    private String playTypeId = "alphaPlayer";
    private String url;

    public DownloadOptions(String str) {
        this.url = str;
    }

    public DownloadOptions enableMta(boolean z) {
        this.enableMta = z;
        return this;
    }

    public String getPlayTypeId() {
        return this.playTypeId;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isEnableMta() {
        return this.enableMta;
    }

    public DownloadOptions setPlayTypeId(String str) {
        this.playTypeId = str;
        return this;
    }
}
