package g.e.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes12.dex */
public interface d extends IInterface {

    /* loaded from: classes12.dex */
    public static abstract class a extends Binder implements d {

        /* renamed from: g.e.b.a.d$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        private static class C0837a implements d {
            public static d a;
        }

        public a() {
            attachInterface(this, "com.huawei.caasservice.thirdparty.IHwCallAbilityCallback");
        }

        public static d caasa() {
            return C0837a.a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 == 1) {
                parcel.enforceInterface("com.huawei.caasservice.thirdparty.IHwCallAbilityCallback");
                caasa(parcel.readInt());
                return true;
            } else if (i2 == 2) {
                parcel.enforceInterface("com.huawei.caasservice.thirdparty.IHwCallAbilityCallback");
                caasa(parcel.readString(), parcel.readInt());
                return true;
            } else if (i2 == 3) {
                parcel.enforceInterface("com.huawei.caasservice.thirdparty.IHwCallAbilityCallback");
                caasb(parcel.readString(), parcel.readInt());
                return true;
            } else if (i2 != 1598968902) {
                return super.onTransact(i2, parcel, parcel2, i3);
            } else {
                parcel2.writeString("com.huawei.caasservice.thirdparty.IHwCallAbilityCallback");
                return true;
            }
        }
    }

    void caasa(int i2);

    void caasa(String str, int i2);

    void caasb(String str, int i2);
}
