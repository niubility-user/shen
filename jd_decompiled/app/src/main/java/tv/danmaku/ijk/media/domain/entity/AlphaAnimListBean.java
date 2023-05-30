package tv.danmaku.ijk.media.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class AlphaAnimListBean implements Serializable {
    private List<AlphaAnimBean> result = new ArrayList();
    private String rtnCode;
    private String rtnMessage;
    private long timestamp;

    public List<AlphaAnimBean> getResult() {
        return this.result;
    }

    public String getRtnCode() {
        return this.rtnCode;
    }

    public String getRtnMessage() {
        return this.rtnMessage;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setResult(List<AlphaAnimBean> list) {
        this.result = list;
    }

    public void setRtnCode(String str) {
        this.rtnCode = str;
    }

    public void setRtnMessage(String str) {
        this.rtnMessage = str;
    }

    public void setTimestamp(long j2) {
        this.timestamp = j2;
    }
}
