package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes9.dex */
public class MediaObject implements Parcelable, Serializable {
    public static final Parcelable.Creator<MediaObject> CREATOR = new a();
    private static final long serialVersionUID = -5562495969515901397L;
    public String actionUrl;
    public String description;
    public String identify;
    public String schema;
    public byte[] thumbData;
    public String title;

    /* loaded from: classes9.dex */
    static class a implements Parcelable.Creator<MediaObject> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final MediaObject createFromParcel(Parcel parcel) {
            return new MediaObject(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public final MediaObject[] newArray(int i2) {
            return new MediaObject[i2];
        }
    }

    public MediaObject() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.actionUrl);
        parcel.writeString(this.schema);
        parcel.writeString(this.identify);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeByteArray(this.thumbData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MediaObject(Parcel parcel) {
        this.actionUrl = parcel.readString();
        this.schema = parcel.readString();
        this.identify = parcel.readString();
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.thumbData = parcel.createByteArray();
    }
}
