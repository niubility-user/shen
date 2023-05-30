package g.f.a.b;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.UUID;

/* loaded from: classes12.dex */
public class a {
    public static String a = "";

    public static String a(Context context) {
        if (TextUtils.isEmpty(a)) {
            try {
                String str = "35" + (Build.BOARD.length() % 10) + (BaseInfo.getDeviceBrand().length() % 10) + (Build.CPU_ABI.length() % 10) + (BaseInfo.getDeviceName().length() % 10) + (BaseInfo.getOSName().length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (BaseInfo.getDeviceManufacture().length() % 10) + (BaseInfo.getDeviceModel().length() % 10) + (BaseInfo.getDeviceProductName().length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10);
                try {
                    return new UUID(str.hashCode(), Build.class.getField("SERIAL").get(null).toString().hashCode()).toString();
                } catch (Exception unused) {
                    a = new UUID(str.hashCode(), -905839116).toString();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                a = "unknow android device";
            }
        }
        return a;
    }
}
