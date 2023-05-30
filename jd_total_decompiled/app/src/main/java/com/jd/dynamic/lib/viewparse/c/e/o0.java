package com.jd.dynamic.lib.viewparse.c.e;

import android.view.View;
import android.view.ViewGroup;
import com.jd.dynamic.yoga.YogaNode;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class o0 implements q0<View> {
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    public void a(HashMap<String, String> hashMap, View view) {
        YogaNode yogaNodeForView;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        YogaLayout.LayoutParams layoutParams2 = new YogaLayout.LayoutParams(-2, -2);
        com.jd.dynamic.lib.viewparse.g.d dVar = new com.jd.dynamic.lib.viewparse.g.d();
        if (!(layoutParams instanceof YogaLayout.LayoutParams)) {
            if (view instanceof YogaLayout) {
                view.setLayoutParams(layoutParams2);
                dVar.a(view.getContext(), hashMap, layoutParams2);
                if (layoutParams2.hasChange) {
                    YogaLayout.applyLayoutParams(layoutParams2, ((YogaLayout) view).getYogaNode(), view);
                    return;
                }
                return;
            }
            return;
        }
        YogaLayout.LayoutParams layoutParams3 = (YogaLayout.LayoutParams) layoutParams;
        dVar.a(view.getContext(), hashMap, layoutParams3);
        if (layoutParams3.hasChange) {
            if (view instanceof YogaLayout) {
                yogaNodeForView = ((YogaLayout) view).getYogaNode();
            } else if (!(view.getParent() instanceof YogaLayout)) {
                return;
            } else {
                yogaNodeForView = ((YogaLayout) view.getParent()).getYogaNodeForView(view);
            }
            YogaLayout.applyLayoutParams(layoutParams3, yogaNodeForView, view);
        }
    }

    public void b(HashMap<String, String> hashMap, View view) {
        YogaLayout yogaLayout;
        YogaLayout.LayoutParams yogaLayoutLayoutParams;
        com.jd.dynamic.lib.viewparse.g.d dVar = new com.jd.dynamic.lib.viewparse.g.d();
        if (!(view instanceof YogaLayout) || (yogaLayoutLayoutParams = (yogaLayout = (YogaLayout) view).getYogaLayoutLayoutParams()) == null) {
            return;
        }
        dVar.a(view.getContext(), hashMap, yogaLayoutLayoutParams);
        if (yogaLayoutLayoutParams.hasChange) {
            YogaLayout.applyLayoutParams(yogaLayoutLayoutParams, yogaLayout.getYogaNode(), view);
        }
    }
}
