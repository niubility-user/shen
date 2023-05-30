package c.t.m.g;

import android.content.Context;
import android.content.Intent;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.UnsupportedEncodingException;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes.dex */
public class i4 {
    public static String a(int i2) {
        byte[] bArr = {-100, -112, -110, -47, ReplyCode.reply0x8b, -102, -111, -100, -102, -111, ReplyCode.reply0x8b, -47, -110, -98, -113, -47, ReplyCode.reply0x88, -98, -108, -102, ReplyCode.reply0x8a, -113};
        int i3 = (i2 * 7) + 15 > 15 ? 21 : 15;
        byte[] bArr2 = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            if (i3 <= 15 || i4 <= 9) {
                bArr2[i4] = (byte) (bArr[i4] ^ (-1));
            } else {
                bArr2[i4] = (byte) (bArr[i4 + 1] ^ (-1));
            }
        }
        try {
            return new String(bArr2, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void b(Context context) {
        try {
            String deviceManufacture = BaseInfo.getDeviceManufacture();
            boolean parseBoolean = Boolean.parseBoolean(m0.a().get("enable_invoke_map"));
            StringBuilder sb = new StringBuilder("invokeMap start, manufacturer = ");
            sb.append(deviceManufacture);
            sb.append(", control = ");
            sb.append(parseBoolean);
            if (parseBoolean) {
                if (deviceManufacture.equals("Meizu") || deviceManufacture.equals("samsung") || deviceManufacture.equals("OnePlus")) {
                    long b = f0.b("LocationSDK", "location_invoke_map_time", 0L);
                    int a = f0.a("LocationSDK", "location_invoke_map_count", 0);
                    int i2 = ((System.currentTimeMillis() - b) > 86400000L ? 1 : ((System.currentTimeMillis() - b) == 86400000L ? 0 : -1));
                    new StringBuilder("invokeMap middle, result = ").append(i2 > 0 && a <= 5);
                    if (i2 <= 0 || a > 5) {
                        return;
                    }
                    f0.f("LocationSDK", "location_invoke_map_time", System.currentTimeMillis());
                    f0.e("LocationSDK", "location_invoke_map_count", a + 1);
                    Intent intent = new Intent();
                    intent.setPackage(a(0));
                    intent.setAction(a(1));
                    intent.putExtra("source", "location_official");
                    context.startService(intent);
                    StringBuilder sb2 = new StringBuilder("invokeMap end, ");
                    sb2.append(a(0));
                    sb2.append(", ");
                    sb2.append(a(1));
                }
            }
        } catch (Throwable unused) {
        }
    }
}
