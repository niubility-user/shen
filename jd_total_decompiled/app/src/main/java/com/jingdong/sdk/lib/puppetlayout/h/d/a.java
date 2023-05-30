package com.jingdong.sdk.lib.puppetlayout.h.d;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* loaded from: classes8.dex */
public class a {
    public void a(View view, String str) {
        if (view instanceof TextView) {
            ((TextView) view).setGravity(com.jingdong.sdk.lib.puppetlayout.h.c.a.a(view.getContext(), str).a);
        } else if (view instanceof LinearLayout) {
            ((LinearLayout) view).setGravity(com.jingdong.sdk.lib.puppetlayout.h.c.a.a(view.getContext(), str).a);
        } else if (view instanceof RelativeLayout) {
            ((RelativeLayout) view).setGravity(com.jingdong.sdk.lib.puppetlayout.h.c.a.a(view.getContext(), str).a);
        }
    }
}
