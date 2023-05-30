package com.jd.lib.flexcube.owidgets.view.hotzone;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IKnowWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.owidgets.entity.hotzone.HotZoneDataPath;
import com.jd.lib.flexcube.owidgets.entity.hotzone.HotZoneEntity;
import com.jd.lib.flexcube.widgets.b.b;
import com.jd.lib.flexcube.widgets.c.a;
import java.util.Map;

/* loaded from: classes15.dex */
public class HotZoneView extends View implements IWidget<HotZoneEntity>, IKnowWidget<HotZoneEntity> {

    /* renamed from: g  reason: collision with root package name */
    private HotZoneEntity f4434g;

    /* renamed from: h  reason: collision with root package name */
    private float f4435h;

    public HotZoneView(Context context) {
        super(context);
    }

    private void b(Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        ClickEvent a = b.a(map, this.f4434g.dataPath.clickEvent);
        if (a != null) {
            a.b bVar = new a.b(getContext(), a);
            bVar.a(iWidgetCommunication.getBabelScope());
            setOnClickListener(bVar.b());
            return;
        }
        setClickable(false);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IKnowWidget
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public HotZoneEntity getWidgetEntity() {
        return this.f4434g;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void updateStyle(@NonNull HotZoneEntity hotZoneEntity, float f2) {
        this.f4434g = hotZoneEntity;
        this.f4435h = f2;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        setClickable(false);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        HotZoneEntity hotZoneEntity = this.f4434g;
        if (hotZoneEntity != null) {
            return hotZoneEntity.getBaseConfig().getH(this.f4435h);
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        HotZoneEntity hotZoneEntity = this.f4434g;
        if (hotZoneEntity != null) {
            return hotZoneEntity.getBaseConfig().getW(this.f4435h);
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        HotZoneDataPath hotZoneDataPath;
        HotZoneEntity hotZoneEntity = this.f4434g;
        if (hotZoneEntity != null && (hotZoneDataPath = hotZoneEntity.dataPath) != null) {
            if (TextUtils.isEmpty(hotZoneDataPath.clickEvent)) {
                setClickable(false);
                return;
            } else {
                b(map, iWidgetCommunication);
                return;
            }
        }
        clear();
    }
}
