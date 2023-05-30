package g.d.a.j;

import com.jingdong.manto.sdk.api.IMantoServerRequester;

/* loaded from: classes12.dex */
public class a {
    private static String a(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod(IMantoServerRequester.GET, String.class).invoke(null, str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean b() {
        return "file".equals(a("ro.crypto.type"));
    }
}
