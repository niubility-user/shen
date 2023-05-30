package com.jd.dynamic.lib.viewparse.c;

import android.text.TextUtils;
import android.view.View;
import android.widget.HorizontalScrollView;
import com.jd.dynamic.DYConstants;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class i extends f<HorizontalScrollView> {
    @Override // com.jd.dynamic.lib.viewparse.c.j
    public /* bridge */ /* synthetic */ View a(HashMap hashMap, View view) {
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) view;
        d(hashMap, horizontalScrollView);
        return horizontalScrollView;
    }

    public HorizontalScrollView d(HashMap<String, String> hashMap, HorizontalScrollView horizontalScrollView) {
        if (hashMap.containsKey(DYConstants.DY_SCROLL_INDICATOR)) {
            String str = hashMap.get(DYConstants.DY_SCROLL_INDICATOR);
            if (!TextUtils.isEmpty(str)) {
                if (!"none".equals(str)) {
                    if (DYConstants.DY_SCROLL_VERTICAL.equals(str)) {
                        horizontalScrollView.setVerticalScrollBarEnabled(true);
                        horizontalScrollView.setHorizontalScrollBarEnabled(false);
                    } else {
                        if (DYConstants.DY_SCROLL_HORIZONTAL.equals(str)) {
                            horizontalScrollView.setVerticalScrollBarEnabled(false);
                        } else if (DYConstants.DY_SCROLL_BOTH.equals(str)) {
                            horizontalScrollView.setVerticalScrollBarEnabled(true);
                        }
                        horizontalScrollView.setHorizontalScrollBarEnabled(true);
                    }
                }
            }
            return horizontalScrollView;
        }
        horizontalScrollView.setVerticalScrollBarEnabled(false);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        return horizontalScrollView;
    }
}
