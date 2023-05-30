package com.jingdong.app.mall.home.floor.view.widget.catatorytab;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;

/* loaded from: classes4.dex */
public abstract class CategoryTabItemBaseView extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    protected CategoryEntity.CaItem f10246g;

    /* renamed from: h  reason: collision with root package name */
    private b f10247h;

    /* renamed from: i  reason: collision with root package name */
    protected int f10248i;

    /* loaded from: classes4.dex */
    class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f10249g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ View f10250h;

        a(CategoryTabItemBaseView categoryTabItemBaseView, int i2, View view) {
            this.f10249g = i2;
            this.f10250h = view;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            String str = this.f10249g + "";
            this.f10250h.setVisibility(this.f10249g);
        }
    }

    /* loaded from: classes4.dex */
    public interface b {
        void onAnimEnd();
    }

    public CategoryTabItemBaseView(Context context) {
        super(context);
    }

    public abstract void a();

    public void b() {
        b bVar = this.f10247h;
        if (bVar != null) {
            bVar.onAnimEnd();
        }
    }

    protected abstract void c(CategoryEntity.CaItem caItem, int i2);

    public void d(CategoryEntity.CaItem caItem, int i2) {
        this.f10246g = caItem;
        c(caItem, i2);
    }

    public void e(b bVar) {
        this.f10247h = bVar;
    }

    public void f(int i2) {
        this.f10248i = i2;
    }

    public void g(View view, int i2) {
        if (view != null) {
            view.post(new a(this, i2, view));
        }
    }

    public abstract void h();

    public abstract void i();
}
