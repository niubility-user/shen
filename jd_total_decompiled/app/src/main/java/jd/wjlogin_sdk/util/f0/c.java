package jd.wjlogin_sdk.util.f0;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes.dex */
public class c {
    public static <T extends Serializable> T a(String str) throws Throwable {
        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(jd.wjlogin_sdk.util.b.a(str))));
        T t = (T) objectInputStream.readObject();
        objectInputStream.close();
        return t;
    }
}
