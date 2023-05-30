package com.jd.lib.flexcube.widgets.a;

import android.content.Context;
import android.view.View;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.widgets.entity.ScrollCardEntity;
import com.jd.lib.flexcube.widgets.view.scrollcard.FlexScrollCardView;

/* loaded from: classes15.dex */
public class g extends com.jd.lib.flexcube.iwidget.a.a {
    @Override // com.jd.lib.flexcube.iwidget.a.a
    public View getView(Context context) {
        return new FlexScrollCardView(context);
    }

    @Override // com.jd.lib.flexcube.iwidget.a.a
    public Class<? extends BaseWidgetEntity> getWidgetEntityClass() {
        return ScrollCardEntity.class;
    }
}
