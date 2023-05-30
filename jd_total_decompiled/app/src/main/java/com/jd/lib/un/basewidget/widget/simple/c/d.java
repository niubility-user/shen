package com.jd.lib.un.basewidget.widget.simple.c;

import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/* loaded from: classes16.dex */
public interface d extends com.jd.lib.un.basewidget.widget.simple.d.e {
    void c(@ColorInt int... iArr);

    @RestrictTo({RestrictTo.Scope.LIBRARY, RestrictTo.Scope.LIBRARY_GROUP, RestrictTo.Scope.SUBCLASSES})
    void d(@NonNull e eVar, int i2, int i3);

    @RestrictTo({RestrictTo.Scope.LIBRARY, RestrictTo.Scope.LIBRARY_GROUP, RestrictTo.Scope.SUBCLASSES})
    void e(@NonNull f fVar, int i2, int i3);

    @NonNull
    View getView();

    @RestrictTo({RestrictTo.Scope.LIBRARY, RestrictTo.Scope.LIBRARY_GROUP, RestrictTo.Scope.SUBCLASSES})
    void h(@NonNull f fVar, int i2, int i3);

    @NonNull
    com.jd.lib.un.basewidget.widget.simple.a.b i();

    @RestrictTo({RestrictTo.Scope.LIBRARY, RestrictTo.Scope.LIBRARY_GROUP, RestrictTo.Scope.SUBCLASSES})
    void o(boolean z, float f2, int i2, int i3, int i4);

    @RestrictTo({RestrictTo.Scope.LIBRARY, RestrictTo.Scope.LIBRARY_GROUP, RestrictTo.Scope.SUBCLASSES})
    int q(@NonNull f fVar, boolean z);
}
