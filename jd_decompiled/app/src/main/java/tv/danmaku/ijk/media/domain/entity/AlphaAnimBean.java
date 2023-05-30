package tv.danmaku.ijk.media.domain.entity;

import android.text.TextUtils;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.Serializable;
import java.sql.Timestamp;

/* loaded from: classes11.dex */
public class AlphaAnimBean implements Serializable {
    private String animName;
    private String downloadUrl;
    private String expireTime;
    private int id;
    private boolean isPreload;
    private String localCachePath;
    private String md5;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return b.a(this.animName, ((AlphaAnimBean) obj).animName);
    }

    public String getAnimName() {
        if (TextUtils.isEmpty(this.md5)) {
            this.animName = "temp_video.mp4";
            return "temp_video.mp4";
        }
        if (TextUtils.isEmpty(this.expireTime)) {
            this.animName = this.md5 + PreDownLoadManager.VIDEO_SKU_SUFFIX;
        } else {
            this.animName = this.md5 + CartConstant.KEY_YB_INFO_LINK + this.expireTime + PreDownLoadManager.VIDEO_SKU_SUFFIX;
        }
        return this.animName;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public String getExpireTime() {
        return this.expireTime;
    }

    public int getId() {
        return this.id;
    }

    public String getLocalCachePath() {
        return this.localCachePath;
    }

    public String getMd5() {
        return this.md5;
    }

    public int hashCode() {
        String str = this.animName;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public boolean isExpired() {
        if (TextUtils.isEmpty(this.expireTime)) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.expireTime);
        sb.append(" 23:59:59");
        return Timestamp.valueOf(sb.toString()).getTime() < System.currentTimeMillis();
    }

    public boolean isPreload() {
        return this.isPreload;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public void setExpireTime(String str) {
        this.expireTime = str;
    }

    public void setId(int i2) {
        this.id = i2;
    }

    public void setLocalCachePath(String str) {
        this.localCachePath = str;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setPreload(boolean z) {
        this.isPreload = z;
    }
}
