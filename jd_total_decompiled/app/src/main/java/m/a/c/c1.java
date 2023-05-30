package m.a.c;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/* loaded from: classes11.dex */
public class c1 implements g0 {

    /* renamed from: g  reason: collision with root package name */
    private URI f20312g;

    /* renamed from: h  reason: collision with root package name */
    private String f20313h;

    /* renamed from: i  reason: collision with root package name */
    private a0 f20314i;

    /* renamed from: j  reason: collision with root package name */
    private k0 f20315j;

    public c1(m.a.b.i iVar) throws IOException {
        this(iVar.n());
    }

    public static c1 g(m.a.b.i iVar) throws IOException {
        a0 a0Var;
        String n2 = iVar.n();
        try {
            URI uri = new URI(n2);
            if (uri.getScheme() == null) {
                String schemeSpecificPart = uri.getSchemeSpecificPart();
                try {
                    if (schemeSpecificPart.charAt(0) == '.') {
                        a0Var = new a0(schemeSpecificPart.substring(1));
                    } else {
                        a0Var = new a0(schemeSpecificPart);
                    }
                    return new c1(uri, schemeSpecificPart, a0Var);
                } catch (IOException e2) {
                    throw ((IOException) new IOException("invalid URI name constraint:" + n2).initCause(e2));
                }
            }
            throw new IOException("invalid URI name constraint (should not include scheme):" + n2);
        } catch (URISyntaxException e3) {
            throw ((IOException) new IOException("invalid URI name constraint:" + n2).initCause(e3));
        }
    }

    @Override // m.a.c.g0
    public int a(g0 g0Var) throws UnsupportedOperationException {
        int i2 = 3;
        if (g0Var != null && g0Var.getType() == 6) {
            c1 c1Var = (c1) g0Var;
            String c2 = c1Var.c();
            if (c2.equalsIgnoreCase(this.f20313h)) {
                return 0;
            }
            Object d = c1Var.d();
            if (this.f20314i == null || !(d instanceof a0)) {
                return 3;
            }
            boolean z = this.f20313h.charAt(0) == '.';
            boolean z2 = c2.charAt(0) == '.';
            int a = this.f20314i.a((a0) d);
            if (z || z2 || (a != 2 && a != 1)) {
                i2 = a;
            }
            return (z == z2 || i2 != 0) ? i2 : z ? 2 : 1;
        }
        return -1;
    }

    @Override // m.a.c.g0
    public void b(m.a.b.h hVar) throws IOException {
        hVar.j(this.f20312g.toASCIIString());
    }

    public String c() {
        return this.f20313h;
    }

    public Object d() {
        k0 k0Var = this.f20315j;
        return k0Var != null ? k0Var : this.f20314i;
    }

    public String e() {
        return this.f20312g.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c1) {
            return this.f20312g.equals(((c1) obj).f());
        }
        return false;
    }

    public URI f() {
        return this.f20312g;
    }

    @Override // m.a.c.g0
    public int getType() {
        return 6;
    }

    public int hashCode() {
        return this.f20312g.hashCode();
    }

    public String toString() {
        return "URIName: " + this.f20312g.toString();
    }

    public c1(String str) throws IOException {
        try {
            URI uri = new URI(str);
            this.f20312g = uri;
            if (uri.getScheme() != null) {
                String host = this.f20312g.getHost();
                this.f20313h = host;
                if (host != null) {
                    if (host.charAt(0) == '[') {
                        String str2 = this.f20313h;
                        try {
                            this.f20315j = new k0(str2.substring(1, str2.length() - 1));
                            return;
                        } catch (IOException unused) {
                            throw new IOException("invalid URI name (host portion is not a valid IPv6 address):" + str);
                        }
                    }
                    try {
                        try {
                            this.f20314i = new a0(this.f20313h);
                            return;
                        } catch (Exception unused2) {
                            throw new IOException("invalid URI name (host portion is not a valid DNS name, IPv4 address, or IPv6 address):" + str);
                        }
                    } catch (IOException unused3) {
                        this.f20315j = new k0(this.f20313h);
                        return;
                    }
                }
                return;
            }
            throw new IOException("URI name must include scheme:" + str);
        } catch (URISyntaxException e2) {
            throw ((IOException) new IOException("invalid URI name:" + str).initCause(e2));
        }
    }

    c1(URI uri, String str, a0 a0Var) {
        this.f20312g = uri;
        this.f20313h = str;
        this.f20314i = a0Var;
    }
}
