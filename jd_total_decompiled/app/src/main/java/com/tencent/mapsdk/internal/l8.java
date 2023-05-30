package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.mapsdk.internal.j8;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public final class l8 extends j8 {
    private ArrayList<j8> b = new ArrayList<>();

    /* renamed from: c  reason: collision with root package name */
    private HashMap<j8, f> f16802c = new HashMap<>();
    private ArrayList<f> d = new ArrayList<>();

    /* renamed from: e  reason: collision with root package name */
    private ArrayList<f> f16803e = new ArrayList<>();

    /* renamed from: f  reason: collision with root package name */
    private boolean f16804f = true;

    /* renamed from: g  reason: collision with root package name */
    private b f16805g = null;

    /* renamed from: h  reason: collision with root package name */
    public boolean f16806h = false;

    /* renamed from: i  reason: collision with root package name */
    private boolean f16807i = false;

    /* renamed from: j  reason: collision with root package name */
    private long f16808j = 0;

    /* renamed from: k  reason: collision with root package name */
    private y8 f16809k = null;

    /* renamed from: l  reason: collision with root package name */
    private long f16810l = -1;

    /* loaded from: classes9.dex */
    public class a extends k8 {
        public boolean a = false;
        public final /* synthetic */ ArrayList b;

        public a(ArrayList arrayList) {
            this.b = arrayList;
        }

        @Override // com.tencent.mapsdk.internal.k8, com.tencent.mapsdk.internal.j8.a
        public void b(j8 j8Var) {
            this.a = true;
        }

        @Override // com.tencent.mapsdk.internal.k8, com.tencent.mapsdk.internal.j8.a
        public void c(j8 j8Var) {
            if (this.a) {
                return;
            }
            int size = this.b.size();
            for (int i2 = 0; i2 < size; i2++) {
                f fVar = (f) this.b.get(i2);
                fVar.a.j();
                l8.this.b.add(fVar.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b implements j8.a {
        private l8 a;

        public b(l8 l8Var) {
            this.a = l8Var;
        }

        @Override // com.tencent.mapsdk.internal.j8.a
        public void a(j8 j8Var) {
        }

        @Override // com.tencent.mapsdk.internal.j8.a
        public void b(j8 j8Var) {
            ArrayList<j8.a> arrayList;
            l8 l8Var = l8.this;
            if (l8Var.f16806h || l8Var.b.size() != 0 || (arrayList = l8.this.a) == null) {
                return;
            }
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                l8.this.a.get(i2).b(this.a);
            }
        }

        @Override // com.tencent.mapsdk.internal.j8.a
        public void c(j8 j8Var) {
            j8Var.b(this);
            l8.this.b.remove(j8Var);
            boolean z = true;
            ((f) this.a.f16802c.get(j8Var)).f16816f = true;
            if (l8.this.f16806h) {
                return;
            }
            ArrayList arrayList = this.a.f16803e;
            int size = arrayList.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                } else if (!((f) arrayList.get(i2)).f16816f) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                ArrayList<j8.a> arrayList2 = l8.this.a;
                if (arrayList2 != null) {
                    ArrayList arrayList3 = (ArrayList) arrayList2.clone();
                    int size2 = arrayList3.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        ((j8.a) arrayList3.get(i3)).c(this.a);
                    }
                }
                this.a.f16807i = false;
            }
        }

        @Override // com.tencent.mapsdk.internal.j8.a
        public void d(j8 j8Var) {
        }
    }

    /* loaded from: classes9.dex */
    public class c {
        private f a;

        public c(j8 j8Var) {
            f fVar = (f) l8.this.f16802c.get(j8Var);
            this.a = fVar;
            if (fVar == null) {
                this.a = new f(j8Var);
                l8.this.f16802c.put(j8Var, this.a);
                l8.this.d.add(this.a);
            }
        }

        public c a(long j2) {
            y8 a = y8.a((g8) null, 0.0d, 1.0d);
            a.a(j2);
            a(a);
            return this;
        }

        public c a(j8 j8Var) {
            f fVar = (f) l8.this.f16802c.get(j8Var);
            if (fVar == null) {
                fVar = new f(j8Var);
                l8.this.f16802c.put(j8Var, fVar);
                l8.this.d.add(fVar);
            }
            this.a.a(new d(fVar, 1));
            return this;
        }

        public c b(j8 j8Var) {
            f fVar = (f) l8.this.f16802c.get(j8Var);
            if (fVar == null) {
                fVar = new f(j8Var);
                l8.this.f16802c.put(j8Var, fVar);
                l8.this.d.add(fVar);
            }
            fVar.a(new d(this.a, 1));
            return this;
        }

        public c c(j8 j8Var) {
            f fVar = (f) l8.this.f16802c.get(j8Var);
            if (fVar == null) {
                fVar = new f(j8Var);
                l8.this.f16802c.put(j8Var, fVar);
                l8.this.d.add(fVar);
            }
            fVar.a(new d(this.a, 0));
            return this;
        }
    }

    /* loaded from: classes9.dex */
    public static class d {

        /* renamed from: c  reason: collision with root package name */
        public static final int f16812c = 0;
        public static final int d = 1;
        public f a;
        public int b;

        public d(f fVar, int i2) {
            this.a = fVar;
            this.b = i2;
        }
    }

    /* loaded from: classes9.dex */
    public static class e implements j8.a {
        private l8 a;
        private f b;

        /* renamed from: c  reason: collision with root package name */
        private int f16813c;

        public e(l8 l8Var, f fVar, int i2) {
            this.a = l8Var;
            this.b = fVar;
            this.f16813c = i2;
        }

        private void e(j8 j8Var) {
            if (this.a.f16806h) {
                return;
            }
            d dVar = null;
            int size = this.b.f16814c.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                d dVar2 = this.b.f16814c.get(i2);
                if (dVar2.b == this.f16813c && dVar2.a.a == j8Var) {
                    j8Var.b(this);
                    dVar = dVar2;
                    break;
                }
                i2++;
            }
            this.b.f16814c.remove(dVar);
            if (this.b.f16814c.size() == 0) {
                this.b.a.j();
                this.a.b.add(this.b.a);
            }
        }

        @Override // com.tencent.mapsdk.internal.j8.a
        public void a(j8 j8Var) {
        }

        @Override // com.tencent.mapsdk.internal.j8.a
        public void b(j8 j8Var) {
        }

        @Override // com.tencent.mapsdk.internal.j8.a
        public void c(j8 j8Var) {
            if (this.f16813c == 1) {
                e(j8Var);
            }
        }

        @Override // com.tencent.mapsdk.internal.j8.a
        public void d(j8 j8Var) {
            if (this.f16813c == 0) {
                e(j8Var);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class f implements Cloneable {
        public j8 a;
        public ArrayList<d> b = null;

        /* renamed from: c  reason: collision with root package name */
        public ArrayList<d> f16814c = null;
        public ArrayList<f> d = null;

        /* renamed from: e  reason: collision with root package name */
        public ArrayList<f> f16815e = null;

        /* renamed from: f  reason: collision with root package name */
        public boolean f16816f = false;

        public f(j8 j8Var) {
            this.a = j8Var;
        }

        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public f clone() {
            try {
                f fVar = (f) super.clone();
                fVar.a = this.a.clone();
                return fVar;
            } catch (CloneNotSupportedException unused) {
                throw new AssertionError();
            }
        }

        public void a(d dVar) {
            if (this.b == null) {
                this.b = new ArrayList<>();
                this.d = new ArrayList<>();
            }
            this.b.add(dVar);
            if (!this.d.contains(dVar.a)) {
                this.d.add(dVar.a);
            }
            f fVar = dVar.a;
            if (fVar.f16815e == null) {
                fVar.f16815e = new ArrayList<>();
            }
            fVar.f16815e.add(this);
        }
    }

    private void m() {
        if (!this.f16804f) {
            int size = this.d.size();
            for (int i2 = 0; i2 < size; i2++) {
                f fVar = this.d.get(i2);
                ArrayList<d> arrayList = fVar.b;
                if (arrayList != null && arrayList.size() > 0) {
                    int size2 = fVar.b.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        d dVar = fVar.b.get(i3);
                        if (fVar.d == null) {
                            fVar.d = new ArrayList<>();
                        }
                        if (!fVar.d.contains(dVar.a)) {
                            fVar.d.add(dVar.a);
                        }
                    }
                }
                fVar.f16816f = false;
            }
            return;
        }
        this.f16803e.clear();
        ArrayList arrayList2 = new ArrayList();
        int size3 = this.d.size();
        for (int i4 = 0; i4 < size3; i4++) {
            f fVar2 = this.d.get(i4);
            ArrayList<d> arrayList3 = fVar2.b;
            if (arrayList3 == null || arrayList3.size() == 0) {
                arrayList2.add(fVar2);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        while (arrayList2.size() > 0) {
            int size4 = arrayList2.size();
            for (int i5 = 0; i5 < size4; i5++) {
                f fVar3 = (f) arrayList2.get(i5);
                this.f16803e.add(fVar3);
                ArrayList<f> arrayList5 = fVar3.f16815e;
                if (arrayList5 != null) {
                    int size5 = arrayList5.size();
                    for (int i6 = 0; i6 < size5; i6++) {
                        f fVar4 = fVar3.f16815e.get(i6);
                        fVar4.d.remove(fVar3);
                        if (fVar4.d.size() == 0) {
                            arrayList4.add(fVar4);
                        }
                    }
                }
            }
            arrayList2.clear();
            arrayList2.addAll(arrayList4);
            arrayList4.clear();
        }
        this.f16804f = false;
        if (this.f16803e.size() != this.d.size()) {
            throw new IllegalStateException("Circular dependencies cannot exist in AnimatorSet");
        }
    }

    public c a(j8 j8Var) {
        if (j8Var != null) {
            this.f16804f = true;
            return new c(j8Var);
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.j8
    public void a() {
        this.f16806h = true;
        if (h()) {
            ArrayList arrayList = null;
            ArrayList<j8.a> arrayList2 = this.a;
            if (arrayList2 != null) {
                arrayList = (ArrayList) arrayList2.clone();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((j8.a) it.next()).b(this);
                }
            }
            y8 y8Var = this.f16809k;
            if (y8Var != null && y8Var.g()) {
                this.f16809k.a();
            } else if (this.f16803e.size() > 0) {
                Iterator<f> it2 = this.f16803e.iterator();
                while (it2.hasNext()) {
                    it2.next().a.a();
                }
            }
            if (arrayList != null) {
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    ((j8.a) it3.next()).c(this);
                }
            }
            this.f16807i = false;
        }
    }

    @Override // com.tencent.mapsdk.internal.j8
    public void a(Interpolator interpolator) {
        Iterator<f> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().a.a(interpolator);
        }
    }

    public void a(Collection<j8> collection) {
        if (collection == null || collection.size() <= 0) {
            return;
        }
        this.f16804f = true;
        c cVar = null;
        for (j8 j8Var : collection) {
            if (cVar == null) {
                cVar = a(j8Var);
            } else {
                cVar.c(j8Var);
            }
        }
    }

    public void a(List<j8> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.f16804f = true;
        int i2 = 0;
        if (list.size() == 1) {
            a(list.get(0));
            return;
        }
        while (i2 < list.size() - 1) {
            i2++;
            a(list.get(i2)).b(list.get(i2));
        }
    }

    public void a(j8... j8VarArr) {
        if (j8VarArr != null) {
            this.f16804f = true;
            int i2 = 0;
            if (j8VarArr.length == 1) {
                a(j8VarArr[0]);
                return;
            }
            while (i2 < j8VarArr.length - 1) {
                i2++;
                a(j8VarArr[i2]).b(j8VarArr[i2]);
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.j8
    public void b(long j2) {
        this.f16808j = j2;
    }

    public void b(j8... j8VarArr) {
        if (j8VarArr != null) {
            this.f16804f = true;
            c a2 = a(j8VarArr[0]);
            for (int i2 = 1; i2 < j8VarArr.length; i2++) {
                a2.c(j8VarArr[i2]);
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.j8
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public l8 a(long j2) {
        if (j2 >= 0) {
            Iterator<f> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().a.a(j2);
            }
            this.f16810l = j2;
            return this;
        }
        throw new IllegalArgumentException("duration must be a value of zero or greater");
    }

    @Override // com.tencent.mapsdk.internal.j8
    public void c() {
        this.f16806h = true;
        if (h()) {
            if (this.f16803e.size() != this.d.size()) {
                m();
                Iterator<f> it = this.f16803e.iterator();
                while (it.hasNext()) {
                    f next = it.next();
                    if (this.f16805g == null) {
                        this.f16805g = new b(this);
                    }
                    next.a.a(this.f16805g);
                }
            }
            y8 y8Var = this.f16809k;
            if (y8Var != null) {
                y8Var.a();
            }
            if (this.f16803e.size() > 0) {
                Iterator<f> it2 = this.f16803e.iterator();
                while (it2.hasNext()) {
                    it2.next().a.c();
                }
            }
            ArrayList<j8.a> arrayList = this.a;
            if (arrayList != null) {
                Iterator it3 = ((ArrayList) arrayList.clone()).iterator();
                while (it3.hasNext()) {
                    ((j8.a) it3.next()).c(this);
                }
            }
            this.f16807i = false;
        }
    }

    @Override // com.tencent.mapsdk.internal.j8
    public long d() {
        return this.f16810l;
    }

    @Override // com.tencent.mapsdk.internal.j8
    public long f() {
        return this.f16808j;
    }

    @Override // com.tencent.mapsdk.internal.j8
    public boolean g() {
        Iterator<f> it = this.d.iterator();
        while (it.hasNext()) {
            if (it.next().a.g()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.j8
    public boolean h() {
        return this.f16807i;
    }

    @Override // com.tencent.mapsdk.internal.j8
    public void j() {
        this.f16806h = false;
        this.f16807i = true;
        m();
        int size = this.f16803e.size();
        for (int i2 = 0; i2 < size; i2++) {
            f fVar = this.f16803e.get(i2);
            ArrayList<j8.a> e2 = fVar.a.e();
            if (e2 != null && e2.size() > 0) {
                Iterator it = new ArrayList(e2).iterator();
                while (it.hasNext()) {
                    j8.a aVar = (j8.a) it.next();
                    if ((aVar instanceof e) || (aVar instanceof b)) {
                        fVar.a.b(aVar);
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < size; i3++) {
            f fVar2 = this.f16803e.get(i3);
            if (this.f16805g == null) {
                this.f16805g = new b(this);
            }
            ArrayList<d> arrayList2 = fVar2.b;
            if (arrayList2 == null || arrayList2.size() == 0) {
                arrayList.add(fVar2);
            } else {
                int size2 = fVar2.b.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    d dVar = fVar2.b.get(i4);
                    dVar.a.a.a(new e(this, fVar2, dVar.b));
                }
                fVar2.f16814c = (ArrayList) fVar2.b.clone();
            }
            fVar2.a.a(this.f16805g);
        }
        if (this.f16808j <= 0) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                f fVar3 = (f) it2.next();
                fVar3.a.j();
                this.b.add(fVar3.a);
            }
        } else {
            y8 a2 = y8.a((g8) null, 0.0d, 1.0d);
            this.f16809k = a2;
            a2.a(this.f16808j);
            this.f16809k.a(new a(arrayList));
            this.f16809k.j();
        }
        ArrayList<j8.a> arrayList3 = this.a;
        if (arrayList3 != null) {
            ArrayList arrayList4 = (ArrayList) arrayList3.clone();
            int size3 = arrayList4.size();
            for (int i5 = 0; i5 < size3; i5++) {
                ((j8.a) arrayList4.get(i5)).d(this);
            }
        }
        if (this.d.size() == 0 && this.f16808j == 0) {
            this.f16807i = false;
            ArrayList<j8.a> arrayList5 = this.a;
            if (arrayList5 != null) {
                ArrayList arrayList6 = (ArrayList) arrayList5.clone();
                int size4 = arrayList6.size();
                for (int i6 = 0; i6 < size4; i6++) {
                    ((j8.a) arrayList6.get(i6)).c(this);
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.j8
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public l8 clone() {
        l8 l8Var = (l8) super.clone();
        l8Var.f16804f = true;
        l8Var.f16806h = false;
        l8Var.f16807i = false;
        l8Var.b = new ArrayList<>();
        l8Var.f16802c = new HashMap<>();
        l8Var.d = new ArrayList<>();
        l8Var.f16803e = new ArrayList<>();
        HashMap hashMap = new HashMap();
        Iterator<f> it = this.d.iterator();
        while (it.hasNext()) {
            f next = it.next();
            f clone = next.clone();
            hashMap.put(next, clone);
            l8Var.d.add(clone);
            l8Var.f16802c.put(clone.a, clone);
            ArrayList arrayList = null;
            clone.b = null;
            clone.f16814c = null;
            clone.f16815e = null;
            clone.d = null;
            ArrayList<j8.a> e2 = clone.a.e();
            if (e2 != null) {
                Iterator<j8.a> it2 = e2.iterator();
                while (it2.hasNext()) {
                    j8.a next2 = it2.next();
                    if (next2 instanceof b) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(next2);
                    }
                }
                if (arrayList != null) {
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        e2.remove((j8.a) it3.next());
                    }
                }
            }
        }
        Iterator<f> it4 = this.d.iterator();
        while (it4.hasNext()) {
            f next3 = it4.next();
            f fVar = (f) hashMap.get(next3);
            ArrayList<d> arrayList2 = next3.b;
            if (arrayList2 != null) {
                Iterator<d> it5 = arrayList2.iterator();
                while (it5.hasNext()) {
                    d next4 = it5.next();
                    fVar.a(new d((f) hashMap.get(next4.a), next4.b));
                }
            }
        }
        return l8Var;
    }

    public ArrayList<j8> l() {
        ArrayList<j8> arrayList = new ArrayList<>();
        Iterator<f> it = this.d.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a);
        }
        return arrayList;
    }
}
