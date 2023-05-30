package k.a.a.i;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import k.a.a.e.a.m;
import k.a.a.f.h;
import k.a.a.f.n;

/* loaded from: classes11.dex */
public class f {
    public static void a(h hVar, File file) {
        try {
            Path path = file.toPath();
            c.n(path, hVar.M());
            c.o(path, hVar.l());
        } catch (NoSuchMethodError unused) {
            c.p(file, hVar.l());
        }
    }

    public static k.a.a.e.a.h b(n nVar) throws IOException {
        if (nVar.e().getName().endsWith(".zip.001")) {
            return new k.a.a.e.a.f(nVar.e(), true, nVar.b().b());
        }
        return new m(nVar.e(), nVar.f(), nVar.b().b());
    }
}
