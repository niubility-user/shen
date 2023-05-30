package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.content.Context;
import android.view.View;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes8.dex */
public class l extends com.jingdong.sdk.lib.puppetlayout.h.a {

    /* renamed from: k  reason: collision with root package name */
    private View f15253k;

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void d(Context context) {
        View view = new View(context);
        this.f15253k = view;
        this.a = view;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public boolean q(String str, String str2, String str3) {
        if (!super.q(str, str2, str3) && str.hashCode() == -1106363674) {
            str.equals(CartConstant.KEY_LENGTH);
        }
        return true;
    }
}
