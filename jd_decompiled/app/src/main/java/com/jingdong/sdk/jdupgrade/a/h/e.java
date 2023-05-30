package com.jingdong.sdk.jdupgrade.a.h;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

/* loaded from: classes7.dex */
public enum e implements Parcelable {
    UPDATE_NO("300"),
    UPGRADE_GRAYSCALE("301"),
    UPGRADE_ORDINARY("302"),
    UPGRADE_FORCE("303"),
    UPGRADE_SIGN_FAILURE("304"),
    UPGRADE_PARAM_ILLEGAL("305"),
    UPGRADE_CLIENT_ILLEGAL("306"),
    UPGRADE_APP_KEY_ERROR("307");
    
    public static final Parcelable.Creator<e> CREATOR = new Parcelable.Creator<e>() { // from class: com.jingdong.sdk.jdupgrade.a.h.e.a
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public e createFromParcel(Parcel parcel) {
            return e.values()[parcel.readInt()];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public e[] newArray(int i2) {
            return new e[i2];
        }
    };
    private final String a;

    e(String str) {
        this.a = str;
    }

    public static e a(@NonNull String str) {
        e[] values = values();
        for (int length = values.length - 1; length >= 0; length--) {
            e eVar = values[length];
            if (str.equals(eVar.a)) {
                return eVar;
            }
        }
        throw new IllegalArgumentException("No enum constant has value equals " + e.class.getCanonicalName() + ", value:" + str);
    }

    public String a() {
        e eVar = UPGRADE_GRAYSCALE;
        if (equals(eVar)) {
            return eVar.a;
        }
        e eVar2 = UPGRADE_ORDINARY;
        if (equals(eVar2)) {
            return eVar2.a;
        }
        e eVar3 = UPGRADE_FORCE;
        return equals(eVar3) ? eVar3.a : UPDATE_NO.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return e.class.hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(ordinal());
    }
}
