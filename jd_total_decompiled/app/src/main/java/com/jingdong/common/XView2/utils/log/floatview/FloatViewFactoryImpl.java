package com.jingdong.common.XView2.utils.log.floatview;

import android.content.Context;

/* loaded from: classes5.dex */
public class FloatViewFactoryImpl implements IFloatViewFactory {
    @Override // com.jingdong.common.XView2.utils.log.floatview.IFloatViewFactory
    public FloatBase getXViewFloatWindow(Context context) {
        return new XViewFloatView(context);
    }
}
