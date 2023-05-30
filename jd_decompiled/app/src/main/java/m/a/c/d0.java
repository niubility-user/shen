package m.a.c;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/* loaded from: classes11.dex */
public class d0 extends e0 implements l<String> {

    /* renamed from: k  reason: collision with root package name */
    private static final Map<m.a.b.j, String> f20320k;

    /* renamed from: l  reason: collision with root package name */
    private static final int[] f20321l;

    /* renamed from: m  reason: collision with root package name */
    private static final int[] f20322m;

    /* renamed from: n  reason: collision with root package name */
    private static final int[] f20323n;
    private static final int[] o;
    private static final int[] p;
    private static final int[] q;
    private static final int[] r;
    private static final int[] s;
    private static final int[] t;
    private static final int[] u;

    /* renamed from: j  reason: collision with root package name */
    private Vector<m.a.b.j> f20324j;

    static {
        HashMap hashMap = new HashMap();
        f20320k = hashMap;
        int[] iArr = {2, 5, 29, 37, 0};
        f20321l = iArr;
        int[] iArr2 = {1, 3, 6, 1, 5, 5, 7, 3, 1};
        f20322m = iArr2;
        int[] iArr3 = {1, 3, 6, 1, 5, 5, 7, 3, 2};
        f20323n = iArr3;
        int[] iArr4 = {1, 3, 6, 1, 5, 5, 7, 3, 3};
        o = iArr4;
        int[] iArr5 = {1, 3, 6, 1, 5, 5, 7, 3, 4};
        p = iArr5;
        int[] iArr6 = {1, 3, 6, 1, 5, 5, 7, 3, 5};
        q = iArr6;
        int[] iArr7 = {1, 3, 6, 1, 5, 5, 7, 3, 6};
        r = iArr7;
        int[] iArr8 = {1, 3, 6, 1, 5, 5, 7, 3, 7};
        s = iArr8;
        int[] iArr9 = {1, 3, 6, 1, 5, 5, 7, 3, 8};
        t = iArr9;
        int[] iArr10 = {1, 3, 6, 1, 5, 5, 7, 3, 9};
        u = iArr10;
        hashMap.put(m.a.b.j.newInternal(iArr), "anyExtendedKeyUsage");
        hashMap.put(m.a.b.j.newInternal(iArr2), "serverAuth");
        hashMap.put(m.a.b.j.newInternal(iArr3), "clientAuth");
        hashMap.put(m.a.b.j.newInternal(iArr4), "codeSigning");
        hashMap.put(m.a.b.j.newInternal(iArr5), "emailProtection");
        hashMap.put(m.a.b.j.newInternal(iArr6), "ipsecEndSystem");
        hashMap.put(m.a.b.j.newInternal(iArr7), "ipsecTunnel");
        hashMap.put(m.a.b.j.newInternal(iArr8), "ipsecUser");
        hashMap.put(m.a.b.j.newInternal(iArr9), "timeStamping");
        hashMap.put(m.a.b.j.newInternal(iArr10), "OCSPSigning");
    }

    public d0(Boolean bool, Object obj) throws IOException {
        this.f20333g = s0.P;
        this.f20334h = bool.booleanValue();
        byte[] bArr = (byte[]) obj;
        this.f20335i = bArr;
        m.a.b.i iVar = new m.a.b.i(bArr);
        if (iVar.a == 48) {
            this.f20324j = new Vector<>();
            while (iVar.f20295c.a() != 0) {
                this.f20324j.addElement(iVar.f20295c.e().p());
            }
            return;
        }
        throw new IOException("Invalid encoding for ExtendedKeyUsageExtension.");
    }

    private void f() throws IOException {
        Vector<m.a.b.j> vector = this.f20324j;
        if (vector != null && !vector.isEmpty()) {
            m.a.b.h hVar = new m.a.b.h();
            m.a.b.h hVar2 = new m.a.b.h();
            for (int i2 = 0; i2 < this.f20324j.size(); i2++) {
                hVar2.p(this.f20324j.elementAt(i2));
            }
            hVar.y((byte) 48, hVar2);
            this.f20335i = hVar.toByteArray();
            return;
        }
        this.f20335i = null;
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = s0.P;
            this.f20334h = false;
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    public List<String> g() {
        ArrayList arrayList = new ArrayList(this.f20324j.size());
        Iterator<m.a.b.j> it = this.f20324j.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toString());
        }
        return arrayList;
    }

    @Override // m.a.c.l
    public String getName() {
        return "ExtendedKeyUsage";
    }

    @Override // m.a.c.e0
    public String toString() {
        String str;
        Vector<m.a.b.j> vector = this.f20324j;
        if (vector == null) {
            return "";
        }
        boolean z = true;
        Iterator<m.a.b.j> it = vector.iterator();
        String str2 = "  ";
        while (it.hasNext()) {
            m.a.b.j next = it.next();
            if (!z) {
                str2 = str2 + "\n  ";
            }
            String str3 = f20320k.get(next);
            if (str3 != null) {
                str = str2 + str3;
            } else {
                str = str2 + next.toString();
            }
            str2 = str;
            z = false;
        }
        return super.toString() + "ExtendedKeyUsages [\n" + str2 + "\n]\n";
    }
}
