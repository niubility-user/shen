package com.jd.dynamic.lib.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jd.dynamic.base.DynamicTemplateEngine;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes13.dex */
public class RichTextViewContainer extends FrameLayout {

    /* renamed from: g */
    private int f2509g;

    /* renamed from: h */
    private String f2510h;

    /* renamed from: i */
    private RichTextView f2511i;

    /* renamed from: j */
    private List<SpanView> f2512j;
    public int maxlines;

    public RichTextViewContainer(@NonNull Context context) {
        this(context, null);
    }

    public RichTextViewContainer(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RichTextViewContainer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2509g = Integer.MAX_VALUE;
        this.maxlines = Integer.MAX_VALUE;
        this.f2512j = new ArrayList();
        a();
    }

    @RequiresApi(api = 21)
    public RichTextViewContainer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f2509g = Integer.MAX_VALUE;
        this.maxlines = Integer.MAX_VALUE;
        this.f2512j = new ArrayList();
    }

    private void a() {
        RichTextView richTextView = new RichTextView(getContext());
        this.f2511i = richTextView;
        super.addView(richTextView, new FrameLayout.LayoutParams(-2, -2));
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        view.setVisibility(8);
        if (view instanceof SpanView) {
            this.f2512j.add((SpanView) view);
            super.addView(view);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        view.setVisibility(8);
        if (view instanceof SpanView) {
            this.f2512j.add((SpanView) view);
            super.addView(view, layoutParams);
        }
    }

    public void attachEngine(DynamicTemplateEngine dynamicTemplateEngine) {
        this.f2511i.setData(dynamicTemplateEngine);
    }

    public String getCloseSufix() {
        return this.f2510h;
    }

    public int getRichMaxlines() {
        return this.f2509g;
    }

    public List<SpanView> getSpanList() {
        return this.f2512j;
    }

    public boolean isRichExpand() {
        return this.f2511i.isRichExpand();
    }

    public void parseAttribute() {
        this.f2511i.parseAttribute();
    }

    public void setCloseSufix(String str) {
        this.f2510h = str;
        RichTextView richTextView = this.f2511i;
        if (richTextView != null) {
            richTextView.setSuffixText(str);
        }
    }

    public void setExpendable(boolean z) {
        this.f2511i.setExpendable(z);
    }

    public void setMaxLines(int i2) {
        this.maxlines = i2;
        this.f2511i.setMaxLines(i2);
    }

    public void setRichMaxlines(int i2) {
        this.f2509g = i2;
        RichTextView richTextView = this.f2511i;
        if (richTextView != null) {
            richTextView.setMaxLines(i2);
        }
    }

    public void setRichTextColor(int i2) {
        this.f2511i.setRichTextColor(i2);
    }

    public void setRichTextSize(float f2) {
        this.f2511i.setRichTextSize(f2);
    }

    public void setRichTextStyle(Typeface typeface) {
        this.f2511i.setRichTextStyle(typeface);
    }
}
