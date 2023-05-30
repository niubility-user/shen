package com.jd.manto.center.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.manto.center.R;
import com.jd.manto.center.k.c;

/* loaded from: classes17.dex */
public class MantoDiscoveryDecoration extends RecyclerView.ItemDecoration {
    private Paint a;

    public MantoDiscoveryDecoration(Context context) {
        Paint paint = new Paint();
        this.a = paint;
        paint.setColor(context.getResources().getColor(R.color.c_ffffff));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int a;
        if (recyclerView.getAdapter() != null) {
            if (recyclerView.getChildAdapterPosition(view) == 0) {
                a = c.a(23.0f);
            } else {
                a = c.a(18.0f);
            }
            rect.set(0, 0, 0, a);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        int a;
        super.onDraw(canvas, recyclerView, state);
        int childCount = recyclerView.getChildCount();
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            if (i2 == 0) {
                a = c.a(23.0f);
            } else {
                a = c.a(18.0f);
            }
            canvas.drawRect(paddingLeft, childAt.getBottom(), width, childAt.getBottom() + a, this.a);
        }
    }
}
