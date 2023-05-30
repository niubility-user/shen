package com.jingdong.app.mall.home.category.floor.decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.category.floor.floorsub.CaMoreSubFloor;
import com.jingdong.app.mall.home.floor.common.d;

/* loaded from: classes4.dex */
public class CaDividerDecoration extends RecyclerView.ItemDecoration {
    private int a;

    /* renamed from: c  reason: collision with root package name */
    private int f8685c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f8686e;

    /* renamed from: g  reason: collision with root package name */
    private Paint f8688g;
    private int b = d.d(4);

    /* renamed from: f  reason: collision with root package name */
    private boolean f8687f = false;

    public CaDividerDecoration() {
        Paint paint = new Paint(1);
        this.f8688g = paint;
        paint.setColor(0);
    }

    private void c(View view, Canvas canvas) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RecyclerView.LayoutParams) {
            int viewLayoutPosition = ((RecyclerView.LayoutParams) layoutParams).getViewLayoutPosition();
            int i2 = this.f8685c;
            boolean z = viewLayoutPosition % i2 == 0;
            boolean z2 = viewLayoutPosition < i2;
            if (this.b > 0 && !z) {
                canvas.drawRect(view.getLeft() - this.b, view.getTop(), view.getLeft(), view.getBottom(), this.f8688g);
            }
            if (this.a <= 0 || z2) {
                return;
            }
            canvas.drawRect(view.getLeft() - (z ? 0 : this.b), view.getTop() - this.a, view.getRight(), view.getTop(), this.f8688g);
        }
    }

    private void e(View view, Canvas canvas) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RecyclerView.LayoutParams) {
            int viewLayoutPosition = ((RecyclerView.LayoutParams) layoutParams).getViewLayoutPosition();
            int i2 = this.f8685c;
            boolean z = viewLayoutPosition % i2 == 0;
            boolean z2 = viewLayoutPosition < i2;
            if (this.b > 0 && !z) {
                canvas.drawRect(view.getLeft() - (this.b / 2.0f), view.getTop() + this.d, view.getLeft() + (this.b / 2.0f), view.getBottom() - this.f8686e, this.f8688g);
            }
            if (this.a <= 0 || z2) {
                return;
            }
            canvas.drawRect(view.getLeft(), view.getTop() + (this.a / 2.0f), view.getRight() + (z ? 0 : this.b), view.getTop() - (this.a / 2.0f), this.f8688g);
        }
    }

    public CaDividerDecoration a(int i2) {
        this.f8686e = d.d(i2);
        return this;
    }

    public CaDividerDecoration b(int i2) {
        this.f8688g.setColor(i2);
        return this;
    }

    public CaDividerDecoration d(boolean z) {
        this.f8687f = z;
        return this;
    }

    public int f() {
        return this.a;
    }

    public CaDividerDecoration g(int i2) {
        this.f8685c = i2;
        return this;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (this.f8687f) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RecyclerView.LayoutParams) {
            int viewLayoutPosition = ((RecyclerView.LayoutParams) layoutParams).getViewLayoutPosition();
            int i2 = this.f8685c;
            rect.left = viewLayoutPosition % i2 == 0 ? 0 : this.b;
            rect.top = viewLayoutPosition >= i2 ? this.a : 0;
        }
    }

    public CaDividerDecoration h(int i2) {
        this.a = d.d(i2);
        return this;
    }

    public CaDividerDecoration i(int i2) {
        this.d = d.d(i2);
        return this;
    }

    public CaDividerDecoration j(int i2) {
        this.b = d.d(i2);
        return this;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
        if (this.f8687f) {
            return;
        }
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            if (childAt != null) {
                c(childAt, canvas);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        super.onDrawOver(canvas, recyclerView, state);
        if (!this.f8687f || this.f8688g.getColor() == 0) {
            return;
        }
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            if (childAt != null && !(childAt instanceof CaMoreSubFloor)) {
                e(childAt, canvas);
            }
        }
    }
}
