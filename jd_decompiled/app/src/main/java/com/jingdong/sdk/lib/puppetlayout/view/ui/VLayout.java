package com.jingdong.sdk.lib.puppetlayout.view.ui;

import android.content.Context;
import android.widget.LinearLayout;
import com.jingdong.sdk.lib.puppetlayout.h.c.a;

/* loaded from: classes8.dex */
public class VLayout extends LinearLayout {
    public static final String NAME = "VLayout";

    public VLayout(Context context) {
        super(context);
        setOrientation(1);
    }

    public void setAlign(String str) {
        setGravity(a.a(getContext(), str).a);
    }
}
