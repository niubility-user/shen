package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.content.Context;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.lib.puppetlayout.ylayout.android.YogaLayout;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Iterate;

/* loaded from: classes8.dex */
public class p extends com.jingdong.sdk.lib.puppetlayout.h.a {

    /* renamed from: k  reason: collision with root package name */
    private YogaLayout f15259k;

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void d(Context context) {
        YogaLayout yogaLayout = new YogaLayout(context);
        this.f15259k = yogaLayout;
        this.a = yogaLayout;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void m(JDJSONObject jDJSONObject, JDJSONArray jDJSONArray, Iterate iterate) {
        YogaLayout yogaLayout;
        if ((jDJSONObject == null && (jDJSONArray == null || jDJSONArray.size() == 0)) || (yogaLayout = this.f15259k) == null || iterate == null || iterate.itemViewBase == null) {
            return;
        }
        yogaLayout.removeAllViews();
        int i2 = 0;
        while (i2 < jDJSONArray.size()) {
            ViewGroup createItemView = iterate.createItemView(yogaLayout.getContext(), this.b);
            if (jDJSONObject != null) {
                iterate.bindData(createItemView, jDJSONObject);
            }
            Object optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject == null) {
                optJSONObject = jDJSONArray.optString(i2);
            }
            Object obj = optJSONObject;
            if (obj != null) {
                iterate.bindItemData(createItemView, obj, i2 == 0, i2 == jDJSONArray.size() - 1, i2);
            }
            this.f15259k.addView(createItemView);
            i2++;
        }
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public boolean q(String str, String str2, String str3) {
        if (super.q(str, str2, str3)) {
        }
        return true;
    }
}
