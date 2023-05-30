package com.jd.lib.cashier.sdk.pay.dialog.k.e;

import com.jd.lib.cashier.sdk.core.model.ExitRetainOptionEntity;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class e {
    @NotNull
    private final ArrayList<ExitRetainOptionEntity> a;

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.jvm.internal.DefaultConstructorMarker, java.util.ArrayList] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public e() {
        this(r0, 1, r0);
        ?? r0 = 0;
    }

    public e(@NotNull ArrayList<ExitRetainOptionEntity> arrayList) {
        this.a = arrayList;
    }

    @NotNull
    public final ArrayList<ExitRetainOptionEntity> a() {
        return this.a;
    }

    public /* synthetic */ e(ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new ArrayList() : arrayList);
    }
}
