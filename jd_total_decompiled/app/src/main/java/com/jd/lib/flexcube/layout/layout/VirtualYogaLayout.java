package com.jd.lib.flexcube.layout.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.yoga.YogaNode;
import com.jd.lib.flexcube.layout.layout.YogaLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes14.dex */
public class VirtualYogaLayout extends ViewGroup {

    /* renamed from: g */
    private final List<View> f4417g;

    /* renamed from: h */
    private final Map<View, YogaNode> f4418h;

    /* renamed from: i */
    private final YogaNode f4419i;

    public VirtualYogaLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void a(View view, YogaNode yogaNode) {
        this.f4417g.add(view);
        this.f4418h.put(view, yogaNode);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof VirtualYogaLayout) {
            VirtualYogaLayout virtualYogaLayout = (VirtualYogaLayout) view;
            virtualYogaLayout.c(this);
            YogaNode b = virtualYogaLayout.b();
            YogaNode yogaNode = this.f4419i;
            yogaNode.addChildAt(b, yogaNode.getChildCount());
            return;
        }
        YogaNode yogaNode2 = new YogaNode();
        YogaLayout.b(new YogaLayout.LayoutParams(layoutParams), yogaNode2, view);
        yogaNode2.setData(view);
        yogaNode2.setMeasureFunction(new YogaLayout.a());
        YogaNode yogaNode3 = this.f4419i;
        yogaNode3.addChildAt(yogaNode2, yogaNode3.getChildCount());
        a(view, yogaNode2);
    }

    public YogaNode b() {
        return this.f4419i;
    }

    public void c(ViewGroup viewGroup) {
        if (viewGroup instanceof VirtualYogaLayout) {
            for (View view : this.f4417g) {
                ((VirtualYogaLayout) viewGroup).a(view, this.f4418h.get(view));
            }
        } else if (viewGroup instanceof YogaLayout) {
            for (View view2 : this.f4417g) {
                ((YogaLayout) viewGroup).a(view2, this.f4418h.get(view2));
            }
        } else {
            throw new RuntimeException("VirtualYogaLayout cannot transfer children to ViewGroup of type " + viewGroup.getClass().getCanonicalName() + ".  Must either be a VirtualYogaLayout or a YogaLayout.");
        }
        this.f4417g.clear();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof YogaLayout.LayoutParams;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new YogaLayout.LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new YogaLayout.LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        throw new RuntimeException("Attempting to layout a VirtualYogaLayout");
    }

    public VirtualYogaLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f4417g = new LinkedList();
        this.f4418h = new HashMap();
        YogaNode yogaNode = new YogaNode();
        this.f4419i = yogaNode;
        YogaLayout.b(new YogaLayout.LayoutParams(context, attributeSet), yogaNode, this);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new YogaLayout.LayoutParams(layoutParams);
    }
}
