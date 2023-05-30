package k.a.a.h;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.regex.Matcher;
import k.a.a.e.a.k;
import k.a.a.f.h;
import k.a.a.f.i;
import k.a.a.f.n;
import k.a.a.g.a;
import k.a.a.h.d;
import k.a.a.i.f;
import k.a.a.i.g;

/* loaded from: classes11.dex */
public abstract class b<T> extends d<T> {
    private n d;

    /* renamed from: e  reason: collision with root package name */
    private byte[] f20278e;

    public b(n nVar, d.a aVar) {
        super(aVar);
        this.f20278e = new byte[4096];
        this.d = nVar;
    }

    private void i(File file) throws k.a.a.c.a {
        if (file.getParentFile().exists() || file.getParentFile().mkdirs()) {
            return;
        }
        throw new k.a.a.c.a("Unable to create parent directories: " + file.getParentFile());
    }

    private void j(k kVar, h hVar, File file, k.a.a.g.a aVar) throws IOException {
        String str = new String(p(kVar, hVar, aVar));
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new k.a.a.c.a("Could not create parent directories");
        }
        try {
            Files.createSymbolicLink(file.toPath(), Paths.get(str, new String[0]), new FileAttribute[0]);
            f.a(hVar, file);
        } catch (NoSuchMethodError unused) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(str.getBytes());
            } catch (Throwable th) {
                try {
                    throw th;
                } finally {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable unused2) {
                    }
                }
            }
        }
    }

    private File k(h hVar, String str, String str2) {
        if (!g.f(str2)) {
            str2 = m(hVar.j());
        }
        return new File(str + k.a.a.i.d.a + str2);
    }

    private String m(String str) {
        return str.replaceAll("[/\\\\]", Matcher.quoteReplacement(k.a.a.i.d.a));
    }

    private boolean o(h hVar) {
        byte[] M = hVar.M();
        if (M == null || M.length < 4) {
            return false;
        }
        return k.a.a.i.b.a(M[3], 5);
    }

    private byte[] p(k kVar, h hVar, k.a.a.g.a aVar) throws IOException {
        int m2 = (int) hVar.m();
        byte[] bArr = new byte[m2];
        if (kVar.read(bArr) == m2) {
            aVar.l(m2);
            return bArr;
        }
        throw new k.a.a.c.a("Could not read complete entry");
    }

    private void q(k kVar, h hVar, File file, k.a.a.g.a aVar) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while (true) {
                int read = kVar.read(this.f20278e);
                if (read != -1) {
                    fileOutputStream.write(this.f20278e, 0, read);
                    aVar.l(read);
                    h();
                } else {
                    fileOutputStream.close();
                    f.a(hVar, file);
                    return;
                }
            }
        } catch (Exception e2) {
            if (file.exists()) {
                file.delete();
            }
            throw e2;
        }
    }

    private void r(k kVar, h hVar) throws IOException {
        if (!k.a.a.i.b.a(hVar.k()[0], 6)) {
            i j2 = kVar.j(hVar);
            if (j2 != null) {
                if (!hVar.j().equals(j2.j())) {
                    throw new k.a.a.c.a("File header and local file header mismatch");
                }
                return;
            }
            throw new k.a.a.c.a("Could not read corresponding local file header for file header: " + hVar.j());
        }
        throw new k.a.a.c.a("Entry with name " + hVar.j() + " is encrypted with Strong Encryption. Zip4j does not support Strong Encryption, as this is patented.");
    }

    @Override // k.a.a.h.d
    protected a.c d() {
        return a.c.EXTRACT_ENTRY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void l(k kVar, h hVar, String str, String str2, k.a.a.g.a aVar) throws IOException {
        String str3 = k.a.a.i.d.a;
        if (!str.endsWith(str3)) {
            str = str + str3;
        }
        File k2 = k(hVar, str, str2);
        aVar.h(k2.getAbsolutePath());
        if (k2.getCanonicalPath().startsWith(new File(str).getCanonicalPath() + File.separator)) {
            r(kVar, hVar);
            if (hVar.p()) {
                if (k2.exists() || k2.mkdirs()) {
                    return;
                }
                throw new k.a.a.c.a("Could not create directory: " + k2);
            } else if (o(hVar)) {
                j(kVar, hVar, k2, aVar);
                return;
            } else {
                i(k2);
                q(kVar, hVar, k2, aVar);
                return;
            }
        }
        throw new k.a.a.c.a("illegal file name that breaks out of the target directory: " + hVar.j());
    }

    public n n() {
        return this.d;
    }
}
