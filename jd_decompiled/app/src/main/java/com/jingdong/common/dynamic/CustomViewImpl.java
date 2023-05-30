package com.jingdong.common.dynamic;

import android.content.Context;
import android.view.View;
import com.jd.dynamic.base.interfaces.ICustomView;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CustomViewImpl implements ICustomView {
    @Override // com.jd.dynamic.base.interfaces.ICustomView
    public View getCustomView(Context context, String str) {
        str.hashCode();
        if (!str.equals("VideoPlayerView")) {
            return new View(context);
        }
        return new DynamicVideoPlayView(context);
    }

    @Override // com.jd.dynamic.base.interfaces.ICustomView
    public View parse(HashMap<String, String> hashMap, View view) {
        if (view instanceof DynamicVideoPlayView) {
            ((DynamicVideoPlayView) view).setAttributes(hashMap);
        }
        return view;
    }
}
