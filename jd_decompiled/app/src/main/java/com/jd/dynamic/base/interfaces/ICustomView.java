package com.jd.dynamic.base.interfaces;

import android.content.Context;
import android.view.View;
import java.util.HashMap;

/* loaded from: classes13.dex */
public interface ICustomView {
    View getCustomView(Context context, String str);

    View parse(HashMap<String, String> hashMap, View view);
}
