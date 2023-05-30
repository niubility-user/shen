package com.jd.dynamic.lib.views.listeners;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.utils.s;
import com.jd.dynamic.lib.utils.t;
import com.jd.dynamic.lib.viewparse.b.carouselView.f;
import com.jingdong.jdsdk.constant.CartConstant;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class VisibilityChangeListener2 extends ViewPager2.OnPageChangeCallback {
    private View a;
    private f b;

    /* renamed from: c */
    private String f2558c;
    private int d;

    /* renamed from: e */
    private boolean f2559e;

    /* renamed from: f */
    private float f2560f;

    /* renamed from: g */
    private String f2561g;

    /* renamed from: h */
    private JSONObject f2562h;

    /* renamed from: i */
    private DynamicTemplateEngine f2563i;

    /* renamed from: j */
    private String f2564j = "_dyn_horizontal_visibility_change_flag_";

    public VisibilityChangeListener2(View view, f fVar, String str, JSONObject jSONObject, DynamicTemplateEngine dynamicTemplateEngine) {
        float f2;
        int i2;
        this.a = view;
        this.b = fVar;
        this.f2558c = str;
        try {
            this.f2561g = str.split(":")[1];
        } catch (Exception unused) {
        }
        try {
            f2 = Float.parseFloat(str.split(":")[0]);
        } catch (Exception unused2) {
            f2 = -1.0f;
        }
        if (f2 == -1.0f || TextUtils.isEmpty(this.f2561g)) {
            return;
        }
        try {
            i2 = Integer.parseInt(str.split(":")[2]);
        } catch (Exception unused3) {
            i2 = 1;
        }
        this.d = i2;
        this.f2559e = f2 > 0.0f;
        this.f2560f = f2 != -1.0f ? Math.abs(f2) : -1.0f;
        this.f2562h = jSONObject;
        this.f2563i = dynamicTemplateEngine;
        this.f2564j += CartConstant.KEY_YB_INFO_LINK + view.getId();
        preCal();
    }

    private void a(String str) {
        if (d()) {
            JSONObject jSONObject = this.f2562h;
            boolean z = jSONObject != null && TextUtils.equals("1", jSONObject.optString(this.f2564j));
            int i2 = this.d;
            if (i2 == 1 && z) {
                return;
            }
            if (i2 == 1) {
                JSONObject jSONObject2 = this.f2562h;
                if (jSONObject2 != null) {
                    try {
                        jSONObject2.put(this.f2564j, "1");
                        t.e("onScrollChanged", "insert flag " + str + " index = " + this.a.getTag(R.id.dynamic_item_position), this.f2562h.optString(this.f2564j));
                    } catch (Exception unused) {
                    }
                } else {
                    f fVar = this.b;
                    if (fVar != null && fVar.w() != null) {
                        this.b.w().unregisterOnPageChangeCallback(this);
                    }
                    t.e("onScrollChanged", "removeOnScrollChangedListener");
                }
            }
            Observable.from(s.i(this.f2561g)).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.listeners.d
                {
                    VisibilityChangeListener2.this = this;
                }

                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    VisibilityChangeListener2.this.e((String) obj);
                }
            }, new Action1() { // from class: com.jd.dynamic.lib.views.listeners.c
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    VisibilityChangeListener2.b((Throwable) obj);
                }
            });
        }
    }

    public static /* synthetic */ void b(Throwable th) {
    }

    private boolean d() {
        View view;
        return ((this.d == 1 && this.f2562h == null) || (view = this.a) == null || view.getVisibility() != 0 || this.f2560f == -1.0f || TextUtils.isEmpty(this.f2561g)) ? false : true;
    }

    public /* synthetic */ void e(String str) {
        View view = this.a;
        s.b(str, view, this.f2563i, view);
    }

    public View getScrollView() {
        f fVar = this.b;
        if (fVar != null) {
            return fVar;
        }
        return null;
    }

    public String getValues() {
        return this.f2558c;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrolled(int i2, float f2, int i3) {
        boolean z;
        if (d()) {
            JSONObject jSONObject = this.f2562h;
            boolean z2 = true;
            boolean z3 = jSONObject != null && TextUtils.equals("1", jSONObject.optString(this.f2564j));
            if (!(this.d == 1 && z3) && this.a.getVisibility() == 0 && this.a.getParent() != null && this.a.getHeight() > 0 && this.a.getWidth() > 0) {
                float height = this.a.getHeight() * this.a.getWidth();
                if (this.a.getLocalVisibleRect(new Rect())) {
                    float height2 = (r0.height() * r0.width()) / height;
                    int[] iArr = new int[2];
                    this.a.getLocationInWindow(iArr);
                    if (iArr[0] == 0 && iArr[1] == 0) {
                        height2 = 0.0f;
                    }
                    View view = this.a;
                    int i4 = R.id.dynamic_expo_percents;
                    Object tag = view.getTag(i4);
                    if (!(tag instanceof Float)) {
                        this.a.setTag(i4, Float.valueOf(height2));
                        t.e("onPageScrolled1", Float.valueOf(height2));
                        return;
                    }
                    float floatValue = ((Float) tag).floatValue();
                    this.a.setTag(i4, Float.valueOf(height2));
                    float f3 = this.f2560f;
                    if (floatValue <= f3 || height2 >= f3 || this.f2559e) {
                        z = false;
                    } else {
                        t.e("onScrollChanged", "onPageScrolled", "\u4ece\u5927\u5230\u5c0f", Float.valueOf(height2), Float.valueOf(floatValue));
                        z = true;
                    }
                    float f4 = this.f2560f;
                    if (floatValue >= f4 || height2 <= f4 || !this.f2559e) {
                        z2 = z;
                    } else {
                        t.e("onScrollChanged", "onPageScrolled", "\u4ece\u5c0f\u5230\u5927", Float.valueOf(height2), Float.valueOf(floatValue));
                    }
                    if (z2) {
                        a("onPageScrolled");
                    }
                }
            }
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageSelected(int i2) {
    }

    public void preCal() {
        if (d()) {
            int[] iArr = new int[2];
            this.a.getLocationInWindow(iArr);
            if (this.a.getVisibility() != 0 || this.a.getParent() == null || ((iArr[0] == 0 && iArr[1] == 0) || this.a.getHeight() == 0 || this.a.getWidth() == 0)) {
                this.a.setTag(R.id.dynamic_expo_percents, Float.valueOf(0.0f));
                return;
            }
            float height = this.a.getHeight() * this.a.getWidth();
            if (this.a.getLocalVisibleRect(new Rect())) {
                float height2 = (r3.height() * r3.width()) / height;
                this.a.setTag(R.id.dynamic_expo_percents, Float.valueOf(height2));
                t.e("onScrollChanged", "pre2", Float.valueOf(height2));
                if (height2 >= this.f2560f) {
                    a("preCal");
                }
            }
        }
    }
}
