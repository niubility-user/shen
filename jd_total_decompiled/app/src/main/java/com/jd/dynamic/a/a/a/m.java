package com.jd.dynamic.a.a.a;

import com.jd.dynamic.DYConstants;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class m implements h {
    @Override // com.jd.dynamic.a.a.a.h
    @Nullable
    public Object a(@Nullable com.jd.dynamic.a.g gVar, @Nullable String str, @NotNull Object... objArr) {
        if (str == null) {
            return "";
        }
        if (objArr.length == 1) {
            if (Intrinsics.areEqual("clear", objArr[0])) {
                com.jd.dynamic.b.m.d.a.b.b(str);
                return Unit.INSTANCE;
            } else if (objArr[0] instanceof String) {
                com.jd.dynamic.b.m.d.a aVar = com.jd.dynamic.b.m.d.a.b;
                Object obj = objArr[0];
                if (obj != null) {
                    return aVar.a(str, (String) obj);
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            } else {
                return "";
            }
        } else if (objArr.length != 2) {
            DYConstants.DYLog("xpj224 params length : " + objArr.length);
            return "";
        } else {
            if (objArr[0] instanceof String) {
                if (objArr[1] == null) {
                    com.jd.dynamic.b.m.d.a aVar2 = com.jd.dynamic.b.m.d.a.b;
                    Object obj2 = objArr[0];
                    if (obj2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                    }
                    aVar2.d(str, (String) obj2);
                } else {
                    com.jd.dynamic.b.m.d.a aVar3 = com.jd.dynamic.b.m.d.a.b;
                    Object obj3 = objArr[0];
                    if (obj3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                    }
                    aVar3.c(str, (String) obj3, objArr[1]);
                }
            }
            return "";
        }
    }

    @Override // com.jd.dynamic.a.a.a.h
    @NotNull
    public String a() {
        return "scopedCache";
    }
}
