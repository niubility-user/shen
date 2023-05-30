package jd.wjweblogin.model;

import android.text.TextUtils;
import com.jdpay.bury.SessionPack;
import java.io.Serializable;
import java.util.Date;
import jd.wjweblogin.d.g;
import jd.wjweblogin.d.l;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class WJWebLoginSigInfo implements Serializable {
    private static final long serialVersionUID = -2271219572399684330L;
    private int tCookie;
    private String ptKey = null;
    private String ptPin = null;
    private String eptPin = null;
    private int ptKeyTimeOut = 0;
    private int ptKeyRefreshTime = 0;
    private Date ptKeyCreateDate = null;
    private String mid = null;
    private Date setCookieDate = null;

    private JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SessionPack.KEY_PT_KEY, this.ptKey);
            jSONObject.put("ptPin", this.ptPin);
            jSONObject.put("eptPin", this.eptPin);
            jSONObject.put("ptKeyTimeOut", this.ptKeyTimeOut);
            jSONObject.put("ptKeyRefreshTime", this.ptKeyRefreshTime);
            jSONObject.put("tCookie", this.tCookie);
            Date date = this.ptKeyCreateDate;
            if (date != null) {
                jSONObject.put("ptKeyCreateDate", l.b(date));
            }
            Date date2 = this.setCookieDate;
            if (date2 != null) {
                jSONObject.put("setCookieDate", l.b(date2));
            }
            jSONObject.put("mid", this.mid);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void createUserInfoFromJSON(JSONObject jSONObject) {
        if (g.b) {
            g.b("WUserSignInfo", "createUserInfoFromJSON  ");
        }
        String optString = jSONObject.optString("ptPin", "");
        String optString2 = jSONObject.optString("eptPin", "");
        String optString3 = jSONObject.optString(SessionPack.KEY_PT_KEY, "");
        int optInt = jSONObject.optInt("ptKeyTimeOut");
        int optInt2 = jSONObject.optInt("ptKeyRefreshTime");
        int optInt3 = jSONObject.optInt("tCookie");
        String optString4 = jSONObject.optString("ptKeyCreateDate", "");
        String optString5 = jSONObject.optString("setCookieDate", "");
        String optString6 = jSONObject.optString("mid", "");
        this.ptKey = optString3;
        this.ptPin = optString;
        this.eptPin = optString2;
        this.ptKeyTimeOut = optInt;
        this.ptKeyRefreshTime = optInt2;
        this.tCookie = optInt3;
        if (!TextUtils.isEmpty(optString4)) {
            this.ptKeyCreateDate = l.a(optString4);
        }
        if (!TextUtils.isEmpty(optString5)) {
            this.setCookieDate = l.a(optString5);
        }
        this.mid = optString6;
    }

    public WJWebLoginSigInfo empty() {
        this.ptKey = null;
        this.ptPin = null;
        this.eptPin = null;
        this.ptKeyTimeOut = 0;
        this.ptKeyRefreshTime = 0;
        this.tCookie = 0;
        this.ptKeyCreateDate = null;
        this.setCookieDate = null;
        this.mid = null;
        return this;
    }

    public String getEptPin() {
        return this.eptPin;
    }

    public String getMid() {
        return this.mid;
    }

    public String getPtKey() {
        return this.ptKey;
    }

    public Date getPtKeyCreateDate() {
        return this.ptKeyCreateDate;
    }

    public int getPtKeyRefreshTime() {
        return this.ptKeyRefreshTime;
    }

    public int getPtKeyTimeOut() {
        return this.ptKeyTimeOut;
    }

    public String getPtPin() {
        return this.ptPin;
    }

    public Date getSetCookieDate() {
        return this.setCookieDate;
    }

    public int gettCookie() {
        return this.tCookie;
    }

    public void setEptPin(String str) {
        this.eptPin = str;
    }

    public void setMid(String str) {
        this.mid = str;
    }

    public void setPtKey(String str) {
        this.ptKey = str;
    }

    public void setPtKeyCreateDate(Date date) {
        this.ptKeyCreateDate = date;
    }

    public void setPtKeyRefreshTime(int i2) {
        this.ptKeyRefreshTime = i2;
    }

    public void setPtKeyTimeOut(int i2) {
        this.ptKeyTimeOut = i2;
    }

    public void setPtPin(String str) {
        this.ptPin = str;
    }

    public void setSetCookieDate(Date date) {
        this.setCookieDate = date;
    }

    public void settCookie(int i2) {
        this.tCookie = i2;
    }

    public String toJSONString() {
        try {
            return toJSONObject().toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
