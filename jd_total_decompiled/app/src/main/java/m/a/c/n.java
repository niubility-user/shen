package m.a.c;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.CertificateException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import sun.misc.HexDumpEncoder;

/* loaded from: classes11.dex */
public class n implements l<e0> {

    /* renamed from: j */
    private static final m.a.b.c f20384j = m.a.b.c.b(j1.NAME);

    /* renamed from: k */
    private static Class[] f20385k = {Boolean.class, Object.class};

    /* renamed from: g */
    private Hashtable<String, e0> f20386g = new Hashtable<>();

    /* renamed from: h */
    private boolean f20387h = false;

    /* renamed from: i */
    private Map<String, e0> f20388i;

    public n() {
    }

    private void h(m.a.b.g gVar) throws IOException {
        for (m.a.b.i iVar : gVar.m(5)) {
            i(new e0(iVar));
        }
    }

    private void i(e0 e0Var) throws IOException {
        try {
            Class b = p0.b(e0Var.c());
            if (b == null) {
                if (e0Var.e()) {
                    this.f20387h = true;
                }
                if (this.f20386g.put(e0Var.c().toString(), e0Var) != null) {
                    throw new IOException("Duplicate extensions not allowed");
                }
                return;
            }
            l lVar = (l) b.getConstructor(f20385k).newInstance(Boolean.valueOf(e0Var.e()), e0Var.d());
            if (this.f20386g.put(lVar.getName(), (e0) lVar) != null) {
                throw new IOException("Duplicate extensions not allowed");
            }
        } catch (IOException e2) {
            throw e2;
        } catch (InvocationTargetException e3) {
            Throwable targetException = e3.getTargetException();
            if (!e0Var.e()) {
                if (this.f20388i == null) {
                    this.f20388i = new HashMap();
                }
                this.f20388i.put(e0Var.c().toString(), new e1(e0Var, targetException));
                m.a.b.c cVar = f20384j;
                if (cVar != null) {
                    cVar.f("Error parsing extension: " + e0Var);
                    targetException.printStackTrace();
                    System.err.println(new HexDumpEncoder().encodeBuffer(e0Var.d()));
                }
            } else if (targetException instanceof IOException) {
                throw ((IOException) targetException);
            } else {
                throw ((IOException) new IOException(targetException.toString()).initCause(targetException));
            }
        } catch (Exception e4) {
            throw ((IOException) new IOException(e4.toString()).initCause(e4));
        }
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws CertificateException, IOException {
        c(outputStream, false);
    }

    public void b(String str) throws IOException {
        if (this.f20386g.get(str) != null) {
            this.f20386g.remove(str);
            return;
        }
        throw new IOException("No extension found with name " + str);
    }

    public void c(OutputStream outputStream, boolean z) throws CertificateException, IOException {
        m.a.b.h hVar = new m.a.b.h();
        Object[] array = this.f20386g.values().toArray();
        for (int i2 = 0; i2 < array.length; i2++) {
            if (array[i2] instanceof l) {
                ((l) array[i2]).a(hVar);
            } else if (array[i2] instanceof e0) {
                ((e0) array[i2]).b(hVar);
            } else {
                throw new CertificateException("Illegal extension object");
            }
        }
        m.a.b.h hVar2 = new m.a.b.h();
        hVar2.y((byte) 48, hVar);
        if (!z) {
            m.a.b.h hVar3 = new m.a.b.h();
            hVar3.y(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) 3), hVar2);
            hVar2 = hVar3;
        }
        outputStream.write(hVar2.toByteArray());
    }

    public Object d(String str) throws IOException {
        e0 e0Var = this.f20386g.get(str);
        if (e0Var != null) {
            return e0Var;
        }
        throw new IOException("No extension found with name " + str);
    }

    public Collection<e0> e() {
        return this.f20386g.values();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof n) {
            n nVar = (n) obj;
            Object[] array = nVar.e().toArray();
            int length = array.length;
            if (length != this.f20386g.size()) {
                return false;
            }
            String str = null;
            for (int i2 = 0; i2 < length; i2++) {
                if (array[i2] instanceof l) {
                    str = ((l) array[i2]).getName();
                }
                e0 e0Var = (e0) array[i2];
                if (str == null) {
                    str = e0Var.c().toString();
                }
                e0 e0Var2 = this.f20386g.get(str);
                if (e0Var2 == null || !e0Var2.equals(e0Var)) {
                    return false;
                }
            }
            return f().equals(nVar.f());
        }
        return false;
    }

    public Map<String, e0> f() {
        Map<String, e0> map = this.f20388i;
        return map == null ? Collections.emptyMap() : map;
    }

    public boolean g() {
        return this.f20387h;
    }

    @Override // m.a.c.l
    public String getName() {
        return "extensions";
    }

    public int hashCode() {
        return this.f20386g.hashCode() + f().hashCode();
    }

    public void j(String str, Object obj) throws IOException {
        if (obj instanceof e0) {
            this.f20386g.put(str, (e0) obj);
            return;
        }
        throw new IOException("Unknown extension type.");
    }

    public String toString() {
        return this.f20386g.toString();
    }

    public n(m.a.b.g gVar) throws IOException {
        h(gVar);
    }
}
