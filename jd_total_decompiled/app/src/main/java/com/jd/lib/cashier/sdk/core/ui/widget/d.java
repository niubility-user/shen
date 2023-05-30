package com.jd.lib.cashier.sdk.core.ui.widget;

import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes14.dex */
final class d implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    private final /* synthetic */ Function1 f3086g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Function1 function1) {
        this.f3086g = function1;
    }

    @Override // android.view.View.OnClickListener
    public final /* synthetic */ void onClick(View view) {
        Intrinsics.checkExpressionValueIsNotNull(this.f3086g.invoke(view), "invoke(...)");
    }
}
