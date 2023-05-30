package com.jd.lib.babel.ifloor.ui;

import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.BaseFloorModel;

/* loaded from: classes.dex */
public interface IFloorView<T extends BaseFloorModel> extends IView<T> {
    void update(@NonNull BabelScope babelScope, @NonNull T t, int i2);
}
