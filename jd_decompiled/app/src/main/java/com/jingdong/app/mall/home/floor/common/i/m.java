package com.jingdong.app.mall.home.floor.common.i;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.view.view.MallFloorMaiDian;
import com.jingdong.common.R;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class m {
    private static SimpleDateFormat a = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ View f9335g;

        a(View view) {
            this.f9335g = view;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            m.K(this.f9335g);
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ View f9336g;

        b(View view) {
            this.f9336g = view;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            m.J(this.f9336g);
        }
    }

    public static boolean A(com.jingdong.app.mall.home.r.e.d dVar) {
        if (dVar == null) {
            return false;
        }
        return B(dVar.mParentModel);
    }

    public static boolean B(com.jingdong.app.mall.home.r.e.h hVar) {
        if (hVar == null) {
            return false;
        }
        String g2 = hVar.g();
        return TextUtils.equals(g2, "10.0.0") || TextUtils.equals(g2, "9.2.4");
    }

    public static boolean C(com.jingdong.app.mall.home.r.e.h hVar) {
        return w(hVar) || B(hVar);
    }

    public static boolean D(com.jingdong.app.mall.home.r.e.h hVar) {
        if (hVar == null) {
            return false;
        }
        return TextUtils.equals(hVar.g(), "9.0.0") || F(hVar);
    }

    public static boolean E(com.jingdong.app.mall.home.r.e.d dVar) {
        return A(dVar);
    }

    public static boolean F(com.jingdong.app.mall.home.r.e.h hVar) {
        return B(hVar);
    }

    public static boolean G(View view, int i2, int i3, int i4) {
        return H(view, i2, i3, i4, false);
    }

    public static boolean H(View view, int i2, int i3, int i4, boolean z) {
        if (view == null) {
            return false;
        }
        try {
            if (view.getParent() == null || view.getParent().getParent() == null) {
                return false;
            }
            int height = view.getHeight();
            if (height != 0 || (view instanceof MallFloorMaiDian)) {
                Rect rect = new Rect();
                view.getWindowVisibleDisplayFrame(rect);
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                int i5 = iArr[0];
                int i6 = iArr[1];
                if (z && i5 == 0 && i6 == 0) {
                    return false;
                }
                if (i4 > 100) {
                    i4 = 100;
                }
                float f2 = i4 > 0 ? i4 / 100.0f : 1.0E-4f;
                if (com.jingdong.app.mall.home.floor.ctrl.h.A != 0) {
                    rect.top = 0;
                }
                float f3 = i6;
                float f4 = height;
                return ((1.0f - f2) * f4) + f3 >= ((float) (rect.top + i2)) && f3 + (f2 * f4) <= ((float) i3);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean I(View view, int i2, int i3, boolean z) {
        return G(view, i2, i3, z ? 100 : 0);
    }

    public static void J(View view) {
        if (view instanceof ViewGroup) {
            if (com.jingdong.app.mall.home.o.a.f.p0()) {
                com.jingdong.app.mall.home.o.a.f.E0(new b(view));
            } else {
                ((ViewGroup) view).removeAllViews();
            }
        }
    }

    public static void K(View view) {
        if (view == null) {
            return;
        }
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new a(view));
            return;
        }
        ViewParent parent = view.getParent();
        com.jingdong.app.mall.home.o.a.f.n(parent);
        ViewGroup viewGroup = (ViewGroup) parent;
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
    }

    public static void L(ViewGroup viewGroup, View view, int i2) {
        if (view == null || viewGroup == null) {
            return;
        }
        if (i2 < 0 || viewGroup.indexOfChild(view) != i2) {
            K(view);
            if (i2 >= 0 && viewGroup.getChildCount() >= i2) {
                if (viewGroup.getChildAt(i2) != null) {
                    viewGroup.removeViewAt(i2);
                }
                viewGroup.addView(view, i2);
                return;
            }
            viewGroup.addView(view);
        }
    }

    public static void M(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.x0(str, a.format(new Date()) + "|" + i2);
    }

    public static void a(ViewGroup viewGroup, View view) {
        b(viewGroup, view, -1);
    }

    public static void b(ViewGroup viewGroup, View view, int i2) {
        if (view == null || viewGroup == null) {
            return;
        }
        if (i2 < 0 || viewGroup.indexOfChild(view) != i2) {
            K(view);
            if (i2 >= 0 && viewGroup.getChildCount() >= i2) {
                viewGroup.addView(view, i2);
            } else {
                viewGroup.addView(view);
            }
        }
    }

    public static void c(View view, String str) {
        if (com.jingdong.app.mall.home.o.a.l.a() && view != null && !TextUtils.isEmpty(str) && com.jingdong.app.mall.home.o.a.f.m0(str)) {
            view.setTag(com.jingdong.app.mall.home.floor.ctrl.e.d, null);
        }
    }

    public static <T extends ViewGroup.LayoutParams> T d(ViewGroup viewGroup, View view, int i2, int i3) {
        if (viewGroup == null || view == null) {
            return null;
        }
        T t = (T) view.getLayoutParams();
        if (t != null) {
            ((ViewGroup.LayoutParams) t).width = i2;
            ((ViewGroup.LayoutParams) t).height = i3;
            return t;
        } else if (viewGroup instanceof RelativeLayout) {
            return new RelativeLayout.LayoutParams(i2, i3);
        } else {
            if (viewGroup instanceof LinearLayout) {
                return new LinearLayout.LayoutParams(i2, i3);
            }
            return viewGroup instanceof FrameLayout ? new FrameLayout.LayoutParams(i2, i3) : t;
        }
    }

    public static void e(View view) {
        if (view == null || com.jingdong.app.mall.home.o.a.f.p0()) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
    }

    public static void f(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null) {
            e(viewHolder.itemView);
        }
    }

    public static String g(String str) {
        return (str == null || DYConstants.DY_NULL_STR.equalsIgnoreCase(str) || str.contains(DYConstants.DY_NULL_STR)) ? "" : str;
    }

    public static float[] h(String str) {
        float[] fArr = null;
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(DYConstants.DY_REGEX_COMMA);
        if (split.length != 0 && split.length % 2 != 1) {
            fArr = new float[4];
            for (int i2 = 0; i2 < 4; i2++) {
                try {
                    fArr[i2] = com.jingdong.app.mall.home.floor.common.d.d((int) Float.valueOf(split[i2]).floatValue());
                } catch (Exception unused) {
                    fArr[i2] = 0.0f;
                }
            }
        }
        return fArr;
    }

    public static int i(String str, int i2) {
        int[] o = o(str, i2);
        return (o == null || o.length < 1) ? i2 : o[0];
    }

    public static int j(String str, int i2) {
        int[] iArr = {0};
        return k(str, iArr) ? iArr[0] : i2;
    }

    public static boolean k(String str, int[] iArr) {
        return l(str, iArr, true);
    }

    public static boolean l(String str, int[] iArr, boolean z) {
        if (iArr != null && iArr.length > 0 && str != null && !str.isEmpty()) {
            try {
                Matcher matcher = Pattern.compile("^#([A-Fa-f0-9]{6})$|^#([A-Fa-f0-9]{2})([A-Fa-f0-9]{6})$").matcher(str.trim());
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    String group3 = matcher.group(3);
                    if (group2 != null && group3 != null) {
                        iArr[0] = Integer.parseInt(group3, 16) | ((z ? Integer.parseInt(group2, 16) : 255) << 24);
                        return true;
                    } else if (group == null) {
                        return false;
                    } else {
                        iArr[0] = (-16777216) | Integer.parseInt(group, 16);
                        return true;
                    }
                }
                return false;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static int m(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        String[] split = CommonBase.getStringFromPreference(str, "").split(DYConstants.DY_REGEX_VERTICAL_LINE);
        if (split[0].equals(a.format(new Date()))) {
            return Integer.parseInt(split[1]);
        }
        return 0;
    }

    public static int[] n(String str, int[] iArr, boolean z) {
        if (str == null || str.isEmpty()) {
            if (z) {
                return iArr;
            }
            return null;
        }
        String[] split = str.split(DYConstants.DY_REGEX_COMMA);
        if (split.length == 0) {
            if (z) {
                int[] iArr2 = {0};
                return k(str, iArr2) ? iArr2 : iArr;
            }
            int[] iArr3 = {0};
            if (k(str, iArr3)) {
                return iArr3;
            }
            return null;
        }
        int[] iArr4 = new int[split.length];
        for (int i2 = 0; i2 < split.length; i2++) {
            int[] iArr5 = {0};
            if (!k(split[i2], iArr5)) {
                if (z) {
                    return iArr;
                }
                return null;
            }
            iArr4[i2] = iArr5[0];
        }
        return iArr4;
    }

    public static int[] o(String str, int i2) {
        return p(str, i2, true);
    }

    public static int[] p(String str, int i2, boolean z) {
        return q(str, i2, z, true);
    }

    public static int[] q(String str, int i2, boolean z, boolean z2) {
        if (str == null || str.isEmpty()) {
            if (z) {
                return new int[]{i2};
            }
            return null;
        }
        String[] split = str.split(DYConstants.DY_REGEX_COMMA);
        if (split.length == 0) {
            if (z) {
                return new int[]{j(str, i2)};
            }
            int[] iArr = {0};
            if (k(str, iArr)) {
                return iArr;
            }
            return null;
        }
        int[] iArr2 = new int[split.length];
        for (int i3 = 0; i3 < split.length; i3++) {
            int[] iArr3 = {0};
            if (!l(split[i3], iArr3, z2)) {
                if (z) {
                    return new int[]{i2};
                }
                return null;
            }
            iArr2[i3] = iArr3[0];
        }
        return iArr2;
    }

    public static Point r(String str) {
        if (TextUtils.isEmpty(str)) {
            return new Point(0, 0);
        }
        try {
            Matcher matcher = Pattern.compile(".+mobilecms/s(\\d{2,4})x(\\d{2,4})_jfs/.+").matcher(str);
            if (matcher.matches() && matcher.groupCount() == 2) {
                return new Point(com.jingdong.app.mall.home.floor.common.d.d(Integer.parseInt(matcher.group(1))), com.jingdong.app.mall.home.floor.common.d.d(Integer.parseInt(matcher.group(2))));
            }
            return new Point(0, 0);
        } catch (Exception unused) {
            return new Point(0, 0);
        }
    }

    public static int s() {
        return JdSdk.getInstance().getApplication().getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_height);
    }

    public static com.jingdong.app.mall.home.floor.common.b t(String str) {
        int[] p = p(str, 0, false);
        if (p != null && p.length != 0) {
            int i2 = p[0];
            if (i2 == -12869377) {
                if (p.length < 2) {
                    return com.jingdong.app.mall.home.floor.common.b.ARROW_COLOR_TYPE_UNKNOW;
                }
                if (p[1] == com.jingdong.app.mall.home.floor.common.a.f9274c[1]) {
                    return com.jingdong.app.mall.home.floor.common.b.ARROW_COLOR_TYPE_PURPLE;
                }
                return com.jingdong.app.mall.home.floor.common.b.ARROW_COLOR_TYPE_UNKNOW;
            } else if (i2 == -45009) {
                if (p.length < 2) {
                    return com.jingdong.app.mall.home.floor.common.b.ARROW_COLOR_TYPE_UNKNOW;
                }
                if (p[1] == com.jingdong.app.mall.home.floor.common.a.b[1]) {
                    return com.jingdong.app.mall.home.floor.common.b.ARROW_COLOR_TYPE_PINK;
                }
                return com.jingdong.app.mall.home.floor.common.b.ARROW_COLOR_TYPE_UNKNOW;
            } else if (i2 != -37376) {
                return com.jingdong.app.mall.home.floor.common.b.ARROW_COLOR_TYPE_UNKNOW;
            } else {
                if (p.length < 3) {
                    return com.jingdong.app.mall.home.floor.common.b.ARROW_COLOR_TYPE_UNKNOW;
                }
                int i3 = p[1];
                int[] iArr = com.jingdong.app.mall.home.floor.common.a.a;
                if (i3 == iArr[1] && p[2] == iArr[2]) {
                    return com.jingdong.app.mall.home.floor.common.b.ARROW_COLOR_TYPE_RED;
                }
                return com.jingdong.app.mall.home.floor.common.b.ARROW_COLOR_TYPE_UNKNOW;
            }
        }
        return com.jingdong.app.mall.home.floor.common.b.ARROW_COLOR_TYPE_UNKNOW;
    }

    public static int u() {
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null) {
            return DPIUtil.getHeight();
        }
        return v(z0.thisActivity);
    }

    public static int v(Context context) {
        View r = com.jingdong.app.mall.home.o.a.f.r(context);
        if (r != null && r.getHeight() > 0) {
            return r.getHeight();
        }
        return DPIUtil.getHeight();
    }

    private static boolean w(com.jingdong.app.mall.home.r.e.h hVar) {
        return hVar != null && hVar.g().equals("8.0.0");
    }

    public static boolean x(View view) {
        return y(view, 0);
    }

    public static boolean y(View view, int i2) {
        return G(view, com.jingdong.app.mall.home.a.f8560i + com.jingdong.app.mall.home.a.j(), com.jingdong.app.mall.home.a.f8562k, i2);
    }

    public static boolean z(int[] iArr) {
        return iArr != null && iArr.length > 1;
    }
}
