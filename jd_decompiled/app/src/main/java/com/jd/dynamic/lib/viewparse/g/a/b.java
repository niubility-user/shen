package com.jd.dynamic.lib.viewparse.g.a;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.lib.viewparse.d;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class b implements com.jd.dynamic.lib.viewparse.g.b<ViewGroup.LayoutParams> {
    @Override // com.jd.dynamic.lib.viewparse.g.b
    public ViewGroup.LayoutParams a(Context context, HashMap<String, String> hashMap, ViewGroup.LayoutParams layoutParams) {
        String str = hashMap.get("width");
        String str2 = hashMap.get("height");
        if (!TextUtils.isEmpty(str)) {
            String replace = str.replace("S", "");
            if (DYConstants.DY_WRAP_CONTENT.equals(replace)) {
                layoutParams.width = -2;
            } else if (DYConstants.DY_MATCH_PARENT.equals(replace)) {
                layoutParams.width = -1;
            } else {
                layoutParams.width = (int) d.a(replace, context);
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            String replace2 = str2.replace("S", "");
            if (DYConstants.DY_WRAP_CONTENT.equals(replace2)) {
                layoutParams.height = -2;
            } else if (DYConstants.DY_MATCH_PARENT.equals(replace2)) {
                layoutParams.height = -1;
            } else {
                layoutParams.height = (int) d.a(replace2, context);
            }
        }
        return layoutParams;
    }
}
