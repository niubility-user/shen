package com.meizu.cloud.pushsdk.notification.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class BrightRemindSetting implements Parcelable {
    public static final String BRIGHT_REMIND = "br";
    public static final String BRIGHT_REMIND_SETTING = "bs";
    public static final Parcelable.Creator<BrightRemindSetting> CREATOR = new a();
    public static final String TAG = "BrightRemindSetting";
    private boolean isBrightRemind;

    /* loaded from: classes14.dex */
    class a implements Parcelable.Creator<BrightRemindSetting> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public BrightRemindSetting createFromParcel(Parcel parcel) {
            return new BrightRemindSetting(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public BrightRemindSetting[] newArray(int i2) {
            return new BrightRemindSetting[i2];
        }
    }

    public BrightRemindSetting() {
    }

    protected BrightRemindSetting(Parcel parcel) {
        this.isBrightRemind = parcel.readByte() == 1;
    }

    public static BrightRemindSetting parse(String str) {
        JSONObject jSONObject;
        if (!TextUtils.isEmpty(str)) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e2) {
                DebugLogger.e(TAG, "parse json string error " + e2.getMessage());
            }
            return parse(jSONObject);
        }
        jSONObject = null;
        return parse(jSONObject);
    }

    public static BrightRemindSetting parse(JSONObject jSONObject) {
        String str;
        BrightRemindSetting brightRemindSetting = new BrightRemindSetting();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(BRIGHT_REMIND)) {
                    brightRemindSetting.setIsBrightRemind(jSONObject.getInt(BRIGHT_REMIND) != 0);
                }
            } catch (JSONException e2) {
                str = "parse json obj error " + e2.getMessage();
            }
            return brightRemindSetting;
        }
        str = "no such tag BrightRemindSetting";
        DebugLogger.e(TAG, str);
        return brightRemindSetting;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean getIsBrightRemind() {
        return this.isBrightRemind;
    }

    public void setIsBrightRemind(boolean z) {
        this.isBrightRemind = z;
    }

    public String toString() {
        return "BrightRemindSetting{isBrightRemind=" + this.isBrightRemind + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeByte(this.isBrightRemind ? (byte) 1 : (byte) 0);
    }
}
