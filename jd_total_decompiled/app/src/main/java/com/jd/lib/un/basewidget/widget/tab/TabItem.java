package com.jd.lib.un.basewidget.widget.tab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.jd.lib.un.basewidget.R;

/* loaded from: classes16.dex */
public final class TabItem extends View {

    /* renamed from: g  reason: collision with root package name */
    final CharSequence f5845g;

    /* renamed from: h  reason: collision with root package name */
    final Drawable f5846h;

    /* renamed from: i  reason: collision with root package name */
    final int f5847i;

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TabItem);
        this.f5845g = obtainStyledAttributes.getText(R.styleable.TabItem_android_text);
        this.f5846h = obtainStyledAttributes.getDrawable(R.styleable.TabItem_android_icon);
        this.f5847i = obtainStyledAttributes.getResourceId(R.styleable.TabItem_android_layout, 0);
        obtainStyledAttributes.recycle();
    }
}
