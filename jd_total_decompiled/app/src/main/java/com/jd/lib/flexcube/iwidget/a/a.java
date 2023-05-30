package com.jd.lib.flexcube.iwidget.a;

import android.content.Context;
import android.view.View;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;

/* loaded from: classes14.dex */
public abstract class a {
    public abstract View getView(Context context);

    public abstract Class<? extends BaseWidgetEntity> getWidgetEntityClass();

    public boolean useCache() {
        return true;
    }
}
