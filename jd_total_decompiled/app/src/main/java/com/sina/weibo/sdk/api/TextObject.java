package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class TextObject extends MediaObject {
    public static final Parcelable.Creator<TextObject> CREATOR = new a();
    private static final long serialVersionUID = -5610314414793811821L;
    public String text;

    /* loaded from: classes9.dex */
    static class a implements Parcelable.Creator<TextObject> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final TextObject createFromParcel(Parcel parcel) {
            return new TextObject(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public final TextObject[] newArray(int i2) {
            return new TextObject[i2];
        }
    }

    public TextObject() {
    }

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.text);
    }

    protected TextObject(Parcel parcel) {
        this.text = parcel.readString();
    }
}
