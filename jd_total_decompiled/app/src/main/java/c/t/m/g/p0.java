package c.t.m.g;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public final class p0 implements IInterface {
    public IBinder a;

    public p0(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.a;
    }

    public final String e() {
        String str;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.asus.msa.SupplementaryDID.IDidAidlInterface");
            this.a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            str = obtain2.readString();
        } catch (Throwable unused) {
            obtain.recycle();
            obtain2.recycle();
            str = "get oaid failed";
        }
        obtain.recycle();
        obtain2.recycle();
        return str;
    }

    public final boolean j() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.asus.msa.SupplementaryDID.IDidAidlInterface");
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } catch (Exception unused) {
            obtain2.recycle();
            obtain.recycle();
        }
        return obtain2.readInt() != 0;
    }
}
