package com.jingdong.app.mall.home.category.floor.feedssub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.common.search.view.PriceHelper;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.jdsdk.JdSdk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class a {
    private static Context a = JdSdk.getInstance().getApplicationContext();

    /* renamed from: com.jingdong.app.mall.home.category.floor.feedssub.a$a */
    /* loaded from: classes4.dex */
    public static class C0275a extends ImageSpan {
        public C0275a(Context context, Bitmap bitmap, int i2) {
            super(context, bitmap, i2);
        }

        public void a(int i2, int i3) {
            Drawable drawable = getDrawable();
            if (drawable == null) {
                return;
            }
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth <= 0) {
                intrinsicWidth = 0;
            }
            int i4 = intrinsicWidth + i2 + i3;
            if (intrinsicHeight <= 0) {
                intrinsicHeight = 0;
            }
            drawable.setBounds(i2, 0, i4, intrinsicHeight);
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
            Drawable drawable = getDrawable();
            if (drawable == null) {
                return;
            }
            if ((drawable.getBounds().right - drawable.getBounds().left) + f2 > canvas.getWidth()) {
                return;
            }
            canvas.save();
            int i7 = paint.getFontMetricsInt().ascent + i5;
            canvas.translate(f2, i7 + ((((i5 + r3.descent) - i7) - (drawable.getBounds().bottom - drawable.getBounds().top)) / 2));
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    public static void a(View view, int i2, int i3, int i4) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (marginLayoutParams.height == i2 && marginLayoutParams.width == i3 && marginLayoutParams.topMargin == i4) {
                return;
            }
            marginLayoutParams.height = i2;
            marginLayoutParams.topMargin = i4;
            view.setLayoutParams(layoutParams);
        }
    }

    public static SpannableString b(List<Bitmap> list, SpannableString spannableString, String str, boolean z) {
        int size = list.size();
        if (size > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                C0275a c0275a = new C0275a(a, list.get(i2), 3);
                c0275a.a(z ? 0 : d.d(5), z ? d.d(5) : 0);
                int length = (z ? 0 : str.length()) + (i2 * 2);
                spannableString.setSpan(c0275a, length, length + 1, 17);
            }
        }
        return spannableString;
    }

    public static int c(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return (int) c.e(str.replace(PriceHelper.PRODUCT_PRICE_LABEL_FULL, "").replace(a.getResources().getString(R.string.yangjiao), ""), 0.0f);
    }

    public static SpannableString d(String str, String str2, float f2) {
        int lastIndexOf;
        String e2 = e(str);
        SpannableString spannableString = new SpannableString(e2);
        if (TextUtils.isEmpty(e2)) {
            return spannableString;
        }
        spannableString.setSpan(new RelativeSizeSpan(f2), 0, 1, 17);
        if (!TextUtils.isEmpty(str2) && (lastIndexOf = e2.lastIndexOf(str2)) > 0) {
            spannableString.setSpan(new RelativeSizeSpan(f2), lastIndexOf, e2.length(), 17);
        }
        return spannableString;
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String string = a.getResources().getString(R.string.yangjiao);
        return (TextUtils.equals(str, string) || TextUtils.equals(str, PriceHelper.PRODUCT_PRICE_LABEL_FULL)) ? "" : (str.contains(string) || str.contains(PriceHelper.PRODUCT_PRICE_LABEL_FULL)) ? str : string.concat(str);
    }

    public static SpannableString f(List<String> list, String str, boolean z) {
        if (list != null && list.size() > 0) {
            return g(list, str, z, false, 1.0f);
        }
        return new SpannableString(str);
    }

    public static SpannableString g(List<String> list, String str, boolean z, boolean z2, float f2) {
        int lastIndexOf;
        if (z2) {
            str = e(str);
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            Bitmap bitmap = UnIconConfigHelper.getBitmap(it.next());
            if (bitmap != null && !bitmap.isRecycled()) {
                arrayList.add(bitmap);
            }
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        if (z) {
            while (i2 < arrayList.size()) {
                sb.append("  ");
                i2++;
            }
            sb.append(str);
        } else {
            sb.append(str);
            while (i2 < arrayList.size()) {
                sb.append("  ");
                i2++;
            }
        }
        SpannableString spannableString = new SpannableString(sb.toString());
        if (z2 && f2 != 1.0f) {
            int max = Math.max(str.indexOf(a.getResources().getString(R.string.yangjiao)), str.indexOf(PriceHelper.PRODUCT_PRICE_LABEL_FULL));
            if (max >= 0) {
                spannableString.setSpan(new RelativeSizeSpan(f2), max, max + 1, 17);
            }
            if (!TextUtils.isEmpty(OrderISVUtil.MONEY_DECIMAL) && (lastIndexOf = str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL)) > 0) {
                spannableString.setSpan(new RelativeSizeSpan(f2), lastIndexOf, str.length(), 17);
            }
        }
        if (arrayList.size() <= 0) {
            return spannableString;
        }
        b(arrayList, spannableString, str, z);
        return spannableString;
    }
}
