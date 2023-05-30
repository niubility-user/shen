package jd.wjlogin_sdk.model;

import android.text.TextUtils;
import com.unionpay.tsmservice.data.Constant;
import java.io.Serializable;
import java.util.Date;
import jd.wjlogin_sdk.util.i;
import jd.wjlogin_sdk.util.p;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WUserSigInfo implements Serializable {
    private static final long serialVersionUID = -2271219572399684330L;
    private String countryCode;
    private boolean isCurrentMainUser;
    private String Account = null;
    private String A2 = null;
    private String Pin = null;
    private int A2TimeOut = 0;
    private int A2RefreshTime = 0;
    private Date A2CreateDate = null;
    private String mHashedEmail = null;
    private String mHashedPin = null;

    private JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Account", this.Account);
            jSONObject.put("A2", this.A2);
            jSONObject.put("Pin", this.Pin);
            jSONObject.put("A2TimeOut", this.A2TimeOut);
            jSONObject.put("A2RefreshTime", this.A2RefreshTime);
            jSONObject.put(Constant.KEY_COUNTRY_CODE, this.countryCode);
            jSONObject.put("mHashedEmail", this.mHashedEmail);
            jSONObject.put("mHashedPin", this.mHashedPin);
            Date date = this.A2CreateDate;
            if (date != null) {
                jSONObject.put("A2CreateDate", i.b(date));
            }
            jSONObject.put("isCurrentMainUser", this.isCurrentMainUser);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void createUserInfoFromJSON(JSONObject jSONObject) {
        if (p.b) {
            p.b("WUserSignInfo", "createUserInfoFromJSON  ");
        }
        String optString = jSONObject.optString("Pin", "");
        String optString2 = jSONObject.optString("A2", "");
        String optString3 = jSONObject.optString("Account", "");
        int optInt = jSONObject.optInt("A2TimeOut");
        int optInt2 = jSONObject.optInt("A2RefreshTime");
        String optString4 = jSONObject.optString("A2CreateDate", "");
        String optString5 = jSONObject.optString(Constant.KEY_COUNTRY_CODE, "");
        String optString6 = jSONObject.optString("mHashedPin", "");
        String optString7 = jSONObject.optString("mHashedEmail", "");
        boolean optBoolean = jSONObject.optBoolean("isCurrentMainUser", false);
        this.A2 = optString2;
        this.Pin = optString;
        this.Account = optString3;
        this.A2TimeOut = optInt;
        this.A2RefreshTime = optInt2;
        this.countryCode = optString5;
        this.mHashedPin = optString6;
        this.mHashedEmail = optString7;
        if (!TextUtils.isEmpty(optString4)) {
            this.A2CreateDate = i.a(optString4);
        }
        this.isCurrentMainUser = optBoolean;
    }

    public WUserSigInfo empty() {
        this.A2 = null;
        this.Pin = null;
        this.A2TimeOut = 0;
        this.A2RefreshTime = 0;
        this.A2CreateDate = null;
        this.mHashedEmail = null;
        this.mHashedPin = null;
        return this;
    }

    public String getA2() {
        return this.A2;
    }

    public Date getA2CreateDate() {
        return this.A2CreateDate;
    }

    public int getA2RefreshTime() {
        return this.A2RefreshTime;
    }

    public int getA2TimeOut() {
        return this.A2TimeOut;
    }

    public String getAccount() {
        return this.Account;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getHashedEmail() {
        return this.mHashedEmail;
    }

    public String getHashedPin() {
        return this.mHashedPin;
    }

    public String getPin() {
        return this.Pin;
    }

    public boolean isCurrentMainUser() {
        return this.isCurrentMainUser;
    }

    public void setA2(String str) {
        this.A2 = str;
    }

    public void setA2CreateDate(Date date) {
        this.A2CreateDate = date;
    }

    public void setA2RefreshTime(int i2) {
        this.A2RefreshTime = i2;
    }

    public void setA2TimeOut(int i2) {
        this.A2TimeOut = i2;
    }

    public void setAccount(String str) {
        this.Account = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setCurrentMainUser(boolean z) {
        this.isCurrentMainUser = z;
    }

    public void setHashedEmail(String str) {
        this.mHashedEmail = str;
    }

    public void setHashedPin(String str) {
        this.mHashedPin = str;
    }

    public void setPin(String str) {
        this.Pin = str;
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
