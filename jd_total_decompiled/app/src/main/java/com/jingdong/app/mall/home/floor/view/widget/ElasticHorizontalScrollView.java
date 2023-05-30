package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import com.jingdong.app.mall.home.floor.common.d;

/* loaded from: classes4.dex */
public class ElasticHorizontalScrollView extends HorizontalScrollView {

    /* renamed from: g  reason: collision with root package name */
    private int f10039g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f10040h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f10041i;

    /* renamed from: j  reason: collision with root package name */
    private float f10042j;

    public ElasticHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10040h = false;
        this.f10041i = true;
        this.f10042j = 0.5f;
        a();
    }

    private void a() {
        this.f10039g = d.d(280);
    }

    @Override // android.view.View
    protected boolean overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
        int i10;
        int i11;
        int i12;
        int i13 = this.f10039g;
        if (this.f10040h) {
            if (i2 < 0 && i2 + i4 < 0) {
                i10 = (int) (i2 * this.f10042j);
            }
            i10 = i2;
        } else {
            if (i2 < 0 && i2 + i4 < 0) {
                i10 = i2;
                i13 = 0;
            }
            i10 = i2;
        }
        if (this.f10041i) {
            if (i2 > 0 && i2 + i4 > i6) {
                i11 = i13;
                i12 = (int) (i2 * this.f10042j);
            }
            i11 = i13;
            i12 = i10;
        } else {
            if (i2 > 0 && i2 + i4 > i6) {
                i12 = i10;
                i11 = 0;
            }
            i11 = i13;
            i12 = i10;
        }
        return super.overScrollBy(i12, i3, i4, i5, i6, i7, i11, i9, z);
    }

    public ElasticHorizontalScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f10040h = false;
        this.f10041i = true;
        this.f10042j = 0.5f;
        a();
    }
}
