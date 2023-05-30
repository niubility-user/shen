package com.jd.manto.center.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.jd.manto.center.R;
import com.jd.manto.center.k.h;
import com.jingdong.common.entity.MiniProgramSearchHistory;
import java.util.List;

/* loaded from: classes17.dex */
public class d extends com.jd.manto.center.widget.a<MiniProgramSearchHistory> {

    /* renamed from: j */
    private com.jd.manto.center.widget.e f6606j;

    /* renamed from: k */
    private e f6607k;

    /* loaded from: classes17.dex */
    public class a implements View.OnTouchListener {
        a(d dVar) {
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
    public class b implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ MiniProgramSearchHistory f6608g;

        /* renamed from: h */
        final /* synthetic */ com.jd.manto.center.widget.b f6609h;

        b(MiniProgramSearchHistory miniProgramSearchHistory, com.jd.manto.center.widget.b bVar) {
            d.this = r1;
            this.f6608g = miniProgramSearchHistory;
            this.f6609h = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (h.d() || d.this.f6606j == null) {
                return;
            }
            com.jd.manto.center.h.b.k(d.this.f6598g, this.f6608g.getWord());
            d.this.f6606j.e(this.f6608g, this.f6609h.c());
        }
    }

    /* loaded from: classes17.dex */
    public class c implements View.OnClickListener {
        c() {
            d.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (d.this.f6607k != null) {
                com.jd.manto.center.h.b.l(d.this.f6598g, "1");
                d.this.f6607k.b();
            }
        }
    }

    /* renamed from: com.jd.manto.center.widget.d$d */
    /* loaded from: classes17.dex */
    public class ViewOnClickListenerC0183d implements View.OnClickListener {
        ViewOnClickListenerC0183d() {
            d.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (d.this.f6607k != null) {
                com.jd.manto.center.h.b.l(d.this.f6598g, "0");
                d.this.f6607k.a();
            }
        }
    }

    /* loaded from: classes17.dex */
    public interface e {
        void a();

        void b();
    }

    public d(Context context, List<MiniProgramSearchHistory> list, int i2) {
        super(context, list, i2);
    }

    private void g(com.jd.manto.center.widget.b bVar) {
        h.b(bVar.d(R.id.fl_normal_history_container));
        FrameLayout frameLayout = (FrameLayout) bVar.d(R.id.fl_more_history_container);
        h.l(frameLayout);
        ImageView imageView = (ImageView) bVar.d(R.id.img_more_history);
        if (imageView != null) {
            h.f(imageView, R.drawable.manto_center_arrow_down);
            h.a(imageView);
            frameLayout.setOnClickListener(new ViewOnClickListenerC0183d());
        }
    }

    private void h(com.jd.manto.center.widget.b bVar) {
        h.b(bVar.d(R.id.fl_normal_history_container));
        int i2 = R.id.fl_more_history_container;
        h.l(bVar.d(i2));
        int i3 = R.id.img_more_history;
        if (bVar.d(i3) instanceof ImageView) {
            h.f((ImageView) bVar.d(i3), R.drawable.manto_center_arrow_down);
            bVar.d(i2).setOnClickListener(new c());
        }
    }

    @Override // com.jd.manto.center.widget.a
    /* renamed from: f */
    public void a(com.jd.manto.center.widget.b bVar, MiniProgramSearchHistory miniProgramSearchHistory) {
        if (com.jd.manto.center.k.b.a(bVar, miniProgramSearchHistory)) {
            return;
        }
        if (TextUtils.equals(miniProgramSearchHistory.getTag(), "2")) {
            h(bVar);
        } else if (TextUtils.equals(miniProgramSearchHistory.getTag(), "1")) {
            g(bVar);
        } else {
            int i2 = R.id.txt_single_history;
            if (bVar.d(i2) instanceof TextView) {
                h.j((TextView) bVar.d(i2), 12.0f);
            }
            h.b(bVar.d(R.id.fl_more_history_container));
            h.l(bVar.d(R.id.fl_normal_history_container));
            h.l(bVar.d(i2));
            bVar.e(i2, miniProgramSearchHistory.getWord());
            int i3 = R.id.root_view;
            bVar.d(i3).setOnTouchListener(new a(this));
            bVar.d(i3).setOnClickListener(new b(miniProgramSearchHistory, bVar));
        }
    }

    @Override // com.jd.manto.center.widget.a, android.widget.Adapter
    public int getCount() {
        List<T> list = this.f6600i;
        if (list == 0 || list.size() <= 0) {
            return 0;
        }
        return this.f6600i.size();
    }

    public void i(com.jd.manto.center.widget.e eVar, e eVar2) {
        this.f6606j = eVar;
        this.f6607k = eVar2;
    }
}
