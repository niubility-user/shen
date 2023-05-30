package com.jingdong.manto.m.t0.d.e;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();
    private BluetoothDevice a;
    public com.jingdong.manto.m.t0.d.e.a b;

    /* renamed from: c  reason: collision with root package name */
    public int f13695c;
    private long d;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<b> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final b createFromParcel(Parcel parcel) {
            return new b(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final b[] newArray(int i2) {
            return new b[i2];
        }
    }

    public b(BluetoothDevice bluetoothDevice, com.jingdong.manto.m.t0.d.e.a aVar, int i2, long j2) {
        this.a = bluetoothDevice;
        this.b = aVar;
        this.f13695c = i2;
        this.d = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(21)
    public b(ScanResult scanResult) {
        this.a = scanResult.getDevice();
        this.b = new com.jingdong.manto.m.t0.d.e.a(scanResult.getScanRecord());
        this.f13695c = scanResult.getRssi();
        this.d = System.currentTimeMillis();
    }

    private b(Parcel parcel) {
        if (parcel.readInt() == 1) {
            this.a = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(parcel);
        }
        if (parcel.readInt() == 1) {
            this.b = com.jingdong.manto.m.t0.d.e.a.a(parcel.createByteArray());
        }
        this.f13695c = parcel.readInt();
        this.d = parcel.readLong();
    }

    /* synthetic */ b(Parcel parcel, a aVar) {
        this(parcel);
    }

    public final BluetoothDevice a() {
        BluetoothDevice bluetoothDevice = this.a;
        if (bluetoothDevice != null) {
            return bluetoothDevice;
        }
        return null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && b.class == obj.getClass()) {
            b bVar = (b) obj;
            if (BTHelper.equals(this.a, bVar.a) && this.f13695c == bVar.f13695c && BTHelper.equals(this.b, bVar.b) && this.d == bVar.d) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, Integer.valueOf(this.f13695c), this.b, Long.valueOf(this.d)});
    }

    public String toString() {
        return "ScanResult{mDevice=" + this.a + ", mScanRecord=" + this.b + ", mRssi=" + this.f13695c + ", mTimestampNanos=" + this.d + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        if (this.a != null) {
            parcel.writeInt(1);
            this.a.writeToParcel(parcel, i2);
        } else {
            parcel.writeInt(0);
        }
        if (this.b != null) {
            parcel.writeInt(1);
            parcel.writeByteArray(this.b.f13693f);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.f13695c);
        parcel.writeLong(this.d);
    }
}
