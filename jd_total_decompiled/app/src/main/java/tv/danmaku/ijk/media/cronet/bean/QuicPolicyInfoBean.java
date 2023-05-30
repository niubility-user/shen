package tv.danmaku.ijk.media.cronet.bean;

import java.io.Serializable;

/* loaded from: classes11.dex */
public class QuicPolicyInfoBean implements Serializable {
    private int code;
    private String quic_domain;
    private String quic_ip_blacklist;
    private String quic_service_port;
    private long sysTime;
    private int use_quic;

    public int getCode() {
        return this.code;
    }

    public String getQuic_domain() {
        return this.quic_domain;
    }

    public String getQuic_ip_blacklist() {
        return this.quic_ip_blacklist;
    }

    public String getQuic_service_port() {
        return this.quic_service_port;
    }

    public long getSysTime() {
        return this.sysTime;
    }

    public int getUse_quic() {
        return this.use_quic;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public void setQuic_domain(String str) {
        this.quic_domain = str;
    }

    public void setQuic_ip_blacklist(String str) {
        this.quic_ip_blacklist = str;
    }

    public void setQuic_service_port(String str) {
        this.quic_service_port = str;
    }

    public void setSysTime(long j2) {
        this.sysTime = j2;
    }

    public void setUse_quic(int i2) {
        this.use_quic = i2;
    }
}
