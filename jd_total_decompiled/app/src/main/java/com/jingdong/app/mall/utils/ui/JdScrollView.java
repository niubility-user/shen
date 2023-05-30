package com.jingdong.app.mall.utils.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/* loaded from: classes4.dex */
public class JdScrollView extends ScrollView {

    /* renamed from: g  reason: collision with root package name */
    private a f11867g;

    /* loaded from: classes4.dex */
    public interface a {
        void onScrollChanged(int i2, int i3, int i4, int i5);
    }

    public JdScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        a aVar = this.f11867g;
        if (aVar != null) {
            aVar.onScrollChanged(i2, i3, i4, i5);
        }
    }

    public JdScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
