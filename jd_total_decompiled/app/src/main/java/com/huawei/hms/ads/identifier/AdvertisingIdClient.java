package com.huawei.hms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import androidx.annotation.Keep;
import com.huawei.hms.ads.identifier.aidl.OpenDeviceIdentifierService;
import java.io.IOException;

@Keep
/* loaded from: classes12.dex */
public class AdvertisingIdClient {

    @Keep
    /* loaded from: classes12.dex */
    public static final class Info {
        private final String advertisingId;
        private final boolean limitAdTrackingEnabled;

        public Info(String str, boolean z) {
            this.advertisingId = str;
            this.limitAdTrackingEnabled = z;
        }

        @Keep
        public final String getId() {
            return this.advertisingId;
        }

        @Keep
        public final boolean isLimitAdTrackingEnabled() {
            return this.limitAdTrackingEnabled;
        }
    }

    @Keep
    public static Info getAdvertisingIdInfo(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.huawei.hwid", 0);
            a aVar = new a();
            Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
            intent.setPackage("com.huawei.hwid");
            try {
                if (context.bindService(intent, aVar, 1)) {
                    try {
                        if (aVar.a) {
                            throw new IllegalStateException();
                        }
                        aVar.a = true;
                        OpenDeviceIdentifierService asInterface = OpenDeviceIdentifierService.Stub.asInterface(aVar.b.take());
                        return new Info(asInterface.getOaid(), asInterface.isOaidTrackLimited());
                    } catch (RemoteException unused) {
                        throw new IOException("bind hms service RemoteException");
                    } catch (InterruptedException unused2) {
                        throw new IOException("bind hms service InterruptedException");
                    }
                }
                throw new IOException("bind failed");
            } finally {
                context.unbindService(aVar);
            }
        } catch (PackageManager.NameNotFoundException unused3) {
            throw new IOException("Service not found");
        }
    }

    public static boolean isAdvertisingIdAvailable(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.huawei.hwid", 0);
            new Intent("com.uodis.opendevice.OPENIDS_SERVICE").setPackage("com.huawei.hwid");
            return !r4.queryIntentServices(r2, 0).isEmpty();
        } catch (PackageManager.NameNotFoundException | Exception unused) {
            return false;
        }
    }
}
