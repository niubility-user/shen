package com.jingdong.manto.pkg.ipc;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;

/* loaded from: classes16.dex */
public class MantoPkgUpdate implements Parcelable {
    public static final Parcelable.Creator<MantoPkgUpdate> CREATOR = new a();
    public UpdateAction action;
    public PkgDetailEntity detailEntity;

    /* loaded from: classes16.dex */
    public enum UpdateAction {
        FAVO,
        UNFAVO
    }

    /* loaded from: classes16.dex */
    class a implements Parcelable.Creator<MantoPkgUpdate> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public MantoPkgUpdate createFromParcel(Parcel parcel) {
            return new MantoPkgUpdate(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public MantoPkgUpdate[] newArray(int i2) {
            return new MantoPkgUpdate[i2];
        }
    }

    public MantoPkgUpdate() {
    }

    protected MantoPkgUpdate(Parcel parcel) {
        this.detailEntity = (PkgDetailEntity) parcel.readParcelable(PkgDetailEntity.class.getClassLoader());
        int readInt = parcel.readInt();
        this.action = readInt == -1 ? null : UpdateAction.values()[readInt];
    }

    public MantoPkgUpdate(PkgDetailEntity pkgDetailEntity, UpdateAction updateAction) {
        this.detailEntity = pkgDetailEntity;
        this.action = updateAction;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.detailEntity, i2);
        UpdateAction updateAction = this.action;
        parcel.writeInt(updateAction == null ? -1 : updateAction.ordinal());
    }
}
