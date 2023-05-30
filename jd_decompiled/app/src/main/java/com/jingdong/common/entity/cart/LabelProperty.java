package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.messagecenter.NotificationMessageSummary;

/* loaded from: classes5.dex */
public class LabelProperty implements Parcelable {
    public static final Parcelable.Creator<LabelProperty> CREATOR = new Parcelable.Creator<LabelProperty>() { // from class: com.jingdong.common.entity.cart.LabelProperty.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LabelProperty createFromParcel(Parcel parcel) {
            return new LabelProperty(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LabelProperty[] newArray(int i2) {
            return new LabelProperty[i2];
        }
    };

    /* renamed from: c  reason: collision with root package name */
    public String f12332c;
    public String d;
    public String dd;
    public int index;
    public int s;
    public int st;
    public String t;
    public String u;
    public String vt;

    public LabelProperty() {
    }

    public static LabelProperty parseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        LabelProperty labelProperty = new LabelProperty();
        labelProperty.s = jDJSONObject.optInt("s");
        labelProperty.u = jDJSONObject.optString("u");
        labelProperty.t = jDJSONObject.optString("t");
        labelProperty.f12332c = jDJSONObject.optString("c");
        labelProperty.d = jDJSONObject.optString("d");
        labelProperty.dd = jDJSONObject.optString(NotificationMessageSummary.DD_MSG);
        labelProperty.vt = jDJSONObject.optString("vt");
        labelProperty.st = jDJSONObject.optInt("st", -1);
        labelProperty.index = jDJSONObject.optInt("index", 0);
        return labelProperty;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.s);
        parcel.writeString(this.u);
        parcel.writeString(this.t);
        parcel.writeString(this.f12332c);
        parcel.writeString(this.d);
        parcel.writeString(this.dd);
        parcel.writeString(this.vt);
        parcel.writeInt(this.st);
        parcel.writeInt(this.index);
    }

    protected LabelProperty(Parcel parcel) {
        this.s = parcel.readInt();
        this.u = parcel.readString();
        this.t = parcel.readString();
        this.f12332c = parcel.readString();
        this.d = parcel.readString();
        this.dd = parcel.readString();
        this.vt = parcel.readString();
        this.st = parcel.readInt();
        this.index = parcel.readInt();
    }
}
