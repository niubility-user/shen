package c.t.m.g;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
public class f1 implements m1 {
    public IBinder a;

    public f1(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    @Override // c.t.m.g.m1
    public String b() {
        String str;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            str = obtain2.readString();
        } catch (Exception unused) {
            str = null;
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
        obtain2.recycle();
        obtain.recycle();
        return str;
    }

    @Override // c.t.m.g.m1
    public boolean d() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
            this.a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            r2 = obtain2.readInt() != 0;
            obtain2.recycle();
            obtain.recycle();
        } catch (Throwable unused) {
            obtain2.recycle();
            obtain.recycle();
        }
        return r2;
    }
}
