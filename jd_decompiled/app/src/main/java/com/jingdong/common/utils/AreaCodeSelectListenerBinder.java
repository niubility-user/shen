package com.jingdong.common.utils;

import android.os.Binder;
import com.jingdong.common.utils.JDAreaCodeSelectUtil;

/* loaded from: classes6.dex */
public class AreaCodeSelectListenerBinder extends Binder {
    public JDAreaCodeSelectUtil.AreaCodeSelectListener areaCodeSelectListener;

    public AreaCodeSelectListenerBinder(JDAreaCodeSelectUtil.AreaCodeSelectListener areaCodeSelectListener) {
        this.areaCodeSelectListener = areaCodeSelectListener;
    }

    public JDAreaCodeSelectUtil.AreaCodeSelectListener getAreaCodeSelectListener() {
        return this.areaCodeSelectListener;
    }
}
