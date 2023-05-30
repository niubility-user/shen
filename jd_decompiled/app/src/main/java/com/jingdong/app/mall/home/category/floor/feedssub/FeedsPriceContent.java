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
public class FeedsPriceContent extends LinearLayout {
    private static final int CONTENT_WIDTH = 600;
    private static final int MAX_COUNT = 4;
    private static final int MAX_WIDTH = 290;
    private FeedsPrice mPrice;
    private List<TextView> mTagViewList;

    public FeedsPriceContent(Context context) {
        super(context);
        this.mTagViewList = new ArrayList();
        setOrientation(0);
        setGravity(16);
        this.mPrice = new FeedsPrice(getContext());
        addView(this.mPrice, new LinearLayout.LayoutParams(-2, -2));
        for (int i2 = 0; i2 < 4; i2++) {
            TextView textView = new TextView(getContext());
            textView.setSingleLine();
            this.mTagViewList.add(textView);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.leftMargin = DPIUtil.dip2px(4.0f);
            addView(textView, layoutParams);
        }
    }

    public void bindData(g gVar) {
        a.a(this, -2, d.d(600), DPIUtil.dip2px(10.0f));
        this.mPrice.a(gVar);
        bindTagData(gVar);
    }

    public void bindTagData(g gVar) {
        List<com.jingdong.app.mall.home.category.floor.feedssub.b.a> T = gVar.T();
        int size = T == null ? 0 : T.size();
        for (int i2 = 0; i2 < 4; i2++) {
            TextView textView = this.mTagViewList.get(i2);
            textView.setVisibility(8);
            if (i2 < size) {
                com.jingdong.app.mall.home.category.floor.feedssub.b.a aVar = T.get(i2);
                if (aVar.c()) {
                    textView.setVisibility(0);
                    textView.setText(aVar.b());
                    UnIconConfigHelper.setTextViewProperties(aVar.a(), textView);
                }
            }
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
            if (textView.getVisibility() == 0 && textView.getRight() > d.d(290)) {
                textView.setVisibility(8);
            }
        }
    }
}
