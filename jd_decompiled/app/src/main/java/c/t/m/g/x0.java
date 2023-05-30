package c.t.m.g;

import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.provider.Settings;
import android.text.TextUtils;

/* loaded from: classes.dex */
public final class x0 implements IInterface {
    public IBinder a;
    public Context b;

    public x0(IBinder iBinder, Context context) {
        this.a = iBinder;
        this.b = context;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x001b, code lost:
        if (r1.readInt() == 0) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean e() {
        /*
            r5 = this;
            android.os.Parcel r0 = android.os.Parcel.obtain()
            android.os.Parcel r1 = android.os.Parcel.obtain()
            r2 = 0
            r3 = 1
            java.lang.String r4 = "com.uodis.opendevice.aidl.OpenDeviceIdentifierService"
            r0.writeInterfaceToken(r4)     // Catch: java.lang.Throwable -> L1e
            android.os.IBinder r4 = r5.a     // Catch: java.lang.Throwable -> L1e
            r4.transact(r3, r0, r1, r2)     // Catch: java.lang.Throwable -> L1e
            r1.readException()     // Catch: java.lang.Throwable -> L1e
            int r4 = r1.readInt()     // Catch: java.lang.Throwable -> L1e
            if (r4 != 0) goto L24
            goto L25
        L1e:
            r0.recycle()
            r1.recycle()
        L24:
            r2 = 1
        L25:
            r0.recycle()
            r1.recycle()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.x0.e():boolean");
    }

    public final String j() {
        String str;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
            this.a.transact(1, obtain, obtain2, 0);
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

    public final String k() {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                String string = Settings.Global.getString(this.b.getContentResolver(), "pps_oaid");
                String string2 = Settings.Global.getString(this.b.getContentResolver(), "pps_track_limit");
                if (!TextUtils.isEmpty(string)) {
                    if (!TextUtils.isEmpty(string2)) {
                        return "get oaid failed";
                    }
                }
                return string;
            } catch (Throwable th) {
                th.printStackTrace();
                return "get oaid failed";
            }
        }
        return null;
    }
}
