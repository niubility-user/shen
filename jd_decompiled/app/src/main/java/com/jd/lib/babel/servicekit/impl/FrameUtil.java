package com.jd.lib.babel.servicekit.impl;

import android.app.Activity;
import android.content.Context;
import com.jd.lib.babel.servicekit.iservice.IFrame;

/* loaded from: classes13.dex */
public class FrameUtil implements IFrame {
    @Override // com.jd.lib.babel.servicekit.iservice.IFrame
    public Activity getActivity(Context context) {
        return CommTools.getActivity(context);
    }
}
