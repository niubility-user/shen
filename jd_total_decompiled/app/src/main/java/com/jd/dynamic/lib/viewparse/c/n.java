package com.jd.dynamic.lib.viewparse.c;

import android.text.TextUtils;
import android.view.View;
import android.widget.ScrollView;
import com.jd.dynamic.DYConstants;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class n extends f<ScrollView> {
    @Override // com.jd.dynamic.lib.viewparse.c.j
    public /* bridge */ /* synthetic */ View a(HashMap hashMap, View view) {
        ScrollView scrollView = (ScrollView) view;
        d(hashMap, scrollView);
        return scrollView;
    }

    public ScrollView d(HashMap<String, String> hashMap, ScrollView scrollView) {
        if (hashMap.containsKey(DYConstants.DY_SCROLL_INDICATOR)) {
            String str = hashMap.get(DYConstants.DY_SCROLL_INDICATOR);
            if (!TextUtils.isEmpty(str)) {
                if (!"none".equals(str)) {
                    if (DYConstants.DY_SCROLL_VERTICAL.equals(str)) {
                        scrollView.setVerticalScrollBarEnabled(true);
                        scrollView.setHorizontalScrollBarEnabled(false);
                    } else {
                        if (DYConstants.DY_SCROLL_HORIZONTAL.equals(str)) {
                            scrollView.setVerticalScrollBarEnabled(false);
                        } else if (DYConstants.DY_SCROLL_BOTH.equals(str)) {
                            scrollView.setVerticalScrollBarEnabled(true);
                        }
                        scrollView.setHorizontalScrollBarEnabled(true);
                    }
                }
            }
            scrollView.setFillViewport(true);
            return scrollView;
        }
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setFillViewport(true);
        return scrollView;
    }
}
