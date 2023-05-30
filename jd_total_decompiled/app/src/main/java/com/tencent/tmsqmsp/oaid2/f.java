package com.tencent.tmsqmsp.oaid2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes9.dex */
public interface f extends IInterface {

    /* loaded from: classes9.dex */
    public static abstract class a extends Binder implements f {

        /* renamed from: com.tencent.tmsqmsp.oaid2.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public static class C0815a implements f {
            public IBinder a;

            public C0815a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }

            /* JADX WARN: Code restructure failed: missing block: B:4:0x001b, code lost:
                if (r1.readInt() == 0) goto L10;
             */
            @Override // com.tencent.tmsqmsp.oaid2.f
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public boolean b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                boolean z = false;
                try {
                    obtain.writeInterfaceToken("com.asus.msa.SupplementaryDID.IDidAidlInterface");
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } catch (Exception unused) {
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
                z = true;
                obtain2.recycle();
                obtain.recycle();
                return z;
            }

            @Override // com.tencent.tmsqmsp.oaid2.f
            public String c() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.asus.msa.SupplementaryDID.IDidAidlInterface");
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable unused) {
                    obtain2.recycle();
                    obtain.recycle();
                    return "";
                }
            }

            @Override // com.tencent.tmsqmsp.oaid2.f
            public String i() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.asus.msa.SupplementaryDID.IDidAidlInterface");
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable unused) {
                    obtain2.recycle();
                    obtain.recycle();
                    return "";
                }
            }
        }

        public static f a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.asus.msa.SupplementaryDID.IDidAidlInterface");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof f)) ? new C0815a(iBinder) : (f) queryLocalInterface;
        }
    }

    boolean b();

    String c();

    String i();
}
