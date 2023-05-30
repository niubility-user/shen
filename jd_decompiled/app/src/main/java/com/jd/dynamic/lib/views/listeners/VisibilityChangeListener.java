package com.jd.dynamic.lib.views.listeners;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.utils.s;
import com.jd.dynamic.lib.utils.t;
import com.jd.dynamic.lib.viewparse.b.carouselView.f;
import com.jd.dynamic.lib.views.CollectionView;
import com.jingdong.jdsdk.constant.CartConstant;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class VisibilityChangeListener extends RecyclerView.OnScrollListener implements ViewPager.OnPageChangeListener {

    /* renamed from: g */
    private View f2550g;

    /* renamed from: h */
    private CollectionView f2551h;

    /* renamed from: i */
    private f f2552i;

    /* renamed from: j */
    private String f2553j;

    /* renamed from: k */
    private int f2554k;

    /* renamed from: l */
    private boolean f2555l;

    /* renamed from: m */
    private float f2556m;

    /* renamed from: n */
    private String f2557n;
    private JSONObject o;
    private DynamicTemplateEngine p;
    private String q = "_dyn_vertical_visibility_change_flag_";

    public VisibilityChangeListener(View view, CollectionView collectionView, f fVar, String str, JSONObject jSONObject, DynamicTemplateEngine dynamicTemplateEngine) {
        float f2;
        int i2;
        this.f2550g = view;
        this.f2551h = collectionView;
        this.f2552i = fVar;
        this.f2553j = str;
        try {
            this.f2557n = str.split(":")[1];
        } catch (Exception unused) {
        }
        try {
            f2 = Float.parseFloat(str.split(":")[0]);
        } catch (Exception unused2) {
            f2 = -1.0f;
        }
        if (f2 == -1.0f || TextUtils.isEmpty(this.f2557n)) {
            return;
        }
        try {
            i2 = Integer.parseInt(str.split(":")[2]);
        } catch (Exception unused3) {
            i2 = 1;
        }
        this.f2554k = i2;
        this.f2555l = f2 > 0.0f;
        this.f2556m = f2 != -1.0f ? Math.abs(f2) : -1.0f;
        this.o = jSONObject;
        this.p = dynamicTemplateEngine;
        this.q += CartConstant.KEY_YB_INFO_LINK + view.getId();
        preCal();
    }

    private void a(String str) {
        if (d()) {
            JSONObject jSONObject = this.o;
            boolean z = jSONObject != null && TextUtils.equals("1", jSONObject.optString(this.q));
            int i2 = this.f2554k;
            if (i2 == 1 && z) {
                return;
            }
            if (i2 == 1) {
                JSONObject jSONObject2 = this.o;
                if (jSONObject2 != null) {
                    try {
                        jSONObject2.put(this.q, "1");
                        t.e("onScrollChanged", "insert flag " + str + " index = " + this.f2550g.getTag(R.id.dynamic_item_position), this.o.optString(this.q));
                    } catch (Exception unused) {
                    }
                } else {
                    CollectionView collectionView = this.f2551h;
                    if (collectionView != null) {
                        collectionView.getRecyclerView().removeOnScrollListener(this);
                    }
                    f fVar = this.f2552i;
                    if (fVar != null && fVar.y() != null) {
                        this.f2552i.y().removeOnPageChangeListener(this);
                    }
                    t.e("onScrollChanged", "removeOnScrollChangedListener");
                }
            }
            Observable.from(s.i(this.f2557n)).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.listeners.b
                {
                    VisibilityChangeListener.this = this;
                }

                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    VisibilityChangeListener.this.e((String) obj);
                }
            }, new Action1() { // from class: com.jd.dynamic.lib.views.listeners.a
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    VisibilityChangeListener.b((Throwable) obj);
                }
            });
        }
    }

    public static /* synthetic */ void b(Throwable th) {
    }

    private boolean d() {
        View view;
        return ((this.f2554k == 1 && this.o == null) || (view = this.f2550g) == null || view.getVisibility() != 0 || this.f2556m == -1.0f || TextUtils.isEmpty(this.f2557n)) ? false : true;
    }

    public /* synthetic */ void e(String str) {
        View view = this.f2550g;
        s.b(str, view, this.p, view);
    }

    public View getScrollView() {
        CollectionView collectionView = this.f2551h;
        if (collectionView != null) {
            return collectionView;
        }
        f fVar = this.f2552i;
        if (fVar != null) {
            return fVar;
        }
        return null;
    }

    public String getValues() {
        return this.f2553j;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
        boolean z;
        if (d()) {
            JSONObject jSONObject = this.o;
            boolean z2 = true;
            boolean z3 = jSONObject != null && TextUtils.equals("1", jSONObject.optString(this.q));
            if (!(this.f2554k == 1 && z3) && this.f2550g.getVisibility() == 0 && this.f2550g.getParent() != null && this.f2550g.getHeight() > 0 && this.f2550g.getWidth() > 0) {
                float height = this.f2550g.getHeight() * this.f2550g.getWidth();
                if (this.f2550g.getLocalVisibleRect(new Rect())) {
                    float height2 = (r0.height() * r0.width()) / height;
                    int[] iArr = new int[2];
                    this.f2550g.getLocationInWindow(iArr);
                    if (iArr[0] == 0 && iArr[1] == 0) {
                        height2 = 0.0f;
                    }
                    View view = this.f2550g;
                    int i4 = R.id.dynamic_expo_percents;
                    Object tag = view.getTag(i4);
                    if (!(tag instanceof Float)) {
                        this.f2550g.setTag(i4, Float.valueOf(height2));
                        t.e("onScrollChanged1", Float.valueOf(height2));
                        return;
                    }
                    float floatValue = ((Float) tag).floatValue();
                    this.f2550g.setTag(i4, Float.valueOf(height2));
                    float f2 = this.f2556m;
                    if (floatValue <= f2 || height2 >= f2 || this.f2555l) {
                        z = false;
                    } else {
                        t.e("onScrollChanged", "\u4ece\u5927\u5230\u5c0f", Float.valueOf(height2), Float.valueOf(floatValue));
                        z = true;
                    }
                    float f3 = this.f2556m;
                    if (floatValue >= f3 || height2 <= f3 || !this.f2555l) {
                        z2 = z;
                    } else {
                        t.e("onScrollChanged", "\u4ece\u5c0f\u5230\u5927", Float.valueOf(height2), Float.valueOf(floatValue));
                    }
                    if (z2) {
                        a("onScrollChanged");
                    }
                }
            }
        }
    }

    public void preCal() {
        if (d()) {
            int[] iArr = new int[2];
            this.f2550g.getLocationInWindow(iArr);
            if (this.f2550g.getVisibility() != 0 || this.f2550g.getParent() == null || ((iArr[0] == 0 && iArr[1] == 0) || this.f2550g.getHeight() == 0 || this.f2550g.getWidth() == 0)) {
                this.f2550g.setTag(R.id.dynamic_expo_percents, Float.valueOf(0.0f));
                return;
            }
            float height = this.f2550g.getHeight() * this.f2550g.getWidth();
            if (this.f2550g.getLocalVisibleRect(new Rect())) {
                float height2 = (r3.height() * r3.width()) / height;
                this.f2550g.setTag(R.id.dynamic_expo_percents, Float.valueOf(height2));
                t.e("onScrollChanged", "pre2", Float.valueOf(height2));
                if (height2 >= this.f2556m) {
                    a("preCal");
                }
            }
        }
    }
}
