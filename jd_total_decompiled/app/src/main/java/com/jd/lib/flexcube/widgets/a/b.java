package com.jd.lib.flexcube.widgets.a;

import android.content.Context;
import android.view.View;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.widgets.FlexGraphicView;
import com.jd.lib.flexcube.widgets.entity.GraphicEntity;

/* loaded from: classes15.dex */
public class b extends com.jd.lib.flexcube.iwidget.a.a {
    @Override // com.jd.lib.flexcube.iwidget.a.a
    public View getView(Context context) {
        return new FlexGraphicView(context);
    }

    @Override // com.jd.lib.flexcube.iwidget.a.a
    public Class<? extends BaseWidgetEntity> getWidgetEntityClass() {
        return GraphicEntity.class;
    }

    @Override // com.jd.lib.flexcube.iwidget.a.a
    public boolean useCache() {
        return false;
    }
}
