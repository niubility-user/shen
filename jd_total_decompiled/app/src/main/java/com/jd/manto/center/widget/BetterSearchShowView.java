package com.jd.manto.center.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.jd.manto.center.R;
import com.jingdong.common.entity.MiniProgramSearchHistory;
import com.jingdong.common.utils.ImageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes17.dex */
public class BetterSearchShowView extends BaseSearchPageView {

    /* renamed from: i  reason: collision with root package name */
    private SearchDiscoveryView f6562i;

    /* loaded from: classes17.dex */
    class a implements View.OnTouchListener {
        a(BetterSearchShowView betterSearchShowView) {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                com.jd.manto.center.k.f.a(view);
                return false;
            }
            return false;
        }
    }

    public BetterSearchShowView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // com.jd.manto.center.widget.BaseSearchPageView
    public void a() {
        View inflate = ImageUtil.inflate(getContext(), R.layout.manto_center_history_discovery_view, this, true);
        this.f6562i = (SearchDiscoveryView) inflate.findViewById(R.id.miniprogram_discovery);
        this.f6552g = (SearchHistoryTagView) inflate.findViewById(R.id.miniprogram_searchhistory);
        setOnTouchListener(new a(this));
    }

    public void b(ArrayList<MiniProgramSearchHistory> arrayList) {
        if (this.f6552g != null) {
            if (arrayList != null && arrayList.size() > 0) {
                this.f6552g.setVisibility(0);
                this.f6552g.k(arrayList);
                return;
            }
            this.f6552g.setVisibility(8);
        }
    }

    public void c(List<String> list) {
        this.f6553h = list;
        SearchDiscoveryView searchDiscoveryView = this.f6562i;
        if (searchDiscoveryView == null) {
            return;
        }
        searchDiscoveryView.c(list);
    }

    public void d() {
        SearchHistoryTagView searchHistoryTagView = this.f6552g;
        if (searchHistoryTagView != null) {
            searchHistoryTagView.j();
        }
    }

    public void e(e eVar) {
        SearchHistoryTagView searchHistoryTagView = this.f6552g;
        if (searchHistoryTagView != null) {
            searchHistoryTagView.l(eVar);
        }
        SearchDiscoveryView searchDiscoveryView = this.f6562i;
        if (searchDiscoveryView != null) {
            searchDiscoveryView.d(eVar);
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
    }

    public BetterSearchShowView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
