package g.c.a;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: classes12.dex */
public class d implements Comparable<d>, Iterable<g.c.a.b>, Serializable {
    private final g.c.a.b first;
    private final g.c.a.b last;

    /* loaded from: classes12.dex */
    private class b implements Iterator<e> {

        /* renamed from: g */
        private g.c.a.b f19394g;

        /* renamed from: h */
        private e f19395h;

        private b() {
            d.this = r1;
            this.f19394g = r1.first;
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public e next() {
            if (hasNext()) {
                int i2 = 0;
                while (i2 < 128 && !this.f19394g.setBit(i2).equals(this.f19394g) && this.f19394g.maximumAddressWithNetworkMask(g.fromPrefixLength(127 - i2)).compareTo(d.this.last) <= 0) {
                    i2++;
                }
                e fromAddressAndMask = e.fromAddressAndMask(this.f19394g, g.fromPrefixLength(128 - i2));
                this.f19395h = fromAddressAndMask;
                if (fromAddressAndMask.getLast().compareTo(d.this.last) < 0) {
                    this.f19394g = this.f19395h.getLast().add(1);
                } else {
                    this.f19394g = null;
                }
                return this.f19395h;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            g.c.a.b bVar = this.f19394g;
            return bVar != null && bVar.compareTo(d.this.last) <= 0;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("This iterator provides read only access");
        }
    }

    /* loaded from: classes12.dex */
    private final class c implements Iterator<g.c.a.b> {

        /* renamed from: g */
        private g.c.a.b f19397g;

        private c() {
            d.this = r1;
            this.f19397g = r1.first;
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public g.c.a.b next() {
            if (hasNext()) {
                g.c.a.b bVar = this.f19397g;
                this.f19397g = bVar.add(1);
                return bVar;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f19397g.compareTo(d.this.last) <= 0;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("This iterator provides read only access");
        }
    }

    public d(g.c.a.b bVar, g.c.a.b bVar2) {
        if (bVar.compareTo(bVar2) <= 0) {
            this.first = bVar;
            this.last = bVar2;
            return;
        }
        throw new IllegalArgumentException("Cannot create ip address range with last address < first address");
    }

    public static d fromFirstAndLast(g.c.a.b bVar, g.c.a.b bVar2) {
        return new d(bVar, bVar2);
    }

    public boolean contains(g.c.a.b bVar) {
        return this.first.compareTo(bVar) <= 0 && this.last.compareTo(bVar) >= 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            g.c.a.b bVar = this.first;
            if (bVar == null ? dVar.first == null : bVar.equals(dVar.first)) {
                g.c.a.b bVar2 = this.last;
                g.c.a.b bVar3 = dVar.last;
                return bVar2 == null ? bVar3 == null : bVar2.equals(bVar3);
            }
            return false;
        }
        return false;
    }

    public d extend(g.c.a.b bVar) {
        if (bVar.compareTo(this.first) < 0) {
            return fromFirstAndLast(bVar, this.last);
        }
        return bVar.compareTo(this.last) > 0 ? fromFirstAndLast(this.first, bVar) : this;
    }

    public g.c.a.b getFirst() {
        return this.first;
    }

    public g.c.a.b getLast() {
        return this.last;
    }

    public int hashCode() {
        g.c.a.b bVar = this.first;
        int hashCode = (bVar != null ? bVar.hashCode() : 0) * 31;
        g.c.a.b bVar2 = this.last;
        return hashCode + (bVar2 != null ? bVar2.hashCode() : 0);
    }

    @Override // java.lang.Iterable
    public Iterator<g.c.a.b> iterator() {
        return new c();
    }

    public boolean overlaps(d dVar) {
        return contains(dVar.first) || contains(dVar.last) || dVar.contains(this.first) || dVar.contains(this.last);
    }

    public List<d> remove(g.c.a.b bVar) {
        if (bVar != null) {
            if (!contains(bVar)) {
                return Collections.singletonList(this);
            }
            if (bVar.equals(this.first) && bVar.equals(this.last)) {
                return Collections.emptyList();
            }
            if (bVar.equals(this.first)) {
                return Collections.singletonList(fromFirstAndLast(this.first.add(1), this.last));
            }
            if (bVar.equals(this.last)) {
                return Collections.singletonList(fromFirstAndLast(this.first, this.last.subtract(1)));
            }
            return Arrays.asList(fromFirstAndLast(this.first, bVar.subtract(1)), fromFirstAndLast(bVar.add(1), this.last));
        }
        throw new IllegalArgumentException("invalid address [null]");
    }

    public BigInteger size() {
        return new BigInteger(1, this.last.toByteArray()).subtract(new BigInteger(1, this.first.toByteArray())).add(BigInteger.ONE);
    }

    public String toLongString() {
        return this.first.toLongString() + " - " + this.last.toLongString();
    }

    public String toString() {
        return this.first.toString() + " - " + this.last.toString();
    }

    public Iterator<e> toSubnets() {
        return new b();
    }

    @Override // java.lang.Comparable
    public int compareTo(d dVar) {
        if (!this.first.equals(dVar.first)) {
            return this.first.compareTo(dVar.first);
        }
        return this.last.compareTo(dVar.last);
    }

    public boolean contains(d dVar) {
        return contains(dVar.first) && contains(dVar.last);
    }

    public List<d> remove(e eVar) {
        if (eVar != null) {
            if (!contains(eVar)) {
                return Collections.singletonList(this);
            }
            if (equals(eVar)) {
                return Collections.emptyList();
            }
            if (this.first.equals(eVar.getFirst())) {
                return Collections.singletonList(fromFirstAndLast(eVar.getLast().add(1), this.last));
            }
            if (this.last.equals(eVar.getLast())) {
                return Collections.singletonList(fromFirstAndLast(this.first, eVar.getFirst().subtract(1)));
            }
            return Arrays.asList(fromFirstAndLast(this.first, eVar.getFirst().subtract(1)), fromFirstAndLast(eVar.getLast().add(1), this.last));
        }
        throw new IllegalArgumentException("invalid network [null]");
    }
}
