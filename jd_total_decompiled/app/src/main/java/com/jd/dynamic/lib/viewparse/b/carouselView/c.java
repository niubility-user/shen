package com.jd.dynamic.lib.viewparse.b.carouselView;

import android.text.TextUtils;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.utils.s;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes13.dex */
public class c extends ViewPager2.OnPageChangeCallback implements ViewPager.OnPageChangeListener {

    /* renamed from: g */
    private final f f2308g;

    /* renamed from: h */
    private final DynamicTemplateEngine f2309h;

    public c(f fVar, DynamicTemplateEngine dynamicTemplateEngine) {
        this.f2308g = fVar;
        this.f2309h = dynamicTemplateEngine;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x000e, code lost:
        if ((r0 instanceof com.jd.dynamic.lib.viewparse.b.carouselView.o) != false) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private View d() {
        Object adapter;
        ViewPager2 w = this.f2308g.w();
        if (w != null) {
            adapter = w.getAdapter();
        }
        adapter = this.f2308g.y().getAdapter();
        if (!(adapter instanceof o)) {
            return null;
        }
        return ((o) adapter).getF2357g();
    }

    private String e(float f2, float f3, float f4) {
        return (f2 <= f4 || f3 >= f4) ? (f2 >= f4 || f3 <= f4) ? DYConstants.DY_NULL_STR : "positive" : "negative";
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrolled(int i2, float f2, int i3) {
        View d = d();
        if (d == null) {
            return;
        }
        i a = this.f2308g.a(this.f2308g.b(i2));
        if (a == null) {
            return;
        }
        int i4 = R.id.carousel_pre_offset;
        Object tag = d.getTag(i4);
        if (tag instanceof Float) {
            String e2 = e(((Float) tag).floatValue(), f2, a.b);
            if (!DYConstants.DY_NULL_STR.equals(e2)) {
                String d2 = s.d(a.a);
                DYConstants.DYLog("==>> position: " + i2 + " pre : " + tag + " offset : " + f2 + " notify type is : " + e2 + " eventId: " + d2);
                d.setTag(i4, Float.valueOf(f2));
                d.setTag(R.id.carousel_notify_type, e2);
                if (TextUtils.isEmpty(d2)) {
                    return;
                }
                d.setTag(R.id.dynamic_item_page_index, Integer.valueOf(i2));
                s.b(d2, d, this.f2309h, d);
                return;
            }
        }
        d.setTag(i4, Float.valueOf(f2));
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageSelected(int i2) {
        if (d() == null) {
            return;
        }
        this.f2308g.setTag(R.id.carousel_current_page, Integer.valueOf(i2));
        String x = this.f2308g.x();
        if (TextUtils.isEmpty(x)) {
            return;
        }
        List<String> i3 = s.i(x);
        if (i3.isEmpty()) {
            return;
        }
        Iterator<String> it = i3.iterator();
        while (it.hasNext()) {
            f fVar = this.f2308g;
            s.b(it.next(), fVar, this.f2309h, fVar);
        }
    }
}
