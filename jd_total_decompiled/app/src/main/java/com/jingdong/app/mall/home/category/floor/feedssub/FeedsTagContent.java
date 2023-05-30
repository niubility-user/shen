package com.jingdong.app.mall.home.category.floor.feedssub;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.n.g.g;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class FeedsTagContent extends LinearLayout {
    private static final int CONTENT_WIDTH = 600;
    private static final int MAX_COUNT = 4;
    private static final int MAX_WIDTH = 290;
    private List<TextView> mTagViewList;

    public FeedsTagContent(Context context) {
        super(context);
        this.mTagViewList = new ArrayList();
        setOrientation(0);
        for (int i2 = 0; i2 < 4; i2++) {
            this.mTagViewList.add(new TextView(getContext()));
        }
    }

    public void bindData(g gVar) {
        removeAllViews();
        List<com.jingdong.app.mall.home.category.floor.feedssub.b.a> T = gVar.T();
        boolean z = T == null || T.size() <= 0;
        a.a(this, z ? 0 : DPIUtil.dip2px(12.0f), d.d(600), z ? 0 : DPIUtil.dip2px(6.0f));
        if (z) {
            return;
        }
        int size = T.size();
        int i2 = 0;
        while (i2 < 4) {
            TextView textView = this.mTagViewList.get(i2);
            textView.setTranslationX(0.0f);
            if (i2 < size) {
                com.jingdong.app.mall.home.category.floor.feedssub.b.a aVar = T.get(i2);
                if (aVar.c()) {
                    textView.setText(aVar.b());
                    UnIconConfigHelper.setTextViewProperties(aVar.a(), textView);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
                    layoutParams.leftMargin = i2 == 0 ? 0 : DPIUtil.dip2px(3.0f);
                    addView(textView, layoutParams);
                }
            }
            i2++;
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (getHeight() == 0) {
            return;
        }
        for (int i6 = 0; i6 < 4; i6++) {
            TextView textView = this.mTagViewList.get(i6);
            if (textView.getRight() > d.d(290)) {
                textView.setTranslationX(getWidth());
            }
        }
    }
}
