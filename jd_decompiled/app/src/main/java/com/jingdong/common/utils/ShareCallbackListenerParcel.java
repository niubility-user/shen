package com.jingdong.common.utils;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes6.dex */
public class ShareCallbackListenerParcel implements Parcelable {
    public static final Parcelable.Creator<ShareCallbackListenerParcel> CREATOR = new Parcelable.Creator<ShareCallbackListenerParcel>() { // from class: com.jingdong.common.utils.ShareCallbackListenerParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareCallbackListenerParcel createFromParcel(Parcel parcel) {
            return new ShareCallbackListenerParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareCallbackListenerParcel[] newArray(int i2) {
            return new ShareCallbackListenerParcel[i2];
        }
    };
    public ShareCallbackListenerBinder callbackListenerBinder;

    public ShareCallbackListenerParcel(ShareCallbackListenerBinder shareCallbackListenerBinder) {
        this.callbackListenerBinder = shareCallbackListenerBinder;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ShareUtil.CallbackListener getCallbackListener() {
        ShareCallbackListenerBinder shareCallbackListenerBinder = this.callbackListenerBinder;
        if (shareCallbackListenerBinder != null) {
            return shareCallbackListenerBinder.getCallbackListener();
        }
        return null;
    }

    public ShareUtil.ClickCallbackListener getClickCallbackListener() {
        ShareCallbackListenerBinder shareCallbackListenerBinder = this.callbackListenerBinder;
        if (shareCallbackListenerBinder != null) {
            return shareCallbackListenerBinder.getClickCallbackListener();
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeValue(this.callbackListenerBinder);
    }

    public ShareCallbackListenerParcel(Parcel parcel) {
        try {
            Object readValue = parcel.readValue(ShareCallbackListenerBinder.class.getClassLoader());
            if (readValue instanceof ShareCallbackListenerBinder) {
                this.callbackListenerBinder = (ShareCallbackListenerBinder) readValue;
            }
        } catch (Exception e2) {
            ExceptionReporter.reportKeyShareException("ShareCallbackListenerParcel", "", e2.toString(), "");
        }
    }
}
