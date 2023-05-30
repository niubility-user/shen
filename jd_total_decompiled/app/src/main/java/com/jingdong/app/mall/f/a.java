package com.jingdong.app.mall.f;

import android.text.TextUtils;
import com.jingdong.app.mall.R;
import com.jingdong.c.b.c;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.secure.DesUtil;
import com.jingdong.jdsdk.utils.Md5Encrypt;

/* loaded from: classes19.dex */
public class a {
    private static String a() {
        String string = JdSdk.getInstance().getApplication().getString(R.string.upgrade_md5key_center);
        String string2 = JdSdk.getInstance().getApplication().getString(R.string.upgrade_md5key_value);
        String str = "";
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && string2.length() >= 5) {
            str = "" + string2.charAt(2) + string2.charAt(3) + string2.charAt(4) + string + string2.charAt(0) + string2.charAt(1);
            if (Log.D) {
                Log.d("UpdateInitialization", "center=" + string + " value= " + string2 + " key= " + str);
            }
        }
        return str;
    }

    public static boolean b(String str, String str2) {
        if (Log.D) {
            Log.d("UpdateInitialization", "ApkCheck-validate-url=" + str + " md5 = " + str2);
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        String a = c.a(JdSdk.getInstance().getApplicationContext());
        String encrypt = DesUtil.encrypt(a, a());
        String md5 = Md5Encrypt.md5(str + encrypt);
        if (Log.D) {
            Log.d("UpdateInitialization", "ApkCheck-validate-uuid=" + a + " des = " + encrypt + " md5Local = " + md5);
        }
        return str2.equals(md5);
    }
}
