package com.jd.manto.center.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.manto.center.R;
import com.jd.manto.center.k.h;
import com.jd.manto.center.widget.d;
import com.jingdong.common.database.table.MiniProgramSearchHistoryTable;
import com.jingdong.common.entity.MiniProgramSearchHistory;
import com.jingdong.common.utils.ImageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes17.dex */
public class SearchHistoryTagView extends RelativeLayout implements d.e {

    /* renamed from: g  reason: collision with root package name */
    private TextView f6588g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f6589h;

    /* renamed from: i  reason: collision with root package name */
    private BetterCloudTagView f6590i;

    /* renamed from: j  reason: collision with root package name */
    private int f6591j;

    /* renamed from: k  reason: collision with root package name */
    private d f6592k;

    /* renamed from: l  reason: collision with root package name */
    private e f6593l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements View.OnTouchListener {
        a(SearchHistoryTagView searchHistoryTagView) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f6594g;

        b(Context context) {
            this.f6594g = context;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (h.d()) {
                return;
            }
            com.jd.manto.center.k.f.a(view);
            if (SearchHistoryTagView.this.f6593l != null) {
                com.jd.manto.center.h.b.j(this.f6594g);
                SearchHistoryTagView.this.f6593l.clearHistory();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ List f6596g;

        c(List list) {
            this.f6596g = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f6596g == null) {
                return;
            }
            SearchHistoryTagView searchHistoryTagView = SearchHistoryTagView.this;
            searchHistoryTagView.f6591j = searchHistoryTagView.f6590i.c();
            if (SearchHistoryTagView.this.f6591j > 0) {
                if (!SearchHistoryTagView.this.f6590i.d()) {
                    SearchHistoryTagView.this.m(this.f6596g);
                } else {
                    SearchHistoryTagView.this.n(this.f6596g);
                }
            }
        }
    }

    public SearchHistoryTagView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void i(Context context) {
        View inflate = ImageUtil.inflate(context, R.layout.manto_center_search_history_view, this, true);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_search_history_title);
        this.f6588g = textView;
        h.j(textView, 16.0f);
        this.f6589h = (ImageView) inflate.findViewById(R.id.img_delete_icon);
        this.f6590i = (BetterCloudTagView) inflate.findViewById(R.id.history_tag_view);
        inflate.setOnTouchListener(new a(this));
        this.f6589h.setOnClickListener(new b(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(List<MiniProgramSearchHistory> list) {
        if (this.f6590i != null) {
            ArrayList arrayList = new ArrayList(list);
            int size = arrayList.size();
            int i2 = this.f6591j;
            if (size > i2 && i2 > 0) {
                MiniProgramSearchHistory miniProgramSearchHistory = new MiniProgramSearchHistory("\u66f4\u591a");
                miniProgramSearchHistory.setTag("2");
                arrayList.add(this.f6591j - 1, miniProgramSearchHistory);
                List subList = arrayList.subList(0, this.f6591j);
                d dVar = this.f6592k;
                if (dVar != null) {
                    dVar.c(subList);
                    return;
                }
                return;
            }
            this.f6592k.c(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(List<MiniProgramSearchHistory> list) {
        if (this.f6590i != null) {
            ArrayList arrayList = new ArrayList(list);
            MiniProgramSearchHistory miniProgramSearchHistory = new MiniProgramSearchHistory("\u6536\u8d77");
            miniProgramSearchHistory.setTag("1");
            arrayList.add(this.f6591j - 1, miniProgramSearchHistory);
            List subList = arrayList.subList(0, this.f6591j);
            d dVar = this.f6592k;
            if (dVar != null) {
                dVar.c(subList);
            }
        }
    }

    @Override // com.jd.manto.center.widget.d.e
    public void a() {
        BetterCloudTagView betterCloudTagView = this.f6590i;
        if (betterCloudTagView != null) {
            betterCloudTagView.g(false);
            k(MiniProgramSearchHistoryTable.getAllSearchHistory());
        }
    }

    @Override // com.jd.manto.center.widget.d.e
    public void b() {
        BetterCloudTagView betterCloudTagView = this.f6590i;
        if (betterCloudTagView != null) {
            betterCloudTagView.g(true);
            k(MiniProgramSearchHistoryTable.getAllSearchHistory());
        }
    }

    public void j() {
        BetterCloudTagView betterCloudTagView = this.f6590i;
        if (betterCloudTagView != null) {
            betterCloudTagView.g(false);
        }
    }

    public void k(List<MiniProgramSearchHistory> list) {
        h.f(this.f6589h, R.drawable.manto_searchhistory_delete);
        if (this.f6590i != null) {
            d dVar = this.f6592k;
            if (dVar == null) {
                d dVar2 = new d(getContext(), list, R.layout.manto_center_history_cloud_tag_edit_item);
                this.f6592k = dVar2;
                dVar2.i(this.f6593l, this);
                this.f6590i.g(false);
                this.f6590i.h(2);
                this.f6590i.i(6);
                this.f6590i.f(this.f6592k);
            } else {
                dVar.c(list);
            }
            this.f6590i.post(new c(list));
        }
    }

    public void l(e eVar) {
        this.f6593l = eVar;
    }

    public SearchHistoryTagView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        i(context);
    }
}
