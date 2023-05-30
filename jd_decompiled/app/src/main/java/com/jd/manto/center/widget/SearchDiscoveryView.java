package com.jd.manto.center.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.manto.center.R;
import com.jd.manto.center.k.h;
import com.jingdong.common.utils.ImageUtil;
import java.util.List;

/* loaded from: classes17.dex */
public class SearchDiscoveryView extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private TextView f6582g;

    /* renamed from: h  reason: collision with root package name */
    private BetterCloudTagView f6583h;

    /* renamed from: i  reason: collision with root package name */
    private com.jd.manto.center.widget.c f6584i;

    /* renamed from: j  reason: collision with root package name */
    private List<String> f6585j;

    /* renamed from: k  reason: collision with root package name */
    private e f6586k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements View.OnTouchListener {
        a(SearchDiscoveryView searchDiscoveryView) {
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
    public class b implements View.OnTouchListener {
        b(SearchDiscoveryView searchDiscoveryView) {
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

    /* loaded from: classes17.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SearchDiscoveryView.this.f6585j == null) {
            }
        }
    }

    public SearchDiscoveryView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void b(Context context) {
        View inflate = ImageUtil.inflate(context, R.layout.manto_center_search_discovery_view, this, true);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_discovery_title);
        this.f6582g = textView;
        h.j(textView, 15.0f);
        BetterCloudTagView betterCloudTagView = (BetterCloudTagView) inflate.findViewById(R.id.discovery_tag_view);
        this.f6583h = betterCloudTagView;
        betterCloudTagView.setOnTouchListener(new a(this));
        setOnTouchListener(new b(this));
    }

    public void c(List<String> list) {
        if (this.f6583h == null) {
            return;
        }
        if (com.jd.manto.center.k.b.c(list)) {
            h.l(this);
            this.f6585j = list;
            com.jd.manto.center.widget.c cVar = this.f6584i;
            if (cVar == null) {
                com.jd.manto.center.widget.c cVar2 = new com.jd.manto.center.widget.c(getContext(), this.f6585j, R.layout.lib_search_discover_tag_item_layout);
                this.f6584i = cVar2;
                cVar2.f(this.f6586k);
                this.f6583h.g(false);
                this.f6583h.h(5);
                this.f6583h.f(this.f6584i);
            } else {
                cVar.notifyDataSetChanged();
            }
            this.f6583h.post(new c());
            return;
        }
        h.b(this);
    }

    public void d(e eVar) {
        this.f6586k = eVar;
    }

    public SearchDiscoveryView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        b(context);
    }
}
