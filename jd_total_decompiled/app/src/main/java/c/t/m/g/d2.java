package c.t.m.g;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public interface d2 extends IInterface {

    /* loaded from: classes.dex */
    public static abstract class a extends Binder implements d2 {

        /* renamed from: c.t.m.g.d2$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0007a implements d2 {
            public IBinder a;

            public C0007a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }

            public String d(String str, String str2, String str3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    obtain.recycle();
                    obtain2.recycle();
                    return readString;
                } catch (Exception unused) {
                    obtain.recycle();
                    obtain2.recycle();
                    return null;
                } catch (Throwable th) {
                    obtain.recycle();
                    obtain2.recycle();
                    throw th;
                }
            }
        }

        public static d2 d(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof d2)) ? new C0007a(iBinder) : (d2) queryLocalInterface;
        }
    }
}
