package com.jingdong.common.XView2.layer.flexcube.aide;

import android.content.Context;
import android.view.View;
import com.jd.lib.flexcube.iwidget.a.a;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jingdong.common.XView2.entity.XViewWebviewEntity;
import com.jingdong.common.XView2.layer.flexcube.view.XViewWebView;

/* loaded from: classes5.dex */
public class XViewWebviewAide extends a {
    @Override // com.jd.lib.flexcube.iwidget.a.a
    public View getView(Context context) {
        return new XViewWebView(context);
    }

    @Override // com.jd.lib.flexcube.iwidget.a.a
    public Class<? extends BaseWidgetEntity> getWidgetEntityClass() {
        return XViewWebviewEntity.class;
    }

    @Override // com.jd.lib.flexcube.iwidget.a.a
    public boolean useCache() {
        return false;
    }
}
