package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.f.b;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CommonCouponEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u00012\u00020\u0002B\t\b\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0005J\u000f\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0007\u0010\u0005R*\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8F@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR*\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8F@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000f\u00a8\u0006\u0014"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/BankCouponResponse;", "Lcom/jd/lib/cashier/sdk/d/f/b;", "Lcom/jd/lib/cashier/sdk/core/model/ICheckNullObj;", "", "checkCanUseCouponList", "()V", "checkCantUseCouponList", "checkNullObjAndInit", "", "Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CommonCouponEntity;", "cantUseCouponList", "Ljava/util/List;", "getCantUseCouponList", "()Ljava/util/List;", "setCantUseCouponList", "(Ljava/util/List;)V", "canUseCouponList", "getCanUseCouponList", "setCanUseCouponList", "<init>", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class BankCouponResponse extends b implements ICheckNullObj {
    @Nullable
    private List<CommonCouponEntity> canUseCouponList;
    @Nullable
    private List<CommonCouponEntity> cantUseCouponList;

    private final void checkCanUseCouponList() {
        if (getCanUseCouponList() == null) {
            this.canUseCouponList = new ArrayList();
        }
        g0.f(getCanUseCouponList());
        if (getCanUseCouponList() == null) {
            Intrinsics.throwNpe();
        }
        if ((!r0.isEmpty()) == true) {
            List<CommonCouponEntity> canUseCouponList = getCanUseCouponList();
            if (canUseCouponList == null) {
                Intrinsics.throwNpe();
            }
            Iterator<T> it = canUseCouponList.iterator();
            while (it.hasNext()) {
                ((CommonCouponEntity) it.next()).checkNullObjAndInit();
            }
        }
    }

    private final void checkCantUseCouponList() {
        if (getCantUseCouponList() == null) {
            this.cantUseCouponList = new ArrayList();
        }
        g0.f(getCantUseCouponList());
        if (getCantUseCouponList() == null) {
            Intrinsics.throwNpe();
        }
        if ((!r0.isEmpty()) == true) {
            List<CommonCouponEntity> cantUseCouponList = getCantUseCouponList();
            if (cantUseCouponList == null) {
                Intrinsics.throwNpe();
            }
            Iterator<T> it = cantUseCouponList.iterator();
            while (it.hasNext()) {
                ((CommonCouponEntity) it.next()).checkNullObjAndInit();
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        checkCanUseCouponList();
        checkCantUseCouponList();
    }

    @Nullable
    public final List<CommonCouponEntity> getCanUseCouponList() {
        List<CommonCouponEntity> list = this.canUseCouponList;
        if (list != null) {
            Iterator<CommonCouponEntity> it = list.iterator();
            while (it.hasNext()) {
                it.next().setViewType(1);
            }
        }
        return this.canUseCouponList;
    }

    @Nullable
    public final List<CommonCouponEntity> getCantUseCouponList() {
        List<CommonCouponEntity> list = this.cantUseCouponList;
        if (list != null) {
            Iterator<CommonCouponEntity> it = list.iterator();
            while (it.hasNext()) {
                it.next().setViewType(2);
            }
        }
        return this.cantUseCouponList;
    }

    public final void setCanUseCouponList(@Nullable List<CommonCouponEntity> list) {
        this.canUseCouponList = list;
    }

    public final void setCantUseCouponList(@Nullable List<CommonCouponEntity> list) {
        this.cantUseCouponList = list;
    }
}
