package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class WebpageObject extends MediaObject {
    public static final Parcelable.Creator<WebpageObject> CREATOR = new a();
    private static final long serialVersionUID = 7142128794153927442L;
    public String defaultText;

    /* loaded from: classes9.dex */
    static class a implements Parcelable.Creator<WebpageObject> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final WebpageObject createFromParcel(Parcel parcel) {
            return new WebpageObject(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public final WebpageObject[] newArray(int i2) {
            return new WebpageObject[i2];
        }
    }

    public WebpageObject() {
    }

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
    }

    public WebpageObject(Parcel parcel) {
        super(parcel);
    }
}
