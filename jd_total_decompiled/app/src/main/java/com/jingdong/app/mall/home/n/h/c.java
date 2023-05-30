package com.jingdong.app.mall.home.n.h;

import android.text.TextUtils;
import android.view.View;
import com.jingdong.jdsdk.res.StringUtil;
import java.text.NumberFormat;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class c {
    private static final Pattern a = Pattern.compile("[\u4e00-\u9fa5]");
    private static final Pattern b = Pattern.compile("(^[1-9]\\d*(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f10439g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ View[] f10440h;

        a(boolean z, View[] viewArr) {
            this.f10439g = z;
            this.f10440h = viewArr;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            c.l(this.f10439g, this.f10440h);
        }
    }

    /* loaded from: classes4.dex */
    class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f10441g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ View[] f10442h;

        b(boolean z, View[] viewArr) {
            this.f10441g = z;
            this.f10442h = viewArr;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            c.l(this.f10441g, this.f10442h);
        }
    }

    public static CharSequence a(CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence) ? StringUtil.no_price : charSequence;
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return a.matcher(str).find();
    }

    public static boolean c(String str) {
        return !TextUtils.isEmpty(str) && b.matcher(str).find();
    }

    public static boolean d(String str) {
        return TextUtils.isEmpty(str) || b(str);
    }

    public static float e(String str, float f2) {
        return f(null, str, f2);
    }

    public static float f(NumberFormat numberFormat, String str, float f2) {
        try {
            float parseFloat = Float.parseFloat(str);
            if (numberFormat != null) {
                try {
                    return e(numberFormat.format(parseFloat), f2);
                } catch (Exception unused) {
                    f2 = parseFloat;
                    return f2;
                }
            }
            return parseFloat;
        } catch (Exception unused2) {
        }
    }

    public static int g(String str) {
        return h(str, 0);
    }

    public static int h(String str, int i2) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i2;
        }
    }

    public static long i(String str) {
        return j(str, 0);
    }

    public static long j(String str, int i2) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return i2;
        }
    }

    public static void k(boolean z, View... viewArr) {
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new b(z, viewArr));
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                int i2 = z ? 8 : 0;
                if (i2 != view.getVisibility()) {
                    view.setVisibility(i2);
                }
            }
        }
    }

    public static void l(boolean z, View... viewArr) {
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new a(z, viewArr));
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                int i2 = z ? 4 : 0;
                if (i2 != view.getVisibility()) {
                    view.setVisibility(i2);
                }
            }
        }
    }
}
