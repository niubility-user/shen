package m.a.c;

import java.util.Comparator;

/* loaded from: classes11.dex */
class b implements Comparator<a> {

    /* renamed from: g  reason: collision with root package name */
    private static final Comparator<a> f20302g = new b();

    private b() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Comparator<a> b() {
        return f20302g;
    }

    @Override // java.util.Comparator
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compare(a aVar, a aVar2) {
        boolean a = aVar.a();
        if (a == aVar2.a()) {
            return aVar.e().compareTo(aVar2.e());
        }
        return a ? -1 : 1;
    }
}
