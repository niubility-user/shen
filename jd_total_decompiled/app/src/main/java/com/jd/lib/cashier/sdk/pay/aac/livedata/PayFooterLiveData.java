package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.s;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jingdong.jdsdk.a.a;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\n"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayFooterLiveData;", "Landroidx/lifecycle/LiveData;", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/s;", "Lcom/jd/lib/cashier/sdk/pay/bean/CashierPayEntity;", "cashierPayEntity", "", a.a, "(Lcom/jd/lib/cashier/sdk/pay/bean/CashierPayEntity;)V", "<init>", "()V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class PayFooterLiveData extends LiveData<s> {
    /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c1 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void a(@Nullable CashierPayEntity cashierPayEntity) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7 = false;
        if ((cashierPayEntity != null ? cashierPayEntity.jdPayChannelList : null) != null) {
            List<Payment> list = cashierPayEntity.jdPayChannelList;
            Intrinsics.checkExpressionValueIsNotNull(list, "cashierPayEntity.jdPayChannelList");
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if ((!Intrinsics.areEqual(((Payment) it.next()).status, "3")) != false) {
                        z6 = false;
                        break;
                    }
                }
            }
            z6 = true;
            if (!z6) {
                z = false;
                if ((cashierPayEntity == null ? cashierPayEntity.payChannelList : null) != null) {
                    List<Payment> list2 = cashierPayEntity.payChannelList;
                    Intrinsics.checkExpressionValueIsNotNull(list2, "cashierPayEntity.payChannelList");
                    if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                        Iterator<T> it2 = list2.iterator();
                        while (it2.hasNext()) {
                            if ((!Intrinsics.areEqual(((Payment) it2.next()).status, "3")) != false) {
                                z5 = false;
                                break;
                            }
                        }
                    }
                    z5 = true;
                    if (!z5) {
                        z2 = false;
                        if ((cashierPayEntity != null ? cashierPayEntity.otherPayChannelList : null) != null) {
                            List<Payment> list3 = cashierPayEntity.otherPayChannelList;
                            Intrinsics.checkExpressionValueIsNotNull(list3, "cashierPayEntity.otherPayChannelList");
                            if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                                Iterator<T> it3 = list3.iterator();
                                while (it3.hasNext()) {
                                    if ((!Intrinsics.areEqual(((Payment) it3.next()).status, "3")) != false) {
                                        z4 = false;
                                        break;
                                    }
                                }
                            }
                            z4 = true;
                            if (!z4) {
                                z3 = false;
                                if (((cashierPayEntity != null ? cashierPayEntity.commonPopupInfo : null) == null) && z && z3 && z2) {
                                    z7 = true;
                                }
                                postValue(new s(z7));
                            }
                        }
                        z3 = true;
                        if ((cashierPayEntity != null ? cashierPayEntity.commonPopupInfo : null) == null) {
                            z7 = true;
                        }
                        postValue(new s(z7));
                    }
                }
                z2 = true;
                if ((cashierPayEntity != null ? cashierPayEntity.otherPayChannelList : null) != null) {
                }
                z3 = true;
                if ((cashierPayEntity != null ? cashierPayEntity.commonPopupInfo : null) == null) {
                }
                postValue(new s(z7));
            }
        }
        z = true;
        if ((cashierPayEntity == null ? cashierPayEntity.payChannelList : null) != null) {
        }
        z2 = true;
        if ((cashierPayEntity != null ? cashierPayEntity.otherPayChannelList : null) != null) {
        }
        z3 = true;
        if ((cashierPayEntity != null ? cashierPayEntity.commonPopupInfo : null) == null) {
        }
        postValue(new s(z7));
    }
}
