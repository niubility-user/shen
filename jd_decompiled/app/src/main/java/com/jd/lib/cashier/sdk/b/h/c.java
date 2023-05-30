package com.jd.lib.cashier.sdk.b.h;

import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.b.h.e.e;
import com.jd.lib.cashier.sdk.freindpay.view.FriendPayActivity;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class c {
    private static com.jd.lib.cashier.sdk.b.h.e.a<CashierPayActivity> a;
    private static com.jd.lib.cashier.sdk.b.h.e.a<FriendPayActivity> b;

    @JvmStatic
    public static final <T extends FragmentActivity> void a(@NotNull d<T> dVar) {
        if (dVar instanceof b) {
            if (a == null) {
                a = new e(((b) dVar).a());
            }
            com.jd.lib.cashier.sdk.b.h.e.a<CashierPayActivity> aVar = a;
            if (aVar != null) {
                aVar.b(dVar.b());
            }
        } else if (dVar instanceof a) {
            if (b == null) {
                b = new com.jd.lib.cashier.sdk.b.h.e.c(((a) dVar).a());
            }
            com.jd.lib.cashier.sdk.b.h.e.a<FriendPayActivity> aVar2 = b;
            if (aVar2 != null) {
                aVar2.b(dVar.b());
            }
        }
    }

    @JvmStatic
    public static final void b(@NotNull FragmentActivity fragmentActivity) {
        com.jd.lib.cashier.sdk.b.h.e.a<FriendPayActivity> aVar;
        if (fragmentActivity instanceof CashierPayActivity) {
            com.jd.lib.cashier.sdk.b.h.e.a<CashierPayActivity> aVar2 = a;
            if (aVar2 != null) {
                aVar2.d();
            }
        } else if (!(fragmentActivity instanceof FriendPayActivity) || (aVar = b) == null) {
        } else {
            aVar.d();
        }
    }

    @JvmStatic
    public static final void c(@NotNull FragmentActivity fragmentActivity) {
        com.jd.lib.cashier.sdk.b.h.e.a<FriendPayActivity> aVar;
        if (fragmentActivity instanceof CashierPayActivity) {
            com.jd.lib.cashier.sdk.b.h.e.a<CashierPayActivity> aVar2 = a;
            if (aVar2 != null) {
                if (aVar2 == null) {
                    Intrinsics.throwNpe();
                }
                aVar2.c();
                a = null;
            }
        } else if (!(fragmentActivity instanceof FriendPayActivity) || (aVar = b) == null) {
        } else {
            if (aVar == null) {
                Intrinsics.throwNpe();
            }
            aVar.c();
            b = null;
        }
    }
}
