package com.jd.lib.cashier.sdk.pay.adapter;

import android.view.View;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class a {
    @NotNull
    public static final String a(@NotNull View view) {
        return view.getVisibility() == 0 ? String.valueOf(view.getContentDescription()) : "";
    }
}
