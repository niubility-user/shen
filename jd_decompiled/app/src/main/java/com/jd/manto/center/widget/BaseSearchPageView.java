package com.jd.manto.center.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes17.dex */
public abstract class BaseSearchPageView extends ScrollView {

    /* renamed from: g  reason: collision with root package name */
    protected SearchHistoryTagView f6552g;

    /* renamed from: h  reason: collision with root package name */
    protected List<String> f6553h;

    public BaseSearchPageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public abstract void a();

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        a();
    }

    public BaseSearchPageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        new ArrayList();
    }
}
