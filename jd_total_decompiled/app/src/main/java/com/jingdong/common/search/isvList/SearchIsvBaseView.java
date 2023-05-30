package com.jingdong.common.search.isvList;

import android.content.Context;
import android.widget.FrameLayout;
import com.jd.framework.json.JDJSONObject;

/* loaded from: classes6.dex */
public abstract class SearchIsvBaseView extends FrameLayout {
    private ProductListCardInterface cardInterface;

    public SearchIsvBaseView(Context context, ProductListCardInterface productListCardInterface) {
        super(context);
        this.cardInterface = productListCardInterface;
    }

    public abstract void handleEvent(JDJSONObject jDJSONObject, String str, int i2);

    public void onPause() {
    }

    public void onResume() {
    }
}
