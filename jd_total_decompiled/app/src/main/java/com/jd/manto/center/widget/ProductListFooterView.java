package com.jd.manto.center.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.jd.manto.center.R;
import com.jingdong.common.utils.ImageUtil;

/* loaded from: classes17.dex */
public class ProductListFooterView extends LinearLayout {
    public ProductListFooterView(Context context) {
        this(context, null);
    }

    private void init() {
        ImageUtil.inflate(getContext(), R.layout.lib_search_product_list_footer_layout, this);
    }

    public ProductListFooterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }
}
