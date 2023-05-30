package c.t.m.g;

import android.location.Location;
import android.text.format.DateFormat;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.tencent.map.geolocation.TencentLocation;

/* loaded from: classes.dex */
public class n6 {
    public static Location a(TencentLocation tencentLocation, boolean z) {
        if (tencentLocation == null) {
            return null;
        }
        String provider = tencentLocation.getProvider();
        Location location = new Location(provider);
        location.setLatitude(tencentLocation.getLatitude());
        location.setLongitude(tencentLocation.getLongitude());
        location.setAccuracy(tencentLocation.getAccuracy());
        location.setTime(tencentLocation.getTime());
        if (z && "gps".equals(provider)) {
            double[] dArr = new double[2];
            u0.k(location, dArr);
            location.setLatitude(dArr[0]);
            location.setLongitude(dArr[1]);
            ((q5) tencentLocation).v(location);
        }
        return location;
    }

    public static String b(TencentLocation tencentLocation, int i2) {
        return ((Object) DateFormat.format("yyyy-MM-dd kk:mm:ss", tencentLocation == null ? System.currentTimeMillis() : tencentLocation.getTime())) + " error=" + i2 + tencentLocation + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
    }

    public static void c(Location location) {
        if (location.getAccuracy() > 500.0f) {
            location.setAccuracy(500.0f);
        }
    }
}
