package com.jingdong.common.utils;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.common.utils.JDAreaCodeSelectUtil;

/* loaded from: classes6.dex */
public class AreaCodeSelectListenerParcel implements Parcelable {
    public static final Parcelable.Creator<AreaCodeSelectListenerParcel> CREATOR = new Parcelable.Creator<AreaCodeSelectListenerParcel>() { // from class: com.jingdong.common.utils.AreaCodeSelectListenerParcel.1
        @Override // android.os.Parcelable.Creator
        public AreaCodeSelectListenerParcel createFromParcel(Parcel parcel) {
            return new AreaCodeSelectListenerParcel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public AreaCodeSelectListenerParcel[] newArray(int i2) {
            return new AreaCodeSelectListenerParcel[i2];
        }
    };
    public AreaCodeSelectListenerBinder areaCodeSelectListenerBinder;

    public AreaCodeSelectListenerParcel(AreaCodeSelectListenerBinder areaCodeSelectListenerBinder) {
        this.areaCodeSelectListenerBinder = areaCodeSelectListenerBinder;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public JDAreaCodeSelectUtil.AreaCodeSelectListener getAreaCodeSelectListener() {
        AreaCodeSelectListenerBinder areaCodeSelectListenerBinder = this.areaCodeSelectListenerBinder;
        if (areaCodeSelectListenerBinder != null) {
            return areaCodeSelectListenerBinder.getAreaCodeSelectListener();
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeValue(this.areaCodeSelectListenerBinder);
    }

    public AreaCodeSelectListenerParcel(Parcel parcel) {
        Object readValue = parcel.readValue(AreaCodeSelectListenerBinder.class.getClassLoader());
        if (readValue instanceof AreaCodeSelectListenerBinder) {
            this.areaCodeSelectListenerBinder = (AreaCodeSelectListenerBinder) readValue;
        }
    }
}
