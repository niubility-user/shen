package com.jd.lib.cashier.sdk.core.utils;

import android.view.ViewGroup;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.ui.loading.ILoading;

/* loaded from: classes14.dex */
public class q {
    public static void a(ViewGroup viewGroup) {
        ILoading loading = DependInitializer.getLoading();
        if (viewGroup == null || loading == null) {
            return;
        }
        loading.onDestroyLoading(viewGroup);
    }

    public static void b(ViewGroup viewGroup) {
        ILoading loading = DependInitializer.getLoading();
        if (viewGroup == null || loading == null) {
            return;
        }
        loading.hideLoading(viewGroup);
    }

    public static void c(ViewGroup viewGroup) {
        ILoading loading = DependInitializer.getLoading();
        if (viewGroup == null || loading == null) {
            return;
        }
        loading.showLoading(viewGroup);
    }
}
