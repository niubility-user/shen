package com.jd.lib.cashier.sdk.pay.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes14.dex */
public class CustomDrawOrderRecyclerView extends RecyclerView {

    /* renamed from: g  reason: collision with root package name */
    public int f4172g;

    /* renamed from: h  reason: collision with root package name */
    public int f4173h;

    public CustomDrawOrderRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4172g = -1;
        this.f4173h = -1;
        setChildrenDrawingOrderEnabled(true);
    }

    private boolean a(int i2, int i3) {
        return i2 >= 0 && i2 < i3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public int getChildDrawingOrder(int i2, int i3) {
        int i4 = this.f4172g;
        if (i4 != -1 && i3 == i4) {
            int i5 = i3 + 1;
            if (a(i5, i2)) {
                return i5;
            }
        }
        int i6 = this.f4173h;
        if (i6 != -1 && i3 == i6) {
            int i7 = i2 - 1;
            if (a(i7, i2)) {
                return i7;
            }
        }
        int i8 = this.f4172g;
        if (i8 != -1 && i3 - 1 == i8 && a(i8, i2)) {
            return this.f4172g;
        }
        int i9 = this.f4173h;
        if (i9 != -1 && i3 == i2 - 1 && a(i9, i2)) {
            return this.f4173h;
        }
        return super.getChildDrawingOrder(i2, i3);
    }

    public CustomDrawOrderRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f4172g = -1;
        this.f4173h = -1;
        setChildrenDrawingOrderEnabled(true);
    }
}
