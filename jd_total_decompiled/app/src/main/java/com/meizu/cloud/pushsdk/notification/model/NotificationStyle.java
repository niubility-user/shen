package com.meizu.cloud.pushsdk.notification.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class NotificationStyle implements Parcelable {
    public static final String BANNER_IMAGE_URL = "bi";
    public static final String BASE_STYLE = "bs";
    public static final Parcelable.Creator<NotificationStyle> CREATOR = new a();
    public static final String EXPANDABLE_IMAGE_URL = "ei";
    public static final String EXPANDABLE_TEXT = "et";
    public static final String INNER_STYLE = "is";
    public static final String NOTIFICATION_STYLE = "ns";
    public static final String TAG = "notification_style";
    private String bannerImageUrl;
    private int baseStyle;
    private String expandableImageUrl;
    private String expandableText;
    private int innerStyle;

    /* loaded from: classes14.dex */
    class a implements Parcelable.Creator<NotificationStyle> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public NotificationStyle createFromParcel(Parcel parcel) {
            return new NotificationStyle(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public NotificationStyle[] newArray(int i2) {
            return new NotificationStyle[i2];
        }
    }

    public NotificationStyle() {
        this.baseStyle = 0;
        this.innerStyle = 0;
    }

    public NotificationStyle(Parcel parcel) {
        this.baseStyle = 0;
        this.innerStyle = 0;
        this.baseStyle = parcel.readInt();
        this.innerStyle = parcel.readInt();
        this.expandableText = parcel.readString();
        this.expandableImageUrl = parcel.readString();
        this.bannerImageUrl = parcel.readString();
    }

    public static NotificationStyle parse(String str) {
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

    public static NotificationStyle parse(JSONObject jSONObject) {
        String str;
        NotificationStyle notificationStyle = new NotificationStyle();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull("bs")) {
                    notificationStyle.setBaseStyle(jSONObject.getInt("bs"));
                }
                if (!jSONObject.isNull("is")) {
                    notificationStyle.setInnerStyle(jSONObject.getInt("is"));
                }
                if (!jSONObject.isNull("et")) {
                    notificationStyle.setExpandableText(jSONObject.getString("et"));
                }
                if (!jSONObject.isNull(EXPANDABLE_IMAGE_URL)) {
                    notificationStyle.setExpandableImageUrl(jSONObject.getString(EXPANDABLE_IMAGE_URL));
                }
                if (!jSONObject.isNull(BANNER_IMAGE_URL)) {
                    notificationStyle.setBannerImageUrl(jSONObject.getString(BANNER_IMAGE_URL));
                }
            } catch (JSONException e2) {
                str = "parse json obj error " + e2.getMessage();
            }
            return notificationStyle;
        }
        str = "no such tag notification_style";
        DebugLogger.e(TAG, str);
        return notificationStyle;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBannerImageUrl() {
        return this.bannerImageUrl;
    }

    public int getBaseStyle() {
        return this.baseStyle;
    }

    public String getExpandableImageUrl() {
        return this.expandableImageUrl;
    }

    public String getExpandableText() {
        return this.expandableText;
    }

    public int getInnerStyle() {
        return this.innerStyle;
    }

    public void setBannerImageUrl(String str) {
        this.bannerImageUrl = str;
    }

    public void setBaseStyle(int i2) {
        this.baseStyle = i2;
    }

    public void setExpandableImageUrl(String str) {
        this.expandableImageUrl = str;
    }

    public void setExpandableText(String str) {
        this.expandableText = str;
    }

    public void setInnerStyle(int i2) {
        this.innerStyle = i2;
    }

    public String toString() {
        return "NotificationStyle{baseStyle=" + this.baseStyle + ", innerStyle=" + this.innerStyle + ", expandableText='" + this.expandableText + "', expandableImageUrl='" + this.expandableImageUrl + "', bannerImageUrl='" + this.bannerImageUrl + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.baseStyle);
        parcel.writeInt(this.innerStyle);
        parcel.writeString(this.expandableText);
        parcel.writeString(this.expandableImageUrl);
        parcel.writeString(this.bannerImageUrl);
    }
}
