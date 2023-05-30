package jd.wjlogin_sdk.util;

import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.json.JSONException;

/* loaded from: classes.dex */
public class o {
    public static void a(String str, Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
            v.a(str, ByteUtil.parseByte2HexStr(byteArrayOutputStream.toByteArray()));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static Object a(String str) {
        try {
            if (v.a(str)) {
                String e2 = v.e(str);
                if (TextUtils.isEmpty(e2)) {
                    return null;
                }
                return new ObjectInputStream(new ByteArrayInputStream(ByteUtil.parseHexStr2Byte(e2))).readObject();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static <T> Object a(String str, Class<T> cls) {
        try {
            if (w.a(str)) {
                String a = w.a(str, (String) null);
                if (TextUtils.isEmpty(a)) {
                    return null;
                }
                try {
                    return l.a(n.a(a), cls);
                } catch (JSONException unused) {
                }
            }
        } catch (Throwable th) {
            c0.a((short) d.g0, "LocalFileUtil getObjectJsonDecrypt e=" + th.getMessage());
        }
        return null;
    }
}
