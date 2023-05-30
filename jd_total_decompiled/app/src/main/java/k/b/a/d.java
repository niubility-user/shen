package k.b.a;

import java.io.BufferedInputStream;

/* loaded from: classes11.dex */
class d {
    static /* synthetic */ Class a;

    static /* synthetic */ Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BufferedInputStream b(String str) {
        Class cls = a;
        if (cls == null) {
            cls = a("net.sourceforge.pinyin4j.ResourceHelper");
            a = cls;
        }
        return new BufferedInputStream(cls.getResourceAsStream(str));
    }
}
