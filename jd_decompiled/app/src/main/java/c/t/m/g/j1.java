package c.t.m.g;

import android.location.Location;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationRequest;

/* loaded from: classes.dex */
public class j1 {
    public static boolean a = true;

    public static TencentLocation a(TencentLocation tencentLocation, byte[] bArr) {
        return tencentLocation;
    }

    public static void b(TencentLocation tencentLocation, Location location) {
    }

    public static void c(boolean z) {
        a = z;
    }

    public static boolean d(int i2) {
        return i2 == 0 || i2 == 1 || i2 == 3 || i2 == 4;
    }

    public static boolean e(TencentLocationRequest tencentLocationRequest) {
        return false;
    }

    public static boolean f(int i2) {
        switch (i2) {
            case 10:
            case 11:
            case 12:
                return true;
            default:
                return false;
        }
    }
}
