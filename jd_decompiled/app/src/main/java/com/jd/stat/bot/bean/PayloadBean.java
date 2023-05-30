package com.jd.stat.bot.bean;

import android.text.TextUtils;
import android.util.Base64;
import com.jd.stat.common.b.b;
import com.jd.stat.common.f;
import com.jd.stat.security.c;

/* loaded from: classes18.dex */
public class PayloadBean {
    public String encrypt_id;
    public String fbp;
    public String nonstr;
    public String nowtime;
    public long outtime;
    public long timestamp;

    public PayloadBean() {
        this.nonstr = "";
        this.encrypt_id = "x,z,*,1";
        this.outtime = 10800000L;
        this.nowtime = "0";
        this.fbp = "";
    }

    public PayloadBean(String str, String str2) {
        this.nonstr = "";
        this.encrypt_id = "x,z,*,1";
        this.outtime = 10800000L;
        this.nowtime = "0";
        this.fbp = "";
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                byte[] decode = Base64.decode(str, 0);
                byte[] bytes = str2.getBytes();
                byte[] bArr = new byte[decode.length];
                for (int i2 = 0; i2 < decode.length; i2++) {
                    bArr[i2] = (byte) (decode[i2] ^ bytes[i2 % bytes.length]);
                }
                String str3 = new String(bArr);
                b.a("bot", "payload after xor=" + str3);
                String[] split = str3.split("~");
                if (split != null && split.length >= 5) {
                    this.timestamp = Long.parseLong(split[0]);
                    b.a("bot", "timestamp=" + this.timestamp);
                    this.nonstr = split[1];
                    b.a("bot", "nonstr=" + this.nonstr);
                    this.encrypt_id = split[2];
                    b.a("bot", "encrypt_id=" + this.encrypt_id);
                    this.outtime = new Double(Double.parseDouble(split[3]) * 60.0d * 60.0d * 1000.0d).longValue();
                    b.a("bot", "outtime=" + this.outtime);
                    this.nowtime = split[4];
                    b.a("bot", "nowtime=" + this.nowtime);
                }
                this.fbp = f.a(c.a);
                b.a("bot", "fbp=" + this.fbp);
            }
        } catch (Exception e2) {
            b.a("bot", "PayloadBean error: " + e2.getMessage());
        }
    }
}
