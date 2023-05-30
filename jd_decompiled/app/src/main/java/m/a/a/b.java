package m.a.a;

import java.security.PrivilegedAction;

/* loaded from: classes11.dex */
public class b implements PrivilegedAction<String> {
    private String a;
    private String b;

    public b(String str) {
        this.a = str;
    }

    @Override // java.security.PrivilegedAction
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public String run() {
        String property = System.getProperty(this.a);
        return property == null ? this.b : property;
    }
}
