package m.a.a;

import java.security.PrivilegedAction;

/* loaded from: classes11.dex */
public class a implements PrivilegedAction<Boolean> {
    private String a;

    public a(String str) {
        this.a = str;
    }

    @Override // java.security.PrivilegedAction
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public Boolean run() {
        return Boolean.valueOf(Boolean.getBoolean(this.a));
    }
}
