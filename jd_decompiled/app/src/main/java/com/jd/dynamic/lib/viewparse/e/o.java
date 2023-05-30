package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.views.CollectionView;
import com.jd.dynamic.lib.views.ItemView;
import com.jd.dynamic.lib.views.TagView;

/* loaded from: classes13.dex */
public abstract class o<T extends ViewGroup> extends p<T> {

    /* renamed from: g  reason: collision with root package name */
    private com.jd.dynamic.lib.viewparse.g.b<ViewGroup.LayoutParams> f2459g = new com.jd.dynamic.lib.viewparse.g.c();

    /* JADX INFO: Access modifiers changed from: protected */
    public ViewGroup.LayoutParams i(Context context, ViewNode viewNode) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
        if (viewNode.getAttributes() == null) {
            return layoutParams;
        }
        com.jd.dynamic.lib.utils.m.z(viewNode.getAttributes());
        return this.f2459g.a(context, viewNode.getAttributes(), layoutParams);
    }

    public T j(ViewNode viewNode, Context context) {
        T t = (T) super.a(viewNode, context);
        for (ViewNode viewNode2 : viewNode.getChilds()) {
            if (TextUtils.equals(DYConstants.DY_ITEMS, viewNode2.getViewName())) {
                ItemView itemView = new ItemView(context, viewNode2, this.f2460c);
                itemView.setTag(1000);
                if (t instanceof CollectionView) {
                    ((CollectionView) t).addItemView(itemView);
                } else if (t instanceof com.jd.dynamic.lib.viewparse.b.carouselView.f) {
                    ((com.jd.dynamic.lib.viewparse.b.carouselView.f) t).h(itemView);
                } else if (t instanceof TagView) {
                    ((TagView) t).addItemView(itemView);
                }
            } else {
                p b = com.jd.dynamic.lib.viewparse.f.b(viewNode2.getViewName(), viewNode2.getAttributes(), this.f2460c, this.d, this.f2461e, this.f2462f);
                ViewGroup.LayoutParams i2 = i(context, viewNode2);
                if ((b instanceof p) && (b instanceof g)) {
                    if (com.jd.dynamic.b.a.b.o().g() && (viewNode2.getAttributes().get("width") == null || viewNode2.getAttributes().get("height") == null)) {
                        ((g) b).k();
                    } else {
                        ((g) b).l(i2);
                    }
                }
                t.addView((View) b.a(viewNode2, context), i2);
            }
        }
        return t;
    }
}
