package g.c.a;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes12.dex */
public final class e extends d {
    private final b address;
    private final g networkMask;
    public static final e MULTICAST_NETWORK = fromString("ff00::/8");
    public static final e SITE_LOCAL_NETWORK = fromString("fec0::/48");
    public static final e LINK_LOCAL_NETWORK = fromString("fe80::/64");

    /* loaded from: classes12.dex */
    private final class a implements Iterator<e> {

        /* renamed from: g */
        private final g f19399g;

        /* renamed from: h */
        private e f19400h;

        /* renamed from: i */
        private BigInteger f19401i;

        public a(g gVar) {
            e.this = r3;
            this.f19399g = gVar;
            this.f19401i = BigInteger.ONE.shiftLeft(128 - gVar.asPrefixLength());
            this.f19400h = e.fromAddressAndMask(r3.address, gVar);
        }

        private e a(e eVar) {
            return e.fromAddressAndMask(b.fromBigInteger(eVar.address.toBigInteger().add(this.f19401i)), this.f19399g);
        }

        @Override // java.util.Iterator
        /* renamed from: b */
        public e next() {
            if (hasNext()) {
                e eVar = this.f19400h;
                this.f19400h = a(eVar);
                return eVar;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f19400h.getLast().compareTo(e.this.getLast()) <= 0;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("This iterator provides read only access");
        }
    }

    private e(b bVar, g gVar) {
        super(bVar.maskWithNetworkMask(gVar), bVar.maximumAddressWithNetworkMask(gVar));
        this.address = bVar.maskWithNetworkMask(gVar);
        this.networkMask = gVar;
    }

    private static String d(String str) {
        return str.substring(0, str.indexOf(47));
    }

    private static int e(String str) {
        try {
            return Integer.parseInt(str.substring(str.indexOf(47) + 1));
        } catch (NumberFormatException unused) {
            throw new IllegalArgumentException("Prefix length should be a positive integer");
        }
    }

    public static e fromAddressAndMask(b bVar, g gVar) {
        return new e(bVar, gVar);
    }

    public static e fromString(String str) {
        if (str.indexOf(47) != -1) {
            return fromAddressAndMask(b.fromString(d(str)), new g(e(str)));
        }
        throw new IllegalArgumentException("Expected format is network-address/prefix-length");
    }

    public static e fromTwoAddresses(b bVar, b bVar2) {
        g fromPrefixLength = g.fromPrefixLength(f.b(bVar, bVar2));
        return new e(bVar.maskWithNetworkMask(fromPrefixLength), fromPrefixLength);
    }

    @Override // g.c.a.d
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && e.class == obj.getClass() && super.equals(obj)) {
            e eVar = (e) obj;
            b bVar = this.address;
            if (bVar == null ? eVar.address == null : bVar.equals(eVar.address)) {
                g gVar = this.networkMask;
                g gVar2 = eVar.networkMask;
                return gVar == null ? gVar2 == null : gVar.equals(gVar2);
            }
            return false;
        }
        return false;
    }

    public g getNetmask() {
        return this.networkMask;
    }

    @Override // g.c.a.d
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        b bVar = this.address;
        int hashCode2 = (hashCode + (bVar != null ? bVar.hashCode() : 0)) * 31;
        g gVar = this.networkMask;
        return hashCode2 + (gVar != null ? gVar.hashCode() : 0);
    }

    public Iterator<e> split(g gVar) {
        if (gVar.asPrefixLength() >= getNetmask().asPrefixLength()) {
            return new a(gVar);
        }
        throw new IllegalArgumentException(String.format("Can not split a network of size %s in subnets of larger size %s", Integer.valueOf(getNetmask().asPrefixLength()), Integer.valueOf(gVar.asPrefixLength())));
    }

    @Override // g.c.a.d
    public String toLongString() {
        return this.address.toLongString() + "/" + this.networkMask.asPrefixLength();
    }

    @Override // g.c.a.d
    public String toString() {
        return this.address.toString() + "/" + this.networkMask.asPrefixLength();
    }
}
