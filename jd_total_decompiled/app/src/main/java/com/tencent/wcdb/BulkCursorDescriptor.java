package com.tencent.wcdb;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public final class BulkCursorDescriptor implements Parcelable {
    public static final Parcelable.Creator<BulkCursorDescriptor> CREATOR = new Parcelable.Creator<BulkCursorDescriptor>() { // from class: com.tencent.wcdb.BulkCursorDescriptor.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BulkCursorDescriptor createFromParcel(Parcel parcel) {
            BulkCursorDescriptor bulkCursorDescriptor = new BulkCursorDescriptor();
            bulkCursorDescriptor.readFromParcel(parcel);
            return bulkCursorDescriptor;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BulkCursorDescriptor[] newArray(int i2) {
            return new BulkCursorDescriptor[i2];
        }
    };
    public String[] columnNames;
    public int count;
    public IBulkCursor cursor;
    public boolean wantsAllOnMoveCalls;
    public CursorWindow window;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        this.cursor = BulkCursorNative.asInterface(parcel.readStrongBinder());
        this.columnNames = readStringArray(parcel);
        this.wantsAllOnMoveCalls = parcel.readInt() != 0;
        this.count = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.window = CursorWindow.CREATOR.createFromParcel(parcel);
        }
    }

    public final String[] readStringArray(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            String[] strArr = new String[readInt];
            for (int i2 = 0; i2 < readInt; i2++) {
                strArr[i2] = parcel.readString();
            }
            return strArr;
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeStrongBinder(this.cursor.asBinder());
        parcel.writeStringArray(this.columnNames);
        parcel.writeInt(this.wantsAllOnMoveCalls ? 1 : 0);
        parcel.writeInt(this.count);
        if (this.window != null) {
            parcel.writeInt(1);
            this.window.writeToParcel(parcel, i2);
            return;
        }
        parcel.writeInt(0);
    }
}
