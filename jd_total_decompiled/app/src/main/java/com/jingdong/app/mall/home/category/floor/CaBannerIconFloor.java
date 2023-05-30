package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.graphics.Rect;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.a;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0004\u001a\u00020\u0003H\u0014\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0014\u00a2\u0006\u0004\b\u0006\u0010\u0005J\u000f\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\u0010"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaBannerIconFloor;", "Lcom/jingdong/app/mall/home/category/floor/CaRecycleLinearFloor;", "Lcom/jingdong/app/mall/home/n/g/a;", "", "M", "()Z", "S", "Lcom/jingdong/app/mall/home/floor/common/f;", "Y", "()Lcom/jingdong/app/mall/home/floor/common/f;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaBannerIconFloor extends CaRecycleLinearFloor<a> {
    public CaBannerIconFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaEventFloor
    protected boolean M() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    protected boolean S() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    @NotNull
    public f Y() {
        getMRecycleView().setClipToPadding(false);
        f fVar = new f(-1, -1);
        fVar.K(new Rect(12, 0, 12, 0));
        return fVar;
    }
}
