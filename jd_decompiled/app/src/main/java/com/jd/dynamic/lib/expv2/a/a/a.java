package com.jd.dynamic.lib.expv2.a.a;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.expv2.a.i;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public abstract class a extends i {
    @Nullable
    private final DynamicTemplateEngine a;
    @Nullable
    private final Object b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    private final View f2231c;

    public a(@Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable Object obj, @Nullable View view) {
        this.a = dynamicTemplateEngine;
        this.b = obj;
        this.f2231c = view;
    }

    public /* synthetic */ a(DynamicTemplateEngine dynamicTemplateEngine, Object obj, View view, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : dynamicTemplateEngine, (i2 & 2) != 0 ? null : obj, (i2 & 4) != 0 ? null : view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final DynamicTemplateEngine b() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final Object c() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final View d() {
        return this.f2231c;
    }
}
