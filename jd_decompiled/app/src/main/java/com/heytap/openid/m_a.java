package com.heytap.openid;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes12.dex */
public interface m_a extends IInterface {

    /* renamed from: com.heytap.openid.m_a$m_a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static abstract class AbstractBinderC0055m_a extends Binder implements m_a {
        public static final String m_a = com.heytap.openid.sdk.m_a.m_a("Y29tLmhleXRhcC5vcGVuaWQuSU9wZW5JRA==");

        /* renamed from: com.heytap.openid.m_a$m_a$m_a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static class C0056m_a implements m_a {
            public IBinder m_a;

            public C0056m_a(IBinder iBinder) {
                this.m_a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.m_a;
            }

            @Override // com.heytap.openid.m_a
            public String m_a(String str, String str2, String str3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC0055m_a.m_a);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.m_a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static m_a m_a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(m_a);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof m_a)) ? new C0056m_a(iBinder) : (m_a) queryLocalInterface;
        }
    }

    String m_a(String str, String str2, String str3);
}
