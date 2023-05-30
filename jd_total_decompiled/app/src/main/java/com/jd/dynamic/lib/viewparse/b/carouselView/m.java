package com.jd.dynamic.lib.viewparse.b.carouselView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.viewpager.widget.PagerAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.jd.dynamic.R;

/* loaded from: classes13.dex */
public class m extends PullToRefreshBase<d> {
    private String a;
    private String b;

    /* renamed from: c */
    private String f2360c;
    private a d;

    public m(Context context) {
        super(context);
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    /* renamed from: a */
    public d createRefreshableView(Context context, AttributeSet attributeSet) {
        d dVar = new d(context, attributeSet);
        dVar.w(400);
        dVar.setId(R.id.pd_big_image_ptrviewpager_bundle);
        return dVar;
    }

    public void a(String str) {
        this.a = str;
        a aVar = this.d;
        if (aVar != null) {
            aVar.c(str);
        }
    }

    public void b(String str) {
        this.b = str;
        a aVar = this.d;
        if (aVar != null) {
            aVar.e(str);
        }
    }

    public void c(String str) {
        this.f2360c = str;
        a aVar = this.d;
        if (aVar != null) {
            aVar.d(str);
        }
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public BaseLoadingLayout createLoadingLayout(Context context, PullToRefreshBase.Mode mode, TypedArray typedArray) {
        a aVar = new a(context);
        this.d = aVar;
        aVar.c(this.a);
        this.d.e(this.b);
        this.d.d(this.f2360c);
        return this.d;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.HORIZONTAL;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullEnd() {
        d refreshableView = getRefreshableView();
        PagerAdapter adapter = refreshableView.getAdapter();
        return adapter != null && refreshableView.getCurrentItem() == adapter.getCount() - 1;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullStart() {
        d refreshableView = getRefreshableView();
        return refreshableView.getAdapter() != null && refreshableView.getCurrentItem() == 0;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public void onRefreshing(boolean z) {
        super.onRefreshing(false);
    }
}
