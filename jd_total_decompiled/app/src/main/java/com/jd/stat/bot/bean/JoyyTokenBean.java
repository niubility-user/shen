package com.jd.stat.bot.bean;

import android.text.TextUtils;
import android.util.Base64;
import com.jd.stat.bot.BlogUtil;
import com.jd.stat.common.b.b;
import com.jingdong.jdsdk.a.a;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JoyyTokenBean {
    public String collectStatus;
    public String collect_rate;
    public String collect_vote;
    public HeaderBean header;
    public String joyytoken;
    public String openMonitor;
    public String openPre;
    public PayloadBean payload;
    public long q;
    public String xcd;
    public String xcd_pg;

    public JoyyTokenBean() {
        this.joyytoken = "";
        this.openMonitor = "1";
        this.openPre = "1";
        this.collectStatus = "1";
        this.collect_vote = "100";
        this.collect_rate = Constant.TRANS_TYPE_LOAD;
        this.xcd = "";
        this.xcd_pg = a.a;
        this.header = new HeaderBean();
        this.payload = new PayloadBean();
    }

    private String getXCD_PG(String str) {
        if (TextUtils.isEmpty(this.xcd) || TextUtils.isEmpty(str)) {
            return a.a;
        }
        byte[] decode = Base64.decode(this.xcd, 0);
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[decode.length];
        for (int i2 = 0; i2 < decode.length; i2++) {
            bArr[i2] = (byte) (decode[i2] ^ bytes[i2 % bytes.length]);
        }
        return new String(bArr);
    }

    public boolean isExpire() {
        PayloadBean payloadBean = this.payload;
        if (payloadBean == null || payloadBean.timestamp == 0) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        PayloadBean payloadBean2 = this.payload;
        return currentTimeMillis - payloadBean2.timestamp > payloadBean2.outtime;
    }

    public JoyyTokenBean(JSONObject jSONObject) {
        this.joyytoken = "";
        this.openMonitor = "1";
        this.openPre = "1";
        this.collectStatus = "1";
        this.collect_vote = "100";
        this.collect_rate = Constant.TRANS_TYPE_LOAD;
        this.xcd = "";
        this.xcd_pg = a.a;
        try {
            this.header = new HeaderBean();
            this.payload = new PayloadBean();
            this.joyytoken = jSONObject.optString("joyytoken", "");
            this.openMonitor = jSONObject.optString("openMonitor", "1");
            this.openPre = jSONObject.optString("openPre", "1");
            this.collectStatus = jSONObject.optString("collectStatus", "1");
            this.collect_vote = jSONObject.optString("collect_vote", "100");
            this.collect_rate = jSONObject.optString("collect_rate", Constant.TRANS_TYPE_LOAD);
            this.xcd = jSONObject.optString("xcd", "");
            b.a("bot", "joyytoken=" + this.joyytoken);
            if (!TextUtils.isEmpty(this.joyytoken)) {
                String[] split = this.joyytoken.split("\\.");
                if (split != null && split.length >= 3) {
                    b.a("bot", "header=" + split[0] + "\npayload=" + split[1] + "\nend=" + split[2]);
                    HeaderBean headerBean = new HeaderBean(split[0]);
                    this.header = headerBean;
                    if (headerBean == null) {
                        this.header = new HeaderBean();
                    }
                    PayloadBean payloadBean = new PayloadBean(split[1], this.header.chihuahua);
                    this.payload = payloadBean;
                    if (payloadBean == null) {
                        this.payload = new PayloadBean();
                    }
                    this.xcd_pg = getXCD_PG(this.header.chihuahua);
                    b.a("bot", "xcd_pg=" + this.xcd_pg);
                    this.q = this.payload.timestamp - System.currentTimeMillis();
                    b.a("bot", "q=" + this.q);
                    return;
                }
                BlogUtil.reportFailEvent("\u89e3\u5bc6\u5f02\u5e38", "joyytoken\u683c\u5f0f\u4e0d\u6b63\u786e");
                return;
            }
            BlogUtil.reportFailEvent("\u89e3\u5bc6\u5f02\u5e38", "joyytoken\u4e3a\u7a7a");
        } catch (Exception e2) {
            b.a("bot", "JoyyTokenBean error=" + e2.getMessage());
            BlogUtil.reportFailEvent("\u89e3\u5bc6\u5f02\u5e38", e2.getMessage());
        }
    }
}
