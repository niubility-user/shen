package com.jingdong.common.XView2.layer.flexcube.aide;

import android.content.Context;
import android.view.View;
import com.jd.lib.flexcube.iwidget.a.a;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.widgets.entity.VideoEntity;
import com.jingdong.common.XView2.layer.flexcube.view.XViewVideoView;

/* loaded from: classes5.dex */
public class XViewVideoViewAide extends a {
    @Override // com.jd.lib.flexcube.iwidget.a.a
    public View getView(Context context) {
        return new XViewVideoView(context);
    }

    @Override // com.jd.lib.flexcube.iwidget.a.a
    public Class<? extends BaseWidgetEntity> getWidgetEntityClass() {
        return VideoEntity.class;
    }

    @Override // com.jd.lib.flexcube.iwidget.a.a
    public boolean useCache() {
        return false;
    }
}
