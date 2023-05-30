package com.jd.lib.cashier.sdk.b.i;

import com.jd.lib.cashier.sdk.d.c.a;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class e {
    private static String a;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\n\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2 = {"com/jd/lib/cashier/sdk/b/i/e$a", "Lcom/jd/lib/cashier/sdk/d/c/a$b;", "", "action", "", "data", "", "onEvent", "(Ljava/lang/String;Ljava/lang/Object;)V", "", "isValid", "()Z", "getActionName", "()Ljava/lang/String;", "cashier_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class a implements a.b {
        final /* synthetic */ g a;
        final /* synthetic */ String b;

        a(g gVar, String str) {
            this.a = gVar;
            this.b = str;
        }

        @Override // com.jd.lib.cashier.sdk.d.c.a.b
        @NotNull
        /* renamed from: getActionName  reason: from getter */
        public String getF2882c() {
            return this.b;
        }

        @Override // com.jd.lib.cashier.sdk.d.c.a.b
        public boolean isValid() {
            return true;
        }

        @Override // com.jd.lib.cashier.sdk.d.c.a.b
        public void onEvent(@NotNull String action, @Nullable Object data) {
            if (!(data instanceof Object)) {
                data = null;
            }
            if (data != null) {
                this.a.onEvent(action, data);
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\n\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2 = {"com/jd/lib/cashier/sdk/b/i/e$b", "Lcom/jd/lib/cashier/sdk/d/c/a$b;", "", "action", "", "data", "", "onEvent", "(Ljava/lang/String;Ljava/lang/Object;)V", "", "isValid", "()Z", "getActionName", "()Ljava/lang/String;", "cashier_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class b implements a.b {
        final /* synthetic */ String a;
        final /* synthetic */ Function1 b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f2882c;

        b(String str, Function1 function1, String str2) {
            this.a = str;
            this.b = function1;
            this.f2882c = str2;
        }

        @Override // com.jd.lib.cashier.sdk.d.c.a.b
        @NotNull
        /* renamed from: getActionName  reason: from getter */
        public String getF2882c() {
            return this.f2882c;
        }

        @Override // com.jd.lib.cashier.sdk.d.c.a.b
        public boolean isValid() {
            return true;
        }

        @Override // com.jd.lib.cashier.sdk.d.c.a.b
        public void onEvent(@NotNull String action, @Nullable Object data) {
            if (!(data instanceof Object)) {
                data = null;
            }
            if (data == null || !Intrinsics.areEqual(action, this.a)) {
                return;
            }
            this.b.invoke(data);
        }
    }

    static {
        String c2 = com.jd.lib.cashier.sdk.d.c.a.c();
        Intrinsics.checkExpressionValueIsNotNull(c2, "CashierSyncEventBus.createKey()");
        a = c2;
    }

    @JvmStatic
    public static final void a() {
        String c2 = com.jd.lib.cashier.sdk.d.c.a.c();
        Intrinsics.checkExpressionValueIsNotNull(c2, "CashierSyncEventBus.createKey()");
        a = c2;
    }

    @JvmStatic
    public static final void b() {
        com.jd.lib.cashier.sdk.d.c.a.d(a);
    }

    @JvmStatic
    public static final <T> void c(@NotNull String str, @NotNull g<T> gVar) {
        com.jd.lib.cashier.sdk.d.c.a.f(a).l(new a(gVar, str));
    }

    @JvmStatic
    public static final <T> void d(@NotNull String str, @NotNull String str2, @NotNull Function1<? super T, Unit> function1) {
        com.jd.lib.cashier.sdk.d.c.a.f(a).l(new b(str2, function1, str));
    }

    public static /* synthetic */ void e(String str, String str2, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = "";
        }
        d(str, str2, function1);
    }

    @JvmStatic
    public static final void f(@NotNull String str, @Nullable Object obj) {
        com.jd.lib.cashier.sdk.d.c.a.f(a).k(str, "", obj);
    }

    @JvmStatic
    public static final void g(@NotNull String str, @NotNull String str2, @Nullable Object obj) {
        com.jd.lib.cashier.sdk.d.c.a.f(a).k(str, str2, obj);
    }

    @JvmStatic
    public static final void h(@Nullable String str) {
        com.jd.lib.cashier.sdk.d.c.a.f(a).m(str);
    }
}
