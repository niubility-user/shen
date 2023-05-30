package com.jd.dynamic.lib.expv2.c;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public final class j implements e {
    public static final a b = new a(null);
    private final com.jd.dynamic.lib.expv2.a.f a;

    /* loaded from: classes13.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int a(@NotNull String str) {
            int lastIndexOf$default;
            lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) str, "fun{", 0, false, 6, (Object) null);
            return lastIndexOf$default;
        }

        public final int b(@NotNull String str) {
            int lastIndexOf$default;
            lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) str, "@{", 0, false, 6, (Object) null);
            return lastIndexOf$default;
        }

        public final int c(@NotNull String str) {
            char[] charArray = str.toCharArray();
            Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
            if (str.length() >= 2 && charArray[str.length() - 1] == '{' && charArray[str.length() - 2] == '$') {
                return str.length() - 2;
            }
            return -1;
        }

        public final boolean d(@NotNull String str) {
            boolean contains$default;
            boolean contains$default2;
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, '{', false, 2, (Object) null);
            if (!contains$default) {
                contains$default2 = StringsKt__StringsKt.contains$default((CharSequence) str, '(', false, 2, (Object) null);
                if (!contains$default2) {
                    return false;
                }
            }
            return true;
        }

        public final boolean e(@NotNull String str) {
            boolean contains$default;
            boolean endsWith$default;
            if (str.length() > 2) {
                contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (char) Typography.dollar, false, 2, (Object) null);
                if (contains$default) {
                    endsWith$default = StringsKt__StringsKt.endsWith$default((CharSequence) str, '(', false, 2, (Object) null);
                    return endsWith$default;
                }
                return false;
            }
            return false;
        }
    }

    public j(@NotNull com.jd.dynamic.lib.expv2.a.f fVar) {
        this.a = fVar;
    }

    @Override // com.jd.dynamic.lib.expv2.c.e
    @NotNull
    public i a(@NotNull String str) {
        a aVar = b;
        i dVar = aVar.e(str) ? new d(str) : aVar.c(str) == 0 ? new f(str) : aVar.b(str) == 0 ? new b(str) : aVar.a(str) == 0 ? new c(str) : aVar.d(str) ? new com.jd.dynamic.lib.expv2.c.a(str) : new g(str);
        dVar.d(this.a);
        return dVar;
    }
}
