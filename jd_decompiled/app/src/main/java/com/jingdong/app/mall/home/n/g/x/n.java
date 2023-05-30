package com.jingdong.app.mall.home.n.g.x;

import android.graphics.Rect;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.R;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class n extends com.jingdong.app.mall.home.n.g.u.e {
    @NotNull

    /* renamed from: k */
    private final AtomicBoolean f10433k;
    @Nullable

    /* renamed from: l */
    private String f10434l;

    /* renamed from: m */
    private boolean f10435m;

    /* renamed from: n */
    private int f10436n;
    private int o;
    @Nullable
    private com.jingdong.app.mall.home.floor.common.f p;
    private int[] q;
    @Nullable
    private com.jingdong.app.mall.home.floor.common.f r;
    private int[] s;
    private String t;
    private String u;
    private int v;
    private String[] w;
    private com.jingdong.app.mall.home.n.g.v.d x;
    private String[] y;
    private String[] z;

    public n(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.b bVar) {
        super(jDJSONObject, bVar);
        this.f10433k = new AtomicBoolean(false);
        this.f10436n = 2;
        this.v = 2;
        this.w = new String[]{""};
    }

    private final void F() {
        this.f10433k.set(true);
        int i2 = this.o + 1;
        this.o = i2;
        int i3 = i2 % this.f10436n;
        this.o = i3;
        this.o = i3 != 0 ? i3 : 1;
        H();
    }

    private final void G() {
        this.f10433k.set(false);
        this.o = 0;
        H();
    }

    private final void H() {
        String str = this.w[this.o];
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.jingdong.app.mall.home.n.g.v.d dVar = this.x;
        if (dVar != null) {
            if (dVar == null) {
                Intrinsics.throwNpe();
            }
            dVar.put("status", this.f10433k.get() ? "1" : "0");
        }
        com.jingdong.app.mall.home.n.g.v.b.c(str, this.f10367f.d(this.x).toString());
    }

    @Nullable
    public final String A() {
        String[] strArr = this.z;
        if (strArr != null) {
            if (strArr == null) {
                Intrinsics.throwNpe();
            }
            return strArr[this.o];
        }
        return null;
    }

    @Nullable
    public final String B() {
        String[] strArr;
        if (!this.f10433k.get() || (strArr = this.y) == null) {
            return null;
        }
        if (strArr == null) {
            Intrinsics.throwNpe();
        }
        return strArr[this.o];
    }

    public final boolean C() {
        return this.f10435m;
    }

    public final boolean D() {
        return this.f10436n > 2;
    }

    @NotNull
    public final AtomicBoolean E() {
        return this.f10433k;
    }

    public final void I(boolean z) {
        if (z) {
            F();
        } else {
            G();
        }
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    public int b() {
        return com.jingdong.app.mall.home.floor.common.d.d(this.f10370i);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    @NotNull
    public String f() {
        return Intrinsics.stringPlus(this.t, this.u);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    public boolean m() {
        return this.f10370i > 0 && !TextUtils.isEmpty(this.f10434l);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    protected void n(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    protected void p() {
        this.t = getJsonString("type");
        this.u = getJsonString("sortFilterId");
        this.f10433k.set(false);
        if (Intrinsics.areEqual("0", this.t)) {
            String str = this.u;
            if (str != null) {
                switch (str.hashCode()) {
                    case 48:
                        if (str.equals("0")) {
                            this.w = new String[]{"", "Category_Selected_Sortbykeyword"};
                            this.f10370i = 124;
                            this.f10433k.set(true);
                            String jsonString = getJsonString("name");
                            this.f10434l = jsonString;
                            this.f10434l = TextUtils.isEmpty(jsonString) ? "\u7efc\u5408" : this.f10434l;
                            this.y = new String[]{"0", "0"};
                            return;
                        }
                        break;
                    case 49:
                        if (str.equals("1")) {
                            this.w = new String[]{"", "Category_Selected_Sortbyamount"};
                            this.f10370i = 124;
                            String jsonString2 = getJsonString("name");
                            this.f10434l = jsonString2;
                            this.f10434l = TextUtils.isEmpty(jsonString2) ? "\u9500\u91cf" : this.f10434l;
                            this.y = new String[]{"0", "1"};
                            return;
                        }
                        break;
                    case 50:
                        if (str.equals("2")) {
                            this.w = new String[]{"", "Category_Selected_Sortbyprice_l2h", "Category_Selected_Sortbyprice_h2l"};
                            this.f10370i = R2.anim.lib_cashier_sdk_fragment_right_out;
                            com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(12, 26);
                            this.r = fVar;
                            if (fVar == null) {
                                Intrinsics.throwNpe();
                            }
                            fVar.F(new Rect(5, 34, 0, 0));
                            int i2 = R.drawable.home_webp_empty;
                            this.s = new int[]{i2, i2, i2};
                            this.f10436n = 3;
                            String jsonString3 = getJsonString("name");
                            this.f10434l = jsonString3;
                            this.f10434l = TextUtils.isEmpty(jsonString3) ? "\u4ef7\u683c" : this.f10434l;
                            this.y = new String[]{"0", "3", "2"};
                            return;
                        }
                        break;
                    case 52:
                        if (str.equals("4")) {
                            this.w = new String[]{"", "Category_Selected_Sortbyreputation"};
                            this.f10370i = 124;
                            String jsonString4 = getJsonString("name");
                            this.f10434l = jsonString4;
                            this.f10434l = TextUtils.isEmpty(jsonString4) ? "\u597d\u8bc4" : this.f10434l;
                            this.y = new String[]{"0", "4"};
                            return;
                        }
                        break;
                }
            }
            this.f10370i = 0;
        } else if (Intrinsics.areEqual("1", this.t)) {
            String str2 = this.u;
            if (str2 != null && str2.hashCode() == 49 && str2.equals("1")) {
                this.w = new String[]{"Category_Selected_FilteredBtn", "Category_Selected_FilteredBtn"};
                this.v = 4;
                this.f10370i = 208;
                com.jingdong.app.mall.home.floor.common.f fVar2 = new com.jingdong.app.mall.home.floor.common.f(26, 26);
                this.p = fVar2;
                if (fVar2 == null) {
                    Intrinsics.throwNpe();
                }
                fVar2.F(new Rect(0, 31, 5, 0));
                int i3 = R.drawable.home_webp_empty;
                this.q = new int[]{i3, i3};
                this.f10435m = true;
                String jsonString5 = getJsonString("name");
                this.f10434l = jsonString5;
                this.f10434l = TextUtils.isEmpty(jsonString5) ? "\u4eac\u4e1c\u7269\u6d41" : this.f10434l;
                this.z = new String[]{"", "1"};
                com.jingdong.app.mall.home.n.g.v.d b = com.jingdong.app.mall.home.n.g.v.d.b();
                this.x = b;
                if (b != null) {
                    b.put("filterOption", this.f10434l);
                    return;
                }
                return;
            }
            this.f10370i = 0;
        }
    }

    public final int u() {
        int[] iArr = this.q;
        if (iArr == null) {
            return -1;
        }
        if (iArr == null) {
            Intrinsics.throwNpe();
        }
        return iArr[this.o];
    }

    @Nullable
    public final com.jingdong.app.mall.home.floor.common.f v() {
        return this.p;
    }

    @Nullable
    public final String w() {
        String str = this.f10434l;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        if (str.length() <= this.v) {
            return this.f10434l;
        }
        String str2 = this.f10434l;
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        int i2 = this.v;
        if (str2 != null) {
            String substring = str2.substring(0, i2);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return substring;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @Nullable
    public final String x() {
        return this.f10434l;
    }

    public final int y() {
        int[] iArr = this.s;
        if (iArr == null) {
            return -1;
        }
        if (iArr == null) {
            Intrinsics.throwNpe();
        }
        return iArr[this.o];
    }

    @Nullable
    public final com.jingdong.app.mall.home.floor.common.f z() {
        return this.r;
    }
}
