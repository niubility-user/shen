package com.jingdong.common.entity.personal;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes5.dex */
public class LibMtaEntity implements Parcelable, Cloneable {
    public static final Parcelable.Creator<LibMtaEntity> CREATOR = new Parcelable.Creator<LibMtaEntity>() { // from class: com.jingdong.common.entity.personal.LibMtaEntity.1
        @Override // android.os.Parcelable.Creator
        public LibMtaEntity createFromParcel(Parcel parcel) {
            return new LibMtaEntity(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public LibMtaEntity[] newArray(int i2) {
            return new LibMtaEntity[i2];
        }
    };
    public String eventId;
    public String eventParam;
    public HashMap<String, String> extendParam;
    public String jsonParam;
    public String pageId;
    public String pageParam;

    protected LibMtaEntity(Parcel parcel) {
        this.eventId = parcel.readString();
        this.pageId = parcel.readString();
        this.eventParam = parcel.readString();
        this.pageParam = parcel.readString();
        this.jsonParam = parcel.readString();
        this.extendParam = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && (obj instanceof LibMtaEntity)) {
            LibMtaEntity libMtaEntity = (LibMtaEntity) obj;
            String str = this.eventId;
            if (str == null ? libMtaEntity.eventId == null : str.equals(libMtaEntity.eventId)) {
                String str2 = this.pageId;
                if (str2 == null ? libMtaEntity.pageId == null : str2.equals(libMtaEntity.pageId)) {
                    String str3 = this.eventParam;
                    if (str3 == null ? libMtaEntity.eventParam == null : str3.equals(libMtaEntity.eventParam)) {
                        String str4 = this.pageParam;
                        if (str4 == null ? libMtaEntity.pageParam == null : str4.equals(libMtaEntity.pageParam)) {
                            HashMap<String, String> hashMap = this.extendParam;
                            if (hashMap == null ? libMtaEntity.extendParam == null : hashMap.equals(libMtaEntity.extendParam)) {
                                String str5 = this.jsonParam;
                                String str6 = libMtaEntity.jsonParam;
                                return str5 != null ? str5.equals(str6) : str6 == null;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String str = this.eventId;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.pageId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.eventParam;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.pageParam;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.jsonParam;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        HashMap<String, String> hashMap = this.extendParam;
        return hashCode5 + (hashMap != null ? hashMap.hashCode() : 0);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.eventId);
        parcel.writeString(this.pageId);
        parcel.writeString(this.eventParam);
        parcel.writeString(this.pageParam);
        parcel.writeString(this.jsonParam);
        parcel.writeMap(this.extendParam);
    }

    @NotNull
    /* renamed from: clone */
    public LibMtaEntity m21clone() {
        try {
            return (LibMtaEntity) super.clone();
        } catch (CloneNotSupportedException e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }

    public LibMtaEntity() {
    }
}
