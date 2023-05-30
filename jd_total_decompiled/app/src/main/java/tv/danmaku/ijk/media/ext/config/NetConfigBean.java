package tv.danmaku.ijk.media.ext.config;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes11.dex */
public class NetConfigBean implements Serializable {
    private boolean httpDnsEnable;
    private DynamicLibInfoBean mcdnLibInfo;
    private List<String> quicBlacklist;
    private boolean quicEnable;

    public List<String> getQuicBlacklist() {
        return this.quicBlacklist;
    }

    public boolean isHttpDnsEnable() {
        return this.httpDnsEnable;
    }

    public boolean isMcdnEnable() {
        DynamicLibInfoBean dynamicLibInfoBean = this.mcdnLibInfo;
        return dynamicLibInfoBean != null && dynamicLibInfoBean.isEnable();
    }

    public boolean isQuicEnable() {
        return this.quicEnable;
    }

    public void setHttpDnsEnable(boolean z) {
        this.httpDnsEnable = z;
    }

    public void setMcdnLibInfo(DynamicLibInfoBean dynamicLibInfoBean) {
        this.mcdnLibInfo = dynamicLibInfoBean;
    }

    public void setQuicBlacklist(List<String> list) {
        this.quicBlacklist = list;
    }

    public void setQuicEnable(boolean z) {
        this.quicEnable = z;
    }
}
