package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.b;
import com.jingdong.app.mall.home.n.g.l;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0004\u001a\u00020\u0003H\u0014\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0014\u00a2\u0006\u0004\b\u0006\u0010\u0005J\u000f\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\u0013"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaIconFloor;", "Lcom/jingdong/app/mall/home/category/floor/CaRecycleGridFloor;", "Lcom/jingdong/app/mall/home/n/g/l;", "", IShareAdapter.SHARE_ACTION_OPEN, "()Z", "M", "Lcom/jingdong/app/mall/home/floor/common/f;", "R", "()Lcom/jingdong/app/mall/home/floor/common/f;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "", "Lcom/jingdong/app/mall/home/n/b;", "subTypeEnums", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;[Lcom/jingdong/app/mall/home/category/CTypeSubEnum;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaIconFloor extends CaRecycleGridFloor<l> {
    public CaIconFloor(@NotNull Context context, @NotNull CaAdapter caAdapter, @NotNull b[] bVarArr) {
        super(context, caAdapter, bVarArr);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaEventFloor
    protected boolean M() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaEventFloor
    protected boolean O() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleGridFloor
    @NotNull
    public f R() {
        return l.F.a();
    }
}
