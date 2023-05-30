package com.jd.lib.flexcube.owidgets.a;

import android.content.Context;
import android.view.View;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.owidgets.entity.ExchangeButtonEntity;
import com.jd.lib.flexcube.owidgets.view.exchange.ExchangeButton;

/* loaded from: classes15.dex */
public class b extends com.jd.lib.flexcube.iwidget.a.a {
    @Override // com.jd.lib.flexcube.iwidget.a.a
    public View getView(Context context) {
        return new ExchangeButton(context);
    }

    @Override // com.jd.lib.flexcube.iwidget.a.a
    public Class<? extends BaseWidgetEntity> getWidgetEntityClass() {
        return ExchangeButtonEntity.class;
    }
}
