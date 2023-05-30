package m.a.c;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class j0 implements Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private final List<i0> f20369g;

    public j0() {
        this.f20369g = new ArrayList();
    }

    public void a(m.a.b.h hVar) throws IOException {
        m.a.b.h hVar2 = new m.a.b.h();
        int c2 = c();
        for (int i2 = 0; i2 < c2; i2++) {
            b(i2).a(hVar2);
        }
        hVar.y((byte) 48, hVar2);
    }

    public i0 b(int i2) {
        return this.f20369g.get(i2);
    }

    public int c() {
        return this.f20369g.size();
    }

    public Object clone() {
        return new j0(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j0) {
            return this.f20369g.equals(((j0) obj).f20369g);
        }
        return false;
    }

    public int hashCode() {
        return this.f20369g.hashCode();
    }

    public String toString() {
        return "   GeneralSubtrees:\n" + this.f20369g.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
    }

    private j0(j0 j0Var) {
        this.f20369g = new ArrayList(j0Var.f20369g);
    }
}
