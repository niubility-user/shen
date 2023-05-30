package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.view.CaMoreLayout;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.b;
import com.jingdong.app.mall.home.n.g.o;
import com.jingdong.app.mall.home.n.g.v.c;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0014\u00a2\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\u000f\u001a\u00020\f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0016\u0010\u0013\u001a\u00020\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0015\u001a\u00020\t8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u000b\u00a8\u0006!"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaMoreIconFloor;", "Lcom/jingdong/app/mall/home/category/floor/CaHorizontalLinearFloor;", "Lcom/jingdong/app/mall/home/n/g/o;", CartConstant.KEY_VENDOR_ITEM, "", "S", "(Lcom/jingdong/app/mall/home/n/g/o;)V", "F", "()V", "", "B", "()Z", "Lcom/jingdong/app/mall/home/floor/common/f;", "z", "Lcom/jingdong/app/mall/home/floor/common/f;", "mMoreTitleSize", "Landroid/widget/TextView;", JshopConst.JSHOP_PROMOTIO_Y, "Landroid/widget/TextView;", "mMoreTitle", "u", "isFloorDisplay", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "", "Lcom/jingdong/app/mall/home/n/b;", "subTypeEnums", "Lcom/jingdong/app/mall/home/n/g/w/b;", "floorInfo", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;[Lcom/jingdong/app/mall/home/category/CTypeSubEnum;Lcom/jingdong/app/mall/home/n/g/w/b;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaMoreIconFloor extends CaHorizontalLinearFloor<o> {

    /* renamed from: y  reason: from kotlin metadata */
    private final TextView mMoreTitle;

    /* renamed from: z  reason: from kotlin metadata */
    private final f mMoreTitleSize;

    public CaMoreIconFloor(@NotNull Context context, @NotNull CaAdapter caAdapter, @NotNull b[] bVarArr, @NotNull com.jingdong.app.mall.home.n.g.w.b bVar) {
        super(context, caAdapter, bVarArr, bVar);
        TextView textView = new TextView(context);
        this.mMoreTitle = textView;
        textView.setMaxLines(1);
        textView.setGravity(16);
        TextPaint paint = textView.getPaint();
        Intrinsics.checkExpressionValueIsNotNull(paint, "mMoreTitle.paint");
        paint.setFakeBoldText(true);
        f fVar = new f(-2, 98);
        this.mMoreTitleSize = fVar;
        fVar.F(new Rect(39, 0, 0, 0));
        addView(textView, fVar.u(textView));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    public boolean B() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaEventFloor
    protected void F() {
        CaMoreLayout j2 = CaMoreLayout.j();
        if (u() && j2 != null && j2.getVisibility() == 0) {
            o oVar = (o) n();
            c e2 = oVar != null ? oVar.e() : null;
            if (e2 == null || e2.A()) {
                return;
            }
            com.jingdong.app.mall.home.n.g.v.b.a(e2);
        }
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaHorizontalLinearFloor, com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: S  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull o item) {
        super.j(item);
        this.mMoreTitle.setTextColor(-16777216);
        this.mMoreTitle.setVisibility(item.F() ? 0 : 4);
        f.c(this.mMoreTitle, this.mMoreTitleSize);
        this.mMoreTitle.setText(item.Q());
        this.mMoreTitle.setTextSize(0, d.d(28));
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    public boolean u() {
        ViewParent parent;
        if (!getIsFloorBind() || n() == 0 || (parent = getParent()) == null) {
            return false;
        }
        com.jingdong.app.mall.home.o.a.f.n(parent);
        View parentView = (View) parent;
        Intrinsics.checkExpressionValueIsNotNull(parentView, "parentView");
        return getBottom() - getHeight() < parentView.getHeight() && getTop() + getHeight() > 0;
    }
}
