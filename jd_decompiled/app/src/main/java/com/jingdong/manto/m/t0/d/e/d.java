package com.jingdong.manto.m.t0.d.e;

import android.bluetooth.BluetoothAdapter;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import java.util.Arrays;
import java.util.UUID;

/* loaded from: classes15.dex */
public class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new a();
    final String a;
    final ParcelUuid b;

    /* renamed from: c  reason: collision with root package name */
    final ParcelUuid f13696c;
    final ParcelUuid d;

    /* renamed from: e  reason: collision with root package name */
    final byte[] f13697e;

    /* renamed from: f  reason: collision with root package name */
    final byte[] f13698f;

    /* renamed from: g  reason: collision with root package name */
    final int f13699g;

    /* renamed from: h  reason: collision with root package name */
    final byte[] f13700h;

    /* renamed from: i  reason: collision with root package name */
    final byte[] f13701i;

    /* renamed from: j  reason: collision with root package name */
    final String f13702j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Parcelable.Creator<d> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final d createFromParcel(Parcel parcel) {
            b bVar = new b();
            if (parcel.readInt() == 1) {
                bVar.f13709j = parcel.readString();
            }
            if (parcel.readInt() == 1) {
                String readString = parcel.readString();
                if (readString != null && !BluetoothAdapter.checkBluetoothAddress(readString)) {
                    throw new IllegalArgumentException("invalid device address ".concat(String.valueOf(readString)));
                }
                bVar.a = readString;
            }
            if (parcel.readInt() == 1) {
                ParcelUuid parcelUuid = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                bVar.a(parcelUuid);
                if (parcel.readInt() == 1) {
                    ParcelUuid parcelUuid2 = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                    if (bVar.f13708i != null && bVar.b == null) {
                        throw new IllegalArgumentException("uuid is null while uuidMask is not null!");
                    }
                    bVar.b = parcelUuid;
                    bVar.f13708i = parcelUuid2;
                }
            }
            if (parcel.readInt() == 1) {
                ParcelUuid parcelUuid3 = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                if (parcel.readInt() == 1) {
                    byte[] bArr = new byte[parcel.readInt()];
                    parcel.readByteArray(bArr);
                    if (parcel.readInt() != 0) {
                        byte[] bArr2 = new byte[parcel.readInt()];
                        parcel.readByteArray(bArr2);
                        if (parcelUuid3 == null) {
                            throw new IllegalArgumentException("serviceDataUuid is null");
                        }
                        byte[] bArr3 = bVar.f13704e;
                        if (bArr3 != null) {
                            byte[] bArr4 = bVar.d;
                            if (bArr4 == null) {
                                throw new IllegalArgumentException("serviceData is null while serviceDataMask is not null");
                            }
                            if (bArr4.length != bArr3.length) {
                                throw new IllegalArgumentException("size mismatch for service data and service data mask");
                            }
                        }
                        bVar.f13703c = parcelUuid3;
                        bVar.d = bArr;
                        bVar.f13704e = bArr2;
                    } else if (parcelUuid3 == null) {
                        throw new IllegalArgumentException("serviceDataUuid is null");
                    } else {
                        bVar.f13703c = parcelUuid3;
                        bVar.d = bArr;
                        bVar.f13704e = null;
                    }
                }
            }
            int readInt = parcel.readInt();
            if (parcel.readInt() == 1) {
                byte[] bArr5 = new byte[parcel.readInt()];
                parcel.readByteArray(bArr5);
                if (parcel.readInt() != 0) {
                    byte[] bArr6 = new byte[parcel.readInt()];
                    parcel.readByteArray(bArr6);
                    if (readInt < 0) {
                        throw new IllegalArgumentException("invalid manufacture id");
                    }
                    byte[] bArr7 = bVar.f13707h;
                    if (bArr7 != null) {
                        byte[] bArr8 = bVar.f13706g;
                        if (bArr8 == null) {
                            throw new IllegalArgumentException("manufacturerData is null while manufacturerDataMask is not null");
                        }
                        if (bArr8.length != bArr7.length) {
                            throw new IllegalArgumentException("size mismatch for manufacturerData and manufacturerDataMask");
                        }
                    }
                    bVar.f13705f = readInt;
                    bVar.f13706g = bArr5;
                    bVar.f13707h = bArr6;
                } else if (readInt < 0) {
                    throw new IllegalArgumentException("invalid manufacture id");
                } else {
                    bVar.f13705f = readInt;
                    bVar.f13706g = bArr5;
                    bVar.f13707h = null;
                }
            }
            return bVar.a();
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final d[] newArray(int i2) {
            return new d[i2];
        }
    }

    /* loaded from: classes15.dex */
    public static final class b {
        String a;
        ParcelUuid b;

        /* renamed from: c  reason: collision with root package name */
        ParcelUuid f13703c;
        byte[] d;

        /* renamed from: e  reason: collision with root package name */
        byte[] f13704e;

        /* renamed from: f  reason: collision with root package name */
        int f13705f = -1;

        /* renamed from: g  reason: collision with root package name */
        byte[] f13706g;

        /* renamed from: h  reason: collision with root package name */
        byte[] f13707h;

        /* renamed from: i  reason: collision with root package name */
        ParcelUuid f13708i;

        /* renamed from: j  reason: collision with root package name */
        String f13709j;

        public final b a(ParcelUuid parcelUuid) {
            this.b = parcelUuid;
            this.f13708i = null;
            return this;
        }

        public final d a() {
            return new d(this.f13709j, this.a, this.b, this.f13708i, this.f13703c, this.d, this.f13704e, this.f13705f, this.f13706g, this.f13707h, null);
        }
    }

    static {
        new b().a();
    }

    private d(String str, String str2, ParcelUuid parcelUuid, ParcelUuid parcelUuid2, ParcelUuid parcelUuid3, byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4) {
        this.f13702j = str;
        this.b = parcelUuid;
        this.f13696c = parcelUuid2;
        this.a = str2;
        this.d = parcelUuid3;
        this.f13697e = bArr;
        this.f13698f = bArr2;
        this.f13699g = i2;
        this.f13700h = bArr3;
        this.f13701i = bArr4;
    }

    /* synthetic */ d(String str, String str2, ParcelUuid parcelUuid, ParcelUuid parcelUuid2, ParcelUuid parcelUuid3, byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, a aVar) {
        this(str, str2, parcelUuid, parcelUuid2, parcelUuid3, bArr, bArr2, i2, bArr3, bArr4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(UUID uuid, UUID uuid2, UUID uuid3) {
        if (uuid2 == null) {
            return uuid.equals(uuid3);
        }
        if ((uuid.getLeastSignificantBits() & uuid2.getLeastSignificantBits()) != (uuid3.getLeastSignificantBits() & uuid2.getLeastSignificantBits())) {
            return false;
        }
        return (uuid.getMostSignificantBits() & uuid2.getMostSignificantBits()) == (uuid2.getMostSignificantBits() & uuid3.getMostSignificantBits());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr3 == null || bArr3.length < bArr.length) {
            return false;
        }
        if (bArr2 == null) {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                if (bArr3[i2] != bArr[i2]) {
                    return false;
                }
            }
            return true;
        }
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if ((bArr2[i3] & bArr3[i3]) != (bArr2[i3] & bArr[i3])) {
                return false;
            }
        }
        return true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && d.class == obj.getClass()) {
            d dVar = (d) obj;
            if (TextUtils.equals(this.f13702j, dVar.f13702j) && TextUtils.equals(this.a, dVar.a) && this.f13699g == dVar.f13699g && BTHelper.deepEquals(this.f13700h, dVar.f13700h) && BTHelper.deepEquals(this.f13701i, dVar.f13701i) && BTHelper.equals(this.d, dVar.d) && BTHelper.deepEquals(this.f13697e, dVar.f13697e) && BTHelper.deepEquals(this.f13698f, dVar.f13698f) && BTHelper.equals(this.b, dVar.b) && BTHelper.equals(this.f13696c, dVar.f13696c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f13702j, this.a, Integer.valueOf(this.f13699g), this.f13700h, this.f13701i, this.d, this.f13697e, this.f13698f, this.b, this.f13696c});
    }

    public String toString() {
        return "BluetoothLeScanFilter [mDeviceName=" + this.f13702j + ", mDeviceAddress=" + this.a + ", mUuid=" + this.b + ", mUuidMask=" + this.f13696c + ", mServiceDataUuid=" + this.d + ", mServiceData=" + Arrays.toString(this.f13697e) + ", mServiceDataMask=" + Arrays.toString(this.f13698f) + ", mManufacturerId=" + this.f13699g + ", mManufacturerData=" + Arrays.toString(this.f13700h) + ", mManufacturerDataMask=" + Arrays.toString(this.f13701i) + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f13702j == null ? 0 : 1);
        String str = this.f13702j;
        if (str != null) {
            parcel.writeString(str);
        }
        parcel.writeInt(this.a == null ? 0 : 1);
        String str2 = this.a;
        if (str2 != null) {
            parcel.writeString(str2);
        }
        parcel.writeInt(this.b == null ? 0 : 1);
        ParcelUuid parcelUuid = this.b;
        if (parcelUuid != null) {
            parcel.writeParcelable(parcelUuid, i2);
            parcel.writeInt(this.f13696c == null ? 0 : 1);
            ParcelUuid parcelUuid2 = this.f13696c;
            if (parcelUuid2 != null) {
                parcel.writeParcelable(parcelUuid2, i2);
            }
        }
        parcel.writeInt(this.d == null ? 0 : 1);
        ParcelUuid parcelUuid3 = this.d;
        if (parcelUuid3 != null) {
            parcel.writeParcelable(parcelUuid3, i2);
            parcel.writeInt(this.f13697e == null ? 0 : 1);
            byte[] bArr = this.f13697e;
            if (bArr != null) {
                parcel.writeInt(bArr.length);
                parcel.writeByteArray(this.f13697e);
                parcel.writeInt(this.f13698f == null ? 0 : 1);
                byte[] bArr2 = this.f13698f;
                if (bArr2 != null) {
                    parcel.writeInt(bArr2.length);
                    parcel.writeByteArray(this.f13698f);
                }
            }
        }
        parcel.writeInt(this.f13699g);
        parcel.writeInt(this.f13700h == null ? 0 : 1);
        byte[] bArr3 = this.f13700h;
        if (bArr3 != null) {
            parcel.writeInt(bArr3.length);
            parcel.writeByteArray(this.f13700h);
            parcel.writeInt(this.f13701i == null ? 0 : 1);
            byte[] bArr4 = this.f13701i;
            if (bArr4 != null) {
                parcel.writeInt(bArr4.length);
                parcel.writeByteArray(this.f13701i);
            }
        }
    }
}
