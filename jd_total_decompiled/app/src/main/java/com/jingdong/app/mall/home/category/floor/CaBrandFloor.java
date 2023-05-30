package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.b;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0004\u001a\u00020\u0003H\u0014\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\u0012"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaBrandFloor;", "Lcom/jingdong/app/mall/home/category/floor/CaRecycleGridFloor;", "Lcom/jingdong/app/mall/home/n/g/b;", "", "M", "()Z", "Lcom/jingdong/app/mall/home/floor/common/f;", "R", "()Lcom/jingdong/app/mall/home/floor/common/f;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "", "Lcom/jingdong/app/mall/home/n/b;", "subTypeEnums", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;[Lcom/jingdong/app/mall/home/category/CTypeSubEnum;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaBrandFloor extends CaRecycleGridFloor<b> {
    public CaBrandFloor(@NotNull Context context, @NotNull CaAdapter caAdapter, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
        super(context, caAdapter, bVarArr);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaEventFloor
    protected boolean M() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleGridFloor
    @NotNull
    public f R() {
        return b.C.a();
    }
}
