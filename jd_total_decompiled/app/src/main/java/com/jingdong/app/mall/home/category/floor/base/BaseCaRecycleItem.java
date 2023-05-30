package com.jingdong.app.mall.home.category.floor.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.app.mall.home.category.widget.CaRoundBgLayout;
import com.jingdong.app.mall.home.n.g.u.e;
import com.jingdong.app.mall.home.n.g.x.l;
import com.jingdong.app.mall.home.o.a.f;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public abstract class BaseCaRecycleItem<M extends e> extends CaRoundBgLayout implements b<M> {

    /* renamed from: m  reason: collision with root package name */
    protected M f8679m;

    /* renamed from: n  reason: collision with root package name */
    protected com.jingdong.app.mall.home.category.adapter.a f8680n;
    protected int o;
    private int p;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BaseCaRecycleItem.this.k();
        }
    }

    public BaseCaRecycleItem(Context context) {
        super(context);
    }

    protected void b(M m2) {
        setOnClickListener(new a());
    }

    public abstract void e(@NotNull M m2);

    /* JADX INFO: Access modifiers changed from: protected */
    public void f(M m2) {
    }

    public final int g() {
        M m2 = this.f8679m;
        if (m2 == null) {
            return 0;
        }
        return m2.getFloorHeight();
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.b
    public final View getContentView() {
        return this;
    }

    public final int h() {
        M m2 = this.f8679m;
        if (m2 == null) {
            return 0;
        }
        return m2.b();
    }

    protected boolean i() {
        return this.f8679m instanceof l;
    }

    public void j(@NotNull M m2, List<Object> list) {
    }

    public final void k() {
        if (i()) {
            return;
        }
        com.jingdong.app.mall.home.n.h.b.d(getContext(), this.f8679m);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.b
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public final void d(M m2, com.jingdong.app.mall.home.category.adapter.a aVar, int i2) {
        if (m2 == null) {
            return;
        }
        M m3 = this.f8679m;
        if (m3 == m2) {
            m3.a(true);
            return;
        }
        try {
            this.o = i2;
            this.f8680n = aVar;
            this.f8679m = m2;
            if (this.p != com.jingdong.app.mall.home.floor.common.d.f9279g) {
                f(m2);
                this.p = com.jingdong.app.mall.home.floor.common.d.f9279g;
            }
            e(this.f8679m);
            b(this.f8679m);
            this.f8679m.a(false);
        } catch (Exception e2) {
            this.f8679m = null;
            e2.printStackTrace();
            f.o(e2.getMessage());
        }
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.b
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public final void c(M m2, com.jingdong.app.mall.home.category.adapter.a aVar, int i2, List<Object> list) {
        if (this.f8679m != m2) {
            d(m2, aVar, i2);
            return;
        }
        try {
            j(m2, list);
        } catch (Exception e2) {
            e2.printStackTrace();
            f.o(e2.getMessage());
        }
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.b
    public void onViewRecycle() {
    }

    @Override // android.view.View
    public final void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (this.f8679m != null) {
            layoutParams.width = h();
            layoutParams.height = g();
        }
        super.setLayoutParams(layoutParams);
    }
}
