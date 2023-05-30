package m.a.c;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.IOException;

/* loaded from: classes11.dex */
public class y0 implements g0 {

    /* renamed from: g  reason: collision with root package name */
    private String f20427g;

    public y0(m.a.b.i iVar) throws IOException {
        String n2 = iVar.n();
        this.f20427g = n2;
        d(n2);
    }

    @Override // m.a.c.g0
    public int a(g0 g0Var) throws UnsupportedOperationException {
        if (g0Var != null && g0Var.getType() == 1) {
            String lowerCase = ((y0) g0Var).c().toLowerCase();
            String lowerCase2 = this.f20427g.toLowerCase();
            if (lowerCase.equals(lowerCase2)) {
                return 0;
            }
            if (lowerCase2.endsWith(lowerCase)) {
                if (lowerCase.indexOf(64) == -1 && (lowerCase.startsWith(OrderISVUtil.MONEY_DECIMAL) || lowerCase2.charAt(lowerCase2.lastIndexOf(lowerCase) - 1) == '@')) {
                    return 2;
                }
            } else if (lowerCase.endsWith(lowerCase2) && lowerCase2.indexOf(64) == -1 && (lowerCase2.startsWith(OrderISVUtil.MONEY_DECIMAL) || lowerCase.charAt(lowerCase.lastIndexOf(lowerCase2) - 1) == '@')) {
                return 1;
            }
            return 3;
        }
        return -1;
    }

    @Override // m.a.c.g0
    public void b(m.a.b.h hVar) throws IOException {
        hVar.j(this.f20427g);
    }

    public String c() {
        return this.f20427g;
    }

    public void d(String str) throws IOException {
        if (str != null && str.length() != 0) {
            String substring = str.substring(str.indexOf(64) + 1);
            if (substring.length() != 0) {
                if (substring.startsWith(OrderISVUtil.MONEY_DECIMAL) && substring.length() == 1) {
                    throw new IOException("RFC822Name domain may not be just .");
                }
                return;
            }
            throw new IOException("RFC822Name may not end with @");
        }
        throw new IOException("RFC822Name may not be null or empty");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof y0) {
            return this.f20427g.equalsIgnoreCase(((y0) obj).f20427g);
        }
        return false;
    }

    @Override // m.a.c.g0
    public int getType() {
        return 1;
    }

    public int hashCode() {
        return this.f20427g.toUpperCase().hashCode();
    }

    public String toString() {
        return "RFC822Name: " + this.f20427g;
    }
}
