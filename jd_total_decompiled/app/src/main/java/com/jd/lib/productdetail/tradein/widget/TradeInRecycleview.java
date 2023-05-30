package com.jd.lib.productdetail.tradein.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.tradein.widget.TradeInPropIDialog;

/* loaded from: classes16.dex */
public class TradeInRecycleview extends RecyclerView {

    /* renamed from: g */
    public int f5642g;

    /* renamed from: h */
    public b f5643h;

    /* loaded from: classes16.dex */
    public class a extends RecyclerView.OnScrollListener {
        public a() {
            TradeInRecycleview.this = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            TradeInPropIDialog.a.C0163a c0163a;
            LinearLayout linearLayout;
            super.onScrollStateChanged(recyclerView, i2);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                int findFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                TradeInRecycleview tradeInRecycleview = TradeInRecycleview.this;
                if (tradeInRecycleview.f5642g != findFirstVisibleItemPosition) {
                    tradeInRecycleview.f5642g = findFirstVisibleItemPosition;
                    b bVar = tradeInRecycleview.f5643h;
                    if (bVar == null || (linearLayout = (c0163a = (TradeInPropIDialog.a.C0163a) bVar).a) == null || findFirstVisibleItemPosition >= linearLayout.getChildCount()) {
                        return;
                    }
                    for (int i3 = 0; i3 < c0163a.a.getChildCount(); i3++) {
                        c0163a.a.getChildAt(i3).setBackgroundColor(Color.parseColor("#F2F2F2"));
                    }
                    c0163a.a.getChildAt(findFirstVisibleItemPosition).setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_FA2C19));
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface b {
    }

    public TradeInRecycleview(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5642g = 0;
        a();
    }

    public final void a() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(0);
        setLayoutManager(linearLayoutManager);
        new PagerSnapHelper().attachToRecyclerView(this);
        addOnScrollListener(new a());
    }

    public void b(b bVar) {
        this.f5643h = bVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }
}
