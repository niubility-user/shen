package m.a.c;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

/* loaded from: classes11.dex */
public class k {

    /* renamed from: c  reason: collision with root package name */
    private static final Class[] f20370c = {Boolean.class, Object.class};
    private Hashtable<String, e0> a = new Hashtable<>();
    private boolean b = false;

    public k() {
    }

    private void f(m.a.b.g gVar) throws CRLException {
        try {
            byte t = (byte) gVar.t();
            if ((t & 192) == 128 && (t & 31) == 0) {
                gVar = gVar.e().f20295c;
            }
            for (m.a.b.i iVar : gVar.m(5)) {
                g(new e0(iVar));
            }
        } catch (IOException e2) {
            throw new CRLException("Parsing error: " + e2.toString());
        }
    }

    private void g(e0 e0Var) throws CRLException {
        try {
            Class b = p0.b(e0Var.c());
            if (b == null) {
                if (e0Var.e()) {
                    this.b = true;
                }
                if (this.a.put(e0Var.c().toString(), e0Var) != null) {
                    throw new CRLException("Duplicate extensions not allowed");
                }
                return;
            }
            l lVar = (l) b.getConstructor(f20370c).newInstance(Boolean.valueOf(e0Var.e()), e0Var.d());
            if (this.a.put(lVar.getName(), (e0) lVar) != null) {
                throw new CRLException("Duplicate extensions not allowed");
            }
        } catch (InvocationTargetException e2) {
            throw new CRLException(e2.getTargetException().getMessage());
        } catch (Exception e3) {
            throw new CRLException(e3.toString());
        }
    }

    public void a(OutputStream outputStream, boolean z) throws CRLException {
        try {
            m.a.b.h hVar = new m.a.b.h();
            Object[] array = this.a.values().toArray();
            for (int i2 = 0; i2 < array.length; i2++) {
                if (array[i2] instanceof l) {
                    ((l) array[i2]).a(hVar);
                } else if (array[i2] instanceof e0) {
                    ((e0) array[i2]).b(hVar);
                } else {
                    throw new CRLException("Illegal extension object");
                }
            }
            m.a.b.h hVar2 = new m.a.b.h();
            hVar2.y((byte) 48, hVar);
            m.a.b.h hVar3 = new m.a.b.h();
            if (z) {
                hVar3.y(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) 0), hVar2);
                hVar2 = hVar3;
            }
            outputStream.write(hVar2.toByteArray());
        } catch (IOException e2) {
            throw new CRLException("Encoding error: " + e2.toString());
        } catch (CertificateException e3) {
            throw new CRLException("Encoding error: " + e3.toString());
        }
    }

    public e0 b(String str) {
        if (new g1(str).a().equalsIgnoreCase(j1.NAME)) {
            str = str.substring(str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL) + 1);
        }
        return this.a.get(str);
    }

    public Collection<e0> c() {
        return this.a.values();
    }

    public Enumeration<e0> d() {
        return this.a.elements();
    }

    public boolean e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        Object[] array;
        int length;
        if (this == obj) {
            return true;
        }
        if ((obj instanceof k) && (length = (array = ((k) obj).c().toArray()).length) == this.a.size()) {
            String str = null;
            for (int i2 = 0; i2 < length; i2++) {
                if (array[i2] instanceof l) {
                    str = ((l) array[i2]).getName();
                }
                e0 e0Var = (e0) array[i2];
                if (str == null) {
                    str = e0Var.c().toString();
                }
                e0 e0Var2 = this.a.get(str);
                if (e0Var2 == null || !e0Var2.equals(e0Var)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }

    public k(m.a.b.g gVar) throws CRLException {
        f(gVar);
    }
}
