package com.jd.stat.bot.bean;

import android.text.TextUtils;
import android.util.Base64;
import com.jd.stat.common.b.b;

/* loaded from: classes18.dex */
public class HeaderBean {
    public String alg;
    public String chihuahua;
    public int platform;
    public String version;

    public HeaderBean() {
        this.alg = "00";
        this.chihuahua = "";
        this.version = "00";
        this.platform = 2;
    }

    public HeaderBean(String str) {
        this.alg = "00";
        this.chihuahua = "";
        this.version = "00";
        this.platform = 2;
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String str2 = new String(Base64.decode(str, 0));
            b.a("bot", "header base64 decode=" + str2);
            this.alg = str2.substring(0, 2);
            b.a("bot", "alg=" + this.alg);
            this.chihuahua = str2.substring(2, 7);
            b.a("bot", "chihuahua=" + this.chihuahua);
            this.version = str2.substring(7, 9);
            b.a("bot", "version=" + this.version);
            b.a("bot", "platform=" + this.platform);
        } catch (Exception e2) {
            b.a("bot", "HeaderBean error: " + e2.getMessage());
        }
    }
}
