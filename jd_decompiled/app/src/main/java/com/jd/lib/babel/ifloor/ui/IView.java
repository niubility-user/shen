package com.jd.lib.babel.ifloor.ui;

import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;

/* loaded from: classes.dex */
public interface IView<T> {
    void initView(String str);

    void update(@NonNull BabelScope babelScope, @NonNull T t, int i2);
}
