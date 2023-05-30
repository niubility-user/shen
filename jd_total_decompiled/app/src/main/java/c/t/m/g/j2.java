package c.t.m.g;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
public class j2 implements r2 {
    public IBinder a;

    public j2(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // c.t.m.g.r2
    public boolean a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } catch (Exception unused) {
            obtain2.recycle();
            obtain.recycle();
        }
        return obtain2.readInt() != 0;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.a;
    }

    @Override // c.t.m.g.r2
    public String c() {
        String str;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
            this.a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            str = obtain2.readString();
        } catch (Throwable unused) {
            obtain.recycle();
            obtain2.recycle();
            str = null;
        }
        obtain.recycle();
        obtain2.recycle();
        return str;
    }
}
