package g.e.a.h.c;

import android.content.Context;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class m extends l {

    /* renamed from: c  reason: collision with root package name */
    private final Map<String, String> f19467c;
    private final Object d;

    /* renamed from: e  reason: collision with root package name */
    private i f19468e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f19469f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(Context context, String str) {
        super(context, str);
        this.f19467c = new HashMap();
        this.d = new Object();
        this.f19469f = true;
        try {
            String a = a("/AD91D45E3E72DB6989DDCB13287E75061FABCB933D886E6C6ABEF0939B577138");
            String a2 = a("/B314B3BF013DF5AC4134E880AF3D2B7C9FFBE8F0305EAC1C898145E2BCF1F21C");
            String a3 = a("/C767BD8FDF53E53D059BE95B09E2A71056F5F180AECC62836B287ACA5793421B");
            String a4 = a("/DCB3E6D4C2CF80F30D89CDBC412C964DA8381BB84668769391FBCC3E329AD0FD");
            if (a == null || a2 == null || a3 == null || a4 == null) {
                this.f19469f = false;
            } else {
                this.f19468e = new h(a, a2, a3, a4);
            }
        } catch (IllegalArgumentException | NoSuchAlgorithmException | InvalidKeySpecException unused) {
            this.f19468e = null;
        }
    }

    private String a(String str) {
        return super.a(str, null);
    }

    @Override // g.e.a.h.c.l, g.e.a.h.c.f
    public String a(String str, String str2) {
        if (!this.f19469f) {
            String a = a(str);
            return a != null ? a : str2;
        } else if (this.f19468e == null) {
            return str2;
        } else {
            synchronized (this.d) {
                String str3 = this.f19467c.get(str);
                if (str3 != null) {
                    return str3;
                }
                String a2 = a(str);
                if (a2 == null) {
                    return str2;
                }
                String a3 = this.f19468e.a(a2, str2);
                this.f19467c.put(str, a3);
                return a3;
            }
        }
    }

    public String toString() {
        return "SecurityResourcesReader{mKey=, encrypt=" + this.f19469f + '}';
    }
}
