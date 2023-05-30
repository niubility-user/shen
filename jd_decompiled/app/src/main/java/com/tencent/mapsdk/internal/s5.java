package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.map.tools.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes9.dex */
public class s5 {
    public static final int E = 0;
    public static final int F = 1;
    public static final int G = 2;
    public static final int H = 3;
    public static final int I = 9;
    public static final int J = -1;
    public static final float K = 200.0f;
    public static final float L = 60.0f;
    public static final String M = "color_texture_flat_style.png";
    public static final String N = "color_point_texture.png";
    public static final String O = "color_texture_line_v2.png";
    public boolean D;
    public ArrayList<GeoPoint> a;
    public ArrayList<GeoPoint> b;

    /* renamed from: c  reason: collision with root package name */
    public int[] f17216c;
    public int[] d;

    /* renamed from: e  reason: collision with root package name */
    public String[] f17217e;

    /* renamed from: f  reason: collision with root package name */
    public int[] f17218f;

    /* renamed from: g  reason: collision with root package name */
    public int[] f17219g;

    /* renamed from: h  reason: collision with root package name */
    public int[] f17220h;

    /* renamed from: i  reason: collision with root package name */
    public int[] f17221i;

    /* renamed from: j  reason: collision with root package name */
    public float f17222j;

    /* renamed from: k  reason: collision with root package name */
    public boolean f17223k;

    /* renamed from: l  reason: collision with root package name */
    public boolean f17224l;

    /* renamed from: m  reason: collision with root package name */
    public float f17225m = 9.0f;

    /* renamed from: n  reason: collision with root package name */
    public String f17226n = "";
    public boolean o = true;
    public float p = 1.0f;
    public boolean q = true;
    public int r = 0;
    public boolean s = false;
    public boolean t = false;
    public Rect u = new Rect();
    public int v = 0;
    public String w = "";
    public float x = -1.0f;
    public int y = -1;
    public int z = -15248742;
    public List<Integer> A = null;
    private int B = 2;
    private int C = -7829368;

    /* loaded from: classes9.dex */
    public static class a {
        public static final int a = 0;
        public static final int b = 1;

        /* renamed from: c  reason: collision with root package name */
        public static final int f17227c = 2;
        public static final int d = 3;

        /* renamed from: e  reason: collision with root package name */
        public static final int f17228e = 4;

        /* renamed from: f  reason: collision with root package name */
        public static final int f17229f = 5;

        /* renamed from: g  reason: collision with root package name */
        public static final int f17230g = 6;

        /* renamed from: h  reason: collision with root package name */
        public static final int f17231h = 7;

        /* renamed from: i  reason: collision with root package name */
        public static final int f17232i = 8;

        /* renamed from: j  reason: collision with root package name */
        public static final int f17233j = 9;

        /* renamed from: k  reason: collision with root package name */
        public static final int f17234k = 33;

        /* renamed from: l  reason: collision with root package name */
        public static final int f17235l = 19;

        /* renamed from: m  reason: collision with root package name */
        public static final int f17236m = 20;
    }

    /* loaded from: classes9.dex */
    public class b {
        public int a;
        public int b;

        public b(int i2, int i3) {
            this.b = i2;
            this.a = i3;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            b bVar = (b) obj;
            return bVar.a == this.a && bVar.b == this.b;
        }
    }

    public s5 a(float f2) {
        this.p = f2;
        return this;
    }

    public s5 a(int i2) {
        this.C = i2;
        return this;
    }

    public s5 a(int i2, int i3) {
        this.y = i2;
        this.z = i3;
        return this;
    }

    public s5 a(String str) {
        this.w = str;
        return this;
    }

    public s5 a(List<Integer> list) {
        if (list != null && list.size() % 2 != 0) {
            list.add(list.get(list.size() - 1));
        }
        this.A = list;
        return this;
    }

    public s5 a(boolean z) {
        this.s = z;
        return this;
    }

    public s5 a(int[] iArr) {
        if (iArr == null || iArr.length < 1) {
            ma.h("\u53c2\u6570endNums\u4e0d\u80fd\u4e3a\u7a7a!");
            return this;
        }
        this.d = iArr;
        return this;
    }

    public s5 a(int[] iArr, int[] iArr2) {
        if (iArr == null || iArr.length < 1 || iArr2 == null || iArr2.length < 1) {
            ma.h("\u53c2\u6570colors \u3001borderColors\u4e3a\u7a7a\uff0c\u6216\u8005\u4e24\u8005\u957f\u5ea6\u4e0d\u540c");
            return this;
        } else if (!this.f17223k) {
            this.f17219g = iArr;
            return this;
        } else {
            if (iArr2.length < iArr.length) {
                int[] iArr3 = new int[iArr.length];
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    if (i2 < iArr2.length) {
                        iArr3[i2] = iArr2[i2];
                    } else {
                        iArr3[i2] = iArr2[iArr2.length - 1];
                    }
                }
                iArr2 = iArr3;
            }
            ArrayList arrayList = new ArrayList();
            int i3 = this.C;
            arrayList.add(new b(i3, i3));
            this.f17219g = new int[iArr.length];
            for (int i4 = 0; i4 < iArr.length; i4++) {
                b bVar = new b(iArr[i4], iArr2[i4]);
                if (!arrayList.contains(bVar)) {
                    arrayList.add(bVar);
                }
                this.f17219g[i4] = arrayList.indexOf(bVar);
            }
            int size = arrayList.size();
            this.f17220h = new int[size];
            this.f17221i = new int[size];
            for (int i5 = 0; i5 < size; i5++) {
                this.f17220h[i5] = ((b) arrayList.get(i5)).b;
                this.f17221i[i5] = ((b) arrayList.get(i5)).a;
            }
            return this;
        }
    }

    public s5 a(String[] strArr) {
        if (strArr == null || strArr.length < 1) {
            ma.h("\u53c2\u6570roadNames\u4e0d\u80fd\u4e3a\u7a7a!");
            return this;
        }
        this.f17217e = strArr;
        return this;
    }

    public boolean a() {
        String str;
        ArrayList<GeoPoint> arrayList = this.b;
        if (arrayList == null || arrayList.size() < 2) {
            str = "LineOptions\u4e2d\u70b9\u7684\u4e2a\u6570\u4e0d\u80fd\u5c0f\u4e8e2";
        } else {
            int[] iArr = this.f17218f;
            if (iArr == null || iArr.length < 1) {
                str = "\u53c2\u6570startIndexes\u4e0d\u80fd\u4e3a\u7a7a!";
            } else {
                int[] iArr2 = this.f17219g;
                if (iArr2 != null && iArr2.length >= 1) {
                    return true;
                }
                str = "\u53c2\u6570colors\u4e0d\u80fd\u4e3a\u7a7a!";
            }
        }
        ma.h(str);
        return false;
    }

    public int b() {
        return this.r;
    }

    public s5 b(float f2) {
        this.f17222j = f2;
        return this;
    }

    public s5 b(List<GeoPoint> list) {
        if (list == null || list.size() < 2) {
            ma.h("\u53c2\u6570points\u4e0d\u80fd\u5c0f\u4e8e2!");
            return this;
        }
        ArrayList<GeoPoint> arrayList = new ArrayList<>(list.size());
        this.b = arrayList;
        arrayList.addAll(list);
        if (this.b.size() < 2) {
            ma.h("\u53c2\u6570points\u5b58\u5728null\u503c");
            return this;
        }
        ArrayList<GeoPoint> arrayList2 = new ArrayList<>(list.size());
        this.a = arrayList2;
        arrayList2.addAll(this.b);
        return this;
    }

    public s5 b(boolean z) {
        this.f17224l = z;
        return this;
    }

    public s5 b(int[] iArr) {
        if (iArr == null || iArr.length < 1) {
            ma.h("\u53c2\u6570startNums\u4e0d\u80fd\u4e3a\u7a7a!");
            return this;
        }
        this.f17216c = iArr;
        return this;
    }

    public void b(int i2) {
        this.B = i2;
    }

    @Deprecated
    public void b(String str) {
        this.f17226n = str;
    }

    public int c() {
        return this.B;
    }

    public s5 c(int i2) {
        this.r = i2;
        return this;
    }

    public s5 c(String str) {
        this.f17226n = str;
        return this;
    }

    public s5 c(boolean z) {
        this.f17223k = z;
        return this;
    }

    public s5 c(int[] iArr) {
        if (iArr == null || iArr.length < 1) {
            ma.h("\u53c2\u6570startIndexes\u4e0d\u80fd\u4e3a\u7a7a!");
            return this;
        }
        this.f17218f = iArr;
        return this;
    }

    @Deprecated
    public void c(float f2) {
        this.p = f2;
    }

    public float d() {
        return this.x;
    }

    public s5 d(float f2) {
        this.x = f2;
        return this;
    }

    public s5 d(int i2) {
        this.v = i2;
        return this;
    }

    public s5 d(boolean z) {
        this.q = z;
        return this;
    }

    public s5 d(int[] iArr) {
        if (iArr == null || iArr.length < 1) {
            ma.h("\u53c2\u6570colors\u4e0d\u80fd\u4e3a\u7a7a!");
            return this;
        } else if (!this.f17223k) {
            this.f17219g = iArr;
            return this;
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(this.C));
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (!arrayList.contains(Integer.valueOf(iArr[i2]))) {
                    arrayList.add(Integer.valueOf(iArr[i2]));
                }
                iArr[i2] = arrayList.indexOf(Integer.valueOf(iArr[i2]));
            }
            this.f17219g = iArr;
            int size = arrayList.size();
            this.f17220h = new int[size];
            for (int i3 = 0; i3 < size; i3++) {
                this.f17220h[i3] = ((Integer) arrayList.get(i3)).intValue();
            }
            return this;
        }
    }

    public s5 e(float f2) {
        this.f17225m = f2;
        return this;
    }

    @Deprecated
    public void e(boolean z) {
        this.o = z;
    }

    public int[] e() {
        return new int[]{this.y, this.z};
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        s5 s5Var = (s5) obj;
        return Float.compare(s5Var.f17222j, this.f17222j) == 0 && this.f17223k == s5Var.f17223k && this.f17224l == s5Var.f17224l && Float.compare(s5Var.f17225m, this.f17225m) == 0 && this.o == s5Var.o && Float.compare(s5Var.p, this.p) == 0 && this.q == s5Var.q && this.r == s5Var.r && this.s == s5Var.s && this.t == s5Var.t && this.v == s5Var.v && Float.compare(s5Var.x, this.x) == 0 && this.y == s5Var.y && this.z == s5Var.z && this.B == s5Var.B && this.C == s5Var.C && this.D == s5Var.D && Util.equals(this.a, s5Var.a) && Util.equals(this.b, s5Var.b) && Arrays.equals(this.f17216c, s5Var.f17216c) && Arrays.equals(this.d, s5Var.d) && Arrays.equals(this.f17217e, s5Var.f17217e) && Arrays.equals(this.f17218f, s5Var.f17218f) && Arrays.equals(this.f17219g, s5Var.f17219g) && Arrays.equals(this.f17220h, s5Var.f17220h) && Arrays.equals(this.f17221i, s5Var.f17221i) && Util.equals(this.f17226n, s5Var.f17226n) && Util.equals(this.u, s5Var.u) && Util.equals(this.w, s5Var.w) && Util.equals(this.A, s5Var.A);
    }

    public s5 f(boolean z) {
        this.t = z;
        return this;
    }

    public int hashCode() {
        return (((((((((((((Util.hash(this.a, this.b, Float.valueOf(this.f17222j), Boolean.valueOf(this.f17223k), Boolean.valueOf(this.f17224l), Float.valueOf(this.f17225m), this.f17226n, Boolean.valueOf(this.o), Float.valueOf(this.p), Boolean.valueOf(this.q), Integer.valueOf(this.r), Boolean.valueOf(this.s), Boolean.valueOf(this.t), this.u, Integer.valueOf(this.v), this.w, Float.valueOf(this.x), Integer.valueOf(this.y), Integer.valueOf(this.z), this.A, Integer.valueOf(this.B), Integer.valueOf(this.C), Boolean.valueOf(this.D)) * 31) + Arrays.hashCode(this.f17216c)) * 31) + Arrays.hashCode(this.d)) * 31) + Arrays.hashCode(this.f17217e)) * 31) + Arrays.hashCode(this.f17218f)) * 31) + Arrays.hashCode(this.f17219g)) * 31) + Arrays.hashCode(this.f17220h)) * 31) + Arrays.hashCode(this.f17221i);
    }
}
